package com.yh.hr.component.wardset.web.action;

import com.yh.hr.component.wardset.dto.CfWardDto;
import com.yh.hr.component.wardset.facade.WardSetFacade;
import jade.workflow.utils.ObjectUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.wardset.web.form.CfWardForm;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.unit.dto.UtUnitDTO;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

public class WardSetAction extends BaseAction {
	
	private WardSetFacade wardSetFacade = (WardSetFacade) SpringBeanUtil.getBean("wardSetFacade");
	
	/**
	 * 获取已经存在的病区科室
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getDeptOptions(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		TableTagBean ttb = new TableTagBean(request);
		List<CfWardDto> list = wardSetFacade.find(ttb);
		JSONArray array = new JSONArray();
		List<Long> deptOids = new ArrayList<Long>();
		for(CfWardDto dto: list) {
			if(dto.getDeptOid()!=null&&!deptOids.contains(dto.getDeptOid())) {
				deptOids.add(dto.getDeptOid());
				JSONObject json = new JSONObject();
				json.put("value", dto.getDeptOid());
				json.put("text", wardSetFacade.findDeptName(dto.getDeptOid()));
				array.element(json);
			}
		}
		response.getWriter().print(array.toString());
		return null;
	}	

	/**
	 * 跳转到病区管理工作台
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goWardSetList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		return mapping.findForward("success");
	}

	/**
	 * 查询病区配置列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listWardSetList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		try {
			TableTagBean ttb = new TableTagBean(request);			
			List<CfWardDto> list = wardSetFacade.find(ttb);
			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();
			json.put("total", ttb.getTotal());
			if(CollectionUtils.isNotEmpty(list)){
				JSONObject obj = null;
				for (CfWardDto dto : list) {
					dto.setWaedType(DicHelper.viewName(DicConstants.YHRS0125,dto.getWaedType()));
					dto.setDeptName(wardSetFacade.findDeptName(dto.getDeptOid()));
					obj = JSONHelper.fromObject(dto, DateUtil.DATE_PATTERN_DEFAULT);
					array.element(obj);
				}
			}
			json.put("rows", array);
			response.getWriter().print(json.toString());
		} catch (Exception e) {
			handleException(request, e, "查询病区配置列表失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "查询病区配置列表失败")));
		}
        return null;
	}
	
	/**
	 * 跳转到新增病区界面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goCreate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CfWardForm cfWardForm = (CfWardForm) form;
		//TODO 查询所有单位信息，如果有多条，默认先取第一条，后续更改为页面选择单位
		List<UtUnitDTO> unitList = wardSetFacade.findUnitInfo();
		if(CollectionUtils.isNotEmpty(unitList))
		{
			cfWardForm.setUnitOid(unitList.get(0).getUnitOid());
		}
		request.setAttribute("cfWardForm", cfWardForm);
		return mapping.findForward(FORWARD_SUCCESS);
	}
	
	/**
	 * 新增病区记录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (this.isCancelled(request)) {
			return mapping.findForward("cancel");
		}
		CfWardForm cfWardForm = (CfWardForm) form;
		try {
			if(cfWardForm.getDeptOid()==null) {
				throw new Exception("请选择病区科室");
			}
			TableTagBean ttb = TableTagBean.getFromRequest(request);
			ttb.getCondition().put("deptOid", cfWardForm.getDeptOid().toString());
			ttb.getCondition().put("waedType", cfWardForm.getWaedType());
			List<CfWardDto> list = wardSetFacade.find(ttb);
			if(list!=null && list.size()>0) {
				throw new Exception("新增失败，该病区类型已存在该科室部门的病区");
			}
			CfWardDto cfWardDto = BeanHelper.copyProperties(cfWardForm, CfWardDto.class);
			wardSetFacade.create(cfWardDto);
			response.getWriter().write(JSONHelper.fromObject(true, null).toString());
		} catch (Exception se) {
			this.handleException(request, se, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(se.getMessage(), "新增失败")).toString());
			return null;
		}
		return null;
	}
	
	/**
	 * 跳转到修改病区记录界面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(this.isCancelled(request)) {
			return mapping.findForward("cancel");
		}
        String waedOid = request.getParameter("waedOid");
		try {
            if (StringUtils.isBlank(waedOid)) {
				throw new ServiceException("error.pk.invalid", "waedOid is null");
			}
            CfWardDto cfWardDto = wardSetFacade.get(ObjectUtil.getValue(waedOid, java.lang.Long.class));
			if (null == cfWardDto) {
				throw new ServiceException("error.object.notfound", "cfWard not found by personOid");
			}
			request.setAttribute("deptName", wardSetFacade.findDeptName(cfWardDto.getDeptOid()));
			CfWardForm cfWardForm = BeanHelper.copyProperties(cfWardDto, CfWardForm.class);
			//TODO 查询所有单位信息，如果有多条，默认先取第一条，后续更改为页面选择单位
			List<UtUnitDTO> unitList = wardSetFacade.findUnitInfo();
			if(CollectionUtils.isNotEmpty(unitList))
			{
				cfWardForm.setUnitOid(unitList.get(0).getUnitOid());
			}
			request.setAttribute("cfWardForm", cfWardForm);
            request.setAttribute("waedOid", waedOid);
		} catch (Exception se) {
			this.handleException(request, se, "waedOid=" + waedOid);
			return mapping.getInputForward();
		}
        return mapping.findForward("success");
	}
	
	/**
	 * 修改病区记录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(this.isCancelled(request)) {
			return mapping.findForward("cancel");
		}
        String waedOid = request.getParameter("waedOid");
        CfWardForm cfWardForm = (CfWardForm) form;
		try {
			if(cfWardForm.getDeptOid()==null) {
				throw new Exception("请选择病区科室");
			}
            if (StringUtils.isBlank(waedOid)) {
				throw new ServiceException("error.pk.invalid", "waedOid is null");
			}
            TableTagBean ttb = TableTagBean.getFromRequest(request);
			ttb.getCondition().put("deptOid", cfWardForm.getDeptOid().toString());
			ttb.getCondition().put("waedType", cfWardForm.getWaedType());
			List<CfWardDto> list = wardSetFacade.find(ttb);
            if(list!=null&&list.size()>0&&!list.get(0).getWaedOid().toString().equals(waedOid)) {
            	throw new ServiceException("更新失败，该病区类型已存在该科室部门的病区");
            }
            CfWardDto cfWardDto = new CfWardDto();
			BeanHelper.copyProperties(cfWardForm, cfWardDto);
			wardSetFacade.update(cfWardDto);
			response.getWriter().write(JSONHelper.fromObject(true, null).toString());
		} catch (Exception se) {
			this.handleException(request, se, cfWardForm);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(se.getMessage(), "修改失败")).toString());
		}
		return null;
	}
	
	/**
	 * 删除病区记录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] waedOids = request.getParameter("waedOids").split(",");
		try {
        	for(String oid: waedOids) {
        		wardSetFacade.delete(ObjectUtil.getValue(oid, java.lang.Long.class));
        	}
        	response.getWriter().write(JSONHelper.fromObject(true, null).toString());
        } catch (Exception se) {
        	this.handleException(request, se, "waedOids=" + Arrays.asList(waedOids).toString());
        	return mapping.getInputForward();
        }
        return null;
	}
	
	/**
	 * 查看病区信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(this.isCancelled(request)) {
			return mapping.findForward("cancel");
		}
        String waedOid = request.getParameter("waedOid");
		try {
            if (StringUtils.isBlank(waedOid)) {
				throw new ServiceException("error.pk.invalid", "waedOid is null");
			}
            CfWardDto cfWardDto = wardSetFacade.get(ObjectUtil.getValue(waedOid, java.lang.Long.class));
			if (null == cfWardDto) {
				throw new ServiceException("error.object.notfound", "cfWard not found by personOid");
			}
			request.setAttribute("deptName", wardSetFacade.findDeptName(cfWardDto.getDeptOid()));
			CfWardForm cfWardForm = BeanHelper.copyProperties(cfWardDto, CfWardForm.class);
			request.setAttribute("cfWardForm", cfWardForm);
            request.setAttribute("waedOid", waedOid);
		} catch (Exception se) {
			this.handleException(request, se, "waedOid=" + waedOid);
			return mapping.getInputForward();
		}
        return mapping.findForward("success");
	}
		
	/**
	 * 导出《医院各病区卫技人员配备情况一览表.xlsx》
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	public ActionForward exportWardCollection(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		response.addHeader("Content-Disposition","attachment;filename=" +URLEncoder.encode("医院各病区卫技人员配备情况一览表（截止"+DateUtil.nowString()+"）.xls", "utf-8")); 
		OutputStream out = response.getOutputStream();
		try {
			List<Map<String,String>> list = wardSetFacade.findWardCollection();
			transformData(list);
			HSSFWorkbook wb = new HSSFWorkbook();
			//标题字体
			HSSFFont titleFont = wb.createFont();
			titleFont.setFontHeightInPoints((short) 18);
			titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			//标题样式
			HSSFCellStyle titleStyle = wb.createCellStyle();
			titleStyle.setFont(titleFont);
			titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			//内容字体
			HSSFFont contentFont = wb.createFont();
			contentFont.setFontHeightInPoints((short) 11);
			contentFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			//内容样式
			HSSFCellStyle contentStyle = wb.createCellStyle();
			contentStyle.setFont(contentFont);
			contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			contentStyle.setWrapText(true);
			contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			HSSFSheet sheet = wb.createSheet(DateUtil.nowString());
			sheet.addMergedRegion(new CellRangeAddress(0,(short)0,0,(short)(list.size()+1)));
			sheet.addMergedRegion(new CellRangeAddress(1,(short)1,0,(short)1));
			sheet.addMergedRegion(new CellRangeAddress(2,(short)2,0,(short)1));
			sheet.addMergedRegion(new CellRangeAddress(3,(short)3,0,(short)1));
			sheet.addMergedRegion(new CellRangeAddress(4,(short)4,0,(short)1));
			sheet.addMergedRegion(new CellRangeAddress(5,(short)5,0,(short)1));
			sheet.addMergedRegion(new CellRangeAddress(6,(short)6,0,(short)1));
			sheet.addMergedRegion(new CellRangeAddress(7,(short)7,0,(short)1));
			sheet.addMergedRegion(new CellRangeAddress(8,(short)8,0,(short)1));
			sheet.addMergedRegion(new CellRangeAddress(9,(short)9,0,(short)1));
			sheet.addMergedRegion(new CellRangeAddress(10,(short)10,0,(short)1));
			sheet.addMergedRegion(new CellRangeAddress(11,(short)12,0,(short)0));
			sheet.addMergedRegion(new CellRangeAddress(13,(short)14,0,(short)0));
			sheet.addMergedRegion(new CellRangeAddress(15,(short)16,0,(short)0));
			sheet.setDefaultColumnWidth(8);
			//写入表格内容
			for(int r=0; r<17; r++) {
				HSSFRow row = sheet.createRow(r);
				HSSFCell c1 = row.createCell(0);
				HSSFCell c2 = row.createCell(1);
				c2.setCellStyle(contentStyle);
				switch(r) {
					case 0:
						c1.setCellStyle(titleStyle);
						c1.setCellValue("医院各病区卫技人员配备情况一览表（截止"+DateUtil.nowString()+"）");
						break;
					case 1:
						c1.setCellStyle(contentStyle);
						c1.setCellValue("病区");
						renderRow(row,contentStyle,list,"deptName");
						break;
					case 2:
						c1.setCellStyle(contentStyle);
						c1.setCellValue("床位数");
						renderRow(row,contentStyle,list,"bedNum");
						break;
					case 3:
						c1.setCellStyle(contentStyle);
						c1.setCellValue("医生");
						renderRow(row,contentStyle,list,"yisheng");
						break;
					case 4:
						c1.setCellStyle(contentStyle);
						c1.setCellValue("护士");
						renderRow(row,contentStyle,list,"hushi");
						break;
					case 5:
						c1.setCellStyle(contentStyle);
						c1.setCellValue("药师");
						renderRow(row,contentStyle,list,"yaoshi");
						break;
					case 6:
						c1.setCellStyle(contentStyle);
						c1.setCellValue("技师");
						renderRow(row,contentStyle,list,"jishi");
						break;
					case 7:
						c1.setCellStyle(contentStyle);
						c1.setCellValue("合计");
						renderRow(row,contentStyle,list,"total");
						break;
					case 8:
						c1.setCellStyle(contentStyle);
						c1.setCellValue("护：床");
						renderRow(row,contentStyle,list,"huPerBed");
						break;
					case 9:
						c1.setCellStyle(contentStyle);
						c1.setCellValue("医：床");
						renderRow(row,contentStyle,list,"yiPerBed");
						break;
					case 10:
						c1.setCellStyle(contentStyle);
						c1.setCellValue("卫技：床");
						renderRow(row,contentStyle,list,"totalPerBed");
						break;
					case 11:
						c1.setCellStyle(contentStyle);
						c1.setCellValue("卫技床比");
						c2.setCellValue("达标标准");
						break;
					case 12:
						c2.setCellValue("达标情况");
						break;
					case 13:
						c1.setCellStyle(contentStyle);
						c1.setCellValue("医床比");
						c2.setCellValue("达标标准");
						break;
					case 14:
						c2.setCellValue("达标情况");
						break;
					case 15:
						c1.setCellStyle(contentStyle);
						c1.setCellValue("护床比");
						c2.setCellValue("达标标准");
						break;
					case 16:
						c2.setCellValue("达标情况");
						break;
				}
			}
			wb.write(out);
			out.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/
	
	/**
	 * 写入每行的数据
	 * @param row
	 * @param style
	 * @param list
	 * @param key
	 *//*
	private void renderRow(HSSFRow row, HSSFCellStyle style, List<Map<String, String>> list, String key) {
		for(int i=0; i<list.size(); i++) {
			HSSFCell cx = row.createCell(i+2);
			cx.setCellStyle(style);
			cx.setCellValue(list.get(i).get(key));
		}
	}*/
	
	/**
	 * 转换、计算需要的数据
	 * @param list
	 */
	public void transformData(List<Map<String,String>> list) {
		Map<String,String> all = new HashMap<String,String>();
		int bedTotal=0,
			yishengTotal=0,
			hushiTotal=0,
			yaoshiTotal=0,
			jishiTotal=0,
			totalTotal=0;
		//数据计算
		for(Map<String,String> map: list) {
			map.put("bedNum", number(map.get("bedNum"))+"");
			map.put("total", sum(new String[]{map.get("yisheng"),map.get("hushi"),map.get("yaoshi"),map.get("jishi")})+"");
			map.put("huPerBed", getPer(map.get("hushi"),map.get("bedNum")));//护：床
			map.put("yiPerBed", getPer(map.get("yisheng"),map.get("bedNum")));//医：床
			map.put("totalPerBed", getPer(map.get("total"),map.get("bedNum")));//卫技：床
			bedTotal+=number(map.get("bedNum"));
			yishengTotal+=number(map.get("yisheng"));
			hushiTotal+=number(map.get("hushi"));
			yaoshiTotal+=number(map.get("yaoshi"));
			jishiTotal+=number(map.get("jishi"));
			totalTotal+=number(map.get("total"));
		}
		all.put("deptName", "总数");
		all.put("bedNum", bedTotal+"");
		all.put("yisheng", yishengTotal+"");
		all.put("hushi", hushiTotal+"");
		all.put("yaoshi", yaoshiTotal+"");
		all.put("jishi", jishiTotal+"");
		all.put("total", totalTotal+"");
		all.put("huPerBed", getPer(all.get("hushi"),all.get("bedNum")));
		all.put("yiPerBed", getPer(all.get("yisheng"),all.get("bedNum")));
		all.put("totalPerBed", getPer(all.get("total"),all.get("bedNum")));
		list.add(all);
	}	
	
	/**
	 * 求和
	 * @param values
	 * @return
	 */
	private int sum(String[] values) {
		int result = 0;
		for(String s: values) {
			result+=number(s);
		}
		return result;
	}
	
	/**
	 * 转换为int类型
	 * @param value
	 * @return
	 */
	private int number(String value) {
		return isEmpty(value)?0:Integer.parseInt(value);
	}
	
	/**
	 * 获取两数的比例字符串
	 * @param num1
	 * @param num2
	 * @return
	 */
	private String getPer(String num1, String num2) {
		if(isEmpty(num1) || isEmpty(num2)) {
			String x = isEmpty(num1)?"0":"1";
			String y = isEmpty(num2)?"0":"1";
			return x+":"+y;
		}else {
			int x = isEmpty(num1)?0:Integer.parseInt(num1);
			int y = isEmpty(num2)?0:Integer.parseInt(num2);
			DecimalFormat df = new DecimalFormat("#.##");
	        /*df.setMaximumFractionDigits(2);
	        df.setGroupingSize(0);
	        df.setRoundingMode(RoundingMode.FLOOR);*/
	        float f = (float)x/(float)y;
			String per = df.format(f);
			return per+":"+1;
		}
	}
	
	/**
	 * 判断字符串是否为空、null、“0”
	 * @param value
	 * @return
	 */
	public boolean isEmpty(String value) {
		if(value==null||value.equals("")||value.equals("0")) {
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 跳转到或导出《医院各病区卫技人员配备情况一览表》
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goviewTechnicalPersonEquipInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Map<String,String>> list = wardSetFacade.findWardCollection();
		transformData(list);
		/*JSONArray array = new JSONArray();
		for(Map<String,String> map: list) {
			JSONObject obj = new JSONObject();
			obj.putAll(map);
			array.element(obj);
		}*/
		request.setAttribute("list", list);
		request.setAttribute("titleColSpan", list.size()+2);
		request.setAttribute("title", "医院各病区卫技人员配备情况一览表");
		//导出标志
		if(!StringUtils.isBlank(request.getParameter("flag"))&&request.getParameter("flag").equals("export")) {
			return mapping.findForward("export");
		}else{
			return mapping.findForward("success");
		}
	}
}
