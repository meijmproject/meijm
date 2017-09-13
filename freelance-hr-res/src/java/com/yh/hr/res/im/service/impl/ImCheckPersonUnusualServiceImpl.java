package com.yh.hr.res.im.service.impl;

import java.util.List;

import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.im.bo.ImCheckPersonUnusual;
import com.yh.hr.res.im.dto.ImCheckPersonUnusualDTO;
import com.yh.hr.res.im.queryhelper.ImCheckPersonUnusualQueryHelper;
import com.yh.hr.res.im.service.ImCheckPersonUnusualService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 人员检查异常明细操作service实现类
 * @author wangx
 * @date 2017-07-11
 * @version 1.0
 */
public class ImCheckPersonUnusualServiceImpl implements ImCheckPersonUnusualService {

	/**
	 * 通过主键获取人员检查异常明细信息
	 * @param checkPersonUnusualOid
	 * @return
	 * @throws ServiceException
	 */
	public ImCheckPersonUnusualDTO get(Long checkPersonUnusualOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(ImCheckPersonUnusual.class, checkPersonUnusualOid), ImCheckPersonUnusualDTO.class);
	}
	
	/**
	 * 创建人员检查异常明细信息
	 * @param imCheckPersonUnusualDTO
	 * @throws ServiceException
	 */
	public void create(ImCheckPersonUnusualDTO imCheckPersonUnusualDTO) throws ServiceException {
		if(imCheckPersonUnusualDTO!=null) {
			ImCheckPersonUnusual imCheckPersonUnusual = new ImCheckPersonUnusual();
			BeanHelper.copyProperties(imCheckPersonUnusualDTO, imCheckPersonUnusual);
			imCheckPersonUnusual.setEffectiveFlag(DicConstants.YHRS0003_1);
			imCheckPersonUnusual.save();
		}
	}
	
	/**
	 * 修改人员检查异常明细信息
	 * @param imCheckPersonUnusualDTO
	 * @throws ServiceException
	 */
	public void update(ImCheckPersonUnusualDTO imCheckPersonUnusualDTO) throws ServiceException {
		if(imCheckPersonUnusualDTO!=null) {
			ImCheckPersonUnusual imCheckPersonUnusual = DaoUtil.get(ImCheckPersonUnusual.class, imCheckPersonUnusualDTO.getCheckPersonUnusualOid());
			if(imCheckPersonUnusual!=null) {
				BeanHelper.copyProperties(imCheckPersonUnusualDTO, imCheckPersonUnusual, BeanHelper.getNullPropertyNames(imCheckPersonUnusualDTO));
				imCheckPersonUnusual.update();
			}
		}
	}
	
	/**
	 * 删除人员检查异常明细信息
	 * @param checkPersonUnusualOid
	 * @throws ServiceException
	 */
	public void delete(Long checkPersonUnusualOid) throws ServiceException {
		if(checkPersonUnusualOid!=null) {
			ImCheckPersonUnusual imCheckPersonUnusual = DaoUtil.get(ImCheckPersonUnusual.class, checkPersonUnusualOid);
			if(imCheckPersonUnusual!=null) {
				imCheckPersonUnusual.delete();
			}
		}
	}
	
	/**
	 * 通过导入批次OID和校核人员OID查询该人员检查异常明细
	 * @param importBatchOid
	 * @param checkPersonInfoOid
	 * @return
	 * @throws ServiceException
	 */
	public List<ImCheckPersonUnusualDTO> findImCheckPersonUnusualByBatchOidAndPersonOid(Long importBatchOid, Long checkPersonInfoOid) throws ServiceException {
		return ImCheckPersonUnusualQueryHelper.findImCheckPersonUnusualByBatchOidAndPersonOid(importBatchOid, checkPersonInfoOid);
	}

	/**
	 * 通过导入批次OID、校核人员OID和检查类型查询该人员该检查类型的异常明细
	 * @param importBatchOid
	 * @param checkPersonInfoOid
	 * @param checkType
	 * @return
	 * @throws ServiceException
	 */
	public List<ImCheckPersonUnusualDTO> findImCheckPersonUnusualByBatchOidAndPersonOid(Long importBatchOid, Long checkPersonInfoOid, String checkType) throws ServiceException {
		return ImCheckPersonUnusualQueryHelper.findImCheckPersonUnusualByBatchOidAndPersonOid(importBatchOid, checkPersonInfoOid, checkType);
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
	public ImCheckPersonUnusualDTO findImCheckPersonUnusualByBatchOidAndPersonOid(Long importBatchOid, Long checkPersonInfoOid, String checkType, String databaseColumnCode) throws ServiceException {
		return ImCheckPersonUnusualQueryHelper.findImCheckPersonUnusualByBatchOidAndPersonOid(importBatchOid, checkPersonInfoOid, checkType, databaseColumnCode);
	}
	
	/**
	 * 通过导入批次OID和校核人员OID查询是否有未检查通过的异常明细
	 * @param importBatchOid
	 * @param checkPersonInfoOid
	 * @return
	 * @throws ServiceException
	 */
	public Boolean checkNoPassedDatas(Long importBatchOid, Long checkPersonInfoOid) throws ServiceException {
		return ImCheckPersonUnusualQueryHelper.checkNoPassedDatas(importBatchOid, checkPersonInfoOid);
	}
	
	/**
	 * 通过导入批次OID、校核人员OID和检查类型查询是否有该检查类型的未检查通过的异常明细
	 * @param importBatchOid
	 * @param checkPersonInfoOid
	 * @param checkType
	 * @return
	 * @throws ServiceException
	 */
	public Boolean checkNoPassedDatasByCheckType(Long importBatchOid, Long checkPersonInfoOid, String checkType) throws ServiceException {
		return ImCheckPersonUnusualQueryHelper.checkNoPassedDatasByCheckType(importBatchOid, checkPersonInfoOid, checkType);
	}
	
	/**
	 * 获取当次字典映射更新涉及到的校核人员OID
	 * @param importBatchOid
	 * @param databaseColumnCode
	 * @param importCollValue
	 * @return
	 * @throws ServiceException
	 */
	public List<Long> getDicNoPassedPersonOids(Long importBatchOid, String databaseColumnCode, String importCollValue) throws ServiceException {
		return ImCheckPersonUnusualQueryHelper.getDicNoPassedPersonOids(importBatchOid, databaseColumnCode, importCollValue);
	}
	
	/**
	 * 查询该批次该人员的关联性检查异常明细
	 * @param importBatchOid
	 * @param checkPersonInfoOid
	 * @return
	 * @throws ServiceException
	 */
	public ImCheckPersonUnusualDTO findRelationImCheckPersonUnusualByBatchOidAndPersonOid(Long importBatchOid, Long checkPersonInfoOid) throws ServiceException {
		return ImCheckPersonUnusualQueryHelper.findRelationImCheckPersonUnusualByBatchOidAndPersonOid(importBatchOid, checkPersonInfoOid);
	}
}
