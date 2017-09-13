package com.yh.hr.info.warning.service;


import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.info.warning.dto.BizOutRetireRuleDTO;
import com.yh.platform.core.exception.ServiceException;


/**
 * @description 离退休预警规则
 * @author zhangsx
 * @created 2013-07-24
 * @version 1.0
 * 
 */
public interface BizRetireRuleService
{
	
	/**
	 * 查询离退休预警列表
	 * @throws ServiceException
	 */
	public List<BizOutRetireRuleDTO> listRetireRule(TableTagBean ttb )throws ServiceException;
	

	/**
	 * 新增离退休预警规则
	 * @throws ServiceException
	 */
	public void createRetireRule(BizOutRetireRuleDTO bizOutRetireRuleDTO)throws ServiceException;
	/**
	 * 查找离退休预警规则
	 * @throws ServiceException
	 */
	public List<BizOutRetireRuleDTO> findPersonType(String personType,Long bizFullProbationOid,String sexCode)throws ServiceException;

	/**
	 * 查找离退休预警规则
	 * @throws ServiceException
	 */
	public BizOutRetireRuleDTO getRetireRule(Long bizOutRetireOid)throws ServiceException;

	/**
	 * 更新离退休预警规则
	 * @throws ServiceException
	 */
	public void updateRetireRule(BizOutRetireRuleDTO dto)throws ServiceException;


	/**
	 * 删除离退休预警规则
	 * @throws ServiceException
	 */
	public void deleteRetireRule(Long bizOutRetireOid)throws ServiceException;

}
