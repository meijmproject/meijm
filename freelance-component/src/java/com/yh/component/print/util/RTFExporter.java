package com.yh.component.print.util;


import java.io.OutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.rtf.RtfWriter2;

/**
 * htf exporter
 * 
 * @author eric
 */
public class RTFExporter extends AbstractItextExporter
{
    public String getContentType() {
        return "application/rtf";
    }

    public String getExtension() {
        return ".rtf";
    }

    @Override
    protected void getWriterInstance(Document document, OutputStream output) {
        RtfWriter2.getInstance(document,output);
    }

}
