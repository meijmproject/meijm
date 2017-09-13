package com.yh.hr.component.validate.comm.check;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.dto.PtWageInfluenceDTO;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.service.PtWageInfluenceService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;

/** 工资业务（机关）
 * 影响工资信息起薪时间校验
 *  1、起薪时间不能为空
 * @author went
 * @date 创建时间：2016-11-09
 * @version 1.0 
 * 
 */

public class PtWageOffStartDateCheckServiceImpl implements BaseValidateService{

	private PtWageInfluenceService ptWageInfluenceService = (PtWageInfluenceService) SpringBeanUtil.getBean("ptWageInfluenceService");
	
	public void validate() throws ServiceException {
		// 1.获取托盘数据
		BaseHandleData data = BaseHandler.get();
		
		PtPerson ptPerson = PtPersonQueryHelper.getByTaskOid(NumberUtils.createLong(data.get("bizTaskOid")));
	    if(ptPerson==null)throw new ServiceException(null, "人员业务信息不存在！bizTaskOid="+data.get("bizTaskOid"));
	    
	    PtWageInfluenceDTO ptWageInfluenceDTO = ptWageInfluenceService.getPtWageInfluenceDTOById(ptPerson.getBizPersonOid());
	    
	    if(null == ptWageInfluenceDTO)
	    {
	    	throw new ServiceException(null, "影响工资基础信息不存在。");
	    }
	    if(null == ptWageInfluenceDTO.getStartDateOfWage())
	    {
	    	throw new ServiceException(null, "影响工资基础信息中起薪时间为空。");
	    }
	}

}
