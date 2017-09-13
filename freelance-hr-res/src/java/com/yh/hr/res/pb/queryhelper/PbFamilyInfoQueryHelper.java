package com.yh.hr.res.pb.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.yh.hr.res.pb.bo.PbFamilyInfo;
import com.yh.hr.res.pb.dto.PbFamilyInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * @desc 家庭成员与社会关系查询工具类
 * @author huangyj
 * @createDate 2016-8-17
 */
public class PbFamilyInfoQueryHelper
{
	/**
	 * 通过主键获取
	 * @param familyOid
	 * @return
	 * @throws ServiceException
	 */
	public static PbFamilyInfoDTO getPbFamilyInfoDTOById(Long familyOid) throws ServiceException
	{
		PbFamilyInfo pbFamilyInfo = DaoUtil.get(PbFamilyInfo.class, familyOid);
		PbFamilyInfoDTO pbFamilyInfoDto = new PbFamilyInfoDTO();
		BeanHelper.copyProperties(pbFamilyInfo, pbFamilyInfoDto);
		return pbFamilyInfoDto;
	}
	
	/**
	 * 通过personOid获取
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PbFamilyInfoDTO> listPbFamilyInfoDTOByPersonId(Long personOid) throws ServiceException
	{
		String hql = "from PbFamilyInfo fi where fi.personOid = :personOid order by fi.createDate asc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personOid", personOid);
		List<PbFamilyInfo> boList = DaoUtil.find(hql, params);
		List<PbFamilyInfoDTO> dtoList = new ArrayList<PbFamilyInfoDTO>();
		
		if(!CollectionUtils.isEmpty(boList))
		{
			for(PbFamilyInfo bo : boList)
			{
				PbFamilyInfoDTO dto = new PbFamilyInfoDTO();
				BeanHelper.copyProperties(bo, dto);
				dtoList.add(dto);
			}
		}
		return dtoList;
	}
	
	/**
	 * 获取查询总数
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public static int countPbFamilyInfoDTOByPersonId(Long personOid) throws ServiceException
	{
		String hql = "select count(*) from PbFamilyInfo fi where fi.personOid = :personOid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personOid", personOid);
		return DaoUtil.countByCondition(hql, params);
	}
	
	/**
	 * 根据personOid删除家庭成员信息
	 * @param personOid
	 * @throws ServiceException
	 */
	public static void deletePbFamilyInfoByPersonId(Long personOid) throws ServiceException
	{
		DaoUtil.executeUpdate("delete from PbFamilyInfo fi where fi.personOid = " + personOid);
	}
}
