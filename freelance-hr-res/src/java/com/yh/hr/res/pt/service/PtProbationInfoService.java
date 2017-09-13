package com.yh.hr.res.pt.service;

import com.yh.hr.res.pt.dto.PtProbationInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 试用
 * 时间:2016-11-22
 */
public interface PtProbationInfoService {
/*
 * 新增
 */
	public void create(PtProbationInfoDTO ptProbationInfoDto) throws ServiceException;
	/*
	 * 更新
	 */
	public void update(PtProbationInfoDTO ptProbationInfoDto) throws ServiceException;
	/*
	 * 根据bizpersonOid查找
	 */
	public  PtProbationInfoDTO getById(Long bizPersonOid) throws ServiceException;
}
