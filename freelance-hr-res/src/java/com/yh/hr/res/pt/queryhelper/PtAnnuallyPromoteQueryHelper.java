package com.yh.hr.res.pt.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.pt.bo.PtAnnuallyPromote;
import com.yh.hr.res.pt.dto.PtAnnuallyPromoteDTO;
import org.springframework.util.CollectionUtils;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
/**
 * 年度考核QueryHelper
 * @author huanglm
 * 时间:2016-11-30
 */
public class PtAnnuallyPromoteQueryHelper {
	/**
	 * 根据bizPersonOid查业务库年度考核信息
	 * 
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static List<PtAnnuallyPromoteDTO> getPtAnnuallyPromoteDTOById(Long bizPersonOid) throws ServiceException {
		String hql = "from PtAnnuallyPromote pt where pt.bizPersonOid = :bizPersonOid order by pt.promoteYear asc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		List<PtAnnuallyPromote> boList = DaoUtil.find(hql, params);
		List<PtAnnuallyPromoteDTO> dtoList = new ArrayList<PtAnnuallyPromoteDTO>();
		
		if(!CollectionUtils.isEmpty(boList))
		{
			for(PtAnnuallyPromote bo : boList)
			{
				PtAnnuallyPromoteDTO dto = new PtAnnuallyPromoteDTO();
				BeanHelper.copyProperties(bo, dto);
				dtoList.add(dto);
			}
		}
		
		return dtoList;
	}
	/**
	 * 根据年度查业务库年度考核信息
	 * 
	 * @param promoteYear
	 * @throws ServiceException
	 */
	public static List<PtAnnuallyPromoteDTO> getPtAnnuallyPromoteDTOByYear(java.lang.Integer promoteYear) throws ServiceException {
		String hql = "from PtAnnuallyPromote pt where pt.promoteYear = :promoteYear ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("promoteYear", promoteYear);
		List<PtAnnuallyPromote> boList = DaoUtil.find(hql, params);
		List<PtAnnuallyPromoteDTO> dtoList = new ArrayList<PtAnnuallyPromoteDTO>();
		
		if(!CollectionUtils.isEmpty(boList))
		{
			for(PtAnnuallyPromote bo : boList)
			{
				PtAnnuallyPromoteDTO dto = new PtAnnuallyPromoteDTO();
				BeanHelper.copyProperties(bo, dto);
				dtoList.add(dto);
			}
		}
		
		return dtoList;
	}

	/**
	 * 根据ptAnnuallyPromoteInfoOid主键查业务库年度考核信息
	 * 
	 * @param ptAnnuallyPromoteInfoOid
	 * @throws ServiceException
	 */
	public static PtAnnuallyPromoteDTO getPtAnnuallyPromoteDTOByMainId(Long ptAnnuallyPromoteInfoOid) throws ServiceException {
		PtAnnuallyPromote ptAnnuallyPromote = DaoUtil.get(PtAnnuallyPromote.class, ptAnnuallyPromoteInfoOid);
		if(null != ptAnnuallyPromote)
		{
			PtAnnuallyPromoteDTO dto = BeanHelper.copyProperties(ptAnnuallyPromote, PtAnnuallyPromoteDTO.class);			
			return dto;
		}
		else 
		{
			return null;
		}

	}
	/**
	 * 根据ptAnnuallyPromoteInfoOid主键删除人员年度考核信息
	 * 
	 * @param ptAnnuallyPromoteInfoOid
	 * @throws ServiceException
	 */
	public static void deleteByMainId(Long ptAnnuallyPromoteInfoOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtAnnuallyPromote pt where pt.ptAnnuallyPromoteInfoOid = " + ptAnnuallyPromoteInfoOid);
	}		
	/**
	 * 根据bizPersonOid删除人员年度考核信息
	 * 
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtAnnuallyPromote pt where pt.bizPersonOid = " + bizPersonOid);
	}	
	

}
