package com.yh.hr.info.dataimport.person.web.action;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.info.dataimport.person.facade.ExportPersonFacade;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.hr.component.im.dto.ColumnDefDTO;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.im.dto.ImCheckPersonUnusualDTO;
import com.yh.hr.res.im.dto.ImImportBatchDTO;
import com.yh.platform.core.util.IOUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.util.XlsUtils;
import com.yh.platform.core.web.action.BaseAction;

public class exportPersonAction extends BaseAction {
	
	private ExportPersonFacade exportPersonFacade = (ExportPersonFacade) SpringBeanUtil.getBean("exportPersonFacade");

	/**
	 * 导出映射字典后的数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward exportDicPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			List<List<String>> list = new ArrayList<List<String>>();
			List<String > listColumnName = new ArrayList<String>();
			List<String > listPersonOid = new ArrayList<String>();
			List<List<ColumnDefDTO>> lists = exportPersonFacade.listDicPerson();
			if(CollectionUtils.isNotEmpty(lists)){
				for(int j=0;j<lists.size();j++){
					List<String > list1 = new ArrayList<String>();
					List<ColumnDefDTO> columns =lists.get(j);
					for(int i =0; i<columns.size();i++){
						if(i == columns.size()-1){
							listPersonOid.add(columns.get(i).getColumnValue());
						}
						list1.add(columns.get(i).getColumnValue());
						if(j==0){
							listColumnName.add(columns.get(i).getColumnName());
						}
					}
					list.add(list1);
				}
			}
			//获取当前最新的导入批次
			ImImportBatchDTO imImportBatchDTO = exportPersonFacade.getCurrentImportBatchDTO();
			Long  importBatchOid = imImportBatchDTO.getImportBatchOid(); 
			
			HSSFWorkbook book = new HSSFWorkbook();
			
			HSSFSheet sheet = book.createSheet("校核人员表");
			
			int rowNum = 0;
			//HSSFCellStyle style = null;
			HSSFRow row = null;
			
			//设置列头样式
			Font font = book.createFont(); 
		    font.setFontHeightInPoints((short) 15); //字体大小 
		    font.setFontName("宋体"); 
		    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //粗体   HSSFFont.BOLDWEIGHT_NORMAL ，HSSFFont.BOLDWEIGHT_BOLD
		    CellStyle cellStyle1 = book.createCellStyle();
		    cellStyle1.setFont(font);
		    cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
		    cellStyle1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中 
		    
		    
		    //设置字体(即内容样式)
		    Font wordFont = book.createFont(); 
		    wordFont.setFontHeightInPoints((short) 11); //字体大小 
		    wordFont.setFontName("宋体"); 
		    CellStyle cellStyle2 = book.createCellStyle();
		    cellStyle2.setFont(wordFont);
		    cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
		    cellStyle2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中 
		   // cellStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  //设置单元格背景颜色
		   // cellStyle2.setFillForegroundColor(HSSFColor.RED.index);  //自定义单元格颜色
			
			//style = XlsUtils.Style.applyBaseBorderStyle(book, null, "宋体", (short) 11, HSSFFont.BOLDWEIGHT_NORMAL);
			Object[] obj = listColumnName.toArray();
			String[] strs = new String[obj.length];
			for(int i=0;i<obj.length-1;i++){
				strs[i]=obj[i].toString();
			}
			row = applyRowData(book, sheet, cellStyle1,
					rowNum,
					strs
					,"错误信息"
					,DicConstants.YHRS0003_0);
			
			row.setHeight((short)(256*3));
			
			
			book.setSelectedTab(0);
			book.setActiveSheet(0);
			
			sheet.createFreezePane(0, 3);//冻结首行
			
			//style = XlsUtils.Style.applySimpleStyle(book, null);
			//style.setWrapText(true);
			
			
			
			if (CollectionUtils.isNotEmpty(list)) {
				for (List<String> json : list) {
					List<String> str = new ArrayList<String>();
					//str.add(String.valueOf(rowNum - 2));//序号
					String personOid;  //获取每条数据对应的人员id
					StringBuffer msg = new StringBuffer();
					List<String> errorDataType = new ArrayList<String>();  //用于存储错误信息的类型
					Map<String,Object> map =  new HashMap<String, Object>();
					List<String> dataErrorNum = new ArrayList<String>();
					for(int i=0;i<json.size();i++){
						str.add(json.get(i));
						 System.out.println(row);
						 // 获取到Excel文件中的所有的列
						int cells = row.getPhysicalNumberOfCells();
						if(i == json.size() -1){
							personOid = json.get(i); //获取到此此条数据对应的人员id
							//获取到此人是否存在异常信息
							 List<ImCheckPersonUnusualDTO> listImCheckPersonUnusualDTO = exportPersonFacade.findImCheckPersonUnusualByBatchOidAndPersonOid(importBatchOid,Long.valueOf(personOid));
							 if(CollectionUtils.isNotEmpty(listImCheckPersonUnusualDTO)){
								 //组装错误信息，用于显示
								 for(ImCheckPersonUnusualDTO dto : listImCheckPersonUnusualDTO){
									 
									 //获取到错误信息的列
									 for(int m = 0;m<cells;m++){
										// 获取到列的值
										Cell cell = row.getCell(m);
										if(cell != null){
											if(XlsUtils.getCellValue(cell).equals(dto.getDatabaseColumnName())){
												dataErrorNum.add(String.valueOf(m));
											}
										}
									 }
									 //字典型检查
									 if(dto.getCheckType().equals(DicConstants.YHRS0140_4)){
										 if(msg.length() == 0){
											 msg.append(dto.getRemark());
											 errorDataType.add(DicConstants.YHRS0140_4);
										 }else{
											 msg.append("," + dto.getRemark());
											 errorDataType.add(DicConstants.YHRS0140_4);
										 }
									 }
									//关联性检查
									 if(dto.getCheckType().equals(DicConstants.YHRS0140_5)){
										 if(msg.length() == 0){
											 msg.append(dto.getRemark());
											 errorDataType.add(DicConstants.YHRS0140_5);
										 }else{
											 msg.append("," + dto.getRemark());
											 errorDataType.add(DicConstants.YHRS0140_5);
										 }
									 }
									//完整性检查 
									 if(dto.getCheckType().equals(DicConstants.YHRS0140_6)){
										 if(msg.length() == 0){
											 msg.append(dto.getRemark());
											 errorDataType.add(DicConstants.YHRS0140_6);
										 }else{
											 msg.append("," + dto.getRemark());
											 errorDataType.add(DicConstants.YHRS0140_6);
										 }
									 }
								 }
								 map.put("msg", msg);
								 map.put("errorNumList", dataErrorNum);
								 map.put("errorDataTypeList", errorDataType);
							 }
						}
					}
					System.out.println(map);
		            Object[] objs = str.toArray();
					String[] strValue = new String[objs.length];
					for(int i=0;i<objs.length-1;i++){
						strValue[i]=objs[i]==null?null:objs[i].toString();
					}
					rowNum++;
					applyErrorRowData(book, sheet, cellStyle2, rowNum,strValue,map,DicConstants.YHRS0003_1);
					//XlsUtils.applyRowData(book, sheet, style, rowNum,strValue);
				}
			}
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			book.write(out);
			
			IOUtil.writeResponse(response, out, "application/octet-stream", "校核人员.xls");
		} catch (Exception e) {
			handleException(request, e, "导出校核人员名册失败");
		}
        
        return null;
	}
		
	/**
	 * 人员入库成功
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward exportPersonSuc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("success");
	}
	
	
	
	public static HSSFRow applyErrorRowData(HSSFWorkbook book, HSSFSheet sheet, CellStyle style, int rowNum, String[] rowDatas,Map<String,Object> map,String flag) throws Exception {
		HSSFRow row = sheet.createRow(rowNum);
		HSSFCell cell = null;
		//第一列(姓名)
		 CellStyle nameCellStyle = book.createCellStyle();
		 nameCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
		 nameCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中 
		 nameCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  //设置单元格背景颜色
		 nameCellStyle.setFillForegroundColor(HSSFColor.RED.index);  //自定义单元格颜色
	    
	    //字典项
	    CellStyle cellStyle4 = book.createCellStyle();
	    cellStyle4.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
	    cellStyle4.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中 
	    cellStyle4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  //设置单元格背景颜色
	    cellStyle4.setFillForegroundColor(HSSFColor.GREY_50_PERCENT.index);  //自定义单元格颜色
	    
	    
	    //关联型
	    CellStyle cellStyle5 = book.createCellStyle();
	    cellStyle5.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
	    cellStyle5.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中 
	    cellStyle5.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  //设置单元格背景颜色
	    cellStyle5.setFillForegroundColor(HSSFColor.SEA_GREEN.index);  //自定义单元格颜色
	    
	    //完整性
	    CellStyle cellStyle6 = book.createCellStyle();
	    cellStyle6.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
	    cellStyle6.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中 
	    cellStyle6.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  //设置单元格背景颜色
	    cellStyle6.setFillForegroundColor(HSSFColor.BLUE.index);  //自定义单元格颜色
	    
		for (int i=0; i<rowDatas.length; i++) {
			if(i == rowDatas.length -1){
				sheet.setColumnWidth(i, 15000); 
			}else{
				//获取到数据异常的列
				List<String> list1 = (List) map.get("errorNumList");
				List<String> errorDataTypeList = (List) map.get("errorDataTypeList");
				if(CollectionUtils.isNotEmpty(list1)){
					sheet.setColumnWidth(i, 5000);  
					cell = row.createCell(i);
					cell.setCellValue(rowDatas[i]);
					cell.setCellStyle(style);  //设置无错误的数据字体、颜色
					
					//设置第一列(姓名为红色)
					Cell nameCell = row.getCell(0);
					//重新创建一个单元格用于替换
					Cell nowNameCell = row.createCell(0);
					nowNameCell.setCellValue(XlsUtils.getCellValue(nameCell));
					nowNameCell.setCellStyle(nameCellStyle);
					
					for(int j = 0;j<list1.size();j++){
						if(i == Integer.valueOf(list1.get(j))){
							//获取指定cell的值,设置样式为对应的颜色
							Cell nowCell = row.getCell(Integer.valueOf(list1.get(j)));
							//重新创建一个单元格用于替换
							Cell nowErrorCell = row.createCell(Integer.valueOf(list1.get(j)));
							nowErrorCell.setCellValue(XlsUtils.getCellValue(nowCell));
							nowErrorCell.setCellStyle(style);
							//字典项检查
							if(errorDataTypeList.get(j).equals(DicConstants.YHRS0140_4)){
								nowErrorCell.setCellStyle(cellStyle4);
							}
							//关联性检查
							if(errorDataTypeList.get(j).equals(DicConstants.YHRS0140_5)){
								nowErrorCell.setCellStyle(cellStyle5);
							}
							//完整性检查
							if(errorDataTypeList.get(j).equals(DicConstants.YHRS0140_6)){
								nowErrorCell.setCellStyle(cellStyle6);
							}
						}
					}
				}else{
					sheet.setColumnWidth(i, 5000);  
					cell = row.createCell(i);
					cell.setCellValue(rowDatas[i]);
					cell.setCellStyle(style);   //设置无错误的数据字体、颜色
				}
			}
		}
		if(map.size()>0){
			if(flag.equals(DicConstants.YHRS0003_1)){
				System.out.println(map.get("msg").toString().length());
				if(map.get("msg").toString().length()>0){
					Cell c = row.createCell(row.getPhysicalNumberOfCells());
					c.setCellValue(map.get("msg").toString());
					//c.setCellStyle(style);   //错误信息不设置颜色
				}
			}
		}
		return row;
	}
	
	/**
	 * 填充行数据
	 * @param book
	 * @param sheet
	 * @param rowNum
	 * @param rowDatas
	 * @throws Exception
	 */
	public static HSSFRow applyRowData(HSSFWorkbook book, HSSFSheet sheet, CellStyle style, int rowNum, String[] rowDatas,String msg,String flag) throws Exception {
		HSSFRow row = sheet.createRow(rowNum);
		HSSFCell cell = null;
		for (int i=0; i<rowDatas.length; i++) {
			if(i == rowDatas.length -1){
				sheet.setColumnWidth(i, 15000); 
			}else{
				sheet.setColumnWidth(i, 5000);  
				cell = row.createCell(i);
				cell.setCellValue(rowDatas[i]);
				cell.setCellStyle(style);
			}
		}
		if(flag.equals(DicConstants.YHRS0003_1)){
			if(msg.length()>0){
				Cell c = row.createCell(row.getPhysicalNumberOfCells());
				c.setCellValue(msg);
				c.setCellStyle(style);
			}
		}
		if(flag.equals(DicConstants.YHRS0003_0)){
			Cell c = row.createCell(row.getPhysicalNumberOfCells());
			c.setCellValue(msg);
			c.setCellStyle(style);
		}
		return row;
	}
}
