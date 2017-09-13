package com.yh.hr.info.warning.web.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.info.warning.dto.BizWarningInfoDTO;
import com.yh.hr.info.warning.facade.impl.BizWarningInfoFacadeImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.util.CollectionUtils;

import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

/**
 * 预警树-控制器
 * @author	Xing.Liu
 * @created 2011-03-08
 */
public class BizWarningInfoTreeAction extends BaseAction
{
	private BizWarningInfoFacadeImpl bizWarningInfoFacade = (BizWarningInfoFacadeImpl)SpringBeanUtil.getBean("bizWarningInfoFacade");

	/**
	 * 预警菜单树-业务事项列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listBizWarningInfoTree(ActionMapping mapping, 
												ActionForm form, 
												HttpServletRequest request, 
												HttpServletResponse response) throws Exception
		{
			try
			{
				response.setContentType("text/html;charset=GBK");
				request.setCharacterEncoding("GBK");
				JSONArray ary = new JSONArray();
				List<BizWarningInfoDTO> bizWarningInfoDTOs = this.bizWarningInfoFacade.listBizWarningInfoDTOs();
				if (!CollectionUtils.isEmpty(bizWarningInfoDTOs))
				{
					for (BizWarningInfoDTO bizWarningInfoDTO : bizWarningInfoDTOs)
					{
						// build doUrl
						String doUrl = bizWarningInfoDTO.getWarningUrl();
						if(bizWarningInfoDTO.getWarningDays() > 0)
							doUrl = doUrl+ "&warningDays=" + bizWarningInfoDTO.getWarningDays();
						// response
						JSONObject obj = new JSONObject();
						obj.put("id", bizWarningInfoDTO.getItemCode());
						if(bizWarningInfoDTO.getItemCount() > 0)
							obj.put("text", bizWarningInfoDTO.getItemName()+"<font color=red>["+bizWarningInfoDTO.getItemCount()+"]</font>");
						else
							obj.put("text", bizWarningInfoDTO.getItemName()+"<font color=green>["+bizWarningInfoDTO.getItemCount()+"]</font>");
						obj.put("leaf", true);
						obj.put("warningDays", bizWarningInfoDTO.getWarningDays());
						ary.element(obj);
					}
				}else
				{
					JSONObject obj = new JSONObject();
					obj.put("id", "#");
					obj.put("text", "<font color=red>没有数据权限</font>");
					obj.put("leaf", true);
					ary.element(obj);
				}
				log.debug(ary.toString());
				response.getWriter().print(ary);
			}
			catch (Exception e)
			{
				handleException(request, e, "加载业务事项树出错!");
				response.getWriter().print("{'fail':'fail','msg':'" + (GenericValidator.isBlankOrNull(e.getMessage())?"加载业务事项树出错!":e.getMessage()) + "'}");
				return null;
			}
		return null;
	}
}