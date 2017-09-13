package com.yh.hr.component.validate.comm;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.service.PtWageHistoryService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 工资历史（必须进行工资计算）
 * @author sijing.liao
 * @version 1.0, 16/10/28
 */
public class PtWageHistoryValidateServiceImpl implements BaseValidateService {
	private PtWageHistoryService ptWageHistoryService = (PtWageHistoryService) SpringBeanUtil.getBean("ptWageHistoryService");
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see BaseValidateService#validate()
	 */
	public void validate() throws ServiceException {
		// 1.获取托盘数据
		BaseHandleData data = BaseHandler.get();
		
		PtPerson ptPerson = PtPersonQueryHelper.getByTaskOid(NumberUtils.createLong(data.get("bizTaskOid")));
	    if(ptPerson==null)throw new ServiceException(null, "人员业务信息不存在！bizTaskOid="+data.get("bizTaskOid"));
		
		// 2.查询是否存在工资历史信息；存在则验证通过，不存在抛出异常；
		int count = ptWageHistoryService.countPtWageHistoryByBizPersonOid(ptPerson.getBizPersonOid());
		if (count == 0)
			throw new ServiceException(null, "工资历史为空。");
	}
	
}
