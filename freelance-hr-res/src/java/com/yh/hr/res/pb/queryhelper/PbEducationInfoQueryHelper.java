package com.yh.hr.res.pb.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pb.bo.PbEducationLevelDegree;
import com.yh.hr.res.pb.dto.PbEducationLevelDegreeDTO;
import com.yh.platform.core.constant.Constant;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

public class PbEducationInfoQueryHelper {
	/**
	 * 通过personOid获取
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PbEducationLevelDegreeDTO> listPbEducationDTOByPersonId(Long personOid) throws ServiceException
	{
		String hql = "from PbEducationInfo ei where ei.personOid = :personOid order by ei.graduateDate desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personOid", personOid);
		List<PbEducationLevelDegree> boList = DaoUtil.find(hql, params);
		List<PbEducationLevelDegreeDTO> dtoList = new ArrayList<PbEducationLevelDegreeDTO>();
		if(CollectionUtils.isNotEmpty(boList))
		{
			dtoList = BeanHelper.copyProperties(boList, PbEducationLevelDegreeDTO.class);
		}
		return dtoList;
	}
	
	
	/*
	 * 当flag为true时，批量更新最高学历或学位标识
	 */
	public static void updateIsHighFlag(Long personOid,Long educationOid,boolean flag) throws ServiceException{
		final StringBuffer hql = new StringBuffer(" update  PbEducationInfo ei ");
		if(flag){
			hql.append(" set ei.isHighestEducationLevel = '" +Constant.NO+ "'");
		}else{
			hql.append(" set ei.isHighestDegree = '" +Constant.NO+ "'");
		}
		hql.append(" where  ei.educationOid <> " +educationOid);
		hql.append(" and  ei.personOid =  " +personOid);
		DaoUtil.executeUpdate(hql.toString());
	}
}
