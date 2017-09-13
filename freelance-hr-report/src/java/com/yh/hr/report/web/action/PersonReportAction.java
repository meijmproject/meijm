package com.yh.hr.report.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.report.facade.impl.BaseReportFacadeImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.ConfigUtil;
import com.yh.platform.core.util.HttpUtil;
import com.yh.platform.core.util.PdfConcat;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;
import com.yh.platform.core.web.action.BaseAction;
/**
 * 打印工作人员表
 * @author liul
 * @date 2017-2-25
 *
 */
public class PersonReportAction extends BaseAction{
	
	private Log log=LogFactory.getLog(PersonReportAction.class);
	private static String REPORT_URL="report.print.temp.dir";
	private static String CONTENT_TYPE_FILE_STREAM="application/pdf";

	private BaseReportFacadeImpl baseReportFacade=(BaseReportFacadeImpl)SpringBeanUtil.getBean("baseReportFacade");
    /*private BaseOrgSAOFacade baseOrgSAOFacade = (BaseOrgSAOFacade)SpringBeanUtil.getBean("baseOrgSAOFacade");
	private PersonFacade personFacade   = (PersonFacade) SpringBeanUtil.getBean("personFacade");*/
	/**
	 * 打印工作人员信息表
	 * @param mapping
	 * @param actionform
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward printOfficeInfo(ActionMapping mapping, ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{	       
		 	String personOids=request.getParameter("personOids");
		 	String flag=request.getParameter("flag");
	        String fileName="";
	        String dir=ConfigUtil.getProperty(REPORT_URL);
		    try
	        {
				List<String> fileList = new ArrayList<String>();
				String createBy = UserContext.getLoginUserName();//填表人
				StringTokenizer token  = new StringTokenizer(personOids,",");
				while(token.hasMoreTokens()){
					String personOid = token.nextToken();
					//得到文件名称
					fileName = baseReportFacade.printOfficeInfo(Long.valueOf(personOid), dir, createBy,flag);
					fileList.add(fileName);
				}
				String[] files = new String[fileList.size()];
				fileList.toArray(files);
				String filename = dir + "person" + System.currentTimeMillis() + ".pdf";
				try
				{
					//将多个文件连接起来
					PdfConcat.concat(files, filename);
					for (int i = 0; i < files.length; i++)
					{
						//把得到的文件删除
						File file = new File(files[i]);					
						file.delete();
					}
					//输出文件
					File file = new File(filename);
					HttpUtil.writeResponse(response,file,CONTENT_TYPE_FILE_STREAM); 
				}
				
				catch (Exception e)
				{
					throw new ServiceException("create pdf file Exception",e);
				}       	
	        }
	        catch (ServiceException se)
	        {
	        	this.handleException(request, se, log);
	        	log.info(se);
	        }
	          
	        return null;
	}
	/**
	 * 打印个人专技信息表
	 * @param mapping
	 * @param actionform
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward printPublicInfo(ActionMapping mapping, ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{	       
		 	String personOids=request.getParameter("personOids");
	        String fileName="";
	        String dir=ConfigUtil.getProperty(REPORT_URL);
		    try
	        {
				List<String> fileList = new ArrayList<String>();
				String createBy = UserContext.getLoginUserName();//填表人
				StringTokenizer token  = new StringTokenizer(personOids,",");
				while(token.hasMoreTokens()){
					String personOid = token.nextToken();
					//得到文件名称
					fileName = baseReportFacade.searchPrintPersonTable(Long.valueOf(personOid), dir, createBy);
					fileList.add(fileName);
				}
				String[] files = new String[fileList.size()];
				fileList.toArray(files);
				String filename = dir + "person" + System.currentTimeMillis() + ".pdf";
				try
				{
					//将多个文件连接起来
					PdfConcat.concat(files, filename);
					for (int i = 0; i < files.length; i++)
					{
						//把临时的文件删除
						File file = new File(files[i]);					
						file.delete();
					}
					//输出文件
					File file = new File(filename);
					HttpUtil.writeResponse(response,file,CONTENT_TYPE_FILE_STREAM); 
				}
				
				catch (Exception e)
				{
					throw new ServiceException("create pdf file Exception",e);
				}       	
	        }
	        catch (ServiceException se)
	        {
	        	this.handleException(request, se, log);
	        	log.info(se);
	        }
	          
	        return null;
	}
}
