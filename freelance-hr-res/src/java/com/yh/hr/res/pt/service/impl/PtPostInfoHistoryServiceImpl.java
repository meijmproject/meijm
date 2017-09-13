package com.yh.hr.res.pt.service.impl;

import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pt.bo.PtPostInfoHistory;
import com.yh.hr.res.pt.dto.PtPostInfoHistoryDTO;
import com.yh.hr.res.pt.queryhelper.PtPostInfoHistoryQueryHelper;
import com.yh.hr.res.pt.service.PtPostInfoHistoryService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * @author admin
 *
 */
public class PtPostInfoHistoryServiceImpl implements PtPostInfoHistoryService {
	/**
	 * 新增任职信息
	 * @param ptPostInfoDTO
	 * @throws ServiceException
	 */
	public void insertPtPostInfo(PtPostInfoHistoryDTO ptPostInfoDTO) throws ServiceException {
			
			PtPostInfoHistory ptPostInfoHistory = BeanHelper.copyProperties(ptPostInfoDTO, PtPostInfoHistory.class);
			//ptPostInfoHistory.setDisposalReasonDesc(DicHelper.viewName(DicConstants.YHRS0035,ptPostInfoHistory.getDisposalReason()));
			ptPostInfoHistory.setCreatedByCode(UserContext.getLoginUserID());
			ptPostInfoHistory.setCreatedByName(UserContext.getLoginUserName());
			ptPostInfoHistory.setCreatedDate(DateUtil.now());
			ptPostInfoHistory.save();
			ptPostInfoDTO.setPtPostHistoryOid(ptPostInfoHistory.getPtPostHistoryOid());
			//PtPostInfoHistoryInnerService.synchroPtPostInfo4PbPersonAttach(ptPostInfoHistory.getBizPersonOid());
	}

	/**
	 * 更新任职信息
	 * 
	 * @param ptPostInfoDTO
	 * @throws ServiceException
	 */
	public void updatePtPostInfo(PtPostInfoHistoryDTO ptPostInfoDTO) throws ServiceException {
			
		PtPostInfoHistory ptPostInfoHistory = DaoUtil.get(PtPostInfoHistory.class, ptPostInfoDTO.getPtPostHistoryOid());
		if(ptPostInfoHistory!=null){
			BeanHelper.copyProperties(ptPostInfoDTO, ptPostInfoHistory,new String[]{"createdDate","createdByCode","createdByName"});
			//ptPostInfoHistory.setDisposalReasonDesc(DicHelper.viewName(DicConstants.YHRS0035, ptPostInfoHistory.getDisposalReason()));
			ptPostInfoHistory.setUpdatedByCode(UserContext.getLoginUserID());
			ptPostInfoHistory.setUpdatedByName(UserContext.getLoginUserName());
			ptPostInfoHistory.setUpdatedDate(DateUtil.now());
			ptPostInfoHistory.update();
			//PtPostInfoHistoryInnerService.synchroPtPostInfo4PbPersonAttach(ptPostInfoHistory.getBizPersonOid());
		}
			
	}

	/**
	 * 获取任职信息
	 * @param ptPostInfoHistoryOid
	 * @return
	 * @throws ServiceException
	 */
	public PtPostInfoHistoryDTO getPtPostInfo(Long ptPostInfoHistoryOid) throws ServiceException {
	
	    if(null==ptPostInfoHistoryOid){
				return null;
		}
	    
		PtPostInfoHistory ptPostInfoHistory = DaoUtil.get(PtPostInfoHistory.class, ptPostInfoHistoryOid);
		
		return BeanHelper.copyProperties(ptPostInfoHistory, PtPostInfoHistoryDTO.class);
		
	}

	/*
	 * (non-Javadoc)
	 * @see PtPostInfoService#listPtPostInfoByBizPersonId(java.lang.Long)
	 */
	public List<PtPostInfoHistoryDTO> listPtPostInfoByBizPersonId(Long bizPersonOid) throws ServiceException {
		
		return PtPostInfoHistoryQueryHelper.listPtPostByBizPersonId(bizPersonOid);
	}
	
	public int  countPtPostInfoHistoryByBizPersonOid (Long bizPersonOid) throws ServiceException {
		return PtPostInfoHistoryQueryHelper.countPtPostInfoHistoryByBizPersonOid(bizPersonOid);
	}
	/*
	 * (non-Javadoc)
	 * @see PtPostInfoService#deletePtPostInfoHistoryById(java.lang.Long)
	 */
	public void deletePtPostInfoHistoryById(Long ptPostInfoHistoryOid) throws ServiceException {

		PtPostInfoHistory ptPostInfoHistory = DaoUtil.get(PtPostInfoHistory.class, ptPostInfoHistoryOid);
		if(ptPostInfoHistory!=null){
			
			ptPostInfoHistory.delete();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see PtPostInfoService#deletePtPostInfoHistoryByIds(java.util.List)
	 */
	public void deletePtPostInfoHistoryByIds(List<Long> positioningOids) throws ServiceException {
		//批量删除
		if (CollectionUtils.isNotEmpty(positioningOids)) {
			for (Long positioningOid : positioningOids) {
				this.deletePtPostInfoHistoryById(positioningOid);
			}
		}
	}
	/**
	 * 根据ptPositioningHistoryOid查找人员任职信息（目前暂定一个业务只有一条拟任信息）
	 * @param ptPositioningHistoryOid
	 * @throws ServiceException
	 */
	public PtPostInfoHistoryDTO getByPtPositioningHistoryOid(Long ptPositioningHistoryOid) throws ServiceException
	{
		return PtPostInfoHistoryQueryHelper.getByPtPositioningHistoryOid(ptPositioningHistoryOid);
	}
	/* (non-Javadoc)
	 * @see PtPostInfoHistoryService#getByBizPersonOid(java.lang.Long)
	 */
	public List<PtPostInfoHistoryDTO> getByBizPersonOid(Long bizPersonOid) throws ServiceException
	{
		return PtPostInfoHistoryQueryHelper.findPDByBizPersonOidAndStatus(bizPersonOid, DicConstants.YHRS0026_001);
	}
}
