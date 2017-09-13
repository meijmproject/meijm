/**
 * 
 */
package com.yh.component.upload.web.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.component.upload.dto.UploadNodeRefDTO;
import com.yh.component.upload.dto.UploadRefAuthDTO;
import com.yh.component.upload.facade.UploadFileFacade;
import com.yh.component.upload.facade.UploadRefAuthFacade;
import com.yh.component.upload.facade.UploadStatusFacade;
import com.yh.component.upload.util.UploadConstants;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.component.upload.dto.UploadStatusDTO;
import com.yh.component.upload.facade.UploadNodeRefFacade;
import com.yh.platform.core.util.IOUtil;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

/**
 * 文件上传
 * 
 * @author zhangqp
 * @version 1.0, 16/11/08
 */
@SuppressWarnings("unchecked")
public class UploadFileAction extends BaseAction {

	private UploadNodeRefFacade uploadNodeRefFacade = (UploadNodeRefFacade)SpringBeanUtil.getBean("uploadNodeRefFacade");
	private UploadStatusFacade uploadStatusFacade = (UploadStatusFacade)SpringBeanUtil.getBean("uploadStatusFacade");
	private UploadRefAuthFacade uploadRefAuthFacade = (UploadRefAuthFacade)SpringBeanUtil.getBean("uploadRefAuthFacade");
	private UploadFileFacade uploadFileFacade = (UploadFileFacade)SpringBeanUtil.getBean("uploadFileFacade");
	
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
	
	public ActionForward goUploadFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			String refRoleCode = request.getParameter("refRoleCode");
			String refCode = request.getParameter("refCode");//当前来源
			String refOid = request.getParameter("refOid");//要操作的所有来源（含当前来源）
			
			//来源配置信息
			UploadNodeRefDTO uploadNodeRefDTO = uploadNodeRefFacade.get(refCode);
			
			//该角色的权限
			List<UploadRefAuthDTO> uploadRefAuthDTOList = uploadRefAuthFacade.findUploadRefAuth(refRoleCode, refCode);
			
			if (CollectionUtils.isNotEmpty(uploadRefAuthDTOList)) {
				for (UploadRefAuthDTO dto : uploadRefAuthDTOList) {
					if (UploadConstants.AUTHORITY_9.equals(dto.getAuthority())) {
						request.setAttribute("hasAuthority9", true);
						break;
					}
				}
			}
			
			request.setAttribute("uploadNodeRefDTO", uploadNodeRefDTO);
			request.setAttribute("uploadRefAuthDTOList", uploadRefAuthDTOList);
			
			request.setAttribute("refRoleCode", refRoleCode);
			request.setAttribute("refCode", refCode);
			request.setAttribute("refOid", refOid);
			
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
	public ActionForward uploadFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FileOutputStream os = null;
		InputStream in = null;
		try {
			//上传文件的参数放到url后面，可以直接getParameter
			String fileMD5 = request.getParameter("fileMD5");
			Long fileLength = Long.valueOf(request.getParameter("fileLength"));
			String uuid = request.getParameter("uuid");
			
			String refCode = request.getParameter("refCode");
			Long refOid = NumberUtils.longValue(request.getParameter("refOid"));
//			if(true)throw new ServiceException("s");
			DiskFileItemFactory factory = new DiskFileItemFactory();
//			factory.setRepository(new File(TEMP_FOLDER));
			factory.setSizeThreshold(1024 * 1024);
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			List<FileItem> list = upload.parseRequest(request);
			
			FileItem item = getUploadFileItem(list);
			String filename = getUploadFileName(item);

			in = item.getInputStream();
			byte[] tempbytes = new byte[in.available()];
//			int byteread = 0;

			File file = null;
			UploadStatusDTO uploadStatus = uploadStatusFacade.get(uuid);
			
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
	            
				File dir = uploadNodeRefFacade.getRefUploadDir(refCode);
	            
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
			
			uploadStatusFacade.saveOrUpdate(uploadStatus);
			
			//上传完成后，写入文件列表
			if (progress == 100) {
				
				uploadFileFacade.create(refCode, refOid, uploadStatus);
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
