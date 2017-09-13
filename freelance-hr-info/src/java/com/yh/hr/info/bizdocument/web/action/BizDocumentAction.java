package com.yh.hr.info.bizdocument.web.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.info.bizdocument.facade.BizDocumentFacade;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.component.taglib.TableTagBean;
import com.yh.component.upload.dto.UploadNodeRefDTO;
import com.yh.component.upload.dto.UploadStatusDTO;
import com.yh.platform.core.util.IOUtil;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

public class BizDocumentAction extends BaseAction{
	
	private BizDocumentFacade bizDocumentFacade = (BizDocumentFacade)SpringBeanUtil.getBean("bizDocumentFacade");
	
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
	
	public ActionForward goBizDocumentManage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String uploadNodeCode="bizdocument";
		List<UploadNodeRefDTO> list=bizDocumentFacade.listBizBusiness(uploadNodeCode);
		request.setAttribute("list", list);
		return mapping.findForward("success");
	}
	
	
	public ActionForward listBizDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			TableTagBean ttb = new TableTagBean(request);
			List<JSONObject> list = bizDocumentFacade.listBizDocument(ttb);

			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();
			json.put("total", ttb.getTotal());
			if(CollectionUtils.isNotEmpty(list)){
				for (JSONObject obj : list) {
					array.element(obj);
				}
			}
			json.put("rows", array);
			response.getWriter().print(json.toString());

		} catch (Exception e) {
			handleException(request, e, "查询业务文档列表失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "查询业务文档列表失败")));
		}
		return null;
}
	
	public ActionForward goCreateBizDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String uploadNodeCode="bizdocument";
			List<UploadNodeRefDTO> list=bizDocumentFacade.listBizBusiness(uploadNodeCode);
			request.setAttribute("list", list);
		} catch (Exception e) {
			handleException(request, e, "跳转文件上传页面失败");
			return null;
		}
		return mapping.findForward("success");
	}
	public ActionForward createBizDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FileOutputStream os = null;
		InputStream in = null;
		try {
			//上传文件的参数放到url后面，可以直接getParameter
			String fileMD5 = request.getParameter("fileMD5");
			Long fileLength = Long.valueOf(request.getParameter("fileLength"));
			String uuid = request.getParameter("uuid");
			
			String refCode = request.getParameter("refCode");
//			Long refOid = NumberUtils.longValue(request.getParameter("refOid"));
//			if(true)throw new ServiceException("s");
			DiskFileItemFactory factory = new DiskFileItemFactory();
//			factory.setRepository(new File(TEMP_FOLDER));
			factory.setSizeThreshold(1024 * 1024);
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			@SuppressWarnings("unchecked")
			List<FileItem> list = upload.parseRequest(request);
			
			FileItem item = getUploadFileItem(list);
			String filename = getUploadFileName(item);

			in = item.getInputStream();
			byte[] tempbytes = new byte[in.available()];
//			int byteread = 0;

			File file = null;
			UploadStatusDTO uploadStatus = bizDocumentFacade.get(uuid);
			
			if (uploadStatus != null) {
				file = new File(uploadStatus.getFilePathRemote());
				//重复上传？
				if(uploadStatus.getPostComplete() == 1){
					file.delete();
				}
			} else {
				String uuidname = filename;
	            int start = uuidname.lastIndexOf(".");
	            uuidname = uuid + uuidname.substring(start);
	            
				File dir = bizDocumentFacade.getRefUploadDir(refCode);
	            
				file = new File(dir, uuidname);
			}
			
			os = new FileOutputStream(file, true);
			while (in.read(tempbytes) != -1) {
				os.write(tempbytes);
			}

			long progress = fileLength == 0 ? 100 : Math.round(file.length() / (double)fileLength * 100);
//			System.out.println("后台文件大小：" + file.length() + "文件总大小" + filetotalsize + "百分比：" + progress + "%");
			
			uploadStatus = new UploadStatusDTO();
			
			uploadStatus.setUuid(uuid);
			uploadStatus.setFileNameLocal(filename);
			uploadStatus.setFilePathRemote(IOUtil.filePathReverse(file.getAbsolutePath()));//
			uploadStatus.setFileMd5(fileMD5);
			uploadStatus.setFileLength(fileLength);
			uploadStatus.setFilePos(file.length());
			uploadStatus.setPostedPercent(String.valueOf(progress));
			uploadStatus.setPostComplete(progress == 100 ? 1 : 0);
			
			bizDocumentFacade.saveOrUpdate(uploadStatus);
			
			//上传完成后，写入文件列表
			if (progress == 100) {
				
				bizDocumentFacade.create(refCode, uploadStatus);
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
}
