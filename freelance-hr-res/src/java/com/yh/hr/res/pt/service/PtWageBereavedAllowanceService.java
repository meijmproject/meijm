package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtWageBereavedAllowanceDTO;
import com.yh.platform.core.exception.ServiceException;

public interface PtWageBereavedAllowanceService {
	/*
	 * 由信息主键查询人员遗属生活困难补助信息
	 * */
	public PtWageBereavedAllowanceDTO getBereavedAllowanceDTOByMainId(Long ptBereavedAllowanceInfoOid) throws ServiceException;
	/*
	 * 由人员ID查询人员遗属生活困难补助信息
	 * */
	public  List<PtWageBereavedAllowanceDTO> getPtBereavedAllowanceDTOById(Long bizPersonOid) throws ServiceException;
	
	/*
	 * 修改人员遗属生活困难补助信息
	 * */
	public  void updatePtWageBereavedAllowance(PtWageBereavedAllowanceDTO ptWageBereavedAllowanceDTO) throws ServiceException;


	/*
	 * 删除人员遗属生活困难补助信息
	 * */
	public void deletePtWageBereavedAllowance(Long bizPersonOid)throws ServiceException;
	public void deletePtWageBereavedAllowanceByMainId(Long ptBereavedAllowanceInfoOid)throws ServiceException;
	
	/*
	 * 新增
	 */
	public void addPtWageBereavedAllowance(PtWageBereavedAllowanceDTO ptWageBereavedAllowanceDTO)
			throws ServiceException;
	
	

	
}
