/**
 * define the common functions
 */
package com.yh.platform.core.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.web.UserContext;

/**
 * @author chenkb
 *
 */
public class CommonFunctions
{
	private static final Logger log = LogManager.getLogger(CommonFunctions.class);
	
	/**
	 * Get current user context
	 * @param request
	 * @return
	 */
	public static UserContext getLogInUser(javax.servlet.http.HttpServletRequest request)
	{
		return (UserContext) request.getSession().getAttribute(UserContext.SESSION_CONTEXT);
	}

	/**
	 * Get current user id
	 * @param request
	 * @return
	 */
	public static String getLoginUserID(javax.servlet.http.HttpServletRequest request)
	{
		UserContext userCtx = CommonFunctions.getLogInUser(request);

		String uid = "unknow";
		if (userCtx != null)
			uid = userCtx.getUid();

		return uid;
	}
	
	/**
	 * is security check required
	 * @return
	 */
	public static boolean isSecurityCheckRequired()
	{
		return "Y".equals(ConfigUtil.getProperty("security.check.enabled"));
	}
	
	public static boolean isPswdCheckRequired()
	{
		return "Y".equals(ConfigUtil.getProperty("password.check.enabled"));
	}
	
	public static String getRandom(int maxValue)
	{		
		SecureRandom random = new SecureRandom();
		return String.valueOf(random.nextInt(maxValue));
	}
	
	public static String getHomePath()
	{
		return ConfigUtil.getProperty("home.path");
	}
	
	public static String getDefaultPassword() {
		return ConfigUtil.getProperty("user.defaultPassword");
	}
	
	/**
     * Wtite file to response
     * 
     * @param response
     * @param file
     * @throws IOException
     */
    public static void writeResponse(HttpServletResponse response, File file) throws IOException
    {
        try
        {
            int BYTE_ARRAY_INITIAL_SIZE = 1024;
            ByteArrayOutputStream bos = new ByteArrayOutputStream(BYTE_ARRAY_INITIAL_SIZE);

            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);

            byte[] buf = new byte[BYTE_ARRAY_INITIAL_SIZE];
            int total = 0;
            int offset = 0;
            while ((total = bis.read(buf, 0, buf.length)) != -1)
            {
                bos.write(buf, 0, total);
                offset += total;
            }

            response.setContentType("application/x-zip-compressed");
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
            response.setContentLength(offset);
            OutputStream os = response.getOutputStream();
            try
            {
            	bos.writeTo(os);
            }
            catch(IOException ioe)
            {
            	log.error("write file to response error. may be client request changed or session time out!");
            }
            finally
            {
	            fis.close();
	            bis.close();
	            bos.flush();
	            bos.close();
	            os.close();
            }
        }
       catch (Exception e)
       {
           log.error("write file to response error.");
       }
    }

    /**
     * ���Ѵ����ļ�����ѹ��
     * @param file
     * @return File ѹ�����ļ�
     * @throws ServiceException,IOException
     * */
    public static File zipFile(File file)throws ServiceException,IOException
    {
    	if (file == null)
            throw new ServiceException("error.export.file.null", "", " file is null !");
    	String fileName = file.getPath();
        String zipFileName = fileName + ".zip";
        if (fileName.indexOf(".") != -1)
        {
            zipFileName = fileName.substring(0, fileName.indexOf(".")) + ".zip";
        }

        File zipFile = new File(zipFileName);
        FileOutputStream fos = new FileOutputStream(zipFile);
        ZipOutputStream zop = new ZipOutputStream(fos);
        zop.setMethod(ZipOutputStream.DEFLATED);
        ZipEntry zn = new ZipEntry(file.getName());
        zop.putNextEntry(zn);

        FileInputStream fileIn = new FileInputStream(file);
        long fileLen = file.length();
        int readBytes = 0;
        int totalRead = 0;
        byte b[] = new byte[6500];
        try
        {
	        while ((long) totalRead < fileLen)
	        {
	            readBytes = fileIn.read(b, 0, 6500);
	            totalRead += readBytes;
	            zop.write(b, 0, readBytes);
	        }
        }
	    catch(IOException ioe)
	    {
	    	log.error("write file to zip error.");
	    }
	    finally
	    {
	    	zop.closeEntry();
	        zop.close();
	        fileIn.close();
	        fos.flush();
	        fos.close();
	    }
        
        // delete orign file
        file.delete();
        
        return zipFile ;
    }
    
    public static void zipFile(File file, HttpServletResponse response) throws ServiceException, IOException
    {
       
        if (response == null)
            throw new ServiceException("error.export.response.null", "response is null !");

        File zipFile = zipFile(file);

        writeResponse(response, zipFile);
    }

    public static void zipFile(String fileName, String filePath, byte[] bytes, HttpServletResponse response)
            throws ServiceException, IOException
    {
        if (fileName == null)
            throw new ServiceException("error.export.file.null", "", " file is null !");
        if (response == null)
            throw new ServiceException("error.export.response.null", "response is null !");

        String fileDir = CommonFunctions.getHomePath() + filePath;
        if (fileDir.endsWith("/") == false)
            fileDir = fileDir + "/";
        fileDir = fileDir.replace('/',File.separatorChar);
        File dir = new File(fileDir);
        if (!dir.exists())
        {
            dir.mkdirs();
        }

        String newFileName = fileDir + fileName;

        File file = new File(newFileName);
        FileOutputStream fos = new FileOutputStream(file);
        try
        {
        	fos.write(bytes);
        }
        catch(IOException ioe)
        {
        	log.error("write bytes to file error. may be filePath not exist!");
        }
        finally
        {
        	fos.close();	
        }
        

        zipFile(file, response);
    }

    public static String generateFileName(String originFileName,String fileType)
    {        
        String newFileName = "";
        String extfix = "";
        if("HTML".equalsIgnoreCase(fileType))
        {
        	extfix="html";        
        }
        if("PDF".equalsIgnoreCase(fileType))
        {
        	extfix="pdf";	
        }
        if("EXCEL".equalsIgnoreCase(fileType)||"xls".equals(fileType))
        {
        	extfix="xls";
        }
        if ("CSV".equalsIgnoreCase(fileType))
        {
        	extfix="csv";	
        }
        newFileName = originFileName + DateUtil.nowString("yyyyMMddHHmmssSSS")+"."+ extfix;            
        
        return newFileName;
    }
    
    /**
     * ѹ���ļ���
     * @param folderPathName
     * @return
     * @throws IOException
     */
    public static String zipFolder(String folderPathName) throws IOException
    {
    	File file = new File(folderPathName);
    	String fileName = file.getPath();
        String zipFileName = fileName + ".zip";
        if (fileName.indexOf(".") != -1)
        {
            zipFileName = fileName.substring(0, fileName.indexOf(".")) + ".zip";
        }

        File zipFile = new File(zipFileName);
        FileOutputStream fos = new FileOutputStream(zipFile);
        ZipOutputStream zop = new ZipOutputStream(fos);
        zop.setMethod(ZipOutputStream.DEFLATED);
        try
        {
        	zipFolderFile(file,zop);	        
        }
	    finally
	    {
	    	zop.closeEntry();
	        zop.close();
	        fos.flush();
	        fos.close();
	    }
        
        return zipFileName ;
    }
    
    private static void zipFolderFile(File file, ZipOutputStream zop) throws IOException
	{		
		if (file.isDirectory())
		{
			File[] fileLst = file.listFiles();
			for(File f:fileLst)
			{
				zipFolderFile(f,zop);
			}
		}
		else
		{
			ZipEntry zn = new ZipEntry(file.getName());
	        zop.putNextEntry(zn);	
			FileInputStream fileIn = new FileInputStream(file);
			long fileLen = file.length();
			int readBytes = 0;
			int totalRead = 0;
			byte b[] = new byte[6500];
			try
			{
				while ((long) totalRead < fileLen)
				{
					readBytes = fileIn.read(b, 0, 6500);
					totalRead += readBytes;
					zop.write(b, 0, readBytes);
				}
			}
			catch (IOException ioe)
			{
				log.error("write file to zip error.");
			}
			finally
			{
				fileIn.close();
			}
		}
		
		 // delete orign file
        file.delete();
	}
	
	public static void main(String[] arg)
	{
		System.out.println(getRandom(99999999));
	}

}
