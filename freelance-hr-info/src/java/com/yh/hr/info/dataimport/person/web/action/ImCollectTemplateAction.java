package com.yh.hr.info.dataimport.person.web.action;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.info.dataimport.person.facade.ImCollectTemplateFacade;
import net.sf.json.JSONArray;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.hr.res.im.dto.ImCollectTemplateDTO;
import com.yh.platform.core.util.ConfigUtil;
import com.yh.platform.core.util.DataConverUtils;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.util.XlsUtils;
import com.yh.platform.core.web.action.BaseAction;

public class ImCollectTemplateAction extends BaseAction {
	
	private ImCollectTemplateFacade imCollectTemplateFacade = (ImCollectTemplateFacade) SpringBeanUtil.getBean("imCollectTemplateFacade");
	
	/**
	 * 文件存放的目录
	 */
	public static final String SELECT_NAME = "--请选择--";
	/**
	 * 跳转到采集项匹配窗口
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */      
	public ActionForward goToImCollectTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			String fileLocation = request.getParameter("formFile");
			String formFile = fileLocation.substring(12);
			int end = formFile.lastIndexOf(".");
			request.setAttribute("formFile",formFile);
			//获取到上一次保存的文件
			String defaultPhotoFile = ConfigUtil.getProperty("standard.import.dir") + "nowImportFile"+ "/"+"nowImportFile"+formFile.substring(end, formFile.length());
			FileInputStream  mInputStream =new FileInputStream(defaultPhotoFile);
			List<ImCollectTemplateDTO> listTempDto = new ArrayList<ImCollectTemplateDTO>(); 
			List<ImCollectTemplateDTO> importDtoList = new ArrayList<ImCollectTemplateDTO>(); 
			if(mInputStream!=null){
				Workbook workbook = new HSSFWorkbook(mInputStream);
				Sheet sheet = workbook.getSheetAt(0);
				//获取导入文件的列头
				List<String> importName =  XlsUtils.getImportTableTitle(sheet); 
				//新增一个请选择下拉值
				/*ImCollectTemplateDTO defaultDto = new ImCollectTemplateDTO();
				defaultDto.setImportCollName(SELECT_NAME);
				importDtoList.add(defaultDto);*/
				for(int j = 0;j<importName.size() ;j++){
					ImCollectTemplateDTO importDTO = new ImCollectTemplateDTO();
					importDTO.setImportCollName(importName.get(j));
					importDtoList.add(importDTO);
				}
				// 获取所有采集项映射模板
				List<ImCollectTemplateDTO> list = imCollectTemplateFacade.findEffectiveCollTemplates();
				if(CollectionUtils.isNotEmpty(list)){
					for(ImCollectTemplateDTO dto : list){
						//如果数据库原有的导入项不为空
						if(dto.getImportCollName() != null && dto.getImportCollName().length() > 0){
							//是否包含，包含的话就提出
							if(importName.contains(dto.getImportCollName())){
								listTempDto.add(dto);
							}
							//不包含
							else{
								//与数据库模板采集项名称比较，若包含就提出
								if(importName.contains(dto.getTemplateCollName())){
									dto.setImportCollName(dto.getTemplateCollName());
									listTempDto.add(dto);
								}else{
									dto.setImportCollName(SELECT_NAME);
									listTempDto.add(dto);
								}
							}
						}
						else{
							if(importName.contains(dto.getTemplateCollName())){
								dto.setImportCollName(dto.getTemplateCollName());
								listTempDto.add(dto);
							}
							else{
								dto.setImportCollName(SELECT_NAME);
								listTempDto.add(dto);
							}
						}
					}
				}
				mInputStream.close();
				request.setAttribute("list", listTempDto);
				request.setAttribute("importNameList", importDtoList);
			}
		}catch(Exception e){
			this.handleException(request,e, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "人员导入失败")).toString());
			return null;
		}
		return mapping.findForward("success");
	}
	
	
	/**
	 * 修改采集项模板
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateImCollectTemplate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			// 先获取所有已被映射的采集项模板，设置他们的ImportCollName为空，再去更新实时的
			List<ImCollectTemplateDTO> list = imCollectTemplateFacade.findAllCollTemplates();
			if(CollectionUtils.isNotEmpty(list)){
				for(ImCollectTemplateDTO dto : list){
					dto.setImportCollName("");
					imCollectTemplateFacade.update(dto);
				}
			}
			//采集项修改
			JSONArray json=JSONArray.fromObject(request.getParameter("mapDatas"));  
			for(int i=0;i<json.size();i++){  
				//String templateCollName=json.getJSONObject(i).get("templateCollName").toString();//获得数据库模板值
				String nowImportCollName=json.getJSONObject(i).get("nowImportCollName").toString();//获得用户手动匹配的值
				String templateOid=json.getJSONObject(i).get("templateOid").toString();//获得用户手动匹配的值
//				String dataColumnCode=json.getJSONObject(i).get("dataColumnCode").toString();//数据库字段代码
				//获取数据库中原有的值
//				ImCollectTemplateDTO dto = imCollectTemplateFacade.findCollTemplateByColumnCode(dataColumnCode);
				ImCollectTemplateDTO dto = new ImCollectTemplateDTO();
				dto.setTemplateOid(DataConverUtils.toLong(templateOid));
				if( SELECT_NAME.equals(nowImportCollName)){
					nowImportCollName = "";
				}else{
					nowImportCollName = nowImportCollName.trim();
				}
				dto.setImportCollName(nowImportCollName);
				imCollectTemplateFacade.update(dto);
			 } 
		}catch(Exception e) {
			this.handleException(request,e, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "修改采集项失败")).toString());
			return null;
		}
		response.getWriter().write(JSONHelper.fromObject(true, null).toString());
		return null;
	}
}
