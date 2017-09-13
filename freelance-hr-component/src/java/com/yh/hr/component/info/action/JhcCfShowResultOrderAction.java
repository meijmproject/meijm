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

import com.yh.hr.component.info.facade.JhcCfShowResultOrderFacade;
import com.yh.hr.res.cf.dto.JhcCfShowResultOrderDTO;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;
import com.yh.platform.core.web.action.BaseAction;

public class JhcCfShowResultOrderAction extends BaseAction {
	
	private JhcCfShowResultOrderFacade jhcCfShowResultOrderFacade = (JhcCfShowResultOrderFacade)SpringBeanUtil.getBean("jhcCfShowResultOrderFacade");
	
	public ActionForward saveSortFields(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String data = request.getParameter("data");
		String functionCode = request.getParameter("functionCode");
		JSONArray arr = JSONArray.fromObject(data);
		List<JhcCfShowResultOrderDTO> list = new ArrayList<JhcCfShowResultOrderDTO>();
		for(int i=0; i<arr.size(); i++) {
			JSONObject obj = arr.getJSONObject(i);
			JhcCfShowResultOrderDTO dto = new JhcCfShowResultOrderDTO();
			dto.setUserId(UserContext.getLoginUserID());
			dto.setResultOid(obj.getLong("resultOid"));
			dto.setFieldOrder(obj.getLong("fieldOrder"));
			dto.setResultOrder(obj.getString("sort"));
			list.add(dto);
		}
		jhcCfShowResultOrderFacade.saveSortFields(list,functionCode);
		return null;
	}
}
