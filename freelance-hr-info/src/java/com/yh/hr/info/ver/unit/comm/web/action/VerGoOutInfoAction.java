package com.yh.hr.info.ver.unit.comm.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.info.ver.unit.comm.facade.VerGoOutInfoFacade;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.hr.info.ver.unit.comm.dto.VerGoOutInfoDTO;
import com.yh.hr.info.ver.unit.comm.web.form.VerGoOutInfoForm;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

/**
 * 人员外出信息 action 
 * @author chenjl
 * @created 2017-04-06
 * @version 1.0
 */
public class VerGoOutInfoAction extends BaseAction
{
	private VerGoOutInfoFacade verGoOutInfoFacade = (VerGoOutInfoFacade) SpringBeanUtil.getBean("verGoOutInfoFacade");
	
	/**
	 * 查看人员外出信息列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listPbGoOutInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String personOid = request.getParameter("personOid");
		try
		{
			if(StringUtils.isEmpty(personOid))
			{
				throw new ServiceException(null, "personOid is null");
			}
			List<VerGoOutInfoDTO> verPbGoOutInfoDtoList = verGoOutInfoFacade.listPbGoOutInfoByPersonOid(NumberUtils.longValue(personOid));
			List<VerGoOutInfoForm> showList = new ArrayList<VerGoOutInfoForm>();
			if(CollectionUtils.isNotEmpty(verPbGoOutInfoDtoList))
			{
				for(VerGoOutInfoDTO verPbGoOutInfoDto : verPbGoOutInfoDtoList)
				{
					VerGoOutInfoForm showDto = new VerGoOutInfoForm();
					BeanHelper.copyProperties(verPbGoOutInfoDto, showDto);
					if(StringUtils.isNotEmpty(verPbGoOutInfoDto.getGoOutType()))
					{
						showDto.setGoOutType(DicHelper.viewName(DicConstants.YHRS0129, verPbGoOutInfoDto.getGoOutType()));
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
	 * 获取人员外出信息
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
		String goOutOid = request.getParameter("goOutOid");
		try
		{
			if(StringUtils.isEmpty(goOutOid))
			{
				throw new ServiceException(null, "goOutOid is null");
			}
			VerGoOutInfoDTO verPbGoOutInfoDto = verGoOutInfoFacade.findPbGoOutInfo(NumberUtils.longValue(goOutOid));
			if(null == verPbGoOutInfoDto)
			{
				throw new ServiceException(null, "查询不到相关信息");
			}
			VerGoOutInfoForm verGoOutInfoForm = BeanHelper.copyProperties(verPbGoOutInfoDto, VerGoOutInfoForm.class);
			request.setAttribute("verGoOutInfoForm", verGoOutInfoForm);
		}
		catch(Exception se)
		{
			this.handleException(request, se, null);
			return mapping.getInputForward();
		}
		return mapping.findForward(FORWARD_SUCCESS);
	}

}
