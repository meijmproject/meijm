package com.yh.hr.component.transfer.comm.tb;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pb.dto.PbRewardInfoDTO;
import com.yh.hr.res.pb.service.PbRewardInfoService;
import com.yh.hr.res.pt.dto.PtRewardInfoDTO;
import com.yh.hr.res.pt.service.PtRewardInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 奖励信息转到基础库
 * @date 2016-10-18 
 * @version 1.0 
 */
public class TbRewardInfoTransferService implements InfoTransferService {
	private PtRewardInfoService ptRewardInfoService = (PtRewardInfoService)SpringBeanUtil.getBean("ptRewardInfoService");
	private PbRewardInfoService pbRewardInfoService = (PbRewardInfoService)SpringBeanUtil.getBean("pbRewardInfoService");
	
	public void transfer(Long bizPersonOid, Long personOid)
			throws ServiceException {
    List<PtRewardInfoDTO> list = ptRewardInfoService.findPtRewardInfoByCondition(bizPersonOid);
		
		if (CollectionUtils.isNotEmpty(list)) {

			PbRewardInfoDTO pbRewardInfoDTO = null;
			for (PtRewardInfoDTO dto : list) {
				
				pbRewardInfoDTO = new PbRewardInfoDTO();
				BeanHelper.copyProperties(dto, pbRewardInfoDTO);
				
				pbRewardInfoDTO.setPersonOid(personOid);
				
				pbRewardInfoService.create(pbRewardInfoDTO);
			}
		}
		
	}

}
