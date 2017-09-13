package com.yh.hr.res.pb.queryhelper;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pb.bo.PbInjuryInfo;
import com.yh.hr.res.pb.dto.PbInjuryInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

public class PbInjuryInfoQueryHelper {

	public static List<PbInjuryInfoDTO> getInjuryInfoByPersonOid(
			Long personOid) throws ServiceException {
		StringBuilder hql = new StringBuilder();
		HashMap<String, Object> hqlParams = new HashMap<String, Object>();
		hql.append("from PbInjuryInfo where 1=1");
		if (personOid != null) {
			hql.append(" and personOid = :personOid");
			hqlParams.put("personOid", personOid);
		}
		List<PbInjuryInfoDTO> list = new ArrayList<PbInjuryInfoDTO>();
		List<PbInjuryInfo> boList = DaoUtil.listByCondition(hql.toString(),
				hqlParams, 0, 0);
		if (!CollectionUtils.isEmpty(boList)) {
			for (PbInjuryInfo bo : boList) {
				PbInjuryInfoDTO dto = new PbInjuryInfoDTO();
				BeanHelper.copyProperties(bo, dto);
				list.add(dto);
			}
		}
		return list;
	}
}
