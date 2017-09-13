package com.yh.hr.component.validate.comm;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.bo.PtPunishmentInfo;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.queryhelper.PtPunishmentInfoQueryHelper;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;

public class PtOffOtherPunishmentValidateServiceImpl implements BaseValidateService{

	@Override
	public void validate() throws ServiceException {
		// 1.获取托盘数据
		BaseHandleData data = BaseHandler.get();
		
		PtPerson ptPerson = PtPersonQueryHelper.getByTaskOid(NumberUtils.createLong(data.get("bizTaskOid")));
		if(ptPerson==null)throw new ServiceException(null, "人员业务信息不存在！bizTaskOid="+data.get("bizTaskOid"));
		
		//获取处分信息
		PtPunishmentInfo ptPunishmentInfo = PtPunishmentInfoQueryHelper.getPtPunishmentInfo(ptPerson.getBizPersonOid());
		
		if(null == ptPunishmentInfo || null == ptPunishmentInfo.getPunishmentReasonType() || null == ptPunishmentInfo.getPunishmentCode())
		{
			throw new ServiceException(null, "处分信息不完整！");
		}
		
	}

}
