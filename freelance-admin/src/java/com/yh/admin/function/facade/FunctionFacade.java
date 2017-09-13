/**
 * 
 */
package com.yh.admin.function.facade;

import com.yh.admin.function.service.FunctionService;

/**
 * 
 * @author zhangqp
 * @version 1.0, 16/08/11
 */

public class FunctionFacade {
	private FunctionService functionService;

	public FunctionService getFunctionService() {
		return functionService;
	}

	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}
	
	
	
}
