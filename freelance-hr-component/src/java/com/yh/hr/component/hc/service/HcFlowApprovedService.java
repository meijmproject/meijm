package com.yh.hr.component.hc.service;

import java.util.List;

import com.yh.hr.res.hc.dto.HcFlowDTO;
import com.yh.platform.core.exception.ServiceException;
/**
 * 编制资源下发接口
 * @author liuhw
 * 2016-9-5
 */
public interface HcFlowApprovedService{
        /**
         * 编制下发接口
         */
	
	public void approved(List<HcFlowDTO> list) throws ServiceException;
}
