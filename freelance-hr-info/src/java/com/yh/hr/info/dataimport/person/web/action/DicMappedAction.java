package com.yh.hr.info.dataimport.person.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.info.dataimport.person.facade.DicMappedFacade;
import com.yh.hr.info.dataimport.person.facade.ImImportBatchFacade;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.hr.info.dataimport.person.facade.CheckBatchHandleFacade;
import com.yh.hr.info.dataimport.person.facade.CheckUnusualHandleFacade;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.im.dto.ImDicItemMappingDTO;
import com.yh.hr.res.im.dto.ImDicTypeMappingDTO;
import com.yh.hr.res.im.dto.ImImportBatchDTO;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

public class DicMappedAction extends BaseAction {
	
	private DicMappedFacade dicMappedFacade = (DicMappedFacade) SpringBeanUtil.getBean("dicMappedFacade");
	private ImImportBatchFacade imImportBatchFacade = (ImImportBatchFacade) SpringBeanUtil.getBean("imImportBatchFacade");
	private CheckBatchHandleFacade checkBatchHandleFacade = (CheckBatchHandleFacade) SpringBeanUtil.getBean("checkBatchHandleFacade");
	private CheckUnusualHandleFacade checkUnusualHandleFacade = (CheckUnusualHandleFacade) SpringBeanUtil.getBean("checkUnusualHandleFacade");

	/**
	 * 跳转到字典映射列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goToDicMapped(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("success");
	}
	/**
	 * 查询左边没映射的字典树
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listNoDicMapped(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			List<JSONObject> list = dicMappedFacade.listNoDicMapped();
			response.getWriter().print(list.toString());
		} catch (Exception e) {
			handleException(request, e, "查询左边没映射的字典树失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "查询左边没映射的字典树失败")));
		}
		return null;
	}
	
	/**
	 * 跳转到字典映射工作台页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goToMainTabDicPanel(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		try {
			String dicTypeCode = request.getParameter("dicTypeCode");
			String dicTypeMappingOid = request.getParameter("dicTypeMappingOid");
			request.setAttribute("dicTypeMappingOid", dicTypeMappingOid);
			request.setAttribute("dicTypeCode", dicTypeCode);
			return mapping.findForward("success");
		} catch (Exception e) {
			handleException(request, e, "跳转到业务办理工作台中转页面");
			return mapping.findForward("error");
		}
	}
	
	/**
	 * 跳转到业务办理未映射/已映射页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goDicMappedViewport(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		String dbflag = request.getParameter("dbflag");
		String dicTypeCode = request.getParameter("dicTypeCode");
		String dicTypeMappingOid = request.getParameter("dicTypeMappingOid");
		try {
			List<ImDicItemMappingDTO> list = dicMappedFacade.listImDicItemMappingDTOIsCreateMapping(Long.valueOf(dicTypeMappingOid), dbflag);
			ImDicTypeMappingDTO imDicTypeMappingDTO = dicMappedFacade.getImDicTypeMappingDTO(Long.valueOf(dicTypeMappingOid));
			request.setAttribute("databaseColumnCode", imDicTypeMappingDTO.getDatabaseColumnCode());
			request.setAttribute("dicTypeCode", dicTypeCode);
			request.setAttribute("list", list);
			return mapping.findForward("success"+dbflag);
		} catch (Exception e) {
			handleException(request, e, "跳转到业务办理未映射/已映射失败");
			return mapping.findForward("error");
		}
	}
	
	/**
	 * 保存映射的字典项
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveDicMapped(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		try {
			ImImportBatchDTO ImImportBatchDTO = imImportBatchFacade.findCurrentImImportBatchDTO();
			//更新导入流程状态--字典映射中 
			checkBatchHandleFacade.updateBatchStatus(ImImportBatchDTO.getImportBatchOid(), DicConstants.YHRS0142_8);
			String dicTypeCode = request.getParameter("dicTypeCode");
			String dicMappedDatas = request.getParameter("dicMappedDatas");
			JSONArray jsStr = JSONArray.fromObject(dicMappedDatas);
			List<String> importItemNames = new ArrayList<String>();
			if(jsStr!=null&&!jsStr.isEmpty()){
				for(int i =0 ; i<jsStr.size();i++){
					JSONObject json = jsStr.getJSONObject(i);
					String importItemName = json.getString("importItemName");
					importItemNames.add(importItemName);
					dicMappedFacade.saveDicMapped(json,dicTypeCode);
				}
			}
			dicMappedFacade.updateImPerson(importItemNames,dicTypeCode);
			dicMappedFacade.checkData(dicTypeCode);
			dicMappedFacade.updateBatchPersonCheckTypeForCheck();
			dicMappedFacade.updateBatchUnusualLogs();
			dicMappedFacade.updateBatchAmountForCheck();
			Boolean flag = dicMappedFacade.checkDicRepeat();
			String message;
			if(flag){
			   //表示没有映射完
				message = "还存在未映射的字典项，请继续映射";
				response.getWriter().write(JSONHelper.fromObject(true, message).toString());
			}else {
				message = "映射完成";
				//更新导入流程状态--字典映射完成
				checkBatchHandleFacade.updateBatchStatus(ImImportBatchDTO.getImportBatchOid(), DicConstants.YHRS0142_9);
				//数据检查
				Boolean hasNoPassedLogs = checkUnusualHandleFacade.checkNopassedLogs(ImImportBatchDTO.getImportBatchOid());
				if(hasNoPassedLogs) {
					//更新导入流程状态--数据校核不通过
					checkBatchHandleFacade.updateBatchStatus(ImImportBatchDTO.getImportBatchOid(), DicConstants.YHRS0142_11);
				}else {
					//更新导入流程状态--数据校核通过
					checkBatchHandleFacade.updateBatchStatus(ImImportBatchDTO.getImportBatchOid(), DicConstants.YHRS0142_10);
				}
				response.getWriter().write(JSONHelper.fromObject(false, message).toString());
			}
		} catch (Exception e) {
			handleException(request, e, e.getMessage());
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(),null)).toString());
		}
		return null;
	}
	
	/**
	 * 跳转到入库界面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goTocheckPersonDicRepeat(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("success");
	}
}
