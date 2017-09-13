package com.yh.hr.component.gw.service;


import java.util.List;

import com.yh.hr.res.gw.dto.GwFlowDTO;
import com.yh.platform.core.exception.ServiceException;
/**
 * 岗位资源下达服务接口
 * @author liuhw
 * 2016-9-5
 */
public interface GwFlowApprovedService {
        /**
         * 岗位下发接口
         */
	
	public void approved(List<GwFlowDTO> list) throws ServiceException;
}
