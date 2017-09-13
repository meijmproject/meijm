/**
 * 
 */
package com.yh.hr.leader.validate.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.yh.hr.leader.validate.dto.LdCashCheckResultDTO;
import org.apache.commons.collections.CollectionUtils;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.leader.validate.dto.LdCashCheckDTO;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;

/**
 * 领导职数验证默认抽象现实
 * @author	zhangqp
 * @version	1.0,	16/11/01
 */

public abstract class LdValidateAbstractService implements BaseValidateService {
	
	protected LdCheckService ldCheckService;

	public void setLdCheckService(LdCheckService ldCheckService) {
		this.ldCheckService = ldCheckService;
	}
	
	/**
	 * 检查
	 * @param ldCashCheckDTOs
	 * @throws ServiceException
	 */
	protected void check(List<LdCashCheckDTO> ldCashCheckDTOs) throws ServiceException {
		List<LdCashCheckResultDTO> fails = ldCheckService.check(ldCashCheckDTOs);
		
		if (CollectionUtils.isNotEmpty(fails)) {
			StringBuilder message = new StringBuilder();
			
			int size = fails.size();
			for (int i=0; i<size; i++) {
				if(size > 1) {
					message.append(i+1);
					message.append("、");
				}
				message.append(fails.get(i).getMessage());
				message.append("<br>");
			}
			
			throw new ServiceException(null, message.toString());
		}
	}
	
	public void validate() throws ServiceException {
		
		List<Long> taskOids = getBizTaskOids();
		
		List<LdCashCheckDTO> ldCashCheckDTOs = getCheckData(taskOids);
		
		check(ldCashCheckDTOs);
	}
	
	/**
	 * 取参数
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	protected List<Long> getBizTaskOids() throws ServiceException {
		// 1.获取托盘数据
		BaseHandleData data = BaseHandler.get();
				
		List<Long> taskOids = new ArrayList<Long>();
		if (data.get("taskOids") != null) {
			taskOids = (List<Long>) data.get("taskOids");
		} else {
			taskOids = Arrays.asList(NumberUtils.createLong(data.get("bizTaskOid")));
		}
		
		return taskOids;
	}
	
	/**
	 * 获取业务参数数据
	 * @param taskOids
	 * @return
	 * @throws ServiceException
	 */
	protected abstract List<LdCashCheckDTO> getCheckData(List<Long> taskOids) throws ServiceException ;
}
