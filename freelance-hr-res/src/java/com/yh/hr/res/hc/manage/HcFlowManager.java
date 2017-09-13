package com.yh.hr.res.hc.manage;

import com.yh.hr.res.hc.dto.HcFlowDTO;
import com.yh.hr.res.hc.service.impl.HcFlowServiceImpl;
import com.yh.platform.core.exception.ServiceException;
/**
 * 编制账户信息管理服务类
 * @author liuhw 
 * 2016-8-23
 */
public class HcFlowManager {
	private HcFlowDTO flowDTO;

    public HcFlowManager(HcFlowDTO flowDTO) {
		super();
		this.flowDTO = flowDTO;
	}

/**
    * 创建流水整体信息
    */
   public void createFlows() throws ServiceException{
	   
	   /**
	    * 创建账户及头寸信息
	    */
	   HcFlowAccount flowAccount = new HcFlowAccount(flowDTO);
	   flowAccount.createOrUpdateAccountAndCash();
	   
	   /**
	    * 创建流水信息
	    */
	   HcFlowServiceImpl service = new HcFlowServiceImpl(flowDTO);
	   service.createFlow();
   }
	
}
