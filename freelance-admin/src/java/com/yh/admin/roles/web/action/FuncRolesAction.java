package com.yh.admin.roles.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.admin.dto.FunctionsDTO;
import com.yh.admin.dto.MtMenuDto;
import com.yh.admin.dto.RolesDTO;
import com.yh.admin.roles.facade.RolesFacade;
import com.yh.admin.roles.web.form.RolesForm;
import com.yh.admin.util.AuthConstants;
import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.util.TreeNode;
import com.yh.platform.core.web.action.BaseAction;

/**
 * 功能角色action
 * @author	zhangqp
 * @version	1.0,	16/09/09
 */
public class FuncRolesAction extends BaseAction {
	private RolesFacade rolesFacade = (RolesFacade)SpringBeanUtil.getBean("rolesFacade");
	/**
	 * 跳转到功能角色工作台
	 * @throws Exception
	 */
	public ActionForward goFuncRoleList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			return mapping.findForward("success");
		} catch (Exception e) {
			handleException(request, e, "跳转到功能角色工作台失败");
			return mapping.findForward("error");
		}
	}

	/**
	 * 查询功能角色列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listFuncRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			TableTagBean ttb = new TableTagBean(request);
			
			ttb.getCondition().put("roleType", AuthConstants.ROLE_TYPE_FUNCTION);
			
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
			handleException(request, e, "查询功能角色列表失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "查询功能角色列表失败")));
		}
		return null;
	}
	
	/**
	 * 跳转到功能角色增加页面
	 * @throws Exception
	 */
	public ActionForward goAddFuncRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			
			return mapping.findForward("success");
		} catch (Exception e) {
			handleException(request, e, "跳转到功能角色增加页面失败");
			return mapping.findForward("error");
		}
	}
	
	/**
	 * 功能角色创建
	 * @throws Exception
	 */
	public ActionForward addFuncRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RolesForm rolesForm = (RolesForm)form;
		try {
				RolesDTO rolesDTO = BeanHelper.copyProperties(rolesForm, RolesDTO.class);
				rolesDTO.setRoleType(AuthConstants.ROLE_TYPE_FUNCTION);
			 if(rolesFacade.checkRoleByRoleName(rolesForm.getRoleName(),rolesDTO)){
				String roleId=rolesFacade.findRolesIdByName(rolesDTO.getRoleName(),AuthConstants.ROLE_TYPE_FUNCTION);
			    response.getWriter().write(JSONHelper.fromObject(true, StringUtils.defaultIfEmpty( null,roleId)).toString());
			 }else{
				 response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty( null,"功能角色名称不能重复！")).toString());
				 return null;
			 }
	        } catch (Exception e) {
	            handleException(request, e, e.getMessage());
	            response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "增加功能角色失败")).toString());
	            return null;
	       }
		return null;
	}
	
	/**
	 * 跳转到功能角色修改工作台
	 * @return
	 * @throws Exception
	 */
	public ActionForward goUpdateFuncRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			handleException(request, e, "跳转到功能角色修改工作台失败");
			return mapping.findForward("error");
		}
	}
	
	/**
	 * 跳转功能角色模态框
	 * */
	
	public ActionForward goUpdateFuncRoleModel (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			handleException(request, e, "跳转到功能角色修改模态框失败");
			return mapping.findForward("error");
		}
	}
	
	/**
	 * 功能角色修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateFuncRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RolesForm rolesForm = (RolesForm)form;
		try {
			if(rolesFacade.checkRoleByRoleNameAndID(rolesForm.getRoleName(),rolesForm.getRoleId())){
				RolesDTO rolesDTO = BeanHelper.copyProperties(rolesForm, RolesDTO.class);
				rolesDTO.setRoleType(AuthConstants.ROLE_TYPE_FUNCTION);
				rolesFacade.updateRole(rolesDTO);
				
			    response.getWriter().write(JSONHelper.fromObject(true, StringUtils.defaultIfEmpty( null,"修改成功！")).toString());
			 }else{
				 response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty( null,"功能角色名称不能重复！")).toString());
				 return null;
			 }
			
			return null;
		} catch (Exception e) {
			handleException(request, e, "功能角色修改失败");
			return mapping.findForward("error");
		}
	}
	/**
	 * 功能角色查看工作台
	 * */
	
	public ActionForward showFuncRoleWorktop(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			handleException(request, e, "跳转到功能角色修改工作台失败");
			return mapping.findForward("error");
		}
	}
	/**
	 * 查看功能角色信息
	 * @throws Exception
	 */
	public ActionForward showleftFuncRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String  roleId =request.getParameter("roleId");
			if(StringUtils.isNotEmpty(roleId)){
				RolesDTO rolesDTo= rolesFacade.findRolesByID(roleId);
				request.setAttribute("rolesDTo", rolesDTo);
			}
			return mapping.findForward("success");
		} catch (Exception e) {
			handleException(request, e, "跳转到功能角色查看页面失败");
			return mapping.findForward("error");
		}
	}
	/**
	 * 删除角色
	 * @param roleID
	 * @throws Exception
	 * */
	public ActionForward deleteFuncRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String  roleId =request.getParameter("roleId");
			if(StringUtils.isNotEmpty(roleId)){
				rolesFacade.deleteRoleByRoleID(roleId,AuthConstants.ROLE_TYPE_FUNCTION);
				response.getWriter().write(JSONHelper.fromObject(true, null).toString());
			}else{
				response.getWriter().write(JSONHelper.fromObject(true, "刪除功能角色失败！").toString());
			}
		} catch (Exception e) {
			handleException(request, e, "删除功能角色异常");
			return null;
		}
		return null;
	}
	/**
	 * 查询功能模块
	 * @param roleID
	 * @throws Exception
	 * */
	private JSONArray toJSONArray(List<TreeNode<FunctionsDTO>> nodes) {
		JSONArray ary = new JSONArray();
		
		if (CollectionUtils.isNotEmpty(nodes)) {
			for (TreeNode<FunctionsDTO> node : nodes) {
				ary.element(toJSON(node));
			}
		}
		
		return ary;
	}
	private JSONArray toJSONArrayItem(List<TreeNode<MtMenuDto>> nodes) {
		JSONArray ary = new JSONArray();
		
		if (CollectionUtils.isNotEmpty(nodes)) {
			for (TreeNode<MtMenuDto> node : nodes) {
				ary.element(toJSONItem(node));
			}
		}
		
		return ary;
	}
	private JSONObject toJSON(TreeNode<FunctionsDTO> node) {
		JSONObject json = JSONHelper.fromObject(node.getEntry());
		json.putAll(node.getAttributes());
		json.put("children", toJSONArray(node.getChildren()));
		json.put("leaf",node.isLeaf());
		
		return json;
	}
	private JSONObject toJSONItem(TreeNode<MtMenuDto> node) {
		JSONObject json = JSONHelper.fromObject(node.getEntry());
		json.putAll(node.getAttributes());
		json.put("children", toJSONArrayItem(node.getChildren()));
		json.put("leaf",node.isLeaf());
		
		return json;
	}
	/**
	 * 查询功能授权列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findFunction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String roleId=request.getParameter("roleId");
			
			List<TreeNode<FunctionsDTO>> list = rolesFacade.findFunction(null,roleId);
			
			response.getWriter().print(toJSONArray(list).toString());
			
		} catch (Exception e) {
			handleException(request, e, "加载Ztree授权树失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "加载Ztree授权树失败")));
		}
		
		return null;
	}
	/**
	 * 查询事项树 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findItemTree(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String roleId=request.getParameter("roleId");
			String menuType=request.getParameter("menuType");
			List<TreeNode<MtMenuDto>> list = rolesFacade.findItemTree(null,roleId,menuType);
			
			response.getWriter().print(toJSONArrayItem(list).toString());
			
		} catch (Exception e) {
			handleException(request, e, "加载事项树失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "加载事项树失败")));
		}
		
		return null;
	}
	/**
	 * 功能授权
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateRoleFunction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String roleId=request.getParameter("roleId");
			String funcIds=request.getParameter("funcIds");
			
			rolesFacade.updateRoleFunction(roleId,funcIds);
			response.getWriter().print(JSONHelper.fromObject(true,"授权成功！"));
		} catch (Exception e) {
			handleException(request, e, "授权失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "授权失败")));
		}
		
		return null;
	}
	/**
	 * 事项环节授权
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateRoleItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String roleId=request.getParameter("roleId");
			String menuCode=request.getParameter("menuCodes");
			String flag = request.getParameter("flag");
			rolesFacade.updateRoleItemNode(roleId,menuCode,flag);
			response.getWriter().print(JSONHelper.fromObject(true,"授权成功！"));
		} catch (Exception e) {
			handleException(request, e, "授权失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "授权失败")));
		}
		
		return null;
	}
}
