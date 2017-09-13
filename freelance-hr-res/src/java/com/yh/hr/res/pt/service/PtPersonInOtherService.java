package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtPersonInOtherDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * @desc 人员其他信息业务逻辑层
 * @author lihj
 * @createDate 2016-10-26
 */
public interface PtPersonInOtherService {
	
	public List<PtPersonInOtherDTO> listPtPersonInOtherDTO(Long bizPersonOid) throws ServiceException;
	/*
	 * 新增
	 */
	public  void createPtPersonInOther(PtPersonInOtherDTO ptPersonInOtherDto) throws ServiceException;
	/*
	 * 更新
	 */
	public  void updatePtPersonInOther(PtPersonInOtherDTO ptPersonInOtherDto) throws ServiceException;
	
	/*
	 * 获取
	 */
	public  PtPersonInOtherDTO getPersonInOtherDTOById(Long personInOtherOid) throws ServiceException;
	
	/**
	 * 得到列表
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtPersonInOtherDTO> listPtPersonInOtherInfoByBizPersonOid(Long bizPersonOid) throws ServiceException;
}