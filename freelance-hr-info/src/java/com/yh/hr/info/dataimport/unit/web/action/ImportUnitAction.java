package com.yh.hr.info.dataimport.unit.web.action;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.info.dataimport.unit.dto.ImportUnitDTO;
import com.yh.hr.info.dataimport.unit.facade.ImportUnitFacade;
import com.yh.hr.info.dataimport.unit.web.form.ImportUnitForm;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.yh.hr.orghc.ub.bo.UbOrg;
import com.yh.hr.orghc.ub.bo.UbUnit;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.ConfigUtil;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.util.StringUtil;
import com.yh.platform.core.web.action.BaseAction;

/**
 * 单位导入action
 * @author chenjl
 * @date 2017-03-23
 * @version 1.0
 */
public class ImportUnitAction extends BaseAction{
	
	private ImportUnitFacade importUnitFacade = (ImportUnitFacade) SpringBeanUtil.getBean("importUnitFacade");

	private final static String BLANK="";
	private final static int limitSize = 10 * 1024 *1024;
	private final static DecimalFormat numberFormat = new DecimalFormat("#");
	private final static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	/**跳转到导入页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goUnitInfoImportWorkBench(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward(FORWARD_SUCCESS);
	}
	
	/**导入
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response	
	 * @return
	 * @throws Exception
	 */
	public ActionForward importUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String flag="Y";
	try
	{
		UbUnit ubUnit=importUnitFacade.getUbUnit();
        if(null==ubUnit){
        	throw new ServiceException(null,"单位不存在，请先访问“单位管理”新增单位信息");
        } 
		String path = ConfigUtil.getProperty("standard.template.dir");
		response.setContentType("text/html;charset=gbk");
		request.setCharacterEncoding("gbk");
		List<ImportUnitDTO> list = new ArrayList<ImportUnitDTO>();
		List<ImportUnitDTO> importUnitList = new ArrayList<ImportUnitDTO>();
		List<ImportUnitDTO> importUnitListAll = new ArrayList<ImportUnitDTO>();
		int length=0;
		ImportUnitForm importForm = (ImportUnitForm)form;
		FormFile formFile = importForm.getUploadFile();
		
		if(null != formFile)
		{
			//验证文件是不是Excel
			checkFileFormat(formFile);
			
			//检查文件大小
			checkFileLimitSize(formFile);
			
			Workbook workbook= new HSSFWorkbook(formFile.getInputStream());;
			//读取excel文件的第一张工作表
//			Sheet unitSheet = workbook.getSheetAt(0);
			//读取excel文件的第二张工作表
			Sheet orgSheet = workbook.getSheetAt(0);
			
			if(orgSheet==null)
			{
				throw new ServiceException(null,"不能取得Excel Sheet");
			}
			//组装错误消息
			StringBuffer msg = new StringBuffer();
			//取数据
//			if(unitSheet.getLastRowNum()== 1)
//			{
//				throw new ServiceException(null,"导入文档数据为空;");
//			}else
//			{
//				for (int i = 2; i <=unitSheet.getLastRowNum(); i++)
//				{   
//					Row unitRow = unitSheet.getRow(i);
//					if(unitRow==null)
//					{
//						continue;
//					}
//					try
//					{
//						importUnitList = getImportUnit(unitRow,importUnitList,"N");
//						importUnitListAll = getImportUnit(unitRow,importUnitListAll,"Y");
//						length=importUnitListAll.size();
//					}
//					catch(ServiceException se)
//					{
//						msg.append(se.getMessage());
//					}
//				}
//			}
			
			if(orgSheet.getLastRowNum()== 1)
			{
				throw new ServiceException(null,"导入文档数据为空;");
			}else{
			    int count=0;
			    int count1=0;
				for (int i = 2; i <=orgSheet.getLastRowNum(); i++){
					Row orgRow = orgSheet.getRow(i);
					if(null!=orgRow){
						String ce0 = getString(orgRow,0);
						String ce1 = getString(orgRow,1);
						String ce2 =getString(orgRow,2);
						String ce3 = getString(orgRow,3);
						String ce4 = getString(orgRow,4);
						String ce5 = getString(orgRow,5);
						String ce6 = getString(orgRow,6);
						String ce7 = getString(orgRow,7);
						String ce8 = getString(orgRow,8);
						String ce9 = getString(orgRow,9);
						String ce10 = getString(orgRow,10);
						String ce11 = getString(orgRow,11);
						String ce12 = getString(orgRow,12);
						if(StringUtils.isNotEmpty(ce0)){
							ImportUnitDTO dto = this.rowToOrgDTO(orgRow);
							list.add(dto);
						}
						if(StringUtils.isEmpty(ce0)&&StringUtils.isEmpty(ce1)&&StringUtils.isEmpty(ce2)&&StringUtils.isEmpty(ce3)&&StringUtils.isEmpty(ce4)&&StringUtils.isEmpty(ce5)
								&&StringUtils.isEmpty(ce6)&&StringUtils.isEmpty(ce7)&&StringUtils.isEmpty(ce8)&&StringUtils.isEmpty(ce9)&&StringUtils.isEmpty(ce10)&&StringUtils.isEmpty(ce11)&&StringUtils.isEmpty(ce12)){
							count++;
						}else{
							count1++;
						}
					}
				}
				if(count!=0&&count1==0){
					throw new ServiceException(null,"导入文档数据为空;");
				}
				for (int i = 2; i <=orgSheet.getLastRowNum(); i++)
				{
					Row orgRow = orgSheet.getRow(i);
					if(orgRow==null)
					{
						continue;
					}
					try
					{
						importUnitList = getImportOrg(orgRow,importUnitList,"N",list);
						importUnitListAll = getImportOrg(orgRow,importUnitListAll,"Y",list);
					}
					catch(ServiceException se)
					{
						msg.append(se.getMessage());
					}
				}
			}
			
			//如果错误数据list不为空，则将错误数据写入excel文件，并保存到服务器指定目录
			if(CollectionUtils.isNotEmpty(importUnitList))
			{
				String fileName = this.outPutTempUnitByExcel(request, response, importUnitListAll,length,formFile);
				request.setAttribute("fileName", fileName);
				flag="N";
			}else{
				//删除错误数据文件
				File root = new File(path);
				if(root.exists()){
					File[] files = root.listFiles();
					for (File file : files) {
						file.delete();
					}
				}
				//清除数据
//				importUnitFacade.deleteData();
				//导入
				if(CollectionUtils.isNotEmpty(importUnitListAll)){
					List<ImportUnitDTO> importList=new ArrayList<ImportUnitDTO>();
					List<ImportUnitDTO> importList2=new ArrayList<ImportUnitDTO>();
					for (ImportUnitDTO dto : importUnitListAll) {
						if(StringUtils.isEmpty(dto.getParentOrgName())){
							importList.add(dto);
						}else{
							importList2.add(dto);
						}
					}
					if(CollectionUtils.isNotEmpty(importList2)){
						for (ImportUnitDTO importUnitDTO : importList2) {
							importList.add(importUnitDTO);
						}
					}
					for (ImportUnitDTO dto : importList) {
						importUnitFacade.insertUnitData(dto);
					}
				}
			}
			if(msg!=null && msg.length()>0)
			{
				request.setAttribute("message", formatMessage(msg));
				flag="N";
			}
		}
    } catch (Exception e) {
    	request.setAttribute("message", e.getMessage());
        flag="N";
//		return mapping.findForward(FORWARD_SUCCESS); 
    }
//	request.setAttribute("message", "导入成功！");
	request.setAttribute("flag", flag);
	return mapping.findForward(FORWARD_SUCCESS);
}
	
	/**检查文件格式是不是.xls
	 * @param file
	 * @throws ServiceException
	 */
	private void checkFileFormat(FormFile file) throws ServiceException   
	{
		if(file == null) return;
		if(StringUtils.isEmpty(file.getFileName())) throw new ServiceException(null,"请选择文件后再导入！");
		String fileFormat = file.getFileName().toLowerCase();
		if (!fileFormat.endsWith("xls")) {   
			throw new ServiceException(null,"（文件格式不正确，请重新上传Excel文件！）");
	    }  
	}
	/**检查文件大小
	 * @param file
	 * @throws ServiceException
	 */
	private void checkFileLimitSize(FormFile file) throws ServiceException 
	{
		if(file == null) return;
		
		if(file.getFileSize() == 0)
		{
			throw new ServiceException(null,"文件大小为0字节，估计文件已损坏，请重新上传！");
		}
		
		if(file.getFileSize() > limitSize)
		{
			throw new ServiceException(null,"文件超出大小，请重新处理后上传！");
		}
	}
	
	/**
	 * 将回车符替换成页面能解析的标签
	 * @param msg
	 * @return
	 */
	private String formatMessage(StringBuffer msg)
	{
		String msgStr = msg.toString();
		return msgStr.replace(";", "<br>");
	}
	
	/**
	 * 写入错误数据信息到硬盘临时文件夹
	 * @author   liuhw
	 * @created  2013-07-29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param formFile 
	 * @return
	 * @throws Exception
	 */
	public String outPutTempUnitByExcel(HttpServletRequest request, HttpServletResponse response,List<ImportUnitDTO> importUnitList,int length, FormFile formFile) throws Exception
	{
		try{
			String path = ConfigUtil.getProperty("standard.template.dir");
			
			Workbook wb=new HSSFWorkbook(formFile.getInputStream());;
			//读取excel文件的第一张工作表
//			Sheet unitSheet = wb.getSheetAt(0);
//			Row unitRow = unitSheet.getRow(1);
//			Cell unitCell = unitRow.createCell(13);
//			unitCell.setCellValue("错误信息");
//			unitSheet.setColumnWidth((short)13, (short)50000);
			//读取excel文件的第二张工作表
			Sheet orgSheet = wb.getSheetAt(0);
			Row orgRow = orgSheet.getRow(1);
			Cell orgCell = orgRow.createCell(13);
			orgCell.setCellValue("错误信息");
			orgSheet.setColumnWidth((short)13, (short)50000);

			//将数据写入unitSheet
//			this.inputIntoUnit(unitSheet,importUnitList,length);
			
			//将数据写入orgSheet
			this.inputIntoOrg(orgSheet,importUnitList,length);
			
			int end = formFile.getFileName().lastIndexOf(".");
			File output =new File(path+"/import_error"+formFile.getFileName().substring(end, formFile.getFileName().length()));
		    FileOutputStream os = new FileOutputStream(output); 
		    wb.write(os);
		    //关闭输出流
		    os.flush();
		    os.close();
		    return output.getName();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	   return null;
	}
	
	/**
	 * 将科室数据写入sheet表
	 * @param orgSheet
	 * @param importUnitList
	 * @param length
	 */
	private void inputIntoOrg(Sheet orgSheet,
			List<ImportUnitDTO> importUnitList, int length) {
		int num=2;
		int columnCount=14;
		for (int j = length; j < importUnitList.size(); j++)
		{
			ImportUnitDTO dto = importUnitList.get(j);
			
			Row row = orgSheet.getRow(num);
			num++;
			for (int c = 0; c < columnCount; c++)
			{
				Cell csCell1 = row.createCell((short) c);
				
				switch (c)
				{
				    case 0:
//				    	csCell1.setCellValue(StringUtil.toString(dto.getOrgUnitName(),"")); 
				    	csCell1.setCellValue(StringUtil.toString(dto.getOrgName(),""));
				    	break;
				    case 1:
				    	csCell1.setCellValue(StringUtil.toString(dto.getOrgCode(),""));
						break;
					case 2:
						csCell1.setCellValue(StringUtil.toString(dto.getOrgOrderOfView(),""));
						break;
					case 3:
						csCell1.setCellValue(StringUtil.toString(dto.getParentOrgName(),""));
						break;
					case 4:
						csCell1.setCellValue(StringUtil.toString(dto.getOrgTypeName(),""));
						break;
					case 5:
						csCell1.setCellValue(StringUtil.toString(dto.getOrgEstablishedDateStr(),""));
						break;
					case 6:
						csCell1.setCellValue(StringUtil.toString(dto.getOrgContacter(),""));
						break;
					case 7:
						csCell1.setCellValue(StringUtil.toString(dto.getOrgMobilePhone(),""));
						break;
					case 8:
						csCell1.setCellValue(StringUtil.toString(dto.getOrgEmail(),""));
						break;
					case 9:
						csCell1.setCellValue(StringUtil.toString(dto.getOrgPhone(),""));
						break;
					case 10:
						csCell1.setCellValue(StringUtil.toString(dto.getOrgFax(),""));
						break;
					case 11:
						csCell1.setCellValue(StringUtil.toString(dto.getOrgAddress(),""));
						break;
					case 12:
						csCell1.setCellValue(StringUtil.toString(dto.getOrgRemark(),""));
						break;
					case 13:
						csCell1.setCellValue(StringUtil.toString(dto.getOrgMessage(),""));
						break;
//					case 14:
//						break;
				}
			}
		}
	}

	/**
	 * 将单位数据写入sheet表
	 * @param unitSheet
	 * @param importUnitList
	 * @param length
	 */
	@SuppressWarnings("unused")
	private void inputIntoUnit(Sheet unitSheet,List<ImportUnitDTO> importUnitList, int length) {
		int num=2;
		int columnCount=14;
		for (int i = 0; i < length; i++)
		{
			ImportUnitDTO dto = importUnitList.get(i);
			
			Row row = unitSheet.getRow(num);
			num++;
			for (int c = 0; c < columnCount; c++)
			{
				Cell csCell1 = row.createCell((short) c);
				
				switch (c)
				{
				  case 0:
						csCell1.setCellValue(StringUtil.toString(dto.getUnitName(),"")); 
					break;
				    case 1:
					csCell1.setCellValue(StringUtil.toString(dto.getUnitShortName(),""));
					break;
					case 2:
						csCell1.setCellValue(StringUtil.toString(dto.getCorporationCode(),""));
						break;
					case 3:
						csCell1.setCellValue(StringUtil.toString(dto.getUnitCreditNo(),""));
						break;
					case 4:
						csCell1.setCellValue(StringUtil.toString(dto.getEstablishedDateStr(),""));
						break;
					case 5:
						csCell1.setCellValue(StringUtil.toString(dto.getOrderOfView(),""));
						break;
					case 6:
						csCell1.setCellValue(StringUtil.toString(dto.getContacter(),""));
						break;
					case 7:
						csCell1.setCellValue(StringUtil.toString(dto.getMobilePhone(),""));
						break;
					case 8:
						csCell1.setCellValue(StringUtil.toString(dto.getEmail(),""));
						break;
					case 9:
						csCell1.setCellValue(StringUtil.toString(dto.getPhone(),""));
						break;
					case 10:
						csCell1.setCellValue(StringUtil.toString(dto.getFax(),""));
						break;
					case 11:
						csCell1.setCellValue(StringUtil.toString(dto.getAddress(),""));
						break;
					case 12:
						csCell1.setCellValue(StringUtil.toString(dto.getRemark(),""));
						break;
					case 13:
						csCell1.setCellValue(StringUtil.toString(dto.getMessage(),""));
						break;
				}
			}
		}
	}

	/**
	 * 读取单元格中的内容，验证数据是否有效，如果有效，则导入数据库，如果无效，则返回错误数据
	 * @param unitRow
	 * @param importUnitList
	 * @param unitOid
	 * @param deptOid
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unused")
	private List<ImportUnitDTO> getImportUnit(Row unitRow ,List<ImportUnitDTO> importUnitList ,String flag) throws ServiceException 
	{
		StringBuffer msg = new StringBuffer();
		
		ImportUnitDTO dto = this.rowToUnitDTO(unitRow);

		if(StringUtils.isNotEmpty(dto.getUnitName())){
			//验证数据是否正确
			this.validateUnit(msg,dto);
			if("Y".equals(flag)){
				importUnitList.add(dto);
			}else{
				//如果错误信息不为空，则将错误信息返回
				if(msg!=null && msg.length()>0)
				{
					importUnitList.add(dto);
				}
			}
		}
		return importUnitList;
	}
	
	private List<ImportUnitDTO> getImportOrg(Row orgRow ,List<ImportUnitDTO> importUnitList,String flag,List<ImportUnitDTO> list) throws ServiceException 
	{
		StringBuffer msg = new StringBuffer();
		
		ImportUnitDTO dto = this.rowToOrgDTO(orgRow);

		if(StringUtils.isNotEmpty(dto.getOrgName())){
			//验证数据是否正确
			this.validateOrg(msg,dto,list);
		}else{
			if(StringUtils.isEmpty(dto.getOrgTypeName())){
				msg.append("必填项不能为空;");
				dto.setOrgMessage(msg.toString());
			}else{
				msg.append("科室名称不能为空;");
				dto.setOrgMessage(msg.toString());
			}
			if(StringUtils.isEmpty(dto.getOrgTypeName())&&StringUtils.isEmpty(dto.getOrgCode())&&StringUtils.isEmpty(dto.getOrgOrderOfView())&&StringUtils.isEmpty(dto.getParentOrgName())&&StringUtils.isEmpty(dto.getOrgTypeName())&&StringUtils.isEmpty(dto.getOrgEstablishedDateStr())
					&&StringUtils.isEmpty(dto.getOrgContacter())&&StringUtils.isEmpty(dto.getOrgPhone())&&StringUtils.isEmpty(dto.getOrgFax())&&StringUtils.isEmpty(dto.getOrgAddress())&&StringUtils.isEmpty(dto.getOrgRemark())){
				msg=null;
				dto.setOrgMessage(null);
			}
		}
		//如果错误信息不为空，则将错误信息返回
		if("Y".equals(flag)){
			importUnitList.add(dto);
		}else{
			if(msg!=null && msg.length()>0)
			{
				importUnitList.add(dto);
			}
		}
		return importUnitList;
	}
	
	/**
	 * 验证excel中单位信息表
	 * @param msg
	 * @param dto
	 */
	private void validateUnit(StringBuffer msg, ImportUnitDTO dto) {

			if(dto.getUnitName().length()>100){
				msg.append("单位名称长度不能大于100;");
			}
			if(StringUtils.isNotEmpty(dto.getUnitShortName())&&dto.getUnitShortName().length()>100){
				msg.append("单位简称长度不能大于100;");
			}
			if(StringUtils.isNotEmpty(dto.getCorporationCode())&&dto.getCorporationCode().length()>20){
				msg.append("组织机构代码长度不能大于20;");
			}
			if(StringUtils.isNotEmpty(dto.getUnitCreditNo())&&dto.getUnitCreditNo().length()>20){
				msg.append("统一社会信用代码长度不能大于20;");
			}
			if(StringUtils.isNotEmpty(dto.getEstablishedDateStr())){
	    		if(!Pattern.matches("^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$", dto.getEstablishedDateStr())){
	    			msg.append("成立时间格式不正确，正确格式如：1949-09-01;");
	    		}
	    	}
			if(StringUtils.isNotEmpty(dto.getOrderOfView())){
				if(dto.getOrderOfView().length()!=3){
					msg.append("排序号长度必须等于3;");
				}
				if(!Pattern.matches("[0-9a-zA-Z]{3}", dto.getOrderOfView())){
					msg.append("排序号必须由三位数字、三位字母、或三位数字字母组合;");
				}
			}
			if(StringUtils.isNotEmpty(dto.getContacter())&&dto.getContacter().length()>100){
				msg.append("联系人长度不能大于100;");
			}
			if(StringUtils.isNotEmpty(dto.getMobilePhone())){
				if(dto.getMobilePhone().length()>11){
					msg.append("手机长度不能大于11;");
				}
				if(!Pattern.matches("^[1][358]\\d{9}$", dto.getMobilePhone())){
					msg.append("手机号不符合规则;");
				}
			}
			if(StringUtils.isNotEmpty(dto.getPhone())){
				if(dto.getPhone().length()>100){
					msg.append("联系电话长度不能大于100;");
				}
				if(!Pattern.matches("^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$", dto.getPhone())){
					msg.append("联系电话不符合规则;");
				}
			}
			if(StringUtils.isNotEmpty(dto.getEmail())){
				if(dto.getEmail().length()>100){
					msg.append("电子邮箱长度不能大于100;");
				}
				if(!Pattern.matches("^[a-zA-Z0-9_-]+(\\.([a-zA-Z0-9_-])+)*@[a-zA-Z0-9_-]+[.][a-zA-Z0-9_-]+([.][a-zA-Z0-9_-]+)*$", dto.getEmail())){
					msg.append("电子邮箱不符合规则;");
				}
			}
			if(StringUtils.isNotEmpty(dto.getFax())){
				if(dto.getFax().length()>100){
					msg.append("传真长度不能大于100;");
				}
				if(!Pattern.matches("^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$", dto.getFax())){
					msg.append("传真不符合规则;");
				}
			}
			if(StringUtils.isNotEmpty(dto.getAddress())&&dto.getAddress().length()>250){
				msg.append("单位地址长度不能大于250;");
			}
			if(StringUtils.isNotEmpty(dto.getRemark())&&dto.getRemark().length()>1000){
				msg.append("备注长度不能大于1000;");
			}
		dto.setMessage(msg.toString());
	}
	
	/**
	 * 验证excel中科室信息表
	 * @param msg
	 * @param dto
	 * @throws ServiceException 
	 */
	private void validateOrg(StringBuffer msg, ImportUnitDTO dto,List<ImportUnitDTO> list) throws ServiceException {
		UbOrg ubOrg=importUnitFacade.getOrgByName(dto.getOrgName());
		if(null!=ubOrg&&DicConstants.YHRS0101_3.equals(ubOrg.getOrgStatus())){
			msg.append("科室已撤销;");
		}else{
			if(dto.getOrgName().length()>100){
				msg.append("科室名称长度不能大于100;");
			}
			if(StringUtils.isNotEmpty(dto.getOrgCode())&&dto.getOrgCode().length()>20){
				msg.append("部门编号长度不能大于20;");	
			}
			if(StringUtils.isNotEmpty(dto.getParentOrgName())){
				Boolean b=false;
				UbOrg parentOrg=importUnitFacade.getOrgByName(dto.getParentOrgName());
				if(CollectionUtils.isNotEmpty(list)){
					for (ImportUnitDTO importUnitDTO : list) {
						if(!dto.equals(importUnitDTO)){
							if(dto.getParentOrgName().equals(importUnitDTO.getOrgName())){
								b=true;
								break;
							}
						}
					}
				}
				if(null==parentOrg&&b==false){
					msg.append("上级科室不存在;");
				}else{
					if(dto.getParentOrgName().length()>100){
						msg.append("上级科室名称长度不能大于100;");
					}
				}
			}
			if(StringUtils.isEmpty(dto.getOrgTypeName())){
				msg.append("科室类型不能为空;");
			}
			if(StringUtils.isNotEmpty(dto.getOrgEstablishedDateStr())){
				if(!Pattern.matches("^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$", dto.getOrgEstablishedDateStr())){
					msg.append("成立时间格式不正确，正确格式如：1949-09-01;");
				}
			}
			if(StringUtils.isNotEmpty(dto.getOrgOrderOfView())){
				if(dto.getOrgOrderOfView().length()!=3){
					msg.append("排序号长度必须等于3;");
				}
				if(!Pattern.matches("[0-9a-zA-Z]{3}", dto.getOrgOrderOfView())){
					msg.append("排序号必须由三位数字、三位字母、或三位数字字母组合;");
				}
			}
			if(StringUtils.isNotEmpty(dto.getOrgContacter())&&dto.getOrgContacter().length()>100){
				msg.append("联系人长度不能大于100;");
			}
			if(StringUtils.isNotEmpty(dto.getOrgMobilePhone())){
				if(dto.getOrgMobilePhone().length()>11){
					msg.append("手机长度不能大于11;");
				}
				if(!Pattern.matches("^[1][358]\\d{9}$", dto.getOrgMobilePhone())){
					msg.append("手机号不符合规则;");
				}
			}
			if(StringUtils.isNotEmpty(dto.getOrgPhone())){
				if(dto.getOrgPhone().length()>100){
					msg.append("联系电话长度不能大于100;");
				}
				if(!Pattern.matches("^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$", dto.getOrgPhone())){
					msg.append("联系电话不符合规则;");
				}
			}
			if(StringUtils.isNotEmpty(dto.getOrgEmail())){
				if(dto.getOrgEmail().length()>100){
					msg.append("电子邮箱长度不能大于100;");
				}
				if(!Pattern.matches("^[a-zA-Z0-9_-]+(\\.([a-zA-Z0-9_-])+)*@[a-zA-Z0-9_-]+[.][a-zA-Z0-9_-]+([.][a-zA-Z0-9_-]+)*$", dto.getOrgEmail())){
					msg.append("电子邮箱不符合规则;");
				}
			}
			if(StringUtils.isNotEmpty(dto.getOrgFax())){
				if(dto.getOrgFax().length()>100){
					msg.append("传真长度不能大于100;");
				}
				if(!Pattern.matches("^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$", dto.getOrgFax())){
					msg.append("传真不符合规则;");
				}
			}
			if(StringUtils.isNotEmpty(dto.getOrgAddress())&&dto.getOrgAddress().length()>250){
				msg.append("科室地址长度不能大于250;");
			}
			if(StringUtils.isNotEmpty(dto.getOrgRemark())&&dto.getOrgRemark().length()>1000){
				msg.append("备注长度不能大于1000;");
			}
		}
		dto.setOrgMessage(msg.toString());
	}

	private ImportUnitDTO rowToUnitDTO(Row unitRow) throws ServiceException
	{
		String unitName =getString(unitRow,0);
		String unitShortName = getString(unitRow,1);
		String corporationCode = getString(unitRow,2);
		String unitCreditNo = getString(unitRow,3);
		String establishedDateStr = getString(unitRow,4);
		String orderOfView = getString(unitRow,5);
		String contacter = getString(unitRow,6);
		String mobilePhone = getString(unitRow,7);
		String phone = getString(unitRow,8);
		String email = getString(unitRow,9);
		String fax = getString(unitRow,10);
		String address = getString(unitRow,11);
		String remark = getString(unitRow,12);
		
		ImportUnitDTO dto = new ImportUnitDTO();
		dto.setUnitName(StringUtil.toString(unitName));
		dto.setUnitShortName(unitShortName);
		dto.setCorporationCode(corporationCode);
		dto.setUnitCreditNo(unitCreditNo);
		dto.setEstablishedDateStr(establishedDateStr);
		dto.setOrderOfView(orderOfView);
		dto.setContacter(contacter);
		dto.setMobilePhone(mobilePhone);
		dto.setPhone(phone);
		dto.setEmail(email);
		dto.setFax(fax);
		dto.setAddress(address);
		dto.setRemark(remark);
		
		return dto;
	}
	
	private ImportUnitDTO rowToOrgDTO(Row orgRow) throws ServiceException
	{
		String orgName = getString(orgRow,0);
		String orgCode = getString(orgRow,1);
		String orderOfView =Pattern.matches("[0-9a-zA-Z]",getString(orgRow,2))==true?getString(orgRow,2).length()==1?"00"+getString(orgRow,2):getString(orgRow,2).length()==2?"0"+getString(orgRow,2):getString(orgRow,2):getString(orgRow,2);
		String parentOrgName = getString(orgRow,3);
		String orgTypeName = getString(orgRow,4);
		String establishedDateStr = getString(orgRow,5);
		String contacter = getString(orgRow,6);
		String mobilePhone = getString(orgRow,7);
		String email = getString(orgRow,8);
		String phone = getString(orgRow,9);
		String fax = getString(orgRow,10);
		String address = getString(orgRow,11);
		String remark = getString(orgRow,12);
		
		ImportUnitDTO dto = new ImportUnitDTO();
		dto.setOrgName(orgName);
		dto.setOrgCode(orgCode);
		dto.setOrgOrderOfView(orderOfView);
		dto.setParentOrgName(parentOrgName);
		dto.setOrgTypeName(orgTypeName);
		dto.setOrgEstablishedDateStr(establishedDateStr);
		dto.setOrgContacter(contacter);
		dto.setOrgMobilePhone(mobilePhone);
		dto.setOrgPhone(phone);
		dto.setOrgEmail(email);
		dto.setOrgFax(fax);
		dto.setOrgAddress(address);
		dto.setOrgRemark(remark);
		
		return dto;
	}
	
	/**
	 * 返回字符串
	 * @param unitRow
	 * @param index
	 * @return
	 * @throws ServiceException
	 */
	private String getString(Row unitRow, int index) throws ServiceException
	{
		return getString(unitRow.getCell((short) index));
	}
	
	/**
	 * 返回字符串
	 * @param cell
	 * @return
	 * @throws ServiceException
	 */
	private String getString(Cell cell) throws ServiceException
	{
		if (cell == null)
			return BLANK;
		switch (cell.getCellType())
		{
		  case HSSFCell.CELL_TYPE_BLANK:
			return BLANK;
		  case HSSFCell.CELL_TYPE_STRING:
			return cell.getStringCellValue().trim();
		  case HSSFCell.CELL_TYPE_NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell))
				return dateFormat.format(cell.getDateCellValue());
			else
				return numberFormat.format(cell.getNumericCellValue());
		  default:
			return BLANK;
		}
	}
	
	/**
	 * 下载导入模板
	 * @param mapping
	 * @param actionform
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward downImportExcel(ActionMapping mapping, ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		try{
			request.setCharacterEncoding("UTF-8");
			String fileName= request.getParameter("fileName");
			String path = ConfigUtil.getProperty("standard.template.dir");
		    
			String filePath = path + fileName;//文件路径
			File file=new File(filePath);
			if(!file.exists())
			{	
				throw new ServiceException(null,"你下载的文件不存在");
			}else{
				response.setContentType("application/vnd.ms-excel");			
				String exportName =URLEncoder.encode(fileName, "UTF-8"); 
				response.setHeader("Content-Disposition", "attachment; filename=" + exportName);
				InputStream inputStream = new FileInputStream(filePath);
				ServletOutputStream os = response.getOutputStream();
				byte[] buf = new byte[4096*2];
				BufferedInputStream bis = new BufferedInputStream(inputStream);
				int i;
				while((i = bis.read(buf, 0, 4096*2)) != -1) 
				{
				      os.write(buf, 0, i);
				}
		
				bis.close();
				os.flush();
			    os.close();	
		    }
		}
	catch(Exception e)
	{
		 handleException(request, e, e.getMessage());
         response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "下载文件失败")).toString());
         return null;
	}
		return null;
    }
}
