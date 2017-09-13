package com.yh.hr.res.cf.service.impl;

import java.util.List;
import java.util.Map;

import com.yh.hr.res.cf.bo.JhcCfShowFieldOrder;
import com.yh.hr.res.cf.dto.JhcCfShowFieldOrderDTO;
import com.yh.hr.res.cf.queryhelper.JhcCfShowFieldOrderQueryHelper;
import com.yh.hr.res.cf.service.JhcCfShowFieldOrderService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;


public class JhcCfShowFieldOrderServiceImpl implements JhcCfShowFieldOrderService {

	public List<Map<String,String>> find(String userId, String functionCode) throws ServiceException {
		return JhcCfShowFieldOrderQueryHelper.find(userId, functionCode);
	}

	public void save(JhcCfShowFieldOrderDTO dto) throws ServiceException {
		JhcCfShowFieldOrder bo = new JhcCfShowFieldOrder();
		BeanHelper.copyRtnProperties(dto, bo);
		bo.save();
	}

	public void delete(JhcCfShowFieldOrderDTO dto) throws ServiceException {
		JhcCfShowFieldOrderQueryHelper.deleteByUserAndResult(dto);
	}

}
