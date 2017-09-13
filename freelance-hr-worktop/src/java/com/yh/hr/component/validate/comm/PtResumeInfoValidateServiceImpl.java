package com.yh.hr.component.validate.comm;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.service.PtResumeInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;

/** 
 * 工作简历信息（必须采集）
 * @date 创建时间：2016-10-11 
 * @version 1.0 
 * @parameter  
 */
public class PtResumeInfoValidateServiceImpl implements BaseValidateService {

	private PtResumeInfoService ptResumeInfoService = (PtResumeInfoService) SpringBeanUtil.getBean("ptResumeInfoService");
	@Override
	public void validate() throws ServiceException {
		// 1.获取托盘数据
		BaseHandleData data = BaseHandler.get();

		PtPerson ptPerson = PtPersonQueryHelper.getByTaskOid(NumberUtils.createLong(data.get("bizTaskOid")));
	    if(ptPerson==null)throw new ServiceException(null, "人员业务信息不存在！bizTaskOid="+data.get("bizTaskOid"));
		
		 //2.查询是否存在工作简历信息记录；存在则验证通过，不存在抛出异常；
		int count =ptResumeInfoService.countPtResumeInfoByBizPersonOid(ptPerson.getBizPersonOid());
	    if(count==0){
	    	throw new ServiceException(null, "工作简历信息不存在。");
	    } 
	}

}
