package com.yh.hr.info.warning.facade.impl;


import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.info.warning.dto.BizFullProbationRuleDTO;
import com.yh.hr.info.warning.service.BizProbationRuleService;
import com.yh.platform.core.exception.ServiceException;


/**
 * 预警聘任制试用期到期预警规则-实现类
 * @author	liuhw
 * @created 2014-03-31
 */
public class BizProbationRuleFacadeImpl
{
	/**
	 * 用于查询人员基础预警信息，不查询日终任务预警信息
	 */
	private BizProbationRuleService bizProbationRuleService;
	
	
	public void setBizProbationRuleService(
			BizProbationRuleService bizProbationRuleService) {
		this.bizProbationRuleService = bizProbationRuleService;
	}
	public List<BizFullProbationRuleDTO> listProbationRule(TableTagBean ttb )throws ServiceException {
		
		return bizProbationRuleService.listProbationRule(ttb);
	}
	public void createProbationRule(BizFullProbationRuleDTO bizFullProbationRuleDTO)throws ServiceException {
		bizProbationRuleService.createProbationRule(bizFullProbationRuleDTO);
		
	}
	public List<BizFullProbationRuleDTO> findPersonType(String personType,Long bizFullProbationOid) throws ServiceException {
		return bizProbationRuleService.findPersonType(personType,bizFullProbationOid);
	}
	public BizFullProbationRuleDTO getProbationRule(Long bizFullProbationOid)throws ServiceException {
		 return bizProbationRuleService.getProbationRule(bizFullProbationOid);
	}
	public void updateProbationRule(BizFullProbationRuleDTO dto)throws ServiceException {
		bizProbationRuleService.updateProbationRule(dto);
		
	}
	public void deleteProbationRule(Long bizFullProbationOid) throws ServiceException {
		bizProbationRuleService.deleteProbationRule(bizFullProbationOid);
		
	}
}