package com.yh.hr.component.validate.comm;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.dto.PtRefuseInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.service.PtRefuseInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 辞退信息需采集
 * @author zhengdr
 *
 * 时间:2016-11-30上午10:18:16
 */
public class PtRefuseInfoValidateService implements BaseValidateService {
	
	private PtRefuseInfoService ptRefuseInfoService = (PtRefuseInfoService) SpringBeanUtil.getBean("ptRefuseInfoService");
	
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

		// 2.查询是否存在辞退信息记录；存在则验证通过，不存在抛出异常；
	    PtRefuseInfoDTO ptRefuseInfoDTO = ptRefuseInfoService.findByBizPersonOid(ptPerson.getBizPersonOid());
		if (ptRefuseInfoDTO == null){
			throw new ServiceException(null, "辞退信息不存在!");
		}
	
	}
	
}
