/**
 * 
 */
package com.yh.hr.leader.validate.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yh.hr.leader.validate.dto.LdCashCheckResultDTO;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.leader.validate.dto.LdCashCheckDTO;
import com.yh.hr.leader.validate.service.LdCheckService;
import com.yh.platform.core.exception.ServiceException;

/**
 * 领导职数检查
 * 
 * @author zhangqp
 * @version 1.0, 16/10/31
 */
public class LdCheckServiceImpl implements LdCheckService {
	private List<LdCheckService> rules;
	
	public List<LdCheckService> getRules() {
		return rules;
	}

	public void setRules(List<LdCheckService> rules) {
		this.rules = rules;
	}

	public List<LdCashCheckResultDTO> check(List<LdCashCheckDTO> ldCashCheckDTOs) throws ServiceException {

		List<LdCashCheckResultDTO>  results = new ArrayList<LdCashCheckResultDTO>();
		if (CollectionUtils.isNotEmpty(rules) 
				&& CollectionUtils.isNotEmpty(ldCashCheckDTOs)) {
			for (LdCheckService rule : rules) {
				
				for (LdCashCheckResultDTO dto : rule.check(ldCashCheckDTOs)) {
					if (!dto.isSuccess()) {
						results.add(dto);
					}
				}
			}
		}
		
		return results;
	}

}
