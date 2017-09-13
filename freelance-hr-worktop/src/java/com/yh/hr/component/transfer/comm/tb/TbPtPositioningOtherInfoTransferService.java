/**
 * 
 */
package com.yh.hr.component.transfer.comm.tb;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pb.dto.PbPositioningOtherInfoDTO;
import com.yh.hr.res.pb.service.PbPositioningOtherInfoService;
import com.yh.hr.res.pt.dto.PtPositioningOtherInfoDTO;
import com.yh.hr.res.pt.service.PtPositioningOtherService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 业务库其他职务信息转到基础库
 * @author	lihj
 */

public class TbPtPositioningOtherInfoTransferService implements InfoTransferService {
	
	private PtPositioningOtherService ptPositioningOtherService = (PtPositioningOtherService) SpringBeanUtil.getBean("ptPositioningOtherService");
	
	private PbPositioningOtherInfoService pbPositioningOtherInfoService = (PbPositioningOtherInfoService) SpringBeanUtil.getBean("pbPositioningOtherInfoService");;


	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long)
	 */
	public void transfer(Long bizPersonOid, Long personOid) throws ServiceException {
		List<PtPositioningOtherInfoDTO> list = ptPositioningOtherService.listPtPositioningOtherInfoByBizPersonOid(bizPersonOid);
		
		if(CollectionUtils.isNotEmpty(list)) {

			PbPositioningOtherInfoDTO pbPositioningOtherInfoDTO = null;
			for (PtPositioningOtherInfoDTO dto : list) {
				
				pbPositioningOtherInfoDTO = new PbPositioningOtherInfoDTO();
				BeanHelper.copyProperties(dto, pbPositioningOtherInfoDTO);
				
				pbPositioningOtherInfoDTO.setPersonOid(personOid);
				
				pbPositioningOtherInfoService.createPbPositioningOtherInfo(pbPositioningOtherInfoDTO);
			}
		}
	}

}
