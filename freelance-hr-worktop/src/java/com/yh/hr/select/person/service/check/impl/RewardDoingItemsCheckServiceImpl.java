package com.yh.hr.select.person.service.check.impl;

import com.yh.hr.res.pb.service.PbRewardInfoService;
import com.yh.hr.select.person.dto.PersonSelectDTO;
import com.yh.hr.select.person.service.check.PersonSelectCheckService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 奖励信息验证
 * @author xiongyx
 *
 * 时间:2016-12-7
 */
public class RewardDoingItemsCheckServiceImpl implements PersonSelectCheckService {
	@SuppressWarnings("unused")
	private PbRewardInfoService pbRewardInfoService = (PbRewardInfoService)SpringBeanUtil.getBean("pbRewardInfoService");

	/*
	 * (non-Javadoc)
	 * @see PersonSelectCheckService#check(java.lang.String, java.lang.String, PersonSelectDTO)
	 */
	public String check(String itemCode, String itemCodeNode, PersonSelectDTO person) throws ServiceException {

//		List<PbRewardInfoDTO> list = pbRewardInfoService.findActivePbRewardInfoByPersonOid(person.getPersonOid());		
//		if(CollectionUtils.isEmpty(list)){
//		    return "该人员不存在奖励信息;";
//		}
//		
		return "";
	}

}
