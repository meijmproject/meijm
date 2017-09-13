package com.yh.hr.res.ld.service.impl;

import com.yh.hr.res.ld.bo.LdInfo;
import com.yh.hr.res.ld.dto.LdFlowDTO;
import com.yh.hr.res.ld.dto.LdInfoDTO;
import com.yh.hr.res.ld.queryhelper.LdInfoQueryHelper;
import com.yh.hr.res.ld.service.LdInfoService;
import com.yh.hr.res.ld.util.LdFlowConstants;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;
/**
 * 领导职数资源使用情况服务实现类
 * @author liuhw
 * 2016-8-23
 */
public class LdInfoServiceImpl implements LdInfoService {
	/*
	 * (non-Javadoc)
	 * @see LdInfoService#createLdInfo(LdFlowDTO)
	 */
	public void createLdInfo(LdFlowDTO dto) throws ServiceException
	{
		LdInfo bo = new LdInfo();
		bo.setBeginDate(DateUtil.now());
		bo.setCount(dto.getFlowCount());
		bo.setCreatedByCode(dto.getCreatedByCode());
		bo.setCreatedByName(dto.getCreatedByName());
		bo.setCreatedDate(DateUtil.now());
		bo.setDutyAttribute(dto.getDutyAttribute());
		bo.setDutyAttributeName(dto.getDutyAttributeName());
		bo.setDutyLevel(dto.getDutyLevel());
		bo.setDutyLevelName(dto.getDutyLevelName());
		bo.setInfoType(LdFlowConstants.getInfoTypeMap().get(dto.getFlowType()));
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
	 * @see LdInfoService#updateLdInfo(LdFlowDTO)
	 */
	public void updateLdInfo(LdFlowDTO bp) throws ServiceException 
	{
		
		//先根据信息查询是否存在领导职数使用情况
		LdInfo bo = LdInfoQueryHelper.findLdInfoByCondition(Long.valueOf(bp.getAccountCode()), bp.getRefOid(), bp.getRefType(), bp.getDutyAttribute(), bp.getDutyLevel(), LdFlowConstants.getStatusMap().get(bp.getFlowType()));
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

	/*
	 * (non-Javadoc)
	 * @see LdInfoService#findLdInfo(LdInfoDTO)
	 */
	public int countLdInfo(LdInfoDTO dto) throws ServiceException {
		int num = 0;
		if(null != dto){
			 num = LdInfoQueryHelper.getLdInfoCountSum(dto.getUnitOid(), dto.getDutyAttribute(), dto.getDutyLevel(), dto.getStatus());
		}
		return num;
	}
}
