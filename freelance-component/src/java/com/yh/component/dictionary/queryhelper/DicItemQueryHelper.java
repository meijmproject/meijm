package com.yh.component.dictionary.queryhelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.yh.component.dictionary.bo.DicItem;
import com.yh.component.dictionary.dto.DicItemDTO;
import com.yh.component.dictionary.utils.DicHelper;
import com.yh.platform.core.constant.Constant;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.util.DataConverUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.util.StringUtil;

public class DicItemQueryHelper 
{
	
	/**
	 * 查询所有的字典明细
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static List<DicItem> listAll() throws DataAccessFailureException
	{
		return DaoUtil.find("from DicItem");
	}
	
	/**
	 * 根据字典类型查询有效的字典明细
	 * @param dicTypeCode
	 * @return
	 * @throws DataAccessFailureException 
	 */
	public static List<DicItem> findDicItemListByTypeId(String dicTypeCode) throws DataAccessFailureException 
	{
		StringBuffer buf =new StringBuffer();
		buf.append("select di from DicItem di,DicType dt where dt.dicTypeOid=di.dicTypeOid and  dt.dicTypeCode='"+dicTypeCode+"'");
		buf.append(" and di.isActive ='"+Constant.YES+"'");
		buf.append(" order by dt.dicTypeOid asc, di.dicItemCode asc, di.displayOrder asc");
		return DaoUtil.find(buf.toString());
	}
	
	/**
	 * 根据字典类型和有效标识查询字典明细
	 * @param dicTypeCode
	 * @param isInDelete
	 * @return
	 * @throws DataAccessFailureException 
	 */
	public static List<DicItem> findDicItemListByTypeId(String dicTypeCode,boolean isInDelete) throws DataAccessFailureException
	{
		StringBuffer buf =new StringBuffer();
		buf.append("select di from DicItem di,DicType dt where dt.dicTypeOid=di.dicTypeOid and  dt.dicTypeCode='"+dicTypeCode+"'");
		if(!isInDelete) {
			buf.append(" and di.isActive ='"+Constant.YES+"'");
		}
		buf.append(" order by di.displayOrder asc");
		return DaoUtil.find(buf.toString());
	}

	public static List<DicItem> findDicItemByTypeCodeAndFlag(Long dicTypeOid,String flag) throws DataAccessFailureException 
	{
		StringBuffer buf =new StringBuffer();
		buf.append("from DicItem di where di.dicTypeOid="+dicTypeOid+" ");
		if(flag == null){
			buf.append("and di.isGeneral='"+Constant.YES+"' and di.isActive='"+Constant.YES+"' and di.disabledDate is null ");
		}
		if(("all").equals(flag)){
			buf.append(" and di.disabledDate is null and di.isActive='"+Constant.YES+"' ");
		}
		if(("disabledDate").equals(flag)){
			buf.append("and di.disabledDate is not null ");
		}
		if(("isActive").equals(flag)){
			buf.append("and di.isActive='"+Constant.NO+"' ");
		}
		buf.append("order by di.displayOrder,di.dicItemCode asc");
		return DaoUtil.find(buf.toString());
	}

	public static DicItem getDicItemByTypeCode(String dicItemCode,Long dicTypeOid) throws DataAccessFailureException 
	{
		List<DicItem> list = DaoUtil.findByNamed("from DicItem dt where dt.dicItemCode=:itemCode and dt.dicTypeOid=:dicTypeOid",new String []{"itemCode","dicTypeOid"},new Object[]{dicItemCode,dicTypeOid});

		if (list.isEmpty())
			return null;
		else
			return list.get(0);
	}

	public static List<DicItem> getDicItemsByOid(Long dicTypeOid) throws DataAccessFailureException 
	{
		StringBuffer buf =new StringBuffer();
    	buf.append("select di from DicItem di where di.dicTypeOid='"+dicTypeOid+"'");
    	buf.append(" order by di.displayOrder asc");
		return DaoUtil.find(buf.toString());
	}

	public static List<DicItem> getDicItemsByDicTypeOidAndParentCode(Long dicTypeOid, String parentCode) throws DataAccessFailureException
	{
		StringBuffer buf =new StringBuffer();
    	buf.append("select di from DicItem di where di.dicTypeOid="+dicTypeOid+" ");
    	if(parentCode == null || parentCode.length() <=0){
    		buf.append(" and di.parentCode is null");
    	}else{
    		buf.append(" and di.parentCode='"+parentCode+"' ");
    	}
    	buf.append(" order by di.displayOrder asc ");
		return DaoUtil.find(buf.toString());
	}

	public static List<DicItemDTO> findDicItemByDicTypeCodeAndParentCode(String dicTypeCode, String parentCode) throws DataAccessFailureException 
	{
		StringBuffer hql = new StringBuffer("select new com.yh.component.dictionary.dto.DicItemDto(di.dicItemOid,di.dicItemCode,di.dicItemName,di.parentCode,di.dicTypeOid) from DicType dt,DicItem di where dt.dicTypeOid=di.dicTypeOid and dt.dicTypeCode='"+dicTypeCode+"'");
		if(dicTypeCode ==  null)
			return null;
		if(null == parentCode || parentCode.equals("")){
			hql.append(" and di.parentCode is null");
		}else{
			hql.append(" and di.parentCode = '"+parentCode+"'");
		}
		hql.append(" order by di.displayOrder asc");
		List<DicItemDTO> ls = DaoUtil.find(hql.toString());
		if(null != ls && ls.size() > 0){
			for (DicItemDTO dicItemDto : ls) {
				getIsLeaf(dicItemDto);
			}
		}
		return ls;
	}
	
	/**
	 * 判断是否是最后子节点
	 * taoy
	 * 2008-1-23
	 * @param dicItemDto
	 * @return
	 */
	
	public static DicItemDTO getIsLeaf(DicItemDTO dicItemDto){
		final StringBuffer hql = new StringBuffer("select count(*) from DicItem di where di.dicTypeOid = "+
								dicItemDto.getDicTypeOid()+" and di.parentCode = '"+
									dicItemDto.getDicItemCode()+"'");
		HibernateTemplate ht = SpringBeanUtil.getHibernateTemplate();
		Object obj = ht.execute(new HibernateCallback() {
			public Object doInHibernate(Session sess) throws HibernateException, SQLException {
				Query query = sess.createQuery(hql.toString());
				return query.uniqueResult();
			}
		});
		int count =((Integer) obj).intValue();
		if(count == 0){
			dicItemDto.setIsleaf(true);
		}else{
			dicItemDto.setIsleaf(false);
		}
		return dicItemDto;
	}

	public static boolean findDicItemByDicItemCodeIsExist(Long dicTypeOid, String dicItemCode) throws DataAccessFailureException {
		
		if(null == dicTypeOid   || StringUtils.isEmpty(dicItemCode)){
			return false;
		}
		String hql = "from DicItem d where d.dicTypeOid="+dicTypeOid+" and d.dicItemCode='"+dicItemCode+"'";
		List<DicItem> list = DaoUtil.find(hql);
		if(list != null && !list.isEmpty()){
			return false;
		}else{
			return true;
		}
	}
	
	public static List<DicItem> findDicItemByIsCasual(Long dicTypeOid) throws DataAccessFailureException {
		if(null == dicTypeOid) return null;
		String hql = "from DicItem d where d.dicTypeOid="+dicTypeOid+" and d.codeResource='"+DicHelper.DIC_LS+"'";
		List<DicItem> list = DaoUtil.find(hql);
		if(list.size() > 0){
			return list;
		}
		return null;
	}
	
	public static DicItem viewDicItemNameByDicTypeCodeandDicItemCode(String dicTypeCode,String dicItemCode)throws DataAccessFailureException{
		if (dicTypeCode==null)
    		return null;
    	
    	StringBuffer buf =new StringBuffer();
		buf.append("select di from DicItem di,DicType dt where dt.dicTypeOid=di.dicTypeOid and dt.dicTypeCode='"+dicTypeCode+"'"+" and di.dicItemCode='"+dicItemCode+"'");
		buf.append(" order by di.displayOrder asc");
		List<DicItem> list = DaoUtil.find(buf.toString());
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
		
	}
	
	public static String findCodeByDicItemName(String dicType, String itemName)throws DataAccessFailureException
	{
		if (dicType == null || itemName==null)
			return null;
		
		List<DicItem> dicList=DaoUtil.findByNamed("select di from DicItem di, DicType dt where dt.dicTypeOid=di.dicTypeOid and dt.dicTypeCode='"+dicType+"' and di.dicItemName=:dicItemName","dicItemName",itemName);
		
		if (dicList!=null && dicList.size()==1)
			return dicList.get(0).getDicItemCode();
		
		return null;
	}
	
	public static List<DicItem> findBlurDicItemListByTypeId(String prefix, String dicTypeCode, String codeResource) throws DataAccessFailureException {
		StringBuffer buf =new StringBuffer();
		buf.append("select di from DicItem di,DicType dt where dt.dicTypeOid=di.dicTypeOid and di.dicItemName like '%" + prefix + "%' and dt.dicTypeCode='"+dicTypeCode+"'");
		if(null != codeResource){
			buf.append(" and di.codeResource not in (" + codeResource + ")");
		}
		buf.append(" order by di.displayOrder asc");
		return DaoUtil.find(buf.toString());
	}
	
	public static List<DicItem> findCodeByDicItemNameCustom(String dicType, String itemName)throws DataAccessFailureException
	{
		if (dicType == null || itemName==null)
			return null;
		
		return DaoUtil.findByNamed("select di from DicItem di, DicType dt where dt.dicTypeOid=di.dicTypeOid and dt.dicTypeCode='"+dicType+"' and di.dicItemName=:dicItemName","dicItemName",itemName);
		
	}
	
	public static List<DicItem> listSubItem(String dicTypeCode,String parentCode)throws DataAccessFailureException
    {
    	if (dicTypeCode==null)
    		return null;
    	
    	StringBuffer buf =new StringBuffer();
		buf.append("select di from DicItem di,DicType dt where dt.dicTypeOid=di.dicTypeOid and dt.dicTypeCode='"+dicTypeCode+"'");
		if (parentCode!=null&&!("").equals(parentCode))
			buf.append(" and di.parentCode='"+parentCode+"'");
		buf.append(" order by di.displayOrder asc");
		return DaoUtil.find(buf.toString());
    }
	
	public static DicItem findDicItemByTypeIdAndDicItemCode(String dicTypeCode,String dicItemCode) throws DataAccessFailureException 
	{
		StringBuffer buf =new StringBuffer(" select di from DicItem  di where di.dicTypeOid = (select dicTypeOid from DicType dt where dt.dicTypeCode = '"+dicTypeCode+"') and di.dicItemCode = '"+dicItemCode+"'");
		List<DicItem> list  = DaoUtil.find(buf.toString());
		if(CollectionUtils.isNotEmpty(list))
		{
			DicItem dicItem = list.get(0);
			return dicItem;
		}
		return null;
	}
	
	/**
	 * 根据DicTypeOid 更新DicItem可用状态
	 * 
	 * @param typeOid
	 * @return
	 * @throws DataAccessException
	 */
	public static int updateDicItemIsActiveByTypeOid(Long dicTypeOid, String isActive) throws DataAccessFailureException
	{
		return DaoUtil.executeUpdate("update DicItem di set di.isActive="+isActive+" where di.dicTypeOid="+dicTypeOid);
	}
	
//	public static List<DicItem> listSubItem(String dicTypeCode, String parentItem)throws DataAccessFailureException
//	{
//		return DaoUtil.find("select di from DicItem di, DicType dt where dt.dicTypeOid=di.dicTypeOid and dt.dicTypeCode='"+dicTypeCode+"' and di.parentCode='"+parentItem+"' order by di.displayOrder");
//	}
	public static DicItem getDicItemByItemCode(String dicItemCode) throws DataAccessFailureException {
		List<DicItem> list = DaoUtil.findByNamed("from DicItem dt where dt.dicItemCode=:itemCode", "itemCode", dicItemCode);

		if (list.isEmpty())
			return null;
		else
			return list.get(0);
	}
    public static DicItem findDicItemUp (String dicTypeCode ,String dicItemCode) throws DataAccessFailureException {
    	StringBuffer buf =new StringBuffer();
		buf.append("select jdi1.* from yha_dic_type jdt,yha_dic_item jdi1 left join yha_dic_item jdi2 on jdi1.dic_item_code=jdi2.parent_code where jdi1.dic_type_oid =jdt.dic_type_oid");
		buf.append(" and jdi2.dic_item_code= '"+dicItemCode+"' and jdt.dic_type_code = '" + dicTypeCode+"'");
		List<Object[]> list = DaoUtil.findWithSQL(buf.toString());
		DicItem dicItem = new DicItem();
		if(list != null && list.size()>0){
			Object[] obj= list.get(0);
			dicItem.setDicItemOid(DataConverUtils.toLong(obj[0]));
			dicItem.setDicItemCode(DataConverUtils.toString(obj[1]));
			dicItem.setDicItemName(DataConverUtils.toString(obj[2]));
			dicItem.setViewName(DataConverUtils.toString(obj[3]));
			return dicItem;
		}
		return null;
    	
    }

	public static List<DicItem> findDicItemByName(String dicTypeCode,
			String dicItemName) throws DataAccessFailureException {
		StringBuffer buf =new StringBuffer();
		buf.append("select jdi.* from yha_dic_item jdi,yha_dic_type jdt where jdi.dic_type_oid = jdt.dic_type_oid");
		buf.append(" and jdi.dic_item_name like '"+StringUtil.wrapPercent(dicItemName)+"' and jdt.dic_type_code = '"+dicTypeCode+"'");
		List<Object[]> list = DaoUtil.findWithSQL(buf.toString());
		List<DicItem> dicItemList = new ArrayList<DicItem>();
		if(list != null && list.size()>0){
			for(Object[] obj : list){
				DicItem dicItem = new DicItem();
				dicItem.setDicItemOid(DataConverUtils.toLong(obj[0]));
				dicItem.setDicItemCode(DataConverUtils.toString(obj[1]));
				dicItem.setDicItemName(DataConverUtils.toString(obj[2]));
				dicItem.setViewName(DataConverUtils.toString(obj[3]));
				dicItemList.add(dicItem);
			}
			return dicItemList;
		}
		return null;
	}
	
	public static String viewDicItemNameByDicTypeCodeAndDicItemCode(String dicTypeCode,String dicItemCode) throws DataAccessFailureException{
		if (dicTypeCode==null||dicItemCode==null)
    		return null;
    	
    	StringBuffer buf =new StringBuffer();
		buf.append("select di from DicItem di,DicType dt where dt.dicTypeOid=di.dicTypeOid and dt.dicTypeCode='"+dicTypeCode+"'"+" and di.dicItemCode='"+dicItemCode+"'");
		buf.append(" order by di.displayOrder asc");
		List<DicItem> list = DaoUtil.find(buf.toString());
		if(list != null && list.size()>0){
			return list.get(0).getDicItemName();
		}
		return null;
		
	}
}
