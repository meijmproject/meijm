package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtRevokeGoOutDTO;
import com.yh.platform.core.exception.ServiceException;
/**
 * 普通外出销假业务信息service
 * @author wangx
 * @date 2017-05-20
 * @version 1.0
 */
public interface PtRevokeGoOutService {

	/**
	 * 新增普通外出销假的业务信息
	 * @param ptRevokeGoOutDTO
	 * @throws ServiceException
	 * @return 
	 */
	public Long create(PtRevokeGoOutDTO ptRevokeGoOutDTO) throws ServiceException;
	
	/**
	 * 修改普通外出销假的业务信息
	 * @param ptRevokeGoOutDTO
	 * @throws ServiceException
	 */
	public void update(PtRevokeGoOutDTO ptRevokeGoOutDTO) throws ServiceException;

	/**
	 * 删除普通外出销假的业务信息
	 * @param ptRevokeGoOutOid
	 * @throws ServiceException
	 */
	public void delete(Long ptRevokeGoOutOid) throws ServiceException;
	
	/**
	 * 查询普通外出对应的普通外出销假信息
	 * @param ptGoOutOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtRevokeGoOutDTO> getPtRevokeGoOutDTOListByPbGoOutOid(Long ptGoOutOid) throws ServiceException;
	
	/**
	 * 根据主键获取普通外出销假信息
	 * @param ptRevokeGoOutOid
	 * @return
	 * @throws ServiceException
	 */
	public PtRevokeGoOutDTO get(Long ptRevokeGoOutOid) throws ServiceException;
}
