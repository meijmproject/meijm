package com.yh.hr.component.transfer.comm.tt;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pt.dto.PtRewardInfoDTO;
import com.yh.hr.res.pt.service.PtRewardInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 业务库奖励信息转到业务库
 * @date 2016-10-18 
 * @version 1.0 
 */
public class TtPtRewardInfoTransferService implements InfoTransferService {

	private PtRewardInfoService ptRewardInfoService = (PtRewardInfoService)SpringBeanUtil.getBean("ptRewardInfoService");

	public void transfer(Long refBizPersonOid, Long bizPersonOid) throws ServiceException {
		List<PtRewardInfoDTO>   list = ptRewardInfoService.findPtRewardInfoByCondition(refBizPersonOid);
		
		if (CollectionUtils.isNotEmpty(list)) {
			
			String uid = UserContext.getLoginUserID();
			String uname = UserContext.getLoginUserName();

			PtRewardInfoDTO ptRewardInfoDTO = null;
			for (PtRewardInfoDTO dto : list) {
				
				ptRewardInfoDTO = new PtRewardInfoDTO();
				BeanHelper.copyProperties(dto, ptRewardInfoDTO);
				
				ptRewardInfoDTO.setPtRewardOid(null);
				ptRewardInfoDTO.setBizPersonOid(bizPersonOid);
				
				ptRewardInfoDTO.setCreateBy(uid);
				ptRewardInfoDTO.setCreateName(uname);
				ptRewardInfoDTO.setCreateDate(DateUtil.now());
				ptRewardInfoDTO.setUpdateBy(uid);
				ptRewardInfoDTO.setUpdateName(uname);
				ptRewardInfoDTO.setUpdateDate(DateUtil.now());
				
				ptRewardInfoService.create(ptRewardInfoDTO);
			}
		}
	}
}
