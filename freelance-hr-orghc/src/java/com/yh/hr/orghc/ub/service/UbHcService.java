package com.yh.hr.orghc.ub.service;

import java.util.List;
import com.yh.hr.orghc.ub.dto.UbHcDTO;
import com.yh.platform.core.exception.ServiceException;

public interface UbHcService {

	/**
	 * 获取编制信息
	 * @param hcOid
	 * @return UbHc
     * @throws ServiceException 
	 */
	public UbHcDTO getUbHcDTOById(Long hcOid) throws ServiceException;
	
	/**
	 * 根据单位OID获取编制列表
	 * @param unitOid
	 * @return List<UbHcDTO>
     * @throws ServiceException 
	 */
	public List<UbHcDTO> listByUnitOid(Long unitOid) throws ServiceException;
	
	/**
	 * 新增编制
	 * @param ubOrgDTO
     * @throws ServiceException 
	 */
	public void createHcInfo(UbHcDTO ubHcDTO) throws ServiceException;

	/**
	 * 新增编制--单位信息校核
	 * @param ubOrgDTO
	 * @param chgCount
     * @throws ServiceException 
	 */
	public void createHcInfo(UbHcDTO ubHcDTO, Long chgCount) throws ServiceException;

	/**
	 * 修改编制
	 * @param ubOrgDTO
     * @throws ServiceException 
	 */
	public void updateHcInfo(UbHcDTO ubHcDTO) throws ServiceException;

	/**
	 * 修改编制--单位信息校核
	 * @param ubOrgDTO
	 * @param chgCount
     * @throws ServiceException 
	 */
	public void updateHcInfo(UbHcDTO ubHcDTO, Long chgCount) throws ServiceException;

	/**
	 * 删除编制
	 * @param orgOid
     * @throws ServiceException 
	 */
	public void deleteHcInfo(Long hcOid) throws ServiceException;

	/**
	 * 根据编制OID统计编制核定数
	 * @param unitOid
	 * @param hcCode
	 * @return num
	 * @throws ServiceException
	 */
	public int countHcByHcCode(Long unitOid,String hcCode) throws ServiceException;
}
