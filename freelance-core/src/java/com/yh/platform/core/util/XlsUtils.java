package com.yh.platform.core.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.yh.platform.core.exception.ServiceException;

/**
 *@description	Excel工具类
 *
 *@author		zhangqp
 *@created		14/01/14
 *@version		1.0
 */

public class XlsUtils {
	
	/**
	 * 读取excel文件
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static HSSFWorkbook read(String filePath) throws Exception {
		try {
			return new HSSFWorkbook(new FileInputStream(filePath));
		} catch (Exception e) {
			throw new Exception("无法解析的文件！", e);
		}
	}
	
	/**
	 * 读取excel文件
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static HSSFWorkbook read(InputStream in) throws Exception {
		try {
			return new HSSFWorkbook(in);
		} catch (Exception e) {
			throw new Exception("无法解析的文件！", e);
		}
	}
	
	/**
	 * 用于标题列检测是否存在对应的列
	 * @param row
	 * @param titles
	 * @return
	 */
	public static boolean isCellExists(HSSFRow row, String[] titles) {
		int begin = row.getFirstCellNum();
		int end = row.getLastCellNum();
		for (int i=begin; i< end; i++) {
			HSSFCell cell = row.getCell(i);
			if (cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
				int idx = ArrayUtils.indexOf(titles, StringUtils.trim(cell.getStringCellValue()));
				if (idx != -1) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 解析对应标题单元格所在的列索引
	 * @param row
	 * @param cellTtitles 必填项
	 * @param begin 开始解析的列索引 基于0，（-1：从第一列开始）
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Integer> parseHeaderIndex(HSSFRow row, String[] cellTtitles, Integer begin) throws Exception {
		begin = begin == null || begin == -1 ? row.getFirstCellNum() : begin;
		int end = row.getLastCellNum();
		String cellText =null;
		Map<String,Integer> map = new HashMap<String, Integer>();
		boolean validate = cellTtitles.length != 0;
		List<String> titles = new ArrayList<String>(Arrays.asList(cellTtitles));
		for (int i=begin; i< end; i++) {
			HSSFCell cell = row.getCell(i);
			if (cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
				cellText = StringUtils.trim(cell.getStringCellValue());
				if (validate) {
					removeFound(cellText, titles);
				}
				map.put(cellText,  Integer.valueOf(i));
			}
		}
		if (validate && titles.size() > 0) {
			throw new Exception("导入的Excel文件缺少列："+StringUtil.connect(titles.toArray(new String[titles.size()]), "、", null));
		}
		
		return map;
	}
	/**
	 * 解析对应标题单元格所在的列索引
	 * @param firstRow
	 * @param cellTtitles 必填项
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Integer> parseHeaderIndex(HSSFRow row, String[] cellTtitles) throws Exception {
		return parseHeaderIndex(row, cellTtitles, -1);
	}
	/**
	 * 解析对应标题单元格所在的列索引，不验证
	 * @param firstRow
	 * @param cellTtitles
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Integer> parseHeaderIndex(HSSFRow row) throws Exception {
		
		return parseHeaderIndex(row, new String[0], -1);
	}
	
	private static int removeFound(String toFind, List<String> titles) {
		if (CollectionUtils.isEmpty(titles)) return -1; 
		
		int idx = titles.indexOf(toFind);
		
		if (idx != -1) {
			titles.remove(idx);
		}
		
		return idx;
	}
	
	/**
	 * 获取不为空的第一行
	 * @param sheet
	 * @return
	 */
	public static HSSFRow getFirstRow(HSSFSheet sheet) {
		HSSFRow row = sheet.getRow(0);
		if (row != null) return row;
		int rowNum = sheet.getLastRowNum();
		for (int i=1; i<=rowNum;i++) {//第一行为空，则从第二行开始找
			row = sheet.getRow(i);
			if (row != null) return row;
		}
		return null;
	}
	
	/**
	 * 如果有多个sheet，则获取第一个不为空的sheet；
	 * 如果只有一个sheet，则直接返回第一个sheet；
	 * @param excel
	 * @return
	 */
	public static HSSFSheet getSheet(HSSFWorkbook excel) {
		int num = excel.getNumberOfSheets();
		if (num == 0 ) return excel.getSheetAt(0);
		HSSFSheet sheet = null;
		for (int i=0; i<num; i++) {
			sheet = excel.getSheetAt(i);
			if (sheet != null) return sheet;
		}
		return null;
	}
	
	private static final DecimalFormat NUMBER_FORMAT = new DecimalFormat("0");
	/**
	 * 获取单元格的值 同时trim
	 * @param hssfCell
	 * @return
	 */
	public static String getValue(HSSFCell hssfCell) { 
		if (hssfCell == null) return null;
		
        if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) { 
            // 返回布尔类型的值 
            return String.valueOf(hssfCell.getBooleanCellValue()); 
        } 
        if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) { 
        	
            return NUMBER_FORMAT.format(hssfCell.getNumericCellValue());
        }
        // 返回字符串类型的值 
        return StringUtils.trim(hssfCell.getStringCellValue());
    }

	public static String getDateValue(HSSFCell hssfCell) {
        if(hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
            short format = hssfCell.getCellStyle().getDataFormat();
            SimpleDateFormat sdf = null;  
            if(format == 14 || format == 31 || format == 57 || format == 58){  
                //日期  
                sdf = new SimpleDateFormat("yyyy-MM-dd");  
            }else if (format == 20 || format == 32) {  
                //时间  
                sdf = new SimpleDateFormat("HH:mm");  
            }  
            double value = hssfCell.getNumericCellValue();  
            Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
            if(null != date)
            {
            	return sdf.format(date);
            }
        }
		return null;
	}
	
	public static String getValue(HSSFRow row, Integer index) {
		if (index != null && index.intValue() >= 0) {
			return getValue(row.getCell(index));
		}
		return null;
	}
	
	public static String getDateValue(HSSFRow row, Integer index){
		if (index != null && index.intValue() >= 0) {
			return getDateValue(row.getCell(index));
		}
		return null;
	}
	
	/**
	 * 创建工作薄及标题头
	 * @param book
	 * @param sheetName
	 * @param titles
	 * @param headerStyle 
	 * @return
	 */
	public static HSSFSheet createSheet(HSSFWorkbook book, String sheetName, String[] titles, HSSFCellStyle headerStyle) {
		HSSFSheet sheet = book.createSheet(sheetName);
		HSSFRow row = sheet.createRow(0);
		if (!ArrayUtils.isEmpty(titles)) {
			HSSFCell cell = null;
			for (int i=0; i<titles.length; i++) {
				cell = row.createCell(i);
				cell.setCellValue(titles[i]);
				if (headerStyle != null) {
					cell.setCellStyle(headerStyle);
				}
			}
		}
		return sheet;
	}
	
	
	

	
	/**
	 * 设置列宽
	 * @param sheet
	 * @param widths
	 */
	public static void setColumnWidth(HSSFSheet sheet, int ... widths) {
		for (int i=0; i<widths.length; i++) {
			sheet.setColumnWidth(i, widths[i]*256);
		}
	}
	/**
	 * [start,length)
	 * @param sheet
	 * @param width
	 * @param end 数组长度
	 * @param start 开始下标，基于0
	 */
	public static void setAllColumnWidth(HSSFSheet sheet, int width, int length, int start) {
		for (int i=start; i<length; i++) {
			sheet.setColumnWidth(i, width*256);
		}
	}
	
	
	/**
	 * 填充行，可设置是否自动计算行高或列宽（不需要再调用autoSizeColumn）（不需此功能，请使用另外的applyRowData方法）
	 * @param book
	 * @param sheet
	 * @param rowNum
	 * @param rowDatas
	 * @param autoSizeColumns 自动计算列宽的列索引（base 0），若为null，表示计算所有列宽；不为null但为空数组对象，表示不自动计算列宽。
	 * @throws Exception
	 */
	public static void applyRowData(HSSFWorkbook book, HSSFSheet sheet, int rowNum, String[] rowDatas, int[] autoSizeColumns) throws Exception {
		HSSFRow row = sheet.createRow(rowNum);
		HSSFCell cell = null;
//		int maxLine = 0;
		
		HSSFCellStyle style = XlsUtils.Style.applyBlackBorder(book, null);
		HSSFCellStyle wrapStyle = XlsUtils.Style.applyBlackBorder(book, null);
		wrapStyle.setWrapText(autoSizeColumns==null||autoSizeColumns.length > 0);//自动换行
		
		for (int i=0; i<rowDatas.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(rowDatas[i]);
			cell.setCellStyle(style);
			
			if (!StringUtils.isEmpty(rowDatas[i])
					&& (autoSizeColumns == null || ArrayUtils.contains(autoSizeColumns, i))) {
				cell.setCellStyle(wrapStyle);//需要处理换行符时，必须设置自动换行才有效果（否则用此方法计算列宽也无太大意义）
				String[] lines = rowDatas[i].split("\\n");
				int maxLength = 0;
				for (String s : lines) {
					maxLength = Math.max(maxLength, s.length());
				}
				
				if (maxLength*2*256 > sheet.getColumnWidth(i)){
					sheet.setColumnWidth(i,  maxLength*2*256);
				}
			}
//			if (autoRowSize) {
//				maxLine = Math.max(maxLine, subCounter(rowDatas[i], "\n"));
//			}
		}
//		if (maxLine > 1) {
//			row.setHeight((short)((maxLine+1)*256));
//		}
		
	}
	
	/**
	 * 填充行
	 * @param book
	 * @param sheet
	 * @param rowNum
	 * @param rowDatas
	 * @throws Exception
	 */
	public static void applyRowData(HSSFWorkbook book, HSSFSheet sheet, int rowNum, String[] rowDatas) throws Exception {
		HSSFRow row = sheet.createRow(rowNum);
		HSSFCell cell = null;
		HSSFCellStyle style = XlsUtils.Style.applyBlackBorder(book, null);
		for (int i=0; i<rowDatas.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(rowDatas[i]);
			cell.setCellStyle(style);
		}
	}
	
	/**
	 * 填充行
	 * @param book
	 * @param sheet
	 * @param rowNum
	 * @param rowDatas
	 * @throws Exception
	 */
	public static HSSFRow applyRowData(HSSFWorkbook book, HSSFSheet sheet, HSSFCellStyle style, int rowNum, String[] rowDatas) throws Exception {
		HSSFRow row = sheet.createRow(rowNum);
		HSSFCell cell = null;
		for (int i=0; i<rowDatas.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(rowDatas[i]);
			cell.setCellStyle(style);
		}
		return row;
	}

	
	/**
	 * 复制数据
	 * @param target
	 * @param srcRow
	 * @param rowNum
	 * @param beginCol
	 * @param endCol
	 * @param style
	 */
	public static void copyRowData(HSSFSheet target, HSSFRow srcRow, int rowNum, int beginCol, int endCol, HSSFCellStyle style) {
		HSSFRow trow = target.createRow(rowNum);
		HSSFCell tcell = null;
		if (srcRow != null) {
			for (int j=beginCol; j<endCol; j++) {
				tcell = trow.createCell(j);
				tcell.setCellStyle(style);
				tcell.setCellValue(XlsUtils.getValue(srcRow.getCell(j)));
			}
		}
	}

	/**
	 * 生成excel <font color=red>临时文件</font>，关闭流
	 * @param book
	 * @param tempFile
	 * @return
	 * @throws Exception
	 */
	public static File write(HSSFWorkbook book, String tempFile) throws Exception {
		OutputStream out = null;
		try {
			File file = new File(tempFile);
			out = new BufferedOutputStream(new FileOutputStream(file));
			book.write(out);
			return file;
		} catch (IOException e) {
			throw new Exception("save excel file fail.", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	public static class Style {
		//HSSFCellStyle
		//1.边框样式
		//2.居中样式
		//3.字体
		
		/**
		 * 1.边框样式：四周加边框 细 黑  同时设置为文本格式
		 */
		public static void setBorder(HSSFWorkbook book, HSSFCellStyle style, short border, short color) {
			
//			if (style == null) 
//				style = book.createCellStyle();
			
			style.setBorderTop(border);
			style.setTopBorderColor(color);
			
			style.setBorderBottom(border);
			style.setBottomBorderColor(color);
			
			style.setBorderLeft(border);
			style.setLeftBorderColor(color);
			
			style.setBorderRight(border);
			style.setRightBorderColor(color);
			
			//设置为文本格式
			style.setDataFormat(book.createDataFormat().getFormat("@"));
			
//			return style;
		}
		
		/**
		 * 2.居中样式：水平对齐、垂直居中样式
		 * @param book
		 * @return
		 */
		public static void setAlignment(HSSFWorkbook book, HSSFCellStyle style, short align, short vertical) {
//			if (style == null) 
//				style = book.createCellStyle();
			
			style.setAlignment(align);// 水平居中
			style.setVerticalAlignment(vertical);// 垂直居中
			
//			return style;
		}
		
		public static HSSFCellStyle applyAlignment(HSSFWorkbook book, HSSFCellStyle style, short align, short vertical) {
			if (style == null) 
				style = book.createCellStyle();
			
			style.setAlignment(align);// 水平居中
			style.setVerticalAlignment(vertical);// 垂直居中
			
			return style;
		}
		
		/**
		 * 3.字体：大小、粗体、颜色
		 * @param book
		 * @param style
		 * @param fontName
		 * @param fontSize
		 * @param boldWeight
		 * @param fontColor -1 表示默认颜色
		 * @return
		 */
		public static void setFont(HSSFWorkbook book, HSSFCellStyle style, String fontName, short fontSize, short fontBoldWeight, short fontColor) {
//			if (style == null) 
//				style = book.createCellStyle();
//			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  //填充单元格 ，好像没什么卵用
//			style.setFillForegroundColor(HSSFColor.WHITE.index);   //填充单元格 ，好像没什么卵用，需要改变颜色的，交由调用者去设置
			HSSFFont font = book.createFont(); 
		    font.setFontHeightInPoints(fontSize); //字体大小 
		    font.setFontName(fontName); 
		    font.setBoldweight(fontBoldWeight); //粗体   HSSFFont.BOLDWEIGHT_NORMAL ，HSSFFont.BOLDWEIGHT_BOLD
		    if(fontColor != -1) {
		    	font.setColor(fontColor);    //绿字 
		    }
		    style.setFont(font); 
//		    return style;
		}
		
		/**
		 * 黑色 细 四边框
		 * @param book
		 * @return
		 */
		public static HSSFCellStyle applyBlackBorder(HSSFWorkbook book, HSSFCellStyle style) {
			if (style == null) 
				style = book.createCellStyle();
			
			setBorder(book, style, HSSFCellStyle.BORDER_THIN,HSSFColor.BLACK.index);
			
			return style;
		}
		
		/**
		 * 四边框，垂直居中，（可选：字体、字体大小，字体加粗，水平对齐，字体颜色）（基础样式）
		 * @param book
		 * @return
		 */
		public static HSSFCellStyle applyNormalStyle(HSSFWorkbook book,HSSFCellStyle style, String fontName, short fontSize, short fontBoldWeight, short align, short fontColor) {
			if (style == null) 
				style = book.createCellStyle();
			
			applyBlackBorder(book, style);//四边框
			setFont(book, style, fontName, fontSize, fontBoldWeight, fontColor);//黑体
			setAlignment(book, style, align, HSSFCellStyle.VERTICAL_CENTER);//垂直居中，HSSFCellStyle.ALIGN_CENTER、HSSFCellStyle.VERTICAL_CENTER
			
			return style;
		}
		
		/**
		 * 四边框，垂直居中，水平居中对齐（可选：字体，字体大小，字体加粗）（一般适用于设计标题）
		 * @param book
		 * @return
		 */
		public static HSSFCellStyle applyBaseBorderStyle(HSSFWorkbook book,HSSFCellStyle style, String fontName, short fontSize, short fontBoldWeight) {
			if (style == null) 
				style = book.createCellStyle();
			
			applyBlackBorder(book, style);//四边框
			setFont(book, style, fontName, fontSize, fontBoldWeight, (short)-1);//黑体
			setAlignment(book, style, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.VERTICAL_CENTER);//垂直居中，HSSFCellStyle.ALIGN_CENTER、HSSFCellStyle.VERTICAL_CENTER
			
			return style;
		}
		
		/**
		 * 无边框，垂直居中，水平居中对齐（可选：字体，字体大小，字体加粗）（一般适用于设计标题）
		 * @param book
		 * @return
		 */
		public static HSSFCellStyle applyBaseStyle(HSSFWorkbook book,HSSFCellStyle style, String fontName, short fontSize, short fontBoldWeight) {
			if (style == null) 
				style = book.createCellStyle();
			
			setFont(book, style, fontName, fontSize, fontBoldWeight, (short)-1);//黑体
			setAlignment(book, style, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.VERTICAL_CENTER);//垂直居中，HSSFCellStyle.ALIGN_CENTER、HSSFCellStyle.VERTICAL_CENTER
			
			return style;
		}
		
		/**
		 * 简单样式：四边框、水平对齐，垂直居中（一般适用于内容）
		 * @param book
		 * @return
		 */
		public static HSSFCellStyle applySimpleStyle(HSSFWorkbook book, HSSFCellStyle style) {
			if (style == null) 
				style = book.createCellStyle();
			
			applyBlackBorder(book, style);//四边框
			setAlignment(book, style, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.VERTICAL_CENTER);//垂直居中，HSSFCellStyle.ALIGN_CENTER、HSSFCellStyle.VERTICAL_CENTER
			
			return style;
		}

		/**
		 * 给单元格全部置上样式 base 0
		 * @param row
		 * @param style
		 * @param length
		 * @param start
		 */
		public static void applyRowCellStyle(HSSFRow row, HSSFCellStyle style, int length, int start) {
			if (row != null) {
				for (int i=start; i<length; i++) {
					HSSFCellUtil.getCell(row, i).setCellStyle(style);
				}
			}
		}

	}
	
//	/**
//	 * 统计字符串出现的次数（如“ccccc”中，c出现的次数为5，cc的个数为2）
//	 * @param str1
//	 * @param str2
//	 * @return
//	 */
//	public static int subCounter(String str1, String str2) {
//        int counter = 0;
//        if (StringUtils.isEmpty(str1) 
//        		|| StringUtils.isEmpty(str2)
//        		|| str2.length() > str1.length()) 
//        	return counter;
//        
//        int index = -1;
//        while(true) {
//        	index = str1.indexOf(str2, index);
//        	if (index < 0) break;
//        	index = index + str2.length();//不迭代统计
//        	counter++;
//        }
//        
//        return counter;
//    }
	
	public static void main(String[] args) throws Exception {
//		HSSFWorkbook excel = XlsUtils.read("C:\\Documents and Settings\\Administrator\\桌面\\aaaa.xls");
//		HSSFSheet sheet = XlsUtils.getSheet(excel);
//		HSSFRow firstRow = XlsUtils.getFirstRow(sheet);
//		if (firstRow == null) throw new Exception(null, "导入的Excel文件记录为空");
//		
//		System.out.println(parseCellIndex(firstRow, new String[]{"姓名","身份证号码"}));
		
		HSSFWorkbook book = new HSSFWorkbook();
		
		HSSFSheet sheet = XlsUtils.createSheet(book, "人员业务信息清单", 
				new String[] {"序号",  "姓名", "备注"}, 
				
				XlsUtils.Style.applyBaseStyle(book, null, "黑体", (short) 12, HSSFFont.BOLDWEIGHT_NORMAL));
		
		XlsUtils.setColumnWidth(sheet, 10,25,25);
		
//		sheet.protectSheet("");//启用保护
		
		book.setSelectedTab(0);
		book.setActiveSheet(0);
		
		sheet.createFreezePane(0, 1);//冻结首行
//		sheet.createFreezePane(1, 1);//冻结首行、首列
//		sheet.createFreezePane(1, 1, 2, 1);//冻结首行、首列，冻结区域的右边从下标2的列开始显示【下标1的列（第二列）隐藏，下标0（第一列）是冻结的列），下边区域从下标1开始显示（第2行开始显示，第1行冻结）】
		
//		int rowNum = 1;//0行为标题行
		XlsUtils.applyRowData(book, sheet, 1, new String[]{"1","张","备注\n备注备注备注备注备注备注备注备注备注备注备注\n备注备注\n备注备注\n备注备注\n备注备注"}, null);
		
		XlsUtils.write(book, "c:\\aaaa.xls");
		
//		System.out.println(XlsUtils.subCounter("ccccc", "c"));
//		System.out.println(XlsUtils.subCounter("cccccc", "ccc"));
	}

	public static HSSFRow applyTitleRowData(HSSFWorkbook book, HSSFSheet sheet,
			HSSFCellStyle rowTitleStyle,HSSFCellStyle rowRequiredTitleStyle, int rowNum, String[] rowDatas,List<String> titles) {
		HSSFRow row = sheet.createRow(rowNum);
		HSSFCell cell = null;
		for (int i=0; i<rowDatas.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(rowDatas[i]);
			if(titles.contains(rowDatas[i])){
				cell.setCellStyle(rowRequiredTitleStyle);
			}else{
			cell.setCellStyle(rowTitleStyle);
			}
		}
		return row;
	}
	
	/**
	 * 获取导入模板的表头、及表头所在模板中的序列
	 * @param asheet
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public static   List<Map<String,Object>> getImportTableTitleMap(Sheet sheet) throws ServiceException
	{
		 List<Map<String,Object>> nameList = new ArrayList<Map<String,Object>>();
		try 
		{
			//校验导入模板的列头
			Row nRow = null;
			Cell nCell = null;
			//校验导入模板的列头
			nRow = sheet.getRow(0); 
			int actualNum = nRow.getPhysicalNumberOfCells();
			int i = 0;
			int j = 0;
			boolean test = true ;
			while(test){
				 Map<String,Object> map =  new HashMap<String, Object>();
				 List<String> list = new ArrayList<String>();
					nCell = nRow.getCell(j);
					if(nCell != null)
					{
						String cellValue0 = getCellValue(nCell) == null ? "" : getCellValue(nCell);
						if(cellValue0.trim().length() > 0){
							String str = cellValue0.trim();//cellValue0.replace(" ", "");
							list.add(str);
							map.put("nameList", list);
							map.put("xulie", j);
							nameList.add(map);
							if (i==actualNum)
								test = false; 
							i++;
						}else{
							if (i==actualNum)
								test = false; 
							i++;
						}	
					}	
						if (i==actualNum)
							test = false; 
					j++;
					
			}
		} 
		catch (Exception e) 
		{
			throw new ServiceException(null,"导入的文件有误："+e.getMessage());
		}
	return nameList;
	}
	
	/**
	 * 获取导入模板的列头
	 * @param asheet
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public static  List<String> getImportTableTitle(Sheet sheet) throws ServiceException
	{
		//getImportTableTitleMap(sheet);
		 List<String> list = new ArrayList<String>();
		try 
		{
			//校验导入模板的列头
			Row nRow = null;
			Cell nCell = null;
			//校验导入模板的列头
			nRow = sheet.getRow(0); 
			int actualNum = nRow.getPhysicalNumberOfCells();
			int i = 0;
			int j = 0;
			boolean test = true ;
			while(test){
					nCell = nRow.getCell(j);
					if(nCell != null)
					{
						String cellValue0 = getCellValue(nCell) == null ? "" : getCellValue(nCell);
						if(cellValue0.trim().length() > 0){
							String str = cellValue0.trim();//cellValue0.replace(" ", "");
							list.add(str);
							if (i==actualNum)
								test = false; 
							i++;
						}
					}else{
						if (i==actualNum)
							test = false; 
						i++;
					}
					if (i==actualNum)
						test = false; 
					j++;
					
			}
		} 
		catch (Exception e) 
		{
			throw new ServiceException(null,"导入的文件有误！");
		}
	return list;
	}
	
	
	public static String getCellValue(Cell cell)
	{
		DecimalFormat format = new DecimalFormat("#.##");
		String obj = null;
		int type ;
		if(cell != null){
			type = cell.getCellType();//获取该单元格格式
		}else{
			type = 3;
		}
		switch (type)
		{
			case 0://数值型
			{
				obj =format.format(cell.getNumericCellValue());
				break;
			}
			case 1://字符串型
			{
				obj = cell.getStringCellValue();
				break;
			}
			default:
				obj = null;
		}
		return obj;
	}
	
}
