package com.yh.hr.res.im.queryhelper;


import java.util.List;

import com.yh.hr.res.im.bo.ImCheckPersonUnusual;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.im.dto.ImCheckPersonUnusualDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

public class ImCheckPersonUnusualQueryHelper {

	/**
	 * 通过导入批次OID和校核人员OID查询该人员检查异常明细
	 * @param importBatchOid
	 * @param checkPersonInfoOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<ImCheckPersonUnusualDTO> findImCheckPersonUnusualByBatchOidAndPersonOid(Long importBatchOid, Long checkPersonInfoOid) throws ServiceException {
		String hql = "from ImCheckPersonUnusual cpu where cpu.effectiveFlag = '1' and cpu.importBatchOid=? and cpu.checkPersonInfoOid=?";
		return BeanHelper.copyProperties(DaoUtil.find(hql, importBatchOid, checkPersonInfoOid), ImCheckPersonUnusualDTO.class);
	}

	/**
	 * 通过导入批次OID、校核人员OID和检查类型查询该人员该检查类型的异常明细
	 * @param importBatchOid
	 * @param checkPersonInfoOid
	 * @param checkType
	 * @return
	 * @throws ServiceException
	 */
	public static List<ImCheckPersonUnusualDTO> findImCheckPersonUnusualByBatchOidAndPersonOid(Long importBatchOid, Long checkPersonInfoOid, String checkType) throws ServiceException {
		String hql = "from ImCheckPersonUnusual cpu where cpu.effectiveFlag = '1' and cpu.importBatchOid=? and cpu.checkPersonInfoOid=? and cpu.checkType=?";
		return BeanHelper.copyProperties(DaoUtil.find(hql, importBatchOid, checkPersonInfoOid, checkType), ImCheckPersonUnusualDTO.class);
	}

	/**
	 * 通过导入批次OID、校核人员OID、检查类型和字段名查询该人员该检查类型该字段的异常明细
	 * @param importBatchOid
	 * @param checkPersonInfoOid
	 * @param checkType
	 * @param databaseColumnCode
	 * @return
	 * @throws ServiceException
	 */
	public static ImCheckPersonUnusualDTO findImCheckPersonUnusualByBatchOidAndPersonOid(Long importBatchOid, Long checkPersonInfoOid, String checkType, String databaseColumnCode) throws ServiceException {
		String hql = "from ImCheckPersonUnusual cpu where cpu.effectiveFlag = '1' and cpu.importBatchOid=? and cpu.checkPersonInfoOid=? and cpu.checkType=? and cpu.databaseColumnCode=?";
		return BeanHelper.copyProperties(DaoUtil.uniqueResult(hql, importBatchOid, checkPersonInfoOid, checkType, databaseColumnCode), ImCheckPersonUnusualDTO.class);
	}

	/**
	 * 查询该批次该人员的关联性检查异常明细
	 * @param importBatchOid
	 * @param checkPersonInfoOid
	 * @return
	 * @throws ServiceException
	 */
	public static ImCheckPersonUnusualDTO findRelationImCheckPersonUnusualByBatchOidAndPersonOid(Long importBatchOid, Long checkPersonInfoOid) throws ServiceException {
		String hql = "from ImCheckPersonUnusual cpu where cpu.effectiveFlag = '1' and cpu.importBatchOid=? and cpu.checkPersonInfoOid=? and cpu.checkType='5'";
		return BeanHelper.copyProperties(DaoUtil.uniqueResult(hql, importBatchOid, checkPersonInfoOid), ImCheckPersonUnusualDTO.class);
	}

	/**
	 * 通过导入批次OID和校核人员OID查询是否有未检查通过的异常明细
	 * @param importBatchOid
	 * @param checkPersonInfoOid
	 * @return
	 * @throws ServiceException
	 */
	public static Boolean checkNoPassedDatas(Long importBatchOid, Long checkPersonInfoOid) throws ServiceException {
		String hql = "from ImCheckPersonUnusual cpu where cpu.effectiveFlag = '1' and cpu.checkStatus = '0' and cpu.importBatchOid=? and cpu.checkPersonInfoOid=?";
		List<ImCheckPersonUnusual> list = DaoUtil.find(hql, importBatchOid, checkPersonInfoOid);
		if(CollectionUtils.isNotEmpty(list)) {
			return true;
			
		}
		return false;
	}

	/**
	 * 通过导入批次OID、校核人员OID和检查类型查询是否有该检查类型的未检查通过的异常明细
	 * @param importBatchOid
	 * @param checkPersonInfoOid
	 * @param checkType
	 * @return
	 * @throws ServiceException
	 */
	public static Boolean checkNoPassedDatasByCheckType(Long importBatchOid, Long checkPersonInfoOid, String checkType) throws ServiceException {
		String hql = "from ImCheckPersonUnusual cpu where cpu.effectiveFlag = '1' and cpu.checkStatus = '0' and cpu.importBatchOid=? and cpu.checkPersonInfoOid=? and cpu.checkType=?";
		List<ImCheckPersonUnusual> list = DaoUtil.find(hql, importBatchOid, checkPersonInfoOid, checkType);
		if(CollectionUtils.isNotEmpty(list)) {
			return true;
			
		}
		return false;
	}
	
	/**
	 * 获取当次字典映射更新涉及到的校核人员OID
	 * @param importBatchOid
	 * @param databaseColumnCode
	 * @param importCollValue
	 * @return
	 * @throws ServiceException
	 */
	public static List<Long> getDicNoPassedPersonOids(Long importBatchOid, String databaseColumnCode, String importCollValue) throws ServiceException {
		String hql = "select distinct cpu.checkPersonInfoOid from ImCheckPersonUnusual cpu where cpu.effectiveFlag = '1' and cpu.checkStatus = '0' and cpu.importBatchOid=? and cpu.databaseColumnCode=? and cpu.databaseColumnValue=?";
		return DaoUtil.find(hql, importBatchOid, databaseColumnCode, importCollValue); 
	}
}
