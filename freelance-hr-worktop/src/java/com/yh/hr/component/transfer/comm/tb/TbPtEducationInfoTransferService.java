/**
 * 
 */
package com.yh.hr.component.transfer.comm.tb;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pb.dto.PbEducationInfoDTO;
import com.yh.hr.res.pb.service.PbEducationInfoService;
import com.yh.hr.res.pt.dto.PtEducationInfoDTO;
import com.yh.hr.res.pt.service.PtEducationInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 业务库学历信息转到基础库
 * @author	zhangqp
 * @version	1.0,	16/10/17
 */

public class TbPtEducationInfoTransferService implements InfoTransferService {
	
	private PtEducationInfoService ptEducationInfoService = (PtEducationInfoService) SpringBeanUtil.getBean("ptEducationInfoService");
	
	private PbEducationInfoService pbEducationInfoService = (PbEducationInfoService) SpringBeanUtil.getBean("pbEducationInfoService");;


	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long)
	 */
	public void transfer(Long bizPersonOid, Long personOid) throws ServiceException {
		List<PtEducationInfoDTO> list = ptEducationInfoService.listPtEducationInfoByBizPersonOid(bizPersonOid);
		
		if(CollectionUtils.isNotEmpty(list)) {

			PbEducationInfoDTO pbEducationInfoDTO = null;
			for (PtEducationInfoDTO dto : list) {
				
				pbEducationInfoDTO = new PbEducationInfoDTO();
				BeanHelper.copyProperties(dto, pbEducationInfoDTO);
				
				pbEducationInfoDTO.setPersonOid(personOid);
				
				pbEducationInfoService.createPbEducationInfo(pbEducationInfoDTO);
			}
		}
	}

}
