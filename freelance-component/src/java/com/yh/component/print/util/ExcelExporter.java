package com.yh.component.print.util;


import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * excel exporter
 * 
 * @author eric
 */
public class ExcelExporter implements Exporter
{
    public String getContentType() {
        return "application/vnd.ms-excel";
    }

    public String getExtension() {
        return ".xls";
    }

    public void export(ExportModel model,OutputStream output) throws IOException {
        try
        {
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("-");

            int rowNum = 0;
            int colNum = 0;

            // create an header row
            HSSFRow xlsRow = sheet.createRow(rowNum++);
            HSSFCellStyle headerStyle = wb.createCellStyle();
            //headerStyle.setFillPattern(HSSFCellStyle.FINE_DOTS);
            //headerStyle.setFillBackgroundColor(HSSFColor.BLUE_GREY.index);
            HSSFFont bold = wb.createFont();
            bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            bold.setColor(HSSFColor.BLACK.index);
            headerStyle.setFont(bold);

            String[][] columns=model.getColumns();
            for(String[] ss:columns) {
                @SuppressWarnings("deprecation")
				HSSFCell cell = xlsRow.createCell((short) colNum++);
                cell.setCellStyle(headerStyle);
                cell.setCellValue(ss[1]);
            }

            // iterator on rows
            while(model.next()) {
                xlsRow = sheet.createRow(rowNum++);
                colNum = 0;

                for(String[] ss:columns) {
                    String s=model.getString(ss[0]);

                    @SuppressWarnings("deprecation")
					HSSFCell cell = xlsRow.createCell((short) colNum++);
                    cell.setCellValue(s);
                }
            }
            
            model.close();
            wb.write(output);
        }
        catch (Exception e)
        {
            throw new IOException(e.toString());
        }        
    }

}
