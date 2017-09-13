package com.yh.hr.component.info.service;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.cf.dto.JhcCfQueryConditionDTO;
import com.yh.hr.res.cf.dto.JhcCfQuerySymbolDTO;
import com.yh.platform.core.exception.ServiceException;

public interface InfoManageService {

	public List<JhcCfQueryConditionDTO> listInfoCondition(TableTagBean ttb) throws ServiceException ;

	/**
	 * 根据查询条件id获取查询符号数组
	 * @param queryConditionOid
	 * @return
	 */
	public List<JhcCfQuerySymbolDTO> listSymbolByCondition(Long queryConditionOid) throws ServiceException ;

}
