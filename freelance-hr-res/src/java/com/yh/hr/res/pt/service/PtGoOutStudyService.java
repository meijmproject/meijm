package com.yh.hr.res.pt.service;

import com.yh.hr.res.pt.dto.PtGoOutStudyDTO;
import com.yh.platform.core.exception.ServiceException;
/**
 * 外出进修业务信息service
 * @author wangx
 * @date 2017-05-20
 * @version 1.0
 */
public interface PtGoOutStudyService {
	
	/**
	 * 新增外出进修的业务信息
	 * @param ptGoOutStudyDTO
	 * @throws ServiceException
	 */
	public Long create(PtGoOutStudyDTO ptGoOutStudyDTO) throws ServiceException;
	
	/**
	 * 修改外出进修的业务信息
	 * @param ptGoOutStudyDTO
	 * @throws ServiceException
	 */
	public void update(PtGoOutStudyDTO ptGoOutStudyDTO) throws ServiceException;

	/**
	 * 删除外出进修的业务信息
	 * @param ptGoOutStudyOid
	 * @throws ServiceException
	 */
	public void delete(Long ptGoOutStudyOid) throws ServiceException;
	
	/**
	 * 查询人员对应的外出进修业务信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public PtGoOutStudyDTO getPtGoOutStudyDTOByBizPersonOid(Long bizPersonOid) throws ServiceException;
	
	/**
	 * 根据主键获取外出进修业务信息
	 * @param ptGoOutStudyOid
	 * @return
	 * @throws ServiceException
	 */
	public PtGoOutStudyDTO get(Long ptGoOutStudyOid) throws ServiceException;
}
