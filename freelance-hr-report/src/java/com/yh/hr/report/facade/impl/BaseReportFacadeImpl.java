package com.yh.hr.report.facade.impl;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;

import javax.sql.DataSource;

import com.yh.hr.report.utils.Constants;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yh.hr.report.service.BaseReportService;
import com.yh.hr.report.utils.ReportConfigUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.PdfConcat;

public class BaseReportFacadeImpl{
	
	@SuppressWarnings("unused")
	private Log log=LogFactory.getLog(BaseReportFacadeImpl.class);
	
	private DataSource				dataSource;
	

	private BaseReportService baseReportService;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public void setBaseReportService(BaseReportService baseReportService) {
		this.baseReportService = baseReportService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.yh.hr.hspszhp.report.facade.BaseReportFacade#printOfficeInfo(java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("static-access")
	public String printOfficeInfo(Long personOid, String path,String createBy,String flag) throws ServiceException{	
		
		// 工作人员信息表
		String fileName_print001_tmp_1 ="";
		String fileName = path + "person_office_info" + System.currentTimeMillis() + ".pdf";
		if(StringUtils.isNotEmpty(flag)&&("wn").equals(flag)){
			fileName_print001_tmp_1 = path + "xinxi_ganbu_wn" + System.currentTimeMillis() + ".pdf";// 工作人员信息表临时文件1
		}else{
			fileName_print001_tmp_1 = path + "xinxi_ganbu_1" + System.currentTimeMillis() + ".pdf";// 工作人员信息表临时文件1
		}
		String fileName_print001_tmp_2 = path + "xinxi_ganbu_2" + System.currentTimeMillis() + ".pdf";// 工作人员信息表临时文件2
		HashMap<String, Object> params = baseReportService.createOfficerInfoReport(personOid, path);
		params.put("createBy", createBy);
		Calendar cal = Calendar.getInstance();
		params.put("printYear", String.valueOf(cal.get(cal.YEAR)));
		params.put("printMonth", String.valueOf(cal.get(cal.MONTH) + 1));
		params.put("printDate", String.valueOf(cal.get(cal.DATE)));
		String jasper= "";
			jasper = ReportConfigUtil.getProperty(Constants.PERSON_GANBU_1_URL);
		if (null != params.get("totalResume"))
		{
				jasper =  ReportConfigUtil.getProperty(Constants.PERSON_GANBU_1_30_URL);
		}
		
		fileName_print001_tmp_1 = this.writeFile(jasper, params, fileName_print001_tmp_1);
		fileName_print001_tmp_2 = this.writeFile(ReportConfigUtil.getProperty(Constants.PERSON_GANBU_2_URL), params, fileName_print001_tmp_2);
		String[] files = new String[]{fileName_print001_tmp_1,fileName_print001_tmp_2};
		try {
			//将打印出来的2个文件合并成一个文件（fileName_print001_tmp_1，fileName_print001_tmp_2 ----> fileName）
			PdfConcat.concat(files, fileName);
			for (int i = 0; i < files.length; i++)
			{
				// 把临时的文件删除
				File file = new File(files[i]);
				file.delete();
			}
		} catch (Exception e) {
			throw new ServiceException("打印"+ReportConfigUtil.getProperty(Constants.PERSON_GANBU_TITLE)+"出错！");
		}
		return fileName;	
	}
	/*
	 * (non-Javadoc)
	 * @see com.yh.hr.hspszhp.report.facade.BaseReportFacade#searchPrintPersonTable(java.lang.Long, java.lang.String, java.lang.String)
	 */
	public String searchPrintPersonTable(Long personOid, String path,String createBy) throws ServiceException{	
		HashMap<String, Object> parameters = baseReportService.createPersonInfoReport(personOid, path,createBy);
		String fileName = path + "person_confirm_info" + System.currentTimeMillis() + ".pdf";
		//if("1".equals(personType)||"2".equals(personType)){
			//String fileName1 = path + "person_confirm_info1" + System.currentTimeMillis() + ".pdf";// 人员信息表临时文件1
			//String fileName2 = path + "person_confirm_info2" + System.currentTimeMillis() + ".pdf";// 人员信息表临时文件2
			fileName = this.writeFile(ReportConfigUtil.getProperty(Constants.PERSON_MAIN_WBJ_URL), parameters, fileName);
			//fileName2 = this.writeFile(ReportConfigUtil.getProperty(Constants.PERSON_SUB_BIND_PERSON_GWY_URL), parameters, fileName2);
			//String[] files = new String[]{fileName1};
			/*try {
				//转换成fileName
				PdfConcat.concat(files, fileName);
				for (int i = 0; i < files.length; i++)
				{
					// 把临时的文件删除
					File file = new File(files[i]);
					file.delete();
				}
			} catch (Exception e) {
				throw new ServiceException("打印个人专技信息表时出错！");
			}*/
		/*}else {
			String fileName1 = path + "person_confirm_info1" + System.currentTimeMillis() + ".pdf";// ��Ա��Ϣ����ʱ�ļ�1
			String fileName2 = path + "person_confirm_info2" + System.currentTimeMillis() + ".pdf";// ��Ա��Ϣ����ʱ�ļ�2
			fileName1 = this.writeFile(ReportConfigUtil.getProperty(Constants.PERSON_MAIN_TEMP_URL), parameters, fileName1);
			fileName2 = this.writeFile(ReportConfigUtil.getProperty(Constants.PERSON_SUB_BIND_PERSON_LP_URL), parameters, fileName2);
			String[] files = new String[]{fileName1,fileName2};
			try {
				//����ӡ������2���ļ��ϲ���һ���ļ���fileName_print001_tmp_1��fileName_print001_tmp_2 ----> fileName��
				PdfConcat.concat(files, fileName);
				for (int i = 0; i < files.length; i++)
				{
					// ����ʱ���ļ�ɾ��
					File file = new File(files[i]);
					file.delete();
				}
			} catch (Exception e) {
				throw new ServiceException("��ӡ��Ա��Ϣ��ʱ���?");
			}
		}*/
		//fileName = this.writeFile(reportName, parameters, fileName);
		return fileName;	
	}
	private String writeFile(String reportName, @SuppressWarnings("rawtypes") HashMap parameters, String fileName) throws ServiceException
	{
		Connection connection = null;
		InputStream inputStream = null;	
		try
		{
			inputStream = this.getClass().getResourceAsStream(reportName);
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
			connection = dataSource.getConnection();
			byte[] bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, connection);
			File file =new File(fileName.substring(0,fileName.lastIndexOf("/")));
			if(!file.exists())
			{
				file.mkdirs(); 
			}
			FileOutputStream fos = new FileOutputStream(fileName);
			fos.write(bytes, 0, bytes.length);
			inputStream.close();
			fos.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ServiceException("",e);
		}
		finally
		{
			// 关闭输入流
			if (inputStream != null)
			{
				try
				{
					inputStream.close();
				}
				catch (IOException e)
				{
					throw new ServiceException("",e);
				}
			}
			//关闭数据库连接
			try
			{
				if (connection != null && !connection.isClosed())
				{
					connection.close();
				}
			}
			catch (SQLException e)
			{
				throw new ServiceException("close connection error in pdf report",e);
			}
		}
		return fileName;
	}

}
