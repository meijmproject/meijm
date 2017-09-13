package com.yh.hr.info.view.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.info.ver.unit.comm.dto.VerPbFamilyInfoDTO;
import com.yh.hr.info.ver.unit.comm.web.form.VerPbFamilyInfoForm;
import com.yh.hr.info.view.facade.ViewPbFamilyInfoFacade;
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
 * @description PbFamilyInfo Action View
 * @author huangyj
 * @created 2016-08-15
 * @version 1.0
 */
public class ViewPbFamilyInfoAction extends BaseAction
{
	private ViewPbFamilyInfoFacade viewPbFamilyInfoFacade = (ViewPbFamilyInfoFacade) SpringBeanUtil.getBean("viewPbFamilyInfoFacade");
	
	/**
	 * 查看家庭成员与社会关系列表
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
			List<VerPbFamilyInfoDTO> verPbFamilyInfoDtoList = viewPbFamilyInfoFacade.listPbFamilyInfoByPersonOid(NumberUtils.longValue(personOid));
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
	 * 获取家庭成员与社会关系
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
			VerPbFamilyInfoDTO verPbFamilyInfoDto = viewPbFamilyInfoFacade.findPbFamilyInfo(NumberUtils.longValue(familyOid));
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

}
