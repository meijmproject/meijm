package com.yh.hr.component.unittree.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.component.unittree.facade.JhcUnitTreeFacade;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.yh.hr.res.unit.dto.UtUnitDTO;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

public class JhcUnitTreeAction extends BaseAction {
	private JhcUnitTreeFacade jhcUnitTreeFacade = (JhcUnitTreeFacade) SpringBeanUtil
			.getBean("jhcUnitTreeFacade");
	

	/**
	 * 跳转到业务办理待办/已办页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goToUnitRight(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String unitOid = request.getParameter("unitOid");
			request.setAttribute("unitOid", unitOid);
			String unitKind = request.getParameter("unitKind");
			request.setAttribute("unitKind", unitKind);
			return mapping.findForward("success"+request.getParameter("dbflag"));
		} catch (Exception e) {
			handleException(request, e, "跳转到业务办理待办/已办页面失败");
			return mapping.findForward("error");
		}
	}

	/**
	 * 跳转到业务办理待办/已办页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goToAdminUnitRight(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String batchNo = request.getParameter("batchNo");
			request.setAttribute("batchNo", batchNo);
			return mapping.findForward("success"+request.getParameter("dbflag"));
		} catch (Exception e) {
			handleException(request, e, "跳转到业务办理待办/已办页面失败");
			return mapping.findForward("error");
		}
	}
	
	/**
	 * 加载右边工作台
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goViewUnitInformation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			return mapping.findForward("success");
		} catch (Exception e) {
			handleException(request, e, "跳转到业务办理页面失败");
			return mapping.findForward("error");
		}
	}
	public ActionForward goBusinessWorktop(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			return mapping.findForward("success");
		} catch (Exception e) {
			handleException(request, e, "跳转到业务办理页面失败");
			return mapping.findForward("error");
		}
	}
	
	
	/**
	 * 查询单位名称
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findUnitNameByUnitOid(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("----------进入JhcUnitTreeAction的findUnitNameByUnitOid方法-----------");
		try {
			String unitOid = request.getParameter("unitOid");
			if (null == unitOid || "".equals(unitOid)) {
				throw new ServiceException("", "unitOid不能为空！");
			}
			log.info("unitOid:" + unitOid);
			UtUnitDTO utUnitDTO = jhcUnitTreeFacade.findUnitInfoByUnitOid(Long.valueOf(unitOid));
			if (null != utUnitDTO) {
				request.setAttribute("unitName", utUnitDTO.getUnitName());
				JSONObject json = new JSONObject();
				json.put("unitName", utUnitDTO.getUnitName());
				response.getWriter().print(json.toString());
			}
			return null;
		} catch (ServiceException e) {
			handleException(request, e, "show tree faild!");
			return mapping.findForward(FORWARD_FAIL);
		}
	}
}
