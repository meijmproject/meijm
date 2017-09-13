package com.yh.hr.info.bizdocument.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.info.bizdocument.queryhelper.BizDocumentQueryHelper;
import com.yh.hr.info.bizdocument.service.BizDocumentService;
import com.yh.platform.core.exception.ServiceException;

public class BizDocumentServiceImpl implements BizDocumentService {

	@Override
	public List<JSONObject> listBizDocument(TableTagBean ttb)
			throws ServiceException {
		return BizDocumentQueryHelper.listBizDocument(ttb);
	}

}
