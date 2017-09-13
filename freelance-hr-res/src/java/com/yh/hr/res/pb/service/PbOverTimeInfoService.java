package com.yh.hr.res.pb.service;

import java.util.Date;
import java.util.List;

import com.yh.hr.res.pb.dto.PbOverTimeInfoDTO;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;

public interface PbOverTimeInfoService {
	
	/**
	 * 新增人员外出的基础信息
	 * @param bizGoOutInfoDTO
	 * @throws ServiceException
	 */
	public void create(PbOverTimeInfoDTO pbOverTimeInfoDTO) throws ServiceException;
	
	/**
	 * 查询人员对应的加班信息
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PbOverTimeInfoDTO> list(Long personOid) throws ServiceException;
	
	/**
	 * 根据主键获取加班信息
	 * @param overtimeOid
	 * @return
	 * @throws ServiceException
	 */
	public PbOverTimeInfoDTO get(Long overtimeOid) throws ServiceException;
	
	/**
	 * 统计加班天数
	 * @param personOid
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws DataAccessFailureException
	 */
	public Float findOvertimeDaysByCon(Long personOid, Date startDate,
			Date endDate)throws ServiceException;
}
