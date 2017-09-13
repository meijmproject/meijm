package com.yh.hr.res.im.service;

import java.util.List;

import com.yh.hr.res.im.dto.ImCheckPersonInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 校核人员操作service接口
 * @author wangx
 * @date 2017-07-11
 * @version 1.0
 */
public interface ImCheckPersonInfoService {

	/**
	 * 通过主键获取校核人员信息
	 * @param checkPersonInfoOid
	 * @return
	 * @throws ServiceException
	 */
	public ImCheckPersonInfoDTO get(Long checkPersonInfoOid) throws ServiceException;
	
	/**
	 * 创建校核人员信息
	 * @param imCheckPersonInfoDTO
	 * @throws ServiceException
	 */
	public void create(ImCheckPersonInfoDTO imCheckPersonInfoDTO) throws ServiceException;
	
	/**
	 * 修改校核人员信息
	 * @param imCheckPersonInfoDTO
	 * @throws ServiceException
	 */
	public void update(ImCheckPersonInfoDTO imCheckPersonInfoDTO) throws ServiceException;
	
	/**
	 * 删除校核人员信息
	 * @param checkPersonInfoOid
	 * @throws ServiceException
	 */
	public void delete(Long checkPersonInfoOid) throws ServiceException;
	
	/**
	 * 更新校核人员的字典项的值
	 * @param importBatchOid
	 * @param name
	 * @param birthday
	 * @param databaseColumnCode
	 * @param dicItemCode
	 * @throws ServiceException
	 */
	public void updateDicCode(Long importBatchOid, String name, String birthday, String databaseColumnCode, String dicItemCode) throws ServiceException;
	
	/**
	 * 通过导入批次OID查询该批次的校核人员
	 * @param importBatchOid
	 * @return
	 * @throws ServiceException
	 */
	public List<ImCheckPersonInfoDTO> findImCheckPersonInfoDTOListByBatchOid(Long importBatchOid) throws ServiceException;
	
	/**
	 * 通过姓名和出生日期查询当前批次的校核人员
	 * @param importBatchOid
	 * @param name
	 * @param birthday
	 * @return
	 * @throws ServiceException
	 */
	public ImCheckPersonInfoDTO findImCheckPersonInfoDTOByBatchOidAndNameAndBirthday(Long importBatchOid, String name, String birthday) throws ServiceException;
	
	/**
	 * 通过字段名获取该校核人员该字段的值
	 * @param checkPersonInfoOid
	 * @param databaseColumnCode
	 * @return
	 * @throws ServiceException
	 */
	public Object getColumnValueByColumnCode(Long checkPersonInfoOid, String databaseColumnCode) throws ServiceException;
}
