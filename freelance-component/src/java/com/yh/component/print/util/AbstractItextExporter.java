package com.yh.component.print.util;


import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;

/**
 * abstract itext exporter support
 * @author eric
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractItextExporter implements Exporter
{
	protected Map parameters;
    public void export(ExportModel model, OutputStream output) throws IOException {
        try {
            String[][] columns=model.getColumns();

            Table table = new Table(columns.length);
            //table.setDefaultVerticalAlignment(Element.ALIGN_TOP);
            table.setCellsFitPage(true);
            table.setWidth(100);

            table.setPadding(2);
            table.setSpacing(0);

            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font smallFont = new Font(bfChinese, 12, Font.NORMAL);

            Document document = new Document(PageSize.A4.rotate(), 60, 60, 40, 40);
            document.addCreationDate();
            HeaderFooter footer = new HeaderFooter(new Phrase("", smallFont), true);
            footer.setBorder(Rectangle.NO_BORDER);
            footer.setAlignment(Element.ALIGN_CENTER);

            getWriterInstance(document, output);
            
            //write column header
            for(String[] ss:columns) {
                Cell hdrCell = getCell(ss[1],smallFont);
                hdrCell.setGrayFill(0.9f);
                hdrCell.setHeader(true);
                table.addCell(hdrCell);
            }
            table.endHeaders();
            
            while(model.next()) {
                for(String[] ss:columns) {
                    String s=model.getString(ss[0]);

                    Cell cell = getCell(s,smallFont);
                    table.addCell(cell);
                }
            }
            
            model.close();

            document.open();
            document.setFooter(footer);
            document.add(table);
            document.close();

        } catch (Exception e) {
            throw new IOException(e.toString());
        }
    }

    protected abstract void getWriterInstance(Document document,OutputStream output);
    
    protected Cell getCell(String value,Font smallFont) throws BadElementException
    {
        Cell cell = new Cell(new Chunk(StringUtils.trimToEmpty(value), smallFont));
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setLeading(8);
        return cell;
    }
    /**
     * 取得中文字体
     * 参数为字体大小
     * @param fontSize
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    protected Font getZNfont(int fontSize) throws DocumentException, IOException{
    	 BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
         Font smallFont = new Font(bfChinese, fontSize, Font.NORMAL);
          return smallFont;
    }
    /**
     * 取得9号中文字体
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    protected Font getZNfont() throws DocumentException, IOException{
    	return getZNfont(9);
   }
}
