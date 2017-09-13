
package com.yh.component.print.util;


import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * csv exporter
 * 
 * @author eric
 */
public class CSVExporter implements Exporter
{
    public String getContentType() {
        return "text/csv";
    }

    public String getExtension() {
        return ".csv";
    }

    public void export(ExportModel model,OutputStream output) throws IOException {
        CSVWriter csv=new CSVWriter(output,',',Charset.defaultCharset());

        try {
            
            //write column header
            String[][] columns=model.getColumns();
            for(String[] ss:columns) {
                    csv.write(ss[1]);
            }
            csv.endRecord();
            
            while(model.next()) {
                for(String[] ss:columns) {
                    String s=model.getString(ss[0]);
                    csv.write(s);
                }
                csv.endRecord();
            }
            
            model.close();
            csv.close();

        } catch (CSVWriter.FinalizedException e) {
            throw new IOException(e.toString());
        }
    }

}
