package com.yh.hr.component.gw.service;

import java.util.List;

import com.yh.hr.res.gw.dto.GwFlowDTO;
import com.yh.platform.core.exception.ServiceException;
/**
 * 岗位占用服务接口
 * @author liuhw
 * 2016-8-23
 */
public interface GwFlowUseInService {
	/**
     * 占用
     */
	public void useIn(List<GwFlowDTO> list) throws ServiceException;
}
