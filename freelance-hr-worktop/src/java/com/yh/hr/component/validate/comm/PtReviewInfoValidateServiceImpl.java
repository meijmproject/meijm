package com.yh.hr.component.validate.comm;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.service.PtReviewInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;

/** 
 * 考核信息（必须采集）
 * @date 创建时间：2016-10-11 
 * @version 1.0 
 * @parameter  
 */
public class PtReviewInfoValidateServiceImpl implements BaseValidateService {

	private PtReviewInfoService ptReviewInfoService = (PtReviewInfoService) SpringBeanUtil.getBean("ptReviewInfoService");
	@Override
	public void validate() throws ServiceException {
		
		// 1.获取托盘数据
		BaseHandleData data = BaseHandler.get();

		PtPerson ptPerson = PtPersonQueryHelper.getByTaskOid(NumberUtils.createLong(data.get("bizTaskOid")));
	    if(ptPerson==null)throw new ServiceException(null, "人员业务信息不存在！bizTaskOid="+data.get("bizTaskOid"));
		
		//2.查询是否存在考核信息记录；存在则验证通过，不存在抛出异常；
		int count =ptReviewInfoService.countPtReviewInfoByBizPersonOid(ptPerson.getBizPersonOid());
	    if(count==0){
	    	throw new ServiceException(null, "考核信息不存在。");
	    } 
	    
	}
}
