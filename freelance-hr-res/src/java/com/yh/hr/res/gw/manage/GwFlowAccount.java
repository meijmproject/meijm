package com.yh.hr.res.gw.manage;


import java.util.List;
import java.util.Map;

import com.yh.hr.res.gw.bo.GwCash;
import com.yh.hr.res.gw.util.GwFlowConstants;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.gw.bo.GwAccount;
import com.yh.hr.res.gw.dto.GwCashDTO;
import com.yh.hr.res.gw.queryhelper.GwFlowQueryHelper;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.StringUtil;
/**
 * 岗位账户头寸管理类
 * @author liuhw
 * 2016-8-24 
 */
public class GwFlowAccount {
	
	private GwAccount  account;
	private GwCash cash;
	private GwCashDTO cashDTO;
	
  public GwFlowAccount(GwCashDTO cashDTO) {
		super();
		this.cashDTO = cashDTO;
	}
  

/**
 * 创建账户及头寸信息
 * 1：相同账户信息不重复创建，
 * 2：相同头寸信息不重复创建
 * 3：岗位下达时需要同步核定数
 * 4：除岗位下达需要同步空闲数
 */
  public void createOrUpdateAccountAndCash() throws ServiceException{
	  
	  GwAccount bha =  getAccount();
	 if(null!=bha) {
		 cashDTO.setAccountOid(bha.getAccountOid());
	 }else{
		 createOrUpdateAccount();
	 }
	 
	 //没有关键信息则什么也不做
	 if(validate(cashDTO))return;
		 
	 GwCash bhc =  getCash();
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
  private Boolean validate(GwCashDTO dto){
	  
	  if(StringUtil.isBlank(dto.getGwLbCode())||
	    StringUtil.isBlank(dto.getGwLevelCode()))
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
  public GwCash getCash()throws ServiceException{
	  
	  TableTagBean ttb  =  buildTTbCondition(cashDTO);
	  
	  List<GwCash> list =  listCash(ttb);
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
  public GwAccount getAccount()throws ServiceException{
	  
	  TableTagBean ttb  =  buildTTbCondition(cashDTO);
	  
	  List<GwAccount> list =  listAccount(ttb);
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
  public List<GwAccount> listAccount(TableTagBean ttb)throws ServiceException
  {
	  return GwFlowQueryHelper.listAccount(ttb);
  }
  
  /**
   * 查询头寸信息
   * @param ttb
   * @return
   * @throws ServiceException
   */
  public List<GwCash> listCash(TableTagBean ttb)throws ServiceException
  {
	  return GwFlowQueryHelper.listCash(ttb);
  }
  
  /**
   * 创建账户信息
   */
  protected void createOrUpdateAccount()throws DataAccessFailureException
  {
	  this.account = new GwAccount();
	  
	  BeanUtils.copyProperties(cashDTO, account);

	  account.setAccountType(GwFlowConstants.ACCOUNT_TYPE_1);
	  account.saveOrUpdate();
	  cashDTO.setAccountOid(account.getAccountOid());
	  
  }
  /**
   * 创建头寸信息
   */
  protected void createOrUpdateCash()throws DataAccessFailureException{
	  
	  this.cash = new GwCash();
	  
	  BeanUtils.copyProperties(cashDTO, cash);
	  //首次创建头寸默认核定数和空闲数为0,保存后更新核定数和空闲数
	  cash.setApprovedCount(0l);
	  cash.setFreeCount(0l);
	  cash.saveOrUpdate();
	  cashDTO.setCashOid(cash.getCashOid());
  }
}
