package com.yh.hr.orghc.unit.common.service.validate;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.orghc.ut.dto.BizUtUnitDTO;
import com.yh.hr.orghc.ut.service.BizUtUnitService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 单位撤销信息 上报验证
 * @author zhengdr
 *
 * 时间:2016-12-23上午11:27:55
 */
public class UnitCancelValidateService implements BaseValidateService {
	
	//单位信息管理
	private BizUtUnitService bizUtUnitService = (BizUtUnitService)SpringBeanUtil.getBean("bizUtUnitService");
	
	@Override
	public void validate() throws ServiceException {
		
		// 1.获取托盘数据
		BaseHandleData data = BaseHandler.get();

		BizUtUnitDTO bizUtUnitDTO = bizUtUnitService.findBizUtUnitByTaskOid(NumberUtils.createLong(data.get("bizTaskOid")));
		if (bizUtUnitDTO == null)
			throw new ServiceException(null, "单位业务信息不存在！bizTaskOid=" + data.get("bizTaskOid"));
		if(bizUtUnitDTO.getFileNo()==null||"".equals(bizUtUnitDTO.getFileNo())){
			throw new ServiceException(null,"单位撤销信息不存在!");
		}

	}
}
