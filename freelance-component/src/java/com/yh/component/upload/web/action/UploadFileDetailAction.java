/**
 * 
 */
package com.yh.component.upload.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.component.taglib.TableTagBean;
import com.yh.component.upload.dto.UploadFileDetailDTO;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.component.upload.facade.UploadFileDetailFacade;
import com.yh.platform.core.util.IOUtil;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.util.StringUtil;
import com.yh.platform.core.web.action.BaseAction;

import net.sf.json.JSONObject;

/**
 * 文件上传
 * 
 * @author zhangqp
 * @version 1.0, 16/11/08
 */
public class UploadFileDetailAction extends BaseAction {

	private UploadFileDetailFacade uploadFileDetailFacade = (UploadFileDetailFacade)SpringBeanUtil.getBean("uploadFileDetailFacade");
	
	/**
	 * 查询文件列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listUploadFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			TableTagBean ttb = new TableTagBean(request);
			
//			String refRoleCode = request.getParameter("refRoleCode");//当前角色
			String viewRefCodes = StringUtil.joinWrap(StringUtils.split(request.getParameter("viewRefCodes"),","));//查看的来源，如果为空，表示查看所有来源
			
			ttb.getCondition().put("viewRefCodes", viewRefCodes);
			
			List<JSONObject> list = uploadFileDetailFacade.listUploadFile(ttb);
			
			JSONObject json = new JSONObject();
			json.put("total", ttb.getTotal());
			json.put("rows", null!=list?list:new ArrayList<Object>());
			response.getWriter().print(json.toString());
		} catch (Exception e) {
			handleException(request, e, "查询文件列表失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "查询文件列表失败")));
		}
		
		return null;
	}
	
	/**
	 * 下载上传文件
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward downloadUploadFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long fileOid = NumberUtils.longValue(request.getParameter("fileOid"));
			
			UploadFileDetailDTO uploadFileDetailDTO = uploadFileDetailFacade.get(fileOid);
			
			File file = null;
			if (uploadFileDetailDTO != null && (file = new File(uploadFileDetailDTO.getPath())).exists()) {
				IOUtil.writeResponse(response, file, "application/octet-stream", uploadFileDetailDTO.getFileName());
				return null;
			}
			
			response.getWriter().print("<center style='margin-top:50px;'>您打开的文件不存在!</center>");
			
		} catch (Exception e) {
			handleException(request, e, "文件下载失败");
			
			response.getWriter().print("<center style='margin-top:50px;'文件下载失败!</center>");
		}
		
		return null;
	}
	
	public ActionForward deleteUploadFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			String[] fileOids=  StringUtil.toString(request.getParameter("fileOid")).split(",");
			if(null!=fileOids){
				for (String oid : fileOids) {
					Long fileOid = NumberUtils.longValue(oid);
					uploadFileDetailFacade.delete(fileOid);
				}
			}
			
			response.getWriter().print(JSONHelper.fromObject(true, "删除成功！"));
		} catch (Exception e) {
			handleException(request, e, "跳转文件上传页面失败");
			response.getWriter().print(JSONHelper.fromObject(false, "删除失败！"));
		}
		return null;
	}
}
