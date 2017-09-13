package com.yh.hr.info.warning.service.impl;


import java.util.ArrayList;
import java.util.List;

import com.yh.hr.info.warning.bo.BizFullProbationRule;
import com.yh.hr.info.warning.dto.BizFullProbationRuleDTO;
import com.yh.hr.info.warning.queryhelper.BizFullProbationRuleQueryHelper;
import com.yh.hr.info.warning.service.BizProbationRuleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 * @description 聘任制试用期到期预警规则
 * @author liuhw
 * @created 2014-3-31
 * @version 1.0
 * 
 */
public class BizProbationRuleServiceImpl implements BizProbationRuleService
{


	public List<BizFullProbationRuleDTO> listProbationRule(TableTagBean ttb )throws ServiceException {
		
		
		 List<BizFullProbationRule> list = BizFullProbationRuleQueryHelper.listProbationRule(ttb);
		 List<BizFullProbationRuleDTO> dtoList = new ArrayList<BizFullProbationRuleDTO>();
		 if(CollectionUtils.isNotEmpty(list))
		 {
			 for(int i=0;i<list.size();i++){
				 BizFullProbationRuleDTO dto = new BizFullProbationRuleDTO();
				 BeanUtils.copyProperties(list.get(i),dto);
				 dtoList.add(dto);
			 }
			
		 }
		 return dtoList;
	}

	public void createProbationRule(BizFullProbationRuleDTO bizFullProbationRuleDTO)throws ServiceException {
		try {
			if(null!=bizFullProbationRuleDTO){
				BizFullProbationRule bizFullProbationRule = new BizFullProbationRule();
				BeanUtils.copyProperties(bizFullProbationRuleDTO, bizFullProbationRule);
				bizFullProbationRule.setCreatedBy(UserContext.getLoginUserID());
				bizFullProbationRule.setCreatedTs(DateUtil.now());
				bizFullProbationRule.save();
			}
	}catch (DataAccessFailureException e) {
		throw new DataAccessFailureException("createProbationRule error",e);
	}
		
	}

	public List<BizFullProbationRuleDTO> findPersonType(String personType,Long bizFullProbationOid) throws ServiceException {
		 List<BizFullProbationRule> list = BizFullProbationRuleQueryHelper.findPersonType(personType,bizFullProbationOid);
		 List<BizFullProbationRuleDTO> dtoList = new ArrayList<BizFullProbationRuleDTO>();
		 if(CollectionUtils.isNotEmpty(list))
		 {
			 for(int i=0;i<list.size();i++){
				 BizFullProbationRuleDTO dto = new BizFullProbationRuleDTO();
				 BeanUtils.copyProperties(list.get(i),dto);
				 dtoList.add(dto);
			 }
			
		 }
		 return dtoList;
	}

	public BizFullProbationRuleDTO getProbationRule(Long bizFullProbationOid)throws ServiceException {
		try {
			 BizFullProbationRule bo = DaoUtil.get(BizFullProbationRule.class,bizFullProbationOid);
			 BizFullProbationRuleDTO dto  = new BizFullProbationRuleDTO();
			 BeanUtils.copyProperties(bo, dto);
			 return dto;
		}catch (DataAccessFailureException e) {
			throw new DataAccessFailureException("getProbationRule error",e);
		}
	}

	public void updateProbationRule(BizFullProbationRuleDTO dto)throws ServiceException {
		try {
			if(null!=dto){
				BizFullProbationRule bo = DaoUtil.get(BizFullProbationRule.class,dto.getBizFullProbationOid());
				BeanUtils.copyProperties(dto, bo, new String[]{"createdBy","createdTs"});
				bo.setUpdatedBy(UserContext.getLoginUserName());
				bo.setUpdatedTs(DateUtil.now());
				bo.update();
			}
	}catch (DataAccessFailureException e) {
		throw new DataAccessFailureException("updateProbationRule error",e);
	}
		
	}

	public void deleteProbationRule(Long bizFullProbationOid)throws ServiceException {
		try {
			 BizFullProbationRule bo = DaoUtil.get(BizFullProbationRule.class,bizFullProbationOid);
			 bo.delete();
		}catch (DataAccessFailureException e) {
			throw new DataAccessFailureException("deleteProbationRule error",e);
		}		
	}
}
