package com.yh.hr.component.info.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.component.dictionary.bo.DicItem;
import com.yh.component.dictionary.facade.DicItemFacade;
import com.yh.component.dictionary.utils.DicSortUtils;
import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.info.facade.InfoManageFacade;
import com.yh.hr.res.cf.dto.JhcCfQueryConditionDTO;
import com.yh.hr.res.cf.dto.JhcCfQuerySymbolDTO;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;
/**
 * 信息管理ACTION
 * @author cheny
 * 2017-03-01
 */
public class InfoManageAction extends BaseAction{
	private InfoManageFacade infoManageFacade = (InfoManageFacade) SpringBeanUtil.getBean("infoManageFacade");
	private DicItemFacade  dicItemFacade	= (DicItemFacade) SpringBeanUtil.getBean("dicItemFacade");
	/**
	 * 跳转到信息管理页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws java.lang.Exception
	 */
	public ActionForward goTOInfoManage(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		return mapping.findForward("success");
	}
	/**
	 * 信息管理条件组合
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws java.lang.Exception
	 */
	public ActionForward listInfoCondition(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {
		try {
			TableTagBean ttb = new TableTagBean(request);
			JSONArray ary = new JSONArray();
			List<JhcCfQueryConditionDTO> list = infoManageFacade.listInfoCondition(ttb);
			JSONObject json = new JSONObject();
			if (!CollectionUtils.isEmpty(list))
			{
				for (JhcCfQueryConditionDTO dto : list)
				{
					List<JhcCfQuerySymbolDTO> symbolList = infoManageFacade.listSymbolByCondition(dto.getQueryConditionOid());
					JSONObject obj = new JSONObject();
					obj.put("conditionName", dto.getConditionName());
					obj.put("conditionCode", dto.getConditionCode());
					obj.put("conditionType", dto.getConditionType());
					obj.put("databaseField", dto.getDatabaseField());
					obj.put("dicType", dto.getDicType()==null?"void 0":dto.getDicType());
					obj.put("symbolList", symbolList);
					ary.element(obj);
				}
			}
			json.put("list", ary);
			response.getWriter().write(json.toString());
		} catch (Exception se) {
			handleException(request, se, null);
			return mapping.findForward("fail");
		}
		return null;
	}
	/**
	 *@description		 信息管理查询条件下拉字典
	 *@author            cheny
	 *@created           2017-03-06
	 *@version           1.0
	 */
	public ActionForward listSelectCondition(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		try {
			JSONArray ary = new JSONArray();
			JSONObject json = new JSONObject();
			String dicTypeCode = request.getParameter("dictionaryType");
			if (null == dicTypeCode || "".equals(dicTypeCode)) {
				throw new ServiceException("", "dicTypeCode不能为空！");
			}
			List<DicItem> allList = new ArrayList<DicItem>();
				allList = dicItemFacade.findDicItemListByTypeId(dicTypeCode,true);
				DicSortUtils.sort(allList);
				if (CollectionUtils.isNotEmpty(allList)) {
					for(DicItem dicItem : allList){
						JSONObject obj = new JSONObject();
						obj.put("dicItemName", dicItem.getDicItemName());
						obj.put("dicItemCode", dicItem.getDicItemCode());
						ary.add(obj);
					}
				}
				json.put("list", ary);
				response.getWriter().write(json.toString());
		} catch (Exception e) {
			handleException(request, e, "信息管理查询条件下拉字典");
			return null;
		}
		return null;
	}
}
