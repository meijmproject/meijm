package com.yh.hr.res.cf.service;

import java.util.List;
import java.util.Map;

import com.yh.hr.res.cf.dto.JhcCfShowResultOrderDTO;
import com.yh.platform.core.exception.ServiceException;

public interface JhcCfShowResultOrderService {

	/**
	 * 查找用户保存的排序
	 * @param userId
	 * @param functionCode
	 * @return
	 * @throws ServiceException
	 */
	List<Map<String, String>> find(String userId, String functionCode) throws ServiceException;

	/**
	 * 根据用户和functionCode删除
	 * @param dto
	 * @param functionCode
	 * @throws ServiceException
	 */
	void delete(String userId, String functionCode) throws ServiceException;

	/**
	 * 新增记录
	 * @param dto
	 * @throws ServiceException
	 */
	void save(JhcCfShowResultOrderDTO dto) throws ServiceException;

}
