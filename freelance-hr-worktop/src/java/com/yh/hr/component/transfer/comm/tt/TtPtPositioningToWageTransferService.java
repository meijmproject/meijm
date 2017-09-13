package com.yh.hr.component.transfer.comm.tt;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pt.dto.PtPositioningHistoryDTO;
import com.yh.hr.res.pt.dto.PtPositioningInfoDTO;
import com.yh.hr.res.pt.dto.PtPostInfoDTO;
import com.yh.hr.res.pt.dto.PtPostInfoHistoryDTO;
import com.yh.hr.res.pt.service.PtPositioningHistoryService;
import com.yh.hr.res.pt.service.PtPositioningService;
import com.yh.hr.res.pt.service.PtPostInfoHistoryService;
import com.yh.hr.res.pt.service.PtPostInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 业务库任职信息、任职历史信息转到任职历史信息业务库
 * 
 * @version 1.0, 16/10/26
 */
public class TtPtPositioningToWageTransferService implements InfoTransferService{

	private PtPositioningService ptPositioningService = (PtPositioningService)SpringBeanUtil.getBean("ptPositioningService");
	private PtPositioningHistoryService ptPositioningHistoryService = (PtPositioningHistoryService) SpringBeanUtil.getBean("ptPositioningHistoryService");
	
	private PtPostInfoHistoryService ptPostInfoHistoryService = (PtPostInfoHistoryService)SpringBeanUtil.getBean("ptPostInfoHistoryService");
	private PtPostInfoService ptPostInfoService = (PtPostInfoService) SpringBeanUtil.getBean("ptPostInfoService");
	
	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long, java.lang.Long)
	 */
	public void transfer(Long refBizPersonOid, Long bizPersonOid) throws ServiceException {
		
		
		//在聘任职信息转库
		List<PtPositioningInfoDTO> list = ptPositioningService.listPtPositioningByBizPersonId(refBizPersonOid);
		
		if (CollectionUtils.isNotEmpty(list)) {
			
			String uid = UserContext.getLoginUserID();
			String uname = UserContext.getLoginUserName();

			PtPositioningHistoryDTO ptPositioningInfoDTO = null;
			for (PtPositioningInfoDTO dto : list) {
				
				ptPositioningInfoDTO = new PtPositioningHistoryDTO();
				BeanHelper.copyProperties(dto, ptPositioningInfoDTO);
				
				ptPositioningInfoDTO.setPtPositioningHistroyOid(null);
				ptPositioningInfoDTO.setBizPersonOid(bizPersonOid);
				ptPositioningInfoDTO.setPositioningStatus(DicConstants.YHRS0026_001);
				
				ptPositioningInfoDTO.setCreatedByCode(uid);
				ptPositioningInfoDTO.setCreatedByName(uname);
				ptPositioningInfoDTO.setCreatedDate(DateUtil.now());
				ptPositioningInfoDTO.setUpdatedByCode(uid);
				ptPositioningInfoDTO.setUpdatedByName(uname);
				ptPositioningInfoDTO.setUpdatedDate(DateUtil.now());
				
				ptPositioningHistoryService.insertPtPositioning(ptPositioningInfoDTO);
				
				List<PtPostInfoDTO> ptPostInfoDTOs = ptPostInfoService.listPtPostInfoByBizPersonOid(dto.getBizPersonOid());
				//PtPostInfoDTO ptPostInfoDTO = ptPostInfoService.getPtPositInfoByPtPositioningOid(dto.getBizPersonOid(), dto.getPtPositioningInfoOid());
				if(CollectionUtils.isNotEmpty(ptPostInfoDTOs))
				{
					PtPostInfoHistoryDTO ptPostInfoHistoryDTO = new PtPostInfoHistoryDTO();
					BeanHelper.copyProperties(ptPostInfoDTOs.get(0), ptPostInfoHistoryDTO);
					ptPostInfoHistoryDTO.setBizPersonOid(bizPersonOid);
					ptPostInfoHistoryDTO.setPtPositioningHistoryOid(ptPositioningInfoDTO.getPtPositioningHistroyOid());
					//TODO 不知道什么东西不能为空，暂时随便写一个值
					ptPostInfoHistoryDTO.setPostOid(ptPositioningInfoDTO.getPtPositioningHistroyOid());
					ptPostInfoHistoryService.insertPtPostInfo(ptPostInfoHistoryDTO);
				}
			}
		}
		//任职历史转库
		List<PtPositioningHistoryDTO> historyList = ptPositioningHistoryService.listPtPositioningByBizPersonId(refBizPersonOid);
		
		if(CollectionUtils.isNotEmpty(historyList)) {

			
			String uid = UserContext.getLoginUserID();
			String uname = UserContext.getLoginUserName();
			
			PtPositioningHistoryDTO ptPositioningHistoryDTO = null;
			for (PtPositioningHistoryDTO dto : historyList) {
				
				ptPositioningHistoryDTO = new PtPositioningHistoryDTO();
				BeanHelper.copyProperties(dto, ptPositioningHistoryDTO);
				
				ptPositioningHistoryDTO.setPtPositioningHistroyOid(null);
				ptPositioningHistoryDTO.setBizPersonOid(bizPersonOid);
				
				ptPositioningHistoryDTO.setCreatedByCode(uid);
				ptPositioningHistoryDTO.setCreatedByName(uname);
				ptPositioningHistoryDTO.setCreatedDate(DateUtil.now());
				ptPositioningHistoryDTO.setUpdatedByCode(uid);
				ptPositioningHistoryDTO.setUpdatedByName(uname);
				ptPositioningHistoryDTO.setUpdatedDate(DateUtil.now());
				
				ptPositioningHistoryService.insertPtPositioning(ptPositioningHistoryDTO);
				
				PtPostInfoHistoryDTO ptPostInfoDTO = ptPostInfoHistoryService.getByPtPositioningHistoryOid(dto.getPtPositioningHistroyOid());
				if(null != ptPostInfoDTO)
				{
					PtPostInfoHistoryDTO ptPostInfoHistoryDTO = new PtPostInfoHistoryDTO();
					BeanHelper.copyProperties(ptPostInfoDTO, ptPostInfoHistoryDTO);
					ptPostInfoHistoryDTO.setBizPersonOid(bizPersonOid);
					ptPostInfoHistoryDTO.setPtPositioningHistoryOid(ptPositioningHistoryDTO.getPtPositioningHistroyOid());
					ptPostInfoHistoryService.insertPtPostInfo(ptPostInfoHistoryDTO);
				}
			}
		}
		
		
	}

}
