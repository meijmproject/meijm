/**
 * 
 */
package com.yh.hr.hc.info.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.hc.info.facade.HrHcCashFacade;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.hr.res.hc.dto.HcCashDTO;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/10/27
 */

public class HrHcCashAction extends BaseAction {
	private HrHcCashFacade hrHcCashFacade = (HrHcCashFacade)SpringBeanUtil.getBean("hrHcCashFacade");
	
	/**
	 * 查找单位编制资源信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findUnitHcCash(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			Long unitOid = NumberUtils.longValue(request.getParameter("unitOid"));
			String hcCode = request.getParameter("hcCode");
			String budgetFromCode = request.getParameter("budgetFromCode");
			
			List<HcCashDTO> list = hrHcCashFacade.findUnitHcCash(unitOid, hcCode, budgetFromCode);
			
			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();
			int i = 0;
			if(CollectionUtils.isNotEmpty(list)){
				JSONObject obj0 = new JSONObject();
				obj0.put("hcCode","");
				obj0.put("hcName", "--请选择--");
				array.element(obj0);
				i++;
				for (HcCashDTO dto : list) {
					if("0".equals(NumberUtils.toString(dto.getApprovedCount())) || (null == dto.getApprovedCount())){
						continue;
					}
					array.element(JSONHelper.fromObject(dto));
					i++;
				}
			}
			json.put("rows", array);
			json.put("le", i);
			response.getWriter().print(json.toString());
		} catch (Exception e) {
			handleException(request, e, "查找单位编制资源信息失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "查找单位编制资源信息失败")));
		}
		return null;
	}
}
