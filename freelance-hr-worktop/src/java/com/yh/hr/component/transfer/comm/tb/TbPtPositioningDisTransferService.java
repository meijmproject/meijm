/**
 * 
 */
package com.yh.hr.component.transfer.comm.tb;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pb.bo.PbPositioningInfo;
import com.yh.hr.res.pt.bo.PtPositioningHistory;
import com.yh.hr.res.pt.bo.PtPositioningInfo;
import com.yh.hr.res.pt.dto.PtPositioningDisDTO;
import com.yh.hr.res.pt.queryhelper.PtPositioningHistoryQueryHelper;
import com.yh.hr.res.pt.queryhelper.PtPositioningInfoQueryHelper;
import com.yh.hr.res.pt.service.PtPositioningDisService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 免职信息转到基础库
 * @author	lenghh
 * @version	1.0,	16/11/05
 */

public class TbPtPositioningDisTransferService implements InfoTransferService {
	
	private  PtPositioningDisService ptPositioningDisService = (PtPositioningDisService)SpringBeanUtil.getBean("ptPositioningDisService");
	
	/*
	 * 免掉免职记录对应的基础任职记录
	 */
	public void transfer(Long bizPersonOid, Long personOid) throws ServiceException{
		List<PtPositioningDisDTO> ptPositioningDisDTOs = ptPositioningDisService.listPtPositioningDisByBizPersonOid(bizPersonOid);
		if(CollectionUtils.isNotEmpty(ptPositioningDisDTOs)){
			for (PtPositioningDisDTO ptPositioningDisDTO : ptPositioningDisDTOs) {
				PbPositioningInfo pbPositioningInfo = DaoUtil.get(PbPositioningInfo.class, ptPositioningDisDTO.getPbPositioningOid());
				if(pbPositioningInfo != null){
					pbPositioningInfo.setPositioningStatus(DicConstants.YHRS0026_002);
					//免职相关信息
					pbPositioningInfo.setDisposalFileno(ptPositioningDisDTO.getDisposalFileno());
					pbPositioningInfo.setDisposalReason(ptPositioningDisDTO.getDisposalReason());
					pbPositioningInfo.setDisposalReasonDesc(DicHelper.viewName(DicConstants.YHRS0035, ptPositioningDisDTO.getDisposalReason()));
					pbPositioningInfo.setDisposalUnit(ptPositioningDisDTO.getDisposalUnit());
					if(ptPositioningDisDTO.getEndDateActual() != null){
						pbPositioningInfo.setEndDateActual(ptPositioningDisDTO.getEndDateActual());
						pbPositioningInfo.setEndDate(ptPositioningDisDTO.getEndDate());
					}else{
						PtPositioningInfo ptPositioningInfo = 	PtPositioningInfoQueryHelper.getByBizPersonOid(bizPersonOid);
						if(ptPositioningInfo != null){
							pbPositioningInfo.setEndDateActual(ptPositioningInfo.getDutyDate());
							pbPositioningInfo.setEndDate(ptPositioningInfo.getDutyDate());
						}
					}
					pbPositioningInfo.update();
				}
				
				//得到任职任聘历史
				List<PtPositioningHistory> ptPositioningHistoryList = PtPositioningHistoryQueryHelper.listByPositioningOid(ptPositioningDisDTO.getPbPositioningOid());
				if(CollectionUtils.isNotEmpty(ptPositioningHistoryList)){
					
					for(PtPositioningHistory ptPositioningHistory:ptPositioningHistoryList){
						//修改业务 任职任聘历史 中 在任为不在任
				    	ptPositioningHistory.setPositioningStatus(DicConstants.YHRS0026_002);
				    	
				    	ptPositioningHistory.update();
					}
					
				}
				
			}
		 }
	}
}
