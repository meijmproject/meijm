package com.yh.hr.info.ver.unit.comm.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.info.ver.unit.comm.dto.VerOverTimeInfoDTO;
import com.yh.hr.info.ver.unit.comm.facade.VerOverTimeInfoFacade;
import com.yh.hr.info.ver.unit.comm.web.form.VerOverTimeInfoForm;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

/**
 * 人员加班信息 action 
 * @author chenjl
 * @created 2017-04-06
 * @version 1.0
 */
public class VerOverTimeInfoAction extends BaseAction
{
	private VerOverTimeInfoFacade verOverTimeInfoFacade = (VerOverTimeInfoFacade) SpringBeanUtil.getBean("verOverTimeInfoFacade");
	
	/**
	 * 查看人员外出信息列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listPbOvertimeInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String personOid = request.getParameter("personOid");
		try
		{
			if(StringUtils.isEmpty(personOid))
			{
				throw new ServiceException(null, "personOid is null");
			}
			List<VerOverTimeInfoDTO> verPbOverTimeInfoDtoList = verOverTimeInfoFacade.listPbOverTimeInfoByPersonOid(NumberUtils.longValue(personOid));
			List<VerOverTimeInfoForm> showList = new ArrayList<VerOverTimeInfoForm>();
			if(CollectionUtils.isNotEmpty(verPbOverTimeInfoDtoList))
			{
				for(VerOverTimeInfoDTO verPbOverTimeInfoDto : verPbOverTimeInfoDtoList)
				{
					VerOverTimeInfoForm showDto = new VerOverTimeInfoForm();
					BeanHelper.copyProperties(verPbOverTimeInfoDto, showDto);
					if(StringUtils.isNotEmpty(verPbOverTimeInfoDto.getOvertimeType()))
					{
						showDto.setOvertimeType(DicHelper.viewName(DicConstants.YHRS0132, verPbOverTimeInfoDto.getOvertimeType()));
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
		String overtimeOid = request.getParameter("overtimeOid");
		try
		{
			if(StringUtils.isEmpty(overtimeOid))
			{
				throw new ServiceException(null, "overtimeOid is null");
			}
			VerOverTimeInfoDTO verPbOverTimeInfoDto = verOverTimeInfoFacade.findPbOverTimeInfo(NumberUtils.longValue(overtimeOid));
			if(null == verPbOverTimeInfoDto)
			{
				throw new ServiceException(null, "查询不到相关信息");
			}
			VerOverTimeInfoForm verOverTimeInfoForm = BeanHelper.copyProperties(verPbOverTimeInfoDto, VerOverTimeInfoForm.class);
			request.setAttribute("verOverTimeInfoForm", verOverTimeInfoForm);
		}
		catch(Exception se)
		{
			this.handleException(request, se, null);
			return mapping.getInputForward();
		}
		return mapping.findForward(FORWARD_SUCCESS);
	}

}
