package com.yh.hr.info.warning.web.action;


import com.yh.platform.core.web.action.BaseAction;

/*
 * @author	zhangsx
 * @created 2013-07-24
 */
public class BizRetireRuleAction extends BaseAction
{
	/*private BizRetireRuleFacadeImpl bizRetireRuleFacade = (BizRetireRuleFacadeImpl)SpringBeanUtil.getBean("bizRetireRuleFacade");
	
	*//**
	 * 跳转离退休预警页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	public ActionForward goModfiyRetire(ActionMapping mapping, 
													   		    ActionForm form,
											   				    HttpServletRequest request, 
											   				    HttpServletResponse response) throws Exception
	{
		response.setContentType("text/html;charset=GBK");
		request.setCharacterEncoding("GBK");
		log.debug("---------------进入BizRetireRuleAction的goModfiyProbation方法-----------------------");
		String itemCode=request.getParameter("itemCode");
		request.setAttribute("itemCode", itemCode);
		return mapping.findForward(this.FORWARD_SUCCESS);
	}
	
	*//**
	 * 跳转离退休预警页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	public ActionForward listRetireRule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		response.setContentType("text/html;charset=GBK");
		request.setCharacterEncoding("GBK");
		log.debug("---------------进入BizRetireRuleAction的listRetireRule方法-----------------------");
		try
		{
			TableTagBean ttb = TableTagBean.getFromRequest(request);
			JSONObject obj = new JSONObject();
			JSONArray ary = new JSONArray();
			List<BizOutRetireRuleDTO> list = bizRetireRuleFacade.listRetireRule(ttb);
			if (!CollectionUtils.isEmpty(list))
			{
				for (BizOutRetireRuleDTO dto : list)
				{
					JSONObject obj1 = new JSONObject();
					obj1.put("bizOutRetireOid", dto.getBizOutRetireOid());
					obj1.put("sexCode",DicHelper.viewName(DicConstants.YHRS0001,dto.getSexCode()));
					obj1.put("createdBy", dto.getCreatedBy());
					obj1.put("createdTsStr", DateUtil.format(dto.getCreatedTs(),DateUtil.TIME_PATTERN_DEFAULT));
					obj1.put("itemCode", dto.getItemCode());
					obj1.put("personType",DicHelper.viewName(DicConstants.YHRS0014,dto.getPersonType()));
					obj1.put("retrieDays", dto.getRetrieDays());
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
			handleException(request, e, "加载离退休预警规则出错!");
			response.getWriter().print("{'fail':'fail','msg':'" + (GenericValidator.isBlankOrNull(e.getMessage())?"加载基本条件出错!":e.getMessage()) + "'}");
			return null;
		}
		return null;
	}
	*//**
	 * 跳转离退休新增页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	public ActionForward goCreateRetireRule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		response.setContentType("text/html;charset=GBK");
		request.setCharacterEncoding("GBK");
		log.debug("---------------进入BizRetireRuleAction的goCreateRetireRule方法-----------------------");
		String itemCode = request.getParameter("itemCode");
		request.setAttribute("itemCode", itemCode);
		request.setAttribute("IS_TRUE", Constants.IS_TRUE);
		request.setAttribute("personTypeCode", DicParameter.SZRS0014);
		request.setAttribute("sexTypeCode", DicParameter.SZRS0001);
		return mapping.findForward(this.FORWARD_SUCCESS);
	}
	
	*//**
	 * 新增离退休预警规则
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	public ActionForward createRetireRule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		response.setContentType("text/html;charset=GBK");
		request.setCharacterEncoding("GBK");
		log.debug("---------------进入BizRetireRuleAction的createRetireRule方法-----------------------");
		try
		{
			BizOutRetireRuleForm bizOutRetireRuleForm = (BizOutRetireRuleForm) form;
			BizOutRetireRuleDTO bizOutRetireRuleDTO = new BizOutRetireRuleDTO();
			BeanUtils.copyProperties(bizOutRetireRuleDTO, bizOutRetireRuleForm);
			//检查人员类别是否重复
			List<BizOutRetireRuleDTO> list = bizRetireRuleFacade.findPersonType(bizOutRetireRuleDTO.getPersonType(),bizOutRetireRuleDTO.getBizOutRetireOid(),bizOutRetireRuleDTO.getSexCode());
			if(!CollectionUtils.isEmpty(list))
			{
				throw new ServiceException("error.warning.sexcodepersontype","该规则已存在请重新录入!（人员类别与性别组合的规则只能有一条）");
			}
			bizRetireRuleFacade.createRetireRule(bizOutRetireRuleDTO);
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
	 * 跳转离退休规则修改页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	public ActionForward goUpdateRetireRule(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		response.setContentType("text/html;charset=GBK");
		request.setCharacterEncoding("GBK");
		log.debug("---------------进入BizRetireRuleAction的goUpdateRetireRule方法-----------------------");
		String bizOutRetireOid = request.getParameter("bizOutRetireOid");
		if (null != bizOutRetireOid && !"".equals(bizOutRetireOid))
		{
			BizOutRetireRuleDTO bizOutRetireRuleDTO = bizRetireRuleFacade.getRetireRule(new Long(bizOutRetireOid));
			BizOutRetireRuleForm bizOutRetireRuleForm = new BizOutRetireRuleForm();
			BeanUtils.copyProperties(bizOutRetireRuleForm, bizOutRetireRuleDTO);
			bizOutRetireRuleForm.setPersonTypeName(DicParameter.viewName(DicParameter.SZRS0014,bizOutRetireRuleForm.getPersonType()));
			bizOutRetireRuleForm.setSexCodeName(DicParameter.viewName(DicParameter.SZRS0001,bizOutRetireRuleForm.getSexCode()));
			request.setAttribute("bizOutRetireRuleForm", bizOutRetireRuleForm);
			request.setAttribute("bizOutRetireOid", bizOutRetireOid);
			request.setAttribute("personTypeCode", DicParameter.SZRS0014);
			request.setAttribute("sexTypeCode", DicParameter.SZRS0001);
			request.setAttribute("IS_TRUE", Constants.IS_TRUE);
			request.setAttribute("itemCode", bizOutRetireRuleForm.getItemCode());
		}
		return mapping.findForward(this.FORWARD_SUCCESS);
	}
	
	*//**
	 * 修改离退休规则
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	public ActionForward updateRetireRule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		response.setContentType("text/html;charset=GBK");
		request.setCharacterEncoding("GBK");
		log.debug("---------------进入BizRetireRuleAction的updateRetireRule方法-----------------------");
		try {
			BizOutRetireRuleForm bizOutRetireRuleForm = (BizOutRetireRuleForm) form;
			BizOutRetireRuleDTO dto = new BizOutRetireRuleDTO();
			BeanUtils.copyProperties(dto,bizOutRetireRuleForm);
			//检查人员类别是否重复
			List<BizOutRetireRuleDTO> list = bizRetireRuleFacade.findPersonType(dto.getPersonType(),dto.getBizOutRetireOid(),dto.getSexCode());
			if(!CollectionUtils.isEmpty(list))
			{
				throw new ServiceException("error.warning.sexcodepersontype","该规则已存在请重新录入!（人员类别与性别组合的规则只能有一条）");
			}
			bizRetireRuleFacade.updateRetireRule(dto);
			request.setAttribute("IS_TRUE", Constants.IS_TRUE);
			request.setAttribute("okUpdate", BizWarningConstants.IS_TRUE);
		} catch (Exception se) {
			this.handleException(request, se, "updateProbationRule error ");
			return mapping.getInputForward();
		}
		return mapping.findForward(this.FORWARD_SUCCESS);
	}
	
	*//**
	 * 删除离退休预警规则
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	public ActionForward deleteRetireRule(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		log.debug("-------------进入BizRetireRuleAction的deleteRetireRule方法--------------------------");
		try
		{
			String ids = request.getParameter("bizOutRetireOids");
			 if(!GenericValidator.isBlankOrNull(ids)){
					String[] uids =ids.split(",");
					for(int i=0;i<uids.length;i++)
					{   
						bizRetireRuleFacade.deleteRetireRule(Long.parseLong(uids[i]));
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