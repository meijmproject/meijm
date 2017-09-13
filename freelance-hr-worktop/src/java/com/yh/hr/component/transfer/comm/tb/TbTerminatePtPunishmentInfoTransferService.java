/**
 * 
 */
package com.yh.hr.component.transfer.comm.tb;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pb.service.PbPunishmentInfoService;
import com.yh.hr.res.pt.dto.PtPersonDTO;
import com.yh.hr.res.pt.dto.PtPunishmentInfoDTO;
import com.yh.hr.res.pt.service.PtPersonService;
import com.yh.hr.res.pt.service.PtPunishmentInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 撤销惩处信息转到基础库
 * @author	xiongyx
 * @version	1.0,	16/12/2
 */

public class TbTerminatePtPunishmentInfoTransferService implements InfoTransferService {
	
	private PtPunishmentInfoService ptPunishmentInfoService = (PtPunishmentInfoService)SpringBeanUtil.getBean("ptPunishmentInfoService");
	@SuppressWarnings("unused")
	private PbPunishmentInfoService pbPunishmentInfoService = (PbPunishmentInfoService)SpringBeanUtil.getBean("pbPunishmentInfoService");
	private PtPersonService ptPersonService = (PtPersonService)SpringBeanUtil.getBean("ptPersonService");

	/* (non-Javadoc)
	 * @see com.yh.hr.component.tansfer.service.impl.InfoTransferService#transfer(java.lang.Long, java.lang.Long)
	 */
	public void transfer(Long bizPersonOid, Long personOid) throws ServiceException {
		@SuppressWarnings("unused")
		PtPersonDTO ptPersonDTO = ptPersonService.getPtPersonDTO(bizPersonOid);
		List<PtPunishmentInfoDTO> pt = ptPunishmentInfoService.findPtPunishmentInfoByBizPersonOid(bizPersonOid);
//		List<PbPunishmentInfoDTO> listPb = pbPunishmentInfoService.findPbPunishmentInfoByCondition(ptPersonDTO.getPersonOid());
		if (CollectionUtils.isNotEmpty(pt)) {
			PtPunishmentInfoDTO ptDTO = new PtPunishmentInfoDTO();
			for (PtPunishmentInfoDTO dto : pt) {
				if(null == dto.getPunishmentOid()){
					BeanHelper.copyProperties(dto, ptDTO);
				}
			}
//			for (PtPunishmentInfoDTO dto : pt) {
//				for (PbPunishmentInfoDTO pb : listPb) {
//					if(null != dto.getPunishmentOid()){
//						if(dto.getPunishmentOid().longValue() == pb.getPunishmentOid().longValue()){
//							pb.setUnchainPunishmentDate(ptDTO.getUnchainPunishmentDate());
//							pb.setUnchainPunishmentFileno(ptDTO.getUnchainPunishmentFileno());
//							pb.setUnchainPunishmentReason(ptDTO.getUnchainPunishmentReason());
//							pb.setEndApprovalUnit(ptDTO.getEndApprovalUnit());		
//							pbPunishmentInfoService.update(pb);
//						}
//					}
//				}
//			}
		}
	}

}
