package com.yh.hr.info.warning.web.action;


import com.yh.platform.core.web.action.BaseAction;

/*
 * @author	liuhw
 * @created 2014-03-31
 */
public class BizProbationRuleAction extends BaseAction
{
	/*private BizProbationRuleFacadeImpl bizProbationRuleFacade = (BizProbationRuleFacadeImpl)SpringBeanUtil.getBean("bizProbationRuleFacade");
	
	*//**
	 * 跳转聘任制试用期到期预警页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	public ActionForward goModfiyProbation(ActionMapping mapping, 
													   		    ActionForm form,
											   				    HttpServletRequest request, 
											   				    HttpServletResponse response) throws Exception
	{
		response.setContentType("text/html;charset=GBK");
		request.setCharacterEncoding("GBK");
		log.debug("---------------进入BizProbationRuleAction的goModfiyProbation方法-----------------------");
		String itemCode=request.getParameter("itemCode");
		request.setAttribute("itemCode", itemCode);
		return mapping.findForward(this.FORWARD_SUCCESS);
	}
	
	*//**
	 * 跳转聘任制试用期到期预警页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	public ActionForward listProbationRule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		response.setContentType("text/html;charset=GBK");
		request.setCharacterEncoding("GBK");
		log.debug("---------------进入BizProbationRuleAction的listProbationRule方法-----------------------");
		try
		{
			TableTagBean ttb = TableTagBean.getFromRequest(request);
			JSONObject obj = new JSONObject();
			JSONArray ary = new JSONArray();
			List<BizFullProbationRuleDTO> list = bizProbationRuleFacade.listProbationRule(ttb);
			if (!CollectionUtils.isEmpty(list))
			{
				for (BizFullProbationRuleDTO dto : list)
				{
					JSONObject obj1 = new JSONObject();
					obj1.put("bizFullProbationOid", dto.getBizFullProbationOid());
					obj1.put("createdBy", dto.getCreatedBy());
					obj1.put("createdTsStr", DateUtil.format(dto.getCreatedTs(),DateUtil.TIME_PATTERN_DEFAULT));
					obj1.put("itemCode", dto.getItemCode());
					obj1.put("personType", DicParameter.viewName(DicParameter.SZRS0014,dto.getPersonType()));
					obj1.put("probationDays", dto.getProbationDays());
					obj1.put("updatedBy", dto.getUpdatedBy());
					obj1.put("updatedTsStr", DateUtil.format(dto.getUpdatedTs(),DateUtil.TIME_PATTERN_DEFAULT));
					ary.element(obj1);
				}
			}
			obj.put("cs", ttb.getTotal());
			obj.put("rs", ary);
			log.debug(ary.toString());
			response.getWriter().print(obj);
		}
		catch (Exception e)
		{
			handleException(request, e, "加载聘任制试用期到期预警规则出错!");
			response.getWriter().print("{'fail':'fail','msg':'" + (GenericValidator.isBlankOrNull(e.getMessage())?"加载基本条件出错!":e.getMessage()) + "'}");
			return null;
		}
		return null;
	}
	*//**
	 * 跳转聘任制试用期到期预警新增页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	public ActionForward goCreateProbationRule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		response.setContentType("text/html;charset=GBK");
		request.setCharacterEncoding("GBK");
		log.debug("---------------进入BizProbationRuleAction的goCreateProbationRule方法-----------------------");
		String itemCode = request.getParameter("itemCode");
		request.setAttribute("itemCode", itemCode);
		request.setAttribute("IS_TRUE", Constants.IS_TRUE);
		request.setAttribute("personTypeCode", DicParameter.SZRS0014);
		return mapping.findForward(this.FORWARD_SUCCESS);
	}
	
	*//**
	 * 新增聘任制试用期到期预警规则
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	public ActionForward createProbationRule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		response.setContentType("text/html;charset=GBK");
		request.setCharacterEncoding("GBK");
		log.debug("---------------进入BizProbationRuleAction的createProbationRule方法-----------------------");
		try
		{
			BizFullProbationRuleForm bizFullProbationRuleForm = (BizFullProbationRuleForm) form;
			BizFullProbationRuleDTO bizFullProbationRuleDTO = new BizFullProbationRuleDTO();
			BeanUtils.copyProperties(bizFullProbationRuleDTO, bizFullProbationRuleForm);
			//检查人员类别是否重复
			List<BizFullProbationRuleDTO> list = bizProbationRuleFacade.findPersonType(bizFullProbationRuleDTO.getPersonType(),bizFullProbationRuleDTO.getBizFullProbationOid());
			if(!CollectionUtils.isEmpty(list))
			{
				throw new ServiceException("error.warning.persontype","该规则已存在请重新录入!（人员类别已存在）");
			}
			bizProbationRuleFacade.createProbationRule(bizFullProbationRuleDTO);
			request.setAttribute("IS_TRUE", Constants.IS_TRUE);
			request.setAttribute("okCreate", BizWarningConstants.IS_TRUE);
		}
		catch (Exception se)
		{
			handleException(request, se, "save enterCadreInfo failed");
			return mapping.getInputForward();
		}

		return mapping.findForward(this.FORWARD_SUCCESS);
	}
	*//**
	 * 跳转聘任制试用期到期预警规则修改页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	public ActionForward goUpdateProbationRule(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		response.setContentType("text/html;charset=GBK");
		request.setCharacterEncoding("GBK");
		log.debug("---------------进入BizProbationRuleAction的goUpdateProbationRule方法-----------------------");
		String bizFullProbationOid = request.getParameter("bizFullProbationOid");
		if (null != bizFullProbationOid && !"".equals(bizFullProbationOid))
		{
			BizFullProbationRuleDTO bizFullProbationRuleDTO = bizProbationRuleFacade.getProbationRule(new Long(bizFullProbationOid));
			BizFullProbationRuleForm bizFullProbationRuleForm = new BizFullProbationRuleForm();
			BeanUtils.copyProperties(bizFullProbationRuleForm, bizFullProbationRuleDTO);
			bizFullProbationRuleForm.setPersonTypeName(DicParameter.viewName(DicParameter.SZRS0014,bizFullProbationRuleForm.getPersonType()));
			request.setAttribute("bizFullProbationRuleForm", bizFullProbationRuleForm);
			request.setAttribute("bizFullProbationOid", bizFullProbationOid);
			request.setAttribute("IS_TRUE", Constants.IS_TRUE);
			request.setAttribute("personTypeCode", DicParameter.SZRS0014);
			request.setAttribute("itemCode", bizFullProbationRuleForm.getItemCode());
		}
		return mapping.findForward(this.FORWARD_SUCCESS);
	}
	
	*//**
	 * 修改聘任制试用期到期预警规则
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	public ActionForward updateProbationRule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		response.setContentType("text/html;charset=GBK");
		request.setCharacterEncoding("GBK");
		log.debug("---------------进入BizProbationRuleAction的updateProbationRule方法-----------------------");
		try {
			BizFullProbationRuleForm bizFullProbationRuleForm = (BizFullProbationRuleForm) form;
			BizFullProbationRuleDTO dto = new BizFullProbationRuleDTO();
			BeanUtils.copyProperties(dto,bizFullProbationRuleForm);
			//检查人员类别是否重复
			List<BizFullProbationRuleDTO> list = bizProbationRuleFacade.findPersonType(dto.getPersonType(),dto.getBizFullProbationOid());
			if(!CollectionUtils.isEmpty(list))
			{
				throw new ServiceException("error.warning.persontype","该规则已存在请重新录入!（人员类别已存在）");
			}
			bizProbationRuleFacade.updateProbationRule(dto);
			request.setAttribute("IS_TRUE", Constants.IS_TRUE);
			request.setAttribute("okUpdate", BizWarningConstants.IS_TRUE);
		} catch (Exception se) {
			this.handleException(request, se, "updateProbationRule error ");
			return mapping.getInputForward();
		}
		return mapping.findForward(this.FORWARD_SUCCESS);
	}
	
	*//**
	 * 删除聘任制试用期到期预警规则
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	public ActionForward deleteProbationRule(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		log.debug("-------------进入BizProbationRuleAction的deleteProbationRule方法--------------------------");
		try
		{
			String ids = request.getParameter("bizFullProbationOids");
			 if(!GenericValidator.isBlankOrNull(ids)){
					String[] uids =ids.split(",");
					for(int i=0;i<uids.length;i++)
					{   
						bizProbationRuleFacade.deleteProbationRule(Long.parseLong(uids[i]));
					}
				}
		}
		catch(Exception se) {
			response.getWriter().print("{'success':'fail','msg':'&nbsp;&nbsp;删除失败！'}");
			handleException(request, se, null);
			return null;
	    }
		response.getWriter().print("{'success':'ok','msg':'&nbsp;&nbsp;删除成功！'}");
		return null;
	}*/
}