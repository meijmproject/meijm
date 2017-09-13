package com.yh.hr.info.bizdocument.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.exception.ServiceException;

public interface BizDocumentService {

	/**
	 * 查询业务文档列表
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	List<JSONObject> listBizDocument(TableTagBean ttb) throws ServiceException;

}
