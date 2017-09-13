/**
 * 
 */
package com.yh.hr.component.tansfer;

import com.yh.hr.component.tansfer.service.ItemTransferService;
import com.yh.hr.res.bt.bo.BtTask;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 业务库转基础库工厂方法
 * @author	zhangqp
 * @version	1.0,	16/10/12
 */

public abstract class TransferTbFactory {
	private static final String TRANSFER_PREFIX = "transferBase";
	
	/**
	 * 业务库转基础库工厂方法（AbsTaskTransferService子类）
	 * @param taskOid
	 * @return
	 * @throws ServiceException
	 */
	public static ItemTransferService getTransferService(Long taskOid) throws ServiceException {
		BtTask task = DaoUtil.get(BtTask.class, taskOid);
		
		if (task != null) {
			ItemTransferService transferService = (ItemTransferService)SpringBeanUtil.getBean(TRANSFER_PREFIX+task.getItemCode());
			
			return transferService;
		}
		return null;
	}
}
