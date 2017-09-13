package com.yh.hr.info.ver.unit.comm.web.action;

import com.yh.hr.info.ver.unit.comm.facade.PbDutyInfoFacade;
import com.yh.hr.info.ver.unit.person.dto.VerPbPersonInfoDTO;
import jade.workflow.utils.ObjectUtil;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.orgtree.facade.JhcOrgTreeFacade;
import com.yh.hr.info.ver.unit.comm.web.form.PbDutyInfoForm;
import com.yh.hr.info.ver.unit.person.facade.VerPbPersonInfoFacade;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pb.dto.PbDutyInfoDTO;
import com.yh.hr.res.unit.dto.UtOrgDTO;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.util.StringUtil;
import com.yh.platform.core.web.UserContext;
import com.yh.platform.core.web.action.BaseAction;

public class PbDutyInfoAction extends BaseAction {
	private PbDutyInfoFacade pbDutyInfoFacade = (PbDutyInfoFacade) SpringBeanUtil.getBean("pbDutyInfoFacade");
	private VerPbPersonInfoFacade	verPbPersonInfoFacade	= (VerPbPersonInfoFacade) SpringBeanUtil.getBean("verPbPersonInfoFacade");
	private JhcOrgTreeFacade jhcOrgTreeFacade = (JhcOrgTreeFacade) SpringBeanUtil.getBean("jhcOrgTreeFacade");
	
	/**
	 * 列出所有院内中层职务任职信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listPbDutyInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TableTagBean ttb = TableTagBean.getFromRequest(request);
		if (request.getMethod().equals("POST")) {
	        @SuppressWarnings("rawtypes")
			Map map = BeanUtils.describe(form);
	        ttb.getCondition().copyFrom(map, true);
	    } else {
	        BeanUtils.populate(form, ttb.getCondition());
	        request.setAttribute("pbDutyInfoForm", form);
	    }
        try {
        	List<PbDutyInfoDTO> list = pbDutyInfoFacade.find(ttb);
        	if(CollectionUtils.isNotEmpty(list)){
        		for(PbDutyInfoDTO dto: list){
        			dto.setDutyStatus(DicHelper.viewName(DicConstants.YHRS0026, dto.getDutyStatus()));
        			dto.setDutyName(DicHelper.viewName(DicConstants.YHRS0126, dto.getDutyName()));
        			UtOrgDTO utOrgDTO = jhcOrgTreeFacade.findOrgByOid(dto.getDeptOid());
        			if(utOrgDTO!=null){
        				dto.setDeptName(utOrgDTO.getOrgName());
        			}else{
        				dto.setDeptName("");
        			}
        			if(dto.getStartDate()!=null){
        				dto.setStartDateStr(DateUtil.formatDate(dto.getStartDate()));
        			}
        			if(dto.getEndDate()!=null){
        				dto.setEndDateStr(DateUtil.formatDate(dto.getEndDate()));

        			}
        		}
        	}
        	
			request.setAttribute("list", list);
	        request.setAttribute("ttb", ttb);
        } catch (Exception se) {
			this.handleException(request, se, null);
			return mapping.findForward("fail");
		}

        return mapping.findForward("success");
	}
	
	/**
	 * 查看院内中层职务任职信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward show(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        String dutyOid = request.getParameter("dutyOid");
        
        if (StringUtil.isBlank(dutyOid)) {
        	dutyOid = ObjectUtil.getValue(request.getAttribute("dutyOid"), String.class);
        }
		try
		{
			if (StringUtils.isEmpty(dutyOid)) {
				throw new ServiceException(null, "educationOid is null");
			}
			PbDutyInfoDTO pbDutyInfoDTO = pbDutyInfoFacade.get(ObjectUtil.getValue(dutyOid, java.lang.Long.class));
			if (null == pbDutyInfoDTO) {
				throw new ServiceException(null, "查询不到相关信息");
			}
			PbDutyInfoForm pbDutyInfoForm = BeanHelper.copyProperties(pbDutyInfoDTO, PbDutyInfoForm.class);
			UtOrgDTO utOrgDTO = jhcOrgTreeFacade.findOrgByOid(pbDutyInfoForm.getDeptOid());
			pbDutyInfoForm.setDeptName(utOrgDTO.getOrgName());
			pbDutyInfoForm.setStartDateStr(DateUtil.format(pbDutyInfoForm.getStartDate(), "yyyy-MM-dd"));
			pbDutyInfoForm.setEndDateStr(DateUtil.format(pbDutyInfoForm.getEndDate(), "yyyy-MM-dd"));
			request.setAttribute("pbDutyInfoForm", pbDutyInfoForm);
            request.setAttribute("dutyOid", dutyOid);
            
		}
		catch(Exception se)
		{
			this.handleException(request, se, "dutyOid=" + dutyOid);
			return mapping.getInputForward();
		}
		return mapping.findForward("success");	
	}
	
	/**
	 * 跳转到院内中层职务任职信息新增页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goCreate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String urlId = request.getParameter("urlId");
		String personOid = request.getParameter("personOid");
		try {
			if(StringUtils.isEmpty(urlId))
			{
				throw new ServiceException("urlId is null");
			}
			if(StringUtils.isEmpty(personOid))
			{
				throw new ServiceException("personOid is null");
			}
			VerPbPersonInfoDTO verSyPersonDTO = verPbPersonInfoFacade.getVerPersonInfoById(NumberUtils.longValue(personOid));

			request.setAttribute("urlId", urlId);
			request.setAttribute("personOid", personOid);
			request.setAttribute("unitOid", verSyPersonDTO.getUnitOid());
		} catch (Exception e) {
			this.handleException(request, e, null);
			return mapping.getInputForward();
		}
		return mapping.findForward(FORWARD_SUCCESS);
	}
	
	/**
	 * 新增院内中层职务任职信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (this.isCancelled(request)) {
			return mapping.findForward("cancel");
		}
		PbDutyInfoForm pbDutyInfoForm = (PbDutyInfoForm) form;
		try {
			
			PbDutyInfoDTO pbDutyInfoDTO = BeanHelper.copyProperties(pbDutyInfoForm, PbDutyInfoDTO.class);
			if(StringUtil.isNotBlank(pbDutyInfoForm.getStartDateStr())){
				pbDutyInfoDTO.setStartDate(DateUtil.parseDate(pbDutyInfoForm.getStartDateStr()));
			}
			if(StringUtil.isNotBlank(pbDutyInfoForm.getEndDateStr())){
				pbDutyInfoDTO.setEndDate(DateUtil.parseDate(pbDutyInfoForm.getEndDateStr()));

			}
			pbDutyInfoDTO.setCreateBy(UserContext.getLoginUserID());
			pbDutyInfoDTO.setCreateName(UserContext.getLoginUserName());
			pbDutyInfoDTO.setCreateDate(DateUtil.now());
			pbDutyInfoFacade.create(pbDutyInfoDTO);
			response.getWriter().write(JSONHelper.fromObject(true, null).toString());
		} catch (Exception se) {
			this.handleException(request, se, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(se.getMessage(), "新增失败")).toString());
		}

		return null;
	}
	
	/**
	 * 跳转到院内中层职务任职信息修改（编辑）页面前的查询回显
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(this.isCancelled(request)) {
			return mapping.findForward("cancel");
		}
		String urlId = request.getParameter("urlId");
        String dutyOid = request.getParameter("dutyOid");

		try {
			if(StringUtils.isEmpty(dutyOid))
			{
				throw new ServiceException(null, "dutyOid is null");
			}
			if(StringUtils.isEmpty(urlId))
			{
				throw new ServiceException(null, "urlId is null");
			}
			
			
			PbDutyInfoDTO pbDutyInfoDTO = pbDutyInfoFacade.get(ObjectUtil.getValue(dutyOid, java.lang.Long.class));
			if (null == pbDutyInfoDTO) {
				throw new ServiceException("error.object.notfound", "pbDutyInfo not found by dutyOid");
			}
			

			
			VerPbPersonInfoDTO verSyPersonDTO = verPbPersonInfoFacade.getVerPersonInfoById(pbDutyInfoDTO.getPersonOid());//获取单位id
			PbDutyInfoForm pbDutyInfoForm = BeanHelper.copyProperties(pbDutyInfoDTO, PbDutyInfoForm.class);
			
			UtOrgDTO utOrgDTO = jhcOrgTreeFacade.findOrgByOid(pbDutyInfoDTO.getDeptOid());//获取内设机构名称
			if(utOrgDTO!=null){
				pbDutyInfoForm.setDeptName(utOrgDTO.getOrgName());
			}else{
				pbDutyInfoForm.setDeptName("");
			}
			
			pbDutyInfoForm.setStartDateStr(DateUtil.format(pbDutyInfoForm.getStartDate(), "yyyy-MM-dd"));
			pbDutyInfoForm.setEndDateStr(DateUtil.format(pbDutyInfoForm.getEndDate(), "yyyy-MM-dd"));
			request.setAttribute("urlId", urlId);
			request.setAttribute("pbDutyInfoForm", pbDutyInfoForm);
            request.setAttribute("dutyOid", dutyOid);
            request.setAttribute("unitOid", verSyPersonDTO.getUnitOid());
		} catch (Exception se) {
			this.handleException(request, se, "dutyOid=" + dutyOid);
			return mapping.getInputForward();
		}
		return goUpdateView(mapping, form, request, response);
	}
	
	/**
	 * 跳转到院内中层职务任职信息修改（编辑）页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goUpdateView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //add jsp init values
//		PbDutyInfoForm pbDutyInfoForm = (PbDutyInfoForm) form;
        return mapping.findForward("success");
    }
	
	/**
	 * 更新院内中层职务任职信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(this.isCancelled(request)) {
			return mapping.findForward("cancel");
		}
        
		String dutyOid = request.getParameter("dutyOid");
		PbDutyInfoForm pbDutyInfoForm = (PbDutyInfoForm) form;
		try
		{
			if (StringUtils.isBlank(dutyOid)) {
				throw new ServiceException(null, "dutyOid is null");
			}
			PbDutyInfoDTO pbDutyInfoDTO = pbDutyInfoFacade.get(NumberUtils.longValue(dutyOid));
			if(null == pbDutyInfoDTO)
			{
				throw new ServiceException(null, "查询不到相关信息");
			}
			BeanHelper.copyProperties(pbDutyInfoForm, pbDutyInfoDTO);
			
			if(StringUtil.isNotBlank(pbDutyInfoForm.getStartDateStr())){
				pbDutyInfoDTO.setStartDate(DateUtil.parseDate(pbDutyInfoForm.getStartDateStr()));
			}
			if(StringUtil.isNotBlank(pbDutyInfoForm.getEndDateStr())){
				pbDutyInfoDTO.setEndDate(DateUtil.parseDate(pbDutyInfoForm.getEndDateStr()));

			}
			pbDutyInfoDTO.setUpdateBy(UserContext.getLoginUserID());
			pbDutyInfoDTO.setUpdateName(UserContext.getLoginUserName());
			pbDutyInfoDTO.setUpdateDate(DateUtil.now());
			pbDutyInfoFacade.update(pbDutyInfoDTO);
			response.getWriter().write(JSONHelper.fromObject(true, null).toString());
		}
		catch(Exception se)
		{
			this.handleException(request, se, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(se.getMessage(), "修改失败")).toString());
		}
		return null;
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String dutyOid = request.getParameter("dutyOid");
     try {
    	 if(StringUtils.isEmpty(dutyOid)){
    		 throw new ServiceException(null, "dutyOid is null");
    	 }
    	 
    	 pbDutyInfoFacade.delete(ObjectUtil.getValue(dutyOid, java.lang.Long.class));
         response.getWriter().write(JSONHelper.fromObject(true, null).toString());
     } catch (Exception se) {
         this.handleException(request, se, "dutyOid=" + dutyOid);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(se.getMessage(), "删除失败")).toString());
     }
     return null;
	}
	
	/**
     * 唯一验证
     * @param pbEducationLevelDegreeForm
     * @throws ServiceException
     * @throws Exception 
     */
	public ActionForward uniqueCheck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取人员id,院内中层职务任职信息id,是否是主要职务
		String isMainDutyInfo = request.getParameter("isMainDutyInfo");
		if(!StringUtil.isBlank(isMainDutyInfo)){
			isMainDutyInfo = isMainDutyInfo.trim();
		}
		String personOid = request.getParameter("personOid");
		String dutyOid = request.getParameter("dutyOid");
		
		String flag = "true";
		
		//根据人员id,获取该人员所有的院内中层职务任职信息
		List<PbDutyInfoDTO> list = pbDutyInfoFacade.listPbDutyInfoByPersonOid(NumberUtils.longValue(personOid));

		for(PbDutyInfoDTO dto: list){
			if(!dto.getDutyOid().toString().equals(dutyOid)){//修改时,跳过当前院内中层职务任职信息id
				
				//判断是否是主要职务
				if(!StringUtil.isBlank(isMainDutyInfo))
				{
					if(!isMainDutyInfo.equals(DicConstants.YHRS0003_0)){//如果为0否,就跳过
						//如果不是是主要专业技术资格
						if(isMainDutyInfo.equals(dto.getIsMainDutyInfo())){
							flag = "false";
						}
					}
				}
			}
		}
		response.getWriter().write(flag);
		return null;
    }
}
