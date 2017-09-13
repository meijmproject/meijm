package com.yh.hr.worktop.web.action;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.hr.worktop.dto.TaskResponseDTO;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.hr.res.bt.dto.LinkDTO;
import com.yh.hr.worktop.util.TaskResponseUtil;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.web.UserContext;
import com.yh.platform.core.web.action.BaseAction;

import jade.workflow.utils.DateUtil;
/**
 * 业务事项-抽象类
 * @author	liuhw
 * @created 2016-08-31
 */
public abstract class TaskFlowBaseAction extends BaseAction
{
	/**
	 *	业务事项 - 创建
	 *	@param	mapping
	 *	@param	form
	 *	@param	request
	 *	@param	response
	 *	@return	null
	 *	@throws	Exception
	 */
	protected ActionForward submitCreate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, Object object) throws Exception
	{
		return this.itemFlow(mapping, form, request, response, object, "submitCreate");
	}
	/**
	 *	业务事项 - 上报
	 *	@param	mapping
	 *	@param	form
	 *	@param	request
	 *	@param	response
	 *	@return	null
	 *	@throws	Exception
	 */
	protected ActionForward submitReported(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, Object object) throws Exception
	{
		return this.itemFlow(mapping, form, request, response, object, "submitReported");
	}
	/**
	 *	业务事项 - 删除
	 *	@param	mapping
	 *	@param	form
	 *	@param	request
	 *	@param	response
	 *	@return	null
	 *	@throws	Exception
	 */
	protected ActionForward submitDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, Object object) throws Exception
	{
		return this.itemFlow(mapping, form, request, response, object, "submitDelete");
	}
	/**
	 *	业务事项 - 终止
	 *	@param	mapping
	 *	@param	form
	 *	@param	request
	 *	@param	response
	 *	@return	null
	 *	@throws	Exception
	 */
	protected ActionForward submitStop(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, Object object) throws Exception
	{
		return this.itemFlow(mapping, form, request, response, object, "submitStop");
	}
	/**
	 *	业务事项 - 撤回
	 *	@param	mapping
	 *	@param	form
	 *	@param	request
	 *	@param	response
	 *	@return	null
	 *	@throws	Exception
	 */
	protected ActionForward submitRevoke(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, Object object) throws Exception
	{
		return this.itemFlow(mapping, form, request, response, object, "submitRevoke");
	}
	/**
	 *	业务事项 - 同意(同意)
	 *	@param	mapping
	 *	@param	form
	 *	@param	request
	 *	@param	response
	 *	@return	null
	 *	@throws	Exception
	 */
	protected ActionForward submitRecheckAgree(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, Object object) throws Exception
	{
		return this.itemFlow(mapping, form, request, response, object, "submitRecheckAgree");
	}
	/**
	 *	业务事项 - 不同意
	 *	@param	mapping
	 *	@param	form
	 *	@param	request
	 *	@param	response
	 *	@return	null
	 *	@throws	Exception
	 */
	protected ActionForward submitRecheckDisAgree(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, Object object) throws Exception
	{
		return this.itemFlow(mapping, form, request, response, object, "submitRecheckDisAgree");
	}
	/**
	 *	业务事项 - 退回（不同意）
	 *	@param	mapping
	 *	@param	form
	 *	@param	request
	 *	@param	response
	 *	@return	null
	 *	@throws	Exception
	 */
	protected ActionForward submitRecheckBack(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, Object object) throws Exception
	{
		return this.itemFlow(mapping, form, request, response, object, "submitRecheckBack");
	}
	/**
	 *	业务事项 - 流程推动
	 *	@param	mapping
	 *	@param	form
	 *	@param	request
	 *	@param	response
	 *	@return	null
	 *	@throws	Exception
	 */
	protected ActionForward submitFlow(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, Object object, String methodName) throws Exception
	{
		return this.itemFlow(mapping, form, request, response, object, methodName);
	}
	
	/**
	 *	业务事项 - 事项流程
	 *	1、初始托盘数据
	 *	2、执行Facade流程方法
	 *	3、组装执行结果反馈信息
	 *	@param	mapping
	 *	@param	form
	 *	@param	request
	 *	@param	response
	 *	@return	null
	 *	@throws	Exception
	 */
	@SuppressWarnings({ "rawtypes"})
	private ActionForward itemFlow(ActionMapping mapping, 
								   ActionForm form,
								   HttpServletRequest request, 
								   HttpServletResponse response,
								   Object  object,
								   String methodName) throws Exception
	{
		String[]   itemNodeCodes = request.getParameter("itemNodeCodes").split(",");
		String[] itemCodes    = request.getParameter("itemCodes").split(",");
		String[] bizTaskOids  = request.getParameter("bizTaskOids").split(",");
		String[] applyNames   = request.getParameter("applyNames").split(",");
		String   opinion 	  = request.getParameter("opinion");
		String auditDateStr = request.getParameter("auditDateStr");
		String defFlowExpress  = request.getParameter("defFlowExpress");
		
		//批量前处理（验证）
		String checkMessage = check(request, itemNodeCodes, itemCodes, bizTaskOids, object, "checkBeforeTask");
		
		if (checkMessage != null) {
			response.getWriter().print(JSONHelper.fromObject(false, checkMessage));
			return null;
		}
		
		List<TaskResponseDTO> applyResponseDTOs = new ArrayList<TaskResponseDTO>();
		boolean success = true;
		for(int i=0; i<bizTaskOids.length; i++)
		{
			try
			{
				// 1、初始托盘数据
//				Map<String, Object>  ma = new HashMap<String, Object>();
				BaseHandleData data = new BaseHandleData();
				BaseHandler.put(data);
				
				data.put("bizTaskOid", bizTaskOids[i]);
				data.put("itemCode", itemCodes==null?null:itemCodes[i]);
				data.put("itemNodeCode",itemNodeCodes==null?null:itemNodeCodes[i]);
					 
				for(Object e : request.getParameterMap().keySet()){
					data.put(e.toString(), request.getParameter(e.toString()).toString());
				}
				if(log.isInfoEnabled()){
					log.info("paramKEY="+"bizTaskOid"+"------value="+bizTaskOids[i]+"-------");
					log.info("paramKEY="+"itemCode"+"------value="+(itemCodes==null?"":itemCodes[i])+"-------");
					log.info("paramKEY="+"itemNodeCode"+"------value="+(itemNodeCodes==null?"":itemNodeCodes[i])+"-------");
					log.info("flow data params: " + data);
				 }
				 
				//initOperationData(new BizOperationDataService(ma));
				
				// 2、执行Facade流程方法
				Class c = object.getClass();
				LinkDTO linkDTO = new LinkDTO();
				linkDTO.setOpinion(opinion);
				if(null != auditDateStr)
				{
					linkDTO.setAuditDate(DateUtil.parseDate(auditDateStr));
				}
				linkDTO.setDefFlowExpress(defFlowExpress);
				linkDTO.setBizTaskOid(new Long(bizTaskOids[i]));
				linkDTO.setItemCode(itemCodes[i]);
				linkDTO.setItemNodeCode(itemNodeCodes[i]);
				
				linkDTO.setCreateByCode(UserContext.getLoginUserID());
				linkDTO.setCreateByName(UserContext.getLoginUserName());
				linkDTO.setUpdateByCode(UserContext.getLoginUserID());
				linkDTO.setUpdateByName(UserContext.getLoginUserName());
				
				@SuppressWarnings("unchecked")
				Method method = c.getMethod(methodName, new Class[]{LinkDTO.class});
				
				invoke(object, method, linkDTO);
				
				// 3、组装执行结果反馈信息
				applyResponseDTOs.add(TaskResponseUtil.getSuccessResponseDTO());
			}
			catch (Exception se)
			{
				this.handleException(request, se, se.getMessage());
				applyResponseDTOs.add(TaskResponseUtil.getFailResponseDTO(se.getMessage(), applyNames[i]));
				success = false;
			}finally
			{
				BaseHandler.remove();
			}
		}
		response.getWriter().print(JSONHelper.fromObject(success, TaskResponseUtil.conversionReportDTO(applyResponseDTOs)));
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String check(HttpServletRequest request, 
								String[] itemNodeCodes, 
								String[] itemCodes, 
								String[] bizTaskOids,
								Object  object,
								String checkMethod) throws IOException {
		
		if(ArrayUtils.isEmpty(itemCodes)) return null;
		
		try {
			Class c = object.getClass();
			Method method = c.getMethod(checkMethod);
			
			List<Long> taskOids = new ArrayList<Long>(bizTaskOids.length);
			for(int i=0; i<bizTaskOids.length; i++) {
				
				taskOids.add(NumberUtils.longValue(bizTaskOids[i]));
			}
			
			BaseHandleData data = new BaseHandleData();
			BaseHandler.put(data);
			
			for(Object e : request.getParameterMap().keySet()){
				data.put(e.toString(), request.getParameter(e.toString()).toString());
			}
			
			data.put("taskOids", taskOids);
			data.put("itemCode", itemCodes==null?null:itemCodes[0]);
			data.put("itemNodeCode",itemNodeCodes==null?null:itemNodeCodes[0]);
			
			invoke(object, method);
		}
		catch (NoSuchMethodException e) {
			//没有提供验证方法，不进行验证
			return null;
		}
		catch (Exception e) {
			this.handleException(request, e, e.getMessage());
			
			return StringUtils.defaultIfEmpty(e.getMessage(), "系统异常。");
		}finally {
			BaseHandler.remove();
		}
		
		return null;
	}
	
	private static Object invoke(Object object, Method method, Object ... params ) throws Exception {
		try {
			return method.invoke(object, params);
		} catch (InvocationTargetException e) {
			
			if (e.getTargetException() instanceof Exception) {
				throw (Exception) e.getTargetException();
			}
			
			throw new Exception(e.getTargetException());
		}
	}
	
	/**
	 * 初始托盘数据
	 * @param itemCode
	 * @param itemNodeCode
	 * @param bizTaskOid
	 * @throws ServiceException 
	 */
	/*protected void initOperationData(String itemCode,String itemNodeCode,Long bizTaskOid) throws ServiceException
	{
		// 1、初始托盘数据
		//BizOperationDataService operationData = new BizOperationDataService(itemCode, itemNodeCode);
		//operationData.setBizTaskOid(bizTaskOid);
		//initOperationData(operationData);
		
	}*/
	/**
	 * 初始托盘数据
	 * @param itemCode
	 * @param itemNodeCode
	 * @param bizTaskOid
	 * @throws ServiceException 
	 */
	/*protected void initOperationData(String itemCode,String itemNodeCode) throws ServiceException
	{
		initOperationData(itemCode, itemNodeCode, null);
	}*/
	/**
	 * 初始托盘数据
	 * @param itemCode
	 * @param itemNodeCode
	 * @param bizTaskOid
	 * @throws ServiceException 
	 */
	/*protected void initOperationData(BaseOperationDataService operationData) throws ServiceException
	{
		BaseOperationDataHandler.removeOperationData();
		BaseOperationDataHandler.setOperationData(operationData);
	}*/
	
	/**
	 *	前置提醒（警告）信息检查
	 *	@param	mapping
	 *	@param	form
	 *	@param	request
	 *	@param	response
	 *	@return	null
	 *	@throws	Exception
	 */
	protected ActionForward checkRemind(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, Object object) throws Exception
	{
		String[] itemNodeCodes = request.getParameter("itemNodeCodes").split(",");
		String[] itemCodes    = request.getParameter("itemCodes").split(",");
		String[] bizTaskOids  = request.getParameter("bizTaskOids").split(",");
//		String[] applyNames   = request.getParameter("applyNames").split(",");
		
		//批量前处理（验证）
		String checkMessage = check(request, itemNodeCodes, itemCodes, bizTaskOids, object, "checkRemind");
		
		if (checkMessage != null) {
			response.getWriter().print(JSONHelper.fromObject(false, checkMessage));
			return null;
		}
		
		response.getWriter().print(JSONHelper.fromObject(true, null));
		return null;
	}
}
