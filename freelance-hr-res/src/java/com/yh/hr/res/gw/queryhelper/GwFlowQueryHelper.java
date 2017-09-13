package com.yh.hr.res.gw.queryhelper;




import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.gw.bo.GwAccount;
import com.yh.hr.res.gw.bo.GwCash;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.util.StringMap;
import com.yh.platform.core.util.StringUtil;
/**
 * 账户流水查询类
 *
 */
public class GwFlowQueryHelper {
	
	/**
	 * 查询账户信息
	 * @param ttb
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static List<GwAccount> listAccount(TableTagBean ttb)  throws DataAccessFailureException
	{
		
		StringMap m = ttb.getCondition();
		//账户类型
		String accountType = m.get("accountType");
		//账户ID/码
		String accountCode = m.get("accountCode");
		
		StringBuffer hql = new StringBuffer();
		
		hql.append("  from GwAccount where 1=1");
		
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
	public static List<GwCash> listCash(TableTagBean ttb)  throws DataAccessFailureException
	{
		StringMap m = ttb.getCondition();
		String gwLevelCode = m.get("gwLevelCode");
		String gwLbCode = m.get("gwLbCode");
		
		String accountOid = m.get("accountOid");
		
		StringBuffer hql = new StringBuffer();
		
		hql.append(" from GwCash where 1=1");
		
        if(StringUtil.isNotBlank(gwLevelCode)){
			
			hql.append(" and gwLevelCode ='"+gwLevelCode+"'");
		}
        
        if(StringUtil.isNotBlank(gwLbCode)){
			
			hql.append(" and gwLbCode ='"+gwLbCode+"'");
		}
        
        if(StringUtil.isNotBlank(accountOid)){
			
			hql.append(" and accountOid ='"+accountOid+"'");
		}
		
		return DaoUtil.find(hql.toString());
	}
}
