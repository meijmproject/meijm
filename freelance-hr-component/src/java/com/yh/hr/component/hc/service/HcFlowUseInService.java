package com.yh.hr.component.hc.service;

import java.util.List;

import com.yh.hr.res.hc.dto.HcFlowDTO;
import com.yh.platform.core.exception.ServiceException;
/**
 * 编制占用服务接口
 * @author liuhw
 * 2016-8-23
 */
public interface HcFlowUseInService {
	/**
     * 占用
     */
	public void useIn(List<HcFlowDTO> list) throws ServiceException;
}
