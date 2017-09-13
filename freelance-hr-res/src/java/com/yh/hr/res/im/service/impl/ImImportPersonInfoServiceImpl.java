package com.yh.hr.res.im.service.impl;

import java.util.Date;
import java.util.List;

import com.yh.hr.res.im.bo.ImImportPersonInfo;
import com.yh.hr.res.im.dto.ImImportPersonInfoDTO;
import com.yh.hr.res.im.queryhelper.ImImportPersonInfoQueryHelper;
import com.yh.hr.res.im.service.ImImportPersonInfoService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 导入人员操作service实现类
 * @author wangx
 * @date 2017-07-11
 * @version 1.0
 */
public class ImImportPersonInfoServiceImpl implements ImImportPersonInfoService {

	/**
	 * 通过主键获取导入人员信息
	 * @param checkPersonInfoOid
	 * @return
	 * @throws ServiceException
	 */
	public ImImportPersonInfoDTO get(Long checkPersonInfoOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(ImImportPersonInfo.class, checkPersonInfoOid), ImImportPersonInfoDTO.class);
	}
	
	/**
	 * 创建导入人员信息
	 * @param imImportPersonInfoDTO
	 * @throws ServiceException
	 */
	public void create(ImImportPersonInfoDTO imImportPersonInfoDTO) throws ServiceException {
		if(imImportPersonInfoDTO!=null) {
			ImImportPersonInfo imImportPersonInfo = new ImImportPersonInfo();
			BeanHelper.copyProperties(imImportPersonInfoDTO, imImportPersonInfo);
			imImportPersonInfo.save();
		}
	}
	
	/**
	 * 修改导入人员信息
	 * @param imImportPersonInfoDTO
	 * @throws ServiceException
	 */
	public void update(ImImportPersonInfoDTO imImportPersonInfoDTO) throws ServiceException {
		if(imImportPersonInfoDTO!=null) {
			ImImportPersonInfo imImportPersonInfo = DaoUtil.get(ImImportPersonInfo.class, imImportPersonInfoDTO.getImportPersonInfoOid());
			if(imImportPersonInfo!=null) {
				BeanHelper.copyProperties(imImportPersonInfoDTO, imImportPersonInfo, BeanHelper.getNullPropertyNames(imImportPersonInfoDTO));
				imImportPersonInfo.update();
			}
		}
	}
	
	/**
	 * 删除导入人员信息
	 * @param checkPersonInfoOid
	 * @throws ServiceException
	 */
	public void delete(Long checkPersonInfoOid) throws ServiceException {
		if(checkPersonInfoOid!=null) {
			ImImportPersonInfo imImportPersonInfo = DaoUtil.get(ImImportPersonInfo.class, checkPersonInfoOid);
			if(imImportPersonInfo!=null) {
				imImportPersonInfo.delete();
			}
		}
	}
	
	/**
	 * 通过导入批次OID查询该批次的导入人员
	 * @param importBatchOid
	 * @return
	 * @throws ServiceException
	 */
	public List<ImImportPersonInfoDTO> findImImportPersonInfoDTOListByBatchOid(Long importBatchOid) throws ServiceException {
		return ImImportPersonInfoQueryHelper.findImImportPersonInfoDTOListByBatchOid(importBatchOid);
	}
	
	/**
	 * 通过数据库字段名和导入字典值查询导入人员信息
	 * @param importBatchOid
	 * @param databaseColumnCode
	 * @param importItemName
	 * @return
	 * @throws ServiceException
	 */
	public List<ImImportPersonInfoDTO> findImImportPersonInfoDTOListByCodeAndName(Long importBatchOid, String databaseColumnCode, String importItemName) throws ServiceException {
		return ImImportPersonInfoQueryHelper.findImImportPersonInfoDTOListByCodeAndName(importBatchOid, databaseColumnCode, importItemName);
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
	public Object getColumnValueByColumnCode(Long importBatchOid,String name, Date birthday, String databaseColumnCode) throws ServiceException {
		return ImImportPersonInfoQueryHelper.getColumnValueByColumnCode(importBatchOid, name, birthday, databaseColumnCode);
	}
}
