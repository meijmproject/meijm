package com.yh.hr.res.gw.queryhelper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.hr.res.gw.bo.GwInfo;
import com.yh.hr.res.gw.dto.GwInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;

/**
 * 岗位使用情况查询服务类
 * @author liuhw
 * 2016-8-23
 */
public class GwInfoQueryHelper 
{
	/**
	 * 根据条件查询岗位使用情况信息
	 * @param unitOid  单位OID
	 * @param refOid 来源OID
	 * @param refType 来源类型
	 * @param gwLbCode 岗位类别
	 * @param gwLevelCode 岗位级别
	 * @param status 状态
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static GwInfo findGwInfoByCondition(Long unitOid,Long refOid,String refType,String gwLbCode,String gwLevelCode,String status)throws DataAccessFailureException
	{
		StringBuffer sb = new StringBuffer("from GwInfo j where 1 = 1");
		if(null != unitOid)
		{
			sb.append(" and j.unitOid=").append(unitOid);
		}
		if(null != refOid)
		{
			sb.append(" and j.refOid =").append(refOid);
		}
		if(StringUtils.isNotEmpty(refType))
		{
			sb.append(" and j.refType = '").append(refType).append("'");
		}
		if(StringUtils.isNotEmpty(gwLbCode))
		{
			sb.append(" and j.gwLbCode = '").append(gwLbCode).append("'");
		}
		if(StringUtils.isNotEmpty(gwLevelCode))
		{
			sb.append(" and j.gwLevelCode = '").append(gwLevelCode).append("'");
		}
		if(StringUtils.isNotEmpty(status))
		{
			sb.append(" and j.status = '").append(status).append("'");
		}
		List<GwInfo> list = DaoUtil.find(sb.toString());
		if(CollectionUtils.isEmpty(list))
		{
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * 根据条件查询岗位使用情况信息（未释放 or 未解冻 or 未解锁）
	 * @param unitOid  单位OID
	 * @param refOid 来源OID
	 * @param refType 来源类型
	 * @param status 状态
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static List<GwInfo> findGwInfoByCondition(Long unitOid,Long refOid,String refType,String status)throws DataAccessFailureException
	{
		StringBuffer sb = new StringBuffer("from GwInfo j where j.count > 0");
		if(null != unitOid)
		{
			sb.append(" and j.unitOid=").append(unitOid);
		}
		if(null != refOid)
		{
			sb.append(" and j.refOid =").append(refOid);
		}
		if(StringUtils.isNotEmpty(refType))
		{
			sb.append(" and j.refType = '").append(refType).append("'");
		}
		if(StringUtils.isNotEmpty(status))
		{
			sb.append(" and j.status = '").append(status).append("'");
		}
		List<GwInfo> list = DaoUtil.find(sb.toString());
		if(CollectionUtils.isEmpty(list))
		{
			return null;
		}
		return list;
	}

	/**
	 * 查询岗位占用，冻结，锁定数
	 * @return
	 * @throws ServiceException
	 */
	public static List<GwInfoDTO> listGwInfo(Long unitOid,String status)throws DataAccessFailureException
	{
		String sql = "select gw.unit_oid,gw.gw_lb_code,gw.gw_level_code,gw.status,sum(gw.count) from yhc_gw_info gw where gw.unit_oid = "+unitOid+" and gw.status='"+status+"' group by gw.unit_oid, gw.gw_lb_code, gw.gw_level_code, gw.status";
		List<Object[]> list = DaoUtil.findWithSQL(sql.toString());
		return buildGwCountInfo(list);

	}
	/**
	 * 组装查询结果
	 * @param list
	 * @return
	 */
	private static List<GwInfoDTO> buildGwCountInfo(List<Object[]> list) {
		List<GwInfoDTO> ubGwInfo = new ArrayList<GwInfoDTO>();
		if(CollectionUtils.isNotEmpty(list)){
			for (int i = 0; i < list.size(); i++){
				Object[] task = list.get(i);
				GwInfoDTO GwInfoDTO = new GwInfoDTO();
				@SuppressWarnings("unused")
				int j = 0;
				GwInfoDTO.setUnitOid(task[0] == null ? null : Long.parseLong(task[0].toString()));
				GwInfoDTO.setGwLbCode(task[1] == null ? null : task[1].toString());
				GwInfoDTO.setGwLevelCode(task[2] == null ? null : task[2].toString());
				GwInfoDTO.setStatus(task[3] == null ? null : task[3].toString());
				GwInfoDTO.setCount(task[4] == null ? null : Long.parseLong(task[4].toString()));
				ubGwInfo.add(GwInfoDTO);
			}
		}
		return ubGwInfo;
	}
}
