package com.yh.hr.res.pt.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.pt.bo.PtPostInfo;
import com.yh.hr.res.pt.bo.PtPostInfoHistory;
import com.yh.hr.res.pt.dto.PtPostInfoHistoryDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringUtil;

/**
 * 事业单位任职聘任信息查询工具类（业务）
 * @author chenjl
 *
 * @date 2016-11-10
 */
public class PtPostInfoHistoryQueryHelper {

	/**
	 * 通过ID获取
	 * @param ptPostInfoHistoryOid
	 * @return
	 * @throws ServiceException
	 */
	public static PtPostInfoHistoryDTO getPtPostInfoHistoryDTOById(Long ptPostInfoHistoryOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtPostInfoHistory.class, ptPostInfoHistoryOid), PtPostInfoHistoryDTO.class);
	}

	/**
	 * 通过ID获取
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtPostInfoHistoryDTO> listPtPostByBizPersonId(Long bizPersonOid) throws ServiceException {
		List<PtPostInfoHistoryDTO> list = null;
		
			final StringBuffer hBuffer = new StringBuffer("from PtPostInfoHistory pt where  1 =1 ");
			if (StringUtil.isNotNull(bizPersonOid)) {
                hBuffer.append(" and  pt.bizPersonOid =" + bizPersonOid);
            }
			 hBuffer.append(" order by pt.endDate desc");
			list = BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), PtPostInfoHistoryDTO.class);
		
		return list;
	}
	
	/**
	 * 计数
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static int countPtPostInfoHistoryByBizPersonOid(Long bizPersonOid) throws ServiceException
	{
		String hql = "select count(*) from PtPostInfoHistory pt where  pt.bizPersonOid = :bizPersonOid";
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
	public static List<PtPostInfoHistoryDTO> findPDByBizPersonOidAndStatus(	Long bizPersonOid ,
																String dutyStatus) throws ServiceException {
		
			Map<String, Object> params = new HashMap<String, Object>();
			final StringBuffer hBuffer = new StringBuffer("from PtPostInfoHistory pd where pd.bizPersonOid = :bizPersonOid");
			if (StringUtil.isNotBlank(dutyStatus)) {
                hBuffer.append(" and pd.positionStatus= :positionStatus");
				params.put("positionStatus", dutyStatus);
            }
			params.put("bizPersonOid", bizPersonOid);
			hBuffer.append(" order by pd.endDate desc");
			List<PtPostInfoHistory> list = DaoUtil.find(hBuffer.toString(), params);
			return BeanHelper.copyProperties(list, PtPostInfoHistoryDTO.class);
		
	}
	
	/**
	 * 根据bizPersonOid删除人员任职信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtPostInfoHistory poi where poi.bizPersonOid = " + bizPersonOid);
	}
	
	/**
	 * 根据ptPositioningHistoryOid查找人员任职信息（目前暂定一个业务只有一条拟任信息）
	 * @param ptPositioningHistoryOid
	 * @throws ServiceException
	 */
	public static PtPostInfoHistoryDTO getByPtPositioningHistoryOid(Long ptPositioningHistoryOid) throws ServiceException {
		PtPostInfoHistory ptPostInfo = DaoUtil.uniqueResult(" from PtPostInfoHistory poi where poi.ptPositioningHistoryOid = " + ptPositioningHistoryOid);
		return BeanHelper.copyProperties(ptPostInfo, PtPostInfoHistoryDTO.class);
	}
	
	/**
	 * 根据bizPersonOid查找人员任职信息（目前暂定一个业务只有一条拟任信息）
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static PtPostInfoHistory getByBizPersonOid(Long bizPersonOid) throws ServiceException {
		return DaoUtil.uniqueResult(" from PtPostInfoHistory poi where poi.bizPersonOid = " + bizPersonOid);
	}
	
	/**
	 * 更具history表主键查询关联查出postInfo
	 * @param positioningOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtPostInfo> getByPositioningOid(Long positioningOid) throws ServiceException{
		final StringBuffer hBuffer = new StringBuffer("from PtPostInfo pt where  1 =1 ");
		if (StringUtil.isNotNull(positioningOid)) {
            hBuffer.append(" and  pt.positioningOid =" + positioningOid);
        }
		 List<PtPostInfo> list = BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), PtPostInfo.class);
	
	return list;
	}
}
