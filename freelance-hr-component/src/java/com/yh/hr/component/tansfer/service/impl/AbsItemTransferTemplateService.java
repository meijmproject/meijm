/**
 * 
 */
package com.yh.hr.component.tansfer.service.impl;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.component.tansfer.service.ItemTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.platform.core.exception.ServiceException;

/**
 * 业务事项转库模板抽象类（ 前缀 b:基础信息pb, t:业务信息pt）
 * @author	zhangqp
 * @version	1.0,	16/10/18
 */

public abstract class AbsItemTransferTemplateService implements ItemTransferService {
	protected List<InfoTransferService> transfers;
	
	protected Long sourceBizOid;//数据源
	protected Long targetBizOid;//目标数据
	
	/**
	 * 前置处理（准备数据）
	 * @param taskOid
	 * @throws ServiceException
	 */
	protected abstract void doBefore(Long taskOid) throws ServiceException;
	
	/**
	 * 后置处理（回写数据）
	 * @param taskOid
	 * @throws ServiceException
	 */
	protected abstract void doAfter(Long taskOid) throws ServiceException;
	
	public void transfer(Long taskOid) throws ServiceException {
		doBefore(taskOid);
		
		if (CollectionUtils.isNotEmpty(transfers)) {
			for (InfoTransferService transfer : transfers) {
				transfer.transfer(sourceBizOid, targetBizOid);
			}
		}
		
		doAfter(taskOid);
	}
	
	public List<InfoTransferService> getTransfers() {
		return transfers;
	}

	public void setTransfers(List<InfoTransferService> transfers) {
		this.transfers = transfers;
	}

	public Long getSourceBizOid() {
		return sourceBizOid;
	}

	public void setSourceBizOid(Long sourceBizOid) {
		this.sourceBizOid = sourceBizOid;
	}

	public Long getTargetBizOid() {
		return targetBizOid;
	}

	public void setTargetBizOid(Long targetBizOid) {
		this.targetBizOid = targetBizOid;
	}
}
