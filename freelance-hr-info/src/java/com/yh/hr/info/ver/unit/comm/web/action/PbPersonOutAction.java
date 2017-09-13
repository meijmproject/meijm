package com.yh.hr.info.ver.unit.comm.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.info.ver.unit.comm.facade.PbPersonOutFacade;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.hr.info.ver.unit.comm.dto.VerPbPersonOutDTO;
import com.yh.hr.info.ver.unit.comm.web.form.PbPersonOutForm;
import com.yh.hr.res.pb.dto.PbPersonOutDTO;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

/**
 * @description PbPersonOut Action
 * @author cheny
 * @created 2017-03-20
 * @version 1.0
 */
public class PbPersonOutAction extends BaseAction
{
private PbPersonOutFacade pbPersonOutFacade = (PbPersonOutFacade) SpringBeanUtil.getBean("pbPersonOutFacade");
	
	/**
	 * 查看人员进入信息列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listPbPersonOut(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String personOid = request.getParameter("personOid");
		try
		{
			if(StringUtils.isEmpty(personOid))
			{
				throw new ServiceException(null, "personOid is null");
			}
			List<VerPbPersonOutDTO> verPbPersonOutDTOList = pbPersonOutFacade.listPbPersonOutByPersonOid(NumberUtils.longValue(personOid));
			List<PbPersonOutDTO> showList = new ArrayList<PbPersonOutDTO>();
			if(CollectionUtils.isNotEmpty(verPbPersonOutDTOList))
			{
				for(VerPbPersonOutDTO verPbPersonOutDTO : verPbPersonOutDTOList)
				{
					PbPersonOutDTO showDto = new PbPersonOutDTO();
					BeanHelper.copyProperties(verPbPersonOutDTO, showDto);
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
	 * 跳转到人员进入信息新增页面
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
	 * 新增人员进入信息
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
		PbPersonOutForm pbPersonOutForm = (PbPersonOutForm) form;
		try
		{
			if(StringUtils.isEmpty(personOid))
			{
				throw new ServiceException(null, "personOid is null");
			}
			VerPbPersonOutDTO verPbPersonOutDTO = BeanHelper.copyProperties(pbPersonOutForm, VerPbPersonOutDTO.class);
			verPbPersonOutDTO.setPersonOid(NumberUtils.longValue(personOid));
			pbPersonOutFacade.createPbPersonOut(verPbPersonOutDTO);
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
	 * 获取人员进入信息
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
		String personOutOid = request.getParameter("personOutOid");
		try
		{
			if(StringUtils.isEmpty(personOutOid))
			{
				throw new ServiceException(null, "personOutOid is null");
			}
			VerPbPersonOutDTO verPbPersonOutDTO = pbPersonOutFacade.findPbPersonOut(NumberUtils.longValue(personOutOid));
			if(null == verPbPersonOutDTO)
			{
				throw new ServiceException(null, "查询不到相关信息");
			}
			PbPersonOutForm pbPersonOutForm = BeanHelper.copyProperties(verPbPersonOutDTO, PbPersonOutForm.class);
			request.setAttribute("pbPersonOutForm", pbPersonOutForm);
		}
		catch(Exception se)
		{
			this.handleException(request, se, null);
			return mapping.getInputForward();
		}
		return mapping.findForward(FORWARD_SUCCESS);
	}
	
	/**
	 * 跳转到人员进入信息修改页面
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
		String personOutOid = request.getParameter("personOutOid");
		String urlId = request.getParameter("urlId");
		try
		{
			if(StringUtils.isEmpty(personOutOid))
			{
				throw new ServiceException(null, "personOutOid is null");
			}
			if(StringUtils.isEmpty(urlId))
			{
				throw new ServiceException("urlId is null");
			}
			VerPbPersonOutDTO verPbPersonOutDTO = pbPersonOutFacade.findPbPersonOut(NumberUtils.longValue(personOutOid));
			if(null != verPbPersonOutDTO)
			{
				PbPersonOutForm pbPersonOutForm = BeanHelper.copyProperties(verPbPersonOutDTO, PbPersonOutForm.class);
				request.setAttribute("pbPersonOutForm", pbPersonOutForm);
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
	 * 修改人员进入信息
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
		String personOutOid = request.getParameter("personOutOid");
		String personOid = request.getParameter("personOid");
		PbPersonOutForm pbPersonOutForm = (PbPersonOutForm) form;
		try
		{
			if(StringUtils.isEmpty(personOutOid))
			{
				throw new ServiceException(null, "personOutOid is null");
			}
			if(StringUtils.isEmpty(personOid))
			{
				throw new ServiceException(null, "personOid is null");
			}
			VerPbPersonOutDTO verPbPersonOutDTO = pbPersonOutFacade.findPbPersonOut(NumberUtils.longValue(personOutOid));
			if(null == verPbPersonOutDTO)
			{
				throw new ServiceException(null, "查询不到相关信息");
			}
			BeanHelper.copyProperties(pbPersonOutForm, verPbPersonOutDTO);
			verPbPersonOutDTO.setPersonOid(NumberUtils.longValue(personOid));
			pbPersonOutFacade.updatePbPersonOut(verPbPersonOutDTO);
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
	 * 删除人员进入信息
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
		String personOutOid = request.getParameter("personOutOid");
		try
		{
			if(StringUtils.isEmpty(personOutOid))
			{
				throw new ServiceException(null, "personOutOid is null");
			}
			pbPersonOutFacade.deletePbPersonOut(NumberUtils.longValue(personOutOid));
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
