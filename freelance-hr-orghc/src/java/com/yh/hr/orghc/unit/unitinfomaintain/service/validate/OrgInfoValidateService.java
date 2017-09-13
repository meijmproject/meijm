package com.yh.hr.orghc.unit.unitinfomaintain.service.validate;

import java.util.List;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.orghc.ut.dto.BizUtOrgDTO;
import com.yh.hr.orghc.ut.dto.BizUtUnitDTO;
import com.yh.hr.orghc.ut.service.BizOrgInfoService;
import com.yh.hr.orghc.ut.service.BizUtUnitService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 内设机构信息验证
 * @author xionyx
 *
 * 时间:2016-12-22
 */
public class OrgInfoValidateService implements BaseValidateService {
	private BizUtUnitService bizUtUnitService = (BizUtUnitService)SpringBeanUtil.getBean("bizUtUnitService");
	private BizOrgInfoService bizOrgInfoService = (BizOrgInfoService)SpringBeanUtil.getBean("bizOrgInfoService");
	@Override
	public void validate() throws ServiceException {
		
		// 1.获取托盘数据
		BaseHandleData data = BaseHandler.get();

		BizUtUnitDTO bizUtUnitDTO = bizUtUnitService.findBizUtUnitByTaskOid(NumberUtils.createLong(data.get("bizTaskOid")));
	    if(bizUtUnitDTO==null)throw new ServiceException(null, "单位业务信息不存在！bizTaskOid="+data.get("bizTaskOid"));
		
		//2.查询是否存在内设机构信息记录；存在则验证通过，不存在抛出异常；
		List<BizUtOrgDTO> bizUtOrgDTOs =bizOrgInfoService.list(bizUtUnitDTO.getUtUnitOid(),null);
	    if(bizUtOrgDTOs==null||bizUtOrgDTOs.size()==0){
	    	throw new ServiceException(null, "内设机构信息不存在!");
	    } 
	    
	}
}
