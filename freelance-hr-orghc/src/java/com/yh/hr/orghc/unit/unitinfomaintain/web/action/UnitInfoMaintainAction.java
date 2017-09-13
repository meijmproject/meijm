package com.yh.hr.orghc.unit.unitinfomaintain.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.orghc.unit.unitchildcreate.dto.UnitChildCreateDTO;
import com.yh.hr.orghc.unit.unitchildcreate.facade.UnitChildCreateFacade;
import com.yh.hr.orghc.unit.unitchildcreate.web.form.UnitChildCreateForm;
import com.yh.hr.orghc.unit.unitinfomaintain.dto.UnitInfoMaintainDTO;
import com.yh.hr.orghc.unit.unitinfomaintain.facade.UnitInfoMaintainFlowFacade;
import com.yh.hr.orghc.unit.util.UnitTaskItemFlowConstants;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
 * 单位信息管理Action
 * @author xiongyx
 *
 * 时间:2016-12-21
 */
public class UnitInfoMaintainAction extends BaseAction{
		private UnitInfoMaintainFlowFacade unitInfoMaintainFlowFacade= (UnitInfoMaintainFlowFacade)SpringBeanUtil.getBean("unitInfoMaintainFlowFacade");
		private UnitChildCreateFacade unitChildCreateFacade = (UnitChildCreateFacade)SpringBeanUtil.getBean("unitChildCreateFacade");

		
		/**
		 * 创建业务
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
		public ActionForward createUnitInfoMaintain(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
			
			UnitInfoMaintainDTO unitInfoMaintainDTO = null;
			
			try{
				String unitOid = request.getParameter("unitOid");
				//创建业务
				unitInfoMaintainDTO = new UnitInfoMaintainDTO();
				unitInfoMaintainDTO.setUnitOid(NumberUtils.longValue(unitOid));
				//事项
				unitInfoMaintainDTO.setItemCode(UnitTaskItemFlowConstants.ITEM_CODE_99995300);
				unitInfoMaintainDTO.setItemNodeCode(UnitTaskItemFlowConstants.ITEM_NODE_CODE_999953001010);
				unitInfoMaintainDTO.setCreateByCode(UserContext.getLoginUserID());
				unitInfoMaintainDTO.setCreateByName(UserContext.getLoginUserName());
				unitInfoMaintainDTO.setUpdateByCode(UserContext.getLoginUserID());
				unitInfoMaintainDTO.setUpdateByName(UserContext.getLoginUserName());
				//创建业务并开启流程
				unitInfoMaintainFlowFacade.submitCreate(unitInfoMaintainDTO);
				response.getWriter().write(JSONHelper.fromObject(true,unitInfoMaintainDTO.getUtUnitOid().toString()).toString());
			}catch (Exception e) {
				e.printStackTrace();
				response.getWriter().write(
						JSONHelper.fromObject(
								false,
								StringUtils.defaultIfEmpty(e.getMessage(),
										"创建单位信息失败")).toString());
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
		public ActionForward goToViewUnitInfoMaintain(	ActionMapping mapping ,
														ActionForm form ,
														HttpServletRequest request ,
														HttpServletResponse response) throws Exception {
			try {
				String  utUnitOid  = request.getParameter("utUnitOid");				
					UnitChildCreateForm unitChildCreateForm = (UnitChildCreateForm) form;
					if(unitChildCreateForm == null){
						unitChildCreateForm = new UnitChildCreateForm();
					}
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
		public ActionForward goUpdateUnitInfoMaintain(	ActionMapping mapping ,
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
				//不是事业单位，选择的单位只能是机关单位
				if(!DicConstants.YHRS0090_104.equals(unitChildCreateForm.getUnitKind())){
					params.put("unitKind", StringUtil.arrayToSql(DicConstants.JG_UNIT_KINDS));
				}
				params.put("unitStatus", DicConstants.YHRS0101_2);
				List<UtUnitDTO> unitList = unitChildCreateFacade.findUnitList(params);
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
				//不是事业单位，选择的单位只能是机关单位
				if(!DicConstants.YHRS0090_104.equals(unitKind)){
					params.put("unitKind", StringUtil.arrayToSql(DicConstants.JG_UNIT_KINDS));
				}
				params.put("unitStatus", DicConstants.YHRS0101_2);
				//得到单位信息
				List<UtUnitDTO> unitList = unitChildCreateFacade.findUnitList(params);
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
}
