package com.yh.hr.res.hc.queryhelper;




import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.hc.bo.HcAccount;
import com.yh.hr.res.hc.bo.HcCash;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.util.StringMap;
import com.yh.platform.core.util.StringUtil;
/**
 * 账户流水查询类
 * @author liuhw
 * 2016-8-23
 */ 
public class HcFlowQueryHelper {
	
	/**
	 * 查询账户信息
	 * @param ttb
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static List<HcAccount> listAccount(TableTagBean ttb)  throws DataAccessFailureException
	{
		
		StringMap m = ttb.getCondition();
		//账户类型
		String accountType = m.get("accountType");
		//账户ID/码
		String accountCode = m.get("accountCode");
		
		StringBuffer hql = new StringBuffer();
		
		hql.append("  from HcAccount where 1=1");
		
		if(StringUtil.isNotBlank(accountType)){
			
			hql.append(" and accountType ='"+accountType+"'");
		}
		
        if(StringUtil.isNotBlank(accountCode)){
			
			hql.append(" and accountCode ='"+accountCode+"'");
		}
		
		return DaoUtil.find(hql.toString());
	}
	
	
	/**
	 * 查询头存信息
	 * @param ttb
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static List<HcCash> listCash(TableTagBean ttb)  throws DataAccessFailureException
	{
		StringMap m = ttb.getCondition();
		String hcCode = m.get("hcCode");
		String budgetFromCode = m.get("budgetFromCode");
		
		String accountOid = m.get("accountOid");
		
		StringBuffer hql = new StringBuffer();
		
		hql.append(" from HcCash where 1=1");
		
        if(StringUtil.isNotBlank(hcCode)){
			
			hql.append(" and hcCode ='"+hcCode+"'");
		}
        
        if(StringUtil.isNotBlank(budgetFromCode)){
			
			hql.append(" and budgetFromCode ='"+budgetFromCode+"'");
		}
        
        if(StringUtil.isNotBlank(accountOid)){
			
			hql.append(" and accountOid ='"+accountOid+"'");
		}
		
		return DaoUtil.find(hql.toString());
	}
}
