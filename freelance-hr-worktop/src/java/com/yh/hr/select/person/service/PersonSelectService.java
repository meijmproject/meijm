package com.yh.hr.select.person.service;

import java.util.List;


import net.sf.json.JSONObject;

import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.exception.ServiceException;

public interface PersonSelectService {

	/**
	 * 获取人员列表
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public List<JSONObject> listPbpersonInfo(TableTagBean ttb)throws ServiceException;
	
}
