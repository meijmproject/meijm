/**
 * 
 */
package com.yh.hr.component.transfer.comm.tt;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pt.dto.PtProfTechInfoDTO;
import com.yh.hr.res.pt.service.PtProfTechInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 业务库专业技术资格信息转到业务库
 * @author	lihj
 * @version	1.0,	16/10/29
 */

public class TtPtProfTechInfoTransferService implements InfoTransferService {
	
	private PtProfTechInfoService ptProfTechInfoService = (PtProfTechInfoService) SpringBeanUtil.getBean("ptProfTechInfoService");
	
	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long)
	 */
	public void transfer(Long refBizPersonOid, Long bizPersonOid) throws ServiceException {
		List<PtProfTechInfoDTO> list = ptProfTechInfoService.listPtProfTechInfoByPersonOid(refBizPersonOid);
		
		if(CollectionUtils.isNotEmpty(list)) {

			
			String uid = UserContext.getLoginUserID();
			String uname = UserContext.getLoginUserName();
			
			PtProfTechInfoDTO ptProfTechInfoDTO = null;
			for (PtProfTechInfoDTO dto : list) {
				
				ptProfTechInfoDTO = new PtProfTechInfoDTO();
				BeanHelper.copyProperties(dto, ptProfTechInfoDTO);
				
				ptProfTechInfoDTO.setPtProfTechOid(null);
				ptProfTechInfoDTO.setBizPersonOid(bizPersonOid);
				
				ptProfTechInfoDTO.setCreatedByCode(uid);
				ptProfTechInfoDTO.setCreatedByName(uname);
				ptProfTechInfoDTO.setCreatedDate(DateUtil.now());
				ptProfTechInfoDTO.setUpdatedByCode(uid);
				ptProfTechInfoDTO.setUpdatedByName(uname);
				ptProfTechInfoDTO.setUpdatedDate(DateUtil.now());
				
				ptProfTechInfoService.createPtProfTechInfo(ptProfTechInfoDTO);
			}
		}
	}

}
