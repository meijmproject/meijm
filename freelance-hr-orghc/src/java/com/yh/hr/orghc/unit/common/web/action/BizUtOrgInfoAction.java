package com.yh.hr.orghc.unit.common.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.orghc.unit.common.dto.BizUtOrgInfoDTO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.hr.orghc.unit.common.facade.BizUtOrgInfoFacade;
import com.yh.hr.orghc.unit.common.web.form.BizUtOrgInfoForm;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

public class BizUtOrgInfoAction extends BaseAction {

	private BizUtOrgInfoFacade bizUtOrgInfoFacade = (BizUtOrgInfoFacade) SpringBeanUtil.getBean("bizUtOrgInfoFacade");
	
	/**
	 * @desc 跳转内设机构新增页面
	 * @author xiongyx
	 * @createDate 2016-9-21
	 */
	public ActionForward goToCreateBizUtOrgInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.saveToken(request);
		BizUtOrgInfoForm bizUbOrgInfoForm = (BizUtOrgInfoForm) form;
		try {
				String utUnitOid = request.getParameter("utUnitOid");
				bizUbOrgInfoForm.setUtUnitOid(NumberUtils.longValue(utUnitOid));
				String unitName = bizUtOrgInfoFacade.getUnitName(NumberUtils.longValue(utUnitOid));
				bizUbOrgInfoForm.setUnitName(unitName);
				//bizUbOrgInfoForm.setOrgStatus(DicConstants.YHRS0101_2);
				List<BizUtOrgInfoDTO> bizUbOrgInfoDTOs = bizUtOrgInfoFacade.listByUtUnitOid(NumberUtils.longValue(utUnitOid));
				List<BizUtOrgInfoDTO> orgLists = new ArrayList<BizUtOrgInfoDTO>();
				for(BizUtOrgInfoDTO vUbOrgInfoDTO : bizUbOrgInfoDTOs){
					if(null != vUbOrgInfoDTO.getOrgOid()){						
						if(DicConstants.YHRS0102_1.equals(vUbOrgInfoDTO.getOrgType()) 
								|| DicConstants.YHRS0102_3.equals(vUbOrgInfoDTO.getOrgType())){
							continue;
						}
						orgLists.add(vUbOrgInfoDTO);
					}
				}
				request.setAttribute("id", request.getParameter("id"));
				request.setAttribute("orgLists", orgLists);
	        } catch (Exception e) {
	            handleException(request, e, null);
	            return mapping.findForward(FORWARD_FAIL);
	        }
	        return mapping.findForward(FORWARD_SUCCESS);
	}
	
	/**
     * 新增内设机构
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward createBizUtOrgInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	BizUtOrgInfoForm verUbOrgInfoForm = (BizUtOrgInfoForm) form;
        try {
        	BizUtOrgInfoDTO bizUtOrgInfoDTO = BeanHelper.copyProperties(verUbOrgInfoForm, BizUtOrgInfoDTO.class);
        	bizUtOrgInfoFacade.create(bizUtOrgInfoDTO);
            response.getWriter().write(JSONHelper.fromObject(true, bizUtOrgInfoDTO.getUtUnitOid().toString()).toString());
        } catch (Exception e) {
            handleException(request, e, e.getMessage());
            response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "新增内设机构信息失败")).toString());
            return null;
        }
        return null;
    }
    
    /**
	 * @desc 内设机构删除
	 * @author xoingyx
	 * @createDate 2016-9-22
	 */
	public ActionForward deleteBizUtOrgInfo(ActionMapping mapping ,
													ActionForm form ,
													HttpServletRequest request ,
													HttpServletResponse response) throws Exception {
		try {
			String orgOid = request.getParameter("utOrgOid");
			bizUtOrgInfoFacade.delete(NumberUtils.longValue(orgOid));
		}
		catch (Exception e) {
			this.handleException(request,e, null);
			response.getWriter().write(JSONHelper.fromObject(false,"内设机构删除失败").toString());
			return null;
		}
		String unitOid = request.getParameter("unitOid");
		response.getWriter().write(JSONHelper.fromObject(true,unitOid).toString());
		return null;
	}
	
	/**
	 * @desc 跳转至内设机构修改页面
	 * @author xiongyx
	 * @createDate 2016-9-22
	 */
	public ActionForward goToUpdateBizUtOrgInfo(ActionMapping mapping ,
														ActionForm form ,
														HttpServletRequest request ,
														HttpServletResponse response) throws Exception {
		try {
			BizUtOrgInfoForm bizUbOrgInfoForm = (BizUtOrgInfoForm) form;
			if(bizUbOrgInfoForm == null){bizUbOrgInfoForm = new BizUtOrgInfoForm();}
			String utUnitOid = request.getParameter("unitOid");
			String unitName = bizUtOrgInfoFacade.getUnitName(NumberUtils.longValue(utUnitOid));
			bizUbOrgInfoForm.setUnitName(unitName);
			//根据positioningOid得到VerJgPbPositioningInfoDTO
			BizUtOrgInfoDTO verUbOrgInfoDTO = bizUtOrgInfoFacade.getBizUtOrgInfoDTOById(NumberUtils.longValue(request.getParameter("orgOid")));
			BeanHelper.copyProperties(verUbOrgInfoDTO, bizUbOrgInfoForm);
			request.setAttribute("verUbOrgInfoForm", bizUbOrgInfoForm);
			List<BizUtOrgInfoDTO> verUbOrgInfoDTOs = bizUtOrgInfoFacade.listByUtUnitOid(NumberUtils.longValue(utUnitOid));
 			List<BizUtOrgInfoDTO> orgLists = new ArrayList<BizUtOrgInfoDTO>();
			for(BizUtOrgInfoDTO vUbOrgInfoDTO : verUbOrgInfoDTOs){
				if(null != vUbOrgInfoDTO.getOrgOid()){					
					if(null != verUbOrgInfoDTO.getOrgOid() 
							&& (DicConstants.YHRS0102_1.equals(vUbOrgInfoDTO.getOrgType()) 
									|| DicConstants.YHRS0102_3.equals(vUbOrgInfoDTO.getOrgType()) 
									|| verUbOrgInfoDTO.getOrgOid().equals(vUbOrgInfoDTO.getOrgOid())
									|| (null != vUbOrgInfoDTO.getParentOrgOid() && (vUbOrgInfoDTO.getParentOrgOid().longValue() == verUbOrgInfoDTO.getOrgOid().longValue())))){
						continue;
					}
					if(null != verUbOrgInfoDTO.getParentOrgOid() 
							&& verUbOrgInfoDTO.getParentOrgOid().equals(vUbOrgInfoDTO.getOrgOid())){
						request.setAttribute("parentOrgName", vUbOrgInfoDTO.getOrgName());
						continue;
					}
					orgLists.add(vUbOrgInfoDTO);
				}
				
			}
			request.setAttribute("id", request.getParameter("id"));
			request.setAttribute("orgLists", orgLists);
			
		}
		catch (Exception e) {
			handleException(request, e, null);
			return mapping.findForward("error");
		}
		return mapping.findForward("success");
	}
	
	/**
	 * @desc 内设机构修改
	 * @author xiongyx
	 * @createDate 2016-9-22
	 */
	public ActionForward updateBizUtOrgInfo(ActionMapping mapping ,
													ActionForm form ,
													HttpServletRequest request ,
													HttpServletResponse response) throws Exception {
		
		try {
			BizUtOrgInfoForm verUbOrgInfoForm = (BizUtOrgInfoForm) form;
			BizUtOrgInfoDTO verUbOrgInfoDTO = BeanHelper.copyProperties(verUbOrgInfoForm, BizUtOrgInfoDTO.class);
			bizUtOrgInfoFacade.update(verUbOrgInfoDTO);
			response.getWriter().write(JSONHelper.fromObject(true, verUbOrgInfoDTO.getUtUnitOid().toString()).toString());
		}
		catch (Exception e) {
			handleException(request, e, e.getMessage());
 		    response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "内设机构修改失败")).toString());
		}


		return null;
	}
	
	/**
	 * @desc 跳转至内设机构查看页面
	 * @author xiongyx
	 * @createDate 2016-9-23
	 */
	public ActionForward goToViewUtOrgInfoPage(ActionMapping mapping ,
														ActionForm form ,
														HttpServletRequest request ,
														HttpServletResponse response) throws Exception {
		try {
			BizUtOrgInfoForm bizUbOrgInfoForm = (BizUtOrgInfoForm) form;
			if(bizUbOrgInfoForm == null){bizUbOrgInfoForm = new BizUtOrgInfoForm();}
			String utUnitOid = request.getParameter("unitOid");
			String unitName = bizUtOrgInfoFacade.getUnitName(NumberUtils.longValue(utUnitOid));
			bizUbOrgInfoForm.setUnitName(unitName);
			BizUtOrgInfoDTO verUbOrgInfoDTO = bizUtOrgInfoFacade.getBizUtOrgInfoDTOById(NumberUtils.longValue(request.getParameter("orgOid")));
//			if(DicConstants.YHRS0101_1.equals(verUbOrgInfoDTO.getOrgStatus())){
//				verUbOrgInfoDTO.setOrgStatus("新建");
//			}
//			if(DicConstants.YHRS0101_2.equals(verUbOrgInfoDTO.getOrgStatus())){
//				verUbOrgInfoDTO.setOrgStatus("正常");
//			}
//			if(DicConstants.YHRS0101_3.equals(verUbOrgInfoDTO.getOrgStatus())){
//				verUbOrgInfoDTO.setOrgStatus("撤销");
//			}
			BeanHelper.copyProperties(verUbOrgInfoDTO, bizUbOrgInfoForm);
			//verUbOrgInfoForm.setUnitName(unitName);
			request.setAttribute("verUbOrgInfoForm", bizUbOrgInfoForm);

			if(null != verUbOrgInfoDTO.getParentOrgOid()){
				List<BizUtOrgInfoDTO> verUbOrgInfoDTOs = bizUtOrgInfoFacade.listByUtUnitOid(NumberUtils.longValue(utUnitOid));
				for(BizUtOrgInfoDTO vUbOrgInfoDTO : verUbOrgInfoDTOs){
					if(verUbOrgInfoDTO.getParentOrgOid().equals(vUbOrgInfoDTO.getOrgOid())){
						bizUbOrgInfoForm.setParentOrgName(vUbOrgInfoDTO.getOrgName());
						continue;
					}
				}
			}
		}
		catch (Exception e) {
			handleException(request, e, null);
			return mapping.findForward("error");
		}
		return mapping.findForward("success");
	}
	
	public ActionForward listBizUtOrgInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String utUnitOid = request.getParameter("utUnitOid");

		List<BizUtOrgInfoDTO> verUbOrgInfoDTOs = bizUtOrgInfoFacade.listByUtUnitOid(NumberUtils.longValue(utUnitOid));
		List<BizUtOrgInfoDTO> orgList = new ArrayList<BizUtOrgInfoDTO>();
		if(CollectionUtils.isNotEmpty(verUbOrgInfoDTOs)){
			for(BizUtOrgInfoDTO verUbOrgInfoDTO : verUbOrgInfoDTOs){
				if(StringUtils.isNotEmpty(verUbOrgInfoDTO.getLevelCode())){
					verUbOrgInfoDTO.setLevelCode(DicHelper.viewName(DicConstants.YHRS0093, verUbOrgInfoDTO.getLevelCode()));
				}
				if(null != verUbOrgInfoDTO.getParentOrgOid() && 0 != verUbOrgInfoDTO.getParentOrgOid()){
					for(BizUtOrgInfoDTO vOrgInfoDTO : verUbOrgInfoDTOs){
						if(null != vOrgInfoDTO.getOrgOid()){							
							if(vOrgInfoDTO.getOrgOid().longValue() == verUbOrgInfoDTO.getParentOrgOid().longValue()){
								verUbOrgInfoDTO.setParentOrgName(vOrgInfoDTO.getOrgName());
							}
						}
					}
				}
				orgList.add(verUbOrgInfoDTO);
			}
		}
		request.setAttribute("orgLists", orgList);
		request.setAttribute("utUnitOid", utUnitOid);
//		String unitName = verUbOrgInfoFacade.getUnitName(NumberUtils.longValue(unitOid));
//		if(null != unitName){
//			request.setAttribute("unitName", unitName);
//		}
		return mapping.findForward("success");
	}
}