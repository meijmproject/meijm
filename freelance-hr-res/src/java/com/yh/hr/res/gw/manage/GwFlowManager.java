package com.yh.hr.res.gw.manage;

import com.yh.hr.res.gw.dto.GwFlowDTO;
import com.yh.hr.res.gw.service.impl.GwFlowServiceImpl;
import com.yh.platform.core.exception.ServiceException;
/**
 * 岗位流水管理类
 * @author liuhw
 * 2016-8-24
 */
public class GwFlowManager {
	private GwFlowDTO flowDTO;

    public GwFlowManager(GwFlowDTO flowDTO) {
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
	   GwFlowAccount flowAccount = new GwFlowAccount(flowDTO);
	   flowAccount.createOrUpdateAccountAndCash();
	   
	   /**
	    * 创建流水信息
	    */
	   GwFlowServiceImpl service = new GwFlowServiceImpl(flowDTO);
	   service.createFlow();
   }
	
}
