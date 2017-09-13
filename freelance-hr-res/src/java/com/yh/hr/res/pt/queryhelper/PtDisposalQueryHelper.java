package com.yh.hr.res.pt.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pt.bo.PtDisposal;
import com.yh.hr.res.pt.dto.PtDisposalDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;


public class PtDisposalQueryHelper {
	/*
	 * 通过ID获取
	 */
	public static PtDisposalDTO getPtDisposalDTOById(Long ptDiposalOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtDisposal.class, ptDiposalOid), PtDisposalDTO.class);
	}
	
	/**
	 * 通过bizPersonOid获取
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtDisposalDTO> listPtDisposalDTOByBizPersonOid(Long bizPersonOid) throws ServiceException
	{
		String hql = "from PtDisposal pio where pio.bizPersonOid = :bizPersonOid order by pio.disposalDate desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		List<PtDisposal> boList = DaoUtil.find(hql, params);
		List<PtDisposalDTO> dtoList = new ArrayList<PtDisposalDTO>();
		if(CollectionUtils.isNotEmpty(boList))
		{
			dtoList = BeanHelper.copyProperties(boList, PtDisposalDTO.class);
		}
		
		return dtoList;
	}
	
	/**
	 * 根据ptDiposalOid删除人员转任信息
	 * @param ptDiposalOid
	 * @throws ServiceException
	 */
	public static void deleteByPtDiposalOid(Long ptDiposalOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtDisposal pt where pt.ptDiposalOid = " + ptDiposalOid);
	}
	/**
	 * 根据bizPersonOid删除人员任职信息
	 * 
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtDisposal poi where poi.bizPersonOid = " + bizPersonOid);
	}
	public static PtDisposal getPtDisposal(Long bizPersonOid) throws ServiceException {
		return DaoUtil.uniqueResult(" from PtDisposal poi where poi.bizPersonOid = " + bizPersonOid);
	}
}