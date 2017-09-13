package com.yh.hr.res.pt.queryhelper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.yh.hr.res.pt.bo.PtReviewBizInfo;
import com.yh.hr.res.pt.dto.PtReviewBizInfoDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * @desc 年度考核信息查询工具类
 * @author xiongyx
 */
public class PtReviewBizInfoQueryHelper {
	private static Log log = LogFactory.getLog(PtReviewBizInfoQueryHelper.class);
	
	/**
	 * 查询
	 */	
	public static PtReviewBizInfoDTO get(Long ptReviewBizOid) throws ServiceException{
		//查询数据
		PtReviewBizInfo ptReviewBizInfo = DaoUtil.get(PtReviewBizInfo.class, ptReviewBizOid);
		//po转换为dto
		PtReviewBizInfoDTO serdto = new PtReviewBizInfoDTO();
		return BeanHelper.copyRtnProperties(ptReviewBizInfo, serdto);
	}
	
	/**
	 * 通过taskOid查找
	 * @param bizTaskOid
	 * @return
	 * @throws ServiceException
	 */
	public static PtReviewBizInfo getByTaskOid(Long bizTaskOid) throws ServiceException {
		return DaoUtil.uniqueResult("from PtReviewBizInfo pr where pr.taskOid = ? ", bizTaskOid);
	}

	/**
	 * 删除
	 */
	public static void delete(Long ptReviewBizOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtReviewBizInfo rebi where rebi.ptReviewBizOid = " + ptReviewBizOid);
	}
	
	//检查是否在办
	public static List<String> check(Long unitOid,String years) throws ServiceException {
		StringBuffer sql = new StringBuffer();
		sql.append(" select r.manage_unit_oid from yhc_pt_review_biz_info r,yhc_bt_task t");	
		sql.append(" where t.task_oid=r.task_oid");
		sql.append(" and (t.complete_time is null or t.PRE_BIZ_STATUS_CODE='U302')");
		sql.append(" and r.manage_unit_oid = "+unitOid);
		sql.append(" and r.years = '"+years+"'");
		List<String> list=DaoUtil.findWithSQL(sql.toString());
		return list;
	}
	
	public static String tansferPerson(Long reviewBizInfoOid,Long unitOid,String years) throws DataAccessFailureException {
		CallableStatement cstmt = null;
		Connection conn = null;
		try {
			log.info("执行存储过程批量同步单位人员，开始时间：" + DateUtil.nowString(DateUtil.TIME_PATTERN_DEFAULT));
			String proc = "{call  ReviewInfoPackage.setreviewdetailinfojg(?,?,?)}";
			conn = DataSourceUtils.getConnection(SpringBeanUtil.getJdbcTemplate().getDataSource());
			cstmt = conn.prepareCall(proc);
			cstmt.setLong(1, reviewBizInfoOid);
			cstmt.setLong(2, unitOid);
			cstmt.setString(3, years);
			cstmt.execute();
			cstmt.close();
			log.info("执行存储过程批量同步单位人员，结束时间：" + DateUtil.nowString(DateUtil.TIME_PATTERN_DEFAULT));
		} catch (Exception e) {
			throw new DataAccessFailureException("执行存储过程批量更新某人员单位数据权限失败", e);
		} finally {
			close(cstmt);
			close(conn);
		}
		return null;
	}
	public static String tansferSyPerson(Long reviewBizInfoOid,Long unitOid,String years) throws DataAccessFailureException {
		CallableStatement cstmt = null;
		Connection conn = null;
		try {
			log.info("执行存储过程批量同步单位人员，开始时间：" + DateUtil.nowString(DateUtil.TIME_PATTERN_DEFAULT));
			String proc = "{call  ReviewInfoPackage.setreviewdetailinfosy(?,?,?)}";
			conn = DataSourceUtils.getConnection(SpringBeanUtil.getJdbcTemplate().getDataSource());
			cstmt = conn.prepareCall(proc);
			cstmt.setLong(1, reviewBizInfoOid);
			cstmt.setLong(2, unitOid);
			cstmt.setString(3, years);
			cstmt.execute(); 
			cstmt.close();
			log.info("执行存储过程批量同步单位人员，结束时间：" + DateUtil.nowString(DateUtil.TIME_PATTERN_DEFAULT));
		} catch (Exception e) {
			throw new DataAccessFailureException("执行存储过程批量更新某人员单位数据权限失败", e);
		} finally {
			close(cstmt);
			close(conn);
		}
		return null;
	}
	private static void close(Statement stmt) throws DataAccessFailureException {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			throw new DataAccessFailureException("关闭Statement失败", e);
		}
	}
	private static void close(Connection conn) throws DataAccessFailureException {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new DataAccessFailureException("关闭Connection失败", e);
		}
	}
}