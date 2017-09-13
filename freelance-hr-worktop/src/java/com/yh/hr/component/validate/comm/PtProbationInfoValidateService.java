package com.yh.hr.component.validate.comm;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.bo.PtProbationInfo;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.queryhelper.PtProbationInfoQueryHelper;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;

/**
 * 试用期转正情况 验证
 * @author zhengdr
 *
 * 时间:2016-11-24上午11:37:16
 */
public class PtProbationInfoValidateService implements BaseValidateService {

	@Override
	public void validate() throws ServiceException {
		// 1.获取托盘数据
		BaseHandleData data = BaseHandler.get();

		PtPerson ptPerson = PtPersonQueryHelper.getByTaskOid(NumberUtils.createLong(data.get("bizTaskOid")));
	    if(ptPerson==null)throw new ServiceException(null, "人员业务信息不存在！bizTaskOid="+data.get("bizTaskOid"));
		
		PtProbationInfo ptProbationInfo = PtProbationInfoQueryHelper.getByBizPersonOid(ptPerson.getBizPersonOid());
		
	    if(ptProbationInfo==null){
	    	throw new ServiceException(null, "转正信息不存在。");
	    } 
	}

}
