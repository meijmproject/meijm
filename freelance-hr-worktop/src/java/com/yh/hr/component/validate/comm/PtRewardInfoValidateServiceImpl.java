package com.yh.hr.component.validate.comm;

import java.util.List;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.dto.PtRewardInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.service.PtRewardInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 奖励信息验证
 * @author zhengdr
 *
 * 时间:2016-12-2下午05:22:46
 */
public class PtRewardInfoValidateServiceImpl implements BaseValidateService {

	private PtRewardInfoService ptRewardInfoService = (PtRewardInfoService) SpringBeanUtil.getBean("ptRewardInfoService");
	@Override
	public void validate() throws ServiceException {
		
		// 1.获取托盘数据
		BaseHandleData data = BaseHandler.get();

		PtPerson ptPerson = PtPersonQueryHelper.getByTaskOid(NumberUtils.createLong(data.get("bizTaskOid")));
	    if(ptPerson==null)throw new ServiceException(null, "人员业务信息不存在！bizTaskOid="+data.get("bizTaskOid"));
		
		//2.查询是否存在奖励信息记录；存在则验证通过，不存在抛出异常；
		List<PtRewardInfoDTO> ptRewardInfoDTOs =ptRewardInfoService.findPtRewardInfoByCondition(ptPerson.getBizPersonOid());
	    if(ptRewardInfoDTOs==null||ptRewardInfoDTOs.size()==0){
	    	throw new ServiceException(null, "奖励信息不存在!");
	    } 
	    
	}
}
