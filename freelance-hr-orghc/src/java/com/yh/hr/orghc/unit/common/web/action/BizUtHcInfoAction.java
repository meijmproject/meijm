package com.yh.hr.orghc.unit.common.web.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.orghc.unit.common.dto.BizHcDtoComparator;
import com.yh.hr.orghc.unit.common.facade.BizUtHcInfoFacade;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.hr.orghc.unit.common.dto.BizUtHcInfoDTO;
import com.yh.hr.orghc.unit.common.web.form.BizUtHcInfoForm;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

public class BizUtHcInfoAction extends BaseAction {
	
	private BizUtHcInfoFacade bizUtHcInfoFacade = (BizUtHcInfoFacade) SpringBeanUtil.getBean("bizUtHcInfoFacade");
	
	
	/**
	 * 根据单位id查询编制列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listBizUtHcInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		@SuppressWarnings("unused")
		BizUtHcInfoForm bizUbHcInfoForm = (BizUtHcInfoForm)form;
		String utUnitOid = request.getParameter("utUnitOid");
		request.setAttribute("utUnitOid", utUnitOid);
		List<BizUtHcInfoDTO> verUbHcInfoDTOs = bizUtHcInfoFacade.listByUnitOid(NumberUtils.longValue(utUnitOid));
		if(CollectionUtils.isNotEmpty(verUbHcInfoDTOs)){			
			request.setAttribute("hcList", verUbHcInfoDTOs);
			//bizUbHcInfoForm.setBizUtHcInfoDTOs(verUbHcInfoDTOs);
		}
		return mapping.findForward("success");
	}
	
	public ActionForward addBizUtHcInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			String utUnitOid = request.getParameter("utUnitOid");
			String verUbHcInfo = request.getParameter("verUbHcInfo");
			JSONObject json=JSONObject.fromObject(verUbHcInfo);
			BizUtHcInfoDTO verUbHcInfoDTO = (BizUtHcInfoDTO)JSONObject.toBean(json,BizUtHcInfoDTO.class);
			verUbHcInfoDTO.setUtUnitOid(NumberUtils.longValue(utUnitOid));
//			verUbHcInfoDTO.setPreCount(verUbHcInfoDTO.getCurCount());
//			verUbHcInfoDTO.setChgCount((long)0);
//			verUbHcInfoDTO.setCurCount((long)0);
			bizUtHcInfoFacade.create(verUbHcInfoDTO);
	        JSONObject obj = showBizUtHcInfoList(NumberUtils.longValue(utUnitOid));
	        response.getWriter().print(obj.toString());
		}
        catch (Exception e) {
			handleException(request, e, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "添加失败")).toString());

		}
		return null;
	}
	
	public ActionForward deleteBizUtHcInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String hcOid = request.getParameter("hcOid");
		try{
			if(StringUtils.isEmpty(hcOid))
			{
				throw new ServiceException(null, "hcOid is null");
			}
			bizUtHcInfoFacade.delete(NumberUtils.longValue(hcOid));
			String utUnitOid = request.getParameter("utUnitOid");
			JSONObject obj = showBizUtHcInfoList(NumberUtils.longValue(utUnitOid));
	        response.getWriter().print(obj.toString());
		}
        catch (Exception e) {
			handleException(request, e, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "删除失败")).toString());
		}
		return null;
	}
	
	public ActionForward updateBizUtHcInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String utUnitOid = request.getParameter("utUnitOid");
			String verUbHcInfo = request.getParameter("verUbHcInfo");
			JSONObject json=JSONObject.fromObject(verUbHcInfo);
			BizUtHcInfoDTO verUbHcInfoDTO = (BizUtHcInfoDTO)JSONObject.toBean(json,BizUtHcInfoDTO.class);
			BizUtHcInfoDTO oldVerUbHcInfoDTO = bizUtHcInfoFacade.getBizUbHcInfoDTO(verUbHcInfoDTO.getUtHcOid());
			oldVerUbHcInfoDTO.setChgCount(verUbHcInfoDTO.getChgCount());
			oldVerUbHcInfoDTO.setCurCount(verUbHcInfoDTO.getCurCount());
			bizUtHcInfoFacade.update(oldVerUbHcInfoDTO);
			JSONObject obj = showBizUtHcInfoList(NumberUtils.longValue(utUnitOid));
	        response.getWriter().print(obj.toString());
		} catch (Exception e) {
			handleException(request, e, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "更新失败")).toString());
		}
		return null;
	}
	
	/**
	 * 根据unitOid刷新页面
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public JSONObject showBizUtHcInfoList(Long unitOid) throws ServiceException{
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		List<BizUtHcInfoDTO> list = bizUtHcInfoFacade.listByUnitOid(unitOid);
		Collections.sort(list,new BizHcDtoComparator());
		if(CollectionUtils.isNotEmpty(list)){
			for (BizUtHcInfoDTO dto : list) {
				array.element(JSONHelper.fromObject(dto));
			}
		}
		obj.put("rows", array);
		obj.put("unitOid", unitOid);
		return obj;
	}
}
