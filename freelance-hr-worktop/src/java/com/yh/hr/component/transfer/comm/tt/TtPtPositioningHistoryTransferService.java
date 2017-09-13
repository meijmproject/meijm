package com.yh.hr.component.transfer.comm.tt;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pt.dto.PtPositioningHistoryDTO;
import com.yh.hr.res.pt.service.PtPositioningHistoryService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 业务库任职历史信息转到业务库
 * 
 * @version 1.0, 16/10/26
 */
public class TtPtPositioningHistoryTransferService implements InfoTransferService {

	private PtPositioningHistoryService ptPositioningHistoryService = (PtPositioningHistoryService) SpringBeanUtil.getBean("ptPositioningHistoryService");
	
	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long, java.lang.Long)
	 */
	public void transfer(Long refBizPersonOid, Long bizPersonOid) throws ServiceException {
		List<PtPositioningHistoryDTO> list = ptPositioningHistoryService.listPtPositioningByBizPersonId(refBizPersonOid);
		
		if(CollectionUtils.isNotEmpty(list)) {

			
			String uid = UserContext.getLoginUserID();
			String uname = UserContext.getLoginUserName();
			
			PtPositioningHistoryDTO ptPositioningHistoryDTO = null;
			for (PtPositioningHistoryDTO dto : list) {
				
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
			}
		}
	}

}
