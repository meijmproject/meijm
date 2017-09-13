/*
 * @(#) SchedulerFactoryWithTargetBean.java        1.00         2006-10-23
 * 
 * Copyright (c) 2006 FEDEX EXPRESS Corporation. All Rights Reserved.
 *
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. FEDERAL EXPRESS AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL FEDERAL
 * EXPRESS OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF FEDERAL EXPRESS HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 */
package com.yh.platform.core.scheduler;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.scheduling.SchedulingException;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *@description
 *
 *@author            chenkb
 *@created           2006-10-23
 *@version           1.0
 *
 */

public class SchedulerFactoryWithTargetBean extends SchedulerFactoryBean
{
	private String targetServer = null;
	private String hostName = null;
	
	public SchedulerFactoryWithTargetBean()
	{
		try
		{
			InetAddress ina = InetAddress.getLocalHost();
			hostName = ina.getHostName();
		}
		catch (UnknownHostException uhe) 
		{
			hostName = null;
		}
	}

	/**
	 * @return Returns the targetServer.
	 */
	public String getTargetServer()
	{
		return targetServer;
	}

	/**
	 * @param target The targetServer to set.
	 */
	public void setTargetServer(String target)
	{
		this.targetServer = target;
	} 
	
	/**
	 * Start the Quartz Scheduler
	 */
	protected void startScheduler(final Scheduler scheduler, final int startupDelay) throws SchedulerException
	{
		logger.info("Ready to start Quartz Scheduler on Target Server with startupDelay");
		logger.info("The host name is "+hostName+", The target server is "+targetServer);
		
		if (isEmpty(targetServer) || isEmpty(hostName))
		{
			logger.info("Will start Quartz scheduler on any server");
			super.startScheduler(scheduler, startupDelay);
		}
		else if (targetServer.equals(hostName))
		{
			logger.info("Will start Quartz scheduler on server " + hostName);
			super.startScheduler(scheduler, startupDelay);
		}
		else
			logger.info("Will not start Quartz scheduler on server " + hostName);
	}
	
	/**
	 * Start the Quartz Scheduler without delay
	 */
	public void start() throws SchedulingException
	{
		logger.info("Ready to start Quartz scheduler on Target Server without delay");

		if (isEmpty(targetServer) || isEmpty(hostName))
		{
			logger.info("Will start Quartz scheduler on any server");
			super.start();
		}
		else if (targetServer.equals(hostName))
		{
			logger.info("Will start Quartz scheduler on server " + hostName);
			super.start();
		}
		else
			logger.info("Will not start Quartz scheduler on server " + hostName);
	}
	
	/**
	 * stop the Quartz Scheduler
	 */
	public void destroy() throws SchedulerException 
	{
		logger.info("Will destroy Quartz scheduler");

		if (isRunning())
		{
			logger.info("Will stop Quartz scheduler");
			super.destroy();
		}
	}

	private boolean isEmpty(String str)
	{
        return str == null || str.equals("");
	}
	
}
