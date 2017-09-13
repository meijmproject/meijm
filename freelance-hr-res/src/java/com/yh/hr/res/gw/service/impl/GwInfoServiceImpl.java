package com.yh.hr.res.gw.service.impl;

import com.yh.hr.res.gw.bo.GwInfo;
import com.yh.hr.res.gw.dto.GwFlowDTO;
import com.yh.hr.res.gw.queryhelper.GwInfoQueryHelper;
import com.yh.hr.res.gw.service.GwInfoService;
import com.yh.hr.res.gw.util.GwFlowConstants;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;
/**
 * 岗位资源使用情况服务实现类
 * @author liuhw
 * 2016-8-23
 */
public class GwInfoServiceImpl implements GwInfoService {
	/*
	 * (non-Javadoc)
	 * @see GwInfoService#createGwInfo(GwFlowDTO)
	 */
	public void createGwInfo(GwFlowDTO dto) throws ServiceException
	{
		GwInfo bo = new GwInfo();
		bo.setBeginDate(DateUtil.now());
		bo.setCount(dto.getFlowCount());
		bo.setCreatedByCode(dto.getCreatedByCode());
		bo.setCreatedByName(dto.getCreatedByName());
		bo.setCreatedDate(DateUtil.now());
		bo.setGwLbCode(dto.getGwLbCode());
		bo.setGwLbName(dto.getGwLbName());
		bo.setGwLevelCode(dto.getGwLevelCode());
		bo.setGwLevelName(dto.getGwLevelName());
		bo.setInfoType(GwFlowConstants.getInfoTypeMap().get(dto.getFlowType()));
		bo.setRefDesc(dto.getRefTypeName());
		bo.setRefOid(dto.getRefOid());
		bo.setRefType(dto.getRefType());
		bo.setStatus(dto.getFlowType());
		bo.setUnitName(dto.getAccountName());
		bo.setUnitOid(Long.valueOf(dto.getAccountCode()));
		bo.save();
	}
	
	/*
	 * (non-Javadoc)
	 * @see GwInfoService#updateGwInfo(GwFlowDTO)
	 */
	public void updateGwInfo(GwFlowDTO bp) throws ServiceException 
	{
		
		//先根据信息查询是否存在岗位使用情况
		GwInfo bo = GwInfoQueryHelper.findGwInfoByCondition(Long.valueOf(bp.getAccountCode()), bp.getRefOid(), bp.getRefType(), bp.getGwLbCode(), bp.getGwLevelCode(), GwFlowConstants.getStatusMap().get(bp.getFlowType()));
		//如果存在，则更新相应字段的值
		if(null != bo)
		{
			//根据使用数量判断是否已经为0，为0则相应的更新状态
			if(bp.getFlowCount()+bo.getCount() == 0)
			{
				bo.setEndDate(DateUtil.now());
				bo.setStatus(bp.getFlowType());
			}
			bo.setCount(bp.getFlowCount()+bo.getCount());
			bo.setUpdatedByCode(bp.getUpdatedByCode());
			bo.setUpdatedByName(bp.getUpdatedByName());
			bo.setUpdatedDate(DateUtil.now());
			bo.update();
		}
	}
}
