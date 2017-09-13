package com.yh.hr.res.pb.queryhelper;

import jade.workflow.utils.ObjectUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pb.bo.PbRewardInfo;
import com.yh.hr.res.pb.dto.PbRewardInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringMap;

public class PbRewardInfoQueryHelper {

	/**
	 * hql拼装
	 * 
	 * @param params
	 * @param hql
	 * @param hqlParams
	 * @throws ServiceException
	 */
	public static void buildHQL(StringMap params, StringBuilder hql,
			HashMap<String, Object> hqlParams) throws ServiceException {
		hql.append("from PbRewardInfo where 1=1");
		String personOid = params.getAsStringEmptyNull("personOid");
		if (personOid != null) {
			hql.append(" and personOid = :personOid");
			try {
				hqlParams.put("personOid",
						ObjectUtil.getValue(personOid, java.lang.Long.class));
			} catch (jade.workflow.exception.ServiceException e) {
				e.printStackTrace();
			}
		}
		String rewardName = params.getAsStringEmptyNull("rewardName");
		if (rewardName != null) {
			hql.append(" and rewardName like :rewardName");
			hqlParams.put("rewardName", "%" + rewardName.trim() + "%");
		}
		String rewardTypeCode = params.getAsStringEmptyNull("rewardTypeCode");
		if (rewardTypeCode != null) {
			hql.append(" and rewardTypeCode like :rewardTypeCode");
			hqlParams.put("rewardTypeCode", "%" + rewardTypeCode.trim() + "%");
		}
		String honourLevel = params.getAsStringEmptyNull("honourLevel");
		if (honourLevel != null) {
			hql.append(" and honourLevel like :honourLevel");
			hqlParams.put("honourLevel", "%" + honourLevel.trim() + "%");
		}
	}

	public static List<PbRewardInfoDTO> getRewardInfoByPersonOid(Long personOid)
			throws ServiceException {
		StringBuilder hql = new StringBuilder();
		HashMap<String, Object> hqlParams = new HashMap<String, Object>();
		hql.append("from PbRewardInfo where 1=1");
		if (personOid != null) {
			hql.append(" and personOid = :personOid");
			hqlParams.put("personOid", personOid);
		}
		List<PbRewardInfoDTO> list = new ArrayList<PbRewardInfoDTO>();
		List<PbRewardInfo> boList = DaoUtil.listByCondition(hql.toString(),
				hqlParams, 0, 0);
		if (!CollectionUtils.isEmpty(boList)) {
			for (PbRewardInfo bo : boList) {
				PbRewardInfoDTO dto = new PbRewardInfoDTO();
				BeanHelper.copyProperties(bo, dto);
				list.add(dto);
			}
		}
		return list;
	}
}
