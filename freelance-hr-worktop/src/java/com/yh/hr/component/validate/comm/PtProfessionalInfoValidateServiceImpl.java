package com.yh.hr.component.validate.comm;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.bo.PtPersonInOther;
import com.yh.hr.res.pt.queryhelper.PtPersonInOtherQueryHelper;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.service.PtProfessionalInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 职业资格信息（必须采集）
 * @author chenjl
 * @date 2016-11-23
 * @version 1.0
 */
public class PtProfessionalInfoValidateServiceImpl implements BaseValidateService {
	private PtProfessionalInfoService ptProfessionalInfoService = (PtProfessionalInfoService) SpringBeanUtil.getBean("ptProfessionalInfoService");
	
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
		// 2.查询是否存在职业资格信息记录；存在则验证通过，不存在抛出异常；
		int count = ptProfessionalInfoService.countPtProfessionalInfoByPersonOid(ttPerson.getBizPersonOid());
		if (DicConstants.YHRS0003_1.equals(ptPersonInOther.getHasProfessionalInfo())){
			if(count == 0) {
				throw new ServiceException(null, "是否有职业资格信息为“是”时，需采集职业资格信息。");
			}
		}
	}
}
