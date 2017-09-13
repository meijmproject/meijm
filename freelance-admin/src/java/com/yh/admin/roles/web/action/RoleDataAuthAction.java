package com.yh.admin.roles.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.admin.dto.RoleDataAuthDTO;
import com.yh.admin.dto.RolesDTO;
import com.yh.admin.roles.facade.RoleDataAuthFacade;
import com.yh.admin.roles.facade.RolesFacade;
import com.yh.admin.sao.unit.dto.SaoAdminOrgDTO;
import com.yh.admin.util.UserUnitAuthThread;
import com.yh.component.dictionary.bo.DicItem;
import com.yh.component.dictionary.facade.DicTypeFacade;
import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.constant.Constant;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.util.StringUtil;
import com.yh.platform.core.web.UserContext;
import com.yh.platform.core.web.action.BaseAction;

/**
 *@description	
 *
 *@author		zhangqp
 *@created		16/01/14
 *@version		1.0
 */
public class RoleDataAuthAction extends BaseAction {
	private RoleDataAuthFacade	roleDataAuthFacade	= (RoleDataAuthFacade) SpringBeanUtil.getBean("roleDataAuthFacade");
	private RolesFacade			rolesFacade			= (RolesFacade) SpringBeanUtil.getBean("rolesFacade");
	private DicTypeFacade dicTypeFacade = (DicTypeFacade) SpringBeanUtil.getBean("dicTypeFacade");
	
	/**
	 * 查询单位列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listDataAuthOrgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			TableTagBean ttb = new TableTagBean(request);
			
			String[] personTypeCodeArray = StringUtils.split(ttb.getCondition().get("personTypeCode"),",");
			String[] exOrgOidArray = StringUtils.split(ttb.getCondition().get("exOrgOids"),",");
			
			ttb.getCondition().put("personTypeCode",StringUtil.joinWrap(personTypeCodeArray));
			ttb.getCondition().put("exOrgOids",StringUtil.join(exOrgOidArray));//排除的已选中的单位
			int pageNo=Integer.parseInt(ttb.getCondition().get("pageNo"));
			int pageSize=Integer.parseInt(ttb.getCondition().get("pageSize"));
			ttb.setPage((pageNo-1)*pageSize);
			ttb.setPageSize(pageSize);
			List<SaoAdminOrgDTO> list = roleDataAuthFacade.listUnitByCondition(ttb);
			
			JSONObject json = new JSONObject();
			json.put("total", ttb.getTotal());
			json.put("rows", list);
			response.getWriter().print(json.toString());
			
		} catch (Exception e) {
			handleException(request, e, "查询校核机关人员基础信息失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "查询校核机关人员基础信息失败")));
		}
		
		return null;
	}

	/**
	 * 转到 机构树授权（查看）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward goUpdateDataRoleMain(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long roleId = NumberUtils.longValue(request.getParameter("roleId"));

			request.setAttribute("roleId", roleId);

			// 人员类别
			List<RoleDataAuthDTO> personTypeList = roleDataAuthFacade.listPersonType(roleId);

			// 是否具有所有权限
			if (roleDataAuthFacade.hasAllUnitAuth(UserContext.getLoginUserID())) {
				request.setAttribute("allSearchAuth", "Y");//是否具所有单位的权限
			}

			// 右侧单位显示
			List<RoleDataAuthDTO> unitSelectList = roleDataAuthFacade.listRoleUnitCodeAuth(roleId);
			request.setAttribute("orgSelectList", unitSelectList);//已授权的单位
			request.setAttribute("personTypeList", personTypeList);
		} catch (Exception e) {
			this.handleException(request, e, "查找有权限的节点时出错");
			return mapping.findForward(FORWARD_FAIL);
		}

		return mapping.findForward(FORWARD_SUCCESS);
	}

	/**
	 * 机构树授权(查看权限)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward orgAuthorized(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long roleId = NumberUtils.longValue(request.getParameter("roleId"));
			
 			String authAllUnitCode = request.getParameter("authAllUnitCode");//授予全部权限（不选择单位节点，只选择纬度时）
			String[] orgOids = request.getParameter("orgOids").split(",");
			//String[] personTypeCodeArray = request.getParameter("personTypeCode").split(",");
			String[] onlyOwnStrArray = request.getParameter("onlyOwnStrArray").split(",");
			
			// 内设机构节点list
			List<String> orgOidList = new ArrayList<String>();
			// 系统类别节点list
			//List<String> personTypeList = new ArrayList<String>();
			// 是否包含本身单位节点List
			List<String> onlyOwnList = new ArrayList<String>();
 
			for (int i = 0; i < orgOids.length; i++) {
				if (StringUtils.isNotEmpty(orgOids[i]) && !"null".equals(orgOids[i])) {
					orgOidList.add(orgOids[i]);
				}
			}
			/*for (int k = 0; k < personTypeCodeArray.length; k++) {
				if (StringUtils.isNotEmpty(personTypeCodeArray[k]) && !"null".equals(personTypeCodeArray[k])) {
					personTypeList.add(personTypeCodeArray[k]);
				}
			}*/
			for (int u = 0; u < onlyOwnStrArray.length; u++) {
				if (StringUtils.isNotEmpty(onlyOwnStrArray[u]) && !"null".equals(onlyOwnStrArray[u])) {
					onlyOwnList.add(onlyOwnStrArray[u]);
				}
			}
			
			RoleDataAuthDTO dto = new RoleDataAuthDTO();
			
			//授权全部单位权限
			dto.setAuthAllUnitCode(Constant.YES.equals(authAllUnitCode));
			
			dto.setOrgOidList(orgOidList);
			//dto.setPersonTypeList(personTypeList);
			dto.setRoleId(roleId);
			dto.setOnlyOwnList(onlyOwnList);
			
			roleDataAuthFacade.createRoleNodeAuthList(dto);
			
			request.setAttribute("roleId", roleId);
			
			if (NumberUtils.isNotNullOrZero(roleId)) {
				// 新建线程更新某角色下所有人员单位数据权限对应关系
				new UserUnitAuthThread(null, roleId, null).start();
			}
			
			response.getWriter().print(JSONHelper.fromObject(true, "授权成功"));
		} catch (Exception e) {
			logger.error("功能角色功能授权失败", e);
			this.handleException(request, e, "权限树授权失败");
			response.getWriter().print(JSONHelper.fromObject(false, "授权失败！"));
		}
		return null;
	}
	//查询字典
	public ActionForward onLoadDicItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String dicTypeCode=request.getParameter("dicTypeCode");
			String parentCode=request.getParameter("parentCode");
			List<DicItem> list=dicTypeFacade.listSubItem(dicTypeCode,parentCode);
			JSONObject json=new JSONObject();
			json.put("list", list);
			response.getWriter().write(json.toString());
		} catch (Exception e) {
			this.handleException(request, e, "查找字典时出错");
		}
		return null;
	}
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
}