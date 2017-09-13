package com.yh.hr.res.pt.service.impl;

import java.util.List;

import com.yh.hr.res.pt.dto.PtPositioningHistoryDTO;
import com.yh.hr.res.pt.queryhelper.PtPositioningHistoryQueryHelper;
import org.apache.commons.collections.CollectionUtils;
import com.yh.component.dictionary.utils.DicHelper;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pt.bo.PtPositioningHistory;
import com.yh.hr.res.pt.service.PtPositioningHistoryService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 任职任聘信息 service实现类（业务）
 * @author zhengdr
 *
 * 时间:2016-10-10上午09:32:48
 */
public class PtPositioningHistoryServiceImpl implements PtPositioningHistoryService {

	/**
	 * 新增任职信息
	 * @param ptPositioningDTO
	 * @throws ServiceException
	 */
	public void insertPtPositioning(PtPositioningHistoryDTO ptPositioningDTO) throws ServiceException {
			
			PtPositioningHistory ptPositioningHistory = BeanHelper.copyProperties(ptPositioningDTO, PtPositioningHistory.class);
			//ptPositioningHistory.setDisposalReasonDesc(DicHelper.viewName(DicConstants.YHRS0035,ptPositioningHistory.getDisposalReason()));
			ptPositioningHistory.setDutyChangeTypeName(DicHelper.viewName(DicConstants.YHRS0034,ptPositioningHistory.getDutyChangeType()));
			ptPositioningHistory.setCreatedByCode(UserContext.getLoginUserID());
			ptPositioningHistory.setCreatedByName(UserContext.getLoginUserName());
			ptPositioningHistory.setCreatedDate(DateUtil.now());
			ptPositioningHistory.save();
			ptPositioningDTO.setPtPositioningHistroyOid(ptPositioningHistory.getPtPositioningHistroyOid());
			//PtPositioningHistoryInnerService.synchroPtPositioning4PbPersonAttach(ptPositioningHistory.getBizPersonOid());
	}

	/**
	 * 更新任职信息
	 * 
	 * @param ptPositioningDTO
	 * @throws ServiceException
	 */
	public void updatePtPositioning(PtPositioningHistoryDTO ptPositioningDTO) throws ServiceException {
			
		PtPositioningHistory ptPositioningHistory = DaoUtil.get(PtPositioningHistory.class, ptPositioningDTO.getPtPositioningHistroyOid());
		if(ptPositioningHistory!=null){
			BeanHelper.copyProperties(ptPositioningDTO, ptPositioningHistory,new String[]{"createdDate","createdByCode","createdByName"});
			//ptPositioningHistory.setDisposalReasonDesc(DicHelper.viewName(DicConstants.YHRS0035, ptPositioningHistory.getDisposalReason()));
			ptPositioningHistory.setDutyChangeTypeName(DicHelper.viewName(DicConstants.YHRS0034, ptPositioningHistory.getDutyChangeType()));
			ptPositioningHistory.setUpdatedByCode(UserContext.getLoginUserID());
			ptPositioningHistory.setUpdatedByName(UserContext.getLoginUserName());
			ptPositioningHistory.setUpdatedDate(DateUtil.now());
			ptPositioningHistory.update();
			//PtPositioningHistoryInnerService.synchroPtPositioning4PbPersonAttach(ptPositioningHistory.getBizPersonOid());
		}
			
	}

	/**
	 * 获取任职信息
	 * @param ptPositioningHistoryOid
	 * @return
	 * @throws ServiceException
	 */
	public PtPositioningHistoryDTO getPtPositioning(Long ptPositioningHistoryOid) throws ServiceException {
	
	    if(null==ptPositioningHistoryOid){
				return null;
		}
	    
		PtPositioningHistory ptPositioningHistory = DaoUtil.get(PtPositioningHistory.class, ptPositioningHistoryOid);
		
		return BeanHelper.copyProperties(ptPositioningHistory, PtPositioningHistoryDTO.class);
		
	}

	/*
	 * (non-Javadoc)
	 * @see PtPositioningService#listPtPositioningByBizPersonId(java.lang.Long)
	 */
	public List<PtPositioningHistoryDTO> listPtPositioningByBizPersonId(Long bizPersonOid) throws ServiceException {
		
		return PtPositioningHistoryQueryHelper.listPtPositioningByBizPersonId(bizPersonOid);
	}
	
	public int  countPtPositioningHistoryByBizPersonOid (Long bizPersonOid) throws ServiceException {
		return PtPositioningHistoryQueryHelper.countPtPositioningHistoryByBizPersonOid(bizPersonOid);
	}
	/*
	 * (non-Javadoc)
	 * @see PtPositioningService#deletePtPositioningHistoryById(java.lang.Long)
	 */
	public void deletePtPositioningHistoryById(Long ptPositioningHistoryOid) throws ServiceException {

		PtPositioningHistory ptPositioningHistory = DaoUtil.get(PtPositioningHistory.class, ptPositioningHistoryOid);
		if(ptPositioningHistory!=null){
			
			ptPositioningHistory.delete();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see PtPositioningService#deletePtPositioningHistoryByIds(java.util.List)
	 */
	public void deletePtPositioningHistoryByIds(List<Long> positioningOids) throws ServiceException {
		//批量删除
		if (CollectionUtils.isNotEmpty(positioningOids)) {
			for (Long positioningOid : positioningOids) {
				this.deletePtPositioningHistoryById(positioningOid);
			}
		}
	}

	
}
