/**
 * 
 */
package com.yh.hr.leader.validate.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yh.hr.leader.validate.dto.LdCashCheckDTO;
import com.yh.hr.leader.validate.dto.LdCashCheckResultDTO;
import com.yh.hr.leader.validate.service.LdCheckRuleAbstractService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.ld.dto.LdCashDTO;
import com.yh.hr.res.ld.service.LdCashService;
import com.yh.hr.res.ld.util.LdFlowConstants;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 领导职数检查：职务属性和职务级别验证
 * 
 * @author zhangqp
 * @version 1.0, 16/10/31
 */
public class LdCheckRuleServiceImpl extends LdCheckRuleAbstractService {
	
	private LdCashService ldCashService;

	public void setLdCashService(LdCashService ldCashService) {
		this.ldCashService = ldCashService;
	}
	
	protected List<LdCashCheckResultDTO> getRuleCheckDTO(List<LdCashCheckDTO> ldCashCheckDTOs) throws ServiceException {
		
		List<LdCashCheckResultDTO> list = new ArrayList<LdCashCheckResultDTO>();
		if (CollectionUtils.isNotEmpty(ldCashCheckDTOs)) {
			LdCashCheckResultDTO ldCashCheckResultDTO = null;
			
			for (LdCashCheckDTO dto : ldCashCheckDTOs) {
				ldCashCheckResultDTO = new LdCashCheckResultDTO();
				BeanHelper.copyProperties(dto, ldCashCheckResultDTO);
//				ldCashCheckResultDTO.setRuleDesc("领导职数检查");
				list.add(ldCashCheckResultDTO);
			}
		}
		
		return list;
	}
	
	protected Long getFreeCount(LdCashCheckResultDTO dto) throws ServiceException {
		LdCashDTO ldCashDTO = new LdCashDTO();
		ldCashDTO.setAccountType(LdFlowConstants.ACCOUNT_TYPE_1);// 单位
		ldCashDTO.setAccountCode(String.valueOf(dto.getUnitOid()));
		ldCashDTO.setDutyAttribute(dto.getDutyAttribute());
		ldCashDTO.setDutyLevel(dto.getDutyLevel());

		List<LdCashDTO> list = ldCashService.findLdCash(ldCashDTO);

		return CollectionUtils.isEmpty(list) ? 0 : list.get(0).getFreeCount();
	}
	
}
