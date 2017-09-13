package com.yh.hr.res.sao.orghc.service;

import java.util.List;
import com.yh.hr.res.sao.orghc.dto.SaoUbHcDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 编制信息查询、维护接口
 * @author	lenghh
 * @version	1.0,	16/09/05
 */

public interface SaoUbHcService {

	/**
	 * 获取编制下达基本信息
	 * @param hcOid
	 * @return SaoUbHcDTO
     * @throws ServiceException 
     * @author lenghh
	 */
	public SaoUbHcDTO getSaoUbHcDTO(Long hcOid) throws ServiceException;
	/**
	 * 根据单位获取编制下达列表
	 * @param unitOid
	 * @return List<SaoUbHcDTO>
     * @throws ServiceException 
     * @author lenghh
	 */
	public List<SaoUbHcDTO> listSaoUbHcDTOByUnitOid(Long unitOid) throws ServiceException;

	/**
	 * 更新编制信息
	 * @param saoUbHcDTO
	 * @return
	 * @throws ServiceException
	 */
	public void updateSaoUbHcDTO(SaoUbHcDTO saoUbHcDTO) throws ServiceException; 
	
	/**
	 * 增加编制信息
	 * @param saoUbHcDTO
	 * @return
	 * @throws ServiceException
	 */
	public void addSaoUbHcDTO(SaoUbHcDTO saoUbHcDTO) throws ServiceException;
	
	/**
	 * 删除编制信息
	 * @param saoUbHcDTO
	 * @return
	 * @throws ServiceException
	 */
	public void deleteSaoUbHcDTO(Long hcOid) throws ServiceException;
	
	/**
	 * 根据编制OID统计编制核定数
	 * @param unitOid
	 * @param hcCode
	 * @return num
	 * @throws ServiceException
	 */
	public int countHcByHcCode(Long unitOid,String hcCode) throws ServiceException;
}
