package com.yh.hr.info.dataimport.person.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.info.dataimport.person.facade.ImImportBatchFacade;
import com.yh.hr.info.dataimport.person.facade.VerPersonDataFacade;
import com.yh.hr.info.dataimport.person.tool.Singleton;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.hr.info.dataimport.person.facade.CheckBatchHandleFacade;
import com.yh.hr.info.dataimport.person.facade.CheckUnusualHandleFacade;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.im.dto.ImImportBatchDTO;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

public class VerPersonDataAction extends BaseAction {
	
	private VerPersonDataFacade verPersonDataFacade = (VerPersonDataFacade) SpringBeanUtil.getBean("verPersonDataFacade");
	private ImImportBatchFacade imImportBatchFacade = (ImImportBatchFacade) SpringBeanUtil.getBean("imImportBatchFacade");
	private CheckBatchHandleFacade checkBatchHandleFacade = (CheckBatchHandleFacade) SpringBeanUtil.getBean("checkBatchHandleFacade");
	private CheckUnusualHandleFacade checkUnusualHandleFacade = (CheckUnusualHandleFacade) SpringBeanUtil.getBean("checkUnusualHandleFacade");
	/**
	 * 跳转到字典检查
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goTocheckPersonDic(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("success");
	}
	/**
	 * 检查导入数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkDicDataNumBefore(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Singleton singleton = Singleton.getInstance();
			String exportPerson = singleton.getExportPerson();
			String rowDicNum = singleton.getRowDicNum();
			
			String checkDataMust = singleton.getCheckDataMust();
			String checkDataFormat = singleton.getCheckDataFormat();
			String checjDataLength = singleton.getChecjDataLength();
			String nowCheckDataNum = singleton.getNowCheckDataNum();
			String nowImportPersonNum = singleton.getNowImportPersonNum();
			String rowNum = singleton.getRowNum();
			JSONObject json = new JSONObject();
			
			json.put("exportPerson", exportPerson);
			json.put("rowDicNum", rowDicNum);
			
			json.put("checkDataMust", checkDataMust);
			json.put("checkDataFormat", checkDataFormat);
			json.put("checjDataLength", checjDataLength);
			json.put("nowCheckDataNum", nowCheckDataNum);
			json.put("rowNum", rowNum);
			json.put("nowImportPersonNum", nowImportPersonNum);
			response.getWriter().write(json.toString());
		} catch (Exception e) {
			handleException(request, e, e.getMessage());
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(),null)).toString());
		}
		return null;
	}
	/**
	 * 字典检查数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkDicDataNum(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			int checkRelationNum = verPersonDataFacade.countExceptionNum(DicConstants.YHRS0140_5);
			int checkDicNum =verPersonDataFacade.countExceptionNum(DicConstants.YHRS0140_4);
			int checkCompleteNum = verPersonDataFacade.countExceptionNum(DicConstants.YHRS0140_6);
			int rowDicNum = verPersonDataFacade.listImCheckPersonInfo().size();
			String message = Singleton.getInstance().getMessage();
			JSONObject json = new JSONObject();
			json.put("checkRelationNum", checkRelationNum);
			json.put("checkDicNum", checkDicNum);
			json.put("checkCompleteNum", checkCompleteNum);
			json.put("rowDicNum", rowDicNum);
			json.put("message", message);
			response.getWriter().write(json.toString());
		} catch (Exception e) {
			handleException(request, e, e.getMessage());
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(),null)).toString());
		}
		return null;
	}
	/**
	 * 导入数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward countExportPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*int exportPerson = verPersonDataFacade.countExportPerson();
			int rowDicNum = verPersonDataFacade.listImCheckPersonInfo().size();*/
			JSONObject json = new JSONObject();
			json.put("rowDicNum", Singleton.getInstance().getRowDicNum());
			json.put("exportPerson", Singleton.getInstance().getExportPerson());
			response.getWriter().write(json.toString());
		} catch (Exception e) {
			handleException(request, e, e.getMessage());
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(),null)).toString());
		}
		return null;
	}
	/**
	 * 查询临时表的人员，进行数据校验
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkPersonDic(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ImImportBatchDTO ImImportBatchDTO = imImportBatchFacade.findCurrentImImportBatchDTO();
			//更新导入流程状态--数据校核中
			checkBatchHandleFacade.updateBatchStatus(ImImportBatchDTO.getImportBatchOid(), DicConstants.YHRS0142_7);
			verPersonDataFacade.checkDataDic();
			verPersonDataFacade.checkDataRelation();
			verPersonDataFacade.checkDataComplete();
			verPersonDataFacade.updateBatchPersonCheckTypeForCheck();
			verPersonDataFacade.updateBatchUnusualLogs();
			verPersonDataFacade.updateBatchAmountForCheck();
			int checkDicNum =verPersonDataFacade.countExceptionNum(DicConstants.YHRS0140_4);
			if(checkDicNum==0) {
				//数据检查
				Boolean hasNoPassedLogs = checkUnusualHandleFacade.checkNopassedLogs(ImImportBatchDTO.getImportBatchOid());
				if(hasNoPassedLogs) {
					//更新导入流程状态--数据校核不通过
					checkBatchHandleFacade.updateBatchStatus(ImImportBatchDTO.getImportBatchOid(), DicConstants.YHRS0142_11);
				}else {
					//更新导入流程状态--数据校核通过
					checkBatchHandleFacade.updateBatchStatus(ImImportBatchDTO.getImportBatchOid(), DicConstants.YHRS0142_10);
				}
			}else {
				//更新导入流程状态--字典映射中 
				checkBatchHandleFacade.updateBatchStatus(ImImportBatchDTO.getImportBatchOid(), DicConstants.YHRS0142_8);
			}
			response.getWriter().write(JSONHelper.fromObject(true, StringUtils.defaultIfEmpty(String.valueOf(checkDicNum),null)).toString());
		} catch (Exception e) {
			handleException(request, e, e.getMessage());
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(),null)).toString());
		}
		return null;
	}
}
