/**
 * 
 */
package com.yh.hr.info.biz.bizuploadphoto.web.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.component.taglib.TableTagBean;
import com.yh.component.upload.dto.UploadStatusDTO;
import com.yh.component.upload.facade.UploadStatusFacade;
import com.yh.hr.info.biz.bizuploadphoto.dto.BizUploadPhotoDTO;
import com.yh.hr.info.biz.bizuploadphoto.facade.BizUploadPhotoFacade;
import com.yh.platform.core.util.ConfigUtil;
import com.yh.platform.core.util.IOUtil;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

/**
 * 文件上传
 * 
 * @author chenjl
 * @version 1.0, 17/03/25
 */
@SuppressWarnings("unchecked")
public class BizUploadPhotoAction extends BaseAction {

	private UploadStatusFacade uploadStatusFacade = (UploadStatusFacade)SpringBeanUtil.getBean("uploadStatusFacade");
	private BizUploadPhotoFacade bizUploadPhotoFacade = (BizUploadPhotoFacade)SpringBeanUtil.getBean("bizUploadPhotoFacade");
	
	private FileItem getUploadFileItem(List<FileItem> list) {
		for (FileItem fileItem : list) {
			if (!fileItem.isFormField()) {
				return fileItem;
			}
		}
		return null;
	}

	private String getUploadFileName(FileItem item) {
		String value = item.getName();
		int start = value.lastIndexOf("/");
		String filename = value.substring(start + 1);

		return filename;
	}
	
	/**
	 * 跳转到上传页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goUploadPhoto(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			String bizPersonOid = request.getParameter("bizPersonOid");
			request.setAttribute("hasAuthority9", true);
			request.setAttribute("refType", request.getParameter("refType"));
			request.setAttribute("bizPersonOid", bizPersonOid);
			
			return mapping.findForward("success");
		} catch (Exception e) {
			handleException(request, e, "跳转文件上传页面失败");
			return null;
		}
	}
	
	
	/**
	 * 上传文件
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward uploadPhoto(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FileOutputStream os = null;
		InputStream in = null;
		try {
			//上传文件的参数放到url后面，可以直接getParameter
			String fileMD5 = request.getParameter("fileMD5");
			Long fileLength = Long.valueOf(request.getParameter("fileLength"));
			String uuid = request.getParameter("uuid");
			
			Long bizPersonOid = NumberUtils.longValue(request.getParameter("bizPersonOid"));
			String refType = request.getParameter("refType");
			//影像文件在服务器上的名称
			String fileName = bizPersonOid+""+System.currentTimeMillis();
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(1024 * 1024);
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			List<FileItem> list = upload.parseRequest(request);
			
			FileItem item = getUploadFileItem(list);
			String filename = getUploadFileName(item);
			int end = filename.lastIndexOf(".");
			String localName=filename.substring(0, end);

			in = item.getInputStream();
			byte[] tempbytes = new byte[in.available()];

			File file = null;
			UploadStatusDTO uploadStatus = uploadStatusFacade.get(uuid);
			
			if (uploadStatus != null) {
				file = new File(uploadStatus.getFilePathRemote());
				//重复上传？
				if(uploadStatus.getPostComplete() == 1){
					file.delete();
				}
			} else {
	            
	            StringBuilder dir = new StringBuilder();
	    		
	    		dir.append(ConfigUtil.getProperty("file.path.affiche")+"/"+bizPersonOid);
	    		
				File file1 = new File(dir.toString());
				//判断文件夹是否存在，如果不存在则创建
				if(!file1.exists())
				{
					file1.mkdirs(); //创建文件夹
				}
	            
				file = new File(file1, fileName+filename.substring(end,filename.length()));
			}
			
			os = new FileOutputStream(file, true);
			while (in.read(tempbytes) != -1) {
				os.write(tempbytes);
			}

			long progress = fileLength == 0 ? 100 : Math.round(file.length() / (double)fileLength * 100);
			
			uploadStatus = new UploadStatusDTO();
			
			uploadStatus.setUuid(uuid);
			uploadStatus.setFileNameLocal(localName);
			uploadStatus.setFileNameRemote(fileName);
			uploadStatus.setFilePathRemote(IOUtil.filePathReverse(file.getAbsolutePath()));
			uploadStatus.setFileMd5(fileMD5);
			uploadStatus.setFileLength(fileLength);
			uploadStatus.setFilePos(file.length());
			uploadStatus.setPostedPercent(String.valueOf(progress));
			uploadStatus.setPostComplete(progress == 100 ? 1 : 0);
			
			uploadStatusFacade.saveOrUpdate(uploadStatus);
			
			//上传完成后，写入文件列表
			if (progress == 100) {
				
				bizUploadPhotoFacade.create(bizPersonOid,refType, uploadStatus);
			}

			response.getWriter().print(JSONHelper.fromObject(uploadStatus));
		} catch (Exception e) {
			this.handleException(request, e, "上传失败");
			response.getWriter().print(JSONHelper.fromObject(false, "上传失败"));
		} finally {
			IOUtil.closeStream(in,os);
		}

		return null;
	}
	
	/**
	 * 查询上传列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listUploadPhoto(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			TableTagBean ttb = new TableTagBean(request);
			
			String refType = request.getParameter("refType");//查看的来源，如果为0，表示查看所有来源
			String bizPersonOid = request.getParameter("bizPersonOid");
			
			ttb.getCondition().put("bizPersonOid", bizPersonOid);
			ttb.getCondition().put("refType", refType);
			
			List<JSONObject> list = bizUploadPhotoFacade.listUploadPhoto(ttb);
			
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
	 * 删除文件
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteUploadPhoto(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			Long photoOid = NumberUtils.longValue(request.getParameter("photoOid"));
			
			bizUploadPhotoFacade.delete(photoOid);
			
			response.getWriter().print(JSONHelper.fromObject(true, "删除成功！"));
		} catch (Exception e) {
			handleException(request, e, "跳转文件上传页面失败");
			response.getWriter().print(JSONHelper.fromObject(false, "删除失败！"));
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
	public ActionForward downloadUploadPhoto(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long photoOid = NumberUtils.longValue(request.getParameter("photoOid"));
			
			BizUploadPhotoDTO bizUploadPhotoDTO = bizUploadPhotoFacade.get(photoOid);
			
			File file = null;
			if (bizUploadPhotoDTO != null && (file = new File(bizUploadPhotoDTO.getFilePathRemote())).exists()) {
				IOUtil.writeResponse(response, file, "application/octet-stream", bizUploadPhotoDTO.getPhotoName()+"."+bizUploadPhotoDTO.getPhotoType());
				return null;
			}
			
			response.getWriter().print("<center style='margin-top:50px;'>您打开的文件不存在!</center>");
			
		} catch (Exception e) {
			handleException(request, e, "文件下载失败");
			
			response.getWriter().print("<center style='margin-top:50px;'文件下载失败!</center>");
		}
		
		return null;
	}
}
