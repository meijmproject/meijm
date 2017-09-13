package com.yh.hr.res.cf.service.impl;

import java.util.List;
import java.util.Map;

import com.yh.hr.res.cf.bo.JhcCfShowResultOrder;
import com.yh.hr.res.cf.dto.JhcCfShowResultOrderDTO;
import com.yh.hr.res.cf.queryhelper.JhcShowResultOrderQueryHelper;
import com.yh.hr.res.cf.service.JhcCfShowResultOrderService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

public class JhcCfShowResultOrderServiceImpl implements JhcCfShowResultOrderService {

	public List<Map<String, String>> find(String userId, String functionCode) throws ServiceException {
		return JhcShowResultOrderQueryHelper.find(userId, functionCode);
	}

	public void delete(String userId, String functionCode) throws ServiceException {
		JhcShowResultOrderQueryHelper.deleteByUserAndResult(userId, functionCode);
	}

	public void save(JhcCfShowResultOrderDTO dto) throws ServiceException {
		JhcCfShowResultOrder bo = new JhcCfShowResultOrder();
		BeanHelper.copyRtnProperties(dto, bo);
		bo.save();
	}

}
