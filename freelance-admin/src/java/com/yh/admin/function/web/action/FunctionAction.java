package com.yh.admin.function.web.action;

import com.yh.admin.function.facade.FunctionFacade;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.action.BaseAction;

public class FunctionAction extends BaseAction {
	@SuppressWarnings("unused")
	private FunctionFacade functionFacade = (FunctionFacade) SpringBeanUtil.getBean("functionFacade");

}
