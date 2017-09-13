package com.yh.hr.info.dataimport.person.web.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.info.dataimport.person.web.form.UploadPersonForm;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.ConfigUtil;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.XlsUtils;
import com.yh.platform.core.web.action.BaseAction;

public class ImportCheckAction extends BaseAction {
	
	private final static int LIMIT_SIZE = 10 * 1024 *1024;
	
	
	/**
	 * 检查导入的excel是否规范
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkImportFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			UploadPersonForm uploadPersonForm = (UploadPersonForm)form;
			//获取上传文件
			FormFile formFile = uploadPersonForm.getUploadFile();
			
			if(uploadPersonForm.getUnitOid()==null) {
				throw new ServiceException(null, "未获取到单位ID");
			}else{
				request.setAttribute("unitOid", uploadPersonForm.getUnitOid());
			}
/*			if(uploadPersonForm.getOrgOid()==null) {
				throw new ServiceException(null, "未获取到科室ID");
			}else{
				request.setAttribute("orgOid",uploadPersonForm.getOrgOid());
			}*/
			if(formFile!=null) {//不为空
				InputStream inFile = null;
				inFile = formFile.getInputStream();
				byte[] tempbytes = new byte[inFile.available()];
				
				StringBuilder dir = new StringBuilder();
				int end = formFile.getFileName().lastIndexOf(".");
	    		dir.append(ConfigUtil.getProperty("standard.import.dir")+"/" + "nowImportFile");
				File file1 = new File(dir.toString());
				//判断文件夹是否存在，如果不存在则创建
				if(!file1.exists()){
					file1.mkdirs();//创建文件夹
				}else{
					//存在的话，列出文件夹下的所有文件，删除
					File[] fileList = file1.listFiles();
					if(fileList!=null) {
						for(File file : fileList) {
							file.delete();//删除文件
						}
					}
				}
				File file = new File(file1, "nowImportFile"+formFile.getFileName().substring(end, formFile.getFileName().length()));
				
				FileOutputStream os = new FileOutputStream(file, true);
				while (inFile.read(tempbytes) != -1) {
					os.write(tempbytes);
					os.close(); // 关闭流 
				}
				//检查文件格式是不是excel，仅支持.xls\.xlsx
				checkFileFormat(formFile);
				//检查文件大小
				checkFileLimitSize(formFile);
				
				//检查文件是否可解析
				InputStream in = formFile.getInputStream();
				XlsUtils.read(in);
				
				Workbook workbook = new HSSFWorkbook(formFile.getInputStream());
				
				Sheet sheet = workbook.getSheetAt(0);   //取得第一个sheet的值
				if(sheet==null) {
					throw new ServiceException(null,"不能取得Excel Sheet!");
				}
				if(sheet.getLastRowNum()== 0) {
					throw new ServiceException(null,"导入文档第一个sheet数据为空!");
				}
				if(sheet.getNumMergedRegions() > 0){
					throw new ServiceException(null,"文件存在合并单元格，系统不支持合并单元格，请先拆分!");
				}
				//检查表头长度、检查表头是否存在重复值
				checkImportTableTitle(sheet);
				//检查文件行数
				//checkImportRowNum(sheet);
				//检查采集项
				checkImportColoumNum(sheet);
				
				JSONObject json = new JSONObject();
				json.put("successCheckNum", sheet.getRow(0).getPhysicalNumberOfCells());
				response.getWriter().write(json.toString());
			}else {
				throw new ServiceException(null,"上传的文件为空");
			}
		}catch(Exception e) {
			this.handleException(request,e, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "人员导入失败")).toString());
			return null;
		}
		//response.getWriter().write(JSONHelper.fromObject(true, null).toString());
		return null;
	}
	

	/**检查文件列数(超过100列)
	 * @param sheet
	 * @throws ServiceException
	 */
	private void checkImportColoumNum(Sheet sheet) throws ServiceException {
		//获得总列数
		int coloumNum=sheet.getRow(0).getPhysicalNumberOfCells();
		if(coloumNum > 100)
		{
			throw new ServiceException(null,"采集项数量超过100项，系统支持最大采集项为100项！");
		}
	}
	/**检查文件行数(超过5001行)
	 * @param sheet
	 * @throws ServiceException
	 */
	private void checkImportRowNum(Sheet sheet) throws ServiceException {
		//获得总行数
		int rowNum=sheet.getLastRowNum();
		if(rowNum > 5001)
		{
			throw new ServiceException(null,"文件行数超过5000行，系统支持最大文件行数为5000！");
		}
	}
	
	/**检查表头长度，最大长度50位
	 * 检查表头是否存在重复值
	 * @param file
	 * @throws ServiceException
	 */
	public void checkImportTableTitle(Sheet sheet) throws ServiceException {
		if(sheet == null) return;
		Row nRow = null;
		Cell nCell = null;
		//int nCol = 0;
		//校验导入模板的列头
		nRow = sheet.getRow(0); //第一行   即列头
		List<String> importName =  XlsUtils.getImportTableTitle(sheet); 
		for(int nCol =0 ;nCol<nRow.getPhysicalNumberOfCells();nCol++){
			int valueLength = 0;
			nCell = nRow.getCell(nCol);
			String cellValue0 = getCellValue(nCell) == null ? "" : getCellValue(nCell);
			String chinese = "[\u0391-\uFFE5]"; 
		    // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1   
		    for (int i = 0; i < cellValue0.length(); i++) {  
		        // 获取一个字符 
		        String temp = cellValue0.substring(i, i + 1);  
		        // 判断是否为中文字符 
		        if (temp.matches(chinese)) {  
		            // 中文字符长度为2   
		            valueLength += 2;  
		        } else {  
		            // 其他字符长度为1   
		            valueLength += 1;  
		        }  
		    }  
			if(valueLength > 50)
			{
				throw new ServiceException(null,"表头长度超过50位，系统最大支持长度为50位.");
			}
		}
		String repeat = "";
		//验证表头是否重复
		for(int i = 0;i<importName.size();i++){
			for(int j = i+1;j<importName.size();j++){
				if(importName.get(i).equals(importName.get(j))){
					if(repeat.length()>0){
						repeat = repeat + "," + importName.get(i);
					}else{
						repeat = repeat + importName.get(i);
					}
				}
			}
		}
		if(repeat.length()>0){
			throw new ServiceException(null,"表头" + repeat +"重复！");
		}
	}
	
	public String getCellValue(Cell cell)
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
	
	
	/**检查文件格式是不是excel，仅支持.xls、.xlsx
	 * @param file
	 * @throws ServiceException
	 */
	private void checkFileFormat(FormFile file) throws ServiceException   
	{
		if(file == null) return;
		if(StringUtils.isEmpty(file.getFileName())) throw new ServiceException(null,"请选择文件后再上传！");
		String fileFormat = file.getFileName().toLowerCase();
		if (!(fileFormat.endsWith("xls") || fileFormat.endsWith("xlsx")  || fileFormat.endsWith("csv"))) {   
			throw new ServiceException(null,"文件格式不正确，请重新上传Excel文件！");
	    }  
	}
	
	/**检查文件大小
	 * @param file
	 * @throws ServiceException
	 */
	private void checkFileLimitSize(FormFile file) throws ServiceException {
		if(file == null) return;
		if(file.getFileSize() == 0) {
			throw new ServiceException(null,"文件大小为0字节，估计文件已损坏，请重新上传！");
		}
		if(file.getFileSize() > LIMIT_SIZE) {
			throw new ServiceException(null,"文件超出大小，请重新处理后上传！");
		}
	}
}
