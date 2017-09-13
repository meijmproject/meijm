package com.yh.hr.info.dataexport.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.info.dataexport.facade.DataExportManagerFacade;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

public class DataExportManagerAction extends BaseAction {

	private DataExportManagerFacade dataExportManagerFacade =  (DataExportManagerFacade) SpringBeanUtil.getBean("dataExportManagerFacade");
	/**
	 * 导出人员名册
	 * 
	 * @param mapping
	 * @param actionform
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward printPersonInfoByExcel(ActionMapping mapping, ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response){
		try {
			String flags = request.getParameter("flags");
			String[] idList = flags.split(",");
			List<List<String>> paramList = getFieldAndName(idList);
			
			List<String> fieldList = paramList.get(0);   //数据库字段列
			List<String> nameList = paramList.get(1);   //属性中文名字段列
			List<String> transFlagList = paramList.get(2);  //转码标识列
			
//			StringBuffer fields = new StringBuffer();
//			for(String str : fieldList){
//				fields.append(str);
//				fields.append(" ,");
//			}
			
			// 导出excel
			String fileName = "医院人员名册表.xlsx";
			response.reset();
			response.setCharacterEncoding("utf-8");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String((fileName).getBytes("utf-8"), "ISO8859-1"));
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");// 设置response内容的类型
			HSSFWorkbook wb = printByExcel(fieldList, nameList, transFlagList);

			wb.write(response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
			
			/*测试
			 * List<Object[]> personlist = dataExportManagerFacade.listDataExportPersonInfo(fields);
			System.out.println(personlist);*/
			
		} catch (Exception e) {
			handleException(request, e, "数据导出出错!");
			return mapping.findForward("error");
		}
		return null;
	}

	private HSSFWorkbook printByExcel(List<String> fieldList, List<String> nameList, List<String> transFlagList) throws Exception {
		
		HSSFWorkbook wb=new HSSFWorkbook(); 
		HSSFCellStyle titleStyle = buildTitleStyle(wb);
		//查询人员信息
		List<List<Object>> personList = dataExportManagerFacade.listDataExportPersonInfo(fieldList, transFlagList);
		
	    if(CollectionUtils.isNotEmpty(personList))
	    {
	    bulidPersonExportInfo(personList, nameList, wb, titleStyle);
	    }
		return wb;
	}

	/**
	 * 表名样式
	 * @param wb
	 * @return
	 */
	private HSSFCellStyle buildTitleStyle(HSSFWorkbook wb) {
		// 列表的单元格格式
		HSSFFont titleFont = wb.createFont(); 
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		titleFont.setFontName("宋体");
		titleFont.setFontHeightInPoints((short)26);
		HSSFCellStyle titleStyle= wb.createCellStyle(); 
		titleStyle.setFont(titleFont);
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);     // 横向居中
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);   // 下边框   
		titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);     // 左边框   
		titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);    // 右边框   
		titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);      // 上边框
		
		return titleStyle;
	}
	
	/**
	 * 导出人员名册信息
	 * @param 
	 * @param 
	 * @param 
	 */
	private void bulidPersonExportInfo(List<List<Object>> personList, List<String> nameList, HSSFWorkbook wb, HSSFCellStyle titleStyle) {
		
		HSSFSheet sheet=wb.createSheet("人员名册表");  //获取到工作表，因为一个excel可能有多个工作表
		
		CellRangeAddress cra=new CellRangeAddress(0, 0, 0, nameList.size());  //合并单元格
		sheet.addMergedRegion(cra);
		sheet.autoSizeColumn(1);
		
		HSSFRow titleRow = sheet.createRow(0);  //获取第一行
		HSSFCell csCell;
		csCell = titleRow.createCell(0);
		csCell.setCellValue("医院人员名册表");    //表名行
		csCell.setCellStyle(titleStyle);
		
		HSSFRow nameRow = sheet.createRow(1);  //获取第二行，属性行
		csCell = nameRow.createCell(0);        //序列
		csCell.setCellValue("序列");
		for(int i=0; i<nameList.size(); i++){
			csCell = nameRow.createCell(i+1);
			csCell.setCellValue(nameList.get(i).trim());
		}
		
		int rowSize = CollectionUtils.isEmpty(personList)? 0 : personList.get(0).size();  //获取结果集的行数
		if(CollectionUtils.isNotEmpty(nameList)){
			for(int i=0; i<rowSize; i++){
				HSSFRow valueRow = sheet.createRow(i+2);  //获取从第三行开始的行，值行
				for(int j=0; j<personList.size(); j++){   //j代表列
					csCell = valueRow.createCell(0);        //序列
					csCell.setCellValue(i+1);
					
					csCell = valueRow.createCell(j+1);
	//				System.out.println(personList.get(j)[i]);
					String value = (CollectionUtils.isNotEmpty(personList.get(j))&& !"".equals(personList.get(j).get(i))&&personList.get(j).get(i)!=null)? personList.get(j).get(i).toString().trim() : "";
					csCell.setCellValue(value);
					}
				}
//			XSSFRow valueRow = sheet.createRow(i+2);  //获取从第三行开始的行，值行
//			personList.get(j).get(i)==null&&"".equals(personList.get(j).get(i))
//			
//			Object[] valueList = personlist.get(i);
//			List<String> resultList = translationDic(valueList, transFlagList);
//			for(int j=0; j<resultList.size(); j++){
//				csCell = valueRow.createCell(j+1);
//				if(null!=resultList.get(j)&&""!=resultList.get(j)){
//					csCell.setCellValue(resultList.get(j).toString().trim());
//				}
//			}
		}
	}
		
	protected List<List<String>> getFieldAndName(String[] idList) {

		List<List<String>> list = new ArrayList<List<String>>();
		List<String> fieldList = new ArrayList<String>();   //数据库字段集
		List<String> nameList = new ArrayList<String>();    //属性中文名集
		List<String> transFlagList = new ArrayList<String>();     //字典码集

		// 传属性名方式
		for (int i = 0; i < idList.length; i++) {
			String param = idList[i].trim();
			if ("name".equals(param)) {
				fieldList.add("NAME");
				nameList.add("姓名");
				transFlagList.add("none");
			} else if ("idCode".equals(param)) {
				fieldList.add("ID_CODE");
				nameList.add("证件类别代码");
				transFlagList.add("YHRS0002");
			} else if ("idNo".equals(param)) {
				fieldList.add("ID_NO");
				nameList.add("证件号码");
				transFlagList.add("none");
			} else if ("englishName".equals(param)) {
				fieldList.add("ENGLISH_NAME");
				nameList.add("外文姓名");
				transFlagList.add("none");
			} else if ("alternativeName".equals(param)) {
				fieldList.add("ALTERNATIVE_NAME");
				nameList.add("曾用名");
				transFlagList.add("none");
			} else if ("sexCode".equals(param)) {
				fieldList.add("SEX_CODE");
				nameList.add("性别");
				transFlagList.add("YHRS0001");
			} else if ("birthday".equals(param)) {
				fieldList.add("BIRTHDAY");
				nameList.add("出生日期");
				transFlagList.add("none");
			} else if ("peopleCode".equals(param)) {
				fieldList.add("PEOPLE_CODE");
				nameList.add("民族");
				transFlagList.add("YHRS0004");
			} else if ("nationalityCode".equals(param)) {
				fieldList.add("NATIONALITY_CODE");
				nameList.add("国籍");
				transFlagList.add("YHRS0005");
			} else if ("ancestorPlaceCode".equals(param)) {
				fieldList.add("ANCESTOR_PLACE_CODE");
				nameList.add("籍贯");
				transFlagList.add("YHRS0006");
			} else if ("birthplaceCode".equals(param)) {
				fieldList.add("BIRTHPLACE_CODE");
				nameList.add("出生地");
				transFlagList.add("YHRS0006");
			} else if ("hukouPlace".equals(param)) {
				fieldList.add("HUKOU_PLACE");
				nameList.add("户口所在地");
				transFlagList.add("YHRS0006");
			} else if ("isSz".equals(param)) {
				fieldList.add("IS_SZ");
				nameList.add("是否本地户口");
				transFlagList.add("YHRS0003");
			} else if ("healthStatusCode".equals(param)) {
				fieldList.add("HEALTH_STATUS_CODE");
				nameList.add("健康状况");
				transFlagList.add("YHRS0007");
			} else if ("marriageStatusCode".equals(param)) {
				fieldList.add("MARRIAGE_STATUS_CODE");
				nameList.add("婚姻状况");
				transFlagList.add("YHRS0008");
			} else if ("address".equals(param)) {
				fieldList.add("ADDRESS");
				nameList.add("家庭住址");
				transFlagList.add("none");
			} else if ("email".equals(param)) {
				fieldList.add("EMAIL");
				nameList.add("个人Email地址");
				transFlagList.add("none");
			} else if ("mobilePhone".equals(param)) {
				fieldList.add("MOBILE_PHONE");
				nameList.add("手机号码");
				transFlagList.add("none");
			} else if ("officePhone".equals(param)) {
				fieldList.add("OFFICE_PHONE");
				nameList.add("办公电话");
				transFlagList.add("none");
			} else if ("unitOid".equals(param)) {
				fieldList.add("UNIT_OID");
				nameList.add("工作单位");
				transFlagList.add("findUnit");
			} else if ("deptOid".equals(param)) {
				fieldList.add("DEPT_OID");
				nameList.add("工作部门");
				transFlagList.add("findDept");
			} else if ("hireDeptOid".equals(param)) {
				fieldList.add("HIRE_DEPT_OID");
				nameList.add("所在部门");
				transFlagList.add("findDept");
			} else if ("personStatus".equals(param)) {
				fieldList.add("PERSON_STATUS");
				nameList.add("人员状态");
				transFlagList.add("YHRS0009");
			} else if ("personType".equals(param)) {
				fieldList.add("PERSON_TYPE");
				nameList.add("人员类别");
				transFlagList.add("YHRS0010");
			} else if ("flagOfHkmctwChineseCode".equals(param)) {
				fieldList.add("FLAG_OF_HKMCTW_CHINESE_CODE");
				nameList.add("港澳台侨属");
				transFlagList.add("YHRS0011");
			} else if ("protTechFlag".equals(param)) {
				fieldList.add("PROT_TECH_FLAG");
				nameList.add("是否专业技术人员");
				transFlagList.add("YHRS0003");
			} else if ("exportFlag".equals(param)) {
				fieldList.add("EXPORT_FLAG");
				nameList.add("是否专家");
				transFlagList.add("YHRS0003");
			} else if ("doctorFlag".equals(param)) {
				fieldList.add("DOCTOR_FLAG");
				nameList.add("是否博士后研究人员");
				transFlagList.add("YHRS0003");
			} else if ("isAbordExpert".equals(param)) {
				fieldList.add("IS_ABORD_EXPERT");
				nameList.add("是否海外专家");
				transFlagList.add("YHRS0003");
			} else if ("isStudyAbordExpert".equals(param)) {
				fieldList.add("IS_STUDY_ABORD_EXPERT");
				nameList.add("是否留学回国专家");
				transFlagList.add("YHRS0003");
			} else if ("isComesChinaExpert".equals(param)) {
				fieldList.add("IS_COMES_CHINA_EXPERT");
				nameList.add("是否来华定居专家");
				transFlagList.add("YHRS0003");
			} else if ("isVeteran".equals(param)) {
				fieldList.add("IS_VETERAN");
				nameList.add("是否退役军人");
				transFlagList.add("YHRS0003");
			} else if ("isAllocation".equals(param)) {
				fieldList.add("IS_ALLOCATION");
				nameList.add("是否军转安置干部");
				transFlagList.add("YHRS0003");
			} else if ("isCadre".equals(param)) {
				fieldList.add("IS_CADRE");
				nameList.add("是否干部身份");
				transFlagList.add("YHRS0003");
			} else if ("dPositionType".equals(param)) {
				fieldList.add("D_POSITION_TYPE");
				nameList.add("编制类型");
				transFlagList.add("YHRS0012");
			} else if ("bankpoll".equals(param)) {
				fieldList.add("BANKPOLL");
				nameList.add("经费形式");
				transFlagList.add("YHRS0013");
			} else if ("personOrderView".equals(param)) {
				fieldList.add("PERSON_ORDER_VIEW");
				nameList.add("人员排序号");
			} else if ("entryCurrentUnitType".equals(param)) {
				fieldList.add("ENTRY_CURRENT_UNIT_TYPE");
				nameList.add("进入本单位方式");
				transFlagList.add("YHRS0110");
			} else if ("entryCurrentUnitDate".equals(param)) {
				fieldList.add("ENTRY_CURRENT_UNIT_DATE");
				nameList.add("进入本单位时间");
				transFlagList.add("none");
			} else if ("emergContact".equals(param)) {
				fieldList.add("EMERG_CONTACT");
				nameList.add("紧急联系人");
				transFlagList.add("none");
			} else if ("emergPhone".equals(param)) {
				fieldList.add("EMERG_PHONE");
				nameList.add("紧急联系人手机号码");
				transFlagList.add("none");
			} /*else if ("dispatchingUnitCode".equals(param)) {
				fieldList.add("DISPATCHING_UNIT_CODE");
				nameList.add("所属劳务派遣公司");
				transFlagList.add("none");
			} */else if ("personCode".equals(param)) {
				fieldList.add("PERSON_CODE");
				nameList.add("工号");
				transFlagList.add("none");
			} else if ("personIntOid".equals(param)) {
				fieldList.add("PERSON_INT_OID");
				nameList.add("人员内码ID");
				transFlagList.add("none");
			} else if ("levelCode".equals(param)) {
				fieldList.add("LEVEL_CODE");
				nameList.add("护士层级");
				transFlagList.add("YHRS0122");
			}
		}
		
		
		//传数值方式
		/*for(int i=0; i<idList.length; i++){
			int c = Integer.parseInt(idList[i].trim());
			switch (c)
			{
				case 0:
					fieldList.add("NAME");
					nameList.add("姓名");
					break;
				case 1:
					fieldList.add("ID_CODE");
					nameList.add("证件类别代码");
					break;
				case 2:
					fieldList.add("ID_NO");
					nameList.add("证件号码");
					break;
				case 3:
					fieldList.add("ENGLISH_NAME");
					nameList.add("外文姓名");
					break;
				case 4:
					fieldList.add("ALTERNATIVE_NAME");
					nameList.add("曾用名");
					break;
				case 5:
					fieldList.add("SEX_CODE");
					nameList.add("性别代码");
					break;
				case 6:
					fieldList.add("BIRTHDAY");
					nameList.add("出生日期");
					break;
				case 7:
					fieldList.add("PEOPLE_CODE");
					nameList.add("民族代码");
					break;
				case 8:
					fieldList.add("NATIONALITY_CODE");
					nameList.add("国籍代码");
					break;
				case 9:
					fieldList.add("ANCESTOR_PLACE_CODE");
					nameList.add("籍贯代码");
					break;
				case 10:
					fieldList.add("BIRTHPLACE_CODE");
					nameList.add("出生地代码");
					break;
				case 11:
					fieldList.add("HUKOU_PLACE");
					nameList.add("户口所在地");
					break;
				case 12:
					fieldList.add("IS_SZ");
					nameList.add("是否本地户口");
					break;
				case 13:
					fieldList.add("HEALTH_STATUS_CODE");
					nameList.add("健康状况代码");
					break;
				case 14:
					fieldList.add("MARRIAGE_STATUS_CODE");
					nameList.add("婚姻状况代码");
					break;
				case 15:
					fieldList.add("ADDRESS");
					nameList.add("家庭住址");
					break;
				case 16:
					fieldList.add("EMAIL");
					nameList.add("个人Email地址");
					break;
				case 17:
					fieldList.add("MOBILE_PHONE");
					nameList.add("手机号码");
					break;
				case 18:
					fieldList.add("OFFICE_PHONE");
					nameList.add("办公电话");
					break;
				case 19:
					fieldList.add("UNIT_OID");
					nameList.add("工作单位");
					break;
				case 20:
					fieldList.add("DEPT_OID");
					nameList.add("工作部门");
					break;
				case 21:
					fieldList.add("HIRE_DEPT_OID");
					nameList.add("所在部门");
					break;
				case 22:
					fieldList.add("PERSON_STATUS");
					nameList.add("人员状态");
					break;
				case 23:
					fieldList.add("PERSON_TYPE");
					nameList.add("人员类别");
					break;
				case 24:
					fieldList.add("FLAG_OF_HKMCTW_CHINESE_CODE");
					nameList.add("港澳台侨属标识");
					break;
				case 25:
					fieldList.add("PROT_TECH_FLAG");
					nameList.add("是否专业技术人员标识");
					break;
				case 26:
					fieldList.add("EXPORT_FLAG");
					nameList.add("是否专家标志");
					break;
				case 27:
					fieldList.add("DOCTOR_FLAG");
					nameList.add("是否博士后研究人员");
					break;
				case 28:
					fieldList.add("IS_ABORD_EXPERT");
					nameList.add("是否海外专家");
					break;
				case 29:
					fieldList.add("IS_STUDY_ABORD_EXPERT");
					nameList.add("是否留学回国专家");
					break;
				case 30:
					fieldList.add("IS_COMES_CHINA_EXPERT");
					nameList.add("是否来华定居专家");
					break;
				case 31:
					fieldList.add("IS_VETERAN");
					nameList.add("是否退役军人标识");
					break;
				case 32:
					fieldList.add("IS_ALLOCATION");
					nameList.add("是否军转安置干部");
					break;
				case 33:
					fieldList.add("IS_CADRE");
					nameList.add("是否干部身份");
					break;
				case 34:
					fieldList.add("D_POSITION_TYPE");
					nameList.add("编制类型");
					break;
				case 35:
					fieldList.add("BANKPOLL");
					nameList.add("经费形式");
					break;
				case 36:
					fieldList.add("PERSON_ORDER_VIEW");
					nameList.add("人员排序号");
					break;
				case 37:
					fieldList.add("ENTRY_CURRENT_UNIT_TYPE");
					nameList.add("进入本单位方式");
					break;
				case 38:
					fieldList.add("ENTRY_CURRENT_UNIT_DATE");
					nameList.add("进入本单位时间");
					break;
				case 39:
					fieldList.add("EMERG_CONTACT");
					nameList.add("紧急联系人");
					break;
				case 40:
					fieldList.add("EMERG_PHONE");
					nameList.add("紧急联系人手机号码");
					break;
				case 41:
					fieldList.add("DISPATCHING_UNIT_CODE");
					nameList.add("所属劳务派遣公司");
					break;
				case 42:
					fieldList.add("PERSON_CODE");
					nameList.add("工号");
					break;
				case 43:
					fieldList.add("PERSON_INT_OID");
					nameList.add("人员内码ID");
					break;
				case 44:
					fieldList.add("LEVEL_CODE");
					nameList.add("护士层级");
					break;
					
			}
		}*/

		list.add(fieldList);
		list.add(nameList);
		list.add(transFlagList);
		return list;
	}
	
	/**
	 * 字典码转码
	 * @param valueList
	 * @return
	 */
	public List<String> translationDic(Object[] valueList, List<String> transFlagList) throws ServiceException{
		List<String> resultList = new ArrayList<String>();
		for(int i=0; i<valueList.length; i++){
			String value;
			if(null!=valueList[i]&&""!=valueList[i]){
				value = valueList[i].toString();
			}else{
				value = "";
			}
			String flag = transFlagList.get(i);
			if(StringUtils.isNotEmpty(value)){
				if("findDept".equals(flag)){
					try {
						resultList.add(dataExportManagerFacade.getOrgName(NumberUtils.longValue(value)));   //查询部门
					} catch (DataAccessFailureException e) {
						e.printStackTrace();
					}
				}else if("findUnit".equals(flag)){
					try {
						resultList.add(dataExportManagerFacade.getUnitName(NumberUtils.longValue(value)));   //查询单位
					} catch (ServiceException e) {
						e.printStackTrace();
					}
				}else if(!"none".equals(flag)){
					try {
						resultList.add(DicHelper.viewName(flag, value));    //有字典码的字段，按字典码查询值
					} catch (ServiceException e) {
						e.printStackTrace();
					}     
				}else{
					resultList.add(value);     //不用转码的字段，直接加载
				}
			}else{
				resultList.add("");            //空字符串  
			}
		}
		return resultList;
		
	}
	
	/**
	 * handle error catched in action
	 * 
	 * @param request
	 * @param se
	 * @param log
	 */
	protected void handleException(HttpServletRequest request, Exception e,
			Object errorObject) {
		handleException(request, e instanceof ServiceException ? (ServiceException) e : new ServiceException(e.getMessage(), e), errorObject, null, null);
	}
	

}
