package com.yh.component.dictionary.facade.impl;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import org.springframework.dao.DataAccessException;

import com.yh.component.dictionary.bo.DicItem;
import com.yh.component.dictionary.dto.DicItemDTO;
import com.yh.component.dictionary.facade.DicItemFacade;
import com.yh.component.dictionary.service.DicItemService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.JSONHelper;
import com.yh.platform.core.web.UserContext;

import net.sf.json.JSONArray;

public class DicItemFacadeImpl implements DicItemFacade
{
	private DicItemService dicItemService;

	public void setDicItemService(DicItemService dicItemService)
	{
		this.dicItemService = dicItemService;
	}

	/**
	 * 修改
	 * 
	 * @param dicItem
	 * @throws ServiceException
	 */
	public void update(DicItem dicItem) throws ServiceException
	{
		dicItemService.update(dicItem);
	}

	/**
	 * 查找所有
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<DicItem> listAllDicItem() throws ServiceException
	{
		return dicItemService.listAllDicItem();
	}

	/**
	 * 陈文斌修改
	 * 2004.11.20
	 * 根据 dicTypeOid 查找 dicItem list
	 * 
	 * @param dicTypeOid
	 * @return
	 * @throws ServiceException
	 */
	public List<DicItem> findDicItemListByTypeId(String dicTypeCode,boolean isInDelete) throws ServiceException
	{
		return dicItemService.findDicItemListByTypeId(dicTypeCode,isInDelete);
	}
	public List<DicItem> findDicItemListByTypeId(String dicTypeCode) throws ServiceException
	{
		return dicItemService.findDicItemListByTypeId(dicTypeCode);
	}
	public List<DicItem> findDicItemByTypeCodeAndFlag(Long dicTypeOid,String flag) throws ServiceException{
		return dicItemService.findDicItemByTypeCodeAndFlag(dicTypeOid,flag);
	}
	
	public void create(DicItem obj) throws ServiceException
	{
		dicItemService.create(obj);
	}

	public DicItem get(Long dicItemOid) throws ServiceException
	{
		return dicItemService.get(dicItemOid);
	}
 
	public void delete(String[] dicItemOids,UserContext uc) throws ServiceException
	{
		dicItemService.delete(dicItemOids,uc);
	}
	
	public DicItem getDicItemByTypeCode(String dicItemCode,Long dicTypeOid) throws ServiceException{
		return dicItemService.getDicItemByTypeCode(dicItemCode,dicTypeOid);
	}
	 /**
     * find dicitems by dictypeOid
     * @param dicTypeOid
     * @return
     * @throws ServiceException
     */
    public List<DicItem> getDicItemsByOid(Long dicTypeOid)throws ServiceException{
    	
    	return dicItemService.getDicItemsByOid(dicTypeOid);
    }

	public JSONArray getItems(Long dicTypeOid) throws ServiceException {

		List<DicItem> dicItems = this.getDicItemsByOid(Long.valueOf(dicTypeOid));
		JSONArray arr = new JSONArray();
		if (dicItems != null && !dicItems.isEmpty()) {

			for (DicItem dicitem : dicItems) {
				arr.element(JSONHelper.fromObject(dicitem, DateUtil.DATE_PATTERN_DEFAULT));
			}
			return arr;
		}
		return null;

	}
    
    /**
     * find dicitems by dictypeOid and parentCode
     * @param dicTypeOid parentCode
     * @return
     * @throws DataAccessException
     */
    public List<DicItem> getDicItemsByDicTypeOidAndParentCode(Long dicTypeOid,String parentCode)throws ServiceException
    {
    	return dicItemService.getDicItemsByDicTypeOidAndParentCode(dicTypeOid,parentCode);
    }

	public List<DicItemDTO> findDicItemByDicTypeCodeAndParentCode(String dicTypeCode, String parentCode) throws ServiceException {
		
		return dicItemService.findDicItemByDicTypeCodeAndParentCode(dicTypeCode,parentCode);
	}

	public DicItem createCustomDicItem(DicItemDTO dicItemDto) throws ServiceException {
		
		return dicItemService.createCustomDicItem(dicItemDto);
	}

	public void createTuneCustomCode(DicItemDTO dicItemDto) throws ServiceException {
		
		dicItemService.createTuneCustomCode(dicItemDto);
	}

	public List<DicItem> findDicItemByIsCasual(Long dicTypeOid) throws ServiceException {
		
		return dicItemService.findDicItemByIsCasual(dicTypeOid);
	}

	public void deleteCustomCode(DicItemDTO dicItemDto) throws ServiceException {
		dicItemService.deleteCustomCode(dicItemDto);
	}

	public void createchangeTuneCustomCode(DicItemDTO dicItemDto) throws ServiceException {
		dicItemService.createchangeTuneCustomCode(dicItemDto);
	}

	public void updateDicItemToCommon(DicItemDTO dto) throws ServiceException {
		
		dicItemService.updateDicItemToCommon(dto);
	}
	
	public void updateDicItemToOrder(DicItemDTO dto) throws ServiceException{
		
		dicItemService.updateDicItemToOrder(dto);
	}

	public void updateDicItemToCoalition(DicItemDTO dto)throws ServiceException {
		
		dicItemService.updateDicItemToCoalition(dto);
	}
	
	public void updateDicItemToName(DicItemDTO dto) throws ServiceException{
		dicItemService.updateDicItemToName(dto);
	}
	  /**
     * 根据dicTypeOid和dicItemCode查找DicItem对象
     * @param dicTypeOid
     * @param typeCode
     * @return
     * @throws ServiceException
     */
    public DicItem viewDicItemNameByDicTypeCodeandDicItemCode(String dicTypeCode, String typeCode) throws ServiceException{
    	return dicItemService.viewDicItemNameByDicTypeCodeandDicItemCode(dicTypeCode, typeCode);
    }
    /**
     * 根据dicTypeOid和itemName查找code
     */
    public List<DicItem> findCodeByDicItemName(String dicType, String itemName)throws ServiceException{
    	return dicItemService.findCodeByDicItemNameCustom(dicType, itemName);
  
    }

    /**
     * 根据dicTypeCode查找dicitem 字符模糊匹配
     */
	public List<DicItem> findBlurDicItemListByTypeId(String prefix, String dicTypeCode, String codeResource) throws ServiceException {
		return dicItemService.findBlurDicItemListByTypeId(prefix, dicTypeCode, codeResource);
	}
	
	public void createDicItem(DicItemDTO dto) throws ServiceException{
		dicItemService.createDicItem(dto);
	}
	
	public void deleteDicItem(DicItemDTO dto) throws ServiceException{
		dicItemService.deleteDicItem(dto);
	}
	
	public boolean findDicItemByDicTypeOidAndDicItemCode(Long dicTypeOid,String dicItemCode) throws ServiceException{
		return dicItemService.findDicItemByDicTypeOidAndDicItemCode(dicTypeOid,dicItemCode);
	}

	public DicItemDTO getIsLeaf(DicItemDTO di) throws ServiceException {
		return dicItemService.getIsLeaf(di);
	}

	public List<DicItem> findDicItemListByCondition(TableTagBean ttb) throws ServiceException {
		return dicItemService.findDicItemListByCondition(ttb);
	}

	public void deleteReal(String[] dicItemOids) throws ServiceException {
		dicItemService.deleteReal(dicItemOids);
	}
}
