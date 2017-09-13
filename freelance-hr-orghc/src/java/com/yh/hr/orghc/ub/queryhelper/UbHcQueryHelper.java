package com.yh.hr.orghc.ub.queryhelper;

import java.util.List;

import com.yh.hr.orghc.ub.dto.UbHcDTO;
import org.apache.commons.lang.StringUtils;

import com.yh.hr.orghc.ub.bo.UbHc;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringUtil;

/**
 * @desc 编制信息查询工具类
 */

public class UbHcQueryHelper {
	
	public static UbHcDTO getUbHcDTOById(Long hcOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(UbHc.class, hcOid), UbHcDTO.class);
	}
	
	public static List<UbHcDTO> listByUnitOid(Long unitOid) throws ServiceException{
		final StringBuffer hBuffer =  new StringBuffer("from UbHc uh where 1 = 1 ");
		if(StringUtil.isNotNull(unitOid)){
			hBuffer.append(" and uh.unitOid =" +unitOid);
		}
		return BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), UbHcDTO.class);
	}

	/**
	 * 根据编制OID统计编制核定数
	 * @param unitOid
	 * @param hcOid
	 * @return num
	 * @throws ServiceException
	 */
	public static int countHcByHcCode(Long unitOid, String hcCode) throws ServiceException {
		final StringBuffer hBuffer =  new StringBuffer("select sum(uh.curCount) from UbHc uh where 1 = 1 ");
		if(StringUtil.isNotNull(unitOid)){
			hBuffer.append(" and uh.unitOid =" +unitOid);
		}
		if(StringUtils.isNotEmpty(hcCode)){
			hBuffer.append(" and uh.hcCode = '" +hcCode + "'");
		}
		Object obj = DaoUtil.uniqueResult(hBuffer.toString());
		return obj == null ? 0 : ((Number) obj).intValue();
	}
}
