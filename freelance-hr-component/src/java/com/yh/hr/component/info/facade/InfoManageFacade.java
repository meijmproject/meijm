package com.yh.hr.component.info.facade;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.info.service.InfoManageService;
import com.yh.hr.res.cf.dto.JhcCfQueryConditionDTO;
import com.yh.hr.res.cf.dto.JhcCfQuerySymbolDTO;
import com.yh.platform.core.exception.ServiceException;

public class InfoManageFacade {
	private InfoManageService infoManageService;

	public void setInfoManageService(InfoManageService infoManageService) {
		this.infoManageService = infoManageService;
	}

	public List<JhcCfQueryConditionDTO> listInfoCondition(TableTagBean ttb) throws ServiceException {
		return infoManageService.listInfoCondition(ttb);
	}

	/**
	 * 根据查询条件id获取查询符号数组
	 * @param queryConditionOid
	 * @return
	 */
	public List<JhcCfQuerySymbolDTO> listSymbolByCondition(Long queryConditionOid) throws ServiceException {
		return infoManageService.listSymbolByCondition(queryConditionOid);
	}

}
