/**
 * 
 */
package com.yh.admin.users.facade;

import com.yh.admin.users.service.UserAgentService;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/08/23
 */

public class UserAgentFacade {
	
	@SuppressWarnings("unused")
	private UserAgentService userAgentService;

	public void setUserAgentService(UserAgentService userAgentService) {
		this.userAgentService = userAgentService;
	}
	
}
