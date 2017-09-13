package com.yh.hr.orghc.ut.service;

import java.util.List;

import com.yh.hr.orghc.ut.dto.BizUtLeaderDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 领导职数信息Service
 * @author xiongyx
 *
 * 
 */
public interface BizLeaderInfoService {

	/**
	 * 新增
	 */
	public void create(BizUtLeaderDTO dto)throws ServiceException;

	/**
	 * 更新
	 */
	public void update(BizUtLeaderDTO dto) throws ServiceException;

	/**
	 * 根据ID删除
	 */	
	public void delete(Long utLeaderOid) throws ServiceException;
	
	/*
	 * 查询
	 */
	public BizUtLeaderDTO get(Long utLeaderOid) throws ServiceException;
	
	/**
	 * 列表
	 */	
	public List<BizUtLeaderDTO> list(Long utUnitOid) throws ServiceException;

}
