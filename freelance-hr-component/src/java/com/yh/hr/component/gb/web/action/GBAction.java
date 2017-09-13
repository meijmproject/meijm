package com.yh.hr.component.gb.web.action;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.component.gb.web.form.GbDescriptionForm;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.gb.dto.GBPlanDTO;
import com.yh.hr.component.gb.dto.GbDescriptionDTO;
import com.yh.hr.component.gb.facade.GBPlanFacade;
import com.yh.hr.component.gb.facade.GbDescriptionFacade;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

public class GBAction extends BaseAction{
	GbDescriptionFacade gbDescriptionFacade = (GbDescriptionFacade) SpringBeanUtil.getBean("gbDescriptionFacade");

	
	private GBPlanFacade gBPlanFacade = (GBPlanFacade) SpringBeanUtil.getBean("gBPlanFacade");
	/**
	 * 跳转到事业单位岗位管理工作台
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws java.lang.Exception
	 */
	public ActionForward goToSygwManageWorkBench(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		return mapping.findForward("success");
	}
	
	/**
	 * 跳转到岗位下达工作台
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws java.lang.Exception
	 */
	public ActionForward goToGBPlanWorkbeanch(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TableTagBean ttb = new TableTagBean(request);
		ttb.setPageSize(-1);
		List<GBPlanDTO> list = gBPlanFacade.listInfoCondition(ttb);
		if(list!=null&&list.size()>0) {
			request.setAttribute("list", JSONArray.fromObject(list).toString());
			return mapping.findForward("success-update");
		}else {
			return mapping.findForward("success-create");
		}
	}
	
	/**
	 * 新建岗位下达
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws java.lang.Exception
	 */
	public ActionForward createGBPlan(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		try {
			List<GBPlanDTO> list = new ArrayList<GBPlanDTO>();
			@SuppressWarnings("rawtypes")
			Enumeration pNames = request.getParameterNames();
			while(pNames.hasMoreElements()){
			    String name = (String)pNames.nextElement();
			    String[] parseName = name.split("_");
			    if(parseName.length==4) {
				    String value = request.getParameter(name);
				    GBPlanDTO dto = new GBPlanDTO();
				    dto.setUnitOid(4810L);
				    dto.setPostType(parseName[1]);
				    dto.setPostLevel(parseName[3]);
				    if(value!=null&&!value.equals("")) {
				    	dto.setPostLevelCount(Integer.parseInt(value));
				    }
				    list.add(dto);
			    }
			}
			gBPlanFacade.createGBPlans(list);
			response.getWriter().write(JSONHelper.fromObject(true, null).toString());
		}catch(Exception e) {
			this.handleException(request, e, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "保存失败")).toString());
		}
		return null;
	}
	
	/**
	 * 跳转到修改岗位下达
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws java.lang.Exception
	 */
	public ActionForward goToUpdatePBPlan(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		TableTagBean ttb = new TableTagBean(request);
		ttb.setPageSize(-1);
		List<GBPlanDTO> list = gBPlanFacade.listInfoCondition(ttb);
		request.setAttribute("list", JSONArray.fromObject(list).toString());
		return mapping.findForward("success");
	}
	/**
	 * 跳转到岗说明书工作台
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws java.lang.Exception
	 */
	public ActionForward goToGbDescriptionWorkBench(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		return mapping.findForward("success");
	}
	/**
	 * 查询岗位说明书信息列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listGbDescription(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			TableTagBean ttb = new TableTagBean(request);
			List<JSONObject> list = gbDescriptionFacade.listGbDescription(ttb);

			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();
			json.put("total", ttb.getTotal());
			if (CollectionUtils.isNotEmpty(list)) {
				for (JSONObject obj : list) {
					array.element(obj);
				}
			}
			json.put("rows", array);
			response.getWriter().print(json.toString());

		} catch (Exception e) {
			handleException(request, e, "查询岗位说明书信息列表失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "查询岗位说明书信息列表失败")));
		}

		return null;
	}
	/**
	 * 跳转到岗位说明书新增页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goToAddGbDescription(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			return mapping.findForward("success");
	}
	/**
	 * 添加 岗位说明书信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addGbDescription(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GbDescriptionForm gbDescriptionForm = (GbDescriptionForm) form;
		try {
			
			GbDescriptionDTO gbDescriptionDTO = BeanHelper.copyProperties(gbDescriptionForm, GbDescriptionDTO.class);
			gbDescriptionFacade.createGbDescription(gbDescriptionDTO);
			response.getWriter().write(JSONHelper.fromObject(true, null).toString());
		} catch (Exception se) {
			this.handleException(request, se, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(se.getMessage(), "新增失败")).toString());
		}

		return null;
	}
	/**
	 * 跳转到岗位说明书信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goToUpdateGbDescription(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long jhgGbDescriptionOid = Long.valueOf(request.getParameter("jhgGbDescriptionOid"));
		try {
			GbDescriptionDTO gbDescriptionDTO = gbDescriptionFacade.findGbDescriptionById(jhgGbDescriptionOid);
			GbDescriptionForm gbDescriptionForm = BeanHelper.copyProperties(gbDescriptionDTO, GbDescriptionForm.class);
			request.setAttribute("gbDescriptionForm", gbDescriptionForm);
			
		} catch (Exception se) {
			this.handleException(request, se, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(se.getMessage(), "新增失败")).toString());
		}
		return mapping.findForward(FORWARD_SUCCESS);
	}
	/**
	 * 修改岗位说明书信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateGbDescription(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GbDescriptionForm gbDescriptionForm = (GbDescriptionForm) form;
		try {
			
			GbDescriptionDTO gbDescriptionDTO = BeanHelper.copyProperties(gbDescriptionForm, GbDescriptionDTO.class);
			gbDescriptionFacade.updateGbDescription(gbDescriptionDTO);
			response.getWriter().write(JSONHelper.fromObject(true, null).toString());
		} catch (Exception se) {
			this.handleException(request, se, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(se.getMessage(), "修改失败")).toString());
		}

		return null;
	}
	/**
	 * 删除岗位说明书信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteGbDescription(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String jhgGbDescriptionOids = request.getParameter("jhgGbDescriptionOids");
		String[] jhgGbDescriptionOid = jhgGbDescriptionOids.split(",");
		try {
			for(String str : jhgGbDescriptionOid){
				gbDescriptionFacade.deleteGbDescription(Long.valueOf(str));
			}
			response.getWriter().write(JSONHelper.fromObject(true, null).toString());
		} catch (Exception se) {
			this.handleException(request, se, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(se.getMessage(), "修改失败")).toString());
		}
		return null;
	}
	
	/**
	 * 修改岗位下达
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws java.lang.Exception
	 */
	public ActionForward updateGBPlan(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		try {
			List<GBPlanDTO> list = new ArrayList<GBPlanDTO>();
			@SuppressWarnings("rawtypes")
			Enumeration pNames = request.getParameterNames();
			while(pNames.hasMoreElements()){
			    String name = (String)pNames.nextElement();
			    String[] parseName = name.split("_");
			    if(parseName.length==4) {
				    String value = request.getParameter(name);
				    GBPlanDTO dto = new GBPlanDTO();
				    dto.setUnitOid(4810L);
				    dto.setPostType(parseName[1]);
				    dto.setPostLevel(parseName[3]);
				    if(value!=null&&!value.equals("")) {
				    	dto.setPostLevelCount(Integer.parseInt(value));
				    }
				    list.add(dto);
			    }
			}
			gBPlanFacade.updateGBPlans(list);
			response.getWriter().write(JSONHelper.fromObject(true, null).toString());
		}catch(Exception e) {
			this.handleException(request, e, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "保存失败")).toString());
		}
		return null;
	}
	
	/**
	 * 跳转到岗位使用情况表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws java.lang.Exception
	 */
	public ActionForward goToGBPlan(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		try {
			TableTagBean ttb = new TableTagBean(request);
			ttb.setPageSize(-1);
			List<GBPlanDTO> list = gBPlanFacade.listInfoCondition(ttb);
			List<JSONObject> listGBPlanWorking = gBPlanFacade.listGBPlanWorking();
			int listGBPlanTwoWorking = gBPlanFacade.listGBPlanTwoWorking();
			if(!StringUtils.isBlank(request.getParameter("flag"))&&request.getParameter("flag").equals("export")) {
				int all = 0;
				JSONObject obj = new JSONObject();
				for(GBPlanDTO gBPlanDTO :list){
					if(gBPlanDTO.getPostLevelCount()==null)
					{
						gBPlanDTO.setPostLevelCount(0);
					}
					obj.put(gBPlanDTO.getPostLevel(),gBPlanDTO.getPostLevelCount());
					all+=gBPlanDTO.getPostLevelCount();
				}
				JSONObject listGBPlanWorkings = gBPlanFacade.listGBPlanWorkings();
				int gBPlanWorkings = 0;
				int guanliGBPlanWorkings = 0;
				int zhuanyeGBPlanWorkings = 0;
				int gongqinGBPlanWorkings = 0;
				 @SuppressWarnings("rawtypes")
				Iterator iterator = listGBPlanWorkings.keys();
				 while(iterator.hasNext()){
			           String  key = (String) iterator.next();
			           String value = listGBPlanWorkings.getString(key);
			           if("1".equals(key.substring(3,4))){
			        	   guanliGBPlanWorkings+=Integer.valueOf(value);
			           }else if("2".equals(key.substring(3,4))){
			        	   zhuanyeGBPlanWorkings+=Integer.valueOf(value);
			           }else if("3".equals(key.substring(3,4))){
			        	   gongqinGBPlanWorkings+=Integer.valueOf(value);
			           }
				 }
				 gBPlanWorkings = guanliGBPlanWorkings+ zhuanyeGBPlanWorkings+ gongqinGBPlanWorkings;
				 request.setAttribute("gBPlanWorkings",gBPlanWorkings);
				 request.setAttribute("guanliGBPlanWorkings",guanliGBPlanWorkings);
				 request.setAttribute("zhuanyeGBPlanWorkings",zhuanyeGBPlanWorkings);
				 request.setAttribute("gongqinGBPlanWorkings",gongqinGBPlanWorkings);
				 request.setAttribute("all",all);
				request.setAttribute("obj",obj);
				request.setAttribute("listGBPlanWorkings", listGBPlanWorkings);
				request.setAttribute("listGBPlanTwoWorking", listGBPlanTwoWorking);
			}else{
				request.setAttribute("list", JSONArray.fromObject(list).toString());
				request.setAttribute("listGBPlanWorking", listGBPlanWorking);
				request.setAttribute("listGBPlanTwoWorking", listGBPlanTwoWorking);
			}
			
		}catch(Exception e) {
			this.handleException(request, e, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "查询失败")).toString());
		}
		//导出标志
		if(!StringUtils.isBlank(request.getParameter("flag"))&&request.getParameter("flag").equals("export")) {
			return mapping.findForward("export");
		}else{
			return mapping.findForward("success");
		}
	}
}
