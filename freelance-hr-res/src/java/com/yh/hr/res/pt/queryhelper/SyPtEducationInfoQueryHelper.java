package com.yh.hr.res.pt.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.pt.bo.PtEducationInfo;
import com.yh.hr.res.pt.dto.PtEducationInfoDTO;
import org.apache.commons.collections.CollectionUtils;

import com.yh.platform.core.constant.Constant;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 学历学位信息查询工具类（业务）
 * @author zhengdr
 *
 * 时间:2016-10-9下午03:09:39
 */
public class SyPtEducationInfoQueryHelper
{
	/**
	 * 通过主键获取
	 * @param ptEducationOid
	 * @return
	 * @throws ServiceException
	 */
	public static PtEducationInfoDTO getPtEducationDTOById(Long ptEducationOid) throws ServiceException
	{
		PtEducationInfo ptEducationInfo = DaoUtil.get(PtEducationInfo.class,ptEducationOid);
		return BeanHelper.copyProperties(ptEducationInfo, PtEducationInfoDTO.class);
	}
	
	/**
	 * 通过bizPersonOid获取
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtEducationInfoDTO> listPtEducationDTOByBizPersonOid(Long bizPersonOid) throws ServiceException
	{
		String hql = "from PtEducationInfo ei where ei.bizPersonOid = :bizPersonOid order by ei.graduateDate desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		List<PtEducationInfo> boList = DaoUtil.find(hql, params);
		List<PtEducationInfoDTO> dtoList = new ArrayList<PtEducationInfoDTO>();
		if(CollectionUtils.isNotEmpty(boList))
		{
			dtoList = BeanHelper.copyProperties(boList, PtEducationInfoDTO.class);
		}
		
		return dtoList;
	}
	
	/**
	 * 获取查询总数
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static int countPtEducationDTOByBizPersonId(Long bizPersonOid) throws ServiceException
	{
		String hql = "select count(*) from PtEducationInfo ei where ei.bizPersonOid = :bizPersonOid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		return DaoUtil.countByCondition(hql, params);
	}

	/**
	 * 根据bizPersonOid删除学历学位信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtEducationInfo ei where ei.bizPersonOid = " + bizPersonOid);
	}
	
	/*
	 * 通过标识flag查询在职或全日制学历信息集
	 */
	/*public static List<PtEducationInfoDTO> listPbEducationDTOByTypeCode(Long bizPersonOid, boolean type) throws ServiceException{
		final StringBuffer hql = new StringBuffer(" from PtEducationInfo ei where ei.bizPersonOid = " +bizPersonOid);
		if(type){
			hql.append(" and ei.studyTypeCode = '" + DicConstants.YHRS0042_01 + "'");
		}else{
			hql.append(" and ei.studyTypeCode <> '" + DicConstants.YHRS0042_01 + "'");
		}
		return BeanHelper.copyProperties(DaoUtil.find(hql.toString()), PtEducationInfoDTO.class);
	}*/
	
	/**
	 * 根据人员id获取毕业院校及专业信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	/*public static PtEducationInfoDTO getSchoolInfoByPersonOid(Long bizPersonOid) throws ServiceException
	{
		String sql = "select pt.qrz_xxzy,pt.zz_xxzy from yhc_pb_temp pt where pt.person_oid = " + bizPersonOid;
		Object obj = DaoUtil.uniqueResultWithSQL(sql);
		if(null != obj)
		{
			Object[] array = (Object[])obj;
			PtEducationInfoDTO PtEducationInfoDto = new PtEducationInfoDTO();
			PtEducationInfoDto.setQrzXxzy(array[0] == null ? null : array[0].toString());
			PtEducationInfoDto.setZzXxzy(array[1] == null ? null : array[1].toString());
			return PtEducationInfoDto;
		}
		return null;
	}*/
	
	
	
	/**
	 * 当flag为true时，批量更新最高学历或学位标识
	 */
	public static void updateIsHighFlag(Long bizPersonOid,Long educationOid,boolean flag) throws ServiceException{
		final StringBuffer hql = new StringBuffer(" update  PtEducationInfo ei ");
		if(flag){
			hql.append(" set ei.isHighestEducationLevel = '" +Constant.NO+ "'");
		}else{
			hql.append(" set ei.isHighestDegree = '" +Constant.NO+ "'");
		}
		hql.append(" where  ei.educationOid <> " +educationOid);
		hql.append(" and  ei.bizPersonOid =  " +bizPersonOid);
		DaoUtil.executeUpdate(hql.toString());
	}
}
