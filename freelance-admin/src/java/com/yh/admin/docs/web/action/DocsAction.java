/**
 * 
 */
package com.yh.admin.docs.web.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.platform.core.util.ConfigUtil;
import com.yh.platform.core.util.IOUtil;
import com.yh.platform.core.web.action.BaseAction;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/09/19
 */

public class DocsAction extends BaseAction {

	/**
	 * 下载文件
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward downFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String fileName = request.getParameter("fileName");
		
		String dir = ConfigUtil.getProperty("file.path.reference");
		
		File file = new File(dir, fileName);
		
		if (file.exists()) {
			
			IOUtil.writeResponse(response, file, "application/octet-stream", "帮助手册.rar");
			return null;
		}
		
		response.getWriter().append("<center style='margin-top:50px;'>您打开的文件不存在!</center>");
		
		return null;
	}
	
}
