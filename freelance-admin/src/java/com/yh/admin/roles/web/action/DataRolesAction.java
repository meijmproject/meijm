package com.yh.admin.roles.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.admin.dto.RoleDataAuthDTO;
import com.yh.admin.dto.RolesDTO;
import com.yh.admin.roles.facade.RoleDataAuthFacade;
import com.yh.admin.roles.web.form.RolesForm;
import com.yh.admin.util.AuthConstants;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.admin.roles.facade.RolesFacade;
import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

/**
 * 数据角色action
 * @author	zhangqp
 * @version	1.0,	16/09/09
 */
public class DataRolesAction extends BaseAction {
	private RolesFacade rolesFacade = (RolesFacade)SpringBeanUtil.getBean("rolesFacade");
	private RoleDataAuthFacade roleDataAuthFacade	= (RoleDataAuthFacade) SpringBeanUtil.getBean("roleDataAuthFacade");
	
	 /**
	 *@param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	  * */
		public ActionForward goDataRoleList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			try {
				return mapping.findForward("success");
			} catch (Exception e) {
				handleException(request, e, "跳转到数据角色工作台失败");
				return mapping.findForward("error");
			}
		}

		/**
		 * 查询数据角色列表
		 * 
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
		public ActionForward listDataRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			try {
				
				TableTagBean ttb = new TableTagBean(request);
				
				ttb.getCondition().put("roleType", AuthConstants.ROLE_TYPE_DATA);
				List<RolesDTO> list = rolesFacade.listByCondition(ttb);
				
				JSONObject json = new JSONObject();
				JSONArray array = new JSONArray();
				json.put("total", ttb.getTotal());
				if(CollectionUtils.isNotEmpty(list)){
					JSONObject obj = null;
					for (RolesDTO dto : list) {
						obj = JSONHelper.fromObject(dto, DateUtil.DATE_PATTERN_DEFAULT);
						array.element(obj);
					}
				}
				json.put("rows", array);
				response.getWriter().print(json.toString());
				
			} catch (Exception e) {
				handleException(request, e, "查询数据角色列表失败");
				response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "查询功能角色列表失败")));
			}
			
			return null;
		}
	/**
	 * 跳转数据角色新增模态框
	 * */
	public ActionForward goCreateDataRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			try {
				return mapping.findForward("success");
			} catch (Exception e) {
				handleException(request, e, "跳转到数据角色添加页面失败");
				return mapping.findForward("error");
			}
		}	
	/**
	 * 数据角色新增
	 * @throws Exception
	 * */
	
	public ActionForward createDataRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RolesForm rolesForm = (RolesForm)form;
		try {
			  RolesDTO rolesDTO = BeanHelper.copyProperties(rolesForm, RolesDTO.class);
			  rolesDTO.setRoleType(AuthConstants.ROLE_TYPE_DATA);
		    if(rolesFacade.checkRoleByRoleName(rolesForm.getRoleName(),rolesDTO)){
		    	
		    	String roleId=rolesFacade.findRolesIdByName(rolesDTO.getRoleName(),AuthConstants.ROLE_TYPE_DATA);
		    	response.getWriter().write(JSONHelper.fromObject(true, StringUtils.defaultIfEmpty( null,roleId)).toString());
		    }else{
				 response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty( null,"数据角色名称不能重复！")).toString());
				 return null;
			 }
	        } catch (Exception e) {
	            handleException(request, e, e.getMessage());
	            response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "增加数据角色失败")).toString());
	            return null;
	       }
		return null;
	}	
	/**
	 * 跳转到数据角色修改综合页面
	 * @throws Exception
	 */
	public ActionForward goUpdateDataRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String  roleId =request.getParameter("roleId");
			if(StringUtils.isNotEmpty(roleId)){
				//RolesDTO rolesDTO= rolesFacade.findRolesByID(roleId);
				request.setAttribute("roleId", roleId);
			}
			return mapping.findForward("success");
		} catch (Exception e) {
			handleException(request, e, "跳转到功能角色修改工作台失败");
			return mapping.findForward("error");
		}
	}
	/**
	 * 跳转到数据角色修改页面
	 * @throws Exception
	 */
	
	public ActionForward goUpdateDataRoleModel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String  roleId =request.getParameter("roleId");
			if(StringUtils.isNotEmpty(roleId)){
				RolesDTO rolesDTo= rolesFacade.findRolesByID(roleId);
				RolesForm rolesForm=new RolesForm();
				BeanHelper.copyProperties(rolesDTo,rolesForm);
				request.setAttribute("rolesForm", rolesForm);
			}
			return mapping.findForward("success");
		} catch (Exception e) {
			handleException(request, e, "跳转到数据角色修改模态框失败");
			return mapping.findForward("error");
		}
	}
	
	/**
	 *数据角色修改
	 * @throws Exception
	 */
	public ActionForward updateDataRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RolesForm rolesForm = (RolesForm)form;
		try {
			if(rolesFacade.checkRoleByRoleNameAndID(rolesForm.getRoleName(),rolesForm.getRoleId())){
				RolesDTO rolesDTO = BeanHelper.copyProperties(rolesForm, RolesDTO.class);
				rolesDTO.setRoleType(AuthConstants.ROLE_TYPE_DATA);
				rolesFacade.updateRole(rolesDTO);
				
			    response.getWriter().write(JSONHelper.fromObject(true, rolesDTO.getRoleId().toString()).toString());
			 }else{
				 response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty( null,"数据角色名称不能重复！")).toString());
				 return null;
			 }
			
			return null;
		} catch (Exception e) {
			handleException(request, e, "数据角色修改失败");
			return mapping.findForward("error");
		}
	}
	
	
	
	/**
	 * 数据角色综合信息查看
	 * @throws Exception
	 */
	
	public ActionForward showDataRoleMain(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String  roleId =request.getParameter("roleId");
			if(StringUtils.isNotEmpty(roleId)){
				request.setAttribute("roleId", roleId);
			}
			return mapping.findForward("success");
		} catch (Exception e) {
			handleException(request, e, "跳转到数据角色查看综合页面失败");
			return mapping.findForward("error");
		}
	}
	/**
	 * 数据角色信息查看
	 * @throws Exception
	 */
	public ActionForward showDataRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String  roleId =request.getParameter("roleId");
			if(StringUtils.isNotEmpty(roleId)){
				RolesDTO rolesDTo= rolesFacade.findRolesByID(roleId);
				List<RoleDataAuthDTO> unitSelectList = roleDataAuthFacade.listRoleUnitCodeAuth(Long.valueOf(roleId));
				request.setAttribute("unitSelectList", unitSelectList);//已授权的单位
				request.setAttribute("rolesDTo", rolesDTo);
			}
			return mapping.findForward("success");
		} catch (Exception e) {
			handleException(request, e, "跳转到数据角色查看页面失败");
			return mapping.findForward("error");
		}
	}
	/**
	 * 删除角色
	 * @param roleID
	 * @throws Exception
	 * */
	public ActionForward deleteDataRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String  roleId =request.getParameter("roleId");
			if(StringUtils.isNotEmpty(roleId)){
				rolesFacade.deleteRoleByRoleID(roleId,AuthConstants.ROLE_TYPE_DATA);
				response.getWriter().write(JSONHelper.fromObject(true, null).toString());
			}else{
				response.getWriter().write(JSONHelper.fromObject(true, "刪除数据角色失败！").toString());
			}
		} catch (Exception e) {
			handleException(request, e, "删除数据角色异常");
			return null;
		}
		return null;
	}
	
}
