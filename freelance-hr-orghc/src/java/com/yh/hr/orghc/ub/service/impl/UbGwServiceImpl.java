package com.yh.hr.orghc.ub.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.hr.component.gw.service.GwFlowApprovedService;
import com.yh.hr.orghc.ub.service.UbGwService;
import com.yh.hr.orghc.ub.service.UbUnitService;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.gt.dto.GtPlanDetailDTO;
import com.yh.hr.res.gw.dto.GwFlowDTO;
import com.yh.hr.res.gw.util.GwFlowConstants;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

public class UbGwServiceImpl implements UbGwService {

	private UbUnitService ubUnitService = (UbUnitService) SpringBeanUtil.getBean("ubUnitService");
	private GwFlowApprovedService gwFlowApprovedService = (GwFlowApprovedService) SpringBeanUtil.getBean("gwFlowApprovedService");
	
	/**
	 * 更新岗位数信息
	 */
	public void updateGwInfo(GtPlanDetailDTO gtPlanDetailDTO, Long chgCount) throws ServiceException {
		
		this.modifyGwInfo(gtPlanDetailDTO, chgCount);
	}
	
	/**
	 * 岗位数下达
	 * @param gtPlanDetailDTO
	 * @param chgCount
	 * @throws ServiceException
	 */
	public void modifyGwInfo(GtPlanDetailDTO gtPlanDetailDTO, Long chgCount) throws ServiceException {
		
		List<GwFlowDTO> list = new ArrayList<GwFlowDTO>();
		if(null != gtPlanDetailDTO)
		{
			GwFlowDTO dto = new GwFlowDTO();
			dto.setAccountCode(gtPlanDetailDTO.getUnitOid().toString());
			dto.setAccountName(ubUnitService.getUnitName(gtPlanDetailDTO.getUnitOid()));
			dto.setAccountType(GwFlowConstants.ACCOUNT_TYPE_1);
			dto.setGwLbCode(gtPlanDetailDTO.getPostType());
			dto.setGwLbName(DicHelper.viewName(DicConstants.YHRS0022, gtPlanDetailDTO.getPostType()));
			dto.setGwLevelCode(gtPlanDetailDTO.getPostLevel());
			dto.setGwLevelName(DicHelper.viewName(DicConstants.YHRS0023, gtPlanDetailDTO.getPostLevel()));
			dto.setFlowCount(chgCount);//变动数
			dto.setFlowType(GwFlowConstants.RES_TYPE_1);
			dto.setFlowTypeName(GwFlowConstants.getResTypeNameMap().get(GwFlowConstants.RES_TYPE_1));
			dto.setCreatedByCode(UserContext.getLoginUserID());
			dto.setCreatedByName(UserContext.getLoginUserName());
			dto.setCreatedDate(DateUtil.now());
			dto.setUpdatedByCode(UserContext.getLoginUserID());
			dto.setUpdatedByName(UserContext.getLoginUserName());
			dto.setUpdatedDate(DateUtil.now());
			list.add(dto);
		}
		gwFlowApprovedService.approved(list);
	}
}
