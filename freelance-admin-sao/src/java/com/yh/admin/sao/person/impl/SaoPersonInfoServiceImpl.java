package com.yh.admin.sao.person.impl;

import java.util.List;

import com.yh.admin.sao.person.SaoPersonInfoService;
import com.yh.admin.sao.person.dto.SaoAdminPersonDTO;
import com.yh.hr.res.pb.service.PbPersonInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

public class SaoPersonInfoServiceImpl implements SaoPersonInfoService {

	private PbPersonInfoService pbPersonInfoService;
	
	public void setPbPersonInfoService(PbPersonInfoService pbPersonInfoService) {
		this.pbPersonInfoService = pbPersonInfoService;
	}

	/**
	 * 通过条件查询人员基础信息列表
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	public List<SaoAdminPersonDTO> listPbPersonInfoByName(String name)
			throws ServiceException {
		return BeanHelper.copyProperties(pbPersonInfoService.listPbPersonInfoByName(name), SaoAdminPersonDTO.class);
	}
}
