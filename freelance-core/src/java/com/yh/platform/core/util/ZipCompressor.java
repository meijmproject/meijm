package com.yh.platform.core.util;

/**
 * Created by weizh on 2016/7/8.
 */

import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

public class ZipCompressor {
    protected static Logger logger = Logger.getLogger("ZipCompressor");
    /**
     * 实现将多个文件进行压缩，生成指定目录下的指定名字的压缩文件
     * Parameters:
     filename ：指定生成的压缩文件的名称
     temp_path ：指定生成的压缩文件所存放的目录
     list ：List集合：用于存放多个File（文件）
     * */
    public static File createZip(String filename, String temp_path,
                                  List<File> list) {
        File file = new File(temp_path);
        File zipFile = new File(temp_path+File.separator+filename);
        InputStream input = null;
        try {
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
            zipOut.setEncoding("UTF-8");
            zipOut.setComment(file.getName());
            if (file.isDirectory()) {
                for (int i = 0; i < list.size(); ++i) {
                    input = new FileInputStream(list.get(i));
                    zipOut.putNextEntry(new ZipEntry( file.getName()+ File.separator + list.get(i).getName()));
                    int temp = 0;
                    while ((temp = input.read()) != -1) {
                        zipOut.write(temp);
                    }
                    input.close();
                }
            }
            zipOut.close();
        } catch (Exception e) {
            logger.error(e);
        }
        return zipFile;
    }
}