package com.yh.hr.res.pt.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.yh.hr.res.pt.bo.PtPositioningHistory;
import com.yh.hr.res.pt.dto.PtTalentPostInfoOperateDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringUtil;

/**
 * 公务员任职聘任信息查询工具类（业务）
 * @author zhengdr
 *
 * 时间:2016-10-9下午03:07:54
 */
public class PtTalentPostInfoOperateQueryHelper {

	/**
	 * 通过ID获取
	 * @param ptPositioningHistoryOid
	 * @return
	 * @throws ServiceException
	 */
	public static PtTalentPostInfoOperateDTO getPtTalentPostInfoOperateDTOById(Long ptPositioningHistoryOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtPositioningHistory.class, ptPositioningHistoryOid), PtTalentPostInfoOperateDTO.class);
	}

	/**
	 * 通过ID获取
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtTalentPostInfoOperateDTO> listPtPostHistoryByBizPersonId(Long bizPersonOid) throws ServiceException {
		List<PtTalentPostInfoOperateDTO> list = null;
		
			final StringBuffer hBuffer = new StringBuffer("from PtPostInfoHistory pt where  1 =1 ");
			if (StringUtil.isNotNull(bizPersonOid)) {
                hBuffer.append(" and  pt.bizPersonOid =" + bizPersonOid);
            }
			 hBuffer.append(" order by pt.beginDate desc");
			list = BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), PtTalentPostInfoOperateDTO.class);
		
		return list;
	}
	
	/**
	 * 计数
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static int countPtPositioningHistoryByBizPersonOid(Long bizPersonOid) throws ServiceException
	{
		String hql = "select count(*) from PtPositioningHistory pt where  pt.bizPersonOid = :bizPersonOid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		
		return DaoUtil.countByCondition(hql, params);
	}
	
	/**
	 * 通过人 员ID和状态查询任职信息
	 * @param bizPersonOid
	 * @param dutyStatus
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtTalentPostInfoOperateDTO> findPDByBizPersonOidAndStatus(	Long bizPersonOid ,
																String dutyStatus) throws ServiceException {
		
			Map<String, Object> params = new HashMap<String, Object>();
			final StringBuffer hBuffer = new StringBuffer("from PtPositioningHistory pd where pd.bizPersonOid = :bizPersonOid");
			if (StringUtil.isNotBlank(dutyStatus)) {
                hBuffer.append(" and pd.positioningStatus= :positioningStatus");
				params.put("positioningStatus", dutyStatus);
            }
			params.put("bizPersonOid", bizPersonOid);
			hBuffer.append(" order by pd.dutyDate desc");
			List<PtPositioningHistory> list = DaoUtil.find(hBuffer.toString(), params);
			return BeanHelper.copyProperties(list, PtTalentPostInfoOperateDTO.class);
		
	}
	
	/**
	 * 根据bizPersonOid删除人员任职信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtPositioningHistory poi where poi.bizPersonOid = " + bizPersonOid);
	}
	
	/**
	 * 根据bizPersonOid查找人员任职信息（目前暂定一个业务只有一条拟任信息）
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static PtPositioningHistory getByBizPersonOid(Long bizPersonOid) throws ServiceException {
		return DaoUtil.uniqueResult(" from PtPositioningHistory poi where poi.bizPersonOid = " + bizPersonOid);
	}
}
