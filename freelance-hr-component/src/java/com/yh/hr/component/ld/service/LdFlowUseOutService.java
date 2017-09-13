package com.yh.hr.component.ld.service;

import java.util.List;

import com.yh.hr.res.ld.dto.LdFlowDTO;
import com.yh.platform.core.exception.ServiceException;
/**
 * 领导职数资源释放服务接口
 * @author liuhw
 * 2016-8-23
 */
public interface LdFlowUseOutService{
    /**
     * 释放
     * @param list
     * @throws ServiceException
     */
	public void useOut(List<LdFlowDTO> list) throws ServiceException;
}
