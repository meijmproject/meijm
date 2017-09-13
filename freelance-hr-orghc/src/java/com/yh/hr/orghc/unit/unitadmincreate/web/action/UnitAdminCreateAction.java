package com.yh.hr.orghc.unit.unitadmincreate.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.orghc.unit.unitadmincreate.facade.UnitAdminCreateFlowFacade;
import com.yh.hr.orghc.unit.unitadmincreate.web.form.UnitAdminCreateForm;
import com.yh.hr.orghc.unit.util.UnitTaskItemFlowConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.admin.dto.UserSystemPositionDTO;
import com.yh.hr.orghc.unit.unitadmincreate.dto.UnitAdminCreateDTO;
import com.yh.hr.orghc.unit.unitadmincreate.facade.UnitAdminCreateFacade;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;
import com.yh.platform.core.web.action.BaseAction;

/**
 * 主管单位创建--Action
 * @author zhengdr
 *
 * 时间:2016-12-20下午03:19:20
 */
public class UnitAdminCreateAction extends BaseAction {
	
	private UnitAdminCreateFlowFacade unitAdminCreateFlowFacade = (UnitAdminCreateFlowFacade)SpringBeanUtil.getBean("unitAdminCreateFlowFacade");
	private UnitAdminCreateFacade unitAdminCreateFacade = (UnitAdminCreateFacade)SpringBeanUtil.getBean("unitAdminCreateFacade");
	
	/**
	 * 跳转到新增
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 **/
	public ActionForward goCreateUnitAdminCreate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//得到岗位信息
		List<UserSystemPositionDTO> uspDTOs = unitAdminCreateFacade.listUserSystemPosition(UserContext.getLoginUserID());
		boolean b = true;//默认只有事业单位
		int num = 0;
	    //是否有公管处权限
	    if(uspDTOs!=null&&uspDTOs.size()>0){
	    	for(UserSystemPositionDTO uspDTO:uspDTOs){
	    		if(uspDTO.getSystemPositionOid().longValue()==22006){
	    			//公管处
	    			b = false;
	    			num++;
	    			break ;
	    		}
	    	}
	    }
	    //是否有事管处权限
	    if(uspDTOs!=null&&uspDTOs.size()>0){
	    	for(UserSystemPositionDTO uspDTO:uspDTOs){
	    		if(uspDTO.getSystemPositionOid().longValue()==43002){
	    			//公管处
	    			b = true;
	    			num++;
	    			break ;
	    		}
	    	}
	    }
	    
	    request.setAttribute("num", num);
	    request.setAttribute("flag", b);
	    
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
    public ActionForward createUnitAdminCreate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	UnitAdminCreateForm unitAdminCreateForm = (UnitAdminCreateForm) form;
        
    	try {
        	UnitAdminCreateDTO unitAdminCreateDTO = BeanHelper.copyProperties(unitAdminCreateForm, UnitAdminCreateDTO.class);
        	unitAdminCreateDTO.setItemCode(UnitTaskItemFlowConstants.ITEM_CODE_99995100);
        	unitAdminCreateDTO.setItemNodeCode(UnitTaskItemFlowConstants.ITEM_NODE_CODE_999951001110);
        	unitAdminCreateDTO.setCreateByCode(UserContext.getLoginUserID());
        	unitAdminCreateDTO.setCreateByName(UserContext.getLoginUserName());
        	unitAdminCreateDTO.setUpdateByCode(UserContext.getLoginUserID());
        	unitAdminCreateDTO.setUpdateByName(UserContext.getLoginUserName());
			unitAdminCreateFlowFacade.submitCreate(unitAdminCreateDTO);
		    //返回utUnitOid
			String utUnitOid=unitAdminCreateDTO.getUtUnitOid().toString();
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
	    String pageNo = request.getParameter("pageNo");
	    if(null != pageNo){
			request.setAttribute("pageNo",pageNo);
		}
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
		String pageNo = request.getParameter("pageNo");
	    if(null != pageNo){
			request.setAttribute("pageNo",pageNo);
		}
	    
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
	public ActionForward goToViewUnitAdminCreate(	ActionMapping mapping ,
													ActionForm form ,
													HttpServletRequest request ,
													HttpServletResponse response) throws Exception {
		try {
			UnitAdminCreateForm unitAdminCreateForm = (UnitAdminCreateForm) form;
			if(unitAdminCreateForm == null){
				unitAdminCreateForm = new UnitAdminCreateForm();
			}
			String  utUnitOid  = request.getParameter("utUnitOid");
			//得到dto
			UnitAdminCreateDTO unitAdminCreateDTO = unitAdminCreateFacade.get(NumberUtils.longValue(utUnitOid));
			BeanHelper.copyProperties(unitAdminCreateDTO, unitAdminCreateForm);
			
			request.setAttribute("unitAdminCreateForm",unitAdminCreateForm);
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
	public ActionForward goUpdateUnitAdminCreate(	ActionMapping mapping ,
													ActionForm form ,
													HttpServletRequest request ,
													HttpServletResponse response) throws Exception {
		try {
			String utUnitOid = request.getParameter("utUnitOid");
			UnitAdminCreateForm unitAdminCreateForm = (UnitAdminCreateForm) form;
			if(unitAdminCreateForm == null){
				unitAdminCreateForm = new UnitAdminCreateForm();
			}
			UnitAdminCreateDTO unitAdminCreateDTO = unitAdminCreateFacade.get(NumberUtils.longValue(utUnitOid));

			request.setAttribute("id", request.getParameter("id"));
			request.setAttribute("utUnitOid", utUnitOid);
			BeanHelper.copyProperties(unitAdminCreateDTO, unitAdminCreateForm);
			
			request.setAttribute("unitAdminCreateForm",unitAdminCreateForm);
			
			
			//得到岗位信息
			List<UserSystemPositionDTO> uspDTOs = unitAdminCreateFacade.listUserSystemPosition(UserContext.getLoginUserID());
			boolean b = true;//默认只有事业单位
			int num = 0;
		    //是否有公管处权限
		    if(uspDTOs!=null&&uspDTOs.size()>0){
		    	for(UserSystemPositionDTO uspDTO:uspDTOs){
		    		if(uspDTO.getSystemPositionOid().longValue()==22006){
		    			//公管处
		    			b = false;
		    			num++;
		    			break ;
		    		}
		    	}
		    }
		    //是否有事管处权限
		    if(uspDTOs!=null&&uspDTOs.size()>0){
		    	for(UserSystemPositionDTO uspDTO:uspDTOs){
		    		if(uspDTO.getSystemPositionOid().longValue()==43002){
		    			//公管处
		    			b = true;
		    			num++;
		    			break ;
		    		}
		    	}
		    }
		    
		    request.setAttribute("num", num);
		    request.setAttribute("flag", b);
		    
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
	public ActionForward updateUnitAdminCreate(	ActionMapping mapping ,
												ActionForm form ,
												HttpServletRequest request ,
												HttpServletResponse response) throws Exception {
		try {
			UnitAdminCreateForm unitAdminCreateForm = (UnitAdminCreateForm) form;
			UnitAdminCreateDTO unitAdminCreateDTO = BeanHelper.copyProperties(unitAdminCreateForm, UnitAdminCreateDTO.class);
			unitAdminCreateFacade.update(unitAdminCreateDTO);
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
    public ActionForward goToShowUnitAdminCreate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		try {
			
			UnitAdminCreateForm unitAdminCreateForm = (UnitAdminCreateForm) form;
			if(unitAdminCreateForm == null){
				unitAdminCreateForm = new UnitAdminCreateForm();
			}
			String  utUnitOid  = request.getParameter("utUnitOid");
			
			UnitAdminCreateDTO unitAdminCreateDTO = unitAdminCreateFacade.get(NumberUtils.longValue(utUnitOid));
			BeanHelper.copyProperties(unitAdminCreateDTO, unitAdminCreateForm);
			request.setAttribute("unitAdminCreateForm",unitAdminCreateForm);
			request.setAttribute("utUnitOid", utUnitOid);
			
		}catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("error");
		}
		
		return mapping.findForward("success");
	  }
	
}
