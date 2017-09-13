package com.yh.admin.users.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yh.admin.dto.UserPositionInfoDTO;
import com.yh.admin.dto.UsersDTO;
import com.yh.admin.oplog.OpLogHelper;
import com.yh.admin.sao.person.dto.SaoAdminPersonDTO;
import com.yh.admin.sao.unit.dto.SaoAdminOrgDTO;
import com.yh.admin.sao.unit.dto.SaoAdminUnitDTO;
import com.yh.admin.users.facade.UsersFacade;
import com.yh.admin.users.web.form.UserSystemPositionForm;
import com.yh.admin.users.web.form.UsersForm;
import com.yh.admin.util.AdminConstants;
import com.yh.admin.util.SystemConstants;
import com.yh.admin.util.UserUnitAuthThread;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.admin.dto.UserRelationDTO;
import com.yh.admin.dto.UserSystemPositionDTO;
import com.yh.component.dictionary.utils.DicHelper;
import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.CommonFunctions;
import com.yh.platform.core.util.ConfigUtil;
import com.yh.platform.core.util.CryptoUtil;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.PasswordCheckUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.util.StringUtil;
import com.yh.platform.core.web.action.BaseAction;

/**
 * @description Users Action
 * @author wm
 * @created Dec 04, 2006
 * @version 1.0
 */

public class UsersAction extends BaseAction
{
	private UsersFacade usersFacade = (UsersFacade) SpringBeanUtil.getBean("usersFacade");
	
	public ActionForward goUsersList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		return mapping.findForward("success");
	}
	public ActionForward listUsersInfo(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
	
			try {
				TableTagBean ttb = new TableTagBean(request);
				List<UsersDTO> list = usersFacade.listUsersInfo(ttb);
				JSONObject json = new JSONObject();
				JSONArray array = new JSONArray();
				json.put("total", ttb.getTotal());
				if(CollectionUtils.isNotEmpty(list)){
					JSONObject obj = null;
					for (UsersDTO dto : list) {
						obj = JSONHelper.fromObject(dto, DateUtil.DATE_PATTERN_DEFAULT);
						obj.put("userStatus", DicHelper.viewName(AdminConstants.YHRS9002, dto.getUserStatus()));
						//obj.put("defaultLoginSystem", DicHelper.viewName(SystemConstants.SYSTEM_HR, dto.getDefaultLoginSystem()));
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
	 * 修改页面的用户信息
	 * */
	public ActionForward updateUserInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UsersForm usersForm=(UsersForm) form;
		try {
			String userOid =request.getParameter("userOid");
			if(StringUtils.isNotEmpty(userOid)){
			UsersDTO usersDto=usersFacade.getUserByOid(userOid);
			BeanHelper.exportProperties(usersDto, usersForm);
			usersForm.setUserSex(DicHelper.viewName(AdminConstants.YHRS0001, usersForm.getUserSex()));
			usersForm.setUserStatus(DicHelper.viewName(AdminConstants.YHRS9002, usersForm.getUserStatus()));
			usersForm.setUserType(DicHelper.viewName(AdminConstants.YHRS9001, usersForm.getUserType()));
			}
		} catch (Exception se) {
			handleException(request, se, null);
			return mapping.findForward("fail");
		}
		return mapping.findForward("success");
	}
	/**
	 * 查看页面的用户信息
	 * */
	
	public ActionForward showUserInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			UsersForm usersForm=(UsersForm) form;
			try {
				String userOid =request.getParameter("userOid");
				if(StringUtils.isNotEmpty(userOid)){
				UsersDTO usersDto=usersFacade.getUserByOid(userOid);
				BeanHelper.exportProperties(usersDto, usersForm);
				usersForm.setUserSex(DicHelper.viewName(AdminConstants.YHRS0001, usersForm.getUserSex()));
				usersForm.setUserStatus(DicHelper.viewName(AdminConstants.YHRS9002, usersForm.getUserStatus()));
				usersForm.setUserType(DicHelper.viewName(AdminConstants.YHRS9001, usersForm.getUserType()));
				}
			} catch (Exception se) {
				handleException(request, se, null);
				return mapping.findForward("fail");
			}
			return mapping.findForward("success");
		
	}
	/**
	 * @desc 跳转用户基础信息综合查看页面
	 *
	 */
	public ActionForward goShowUsersMain(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userOid =request.getParameter("userOid");
		String usersId=request.getParameter("userId");
		if(StringUtils.isNotEmpty(userOid)){
			request.setAttribute("userOid", userOid);
		}
		if(StringUtils.isNotEmpty(usersId)){
			request.setAttribute("usersId", usersId);
		}
		return mapping.findForward("success");
	}
	/**
	 * 跳转到用户信息修改页面
	 * @param usersOid
	 * */
	 public ActionForward goUpdateUsers(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	        this.saveToken(request);
	        UsersForm usersForm=(UsersForm) form;
	        try {
	            String userOid = request.getParameter("userOid");
	        	if(StringUtils.isNotEmpty(userOid)){
	        		UsersDTO usersDto =usersFacade.getUserByOid(userOid);
					BeanHelper.exportProperties(usersDto, usersForm);
					UserRelationDTO userRelationDTO = usersFacade.getUserRelationDTOByUserId(usersDto.getUserId());
					usersForm.setUserSexDesc(DicHelper.viewName(AdminConstants.YHRS0001, usersDto.getUserSex()));
					usersForm.setRefOid(userRelationDTO==null?null:userRelationDTO.getRefOid());
					usersForm.setRefType(userRelationDTO==null?null:userRelationDTO.getRefType());
	        	}
				request.setAttribute("usersForm", usersForm);
	        } catch (Exception e) {
	            handleException(request, e, null);
	            return mapping.findForward(FORWARD_FAIL);
	        }
	        return mapping.findForward(FORWARD_SUCCESS);
	    }
	/**
	 * 
	 * 用户信息修改
	 * 
	 * */
	public ActionForward updateUsers(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			UsersForm usersForm=(UsersForm) form;
			 try {
				 UsersDTO usersDto = BeanHelper.exportProperties(usersForm, UsersDTO.class);
				 if(usersDto!=null){
					 if(AdminConstants.YHRS9001_01.equals(usersForm.getUserType())&&usersForm.getRefOid()==null) {
						 response.getWriter().write(JSONHelper.fromObject(false, "该人员信息不存在！").toString());
						 return null;
					 }

					 if(usersForm.getRefOid()!=null) {
						UserRelationDTO dto = usersFacade.getUserRelationDTOByRefOidAndRefType(usersForm.getRefOid(),AdminConstants.YHRS0137_02);
						if(dto!=null&&!usersForm.getUserId().equals(dto.getUserId())) {
							response.getWriter().write(JSONHelper.fromObject(false, "该人员信息已创建账号，不能重复创建！").toString());
							return null;
						}
					 }
					 if(StringUtil.isNotNull(usersDto.getDeptId())){
						 String deptName = usersFacade.getUnitOrgName(Long.valueOf(usersDto.getDeptId()));
						 if(StringUtils.isNotEmpty(deptName)){
							 usersDto.setDeptName(deptName);
						 }
					 }
					 usersFacade.updateUsersInfo(usersDto);
					 
					 if(AdminConstants.YHRS9001_01.equals(usersForm.getUserType())
							 &&AdminConstants.YHRS9001_02.equals(usersForm.getUserType())
							 &&AdminConstants.YHRS9001_03.equals(usersForm.getUserType())) {
						 UserRelationDTO userRelationDTO = new UserRelationDTO();
						 userRelationDTO.setUserId(usersForm.getUserId());
						 userRelationDTO.setRefOid(usersForm.getRefOid());
						 if(AdminConstants.YHRS9001_01.equals(usersForm.getUserType())) {
							 userRelationDTO.setRefType(AdminConstants.YHRS0137_02);
						 }
						 usersFacade.createOrUpdateUserRelation(userRelationDTO);
					 }else {
						 usersFacade.deleteUserRelation(usersForm.getUserId(), AdminConstants.YHRS0137_02);
					 }
					 
					 JSONObject json = new JSONObject();
					 json.put("userOid", usersDto.getUserOid());
					 json.put("userId", usersDto.getUserId());
					 response.getWriter().write(JSONHelper.fromObject(true, json.toString()).toString());
				 }
	        } catch (Exception e) {
	            handleException(request, e, e.getMessage());
	            response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "修改用户信息失败")).toString());
	            return null;
	       }
		return null;
		
	}
	/**
	 *跳转到用户添加页面 
	 * */
	public ActionForward goCreateUsersPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			//将来要对单位信息做查询，加载到页面上
		try {
			//List<SubSystemDTO> subSystems=usersFacade.findSubSystemsInfo();
			
			TableTagBean ttb = new TableTagBean();
			ttb.setPageSize(0);
			List<SaoAdminUnitDTO> adminUnits=usersFacade.findUsersUnitList(ttb);
			if(CollectionUtils.isNotEmpty(adminUnits)){
				request.setAttribute("adminUnits", adminUnits.get(0));
			}
		} catch (Exception e) {
			handleException(request, e, null);
		}
		return mapping.findForward("success");
	}
	
	
	public ActionForward changeOrgByUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String unitOid = request.getParameter("unitOid");
		try
		{
			if(StringUtils.isEmpty(unitOid))
			{
				return null;
			}
			//内设机构
			List<SaoAdminOrgDTO> orgList = usersFacade.listUnitOrg(NumberUtils.longValue(unitOid));
			request.setAttribute("orgList", orgList);
			
			JSONObject obj = new JSONObject();
			JSONArray array = new JSONArray();
			JSONObject obj0 = new JSONObject();
			obj0.put("orgOid","");
			obj0.put("orgName", "--请选择--");
			array.element(obj0);
			if(CollectionUtils.isNotEmpty(orgList))
			{
				for(SaoAdminOrgDTO saoAdminOrgDTO : orgList)
				{
					JSONObject obj1 = new JSONObject();
					obj1.put("orgOid",saoAdminOrgDTO.getOrgOid());
					obj1.put("orgName", saoAdminOrgDTO.getOrgName());
					array.element(obj1);
				}

			}
			obj.put("rs", array);
			response.getWriter().print(obj.toString());
		}
		catch(Exception se)
		{
			this.handleException(request, se, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(se.getMessage(), "查询失败")).toString());
		}
		return null;
	}
	
	
	/**
	 * 创建用户
	 * */
	public ActionForward createUsers(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			UsersForm usersForm=(UsersForm) form;
		 try {
			UsersDTO usersDto = BeanHelper.exportProperties(usersForm, UsersDTO.class);
			usersDto.setPassword(CryptoUtil.md5(StringUtils.defaultIfEmpty(ConfigUtil.getProperty("user.defaultPassword"), AdminConstants.DEFAULT_PASSWORD)));
		    
//				String unitName= usersFacade.getUnitNameByUnitID(usersDto.getUnitId());
//				 if(StringUtils.isNotEmpty(unitName)){
//					 usersDto.setUnitName(unitName);
//				 }
			 if(StringUtil.isNotNull(usersDto.getDeptId())){
				 String deptName = usersFacade.getUnitOrgName(usersDto.getDeptId());
				 if(StringUtils.isNotEmpty(deptName)){
					 usersDto.setDeptName(deptName);
				 }
			 }
			usersDto.setDefaultLoginSystem(SystemConstants.SYSTEM_HR);
			if(usersFacade.checkUserByUsersID(usersForm.getUserId())){
				if(AdminConstants.YHRS9001_01.equals(usersForm.getUserType())&&usersForm.getRefOid()==null) {
					 response.getWriter().write(JSONHelper.fromObject(false, "该人员信息不存在！").toString());
					 return null;
				 }
				if(usersForm.getRefOid()!=null) {
					UserRelationDTO dto = usersFacade.getUserRelationDTOByRefOidAndRefType(usersForm.getRefOid(),AdminConstants.YHRS0137_02);
					if(dto!=null&&!usersDto.getUserId().equals(dto.getUserId())) {
						response.getWriter().write(JSONHelper.fromObject(false, "该人员信息已创建账号，不能重复创建！").toString());
						return null;
					}
				}
				usersFacade.createUsersInfo(usersDto);
				
				if(AdminConstants.YHRS9001_01.equals(usersForm.getUserType())
						 &&AdminConstants.YHRS9001_02.equals(usersForm.getUserType())
						 &&AdminConstants.YHRS9001_03.equals(usersForm.getUserType())
						 &&usersForm.getRefOid()!=null) {
					UserRelationDTO userRelationDTO = new UserRelationDTO();
					userRelationDTO.setUserId(usersForm.getUserId());
					userRelationDTO.setRefOid(usersForm.getRefOid());
					userRelationDTO.setRefType(AdminConstants.YHRS0137_02);
					usersFacade.createOrUpdateUserRelation(userRelationDTO);
				}
				
				//跳转用户岗位页面
				UsersDTO newDto=usersFacade.findUsersByUserId(usersDto.getUserId());
				JSONObject json = new JSONObject();
				json.put("userOid", newDto.getUserOid());
				json.put("userId", newDto.getUserId());
				response.getWriter().write(JSONHelper.fromObject(true, json.toString()).toString());
			 }else{
				 response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty( null,"用户ID不能重复！")).toString());
				 return null;
			 }
	        } catch (Exception e) {
	            handleException(request, e, e.getMessage());
	            response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "系统异常，增加用户失败")).toString());
	            return null;
	       }
		return null;
	}
	/**
	 * 用户信息删除
	 * @param userOid
	 * @throws 
	 * */
	public ActionForward deleteUsers(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			String userOid =request.getParameter("userOid");
			if(StringUtils.isNotEmpty(userOid)){
				usersFacade.deleteUsers(userOid.trim(),AdminConstants.YHRS0137_02);
				response.getWriter().write(JSONHelper.fromObject(true, null).toString());
			}else{
				response.getWriter().write(JSONHelper.fromObject(true, "刪除用户失败！").toString());
			}
		
		return null;
	}
	/**
	 * 用户信息密码重置
	 * @param userOid
	 * @throws 
	 * */
	public ActionForward reSetPs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			String userOid =request.getParameter("userOid");
			if(StringUtils.isNotEmpty(userOid)){
				usersFacade.updatePasswordForReset(userOid.trim());
				response.getWriter().write(JSONHelper.fromObject(true, null).toString());
			}else{
				response.getWriter().write(JSONHelper.fromObject(true, "密码重置失败！").toString());
			}
		
		return null;
	}
	/**
	 * 查看页面的用户岗位信息
	 * @param userOid
	 * */
	public ActionForward showUsersPosition(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			String usersId=request.getParameter("userId");
			if(StringUtils.isNotEmpty(usersId)){
				List<UserPositionInfoDTO> list= usersFacade.findUsersPosition(usersId,"");
				request.setAttribute("userPositionInfo", list);
			}
		return mapping.findForward("success");
	}
	
	/**
	 * 修改页面的用户岗位信息
	 * */
	public ActionForward updateUSP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String usersId=request.getParameter("userId");
		if(StringUtils.isNotEmpty(usersId)){
			List<UserPositionInfoDTO> list= usersFacade.findUsersPosition(usersId,"");
			request.setAttribute("userPositionInfo", list);
		}
	return mapping.findForward("success");
}
	
	/**
	 * 跳转至用户岗位查看页面
	 * @param userOid
	 * @param userId
	 * */
	public ActionForward goUsersPositionPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			String userOid =request.getParameter("userOid");
			if(StringUtils.isNotEmpty(userOid)){
			UsersDTO usersDto=usersFacade.getUserByOid(userOid);
			UsersForm usersForm=(UsersForm) form;
			BeanHelper.exportProperties(usersDto, usersForm);
			usersForm.setUserSex(DicHelper.viewName(AdminConstants.YHRS0001, usersForm.getUserSex()));
			usersForm.setUserStatus(DicHelper.viewName(AdminConstants.YHRS9002, usersForm.getUserStatus()));
			usersForm.setUserType(DicHelper.viewName(AdminConstants.YHRS9001, usersForm.getUserType()));
			String usersId=request.getParameter("userId");
			if(StringUtils.isNotEmpty(usersId)){
				List<UserPositionInfoDTO> list= usersFacade.findUsersPosition(usersId,"");
				request.setAttribute("userPositionInfo", list);
			}
			}
			return mapping.findForward("success");
	}
	/**
	 * 跳转到用户岗位修改页面
	 * */
	public ActionForward goUsersPositionUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String systemPositionOid=request.getParameter("systemPositionOid");
		String userId=request.getParameter("userId");
		//岗位名称，岗位描述，权限
		if(StringUtils.isNotEmpty(systemPositionOid)&&StringUtils.isNotEmpty(userId)){
			List<UserPositionInfoDTO> list=usersFacade.findUsersPosition(userId.trim(),systemPositionOid);
			UserPositionInfoDTO userPositionInfo=list.get(0);
			if(userPositionInfo!=null){
				request.setAttribute("userPositionInfo", userPositionInfo);
			}
			
		}
		return mapping.findForward("success");
	}
	
	
	/**
	 * 用户岗位信息修改
	 * */
	public ActionForward updateUsersPosition(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			UserSystemPositionForm uspForm=(UserSystemPositionForm) form;
			 try {
				 UserSystemPositionDTO uspDto = BeanHelper.exportProperties(uspForm, UserSystemPositionDTO.class);
				 usersFacade.updateUsersPosition(uspDto);
		         response.getWriter().write(JSONHelper.fromObject(true, null).toString());
		        } catch (Exception e) {
		            handleException(request, e, e.getMessage());
		            response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "修改用户岗位信息失败")).toString());
		            return null;
		       }
		
		return null;
	}
	/**
	 * 
	 * */
	  
	  public ActionForward usersPositionListPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		  request.setAttribute("userId", request.getParameter("userId")); 
		  request.setAttribute("userOid", request.getParameter("userOid"));
		  return mapping.findForward("success");
	  }
	/**
	 * 跳转到用户岗位添加页面
	 * 
	 * */
	public ActionForward goUsersPositionAddPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			TableTagBean ttb = new TableTagBean(request);
			String userId=request.getParameter("userId");
			request.setAttribute("userId", userId);
			request.setAttribute("userOid", request.getParameter("userOid"));
			if(StringUtils.isNotEmpty(userId)){
			ttb.getCondition().put("userId",userId.trim());
			List<UserPositionInfoDTO> list = usersFacade.findAllUsersPosition(ttb);
			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();
			json.put("total", ttb.getTotal());
			if(CollectionUtils.isNotEmpty(list)){
				JSONObject obj = null;
				for (UserPositionInfoDTO dto : list) {
					obj = JSONHelper.fromObject(dto, DateUtil.DATE_PATTERN_DEFAULT);
					//obj.put("userStatus", DicHelper.viewName(AdminConstants.YHRS9002, dto.getUserStatus()));
					array.element(obj);
				}
			}
			json.put("rows", array);
			response.getWriter().print(json.toString());
			}
		} catch (Exception e) {
			handleException(request, e, "查询岗位信息失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "查询用户基础信息失败")));
		}
		return null;
	}
	/**
	 * 用户岗位添加
	 * 
	 * */
	public ActionForward usersPositionAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			String userOid=request.getParameter("userOid");
			request.setAttribute("userOid",userOid);
			String systemPositionOids=request.getParameter("systemPositionOids");
			String userId =request.getParameter("userId");
			if(systemPositionOids!=null&&!"".equals(systemPositionOids)) {
				usersFacade.addUsersPosition(systemPositionOids,userId);
			}
			//调用存储过程
			try {
				new UserUnitAuthThread(userId, null, null).start();
			} catch (Exception e) {
				handleException(request, e, "用户岗位添加调用存储过程失败");
			}
			response.getWriter().write(JSONHelper.fromObject(true, null).toString());
		} catch (Exception e) {
			handleException(request, e, "用户岗位添加失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "查询用户基础信息失败")));
		}
		return null;
	}
	/**
	 * 用户岗位移除
	 * */
	public ActionForward removeUsersPosition(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
		String  systemPositionOid=request.getParameter("systemPositionOid");
		String  userId=request.getParameter("userId");
		//String userOid= request.getParameter("userOid");
		if(StringUtils.isNotEmpty(systemPositionOid)&&StringUtils.isNotEmpty(userId)){
			usersFacade.deleteUsersPosition(systemPositionOid.trim(),userId.trim());
		}
		//调用存储过程
		try {
			new  UserUnitAuthThread(userId, null, null).start();
		} catch (Exception e) {
			handleException(request, e, "用户岗位添加调用存储过程失败");
		}
		response.getWriter().write(JSONHelper.fromObject(true, null).toString());
	} catch (Exception e) {
		handleException(request, e, "用户岗位移除失败");
		response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "查询用户基础信息失败")));
	}
		return null;
	}
	/**
	 * 用户信息修改工作台
	 * */
	public ActionForward goUpdateUsersWorkTop(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userOid =request.getParameter("userOid");
		if(StringUtils.isNotEmpty(userOid)){
		UsersDTO usersDto=usersFacade.getUserByOid(userOid);
		UsersForm usersForm=(UsersForm) form;
		BeanHelper.exportProperties(usersDto, usersForm);
		usersForm.setUserSex(DicHelper.viewName(AdminConstants.YHRS0001, usersForm.getUserSex()));
		usersForm.setUserStatus(DicHelper.viewName(AdminConstants.YHRS9002, usersForm.getUserStatus()));
		usersForm.setUserType(DicHelper.viewName(AdminConstants.YHRS9001, usersForm.getUserType()));
		String usersId=request.getParameter("userId");
		if(StringUtils.isNotEmpty(usersId)){
			List<UserPositionInfoDTO> list= usersFacade.findUsersPosition(usersId,"");
			request.setAttribute("userPositionInfo", list);
		}
		}
		return mapping.findForward("success");
	}
	/**
	 * 用户密码修改
	 * @param userOid
	 * @param password
	 * @param newPassword
	 * */
	public ActionForward updateUserPassword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String userPassword =request.getParameter("userPassword");
			String newPassword=request.getParameter("newPassword");
			String checkResult=PasswordCheckUtil.checkPassword(newPassword);
			if(checkResult==null){
				UsersDTO usersDTO= usersFacade.findLoginUserByUserId();
				if(usersDTO.getPassword().equals(CryptoUtil.md5(userPassword.trim()))){
					usersDTO.setPassword(CryptoUtil.md5(newPassword.trim()));
					usersFacade.updateUsersInfo(usersDTO);
					OpLogHelper.saveLog("updatePwd", "修改密码");
					response.getWriter().write(JSONHelper.fromObject(true, null).toString());
				}else{
					response.getWriter().write(JSONHelper.fromObject(false, "当前密码错误！").toString());
				}
			}else{
				response.getWriter().write(JSONHelper.fromObject(false, checkResult).toString());
			}
		} catch (Exception e) {
			handleException(request, e, "密码修改失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "密码修改失败！")));
		}
		return null;
	}
	public ActionForward goUsersPasswordUpdatePage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("success");
	}
	
	/**
	 * 检查用户是否为默认密码
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkPsw(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);
			if (session != null) {
				String psw = (String) session.getAttribute("password");
				String defaultPaw = CryptoUtil.md5(CommonFunctions.getDefaultPassword());
		        if (psw != null) {
		        	if(psw.equals(defaultPaw)) {
		        		response.getWriter().write("OK");
		        	}
		        }
		    }
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	/**
	 * 修改用户默认密码
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goUsersDefaultPasswordUpdatePage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("success");
	}
	
	/**
	 * 查询人员基础信息列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listPbPersonInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String name = request.getParameter("name");
			List<SaoAdminPersonDTO> list = usersFacade.listPbPersonInfoByName(name);
			
			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();
			if(CollectionUtils.isNotEmpty(list)){
				JSONObject obj = null;
				for (SaoAdminPersonDTO dto : list) {
					obj = JSONHelper.fromObject(dto, DateUtil.DATE_PATTERN_DEFAULT);
					obj.put("sexCodeDesc", DicHelper.viewName(AdminConstants.YHRS0001, dto.getSexCode()));
					//obj.put("hireDeptName", usersFacade.getUnitOrgName(dto.getHireDeptOid()));
					array.element(obj);
				}
			}
			json.put("rows", array);
			response.getWriter().print(json.toString());
			
		} catch (Exception e) {
			handleException(request, e, "查询人员基础信息失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "查询人员基础信息失败")));
		}
		
		return null;
	}
	
	/**
	 * 获取hireDeptName
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getHireDeptName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String hireDeptOid = request.getParameter("hireDeptOid");
		String hireDeptName = usersFacade.getUnitOrgName(Long.valueOf(hireDeptOid));
		response.getWriter().print(hireDeptName);
		return null;
	}
}	
