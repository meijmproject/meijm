package com.yh.hr.res.ld.manage;

import com.yh.hr.res.ld.dto.LdFlowDTO;
import com.yh.hr.res.ld.service.impl.LdFlowServiceImpl;
import com.yh.platform.core.exception.ServiceException;
/**
 * 领导职数账户信息管理服务类
 * @author liuhw 
 * 2016-8-23
 */
public class LdFlowManager {
	private LdFlowDTO flowDTO;

    public LdFlowManager(LdFlowDTO flowDTO) {
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
	   LdFlowAccount flowAccount = new LdFlowAccount(flowDTO);
	   flowAccount.createOrUpdateAccountAndCash();
	   
	   /**
	    * 创建流水信息
	    */
	   LdFlowServiceImpl service = new LdFlowServiceImpl(flowDTO);
	   service.createFlow();
   }
	
}
