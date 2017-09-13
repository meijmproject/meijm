package com.yh.hr.info.ver.unit.comm.web.action;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.hr.info.ver.unit.comm.dto.VerPbVacationInfoDTO;
import com.yh.hr.info.ver.unit.comm.facade.VerVacationInfoFacade;
import com.yh.hr.info.ver.unit.comm.web.form.VerPbVacationInfoForm;
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
 * 人员请休假信息 action
 * @author renp
 * @created 2017-04-07
 * @version 1.0
 */
public class VerVacationInfoAction extends BaseAction
{
	private VerVacationInfoFacade verVacationInfoFacade = (VerVacationInfoFacade) SpringBeanUtil.getBean("verVacationInfoFacade");
	
	/**
	 * 查看人员请休假信息列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listPbVacation(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String personOid = request.getParameter("personOid");
		try
		{
			if(StringUtils.isEmpty(personOid))
			{
				throw new ServiceException(null, "personOid is null");
			}
			List<VerPbVacationInfoDTO> verPbVacationInfoDTOList = verVacationInfoFacade.listPbVacationInfoByPersonOid(NumberUtils.longValue(personOid));
			List<VerPbVacationInfoForm> showList = new ArrayList<VerPbVacationInfoForm>();
			if(CollectionUtils.isNotEmpty(verPbVacationInfoDTOList))
			{
				for(VerPbVacationInfoDTO verPbVacationInfoDTO : verPbVacationInfoDTOList)
				{
					VerPbVacationInfoForm showDto = new VerPbVacationInfoForm();
					BeanHelper.copyProperties(verPbVacationInfoDTO, showDto);
					if(StringUtils.isNotEmpty(verPbVacationInfoDTO.getVacationType()))
					{
						showDto.setVacationType(DicHelper.viewName(DicConstants.YHRS0130, verPbVacationInfoDTO.getVacationType()));
					}
					if(verPbVacationInfoDTO.getStartDate()!=null)
					{
						showDto.setStartDateStr(DateUtil.format(verPbVacationInfoDTO.getStartDate(), "yyyy-MM-dd"));
					}
					if(verPbVacationInfoDTO.getEndDate()!=null)
					{
						showDto.setEndDateStr(DateUtil.format(verPbVacationInfoDTO.getEndDate(), "yyyy-MM-dd"));
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
	 * 获取人员请休假信息
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
		String vacationOid = request.getParameter("vacationOid");
		try
		{
			if(StringUtils.isEmpty(vacationOid))
			{
				throw new ServiceException(null, "vacationOid is null");
			}
			VerPbVacationInfoDTO verPbVacationInfoDTO = verVacationInfoFacade.findPbVacationInfo(NumberUtils.longValue(vacationOid));
			if(null == verPbVacationInfoDTO)
			{
				throw new ServiceException(null, "查询不到相关信息");
			}
			if(verPbVacationInfoDTO.getStartDate()!=null)
			{
				verPbVacationInfoDTO.setStartDateStr(DateUtil.format(verPbVacationInfoDTO.getStartDate(), "yyyy-MM-dd"));
			}
			if(verPbVacationInfoDTO.getEndDate()!=null)
			{
				verPbVacationInfoDTO.setEndDateStr(DateUtil.format(verPbVacationInfoDTO.getEndDate(), "yyyy-MM-dd"));
			}
			if(verPbVacationInfoDTO.getStatutoryHolidayDays()==null){
				verPbVacationInfoDTO.setStatutoryHolidayDays(0.0);
			}
			VerPbVacationInfoForm verPbVacationInfoForm = BeanHelper.copyProperties(verPbVacationInfoDTO, VerPbVacationInfoForm.class);
			request.setAttribute("verPbVacationInfoForm", verPbVacationInfoForm);
		}
		catch(Exception se)
		{
			this.handleException(request, se, null);
			return mapping.getInputForward();
		}
		return mapping.findForward(FORWARD_SUCCESS);
	}

}
