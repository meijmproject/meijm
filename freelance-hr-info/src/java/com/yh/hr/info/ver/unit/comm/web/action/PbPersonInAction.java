package com.yh.hr.info.ver.unit.comm.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.info.ver.unit.comm.dto.VerPbPersonInDTO;
import com.yh.hr.info.ver.unit.comm.facade.PbPersonInFacade;
import com.yh.hr.info.ver.unit.comm.web.form.PbPersonInForm;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.hr.res.pb.dto.PbPersonInDTO;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;
import com.yh.platform.core.web.action.BaseAction;

/**
 * @description PbPersonIn Action
 * @author cheny
 * @created 2017-03-20
 * @version 1.0
 */
public class PbPersonInAction extends BaseAction
{
	private PbPersonInFacade pbPersonInFacade = (PbPersonInFacade) SpringBeanUtil.getBean("pbPersonInFacade");
	
	/**
	 * 查看人员进入信息列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listPbPersonIn(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String personOid = request.getParameter("personOid");
		try
		{
			if(StringUtils.isEmpty(personOid))
			{
				throw new ServiceException(null, "personOid is null");
			}
			List<VerPbPersonInDTO> verPbPersonInDTOList = pbPersonInFacade.listPbPersonInByPersonOid(NumberUtils.longValue(personOid));
			List<PbPersonInDTO> showList = new ArrayList<PbPersonInDTO>();
			if(CollectionUtils.isNotEmpty(verPbPersonInDTOList))
			{
				for(VerPbPersonInDTO verPbPersonInDTO : verPbPersonInDTOList)
				{
					PbPersonInDTO showDto = new PbPersonInDTO();
					BeanHelper.copyProperties(verPbPersonInDTO, showDto);
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
		PbPersonInForm pbPersonInForm = (PbPersonInForm) form;
		try
		{
			if(StringUtils.isEmpty(personOid))
			{
				throw new ServiceException(null, "personOid is null");
			}
			VerPbPersonInDTO verPbPersonInDTO = BeanHelper.copyProperties(pbPersonInForm, VerPbPersonInDTO.class);
			verPbPersonInDTO.setPersonOid(NumberUtils.longValue(personOid));
			verPbPersonInDTO.setCreateBy(UserContext.getLoginUserID());
			verPbPersonInDTO.setCreateName(UserContext.getLoginUserName());
			verPbPersonInDTO.setCreateDate(DateUtil.now());
			pbPersonInFacade.createPbPersonIn(verPbPersonInDTO);
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
		String personInOid = request.getParameter("personInOid");
		try
		{
			if(StringUtils.isEmpty(personInOid))
			{
				throw new ServiceException(null, "personInOid is null");
			}
			VerPbPersonInDTO verPbPersonInDTO = pbPersonInFacade.findPbPersonIn(NumberUtils.longValue(personInOid));
			if(null == verPbPersonInDTO)
			{
				throw new ServiceException(null, "查询不到相关信息");
			}
			PbPersonInForm pbPersonInForm = BeanHelper.copyProperties(verPbPersonInDTO, PbPersonInForm.class);
			request.setAttribute("pbPersonInForm", pbPersonInForm);
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
		String personInOid = request.getParameter("personInOid");
		String urlId = request.getParameter("urlId");
		try
		{
			if(StringUtils.isEmpty(personInOid))
			{
				throw new ServiceException(null, "personInOid is null");
			}
			if(StringUtils.isEmpty(urlId))
			{
				throw new ServiceException("urlId is null");
			}
			VerPbPersonInDTO verPbPersonInDTO = pbPersonInFacade.findPbPersonIn(NumberUtils.longValue(personInOid));
			if(null != verPbPersonInDTO)
			{
				PbPersonInForm pbPersonInForm = BeanHelper.copyProperties(verPbPersonInDTO, PbPersonInForm.class);
				request.setAttribute("pbPersonInForm", pbPersonInForm);
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
		String personInOid = request.getParameter("personInOid");
		String personOid = request.getParameter("personOid");
		PbPersonInForm pbPersonInForm = (PbPersonInForm) form;
		try
		{
			if(StringUtils.isEmpty(personInOid))
			{
				throw new ServiceException(null, "personInOid is null");
			}
			if(StringUtils.isEmpty(personOid))
			{
				throw new ServiceException(null, "personOid is null");
			}
			VerPbPersonInDTO verPbPersonInDTO = pbPersonInFacade.findPbPersonIn(NumberUtils.longValue(personInOid));
			if(null == verPbPersonInDTO)
			{
				throw new ServiceException(null, "查询不到相关信息");
			}
			BeanHelper.copyProperties(pbPersonInForm, verPbPersonInDTO);
			verPbPersonInDTO.setPersonOid(NumberUtils.longValue(personOid));
			pbPersonInFacade.updatePbPersonIn(verPbPersonInDTO);
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
		String personInOid = request.getParameter("personInOid");
		try
		{
			if(StringUtils.isEmpty(personInOid))
			{
				throw new ServiceException(null, "personInOid is null");
			}
			pbPersonInFacade.deletePbPersonIn(NumberUtils.longValue(personInOid));
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
