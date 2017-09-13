package com.yh.hr.res.hc.manage;


import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.hc.bo.HcAccount;
import com.yh.hr.res.hc.bo.HcCash;
import com.yh.hr.res.hc.dto.HcCashDTO;
import com.yh.hr.res.hc.queryhelper.HcFlowQueryHelper;
import com.yh.hr.res.hc.util.HcFlowConstants;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.StringUtil;
/**
 * 编制账户管理
 * @author liuhw
 * 2016-8-23
 */
public class HcFlowAccount {
	
	private HcAccount  account;
	private HcCash cash;
	private HcCashDTO cashDTO;
	
  public HcFlowAccount(HcCashDTO cashDTO) {
		super();
		this.cashDTO = cashDTO;
	}
  

/**
 * 创建账户及头寸信息
 * 1：相同账户信息不重复创建，
 * 2：相同头寸信息不重复创建
 * 3：编制下达时需要同步核定数
 * 4：除编制下达需要同步空闲数
 */
  public void createOrUpdateAccountAndCash() throws ServiceException{
	  
	  HcAccount bha =  getAccount();
	 if(null!=bha) {
		 cashDTO.setAccountOid(bha.getAccountOid());
	 }else{
		 createOrUpdateAccount();
	 }
	 
	 //没有关键信息则什么也不做
	 if(validate(cashDTO))return;
		 
	 HcCash bhc =  getCash();
	 if(null!=bhc) {
		 cashDTO.setCashOid(bhc.getCashOid());
	 }else{
		 cashDTO.setIsFirst(true);
		 createOrUpdateCash();
	 }
  }
  /**
   * 没有关键信息则什么也不做
   * @param dto
   * @return
   */
  private Boolean validate(HcCashDTO dto){
	  
	  if(StringUtil.isBlank(dto.getHcCode())||
	    StringUtil.isBlank(dto.getBudgetFromCode()))
	  return true;
	  return false;
  }
  
  /**
   * 构建参数对象
   * @return
   */
  
  @SuppressWarnings("unchecked")
  public  TableTagBean buildTTbCondition(Object bean){
	  
	  TableTagBean ttb = new TableTagBean();
	  try {
		  
		  Map<String,String>  map = org.apache.commons.beanutils.BeanUtils.describe(bean);
		  ttb.getCondition().putAll(map);
			 
		} catch (Exception e) {
		}
	
	  return ttb;
  }
  /**
   * 取得头寸信息
   * @return
   * @throws ServiceException
   */
  public HcCash getCash()throws ServiceException{
	  
	  TableTagBean ttb  =  buildTTbCondition(cashDTO);
	  
	  List<HcCash> list =  listCash(ttb);
	  if(CollectionUtils.isNotEmpty(list)){
		 return list.get(0);
	  }
	  return null;
  }
  /**
   *是否需要新建账户
   * @return
   * @throws ServiceException
   */
  public HcAccount getAccount()throws ServiceException{
	  
	  TableTagBean ttb  =  buildTTbCondition(cashDTO);
	  
	  List<HcAccount> list =  listAccount(ttb);
	  if(CollectionUtils.isNotEmpty(list)){
		 return list.get(0);
	  }
	  return null;
  }
  /**
   * 查询账户信息
   * @param ttb
   * @return
   * @throws ServiceException
   */
  public List<HcAccount> listAccount(TableTagBean ttb)throws ServiceException
  {
	  return HcFlowQueryHelper.listAccount(ttb);
  }
  
  /**
   * 查询头寸信息
   * @param ttb
   * @return
   * @throws ServiceException
   */
  public List<HcCash> listCash(TableTagBean ttb)throws ServiceException
  {
	  return HcFlowQueryHelper.listCash(ttb);
  }
  
  /**
   * 创建账户信息
   */
  protected void createOrUpdateAccount()throws DataAccessFailureException
  {
	  this.account = new HcAccount();
	  
	  BeanUtils.copyProperties(cashDTO, account);
	 
	  account.setAccountType(HcFlowConstants.ACCOUNT_TYPE_1);
	  account.saveOrUpdate();
	  cashDTO.setAccountOid(account.getAccountOid());
	  
  }
  /**
   * 创建头寸信息
   */
  protected void createOrUpdateCash()throws DataAccessFailureException{
	  
	  this.cash = new HcCash();
	  
	  BeanUtils.copyProperties(cashDTO, cash);
	  //首次创建头寸默认核定数和空闲数为0,保存后更新核定数和空闲数
	  cash.setApprovedCount(0l);
	  cash.setFreeCount(0l);
	  cash.saveOrUpdate();
	  cashDTO.setCashOid(cash.getCashOid());
  }
}
