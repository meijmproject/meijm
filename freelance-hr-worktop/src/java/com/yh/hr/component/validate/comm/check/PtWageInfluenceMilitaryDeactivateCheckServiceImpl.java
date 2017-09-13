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
 * 机关事业调入、军转安置、工龄变更工资核定业务校验规则一致 ，目前通用这一个，后续如有改变再维护
 * 影响工资信息校验（机关军转安置业务）
 * 	1、参加工作时间
 *  2、参加工作转正时间
 *  3、工龄起算时间
 *  4、工龄
 *  5、起薪时间
 *  6、工资关系单位
 *
 * @author wuxq
 * @date 创建时间：2016-10-11 
 * @version 1.0 
 * 
 */

public class PtWageInfluenceMilitaryDeactivateCheckServiceImpl implements BaseValidateService{

	private PtWageInfluenceService ptWageInfluenceService = (PtWageInfluenceService) SpringBeanUtil.getBean("ptWageInfluenceService");
	
	public void validate() throws ServiceException {
		// 1.获取托盘数据
		BaseHandleData data = BaseHandler.get();
		
		PtPerson ptPerson = PtPersonQueryHelper.getByTaskOid(NumberUtils.createLong(data.get("bizTaskOid")));
	    if(ptPerson==null)throw new ServiceException(null, "人员业务信息不存在！bizTaskOid="+data.get("bizTaskOid"));
	    
	    PtWageInfluenceDTO ptWageInfluenceDTO = ptWageInfluenceService.getPtWageInfluenceDTOById(ptPerson.getBizPersonOid());
	   // if(null == ptWageInfluenceDTO.getPersonOid()){
	    	
	    	if(null == ptWageInfluenceDTO)
	    	{
	    		throw new ServiceException(null, "影响工资基础信息不存在。");
	    	}
	    
	    	if(null == ptWageInfluenceDTO.getStartWorkDate())
	    	{
	    		throw new ServiceException(null, "影响工资基础信息中参加工作时间为空。");
	    	}
	    
	    	if(null == ptWageInfluenceDTO.getPassProbationDate())
	    	{
	    		throw new ServiceException(null, "影响工资基础信息中参加工作转正时间为空。");
	    	}
	    
	    	if(null ==ptWageInfluenceDTO.getWageStart())
	    	{
	    		throw new ServiceException(null, "影响工资基础信息中工龄起算时间为空。");
	    	}
	    
	    	if(null ==ptWageInfluenceDTO.getServiceYears())
	    	{
	    		throw new ServiceException(null, "影响工资基础信息中工龄为空。");
	    	}
	    
	    	if(null == ptWageInfluenceDTO.getStartDateOfWage())
	    	{
	    		throw new ServiceException(null, "影响工资基础信息中起薪时间为空。");
	    	}
	    
	    	if(null == ptWageInfluenceDTO.getWageServiceUnit())
	    	{
	    		throw new ServiceException(null, "影响工资基础信息中工资关系单位为空。");
	    	}
	    //}
	    
	   // else{
	    	//if(null == ptWageInfluenceDTO.getWageServiceUnit())
		  //  {
		    	//throw new ServiceException(null, "影响工资基础信息中工资关系单位为空。");
		  //  }
	    	 //if(null == ptWageInfluenceDTO.getStartDateOfWage())
	 	  //  {
	 	    //	throw new ServiceException(null, "影响工资基础信息中起薪时间为空。");
	 	   // }
	   // }
	}

}
