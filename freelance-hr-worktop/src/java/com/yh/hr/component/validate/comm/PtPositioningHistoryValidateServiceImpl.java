package com.yh.hr.component.validate.comm;


import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.service.PtPositioningHistoryService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 任职历史信息必填验证
 * @author wangjie
 * @date 2016-11-1 
 * @version 1.0 
 */
public class PtPositioningHistoryValidateServiceImpl implements BaseValidateService{
	private PtPositioningHistoryService ptPositioningHistoryService = (PtPositioningHistoryService) SpringBeanUtil.getBean("ptPositioningHistoryService");
	
	public void validate() throws ServiceException {
	// 1.获取托盘数据
	BaseHandleData data = BaseHandler.get();

	PtPerson ptPerson = PtPersonQueryHelper.getByTaskOid(NumberUtils.createLong(data.get("bizTaskOid")));
    if(ptPerson==null)throw new ServiceException(null, "人员业务信息不存在！bizTaskOid="+data.get("bizTaskOid"));
    
    //2.任职历史信息必填校验
    int  count= ptPositioningHistoryService.countPtPositioningHistoryByBizPersonOid(ptPerson.getBizPersonOid());
    if(count==0) throw new ServiceException(null, "任职聘任信息不存在。");
   
}

}
