/**
 * 
 */
package com.yh.hr.leader.validate.service;

import java.util.List;

import com.yh.hr.leader.validate.dto.LdCashCheckResultDTO;
import org.apache.commons.lang.StringUtils;

import com.yh.hr.leader.validate.dto.LdCashCheckDTO;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.StringUtil;

/**
 * 领导职数检查：职务属性和职务级别验证
 * 
 * @author zhangqp
 * @version 1.0, 16/10/31
 */
public abstract class LdCheckRuleAbstractService implements LdCheckService {
	
	public List<LdCashCheckResultDTO> check(List<LdCashCheckDTO> ldCashCheckDTOs) throws ServiceException {
		
		List<LdCashCheckResultDTO> ruleCheckDTOs = getRuleCheckDTO(ldCashCheckDTOs);
		
		// 验证
		for (LdCashCheckResultDTO dto : ruleCheckDTOs) {
			//1、获取本纬度需要的参数，并作为返回结果
			Long freeCount = getFreeCount(dto);
			//2、获取空闲数
			dto.setFreeCount(freeCount);
			//3、验证，并写入验证结果
			check(dto);
		}

		return ruleCheckDTOs;
	}
	
	/**
	 * 1、获取本纬度需要的参数，并作为返回结果
	 * @param ldCashCheckDTOs
	 * @return
	 * @throws ServiceException
	 */
	protected abstract List<LdCashCheckResultDTO> getRuleCheckDTO(List<LdCashCheckDTO> ldCashCheckDTOs) throws ServiceException;

	/**
	 * 2、获取空闲数
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	protected abstract Long getFreeCount(LdCashCheckResultDTO dto) throws ServiceException;
	
	/**
	 * 3、验证，并写入验证结果
	 * @param dto
	 * @throws ServiceException
	 */
	protected void check(LdCashCheckResultDTO dto) throws ServiceException {
		if (dto.getFreeCount() < dto.getCheckCount()) {
			dto.setSuccess(false);
			
			StringBuilder message = new StringBuilder();
			message.append(StringUtil.wrap(dto.getRuleDesc(), null, "："));
			message.append("单位【");
			message.append(dto.getUnitName());
			message.append("】");
			if (StringUtils.isNotEmpty(dto.getDutyLevelName())) {
				message.append("【").append(dto.getDutyLevelName()).append("】");
			}
			if (StringUtils.isNotEmpty(dto.getDutyAttributeName())) {
				message.append("【").append(dto.getDutyAttributeName()).append("】");
			}
			message.append("空闲数【").append(dto.getFreeCount()).append("】小于拟用数");
			message.append("【").append(dto.getCheckCount()).append("】");
			
			dto.setMessage(message.toString());
		}
	}
	
}
