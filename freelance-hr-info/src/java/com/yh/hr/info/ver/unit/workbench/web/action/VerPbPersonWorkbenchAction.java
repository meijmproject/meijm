/**
 * 
 */
package com.yh.hr.info.ver.unit.workbench.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.info.ver.unit.workbench.dto.VerPersonDTO;
import com.yh.hr.info.ver.unit.workbench.facade.VerPbPersonWorkbenchFacade;
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
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.component.print.util.ExporterUtil;
import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.info.facade.JhcCfShowFieldOrderFacade;
import com.yh.hr.component.info.facade.JhcCfShowResultOrderFacade;
import com.yh.hr.component.info.facade.JhcCfShowResultSetFacade;
import com.yh.hr.res.cf.dto.JhcCfShowResultSetDTO;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.unit.dto.UtOrgDTO;
import com.yh.hr.res.unit.dto.UtUnitDTO;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;
import com.yh.platform.core.web.action.BaseAction;

/**
 * 人员基础信息校核action
 * @author zhangqp
 * @version 1.0, 16/08/16
 */
public class VerPbPersonWorkbenchAction extends BaseAction {
	final static String FUNCTION_CODE = "personWorkBench";

	private VerPbPersonWorkbenchFacade verPbPersonWorkbenchFacade = (VerPbPersonWorkbenchFacade)SpringBeanUtil.getBean("verPbPersonWorkbenchFacade");
	private JhcCfShowResultSetFacade jhcCfShowResultSetFacade = (JhcCfShowResultSetFacade)SpringBeanUtil.getBean("jhcCfShowResultSetFacade");
	private JhcCfShowFieldOrderFacade jhcCfShowFieldOrderFacade = (JhcCfShowFieldOrderFacade)SpringBeanUtil.getBean("jhcCfShowFieldOrderFacade");
	private JhcCfShowResultOrderFacade jhcCfShowResultOrderFacade = (JhcCfShowResultOrderFacade)SpringBeanUtil.getBean("jhcCfShowResultOrderFacade");
	
	/**
	 * 跳转到人员基础信息校核工作台
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goVerPbPersonWorkbench(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String unitOid = request.getParameter("unitOid");
			String orgOid = request.getParameter("orgOid");
			String organizationOid = request.getParameter("organizationOid");
			String orgType = request.getParameter("orgType");
			/*StringMap params = new StringMap();
			//单位状态
			params.put("unitStatus",DicConstants.YHRS0101_1+","+DicConstants.YHRS0101_2+","+DicConstants.YHRS0101_3);
			params.put("unitKind", StringUtil.arrayToSql(DicConstants.JG_UNIT_KINDS));
			
			//具有权限的单位列表
			List<UtUnitDTO> authUnits = verPbPersonWorkbenchFacade.findUserAuthedUnitList(UserContext.getLoginAgentUserID(), params);
			request.setAttribute("authUnits", authUnits);*/
			
			//TODO 查询所有单位信息，如果有多个单位，默认取第一个，如果要查询其他单位信息，自己选择要查询的单位
        	List<UtUnitDTO> utUnitDTOList = verPbPersonWorkbenchFacade.findUnitInfo();
        	if(CollectionUtils.isNotEmpty(utUnitDTOList))
        	{
        		UtUnitDTO utUnitDTO = utUnitDTOList.get(0);
        		request.setAttribute("unitOid", utUnitDTO.getUnitOid());
    			
    			request.setAttribute("unitName", utUnitDTO.getUnitName());
        	}
			request.setAttribute("userUnitOid", UserContext.getLoginUserUnitOid());
			if(unitOid!=null&&!unitOid.equals("")) {
				request.setAttribute("unitOid", unitOid);
			}
			request.setAttribute("orgOid", orgOid);
			request.setAttribute("organizationOid", organizationOid);
			request.setAttribute("orgType", orgType);
			return mapping.findForward("success");
		} catch (Exception e) {
			handleException(request, e, "跳转到人员基础信息校核工作台失败");
			return mapping.findForward("error");
		}
	}
	/**
	 * 跳转到人员基础信息校核工作台
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goToPersonWorkBench(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			request.setAttribute("unitOid", request.getParameter("unitOid"));
			request.setAttribute("orgOid",request.getParameter("orgOid") );
			request.setAttribute("organizationOid",request.getParameter("organizationOid") );
			request.setAttribute("orgType",request.getParameter("orgType") );
			List<JhcCfShowResultSetDTO> columnsList = jhcCfShowResultSetFacade.findColumns(FUNCTION_CODE);
			JSONArray array = new JSONArray();
			for(JhcCfShowResultSetDTO dto: columnsList) {
				JSONObject obj = new JSONObject();
				obj.put("resultOid", dto.getResultOid());
				obj.put("header", dto.getLabelName());
				obj.put("field", dto.getLabelValue());
				obj.put("width", dto.getLabelWidth());
				obj.put("functionCode", dto.getFunctionCode());
				obj.put("isShow", dto.getIsShow());
				array.element(obj);
			}
			List<Map<String,String>> userColumns = jhcCfShowFieldOrderFacade.findFieldOrderColumnsByUser(UserContext.getLoginUserID(),FUNCTION_CODE);
			if(userColumns!=null&&userColumns.size()>0&&userColumns.size()==columnsList.size()) {
				request.setAttribute("columns", JSONArray.fromObject(userColumns));
			}else {
				request.setAttribute("columns", array);
			}
			List<Map<String,String>> sortFields = jhcCfShowResultOrderFacade.findSortsColumnsByUser(UserContext.getLoginUserID(), FUNCTION_CODE);
			request.setAttribute("sortFields", JSONArray.fromObject(sortFields));
			return mapping.findForward("success");
		} catch (Exception e) {
			handleException(request, e, "跳转到人员基础信息校核工作台失败");
			return mapping.findForward("error");
		}
	}
	/**
	 * 查询校核人员基础信息列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listVerPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			TableTagBean ttb = new TableTagBean(request);
			List<VerPersonDTO> list = verPbPersonWorkbenchFacade.listVerPerson(ttb);
			
			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();
			json.put("total", ttb.getTotal());
			if(CollectionUtils.isNotEmpty(list)){
				JSONObject obj = null;
				for (VerPersonDTO dto : list) {
					obj = JSONHelper.fromObject(dto, DateUtil.DATE_PATTERN_DEFAULT);
					obj.put("personStatus", DicHelper.viewName(DicConstants.YHRS0009, dto.getPersonStatus()));
					obj.put("idCode", DicHelper.viewName(DicConstants.YHRS0002, dto.getIdCode()));
					obj.put("personType", DicHelper.viewName(DicConstants.YHRS0010, dto.getPersonType()));
					obj.put("sexCode", DicHelper.viewName(DicConstants.YHRS0001, dto.getSexCode()));
					obj.put("marriageStatusCode", DicHelper.viewName(DicConstants.YHRS0008, dto.getMarriageStatusCode()));
					obj.put("birthplaceCode", DicHelper.viewName(DicConstants.YHRS0006, dto.getBirthplaceCode()));
					obj.put("hukouPlace", DicHelper.viewName(DicConstants.YHRS0006, dto.getHukouPlace()));
					obj.put("isSz", DicHelper.viewName(DicConstants.YHRS0003, dto.getIsSz()));
					obj.put("ancestorPlaceCode", DicHelper.viewName(DicConstants.YHRS0006, dto.getAncestorPlaceCode()));
					obj.put("politicStatusCode", DicHelper.viewName(DicConstants.YHRS0025, dto.getPoliticStatusCode()));
					obj.put("ftEducationLevelCode", DicHelper.viewName(DicConstants.YHRS0039, dto.getFtEducationLevelCode()));
					obj.put("ftDegreeCode", DicHelper.viewName(DicConstants.YHRS0040, dto.getFtDegreeCode()));
					obj.put("ojEducationLevelCode", DicHelper.viewName(DicConstants.YHRS0039, dto.getOjEducationLevelCode()));
					obj.put("ojDegreeCode", DicHelper.viewName(DicConstants.YHRS0040, dto.getOjDegreeCode()));
					obj.put("levelCode", DicHelper.viewName(DicConstants.YHRS0122, dto.getLevelCode()));
					obj.put("hireDeptOid", dto.getHireDeptName());
					obj.put("qualificationsType", DicHelper.viewName(DicConstants.YHRS0127, dto.getQualificationsType()));
					obj.put("mPositionType", DicHelper.viewName(DicConstants.YHRS0022, dto.getmPositionType()));
					obj.put("hisPositionType", DicHelper.viewName(DicConstants.YHRS0113, dto.getHisPositionType()));
					obj.put("peopleCode", DicHelper.viewName(DicConstants.YHRS0004, dto.getPeopleCode()));
					obj.put("profTechLevel", DicHelper.viewName(DicConstants.YHRS0047, dto.getProfTechLevel()));
					obj.put("qualificationsLevelCode", DicHelper.viewName(DicConstants.YHRS0049, dto.getQualificationsLevelCode()));
					array.element(obj);
				}
			}
			json.put("rows", array);
			response.getWriter().print(json.toString());
			
		} catch (Exception e) {
			handleException(request, e, "查询校核人员基础信息失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "查询校核机关人员基础信息失败")));
		}
		
		return null;
	}
	
	//查询部门列表
	public ActionForward findVerDept(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		try {
			String unitOid = request.getParameter("unitOid");
			if (StringUtils.isEmpty(unitOid)) {
                throw new ServiceException(null, "单位oid为空!");
            }
			//查询内设机构列表
			List<UtOrgDTO> list = verPbPersonWorkbenchFacade.findOrgList(NumberUtils.longValue(unitOid));
			if(CollectionUtils.isEmpty(list)) return null;
			JSONArray arr= new JSONArray();
			for(UtOrgDTO utOrgDTO:list){
				JSONObject json=new JSONObject();
				json.put("deptOid", utOrgDTO.getOrgOid());
				json.put("deptName", utOrgDTO.getOrgName());
				json.put("hireDeptOid", utOrgDTO.getOrgOid());
				json.put("hireDeptName", utOrgDTO.getOrgName());
				arr.element(json);
			}
			response.getWriter().print(arr.toString());
			return null;
		} catch (Exception e) {
			handleException(request, e, "查看单位校核列表");
			return null;
		}
	}
	/**
	 * 导出人员列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward printPersonInfoByExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String filename = "医院人员名册表";
			//列
			List<JhcCfShowResultSetDTO> columnsList = jhcCfShowResultSetFacade.findColumns(FUNCTION_CODE);
			JSONArray columnsListArray = new JSONArray();
			for(JhcCfShowResultSetDTO dto: columnsList) {
				JSONObject obj = new JSONObject();
				obj.put("resultOid", dto.getResultOid());
				obj.put("header", dto.getLabelName());
				obj.put("field", dto.getLabelValue());
				obj.put("width", dto.getLabelWidth());
				obj.put("functionCode", dto.getFunctionCode());
				obj.put("isShow", dto.getIsShow());
				columnsListArray.element(obj);
			}
			List<Map<String,String>> userColumns = jhcCfShowFieldOrderFacade.findFieldOrderColumnsByUser(UserContext.getLoginUserID(),FUNCTION_CODE);
			if(userColumns!=null&&userColumns.size()>0&&userColumns.size()==columnsList.size()) {
				columnsListArray = JSONArray.fromObject(userColumns);
			}
			List<JSONObject> _columnsList = new ArrayList<JSONObject>(); 
			for(int i=0; i<columnsListArray.size(); i++){
				JSONObject obj = columnsListArray.getJSONObject(i);
				if(obj.getInt("isShow")==1) {
					_columnsList.add(obj);
					/*columns[i+1][0] = obj.getString("field");
					columns[i+1][1] = obj.getString("header");*/
				}
			}
			String[][] columns = new String[_columnsList.size()+1][2];
			columns[0][0] = "number";
			columns[0][1] = "序号";
			for(int i=0; i<_columnsList.size(); i++){
				JSONObject obj = _columnsList.get(i);
				columns[i+1][0] = obj.getString("field");
				columns[i+1][1] = obj.getString("header");
			}
			String[] oids = request.getParameter("personOids").split(",");
			TableTagBean ttb = new TableTagBean(request);
			ttb.setPageSize(-1);
			//数据
			List<VerPersonDTO> dtoList = verPbPersonWorkbenchFacade.listVerPerson(ttb);
			List<JSONObject> list = new ArrayList<JSONObject>();
			if(CollectionUtils.isNotEmpty(dtoList)){
				JSONObject obj = null;
				for (VerPersonDTO dto : dtoList) {
					obj = JSONHelper.fromObject(dto, DateUtil.DATE_PATTERN_DEFAULT, BeanHelper.getNullPropertyNames(dto));
					obj.put("personStatus", DicHelper.viewName(DicConstants.YHRS0009, dto.getPersonStatus()));
					obj.put("idCode", DicHelper.viewName(DicConstants.YHRS0002, dto.getIdCode()));
					obj.put("personType", DicHelper.viewName(DicConstants.YHRS0010, dto.getPersonType()));
					obj.put("sexCode", DicHelper.viewName(DicConstants.YHRS0001, dto.getSexCode()));
					obj.put("marriageStatusCode", DicHelper.viewName(DicConstants.YHRS0008, dto.getMarriageStatusCode()));
					obj.put("birthplaceCode", DicHelper.viewName(DicConstants.YHRS0006, dto.getBirthplaceCode()));
					obj.put("hukouPlace", DicHelper.viewName(DicConstants.YHRS0006, dto.getHukouPlace()));
					obj.put("isSz", DicHelper.viewName(DicConstants.YHRS0003, dto.getIsSz()));
					obj.put("ancestorPlaceCode", DicHelper.viewName(DicConstants.YHRS0006, dto.getAncestorPlaceCode()));
					obj.put("politicStatusCode", DicHelper.viewName(DicConstants.YHRS0025, dto.getPoliticStatusCode()));
					obj.put("ftEducationLevelCode", DicHelper.viewName(DicConstants.YHRS0039, dto.getFtEducationLevelCode()));
					obj.put("ftDegreeCode", DicHelper.viewName(DicConstants.YHRS0040, dto.getFtDegreeCode()));
					obj.put("ojEducationLevelCode", DicHelper.viewName(DicConstants.YHRS0039, dto.getOjEducationLevelCode()));
					obj.put("ojDegreeCode", DicHelper.viewName(DicConstants.YHRS0040, dto.getOjDegreeCode()));
					obj.put("levelCode", DicHelper.viewName(DicConstants.YHRS0122, dto.getLevelCode()));
					obj.put("hireDeptOid", dto.getHireDeptName());
					obj.put("qualificationsType", DicHelper.viewName(DicConstants.YHRS0127, dto.getQualificationsType()));
					obj.put("mPositionType", DicHelper.viewName(DicConstants.YHRS0022, dto.getmPositionType()));
					obj.put("hisPositionType", DicHelper.viewName(DicConstants.YHRS0113, dto.getHisPositionType()));
					obj.put("peopleCode", DicHelper.viewName(DicConstants.YHRS0004, dto.getPeopleCode()));
					obj.put("profTechLevel", DicHelper.viewName(DicConstants.YHRS0047, dto.getProfTechLevel()));
					obj.put("qualificationsLevelCode", DicHelper.viewName(DicConstants.YHRS0049, dto.getQualificationsLevelCode()));
					
					list.add(obj);
				}
			}
			List<JSONObject> list1 = new ArrayList<JSONObject>();
			if(oids!=null&&oids.length>0) {
				for(JSONObject obj: list) {
					for(String s: oids) {
						if(obj.getString("personOid").equals(s)) {
							list1.add(obj);
						}
					}
				}
			}
			if(list1.size()>0) {
				for(int i=0; i<list1.size(); i++) {
					list1.get(i).put("number", i+1+"");
				}
				ExporterUtil.export("excel", columns, list1, response, filename);
			}else {
				for(int i=0; i<list.size(); i++) {
					list.get(i).put("number", i+1+"");
				}
				ExporterUtil.export("excel", columns, list, response, filename);
			}
			return null;/*
			
			
			String fileName = "医院人员名册表.xlsx";
			TableTagBean ttb = new TableTagBean(request);
			ttb.setPageSize(-1);
			//列
			List<JhcCfShowResultSetDTO> columnsList = jhcCfShowResultSetFacade.findColumns(FUNCTION_CODE);
			JSONArray columnsListArray = new JSONArray();
			for(JhcCfShowResultSetDTO dto: columnsList) {
				JSONObject obj = new JSONObject();
				obj.put("resultOid", dto.getResultOid());
				obj.put("header", dto.getLabelName());
				obj.put("field", dto.getLabelValue());
				obj.put("width", dto.getLabelWidth());
				obj.put("functionCode", dto.getFunctionCode());
				obj.put("isShow", "1");
				columnsListArray.element(obj);
			}
			List<Map<String,String>> userColumns = jhcCfShowFieldOrderFacade.findFieldOrderColumnsByUser(UserContext.getLoginUserID(),FUNCTION_CODE);
			if(userColumns!=null&&userColumns.size()>0&&userColumns.size()==columnsList.size()) {
				columnsListArray = JSONArray.fromObject(userColumns);
			}
			//数据
			List<VerPersonDTO> list = verPbPersonWorkbenchFacade.listVerPerson(ttb);
			JSONArray array = new JSONArray();
			if(CollectionUtils.isNotEmpty(list)){
				JSONObject obj = null;
				for (VerPersonDTO dto : list) {
					obj = JSONHelper.fromObject(dto, DateUtil.DATE_PATTERN_DEFAULT);
					obj.put("personStatus", DicHelper.viewName(DicConstants.YHRS0009, dto.getPersonStatus()));
					obj.put("idCode", DicHelper.viewName(DicConstants.YHRS0002, dto.getIdCode()));
					obj.put("personType", DicHelper.viewName(DicConstants.YHRS0010, dto.getPersonType()));
					obj.put("sexCode", DicHelper.viewName(DicConstants.YHRS0001, dto.getSexCode()));
					obj.put("marriageStatusCode", DicHelper.viewName(DicConstants.YHRS0008, dto.getMarriageStatusCode()));
					obj.put("birthplaceCode", DicHelper.viewName(DicConstants.YHRS0006, dto.getBirthplaceCode()));
					obj.put("hukouPlace", DicHelper.viewName(DicConstants.YHRS0006, dto.getHukouPlace()));
					obj.put("isSz", DicHelper.viewName(DicConstants.YHRS0003, dto.getIsSz()));
					obj.put("ancestorPlaceCode", DicHelper.viewName(DicConstants.YHRS0006, dto.getAncestorPlaceCode()));
					obj.put("politicStatusCode", DicHelper.viewName(DicConstants.YHRS0025, dto.getPoliticStatusCode()));
					obj.put("ftEducationLevelCode", DicHelper.viewName(DicConstants.YHRS0039, dto.getFtEducationLevelCode()));
					obj.put("ftDegreeCode", DicHelper.viewName(DicConstants.YHRS0040, dto.getFtDegreeCode()));
					obj.put("ojEducationLevelCode", DicHelper.viewName(DicConstants.YHRS0039, dto.getOjEducationLevelCode()));
					obj.put("ojDegreeCode", DicHelper.viewName(DicConstants.YHRS0040, dto.getOjDegreeCode()));
					obj.put("levelCode", DicHelper.viewName(DicConstants.YHRS0122, dto.getLevelCode()));
					obj.put("hireDeptOid", dto.getHireDeptName());
					
					array.element(obj);
				}
			}
			
			response.reset();
			response.setCharacterEncoding("utf-8");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String((fileName).getBytes("utf-8"), "ISO8859-1"));
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");// 设置response内容的类型
			HSSFWorkbook wb = printByExcel(array, columnsListArray);
			wb.write(response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();*/
		} catch (Exception e) {
			handleException(request, e, "导出校核人员列表失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "查询校核机关人员基础信息失败")));
		}
		
		return null;
	}
	@SuppressWarnings("unused")
	private HSSFWorkbook printByExcel(JSONArray dataArrays, JSONArray columnsListArray) {
		for(int i=0; i<columnsListArray.size(); i++){
			JSONObject obj = columnsListArray.getJSONObject(i);
			if(obj.getInt("isShow")==0) {
				columnsListArray.remove(i);
				i--;
			}
		}
		HSSFWorkbook wb=new HSSFWorkbook(); 
		HSSFCellStyle titleStyle = buildTitleStyle(wb);
		HSSFSheet sheet=wb.createSheet("人员名册表");  //获取到工作表，因为一个excel可能有多个工作表
		
		CellRangeAddress cra=new CellRangeAddress(0, 0, 0, columnsListArray.size());  //合并单元格
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
		for(int i=0; i<columnsListArray.size(); i++){
			csCell = nameRow.createCell(i+1);
			JSONObject obj = (JSONObject)columnsListArray.get(i);
			csCell.setCellValue(obj.getString("header"));
		}		
		int rowSize = CollectionUtils.isEmpty(dataArrays)? 0 : dataArrays.size();  //获取结果集的行数
		if(CollectionUtils.isNotEmpty(dataArrays)){
			for(int i=0; i<rowSize; i++){
				JSONObject dataObj = dataArrays.getJSONObject(i);
				HSSFRow valueRow = sheet.createRow(i+2);  //获取从第三行开始的行，值行
				for(int j=0; j<columnsListArray.size(); j++){
					csCell = valueRow.createCell(0);        //序列
					csCell.setCellValue(i+1);
					csCell = valueRow.createCell(j+1);
					JSONObject obj = columnsListArray.getJSONObject(j);
					String value = dataObj.has(obj.getString("field"))&&!"null".equals(dataObj.getString(obj.getString("field")))?dataObj.getString(obj.getString("field")):"";
					csCell.setCellValue(value);
				}
			}
		}
		/*if(CollectionUtils.isNotEmpty(dataArrays)){
			for(int i=0; i<rowSize; i++){
				XSSFRow valueRow = sheet.createRow(i+2);  //获取从第三行开始的行，值行
				for(int j=0; j<dataArrays.size(); j++){   //j代表列
					JSONObject obj = dataArrays.getJSONObject(i);
					csCell = valueRow.createCell(0);        //序列
					csCell.setCellValue(i+1);
					
					csCell = valueRow.createCell(j+1);
					String value = (obj!=null? obj.get(j).get(i).toString().trim() : "";
					csCell.setCellValue(value);
					}
				}
		}*/
		return wb;
	}
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
	/*//查询单位列表
	public ActionForward findVerUnit(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		try {
			StringMap params = new StringMap();
			//单位状态
			params.put("unitStatus",DicConstants.YHRS0101_1+","+DicConstants.YHRS0101_2+","+DicConstants.YHRS0101_3);
			params.put("unitKind", StringUtil.arrayToSql(DicConstants.JG_UNIT_KINDS));
			
			//具有权限的单位列表
			List<UtUnitDTO> list = verPbPersonWorkbenchFacade.findUserAuthedUnitList(UserContext.getLoginAgentUserID(), params);
			if(CollectionUtils.isEmpty(list)) return null;
			JSONArray arr= new JSONArray();
			for(UtUnitDTO utUnitDTO:list){
				JSONObject json=new JSONObject();
				json.put("unitOid", utUnitDTO.getUnitOid());
				json.put("unitName", utUnitDTO.getUnitName());
				arr.element(json);
			}
			response.getWriter().print(arr.toString());
			return null;
		} catch (Exception e) {
			handleException(request, e, "查看单位校核列表");
			return null;
		}
	}*/
}