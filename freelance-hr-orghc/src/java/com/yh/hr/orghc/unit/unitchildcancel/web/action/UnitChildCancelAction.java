package com.yh.hr.orghc.unit.unitchildcancel.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.orghc.unit.unitchildcancel.facade.UnitChildCancelFacade;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.hr.orghc.unit.unitchildcancel.dto.UnitChildCancelDTO;
import com.yh.hr.orghc.unit.unitchildcancel.facade.UnitChildCancelFlowFacade;
import com.yh.hr.orghc.unit.unitchildcancel.web.form.UnitChildCancelForm;
import com.yh.hr.orghc.unit.util.UnitTaskItemFlowConstants;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;
import com.yh.platform.core.web.action.BaseAction;

/**
 * 下级单位撤销 Action
 * @author zhengdr
 *
 * 时间:2016-12-23下午02:00:51
 */
public class UnitChildCancelAction extends BaseAction {
	
	private UnitChildCancelFlowFacade unitChildCancelFlowFacade = (UnitChildCancelFlowFacade)SpringBeanUtil.getBean("unitChildCancelFlowFacade");
	private UnitChildCancelFacade unitChildCancelFacade = (UnitChildCancelFacade)SpringBeanUtil.getBean("unitChildCancelFacade");
	
	/**
	 * 跳转到新增
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 **/
	public ActionForward goCreateUnitChildCancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return mapping.findForward("success");
	}
	
	/**
     * 新增业务信息
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exceptions
     */
    public ActionForward createUnitChildCancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	try {
    		//得到单位unitOid
    		String unitOid = request.getParameter("unitOid");
    		
        	UnitChildCancelDTO unitChildCancelDTO = new UnitChildCancelDTO();
        	//设置unitOid
        	unitChildCancelDTO.setUnitOid(NumberUtils.longValue(unitOid));
        	
        	unitChildCancelDTO.setItemCode(UnitTaskItemFlowConstants.ITEM_CODE_99995210);
        	unitChildCancelDTO.setItemNodeCode(UnitTaskItemFlowConstants.ITEM_NODE_CODE_999952101110);
        	unitChildCancelDTO.setCreateByCode(UserContext.getLoginUserID());
        	unitChildCancelDTO.setCreateByName(UserContext.getLoginUserName());
        	unitChildCancelDTO.setUpdateByCode(UserContext.getLoginUserID());
        	unitChildCancelDTO.setUpdateByName(UserContext.getLoginUserName());
			unitChildCancelFlowFacade.submitCreate(unitChildCancelDTO);
		    //返回utUnitOid
			String utUnitOid=unitChildCancelDTO.getUtUnitOid().toString();
			response.getWriter().write(JSONHelper.fromObject(true, StringUtils.defaultIfEmpty(null,utUnitOid)).toString());
        
        } catch (Exception e) {
            handleException(request, e, e.getMessage());
            response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "新增业务信息失败")).toString());
        }
        
        return null;
    }
    
	/**
	 * 修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String  utUnitOid  = request.getParameter("utUnitOid");
		
	    request.setAttribute("utUnitOid",utUnitOid);
		
		return mapping.findForward("success");
	}
	
	/**
	 * 查看
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		String  utUnitOid  = request.getParameter("utUnitOid");
		request.setAttribute("utUnitOid",utUnitOid);
		
		return mapping.findForward("success");
	}
	
	/**
	 * 跳转到修改 查看页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goToViewUnitChildCancel(	ActionMapping mapping ,
													ActionForm form ,
													HttpServletRequest request ,
													HttpServletResponse response) throws Exception {
		try {
			UnitChildCancelForm unitChildCancelForm = (UnitChildCancelForm) form;
			if(unitChildCancelForm == null){
				unitChildCancelForm = new UnitChildCancelForm();
			}
			String  utUnitOid  = request.getParameter("utUnitOid");
			//得到dto
			UnitChildCancelDTO unitChildCancelDTO = unitChildCancelFacade.get(NumberUtils.longValue(utUnitOid));
			BeanHelper.copyProperties(unitChildCancelDTO, unitChildCancelForm);
			
			request.setAttribute("unitChildCancelForm",unitChildCancelForm);
			request.setAttribute("utUnitOid", utUnitOid);
		}
		catch (Exception e) {
			handleException(request, e, null);
			return mapping.findForward("error");
		}
		return mapping.findForward("success");
	}
	
	/**
	 * 弹出修改页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goUpdateUnitChildCancel(	ActionMapping mapping ,
													ActionForm form ,
													HttpServletRequest request ,
													HttpServletResponse response) throws Exception {
		try {
			String utUnitOid = request.getParameter("utUnitOid");
			UnitChildCancelForm unitChildCancelForm = (UnitChildCancelForm) form;
			if(unitChildCancelForm == null){
				unitChildCancelForm = new UnitChildCancelForm();
			}
			UnitChildCancelDTO unitChildCancelDTO = unitChildCancelFacade.get(NumberUtils.longValue(utUnitOid));

			request.setAttribute("id", request.getParameter("id"));
			request.setAttribute("utUnitOid", utUnitOid);
			BeanHelper.copyProperties(unitChildCancelDTO, unitChildCancelForm);
			
			request.setAttribute("unitChildCancelForm",unitChildCancelForm);
		}
		catch (Exception e) {
			handleException(request, e, null);
			return mapping.findForward("error");
		}
		return mapping.findForward("success");
	}


	
	/**
	 * 修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateUnitChildCancel(	ActionMapping mapping ,
												ActionForm form ,
												HttpServletRequest request ,
												HttpServletResponse response) throws Exception {
		try {
			UnitChildCancelForm unitChildCancelForm = (UnitChildCancelForm) form;
			UnitChildCancelDTO unitChildCancelDTO = BeanHelper.copyProperties(unitChildCancelForm, UnitChildCancelDTO.class);
			unitChildCancelFacade.update(unitChildCancelDTO);
			response.getWriter().write(JSONHelper.fromObject(true, null).toString());
        }
        catch (Exception e) {
            handleException(request, e, e.getMessage());
            response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "修改业务信息失败")).toString());
            return null;
        }
		
		
        return null;
	}
	
	/**
	 * 查看业务信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward goToShowUnitChildCancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		try {
			
			UnitChildCancelForm unitChildCancelForm = (UnitChildCancelForm) form;
			if(unitChildCancelForm == null){
				unitChildCancelForm = new UnitChildCancelForm();
			}
			String  utUnitOid  = request.getParameter("utUnitOid");
			
			UnitChildCancelDTO unitChildCancelDTO = unitChildCancelFacade.get(NumberUtils.longValue(utUnitOid));
			BeanHelper.copyProperties(unitChildCancelDTO, unitChildCancelForm);
			request.setAttribute("unitChildCancelForm",unitChildCancelForm);
			request.setAttribute("utUnitOid", utUnitOid);
			
		}catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("error");
		}
		
		return mapping.findForward("success");
	  }
	
}
