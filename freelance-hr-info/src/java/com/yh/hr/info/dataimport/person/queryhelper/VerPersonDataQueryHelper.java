package com.yh.hr.info.dataimport.person.queryhelper;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.component.dictionary.bo.DicItem;
import com.yh.hr.component.im.tablehandle.util.SqlDaoUtil;
import com.yh.hr.res.im.bo.ImCheckDefinition;
import com.yh.hr.res.im.bo.ImCheckPersonInfo;
import com.yh.hr.res.im.bo.ImCollectTemplate;
import com.yh.hr.res.im.bo.ImDicItemMapping;
import com.yh.hr.res.pb.bo.PbPersonInfo;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringUtil;

public class VerPersonDataQueryHelper {
	/*
	 * 查询没检查通过的人员 1：通过	0：未通过
	 */
	public static List<ImCheckPersonInfo> listImCheckPersonInfos(String checkStatus) throws ServiceException {
		String hql = "from ImCheckPersonInfo ";
		return BeanHelper.copyProperties(DaoUtil.find(hql), ImCheckPersonInfo.class);
	}
	/*
	 * 需要进行字典检查的字段
	 */
	public static List<ImCollectTemplate> listCheckDicData(String isDicColumn) throws ServiceException {
		String hql = "from ImCollectTemplate where effectiveFlag='1' and templateCollName=importCollName and isDicColumn = ?";
		return BeanHelper.copyProperties(DaoUtil.find(hql,isDicColumn), ImCollectTemplate.class);
	}
	public static List<ImCheckDefinition> listCheckCompleteData(String checkType) throws  ServiceException {
		String hql = "from ImCheckDefinition where checkType = ? and effectiveFlag='1'";
		return BeanHelper.copyProperties(DaoUtil.find(hql,checkType), ImCheckDefinition.class);
	}
	public static List<ImCheckDefinition> listCheckData(String checkType) throws  ServiceException {
		String hql = "select ic from ImCheckDefinition ic,ImCollectTemplate icl where ic.databaseColumnCode = icl.databaseColumnCode and (icl.importCollName is not null and icl.importCollName <> '') and ic.checkType = ? and ic.effectiveFlag='1' and icl.effectiveFlag='1'";
		return BeanHelper.copyProperties(DaoUtil.find(hql,checkType), ImCheckDefinition.class);
	}
	public static void updateImPerson(String databaseColumnCode,String databaseColumnCodeName,String importItemName) throws  ServiceException{
		StringBuilder sql  = new StringBuilder();
		sql.append(" update yhc_im_check_person_info jic SET jic."+databaseColumnCode+" = ");
		sql.append(" (SELECT jidi.dic_item_code FROM yhc_im_dic_item_mapping jidi, yhc_im_dic_type_mapping jidt");
		sql.append(" WHERE jidi.DIC_TYPE_MAPPING_OID = jidt.DIC_TYPE_MAPPING_OID AND jidt.DATABASE_COLUMN_CODE = '"+databaseColumnCode+"'");
		sql.append(" AND jidi.EFFECTIVE_FLAG = '1' and jidt.EFFECTIVE_FLAG = '1'");
		sql.append(" AND jidi.IMPORT_ITEM_NAME = '"+importItemName+"')");
		sql.append(" ,jic."+databaseColumnCodeName+" = ");
		sql.append(" (SELECT jidi.dic_item_name FROM yhc_im_dic_item_mapping jidi, yhc_im_dic_type_mapping jidt");
		sql.append(" WHERE jidi.DIC_TYPE_MAPPING_OID = jidt.DIC_TYPE_MAPPING_OID AND jidt.DATABASE_COLUMN_CODE = '"+databaseColumnCode+"'");
		sql.append(" AND jidi.EFFECTIVE_FLAG = '1' and jidt.EFFECTIVE_FLAG = '1'");
		sql.append(" AND jidi.IMPORT_ITEM_NAME = '"+importItemName+"')");
		sql.append(" WHERE jic."+databaseColumnCodeName+"  = '"+importItemName+"'");
		SqlDaoUtil.executeSqlUpdate(sql.toString());
		
	}
	public static void updateImPersonBefore(String databaseColumnCode,String databaseColumnCodeName,String dicItemCode,String importItemName) throws  ServiceException{
		StringBuilder sql  = new StringBuilder();
		sql.append(" update yhc_im_check_person_info jic SET jic."+databaseColumnCode+" = '"+dicItemCode+"'");
		sql.append(" WHERE jic."+databaseColumnCodeName+"  = '"+importItemName+"'");
		SqlDaoUtil.executeSqlUpdate(sql.toString());
		
	}
	public static List<ImDicItemMapping> listCheckDicData() throws  ServiceException{
		String hql = "from ImDicItemMapping where effectiveFlag='1' and isCreateMapping=0";
		return BeanHelper.copyProperties(DaoUtil.find(hql), ImDicItemMapping.class);
	}
	public static DicItem  findDicItemByTypeIdAndDicItemName(String dicTypeCode,String importItemName) throws DataAccessFailureException {
		StringBuffer buf =new StringBuffer(" select di from DicItem  di where di.dicTypeOid = (select dicTypeOid from DicType dt where dt.dicTypeCode = '"+dicTypeCode+"') and di.dicItemName = '"+importItemName+"'");
		List<DicItem> list  = DaoUtil.find(buf.toString());
		if(CollectionUtils.isNotEmpty(list))
		{
			return list.get(0);
		}
		return null;
		
	}
	public static List<String> listColumnValueByName(String databaseColumnCodeName) throws DataAccessFailureException {
		StringBuffer buf =new StringBuffer(" select DISTINCT(jic."+databaseColumnCodeName+"),1 from yhc_im_check_person_info jic ");
		List<String> strList = new ArrayList<String>();
		List<Object[]> list  = DaoUtil.findWithSQL(buf.toString());
		if(CollectionUtils.isNotEmpty(list))
		{
			for(Object[] obj : list){
				String str = obj[0]==null?null:obj[0].toString();
				if(StringUtil.isNotBlank(str)){
					strList.add(str);
				}
			}
			return strList;
		}
		return null;
	}
	public static int countExceptionNum(String checkType) throws DataAccessFailureException{
		StringBuffer buf =new StringBuffer(" select DISTINCT(jicpu.check_person_info_oid) from yhc_im_check_person_unusual jicpu WHERE jicpu.check_status = '0'");
		if(StringUtil.isNotBlank(checkType)){
			buf.append(" and jicpu.check_type='"+checkType+"'");
		}
		List<Object[]> list  = DaoUtil.findWithSQL(buf.toString());
		if(CollectionUtils.isNotEmpty(list))
		{
			return list.size();
		}
		return 0;
	}
	public static int countExportPerson() throws ServiceException{
		String hql = "from PbPersonInfo ";
		List<PbPersonInfo> list = BeanHelper.copyProperties(DaoUtil.find(hql), PbPersonInfo.class);
		if(CollectionUtils.isNotEmpty(list)){
			return list.size();
		}
		return 0;
	}
}
