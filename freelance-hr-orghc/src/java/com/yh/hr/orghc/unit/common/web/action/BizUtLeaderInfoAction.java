package com.yh.hr.orghc.unit.common.web.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.orghc.unit.common.dto.BizUtLeaderInfoDTO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.hr.orghc.unit.common.dto.BizLeaderDtoComparator;
import com.yh.hr.orghc.unit.common.facade.BizUtLeaderInfoFacade;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

public class BizUtLeaderInfoAction extends BaseAction{
	private BizUtLeaderInfoFacade bizUtLeaderInfoFacade = (BizUtLeaderInfoFacade)SpringBeanUtil.getBean("bizUtLeaderInfoFacade");
	
	/**
	 * 根据单位id查询领导职数
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listBizUtLeaderInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String utUnitOid = request.getParameter("utUnitOid");
		List<BizUtLeaderInfoDTO> verUbLeaderInfoDTOs = bizUtLeaderInfoFacade.listByUnitOid(NumberUtils.longValue(utUnitOid));
		Collections.sort(verUbLeaderInfoDTOs,new BizLeaderDtoComparator());
		request.setAttribute("leaderList", verUbLeaderInfoDTOs);
		return mapping.findForward("success");
	}
	
	public ActionForward addBizUtLeaderInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			String utUnitOid = request.getParameter("utUnitOid");
			String verUbLeaderInfo = request.getParameter("verUbLeaderInfo");
			JSONObject json=JSONObject.fromObject(verUbLeaderInfo);
			BizUtLeaderInfoDTO verUbLeaderInfoDTO = (BizUtLeaderInfoDTO)JSONObject.toBean(json,BizUtLeaderInfoDTO.class);
			verUbLeaderInfoDTO.setUtUnitOid(NumberUtils.longValue(utUnitOid));
//			verUbLeaderInfoDTO.setPreCount(verUbLeaderInfoDTO.getCurCount());
//			verUbLeaderInfoDTO.setChgCount((long)0);
//			verUbLeaderInfoDTO.setCurCount((long)0);
			bizUtLeaderInfoFacade.create(verUbLeaderInfoDTO);
	        JSONObject obj = showBizUtHcInfoList(NumberUtils.longValue(utUnitOid));
	        response.getWriter().print(obj.toString());
		}
        catch (Exception e) {
			handleException(request, e, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "添加失败")).toString());
		}
		return null;
	}
	
	public ActionForward deleteBizUtLeaderInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String leaderOid = request.getParameter("leaderOid");
		try{
			if(StringUtils.isEmpty(leaderOid))
			{
				throw new ServiceException(null, "leaderOid is null");
			}
			bizUtLeaderInfoFacade.delete(NumberUtils.longValue(leaderOid));
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
	
	public ActionForward updateBizUtLeaderInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String utUnitOid = request.getParameter("utUnitOid");
			String verUbLeaderInfo = request.getParameter("verUbLeaderInfo");
			JSONObject json=JSONObject.fromObject(verUbLeaderInfo);
			BizUtLeaderInfoDTO verUbLeaderInfoDTO = (BizUtLeaderInfoDTO)JSONObject.toBean(json,BizUtLeaderInfoDTO.class);
			BizUtLeaderInfoDTO oldVerUbLeaderInfoDTO = bizUtLeaderInfoFacade.get(verUbLeaderInfoDTO.getUtLeaderOid());
			oldVerUbLeaderInfoDTO.setChgCount(verUbLeaderInfoDTO.getChgCount());
			oldVerUbLeaderInfoDTO.setCurCount(verUbLeaderInfoDTO.getCurCount());
			bizUtLeaderInfoFacade.update(oldVerUbLeaderInfoDTO);
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
		List<BizUtLeaderInfoDTO> list = bizUtLeaderInfoFacade.listByUnitOid(unitOid);
		Collections.sort(list,new BizLeaderDtoComparator());
		if(CollectionUtils.isNotEmpty(list)){
			for (BizUtLeaderInfoDTO dto : list) {
				array.element(JSONHelper.fromObject(dto));
			}
		}
		obj.put("rows", array);
		obj.put("unitOid", unitOid);
		return obj;
	}
	
}
