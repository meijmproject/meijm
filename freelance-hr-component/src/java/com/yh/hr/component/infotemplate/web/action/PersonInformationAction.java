package com.yh.hr.component.infotemplate.web.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.component.infotemplate.facade.PersonInformationFacade;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.hr.component.infotemplate.dto.ItLibraryGroupDetailDTO;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

public class PersonInformationAction extends BaseAction {
	private PersonInformationFacade personInformationFacade =(PersonInformationFacade) SpringBeanUtil.getBean("personInformationFacade");


	// 根据人员类别查询人所对应的信息集
	public ActionForward findInByfunctionCode(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		try {
			String functionCode = request.getParameter("functionCode");
			if(StringUtils.isNotEmpty(functionCode)){
				List<ItLibraryGroupDetailDTO> list=personInformationFacade.findInforList(functionCode);
				JSONObject json= new JSONObject();
				json.put("list", list);
				response.getWriter().write(json.toString());
			}else{
				response.getWriter().write(JSONHelper.fromObject(false, "该人员无人员类别,暂无信息集").toString());
			}
		} catch (Exception se) {
			handleException(request, se, null);
			return mapping.findForward("fail");
		}
		return null;
	}
	//根据单位id查看单位类别
	public ActionForward findUnitType(ActionMapping mapping,
			ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		try {
			String unitOid = request.getParameter("unitOid");
			if(StringUtils.isNotEmpty(unitOid)){
				String unitType=personInformationFacade.findUnitType(unitOid);
				JSONObject json= new JSONObject();
				json.put("unitType", unitType);
				response.getWriter().write(json.toString());
			}else{
				response.getWriter().write(JSONHelper.fromObject(false, "该人员无人员类别,暂无信息集").toString());
			}
		} catch (Exception se) {
			handleException(request, se, null);
			return mapping.findForward("fail");
		}
		return null;
	}
		public ActionForward findUnitTypeForUpdate(ActionMapping mapping,
				ActionForm form, javax.servlet.http.HttpServletRequest request,
				javax.servlet.http.HttpServletResponse response)
				throws java.lang.Exception {
			try {
				String unitOid = request.getParameter("unitOid");
				if(StringUtils.isNotEmpty(unitOid)){
					String unitType=personInformationFacade.findUnitTypeForUpdate(unitOid);
					JSONObject json= new JSONObject();
					json.put("unitType", unitType);
					response.getWriter().write(json.toString());
				}else{
					response.getWriter().write(JSONHelper.fromObject(false, "该人员无人员类别,暂无信息集").toString());
				}
			} catch (Exception se) {
				handleException(request, se, null);
				return mapping.findForward("fail");
			}
			return null;
		}
	public ActionForward goVerPersonBasic(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			return mapping.findForward("success");
		} catch (Exception e) {
			handleException(request, e, null);
			return mapping.findForward("error");
		}
	}

	public ActionForward goVerPersonFor(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			return mapping.findForward("success");
		} catch (Exception e) {
			handleException(request, e, null);
			return mapping.findForward("error");
		}
	}

	public ActionForward goVerPersonSchool(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			return mapping.findForward("success");
		} catch (Exception e) {
			handleException(request, e, null);
			return mapping.findForward("error");
		}
	}

}
