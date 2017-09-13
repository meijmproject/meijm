package com.yh.hr.orghc.ut.service;

import java.util.List;

import com.yh.hr.orghc.ut.dto.BizUtOrgDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 内设机构信息Service
 * @author xiongyx
 *
 * 
 */
public interface BizOrgInfoService {

	/**
	 * 新增
	 */
	public void create(BizUtOrgDTO dto)throws ServiceException;

	/**
	 * 更新
	 */
	public void update(BizUtOrgDTO dto) throws ServiceException;

	/**
	 * 根据ID删除
	 */	
	public void delete(Long utOrgOid) throws ServiceException;
	
	/*
	 * 查询
	 */
	public BizUtOrgDTO get(Long utOrgOid) throws ServiceException;
	
	/**
	 * 列表
	 */	
	public List<BizUtOrgDTO> list(Long utUnitOid,String status) throws ServiceException;

}
