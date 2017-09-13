package com.yh.hr.res.gw.queryhelper;

import java.util.List;

import com.yh.hr.res.gw.bo.GwCash;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.hr.res.gw.dto.GwCashDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.StringUtil;

/**
 * 岗位数头寸信息查询
 * @author wangx
 * @created 2016-12-22
 * @version 1.0
 */
public class GwCashQueryHelper {

	/**
	 * 查询岗位数头寸信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public static List<GwCash> findGwCash(GwCashDTO dto) throws ServiceException {
		
		StringBuilder hql = new StringBuilder();
		
		hql.append(" from GwCash gc where 1=1");
		
		//提供账户ID，则直接按账户ID查询
		if (NumberUtils.isNotNullOrZero(dto.getAccountOid())) {
			hql.append(" and gc.accountOid = ").append(dto.getAccountOid());
		}
		//accountCode 为账户代码，可以为单位OID
		else if (StringUtils.isNotEmpty(dto.getAccountCode())) {
			hql.append(" and gc.accountOid in (");
			hql.append(" select ga.accountOid from GwAccount ga where ga.accountCode = '").append(dto.getAccountCode()).append("' ");
			if (StringUtils.isNotEmpty(dto.getAccountType())) {
				hql.append(" and ga.accountType = '").append(dto.getAccountType()).append("' ");
			}
			hql.append(")");
		}
		
		if (StringUtils.isNotBlank(dto.getGwLbCode())) {
			hql.append(" and gc.gwLbCode = '").append(dto.getGwLbCode()).append("' ");
		}
		
		if (StringUtils.isNotBlank(dto.getGwLevelCode())) {
			hql.append(" and gc.gwLevelCode = '").append(dto.getGwLevelCode()).append("' ");
		}
		
		hql.append(" order by gc.accountOid, gc.gwLbCode, gc.gwLevelCode, gc.cashOid ");
		
		return DaoUtil.find(hql.toString());
	}
	
	/**
	 * 通过账户类型、账户代码、岗位类别和岗位级别查询岗位数头寸信息
	 * @param accountType
	 * @param accountCode
	 * @param gwLbCode
	 * @param gwLevelCode
	 * @return
	 * @throws ServiceException
	 */
	public static GwCash findGwCash(String accountType, String accountCode, String gwLbCode, String gwLevelCode) throws ServiceException {
		
		StringBuilder hql = new StringBuilder();
		StringBuilder hqlHeader = new StringBuilder("select gc");
		
		hql.append(" from GwAccount ga, GwCash gc where ga.accountOid=gc.accountOid");
		
		if(StringUtil.isNotBlank(accountType)){
			
			hql.append(" and ga.accountType ='"+accountType+"'");
		}
		
        if(StringUtil.isNotBlank(accountCode)){
			
			hql.append(" and ga.accountCode ='"+accountCode+"'");
		}	
        
        if(StringUtil.isNotBlank(gwLbCode)){
			
			hql.append(" and gc.gwLbCode ='"+gwLbCode+"'");
		}
        
        if(StringUtil.isNotBlank(gwLevelCode)){
			
			hql.append(" and gc.gwLevelCode ='"+gwLevelCode+"'");
		}
        
        List<GwCash> list = DaoUtil.find(hqlHeader.append(hql).toString());
        if(CollectionUtils.isNotEmpty(list)) {
        	return list.get(0);
        }
        return null;
	}
}
