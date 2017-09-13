package com.yh.hr.component.info.service.impl;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.info.queryhelper.InfoManageQueryHelper;
import com.yh.hr.component.info.service.InfoManageService;
import com.yh.hr.res.cf.dto.JhcCfQueryConditionDTO;
import com.yh.hr.res.cf.dto.JhcCfQuerySymbolDTO;
import com.yh.platform.core.exception.ServiceException;

public class InfoManageServiceImpl implements InfoManageService {

	public List<JhcCfQueryConditionDTO> listInfoCondition(TableTagBean ttb) throws ServiceException {
		return InfoManageQueryHelper.listInfoCondition(ttb);
	}

	public List<JhcCfQuerySymbolDTO> listSymbolByCondition(Long queryConditionOid) throws ServiceException {
		return InfoManageQueryHelper.listSymbolByCondition(queryConditionOid);
	}

}
