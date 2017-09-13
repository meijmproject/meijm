package com.yh.hr.res.pt.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.pt.bo.PtProfTechInfo;
import org.springframework.util.CollectionUtils;

import com.yh.hr.res.pt.dto.PtProfTechInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * @desc 专业技术资格信息查询工具类
 * @author lihj
 * @createDate 2016-10-26
 */
public class PtProfTechInfoQueryHelper
{
	/**
	 * 通过主键获取
	 * @param ptProfTechOid
	 * @return
	 * @throws ServiceException
	 */
	public static PtProfTechInfoDTO getPtProfTechInfoDTOById(Long ptProfTechOid) throws ServiceException
	{
		PtProfTechInfo ptProfTechInfo = DaoUtil.get(PtProfTechInfo.class, ptProfTechOid);
		PtProfTechInfoDTO ptProfTechInfoDto = new PtProfTechInfoDTO();
		BeanHelper.copyProperties(ptProfTechInfo, ptProfTechInfoDto);
		
		return ptProfTechInfoDto;
	}
	
	/**
	 * 通过personOid获取
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtProfTechInfoDTO> listPtProfTechInfoDTOByPersonId(Long personOid) throws ServiceException
	{
		String hql = "from PtProfTechInfo fi where fi.bizPersonOid = :personOid order by fi.createdDate asc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personOid", personOid);
		List<PtProfTechInfo> boList = DaoUtil.find(hql, params);
		List<PtProfTechInfoDTO> dtoList = new ArrayList<PtProfTechInfoDTO>();
		
		if(!CollectionUtils.isEmpty(boList))
		{
			for(PtProfTechInfo bo : boList)
			{
				PtProfTechInfoDTO dto = new PtProfTechInfoDTO();
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
	public static int countPtProfTechInfoDTOByPersonId(Long personOid) throws ServiceException
	{
		String hql = "select count(*) from PtProfTechInfo fi where fi.bizPersonOid = :personOid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personOid", personOid);
		return DaoUtil.countByCondition(hql, params);
	}

	/**
	 * 根据bizPersonOid删除专业技术资格信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtProfTechInfo fi where fi.bizPersonOid = " + bizPersonOid);
	}
}
