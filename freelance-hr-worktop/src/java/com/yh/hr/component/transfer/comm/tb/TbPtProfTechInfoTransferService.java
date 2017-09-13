/**
 * 
 */
package com.yh.hr.component.transfer.comm.tb;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.pb.dto.PbProfTechInfoDTO;
import com.yh.hr.res.pb.service.PbProfTechInfoService;
import com.yh.hr.res.pt.dto.PtProfTechInfoDTO;
import com.yh.hr.res.pt.service.PtProfTechInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 业务库专业技术资格信息转到基础库
 * @author	lihj
 * @version	1.0,	16/10/29
 */

public class TbPtProfTechInfoTransferService implements InfoTransferService {
	
	private PtProfTechInfoService ptProfTechInfoService = (PtProfTechInfoService) SpringBeanUtil.getBean("ptProfTechInfoService");
	
	private PbProfTechInfoService pbProfTechInfoService = (PbProfTechInfoService) SpringBeanUtil.getBean("pbProfTechInfoService");;


	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long)
	 */
	public void transfer(Long bizPersonOid, Long personOid) throws ServiceException {
		List<PtProfTechInfoDTO> list = ptProfTechInfoService.listPtProfTechInfoByPersonOid(bizPersonOid);
		
		if(CollectionUtils.isNotEmpty(list)) {

			PbProfTechInfoDTO pbProfTechInfoDTO = null;
			for (PtProfTechInfoDTO dto : list) {
				
				pbProfTechInfoDTO = new PbProfTechInfoDTO();
				BeanHelper.copyProperties(dto, pbProfTechInfoDTO);
				
				pbProfTechInfoDTO.setPersonOid(personOid);
				
				pbProfTechInfoService.addPbProfTechInfoDTO(pbProfTechInfoDTO);
			}
		}
	}

}
