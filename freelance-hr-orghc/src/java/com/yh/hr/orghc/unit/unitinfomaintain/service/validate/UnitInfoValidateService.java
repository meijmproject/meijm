package com.yh.hr.orghc.unit.unitinfomaintain.service.validate;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.orghc.ut.dto.BizUtUnitDTO;
import com.yh.hr.orghc.ut.service.BizUtUnitService;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 单位信息验证
 * @author xionyx
 *
 * 时间:2016-12-23
 */
public class UnitInfoValidateService implements BaseValidateService {
	private BizUtUnitService bizUtUnitService = (BizUtUnitService)SpringBeanUtil.getBean("bizUtUnitService");
	@Override
	public void validate() throws ServiceException {
		
		// 1.获取托盘数据
		BaseHandleData data = BaseHandler.get();

		BizUtUnitDTO bizUtUnitDTO = bizUtUnitService.findBizUtUnitByTaskOid(NumberUtils.createLong(data.get("bizTaskOid")));
	    if(bizUtUnitDTO==null)throw new ServiceException(null, "单位业务信息不存在！bizTaskOid="+data.get("bizTaskOid"));
		
		//2.查询是否存在单位信息记录；存在则验证通过，不存在抛出异常；
	    if(null == bizUtUnitDTO.getUnitName()
	    		|| null == bizUtUnitDTO.getUnitKind()
	    		|| null == bizUtUnitDTO.getUnitCategoryCode()
	    		|| null == bizUtUnitDTO.getAreaCode()
	    		|| null == bizUtUnitDTO.getBudgetFromCode()
	    		|| null == bizUtUnitDTO.getFileNo()
	    		|| null == bizUtUnitDTO.getEstablishedDate()
	    		|| null == bizUtUnitDTO.getLevelCode()){
	    	throw new ServiceException(null, "单位信息不完整!");
	    } 
	    if(DicConstants.YHRS0090_104.equals(bizUtUnitDTO.getUnitKind()) && null == bizUtUnitDTO.getUnitFunc()){
	    	throw new ServiceException(null, "单位信息不完整!");
	    }
	    
	}
}
