package com.yh.hr.info.ver.unit.comm.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.info.ver.unit.comm.dto.VerPbFamilyInfoDTO;
import com.yh.hr.info.ver.unit.comm.web.form.VerPbFamilyInfoForm;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.hr.info.ver.unit.comm.facade.VerPbFamilyInfoFacade;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

/**
 * @description PbFamilyInfo Action
 * @author huangyj
 * @created 2016-08-15
 * @version 1.0
 */
public class VerPbFamilyInfoAction extends BaseAction
{
	private VerPbFamilyInfoFacade verPbFamilyInfoFacade = (VerPbFamilyInfoFacade) SpringBeanUtil.getBean("verPbFamilyInfoFacade");
	
	/**
	 * 查看家庭主要成员情况列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listPbFamilyInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String personOid = request.getParameter("personOid");
		try
		{
			if(StringUtils.isEmpty(personOid))
			{
				throw new ServiceException(null, "personOid is null");
			}
			List<VerPbFamilyInfoDTO> verPbFamilyInfoDtoList = verPbFamilyInfoFacade.listPbFamilyInfoByPersonOid(NumberUtils.longValue(personOid));
			List<VerPbFamilyInfoDTO> showList = new ArrayList<VerPbFamilyInfoDTO>();
			if(CollectionUtils.isNotEmpty(verPbFamilyInfoDtoList))
			{
				for(VerPbFamilyInfoDTO verPbFamilyInfoDto : verPbFamilyInfoDtoList)
				{
					VerPbFamilyInfoDTO showDto = new VerPbFamilyInfoDTO();
					BeanHelper.copyProperties(verPbFamilyInfoDto, showDto);
					if(StringUtils.isNotEmpty(verPbFamilyInfoDto.getRelationship()))
					{
						showDto.setRelationship(DicHelper.viewName(DicConstants.YHRS0024, verPbFamilyInfoDto.getRelationship()));
					}
					if(StringUtils.isNotEmpty(verPbFamilyInfoDto.getPoliticsVisage()))
					{
						showDto.setPoliticsVisage(DicHelper.viewName(DicConstants.YHRS0025, verPbFamilyInfoDto.getPoliticsVisage()));
					}
					showList.add(showDto);
				}
			}
			request.setAttribute("list", showList);
		}
		catch(Exception se)
		{
			this.handleException(request, se, null);
			return mapping.getInputForward();
		}
		return mapping.findForward(FORWARD_SUCCESS);
	}
	
	/**
	 * 跳转到家庭主要成员情况新增页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goInsert(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String urlId = request.getParameter("urlId");
		String personOid = request.getParameter("personOid");
		try
		{
			if(StringUtils.isEmpty(urlId))
			{
				throw new ServiceException("urlId is null");
			}
			if(StringUtils.isEmpty(personOid))
			{
				throw new ServiceException("personOid is null");
			}
			request.setAttribute("urlId", urlId);
			request.setAttribute("personOid", personOid);
		}
		catch(Exception se)
		{
			this.handleException(request, se, null);
			return mapping.getInputForward();
		}
		return mapping.findForward(FORWARD_SUCCESS);
	}
	
	/**
	 * 新增家庭主要成员情况
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String personOid = request.getParameter("personOid");
		VerPbFamilyInfoForm verPbFamilyInfoForm = (VerPbFamilyInfoForm) form;
		try
		{
			if(StringUtils.isEmpty(personOid))
			{
				throw new ServiceException(null, "personOid is null");
			}
			VerPbFamilyInfoDTO verPbFamilyInfoDto = BeanHelper.copyProperties(verPbFamilyInfoForm, VerPbFamilyInfoDTO.class);
			verPbFamilyInfoDto.setPersonOid(NumberUtils.longValue(personOid));
			verPbFamilyInfoFacade.createPbFamilyInfo(verPbFamilyInfoDto);
			response.getWriter().write(JSONHelper.fromObject(true, null).toString());
		}
		catch(Exception se)
		{
			this.handleException(request, se, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(se.getMessage(), "修改失败")).toString());
		}
		return null;
	}
	
	/**
	 * 获取家庭主要成员情况
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward show(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String familyOid = request.getParameter("familyOid");
		try
		{
			if(StringUtils.isEmpty(familyOid))
			{
				throw new ServiceException(null, "familyOid is null");
			}
			VerPbFamilyInfoDTO verPbFamilyInfoDto = verPbFamilyInfoFacade.findPbFamilyInfo(NumberUtils.longValue(familyOid));
			if(null == verPbFamilyInfoDto)
			{
				throw new ServiceException(null, "查询不到相关信息");
			}
			VerPbFamilyInfoForm verPbFamilyInfoForm = BeanHelper.copyProperties(verPbFamilyInfoDto, VerPbFamilyInfoForm.class);
			request.setAttribute("verPbFamilyInfoForm", verPbFamilyInfoForm);
		}
		catch(Exception se)
		{
			this.handleException(request, se, null);
			return mapping.getInputForward();
		}
		return mapping.findForward(FORWARD_SUCCESS);
	}
	
	/**
	 * 跳转到家庭主要成员情况修改页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String familyOid = request.getParameter("familyOid");
		String urlId = request.getParameter("urlId");
		try
		{
			if(StringUtils.isEmpty(familyOid))
			{
				throw new ServiceException(null, "familyOid is null");
			}
			if(StringUtils.isEmpty(urlId))
			{
				throw new ServiceException("urlId is null");
			}
			VerPbFamilyInfoDTO verPbFamilyInfoDto = verPbFamilyInfoFacade.findPbFamilyInfo(NumberUtils.longValue(familyOid));
			if(null != verPbFamilyInfoDto)
			{
				VerPbFamilyInfoForm verPbFamilyInfoForm = BeanHelper.copyProperties(verPbFamilyInfoDto, VerPbFamilyInfoForm.class);
				request.setAttribute("verPbFamilyInfoForm", verPbFamilyInfoForm);
				request.setAttribute("urlId", urlId);
			}
			else
			{
				throw new ServiceException(null, "查询不到相关信息");
			}
		}
		catch(Exception se)
		{
			this.handleException(request, se, null);
			return mapping.getInputForward();
		}
		return mapping.findForward(FORWARD_SUCCESS);
	}
	
	/**
	 * 修改家庭主要成员情况
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String familyOid = request.getParameter("familyOid");
		String personOid = request.getParameter("personOid");
		VerPbFamilyInfoForm verPbFamilyInfoForm = (VerPbFamilyInfoForm) form;
		try
		{
			if(StringUtils.isEmpty(familyOid))
			{
				throw new ServiceException(null, "familyOid is null");
			}
			if(StringUtils.isEmpty(personOid))
			{
				throw new ServiceException(null, "personOid is null");
			}
			VerPbFamilyInfoDTO verPbFamilyInfoDto = verPbFamilyInfoFacade.findPbFamilyInfo(NumberUtils.longValue(familyOid));
			if(null == verPbFamilyInfoDto)
			{
				throw new ServiceException(null, "查询不到相关信息");
			}
			BeanHelper.copyProperties(verPbFamilyInfoForm, verPbFamilyInfoDto);
			verPbFamilyInfoDto.setPersonOid(NumberUtils.longValue(personOid));
			verPbFamilyInfoFacade.updatePbFamilyInfo(verPbFamilyInfoDto);
			response.getWriter().write(JSONHelper.fromObject(true, null).toString());
		}
		catch(Exception se)
		{
			this.handleException(request, se, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(se.getMessage(), "修改失败")).toString());
		}
		return null;
	}
	
	/**
	 * 删除家庭主要成员情况
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String familyOid = request.getParameter("familyOid");
		try
		{
			if(StringUtils.isEmpty(familyOid))
			{
				throw new ServiceException(null, "familyOid is null");
			}
			verPbFamilyInfoFacade.deletePbFamilyInfo(NumberUtils.longValue(familyOid));
			response.getWriter().write(JSONHelper.fromObject(true, null).toString());
		}
		catch(Exception se)
		{
			this.handleException(request, se, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(se.getMessage(), "删除失败")).toString());
		}
		return null;
	}
}
