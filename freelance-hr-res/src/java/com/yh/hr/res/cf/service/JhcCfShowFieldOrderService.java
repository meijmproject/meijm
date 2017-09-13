package com.yh.hr.res.cf.service;

import java.util.List;
import java.util.Map;

import com.yh.hr.res.cf.dto.JhcCfShowFieldOrderDTO;
import com.yh.platform.core.exception.ServiceException;

public interface JhcCfShowFieldOrderService {

	/**
	 * 查找用户设置的表格列
	 * @param userId
	 * @param functionCode 
	 * @return
	 */
	List<Map<String,String>> find(String userId, String functionCode) throws ServiceException;

	/**
	 * 保存
	 * @param dto
	 */
	void save(JhcCfShowFieldOrderDTO dto) throws ServiceException;

	/**
	 * 删除
	 * @param dto
	 */
	void delete(JhcCfShowFieldOrderDTO dto) throws ServiceException;

}
