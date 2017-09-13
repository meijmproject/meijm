package com.yh.hr.res.hc.service.impl;

import org.springframework.beans.BeanUtils;

import com.yh.hr.res.hc.bo.HcCash;
import com.yh.hr.res.hc.bo.HcFlow;
import com.yh.hr.res.hc.dto.HcFlowDTO;
import com.yh.hr.res.hc.util.HcFlowConstants;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.StringUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 编制流水服务实现类
 * @author liuhw
 * 2016-8-23
 */
public class HcFlowServiceImpl {
	private HcFlow flow;
	
	private HcFlowDTO flowDto;
	
	//是否更新头存信息 --核定数
	protected Boolean isUpdateCashApproved = false;
	//是否更新头存信息 --空闲数
	protected Boolean isUpdateCashFreeze = true;
	
	protected HcFlowDTO getHcFlowDto() {
		return flowDto;
	}
	protected void setHcFlowDto(HcFlowDTO flowDto) {
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
	
	public HcFlowServiceImpl(HcFlowDTO flowDto) {
		super();
		this.flowDto = flowDto;
		this.isUpdateCashApproved = flowDto.getIsUpload();
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
	    updateHcCash();
	}
	
	 /**
    * 没有关键信息则什么也不做
    * @param dto
    * @return
    */
   private Boolean validate(HcFlowDTO dto){
 	  
 	  if(StringUtil.isBlank(dto.getHcCode())||
 	    StringUtil.isBlank(dto.getBudgetFromCode()))
 	  return true;
 	  return false;
   }
	   
   /**
    * 创建流水信息
    * @throws DataAccessFailureException
    */ 
   protected void createFlowInfo() throws DataAccessFailureException{
	   
	   this.flow = new HcFlow();
	   BeanUtils.copyProperties(flowDto, flow);
	   flow.saveOrUpdate();
   }
   
   /**
    * 更新头寸信息
    * @throws DataAccessFailureException
    */
   
   protected void updateHcCash() throws DataAccessFailureException{
	   updateBaseCashFreeze();
	   updateBaseCashApproved();
   }
   
   /**
    * 更新空闲数
    * @throws DataAccessFailureException
    */
   
   protected void updateBaseCashFreeze() throws DataAccessFailureException
   {   
	   HcCash bhc =  DaoUtil.get(HcCash.class, flowDto.getCashOid());
	   //下达操作
	   if(HcFlowConstants.RES_TYPE_1.equals(flowDto.getFlowType()))
	   {
		   //编制下达空闲数=本次变动数+空闲数
		   bhc.setFreeCount(flowDto.getFlowCount()+bhc.getFreeCount());
	   }else{
		   //编制使用后空闲数=空闲数-编制使用数（使用+1；释放-1）
		   bhc.setFreeCount(bhc.getFreeCount()-flowDto.getFlowCount());  
	   }
	   bhc.setUpdatedByCode(UserContext.getLoginUserID());
	   bhc.setUpdatedByName(UserContext.getLoginUserName());
	   bhc.setUpdatedDate(DateUtil.now());
	   bhc.update();
   }
   
   /**
    * 更新核定数
    * @throws DataAccessFailureException
    */
   
   protected void updateBaseCashApproved() throws DataAccessFailureException{
	   //下达操作
	   if(HcFlowConstants.RES_TYPE_1.equals(flowDto.getFlowType()))
	   {
		   //编制下达核定数=本次变动数+头寸核定数
		   HcCash bhc =  DaoUtil.get(HcCash.class, flowDto.getCashOid());
		   bhc.setApprovedCount(flowDto.getFlowCount()+bhc.getApprovedCount());
		   bhc.setUpdatedByCode(UserContext.getLoginUserID());
		   bhc.setUpdatedByName(UserContext.getLoginUserName());
		   bhc.setUpdatedDate(DateUtil.now());
		   bhc.update();
	   }
   }
}
