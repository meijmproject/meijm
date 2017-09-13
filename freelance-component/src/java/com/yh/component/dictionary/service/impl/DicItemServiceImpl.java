package com.yh.component.dictionary.service.impl;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import com.yh.component.dictionary.bo.DicItem;
import com.yh.component.dictionary.dto.DicItemDTO;
import com.yh.component.dictionary.queryhelper.DicItemQueryHelper;
import com.yh.component.dictionary.queryhelper.DicTypeQueryHelper;
import com.yh.component.dictionary.service.DicItemService;
import com.yh.component.dictionary.utils.DicHelper;
import com.yh.component.taglib.TableTagBean;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.yh.component.dictionary.bo.DicType;
import com.yh.platform.core.constant.Constant;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.util.StringMap;
import com.yh.platform.core.web.UserContext;

public class DicItemServiceImpl implements DicItemService
{
	
	private Logger log = Logger.getLogger(DicItemServiceImpl.class);

	
	/**
	 * 修改
	 * 
	 * @param dicItem
	 * @throws ServiceException
	 */
	public void update(DicItem dicItem) throws ServiceException
	{
		try
		{
			dicItem.update();
		}
		catch (DataAccessException de)
		{
			throw new DataAccessFailureException(" update dicItem error !", de);
		}
	}

	/**
	 * 查找所有
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<DicItem> listAllDicItem() throws ServiceException
	{
		try
		{
			return DicItemQueryHelper.listAll();
		}
		catch (DataAccessException de)
		{
			throw new DataAccessFailureException(
					" find list all dicItem error !", de);
		}
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
	public List<DicItem> findDicItemListByTypeId(String dicTypeCode) throws ServiceException
	{
		try
		{
			return DicItemQueryHelper.findDicItemListByTypeId(dicTypeCode);
		}
		catch (DataAccessException de)
		{
			de.printStackTrace();
			throw new DataAccessFailureException(" find dic item list by dic type id error !", de);
		}		
	}
	public List<DicItem> findDicItemListByTypeId(String dicTypeCode,boolean isInDelete) throws ServiceException
	{
		try
		{
			return DicItemQueryHelper.findDicItemListByTypeId(dicTypeCode,isInDelete);
		}
		catch (DataAccessException de)
		{
			de.printStackTrace();
			throw new DataAccessFailureException(" find dic item list by dic type id error !", de);
		}		
	}
	
	public List<DicItem> findDicItemByTypeCodeAndFlag(Long dicTypeOid,String flag) throws ServiceException
	{
		try
		{
			return DicItemQueryHelper.findDicItemByTypeCodeAndFlag(dicTypeOid,flag);
		}
		catch (DataAccessException de)
		{
			throw new DataAccessFailureException(" find dic item list by dic type id error !", de);
		}		
	}
	public void create(DicItem obj) throws ServiceException
	{
		try
		{
			obj.save();
		}
		catch (DataAccessException e)
		{
			throw new DataAccessFailureException("create dicitem  faild", e);
		}
	}

	public DicItem get(Long dicItemOid) throws ServiceException
	{
		try
		{
			return DaoUtil.get(DicItem.class,dicItemOid);
		}
		catch (DataAccessException e)
		{
			throw new DataAccessFailureException("get dicitem by oid faild", e);
		}
	}
    
	public void delete(String[] dicItemOids,UserContext uc) throws ServiceException
	{
		try
		{
			if(null != dicItemOids && dicItemOids.length>0)
			{
				for(String oid:dicItemOids)
				{
					DicItem dicItem = DaoUtil.get(DicItem.class, Long.valueOf(oid));
					if(dicItem != null){
						dicItem.setIsActive(Constant.NO);
						dicItem.setLastModifierCode(uc.getUid());
						dicItem.setLastModifierName(uc.getDisplayName());
						dicItem.setLastModifyDate(new Date());
						
						dicItem.update();
					}
				}
			}
		}
		catch (DataAccessException e)
		{
			throw new DataAccessFailureException("delete dicitem  faild", e);
		}
	}

	public DicItem getDicItemByTypeCode(String dicItemCode, Long dicTypeOid) throws ServiceException {
		try
		{
			return DicItemQueryHelper.getDicItemByTypeCode(dicItemCode, dicTypeOid);
		}
		catch (DataAccessException e)
		{
			throw new DataAccessFailureException("get dicitem by oid faild", e);
		}
	}
	 /**
     * find dicitems by dictypeOid
     * @param dicTypeOid
     * @return
     * @throws ServiceException
     */
    public List<DicItem> getDicItemsByOid(Long dicTypeOid)throws ServiceException{
    	return DicItemQueryHelper.getDicItemsByOid(dicTypeOid);
    } 
    
    /**
     * find dicitems by dictypeOid and parentCode
     * @param dicTypeOid parentCode
     * @return
     * @throws DataAccessException
     */
    public List<DicItem> getDicItemsByDicTypeOidAndParentCode(Long dicTypeOid,String parentCode)throws ServiceException
    {
    	try
		{
			return DicItemQueryHelper.getDicItemsByDicTypeOidAndParentCode(dicTypeOid, parentCode);
		}
		catch (DataAccessException e)
		{
			throw new DataAccessFailureException("find dicitems by dictypeOid and parentCode faild", e);
		}
    }

	public List<DicItemDTO> findDicItemByDicTypeCodeAndParentCode(String dicTypeCode, String parentCode) throws ServiceException {
		try
		{
			return DicItemQueryHelper.findDicItemByDicTypeCodeAndParentCode(dicTypeCode, parentCode);
		}
		catch (DataAccessException e)
		{
			throw new DataAccessFailureException("find dicitems by dicTypeCode and parentCode faild", e);
		}
	}

	public DicItem createCustomDicItem(DicItemDTO dicItemDto) throws ServiceException {
		try{
			DicItem temp =null;		
			String dicTypeCode  = dicItemDto.getDicTypeCode();
			if(dicTypeCode != null && dicTypeCode.length() > 0){
				temp = new DicItem();				
				//得到dicTypeOid
				DicType dicType = DicTypeQueryHelper.getDicTypeByTypeCode(dicTypeCode);
				if(dicType != null){
					String itemCode = "";
					//得到itemCode并判断不存在
					for(;;){
						Calendar c = Calendar.getInstance();
						String s =  String.valueOf(c.getTimeInMillis());
						itemCode = s.substring(s.length()-10);
						boolean isExist = DicItemQueryHelper.findDicItemByDicItemCodeIsExist(dicType.getDicTypeOid(),itemCode);
						if(isExist){
							break;
						}						
					}
					temp.setCreatedByCode(dicItemDto.getCreatedByCode());
					temp.setCreatedByName(dicItemDto.getCreatedByName());
					temp.setCreatedDate(new Date());
					temp.setDicItemCode(itemCode);
					temp.setDicTypeOid(dicType.getDicTypeOid());
					temp.setDicItemName(dicItemDto.getDicItemName());
					temp.setViewName(dicItemDto.getViewName());
					temp.setIsActive(Constant.YES);
					temp.setIsGeneral(Constant.YES);
					temp.setCodeResource(DicHelper.DIC_LS);
					temp.setDisplayOrder(9999D);		
					temp.setRemark(dicItemDto.getRemark());
					temp.save();
					
					log.info("自定义创建代码成功!");
				}
			}
			return temp;
		}
		catch (DataAccessException e)
		{
			throw new DataAccessFailureException("create custom dicitems faild", e);
		}
	}

	@SuppressWarnings("unused")
	public void createTuneCustomCode(DicItemDTO dto) throws ServiceException {
		try{
			String[] dicItemOids = dto.getDicItemIdTemp();
			if(null != dicItemOids && dicItemOids.length > 0){
				Long dicItemOid = dto.getDicItemOid();
				//如果不为空，加到父节点
				if(null != dicItemOid){
					DicItem dicItem = DaoUtil.get(DicItem.class,dicItemOid);
					if(null != dicItem){
						//判断是操作专业还是学校 
						//DicType dicType = DaoUtil.get(DicType.class,dicItem.getDicTypeOid());
						String dicItemCode = dicItem.getDicItemCode();
						if(null != dicItemCode && dicItemCode.length() > 0){
							for(int i = 0;i < dicItemOids.length;i++){
								DicItem dicItemTemp = DaoUtil.get(DicItem.class,Long.valueOf(dicItemOids[i]));
								//加到父节点
								dicItemTemp.setParentCode(dicItemCode);
								dicItemTemp.setCodeResource(DicHelper.DIC_ZJ);
								dicItemTemp.update();
								String dicItemCodeTemp = dicItemTemp.getDicItemCode();
							}
						}
					}
				}
				//此代码会导致空指针，但不清楚这段逻辑是为了处理什么，暂时注释
				/*else{
					//为空，作为父节点
					DicItem dicItem = DaoUtil.get(DicItem.class,dicItemOid);
					if(null != dicItem){
						//判断是操作专业还是学校 
						//DicType dicType = DaoUtil.get(DicType.class,dicItem.getDicTypeOid());
						for(int i = 0;i < dicItemOids.length;i++){
							DicItem dicItemTemp = DaoUtil.get(DicItem.class,new Long(dicItemOids[i]));
//							dicItemTemp.setIsCasual(Constant.NO);
							dicItemTemp.setCodeResource(DicParameter.DIC_ZJ);
							dicItemTemp.update();
							String dicItemCodeTemp = dicItemTemp.getDicItemCode();
						}
					}
				}*/
			}
		}catch (DataAccessException e)
		{
			throw new DataAccessFailureException("create tuneCustomCode faild", e);
		}
		
	}

	public List<DicItem> findDicItemByIsCasual(Long dicTypeOid) throws ServiceException {
		try{
			return DicItemQueryHelper.findDicItemByIsCasual(dicTypeOid);
		}catch(DataAccessException e){
			throw new DataAccessFailureException("find dicItem by dicTypeOid isCasual faild", e);
		}
	}

	public void deleteCustomCode(DicItemDTO dicItemDto) throws ServiceException {
		try{
			String[] dicItemOids = dicItemDto.getDicItemIdTemp();
			if(null != dicItemOids && dicItemOids.length > 0){
				for(int i = 0;i < dicItemOids.length;i++){
					DicItem dicItem = DaoUtil.get(DicItem.class,new Long(dicItemOids[i]));
					dicItem.setIsActive(Constant.NO);
					dicItem.setLastModifierCode(dicItemDto.getLastModifierCode());
					dicItem.setLastModifierName(dicItemDto.getLastModifierName());
					dicItem.setLastModifyDate(new Date());
					dicItem.update();
				}
			}
		}catch (DataAccessException e)
		{
			throw new DataAccessFailureException("delete CustomCode faild", e);
		}
		
	}

	/**
	 * 删除代码库中的临时代码
	 * 同步更新引用临时代码
	 */
	@SuppressWarnings("unused")
	public void createchangeTuneCustomCode(DicItemDTO dicItemDto) throws ServiceException {
		try{
			String[] dicItemOids = dicItemDto.getDicItemIdTemp();
			if(null != dicItemOids && dicItemOids.length > 0){
				Long dicItemOid = dicItemDto.getDicItemOid();
				if(null != dicItemOid){
					//得到要替换的新代码
					DicItem dicItem = DaoUtil.get(DicItem.class,dicItemOid);
					for(int i = 0;i < dicItemOids.length;i++){
						DicType dicType = DaoUtil.get(DicType.class,dicItem.getDicTypeOid());
						//得到要替换的旧代码
						DicItem dicItemTemp = DaoUtil.get(DicItem.class,new Long(dicItemOids[i]));
						//删除代码库中的自定义代码
						//dicItemDao.remove(new Long(dicItemOids[i]));
						dicItemTemp.delete();
					}
				}
			}
		}catch (DataAccessException e)
		{
			throw new DataAccessFailureException("change CustomCode faild", e);
		}
	}

	public void updateDicItemToCommon(DicItemDTO dto) throws ServiceException {
		try{
			String[] dicItemOids = dto.getDicItemIdTemp();
			if(dicItemOids != null && dicItemOids.length > 0){
				for(int i = 0;i<dicItemOids.length ;i++){
					DicItem dicItem = DaoUtil.get(DicItem.class,new Long(dicItemOids[i]));
					dicItem.setIsGeneral(Constant.YES);
					dicItem.update();
				}
			}
		}catch (DataAccessException e)
		{
			throw new DataAccessFailureException("代码设置为常用失败", e);
		}
		
	}

	public void updateDicItemToOrder(DicItemDTO dto) throws ServiceException {
		try{
			DicItem dicItem = DaoUtil.get(DicItem.class,dto.getDicItemOid());
			if(dicItem != null){
				dicItem.setDisplayOrder(dto.getDisplayOrder());
				dicItem.setLastModifierCode(dto.getLastModifierCode());
				dicItem.setLastModifierName(dto.getLastModifierName());
				dicItem.setLastModifyDate(new Date());
				dicItem.update();
			}
		}catch (DataAccessException e)
		{
			throw new DataAccessFailureException("代码设置为排序失败", e);
		}
	}

	/**
	 * 合并
	 * 
	 */
	public void updateDicItemToCoalition(DicItemDTO dto) throws ServiceException {
		try{
			String dicItemCodes = dto.getOldDicItemCode();
			Long  dicTypeOid = dto.getDicTypeOid();
			//x先把要合并的代码的 isActive 置为N
			StringTokenizer token = new StringTokenizer(dicItemCodes,",");
			while(token.hasMoreTokens()){
				String oldCode = token.nextToken();
				DicItem dicItem = DicItemQueryHelper.getDicItemByTypeCode(oldCode, dicTypeOid);
				dicItem.setIsActive(Constant.NO);
				dicItem.setLastModifierCode(dto.getLastModifierCode());
				dicItem.setLastModifierName(dto.getLastModifierName());
				dicItem.setLastModifyDate(new Date());
				dicItem.update();
			}
			//再添加
			dto.setCreatedByCode(dto.getLastModifierCode());
			dto.setCreatedByName(dto.getCreatedByName());
			dto.setCreatedDate(new Date());
			dto.setOldDicItemCode(dto.getDicItemCodes());
			createDicItem(dto);
			
		}catch (DataAccessException e)
		{
			throw new DataAccessFailureException("代码合并失败", e);
		}
		
	}
	
	
	public void updateDicItemToName(DicItemDTO dto) throws ServiceException{
		try{
			DicItem dicItem = DicItemQueryHelper.getDicItemByTypeCode(dto.getDicItemCode(), dto.getDicTypeOid());
			if(dicItem != null){
				dicItem.setDicItemName(dto.getDicItemName());
				dicItem.setViewName(dto.getViewName());
				dicItem.setRemark(dto.getRemark());
				dicItem.setDisplayOrder(dto.getDisplayOrder());
				dicItem.setLastModifierCode(dto.getLastModifierCode());
				dicItem.setLastModifierName(dto.getLastModifierName());
				dicItem.setLastModifyDate(new Date());
				dicItem.update();
			}
		}catch (DataAccessException e)
		{
			throw new DataAccessFailureException("代码设置更名失败", e);
		}
	}
   public DicItem viewDicItemNameByDicTypeCodeandDicItemCode(String dicTypeCode, String typeCode) throws ServiceException{
	   try{
			DicItem dicItem = DicItemQueryHelper.viewDicItemNameByDicTypeCodeandDicItemCode(dicTypeCode, typeCode);
			if(dicItem != null){
				return dicItem;
			}
		}catch (DataAccessException e)
		{
			throw new DataAccessFailureException("寻找代码失败", e);
		} 
		return null;  
   }
   public String findCodeByDicItemName(String dicType, String itemName)throws ServiceException{
			return DicItemQueryHelper.findCodeByDicItemName(dicType, itemName);
   }

   /**
    * 根据dicTypeCode查找dicitem 字符模糊匹配
    */
   public List<DicItem> findBlurDicItemListByTypeId(String prefix, String dicTypeCode, String codeResource) throws ServiceException {
	   try{
		   return DicItemQueryHelper.findBlurDicItemListByTypeId(prefix, dicTypeCode, codeResource);
	   }catch(DataAccessException e){
		   throw new DataAccessFailureException("find blur dicItem by dicTypeOid faild", e);
	   }
   }

	public List<DicItem> findCodeByDicItemNameCustom(String dicType, String itemName) throws ServiceException
	{
		return DicItemQueryHelper.findCodeByDicItemNameCustom(dicType, itemName);
	}
	
	/**
	 * 新增
	 * @param dto
	 * @throws ServiceException
	 */
	public void createDicItem(DicItemDTO dto) throws ServiceException{
		try{
			DicItem dicItem = new DicItem();
			dicItem.setDicTypeOid(dto.getDicTypeOid());			
			dicItem.setDicItemCode(dto.getDicItemCode());
			dicItem.setDicItemName(dto.getDicItemName());
			dicItem.setViewName(dto.getViewName());
			dicItem.setDisplayOrder(dto.getDisplayOrder());
			dicItem.setParentCode(dto.getOldDicItemCode());
			dicItem.setCreatedDate(DateUtil.now());
			dicItem.setCreatedByCode(dto.getCreatedByCode());
			dicItem.setCreatedByName(dto.getCreatedByName());
			dicItem.setCodeResource(DicHelper.DIC_ZJ);
//			dicItem.setIsCasual(Constant.NO);
			dicItem.setIsActive(Constant.YES);
			dicItem.setIsGeneral(Constant.YES);
			dicItem.save();
		}catch(DataAccessException e){
			throw new DataAccessFailureException("create dicTypeOid faild", e);
		}
	}
	
	/**
	 * 删除
	 * @param dicTypeOid
	 * @param dicItemOid
	 * @throws ServiceException
	 */
	public void deleteDicItem(DicItemDTO dto) throws ServiceException{
		try{
			String dicItemCode = dto.getDicItemCode();
			Long dicTypeOid = dto.getDicTypeOid();
			DicItem dicItem = DicItemQueryHelper.getDicItemByTypeCode(dicItemCode, dicTypeOid);
			if(dicItem != null){
				dicItem.setIsActive(Constant.NO);
				dicItem.setLastModifierCode(dto.getLastModifierCode());
				dicItem.setLastModifierName(dto.getLastModifierName());
				dicItem.setLastModifyDate(dto.getLastModifyDate());
				dicItem.update();
			}
		}catch(DataAccessException e){
			throw new DataAccessFailureException("delete dicTypeOid faild", e);
		}
	}
	
	public void deleteDicItemFun(Long dicTypeOid,List<DicItem> dicItems) throws ServiceException{
		
		for(DicItem dicItem:dicItems){
			String dicItemCode = dicItem.getDicItemCode();
			List<DicItem> dis = DicItemQueryHelper.getDicItemsByDicTypeOidAndParentCode(dicTypeOid,dicItemCode);
			if(CollectionUtils.isEmpty(dis)){
				dicItems.add(DicItemQueryHelper.getDicItemByTypeCode(dicItemCode, dicTypeOid));
			}else{
				for(DicItem dics:dis){
					dicItems.add(dics);
				}
				deleteDicItemFun(dicTypeOid,dis);
			}
		}
		
	}
	
	 /**
     * 找记录
     * @param dicTypeOid
     * @param dicItemCode
     * @return
     * @throws ServiceException
     */
    public boolean findDicItemByDicTypeOidAndDicItemCode(Long dicTypeOid,String dicItemCode) throws ServiceException{
    	return DicItemQueryHelper.findDicItemByDicItemCodeIsExist(dicTypeOid,dicItemCode);
    }
	
    /*
     * (non-Javadoc)
     * @see gov.szghrs.base.service.DicItemService#getDicItemsByDicTypeCodeAndParentCode(java.lang.String, java.lang.String)
     */
//    public List<DicItem> getDicItemsByDicTypeCodeAndParentCode(String dicTypeCode, String parentCode) throws ServiceException
//    {
//    	return DicItemQueryHelper.getDicItemsByDicTypeCodeAndParentCode(dicTypeCode, parentCode);
//    }

	public DicItem findDicItemByTypeIdAndDicItemCode(String dicTypeCode,String dicItemCode) throws ServiceException 
	{
		try 
		{
    		return DicItemQueryHelper.findDicItemByTypeIdAndDicItemCode(dicTypeCode, dicItemCode);
		}catch (DataAccessException e)
		{
			throw new DataAccessFailureException("deleteDicItemFun faild", e);
		}
	}

	public DicItemDTO getIsLeaf(DicItemDTO di) throws ServiceException {
		return DicItemQueryHelper.getIsLeaf(di);
	}

	public List<DicItem> findDicItemListByCondition(TableTagBean ttb)
			throws ServiceException {
		List<DicItem> list = this.findDicItemListByCondition(ttb,false);
		List<DicItem> total= this.findDicItemListByCondition(ttb,true);
		if(total!=null && total.size()>0) {
			ttb.setTotal(total.size());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	private List<DicItem> findDicItemListByCondition(final TableTagBean ttb, final boolean total) {
		final StringBuffer hql = new StringBuffer();
		final HashMap<String, Object> hqlParams = new HashMap<String, Object>();

		buildHQL(ttb, hql, hqlParams);

		HibernateTemplate ht = SpringBeanUtil.getHibernateTemplate();
		return ht.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session sess) throws HibernateException, SQLException {
				Query query = sess.createQuery(hql.toString());
				if (!total) {
					query.setFirstResult(ttb.getPage());
					query.setMaxResults(ttb.getPageSize());
				}
				for (String name : hqlParams.keySet()) {
					Object value = hqlParams.get(name);
					query.setParameter(name, value);
				}
				return query.list();
			}
		});
	}

	private void buildHQL(TableTagBean ttb, StringBuffer hql,
			HashMap<String, Object> hqlParams) {
		hql.append("from DicItem di where 1=1 ");
		StringMap params=ttb.getCondition();
		if (params.getAsStringEmptyNull("dicTypeOid") != null) {
			hql.append(" and di.dicTypeOid = :dicTypeOid");
			hqlParams.put("dicTypeOid", params.getAsLong("dicTypeOid"));
		}
		if (params.getAsStringEmptyNull("dicItemName") != null) {
			hql.append(" and di.dicItemName like :dicItemName");
			hqlParams.put("dicItemName", "%" + params.getAsStringEmptyNull("dicItemName").trim().toUpperCase() + "%");
		}
		if (params.getAsStringEmptyNull("dicItemCode") != null) {
			hql.append(" and di.dicItemCode = :dicItemCode");
			hqlParams.put("dicItemCode", params.get("dicItemCode"));
		}
		String orderby=ttb.getOrderBy();
		if(orderby!=null) {
			hql.append(" order by di."+orderby);
		}else {
			hql.append(" order by di.displayOrder");
		}
		if(ttb.getAsc()) {
			hql.append(" ASC");
		}else {
			hql.append(" DESC");
		}
	}

	public void deleteReal(String[] dicItemOids) throws ServiceException {
		try
		{
			if(null != dicItemOids && dicItemOids.length>0)
			{
				for(String oid:dicItemOids)
				{
					DicItem dicItem = new DicItem();
					dicItem.setDicItemOid(Long.valueOf(oid));
					dicItem.delete();
				}
			}
		}
		catch (DataAccessException e)
		{
			throw new DataAccessFailureException("delete dicitem  faild", e);
		}
	}
}
