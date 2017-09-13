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

/** 
 * 影响工资信息校验
 * 	1、参加工作时间不能为空
 *  2、工龄起薪时间不能为空
 *  3、起薪时间不能为空
 * @author liaosj
 * @date 创建时间：2016-10-11 
 * @version 1.0 
 * 
 */

public class PtWageInfluenceCheckServiceImpl implements BaseValidateService{

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
	    if(null == ptWageInfluenceDTO.getStartWorkDate())
	    {
	    	throw new ServiceException(null, "影响工资基础信息中参加工作时间为空。");
	    }
	    if(null ==ptWageInfluenceDTO.getWageStart())
	    {
	    	throw new ServiceException(null, "影响工资基础信息中工龄起算时间为空。");
	    }
	    if(null == ptWageInfluenceDTO.getStartDateOfWage())
	    {
	    	throw new ServiceException(null, "影响工资基础信息中起薪时间为空。");
	    }
	}

}
