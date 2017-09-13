package com.yh.component.config.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.component.config.util.PropertiesHelper;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.web.action.BaseAction;

public class PropertiesAction extends BaseAction {

	/**
	 * 获取系统配置参数值
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findCfgPropertiesValue(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String cfgPropertiesCode = request.getParameter("cfgPropertiesCode");
			String cfgPropertiesType = request.getParameter("cfgPropertiesType");
			String cfgPropertiesValue = PropertiesHelper.findCfgPropertiesValueByTypeAndCode(cfgPropertiesType, cfgPropertiesCode);
			JSONObject json = new JSONObject();
			if (cfgPropertiesValue!=null) {
				json.put("cfgPropertiesValue", cfgPropertiesValue);
			}
			response.getWriter().print(json.toString());
			return null;
		} catch (ServiceException e) {
			handleException(request, e, "find value faild!");
			return mapping.findForward(FORWARD_FAIL);
		}
	}
}
