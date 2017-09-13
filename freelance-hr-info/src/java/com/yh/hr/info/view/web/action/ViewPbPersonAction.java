/**
 * 
 */
package com.yh.hr.info.view.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.info.ver.unit.person.dto.VerPbPersonInfoDTO;
import com.yh.hr.info.ver.unit.person.web.form.VerPbPersonInfoForm;
import com.yh.hr.info.view.facade.ViewPbPersonInfoFacade;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

/**
 * @desc 基础人员信息校核查看WEB层
 * @author luqy
 * @createDate 2016-8-15下午03:50:49
 */
public class ViewPbPersonAction extends BaseAction {
	private ViewPbPersonInfoFacade viewPbPersonInfoFacade	= (ViewPbPersonInfoFacade) SpringBeanUtil.getBean("viewPbPersonInfoFacade");

	/**
	 * @desc 跳转至人员基本信息查看页面
	 * @author luqy
	 * @createDate 2016-8-15下午04:13:22
	 */
	public ActionForward goToViewPbPersonPage(	ActionMapping mapping ,
													ActionForm form ,
													HttpServletRequest request ,
													HttpServletResponse response) throws Exception {
		try {
			VerPbPersonInfoForm verPbPersonInfoForm = (VerPbPersonInfoForm) form;
			if(verPbPersonInfoForm == null){verPbPersonInfoForm = new VerPbPersonInfoForm();}
			String  personOid  = request.getParameter("personOid");
			VerPbPersonInfoDTO pbPersonInfoDTO = viewPbPersonInfoFacade.getPbPersonInfoDTOById(NumberUtils.longValue(personOid));
			BeanHelper.copyProperties(pbPersonInfoDTO, verPbPersonInfoForm);
			//查询单位名称
			String unitName = viewPbPersonInfoFacade.getUnitName(verPbPersonInfoForm.getUnitOid());
			verPbPersonInfoForm.setUnitName(unitName);
			//查询部门名称
			if(null != verPbPersonInfoForm.getDeptOid())
			{
				verPbPersonInfoForm.setDeptName(viewPbPersonInfoFacade.getOrgName(verPbPersonInfoForm.getDeptOid()));
			}
			//查询所在部门名称
			if(null != verPbPersonInfoForm.getHireDeptOid())
			{
				verPbPersonInfoForm.setHireDeptName(viewPbPersonInfoFacade.getOrgName(verPbPersonInfoForm.getHireDeptOid()));
			}
			//查询现任职务所在部门
			if(null != verPbPersonInfoForm.getDutyDeptOid())
			{
				verPbPersonInfoForm.setDutyDeptName(viewPbPersonInfoFacade.getOrgName(verPbPersonInfoForm.getDutyDeptOid()));
			}
			
			request.setAttribute("flag", request.getParameter("flag"));
			request.setAttribute("updateFlag", request.getParameter("updateFlag"));
			//BeanHelper.copyProperties(verJgPbPersonInfoDTO, VerJgPbPersonInfoForm.class);
		}
		catch (Exception e) {
			handleException(request, e, null);
			return mapping.findForward("error");
		}
		return mapping.findForward("success");
	}

}
