/**
 * 
 */
package com.yh.hr.info.dataexport.web.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.hr.info.ver.unit.workbench.dto.VerPersonDTO;
import com.yh.hr.info.ver.unit.workbench.facade.VerPbPersonWorkbenchFacade;
import com.yh.hr.res.pb.dto.PbPersonInfoDTO;
import com.yh.hr.res.unit.dto.UtOrgDTO;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.IOUtil;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.util.XlsUtils;
import com.yh.platform.core.web.action.BaseAction;

/**
 * 
 * @author	cheny
 * @version	1.0,	16/04/12
 */

public class VerPbPersonInfoSetExportAction extends BaseAction {
	private VerPbPersonWorkbenchFacade verPbPersonWorkbenchFacade = (VerPbPersonWorkbenchFacade)SpringBeanUtil.getBean("verPbPersonWorkbenchFacade");
	/**
	 * 导出单位人员名册
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward exportPbPersonInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			//读取配置
			List<BaleSet> configs = getJgPerosnBaleSet();
			String checkFlag=request.getParameter("checkFlag");
			if("Y".equals(checkFlag)){
				HSSFWorkbook book = new HSSFWorkbook();
				String persons = request.getParameter("persons");
				JSONArray jsStr = JSONArray.fromObject(persons);
				if(null != jsStr && !jsStr.isEmpty())
				{
					for(int i =0 ; i<jsStr.size();i++){
						JSONObject json = jsStr.getJSONObject(i);
						String personCode = json.get("personCode").toString();
						Long personOid = NumberUtils.longValue(json.get("personOid").toString());
						PbPersonInfoDTO pbPersonInfo =verPbPersonWorkbenchFacade.getPbPersonById(personOid);
						if(null==pbPersonInfo){
							throw new ServiceException(null,"人员信息不存在");
						}
						HSSFSheet sheet = book.createSheet(pbPersonInfo.getName()+"-"+personCode);//底下页签的标题
						
						int rowNum = 0;
						int sequence = 0;
						HSSFCellStyle style = null;
						HSSFRow row = null;
						
						//大标题样式
						style = XlsUtils.Style.applyBaseStyle(book, null, "黑体", (short) 12, HSSFFont.BOLDWEIGHT_NORMAL);
						
						row = sheet.createRow(rowNum);
						sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 8));//第一行合并
						HSSFCell cell = row.createCell(0);
						cell.setCellValue("人员基本信息确认表");//大标题
						cell.setCellStyle(style);
						row.setHeight((short)(256*3));
						rowNum++;
						
						row = sheet.createRow(rowNum);
						cell = row.createCell(0);
						cell.setCellValue("导出时间："+DateUtil.nowString());
						cell.setCellStyle(XlsUtils.Style.applyAlignment(book, null, HSSFCellStyle.ALIGN_RIGHT, HSSFCellStyle.VERTICAL_CENTER));
	//					row.createCell(1).setCellValue(DateUtil.nowString());
						rowNum++;
						if(CollectionUtils.isNotEmpty(configs))
						{
							for (BaleSet config : configs) {
								sequence++;
								rowNum = createSubInfoSet(personOid, config, book, sheet, rowNum,sequence) + 1;
							}
						}
					}
				}
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				book.write(out);
				IOUtil.writeResponse(response, out, "application/octet-stream", "人员基本信息确认表.xls");
			}else{
				String orgOid=request.getParameter("orgOid");
				String organizationOid=request.getParameter("organizationOid");
				List<UtOrgDTO> list = verPbPersonWorkbenchFacade.listLeafOrg(orgOid,organizationOid);
				
				 List<File> srcfile=new ArrayList<File>(); 
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHSS");  
			     String path = sdf.format(new Date());  
			     String serverPath = request.getSession().getServletContext().getRealPath("/");  
			     //在服务器端创建文件夹  
		         File file = new File(serverPath+path);  
		         if(!file.exists()){  
		            file.mkdir();  
		          }  
		         if(CollectionUtils.isNotEmpty(list))
		         {
					for(UtOrgDTO utOrgDTO : list){
						HSSFWorkbook book = new HSSFWorkbook();
						List<VerPersonDTO> personList = verPbPersonWorkbenchFacade.listVerPersonByOrgOid(utOrgDTO.getOrgOid());
						if(CollectionUtils.isNotEmpty(personList))
						{
							for(VerPersonDTO verPersonDTO : personList){
								String name = verPersonDTO.getName();
								String personCode = verPersonDTO.getPersonCode();
								Long personOid = verPersonDTO.getPersonOid();
								HSSFSheet sheet = book.createSheet(name+"-"+personCode);//底下页签的标题
								int rowNum = 0;
								int sequence = 0;
								HSSFCellStyle style = null;
								HSSFRow row = null;
								style = XlsUtils.Style.applyBaseStyle(book, null, "黑体", (short) 12, HSSFFont.BOLDWEIGHT_NORMAL);
								row = sheet.createRow(rowNum);
								sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 8));//第一行合并
								HSSFCell cell = row.createCell(0);
								cell.setCellValue("人员基本信息确认表");//大标题
								cell.setCellStyle(style);
								row.setHeight((short)(256*3));
								rowNum++;
								
								row = sheet.createRow(rowNum);
								cell = row.createCell(0);
								cell.setCellValue("导出时间："+DateUtil.nowString());
								cell.setCellStyle(XlsUtils.Style.applyAlignment(book, null, HSSFCellStyle.ALIGN_RIGHT, HSSFCellStyle.VERTICAL_CENTER));
		//						row.createCell(1).setCellValue(DateUtil.nowString());
								rowNum++;
								sequence++;
								if(CollectionUtils.isNotEmpty(configs))
								{
									for (BaleSet config : configs) {
										rowNum = createSubInfoSet(personOid, config, book, sheet, rowNum,sequence) + 1;
									}
								}
							}
						}
						String templateName =utOrgDTO.getOrgName();//excel名
						SimpleDateFormat sfm = new SimpleDateFormat("yyyy-MM-dd");  
		                String filename = templateName + "_" + sfm.format(new Date());  
		                  
		                String encodedfileName = new String(filename.getBytes());
		                //将生成的多个excel放到服务器的指定的文件夹中  
		                FileOutputStream out = new FileOutputStream(serverPath+path+"\\"+encodedfileName+".xls"); 
		                response.setContentType("application/x-download");// 设置response内容的类型  
		                response.setHeader("Content-Disposition", " filename=\"" + encodedfileName + "\"" );
		                book.write(out);
		                srcfile.add(new File(serverPath+path+"\\"+encodedfileName+".xls")); 
					}
		         }
				//将服务器上存放Excel的文件夹打成zip包  
		        File zipfile = new File(serverPath+path+".zip"); 
		        this.zipFiles(srcfile, zipfile);  
		        //弹出下载框供用户下载  
		        this.downFile(response,serverPath, path+".zip");  
			}
		} catch (Exception e) {
			handleException(request, e, "导出人员基本信息确认表失败!");
			response.getWriter().append("<center style='margin-top:50px;color:red;'>导出人员基本信息确认表失败!</center>");
		}
        
        return null;
	}
	/** 
     * 将多个Excel打包成zip文件 
     * @param srcfile 
     * @param zipfile 
     */  
    public void zipFiles(List<File> srcfile, File zipfile) {    
        byte[] buf = new byte[1024];    
        try {    
            // Create the ZIP file    
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile)); 
            out.setEncoding(System.getProperty("sun.jnu.encoding"));//设置文件名编码方式
            //out.setEncoding("utf-8");//设置编码格式
            // Compress the files    
            for (int i = 0; i < srcfile.size(); i++) {    
                File file = srcfile.get(i);    
                FileInputStream in = new FileInputStream(file);    
                // Add ZIP entry to output stream.    
                out.putNextEntry(new ZipEntry(file.getName()));    
                // Transfer bytes from the file to the ZIP file    
                int len;    
                while ((len = in.read(buf)) > 0) {    
                    out.write(buf, 0, len);    
                }    
                // Complete the entry    
                out.closeEntry();    
                in.close();    
            }    
            // Complete the ZIP file    
            out.close();   
        } catch (IOException e) {    
           e.printStackTrace();  
        }    
    }   
    public void downFile(HttpServletResponse response,String serverPath, String str) {    
        try {    
            String path = serverPath + str;    
            File file = new File(path);    
            if (file.exists()) {    
                InputStream ins = new FileInputStream(path);    
                BufferedInputStream bins = new BufferedInputStream(ins);// 放到缓冲流里面    
                OutputStream outs = response.getOutputStream();// 获取文件输出IO流    
                BufferedOutputStream bouts = new BufferedOutputStream(outs);    
                response.setContentType("application/x-download");// 设置response内容的类型    
                response.setHeader(    
                        "Content-disposition",    
                        "attachment;filename="    
                                + URLEncoder.encode(str, "GBK"));// 设置头部信息    
                int bytesRead = 0;    
                byte[] buffer = new byte[8192];    
                 //开始向网络传输文件流    
                while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {    
                   bouts.write(buffer, 0, bytesRead);    
               }    
               bouts.flush();// 这里一定要调用flush()方法    
                ins.close();    
                bins.close();    
                outs.close();    
                bouts.close();    
            } else {    
                response.sendRedirect("../error.jsp");    
            }    
        } catch (IOException e) {    
            e.printStackTrace();  
        }    
    } 
	private int createSubInfoSet(Long personOid, BaleSet config, HSSFWorkbook book, HSSFSheet sheet, int rowNum,int sequence) throws Exception {
		
		List<Object> list = invoke(SpringBeanUtil.getBean(config.bean), config.method, personOid);
		
//		if (CollectionUtils.isEmpty(list)) {
//			return --rowNum;
//		}
			
		HSSFRow row;
		
		//大标题样式
		HSSFCellStyle headerStyle = XlsUtils.Style.applyBaseStyle(book, null, "黑体", (short) 14, HSSFFont.BOLDWEIGHT_NORMAL);
//			headerStyle.setFillBackgroundColor(HSSFColor.YELLOW.index);
		headerStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
		headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		XlsUtils.Style.setBorder(book, headerStyle, HSSFCellStyle.BORDER_THIN, HSSFColor.BLACK.index);
		XlsUtils.Style.setAlignment(book, headerStyle, HSSFCellStyle.ALIGN_LEFT, HSSFCellStyle.VERTICAL_BOTTOM);
		
		//信息集标题栏
		row = sheet.createRow(rowNum);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, config.colspan > 1  ? config.colspan : config.filedTitlesMaxLength - 1));//第一行合并
		HSSFCell cell = row.createCell(0);
		cell.setCellValue(sequence+"、"+config.title);//大标题
		cell.setCellStyle(headerStyle);
		XlsUtils.Style.applyRowCellStyle(row, headerStyle, config.colspan > 1  ? config.colspan+1 : config.filedTitlesMaxLength, 0);
		row.setHeight((short)(256*2));
		rowNum++;//用完后，自动加+1，后续也要保证用完后自动加+1
		
		//字段标题样式 黑体*12
		HSSFCellStyle rowTitleStyle = XlsUtils.Style.applyBaseBorderStyle(book, null, "黑体", (short) 12, HSSFFont.BOLDWEIGHT_NORMAL);
		HSSFCellStyle rowRequiredTitleStyle = XlsUtils.Style.applyBaseBorderStyle(book, null, "黑体", (short) 12,HSSFFont.BOLDWEIGHT_NORMAL);
		rowRequiredTitleStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
		rowRequiredTitleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		
		HSSFCellStyle rowStyle = XlsUtils.Style.applySimpleStyle(book, null);
		rowStyle.setWrapText(true);
		
		for (int i=0; i<config.fieldTitles.size(); i++) {
			
			//信息集字段标题栏
			if (config.columnDisplay) {
				List<Bale> listBale = config.columns.get(i);
				List<String> titles=new ArrayList<String>();
				for(Bale bale : listBale){
					if("true".equals(bale.required)){
						titles.add(bale.title);
					}
				}
				//row = XlsUtils.applyRowData(book, sheet, rowTitleStyle, rowNum, config.fieldTitles.get(i));
				row = XlsUtils.applyTitleRowData(book, sheet, rowTitleStyle,rowRequiredTitleStyle, rowNum,config.fieldTitles.get(i),titles);
				row.setHeight((short)(256*2));
				if (config.colspan > 1) {
					sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, config.colspan));//第一行合并
					XlsUtils.Style.applyRowCellStyle(row, rowTitleStyle, config.colspan+1, 0);
				}
				rowNum++;
			}
			
			XlsUtils.setAllColumnWidth(sheet, 25, config.fieldTitles.get(i).length, 0);
			if (CollectionUtils.isNotEmpty(list)) {
				int index = 1;
				for (Object data : list) {
					row = XlsUtils.applyRowData(book, sheet, rowStyle, rowNum, getRowData(data, config.columns.get(i), index++));
					row.setHeight((short)(256*2));
					if (config.height > 0) {
						row.setHeight((short)(256*config.height));
					}
					if (config.colspan > 1) {
						sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, config.colspan));//第一行合并
						XlsUtils.Style.applyRowCellStyle(row, rowStyle, config.colspan+1, 0);
						if (config.columnAlignLeft) {
							HSSFCellStyle alignLeft = XlsUtils.Style.applySimpleStyle(book, null);
							alignLeft.setWrapText(true);
							XlsUtils.Style.setAlignment(book, alignLeft, HSSFCellStyle.ALIGN_LEFT, HSSFCellStyle.VERTICAL_TOP);
							row.getCell(0).setCellStyle(alignLeft);
						}
					}
					rowNum++;
					
				}
			}
		}
		
		return rowNum;
	}
	
	private String[] getRowData(Object obj, List<Bale> columns, int rowNum) throws ServiceException {
		
		if (CollectionUtils.isEmpty(columns)) return new String[0];
		
		List<String> list = new ArrayList<String>(columns.size());
		
		for (Bale column : columns) {
			list.add(getValue(obj, column, rowNum));
		}
		
		return list.toArray(new String[list.size()]);
	};
	
	private String getValue(Object obj, Bale column, int rowNum) throws ServiceException {
		
		if (StringUtils.isEmpty(column.field)) {
			if (StringUtils.isEmpty(column.type) || "rowNumber".equals(column.type)) {
				return String.valueOf(rowNum);
			}
			return null;
		}
		
		Object val = BeanHelper.get(obj, column.field);
		
		if (val == null) return column.emptyAs;
		
		if (val instanceof String) {
			
			if (StringUtils.isNotEmpty(column.dicCode)) {
				return DicHelper.viewName(column.dicCode, (String)val);
			}
			if (StringUtils.isEmpty((String)val)) {
				return column.emptyAs;
			}
			return column.brp ? ((String)val).replaceAll("<br>", "\n").replaceAll("<br/>", "\n").replaceAll("&nbsp;", " ") : (String)val;
		}else if(val instanceof Long){
			if (StringUtils.isNotEmpty(column.dicCode)) {
				return DicHelper.viewName(column.dicCode, val.toString());
			}
			if (StringUtils.isEmpty(val.toString())) {
				return column.emptyAs;
			}
			return column.brp ? (val.toString()).replaceAll("<br>", "\n").replaceAll("<br/>", "\n").replaceAll("&nbsp;", " ") : val.toString();
		}
		
		if ( val instanceof Date) {
			
			return DateUtil.format((Date)val, StringUtils.defaultIfEmpty(column.format, DateUtil.DATE_PATTERN_DEFAULT));
		}
		
		if( val instanceof Double){
			return new java.text.DecimalFormat("#.00").format(val);
		}
		
		return val.toString();
	}
	
	@SuppressWarnings("unchecked")
	private List<Object> invoke(Object bean, String method, Long personOid) throws Exception {
		try {
			
			 Object result = bean.getClass().getMethod(method, Long.class).invoke(bean, personOid);
			 
			 if (result == null) return null;
			 
			 if (result instanceof List) {
				 return (List<Object>) result;
			 }
			 
			 return Arrays.asList(result);
		} catch (InvocationTargetException e) {
			
			if (e.getTargetException() instanceof Exception) {
				throw (Exception) e.getTargetException();
			}
			
			throw new Exception(e.getTargetException());
		} catch (Exception e) {
			throw new ServiceException("VerPbPersonInfoSetExportAction#invoke fail.", e);
		}
	}
	
	/********************************** 信息配置 **************************************/
	@SuppressWarnings("unchecked")
	private static List<BaleSet> getJgPerosnBaleSet() throws DocumentException {
		
		Document doc = new SAXReader().read(VerPbPersonInfoSetExportAction.class.getResourceAsStream(new StringBuilder().append("personinfo_export.xml").toString()));
		Element root = doc.getRootElement();
		
		List<Element> infos = root.elements("info");
		
		List<BaleSet> list = new ArrayList<BaleSet>();
		if(CollectionUtils.isNotEmpty(infos)) {
			BaleSet set = null;
			List<Element> es = null;
			for (Element ele : infos) {
				
				set = new BaleSet();
				
				set.title = ele.attributeValue("title");
				set.bean = ele.attributeValue("bean");
				set.method = ele.attributeValue("method");
				set.height = Short.valueOf(StringUtils.defaultIfEmpty(ele.attributeValue("height"), "0"));
				set.colspan = Short.valueOf(StringUtils.defaultIfEmpty(ele.attributeValue("colspan"), "0"));
				set.columnDisplay = Boolean.valueOf(StringUtils.defaultIfEmpty(ele.attributeValue("columnDisplay"), "true"));
				set.columnAlignLeft = Boolean.valueOf(StringUtils.defaultIfEmpty(ele.attributeValue("columnAlignLeft"), "false"));
				
				es = ele.elements();
				
				if (CollectionUtils.isNotEmpty(es)) {
					List<List<Bale>> groups = new ArrayList<List<Bale>>();
					
					List<Bale> columns = new ArrayList<Bale>(es.size());
					groups.add(columns);
					
					Bale column = null;
					for (Element e: es) {
						
						if ("br".equals(e.getName())) {
							columns = new ArrayList<Bale>(es.size());
							groups.add(columns);
							continue;
						}
						
						column = new Bale();
						column.title = e.attributeValue("title");
						column.type = e.attributeValue("type");
						column.field = e.attributeValue("field");
						column.dicCode = e.attributeValue("dicCode");
						column.format = e.attributeValue("format");
						column.emptyAs = e.attributeValue("emptyAs");
						column.required = e.attributeValue("required");
						column.brp = Boolean.valueOf(StringUtils.defaultIfEmpty(e.attributeValue("brp"), "false"));
						
						columns.add(column);
					}
					set.columns = groups;
					set.setFieldTitles();
				}
				
				list.add(set);
			}
		}
		
		return list;
	}
	
	private static class BaleSet {
		private String title;
		private String bean;//spring中对应的Facade id
		private String method;//facade中的方法名称，参数为人员id (personOid)
		private List<List<Bale>> columns;//列
		private short height;
		private short colspan;
		private boolean columnDisplay;
		private boolean columnAlignLeft;
		
		private List<String[]> fieldTitles;//标题集合
		
		private int filedTitlesMaxLength;// 标题中最长的长度
		
		private void setFieldTitles() {
			List<String[]> titles = new ArrayList<String[]>();
			if (CollectionUtils.isNotEmpty(columns)) {
				int length = 0;
				for (List<Bale> bales : columns) {
					List<String> t = new ArrayList<String>();
					
					for (Bale b: bales) {
						t.add(b.title);
					}
					length = Math.max(length, t.size());
					titles.add(t.toArray(new String[t.size()]));
				}
				
				filedTitlesMaxLength = length;
			}
			
			fieldTitles = titles;
		}
	}
	
	private static class Bale {
		private String title;//信息标题
		private String type;//生成方式，暂只支持rowNumber
		private String field;//DTO中的取值属性名称
		private String dicCode;//翻译代码
		private String format;//时间格式，如果是date类型，默认会转成yyyy-MM-dd，需要指定为其他格式类型时，设置此属性
		private String emptyAs;//字段默认显示值
		private boolean brp;//处理<br>标签
		private String required;//必填项
	}
}
