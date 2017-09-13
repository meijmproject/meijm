package com.yh.admin.position.web.action;

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
import com.yh.admin.bo.SystemPosition;
import com.yh.admin.bo.Users;
import com.yh.admin.dto.RolesDTO;
import com.yh.admin.dto.SystemPositionDTO;
import com.yh.admin.dto.UserSystemPositionDTO;
import com.yh.admin.dto.UsersDTO;
import com.yh.admin.position.facade.SystemPositionFacade;
import com.yh.admin.position.web.form.SystemPositionForm;
import com.yh.admin.users.web.form.UsersForm;
import com.yh.admin.util.AdminConstants;
import com.yh.admin.util.UserUnitAuthThread;
import com.yh.component.dictionary.utils.DicHelper;
import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

public class SystemPositionAction extends BaseAction
{
	private SystemPositionFacade systemPositionFacade = (SystemPositionFacade) SpringBeanUtil.getBean("systemPositionFacade");
//	private UserAgentFacade userAgentFacade = (UserAgentFacade) SpringBeanUtil.getBean("userAgentFacade");
//	private RolesFacade rolesFacade = (RolesFacade) SpringBeanUtil.getBean("rolesFacade");
//	private UsersFacade usersFacade = (UsersFacade) SpringBeanUtil.getBean("usersFacade");
	/**
	 * 跳转到岗位管理列表工作台
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goSystemPoistionList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			return mapping.findForward("success");
		} catch (Exception e) {
			handleException(request, e, "跳转到机关人员基础信息校核工作台失败");
			return mapping.findForward("error");
		}
	}
	/**
	 * 跳转到角色列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goFindRoleList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			return mapping.findForward("success");
		} catch (Exception e) {
			handleException(request, e, "跳转到角色列表失败");
			return mapping.findForward("error");
		}
	}
	/**
	 * 跳转到新增岗位列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goAddSystemPoistion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			return mapping.findForward("success");
		} catch (Exception e) {
			handleException(request, e, "跳转到新增岗位失败");
			return mapping.findForward("error");
		}
	}
	/**
	 * 新增岗位列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addSystemPoistion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			SystemPositionForm systemPositionForm=(SystemPositionForm) form;
			SystemPositionDTO systemPositionDTO = BeanHelper.copyProperties(systemPositionForm,SystemPositionDTO.class);
			if(systemPositionFacade.addSystemPosition(systemPositionDTO,systemPositionDTO.getSystemPositionName())){
				SystemPositionDTO systemPosition=systemPositionFacade.getSystemPositionByName(systemPositionDTO.getSystemPositionName());
				response.getWriter().write(JSONHelper.fromObject(true, systemPosition.getSystemPositionOid().toString()).toString());
			   }else{
				   response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty( null,"系统岗位名称不能重复！")).toString());
			   }
			return null;
		} catch (Exception e) {
			handleException(request, e, "新增岗位失败");
			return mapping.findForward("error");
		}
	}
	/**
	 * 删除岗位
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteSystemPoistion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long systemPositionOid=Long.valueOf(request.getParameter("systemPositionOid"));
			if(systemPositionOid!=null){
				systemPositionFacade.deleteSystemPoistion(systemPositionOid);
			}else{
				response.getWriter().print(JSONHelper.fromObject(false, "岗位ID不存在"));
			}
			response.getWriter().print(JSONHelper.fromObject(true, "删除成功！"));	
		} catch (Exception e) {
			handleException(request, e, "新增岗位失败");
			response.getWriter().print(JSONHelper.fromObject(false, "删除失败"));
		}
		return null;
	}
	/**
	 * 查询岗位管理列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listPosition(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			TableTagBean ttb = new TableTagBean(request);
			
			List<UserSystemPositionDTO> list = systemPositionFacade.findSysPositionList(ttb);
			
			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();
			json.put("total", ttb.getTotal());
			if(CollectionUtils.isNotEmpty(list)){
				JSONObject obj = null;
				for (UserSystemPositionDTO dto : list) {
					obj = JSONHelper.fromObject(dto, DateUtil.DATE_PATTERN_DEFAULT);
					array.element(obj);
				}
			}
			json.put("rows", array);
			response.getWriter().print(json.toString());
			
		} catch (Exception e) {
			handleException(request, e, "查询岗位管理列表失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "查询岗位管理列表失败")));
		}
		
		return null;
	}
	/**
	 * 查询岗位管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goViewSystemPoistion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			SystemPositionForm systemPositionForm=(SystemPositionForm) form;
			Long systemPositionOid=Long.valueOf(request.getParameter("systemPositionOid"));
			if(systemPositionOid!=null){
				SystemPosition systemPosition=systemPositionFacade.findSysPosition(systemPositionOid);
				BeanHelper.copyProperties(systemPosition, systemPositionForm);
				return mapping.findForward("success");
			}else{
				return mapping.findForward("error");
			}
		} catch (Exception e) {
			handleException(request, e, "跳转到岗位管理失败");
			return mapping.findForward("error");
		}
	}
	/**
	 * 查询岗位角色管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goViewPoistionRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			SystemPositionForm systemPositionForm=(SystemPositionForm) form;
			Long systemPositionOid=Long.valueOf(request.getParameter("systemPositionOid"));
			if(systemPositionOid!=null){
				SystemPositionDTO systemPositionDto=systemPositionFacade.viewPoistionRole(systemPositionOid);
				BeanHelper.copyProperties(systemPositionDto, systemPositionForm);
				return mapping.findForward("success");
			}else{
				return mapping.findForward("error");
			}
		} catch (Exception e) {
			handleException(request, e, "跳转到岗位管理失败");
			return mapping.findForward("error");
		}
	}
	/**
	 * 修改岗位管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateSystemPoistion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SystemPositionForm systemPositionForm = (SystemPositionForm)form;
		try{
			SystemPositionDTO verPbPunishmentInfoDTO = BeanHelper.copyProperties(systemPositionForm, SystemPositionDTO.class);
			if(systemPositionFacade.updateSystemPoistion(verPbPunishmentInfoDTO)){
				response.getWriter().write(JSONHelper.fromObject(true, null).toString());
			}else{
				response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty( null,"系统岗位名不能重复！")).toString());
			}
		}catch(ServiceException se){
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(se.getMessage(), "系统岗位信息更新失败！")).toString());
			handleException(request, se, null);
			return null;
		}
		return null;
	}
	/**
	 * 根据岗位ID查询用户
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findUserBySpId(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
				TableTagBean ttb = new TableTagBean(request);
				
				List<UsersDTO> list = systemPositionFacade.findUserBySpId(ttb);
				
				JSONObject json = new JSONObject();
				JSONArray array = new JSONArray();
				json.put("total", ttb.getTotal());
				if(CollectionUtils.isNotEmpty(list)){
					JSONObject obj = null;
					for (UsersDTO dto : list) {
						obj = JSONHelper.fromObject(dto, DateUtil.DATE_PATTERN_DEFAULT);
						obj.put("systemPositionOid", ttb.getCondition().get("systemPositionOid"));
						array.element(obj);
					}
				}
				json.put("rows", array);
				response.getWriter().print(json.toString());
				
		} catch (Exception e) {
			handleException(request, e, "跳转到岗位管理失败");
			response.getWriter().print(JSONHelper.fromObject(false, "跳转到岗位管理失败"));
		}
		return null;
	}
	/**
	 * 删除岗位用户
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deletePositionUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long userOid=Long.valueOf(request.getParameter("userOid"));
			Long systemPositionOid=Long.valueOf(request.getParameter("systemPositionOid"));
			if(userOid!=null){
				systemPositionFacade.deletePositionUserByUserOidAndPositionOid(userOid,systemPositionOid);
				Users users=systemPositionFacade.findUserById(userOid);
				// 新建线程更新某角色下所有人员单位数据权限对应关系
				new UserUnitAuthThread(users.getUserId(), null, null).start();
			}else{
				response.getWriter().print(JSONHelper.fromObject(false, "用户ID不存在"));
			}
			response.getWriter().print(JSONHelper.fromObject(true, "删除成功！"));	
		} catch (Exception e) {
			handleException(request, e, "跳转到岗位管理失败");
			response.getWriter().print(JSONHelper.fromObject(false, "删除失败"));
		}
		return null;
	}
	/**
	 * 跳转新增岗位用户
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goFindPositionUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			TableTagBean ttb = new TableTagBean(request);

			List<UsersDTO> list = systemPositionFacade.findUsersInfo(ttb);
			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();
			json.put("total", ttb.getTotal());
			if(CollectionUtils.isNotEmpty(list)){
				JSONObject obj = null;
				for (UsersDTO dto : list) {
					obj = JSONHelper.fromObject(dto, DateUtil.DATE_PATTERN_DEFAULT);
					obj.put("userStatus", DicHelper.viewName(AdminConstants.YHRS9002, dto.getUserStatus()));
					array.element(obj);
				}
			}
			json.put("rows", array);
			response.getWriter().print(json.toString());
		} catch (Exception e) {
			handleException(request, e, "查询用户基础信息失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "查询用户基础信息失败")));
		}
		return null;
	}
	/**
	 * 新增岗位用户
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addPositionUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String userIds=request.getParameter("userIds");
			Long systemPositionOid=Long.valueOf(request.getParameter("systemPositionOid"));
			String[] userIdStr=userIds.split(",");
			for(int i=0;i<userIdStr.length;i++){
				String userId=userIdStr[i];
				if(userId!=null&&systemPositionOid!=null){
					systemPositionFacade.addPositionUser(userId,systemPositionOid);
						// 新建线程更新某角色下所有人员单位数据权限对应关系
					new UserUnitAuthThread(userId, null, null).start();
					
				}else{
					response.getWriter().print(JSONHelper.fromObject(false, "用户ID不存在"));
				}
			}
			response.getWriter().print(JSONHelper.fromObject(true, null));	
		} catch (Exception e) {
			handleException(request, e, "跳转到岗位管理失败");
			response.getWriter().print(JSONHelper.fromObject(false, "跳转到岗位管理失败"));
		}
		return null;
	}
	/**
	 * 查询岗位用户
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goViewPoistionUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			UsersForm usersForm=(UsersForm) form;
			Long userOid=Long.valueOf(request.getParameter("userOid"));
			Long systemPositionOid=Long.valueOf(request.getParameter("systemPositionOid"));
			if(userOid!=null){
				Users users=systemPositionFacade.findUserById(userOid);
				BeanHelper.copyProperties(users, usersForm);
				
				UserSystemPositionDTO dto = systemPositionFacade.findUserSystemPositionDTO(users.getUserId(), systemPositionOid);
				if(null != dto)
				{
					usersForm.setEffectiveDate(dto.getEffectiveDate());
					usersForm.setExpiredDate(dto.getExpiredDate());
				}
				usersForm.setSystemPositionOid(systemPositionOid);
				return mapping.findForward("success");
			}else{
				return mapping.findForward("error");
			}
		} catch (Exception e) {
			handleException(request, e, "跳转到岗位管理失败");
			return mapping.findForward("error");
		}
	}
	/**
	 * 修改岗位用户
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updatePoistionUserSuc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UsersForm usersForm=(UsersForm) form;
		try{
			UsersDTO usersDTO = BeanHelper.copyProperties(usersForm, UsersDTO.class);
			systemPositionFacade.updateUser(usersDTO);
			response.getWriter().write(JSONHelper.fromObject(true, null).toString());
		}catch(ServiceException se){
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(se.getMessage(), "系统岗位用户信息更新失败！")).toString());
			handleException(request, se, null);
			return null;
		}
		return null;
	}
	/**
	 * 根据角色类型查询角色
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findRoleByType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			TableTagBean ttb = new TableTagBean(request);

			List<RolesDTO> list = systemPositionFacade.findRoleByType(ttb);
			JSONObject json = new JSONObject();
			json.put("total", ttb.getTotal());
			json.put("rows", list);
			response.getWriter().print(json.toString());
		} catch (Exception e) {
			handleException(request, e, "查询角色信息失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "查询角色信息失败")));
		}
		return null;
	}
	/**
	 * 修改岗位角色
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updatePositionUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			String roleId=request.getParameter("roleId");
			String roleType=request.getParameter("roleType");
			String systemPositionOid=request.getParameter("systemPositionOid");
			if(roleId!=null&&roleType!=null&&systemPositionOid!=null){
				systemPositionFacade.updatePositionUser(Long.valueOf(roleId),Long.valueOf(roleType),Long.valueOf(systemPositionOid));
				if (NumberUtils.isNotNullOrZero(systemPositionOid)) {
					// 新建线程更新某角色下所有人员单位数据权限对应关系
					new UserUnitAuthThread(null, null, Long.valueOf(systemPositionOid)).start();
				}
				
			}else{
				response.getWriter().print(JSONHelper.fromObject(false, "修改岗位角色失败！"));
			}
			
			response.getWriter().write(JSONHelper.fromObject(true, null).toString());
		}catch(ServiceException se){
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(se.getMessage(), "修改岗位角色失败！")).toString());
			handleException(request, se, null);
			return null;
		}
		return null;
	}
}
