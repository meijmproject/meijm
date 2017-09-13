package com.yh.component.dictionary.facade.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yh.component.dictionary.bo.DicItem;
import com.yh.component.dictionary.bo.DicType;
import com.yh.component.dictionary.facade.DicTypeFacade;
import com.yh.component.dictionary.service.DicTypeService;
import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class DicTypeFacadeImpl implements DicTypeFacade
{
	private DicTypeService dicTypeService;

	public void setDicTypeService(DicTypeService dicTypeService)
	{
		this.dicTypeService = dicTypeService;
	}

	/**
	 * 查找所有
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<DicType> listAllDicType() throws ServiceException
	{
		return dicTypeService.listAllDicType();
	}
	
	public void create(DicType obj) throws ServiceException
	{
		dicTypeService.create(obj);
	}

	public DicType get(Long dicTypeOid) throws ServiceException
	{
		return dicTypeService.get(dicTypeOid);
	}
    
	public void update(DicType obj) throws ServiceException
	{
		dicTypeService.update(obj);
	}
    
	public void delete(String[] dicTypeOids) throws ServiceException
	{
		dicTypeService.delete(dicTypeOids);
	}
	
//	/**
//	 * 参数总记录数
//	 * 
//	 * @param params
//	 * @return int
//	 * @throws ServiceException
//	 */
//	public int countDicTypeByCondition(StringMap params) throws ServiceException
//	{
//		return dicTypeService.countDicTypeByCondition(params);
//	}

	public List<DicType> findDicTypeListByCondition(TableTagBean ttb)
			throws ServiceException {
		{
			return dicTypeService.findDicTypeListByCondition(ttb);
		}
	}
	/**
	 * find dictype by dictypeCode
	 * @param dicTypeCode
	 * @return
	 * @throws ServiceException
	 */
	public DicType getDicTypeByCode(String dicTypeCode) throws ServiceException{
		
		return dicTypeService.getDicTypeByCode(dicTypeCode);
	}
	public DicItem getDicItemByCode(String dicItemCode,Long dicTypeOid) throws ServiceException{
		
		return dicTypeService.getDicItemByCode(dicItemCode,dicTypeOid);
	}

	/* (non-Javadoc)
	 * @see gov.szghrs.base.facade.DicTypeFacade#deleteDicType(java.lang.String)
	 */
	public String deleteDicType(String dicTypeOid)
	{
		String[] oids = dicTypeOid.split(",");
		String success = "true";
		try
		{
			dicTypeService.delete(oids);
		}
		catch (ServiceException e)
		{
			success = "flase";
		}
		return success;
	}

	/* (non-Javadoc)
	 * @see gov.szghrs.base.facade.DicTypeFacade#update(gov.szghrs.base.bo.DicType, javax.servlet.http.HttpSession)
	 */
	public String updateDicType(DicType obj, HttpServletRequest request)
	{
		obj.setLastModifyDate(DateUtil.now());
		obj.setLastModifierCode(UserContext.getLoginUserID());
		obj.setLastModifierName(UserContext.getLoginUserName());
		obj.setIsActive("Y");
		String msg = "更新成功";
		try
		{
			dicTypeService.update(obj);
		}
		catch (ServiceException e)
		{
			msg = "更新失败";
		}
		return msg;
	}

	public List<DicItem> listSubItem(String dicTypeCode, String parentItem) throws ServiceException
	{
		return dicTypeService.listSubItem(dicTypeCode,parentItem);
	}

	public DicItem findDicItemUp(String dicTypeCode, String dicItemCode)
			throws ServiceException {
		return dicTypeService.findDicItemUp(dicTypeCode, dicItemCode);
	}

	public List<DicItem> findDicItemByName(String dicTypeCode,
			String dicItemName) throws ServiceException {
		return dicTypeService.findDicItemByName(dicTypeCode,dicItemName);
	}

	
}
