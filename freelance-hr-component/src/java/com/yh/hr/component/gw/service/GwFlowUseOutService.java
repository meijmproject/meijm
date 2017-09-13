package com.yh.hr.component.gw.service;

import java.util.List;

import com.yh.hr.res.gw.dto.GwFlowDTO;
import com.yh.platform.core.exception.ServiceException;
/**
 * 岗位资源释放服务接口
 * @author liuhw
 * 2016-8-23
 */
public interface GwFlowUseOutService{
    /**
     * 释放
     * @param list
     * @throws ServiceException
     */
	public void useOut(List<GwFlowDTO> list) throws ServiceException;
}
