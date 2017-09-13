package com.yh.hr.orghc.ub.queryhelper;

import java.util.List;

import com.yh.hr.orghc.ub.bo.UbLeader;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.hr.orghc.ub.dto.UbLeaderDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringUtil;

/**
 * @desc 领导职数信息查询工具类
 */
public class UbLeaderQueryHelper {
	
	public static UbLeaderDTO getUbOrgDTOById(Long leaderOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(UbLeader.class, leaderOid), UbLeaderDTO.class);
	}
	
	public static List<UbLeaderDTO> listByUnitOid(Long unitOid) throws ServiceException{
		final StringBuffer hBuffer =  new StringBuffer("from UbLeader ul where 1 = 1 ");
		if(StringUtil.isNotNull(unitOid)){
			hBuffer.append(" and ul.unitOid =" +unitOid);
		}
		return BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), UbLeaderDTO.class);
	}

	/**
	 * 获取主管单位领导职级核定数
	 * @param adminUnitOid
	 * @param positionLevelCode
	 * @return num
     * @throws ServiceException 
     * @author lenghh
	 */
	public static int countAdminUnitLeader(Long adminUnitOid,String positionLevelCode) throws ServiceException {
		if(null == adminUnitOid || 0 == adminUnitOid) throw new ServiceException(null, "adminUnitOid不能为空");
		List<Long> unitOids = UbUnitQueryHelper.findUnitOidsByAdminUnitOid(adminUnitOid);
		if(CollectionUtils.isNotEmpty(unitOids)){
			StringBuilder hql = new StringBuilder().append("select sum(curCount) from UbLeader ul where 1=1");
			hql.append(" and ul.unitOid in(").append(StringUtil.arrayToSql(unitOids)).append(")");
	        if(StringUtils.isNotBlank(positionLevelCode)){
	        	hql.append(" and ul.dutyLevel =").append(positionLevelCode);
	        }
	        Object obj = DaoUtil.uniqueResult(hql.toString());
			return obj == null ? 0 : ((Number) obj).intValue();
		}
		return 0;
	}
}
