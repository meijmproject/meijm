package com.yh.hr.res.pb.service;

import java.util.Date;

import com.yh.hr.res.pb.dto.PbRevokeVacationDto;
import com.yh.platform.core.exception.ServiceException;

public interface PbRevokeService {

	/**
	 * 新增销假信息
	 * @param pbVacationDto
	 * @throws ServiceException
	 */
	public void create(PbRevokeVacationDto dto) throws ServiceException;

	/**
	 * 修改销假信息
	 * @param pbVacationDto
	 * @throws ServiceException
	 */
	public void update(PbRevokeVacationDto dto) throws ServiceException;

	/**
	 * 查询人员对应的销假信息
	 * @param vacationOid
	 * @return
	 * @throws ServiceException
	 */
	public PbRevokeVacationDto getPbRevokeVacationByVacationOid(Long vacationOid) throws ServiceException;

	/**
	 * 根据主键获取销假信息
	 * @param vacationOid
	 * @return
	 * @throws ServiceException
	 */
	public PbRevokeVacationDto get(Long revokeVacationOid) throws ServiceException;

	public PbRevokeVacationDto getPbRevokeVacation(Long vacationOid,
			Date startDate, Date endDate) throws ServiceException;
}
