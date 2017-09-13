package com.yh.hr.component.task.service.impl;

import java.util.List;

import com.yh.hr.component.task.service.TaskItemService;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.task.queryhelper.TaskAnnuallyPromoteItemDefaultQueryHelper;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.unit.service.UtUnitService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 工资年度考核事项查询服务类（工作台右边查询列表）
 * @author huanglm
 * @time 2017-01-08
 */
public class TaskWageAnnuallyPromoteItemServiceImpl implements TaskItemService
{
	//private SaoUserUnitAuthorizationService saoUserUnitAuthorizationService =(SaoUserUnitAuthorizationService) SpringBeanUtil.getBean("saoUserUnitAuthorizationService");
	
	@SuppressWarnings("unused")
	private UtUnitService utUnitService =(UtUnitService) SpringBeanUtil.getBean("utUnitService");;
	
	
	/*
	 * (non-Javadoc)
	 * @see qzhrssb.common.service.JhcBtTaskItemService#list(TableTagBean)
	 */
	public List<JSONObject> list(TableTagBean ttb) throws ServiceException 
	{/*
		List<String> authUnits = saoUserUnitAuthorizationService.findAuthorityList(UserContext.getLoginAgentUserID());
		if(CollectionUtils.isEmpty(authUnits)){
			return null;
		}
		
		// 是否含下设单位(默认为true，包含下设单位。参数为false时仅查有权限的单位)
		ttb.getCondition().put("authUnits", StringUtil.arrayToSql(authUnits));*/
		ttb.setTotal(TaskAnnuallyPromoteItemDefaultQueryHelper.count(ttb));
		List<JSONObject> dtoList=TaskAnnuallyPromoteItemDefaultQueryHelper.list(ttb);
		
		if(CollectionUtils.isNotEmpty(dtoList)){
			for(JSONObject json:dtoList ){
				json.put("personTypeName", DicHelper.viewName(DicConstants.YHRS0010, ObjectUtils.toString(json.get("personType"), null)));
				json.put("dutyLevelName", DicHelper.viewName(DicConstants.YHRS0015, ObjectUtils.toString(json.get("dutyLevel"), null)));
				json.put("processResult", DicHelper.viewName(DicConstants.YHRS2003, ObjectUtils.toString(json.get("processResult"), null)));
				json.put("mPositionTypeDesc", DicHelper.viewName(DicConstants.YHRS0022, ObjectUtils.toString(json.get("mPositionType"), null)));
				json.put("mPositionLevelDesc", DicHelper.viewName(DicConstants.YHRS0023, ObjectUtils.toString(json.get("mPositionLevel"), null)));
				/*UtUnitDTO utUnitDTO= utUnitService.getAdminUnit(Long.valueOf(json.get("unitOid").toString()));
				if(null!=utUnitDTO){
					json.put("adminUnitOid", utUnitDTO.getUnitOid());//主管单位OID
					json.put("adminUnit", utUnitDTO.getUnitName());//主管单位
				}*/
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
		/*List<String> authUnits = saoUserUnitAuthorizationService.findAuthorityList(UserContext.getLoginAgentUserID());
		if(CollectionUtils.isEmpty(authUnits)){
			return 0;
		}
		ttb.getCondition().put("authUnits", StringUtil.arrayToSql(authUnits));*/
		return TaskAnnuallyPromoteItemDefaultQueryHelper.count(ttb);
	}
}
