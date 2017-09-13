/*
 * @(#) LoginAction.java        1.00         2006-5-24
 * 
 * Copyright (c) 2006 FEDEX EXPRESS Corporation. All Rights Reserved.
 *
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. FEDERAL EXPRESS AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL FEDERAL
 * EXPRESS OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF FEDERAL EXPRESS HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 */

package com.yh.admin.login.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yh.admin.dto.UsersDTO;
import com.yh.admin.login.web.form.LoginForm;
import com.yh.admin.users.facade.UsersFacade;
import com.yh.admin.util.AdminConstants;
import com.yh.admin.util.EncryptUtil;
import com.yh.admin.util.SystemConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.springframework.util.CollectionUtils;

import com.yh.admin.dto.ResourcesDTO;
import com.yh.admin.dto.SubSystemDTO;
import com.yh.admin.dto.UserRelationDTO;
import com.yh.admin.oplog.OpLogHelper;
import com.yh.admin.oplog.dto.OpLogDTO;
import com.yh.admin.subsystem.facade.SubSystemFacade;
import com.yh.platform.core.constant.Constant;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.ConfigUtil;
import com.yh.platform.core.util.CryptoUtil;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;
import com.yh.platform.core.web.action.BaseAction;

/**
 * @description Login actions
 * @author chenkebing
 * @created 2006-5-24
 * @version 1.0
 */

public class LoginAction extends BaseAction
{
	private UsersFacade usersFacade = (UsersFacade) SpringBeanUtil.getBean("usersFacade");

	private SubSystemFacade subSystemFacade = (SubSystemFacade) SpringBeanUtil.getBean("subSystemFacade");

	/**
	 * 用户登录子系统
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws java.lang.Exception
	 */
	public ActionForward login(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
	{
		//取用户名和密码
		String target = "success";
		LoginForm loginForm = (LoginForm) form;
		String userCode = loginForm.getUserCode();
		String passWord = loginForm.getPassword();
		String pw = CryptoUtil.md5(passWord);
		String ipAddr = request.getRemoteAddr();
		//取子系统
//		String systemCode = loginForm.getSystemCode();
		String systemCode = SystemConstants.SYSTEM_HR;
		//取代理用户
		boolean isFirstLogin = true;
		String agentUserCode = (String)request.getParameter("agentUid");
		if(agentUserCode != null)
		{
			isFirstLogin = false;
			systemCode = (String)request.getParameter("systeCode");
			userCode = request.getParameter("userCode");
		}
		else
		{
			agentUserCode = userCode;
		}
		
		try
		{
			//原会话失效，每次登陆均是新的会话，解决跨站点请求编制XSS漏洞
			log.info("login safe req sessionId=====" + request.getSession().getId());
			request.getSession().invalidate();
			HttpSession session = request.getSession(true);//true明确是创建新会话
			log.info("login safe new sessionId=====" + session.getId());
			if(request.getCookies() != null)
			{
				//如果有自带cookie，则是其cookie过期
				for(int i=0; i < request.getCookies().length; i ++)
				{
					log.info("login safe set cookie maxage 0 ：" + request.getCookies()[i].getName());
					request.getCookies()[i].setMaxAge(0);//让cookie过期
				}
			}
			
			//验证系统license的有效性
			//判断是电脑mac地址还是域名制作的license type=1:mac type2:dns
			//如果为Y，则需要验证license，否则不需要验证
			if("Y".equals(ConfigUtil.getProperty("isTrue")))
			{
				boolean isLegal = false;
				EncryptUtil encryptUtil = new EncryptUtil();
				if("2".equals(ConfigUtil.getProperty("system.license.type")))
				{
					isLegal = encryptUtil.isLegalByDNS(request);
				}else
				{
					isLegal = encryptUtil.isLegal();
				}
				if(!isLegal)
				{
					ActionMessages errors = new ActionMessages();
					errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.license.invalid"));
					if (!errors.isEmpty())
					{
						saveErrors(request, errors);
					}
					return mapping.getInputForward();
				}
			}
			//构造全局用户信息
			UserContext uc = new UserContext();
			uc.setUid(userCode);			
			uc.setAgentUid(agentUserCode);
			uc.setAttribute(UserContext.WEB_KEY_SYSTEMID, systemCode);
			UserContext.setInstance(uc);

			// 检查用户密码 及 功能权限
			UsersDTO userDto = null;
			if (ConfigUtil.isSecurityCheckRequired() && isFirstLogin==true)
			{
				log.info("Geting user's resources for system["+systemCode+"].");
				userDto = usersFacade.checkUserPswd(userCode, pw);
				if (userDto == null)
				{
					ActionMessages errors = new ActionMessages();
					errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.login"));
					if (!errors.isEmpty())
					{
						saveErrors(request, errors);
					}

					return mapping.getInputForward();
				}
				// 检查登录用户是否在某子系统中
				if (!usersFacade.checkUserRight(userCode,systemCode))
				{
					throw new ServiceException("user.menu.none", "用户没有权限");
				}
			}
			else
			{
				userDto = usersFacade.findUsers(userCode);
				if (userDto == null)
					throw new ServiceException("user.menu.none", "用户名或密码错误");
			}
			
			if (log.isDebugEnabled())
				log.debug("Geting user's unit authzations.");
			
			//设置资源
			setUcResources(uc, agentUserCode, systemCode,request);
			
			uc.setDisplayName(userDto.getUserName()); // 把用户姓名放入UserContext
			
			if (log.isDebugEnabled())
				log.debug(uc.getResources());
			
			if (log.isDebugEnabled())
				log.debug("用户所在单位:" + userDto.getUnitId());
			uc.setAttribute("ipAddr", ipAddr);
			uc.setAttribute("unit_oid", userDto.getUnitId());
			
			UserRelationDTO userRelationDTO = usersFacade.getUserRelationDTOByUserId(userCode);
			if(userRelationDTO!=null) {
				uc.setAttribute("PERSON_OID", userRelationDTO.getRefOid());
				uc.setAttribute("REF_TYPE", userRelationDTO.getRefType());
			}
			
			uc.setAttribute(AdminConstants.OPERATOR_TYPE, userDto.getUserType());
			session.setAttribute("currentOperator", userDto.getUserName()); // 当前操作人
			session.setAttribute(AdminConstants.OPERATOR_TYPE, userDto.getUserType()); // 操作人类别
			session.setAttribute(UserContext.SESSION_CONTEXT, uc);
			session.setAttribute(AdminConstants.OPERATOR_SYSTEMID, userDto.getDefaultLoginSystem()); // 登录人的所属系统ID
			session.setAttribute(AdminConstants.OPERATOR_UNITID, userDto.getUnitId()); // 登录人所属单位ID
			session.setAttribute(AdminConstants.OPERATOR_UNIT_NAME, userDto.getUnitName() == null ? "未知单位" : userDto.getUnitName());
			session.setAttribute("password", pw);
			session.setAttribute("UID",uc.getUid());
//			session.setAttribute("s_personOid", userDto.getPersonOid());//存入personOid到session中
			session.setAttribute("visitCounts", 0); //访问主页面的次数
			String agentDisplay = "";
			
			if(agentUserCode.equals(userCode)==false)
			{
				UsersDTO agentDto = usersFacade.findUsers(agentUserCode);
				if(agentDto != null)
				{
					agentDisplay = "<代理"+agentDto.getUserName()+"操作>";
				}					
			}
			session.setAttribute("AGENT_NAME",agentDisplay);

//			StarterUnitSAODTO um = starterUnitSAOFacade.getUnitInfo(Long.parseLong(userDto.getUnitId()));
//			if (um != null)
//			{
//				session.setAttribute(Constants.OPERATOR_PARENT_UNITID, um.getAdminUnitOid() == null && um.getAdminUnitOid() != 0L ? um.getUnitOid().toString() : um.getAdminUnitOid().toString()); // 登录人所属单位ID
//				session.setAttribute(Constants.OPERATOR_UNIT_NAME, um.getUnitName() == null ? "未知单位" : um.getUnitName());
//			}
				
			// 查找用户所能登录的子系统
			//findSysgems(userCode, systemId, request);
			if(StringUtils.isNotEmpty(ipAddr))
			    session.setAttribute("ipAddr", ipAddr);
			log.info(userCode + "[" + userDto.getUserName() + "] login success !");
			setLocation(request, response, form);
			if (null != systemCode && !"".equals(systemCode))
			{
				session.setAttribute(UserContext.WEB_KEY_SYSTEMID, systemCode);
				
				SubSystemDTO subSys = subSystemFacade.get(systemCode);
				String subSystemName = subSys.getSubSystemName();
				session.setAttribute("systemName", subSystemName);
				uc.setAttribute("systemName", subSystemName);
				
				target = StringUtils.defaultIfEmpty(subSys.getSubSystemLocation(), "success");
				
				OpLogDTO opLog = OpLogHelper.getLastOpLog(userCode, systemCode, "login");
				if (opLog != null) {
					session.setAttribute("lastIpAddr", opLog.getIpAddress());
					session.setAttribute("lastLoginDate", DateUtil.formatTime(opLog.getLogDate()));
				}
				
				OpLogHelper.saveLog("login", "登录系统");// 记录日志
			}
			form = null;
		}
		catch (Exception se)
		{
			this.handleException(request, se, loginForm);
			target = "fail";
			return (mapping.findForward(target));
		}
		// 登录检查成功后 把当前系统存入Cookie
//		CookiesHelper.setCookiesByCurrentOrg(request, response);
		return mapping.findForward(target);
	}
	
	

	/**
	 * 设置UC的Resources字段
	 * 
	 * @author xuhj
	 * @created Nov 4, 2009
	 * @param uc
	 * @param userCode
	 * @param systemId
	 * @throws ServiceException
	 */
	private void setUcResources(UserContext uc, String userCode, String systemId, HttpServletRequest request) throws ServiceException {
		List<ResourcesDTO> resources;
		if (ConfigUtil.isSecurityCheckRequired()) {
			resources = usersFacade.listUserResources(userCode);
		} else {
			resources = usersFacade.listResources(systemId);
		}
		List<String> resValues = new ArrayList<String>();
		boolean flag = true;
		if (!CollectionUtils.isEmpty(resources)) {
			for (ResourcesDTO rs : (List<ResourcesDTO>) resources) {
				// 如果功能权限包含预警权限码则首先跳转预警页
//				if (rs.getResValue().equalsIgnoreCase(Constant.parmWaningCode)) {
//					request.getSession().setAttribute("parmWaningCode", Constant.YES);
//					flag = false;
//				}
				resValues.add(rs.getResValue());
			}
		}
		// 如果功能权限包含预警权限码则只显示欢迎首页
		if (flag) {
			request.getSession().setAttribute("parmWaningCode", Constant.NO);
		}
		uc.setResources(resValues);
	}



	/**
	 * change location
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward changeLocation(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception
	{
		try
		{
			setLocation(request, response, form);
		}
		catch (Exception ex)
		{
			ActionMessages errors = new ActionMessages();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.login.location"));
			if (!errors.isEmpty()) saveErrors(request, errors);	
		}
		return mapping.findForward(FORWARD_SUCCESS);
	}

	/**
	 * This action looks for lanaguage and country properties on the given form,
	 * constructs an appropriate Locale object, and sets it as the Struts Locale
	 * for this user's session.
	 */
	@SuppressWarnings("unused")
	private void setLocation(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, ActionForm form) throws Exception
	{
		// extract attributes
		HttpSession session = request.getSession(false);
		Locale locale = Locale.getDefault();

		String language = null;
		String country = null;

		//LoginForm loginForm = (LoginForm) form;
		try
		{
			
			locale = Locale.SIMPLIFIED_CHINESE;
		}
		catch (Exception ex)
		{
			locale = Locale.getDefault();
		}

		session.removeAttribute(Globals.LOCALE_KEY);
		session.setAttribute(Globals.LOCALE_KEY, locale);
	}

	/**
	 * logout
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward logout(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
		try {
			OpLogHelper.saveLog("logout", "退出系统");// 记录日志
			// clear session
			request.getSession(false).invalidate();
		} catch (Exception e) {
			//this.handleException(request, e, "退出系统异常.");
		}

		return mapping.findForward(FORWARD_SUCCESS);
	}

	/**
	 * 进入切换系统操作页面
	 * @author   xuhj
	 * @created  Nov 3, 2009
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goChangeSystem(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception
	{
		//获取操作人ID
		/*String userId = request.getParameter("userCode");
		if(userId == null)
		{
			UserContext uc = (UserContext) request.getSession().getAttribute(UserContext.s_SESSION_NAME);
			userId = uc.getUid();
		}
		
		HashMap<String,List<LabelValueBean>> rtn = usersFacade.findAllSystemByUserId(userId);	
		List<String> usersList = new ArrayList<String>();		
		for(Map.Entry<String,List<LabelValueBean>> entry : rtn.entrySet())
		{
			usersList.add(entry.getKey());
			request.setAttribute(entry.getKey(), entry.getValue());
		}
		request.setAttribute("userList", usersList);
		*/
		return mapping.findForward(FORWARD_SUCCESS);
	}
	
	/**
	 * 切换系统
	 * @author   xuhj
	 * @created  Nov 3, 2009
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	public ActionForward changeSystem(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception
	{
		String url = request.getParameter("url");
		url = url.replaceAll("@", "&");
		response.sendRedirect(url);
		return null;
	}

	

	// 查找用户所能登录的子系统
	@SuppressWarnings("unused")
	private void findSysgems(String userCode, String systemId, javax.servlet.http.HttpServletRequest request) throws Exception
	{
		/*List<String> systems = usersFacade.findSystemByUserId(userCode); // 用户所能登录的子系统

		List<SubSystemDescDto> allSystems = subSystemDescFacade.list(); // 所有子系统

		List<LabelValueBean> systemOption = new ArrayList<LabelValueBean>();
		systemOption.add(0, new LabelValueBean("请选择...", ""));
		for (int i = 0; i < systems.size(); i++)
		{
			String system = systems.get(i);
			for (int j = 0; j < allSystems.size(); j++)
			{
				SubSystemDescDto subSystem = allSystems.get(j);
				String subSystemId = subSystem.getSubSystemCode();
				if (systemId.equals(system))
					continue;
				else if (subSystemId.equals(system))
				{
					systemOption.add(new LabelValueBean(subSystem.getSubSystemName(), subSystem.getSubSystemCode()));
				}
			}
		}

		request.getSession().setAttribute("systems", systemOption);*/
	}

	@SuppressWarnings("unused")
	private void recordLog(javax.servlet.http.HttpServletRequest request, String systemId, String operator, String flag) throws NumberFormatException, ServiceException
	{
		/*String logContext = null; // 日志信息

		SubSystemDescDto subSys = subSystemDescFacade.findById(Long.valueOf(systemId));

		if ("logIn".equals(flag))
		{
			logContext = operator + "登录" + subSys.getSubSystemName() + "。";
		}
		else if ("logOut".equals(flag))
		{
			logContext = operator + "登出" + subSys.getSubSystemName() + "。";
		}
		else if ("changeSys".equals(flag))
		{
			logContext = operator + "切换至" + subSys.getSubSystemName() + "。";
		}*/
	}

	public ActionForward loginSecurity(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception
	{
		UserContext uc = (UserContext) request.getSession().getAttribute(UserContext.SESSION_CONTEXT);
		String SystemId = (String) uc.getAttribute(UserContext.WEB_KEY_SYSTEMID);
		log.info(SystemId);
		uc.setAttribute(UserContext.WEB_KEY_SYSTEMID, SystemId);
		UserContext.setInstance(uc);
		return mapping.findForward(FORWARD_SUCCESS);
	}

	/**
	 * 当EXT请求无操作权限时将信息返回
	 * 
	 * @author xuhj
	 * @created Jan 9, 2009
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws java.lang.Exception
	 */
	public ActionForward noAuthority(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.lang.Exception
	{
		response.getWriter().print("'warning':'<font color=red>对不起，您没有该功能的操作权限!</font>'");
		return null;
	}

}
