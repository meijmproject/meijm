package com.yh.hr.info.dataexport.web.action;

import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.hr.info.dataexport.facade.ExportTrainInfoFacade;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

public class ExportTrainInfoAction  extends BaseAction {

	public ExportTrainInfoFacade exportTrainInfoFacade = (ExportTrainInfoFacade) SpringBeanUtil.getBean("exportTrainInfoFacade");
	
	/**
	 * 导出人员名册
	 * 
	 * @param mapping
	 * @param actionform
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward printTrainInfoByExcel(ActionMapping mapping, ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response){
		
		try { 
//		    TableTagBean ttb = new TableTagBean(request);
		    String path = request.getSession().getServletContext().getRealPath("");
				String filepath = path+"/hrinfo/dataexport/培训情况统计报表.xlsx";
			    String fileName = "培训情况统计报表.xlsx"; 
			    response.reset();
			    response.setCharacterEncoding("utf-8");
				response.addHeader("Content-Disposition","attachment;filename=" +new String((fileName).getBytes("utf-8"), "ISO8859-1")); 	
			    response.setContentType("application/vnd.ms-excel;charset=UTF-8");//设置response内容的类型  
			    FileInputStream fs = new FileInputStream(filepath);
				HSSFWorkbook templetWb = new HSSFWorkbook(fs);
				
				//构建excel
//				XSSFWorkbook wb = printByExcel(fields, nameList, transFlagList);
				HSSFWorkbook resultWb = exportTrainInfoFacade.buildExcel(templetWb);

				//导出excel
				resultWb.write(response.getOutputStream());
				response.getOutputStream().flush();
				response.getOutputStream().close();
		} catch (Exception e) {
			handleException(request, e, "数据导出出错!");
			return mapping.findForward("error");
		}
		return null;
		
	}
	 
	
	
/*	@SuppressWarnings("resource")
	private void printByExcel(TableTagBean ttb, HttpServletResponse response,
			String path) throws Exception {

		String filepath = path+"/hrinfo/dataexport/培训情况统计报表.xlsx";
	    String fileName = "培训情况统计报表.xlsx"; 
	    response.reset();
	    response.setCharacterEncoding("utf-8");
		response.addHeader("Content-Disposition","attachment;filename=" +new String((fileName).getBytes("utf-8"), "ISO8859-1")); 	
	    response.setContentType("application/vnd.ms-excel;charset=UTF-8");//设置response内容的类型  
	    FileInputStream fs = new FileInputStream(filepath);;
		XSSFWorkbook wb = new XSSFWorkbook(fs);;

		wb.write(response.getOutputStream());
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}*/



	/**
	 * handle error catched in action
	 * 
	 * @param request
	 * @param se
	 * @param log
	 */
	protected void handleException(HttpServletRequest request, Exception e, Object errorObject) {
		handleException(request, e instanceof ServiceException ? (ServiceException) e : new ServiceException(e.getMessage(), e), errorObject,null, null);
	}

}
