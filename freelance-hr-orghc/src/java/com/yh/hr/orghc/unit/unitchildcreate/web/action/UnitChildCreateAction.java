package com.yh.hr.orghc.unit.unitchildcreate.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.orghc.unit.unitchildcreate.dto.UnitChildCreateDTO;
import com.yh.hr.orghc.unit.unitchildcreate.facade.UnitChildCreateFacade;
import com.yh.hr.orghc.unit.unitchildcreate.facade.UnitChildCreateFlowFacade;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.yh.hr.orghc.unit.unitchildcreate.web.form.UnitChildCreateForm;
import com.yh.hr.orghc.unit.util.UnitTaskItemFlowConstants;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.unit.dto.UtUnitDTO;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.util.StringMap;
import com.yh.platform.core.util.StringUtil;
import com.yh.platform.core.web.UserContext;
import com.yh.platform.core.web.action.BaseAction;

/**
 * 下级单位创建  Action
 * @author zhengdr
 *
 * 时间:2016-12-22下午03:02:07
 */
public class UnitChildCreateAction extends BaseAction {
	
	private UnitChildCreateFlowFacade unitChildCreateFlowFacade = (UnitChildCreateFlowFacade)SpringBeanUtil.getBean("unitChildCreateFlowFacade");
	private UnitChildCreateFacade unitChildCreateFacade = (UnitChildCreateFacade)SpringBeanUtil.getBean("unitChildCreateFacade");
	
	/**
	 * 跳转到新增
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 **/
	public ActionForward goCreateUnitChildCreate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//获取单位列表
		StringMap params = new StringMap();
		params.put("unitStatus",DicConstants.YHRS0101_2);
		//params.put("unitKind", StringUtil.arrayToSql(DicConstants.JG_UNIT_KINDS));
		List<UtUnitDTO> unitList = unitChildCreateFacade.findUserAuthedUnitList(UserContext.getLoginAgentUserID(), params);
		if(CollectionUtils.isNotEmpty(unitList)){
			List<UtUnitDTO> unitLists = new ArrayList<UtUnitDTO>();
			for(UtUnitDTO utUnitDTO : unitList){
				unitLists.add(utUnitDTO);
			}
			request.setAttribute("unitList", unitLists);
		}
		
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
    public ActionForward createUnitChildCreate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	UnitChildCreateForm unitChildCreateForm = (UnitChildCreateForm) form;
        
    	try {
        	UnitChildCreateDTO unitChildCreateDTO = BeanHelper.copyProperties(unitChildCreateForm, UnitChildCreateDTO.class);
        	unitChildCreateDTO.setItemCode(UnitTaskItemFlowConstants.ITEM_CODE_99995200);
        	unitChildCreateDTO.setItemNodeCode(UnitTaskItemFlowConstants.ITEM_NODE_CODE_999952001110);
        	unitChildCreateDTO.setCreateByCode(UserContext.getLoginUserID());
        	unitChildCreateDTO.setCreateByName(UserContext.getLoginUserName());
        	unitChildCreateDTO.setUpdateByCode(UserContext.getLoginUserID());
        	unitChildCreateDTO.setUpdateByName(UserContext.getLoginUserName());
			unitChildCreateFlowFacade.submitCreate(unitChildCreateDTO);
		    //返回utUnitOid
			String utUnitOid=unitChildCreateDTO.getUtUnitOid().toString();
			response.getWriter().write(JSONHelper.fromObject(true, StringUtils.defaultIfEmpty(null,utUnitOid)).toString());
        
        } catch (Exception e) {
            handleException(request, e, e.getMessage());
            response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "新增业务信息失败")).toString());
        }
        
        return null;
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
	public ActionForward goToViewUnitChildCreate(	ActionMapping mapping ,
													ActionForm form ,
													HttpServletRequest request ,
													HttpServletResponse response) throws Exception {
		try {
			UnitChildCreateForm unitChildCreateForm = (UnitChildCreateForm) form;
			if(unitChildCreateForm == null){
				unitChildCreateForm = new UnitChildCreateForm();
			}
			String  utUnitOid  = request.getParameter("utUnitOid");
			//得到dto
			UnitChildCreateDTO unitChildCreateDTO = unitChildCreateFacade.get(NumberUtils.longValue(utUnitOid));
			BeanHelper.copyProperties(unitChildCreateDTO, unitChildCreateForm);
			
			request.setAttribute("unitChildCreateForm",unitChildCreateForm);
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
	public ActionForward goUpdateUnitChildCreate(	ActionMapping mapping ,
													ActionForm form ,
													HttpServletRequest request ,
													HttpServletResponse response) throws Exception {
		try {
			String utUnitOid = request.getParameter("utUnitOid");
			UnitChildCreateForm unitChildCreateForm = (UnitChildCreateForm) form;
			if(unitChildCreateForm == null){
				unitChildCreateForm = new UnitChildCreateForm();
			}
			UnitChildCreateDTO unitChildCreateDTO = unitChildCreateFacade.get(NumberUtils.longValue(utUnitOid));

			request.setAttribute("id", request.getParameter("id"));
			request.setAttribute("utUnitOid", utUnitOid);
			BeanHelper.copyProperties(unitChildCreateDTO, unitChildCreateForm);
			
			request.setAttribute("unitChildCreateForm",unitChildCreateForm);
			
			//获取单位
			StringMap params = new StringMap();
			params.put("unitStatus",DicConstants.YHRS0101_2);
			//不是事业单位，选择的单位只能是机关单位
			if(!DicConstants.YHRS0090_104.equals(unitChildCreateForm.getUnitKind())){
				params.put("unitKind", StringUtil.arrayToSql(DicConstants.JG_UNIT_KINDS));
			}
			
			List<UtUnitDTO> unitList = unitChildCreateFacade.findUserAuthedUnitList(UserContext.getLoginAgentUserID(), params);
			if(CollectionUtils.isNotEmpty(unitList)){
				List<UtUnitDTO> unitLists = new ArrayList<UtUnitDTO>();
				for(UtUnitDTO utUnitDTO : unitList){
					if(utUnitDTO.getUnitOid().equals(unitChildCreateForm.getParentUnitOid())){
						request.setAttribute("selectUnit", unitChildCreateForm.getParentUnitName());
						continue;
					}
					unitLists.add(utUnitDTO);
				}
				request.setAttribute("unitList", unitLists);
			}
		}
		catch (Exception e) {
			handleException(request, e, null);
			return mapping.findForward("error");
		}
		return mapping.findForward("success");
	}

	/**
	 * 根据单位性质  联动 单位
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward changeByUnitKind(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String unitKind = request.getParameter("unitKind");
		try
		{
			//获取单位
			StringMap params = new StringMap();
			params.put("unitStatus",DicConstants.YHRS0101_2);
			//不是事业单位，选择的单位只能是机关单位
			if(!DicConstants.YHRS0090_104.equals(unitKind)){
				params.put("unitKind", StringUtil.arrayToSql(DicConstants.JG_UNIT_KINDS));
			}
			//得到单位信息
			List<UtUnitDTO> unitList = unitChildCreateFacade.findUserAuthedUnitList(UserContext.getLoginAgentUserID(), params);
			JSONObject obj = new JSONObject();
			JSONArray array = new JSONArray();
			//加入json
			JSONObject obj0 = new JSONObject();
			obj0.put("parentUnitOid","");
			obj0.put("parentUnitName", "--请选择--");
			array.element(obj0);
			
			if(CollectionUtils.isNotEmpty(unitList)){
				
				for(UtUnitDTO utUnitDTO : unitList){
					JSONObject obj1 = new JSONObject();
					obj1.put("parentUnitOid",utUnitDTO.getUnitOid());
					obj1.put("parentUnitName", utUnitDTO.getUnitName());
					array.element(obj1);
				}
				
			}
			
			obj.put("rs", array);
			response.getWriter().print(obj.toString());
		}
		catch(Exception se)
		{
			this.handleException(request, se, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(se.getMessage(), "查询失败")).toString());
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
	public ActionForward updateUnitChildCreate(	ActionMapping mapping ,
												ActionForm form ,
												HttpServletRequest request ,
												HttpServletResponse response) throws Exception {
		try {
			UnitChildCreateForm unitChildCreateForm = (UnitChildCreateForm) form;
			UnitChildCreateDTO unitChildCreateDTO = BeanHelper.copyProperties(unitChildCreateForm, UnitChildCreateDTO.class);
			unitChildCreateFacade.update(unitChildCreateDTO);
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
    public ActionForward goToShowUnitChildCreate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		try {
			
			UnitChildCreateForm unitChildCreateForm = (UnitChildCreateForm) form;
			if(unitChildCreateForm == null){
				unitChildCreateForm = new UnitChildCreateForm();
			}
			String  utUnitOid  = request.getParameter("utUnitOid");
			
			UnitChildCreateDTO unitChildCreateDTO = unitChildCreateFacade.get(NumberUtils.longValue(utUnitOid));
			BeanHelper.copyProperties(unitChildCreateDTO, unitChildCreateForm);
			request.setAttribute("unitChildCreateForm",unitChildCreateForm);
			request.setAttribute("utUnitOid", utUnitOid);
			
		}catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("error");
		}
		
		return mapping.findForward("success");
	  }
	
}
