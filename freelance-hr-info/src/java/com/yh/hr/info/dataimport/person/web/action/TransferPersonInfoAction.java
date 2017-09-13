package com.yh.hr.info.dataimport.person.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.info.dataimport.person.facade.CheckBatchHandleFacade;
import com.yh.hr.info.dataimport.person.facade.ImImportBatchFacade;
import com.yh.hr.info.dataimport.person.facade.TransferPersonInfoFacade;
import com.yh.hr.info.dataimport.person.tool.Singleton;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.im.bo.ImCheckPersonInfo;
import com.yh.hr.res.im.dto.ImImportBatchDTO;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

public class TransferPersonInfoAction extends BaseAction {
	
	private TransferPersonInfoFacade transferPersonInfoFacade = (TransferPersonInfoFacade) SpringBeanUtil.getBean("transferPersonInfoFacade");
	private ImImportBatchFacade imImportBatchFacade = (ImImportBatchFacade) SpringBeanUtil.getBean("imImportBatchFacade");
	private CheckBatchHandleFacade checkBatchHandleFacade = (CheckBatchHandleFacade) SpringBeanUtil.getBean("checkBatchHandleFacade");

	/**
	 * 人员入库
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward transferPersonInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ImImportBatchDTO ImImportBatchDTO = imImportBatchFacade.findCurrentImImportBatchDTO();
			//更新导入流程状态--入库中
			checkBatchHandleFacade.updateBatchStatus(ImImportBatchDTO.getImportBatchOid(), DicConstants.YHRS0142_12);
			List<ImCheckPersonInfo> list = transferPersonInfoFacade.listPersonInfo();
			Singleton singleton = Singleton.getInstance();
			if(CollectionUtils.isNotEmpty(list)){
				for(int i=0;i<list.size();i++){
					transferPersonInfoFacade.transferPersonInfo(list.get(i).getCheckPersonInfoOid());
					singleton.setExportPerson(String.valueOf(i+1));
					singleton.setRowDicNum(String.valueOf(list.size()));
				}
				//更新导入流程状态--入库完成
				checkBatchHandleFacade.updateBatchStatus(ImImportBatchDTO.getImportBatchOid(), DicConstants.YHRS0142_13);
			}
			String message ="计划入库人员总数"+list.size()+"人，成功入库人员总数"+list.size()+"人";
			response.getWriter().print(JSONHelper.fromObject(true, message));
		} catch (Exception e) {
			handleException(request, e, "人员入库失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "人员入库失败")));
		}
		return null;
	}
	
}
