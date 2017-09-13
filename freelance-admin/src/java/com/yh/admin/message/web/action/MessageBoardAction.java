package com.yh.admin.message.web.action;

import com.yh.admin.message.dto.MessageBoardDto;
import com.yh.admin.message.facade.MessageBoardFacade;
import com.yh.admin.message.web.form.MessageBoardForm;
import jade.workflow.utils.ObjectUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

public class MessageBoardAction extends BaseAction {

	private MessageBoardFacade messageBoardFacade	= (MessageBoardFacade) SpringBeanUtil.getBean("messageBoardFacade");
	
	/**
	 * 跳转到消息管理工作台
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goToNoticeManageWorkbench(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("success");
	}
	
	/**
	 * 查询通知通告列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			TableTagBean ttb = new TableTagBean(request);
			ttb.getCondition().put("show", request.getParameter("show"));
			List<MessageBoardDto> list = messageBoardFacade.find(ttb);
			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();
			json.put("total", ttb.getTotal());
			if(CollectionUtils.isNotEmpty(list)){
				JSONObject obj = null;
				for (MessageBoardDto dto : list) {
					obj = JSONHelper.fromObject(dto, DateUtil.DATE_PATTERN_DEFAULT);
					array.element(obj);
				}
			}
			json.put("rows", array);
			response.getWriter().print(json.toString());
		} catch (Exception e) {
			handleException(request, e, "查询通知通告列表失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "查询病区配置列表失败")));
		}
        return null;
	}
	
	/**
	 * 跳转到添加通知通告
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goCreate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MessageBoardForm messageBoardForm = (MessageBoardForm) form;
		request.setAttribute("messageBoardForm", messageBoardForm);
		return mapping.findForward(FORWARD_SUCCESS);
	}
	
	/**
	 * 新增通知通告
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MessageBoardForm messageBoardForm = (MessageBoardForm) form;
		try {
			MessageBoardDto messageBoardDto = BeanHelper.copyProperties(messageBoardForm, MessageBoardDto.class);
			messageBoardFacade.create(messageBoardDto);
			response.getWriter().write(JSONHelper.fromObject(true, null).toString());
		} catch (Exception se) {
			this.handleException(request, se, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(se.getMessage(), "新增失败")).toString());
			return null;
		}
		return null;
	}
	
	/**
	 * 跳转到修改通知通告界面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MessageBoardForm messageBoardForm = (MessageBoardForm)form;
		String messageOid = request.getParameter("messageOid");
		try {
            if (StringUtils.isBlank(messageOid)) {
				throw new ServiceException("error.pk.invalid", "messageOid is null");
			}
            TableTagBean ttb = new TableTagBean(request);
            ttb.getCondition().put("messageOid", messageOid);
			List<MessageBoardDto> list = messageBoardFacade.find(ttb);
			if(CollectionUtils.isNotEmpty(list)){
				BeanHelper.copyProperties(list.get(0), messageBoardForm);
				request.setAttribute("messageBoardForm", messageBoardForm);
			}
		} catch (Exception se) {
			this.handleException(request, se, "messageOid=" + messageOid);
			return mapping.getInputForward();
		}
        return mapping.findForward("success");
	}
	
	/**
	 * 修改通知通告
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        MessageBoardForm messageBoardForm = (MessageBoardForm) form;
		try {
			if(messageBoardForm.getMessageOid()==null) {
				throw new Exception("请选择一条通知通告");
			}
			MessageBoardDto messageBoardDto = new MessageBoardDto();
			BeanHelper.copyProperties(messageBoardForm, messageBoardDto);
			messageBoardFacade.update(messageBoardDto);
			response.getWriter().write(JSONHelper.fromObject(true, null).toString());
		} catch (Exception se) {
			this.handleException(request, se, messageBoardForm);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(se.getMessage(), "修改失败")).toString());
		}
		return null;
	}
	
	/**
	 * 删除通知通告
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] messageOids = request.getParameter("messageOids").split(",");
		try {
			if(null != messageOids && messageOids.length>0)
			{
	        	for(String oid: messageOids) {
	        		messageBoardFacade.delete(ObjectUtil.getValue(oid, java.lang.Long.class));
	        	}
			}
        	response.getWriter().write(JSONHelper.fromObject(true, null).toString());
        } catch (Exception se) {
        	this.handleException(request, se,"删除失败");
        	return mapping.getInputForward();
        }
        return null;
	}
	
	/**
	 * 查看通知通告
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MessageBoardForm messageBoardForm = (MessageBoardForm)form;
		String messageOid = request.getParameter("messageOid");
		try {
            if (StringUtils.isBlank(messageOid)) {
				throw new ServiceException("error.pk.invalid", "messageOid is null");
			}
            TableTagBean ttb = new TableTagBean(request);
            ttb.getCondition().put("messageOid", messageOid);
			List<MessageBoardDto> list = messageBoardFacade.find(ttb);
			if(CollectionUtils.isNotEmpty(list)){
				BeanHelper.copyProperties(list.get(0), messageBoardForm);
				request.setAttribute("messageBoardForm", messageBoardForm);
			}
		} catch (Exception se) {
			this.handleException(request, se, "messageOid=" + messageOid);
			return mapping.getInputForward();
		}
        return mapping.findForward("success");
	}
}
