package com.yh.hr.res.hc.queryhelper;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.hr.res.hc.bo.HcCash;
import com.yh.hr.res.hc.dto.HcCashDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.StringUtil;

/**
 * 编制资源头寸信息
 * @author	zhangqp
 * @version	1.0,	16/10/27
 */
public class HcCashQueryHelper {
	
	/**
	 * 查询编制资源头寸信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public static List<HcCash> findHcCash(HcCashDTO dto) throws ServiceException {
		
		StringBuilder hql = new StringBuilder();
		
		hql.append(" from HcCash hca where 1=1");
		
		//提供账户ID，则直接按账户ID查询
		if (NumberUtils.isNotNullOrZero(dto.getAccountOid())) {
			hql.append(" and hca.accountOid = ").append(dto.getAccountOid());
		}
		//accountCode 为账户代码，可以为单位OID
		else if (StringUtils.isNotEmpty(dto.getAccountCode())) {
			hql.append(" and hca.accountOid in (");
			hql.append(" select hac.accountOid from HcAccount hac where hac.accountCode = '").append(dto.getAccountCode()).append("' ");
			if (StringUtils.isNotEmpty(dto.getAccountType())) {
				hql.append(" and hac.accountType = '").append(dto.getAccountType()).append("' ");
			}
			hql.append(")");
		}
		
		if (StringUtils.isNotBlank(dto.getHcCode())) {
			hql.append(" and hca.hcCode = '").append(dto.getHcCode()).append("' ");
		}
		
		if (StringUtils.isNotBlank(dto.getBudgetFromCode())) {
			hql.append(" and hca.budgetFromCode = '").append(dto.getBudgetFromCode()).append("' ");
		}
		
		hql.append(" order by hca.accountOid, hca.hcCode, hca.budgetFromCode, hca.cashOid ");
		
		return DaoUtil.find(hql.toString());
	}
	
	/**
	 * 通过账户类型、账户代码、编制类型和经费来源查询编制数头寸信息
	 * @param accountType
	 * @param accountCode
	 * @param hcCode
	 * @param budgetFromCode
	 * @return
	 * @throws ServiceException
	 */
	public static HcCash findHcCash(String accountType, String accountCode, String hcCode, String budgetFromCode) throws ServiceException {
		
		StringBuilder hql = new StringBuilder();
		StringBuilder hqlHeader = new StringBuilder("select hca");
		
		hql.append(" from HcAccount hac, HcCash hca where hac.accountOid=hca.accountOid");
		
		if(StringUtil.isNotBlank(accountType)){
			
			hql.append(" and hac.accountType ='"+accountType+"'");
		}
		
        if(StringUtil.isNotBlank(accountCode)){
			
			hql.append(" and hac.accountCode ='"+accountCode+"'");
		}	
        
        if(StringUtil.isNotBlank(hcCode)){
			
			hql.append(" and hca.hcCode ='"+hcCode+"'");
		}
        
        if(StringUtil.isNotBlank(budgetFromCode)){
			
			hql.append(" and hca.budgetFromCode ='"+budgetFromCode+"'");
		}
        
        List<HcCash> list = DaoUtil.find(hqlHeader.append(hql).toString());
        if(CollectionUtils.isNotEmpty(list)) {
        	return list.get(0);
        }
        return null;
	}
}
