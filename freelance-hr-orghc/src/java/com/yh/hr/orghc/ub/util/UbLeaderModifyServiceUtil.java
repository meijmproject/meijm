package com.yh.hr.orghc.ub.util;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import com.yh.hr.component.ld.service.LdFlowApprovedService;
import com.yh.hr.orghc.ub.bo.UbLeader;
import com.yh.hr.orghc.ub.service.UbUnitService;
import com.yh.hr.res.ld.dto.LdFlowDTO;
import com.yh.hr.res.ld.util.LdFlowConstants;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

public class UbLeaderModifyServiceUtil {

	public static LdFlowApprovedService ldFlowApprovedService = (LdFlowApprovedService)SpringBeanUtil.getBean("ldFlowApprovedService");
	public static UbUnitService ubUnitService = (UbUnitService) SpringBeanUtil.getBean("ubUnitService");
	
	public static void modifyUbLeader(UbLeader ubLeader) throws ServiceException{
		List<UbLeader> ubLeaders = new ArrayList<UbLeader>();
		ubLeaders.add(ubLeader);
		modifyUbLeader(ubLeaders);
	}
	
	public static void modifyUbLeader(List<UbLeader> ubLeaders) throws ServiceException{
		List<LdFlowDTO> list = new ArrayList<LdFlowDTO>();
		if(CollectionUtils.isNotEmpty(ubLeaders)){
			LdFlowDTO dto = null;
			for (UbLeader ubLeader : ubLeaders) {
				dto = new LdFlowDTO();
				dto.setAccountCode(ubLeader.getUnitOid().toString());
				dto.setAccountName(ubUnitService.getUnitName(ubLeader.getUnitOid()));
				dto.setAccountType(LdFlowConstants.ACCOUNT_TYPE_1);
				dto.setDutyLevel(ubLeader.getDutyLevel());
				dto.setDutyLevelName(ubLeader.getDutyLevelName());
				dto.setDutyAttribute(ubLeader.getDutyAttribute());
				dto.setDutyAttributeName(ubLeader.getDutyAttributeName());
				dto.setFlowCount(ubLeader.getChgCount());
				dto.setFlowType(LdFlowConstants.RES_TYPE_1);
				dto.setFlowTypeName(LdFlowConstants.getResTypeNameMap().get(LdFlowConstants.RES_TYPE_1));
				dto.setCreatedByCode(UserContext.getLoginUserID());
				dto.setCreatedByName(UserContext.getLoginUserName());
				dto.setCreatedDate(DateUtil.now());
				dto.setUpdatedByCode(UserContext.getLoginUserID());
				dto.setUpdatedByName(UserContext.getLoginUserName());
				dto.setUpdatedDate(DateUtil.now());
				list.add(dto);
			}
		}
		ldFlowApprovedService.approved(list);
	}

	
	/**
	 * 领导职数下达--单位信息校核
	 * @param ubLeader
	 * @param chgCount 变动数
	 * @throws ServiceException
	 */
	public static void modifyUbLeader(UbLeader ubLeader,Long chgCount) throws ServiceException
	{
		List<LdFlowDTO> list = new ArrayList<LdFlowDTO>();
		if(ubLeader!=null)
		{
			LdFlowDTO dto = null;
			dto = new LdFlowDTO();
			dto.setAccountCode(ubLeader.getUnitOid().toString());
			dto.setAccountName(ubUnitService.getUnitName(ubLeader.getUnitOid()));
			dto.setAccountType(LdFlowConstants.ACCOUNT_TYPE_1);
			dto.setDutyLevel(ubLeader.getDutyLevel());
			dto.setDutyLevelName(ubLeader.getDutyLevelName());
			dto.setDutyAttribute(ubLeader.getDutyAttribute());
			dto.setDutyAttributeName(ubLeader.getDutyAttributeName());
			dto.setFlowCount(chgCount);
			dto.setFlowType(LdFlowConstants.RES_TYPE_1);
			dto.setFlowTypeName(LdFlowConstants.getResTypeNameMap().get(LdFlowConstants.RES_TYPE_1));
			dto.setCreatedByCode(UserContext.getLoginUserID());
			dto.setCreatedByName(UserContext.getLoginUserName());
			dto.setCreatedDate(DateUtil.now());
			dto.setUpdatedByCode(UserContext.getLoginUserID());
			dto.setUpdatedByName(UserContext.getLoginUserName());
			dto.setUpdatedDate(DateUtil.now());
			list.add(dto);
		}
		ldFlowApprovedService.approved(list);
	}
}
