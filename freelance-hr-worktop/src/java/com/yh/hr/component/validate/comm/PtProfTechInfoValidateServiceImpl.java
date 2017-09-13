package com.yh.hr.component.validate.comm;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.bo.PtPersonInOther;
import com.yh.hr.res.pt.queryhelper.PtPersonInOtherQueryHelper;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.service.PtProfTechInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 专业技术资格信息（必须采集）
 * @author chenjl
 * @date 2016-11-23
 * @version 1.0
 */
public class PtProfTechInfoValidateServiceImpl implements BaseValidateService {
	private PtProfTechInfoService ptProfTechInfoService = (PtProfTechInfoService) SpringBeanUtil.getBean("ptProfTechInfoService");
	
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
		PtPersonInOther ptPersonInOther = PtPersonInOtherQueryHelper.getPtPersonInOther(ttPerson.getBizPersonOid());
		if(null == ptPersonInOther) {
			throw new ServiceException(null, "人员调入信息不存在！bizTaskOid="+data.get("bizTaskOid"));
		}
		// 2.查询是否存在专业技术资格信息记录；存在则验证通过，不存在抛出异常；
		int count = ptProfTechInfoService.countPtProfTechInfoByPersonOid(ttPerson.getBizPersonOid());
		if (DicConstants.YHRS0003_1.equals(ptPersonInOther.getHasProfTechInfo()))
		{
			if(count == 0) {
				throw new ServiceException(null, "是否有专业技术职称为“是”时，需采集专业技术资格信息。");
			}
		}
	}
}
