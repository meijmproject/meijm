package com.yh.component.print.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PRAcroForm;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.SimpleBookmark;


public class PrintPdfUtil {
	public static String REPORT_URL="report.print.temp.dir";
	private static String CONTENT_TYPE_FILE_STREAM = "application/pdf";
	
	/**
	 * 将多个报表合并成一个报表（iReport）
	 * @param reportNames
	 * @param parameters
	 * @param fileName
	 * @throws ServiceException
	 */
	public static void concatPdf(String[] reportNames,@SuppressWarnings("rawtypes") HashMap parameters,String fileName) throws ServiceException
	{
		//TODO 异常提示  路径不存在的时候的提示  fileName不正确时的提示  files为空的提示  等等等
		String path  = PrintConfigUtil.getCommonProperty(REPORT_URL);
		String[] files = new String[reportNames.length];
		for(int i=0;i<reportNames.length;i++)
		{
			String fileName1 = path + "pdf" + System.currentTimeMillis() + ".pdf";// 人员信息表临时文件1
			fileName1 = writeFile(PrintConfigUtil.getPrintProperty(reportNames[i]), parameters, fileName1);
			files[i] = fileName1;
		}
		try {
			//将打印出来的多个文件合并成一个文件（fileName_print001_tmp_1，fileName_print001_tmp_2 ----> fileName）
			concat(files,fileName);
			for (int i = 0; i < files.length; i++)
			{
				// 把临时的文件删除
				File file = new File(files[i]);
				file.delete();
			}
		} catch (Exception e) {
			throw new ServiceException("合并报表时出错！");
		}
	}
	/**
	 * 将多个pdf文件合并成一个pdf文件
	 * 合并后会将合并的pdf文件删除
	 * @param fileNames   要合并的文件（需要带路径）
	 * @param fileName   合并后的文件（打印到默认路径）
	 * @throws ServiceException
	 */
	public static String concatForFile(String[] fileNames,String fileName) throws ServiceException
	{
		//TODO 异常提示  路径不存在的时候的提示  fileName不正确时的提示  files为空的提示  等等等
		String path  = PrintConfigUtil.getCommonProperty(REPORT_URL)+fileName;
		try {
			//将打印出来的多个文件合并成一个文件（fileName_print001_tmp_1，fileName_print001_tmp_2 ----> fileName）
			concat(fileNames,path);
			for (int i = 0; i < fileNames.length; i++)
			{
				// 把临时的文件删除
				File file = new File(fileNames[i]);
				file.delete();
			}
			return path;
		} catch (Exception e) {
			throw new ServiceException("合并报表时出错！");
		}
	}
	/**
	 * 将已经打印出来的pdf合并在一起
	 * 合并后不会删除合并的pdf文件
	 * Concatenate existing PDF file to one file
	 * 
	 * @param inFiles  要合并的文件（需要带路径）
	 * @param file    合并后的文件（需要带路径）
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void concat(String[] inFiles, String file) throws ServiceException
	{
		try {
			if (inFiles.length < 1) return;
			
			int pageOffset = 0;
			int index = 0;
			@SuppressWarnings("rawtypes")
			ArrayList master = new ArrayList();
			
			Document document = null;
			PdfCopy writer = null;
			PdfReader reader = null;
			
			while (index < inFiles.length)
			{
				// create a reader for certain document
				reader = new PdfReader(inFiles[index]);
				reader.consolidateNamedDestinations();
				
				// retrieve the total number of pages
				int pages = reader.getNumberOfPages();
				@SuppressWarnings("rawtypes")
				List bookmarks = SimpleBookmark.getBookmark(reader);
				if (bookmarks != null)
				{
					if (pageOffset != 0)
						SimpleBookmark.shiftPageNumbers(bookmarks, pageOffset, null);
					master.addAll(bookmarks);
				}
				pageOffset += pages;
				
				if (index == 0)
				{
					String outFile = file;
					// create the new document object
					document = new Document(reader.getPageSizeWithRotation(1));
					writer = new PdfCopy(document, new FileOutputStream(outFile));
					document.open();
				}
				
				// add the content
				for (int i = 1; i <= pages; i++)
				{
					PdfImportedPage page = writer.getImportedPage(reader, i);
					writer.addPage(page);
				}
				
				PRAcroForm form = reader.getAcroForm();
				if (form != null) writer.copyAcroForm(reader);
				
				index ++;
				
			}
			
			if (master.size() > 0) writer.setOutlines(master);
			
			// close the document
			document.close();
			writer.close();
		}catch (Exception e) {
			throw new ServiceException("合并pdf文件时出错！");
		}
	}
	
	/**
	 * 输出文件
	 * @param reportName
	 * @param parameters
	 * @param fileName
	 * @return
	 * @throws ServiceException
	 */
	private static String writeFile(String reportName, @SuppressWarnings("rawtypes") HashMap parameters, String fileName) throws ServiceException
	{
		Transaction tx = null;
		Connection connection = null;
		InputStream inputStream = null;	
		Session session = DaoUtil.getHibernateTemplate().getSessionFactory().openSession();

		try
		{
			inputStream = PrintPdfUtil.class.getResourceAsStream(reportName);
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
			tx = session.beginTransaction();
			connection = session.connection();
			byte[] bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, connection);
			FileOutputStream fos = new FileOutputStream(fileName);
			fos.write(bytes, 0, bytes.length);
			inputStream.close();
			fos.close();
			tx.commit(); //使用 Hibernate事务处理边界
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
			// 关闭数据库连接
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
			 session.close();
		}
		return fileName;
	}
	
	/**
	 * 输出文件
	 * @param response
	 * @param file
	 * @param fileType
	 * @param download
	 * @throws IOException
	 * @throws ServiceException
	 */
	public static  void writeResponse(HttpServletResponse response, File file,String fileType,boolean download) throws IOException, ServiceException
	{
		try{
			if(StringUtils.isEmpty(fileType))
			{
				fileType = CONTENT_TYPE_FILE_STREAM;
			}

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
				
			FileInputStream in = new FileInputStream(file);

			byte[] pdf = new byte[in.available()];
			in.read(pdf);
			in.close();
			bos.write(pdf);
	
			if (download)
			{
				response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
			}
			response.setContentType(fileType);
			response.setContentLength(pdf.length);
			bos.writeTo(response.getOutputStream());
	
			bos.flush();
			bos.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ServiceException("errors.service.common", "系统异常。");
		}
	}
	
	/**
	 * 判断对象是否为空
	 * @param Object
	 * @param type : 日期类型转换指定格式需要填写、后面需要转换的形式,ItemCode需要转换成ItemName、后面需要传ItemCode
	 * @return String
	 */
	/*public static String checkObjIsNull(Object obj,String type)
	{
		if(null == obj || "".equals(obj))
		{
			return "/";
		}
		else if((obj instanceof String || obj instanceof Long || obj instanceof Integer) && null != type)
		{	
			return DicParameter.viewDicItemNameByDicTypeCodeandDicItemCode(type, (String) obj);
		}
		else if(obj instanceof java.util.Date && null != type)
		{
			return DateUtil.format((Date) obj, type);
		}
		else if(obj instanceof java.util.Date && null == type)
		{	
			return DateUtil.formatDate((Date) obj);
		}
		else
		{
			return obj.toString();
		}
	}*/
	
	/**
	 * 简历信息按时间排序
     * <br>(如果没有起始时间,则过滤掉qieren@4.9)
	 * 
	 * @param list
	 * @param dateDivision 默认为 "-"
	 * @return
	 */
	public static List<String> sortList(List<String> list, String dateDivision)
	{
		if(StringUtils.isEmpty(dateDivision))
		{
			dateDivision = "-";
		}
		List<String> sortList = new ArrayList<String>();
		String maxInfo = "";
		String curInfo = "";
		int max = 0;
		int cur = 0;
		int maxindex = 0;
		for (int i = 0; i < list.size(); i++)
		{
			maxindex = i;
            maxInfo = list.get(i);
            if(maxInfo.length() < 10 || !maxInfo.substring(4,5).equals(dateDivision)){
                continue;
            }
            max = Integer.parseInt((String) list.get(i).substring(0, 10).replace(dateDivision, ""));
			for (int j = i + 1; j < list.size(); j++)
			{
                curInfo = list.get(j);
                if(curInfo.length() < 10 || !curInfo.substring(4,5).equals(dateDivision)){
                    continue;
                }
                cur = Integer.parseInt((String) list.get(j).substring(0, 10).replace(dateDivision, ""));
				if (max > cur)
				{
					maxindex = j;
					max = cur;
					maxInfo = curInfo;
				}
			}
			sortList.add(maxInfo);
			if (maxindex != i)
			{
				list.set(maxindex, list.get(i));
			}
		}

		return sortList;
	}
	/**
	 * 根据生日计算年龄
	 * 
	 * @param date
	 * @return
	 */
	public static String caculateAge(Date birthDate, Date baseDate)
	{
		if (birthDate == null) { return ""; }

		Calendar birthday = Calendar.getInstance();
		birthday.setTime(birthDate);
		Calendar now = Calendar.getInstance();
		if(baseDate!=null)
		{
			now.setTime(baseDate);
		}

		int birthYear = birthday.get(Calendar.YEAR);
		int nowYear = now.get(Calendar.YEAR);

		//int birthMonth = birthday.get(Calendar.MONTH);
		//int nowMonth = now.get(Calendar.MONTH);

		
		int years = nowYear-birthYear;
		if(years<1) return "";
		
		/*int months = nowMonth-birthMonth;
		if(months<0)
		{
			years = years -1;
			months = months + 12;
		}*/
		String age = years + "岁";
//		if(months != 0)
//		{
//			age = age + months +"月";
//		}
		return age;
	}
	
	/**
	 * 写报表(自己提供数据源)
	 * 
	 * @author 
	 * @created 
	 * @param reportName
	 * @param parameters
	 * @param fileName
	 * @param dataSource
	 * @return
	 * @throws ServiceException
	 */
	public static String writeFile(String reportName, Map<String, Object> parameters, String fileName, JRDataSource dataSource) throws ServiceException
	{
		InputStream inputStream = null;
		JasperReport jasperReport = null;
		try
		{
			inputStream = PrintPdfUtil.class.getResourceAsStream(reportName);
			jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
			byte[] bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, dataSource);
			FileOutputStream fos = new FileOutputStream(fileName);
			fos.write(bytes, 0, bytes.length);
			inputStream.close();
			fos.close();
		}
		catch (Exception e)
		{
			throw new ServiceException("生成报表出错", e);
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
					throw new ServiceException("生成报表出错", e);
				}
			}
		}
		return fileName;
	}
	/**
	 * 写报表(自己提供数据连接)
	 * 
	 * @author 
	 * @created 
	 * @param reportName
	 * @param parameters
	 * @param fileName
	 * @param dataSource
	 * @return
	 * @throws ServiceException
	 */
	public String writeFile(String reportName, Map<String, Object> parameters, String fileName,Connection connection) throws ServiceException
	{
//		Connection connection = null;
		InputStream inputStream = null;	
		try
		{
			inputStream = this.getClass().getResourceAsStream(reportName);;
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
//			connection = dataSource.getConnection();
			byte[] bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, connection);
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
			// 关闭数据库连接
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
