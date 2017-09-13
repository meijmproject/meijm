package com.yh.component.dictionary.web.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yh.component.dictionary.facade.DicTypeFacade;
import com.yh.component.dictionary.utils.DicHelper;
import com.yh.component.dictionary.web.form.DicItemForm;
import com.yh.component.taglib.TableTagBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yh.component.dictionary.bo.DicItem;
import com.yh.component.dictionary.bo.DicType;
import com.yh.component.dictionary.dto.DicItemDTO;
import com.yh.component.dictionary.facade.DicItemFacade;
import com.yh.component.dictionary.utils.DicSortUtils;
import com.yh.platform.core.constant.Constant;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

/**
 * 跳转到树的action
 * 
 * @author taoy
 * 
 */
public class DicTypeTreeAction extends BaseAction {
	private DicTypeFacade dicTypeFacade = (DicTypeFacade) SpringBeanUtil
			.getBean("dicTypeFacade");
	private DicItemFacade dicItemFacade = (DicItemFacade) SpringBeanUtil
	.getBean("dicItemFacade");
	/**
	 * 跳转到显示树
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goToSelTree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("----------进入DicTypeTreeAction的goToSelTree方法-----------");
		try {
			String dicTypeCode = request.getParameter("dicTypeCode");
			if (null == dicTypeCode || "".equals(dicTypeCode)) {
				throw new ServiceException("", "dicTypeCode不能为空！");
			}
			log.info("dicTypeCode:" + dicTypeCode);
			DicType dicType = dicTypeFacade.getDicTypeByCode(dicTypeCode);
			if (null != dicType) {
				request.setAttribute("dicTypeName", dicType.getDicTypeName());
				JSONObject json = new JSONObject();
				json.put("dicTypeName", dicType.getDicTypeName());
				response.getWriter().print(json.toString());
			}
			return null;
		} catch (ServiceException e) {
			handleException(request, e, "show tree faild!");
			return mapping.findForward(FORWARD_FAIL);
		}
	}
	/**
	 * 查询下级节点
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goToSelTreeChil(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("----------进入DicTypeTreeAction的goToSelTreeChil方法-----------");
		try {
			String dicItemCode = request.getParameter("dicItemCode");
			String dicTypeCode = request.getParameter("dicTypeCode");
			DicType dicType = dicTypeFacade.getDicTypeByCode(dicTypeCode);
			Long dicTypeOid=dicType.getDicTypeOid();
			if (null == dicItemCode || "".equals(dicItemCode)) {
				throw new ServiceException("", "dicItemCode不能为空！");
			}
			log.info("dicTypeCode:" + dicItemCode);
			DicItem dicItem = dicTypeFacade.getDicItemByCode(dicItemCode,dicTypeOid);
			if (null != dicItem) {
				if(dicItem.getParentCode()!=null&&!("").equals(dicItem.getParentCode())){
				}
				request.setAttribute("dicTypeName", dicItem.getDicItemName());
				JSONObject json = new JSONObject();
				json.put("dicItemName", dicItem.getDicItemName());
				response.getWriter().print(json.toString());
			}
			return null;
		} catch (ServiceException e) {
			handleException(request, e, "show tree faild!");
			return mapping.findForward(FORWARD_FAIL);
		}
	}
	/**
	 * 查询上级节点
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findDicItemUp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String dicItemCode = request.getParameter("dicItemCode");
			String dicTypeCode = request.getParameter("dicTypeCode");
			DicItem dicItem = dicTypeFacade.findDicItemUp(dicTypeCode,dicItemCode);
			JSONObject json = new JSONObject();
			if (null != dicItem) {
				json.put("dicItem", dicItem);
			}
			response.getWriter().print(json.toString());
			return null;
		} catch (ServiceException e) {
			handleException(request, e, "show tree faild!");
			return mapping.findForward(FORWARD_FAIL);
		}
	}
	/**
	 * 通过名称查询DicItem
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findDicItemByName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String dicItemName = request.getParameter("dicItemName");
			String dicTypeCode = request.getParameter("dicTypeCode");
			List<DicItem> dicItem = dicTypeFacade.findDicItemByName(dicTypeCode,dicItemName);
			List<DicItemDTO> dicItemDto=BeanHelper.copyProperties(dicItem, DicItemDTO.class);
			JSONObject json = new JSONObject();
			if (CollectionUtils.isNotEmpty(dicItemDto)) {
				json.put("dicItemDto", dicItemDto);
			}
			response.getWriter().print(json.toString());
			return null;
		} catch (ServiceException e) {
			handleException(request, e, "show tree faild!");
			return mapping.findForward(FORWARD_FAIL);
		}
	}
	/**
	 * 展示父子关系（过滤）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getTreeFilterSecondFloor(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String dicTypeCode = request.getParameter("dicTypeCode");
			String dicItemCode = request.getParameter("dicItemCode");
			String isInDelete = request.getParameter("isInDelete");
			String[] notInclude = request.getParameter("notInclude")==null?null:request.getParameter("notInclude").split(",");
			log.info("dicTypeCode:" + dicTypeCode);
			log.info("dicItemCode:" + dicItemCode);
			boolean isIncludeDel = false;
			if(StringUtils.isNotEmpty(isInDelete))
			{
				isIncludeDel = true;
			}
			List<DicItem> dicItemsAll = this.getAllDicItemByDicItemCode(dicTypeCode,isIncludeDel);
			List<DicItemDTO> dicItems = BeanHelper.copyProperties(dicItemsAll, DicItemDTO.class);
			List<DicItemDTO> dicItemDtos = new ArrayList<DicItemDTO>();
			if (StringUtils.isEmpty(dicItemCode)) {
				if (CollectionUtils.isNotEmpty(dicItems)) {
					for (DicItemDTO di : dicItems) {
						String pc = di.getParentCode();
						if ((pc == null || pc.length() < 1) && !ArrayUtils.contains(notInclude, di.getDicItemCode())) {
							DicItemDTO dicItemDTO = dicItemFacade.getIsLeaf(di);
							if(dicItemDTO == null) di.setIsleaf(true);
							dicItemDtos.add(di);
						}
					}
				}
			} else {
				if (CollectionUtils.isNotEmpty(dicItems)) {
					for (DicItemDTO di : dicItems) {
						if (dicItemCode.equals(di.getParentCode()) && !ArrayUtils.contains(notInclude, di.getDicItemCode())) {
							DicItemDTO dicItemDTO = dicItemFacade.getIsLeaf(di);
							if(dicItemDTO == null) di.setIsleaf(true);
							dicItemDtos.add(di);
						}
					}
				}
			}
			JSONArray array = new JSONArray();
			if(CollectionUtils.isNotEmpty(dicItemDtos)){
				for (DicItemDTO dto : dicItemDtos) {
					array.element(JSONHelper.fromObject(dto));
				}
			}
			JSONObject json = new JSONObject();
			json.put("json", array);
			response.getWriter().print(json.toString());
		} catch (Exception e) {
			handleException(request, e, "show tree faild!");
			
		}
		return null;
	}
	/**
	 * 查询所有的DicItem
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<DicItem> getAllDicItemByDicItemCode(String dicTypeCode,boolean isIncludeDel)
			throws ServiceException {
		List<DicItem> allList = new ArrayList<DicItem>();
		if (null != dicTypeCode) {
			allList = DicHelper.findDicItemByCode(dicTypeCode,isIncludeDel);
			DicSortUtils.sort(allList);
			if (CollectionUtils.isNotEmpty(allList)) {
				return allList;
			}
		}
		return allList;
	}
	
	/**
	 * 跳转到数据字典管理工作台
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goToDictionaryManageWorkbench(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward(FORWARD_SUCCESS);
	}
	
	/**
	 * 获取数据字典类型列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listDicTypeList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			TableTagBean ttb = new TableTagBean(request);
			List<DicType> list = dicTypeFacade.findDicTypeListByCondition(ttb);
			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();
			json.put("total", ttb.getTotal());
			if(CollectionUtils.isNotEmpty(list)){
				JSONObject obj = null;
				for (DicType dto : list) {
					obj = JSONHelper.fromObject(dto, DateUtil.DATE_PATTERN_DEFAULT);
					array.element(obj);
				}
			}
			json.put("rows", array);
			response.getWriter().print(json.toString());
		} catch (Exception e) {
			handleException(request, e, "查询数据字典类型列表失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "查询数据字典类型列表失败")));
		}
        return null;
	}
	
	/**
	 * 跳转到数据字典DICITEM工作台
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goToDicItemWorkbench(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("dicTypeOid", request.getParameter("dicTypeOid"));
		TableTagBean ttb = new TableTagBean(request);
		List<DicType> list = dicTypeFacade.findDicTypeListByCondition(ttb);
		if(list!=null&&list.size()>0) {
			request.setAttribute("dicTypeName", list.get(0).getDicTypeName());
		}
		return mapping.findForward(FORWARD_SUCCESS);
	}
	
	/**
	 * 跳转到查看数据类型窗口
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goViewDicType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String dicTypeOid = request.getParameter("dicTypeOid");
		if(dicTypeOid!=null) {
			DicType dicType = dicTypeFacade.get(Long.valueOf(dicTypeOid));
			request.setAttribute("dicType", dicType);
			return mapping.findForward(FORWARD_SUCCESS);
		}
		return mapping.findForward(FORWARD_SUCCESS);
	}
	
	/**
	 * 获取数据字典列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listDicItemList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			TableTagBean ttb = new TableTagBean(request);
			List<DicItem> list = dicItemFacade.findDicItemListByCondition(ttb);
			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();
			json.put("total", ttb.getTotal());
			if(CollectionUtils.isNotEmpty(list)){
				JSONObject obj = null;
				for (DicItem dto : list) {
					obj = JSONHelper.fromObject(dto, DateUtil.DATE_PATTERN_DEFAULT);
					if(dto.getIsActive()!=null&&!dto.getIsActive().equals("")) {
						if(dto.getIsActive().equals("Y")) {
							obj.put("isActiveDesc", "生效");
						}else if(dto.getIsActive().equals("N")) {
							obj.put("isActiveDesc", "失效");
						}
					}else {
						obj.put("isActiveDesc", "新建");
					}
					array.element(obj);
				}
			}
			json.put("rows", array);
			response.getWriter().print(json.toString());
		} catch (Exception e) {
			handleException(request, e, "查询数据字典列表失败");
			response.getWriter().print(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(e.getMessage(), "查询数据字典列表失败")));
		}
        return null;
	}
	
	/**
	 * 跳转到数据字典DICITEM新增窗口
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goCreateDicItem(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("dicTypeOid", request.getParameter("dicTypeOid"));
		return mapping.findForward(FORWARD_SUCCESS);
	}
	
	/**
	 * 新增数据字典
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createDicItem(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DicItemForm dicItemForm = (DicItemForm) form;
		try {
			TableTagBean ttb = new TableTagBean();
			ttb.getCondition().put("dicTypeOid", dicItemForm.getDicTypeOid().toString());
			ttb.getCondition().put("dicItemCode", dicItemForm.getDicItemCode());
			List<DicItem> list = dicItemFacade.findDicItemListByCondition(ttb);
			if(list!=null&&list.size()>0) {
				throw new Exception("新增失败！该字典类型中已存在字典编码"+dicItemForm.getDicItemCode());
			}
			DicItem dicItem = BeanHelper.copyProperties(dicItemForm, DicItem.class);
			dicItemFacade.create(dicItem);
			response.getWriter().write(JSONHelper.fromObject(true, null).toString());
		} catch (Exception se) {
			this.handleException(request, se, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(se.getMessage(), "新增失败")).toString());
			return null;
		}
		return null;
	}
	
	/**
	 * 删除数据字典
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteDicItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] dicItemOids = request.getParameter("dicItemOids").split(",");
		try {
        	dicItemFacade.deleteReal(dicItemOids);
        	response.getWriter().write(JSONHelper.fromObject(true, null).toString());
        } catch (Exception se) {
        	this.handleException(request, se, "dicItemOids=" + Arrays.asList(dicItemOids).toString());
        	return mapping.getInputForward();
        }
        return null;
	}
	
	/**
	 * 跳转到数据字典DICITEM修改窗口
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goUpdateDicItem(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String dicItemOid = request.getParameter("dicItemOid");
		try{
			if(dicItemOid!=null) {
				DicItem dicItem = dicItemFacade.get(Long.valueOf(dicItemOid));
				DicItemForm dicItemForm = BeanHelper.copyProperties(dicItem, DicItemForm.class);
				request.setAttribute("dicItemForm", dicItemForm);
				return mapping.findForward(FORWARD_SUCCESS);
			}
		} catch (Exception se) {
        	this.handleException(request, se, "dicItemOid=" + dicItemOid);
        }
		return mapping.getInputForward();
	}
	
	/**
	 * 修改数据字典
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateDicItem(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DicItemForm dicItemForm = (DicItemForm) form;
		try {
			DicItem dicItem = dicItemFacade.get(dicItemForm.getDicItemOid());
			TableTagBean ttb = new TableTagBean();
			ttb.getCondition().put("dicTypeOid", dicItem.getDicTypeOid().toString());
			ttb.getCondition().put("dicItemCode", dicItemForm.getDicItemCode());
			List<DicItem> list = dicItemFacade.findDicItemListByCondition(ttb);
			if(list!=null&&list.size()>0&&!list.get(0).getDicItemOid().equals(dicItemForm.getDicItemOid())) {
				throw new Exception("新增失败！该字典类型中已存在字典编码"+dicItemForm.getDicItemCode());
			}
			dicItem.setDicItemCode(dicItemForm.getDicItemCode());
			dicItem.setDicItemName(dicItemForm.getDicItemName());
			dicItem.setDisplayOrder(dicItemForm.getDisplayOrder());
			dicItem.setViewName(dicItemForm.getViewName());
			dicItem.setRemark(dicItemForm.getRemark());
			dicItemFacade.update(dicItem);
			response.getWriter().write(JSONHelper.fromObject(true, null).toString());
		} catch (Exception se) {
			this.handleException(request, se, null);
			response.getWriter().write(JSONHelper.fromObject(false, StringUtils.defaultIfEmpty(se.getMessage(), "修改数据字典失败")).toString());
			return null;
		}
		return null;
	}
	
	/**
	 * 置为生效
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setActiveYesDicItem(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] dicItemOids = request.getParameter("dicItemOids").split(",");
		try {
			if(null != dicItemOids && dicItemOids.length>0)
			{
				for(String oid:dicItemOids)
				{
					DicItem dicItem = dicItemFacade.get(Long.valueOf(oid));
					dicItem.setIsActive(Constant.YES);
					dicItemFacade.update(dicItem);
					DicType dicType = dicTypeFacade.get(dicItem.getDicTypeOid());
					DicHelper.reSetEffect(dicType.getDicTypeCode());
				}
			}
        	response.getWriter().write(JSONHelper.fromObject(true, null).toString());
        } catch (Exception se) {
        	this.handleException(request, se, "dicItemOids=" + Arrays.asList(dicItemOids).toString());
        	return mapping.getInputForward();
        }
        return null;
	}
	
	/**
	 * 置为失效
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setActiveNoDicItem(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] dicItemOids = request.getParameter("dicItemOids").split(",");
		try {
			if(null != dicItemOids && dicItemOids.length>0)
			{
				for(String oid:dicItemOids)
				{
					DicItem dicItem = dicItemFacade.get(Long.valueOf(oid));
					dicItem.setIsActive(Constant.NO);
					dicItemFacade.update(dicItem);
					DicType dicType = dicTypeFacade.get(dicItem.getDicTypeOid());
					DicHelper.reSetEffect(dicType.getDicTypeCode());
				}
			}
        	response.getWriter().write(JSONHelper.fromObject(true, null).toString());
        } catch (Exception se) {
        	this.handleException(request, se, "dicItemOids=" + Arrays.asList(dicItemOids).toString());
        	return mapping.getInputForward();
        }
        return null;
	}
	
	/**
	 * 跳转到查看数据字典DICITEM
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goViewDicItem(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String dicItemOid = request.getParameter("dicItemOid");
		try{
			if(dicItemOid!=null) {
				DicItem dicItem = dicItemFacade.get(Long.valueOf(dicItemOid));
				DicItemForm dicItemForm = BeanHelper.copyProperties(dicItem, DicItemForm.class);
				request.setAttribute("dicItemForm", dicItemForm);
				return mapping.findForward(FORWARD_SUCCESS);
			}
		} catch (Exception se) {
        	this.handleException(request, se, "dicItemOid=" + dicItemOid);
        }
		return mapping.getInputForward();
	}
}
