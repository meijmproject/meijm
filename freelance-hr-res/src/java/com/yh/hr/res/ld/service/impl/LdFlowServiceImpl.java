package com.yh.hr.res.ld.service.impl;

import com.yh.hr.res.ld.bo.LdCash;
import com.yh.hr.res.ld.bo.LdFlow;
import com.yh.hr.res.ld.dto.LdFlowDTO;
import org.springframework.beans.BeanUtils;

import com.yh.hr.res.ld.util.LdFlowConstants;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.StringUtil;

/**
 * 领导职数流水服务实现类
 * @author liuhw
 * 2016-8-23
 */
public class LdFlowServiceImpl {
	private LdFlow flow;
	
	private LdFlowDTO flowDto;
	
	//是否更新头存信息 --核定数
	protected Boolean isUpdateCashApproved = false;
	//是否更新头存信息 --空闲数
	protected Boolean isUpdateCashFreeze = true;
	
	protected LdFlowDTO getLdFlowDto() {
		return flowDto;
	}
	protected void setLdFlowDto(LdFlowDTO flowDto) {
		this.flowDto = flowDto;
	}
	
	protected Boolean getIsUpdateCashApproved() {
		return isUpdateCashApproved;
	}

	protected Boolean getIsUpdateCashFreeze() {
		return isUpdateCashFreeze;
	}
	
	protected void setIsUpdateCashApproved(Boolean isUpdateCashApproved) {
		this.isUpdateCashApproved = isUpdateCashApproved;
	}

	protected void setIsUpdateCashFreeze(Boolean isUpdateCashFreeze) {
		this.isUpdateCashFreeze = isUpdateCashFreeze;
	}
	
	public LdFlowServiceImpl(LdFlowDTO flowDto) {
		super();
		this.flowDto = flowDto;
	}
	/**
	 * 创建流水信息
	 * @throws ServiceException
	 */
	public void createFlow() throws ServiceException 
	{
		//没有关键信息则什么也不做
	    if(validate(flowDto))return;
	    this.createFlowInfo();
	    updateLdCash();
	}
	
	 /**
    * 没有关键信息则什么也不做
    * @param dto
    * @return
    */
   private Boolean validate(LdFlowDTO dto){
 	  
 	  if(StringUtil.isBlank(dto.getDutyAttribute())||
 	    StringUtil.isBlank(dto.getDutyLevel()))
 	  return true;
 	  return false;
   }
	   
   /**
    * 创建流水信息
    * @throws DataAccessFailureException
    */ 
   protected void createFlowInfo() throws DataAccessFailureException{
	   
	   this.flow = new LdFlow();
	   BeanUtils.copyProperties(flowDto, flow);
	   flow.saveOrUpdate();
   }
   
   /**
    * 更新头寸信息
    * @throws DataAccessFailureException
    */
   
   protected void updateLdCash() throws DataAccessFailureException{
	   updateBaseCashFreeze();
	   updateBaseCashApproved();
   }
   
   /**
    * 更新空闲数
    * @throws DataAccessFailureException
    */
   
   protected void updateBaseCashFreeze() throws DataAccessFailureException
   {   
	   LdCash bhc =  DaoUtil.get(LdCash.class, flowDto.getCashOid());
	   //如果是编制调整，则空闲数为空闲数+编制调整数
	   if(LdFlowConstants.RES_TYPE_1.equals(flowDto.getFlowType())){
		   bhc.setFreeCount(bhc.getFreeCount()+flowDto.getFlowCount());
	   }else{
		 //领导职数使用后空闲数=空闲数-领导职数使用数（使用+1；释放-1）
		  bhc.setFreeCount(bhc.getFreeCount()-flowDto.getFlowCount());
	   }
	   bhc.setUpdatedByCode(flowDto.getUpdatedByCode());
	   bhc.setUpdatedByName(flowDto.getUpdatedByName());
	   bhc.setUpdatedDate(DateUtil.now());
	   bhc.update();
   }
   
   /**
    * 更新核定数
    * @throws DataAccessFailureException
    */
   
   protected void updateBaseCashApproved() throws DataAccessFailureException{
	   //如果是编制调整，则领导职数下达核定数=本次变动数+头寸核定数,否则核定数不做调整
	   if(LdFlowConstants.RES_TYPE_1.equals(flowDto.getFlowType())){
		   //领导职数下达核定数=本次变动数+头寸核定数
		   LdCash bhc =  DaoUtil.get(LdCash.class, flowDto.getCashOid());
		   bhc.setApprovedCount(flowDto.getFlowCount()+bhc.getApprovedCount());
		   bhc.setUpdatedByCode(flowDto.getUpdatedByCode());
		   bhc.setUpdatedByName(flowDto.getUpdatedByName());
		   bhc.setUpdatedDate(DateUtil.now());
		   bhc.update();
	   }
   }
}
