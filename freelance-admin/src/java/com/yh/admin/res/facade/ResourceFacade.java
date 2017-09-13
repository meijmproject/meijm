/*
 * @(#) SecurityFacade.java        1.00         Nov 15, 2006
 * 
 * Copyright (c) 2006  EXPRESS Corporation. All Rights Reserved.
 *
 */
package com.yh.admin.res.facade;

import com.yh.admin.res.service.ResourceService;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/08/23
 */
public class ResourceFacade {

	@SuppressWarnings("unused")
	private ResourceService resourceService;

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}
	
	
}
