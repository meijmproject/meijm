package com.yh.hr.res.pt.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.pt.bo.PtPositioningInfo;
import com.yh.hr.res.pt.dto.PtPositioningInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringUtil;

/**
 * 公务员任职聘任信息查询工具类（业务）
 * 
 * @author zhengdr
 *
 *         时间:2016-10-9下午03:07:54
 */
public class SyPtPositioningInfoQueryHelper {

	/**
	 * 通过ID获取
	 * 
	 * @param ptPositioningInfoOid
	 * @return
	 * @throws ServiceException
	 */
	public static PtPositioningInfoDTO getPtPositioningInfoDTOById(Long ptPositioningInfoOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtPositioningInfo.class, ptPositioningInfoOid), PtPositioningInfoDTO.class);
	}

	/**
	 * 通过ID获取
	 * 
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtPositioningInfoDTO> listPtPositioningByBizPersonId(Long bizPersonOid) throws ServiceException {
		List<PtPositioningInfoDTO> list = null;

		final StringBuffer hBuffer = new StringBuffer("from PtPositioningInfo pt where  1 =1 ");
		hBuffer.append(" and  pt.bizPersonOid =" + bizPersonOid);// 必须是按人来查询
		hBuffer.append(" order by pt.dutyDate desc");
		list = BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), PtPositioningInfoDTO.class);

		return list;
	}

	/**
	 * 计数
	 * 
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static int countPtPositioningInfoByBizPersonOid(Long bizPersonOid) throws ServiceException {
		String hql = "select count(*) from PtPositioningInfo pt where  pt.bizPersonOid = :bizPersonOid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);

		return DaoUtil.countByCondition(hql, params);
	}

	/**
	 * 通过人 员ID和状态查询任职信息
	 * 
	 * @param bizPersonOid
	 * @param dutyStatus
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtPositioningInfoDTO> findPDByBizPersonOidAndStatus(Long bizPersonOid, String dutyStatus) throws ServiceException {

		Map<String, Object> params = new HashMap<String, Object>();
		final StringBuffer hBuffer = new StringBuffer("from PtPositioningInfo pd where pd.bizPersonOid = :bizPersonOid");
		if (StringUtil.isNotBlank(dutyStatus)) {
			hBuffer.append(" and pd.positioningStatus= :positioningStatus");
			params.put("positioningStatus", dutyStatus);
		}
		params.put("bizPersonOid", bizPersonOid);
		hBuffer.append(" order by pd.dutyDate desc");
		List<PtPositioningInfo> list = DaoUtil.find(hBuffer.toString(), params);
		return BeanHelper.copyProperties(list, PtPositioningInfoDTO.class);

	}

	/**
	 * 根据bizPersonOid删除人员任职信息
	 * 
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtPositioningInfo poi where poi.bizPersonOid = " + bizPersonOid);
	}

	/**
	 * 根据bizPersonOid查找人员任职信息（目前暂定一个业务只有一条拟任信息）
	 * 
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static PtPositioningInfo getByBizPersonOid(Long bizPersonOid) throws ServiceException {
		return DaoUtil.uniqueResult(" from PtPositioningInfo poi where poi.bizPersonOid = " + bizPersonOid);
	}
	
	/*
	 * 通过人 员ID和状态查询任职信息
	 */
	public static List<PtPositioningInfoDTO> findPDByPersonOidAndStatus(	Long bizPersonOid ,
																String dutyStatus) throws ServiceException {
		
			Map<String, Object> params = new HashMap<String, Object>();
			final StringBuffer hBuffer = new StringBuffer("from PtPositioningInfo poi where poi.bizPersonOid = :bizPersonOid");
			if (StringUtil.isNotBlank(dutyStatus)) {
                hBuffer.append(" and poi.positioningStatus= :positioningStatus");
				params.put("positioningStatus", dutyStatus);
            }
			params.put("bizPersonOid", bizPersonOid);
			hBuffer.append(" order by poi.dutyDate desc");
			List<PtPositioningInfo> list = DaoUtil.find(hBuffer.toString(), params);
			return BeanHelper.copyProperties(list, PtPositioningInfoDTO.class);
		
	}
	/*
	 * 根据岗位类型查询任职情况
	 */
	public static List<PtPositioningInfoDTO> findSPdByPersonOidAndStatus(	Long bizPersonOid ,
																		String dutyStatus,String isMPosition) throws ServiceException {
		
			Map<String, Object> params = new HashMap<String, Object>();
			final StringBuffer hBuffer = new StringBuffer("select pd from PtPositioningInfo pd,PtPostInfo pi where pi.positioningOid=pd.ptPositioningInfoOid and pd.bizPersonOid = :bizPersonOid and pi.isMPosition = :isMPosition");
			if (StringUtil.isNotBlank(dutyStatus)) {
				hBuffer.append(" and pd.positioningStatus= :positioningStatus");
				params.put("positioningStatus", dutyStatus);
			}
			params.put("bizPersonOid", bizPersonOid);
			params.put("isMPosition", isMPosition);
			hBuffer.append(" order by pd.dutyDate desc");
			List<PtPositioningInfo> list = DaoUtil.find(hBuffer.toString(), params);
			return BeanHelper.copyProperties(list, PtPositioningInfoDTO.class);
		
	}
}
