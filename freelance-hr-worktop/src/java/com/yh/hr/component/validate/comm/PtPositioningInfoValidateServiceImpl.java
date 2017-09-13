package com.yh.hr.component.validate.comm;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.dto.PtPositioningInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.service.PtPositioningService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import org.apache.commons.lang.StringUtils;

/** 
 * 任职聘任信息（必须采集）
 * @author  作者：
 * @date 创建时间：2016-10-11 
 * @version 1.0 
 */
public class PtPositioningInfoValidateServiceImpl implements BaseValidateService{
	private PtPositioningService ptPositioningService = (PtPositioningService) SpringBeanUtil.getBean("ptPositioningService");
	@Override
	public void validate() throws ServiceException {
		// 1.获取托盘数据
		BaseHandleData data = BaseHandler.get();

		PtPerson ptPerson = PtPersonQueryHelper.getByTaskOid(NumberUtils.createLong(data.get("bizTaskOid")));
	    if(ptPerson==null)throw new ServiceException(null, "人员业务信息不存在！bizTaskOid="+data.get("bizTaskOid"));
		
		//2.查询是否存在任职聘任信息记录；存在则验证通过，不存在抛出异常；
		PtPositioningInfoDTO ptPositioningInfoDTO  =ptPositioningService.getByBizPersonOid(ptPerson.getBizPersonOid());
	    if(null==ptPositioningInfoDTO){
	    	throw new ServiceException(null, "拟任职务信息不存在。");
	    }
		else if(StringUtils.isBlank(ptPositioningInfoDTO.getDutyLevel()))
		{
			throw new ServiceException(null, "拟任职务信息不存在。");
		}
	}

}
