package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtPersonInDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * @desc 人员转任信息业务逻辑层
 * @author xiongyx
 * @createDate 2016-10-09
 */
public interface PtPersonInService {
	
	/**
	 * 得到转任信息列表
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtPersonInDTO> listPtPersonInDTO(Long bizPersonOid) throws ServiceException;
	/*
	 * 新增
	 */
	public  void createPtPersonIn(PtPersonInDTO ptPersonInDto) throws ServiceException;
	/*
	 * 更新
	 */
	public  void updatePtPersonIn(PtPersonInDTO ptPersonInDTO) throws ServiceException;
	
	/*
	 * 获取
	 */
	public  PtPersonInDTO getPersonInDTOById(Long personOid) throws ServiceException;
}