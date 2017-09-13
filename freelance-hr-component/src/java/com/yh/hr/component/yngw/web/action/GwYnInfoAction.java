package com.yh.hr.component.yngw.web.action;

import com.yh.hr.component.yngw.dto.GwYnInfoDTO;
import com.yh.hr.component.yngw.util.GwYnInfoConstants;
import jade.workflow.utils.ObjectUtil;

import java.util.ArrayList;
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

import com.yh.component.dictionary.bo.DicItem;
import com.yh.component.dictionary.facade.DicItemFacade;
import com.yh.component.dictionary.facade.DicTypeFacade;
import com.yh.component.dictionary.utils.DicHelper;
import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.yngw.facade.GwYnInfoFacade;
import com.yh.hr.component.yngw.web.form.GwYnInfoForm;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.util.StringUtil;
import com.yh.platform.core.web.action.BaseAction;

public class GwYnInfoAction extends BaseAction{
	
	private GwYnInfoFacade gwYnInfoFacade = (GwYnInfoFacade) SpringBeanUtil.getBean("gwYnInfoFacade");
	private DicItemFacade dicItemFacade = (DicItemFacade) SpringBeanUtil.getBean("dicItemFacade");
	@SuppressWarnings("unused")
	private DicTypeFacade dicTypeFacade = (DicTypeFacade) SpringBeanUtil.getBean("dicTypeFacade");
	
	/**
	 * 跳转到院内岗位管理工作台
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goYngwSetList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		return mapping.findForward("success");
	}
	
	/**
	 * 查询院内岗位信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listGwYnInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			TableTagBean ttb = new TableTagBean(request);
			
			List<GwYnInfoDTO> list = gwYnInfoFacade.find(ttb);
			
			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();
			json.put("total", ttb.getTotal());
			if(CollectionUtils.isNotEmpty(list)){
				JSONObject obj = null;
				for (GwYnInfoDTO dto : list) {
					dto.setHisWorkType(DicHelper.viewName(GwYnInfoConstants.YHRS0112, dto.getHisWorkType()));//工作类别
					dto.setHisPositionType(DicHelper.viewName(GwYnInfoConstants.YHRS0113, dto.getHisPositionType()));//岗位类别
					obj = JSONHelper.fromObject(dto, DateUtil.DATE_PATTERN_DEFAULT);
					array.element(obj);
				}
			}
			json.put("rows", array);
			response.getWriter().print(json.toString());
			
		} catch (Exception e) {
			handleException(request, e, "查询院内岗位管理列表失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "查询院内岗位管理列表失败")));
		}
		return null;
	}
	
	/**
	 * 跳转到院内岗位信息添加页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goToCreateGwYnInfoPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return mapping.findForward("success");
	}
	
	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		GwYnInfoForm gwYnInfoForm = (GwYnInfoForm) form;
		try {
			GwYnInfoDTO dto = BeanHelper.copyProperties(gwYnInfoForm, GwYnInfoDTO.class);
			gwYnInfoFacade.create(dto);
			response.getWriter().write(JSONHelper.fromObject(true, null).toString());
		} catch (Exception se) {
			this.handleException(request, se, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(se.getMessage(), "新增失败")).toString());
		}

		return null;
	}
	
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(StringUtil.isBlank(request.getParameter("positionOids")))
		{
			throw new ServiceException(null, "positionOids is null");
		}
		String[] positionOids=request.getParameter("positionOids").split(",");
		try{
			if(null != positionOids && positionOids.length>0)
			{
	            for (String positionOid : positionOids) {
	            	gwYnInfoFacade.delete(ObjectUtil.getValue(positionOid, java.lang.Long.class));
	            }
			}
            response.getWriter().write(JSONHelper.fromObject(true, null).toString());
		}catch (Exception e) {
			this.handleException(request,e, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "删除失败")).toString());
		}
		return null;
	}
	
	/**
	 * 获取下拉框选项
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getSelectItems(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取工作类别
		String hisWorkType = request.getParameter("hisWorkType");
		
		//获取岗位类别
		String hisPositionType = request.getParameter("hisPositionType");
		
		JSONArray array = new JSONArray();
		JSONArray array2 = new JSONArray();
		JSONObject json = new JSONObject();
		
		if(!"".equals(hisWorkType)&&hisWorkType!=null){
			//根据工作类别获取岗位类别的选项
			String[] dicItemCodes = GwYnInfoConstants.getHisWorkTypeMap().get(hisWorkType).split(",");
			
			List<DicItem> selectItems = new ArrayList<DicItem>();
			for(String dicItemCode: dicItemCodes){
				DicItem di = dicItemFacade.viewDicItemNameByDicTypeCodeandDicItemCode(GwYnInfoConstants.YHRS0113,dicItemCode);
				selectItems.add(di);
			}
			if(CollectionUtils.isNotEmpty(selectItems)){
				for (DicItem dicItem : selectItems) {
					array.element(JSONHelper.fromObject(dicItem)); 
				}
			}
			json.put("hisPositionTypeItems", array);
			
			//同时加载岗位名称(大类)选项
			//获取当前岗位类别的第一个选项
			List<GwYnInfoDTO> positionNameList = gwYnInfoFacade.findPositionInfoByPositionType(selectItems.get(0).getDicItemCode());
			
			if(CollectionUtils.isNotEmpty(positionNameList)){
				for (GwYnInfoDTO gwYnInfoDTO : positionNameList) {
					array2.element(JSONHelper.fromObject(gwYnInfoDTO)); 
				}
				
			}
			json.put("parentPositionName", array2);
		}

		if(!"".equals(hisPositionType)&&hisPositionType!=null){
			//根据取岗位类别获取上级岗位名称的选项
			List<GwYnInfoDTO> positionNameList = gwYnInfoFacade.findPositionInfoByPositionType(hisPositionType);
			
			if(CollectionUtils.isNotEmpty(positionNameList)){
				for (GwYnInfoDTO gwYnInfoDTO : positionNameList) {
					array2.element(JSONHelper.fromObject(gwYnInfoDTO)); 
				}
				
			}
			json.put("parentPositionName", array2);
		}
		
//		System.out.println(json.toString());
		response.getWriter().print(json.toString());
		return null;
		
	}
	
	/**
	 * 刷新选项,恢复初始选项
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward resetSelectItems(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		JSONArray array = new JSONArray();
		@SuppressWarnings("unused")
		JSONArray array2 = new JSONArray();
		JSONObject json = new JSONObject();
		//获取工作类别
		String hisWorkType = request.getParameter("hisWorkType");
		
		if(!"".equals(hisWorkType)&&hisWorkType!=null){
			//根据工作类别获取岗位类别的选项
			String[] dicItemCodes = GwYnInfoConstants.getHisWorkTypeMap().get(hisWorkType).split(",");
			
			List<DicItem> selectItems = new ArrayList<DicItem>();
			for(String dicItemCode: dicItemCodes){
				DicItem di = dicItemFacade.viewDicItemNameByDicTypeCodeandDicItemCode(GwYnInfoConstants.YHRS0113,dicItemCode);
				selectItems.add(di);
			}
			if(CollectionUtils.isNotEmpty(selectItems)){
				for (DicItem dicItem : selectItems) {
					array.element(JSONHelper.fromObject(dicItem)); 
				}
			}
			json.put("hisPositionTypeItems", array);
		}
		response.getWriter().print(json.toString());
		return null;
	}
	
	/**
     * 唯一验证
     * @param pbEducationLevelDegreeForm
     * @throws ServiceException
     * @throws Exception 
     */
	public ActionForward uniqueCheck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取人员id,学历证书号,学位证书号
		String positionNameDl = request.getParameter("positionNameDl");
		if(!StringUtil.isBlank(positionNameDl)){
			positionNameDl = positionNameDl.trim();
		}
		
		String flag = "true";
		
		//根据人员id,获取该人员所有的学历学位信息
		/*List<GwYnInfoDTO> list = gwYnInfoFacade.listAllGwYnInfo();

		for(GwYnInfoDTO dto: list){
			//比对当前信息中,是否以创建该大类
			if(dto.getPositionNameXl()==null){
				if(dto.getPositionNameDl().equals(positionNameDl)){
					flag="false";
				}
			}
		}*/
		response.getWriter().write(flag);
		return null;
    }
	
	
	public ActionForward goGwYnInfoUpdatePage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(this.isCancelled(request)) {
			return mapping.findForward("cancel");
		}

        String positionOid = request.getParameter("positionOid");

		try {
            if (StringUtils.isBlank(positionOid)) {
				throw new ServiceException("error.pk.invalid", "positionOid positionOid is null");
			}
            GwYnInfoDTO dto = gwYnInfoFacade.get(ObjectUtil.getValue(positionOid, java.lang.Long.class));
			if (null == dto) {
				throw new ServiceException("error.object.notfound", "GwYnInfo not found by positionOid");
			}
			GwYnInfoForm gwYnInfoForm = BeanHelper.copyProperties(dto, GwYnInfoForm.class);
			
			request.setAttribute("gwYnInfoForm", gwYnInfoForm);
            request.setAttribute("positionOid", positionOid);
		} catch (Exception se) {
			this.handleException(request, se, "positionOid=" + positionOid);
			return mapping.getInputForward();
		}
		
		return mapping.findForward("success");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(this.isCancelled(request)) {
			return mapping.findForward("cancel");
		}
        String positionOid = request.getParameter("positionOid");
        GwYnInfoForm gwYnInfoForm = (GwYnInfoForm) form;
        
		try {
            if (StringUtils.isBlank(positionOid)) {
				throw new ServiceException("error.pk.invalid", "pbQualificationsInfo qualificationsOid is null");
			}

            GwYnInfoDTO dto = gwYnInfoFacade.get(NumberUtils.longValue(positionOid));
			if(null == dto)
			{
				throw new ServiceException(null, "查询不到相关信息");
			}
			
			BeanHelper.copyProperties(gwYnInfoForm, dto);
			gwYnInfoFacade.update(dto);
			response.getWriter().write(JSONHelper.fromObject(true, null).toString());
		} catch (Exception se) {
			this.handleException(request, se, gwYnInfoForm);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(se.getMessage(), "修改失败")).toString());
		}

		return null;
	}
	
	public ActionForward show(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String positionOid = request.getParameter("positionOid");
        if (StringUtil.isBlank(positionOid)) {
        	positionOid = ObjectUtil.getValue(request.getAttribute("positionOid"), String.class);
        }
		try {
			GwYnInfoDTO gwYnInfoDTO = gwYnInfoFacade.get(ObjectUtil.getValue(positionOid, java.lang.Long.class));
			if (null == gwYnInfoDTO) {
				throw new ServiceException(null, "查询不到相关信息");
			}
			GwYnInfoForm gwYnInfoForm = BeanHelper.copyProperties(gwYnInfoDTO, GwYnInfoForm.class);
			
			request.setAttribute("gwYnInfoForm", gwYnInfoForm);
            request.setAttribute("positionOid", positionOid);
		} catch (Exception se) {
			this.handleException(request, se, "positionOid=" + positionOid);
			return mapping.getInputForward();
		}
		return mapping.findForward("success");
	}
	
	
	/**
	 * 查询岗位名称信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws java.lang.Exception
	 */
	public ActionForward listPositionName(ActionMapping mapping, ActionForm form,javax.servlet.http.HttpServletRequest request,javax.servlet.http.HttpServletResponse response)throws java.lang.Exception {
		try {
			TableTagBean ttb = new TableTagBean(request);
			ttb.setPageSize(0);
			
			List<GwYnInfoDTO> list = gwYnInfoFacade.listPositionName(ttb);
			
			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();
			if(CollectionUtils.isNotEmpty(list)){
				JSONObject obj = null;
				for (GwYnInfoDTO dto : list) {
					obj = JSONHelper.fromObject(dto, DateUtil.DATE_PATTERN_DEFAULT);
					obj.put("hisPositionName", dto.getPositionName());
					obj.put("hisPositionOid", dto.getPositionOid());
					obj.put("hisPositionType", DicHelper.viewName("YHRS0113", dto.getHisPositionType()));
					obj.put("hisWorkType", DicHelper.viewName("YHRS0112", dto.getHisWorkType()));
					obj.put("hisPositionTypeCode", dto.getHisPositionType());
					obj.put("hisWorkTypeCode", dto.getHisWorkType());
					
					if(StringUtils.isNotEmpty(dto.getHaveBranchPositionName())&&dto.getHaveBranchPositionName().equals("true")){
						//是大类且含有子类
						obj.put("haveNext", "true");
					}else{
						//其他情况 不需要能点击查询
						obj.put("haveNext", "false");
					}
					array.element(obj);
				}
			}
			json.put("rows", array);
			response.getWriter().print(json.toString());
			
		} catch (Exception ex) {
			handleException(request, ex, null);
			response.getWriter().write("查询岗位名称信息失败");
			return null;
		}
		return null;
	}
	
	
}
