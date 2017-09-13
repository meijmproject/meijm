package com.yh.hr.component.im.check.service.integrity.impl;

import com.yh.hr.component.im.check.service.integrity.IntegrityCheckService;
import com.yh.hr.component.im.tablehandle.util.SqlDaoUtil;
import com.yh.platform.core.exception.ServiceException;

/**
 * 完整性检查接口
 * @author wangx
 * @date 2017-07-19
 * @version 1.0
 */
public class IntegrityCheckServiceImpl implements IntegrityCheckService {

	/**
	 * 检查
	 * @param importBatchOid 当前导入批次号
	 * @param databaseColumnCode 数据库字段代码
	 * @param templateCollName 模板采集项名称
	 * @throws ServiceException
	 */
	public void check(Long importBatchOid, String databaseColumnCode, String templateCollName) throws ServiceException {
		//先清空该导入批次所有人员该字段的完整性检查异常数据
		deleteUnusualLogs(importBatchOid, databaseColumnCode);
		//批量插入该导入批次所有人员该字段的完整性检查异常数据
		insertUnusualLogs(importBatchOid, databaseColumnCode, templateCollName);
	}
	
	/**
	 * 删除该导入批次所有人员该字段的完整性检查异常数据
	 * @param importBatchOid
	 * @param databaseColumnCode
	 * @throws ServiceException
	 */
	private void deleteUnusualLogs(Long importBatchOid, String databaseColumnCode) throws ServiceException {
		String delSql = "DELETE FROM yhc_im_check_person_unusual WHERE CHECK_TYPE = '6' AND DATABASE_COLUMN_CODE = '"+databaseColumnCode+"' AND IMPORT_BATCH_OID = "+ importBatchOid;
		SqlDaoUtil.executeSqlUpdate(delSql);
	}
	
	/**
	 * 批量插入该导入批次所有人员该字段的完整性检查异常数据
	 * @param importBatchOid
	 * @param databaseColumnCode
	 * @param databaseColumnCodeName
	 * @throws ServiceException
	 */
	private void insertUnusualLogs(Long importBatchOid, String databaseColumnCode, String templateCollName) throws ServiceException {
		String message = templateCollName+"为空";
		String insSql = "INSERT INTO yhc_im_check_person_unusual(CHECK_PERSON_INFO_OID,IMPORT_BATCH_OID,CHECK_TYPE,DATABASE_COLUMN_CODE,DATABASE_COLUMN_NAME,CHECK_STATUS,EFFECTIVE_FLAG,REMARK) " +
				"(SELECT icp.CHECK_PERSON_INFO_OID" +
				","+importBatchOid+
				",'6'" +
				",'" +databaseColumnCode+"'"+
				",'" +templateCollName+"'"+
				",'0'" +
				",'1'" +
				",'"+message+
				"' FROM yhc_im_check_person_info icp where icp."+databaseColumnCode+" is null)";
		SqlDaoUtil.executeSqlUpdate(insSql);
	}
}
