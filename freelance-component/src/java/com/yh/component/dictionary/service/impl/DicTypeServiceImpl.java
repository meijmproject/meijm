package com.yh.component.dictionary.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.yh.component.dictionary.queryhelper.DicItemQueryHelper;
import com.yh.component.dictionary.service.DicTypeService;
import com.yh.component.taglib.TableTagBean;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.yh.component.dictionary.bo.DicItem;
import com.yh.component.dictionary.bo.DicType;
import com.yh.component.dictionary.queryhelper.DicTypeQueryHelper;
import com.yh.platform.core.constant.Constant;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.util.StringMap;

public class DicTypeServiceImpl implements DicTypeService {

	/**
	 * 查找所有
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<DicType> listAllDicType() throws ServiceException {
		return DicTypeQueryHelper.list();
	}

	public void create(DicType obj) throws ServiceException {
		try {
			DicType dicType = DicTypeQueryHelper.getDicTypeByTypeCode(obj.getDicTypeCode());

			if (null == dicType)
				obj.save();
				
		} catch (DataAccessException e) {
			throw new DataAccessFailureException("create dictype faild", e);
		}
	}

	public DicType get(Long dicTypeOid) throws ServiceException {
		try {
			return DaoUtil.get(DicType.class,dicTypeOid);
		} catch (DataAccessException e) {
			throw new DataAccessFailureException("get dictype by oid faild", e);
		}
	}

	public void update(DicType obj) throws ServiceException {
		try {
			// dicType状态不可用 修改参数代码中的状态为不可用
			if (Constant.NO.equals(obj.getIsActive()))
				DicItemQueryHelper.updateDicItemIsActiveByTypeOid(obj.getDicTypeOid(), obj.getIsActive());

			obj.update();
		} catch (DataAccessException e) {
			throw new DataAccessFailureException("update dictype faild", e);
		}
	}

	public void delete(String[] dicTypeOids) throws ServiceException {
		try {
			if (null != dicTypeOids && dicTypeOids.length > 0) {
				for (String oid : dicTypeOids) {
					DicType dicType = DaoUtil.get(DicType.class,new Long(oid));
					// 删除 修改状态为不可用
					dicType.setIsActive(Constant.NO);
					dicType.update();

					// 根据typeCode 修改dicItem中的状态为不可用
					DicItemQueryHelper.updateDicItemIsActiveByTypeOid(dicType.getDicTypeOid(), dicType.getIsActive());
				}
			} else
				throw new ServiceException("error.object.notfound", "dic type oid is null ");
		} catch (DataAccessException e) {
			throw new DataAccessFailureException("delete dictype  faild", e);
		}
	}

	private void buildHQL(TableTagBean ttb, StringBuffer hql, HashMap<String, Object> hqlParams) {
		hql.append("from DicType dy where isActive = 'Y' ");
		StringMap params=ttb.getCondition();
		if (params.getAsStringEmptyNull("queryDicTypeName") != null) {
			hql.append(" and dy.dicTypeName like :queryDicTypeName");
			hqlParams.put("queryDicTypeName", "%" + params.getAsStringEmptyNull("queryDicTypeName").trim().toUpperCase() + "%");
		}
		if (params.getAsStringEmptyNull("dicTypeOid") != null) {
			hql.append(" and dy.dicTypeOid = :dicTypeOid");
			hqlParams.put("dicTypeOid", params.getAsLong("dicTypeOid"));
		}
		String orderby=ttb.getOrderBy();
		if(orderby!=null) {
			hql.append(" order by dy."+orderby);
		}else {
			hql.append(" order by dy.displayOrder");
		}
		if(ttb.getAsc()) {
			hql.append(" ASC");
		}else {
			hql.append(" DESC");
		}
	}

	public List<DicType> findDicTypeListByCondition(TableTagBean ttb) throws ServiceException {
		List<DicType> list = this.findDicTypeListByCondition(ttb,false);
		List<DicType> total= this.findDicTypeListByCondition(ttb,true);
		if(total!=null && total.size()>0) {
			ttb.setTotal(total.size());
		}
		return list;
	}
	/**
	 * 参数列表
	 * 
	 * @param params
	 * @param start
	 * @param size
	 * @param order
	 * @param asc
	 * @param total:false则列出所有记录
	 * @return List<BankBillingInfoDto>
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public List<DicType> findDicTypeListByCondition(final TableTagBean ttb, final boolean total) throws ServiceException {
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

	/**
	 * find dictype by dictypeCode
	 * @param dicTypeCode
	 * @return
	 * @throws ServiceException
	 */
	public DicType getDicTypeByCode(String dicTypeCode) throws ServiceException{
		 
		return DicTypeQueryHelper.getDicTypeByTypeCode(dicTypeCode);
	}

	public List<DicItem> listSubItem(String dicTypeCode, String parentItem) throws ServiceException
	{
		return DicItemQueryHelper.listSubItem(dicTypeCode,parentItem);
	}

	public DicItem getDicItemByCode(String dicItemCode,Long dicTypeOid) throws ServiceException {
		 return DicItemQueryHelper.getDicItemByTypeCode(dicItemCode,dicTypeOid);
	}

	public DicItem findDicItemUp(String dicTypeCode, String dicItemCode)
			throws ServiceException {
		return DicItemQueryHelper.findDicItemUp(dicTypeCode, dicItemCode);
	}

	public List<DicItem> findDicItemByName(String dicTypeCode,
			String dicItemName) throws ServiceException {
		return DicItemQueryHelper.findDicItemByName(dicTypeCode,dicItemName);
	}
}
