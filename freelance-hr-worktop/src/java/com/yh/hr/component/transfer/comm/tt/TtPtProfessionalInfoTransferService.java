/**
 * 
 */
package com.yh.hr.component.transfer.comm.tt;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pt.dto.PtProfessionalInfoDTO;
import com.yh.hr.res.pt.service.PtProfessionalInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 业务库职业（执业）资格信息转到业务库
 * @author	lihj
 * @version	1.0,	16/10/29
 */

public class TtPtProfessionalInfoTransferService implements InfoTransferService {
	
	private PtProfessionalInfoService ptProfessionalInfoService = (PtProfessionalInfoService) SpringBeanUtil.getBean("ptProfessionalInfoService");
	
	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long)
	 */
	public void transfer(Long refBizPersonOid, Long bizPersonOid) throws ServiceException {
		List<PtProfessionalInfoDTO> list = ptProfessionalInfoService.listPtProfessionalInfoByPersonOid(refBizPersonOid);
		
		if(CollectionUtils.isNotEmpty(list)) {

			
			String uid = UserContext.getLoginUserID();
			String uname = UserContext.getLoginUserName();
			
			PtProfessionalInfoDTO ptProfessionalInfoDTO = null;
			for (PtProfessionalInfoDTO dto : list) {
				
				ptProfessionalInfoDTO = new PtProfessionalInfoDTO();
				BeanHelper.copyProperties(dto, ptProfessionalInfoDTO);
				
				ptProfessionalInfoDTO.setPtProfessionalOid(null);
				ptProfessionalInfoDTO.setBizPersonOid(bizPersonOid);
				
				ptProfessionalInfoDTO.setCreatedByCode(uid);
				ptProfessionalInfoDTO.setCreatedByName(uname);
				ptProfessionalInfoDTO.setCreatedDate(DateUtil.now());
				ptProfessionalInfoDTO.setUpdatedByCode(uid);
				ptProfessionalInfoDTO.setUpdatedByName(uname);
				ptProfessionalInfoDTO.setUpdatedDate(DateUtil.now());
				
				ptProfessionalInfoService.createPtProfessionalInfo(ptProfessionalInfoDTO);
			}
		}
	}

}
