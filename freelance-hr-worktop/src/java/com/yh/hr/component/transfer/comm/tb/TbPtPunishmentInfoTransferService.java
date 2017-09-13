/**
 * 
 */
package com.yh.hr.component.transfer.comm.tb;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.pb.dto.PbPunishmentInfoDTO;
import com.yh.hr.res.pb.service.PbPunishmentInfoService;
import com.yh.hr.res.pt.dto.PtPunishmentInfoDTO;
import com.yh.hr.res.pt.service.PtPunishmentInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 惩处信息转到基础库
 * @author	zhangqp
 * @version	1.0,	16/10/17
 */

public class TbPtPunishmentInfoTransferService implements InfoTransferService {
	
	private PtPunishmentInfoService ptPunishmentInfoService = (PtPunishmentInfoService)SpringBeanUtil.getBean("ptPunishmentInfoService");
	private PbPunishmentInfoService pbPunishmentInfoService = (PbPunishmentInfoService)SpringBeanUtil.getBean("pbPunishmentInfoService");
	

	/* (non-Javadoc)
	 * @see com.yh.hr.component.tansfer.service.impl.InfoTransferService#transfer(java.lang.Long, java.lang.Long)
	 */
	public void transfer(Long bizPersonOid, Long personOid) throws ServiceException {
		List<PtPunishmentInfoDTO> list = ptPunishmentInfoService.findPtPunishmentInfoByBizPersonOid(bizPersonOid);
		
		if (CollectionUtils.isNotEmpty(list)) {

			PbPunishmentInfoDTO pbPunishmentInfoDTO = null;
			for (PtPunishmentInfoDTO dto : list) {
				
				pbPunishmentInfoDTO = new PbPunishmentInfoDTO();
				BeanHelper.copyProperties(dto, pbPunishmentInfoDTO);
				
				pbPunishmentInfoDTO.setPersonOid(personOid);
				
				pbPunishmentInfoService.create(pbPunishmentInfoDTO);
			}
		}
	}

}
