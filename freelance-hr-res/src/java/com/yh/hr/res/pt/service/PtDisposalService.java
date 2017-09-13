package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.bo.PtDisposal;
import com.yh.hr.res.pt.dto.PtDisposalDTO;
import com.yh.platform.core.exception.ServiceException;

public interface PtDisposalService {
	
	/**
	 * 得到免职信息列表
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtDisposalDTO> listPtDisposalDTO(Long bizPersonOid) throws ServiceException;
	/*
	 * 新增
	 */
	public  void createPtDisposal(PtDisposalDTO ptDisposalDto) throws ServiceException;
	/*
	 * 更新
	 */
	public  void updatePtDisposal(PtDisposalDTO ptDisposalDTO) throws ServiceException;
	
	/*
	 * 获取
	 */
	public  PtDisposal getDisposalByBizPersonId(Long bizPersonOid) throws ServiceException;
	
	public  PtDisposalDTO getDisposalDTOById(Long ptDisposalOid) throws ServiceException;
}