package com.yh.hr.info.warning.service;


import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.info.warning.dto.BizFullProbationRuleDTO;
import com.yh.platform.core.exception.ServiceException;


/**
 * @description 聘任制试用期到期预警规则
 * @author zhangsx
 * @created 2013-07-24
 * @version 1.0
 * 
 */
public interface BizProbationRuleService
{
	
	/**
	 * 查询聘任制试用期到期预警列表
	 * @throws ServiceException
	 */
	public List<BizFullProbationRuleDTO> listProbationRule(TableTagBean ttb )throws ServiceException;
	

	/**
	 * 新增聘任制试用期到期预警规则
	 * @throws ServiceException
	 */
	public void createProbationRule(BizFullProbationRuleDTO bizFullProbationRuleDTO)throws ServiceException;
	/**
	 * 查找聘任制试用期到期预警规则
	 * @throws ServiceException
	 */
	public List<BizFullProbationRuleDTO> findPersonType(String personType,Long bizFullProbationOid)throws ServiceException;

	/**
	 * 查找聘任制试用期到期预警规则
	 * @throws ServiceException
	 */
	public BizFullProbationRuleDTO getProbationRule(Long bizFullProbationOid)throws ServiceException;

	/**
	 * 更新聘任制试用期到期预警规则
	 * @throws ServiceException
	 */
	public void updateProbationRule(BizFullProbationRuleDTO dto)throws ServiceException;


	/**
	 * 删除聘任制试用期到期预警规则
	 * @throws ServiceException
	 */
	public void deleteProbationRule(Long bizFullProbationOid)throws ServiceException;

}
