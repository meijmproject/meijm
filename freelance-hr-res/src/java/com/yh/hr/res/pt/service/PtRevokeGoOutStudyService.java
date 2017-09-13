package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtRevokeGoOutStudyDTO;
import com.yh.platform.core.exception.ServiceException;
/**
 * 外出进修销假业务信息service
 * @author wangx
 * @date 2017-05-20
 * @version 1.0
 */
public interface PtRevokeGoOutStudyService {

	/**
	 * 新增外出进修销假的业务信息
	 * @param ptRevokeGoOutStudyDTO
	 * @throws ServiceException
	 * @return 
	 */
	public Long create(PtRevokeGoOutStudyDTO ptRevokeGoOutStudyDTO) throws ServiceException;
	
	/**
	 * 修改外出进修销假的业务信息
	 * @param ptRevokeGoOutStudyDTO
	 * @throws ServiceException
	 */
	public void update(PtRevokeGoOutStudyDTO ptRevokeGoOutStudyDTO) throws ServiceException;

	/**
	 * 删除外出进修销假的业务信息
	 * @param ptRevokeGoOutStudyOid
	 * @throws ServiceException
	 */
	public void delete(Long ptRevokeGoOutStudyOid) throws ServiceException;
	
	/**
	 * 查询外出进修对应的外出进修销假信息
	 * @param ptGoOutStudyOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtRevokeGoOutStudyDTO> getPtRevokeGoOutStudyDTOListByPbGoOutStudyOid(Long ptGoOutStudyOid) throws ServiceException;
	
	/**
	 * 根据主键获取外出进修销假信息
	 * @param ptRevokeGoOutStudyOid
	 * @return
	 * @throws ServiceException
	 */
	public PtRevokeGoOutStudyDTO get(Long ptRevokeGoOutStudyOid) throws ServiceException;

	/**
	 * 获取外出进修销假信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public PtRevokeGoOutStudyDTO getPtGoOutCancelDTOByBizPersonOid(Long bizPersonOid) throws ServiceException;
}
