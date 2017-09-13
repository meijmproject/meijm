package com.yh.hr.component.validate.comm;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.dto.PtPersonOutDTO;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.service.PtPersonOutService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 调离本市单位 信息需采集
 * @author zhengdr
 *
 * 时间:2016-12-2上午08:52:16
 */
public class PtPersonOutValidateService implements BaseValidateService {
	
	private PtPersonOutService ptPersonOutService = (PtPersonOutService) SpringBeanUtil.getBean("ptPersonOutService");
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see BaseValidateService#validate()
	 */
	public void validate() throws ServiceException {
		// 1.获取托盘数据
		BaseHandleData data = BaseHandler.get();
		
		PtPerson ptPerson = PtPersonQueryHelper.getByTaskOid(NumberUtils.createLong(data.get("bizTaskOid")));
	    if(ptPerson==null){
	    	throw new ServiceException(null, "人员业务信息不存在！bizTaskOid="+data.get("bizTaskOid"));
	    }

		// 2.查询是否存在调离本市单位信息记录；存在则验证通过，不存在抛出异常；
	    PtPersonOutDTO ptPersonOutDTO = ptPersonOutService.findByBizPersonOid(ptPerson.getBizPersonOid());
		if (ptPersonOutDTO == null){
			throw new ServiceException(null, "调离信息不存在!");
		}
	
	}
	
}
