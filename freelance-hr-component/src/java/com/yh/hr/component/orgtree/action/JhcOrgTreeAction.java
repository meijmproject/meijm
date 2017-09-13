package com.yh.hr.component.orgtree.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.component.orgtree.facade.JhcOrgTreeFacade;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.unit.dto.UtOrgDTO;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.util.TreeNode;
import com.yh.platform.core.web.action.BaseAction;
/**
 * 内设机构树组件ACTION
 * @author liuhw
 * 2016-10-28
 */
public class JhcOrgTreeAction extends BaseAction{
	private JhcOrgTreeFacade jhcOrgTreeFacade = (JhcOrgTreeFacade) SpringBeanUtil.getBean("jhcOrgTreeFacade");
	
	/**
	 * 跳转到选择单位页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws java.lang.Exception
	 */
	public ActionForward selectOrg(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		return mapping.findForward("success");
	}
	
	/**
	 * 查询内设机构信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws java.lang.Exception
	 */
	public ActionForward listOrg(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		
		try {
			TableTagBean ttb = new TableTagBean(request);
			List<UtOrgDTO> orgList = jhcOrgTreeFacade.listOrg(ttb);
			JSONObject json = new JSONObject();
			if (CollectionUtils.isNotEmpty(orgList)) {
				json.put("orgList", orgList);
			}
			response.getWriter().print(json.toString());
			
		} catch (Exception ex) {
			handleException(request, ex, null);
			response.getWriter().write("查询内设机构信息失败");
			return null;
		}
		return null;
	}
	
	/**
	 * 查询下级节点
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findOrg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("----------进入JhcOrgTreeAction的findOrg方法-----------");
		try {
			String orgOid = request.getParameter("orgOid");
			/*String dicTypeCode = request.getParameter("dicTypeCode");
			DicType dicType = dicTypeFacade.getDicTypeByCode(dicTypeCode);
			Long dicTypeOid=dicType.getDicTypeOid();*/
			if (StringUtils.isEmpty(orgOid)) {
				throw new ServiceException("", "orgOid不能为空！");
			}
			log.info("orgOid:" + orgOid);
			UtOrgDTO orgDto = jhcOrgTreeFacade.findOrgByOid(Long.valueOf(orgOid));
			if (null != orgDto) {
				/*if(dicItem.getParentCode()!=null&&!("").equals(dicItem.getParentCode())){
				}*/
				request.setAttribute("orgName", orgDto.getOrgName());
				JSONObject json = new JSONObject();
				json.put("orgName", orgDto.getOrgName());
				response.getWriter().print(json.toString());
			}
			return null;
		} catch (ServiceException e) {
			handleException(request, e, "show tree faild!");
			return mapping.findForward(FORWARD_FAIL);
		}
	}
	
	/**
	 * 展示父子关系（过滤）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getChildOrg(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String unitOid = request.getParameter("unitOid");
			String orgOidStr = request.getParameter("orgOid");
			String powerControl = request.getParameter("powerControl");
			log.info("unitOid:" + unitOid);
			log.info("orgOid:" + orgOidStr);
			List<UtOrgDTO> orgDtos = jhcOrgTreeFacade.findOrgInfoByCon(unitOid,orgOidStr,powerControl);
			JSONObject json = new JSONObject();
			if(CollectionUtils.isEmpty(orgDtos))
			{
				orgDtos = new ArrayList<UtOrgDTO>();
			}
			json.put("json", orgDtos);
			response.getWriter().print(json.toString());
		} catch (Exception e) {
			handleException(request, e, "show tree faild!");
			
		}
		return null;
	}
	
	/**
	 * 查询上级节点
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findOrgInfoUp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String orgOid = request.getParameter("orgOid");
			String unitOid = request.getParameter("unitOid");
			UtOrgDTO orgDto = jhcOrgTreeFacade.findOrgInfoUp(unitOid,orgOid);
			JSONObject json = new JSONObject();
			if (null != orgDto) {
				json.put("orgDto", orgDto);
			}
			response.getWriter().print(json.toString());
			return null;
		} catch (ServiceException e) {
			handleException(request, e, "show tree faild!");
			return mapping.findForward(FORWARD_FAIL);
		}
	}
	/**
	 * 查找内设机构树
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findOrgList(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		try {
			String controlDataAuthority = request.getParameter("controlDataAuthority");
			List<TreeNode<UtOrgDTO>> list=jhcOrgTreeFacade.findOrgList(controlDataAuthority);
			response.getWriter().print(toJSONArray(list).toString());
		} catch (Exception se) {
			handleException(request, se, null);
			return mapping.findForward("fail");
		}
		return null;
	}
	/**
	 * 跳到机构树页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goViewUnitInformation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			return mapping.findForward("success");
		} catch (Exception e) {
			handleException(request, e, "跳转到业务办理页面失败");
			return mapping.findForward("error");
		}
	}
	private JSONArray toJSONArray(List<TreeNode<UtOrgDTO>> nodes) {
		JSONArray ary = new JSONArray();
		
		if (CollectionUtils.isNotEmpty(nodes)) {
			for (TreeNode<UtOrgDTO> node : nodes) {
				ary.element(toJSON(node));
			}
		}
		return ary;
	}
	private JSONObject toJSON(TreeNode<UtOrgDTO> node) {
		JSONObject json = JSONHelper.fromObject(node.getEntry());
		json.putAll(node.getAttributes());
		json.put("children", toJSONArray(node.getChildren()));
		json.put("leaf",node.isLeaf());
		
		return json;
	}
}
