package com.yh.component.print.util;


import java.io.OutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.html.HtmlWriter;

/**
 * html exporter
 * 
 * @author eric
 */
public class HtmlExporter extends AbstractItextExporter
{
    public String getContentType() {
        return "text/html";
    }

    public String getExtension() {
        return ".html";
    }

    @Override
    protected void getWriterInstance(Document document, OutputStream output) {
        HtmlWriter.getInstance(document,output);
        
    }

}
