/*
 * @(#) BaseAction.java        1.00         2006-5-24
 * 
 * Copyright (c) 2006 JADE EXPRESS Corporation. All Rights Reserved.
 *
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. FEDERAL EXPRESS AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL FEDERAL
 * EXPRESS OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF FEDERAL EXPRESS HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 */
package com.yh.platform.core.web.action;

import javax.servlet.http.HttpServletRequest;

import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.util.CommonFunctions;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.PageListInfo;

/**
 * @description This class define methods for error handling.
 *
 * @author chenkebing
 * @created 2006-5-24
 * @version 1.0
 *
 */

public abstract class BaseAction extends DispatchAction {
	protected Logger			logger			= Logger.getLogger(this.getClass());

	// struts forward key
	public static final String	FORWARD_SUCCESS	= "success";
	public static final String	FORWARD_FAIL	= "fail";
	public static final String	FORWARD_CANCEL	= "cancel";
	public static final String	FORWARD_EXCEL	= "excel";

	/**
	 * handle error catched in action
	 * 
	 * @param request
	 * @param se
	 * @param log
	 */
	protected void handleException(HttpServletRequest request, Exception e, Object errorObject) {
		handleException(request, e instanceof ServiceException ? (ServiceException) e : new ServiceException(e.getMessage(), e), errorObject, null, null);
	}

	protected void handleException(HttpServletRequest request, ServiceException se, Object errorObject, String[] oldErrorkey, String[] newErrorkey) {
		
		ActionMessages errors = new ActionMessages();

		String errorKey = "errors.service.common";
		if (se != null && se.getErrorKey() != null) {
			errorKey = se.getErrorKey();
		}
		// 用新指定的KEY替代缺省的KEY
		if (oldErrorkey != null && newErrorkey != null && oldErrorkey.length == newErrorkey.length) {
			for (int i = 0; i < oldErrorkey.length; i++) {
				if (oldErrorkey[i].equals(errorKey)) {
					errorKey = newErrorkey[i];
					break;
				}
			}

		}

		Object value = se.getValue1();
		if (value != null)
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorKey, value));
		else
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorKey));

		saveErrors(request, errors);

		if (se != null) {
			String userCode = CommonFunctions.getLoginUserID(request);

			if (se instanceof DataAccessFailureException) {
				String errinfo = "";
				if (errorObject != null)
					errinfo = errorObject.toString();

				if (se.getMessage() != null)
					logger.info("[" + userCode + "][errkey=" + errorKey + "] " + se.getMessage() + ",detail:" + errinfo, se);
				else
					logger.info("[" + userCode + "][errkey=" + errorKey + "] " + errinfo, se);
			} else {
				logger.info("[" + userCode + "][errkey=" + errorKey + "] " + se.getMessage());
			}

			logger.error(se.getMessage(),se);
		}
	}

	/**
	 * print log info for do action success.
	 * 
	 * @param request
	 * @param event
	 * @param obj
	 */
	public void logSuccessful(HttpServletRequest request, String event, Object obj) {
		String userCode = CommonFunctions.getLoginUserID(request);

		String detail = "";
		if (obj != null)
			detail = obj.toString();

		if (logger.isInfoEnabled())
			logger.info("[" + userCode + "] " + event + " successfully!   " + detail);
	}

	/**
	 * save error message in action
	 * 
	 * @param request
	 * @param errorKey
	 */
	protected void saveErrors(HttpServletRequest request, String errorKey) {
		ActionMessages errors = new ActionMessages();

		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorKey));
		if (!errors.isEmpty())
			saveErrors(request, errors);
	}

	protected void debug(HttpServletRequest request, String loginfo) {
		if (logger.isDebugEnabled()) {
			String userCode = CommonFunctions.getLoginUserID(request);

			logger.debug("[" + userCode + "] " + loginfo);
		}
	}

	protected void info(HttpServletRequest request, String loginfo) {
		if (logger.isInfoEnabled()) {
			String userCode = CommonFunctions.getLoginUserID(request);

			logger.info("[" + userCode + "] " + loginfo);
		}
	}

	/**
	 * parse the requeset parameter for paged list
	 * 
	 * @param request
	 * @param listInfo
	 * @return
	 */
	public PageListInfo buildPageListInfo(HttpServletRequest request) {
		PageListInfo listInfo = new PageListInfo();
		listInfo.setLocale(this.getLocale(request));
		// parse the request paramter
		String criteria = request.getParameter("criteria");
		String orderby = request.getParameter("orderby");
		String queryName = request.getParameter("queryName");
		String total = request.getParameter("total");
		String pageNo = request.getParameter("pageNo");

		if (pageNo != null && !pageNo.equals("")) {
			listInfo.setPageNum(Integer.parseInt(pageNo));
		}

		if (criteria != null && !criteria.equals("")) {
			listInfo.setCriteria(criteria);
		}

		if (total != null && !total.equals("")) {
			listInfo.setTotalRows(Integer.parseInt(total));
		}

		if (queryName != null && !queryName.equals("")) {
			listInfo.setQuery(queryName);
		}

		if (orderby != null && !orderby.equals("")) {
			listInfo.setOrderby(orderby);
		}

		return listInfo;
	}
	
}
