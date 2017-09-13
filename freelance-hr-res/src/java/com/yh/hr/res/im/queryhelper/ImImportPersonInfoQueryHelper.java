package com.yh.hr.res.im.queryhelper;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.im.dto.ImImportPersonInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DataConverUtils;
import com.yh.platform.core.util.DateUtil;

public class ImImportPersonInfoQueryHelper {

	/**
	 * 通过导入批次OID查询该批次的导入人员
	 * @param importBatchOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<ImImportPersonInfoDTO> findImImportPersonInfoDTOListByBatchOid(Long importBatchOid) throws ServiceException {
		String hql = "from ImImportPersonInfo ipi where ipi.importBatchOid=?";
		return BeanHelper.copyProperties(DaoUtil.find(hql, importBatchOid), ImImportPersonInfoDTO.class);
	}
	
	/**
	 * 通过数据库字段名和导入字典值查询导入人员信息
	 * @param importBatchOid
	 * @param databaseColumnCode
	 * @param importItemName
	 * @return
	 * @throws ServiceException
	 */
	public static List<ImImportPersonInfoDTO> findImImportPersonInfoDTOListByCodeAndName(Long importBatchOid, String databaseColumnCode, String importItemName) throws ServiceException {
		String sql = "SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS WHERE COLUMN_NAME = '"+databaseColumnCode+"' AND TABLE_NAME = 'YHC_IM_IMPORT_PERSON_INFO'";
		List<Object[]> list = DaoUtil.findWithSQL(sql);
		if(CollectionUtils.isEmpty(list)) {
			throw new ServiceException(null,"导入人员表"+databaseColumnCode+"字段不存在！"); 
		}
		String sql2 = "select ipi.NAME,ipi.BIRTHDAY from YHC_IM_IMPORT_PERSON_INFO ipi where ipi."+databaseColumnCode+"='"+importItemName+"' and ipi.IMPORT_BATCH_OID="+importBatchOid;
		List<Object[]> list2 = DaoUtil.findWithSQL(sql2);
		return BeanHelper.copyProperties(list2, new BeanHelper.PropertiesHandler<Object[],ImImportPersonInfoDTO>(){
			public ImImportPersonInfoDTO getValue(Object[] obj) throws ServiceException {
				ImImportPersonInfoDTO dto = new ImImportPersonInfoDTO();
				dto.setName(obj[0]==null?null:DataConverUtils.toString(obj[0]));
				dto.setBirthday(obj[1]==null?null:DataConverUtils.toString(obj[1]));
				return dto;
			}
		});
	}
	
	/**
	 * 通过数据库字段名查询该人员表中该字段的值
	 * @param importBatchOid
	 * @param name
	 * @param birthday
	 * @param databaseColumnCode
	 * @return
	 * @throws ServiceException
	 */
	public static Object getColumnValueByColumnCode(Long importBatchOid,String name, Date birthday, String databaseColumnCode) throws ServiceException {
		String sql = "SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS WHERE COLUMN_NAME = '"+databaseColumnCode+"' AND TABLE_NAME = 'YHC_IM_IMPORT_PERSON_INFO'";
		List<Object[]> list = DaoUtil.findWithSQL(sql);
		if(CollectionUtils.isEmpty(list)) {
			throw new ServiceException(null,"导入人员表"+databaseColumnCode+"字段不存在！"); 
		}
		String sql2 = "select ipi."+databaseColumnCode+" from YHC_IM_IMPORT_PERSON_INFO ipi " +
				"where ipi.name='"+name+"' and ipi.birthday='"+DateUtil.format(birthday, DateUtil.DATE_PATTERN_DEFAULT)+"' and ipi.IMPORT_BATCH_OID="+importBatchOid;
		List<Object> list2 = DaoUtil.findWithSQL(sql2);
		if(CollectionUtils.isNotEmpty(list2)) {
			return list2.get(0);
		}
		return null;
	}
}
