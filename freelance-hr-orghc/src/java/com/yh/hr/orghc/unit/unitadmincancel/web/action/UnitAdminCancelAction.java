package com.yh.hr.orghc.unit.unitadmincancel.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.orghc.unit.unitadmincancel.web.form.UnitAdminCancelForm;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.hr.orghc.unit.unitadmincancel.dto.UnitAdminCancelDTO;
import com.yh.hr.orghc.unit.unitadmincancel.facade.UnitAdminCancelFacade;
import com.yh.hr.orghc.unit.unitadmincancel.facade.UnitAdminCancelFlowFacade;
import com.yh.hr.orghc.unit.util.UnitTaskItemFlowConstants;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;
import com.yh.platform.core.web.action.BaseAction;

/**
 * 主管单位撤销Action
 * @author zhengdr
 *
 * 时间:2016-12-22下午06:16:46
 */
public class UnitAdminCancelAction extends BaseAction {
	
	private UnitAdminCancelFlowFacade unitAdminCancelFlowFacade = (UnitAdminCancelFlowFacade)SpringBeanUtil.getBean("unitAdminCancelFlowFacade");
	private UnitAdminCancelFacade unitAdminCancelFacade = (UnitAdminCancelFacade)SpringBeanUtil.getBean("unitAdminCancelFacade");
	
	/**
	 * 跳转到新增
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 **/
	public ActionForward goCreateUnitAdminCancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
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
    public ActionForward createUnitAdminCancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	try {
    		//得到单位unitOid
    		String unitOid = request.getParameter("unitOid");
    		
        	UnitAdminCancelDTO unitAdminCancelDTO = new UnitAdminCancelDTO();
        	//设置unitOid
        	unitAdminCancelDTO.setUnitOid(NumberUtils.longValue(unitOid));
        	
        	unitAdminCancelDTO.setItemCode(UnitTaskItemFlowConstants.ITEM_CODE_99995110);
        	unitAdminCancelDTO.setItemNodeCode(UnitTaskItemFlowConstants.ITEM_NODE_CODE_999951101110);
        	unitAdminCancelDTO.setCreateByCode(UserContext.getLoginUserID());
        	unitAdminCancelDTO.setCreateByName(UserContext.getLoginUserName());
        	unitAdminCancelDTO.setUpdateByCode(UserContext.getLoginUserID());
        	unitAdminCancelDTO.setUpdateByName(UserContext.getLoginUserName());
			unitAdminCancelFlowFacade.submitCreate(unitAdminCancelDTO);
		    //返回utUnitOid
			String utUnitOid=unitAdminCancelDTO.getUtUnitOid().toString();
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
	public ActionForward goToViewUnitAdminCancel(	ActionMapping mapping ,
													ActionForm form ,
													HttpServletRequest request ,
													HttpServletResponse response) throws Exception {
		try {
			UnitAdminCancelForm unitAdminCancelForm = (UnitAdminCancelForm) form;
			if(unitAdminCancelForm == null){
				unitAdminCancelForm = new UnitAdminCancelForm();
			}
			String  utUnitOid  = request.getParameter("utUnitOid");
			//得到dto
			UnitAdminCancelDTO unitAdminCancelDTO = unitAdminCancelFacade.get(NumberUtils.longValue(utUnitOid));
			BeanHelper.copyProperties(unitAdminCancelDTO, unitAdminCancelForm);
			
			request.setAttribute("unitAdminCancelForm",unitAdminCancelForm);
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
	public ActionForward goUpdateUnitAdminCancel(	ActionMapping mapping ,
													ActionForm form ,
													HttpServletRequest request ,
													HttpServletResponse response) throws Exception {
		try {
			String utUnitOid = request.getParameter("utUnitOid");
			UnitAdminCancelForm unitAdminCancelForm = (UnitAdminCancelForm) form;
			if(unitAdminCancelForm == null){
				unitAdminCancelForm = new UnitAdminCancelForm();
			}
			UnitAdminCancelDTO unitAdminCancelDTO = unitAdminCancelFacade.get(NumberUtils.longValue(utUnitOid));

			request.setAttribute("id", request.getParameter("id"));
			request.setAttribute("utUnitOid", utUnitOid);
			BeanHelper.copyProperties(unitAdminCancelDTO, unitAdminCancelForm);
			
			request.setAttribute("unitAdminCancelForm",unitAdminCancelForm);
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
	public ActionForward updateUnitAdminCancel(	ActionMapping mapping ,
												ActionForm form ,
												HttpServletRequest request ,
												HttpServletResponse response) throws Exception {
		try {
			UnitAdminCancelForm unitAdminCancelForm = (UnitAdminCancelForm) form;
			UnitAdminCancelDTO unitAdminCancelDTO = BeanHelper.copyProperties(unitAdminCancelForm, UnitAdminCancelDTO.class);
			unitAdminCancelFacade.update(unitAdminCancelDTO);
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
    public ActionForward goToShowUnitAdminCancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		try {
			
			UnitAdminCancelForm unitAdminCancelForm = (UnitAdminCancelForm) form;
			if(unitAdminCancelForm == null){
				unitAdminCancelForm = new UnitAdminCancelForm();
			}
			String  utUnitOid  = request.getParameter("utUnitOid");
			
			UnitAdminCancelDTO unitAdminCancelDTO = unitAdminCancelFacade.get(NumberUtils.longValue(utUnitOid));
			BeanHelper.copyProperties(unitAdminCancelDTO, unitAdminCancelForm);
			request.setAttribute("unitAdminCancelForm",unitAdminCancelForm);
			request.setAttribute("utUnitOid", utUnitOid);
			
		}catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("error");
		}
		
		return mapping.findForward("success");
	  }
	
}
