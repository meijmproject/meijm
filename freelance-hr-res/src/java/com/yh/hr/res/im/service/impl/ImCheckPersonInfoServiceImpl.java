package com.yh.hr.res.im.service.impl;

import java.util.List;

import com.yh.hr.res.im.bo.ImCheckPersonInfo;
import com.yh.hr.res.im.dto.ImCheckPersonInfoDTO;
import com.yh.hr.res.im.queryhelper.ImCheckPersonInfoQueryHelper;
import com.yh.hr.res.im.service.ImCheckPersonInfoService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 校核人员操作service实现类
 * @author wangx
 * @date 2017-07-11
 * @version 1.0
 */
public class ImCheckPersonInfoServiceImpl implements ImCheckPersonInfoService {

	/**
	 * 通过主键获取校核人员信息
	 * @param checkPersonInfoOid
	 * @return
	 * @throws ServiceException
	 */
	public ImCheckPersonInfoDTO get(Long checkPersonInfoOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(ImCheckPersonInfo.class, checkPersonInfoOid), ImCheckPersonInfoDTO.class);
	}
	
	/**
	 * 创建校核人员信息
	 * @param imCheckPersonInfoDTO
	 * @throws ServiceException
	 */
	public void create(ImCheckPersonInfoDTO imCheckPersonInfoDTO) throws ServiceException {
		if(imCheckPersonInfoDTO!=null) {
			ImCheckPersonInfo imCheckPersonInfo = new ImCheckPersonInfo();
			BeanHelper.copyProperties(imCheckPersonInfoDTO, imCheckPersonInfo);
			imCheckPersonInfo.save();
		}
	}
	
	/**
	 * 修改校核人员信息
	 * @param imCheckPersonInfoDTO
	 * @throws ServiceException
	 */
	public void update(ImCheckPersonInfoDTO imCheckPersonInfoDTO) throws ServiceException {
		if(imCheckPersonInfoDTO!=null) {
			ImCheckPersonInfo imCheckPersonInfo = DaoUtil.get(ImCheckPersonInfo.class, imCheckPersonInfoDTO.getCheckPersonInfoOid());
			if(imCheckPersonInfo!=null) {
				BeanHelper.copyProperties(imCheckPersonInfoDTO, imCheckPersonInfo, BeanHelper.getNullPropertyNames(imCheckPersonInfoDTO));
				imCheckPersonInfo.update();
			}
		}
	}
	
	/**
	 * 删除校核人员信息
	 * @param checkPersonInfoOid
	 * @throws ServiceException
	 */
	public void delete(Long checkPersonInfoOid) throws ServiceException {
		if(checkPersonInfoOid!=null) {
			ImCheckPersonInfo imCheckPersonInfo = DaoUtil.get(ImCheckPersonInfo.class, checkPersonInfoOid);
			if(imCheckPersonInfo!=null) {
				imCheckPersonInfo.delete();
			}
		}
	}
	
	/**
	 * 更新校核人员的字典项的值
	 * @param importBatchOid
	 * @param name
	 * @param birthday
	 * @param databaseColumnCode
	 * @param dicItemCode
	 * @throws ServiceException
	 */
	public void updateDicCode(Long importBatchOid, String name, String birthday, String databaseColumnCode, String dicItemCode) throws ServiceException {
		//检查传入的字段名在校核人员中是否存在
		ImCheckPersonInfoQueryHelper.checkColumnExist(databaseColumnCode);
		//获取该批次的校核人员信息
		ImCheckPersonInfoDTO personInfoDto = ImCheckPersonInfoQueryHelper.findImCheckPersonInfoDTOByBatchOidAndNameAndBirthday(importBatchOid, name, birthday);
		if(personInfoDto!=null) {
			//更新字典值
			DaoUtil.executeUpdateWithSql("UPDATE YHC_IM_CHECK_PERSON_INFO cpi set cpi."+databaseColumnCode+"='"+dicItemCode+"' WHERE cpi.CHECK_PERSON_INFO_OID="+personInfoDto.getCheckPersonInfoOid());
		}
	}
	
	/**
	 * 通过导入批次OID查询该批次的校核人员
	 * @param importBatchOid
	 * @return
	 * @throws ServiceException
	 */
	public List<ImCheckPersonInfoDTO> findImCheckPersonInfoDTOListByBatchOid(Long importBatchOid) throws ServiceException {
		return ImCheckPersonInfoQueryHelper.findImCheckPersonInfoDTOListByBatchOid(importBatchOid);
	}
	
	/**
	 * 通过姓名和出生日期查询当前批次的校核人员
	 * @param importBatchOid
	 * @param name
	 * @param birthday
	 * @return
	 * @throws ServiceException
	 */
	public ImCheckPersonInfoDTO findImCheckPersonInfoDTOByBatchOidAndNameAndBirthday(Long importBatchOid, String name, String birthday) throws ServiceException {
		return ImCheckPersonInfoQueryHelper.findImCheckPersonInfoDTOByBatchOidAndNameAndBirthday(importBatchOid, name, birthday);
	}
	
	/**
	 * 通过字段名获取该校核人员该字段的值
	 * @param checkPersonInfoOid
	 * @param databaseColumnCode
	 * @return
	 * @throws ServiceException
	 */
	public Object getColumnValueByColumnCode(Long checkPersonInfoOid, String databaseColumnCode) throws ServiceException {
		return ImCheckPersonInfoQueryHelper.getColumnValueByColumnCode(checkPersonInfoOid, databaseColumnCode);
	}
}
