package com.yh.hr.info.ver.unit.comm.web.action;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.hr.info.ver.unit.comm.dto.VerChangeOffInfoDTO;
import com.yh.hr.info.ver.unit.comm.facade.VerChangeOffInfoFacade;
import com.yh.hr.info.ver.unit.comm.web.form.VerChangeOffInfoForm;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 人员调休信息 action
 * @author renp
 * @created 2017-04-07
 * @version 1.0
 */
public class VerChangeOffInfoAction extends BaseAction
{
	private VerChangeOffInfoFacade verChangeOffInfoFacade = (VerChangeOffInfoFacade) SpringBeanUtil.getBean("verChangeOffInfoFacade");
	
	/**
	 * 查看人员调休信息列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listPbChangeOffInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String personOid = request.getParameter("personOid");
		try
		{
			if(StringUtils.isEmpty(personOid))
			{
				throw new ServiceException(null, "personOid is null");
			}
			List<VerChangeOffInfoDTO> verChangeOffInfoDTOList = verChangeOffInfoFacade.listPbChangeOffInfoByPersonOid(NumberUtils.longValue(personOid));
			List<VerChangeOffInfoForm> showList = new ArrayList<VerChangeOffInfoForm>();
			if(CollectionUtils.isNotEmpty(verChangeOffInfoDTOList))
			{
				for(VerChangeOffInfoDTO verChangeOffInfoDTO : verChangeOffInfoDTOList)
				{
					VerChangeOffInfoForm showDto = new VerChangeOffInfoForm();
					BeanHelper.copyProperties(verChangeOffInfoDTO, showDto);
					if(StringUtils.isNotEmpty(verChangeOffInfoDTO.getChangeOffType()))
					{
						showDto.setChangeOffType(DicHelper.viewName(DicConstants.YHRS0131, verChangeOffInfoDTO.getChangeOffType()));
					}
					if(verChangeOffInfoDTO.getStartDate()!=null)
					{
						showDto.setStartDateStr(DateUtil.format(verChangeOffInfoDTO.getStartDate(), "yyyy-MM-dd"));
					}
					if(verChangeOffInfoDTO.getEndDate()!=null)
					{
						showDto.setEndDateStr(DateUtil.format(verChangeOffInfoDTO.getEndDate(), "yyyy-MM-dd"));
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
	 * 获取人员调休信息
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
		String changeOffOid = request.getParameter("changeOffOid");
		try
		{
			if(StringUtils.isEmpty(changeOffOid))
			{
				throw new ServiceException(null, "changeOffOid is null");
			}
			VerChangeOffInfoDTO verChangeOffInfoDTO = verChangeOffInfoFacade.findPbChangeOffInfo(NumberUtils.longValue(changeOffOid));
			if(null == verChangeOffInfoDTO)
			{
				throw new ServiceException(null, "查询不到相关信息");
			}
			if(verChangeOffInfoDTO.getStartDate()!=null)
			{
				verChangeOffInfoDTO.setStartDateStr(DateUtil.format(verChangeOffInfoDTO.getStartDate(), "yyyy-MM-dd"));
			}
			if(verChangeOffInfoDTO.getEndDate()!=null)
			{
				verChangeOffInfoDTO.setEndDateStr(DateUtil.format(verChangeOffInfoDTO.getEndDate(), "yyyy-MM-dd"));
			}
			VerChangeOffInfoForm verChangeOffInfoForm = BeanHelper.copyProperties(verChangeOffInfoDTO, VerChangeOffInfoForm.class);
			request.setAttribute("verChangeOffInfoForm", verChangeOffInfoForm);
		}
		catch(Exception se)
		{
			this.handleException(request, se, null);
			return mapping.getInputForward();
		}
		return mapping.findForward(FORWARD_SUCCESS);
	}

}
