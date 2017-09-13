package com.yh.hr.res.pb.service;

import java.util.Date;
import java.util.List;

import com.yh.hr.res.pb.dto.PbRevokeGoOutStudyDTO;
import com.yh.platform.core.exception.ServiceException;
/**
 * 外出进修销假基础信息service
 * @author wangx
 * @date 2017-05-20
 * @version 1.0
 */
public interface PbRevokeGoOutStudyService {

	/**
	 * 新增外出进修销假的基础信息
	 * @param pbRevokeGoOutStudyDTO
	 * @throws ServiceException
	 * @return 
	 */
	public Long create(PbRevokeGoOutStudyDTO pbRevokeGoOutStudyDTO) throws ServiceException;
	
	/**
	 * 修改外出进修销假的基础信息
	 * @param pbRevokeGoOutStudyDTO
	 * @throws ServiceException
	 */
	public void update(PbRevokeGoOutStudyDTO pbRevokeGoOutStudyDTO) throws ServiceException;

	/**
	 * 删除外出进修销假的基础信息
	 * @param pbRevokeGoOutStudyOid
	 * @throws ServiceException
	 */
	public void delete(Long pbRevokeGoOutStudyOid) throws ServiceException;
	
	/**
	 * 查询外出进修对应的外出进修销假信息
	 * @param pbGoOutStudyOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PbRevokeGoOutStudyDTO> getPbRevokeGoOutStudyDTOListByPbGoOutStudyOid(Long pbGoOutStudyOid) throws ServiceException;
	
	/**
	 * 根据主键获取外出进修销假信息
	 * @param pbRevokeGoOutStudyOid
	 * @return
	 * @throws ServiceException
	 */
	public PbRevokeGoOutStudyDTO get(Long pbRevokeGoOutStudyOid) throws ServiceException;

	public List<PbRevokeGoOutStudyDTO> getPbRevokeGoOutStudyDTOList(
			Long pbGoOutStudyOid, Date startDate, Date endDate ) throws ServiceException;
}
