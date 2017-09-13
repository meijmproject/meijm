package com.yh.hr.select.unit.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yh.hr.select.unit.service.UnitSelectService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import com.yh.component.dictionary.utils.DicHelper;
import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.sao.unit.SaoUserUnitAuthorizationService;
import com.yh.hr.select.unit.dto.UnitSelectDTO;
import com.yh.hr.select.unit.service.check.UnitSelectCheckService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.util.StringUtil;
import com.yh.platform.core.web.UserContext;

import net.sf.json.JSONObject;

/**
 * 单位选择查询Service模板
 * @author	xiongyx
 * @version	1.0,	16/12/21
 */
public abstract class AbsUnitSelectService implements UnitSelectService {
	private SaoUserUnitAuthorizationService saoUserUnitAuthorizationService = (SaoUserUnitAuthorizationService) SpringBeanUtil.getBean("saoUserUnitAuthorizationService");;
	
	protected List<UnitSelectCheckService> checkServices;
	
	public List<UnitSelectCheckService> getCheckServices() {
		return checkServices;
	}

	public void setCheckServices(List<UnitSelectCheckService> checkServices) {
		this.checkServices = checkServices;
	}
	
	/* (non-Javadoc)
	 * @see PersonSelectService#listPbpersonInfo(TableTagBean)
	 */
	public List<JSONObject> listUbUnitInfo(TableTagBean ttb) throws ServiceException {
		List<String> authUnits = saoUserUnitAuthorizationService.findAuthorityList(UserContext.getLoginAgentUserID());
		if (CollectionUtils.isEmpty(authUnits)) {
			return null;
		}
		ttb.getCondition().put("authUnits", StringUtil.arrayToSql(authUnits));

		List<UnitSelectDTO> list = getUnitList(ttb);
		
		if (CollectionUtils.isNotEmpty(list)) {
			String itemCode = ttb.getCondition().get("itemCode").replace("'", "");
			String itemCodeNode = ttb.getCondition().get("itemCodeNode").replace("'", "");

			List<JSONObject> results = new ArrayList<JSONObject>();
			JSONObject json = null;
			for (UnitSelectDTO dto : list) {
				//1、翻译，数据处理
				json = toJSON(dto);
				
				//2、检查
				json.put("remark", check(itemCode, itemCodeNode, dto));
				
				results.add(json);
			}
			
			return results;
		}
		
		return null;
	}
	
	/**
	 * 转为JSON格式返回
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	protected JSONObject toJSON(UnitSelectDTO dto) throws ServiceException {
		JSONObject json = JSONHelper.fromObject(dto);
		json.put("unitKindName", DicHelper.viewName(DicConstants.YHRS0090, ObjectUtils.toString(json.get("unitKind"), null)));
		json.put("unitCategoryCodeName", DicHelper.viewName(DicConstants.YHRS0091, ObjectUtils.toString(json.get("unitCategoryCode"), null)));
		json.put("unitLevelCodeName", DicHelper.viewName(DicConstants.YHRS0093, ObjectUtils.toString(json.get("unitLevelCode"), null)));
		return json;
	}
	
	/**
	 * 获取人员结果列表
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	protected abstract List<UnitSelectDTO> getUnitList(TableTagBean ttb) throws ServiceException ;
	
	/**
	 * 检查待办
	 * @param itemCode
	 * @param itemCodeNode
	 * @param person
	 * @return
	 * @throws ServiceException
	 */
	protected String check(String itemCode, String itemCodeNode, UnitSelectDTO unit) throws ServiceException {
		StringBuilder sb = new StringBuilder();
		
		if (CollectionUtils.isNotEmpty(checkServices)) {
			for (UnitSelectCheckService checkService : checkServices) {
				sb.append(checkService.check(itemCode, itemCodeNode, unit));
			}
		}
		
		return sb.toString();
	}
}
