package com.yh.component.print.util;


import java.awt.Color;

import com.yh.platform.core.util.DateUtil;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

public class WaterMaskEvent extends PdfPageEventHelper{
	private String waterMask;
	private String waterMaskDate;
	/**
	 * 重写父类方法
	 * 1、在每页结束时都会执行此方法
	 */
	public void onEndPage(PdfWriter arg0, Document arg1) {
		addmark(arg0, arg1);
	}
	/**
	 * 自定义水印构造函数
	 * @param waterMask
	 */
	public WaterMaskEvent(String waterMask){
		this.waterMask = waterMask;
	}
	/**
	 * 自定义水印构造函数
	 * @param waterMask
	 * @param waterMaskDate
	 */
	public WaterMaskEvent(String waterMask, String waterMaskDate) {
		super();
		this.waterMask = waterMask;
		this.waterMaskDate = waterMaskDate;
	}
	/**
	 * 默认水印构造函数（当前日期）
	 */
	public WaterMaskEvent(){
		this.waterMaskDate = DateUtil.nowString();
	}
	/**
	 * 添加水印方法
	 * 底层加水印居中
	 * @param writer
	 * @param document
	 */
	private void addmark(PdfWriter writer,Document document){
	try {
		//拿到最底层的背景层
	    PdfContentByte under =  writer.getDirectContentUnder();
     	//设置中文字体
	    BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
	    //取得页面宽度
		float x = document.getPageSize().getWidth();  
		//取得页面高度
	    float y = document.getPageSize().getHeight();  
	    float fontSize = 48f;
	    under.setFontAndSize(bfChinese, fontSize);
	    //开始
	    under.setColorFill(Color.LIGHT_GRAY);  
     	under.beginText();  
     	
     	//定位水印位置(居中)（必须写在 beginText 和endText 中间）
     	//(页面宽度-字体所占宽度/2)/2(横向) 
     	//页面高度/2(纵向)  
     	under.setTextMatrix((x-(fontSize*stringLength(waterMask))/2)/2+15,y/2);
     	//斜度 
//      under.setTextRise(rise);  
//        under.showText(waterMask);  
     	under.newlineShowText(waterMask);
     	//添加第二个水印
     	if(null!=waterMask && null!=waterMaskDate){
     		under.setTextMatrix(x-(fontSize*stringLength(waterMaskDate))/2-25,y/2-fontSize);
     		under.newlineShowText(waterMaskDate);
     	}
     	under.endText();
//	    ColumnText.showTextAligned(under, Element.ALIGN_CENTER, new Phrase(waterMark, new Font(bfChinese,48f)),(x-fontSize*waterMark.length())/2, y/2, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 得到字符串转换后的长度(汉字占两个位置)
	 * @author luzw 2013-09-23
	 * @param str
	 * @return
	 */
	private  int stringLength(String str)
	{
		int count = 0;
		if(str == null)
		{
			return 0;
		}
		for(int i = 0; i < str.length(); i++)
		{
			if(str.charAt(i) >= 256)
			{
				count+=2;
			}else
			{
				count+=1;
			}
		}
		return count;
	}
}
