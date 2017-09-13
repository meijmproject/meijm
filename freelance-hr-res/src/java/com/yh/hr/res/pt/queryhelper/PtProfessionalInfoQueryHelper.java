package com.yh.hr.res.pt.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.pt.bo.PtProfessionalInfo;
import com.yh.hr.res.pt.dto.PtProfessionalInfoDTO;
import org.springframework.util.CollectionUtils;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * @desc 职业（执业）资格信息查询工具类
 * @author lihj
 * @createDate 2016-10-26
 */
public class PtProfessionalInfoQueryHelper
{
	/**
	 * 通过主键获取
	 * @param professionalOid
	 * @return
	 * @throws ServiceException
	 */
	public static PtProfessionalInfoDTO getPtProfessionalInfoDTOById(Long professionalOid) throws ServiceException
	{
		PtProfessionalInfo ptProfessionalInfo = DaoUtil.get(PtProfessionalInfo.class, professionalOid);
		PtProfessionalInfoDTO ptProfessionalDto = new PtProfessionalInfoDTO();
		BeanHelper.copyProperties(ptProfessionalInfo, ptProfessionalDto);
		
		return ptProfessionalDto;
	}
	
	/**
	 * 通过personOid获取
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtProfessionalInfoDTO> listPtProfessionalInfoDTOByPersonId(Long personOid) throws ServiceException
	{
		String hql = "from PtProfessionalInfo fi where fi.bizPersonOid = :personOid order by fi.createdDate asc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personOid", personOid);
		List<PtProfessionalInfo> boList = DaoUtil.find(hql, params);
		List<PtProfessionalInfoDTO> dtoList = new ArrayList<PtProfessionalInfoDTO>();
		
		if(!CollectionUtils.isEmpty(boList))
		{
			for(PtProfessionalInfo bo : boList)
			{
				PtProfessionalInfoDTO dto = new PtProfessionalInfoDTO();
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
	public static int countPtProfessionalDTOByPersonId(Long personOid) throws ServiceException
	{
		String hql = "select count(*) from PtProfessionalInfo fi where fi.bizPersonOid = :personOid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personOid", personOid);
		return DaoUtil.countByCondition(hql, params);
	}

	/**
	 * 根据bizPersonOid删除职业（执业）资格信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtProfessionalInfo fi where fi.bizPersonOid = " + bizPersonOid);
	}
}
