package com.yh.admin.sao.person;

import java.util.List;

import com.yh.admin.sao.person.dto.SaoAdminPersonDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 外部人员基础信息查询接口
 * @author wangx
 * @date 2017-05-22
 * @version 1.0
 */
public interface SaoPersonInfoService {

	/**
	 * 通过姓名模糊查询人员基础信息列表
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	public List<SaoAdminPersonDTO> listPbPersonInfoByName(String name) throws ServiceException;
}
