package com.yh.hr.select.unit.service;

import java.util.List;


import net.sf.json.JSONObject;

import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.exception.ServiceException;

public interface UnitSelectService {

	/**
	 * 获取单位列表
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public List<JSONObject> listUbUnitInfo(TableTagBean ttb)throws ServiceException;
	
}
