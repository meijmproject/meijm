package com.yh.hr.orghc.unit.orgmanger.web.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.orghc.ub.dto.UbOrgDTO;
import com.yh.hr.orghc.unit.unitmanger.facade.UnitMangerFacade;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.hr.orghc.unit.unitmanger.dto.OrgDTO;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

public class OrgMangerAction extends BaseAction {
	
	private UnitMangerFacade unitMangerFacade = (UnitMangerFacade) SpringBeanUtil.getBean("unitMangerFacade");

	
	/**
	 * 跳转科室管理主页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goToOrgManageWorkBench(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		return mapping.findForward("success");
	}
	
	/**
	 * 科室管理工作台
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goToOrgManage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		request.setAttribute("unitOid", request.getParameter("unitOid"));
		return mapping.findForward("success");
	}
	
	/**
	 * 跳转查看科室大页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goToViewOrg(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		try {
			String orgOid=request.getParameter("orgOid");
			OrgDTO dto=unitMangerFacade.getOrg(NumberUtils.longValue(orgOid));
			if(null!=dto){
				if(NumberUtils.isNotNullOrZero(dto.getParentOrgOid())){
					UbOrgDTO parentDTO=unitMangerFacade.getParentOrg(dto.getParentOrgOid());
					if(null!=parentDTO){
						if(DicConstants.YHRS0101_1.equals(parentDTO.getOrgType())||DicConstants.YHRS0101_2.equals(parentDTO.getOrgType())||DicConstants.YHRS0101_3.equals(parentDTO.getOrgType())){
						}else{
							dto.setParentOrgName(null);
						}
					}
				}
				BeanHelper.copyProperties(dto, form);
			}
			request.setAttribute("orgOid", request.getParameter("orgOid"));
		} catch (Exception e) {
			handleException(request, e, e.getMessage());
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "跳转查看科室页面失败")).toString());
			return null;
		}
		return mapping.findForward("success");
	}
}
