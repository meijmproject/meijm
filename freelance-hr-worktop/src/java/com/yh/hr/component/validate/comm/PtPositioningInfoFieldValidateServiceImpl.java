package com.yh.hr.component.validate.comm;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.bo.PtPositioningDis;
import com.yh.hr.res.pt.bo.PtPositioningInfo;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.queryhelper.PtPositioningDisQueryHelper;
import com.yh.hr.res.pt.queryhelper.PtPositioningInfoQueryHelper;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;


/**
 * 同主管单位职务变动 校验必填
 * @author zhengdr
 * 时间:2016-11-7上午10:59:27
 */
public class PtPositioningInfoFieldValidateServiceImpl implements BaseValidateService{
	
	/**
	 * 根据字段判断是否采集 任职任聘信息
	 */
	public void validate() throws ServiceException {
		// 1.获取托盘数据
		BaseHandleData data = BaseHandler.get();

		PtPerson ptPerson = PtPersonQueryHelper.getByTaskOid(NumberUtils.createLong(data.get("bizTaskOid")));
	    if(ptPerson==null)throw new ServiceException(null, "人员业务信息不存在！bizTaskOid="+data.get("bizTaskOid"));
		
		//查询是否存在任职聘任信息记录；
	    //存在是否字段为空
	    PtPositioningInfo ptPositioningInfo = PtPositioningInfoQueryHelper.getByBizPersonOid(ptPerson.getBizPersonOid());
	    if(ptPositioningInfo==null||ptPositioningInfo.getDutyAttribute()==null){
	    	throw new ServiceException(null, "变动职务信息不存在！");
	    }
	    List<PtPositioningDis> ptPositioningDisList = PtPositioningDisQueryHelper.listPtPositioningDisBoByBizPersonOid(ptPerson.getBizPersonOid());
	    if(CollectionUtils.isNotEmpty(ptPositioningDisList)){
	    	PtPositioningDis ptPositioningDis = ptPositioningDisList.get(0);
	    	if(null == ptPositioningDis.getEndDateActual()){
	    		throw new ServiceException(null, "免职业务信息不完整！");
	    	}
	    }
	}

}
