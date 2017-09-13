package com.yh.hr.component.validate.comm;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.dto.PtDismissInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.service.PtDismissInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 开除信息  需采集
 * @author zhengdr
 *
 * 时间:2016-11-30下午05:49:18
 */
public class PtDismissInfoValidateService implements BaseValidateService {
	
	private PtDismissInfoService ptDismissInfoService = (PtDismissInfoService) SpringBeanUtil.getBean("ptDismissInfoService");
	
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

		// 2.查询是否存在开除信息记录；存在则验证通过，不存在抛出异常；
	    PtDismissInfoDTO ptDismissInfoDTO = ptDismissInfoService.findByBizPersonOid(ptPerson.getBizPersonOid());
		if (ptDismissInfoDTO == null){
			throw new ServiceException(null, "开除信息不存在!");
		}
	
	}
	
}
