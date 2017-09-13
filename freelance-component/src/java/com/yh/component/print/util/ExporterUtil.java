package com.yh.component.print.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * exporter util
 * 
 * @author eric
 */
@SuppressWarnings("rawtypes")
public class ExporterUtil
{
	 /**
	 * 导出工具类
	 * 注:如是导出PDF 且要加水印，加表头  需要构造 参数 
	 *  Map<String,Object> parameters  = new HashMap<String,Object>();
	 *  parameters.put("waterMask", XXXX);  //水印
	 *  parameters.put("header", XXXX); //表头
	 * @param exporter_id: "csv","excel","pdf","xml","rtf","html"，
	 *  也可是自己的实现类 ："xxx.xxx.myClass" 必须 impl Exporter; 
	 *  如果："xxx.xxx.myClass" 为 PDF，html,rtf,可 extends  AbstractItextExporter
	 * @param columns {{"name","姓名"},...}; 
	 * @param list 表格中的数据 
	 * @param resp
	 * @param filename 为构造的参数，如是自己的实现类可支持各种参数的扩展   Map<String,Object> parameters  = new HashMap<String,Object>();
    	parameters.put("filename", filename); 
	 * @throws IOException
	 */
    public static void export(String exporter_id,String[][] columns,List<? extends Object> list,HttpServletResponse resp,String filename) throws IOException {
    	Map<String,Object> parameters  = new HashMap<String,Object>();
    	parameters.put("filename", filename);
    	Exporter exporter=ExporterFactory.getExporter(exporter_id);
    	export(exporter, columns, list, resp, parameters);
    }
    /**
	 * 导出工具类
	 * 注:如是导出PDF 且要加水印，加表头  需要构造 参数 
	 *  Map<String,Object> parameters  = new HashMap<String,Object>();
	 *  parameters.put("waterMask", XXXX); 
	 *  parameters.put("header", XXXX); 
	 * @param exporter_id: "csv","excel","pdf","xml","rtf","html"，
	 *  也可是自己的实现类 ："xxx.xxx.myClass" 必须 impl Exporter; 
	 *  如果："xxx.xxx.myClass" 为 PDF，html,rtf,可 extends  AbstractItextExporter
	 * @param columns {{"name","姓名"},...}; 
	 * @param list 表格中的数据 
	 * @param resp
	 * @param parameters 为构造的参数，如是自己的实现类可支持各种参数的扩展   Map<String,Object> parameters  = new HashMap<String,Object>();
    	parameters.put("filename", filename); //文件名
	 * @throws IOException
	 */
	public static void export(Exporter exporterImpl,String[][] columns,List<? extends Object> list,HttpServletResponse resp,Map parameters) throws IOException {
        
        resp.setContentType(exporterImpl.getContentType());
        resp.setHeader("Content-Disposition","attachment;filename="+URLEncoder.encode((String) (null==parameters.get("filename")?"temp":parameters.get("filename")) ,"UTF-8")+exporterImpl.getExtension());
        ServletOutputStream output=resp.getOutputStream();
        
        BeanListExportModel model=new BeanListExportModel(columns,list,parameters);
        exporterImpl.export(model,output);
    }
}
