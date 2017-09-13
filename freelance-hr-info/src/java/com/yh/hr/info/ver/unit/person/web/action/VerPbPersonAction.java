/**
 * 
 */
package com.yh.hr.info.ver.unit.person.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.info.ver.unit.comm.dto.VerPbImageDTO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.hr.info.ver.unit.comm.dto.VerPbPhotoDTO;
import com.yh.hr.info.ver.unit.comm.facade.VerPbImageFacade;
import com.yh.hr.info.ver.unit.comm.facade.VerPbPhotoFacade;
import com.yh.hr.info.ver.unit.person.dto.VerPbPersonInfoDTO;
import com.yh.hr.info.ver.unit.person.facade.VerPbPersonInfoFacade;
import com.yh.hr.info.ver.unit.person.web.form.VerPbPersonInfoForm;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.ConfigUtil;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

/**
 * @desc 基础人员信息校核WEB层
 * @author luqy
 * @createDate 2016-8-15下午03:50:49
 */
public class VerPbPersonAction extends BaseAction {
	private VerPbPersonInfoFacade	verPbPersonInfoFacade	= (VerPbPersonInfoFacade) SpringBeanUtil.getBean("verPbPersonInfoFacade");
	private VerPbImageFacade verPbImageFacade = (VerPbImageFacade) SpringBeanUtil.getBean("verPbImageFacade");
	private VerPbPhotoFacade verPbPhotoFacade = (VerPbPhotoFacade) SpringBeanUtil.getBean("verPbPhotoFacade");

	/**
	 * @desc 跳转至人员基础信息修改页面
	 * @author luqy
	 * @createDate 2016-8-15下午04:13:22
	 */
	public ActionForward goToViewVerPersonPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String personOid = request.getParameter("personOid");
			response.getWriter().print(personOid);
			return mapping.findForward("success");
		}
		catch (Exception e) {
			handleException(request, e, null);
			return mapping.findForward("error");
		}
	}
	
	/**
     * 跳转到人员信息新增页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward goToAddVerPersonPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.saveToken(request);
        VerPbPersonInfoForm verPbPersonInfoForm = (VerPbPersonInfoForm) form;
        try {
            String unitName=verPbPersonInfoFacade.getUnitName(verPbPersonInfoForm.getUnitOid());
            if (StringUtils.isEmpty(unitName)) {
                throw new ServiceException(null, "跳转到新增页面失败!");
            }
        	/*//整个系统只有一个单位 
        	UtUnitDTO utUnitDTO = verPbPersonInfoFacade.findUnitInfo();
        	if(utUnitDTO == null)
        	{
        		throw new ServiceException(null, "跳转到新增页面失败!");
        		
        	}*/
        	//verPbPersonInfoForm.setUnitOid(utUnitDTO.getUnitOid());
            verPbPersonInfoForm.setUnitName(unitName);
            verPbPersonInfoForm.setIdCode("1");
            verPbPersonInfoForm.setNationalityCode("A1560");
        } catch (Exception e) {
            handleException(request, e, null);
            return mapping.findForward(FORWARD_FAIL);
        }
        return mapping.findForward(FORWARD_SUCCESS);
    }
    
    
    /**
     * 新增人员信息
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addVerPersonInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        VerPbPersonInfoForm verPbPersonInfoForm = (VerPbPersonInfoForm) form;
        try {
        	VerPbPersonInfoDTO verPbPersonInfoDTO = BeanHelper.copyProperties(verPbPersonInfoForm, VerPbPersonInfoDTO.class);
            verPbPersonInfoFacade.addVerPersonInfo(verPbPersonInfoDTO);
            response.getWriter().write(JSONHelper.fromObject(true, null).toString());
        } catch (Exception e) {
            handleException(request, e, e.getMessage());
            response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "新增人员信息失败")).toString());
            return null;
        }
        return null;
    }
    /**
	 * @desc 跳转至人员基本信息修改页面
	 * @author luqy
	 * @createDate 2016-8-15下午04:13:22
	 */
	public ActionForward goToUpdateVerPersonPage(	ActionMapping mapping ,
													ActionForm form ,
													HttpServletRequest request ,
													HttpServletResponse response) throws Exception {
		try {
			VerPbPersonInfoForm verPbPersonInfoForm = (VerPbPersonInfoForm) form;
			if(verPbPersonInfoForm == null){verPbPersonInfoForm = new VerPbPersonInfoForm();}
			VerPbPersonInfoDTO verPbPersonInfoDTO = verPbPersonInfoFacade.getVerPersonInfoById(NumberUtils.longValue(request.getParameter("personOid")));
			Long personOid=verPbPersonInfoDTO.getPersonOid();
			int imageflag = 0;
			VerPbImageDTO verPbImageDTO = null;
			if(personOid!=null)
			{
				verPbImageDTO = verPbImageFacade.getPbImage(personOid);
			}
			if(verPbImageDTO!=null)
			{
				imageflag = 1;
			}
			BeanHelper.copyProperties(verPbPersonInfoDTO, verPbPersonInfoForm);
			if(null != verPbPersonInfoDTO.getUnitOid())
			{
				Long unitOid = verPbPersonInfoDTO.getUnitOid();
				verPbPersonInfoForm.setUnitName(verPbPersonInfoFacade.getUnitName(unitOid));
				//查询部门名称
				if(null != verPbPersonInfoForm.getDeptOid())
				{
					verPbPersonInfoForm.setDeptName(verPbPersonInfoFacade.getOrgName(verPbPersonInfoForm.getDeptOid()));
				}
				//查询所在部门名称
				if(null != verPbPersonInfoForm.getHireDeptOid())
				{
					verPbPersonInfoForm.setHireDeptName(verPbPersonInfoFacade.getOrgName(verPbPersonInfoForm.getHireDeptOid()));
				}
			}
			request.setAttribute("id", request.getParameter("id"));
			request.setAttribute("personOid", request.getParameter("personOid"));
			request.setAttribute("imageflag", imageflag);
		}
		catch (Exception e) {
			handleException(request, e, null);
			return mapping.findForward("error");
		}
		return mapping.findForward("success");
	}

	/**
	 * @desc 人员基础信息修改
	 * @author luqy
	 * @createDate 2016-8-15下午04:14:05
	 */
	public ActionForward updateVerPersonInfo(	ActionMapping mapping ,
												ActionForm form ,
												HttpServletRequest request ,
												HttpServletResponse response) throws Exception {
		try {
			VerPbPersonInfoForm verPbPersonInfoForm = (VerPbPersonInfoForm) form;
			VerPbPersonInfoDTO verPbPersonInfoDTO = BeanHelper.copyProperties(verPbPersonInfoForm, VerPbPersonInfoDTO.class);
			verPbPersonInfoFacade.updateVerPersonInfo(verPbPersonInfoDTO);
			response.getWriter().write(JSONHelper.fromObject(true, null).toString());
        }
        catch (Exception e) {
            handleException(request, e, e.getMessage());
            response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "修改人员信息失败")).toString());
            return null;
        }
		
		
        return null;
	}
	
	/**
	 * @desc 人员基础信息管理页面修改
	 * @author luqy
	 * @createDate 2016-8-15下午04:14:05
	 */
	public ActionForward updateManagePersonInfo(	ActionMapping mapping ,
												ActionForm form ,
												HttpServletRequest request ,
												HttpServletResponse response) throws Exception {
		try {
			VerPbPersonInfoForm verPbPersonInfoForm = (VerPbPersonInfoForm) form;
			VerPbPersonInfoDTO verPbPersonInfoDTO = BeanHelper.copyProperties(verPbPersonInfoForm, VerPbPersonInfoDTO.class);
			verPbPersonInfoFacade.updateVerPersonInfo(verPbPersonInfoDTO);
			response.getWriter().write(JSONHelper.fromObject(true, null).toString());
        }
        catch (Exception e) {
            handleException(request, e, e.getMessage());
            response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "修改人员信息失败")).toString());
            return null;
        }
		
		
        return null;
	}

	/**
	 * @desc 人员基础信息删除
	 * @author luqy
	 * @createDate 2016-8-15下午04:14:05
	 */
	public ActionForward deleteVerPersonInfo(	ActionMapping mapping ,
												ActionForm form ,
												HttpServletRequest request ,
												HttpServletResponse response) throws Exception {
		String[] personOids=request.getParameter("personOids").split(",");
		try{
			for (String personOid : personOids) {
				if(null!=personOid){
						verPbPersonInfoFacade.deleteVerPbPersonInfoById(NumberUtils.longValue(personOid));
				}
			}
			response.getWriter().write(JSONHelper.fromObject(true, null).toString());
		}
		catch (Exception e) {
			this.handleException(request,e, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "人员基础信息删除失败")).toString());
		}
		return null;
	}
	/**
	 * @desc 查看影像信息
	 * @author cheny
	 * @createDate 2017-3-14下午04:14:05
	 */
	public ActionForward listPersonPhoto(	ActionMapping mapping ,
												ActionForm form ,
												HttpServletRequest request ,
												HttpServletResponse response) throws Exception {
		String personOid=request.getParameter("personOid");
		try
		{
			JSONObject o = new JSONObject();
			JSONArray arr = new JSONArray();
			if(personOid!=null&&personOid.length()>0)
			{
				List<VerPbPhotoDTO>  list= verPbPhotoFacade.listPersonPhoto(NumberUtils.longValue(personOid));
				if(CollectionUtils.isNotEmpty(list)){
					for(VerPbPhotoDTO verPbPhotoDTO :list){
						JSONObject obj = new JSONObject();
						obj.put("photoName",verPbPhotoDTO.getPhotoName());
						obj.put("photoCode",verPbPhotoDTO.getPhotoCode());
						obj.put("personOid",verPbPhotoDTO.getPersonOid());
						obj.put("photoPath",ConfigUtil.getProperty("file.path.affiche")+verPbPhotoDTO.getPersonOid()+"/"+verPbPhotoDTO.getPhotoCode()+"."+verPbPhotoDTO.getPhotoType());
						arr.add(obj);
					}
				}
			}
			o.put("list", arr);
			response.getWriter().print(o.toString());
		}
		catch(RuntimeException  e)
		{
			Logger log = Logger.getLogger("web");
			log.error("Photo show error!",e);
		}
		return null;
	}
}
