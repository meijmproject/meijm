package com.yh.hr.res.ld.queryhelper;




import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.ld.bo.LdAccount;
import com.yh.hr.res.ld.bo.LdCash;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.util.StringMap;
import com.yh.platform.core.util.StringUtil;
/**
 * 账户流水查询类
 * @author liuhw
 * 2016-8-23
 */ 
public class LdFlowQueryHelper {
	
	/**
	 * 查询账户信息
	 * @param ttb
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static List<LdAccount> listAccount(TableTagBean ttb)  throws DataAccessFailureException
	{
		
		StringMap m = ttb.getCondition();
		//账户类型
		String accountType = m.get("accountType");
		//账户ID/码
		String accountCode = m.get("accountCode");
		
		StringBuffer hql = new StringBuffer();
		
		hql.append("  from LdAccount where 1=1");
		
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
	public static List<LdCash> listCash(TableTagBean ttb)  throws DataAccessFailureException
	{
		StringMap m = ttb.getCondition();
		String dutyAttribute = m.get("dutyAttribute");
		String dutyLevel = m.get("dutyLevel");
		
		String accountOid = m.get("accountOid");
		
		StringBuffer hql = new StringBuffer();
		
		hql.append(" from LdCash where 1=1");
		
        if(StringUtil.isNotBlank(dutyAttribute)){
			
			hql.append(" and dutyAttribute ='"+dutyAttribute+"'");
		}
        
        if(StringUtil.isNotBlank(dutyLevel)){
			
			hql.append(" and dutyLevel ='"+dutyLevel+"'");
		}
        
        if(StringUtil.isNotBlank(accountOid)){
			
			hql.append(" and accountOid ='"+accountOid+"'");
		}
		
		return DaoUtil.find(hql.toString());
	}
}
