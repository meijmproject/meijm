package com.yh.hr.component.validate.comm;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.dto.PtDeathInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.service.PtDeathInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 自然减员信息需采集
 * @author zhengdr
 *
 * 时间:2016-11-28下午03:46:28
 */
public class PtDeathInfoValidateService implements BaseValidateService {
	
	private PtDeathInfoService ptDeathInfoService = (PtDeathInfoService) SpringBeanUtil.getBean("ptDeathInfoService");
	
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

		// 2.查询是否存在自然减员记录；存在则验证通过，不存在抛出异常；
	    PtDeathInfoDTO ptDeathInfoDTO = ptDeathInfoService.findByBizPersonOid(ptPerson.getBizPersonOid());
		if (ptDeathInfoDTO == null){
			throw new ServiceException(null, "自然减员信息不存在!");
		}
	
	}
	
}
