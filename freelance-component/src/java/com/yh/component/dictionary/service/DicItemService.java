package com.yh.component.dictionary.service;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import org.springframework.dao.DataAccessException;

import com.yh.component.dictionary.bo.DicItem;
import com.yh.component.dictionary.dto.DicItemDTO;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.web.UserContext;

public interface DicItemService
{
	/**
	 * 修改
	 * 
	 * @param dicItem
	 * @throws ServiceException
	 */
	public void update(DicItem dicItem) throws ServiceException;

	/**
	 * 查找所有
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<DicItem> listAllDicItem() throws ServiceException;
	
	/**
	 * 陈文斌修改
	 * 2004.11.20
	 * 根据 dicTypeOid 查找 dicItem list
	 * 
	 * @param dicTypeOid
	 * @return
	 * @throws ServiceException
	 */
	public List<DicItem> findDicItemListByTypeId(String dicTypeCode) throws ServiceException;
	/**
	 * 
	 * @param dicTypeCode
	 * @param isInDelete如果为真则包含删除的代码
	 * @return
	 * @throws ServiceException
	 */
	public List<DicItem> findDicItemListByTypeId(String dicTypeCode,boolean isInDelete) throws ServiceException;
	
	public List<DicItem> findDicItemByTypeCodeAndFlag(Long dicTypeOid,String flag) throws ServiceException;
	
    public void create(DicItem obj) throws ServiceException;

    public DicItem get(Long dicItemOid) throws ServiceException;
    
    public void delete(String[] dicItemOids,UserContext uc) throws ServiceException;
    
    public DicItem getDicItemByTypeCode(String dicItemCode,Long dicTypeOid) throws ServiceException;
	 /**
     * find dicitems by dictypeOid
     * @param dicTypeOid
     * @return
     * @throws ServiceException
     */
    public List<DicItem> getDicItemsByOid(Long dicTypeOid)throws ServiceException;
    
    /**
     * find dicitems by dictypeOid and parentCode
     * @param dicTypeOid parentCode
     * @return
     * @throws DataAccessException
     */
    public List<DicItem> getDicItemsByDicTypeOidAndParentCode(Long dicTypeOid,String parentCode)throws ServiceException;
    
    /**
     * 根据dicTypeCode和parentCode找记录
     * @param dicTypeCode
     * @param parentCode
     * @return
     * @throws ServiceException
     */
    public List<DicItemDTO> findDicItemByDicTypeCodeAndParentCode(String dicTypeCode, String parentCode) throws ServiceException;
    
    /**
     * 自定义代码
     * @param dicItem
     * @throws ServiceException
     */
    public DicItem createCustomDicItem(DicItemDTO dicItemDto) throws ServiceException ;
    
    /**
     *  调整临时代码
     * @param dicItemDto
     * @throws ServiceException
     */
    public void createTuneCustomCode(DicItemDTO dicItemDto) throws ServiceException;
    
    /**
     * 替换临时代码
     * @param dicItemDto
     * @throws ServiceException
     */
    public void createchangeTuneCustomCode(DicItemDTO dicItemDto) throws ServiceException;
    
    /**
     * 删除调整的临时代码
     * @param dicItemDto
     * @throws ServiceException
     */
    public void deleteCustomCode(DicItemDTO dicItemDto) throws ServiceException;
    
    
    /**
     * 得到dicTypeOid的自定义代码 
     * @param dicTypeOid
     * @return
     * @throws ServiceException
     */
    public List<DicItem> findDicItemByIsCasual(Long dicTypeOid) throws ServiceException;
    
    /**
     * 代码设置为常用
     * @param dto
     * @throws ServiceException
     */
    public void updateDicItemToCommon(DicItemDTO dto) throws ServiceException ;
    
    /**
     * 修改排序
     * @param dto
     * @throws ServiceException
     */
    public void updateDicItemToOrder(DicItemDTO dto) throws ServiceException;
    
    /**
     * 合并
     * @param dto
     * @throws ServiceException
     */
    public void updateDicItemToCoalition(DicItemDTO dto)throws ServiceException;
    
    public void updateDicItemToName(DicItemDTO dto) throws ServiceException;
    
    public DicItem viewDicItemNameByDicTypeCodeandDicItemCode(String dicTypeCode, String typeCode) throws ServiceException;
    
    public String findCodeByDicItemName(String dicType, String itemName)throws ServiceException;
    
    /**
	 * 根据dicTypeCode查找dicitem 字符模糊匹配
	 * @param prefix 匹配字符
	 * @param dicTypeCode
	 * @param codeResource	排除的代码来源(not in)
	 * @return List<DicItem>
	 * @throws ServiceExcepton
	 */
	public List<DicItem> findBlurDicItemListByTypeId(String prefix, String dicTypeCode, String codeResource) throws ServiceException;

	public List<DicItem> findCodeByDicItemNameCustom(String dicType, String itemName)throws ServiceException;
	
	  /**
     * 新增
     * @param dto
     * @throws ServiceException
     */
    public void createDicItem(DicItemDTO dto) throws ServiceException;
    
    /**
     * 删除
     * @param dicTypeOid
     * @param dicItemOid
     * @throws ServiceException
     */
    public void deleteDicItem(DicItemDTO dto) throws ServiceException;
    
    /**
     * 找记录
     * @param dicTypeOid
     * @param dicItemCode
     * @return
     * @throws ServiceException
     */
    public boolean findDicItemByDicTypeOidAndDicItemCode(Long dicTypeOid,String dicItemCode) throws ServiceException;
    
    /**
     * 通过dicTypeCode和parentCode查找dicitems
     * @param dicTypeCode
     * @param parentCode
     * @return
     * @throws ServiceException
     */
//    public List<DicItem> getDicItemsByDicTypeCodeAndParentCode(String dicTypeCode, String parentCode) throws ServiceException;

	public DicItem findDicItemByTypeIdAndDicItemCode(String dicTypeCode,String dicItemCode)throws ServiceException;

	public DicItemDTO getIsLeaf(DicItemDTO di) throws ServiceException;

	/**
	 * 查找字典列表
	 * @param ttb
	 * @return
	 */
	public List<DicItem> findDicItemListByCondition(TableTagBean ttb) throws ServiceException;

	/**
	 * 从数据库物理删除
	 * @param dicItemOids
	 */
	public void deleteReal(String[] dicItemOids) throws ServiceException;
}
