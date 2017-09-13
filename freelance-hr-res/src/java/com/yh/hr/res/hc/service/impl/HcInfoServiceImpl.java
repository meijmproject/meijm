package com.yh.hr.res.hc.service.impl;

import com.yh.hr.res.hc.bo.HcInfo;
import com.yh.hr.res.hc.dto.HcFlowDTO;
import com.yh.hr.res.hc.queryhelper.HcInfoQueryHelper;
import com.yh.hr.res.hc.service.HcInfoService;
import com.yh.hr.res.hc.util.HcFlowConstants;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;
/**
 * 编制资源使用情况服务实现类
 * @author liuhw
 * 2016-8-23
 */
public class HcInfoServiceImpl implements HcInfoService {
	/*
	 * (non-Javadoc)
	 * @see HcInfoService#createHcInfo(HcFlowDTO)
	 */
	public void createHcInfo(HcFlowDTO dto) throws ServiceException 
	{
		HcInfo bo = new HcInfo();
		bo.setBeginDate(DateUtil.now());
		bo.setCount(dto.getFlowCount());
		bo.setCreatedByCode(dto.getCreatedByCode());
		bo.setCreatedByName(dto.getCreatedByName());
		bo.setCreatedDate(DateUtil.now());
		bo.setHcCode(dto.getHcCode());
		bo.setHcName(dto.getHcName());
		bo.setBudgetFromCode(dto.getBudgetFromCode());
		bo.setBudgetFromName(dto.getBudgetFromName());
		bo.setInfoType(HcFlowConstants.getInfoTypeMap().get(dto.getFlowType()));
		bo.setRefDesc(dto.getRefTypeName());
		bo.setRefOid(dto.getRefOid());
		bo.setRefType(dto.getRefTypeCode());
		bo.setStatus(dto.getFlowType());
		bo.setUnitName(dto.getAccountName());
		bo.setUnitOid(Long.valueOf(dto.getAccountCode()));
		bo.save();
	}
	
	/*
	 * (non-Javadoc)
	 * @see HcInfoService#updateHcInfo(HcFlowDTO)
	 */
	public void updateHcInfo(HcFlowDTO bp) throws ServiceException 
	{
		
		//先根据信息查询是否存在编制使用情况
		HcInfo bo = HcInfoQueryHelper.findHcInfoByCondition(Long.valueOf(bp.getAccountCode()), bp.getRefOid(), bp.getRefTypeCode(), bp.getHcCode(), bp.getBudgetFromCode(), HcFlowConstants.getStatusMap().get(bp.getFlowType()));
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
	 * @see HcInfoService#countHcInfo(HcFlowDTO)
	 */
	public int countHcInfo(HcFlowDTO bp) throws ServiceException {
		
		//统计数量并返回
		return HcInfoQueryHelper.countHcInfoByCondition(Long.valueOf(bp.getAccountCode()), bp.getRefOid(), bp.getRefTypeCode(), bp.getHcCode(), bp.getBudgetFromCode(),bp.getFlowType());
	}
}
