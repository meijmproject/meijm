/**
 * 
 */
package com.yh.hr.component.transfer.comm.tt;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.pt.dto.PtPositioningOtherInfoDTO;
import com.yh.hr.res.pt.service.PtPositioningOtherService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 业务库其他职务信息转到业务库
 * @author	lihj
 */

public class TtPtPositioningOtherInfoTransferService implements InfoTransferService {
	
	private PtPositioningOtherService ptPositioningOtherService = (PtPositioningOtherService) SpringBeanUtil.getBean("ptPositioningOtherService");
	
	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long)
	 */
	public void transfer(Long refBizPersonOid, Long bizPersonOid) throws ServiceException {
		List<PtPositioningOtherInfoDTO> list = ptPositioningOtherService.listPtPositioningOtherInfoByBizPersonOid(refBizPersonOid);
		
		if(CollectionUtils.isNotEmpty(list)) {

			
			String uid = UserContext.getLoginUserID();
			String uname = UserContext.getLoginUserName();
			
			PtPositioningOtherInfoDTO ptPositioningOtherInfoDTO = null;
			for (PtPositioningOtherInfoDTO dto : list) {
				
				ptPositioningOtherInfoDTO = new PtPositioningOtherInfoDTO();
				BeanHelper.copyProperties(dto, ptPositioningOtherInfoDTO);
				
				ptPositioningOtherInfoDTO.setPtPositioningOtherOid(null);
				ptPositioningOtherInfoDTO.setBizPersonOid(bizPersonOid);
				
				ptPositioningOtherInfoDTO.setCreatedByCode(uid);
				ptPositioningOtherInfoDTO.setCreatedByName(uname);
				ptPositioningOtherInfoDTO.setCreatedDate(DateUtil.now());
				ptPositioningOtherInfoDTO.setUpdatedByCode(uid);
				ptPositioningOtherInfoDTO.setUpdatedByName(uname);
				ptPositioningOtherInfoDTO.setUpdatedDate(DateUtil.now());
				
				ptPositioningOtherService.createPtPositioningOtherInfo(ptPositioningOtherInfoDTO);
			}
		}
	}

}
