package com.yh.hr.orghc.unit.worktop.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.task.service.TaskItemService;
import com.yh.hr.orghc.unit.worktop.queryhelper.UnitTaskItemDefaultQueryHelper;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.sao.unit.SaoUserUnitAuthorizationService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.util.StringUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 机构编制查询
 * @author xiongyx
 * @time 2016-12-19
 */
public class UnitTaskItemServiceImpl implements TaskItemService
{
	private SaoUserUnitAuthorizationService saoUserUnitAuthorizationService =(SaoUserUnitAuthorizationService) SpringBeanUtil.getBean("saoUserUnitAuthorizationService");
	
	
	/*
	 * (non-Javadoc)
	 * @see qzhrssb.common.service.JhcBtTaskItemService#list(TableTagBean)
	 */
	public List<JSONObject> list(TableTagBean ttb) throws ServiceException 
	{
		List<String> authUnits = saoUserUnitAuthorizationService.findAuthorityList(UserContext.getLoginAgentUserID());
		if(CollectionUtils.isEmpty(authUnits)){
			return null;
		}
		
		// 是否含下设单位(默认为true，包含下设单位。参数为false时仅查有权限的单位)
		ttb.getCondition().put("authUnits", StringUtil.arrayToSql(authUnits));
		ttb.setTotal(UnitTaskItemDefaultQueryHelper.count(ttb));
		List<JSONObject> dtoList=UnitTaskItemDefaultQueryHelper.list(ttb);
		
		if(CollectionUtils.isNotEmpty(dtoList)){
			for(JSONObject json:dtoList ){
				json.put("processResult", DicHelper.viewName(DicConstants.YHRS2003, ObjectUtils.toString(json.get("processResult"), null)));
				json.put("unitKindName", DicHelper.viewName(DicConstants.YHRS0090, ObjectUtils.toString(json.get("unitKind"), null)));
				json.put("unitCategoryCodeName", DicHelper.viewName(DicConstants.YHRS0091, ObjectUtils.toString(json.get("unitCategoryCode"), null)));
				json.put("levelCodeName", DicHelper.viewName(DicConstants.YHRS0093, ObjectUtils.toString(json.get("levelCode"), null)));
			}
			
		}
		return dtoList;
	}

	/*
	 * (non-Javadoc)
	 * @see qzhrssb.common.service.JhcBtTaskItemService#count(TableTagBean)
	 */
	public int count(TableTagBean ttb) throws ServiceException 
	{
		List<String> authUnits = saoUserUnitAuthorizationService.findAuthorityList(UserContext.getLoginAgentUserID());
		if(CollectionUtils.isEmpty(authUnits)){
			return 0;
		}
		ttb.getCondition().put("authUnits", StringUtil.arrayToSql(authUnits));
		return UnitTaskItemDefaultQueryHelper.count(ttb);
	}
}
