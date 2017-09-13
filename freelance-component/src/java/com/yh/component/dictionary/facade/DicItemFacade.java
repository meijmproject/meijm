package com.yh.component.dictionary.facade;

import java.util.List;

import com.yh.component.dictionary.bo.DicItem;
import com.yh.component.dictionary.dto.DicItemDTO;
import com.yh.component.taglib.TableTagBean;
import org.springframework.dao.DataAccessException;

import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.web.UserContext;

import net.sf.json.JSONArray;

/**
 * 该类处理与参数代码相关的请求
 * 
 * @author Chen.Wenbin
 */
public interface DicItemFacade
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
	 * @param dicTypeCode
	 * @return
	 * @throws ServiceException
	 */
	public List<DicItem> findDicItemListByTypeId(String dicTypeCode) throws ServiceException;
	/**
	 * 
	 * @param dicTypeCode
	 * @param isInDelete为true是包含删除的代码
	 * @return
	 * @throws ServiceException
	 */
	public List<DicItem> findDicItemListByTypeId(String dicTypeCode,boolean isInDelete) throws ServiceException;
	
	/**
	 * 根据dicTypeCode查找dicitem 字符模糊匹配
	 * @param prefix 匹配字符
	 * @param dicTypeCode
	 * @param codeResource  排除的代码来源(not in)
	 * @return List<DicItem>
	 * @throws ServiceExcepton
	 */
	public List<DicItem> findBlurDicItemListByTypeId(String prefix, String dicTypeCode, String codeResource) throws ServiceException;
	
	/**
	 * 根据 dicTypeCode 
	 * flag is null 列出全部代码
	 * flag is disabledDate and disabledDate not null 列出失效代码
	 * flag is isGeneral and  isGeneral is Y 列出常用代码
	 * flag is isActive and isActive is N 列出删除代码
	 * @param dicTypeOid
	 * @return
	 * @throws ServiceException
	 */
	public List<DicItem> findDicItemByTypeCodeAndFlag(Long dicTypeOid,String flag) throws ServiceException;
	
    public void create(DicItem obj) throws ServiceException;

    public DicItem get(Long dicItemOid) throws ServiceException;
    
    public void delete(String[] dicItemOids,UserContext uc) throws ServiceException;
    
    /**
     * 根据dicTypeCode从DicItem找是否有dicItemCode记录
     * @param dicItemCode
     * @param dicTypeCode
     * @return
     * @throws ServiceException
     */
    public DicItem getDicItemByTypeCode(String dicItemCode,Long dicTypeOid) throws ServiceException;
    /**
     * find dicitems by dictypeOid
     * @param dicTypeOid
     * @return
     * @throws ServiceException
     */
    public List<DicItem> getDicItemsByOid(Long dicTypeOid)throws ServiceException;
    
    public JSONArray getItems(Long dicTypeOid)throws ServiceException;
    
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
    public DicItem createCustomDicItem(DicItemDTO dicItemDto) throws ServiceException;
    
    /**
     * 调整临时代码
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
    public void updateDicItemToCommon(DicItemDTO dto) throws ServiceException;
    
    /**
     * 修改排序
     * @param dto
     * @throws ServiceException
     */
    public void updateDicItemToOrder(DicItemDTO dto) throws ServiceException;
    
    /**
     * 合并
     */
    public void updateDicItemToCoalition(DicItemDTO dto) throws ServiceException;
    
    /**
     *  更名 
     * @param dto
     * @throws ServiceException
     */
    public void updateDicItemToName(DicItemDTO dto) throws ServiceException;
    /**
     * 根据dicTypeOid和dicItemCode查找DicItem对象
     * @param dicTypeOid
     * @param typeCode
     * @return
     * @throws ServiceException
     */
    public DicItem viewDicItemNameByDicTypeCodeandDicItemCode(String dicTypeCode, String typeCode) throws ServiceException;
    
    public List<DicItem> findCodeByDicItemName(String dicType, String itemName)throws ServiceException;
    
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
