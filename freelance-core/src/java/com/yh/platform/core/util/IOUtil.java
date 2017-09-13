/**
 * define the common functions
 */
package com.yh.platform.core.util;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author chenkb
 */
public class IOUtil {
    private static final Logger log = LogManager.getLogger(IOUtil.class);

    /**
     * Wtite file to response
     *
     * @param response
     * @param file
     * @throws java.io.IOException
     */
    public static void writeZipResponse(HttpServletResponse response, File file) throws IOException {
        writeResponse(response, file, "application/x-zip-compressed");
    }

    public static File zipFiles(List<File> files, String zipFileName) throws IOException {
        if (files == null || files.size() == 0) {
            return null;
        }
        if (zipFileName == null || "".equals(zipFileName.trim()) || zipFileName.endsWith(File.separator)) {
            return null;
        }
        if (!zipFileName.endsWith(".zip")) {
            zipFileName += ".zip";
        }

        File zipFile = new File(zipFileName);

        FileOutputStream fos = new FileOutputStream(zipFile);
        ZipOutputStream zop = new ZipOutputStream(fos);
        zop.setMethod(ZipOutputStream.DEFLATED);

        try {
            for (File file : files) {
                ZipEntry zn = new ZipEntry(file.getName());
                zop.putNextEntry(zn);
                FileInputStream fileIn = new FileInputStream(file);
                long fileLen = file.length();
                int readBytes = 0;
                int totalRead = 0;
                byte b[] = new byte[6500];
                try {
                    while ((long) totalRead < fileLen) {
                        readBytes = fileIn.read(b, 0, 6500);
                        totalRead += readBytes;
                        zop.write(b, 0, readBytes);
                    }
                } catch (IOException ioe) {
                    log.error("write file " + file.getName() + " to zip error.");
                } finally {
                    zop.closeEntry();
                    fileIn.close();
                }
            }
        } finally {
            zop.finish();
            zop.close();
            fos.flush();
            fos.close();
        }
        return zipFile;
    }

    public static File zipFile(File file) throws IOException {
        if (file == null) {
            return null;
        }
        String fileName = file.getPath();
        String zipFileName = fileName + ".zip";
        if (fileName.contains(".")) {
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
        try {
            while ((long) totalRead < fileLen) {
                readBytes = fileIn.read(b, 0, 6500);
                totalRead += readBytes;
                zop.write(b, 0, readBytes);
            }
        } catch (IOException ioe) {
            log.error("write file to zip error.");
        } finally {
            zop.closeEntry();
            zop.close();
            fileIn.close();
            fos.flush();
            fos.close();
        }

        // delete orign file
        file.delete();

        return zipFile;
    }

    public static void zipFile(File file, HttpServletResponse response) throws IOException {

        if (response == null) {
            return;
        }

        File zipFile = zipFile(file);
        writeZipResponse(response, zipFile);
    }

    public static void zipFile(String fileName, String filePath, byte[] bytes, HttpServletResponse response)
            throws IOException {
        if (fileName == null) {
            return;
        }
        if (response == null) {
            return;
        }

        String fileDir = ConfigUtil.getHomePath() + filePath;
        if (!fileDir.endsWith("/"))
            fileDir = fileDir + "/";
        fileDir = fileDir.replace('/', File.separatorChar);
        File dir = new File(fileDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String newFileName = fileDir + fileName;

        File file = new File(newFileName);
        FileOutputStream fos = new FileOutputStream(file);
        try {
            fos.write(bytes);
        } catch (IOException ioe) {
            log.error("write bytes to file error. may be filePath not exist!");
        } finally {
            fos.close();
        }


        zipFile(file, response);
    }

    public static String zipFolder(String folderPathName) throws IOException {
        return zipFolder(folderPathName, null);
    }

    public static String zipFolder(String folderPathName, String zipFileNameWithoutPath) throws IOException {
        File file = new File(folderPathName);
        String folderName = file.getName();

        String zipFileName = null;
        if (zipFileNameWithoutPath == null) {
            zipFileName = folderPathName + ".zip";
        } else {
            if (zipFileNameWithoutPath.indexOf(".") != -1) {
                zipFileNameWithoutPath = zipFileNameWithoutPath.substring(0, zipFileNameWithoutPath.indexOf("."));
            }
            zipFileName = folderPathName.substring(0, folderPathName.indexOf(folderName)) + zipFileNameWithoutPath + ".zip";
        }

        File zipFile = new File(zipFileName);
        FileOutputStream fos = new FileOutputStream(zipFile);
        ZipOutputStream zop = new ZipOutputStream(fos);
        zop.setMethod(ZipOutputStream.DEFLATED);
        try {
            zipFolderFile(file, zop);
        } finally {
            zop.closeEntry();
            zop.close();
            fos.flush();
            fos.close();
        }

        return zipFileName;
    }

    private static void zipFolderFile(File file, ZipOutputStream zop) throws IOException {
        if (file.isDirectory()) {
            File[] fileLst = file.listFiles();
            for (File f : fileLst) {
                zipFolderFile(f, zop);
            }
        } else {
            ZipEntry zn = new ZipEntry(file.getName());
            zop.putNextEntry(zn);
            FileInputStream fileIn = new FileInputStream(file);
            long fileLen = file.length();
            int readBytes = 0;
            int totalRead = 0;
            byte b[] = new byte[6500];
            try {
                while ((long) totalRead < fileLen) {
                    readBytes = fileIn.read(b, 0, 6500);
                    totalRead += readBytes;
                    zop.write(b, 0, readBytes);
                }
            } catch (IOException ioe) {
                log.error("write file to zip error.");
            } finally {
                fileIn.close();
            }
        }
        file.delete();
    }

	/**
	 * window文件路径格式转换为linux文件路径格式（去盘符，“\”转为“/”）
	 * @param path
	 * @return
	 */
	public static String filePathReverse(String path) {
		return path.replaceFirst("^.*:", "").replaceAll("\\\\", "/");
	}
	
    public static void main(String[] arg) {
        try {
            String path = "D:/sharenfs/common/szghrs/temp1";
            System.out.println(zipFolder(path, null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int BUFFER_SIZE = 4096;

    public static byte[] readInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[BUFFER_SIZE];
        int len;
        while ((len = is.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }

        return baos.toByteArray();
    }

    public static long copyStream(InputStream is, OutputStream os)
            throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        int len;
        long total = 0;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
            total += len;
        }

        return total;
    }

    /**
     * Close InputStream or OutputStream silently, no exception will be throwed
     * event the stream is null.
     * <p/>
     * <pre>
     *
     *
     *
     *
     *        For example:
     *        InputStream is=null;
     *        OutputStream os=null;
     *        try
     *        {
     *             create streams and do something...
     *        }
     *        catch(some exception)
     *        {
     *             deal with errors...
     *        }
     *        finally
     *        {
     *             StreamUtils.closeStream(is,os);
     *        }
     *
     *
     *
     *
     * </pre>
     *
     * @param streams streams want to close
     */
    public static void closeStream(Closeable... streams) {
        for (Closeable stream : streams) {
                try {
                	if (stream != null)
                		stream.close();
                } catch (IOException ioi) {/* ignore */
                }
        }
    }

    /**
     * copy a file
     *
     * @param inf
     * @param outf
     * @throws IOException
     */
    public static void copyFile(File inf, File outf) throws IOException {
        InputStream is = new FileInputStream(inf);
        OutputStream os = new FileOutputStream(outf);
        copyStream(is, os);
        closeStream(is, os);
    }

    /**
     * Close writer silently, no exception will be throwed event the writer is
     * null.
     *
     * @param writers writers want to close
     */
    public static void closeWriter(Writer... writers) {
        for (Writer writer : writers) {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ioi) {/* ignore */
                }
            }
        }// for each writer
    }

    /**
     * Close reader silently, no exception will be throwed event the reader is
     * null.
     *
     * @param readers readers want to close
     */
    public static void closeReader(Reader... readers) {
        for (Reader reader : readers) {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioi) {/* ignore */
                }
            }
        }// for each writer
    }
    @SuppressWarnings("rawtypes") 
    public static InputStream getPackageRes(Class c, String fn) {
        String name = c.getPackage().getName().replace('.', '/');
        name += "/" + fn;
        return c.getClassLoader().getResourceAsStream(name);
    }

    /**
     * Delete a whole directory tree starting at the given directory file.
     *
     * @param f the file or directory to be deleted.
     * @return <tt>true</tt> if the file is deleted.
     */
    static public boolean deleteAll(File f) {
        if (f.isDirectory()) {
            File[] contents = f.listFiles();
            if (contents != null) {
                for (File file : contents) {
                    deleteAll(file);
                }
            }
        }
        return f.delete();
    }

    /**
     * @param response
     * @param file
     * @param fileType
     * @throws IOException
     */
    public static void writeResponse(HttpServletResponse response, File file, String fileType) throws IOException {
        String CONTENT_TYPE_FILE_STREAM = fileType;
        if (StringUtils.isEmpty(fileType)) {
            CONTENT_TYPE_FILE_STREAM = "application/pdf";
        }


        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        FileInputStream in = new FileInputStream(file);
        byte[] returnPdf = new byte[in.available()];
        in.read(returnPdf);
        bos.write(returnPdf);

        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
        response.setContentType(CONTENT_TYPE_FILE_STREAM);
        response.setContentLength(returnPdf.length);
        bos.writeTo(response.getOutputStream());

        in.close();
        bos.flush();
        bos.close();

    }

    /**
     * @param response
     * @param out
     * @param fileType
     * @param fileName
     * @throws IOException
     */
    public static void writeResponse(HttpServletResponse response, ByteArrayOutputStream out, String fileType, String fileName) throws IOException {
        String CONTENT_TYPE_FILE_STREAM = fileType;
        if (StringUtils.isEmpty(fileType)) {
            CONTENT_TYPE_FILE_STREAM = "application/pdf";
        }
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setContentType(CONTENT_TYPE_FILE_STREAM);
        response.setContentLength(out.size());
        out.writeTo(response.getOutputStream());
        out.flush();
        out.close();
    }
    
    /**
     * @param response
     * @param file
     * @param fileType
     * @throws IOException
     */
    public static void writeResponse(HttpServletResponse response, File file, String fileType, String fileDesc) throws IOException {
        String CONTENT_TYPE_FILE_STREAM = fileType;
        if (StringUtils.isEmpty(fileType)) {
            CONTENT_TYPE_FILE_STREAM = "application/pdf";
        }


        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        FileInputStream in = new FileInputStream(file);
        byte[] returnPdf = new byte[in.available()];
        in.read(returnPdf);
        bos.write(returnPdf);

        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileDesc, "UTF-8"));
        response.setContentType(CONTENT_TYPE_FILE_STREAM);
        response.setContentLength(returnPdf.length);
        bos.writeTo(response.getOutputStream());
        
        in.close();
        bos.flush();
        bos.close();
    }
}
