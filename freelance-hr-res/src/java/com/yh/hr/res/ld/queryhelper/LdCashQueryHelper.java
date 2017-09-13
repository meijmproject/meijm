package com.yh.hr.res.ld.queryhelper;

import java.util.List;

import com.yh.hr.res.ld.bo.LdCash;
import com.yh.hr.res.ld.dto.LdCashDTO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.StringUtil;

/**
 * 领导职数资源使用信息查询
 * @author	zhangqp
 * @version	1.0,	16/10/27
 */
public class LdCashQueryHelper {
	
	/**
	 * 查询领导职数资源使用信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public static List<LdCash> findLdCash(LdCashDTO dto) throws ServiceException {
		
		StringBuilder hql = new StringBuilder();
		
		hql.append(" from LdCash lca where 1=1");
		
		//提供账户ID，则直接按账户ID查询
		if (NumberUtils.isNotNullOrZero(dto.getAccountOid())) {
			hql.append(" and lca.accountOid = ").append(dto.getAccountOid());
		}
		//accountCode 为账户代码，可以为单位OID
		else if (StringUtils.isNotEmpty(dto.getAccountCode())) {
			hql.append(" and lca.accountOid in (");
			hql.append(" select lac.accountOid from LdAccount lac where lac.accountCode = '").append(dto.getAccountCode()).append("' ");
			if (StringUtils.isNotEmpty(dto.getAccountType())) {
				hql.append(" and lac.accountType = '").append(dto.getAccountType()).append("' ");
			}
			hql.append(")");
		}
		
		if (StringUtils.isNotBlank(dto.getDutyAttribute())) {
			hql.append(" and lca.dutyAttribute = '").append(dto.getDutyAttribute()).append("' ");
		}
		
		if (StringUtils.isNotBlank(dto.getDutyLevel())) {
			hql.append(" and lca.dutyLevel = '").append(dto.getDutyLevel()).append("' ");
		}
		
		hql.append(" order by lca.accountOid, lca.dutyAttribute, lca.dutyLevel, lca.cashOid ");
		
		return DaoUtil.find(hql.toString());
	}
	
	/**
	 * 通过账户类型、账户代码、职务属性和职务级别查询领导职数头寸信息
	 * @param accountType
	 * @param accountCode
	 * @param dutyAttribute
	 * @param dutyLevel
	 * @return
	 * @throws Exception
	 */
	public static LdCash findLdCash(String accountType, String accountCode, String dutyAttribute, String dutyLevel) throws ServiceException {
		StringBuilder hql = new StringBuilder();
		StringBuilder hqlHeader = new StringBuilder("select lca");
		
		hql.append(" from LdAccount lac, LdCash lca where lac.accountOid=lca.accountOid");
		
		if(StringUtil.isNotBlank(accountType)){
			
			hql.append(" and lac.accountType ='"+accountType+"'");
		}
		
        if(StringUtil.isNotBlank(accountCode)){
			
			hql.append(" and lac.accountCode ='"+accountCode+"'");
		}	
        
        if(StringUtil.isNotBlank(dutyAttribute)){
			
			hql.append(" and lca.dutyAttribute ='"+dutyAttribute+"'");
		}
        
        if(StringUtil.isNotBlank(dutyLevel)){
			
			hql.append(" and lca.dutyLevel ='"+dutyLevel+"'");
		}
        
        List<LdCash> list = DaoUtil.find(hqlHeader.append(hql).toString());
        if(CollectionUtils.isNotEmpty(list)) {
        	return list.get(0);
        }
        return null;
	}
}
