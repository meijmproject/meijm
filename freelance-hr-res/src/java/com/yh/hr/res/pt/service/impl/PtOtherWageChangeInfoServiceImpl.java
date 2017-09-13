package com.yh.hr.res.pt.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.pt.dto.PtOtherWageChangeInfoDTO;
import com.yh.hr.res.pt.service.PtOtherWageChangeInfoService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pt.bo.PtOtherWageChangeInfo;
import com.yh.hr.res.pt.dto.PtWageInfluenceDTO;
import com.yh.hr.res.pt.service.PtWageInfluenceService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.util.StringUtil;

/**
 * @description The implement for PtOtherWageChangeInfo service
 * @author wuxq
 * @created 2016-12-08
 * @version 1.0
 */
public class PtOtherWageChangeInfoServiceImpl implements PtOtherWageChangeInfoService
{
	private PtWageInfluenceService ptWageInfluenceService = (PtWageInfluenceService) SpringBeanUtil.getBean("ptWageInfluenceService");

	/**
	 * insert PtOtherWageChangeInfo obj
	 */
	public void createPtOtherWageChangeInfo(PtOtherWageChangeInfoDTO ptOtherWageChangeInfoDTO) throws ServiceException
	{
		PtOtherWageChangeInfo ptOtherWageChangeInfo = BeanHelper.copyProperties(ptOtherWageChangeInfoDTO, PtOtherWageChangeInfo.class);
		ptOtherWageChangeInfo.save();
		
		Date changeStartDateOfWage = ptOtherWageChangeInfo.getChangeStartDateOfWage();
		//根据人员id获的影响工资基础信息
		PtWageInfluenceDTO ptWageInfluenceDTO =  ptWageInfluenceService.getPtWageInfluenceDTOById(ptOtherWageChangeInfo.getBizPersonOid());
		//设置起薪时间
		ptWageInfluenceDTO.setStartDateOfWage(changeStartDateOfWage);
		ptWageInfluenceService.updatePtWageInfluence(ptWageInfluenceDTO);
	}
	
	/**
	 * get PtOtherWageChangeInfo obj
	 */
	public PtOtherWageChangeInfoDTO findPtOtherWageChangeInfoDTOById(Long ptOtherWageChangeOid) throws ServiceException
	{
		PtOtherWageChangeInfo ptOtherWageChangeInfo = DaoUtil.get(PtOtherWageChangeInfo.class, ptOtherWageChangeOid);
		return BeanHelper.copyProperties(ptOtherWageChangeInfo, PtOtherWageChangeInfoDTO.class);
	}
	
	/**
	 * update PtEducationInfo obj
	 */
	public void updatePtOtherWageChangeInfo(PtOtherWageChangeInfoDTO ptOtherWageChangeInfoDTO) throws ServiceException
	{
		PtOtherWageChangeInfo ptOtherWageChangeInfo = BeanHelper.copyProperties(ptOtherWageChangeInfoDTO, PtOtherWageChangeInfo.class);
		ptOtherWageChangeInfo.update();
	}
	
	/**
	 * delete PtOtherWageChangeInfo obj
	 */
	public void deletePtOtherWageChangeInfo(Long ptOtherWageChangeOid) throws ServiceException
	{
		PtOtherWageChangeInfo ptOtherWageChangeInfo = DaoUtil.get(PtOtherWageChangeInfo.class, ptOtherWageChangeOid);
		ptOtherWageChangeInfo.delete();
	}
	
	/**
	 * 根据bizPersonOid查询
	 */
	public PtOtherWageChangeInfoDTO listPtOtherWageChangeInfoByBizPersonOid(Long bizPersonOid) throws ServiceException
	{
		final StringBuffer hBuffer = new StringBuffer(
				"from PtOtherWageChangeInfo wh  ");
		if (StringUtil.isNotNull(bizPersonOid)) {
			hBuffer.append("  where wh.bizPersonOid ="
					+ bizPersonOid);
		}		
		@SuppressWarnings("rawtypes")
		List list = DaoUtil.find(hBuffer.toString());
		if (CollectionUtils.isEmpty(list))
			return null;
		return BeanHelper.copyProperties(list.get(0),
				PtOtherWageChangeInfoDTO.class);
	}
	
	/**
	 * 获取查询总数
	 */
	public int countPtOtherWageChangeInfoByBizPersonOid(Long bizPersonOid) throws ServiceException
	{
		String hql = "select count(*) from PtOtherWageChangeInfo ei where ei.bizPersonOid = :bizPersonOid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		return DaoUtil.countByCondition(hql, params);
	}
}

