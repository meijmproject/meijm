package com.yh.hr.orghc.unit.unitinfomaintain.service.validate;

import java.util.List;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.orghc.ut.dto.BizUtHcDTO;
import com.yh.hr.orghc.ut.dto.BizUtUnitDTO;
import com.yh.hr.orghc.ut.service.BizHcInfoService;
import com.yh.hr.orghc.ut.service.BizUtUnitService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 编制信息验证
 * @author xionyx
 *
 * 时间:2016-12-22
 */
public class HcInfoValidateService implements BaseValidateService {
	private BizUtUnitService bizUtUnitService = (BizUtUnitService)SpringBeanUtil.getBean("bizUtUnitService");
	private BizHcInfoService bizHcInfoService = (BizHcInfoService)SpringBeanUtil.getBean("bizHcInfoService");
	@Override
	public void validate() throws ServiceException {
		
		// 1.获取托盘数据
		BaseHandleData data = BaseHandler.get();

		BizUtUnitDTO bizUtUnitDTO = bizUtUnitService.findBizUtUnitByTaskOid(NumberUtils.createLong(data.get("bizTaskOid")));
	    if(bizUtUnitDTO==null)throw new ServiceException(null, "单位业务信息不存在！bizTaskOid="+data.get("bizTaskOid"));
		
		//2.查询是否存在编制信息记录；存在则验证通过，不存在抛出异常；
		List<BizUtHcDTO> bizUtHcDTOs =bizHcInfoService.list(bizUtUnitDTO.getUtUnitOid());
	    if(bizUtHcDTOs==null||bizUtHcDTOs.size()==0){
	    	throw new ServiceException(null, "编制信息不存在!");
	    } 
	    
	}
}
