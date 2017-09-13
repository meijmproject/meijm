package com.yh.hr.component.info.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.hr.component.info.facade.JhcCfShowFieldOrderFacade;
import com.yh.hr.res.cf.dto.JhcCfShowFieldOrderDTO;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;
import com.yh.platform.core.web.action.BaseAction;


public class JhcCfShowFieldOrderAction extends BaseAction {

	private JhcCfShowFieldOrderFacade jhcCfShowFieldOrderFacade = (JhcCfShowFieldOrderFacade)SpringBeanUtil.getBean("jhcCfShowFieldOrderFacade");
	
	public ActionForward saveOrderFields(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String data = request.getParameter("data");
		JSONArray arr = JSONArray.fromObject(data);
		List<JhcCfShowFieldOrderDTO> list = new ArrayList<JhcCfShowFieldOrderDTO>();
		for(int i=0; i<arr.size(); i++) {
			JSONObject obj = arr.getJSONObject(i);
			JhcCfShowFieldOrderDTO dto = new JhcCfShowFieldOrderDTO();
			dto.setUserId(UserContext.getLoginUserID());
			dto.setResultOid(obj.getLong("resultOid"));
			dto.setFieldOrder(obj.getLong("fieldOrder"));
			dto.setIsShow(obj.getString("isShow"));
			list.add(dto);
		}
		jhcCfShowFieldOrderFacade.saveOrderFields(list);
		return null;
	}
}
