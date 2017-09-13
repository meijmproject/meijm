package com.yh.hr.info.warning.service.impl;


import java.util.ArrayList;
import java.util.List;

import com.yh.hr.info.warning.queryhelper.BizOutRetireRuleQueryHelper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.info.warning.bo.BizOutRetireRule;
import com.yh.hr.info.warning.dto.BizOutRetireRuleDTO;
import com.yh.hr.info.warning.service.BizRetireRuleService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;


/**
 * @description 离退休预警规则
 * @author liuhw
 * @created 2014-3-31
 * @version 1.0
 * 
 */
public class BizRetireRuleServiceImpl implements BizRetireRuleService
{


	public List<BizOutRetireRuleDTO> listRetireRule(TableTagBean ttb )throws ServiceException {
		
		
		 List<BizOutRetireRule> list = BizOutRetireRuleQueryHelper.listRetireRule(ttb);
		 List<BizOutRetireRuleDTO> dtoList = new ArrayList<BizOutRetireRuleDTO>();
		 if(CollectionUtils.isNotEmpty(list))
		 {
			 for(int i=0;i<list.size();i++){
				 BizOutRetireRuleDTO dto = new BizOutRetireRuleDTO();
				 BeanUtils.copyProperties(list.get(i),dto);
				 dtoList.add(dto);
			 }
			
		 }
		 return dtoList;
	}

	public void createRetireRule(BizOutRetireRuleDTO bizOutRetireRuleDTO)throws ServiceException {
		try {
			if(null!=bizOutRetireRuleDTO){
				BizOutRetireRule bo = new BizOutRetireRule();
				BeanUtils.copyProperties(bizOutRetireRuleDTO, bo);
				bo.setCreatedBy(UserContext.getLoginUserName());
				bo.setCreatedTs(DateUtil.now());
				bo.save();
			}
	}catch (DataAccessException e) {
		throw new DataAccessFailureException("createRetireRule error",e);
	}
		
	}

	public List<BizOutRetireRuleDTO> findPersonType(String personType,Long bizOutRetireRuleOid,String sexCode) throws ServiceException {
		 List<BizOutRetireRule> list = BizOutRetireRuleQueryHelper.findPersonType(personType,bizOutRetireRuleOid,sexCode);
		 List<BizOutRetireRuleDTO> dtoList = new ArrayList<BizOutRetireRuleDTO>();
		 if(CollectionUtils.isNotEmpty(list))
		 {
			 for(int i=0;i<list.size();i++){
				 BizOutRetireRuleDTO dto = new BizOutRetireRuleDTO();
				 BeanUtils.copyProperties(list.get(i),dto);
				 dtoList.add(dto);
			 }
			
		 }
		 return dtoList;
	}

	public BizOutRetireRuleDTO getRetireRule(Long bizOutRetireOid)throws ServiceException {
		try {
			 BizOutRetireRule bo = DaoUtil.get(BizOutRetireRule.class,bizOutRetireOid);
			 BizOutRetireRuleDTO dto  = new BizOutRetireRuleDTO();
			 BeanUtils.copyProperties(bo, dto);
			 return dto;
		}catch (DataAccessException e) {
			throw new DataAccessFailureException("getRetireRule error",e);
		}
	}

	public void updateRetireRule(BizOutRetireRuleDTO dto)throws ServiceException {
		try {
			if(null!=dto){
				BizOutRetireRule bo = DaoUtil.get(BizOutRetireRule.class,dto.getBizOutRetireOid());
				BeanUtils.copyProperties(dto, bo, new String[]{"createdBy","createdTs"});
				bo.setUpdatedBy(UserContext.getLoginUserName());
				bo.setUpdatedTs(DateUtil.now());
				bo.update();
			}
	}catch (DataAccessException e) {
		throw new DataAccessFailureException("updateRetireRule error",e);
	}
		
	}

	public void deleteRetireRule(Long bizOutRetireOid)throws ServiceException {
		try {
			 BizOutRetireRule bo = DaoUtil.get(BizOutRetireRule.class,bizOutRetireOid);
			 bo.delete();
		}catch (DataAccessException e) {
			throw new DataAccessFailureException("deleteRetireRule error",e);
		}		
	}
}
