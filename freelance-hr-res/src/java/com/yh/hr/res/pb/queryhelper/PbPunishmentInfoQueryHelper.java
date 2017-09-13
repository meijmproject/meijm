package com.yh.hr.res.pb.queryhelper;

import jade.workflow.utils.ObjectUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pb.bo.PbPunishmentInfo;
import com.yh.hr.res.pb.dto.PbPunishmentInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringMap;

public class PbPunishmentInfoQueryHelper {

	/**
	 * hql拼装
	 * @param params
	 * @param hql
	 * @param hqlParams
	 * @throws ServiceException
	 */
    public static void buildHQL(StringMap params, StringBuilder hql, HashMap<String, Object> hqlParams) throws ServiceException {
        hql.append("from PbPunishmentInfo where 1=1");
        String personOid = params.getAsStringEmptyNull("personOid");
        if (personOid != null){
           	hql.append(" and personOid = :personOid");
           	try{
           		hqlParams.put("personOid", ObjectUtil.getValue(personOid, java.lang.Long.class));
        	} catch (jade.workflow.exception.ServiceException e) {
			e.printStackTrace();
			}
        }
        String punishmentName = params.getAsStringEmptyNull("punishmentName");
        if (punishmentName != null){
           hql.append(" and punishmentName like :punishmentName");
           hqlParams.put("punishmentName", "%"+punishmentName.trim()+"%");
        }
        String punishmentReason = params.getAsStringEmptyNull("punishmentReason");
        if (punishmentReason != null){
           hql.append(" and punishmentReason like :punishmentReason");
           hqlParams.put("punishmentReason", "%"+punishmentReason.trim()+"%");
        }
        String punishmentMonth = params.getAsStringEmptyNull("punishmentMonth");
        if (punishmentMonth != null){
           	hql.append(" and punishmentMonth = :punishmentMonth");
           	try{
           		hqlParams.put("punishmentMonth", ObjectUtil.getValue(punishmentMonth, java.lang.Long.class));
        	} catch (jade.workflow.exception.ServiceException e) {
			e.printStackTrace();
			}
        }
   }

	public static List<PbPunishmentInfoDTO> getPunishmentInfoByPersonOid(
			Long personOid) throws ServiceException {
		StringBuilder hql = new StringBuilder();
		HashMap<String, Object> hqlParams = new HashMap<String, Object>();
		hql.append("from PbPunishmentInfo where 1=1");
		if (personOid != null) {
			hql.append(" and personOid = :personOid");
			hqlParams.put("personOid", personOid);
		}
		List<PbPunishmentInfoDTO> list = new ArrayList<PbPunishmentInfoDTO>();
		List<PbPunishmentInfo> boList = DaoUtil.listByCondition(hql.toString(),
				hqlParams, 0, 0);
		if (!CollectionUtils.isEmpty(boList)) {
			for (PbPunishmentInfo bo : boList) {
				PbPunishmentInfoDTO dto = new PbPunishmentInfoDTO();
				BeanHelper.copyProperties(bo, dto);
				list.add(dto);
			}
		}
		return list;
	}
}
