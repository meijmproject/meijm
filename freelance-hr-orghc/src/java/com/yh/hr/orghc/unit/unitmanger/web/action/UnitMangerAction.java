package com.yh.hr.orghc.unit.unitmanger.web.action;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.orghc.ub.dto.UbUnitDTO;
import com.yh.hr.orghc.unit.unitmanger.dto.OrgResponseDTO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.orghc.ub.bo.UbUnit;
import com.yh.hr.orghc.ub.dto.UbHcDTO;
import com.yh.hr.orghc.ub.dto.UbLeaderDTO;
import com.yh.hr.orghc.ub.dto.UbOrgDTO;
import com.yh.hr.orghc.unit.unitmanger.dto.OrgDTO;
import com.yh.hr.orghc.unit.unitmanger.facade.UnitMangerFacade;
import com.yh.hr.orghc.unit.unitmanger.util.OrgResponseUtil;
import com.yh.hr.orghc.unit.unitmanger.web.form.OrgForm;
import com.yh.hr.orghc.unit.unitmanger.web.form.UbUnitForm;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

public class UnitMangerAction extends BaseAction {
	private UnitMangerFacade unitMangerFacade = (UnitMangerFacade) SpringBeanUtil.getBean("unitMangerFacade");
	/**
	 * 跳转到单位管理工作台
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goToUnitManageWorkBench(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		int count = unitMangerFacade.listUnitCounts();
		request.setAttribute("unitCount",count);
		return mapping.findForward("success");
	}
	/**
	 * 跳转到单位新增页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goToAddUbUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		return mapping.findForward("success");
	}
	/**
	 * 单位新增
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addUnitInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		try
		{
			UbUnitForm ubUnitForm = (UbUnitForm) form;
			UbUnitDTO ubUnitDTO = BeanHelper.copyProperties(ubUnitForm, UbUnitDTO.class);
			unitMangerFacade.addUnitInfo(ubUnitDTO);
			response.getWriter().write(JSONHelper.fromObject(true, null).toString());
		}
		catch(Exception se)
		{
			this.handleException(request, se, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(se.getMessage(), "新增失败")).toString());
		}
		return null;
	}
	/**
	 * 获取单位信息详细信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getUnitInformation(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		UbUnitForm ubUnitForm = (UbUnitForm) form;
		String unitOid = request.getParameter("unitOid");
		UbUnit ubUnit = unitMangerFacade.getUnitInformationByUnitOid(unitOid);
		BeanHelper.copyProperties(ubUnit, ubUnitForm);
		request.setAttribute("unitOid", unitOid);
		return mapping.findForward("success");
	}
	/**
	 * 跳转到单位管理详情页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goToUnitManger(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		try {
			String unitOid = request.getParameter("unitOid");
			UbUnitForm ubUnitForm = (UbUnitForm) form;
			UbUnit ubUnit = unitMangerFacade.getUnitInformationByUnitOid(unitOid);
			BeanHelper.copyProperties(ubUnit, ubUnitForm);
			/*List<UbLeaderDTO> leaderList= unitMangerFacade.listleaderByUnitOid(unitOid);
			List<UbHcDTO> hcList = unitMangerFacade.listhcByUnitOid(unitOid);
			request.setAttribute("leaderList", leaderList);
			request.setAttribute("hcList", hcList);*/
			request.setAttribute("unitOid", unitOid);
			} catch (Exception e) {
				handleException(request, e, null);
				return mapping.findForward("success");
			}
			return mapping.findForward("success");
	}
	
	/**
	 * 根据unitOid查询科室列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listOrgByUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			TableTagBean ttb = new TableTagBean(request);
			List<JSONObject> list = unitMangerFacade.listOrg(ttb);
			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();
			json.put("total", ttb.getTotal());
			if(CollectionUtils.isNotEmpty(list)){
				for (JSONObject obj : list) {
					if(null!=obj.get("parentOrgOid")){
						UbOrgDTO dto=unitMangerFacade.getParentOrg(NumberUtils.longValue(obj.get("parentOrgOid").toString()));
						if(null!=dto){
							if(DicConstants.YHRS0101_1.equals(dto.getOrgType())||DicConstants.YHRS0101_2.equals(dto.getOrgType())||DicConstants.YHRS0101_3.equals(dto.getOrgType())){
								obj.put("parentOrgName", dto.getOrgName());
							}else{
								obj.put("parentOrgName", null);
							}
						}
					}
					array.element(obj);
				}
			}
			json.put("rows", array);
			response.getWriter().print(json.toString());

		} catch (Exception e) {
			handleException(request, e, "查询科室列表失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "查询科室列表失败")));
		}

		return null;
	}
	
	/**
	 * 跳转新增科室
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goCreateOrg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("unitOid", request.getParameter("unitOid"));
		return mapping.findForward("success");
	}
	/**
	 * 新增科室
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createOrg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		OrgForm orgForm = (OrgForm) form;
		try {
			OrgDTO dto = BeanHelper.copyProperties(orgForm, OrgDTO.class);
			Long orgOid=unitMangerFacade.create(dto);
			response.getWriter().write(JSONHelper.fromObject(true, StringUtils.defaultIfEmpty(orgOid.toString(),"新增科室信息成功")).toString());
		} catch (Exception e) {
			handleException(request, e, e.getMessage());
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "新增科室信息失败")).toString());
			return null;
		}
		return null;
	}
	/**
	 * 跳转修改科室
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goUpdateOrg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String orgOid=request.getParameter("orgOid");
			if(null==orgOid) throw new ServiceException(null,"科室id不存在");
			OrgDTO dto = unitMangerFacade.getOrg(NumberUtils.longValue(orgOid));
			if(null!=dto){
				if(NumberUtils.isNotNullOrZero(dto.getParentOrgOid())){
					UbOrgDTO parentDTO=unitMangerFacade.getParentOrg(dto.getParentOrgOid());
					if(null!=parentDTO){
						if(DicConstants.YHRS0101_1.equals(parentDTO.getOrgType())||DicConstants.YHRS0101_2.equals(parentDTO.getOrgType())||DicConstants.YHRS0101_3.equals(parentDTO.getOrgType())){
						}else{
							dto.setParentOrgName(null);
						}
					}
				}
			}
			BeanHelper.copyProperties(dto, form);
			request.setAttribute("initParentOrgOid", dto.getParentOrgOid());
			request.setAttribute("unitOid", dto.getUnitOid());
			request.setAttribute("flag", request.getParameter("flag"));
		} catch (Exception e) {
			handleException(request, e, e.getMessage());
//			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "跳转修改科室页面失败")).toString());
			return null;
		}
		return mapping.findForward("success");
	}
	/**
	 * 修改科室
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateOrg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		OrgForm orgForm = (OrgForm) form;
		try {
			OrgDTO dto = BeanHelper.copyProperties(orgForm, OrgDTO.class);
			unitMangerFacade.update(dto);
			response.getWriter().write(JSONHelper.fromObject(true, StringUtils.defaultIfEmpty(null,"修改科室信息成功")).toString());
		} catch (Exception e) {
			handleException(request, e, e.getMessage());
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "修改科室信息失败")).toString());
			return null;
		}
		return null;
	}
	
	/**
	 * 查看科室信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goViewOrg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String orgOid=request.getParameter("orgOid");
			if(null==orgOid) throw new ServiceException(null,"科室id不存在");
			OrgDTO dto = unitMangerFacade.getOrg(NumberUtils.longValue(orgOid));
			if(null!=dto){
				if(NumberUtils.isNotNullOrZero(dto.getParentOrgOid())){
					UbOrgDTO parentDTO=unitMangerFacade.getParentOrg(dto.getParentOrgOid());
					if(null!=parentDTO){
						if(DicConstants.YHRS0101_1.equals(parentDTO.getOrgType())||DicConstants.YHRS0101_2.equals(parentDTO.getOrgType())||DicConstants.YHRS0101_3.equals(parentDTO.getOrgType())){
						}else{
							dto.setParentOrgName(null);
						}
					}
				}
			}
			BeanHelper.copyProperties(dto, form);
		} catch (Exception e) {
			handleException(request, e, e.getMessage());
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "跳转查看科室页面失败")).toString());
			return null;
		}
		return mapping.findForward("success");
	}
	
	/**
	 * 删除科室信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteOrg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] orgOids=request.getParameter("orgOids").split(",");
		String[] applyNames   = request.getParameter("applyNames").split(",");
		List<OrgResponseDTO> applyResponseDTOs = new ArrayList<OrgResponseDTO>();
		boolean success = true;
		if(null!=orgOids){
			for (int i = 0; i < orgOids.length; i++) {
				
				try {
					
					unitMangerFacade.delete(NumberUtils.longValue(orgOids[i]));
					// 3、组装执行结果反馈信息
					applyResponseDTOs.add(OrgResponseUtil.getSuccessResponseDTO());
					}
					catch (Exception se)
					{
					this.handleException(request, se, se.getMessage());
					applyResponseDTOs.add(OrgResponseUtil.getFailResponseDTO(se.getMessage(), applyNames[i]));
					success = false;
					}
			}
		}
	    response.getWriter().print(JSONHelper.fromObject(success, OrgResponseUtil.conversionReportDTO(applyResponseDTOs)));
		return null;
	}
	
	/**
	 * 撤销科室信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward revokeOrg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] orgOids=request.getParameter("orgOids").split(",");
		String[] applyNames   = request.getParameter("applyNames").split(",");
		List<OrgResponseDTO> applyResponseDTOs = new ArrayList<OrgResponseDTO>();
		boolean success = true;
		if(null!=orgOids){
			for (int i = 0; i < orgOids.length; i++) {
				
				try {
					
					unitMangerFacade.updateOrgStatus(NumberUtils.longValue(orgOids[i]));
					// 3、组装执行结果反馈信息
					applyResponseDTOs.add(OrgResponseUtil.getSuccessResponseDTO());
				}
				catch (Exception se)
				{
					this.handleException(request, se, se.getMessage());
					applyResponseDTOs.add(OrgResponseUtil.getFailResponseDTO(se.getMessage(), applyNames[i]));
					success = false;
				}
			}
		}
	    response.getWriter().print(JSONHelper.fromObject(success, OrgResponseUtil.conversionReportDTO(applyResponseDTOs)));
		return null;
	}
	/**
	 * @desc 单位信息修改
	 * @author cheny
	 * @createDate 20174-18下午04:14:05
	 */
	public ActionForward updateUnitInfo(ActionMapping mapping ,ActionForm form ,HttpServletRequest request ,HttpServletResponse response) throws Exception {
		try {
			UbUnitForm ubUnitForm = (UbUnitForm) form;
			UbUnitDTO ubUnitDTO = BeanHelper.copyProperties(ubUnitForm, UbUnitDTO.class);
			unitMangerFacade.updateUnitInfo(ubUnitDTO);
			response.getWriter().write(JSONHelper.fromObject(true, null).toString());
        }
        catch (Exception e) {
            handleException(request, e, e.getMessage());
            response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "修改单位信息失败")).toString());
            return null;
        }
        return null;
	}
	/**
	 * @desc 增加编制信息
	 * @author cheny
	 * @createDate 20174-18下午04:14:05
	 */
	public ActionForward addVerUbHcInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			String unitOid = request.getParameter("unitOid");
			String verUbHcInfo = request.getParameter("verUbHcInfo");
			JSONObject json=JSONObject.fromObject(verUbHcInfo);
			UbHcDTO ubHcDTO = (UbHcDTO)JSONObject.toBean(json,UbHcDTO.class);
			ubHcDTO.setUnitOid(NumberUtils.longValue(unitOid));
			unitMangerFacade.addVerUbHcInfoDTO(ubHcDTO);
	        JSONObject obj = showVerUbHcInfoList(NumberUtils.longValue(unitOid));
	        response.getWriter().print(obj.toString());
		}
        catch (Exception e) {
			handleException(request, e, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "添加失败")).toString());

		}
		return null;
	}
	/**
	 * @desc 编制信息列表
	 * @author cheny
	 * @createDate 20174-18下午04:14:05
	 */
	public ActionForward listHcInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			String unitOid = request.getParameter("unitOid");
			JSONArray array = new JSONArray();
			List<UbHcDTO> list = unitMangerFacade.listhcByUnitOid(unitOid.toString());
			if(CollectionUtils.isNotEmpty(list)){
				for (UbHcDTO dto : list) {
					array.element(JSONHelper.fromObject(dto));
				}
			}
		    request.setAttribute("hcList", array);
		}
        catch (Exception e) {
			handleException(request, e, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "查询失败")).toString());
		}
        return mapping.findForward("success");
	}
	/**
	 * 根据unitOid刷新页面
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public JSONObject showVerUbHcInfoList(Long unitOid) throws ServiceException{
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		List<UbHcDTO> list = unitMangerFacade.listhcByUnitOid(unitOid.toString());
		if(CollectionUtils.isNotEmpty(list)){
			for (UbHcDTO dto : list) {
				array.element(JSONHelper.fromObject(dto));
			}
		}
		obj.put("rows", array);
		obj.put("unitOid", unitOid);
		return obj;
	}
	/**
	 * @desc 删除编制信息
	 * @author cheny
	 * @createDate 20174-18下午04:14:05
	 */
	public ActionForward deleteVerUbHcInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String hcOids = request.getParameter("hcOids");
		try{
			if(StringUtils.isEmpty(hcOids))
			{
				throw new ServiceException(null, "hcOids is null");
			}
			String[] hcOid = hcOids.split(",");
			for(int i=0;i<hcOid.length;i++){
				unitMangerFacade.deleteVerUbHcInfoDTO(NumberUtils.longValue(hcOid[i]));
			}
			response.getWriter().write(JSONHelper.fromObject(true, null).toString());
		}
        catch (Exception e) {
			handleException(request, e, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "删除失败")).toString());
		}
		return null;
	}
	/**
	 * @desc 修改编制信息
	 * @author cheny
	 * @createDate 20174-18下午04:14:05
	 */
	public ActionForward updateVerUbHcInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String unitOid = request.getParameter("unitOid");
			String verUbHcInfo = request.getParameter("verUbHcInfo");
			JSONObject json=JSONObject.fromObject(verUbHcInfo);
			UbHcDTO ubHcDTO = (UbHcDTO)JSONObject.toBean(json,UbHcDTO.class);
			UbHcDTO oldVerUbHcInfoDTO = unitMangerFacade.getVerUbHcInfoDTO(ubHcDTO.getHcOid());
			oldVerUbHcInfoDTO.setChgCount(ubHcDTO.getChgCount());
			oldVerUbHcInfoDTO.setCurCount(ubHcDTO.getCurCount());
			oldVerUbHcInfoDTO.setPreCount(ubHcDTO.getPreCount());
			unitMangerFacade.updateUbHcDTO(oldVerUbHcInfoDTO);
			JSONObject obj = showVerUbHcInfoList(NumberUtils.longValue(unitOid));
	        response.getWriter().print(obj.toString());
		} catch (Exception e) {
			handleException(request, e, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "更新失败")).toString());
		}
		return null;
	}
	/**
	 * @desc 添加领导职数信息
	 * @author cheny
	 * @createDate 20174-18下午04:14:05
	 */
	public ActionForward addVerUbLeaderInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			String unitOid = request.getParameter("unitOid");
			String verUbLeaderInfo = request.getParameter("verUbLeaderInfo");
			JSONObject json=JSONObject.fromObject(verUbLeaderInfo);
			UbLeaderDTO ubLeaderDTO = (UbLeaderDTO)JSONObject.toBean(json,UbLeaderDTO.class);
			ubLeaderDTO.setUnitOid(NumberUtils.longValue(unitOid));
			unitMangerFacade.addVerUbLeaderInfo(ubLeaderDTO);
	        JSONObject obj = showVerUbLeaderInfoList(NumberUtils.longValue(unitOid));
	        response.getWriter().print(obj.toString());
		}
        catch (Exception e) {
			handleException(request, e, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "添加失败")).toString());
		}
		return null;
	}
	/**
	 * @desc 删除领导职数信息
	 * @author cheny
	 * @createDate 20174-18下午04:14:05
	 */
	public ActionForward deleteVerUbLeaderInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String leaderOids = request.getParameter("leaderOids");
		try{
			if(StringUtils.isEmpty(leaderOids))
			{
				throw new ServiceException(null, "hcOids is null");
			}
			String[] leaderOid = leaderOids.split(",");
			for(int i=0;i<leaderOid.length;i++){
				unitMangerFacade.deleteVerUbLeaderInfo(NumberUtils.longValue(leaderOid[i]));
			}
			response.getWriter().write(JSONHelper.fromObject(true, null).toString());
		}
        catch (Exception e) {
			handleException(request, e, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "删除失败")).toString());
		}
		return null;
	}
	/**
	 * @desc 修改领导职数信息
	 * @author cheny
	 * @createDate 20174-18下午04:14:05
	 */
	public ActionForward updateVerUbLeaderInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String unitOid = request.getParameter("unitOid");
			String verUbLeaderInfo = request.getParameter("verUbLeaderInfo");
			JSONObject json=JSONObject.fromObject(verUbLeaderInfo);
			UbLeaderDTO ubLeaderDTO = (UbLeaderDTO)JSONObject.toBean(json,UbLeaderDTO.class);
			UbLeaderDTO oldVerUbLeaderInfoDTO = unitMangerFacade.getVerUbLeaderInfo(ubLeaderDTO.getLeaderOid());
			oldVerUbLeaderInfoDTO.setChgCount(ubLeaderDTO.getChgCount());
			oldVerUbLeaderInfoDTO.setCurCount(ubLeaderDTO.getCurCount());
			oldVerUbLeaderInfoDTO.setPreCount(ubLeaderDTO.getPreCount());
			unitMangerFacade.updateVerUbLeaderInfo(oldVerUbLeaderInfoDTO);
	        JSONObject obj = showVerUbLeaderInfoList(NumberUtils.longValue(unitOid));
	        response.getWriter().print(obj.toString());
		} catch (Exception e) {
			handleException(request, e, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "更新失败")).toString());
		}
		return null;
	}
	/**
	 * @desc 领导职数信息列表
	 * @author cheny
	 * @createDate 20174-18下午04:14:05
	 */
	public ActionForward listLeaderInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			String unitOid = request.getParameter("unitOid");
			JSONArray array = new JSONArray();
			List<UbLeaderDTO> list = unitMangerFacade.listVerUbLeaderInfo(Long.valueOf(unitOid));
			if(CollectionUtils.isNotEmpty(list)){
				for (UbLeaderDTO dto : list) {
					array.element(JSONHelper.fromObject(dto));
				}
			}
		    request.setAttribute("leaderList", array);
		}
        catch (Exception e) {
			handleException(request, e, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "查询失败")).toString());
		}
        return mapping.findForward("success");
	}
	/**
	 * 根据unitOid刷新页面
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public JSONObject showVerUbLeaderInfoList(Long unitOid) throws ServiceException{
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		List<UbLeaderDTO> list = unitMangerFacade.listVerUbLeaderInfo(unitOid);
		if(CollectionUtils.isNotEmpty(list)){
			for (UbLeaderDTO dto : list) {
				array.element(JSONHelper.fromObject(dto));
			}
		}
		obj.put("rows", array);
		obj.put("unitOid", unitOid);
		return obj;
	}
	/**
	 * 上级科室联动科室类型
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findByOrgType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			JSONObject obj = new JSONObject();
			JSONArray array = new JSONArray();
			String orgType=request.getParameter("orgCategory");
			String unitOid=request.getParameter("unitOid");
			List<OrgDTO> list = new ArrayList<OrgDTO>();
			if(null!=orgType&&null!=unitOid){
				list=unitMangerFacade.findByOrgType(unitOid,orgType);
			}
			if(CollectionUtils.isNotEmpty(list)){
				for (OrgDTO dto : list) {
					array.element(dto);
				}
			}
			obj.put("rs", array);
			response.getWriter().print(obj.toString());
		} catch (Exception e) {
			handleException(request, e, e.getMessage());
			return null;
		}
		return null;
	}
	
	/**
	 * 获取科室子节点
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getOrg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String unitOid = request.getParameter("unitOid");
			String orgOidStr = request.getParameter("orgOid");
			List<UbOrgDTO> orgDtos = unitMangerFacade.getOrg(NumberUtils.longValue(unitOid),NumberUtils.longValue(orgOidStr));
			JSONObject json = new JSONObject();
			if(CollectionUtils.isEmpty(orgDtos))
			{
				orgDtos = new ArrayList<UbOrgDTO>();
			}
			json.put("json", orgDtos);
			response.getWriter().print(json.toString());
		} catch (Exception e) {
			handleException(request, e, "show tree faild!");
			
		}
		return null;
	}
}
