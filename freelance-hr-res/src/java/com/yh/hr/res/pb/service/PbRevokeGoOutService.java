package com.yh.hr.res.pb.service;

import java.util.Date;
import java.util.List;

import com.yh.hr.res.pb.dto.PbRevokeGoOutDTO;
import com.yh.platform.core.exception.ServiceException;
/**
 * 普通外出销假基础信息service
 * @author wangx
 * @date 2017-05-20
 * @version 1.0
 */
public interface PbRevokeGoOutService {

	/**
	 * 新增普通外出销假的基础信息
	 * @param pbRevokeGoOutDTO
	 * @throws ServiceException
	 * @return 
	 */
	public Long create(PbRevokeGoOutDTO pbRevokeGoOutDTO) throws ServiceException;
	
	/**
	 * 修改普通外出销假的基础信息
	 * @param pbRevokeGoOutDTO
	 * @throws ServiceException
	 */
	public void update(PbRevokeGoOutDTO pbRevokeGoOutDTO) throws ServiceException;

	/**
	 * 删除普通外出销假的基础信息
	 * @param pbRevokeGoOutOid
	 * @throws ServiceException
	 */
	public void delete(Long pbRevokeGoOutOid) throws ServiceException;
	
	/**
	 * 查询普通外出对应的普通外出销假信息
	 * @param pbGoOutOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PbRevokeGoOutDTO> getPbRevokeGoOutDTOListByPbGoOutOid(Long pbGoOutOid) throws ServiceException;
	
	/**
	 * 根据主键获取普通外出销假信息
	 * @param pbRevokeGoOutOid
	 * @return
	 * @throws ServiceException
	 */
	public PbRevokeGoOutDTO get(Long pbRevokeGoOutOid) throws ServiceException;

	public List<PbRevokeGoOutDTO> getPbRevokeGoOutDTOList(Long goOutOid,
			Date startDate, Date endDate) throws ServiceException;
}
