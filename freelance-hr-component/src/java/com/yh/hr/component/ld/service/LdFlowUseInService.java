package com.yh.hr.component.ld.service;

import java.util.List;

import com.yh.hr.res.ld.dto.LdFlowDTO;
import com.yh.platform.core.exception.ServiceException;
/**
 * 领导职数占用服务接口
 * @author liuhw
 * 2016-8-23
 */
public interface LdFlowUseInService {
	/**
     * 占用
     */
	public void useIn(List<LdFlowDTO> list) throws ServiceException;
}
