package com.yh.hr.info.warning.web.action;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.info.warning.dto.BizWarningInfoDTO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.util.CollectionUtils;

import com.yh.hr.info.warning.facade.impl.BizWarningInfoFacadeImpl;
import com.yh.hr.info.warning.util.BizWarningConstants;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

/*
 * @author	zxh
 * @created 2012-05-24
 */
public class BizWarningOutDaysInfoAction extends BaseAction
{
	private BizWarningInfoFacadeImpl bizWarningInfoFacade = (BizWarningInfoFacadeImpl)SpringBeanUtil.getBean("bizWarningInfoFacade");

	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public ActionForward goBizWarningOutDaysInfo(ActionMapping mapping, 
													   		    ActionForm form,
											   				    HttpServletRequest request, 
											   				    HttpServletResponse response) throws Exception
	{
		request.setAttribute("BIZ_WARNING_YJPRZSYQ", BizWarningConstants.BIZ_WARNING_YJPRZSYQ);
		request.setAttribute("BIZ_WARNING_YJLTX", BizWarningConstants.BIZ_WARNING_YJLTX);
		return mapping.findForward(this.FORWARD_SUCCESS);
	}	
	/**
	 * 业务事项列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listBizWarningDays(ActionMapping mapping, 
												ActionForm form, 
												HttpServletRequest request, 
												HttpServletResponse response) throws Exception
		{
			try
			{
				response.setContentType("text/html;charset=GBK");
				request.setCharacterEncoding("GBK");
				JSONArray ary = new JSONArray();
				JSONObject object = new JSONObject();
				List<BizWarningInfoDTO> bizWarningInfoDTOs = this.bizWarningInfoFacade.listBizWarningInfoDTOs();
				if (!CollectionUtils.isEmpty(bizWarningInfoDTOs))
				{
					for (BizWarningInfoDTO bizWarningInfoDTO : bizWarningInfoDTOs)
					{
						if(!bizWarningInfoDTO.getItemCode().equals("SETDAYS"))
						{
							JSONObject obj = new JSONObject();
							obj.put("bizWarningInfoOid", bizWarningInfoDTO.getBizWarningInfoOid().toString());
							obj.put("itemCode", bizWarningInfoDTO.getItemCode());
							obj.put("itemName", bizWarningInfoDTO.getItemName());
							obj.put("warningDays", bizWarningInfoDTO.getWarningDays());
							ary.element(obj);
						}
					}
				}
				object.put("rs", ary);
				response.setCharacterEncoding("gbk");
				response.getWriter().print(object.toString());
			}
			catch (Exception e)
			{
				handleException(request, e, "加载出错!");
				response.getWriter().print("{'fail':'fail','msg':'" + (GenericValidator.isBlankOrNull(e.getMessage())?"加载出错!":e.getMessage()) + "'}");
				return null;
			}
		return null;
	}
	public  void updateOutDays(BizWarningInfoDTO dto)
	{
		try
		{
			this.bizWarningInfoFacade.updateWarningDays(dto);
		}
		catch(ServiceException e)
		{
			e.fillInStackTrace();
		}
	}
	@SuppressWarnings("static-access")
	public ActionForward updateWarningDays(ActionMapping mapping, 
   		    ActionForm form,
			    HttpServletRequest request, 
			    HttpServletResponse response) throws Exception
	{
		response.setContentType("text/html;charset=GBK");
		request.setCharacterEncoding("GBK");
	    String data = request.getParameter("data");
	    JSONArray array = new JSONArray();
	    array=array.fromObject(data);
	    ArrayList<BizWarningInfoDTO> list = new ArrayList<BizWarningInfoDTO>();
	    for(int i=0;i<array.size();i++)
	    {
	    	BizWarningInfoDTO dto = new BizWarningInfoDTO();
	    	JSONObject t= (JSONObject)array.get(i);
	    	dto.setBizWarningInfoOid(Long.valueOf(t.getInt("bizWarningInfoOid")));
	    	dto.setWarningDays(t.getInt("warningDays"));
	    	list.add(dto);
	    }
	    for(BizWarningInfoDTO dto:list)
	    {
	    	this.updateOutDays(dto);
	    }
		return mapping.findForward(this.FORWARD_SUCCESS);
	}
}