package com.yh.component.print.util;

import java.io.IOException;
import java.io.OutputStream;

/**
 * exporter interface
 * 
 * @author eric
 */
public interface Exporter
{
	/**
	 * 声明类别
	 * @return
	 */
    public String getContentType();
    
    /**
     * 声明后缀
     * @return
     */
    public String getExtension();
    /**
     * 导出
     * @param model
     * @param output
     * @throws IOException
     */
    public void export(ExportModel model,OutputStream output) throws IOException;
}
