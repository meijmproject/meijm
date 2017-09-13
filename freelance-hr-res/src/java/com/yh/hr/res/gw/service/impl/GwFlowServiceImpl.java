package com.yh.hr.res.gw.service.impl;

import com.yh.hr.res.gw.bo.GwCash;
import com.yh.hr.res.gw.util.GwFlowConstants;
import org.springframework.beans.BeanUtils;

import com.yh.hr.res.gw.bo.GwFlow;
import com.yh.hr.res.gw.dto.GwFlowDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.StringUtil;

/**
 * 岗位流水服务实现类
 * @author liuhw
 * 2016-8-23
 */
public class GwFlowServiceImpl {
	private GwFlow flow;
	
	private GwFlowDTO flowDto;
	
	//是否更新头存信息 --核定数
	protected Boolean isUpatedCashApproved = false;
	//是否更新头存信息 --空闲数
	protected Boolean isUpatedCashFreeze = true;
	
	protected GwFlowDTO getGwFlowDto() {
		return flowDto;
	}
	protected void setGwFlowDto(GwFlowDTO flowDto) {
		this.flowDto = flowDto;
	}
	
	protected Boolean getIsUpatedCashApproved() {
		return isUpatedCashApproved;
	}

	protected Boolean getIsUpatedCashFreeze() {
		return isUpatedCashFreeze;
	}
	
	protected void setIsUpatedCashApproved(Boolean isUpatedCashApproved) {
		this.isUpatedCashApproved = isUpatedCashApproved;
	}

	protected void setIsUpatedCashFreeze(Boolean isUpatedCashFreeze) {
		this.isUpatedCashFreeze = isUpatedCashFreeze;
	}
	
	public GwFlowServiceImpl(GwFlowDTO flowDto) {
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
	    updateGwCash();
	}
	
	 /**
    * 没有关键信息则什么也不做
    * @param dto
    * @return
    */
   private Boolean validate(GwFlowDTO dto){
 	  
 	  if(StringUtil.isBlank(dto.getGwLbCode())||
 	    StringUtil.isBlank(dto.getGwLevelCode()))
 	  return true;
 	  return false;
   }
	   
   /**
    * 创建流水信息
    * @throws DataAccessFailureException
    */ 
   protected void createFlowInfo() throws DataAccessFailureException{
	   
	   this.flow = new GwFlow();
	   BeanUtils.copyProperties(flowDto, flow);
	   flow.saveOrUpdate();
   }
   
   /**
    * 更新头寸信息
    * @throws DataAccessFailureException
    */
   
   protected void updateGwCash() throws DataAccessFailureException{
	   /**
	    * 如果是第一次生成数据则不操作，避免数据计算重复
	    */
//	   if(flowDto.getIsFirst())return;
	   updateBaseCashFreeze();
	   updateBaseCashApproved();
   }
   
   /**
    * 更新空闲数
    * @throws DataAccessFailureException
    */
   
   protected void updateBaseCashFreeze() throws DataAccessFailureException
   {   
	   GwCash bhc =  DaoUtil.get(GwCash.class, flowDto.getCashOid());
	   //下达操作
	   if(GwFlowConstants.RES_TYPE_1.equals(flowDto.getFlowType()))
	   {
		   //岗位下达空闲数=本次变动数+空闲数
		   bhc.setFreeCount(flowDto.getFlowCount()+bhc.getFreeCount());
	   }else{
		 //岗位使用后空闲数=空闲数-岗位使用数（使用+1；释放-1）
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
	   if(GwFlowConstants.RES_TYPE_1.equals(flowDto.getFlowType())) {
		   GwCash bhc = DaoUtil.get(GwCash.class, flowDto.getCashOid());
		   //岗位下达核定数=本次变动数+头寸核定数
		   bhc.setApprovedCount(flowDto.getFlowCount() + bhc.getApprovedCount());
		   bhc.setUpdatedByCode(flowDto.getUpdatedByCode());
		   bhc.setUpdatedByName(flowDto.getUpdatedByName());
		   bhc.setUpdatedDate(DateUtil.now());
		   bhc.update();
	   }
   }
}
