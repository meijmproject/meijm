package com.yh.hr.component.hc.service;

import java.util.List;

import com.yh.hr.res.hc.dto.HcFlowDTO;
import com.yh.platform.core.exception.ServiceException;
/**
 * 编制资源释放服务接口
 * @author liuhw
 * 2016-8-23
 */
public interface HcFlowUseOutService{
    /**
     * 释放
     * @param list
     * @throws ServiceException
     */
	public void useOut(List<HcFlowDTO> list) throws ServiceException;
}
