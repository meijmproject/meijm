package com.yh.hr.res.pt.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.yh.hr.res.pt.bo.PtWageBereavedAllowance;
import com.yh.hr.res.pt.dto.PtWageBereavedAllowanceDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
/**
 * 遗属生活困难补助QueryHelper
 * @author huanglm
 * 时间:2016-11-30
 */
public class PtWageBereavedAllowanceQueryHelper {
	/**
	 * 根据bizPersonOid查业务库遗属及生活困难补助信息
	 * 
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static List<PtWageBereavedAllowanceDTO> getPtWageBereavedAllowanceDTOById(Long bizPersonOid) throws ServiceException {
		String hql = "from PtWageBereavedAllowance pa where pa.bizPersonOid = :bizPersonOid order by pa.createdDate asc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		List<PtWageBereavedAllowance> boList = DaoUtil.find(hql, params);
		List<PtWageBereavedAllowanceDTO> dtoList = new ArrayList<PtWageBereavedAllowanceDTO>();
		
		if(!CollectionUtils.isEmpty(boList))
		{
			for(PtWageBereavedAllowance bo : boList)
			{
				PtWageBereavedAllowanceDTO dto = new PtWageBereavedAllowanceDTO();
				BeanHelper.copyProperties(bo, dto);
				dtoList.add(dto);
			}
		}
		
		return dtoList;
	}

	/**
	 * 根据ptBereavedAllowanceInfoOid主键查业务库遗属及生活困难补助信息
	 * 
	 * @param ptBereavedAllowanceInfoOid
	 * @throws ServiceException
	 */
	public static PtWageBereavedAllowanceDTO getPtWageBereavedAllowanceDTOByMainId(Long ptBereavedAllowanceInfoOid) throws ServiceException {
		PtWageBereavedAllowance ptWageBereavedAllowance = DaoUtil.get(PtWageBereavedAllowance.class, ptBereavedAllowanceInfoOid);
		if(null != ptWageBereavedAllowance)
		{
			PtWageBereavedAllowanceDTO dto = BeanHelper.copyProperties(ptWageBereavedAllowance, PtWageBereavedAllowanceDTO.class);			
			return dto;
		}
		else 
		{
			return null;
		}

	}
	/**
	 * 根据ptBereavedAllowanceInfoOid主键删除人员遗属及生活困难补助信息
	 * 
	 * @param ptBereavedAllowanceInfoOid
	 * @throws ServiceException
	 */
	public static void deleteByMainId(Long ptBereavedAllowanceInfoOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtWageBereavedAllowance pt where pt.ptBereavedAllowanceInfoOid = " + ptBereavedAllowanceInfoOid);
	}		
	/**
	 * 根据bizPersonOid删除人员遗属及生活困难补助信息
	 * 
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtWageBereavedAllowance pt where pt.bizPersonOid = " + bizPersonOid);
	}	
	

}
