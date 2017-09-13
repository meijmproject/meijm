package com.yh.hr.info.dataimport.person.web.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yh.hr.info.dataimport.person.facade.ImCollectTemplateFacade;
import com.yh.hr.info.dataimport.person.tool.Singleton;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.hr.component.im.dto.CheckColumnDTO;
import com.yh.hr.component.im.dto.CheckResultDTO;
import com.yh.hr.component.im.dto.ColumnDefDTO;
import com.yh.hr.component.im.dto.TableDefDTO;
import com.yh.hr.info.dataimport.person.facade.CheckBatchHandleFacade;
import com.yh.hr.info.dataimport.person.facade.CheckManageFacade;
import com.yh.hr.info.dataimport.person.facade.CheckUnusualHandleFacade;
import com.yh.hr.info.dataimport.person.facade.ImImportBatchFacade;
import com.yh.hr.info.dataimport.person.facade.ImportPersonFacade;
import com.yh.hr.info.dataimport.person.facade.TableDataOperateFacade;
import com.yh.hr.info.dataimport.person.facade.VerPersonDataFacade;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.im.dto.ImCollectTemplateDTO;
import com.yh.hr.res.im.dto.ImDataUnusualLogDTO;
import com.yh.hr.res.im.dto.ImImportBatchDTO;
import com.yh.hr.res.unit.dto.UtOrgDTO;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.ConfigUtil;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.util.XlsUtils;
import com.yh.platform.core.web.action.BaseAction;

public class ImportPersonAction extends BaseAction {
	
	private final static String BLANK = "";
	private final static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private final static DecimalFormat numberFormat = new DecimalFormat("#");
	private final static int BIGIN_ROW_NUMBER = 1;
	
	private ImImportBatchFacade imImportBatchFacade = (ImImportBatchFacade) SpringBeanUtil.getBean("imImportBatchFacade");
	private ImCollectTemplateFacade imCollectTemplateFacade = (ImCollectTemplateFacade) SpringBeanUtil.getBean("imCollectTemplateFacade");
	private CheckUnusualHandleFacade checkUnusualHandleFacade = (CheckUnusualHandleFacade) SpringBeanUtil.getBean("checkUnusualHandleFacade");
	private CheckBatchHandleFacade checkBatchHandleFacade = (CheckBatchHandleFacade) SpringBeanUtil.getBean("checkBatchHandleFacade");
	private TableDataOperateFacade tableDataOperateFacade = (TableDataOperateFacade) SpringBeanUtil.getBean("tableDataOperateFacade");
	private CheckManageFacade checkManageFacade = (CheckManageFacade) SpringBeanUtil.getBean("checkManageFacade");
	private ImportPersonFacade importPersonFacade = (ImportPersonFacade) SpringBeanUtil.getBean("importPersonFacade");
	private VerPersonDataFacade verPersonDataFacade = (VerPersonDataFacade) SpringBeanUtil.getBean("verPersonDataFacade");
	
	
	/**
	 * 跳转到导入人员窗口
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goToUploadPersonListPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String unitOid = request.getParameter("unitOid");
		String isNewImport = request.getParameter("isNewImport");
		if(StringUtils.isNotBlank(unitOid)){
			HttpSession seesion = request.getSession(false);
			seesion.setAttribute("unitOid", unitOid);
		}
	//	Singleton.getInstance().setUnitOid(unitOid);
		if(isNewImport!=null&&DicConstants.YHRS0003_0.equals(isNewImport)) {
			ImImportBatchDTO imImportBatchDTO = imImportBatchFacade.findCurrentImImportBatchDTO();
			if(imImportBatchDTO!=null) {
				String importFlowStatus = imImportBatchDTO.getImportFlowStatus();
				Long importBatchOid = imImportBatchDTO.getImportBatchOid();
				if(DicConstants.YHRS0142_4.equals(importFlowStatus)) {
					String tempFilePath = ConfigUtil.getProperty("standard.import.dir") + "nowImportFile";
					FileInputStream  mInputStream = null;
					try {
						File fileAbsolute = new File(tempFilePath);
						File[] fileList = fileAbsolute.listFiles();
						File tempFile=null;
						if(fileList!=null) {
							tempFile = fileList[0];
							mInputStream = new FileInputStream(tempFile);
						}
						if(mInputStream!=null) {//不为空
							Workbook workbook = new HSSFWorkbook(mInputStream);
							Sheet sheet = workbook.getSheetAt(0);
							if(sheet==null) {
								throw new ServiceException(null,"不能取得Excel Sheet");
							}
							if(sheet.getLastRowNum()== 0) {
								throw new ServiceException(null,"导入文档数据为空");
							}
							Integer checkDataLength = 0;
							Integer checkDataFormat = 0;
							Integer checkDataMust = 0;
							List<ImDataUnusualLogDTO> logList = checkUnusualHandleFacade.findImDataUnusualLogDTOByBatchOidAndStatus(importBatchOid, DicConstants.YHRS0003_0);
							if(CollectionUtils.isNotEmpty(logList)) {
								for(ImDataUnusualLogDTO dto:logList) {
									if(DicConstants.YHRS0140_1.equals(dto.getCheckType())) {
										checkDataMust = dto.getCheckNopassedAmount();
									}
									if(DicConstants.YHRS0140_2.equals(dto.getCheckType())) {
										checkDataLength = dto.getCheckNopassedAmount();
									}
									if(DicConstants.YHRS0140_3.equals(dto.getCheckType())) {
										checkDataFormat = dto.getCheckNopassedAmount();
									}
								}
							}
							Singleton singleton = Singleton.getInstance();
							singleton.setChecjDataLength(String.valueOf(checkDataLength));
							singleton.setCheckDataFormat(String.valueOf(checkDataFormat));
							singleton.setCheckDataMust(String.valueOf(checkDataMust));
							singleton.setRowNum(String.valueOf(sheet.getLastRowNum()));
							singleton.setNowCheckDataNum(String.valueOf(sheet.getLastRowNum()));
							singleton.setNowImportPersonNum(String.valueOf("0"));
							request.setAttribute("formFile",tempFile.getAbsolutePath());
							request.setAttribute("checkDataLength",checkDataLength);
							request.setAttribute("checkDataFormat",checkDataFormat);
							request.setAttribute("checkDataMust",checkDataMust);
							request.setAttribute("rowNum",sheet.getLastRowNum());
							request.setAttribute("nowCheckDataNum",sheet.getLastRowNum());
							request.setAttribute("nowImportPersonNum",0);
							return mapping.findForward("success_beforecheckend");
						}	
					} catch(Exception e) {
						this.handleException(request,e, null);
					}finally {
						if(mInputStream!=null) {
							mInputStream.close();
						}
					}
				}
				if(DicConstants.YHRS0142_8.equals(importFlowStatus)) {
					int checkRelationNum = verPersonDataFacade.countExceptionNum(DicConstants.YHRS0140_5);
					int checkDicNum =verPersonDataFacade.countExceptionNum(DicConstants.YHRS0140_4);
					int checkCompleteNum = verPersonDataFacade.countExceptionNum(DicConstants.YHRS0140_6);
					int rowDicNum = verPersonDataFacade.listImCheckPersonInfo().size();
					request.setAttribute("checkRelationNum", checkRelationNum);
					request.setAttribute("checkDicNum", checkDicNum);
					request.setAttribute("checkCompleteNum", checkCompleteNum);
					request.setAttribute("rowDicNum", rowDicNum);
					return mapping.findForward("success_dicmapping");
				}
				if(DicConstants.YHRS0142_9.equals(importFlowStatus)||DicConstants.YHRS0142_10.equals(importFlowStatus)
						||DicConstants.YHRS0142_11.equals(importFlowStatus)||DicConstants.YHRS0142_12.equals(importFlowStatus)) {
					int checkRelationNum = verPersonDataFacade.countExceptionNum(DicConstants.YHRS0140_5);
					int checkCompleteNum = verPersonDataFacade.countExceptionNum(DicConstants.YHRS0140_6);
					int rowDicNum = verPersonDataFacade.listImCheckPersonInfo().size();
					request.setAttribute("checkRelationNum", checkRelationNum);
					request.setAttribute("checkCompleteNum", checkCompleteNum);
					request.setAttribute("rowDicNum", rowDicNum);
					request.setAttribute("importFlowStatus", importFlowStatus);
					return mapping.findForward("success_beforetransfer");
				}
			}
		}
		return mapping.findForward("success");
	}
	
	
	/**
	 * 跳转到数据检查窗口
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goTocheckImportFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String formFile = request.getParameter("formFile");
		request.setAttribute("formFile",formFile);
		return mapping.findForward("success");
	}
	
	/**
	 * 导入人员
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward uploadPersonList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FileInputStream  mInputStream = null;
		try{
			//String unitOid = Singleton.getInstance().getUnitOid();
			HttpSession seesion = request.getSession(false);
			String unitOid = seesion.getAttribute("unitOid").toString();
			String fileLocation = request.getParameter("formFile");
			int end = fileLocation.lastIndexOf(".");
			//获取到上一次保存的文件
			String defaultPhotoFile = ConfigUtil.getProperty("standard.import.dir") + "nowImportFile"+ "/"+ "nowImportFile"+ fileLocation.substring(end, fileLocation.length());
			mInputStream = new FileInputStream(defaultPhotoFile);
			if(unitOid==null) {
				throw new ServiceException(null, "未获取到单位ID");
			}
/*			if(orgOid==null) {
				throw new ServiceException(null, "未获取到科室ID");
			}*/
			if(mInputStream!=null) {//不为空
				Workbook workbook = new HSSFWorkbook(mInputStream);
				Sheet sheet = workbook.getSheetAt(0);
				if(sheet==null) {
					throw new ServiceException(null,"不能取得Excel Sheet");
				}
				if(sheet.getLastRowNum()== 0) {
					throw new ServiceException(null,"导入文档数据为空");
				}else {
					//清空校核人员导入异常数据明细表
					tableDataOperateFacade.emptyTable("yhc_im_check_person_unusual");
					//清空校核人员表
					tableDataOperateFacade.emptyTable("yhc_im_check_person_info");
					//清空导入人员表
					tableDataOperateFacade.emptyTable("yhc_im_import_person_info");
					//获得总行数(即此次导入的文件中有多少行数据),创建批次信息
					int rowNum=sheet.getLastRowNum();
					//创建批次表信息
					ImImportBatchDTO imImportBatchDTO = checkImportColoumNum(rowNum, unitOid);
					Long importBatchOid = imImportBatchDTO.getImportBatchOid();
					//获取列头
					List<Map<String,Object>> nameListMap = XlsUtils.getImportTableTitleMap(sheet);
					//List<String> stringList = XlsUtils.getImportTableTitle(sheet);
					List<CheckResultDTO> moreResultDTOList = new ArrayList<CheckResultDTO>(); //必填项检查
					
					//获取所有已被映射的采集项映射模板
					List<ImCollectTemplateDTO>  templateDTOList= imCollectTemplateFacade.findCollTemplateForMapped();
					
					//获取到人员姓名所在的列(注：因为在此之前，已存在映射的采集项模板和列头，因此没做null的判断)
					int nameXulie = 0;
					for(ImCollectTemplateDTO dto :templateDTOList){
						if("NAME".equals(dto.getDatabaseColumnCode())){
							nameXulie = getImportCollName(nameListMap,dto);
						}
					}
					
					//用于组装错误信息的集合
					List<Map<String,Object>> errorList = new ArrayList<Map<String,Object>>();
					
					//用于组装数据检查未通过的人员的数量
					int checkDataMust =0;   //必填项
					int checkDataFormat =0; //数据格式
					int checjDataLength =0; //数据长度
					//更新导入流程状态--导入前检查中
					checkBatchHandleFacade.updateBatchStatus(importBatchOid, DicConstants.YHRS0142_2);
					int nowCheckDataNum=0;//用于计算当前检查了多少条数据
					//遍历每一条数据
					for (int i = BIGIN_ROW_NUMBER; i <=sheet.getLastRowNum(); i++) {

						Row row = sheet.getRow(i);
						if(row==null) {
							continue;
						}
						//用于集成每条记录中检查未通过的个数
						int checkDataMustNum =0;   //必填项
						int checkDataFormatNum =0; //数据格式
						int checjDataLengthNum =0; //数据长度
						
						//导入数据检查组装
						List<CheckColumnDTO> resultDTOList = getResultDTOList(templateDTOList,nameListMap,row);
						if(CollectionUtils.isNotEmpty(resultDTOList)){
							//数据检查
							List<CheckResultDTO> checkResultDTOlist = checkManageFacade.check(resultDTOList,templateDTOList);
							if(CollectionUtils.isNotEmpty(checkResultDTOlist)) {
								//导入异常日志处理
								checkUnusualHandleFacade.updateUnusualLog(checkResultDTOlist);
								//更新导入批次数据量
								checkBatchHandleFacade.updateImportBeforeBatchAmount(importBatchOid, true);
								//组装错误信息
								StringBuffer msg = new StringBuffer();
								List<String> errorDataType = new ArrayList<String>();  //用于存储错误信息的类型
								Map<String,Object> map =  new HashMap<String, Object>();
								
								for(CheckResultDTO checkResultDTO : checkResultDTOlist){
									if(checkResultDTO.getCheckMessage() != null){
										ImCollectTemplateDTO dto = imCollectTemplateFacade.findCollTemplateByColumnCode(checkResultDTO.getDatabaseColumnCode());
										if(checkResultDTO.getCheckType().equals(DicConstants.YHRS0140_1)){
											if(msg.length() == 0){
												msg.append(dto.getImportCollName()+"为必填项");
												errorDataType.add(DicConstants.YHRS0140_1);
											}else{
												msg.append("," + dto.getImportCollName()+"为必填项");
												errorDataType.add(DicConstants.YHRS0140_1);
											}
											checkDataMustNum++;
											//checkDataMust++;  //获取检查未通过的数量
										}
										if(checkResultDTO.getCheckType().equals(DicConstants.YHRS0140_2)){
											if(msg.length() == 0){
												msg.append(dto.getImportCollName()+"超长");
												errorDataType.add(DicConstants.YHRS0140_2);
											}else{
												msg.append("," + dto.getImportCollName()+"超长");
												errorDataType.add(DicConstants.YHRS0140_2);
											}
											checjDataLengthNum++;
											//checjDataLength++;
										}if(checkResultDTO.getCheckType().equals(DicConstants.YHRS0140_3)){
											if(msg.length() == 0){
												msg.append(dto.getImportCollName()+"格式不正确");
												errorDataType.add(DicConstants.YHRS0140_3);
											}else{
												msg.append("," + dto.getImportCollName()+"格式不正确");
												errorDataType.add(DicConstants.YHRS0140_3);
											}
											checkDataFormatNum++;
											//checkDataFormat++;
										}
										//针对科室不存在的情况统计
										if(checkResultDTO.getCheckType().equals(DicConstants.YHRS0140_7)){
											if(msg.length() == 0){
												msg.append(dto.getImportCollName()+"不存在");
												errorDataType.add(DicConstants.YHRS0140_7);
											}else{
												msg.append("," + dto.getImportCollName()+"不存在");
												errorDataType.add(DicConstants.YHRS0140_7);
											}
											checkDataMustNum++;
											//checkDataMust++; 
										}
									}
								}
								map.put("msg", msg);
								map.put("rowNumber", i);
								map.put("errorDataTypeList", errorDataType);
								//获取每条记录中错误信息的序列
								List<String> importDataErrorNum = getImportDataErrorNum(checkResultDTOlist, templateDTOList,nameListMap);
								map.put("errorNumList", importDataErrorNum);
								errorList.add(map);
							}else {
								//更新导入批次数据量
								checkBatchHandleFacade.updateImportBeforeBatchAmount(importBatchOid, false);
							}
							moreResultDTOList.addAll(checkResultDTOlist);
						}
						nowCheckDataNum++;
						//组装检查未通过的人数
						if(checjDataLengthNum != 0 && checjDataLengthNum >=1 ){
							checjDataLength = checjDataLength +1;
						}
						if(checkDataFormatNum != 0 && checkDataFormatNum >=1 ){
							checkDataFormat= checkDataFormat +1;
						}
						if(checkDataMustNum != 0 && checkDataMustNum >=1 ){
							checkDataMust= checkDataMust +1;
						}
						Singleton singleton = Singleton.getInstance();
						singleton.setChecjDataLength(String.valueOf(checjDataLength));
						singleton.setCheckDataFormat(String.valueOf(checkDataFormat));
						singleton.setCheckDataMust(String.valueOf(checkDataMust));
						singleton.setRowNum(String.valueOf(rowNum));
						singleton.setNowCheckDataNum(String.valueOf(nowCheckDataNum));
						singleton.setNowImportPersonNum("0");
						/*HttpSession session = request.getSession(false);//true明确是创建新会话
						session.setAttribute("checkDataMust", checkDataMust);
						session.setAttribute("checkDataFormat",checkDataFormat);
						session.setAttribute("checjDataLength",checjDataLength);
						session.setAttribute("rowNum",rowNum);
						session.setAttribute("nowCheckDataNum",nowCheckDataNum);*/
					}
					if(CollectionUtils.isNotEmpty(errorList)){
						//生成错误数据的excel
						outPutTempUnitByExcel(errorList,mInputStream,"nowImportFile"+ fileLocation.substring(end, fileLocation.length()),workbook,nameXulie);
					}
					//数据检查
					Boolean hasNoPassedLogs = checkUnusualHandleFacade.checkNopassedLogs(importBatchOid);
					if(hasNoPassedLogs){
						//更新导入流程状态--导入前检查未通过
						checkBatchHandleFacade.updateBatchStatus(importBatchOid, DicConstants.YHRS0142_4);
						mInputStream.close();
						response.getWriter().write(JSONHelper.fromObject(false, null).toString());
					}else{
						//更新导入流程状态--导入前检查通过
						checkBatchHandleFacade.updateBatchStatus(importBatchOid, DicConstants.YHRS0142_3);
						//更新导入流程状态--导入中
						checkBatchHandleFacade.updateBatchStatus(importBatchOid, DicConstants.YHRS0142_5);
						//将数据导入人员、校核表中
						for (int i = BIGIN_ROW_NUMBER; i <=sheet.getLastRowNum(); i++) {
							createImportData( sheet,  templateDTOList, nameListMap, importBatchOid ,i);
							Singleton singleton = Singleton.getInstance();
							singleton.setNowImportPersonNum(String.valueOf(i));
						}
						ImImportBatchDTO batchDTO = imImportBatchFacade.findCurrentImImportBatchDTO();
						batchDTO.setImportAmount(sheet.getLastRowNum());//更新导入数据量
						imImportBatchFacade.update(imImportBatchDTO);
						//更新导入流程状态--导入完成
						checkBatchHandleFacade.updateBatchStatus(importBatchOid, DicConstants.YHRS0142_6);
						response.getWriter().write(JSONHelper.fromObject(true, null).toString());
					}
				}
			}
			else {
				throw new ServiceException(null,"上传的文件为空");
			}
		}catch(Exception e) {
			this.handleException(request,e, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "人员导入失败")).toString());
			return null;
		}finally {
			if(mInputStream!=null) {
				mInputStream.close();
			}
		}
		return null;
	}
	
	/**
	 * 获取每条记录中错误信息的序列
	 * @param resultDTOList
	 * @throws FileNotFoundException 
	 */
	private List<String> getImportDataErrorNum(List<CheckResultDTO> checkResultDTOlist,List<ImCollectTemplateDTO>  templateDTOList, List<Map<String,Object>> nameListMap) throws Exception {
		//组装数据异常的名称
		List<ImCollectTemplateDTO> errorCheckList = new ArrayList<ImCollectTemplateDTO>();
		List<String> importDataErrorNum = new ArrayList<String>();
		//获取到异常的列
		for(CheckResultDTO checkResultDTO : checkResultDTOlist){
			for(ImCollectTemplateDTO  dto :templateDTOList){
				String code = dto.getDatabaseColumnCode();
				//如果异常信息与之匹配
				if(checkResultDTO.getDatabaseColumnCode().equals(code)){
					errorCheckList.add(dto);
				}
			}
		}
		for(ImCollectTemplateDTO dto :errorCheckList){
			int errorXulie  = getImportCollName(nameListMap , dto);
			importDataErrorNum.add(String.valueOf(errorXulie));
		}
		return importDataErrorNum;
	}
	
	
	/**
	 * 将错误数据导出
	 * 返回文件名称
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public ActionForward downImportPersonExcel(ActionMapping mapping, ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		try{
			request.setCharacterEncoding("UTF-8");
			String nowFileName = request.getParameter("fileName");
			int end = request.getParameter("fileName").lastIndexOf(".");
			//因考虑到csv格式的文件不能进行设置样式的操作，所以统一生成xls文件
			String fileName= "error_nowImportFile.xls";// + nowFileName.substring(end, nowFileName.length());
			String path = ConfigUtil.getProperty("standard.template.dir") + "nowImportFile";
		    
			String filePath = path +"/"+ fileName;//文件路径
			File file=new File(filePath);
			if(!file.exists())
			{	
				throw new ServiceException(null,"你下载的文件不存在!");
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
	
	/**
	 * 将错误数据写入excel中存储
	 * 返回文件名称
	 * @param request
	 * @param response
	 * @param errorList
	 * @param formFile
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private String outPutTempUnitByExcel(List<Map<String, Object>> errorList, FileInputStream  mInputStream,String fileLocation,Workbook workbook,int nameXulie) throws Exception {
		String path = ConfigUtil.getProperty("standard.template.dir") + "nowImportFile";
		File dir =new File(path);
		if(!dir.exists()) {
			dir.mkdirs(); //删除文件夹
		}else{
			//存在的话，看是否文件夹下面存在相同的文件，若存在即删除
			File firstFile = new File(dir, "error_nowImportFile.xls");//+fileLocation);
			if(firstFile.exists()){
				firstFile.delete();//删除文件
			}
		}
		Sheet sheet = workbook.getSheetAt(0);
		Row unitRow = sheet.getRow(BIGIN_ROW_NUMBER-1);
		
		Font font = workbook.createFont(); 
	    font.setFontHeightInPoints((short) 15); //字体大小 
	    font.setFontName("宋体"); 
	    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //粗体   HSSFFont.BOLDWEIGHT_NORMAL ，HSSFFont.BOLDWEIGHT_BOLD
		
	    //设置列头样式
	    CellStyle cellStyle1 = workbook.createCellStyle();
	    cellStyle1.setFont(font);
	    cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
	    cellStyle1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中 
	    
	    //必填项检查未通过的设置填充数据样式
	    CellStyle cellStyle2 = workbook.createCellStyle();
	    setCellStyleColor(cellStyle2,HSSFColor.RED.index);
	    
	    //数据格式检查未通过的设置填充数据样式
	    CellStyle cellStyle3 = workbook.createCellStyle();
	    setCellStyleColor(cellStyle3,HSSFColor.PINK.index);
	    
	    //数据长度检查未通过的设置填充数据样式
	    CellStyle cellStyle4 = workbook.createCellStyle();
	    setCellStyleColor(cellStyle4,HSSFColor.LIGHT_ORANGE.index);
	    
		Cell cell = unitRow.createCell(sheet.getRow(0).getPhysicalNumberOfCells()+1);
		cell.setCellValue("错误信息");
		cell.setCellStyle(cellStyle1);
		//获得总列数
		int coloumNum=sheet.getRow(0).getPhysicalNumberOfCells();
		for(int j = 0; j<= coloumNum; j++){
			if(j == coloumNum){
				//设置放置错误信息的列的宽度
				sheet.setColumnWidth((short)j, (short)15000);
			}
		}
		sheet.setColumnWidth((short)0, (short)5000);
		for (int i = 1; i <=sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			Cell c = row.createCell(sheet.getRow(0).getPhysicalNumberOfCells());
			boolean flag = false;
			for(Map<String,Object> map: errorList) {
				if(Integer.parseInt(map.get("rowNumber").toString())==i) {
					//获取到数据异常的列
					List<String> list1 = (List) map.get("errorNumList");
					List<String> errorDataTypeList = (List) map.get("errorDataTypeList");
					c.setCellValue(map.get("msg").toString());  //设置错误信息
					//c.setCellStyle(cellStyle1);                 //设置样式
					
					//获取到姓名所在的列
					Cell name = row.getCell(nameXulie);
					//重新创建一个单元格用于替换
					Cell nameCell = row.createCell(nameXulie);
					nameCell.setCellValue(XlsUtils.getCellValue(name));
					nameCell.setCellStyle(cellStyle2);
					
					for(int j=0;j<list1.size();j++){  
						//获取指定cell的值,设置样式为对应的颜色
						Cell nowCell = row.getCell(Integer.valueOf(list1.get(j)));
						//重新创建一个单元格用于替换
						Cell nowErrorCell = row.createCell(Integer.valueOf(list1.get(j)));
						nowErrorCell.setCellValue(XlsUtils.getCellValue(nowCell));
						//必填项
						if(errorDataTypeList.get(j).equals(DicConstants.YHRS0140_1)){
							nowErrorCell.setCellStyle(cellStyle2);
						}
						//数据项长度
						if(errorDataTypeList.get(j).equals(DicConstants.YHRS0140_2)){
							nowErrorCell.setCellStyle(cellStyle3);
						}
						//数据格式
						if(errorDataTypeList.get(j).equals(DicConstants.YHRS0140_3)){
							nowErrorCell.setCellStyle(cellStyle4);
						}
						//科室检查
						if(errorDataTypeList.get(j).equals(DicConstants.YHRS0140_7)){
							nowErrorCell.setCellStyle(cellStyle2);
						}
					}
					flag = false;
					break;
				}else {
					flag = false;
				}
			}
			if(flag) {
				Row r = sheet.getRow(i);
	            sheet.removeRow(r);
			}
		}
		int end = fileLocation.lastIndexOf(".");
		File output = new File(dir, "error_nowImportFile.xls");//+fileLocation.substring(end, fileLocation.length()));
	    FileOutputStream os = new FileOutputStream(output); 
	    workbook.write(os);
	    //关闭输出流
	    os.flush();
	    os.close();
	    return output.getName();
	}
	
	
	/**根据传入的不同的颜色，设置不同的颜色样式
	 * @param cellStyle
	 * @param color
	 * @throws Exception 
	 */
	private void setCellStyleColor( CellStyle cellStyle,short color) throws ServiceException   
	{
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框   
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框   
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框   
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框 
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
			cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中 
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  //设置单元格背景颜色
			cellStyle.setFillForegroundColor(color);  //自定义单元格颜色
	}
	
	
	/**将数据导入人员、校核表中
	 * @param sheet
	 * @param templateDTOList
	 * @param stringList
	 * @param importBatchOid
	 * @throws Exception 
	 */
	private void createImportData(Sheet sheet,List<ImCollectTemplateDTO>  templateDTOList, List<Map<String,Object>> nameListMap,Long importBatchOid ,int i) throws Exception {
			List<ColumnDefDTO>  columnDefCheckDTOList = new ArrayList<ColumnDefDTO>();  //导入校核表信息
			for(ImCollectTemplateDTO templateDTO :templateDTOList){
				Row row = sheet.getRow(i);
				if(row==null) {
					continue;
				}
				ColumnDefDTO columnDefDTO = new ColumnDefDTO();
				//获取到序列，通过序列获取到导入的文件对应的值
				int xulie = getImportCollName(nameListMap,templateDTO);
				String columnValue = getString(row,xulie);
				if(columnValue.length() == 0){
					columnValue = null;
				}
				columnDefDTO.setColumnValue(columnValue);  //字段值，从导入的文件中获取值set  
				if(templateDTO.getDatabaseColumnCodeName()!= null && templateDTO.getDatabaseColumnCodeName().length() > 0){
					columnDefDTO.setColumnCode(templateDTO.getDatabaseColumnCodeName()); //字段代码,从采集模板表中获取
				}else{
					columnDefDTO.setColumnCode(templateDTO.getDatabaseColumnCode()); //字段代码,从采集模板表中获取
				}
				columnDefCheckDTOList.add(columnDefDTO);
				if("HIRE_DEPT_OID".equals(templateDTO.getDatabaseColumnCode())) {
					int index = getImportCollName(nameListMap,templateDTO);
					String colValue = getString(row,index);
					if(colValue != null&&!"".equals(colValue)){
						UtOrgDTO orgDTO = importPersonFacade.findOrgDTOByName(colValue);
						if(orgDTO!=null) {
							ColumnDefDTO colDTO = new ColumnDefDTO();
							colDTO.setColumnCode(templateDTO.getDatabaseColumnCode());
							colDTO.setColumnValue(orgDTO.getOrgOid().toString());
							columnDefCheckDTOList.add(colDTO);
						}
					}
				}
			}
			//如果信息不为空，导入校核表、人员表中
			if(CollectionUtils.isNotEmpty(columnDefCheckDTOList)){
				ColumnDefDTO columnDefDTO = new ColumnDefDTO();
				columnDefDTO.setColumnCode("IMPORT_BATCH_OID");
				columnDefDTO.setColumnValue(importBatchOid.toString());
				columnDefCheckDTOList.add(columnDefDTO);
				
				TableDefDTO tableDefDTO = new TableDefDTO();
				tableDefDTO.setColumns(columnDefCheckDTOList);
				tableDefDTO.setTableCode("yhc_im_check_person_info");
				tableDataOperateFacade.insertData(tableDefDTO);
				
				List<ColumnDefDTO> defList = new ArrayList<ColumnDefDTO>();

				for(ColumnDefDTO defDTO : columnDefCheckDTOList) {
					if("HIRE_DEPT_OID".equals(defDTO.getColumnCode())) {
						continue;
					}
					defList.add(defDTO);
				}
				
				TableDefDTO defDTO = new TableDefDTO();
				defDTO.setColumns(defList);
				defDTO.setTableCode("yhc_im_import_person_info");
				tableDataOperateFacade.insertData(defDTO);
			}
	}
	
	
	/**
	 * 创建批次信息
	 * @param resultDTOList
	 * @throws FileNotFoundException 
	 */
	private ImImportBatchDTO checkImportColoumNum(int rowNum,String unitOid) throws Exception {
		ImImportBatchDTO imImportBatchDTO =  new ImImportBatchDTO();
		imImportBatchDTO.setTotalAmount(rowNum);     //文件数据量
		imImportBatchDTO.setStartTime(DateUtil.now());  //导入开始时间
		imImportBatchDTO.setBatchBelongId(Long.valueOf(unitOid));  //单位
		imImportBatchDTO.setBatchBelongType(DicConstants.YHRS0003_1); //批次隶属类型	1：单位	2：科室	3：地区
		imImportBatchDTO.setImportFlowStatus(DicConstants.YHRS0142_1); //开始解析文件
		Long importBatchOid = imImportBatchFacade.create(imImportBatchDTO);
		imImportBatchDTO.setImportBatchOid(importBatchOid);
		return imImportBatchDTO;
	}
	
	
	/**
	 * 导入数据检查组装
	 * @param resultDTOList
	 * @throws FileNotFoundException 
	 */
	public List<CheckColumnDTO> getResultDTOList(List<ImCollectTemplateDTO>  templateDTOList, List<Map<String,Object>> nameListMap ,Row row)throws Exception{
		List<CheckColumnDTO> resultDTOList = new ArrayList<CheckColumnDTO>(); //必填项检查
		if(CollectionUtils.isNotEmpty(templateDTOList)){
			//数据检查
			for(ImCollectTemplateDTO dto :templateDTOList){
				//必填项检查
				if(DicConstants.YHRS0140_1.equals(dto.getIsRequired())){
					CheckColumnDTO columnDTO = new CheckColumnDTO();
					columnDTO.setImportCollName(dto.getImportCollName());
					columnDTO.setCheckType(DicConstants.YHRS0140_1);
					//获取到序列，通过序列获取到值
					int xulie = getImportCollName(nameListMap,dto);
					columnDTO.setImportCollValue(getString(row,xulie));
					resultDTOList.add(columnDTO);
				}
				//时间类型数据检查
				if(DicConstants.YHRS0139_2.equals(dto.getDatabaseColumnType())){
					CheckColumnDTO columnDTO = new CheckColumnDTO();
					columnDTO.setImportCollName(dto.getImportCollName());
					columnDTO.setCheckType(DicConstants.YHRS0140_3);
					//获取到序列，通过序列获取到值
					int xulie = getImportCollName(nameListMap,dto);
					columnDTO.setImportCollValue(getString(row,xulie));
					resultDTOList.add(columnDTO);
				}else {
					//身份证号格式检查
//					if("ID_NO".equals(dto.getDatabaseColumnCode())) {
//						CheckColumnDTO colDTO = new CheckColumnDTO();
//						colDTO.setImportCollName(dto.getImportCollName());
//						colDTO.setCheckType(DicConstants.YHRS0140_3);
//						//获取到序列，通过序列获取到值
//						int index = getImportCollName(nameListMap,dto);
//						colDTO.setImportCollValue(getString(row,index));
//						resultDTOList.add(colDTO);
//					}
				}
				CheckColumnDTO columnDTO = new CheckColumnDTO();
				columnDTO.setImportCollName(dto.getImportCollName());
				columnDTO.setCheckType(DicConstants.YHRS0140_2);
				//获取到序列，通过序列获取到值
				int xulie = getImportCollName(nameListMap,dto);
				columnDTO.setImportCollValue(getString(row,xulie));
				resultDTOList.add(columnDTO);
				//科室检查
				if("HIRE_DEPT_OID".equals(dto.getDatabaseColumnCode())) {
					CheckColumnDTO colDTO = new CheckColumnDTO();
					colDTO.setImportCollName(dto.getImportCollName());
					colDTO.setCheckType(DicConstants.YHRS0140_7);
					//获取到序列，通过序列获取到值
					int index = getImportCollName(nameListMap,dto);
					colDTO.setImportCollValue(getString(row,index));
					resultDTOList.add(colDTO);
				}
			}
		}
		return resultDTOList ;
	}
	
	/**
	 * 获取列头对应的序列
	 * @param stringList
	 * @param ImCollectTemplateDTO
	 * @throws FileNotFoundException 
	 */
	public int getImportCollName(List<Map<String,Object>> nameListMap,ImCollectTemplateDTO dto)throws Exception{
		int xulie= 0;
		for(Map<String,Object> map : nameListMap){
			List<String> list1 = (List) map.get("nameList");
			for(int i=0;i<list1.size();i++){
				String lieName =  list1.get(i);
				if(lieName.equals(dto.getImportCollName())){
					 xulie = (Integer) map.get("xulie");
				}
			}
		}
		return xulie;
/*		int xulie= 0;
		//遍历列头
		for(int j=0; j<stringList.size();j++){
			if(dto.getImportCollName().equals(stringList.get(j))){
				xulie = j;
				return xulie;
			}
		}
		return xulie;*/
	}
	
	

	/**
	 * 表格某行的第几个单元格字符串
	 * @param unitRow
	 * @param index
	 * @return
	 * @throws ServiceException
	 */
	private String getString(Row row, int index) throws ServiceException {
		Cell cell = row.getCell((short) index);
		if (cell == null) {
			return BLANK;
		}
		switch (cell.getCellType()) {
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
	
}
