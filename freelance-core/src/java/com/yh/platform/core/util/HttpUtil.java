package com.yh.platform.core.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.yh.platform.core.exception.ServiceException;

/**
 * 
 * @author wanxm
 *
 */
public class HttpUtil {

	/**
	 * Wtite file to response
	 * @param response
	 * @param file
	 * @throws IOException
	 * @throws ServiceException 
	 */
	public static  void writeResponse(HttpServletResponse response, File file,String fileType) throws IOException, ServiceException
	{
		writeResponse(response,file,fileType,false);
	}
	
	/**
	 * @param response
	 * @param file
	 * @param fileType
	 * @param download
	 * @throws IOException
	 * @throws ServiceException
	 */
	public static  void writeResponse(HttpServletResponse response, File file,String fileType,boolean download) throws IOException, ServiceException
	{
		String CONTENT_TYPE_FILE_STREAM = fileType;
		if (StringUtils.isEmpty(fileType))
		{
			CONTENT_TYPE_FILE_STREAM = "application/pdf";
		}

		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] returnPdf = null;
		try
		{
			
			FileInputStream in = new FileInputStream(file);

			byte[] pdf = new byte[in.available()];
			in.read(pdf);
			in.close();
			returnPdf = pdf;

		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ServiceException("errors.service.common", "系统异常。");
		}
		bos.write(returnPdf);

		if (download)
		{
			response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
		}
		response.setContentType(CONTENT_TYPE_FILE_STREAM);
		response.setContentLength(returnPdf.length);
		bos.writeTo(response.getOutputStream());

		bos.flush();
		bos.close();
		
	}
	
	/**
	 * 打印报表是是否加密
	 * @param response
	 * @param bytes
	 * @param fileType
	 * @param fileName
	 * @param download
	 * @throws IOException
	 * @throws ServiceException
	 */
	public static  void writeResponse(HttpServletResponse response, byte[] bytes,String fileType,String fileName,boolean download) throws IOException, ServiceException
	{
		String CONTENT_TYPE_FILE_STREAM = fileType;
		if (StringUtils.isEmpty(fileType))
		{
			CONTENT_TYPE_FILE_STREAM = "application/pdf";
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		bos.write(bytes);
		if (download)
		{
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		}
		response.setContentType(CONTENT_TYPE_FILE_STREAM);
		response.setContentLength(bytes.length);
		bos.writeTo(response.getOutputStream());
		bos.flush();
		bos.close();
	}
	/**输出字节流
	 * @param bytes
	 * @param fileName
	 * @return
	 * @throws ServiceException
	 */
	public static String writeFile(byte[] bytes, String fileName) throws ServiceException
	{
		try
		{
			FileOutputStream fos = new FileOutputStream(fileName);
			fos.write(bytes, 0, bytes.length);
			fos.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ServiceException("",e);
		}
		finally
		{
		}
		return fileName;
	}
}
