package com.yh.hr.select.post.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.select.post.dto.PostSelectDTO;
import com.yh.hr.select.post.facade.PostSelectFacade;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

public class PostSelectAction extends BaseAction {

	private PostSelectFacade postSelectFacade = (PostSelectFacade) SpringBeanUtil
			.getBean("postSelectFacade");

	public ActionForward selectPost(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TableTagBean ttb = new TableTagBean(request);
		ttb.getCondition().put("bizPersonOid", request.getParameter("bizPersonOid"));
		try {
			List<PostSelectDTO> list = postSelectFacade.listPbPostInfo(ttb);
			List<JSONObject> results = new ArrayList<JSONObject>();
			JSONObject json = null;
			
			if (CollectionUtils.isNotEmpty(list)) {
				for (PostSelectDTO dto : list) {
					//1、翻译，数据处理
					json = JSONHelper.fromObject(dto);
					json.put("positionType", DicHelper.viewName(DicConstants.YHRS0022, ObjectUtils.toString(json.get("positionType"), null)));
					json.put("positionLevel", DicHelper.viewName(DicConstants.YHRS0023, ObjectUtils.toString(json.get("positionLevel"), null)));
					json.put("dutyLevel", DicHelper.viewName(DicConstants.YHRS0015, ObjectUtils.toString(json.get("dutyLevel"), null)));
					json.put("beginDate", DateUtil.formatDate(dto.getBeginDate()));
					json.put("endDate", DateUtil.formatDate(dto.getEndDate()));
					results.add(json);
				}
				json.put("total", ttb.getTotal());
				json.put("rows", null != results ? results : new ArrayList<JSONObject>());
				response.getWriter().print(json.toString());
			}
		} catch (Exception e) {
			this.handleException(request, e, "查询拟免岗位信息选择列表失败!");
			response.getWriter().print(
					JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(
							e.getMessage(), "查询拟免岗位信息失败")));
		}
		return null;
	}

	public ActionForward goSelectPost(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("bizPersonOid", request.getParameter("bizPersonOid"));
		return mapping.findForward("success");
	}

}
