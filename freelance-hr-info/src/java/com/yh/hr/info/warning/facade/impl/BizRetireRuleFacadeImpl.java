package com.yh.hr.info.warning.facade.impl;


import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.info.warning.dto.BizOutRetireRuleDTO;
import com.yh.hr.info.warning.service.BizRetireRuleService;
import com.yh.platform.core.exception.ServiceException;


/**
 * 预警离退休预警规则-实现类
 * @author	liuhw
 * @created 2014-03-31
 */
public class BizRetireRuleFacadeImpl 
{
	private BizRetireRuleService bizRetireRuleService;
	
	
	public void setBizRetireRuleService(BizRetireRuleService bizRetireRuleService) {
		this.bizRetireRuleService = bizRetireRuleService;
	}


	public List<BizOutRetireRuleDTO> listRetireRule(TableTagBean ttb )throws ServiceException {
		
		return bizRetireRuleService.listRetireRule(ttb);
	}
	public void createRetireRule(BizOutRetireRuleDTO bizOutRetireRuleDTO)throws ServiceException {
		bizRetireRuleService.createRetireRule(bizOutRetireRuleDTO);
		
	}
	public List<BizOutRetireRuleDTO> findPersonType(String personType,Long bizFullProbationOid,String sexCode) throws ServiceException {
		return bizRetireRuleService.findPersonType(personType,bizFullProbationOid,sexCode);
	}
	public BizOutRetireRuleDTO getRetireRule(Long bizOutRetireOid)throws ServiceException {
		 return bizRetireRuleService.getRetireRule(bizOutRetireOid);
	}
	public void updateRetireRule(BizOutRetireRuleDTO dto)throws ServiceException {
		bizRetireRuleService.updateRetireRule(dto);
		
	}
	public void deleteRetireRule(Long bizOutRetireOid) throws ServiceException {
		bizRetireRuleService.deleteRetireRule(bizOutRetireOid);
		
	}
}