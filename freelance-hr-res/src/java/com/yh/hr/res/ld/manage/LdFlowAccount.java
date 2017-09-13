package com.yh.hr.res.ld.manage;


import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.ld.bo.LdAccount;
import com.yh.hr.res.ld.bo.LdCash;
import com.yh.hr.res.ld.dto.LdCashDTO;
import com.yh.hr.res.ld.queryhelper.LdFlowQueryHelper;
import com.yh.hr.res.ld.util.LdFlowConstants;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.StringUtil;
/**
 * 领导职数账户管理
 * @author liuhw
 * 2016-8-23
 */
public class LdFlowAccount {
	
	private LdAccount  account;
	private LdCash cash;
	private LdCashDTO cashDTO;
	
  public LdFlowAccount(LdCashDTO cashDTO) {
		super();
		this.cashDTO = cashDTO;
	}
  

/**
 * 创建账户及头寸信息
 * 1：相同账户信息不重复创建，
 * 2：相同头寸信息不重复创建
 * 3：领导职数下达时需要同步核定数
 * 4：除领导职数下达需要同步空闲数
 */
  public void createOrUpdateAccountAndCash() throws ServiceException{
	  
	  LdAccount bha =  getAccount();
	 if(null!=bha) {
		 cashDTO.setAccountOid(bha.getAccountOid());
	 }else{
		 createOrUpdateAccount();
	 }
	 
	 //没有关键信息则什么也不做
	 if(validate(cashDTO))return;
		 
	 LdCash bhc =  getCash();
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
  private Boolean validate(LdCashDTO dto){
	  
	  if(StringUtil.isBlank(dto.getDutyAttribute())||
	    StringUtil.isBlank(dto.getDutyLevel()))
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
  public LdCash getCash()throws ServiceException{
	  
	  TableTagBean ttb  =  buildTTbCondition(cashDTO);
	  
	  List<LdCash> list =  listCash(ttb);
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
  public LdAccount getAccount()throws ServiceException{
	  
	  TableTagBean ttb  =  buildTTbCondition(cashDTO);
	  
	  List<LdAccount> list =  listAccount(ttb);
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
  public List<LdAccount> listAccount(TableTagBean ttb)throws ServiceException
  {
	  return LdFlowQueryHelper.listAccount(ttb);
  }
  
  /**
   * 查询头寸信息
   * @param ttb
   * @return
   * @throws ServiceException
   */
  public List<LdCash> listCash(TableTagBean ttb)throws ServiceException
  {
	  return LdFlowQueryHelper.listCash(ttb);
  }
  
  /**
   * 创建账户信息
   */
  protected void createOrUpdateAccount()throws DataAccessFailureException
  {
	  this.account = new LdAccount();
	  
	  BeanUtils.copyProperties(cashDTO, account);

	  account.setAccountType(LdFlowConstants.ACCOUNT_TYPE_1);
	  account.saveOrUpdate();
	  cashDTO.setAccountOid(account.getAccountOid());
	  
  }
  /**
   * 创建头寸信息
   */
  protected void createOrUpdateCash()throws DataAccessFailureException{
	  
	  this.cash = new LdCash();
	  
	  BeanUtils.copyProperties(cashDTO, cash);
	  //首次创建头寸默认核定数和空闲数为0,保存后更新核定数和空闲数
	  cash.setApprovedCount(0l);
	  cash.setFreeCount(0l);
	  cash.saveOrUpdate();
	  cashDTO.setCashOid(cash.getCashOid());
  }
}
