package com.yh.hr.res.cf.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.cf.dto.JhcCfShowResultSetDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

public class JhcCfShowResultSetQueryHelper {

	/**
	 * 通过functionCode查找表格列
	 * @param functionCode
	 * @return
	 * @throws ServiceException
	 */
	public static List<JhcCfShowResultSetDTO> find(String functionCode) throws ServiceException {
		final Map<String, Object> hqlParams = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("select j from JhcCfShowResultSet j where 1=1 and functionCode = '"+functionCode+"' order by ordrrNo");
		List<JhcCfShowResultSetDTO> list = BeanHelper.copyProperties(DaoUtil.find(hql.toString(), hqlParams),JhcCfShowResultSetDTO.class);
		return list;
		
	}

}
