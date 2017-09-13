package com.yh.hr.component.validate.comm;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.service.PtEducationInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 学历学位信息（必须采集）
 * @author zhangqp
 * @version 1.0, 16/10/10
 */
public class PtEducationInfoValidateServiceImpl implements BaseValidateService {
	private PtEducationInfoService ptEducationInfoService = (PtEducationInfoService) SpringBeanUtil.getBean("ptEducationInfoService");
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see BaseValidateService#validate()
	 */
	public void validate() throws ServiceException {
		// 1.获取托盘数据
		BaseHandleData data = BaseHandler.get();
		
		PtPerson ttPerson = PtPersonQueryHelper.getByTaskOid(NumberUtils.createLong(data.get("bizTaskOid")));
		if(ttPerson == null) {
			throw new ServiceException(null, "人员业务信息不存在！bizTaskOid="+data.get("bizTaskOid"));
		}
		// 2.查询是否存在学历信息记录；存在则验证通过，不存在抛出异常；
		int count = ptEducationInfoService.countPtEducationInfoByBizPersonOid(ttPerson.getBizPersonOid());
		if (count == 0)
			throw new ServiceException(null, "学历信息不存在。");
	}
	
}
