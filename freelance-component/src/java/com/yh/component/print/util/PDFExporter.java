package com.yh.component.print.util;



import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.lang.StringUtils;

import com.yh.platform.core.util.DateUtil;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

/**
 * pdf exporter
 * 
 * @author eric
 */
public class PDFExporter extends AbstractItextExporter
{
    public String getContentType() {
        return "application/pdf";
    }

    public String getExtension() {
        return ".pdf";
    }
   /**
    *  构建PDF五步骤
	 * ①建立com.lowagie.text.Document对象的实例
	 * ②建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中
	 * ③打开文档
	 * ④向文档中添加内容。
	 * ⑤关闭文档
	 * 注：构造 table 此处用com.lowagie.text.pdf.PdfPTable,
	 * 没有用 com.lowagie.text.Table  table 的 每一行无法设定最小高度，导致每一页的格式不固定 ;
	 * 分页时如果添加分布事件时，打印出来的 表样不一致，
    * 重写父类的方法
    * 
    */
    public void export(ExportModel model, OutputStream output) throws IOException {
        try {
        	//构造PDF对象（pageSize:A4纸,rotate()：横向）
        	Document document = new Document(PageSize.A4.rotate(), 60, 60, 40, 40);
        	//pdf创建时间
        	document.addCreationDate();
        	//取得参数 参数一定要放在 取得writer对象之前
        	parameters = model.getParameters();
        	//writer对象实例
        	getWriterInstance(document, output);
        	//列数/列头
            String[][] columns=model.getColumns();
            //table 对象
            PdfPTable table = new PdfPTable(columns.length+1);
            //设置table元素的默认边界
            table.getDefaultCell().setPadding(1);
            //表格大小 %比
            table.setWidthPercentage(100);
            //表格头2行为表头，分布时会自动循环
            table.setHeaderRows(2);
            //9号中文字体
            Font   smallFont  =    getZNfont();
            
            //表头构造
            PdfPCell f1 = getPDFCell("制表时间："+DateUtil.nowString(),smallFont);
            //所占列数 
            f1.setColspan(columns.length+1);
            //设置边框
            f1.setBorder(Rectangle.NO_BORDER);
           //设置方向--居右
            f1.setHorizontalAlignment(PdfPTable.ALIGN_RIGHT);
            //添加到Table中
            table.addCell(f1);
            PdfPCell xh = getPDFCell("序号",smallFont);
            xh.setGrayFill(0.9f);
            table.addCell(xh);
            //设置table 头列
            for(String[] ss:columns) {
            	PdfPCell hdrCell = getPDFCell(ss[1],smallFont);
                hdrCell.setGrayFill(0.9f);
                table.addCell(hdrCell);
            }
            int i =1;
            //设置table 数据列
            while(model.next()) {
            	String x=String.valueOf(i);
            	PdfPCell c = getPDFCell(x,getZNfont(8));
            	table.addCell(c);
                for(String[] ss:columns) {
                    String s=model.getString(ss[0]);
                    PdfPCell cell = getPDFCell(s,getZNfont(8));
                    table.addCell(cell);
                }
                i++;
            }
            model.close();
           //设置页脚
            HeaderFooter footer = new HeaderFooter(new Phrase("", smallFont), true);
            footer.setBorder(Rectangle.NO_BORDER);
            footer.setAlignment(Element.ALIGN_CENTER);
            //开始写pdf 
            document.open();
            document.setFooter(footer);
            //文件内容（一定要写在open 与 close 中间）
            document.add(table);
            document.close();
        } catch (Exception e) {
            throw new IOException(e.toString());
        }
    }
    /**
     * 取得写实例
     * 在写实例中添加 写 水印事件
     * 设置页眉
     */
    protected void getWriterInstance(Document document, OutputStream output) {
        try {
        	PdfWriter writer =  PdfWriter.getInstance(document,output);
        	//设置水印
        	if(null!=parameters.get("waterMask")&& null!=parameters.get("waterMaskDate")){
        		
        		PdfPageEventHelper p = new WaterMaskEvent((String) parameters.get("waterMask"),(String) parameters.get("waterMaskDate"));
        		writer.setPageEvent(p);
        	}else if(null!=parameters.get("waterMask"))
        	{
        		PdfPageEventHelper p = new WaterMaskEvent((String) parameters.get("waterMask"));
        		writer.setPageEvent(p);
        	}
        	
            //设置页眉
        	if(null!=parameters.get("header")){
        		HeaderFooter header = new HeaderFooter(new Phrase((String) parameters.get("header"),getZNfont(16)),false);
        		header.setBorder(Rectangle.NO_BORDER);
        		header.setAlignment(Element.ALIGN_CENTER);
        		document.setHeader(header);
        	}
            
        } catch (Exception de) {
            throw new RuntimeException(de);
        }
    }
    /**
     * 构造格子元素
     * 对齐方式居中
     * 格子最底高度20
     * @param value
     * @param smallFont
     * @return
     * @throws BadElementException
     */
    protected PdfPCell getPDFCell(String value,Font smallFont) throws BadElementException
    {
    	PdfPCell cell = new PdfPCell(new Paragraph(StringUtils.trimToEmpty(value),smallFont));
    	//设置横向对齐方式
    	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	//设置纵向对齐方式
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
       //设置格子最底高度;
        cell.setMinimumHeight(10);
        return cell;
    }
   /**
    * 向表格中添加格子元素
    * @param cellTexts 添加的内容数组
    * @param table	要 添加到的表
    * @param smallFont 字体设置
 * @throws BadElementException 
    */
    protected void tableAddCell(String[] cellTexts,PdfPTable table,Font smallFont) throws BadElementException{
    	if(null!=cellTexts && cellTexts.length>0){
    		for(int i = 0;i<cellTexts.length;i++){
    			
    			table.addCell(getPDFCell(cellTexts[i], smallFont));
    		}
    	}
    }
}
