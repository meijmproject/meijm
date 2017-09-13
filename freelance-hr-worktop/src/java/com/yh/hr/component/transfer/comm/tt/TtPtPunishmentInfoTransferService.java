package com.yh.hr.component.transfer.comm.tt;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pt.dto.PtPunishmentInfoDTO;
import com.yh.hr.res.pt.service.PtPunishmentInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 业务库惩处信息转到业务库
 * @date 2016-10-18 
 * @version 1.0 
 */
public class TtPtPunishmentInfoTransferService implements InfoTransferService {

	private PtPunishmentInfoService ptPunishmentInfoService = (PtPunishmentInfoService)SpringBeanUtil.getBean("ptPunishmentInfoService");

	public void transfer(Long refBizPersonOid, Long bizPersonOid) throws ServiceException {
		List<PtPunishmentInfoDTO> list = ptPunishmentInfoService.findPtPunishmentInfoByBizPersonOid(refBizPersonOid);
		
		if (CollectionUtils.isNotEmpty(list)) {
			
			String uid = UserContext.getLoginUserID();
			String uname = UserContext.getLoginUserName();

			PtPunishmentInfoDTO ptPunishmentInfoDTO = null;
			for (PtPunishmentInfoDTO dto : list) {
				
				ptPunishmentInfoDTO = new PtPunishmentInfoDTO();
				BeanHelper.copyProperties(dto, ptPunishmentInfoDTO);
				
				ptPunishmentInfoDTO.setPtPunishmentOid(null);
				ptPunishmentInfoDTO.setBizPersonOid(bizPersonOid);
				
				ptPunishmentInfoDTO.setCreatedByCode(uid);
				ptPunishmentInfoDTO.setCreatedByName(uname);
				ptPunishmentInfoDTO.setCreatedDate(DateUtil.now());
				ptPunishmentInfoDTO.setUpdatedByCode(uid);
				ptPunishmentInfoDTO.setUpdatedByName(uname);
				ptPunishmentInfoDTO.setUpdatedDate(DateUtil.now());
				
				ptPunishmentInfoService.create(ptPunishmentInfoDTO);
			}
		}
	}
}
