package com.yh.component.print.util;


import java.io.IOException;
import java.io.OutputStream;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/**
 * xml exporter
 * 
 * @author eric
 */
public class XMLExporter implements Exporter
{
    public String getContentType() {
        return "text/xml";
    }

    public String getExtension() {
        return ".xml";
    }

    public void export(ExportModel model,OutputStream output) throws IOException {
        
        String[][] columns=model.getColumns();
        Element list_e=new Element("list");
        
        while(model.next()) {
            Element row_e=new Element("row");
            list_e.addContent(row_e);
            
            for(String[] ss:columns) {
                String s=model.getString(ss[0]);
                
                Element e=new Element(ss[0]);
                row_e.addContent(e);
                e.setText(s);
            }
        }
        
        model.close();

        XMLOutputter xmlout=new XMLOutputter();
        
        Document doc=new Document();
        doc.setRootElement(list_e);
        
        xmlout.output(doc,output);
    }

}
