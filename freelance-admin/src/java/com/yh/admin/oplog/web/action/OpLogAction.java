package com.yh.admin.oplog.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.admin.oplog.dto.OpLogDTO;
import com.yh.admin.oplog.facade.OpLogFacade;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OpLogAction extends BaseAction{

	private OpLogFacade opLogFacade = (OpLogFacade) SpringBeanUtil.getBean("opLogFacade");
	
	//跳转到系统日志工作台
	public ActionForward goOpLogList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		return mapping.findForward("success");
	}
	//查询日志信息
	public ActionForward showOpLogList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		try {
			TableTagBean ttb = new TableTagBean(request);
			List<OpLogDTO> list = opLogFacade.listOpLog(ttb);
			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();
			json.put("total", ttb.getTotal());
			if(CollectionUtils.isNotEmpty(list)){
				JSONObject obj = null;
				for (OpLogDTO dto : list) {
					obj = JSONHelper.fromObject(dto, DateUtil.DATE_PATTERN_DEFAULT);
					
					array.element(obj);
				}
			}
			json.put("rows", array);
			response.getWriter().print(json.toString());
		} catch (Exception e) {
			handleException(request, e, "查询系统日志信息失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "查询用户基础信息失败")));
		}
		return null;
	}
}
