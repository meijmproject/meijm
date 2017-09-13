package com.yh.hr.component.wardset.service;

import java.util.List;
import java.util.Map;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.wardset.dto.CfWardDto;
import com.yh.platform.core.exception.ServiceException;

public interface WardSetService {

	/**
	 * 根据条件查询病区列表
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public List<CfWardDto> find(TableTagBean ttb) throws ServiceException;

	/**
	 * 创建病区记录
	 * @param cfWardDto
	 * @throws ServiceException
	 */
	public void create(CfWardDto cfWardDto) throws ServiceException;

	/**
	 * 获取病区记录
	 * @param wardOid
	 * @return
	 * @throws ServiceException
	 */
	public CfWardDto get(Long wardOid) throws ServiceException;

	/**
	 * 修改病区记录
	 * @param cfWardDto
	 * @throws ServiceException
	 */
	public void update(CfWardDto cfWardDto) throws ServiceException;

	/**
	 * 删除病区记录
	 * @param waedOid
	 * @throws ServiceException
	 */
	public void delete(Long waedOid) throws ServiceException;

	/**
	 * 获取《医院各病区卫技人员配备情况一览表.xlsx》所需数据
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String,String>> findWardCollection() throws ServiceException;

}
