package com.yh.component.print.util;


import java.util.HashMap;

/**
 * exporter factory
 *  
 * @author eric
 */
public class ExporterFactory
{
    private static final HashMap<String,Exporter> s_exporters=new HashMap<String,Exporter>();
    
    static {
        s_exporters.put("csv",new CSVExporter());
        s_exporters.put("excel",new ExcelExporter());
        s_exporters.put("pdf",new PDFExporter());
        s_exporters.put("xml",new XMLExporter());
        s_exporters.put("rtf",new RTFExporter());
        s_exporters.put("html",new HtmlExporter());
    }
    /**
     * 工厂取得实现
     * 如Map中没有默认实现则通过反射取自定义的实现
     * exporter_id =“xxx.xxx.xx.Myclass”
     * @param exporter_id
     * @return
     */
    public static Exporter getExporter(String exporter_id) {
    	if(null!=s_exporters.get(exporter_id)){
    		return s_exporters.get(exporter_id);
    	}else{
			try {
				Exporter obj  =  (Exporter) Class.forName(exporter_id).newInstance();
				return 	obj;
			} catch (Exception e) {
				return null;
			}
    	}
    }
}
