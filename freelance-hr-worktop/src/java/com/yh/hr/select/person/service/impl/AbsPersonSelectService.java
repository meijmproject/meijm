package com.yh.hr.select.person.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import com.yh.component.dictionary.utils.DicHelper;
import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.sao.unit.SaoUserUnitAuthorizationService;
import com.yh.hr.select.person.dto.PersonSelectDTO;
import com.yh.hr.select.person.service.PersonSelectService;
import com.yh.hr.select.person.service.check.PersonSelectCheckService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.util.StringUtil;
import com.yh.platform.core.web.UserContext;

import net.sf.json.JSONObject;

/**
 * 人员选择查询Service模板
 * @author	zhangqp
 * @version	1.0,	16/11/09
 */
public abstract class AbsPersonSelectService implements PersonSelectService {
	private SaoUserUnitAuthorizationService saoUserUnitAuthorizationService = (SaoUserUnitAuthorizationService) SpringBeanUtil.getBean("saoUserUnitAuthorizationService");;
	
	protected List<PersonSelectCheckService> checkServices;
	
	public List<PersonSelectCheckService> getCheckServices() {
		return checkServices;
	}

	public void setCheckServices(List<PersonSelectCheckService> checkServices) {
		this.checkServices = checkServices;
	}
	
	/* (non-Javadoc)
	 * @see PersonSelectService#listPbpersonInfo(TableTagBean)
	 */
	public List<JSONObject> listPbpersonInfo(TableTagBean ttb) throws ServiceException {
		List<String> authUnits = saoUserUnitAuthorizationService.findAuthorityList(UserContext.getLoginAgentUserID());
	/*	if (CollectionUtils.isEmpty(authUnits)) {
			return null;
		}*/
		ttb.getCondition().put("authUnits", StringUtil.arrayToSql(authUnits));

		List<PersonSelectDTO> list = getPbPersonList(ttb);
		
		if (CollectionUtils.isNotEmpty(list)) {
			String itemCode = ttb.getCondition().get("itemCode");
			String itemCodeNode = ttb.getCondition().get("itemCodeNode");

			List<JSONObject> results = new ArrayList<JSONObject>();
			JSONObject json = null;
			for (PersonSelectDTO dto : list) {
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
	protected JSONObject toJSON(PersonSelectDTO dto) throws ServiceException {
		JSONObject json = JSONHelper.fromObject(dto);
		String mPositionType=json.get("mPositionType").toString();
		String mPositionLevel=json.get("mPositionLevel").toString();
		String sPositionType=json.get("sPositionType").toString();
		String sPositionLevel=json.get("sPositionLevel").toString();
		String administrativeDutyLevel=json.get("administrativeDutyLevel").toString();
		       administrativeDutyLevel=DicHelper.viewName(DicConstants.YHRS0015, administrativeDutyLevel)==null?"":DicHelper.viewName(DicConstants.YHRS0015, administrativeDutyLevel);
		String administrativeDutyAttribute=json.get("administrativeDutyAttribute").toString();
			   administrativeDutyAttribute=DicHelper.viewName(DicConstants.YHRS0028,administrativeDutyAttribute)==null?"":DicHelper.viewName(DicConstants.YHRS0028,administrativeDutyAttribute);
		json.put("personStatus", DicHelper.viewName(DicConstants.YHRS0009, ObjectUtils.toString(json.get("personStatus"), null)));
		json.put("administrativeDutyLevel", DicHelper.viewName(DicConstants.YHRS0015, ObjectUtils.toString(json.get("administrativeDutyLevel"), null)));
		json.put("administrativeDutyAttribute", DicHelper.viewName(DicConstants.YHRS0028, ObjectUtils.toString(json.get("administrativeDutyAttribute"), null)));
		json.put("positionLevel",administrativeDutyLevel+ administrativeDutyAttribute);
		json.put("allPositionLevel", mPositionType+mPositionLevel+sPositionType+sPositionLevel);
		return json;
	}
	
	/**
	 * 获取人员结果列表
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	protected abstract List<PersonSelectDTO> getPbPersonList(TableTagBean ttb) throws ServiceException ;
	
	/**
	 * 检查待办
	 * @param itemCode
	 * @param itemCodeNode
	 * @param person
	 * @return
	 * @throws ServiceException
	 */
	protected String check(String itemCode, String itemCodeNode, PersonSelectDTO person) throws ServiceException {
		StringBuilder sb = new StringBuilder();
		
		if (CollectionUtils.isNotEmpty(checkServices)) {
			for (PersonSelectCheckService checkService : checkServices) {
				sb.append(checkService.check(itemCode, itemCodeNode, person));
			}
		}
		
		return sb.toString();
	}
}
