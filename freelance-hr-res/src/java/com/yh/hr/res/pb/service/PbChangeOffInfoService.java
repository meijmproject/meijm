package com.yh.hr.res.pb.service;

import com.yh.hr.res.pb.dto.PbChangeOffInfoDTO;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;

import java.util.Date;
import java.util.List;

public interface PbChangeOffInfoService {

	/**
	 * 新增人员调休假基础信息
	 * @param pbChangeOffInfoDTO
	 * @throws com.jade.platform.core.exception.ServiceException
	 */
	public void create(PbChangeOffInfoDTO pbChangeOffInfoDTO) throws ServiceException;

	/**
	 * 查询人员对应的调休假信息
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PbChangeOffInfoDTO> list(Long personOid) throws ServiceException;

	/**
	 * 根据主键获取调休假信息
	 * @param changeOffOid
	 * @return
	 * @throws ServiceException
	 */
	public PbChangeOffInfoDTO get(Long changeOffOid) throws ServiceException;
	
	/**
	 * 统计调休天数
	 * @param personOid
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws DataAccessFailureException 
	 */
	public List<PbChangeOffInfoDTO> findChangeOffDaysByCon(Long personOid, Date startDate,
			Date endDate,Long parameterSetOid) throws ServiceException;
}
