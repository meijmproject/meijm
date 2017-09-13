package com.yh.component.dictionary.facade;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.yh.component.dictionary.bo.DicItem;
import com.yh.component.dictionary.bo.DicType;
import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.exception.ServiceException;

public interface DicTypeFacade
{
	/**
	 * 查找所有
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<DicType> listAllDicType() throws ServiceException;
	
    public void create(DicType obj) throws ServiceException;

    public DicType get(Long dicTypeOid) throws ServiceException;
    
    public void update(DicType obj) throws ServiceException;
    
    public String updateDicType(DicType obj, HttpServletRequest request);
    
    public void delete(String[] dicTypeOids) throws ServiceException;
    
    public String deleteDicType(String dicTypeOid);
	
	/**
	 * 参数总记录数
	 * 
	 * @param params
	 * @return int
	 * @throws ServiceException
	 */
//	public int countDicTypeByCondition(StringMap params) throws ServiceException;

	public List<DicType> findDicTypeListByCondition(TableTagBean ttb)throws ServiceException;
	/**
	 * find dictype by dictypeCode
	 * @param dicTypeCode
	 * @return
	 * @throws ServiceException
	 */
	public DicType getDicTypeByCode(String dicTypeCode) throws ServiceException;
	
	public DicItem getDicItemByCode(String dicItemCode,Long dicTypeOid) throws ServiceException;
	
	public List<DicItem> listSubItem(String dicTypeCode, String parentItem) throws ServiceException;

	public DicItem findDicItemUp(String dicTypeCode, String dicItemCode) throws ServiceException;

	public List<DicItem> findDicItemByName(String dicTypeCode,
			String dicItemName) throws ServiceException;


}