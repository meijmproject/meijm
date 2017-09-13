package com.yh.hr.res.pt.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.pt.bo.PtPersonInOther;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.hr.res.pt.dto.PtPersonInOtherDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * @desc 人员其他转任信息工具类
 * @author lihj
 * @createDate 2016-10-26
 */
public class PtPersonInOtherQueryHelper {
	/*
	 * 通过ID获取
	 */
	public static PtPersonInOtherDTO getPtPersonInOtherDTOById(Long bizPersonInOtherOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtPersonInOther.class, bizPersonInOtherOid), PtPersonInOtherDTO.class);
	}
	
	/**
	 * 通过bizPersonOid获取
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtPersonInOtherDTO> listPtPersonInOtherDTOByBizPersonOid(Long bizPersonOid) throws ServiceException
	{
		String hql = "from PtPersonInOther pio where pio.bizPersonOid = :bizPersonOid order by pio.createdDate desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		List<PtPersonInOther> boList = DaoUtil.find(hql, params);
		List<PtPersonInOtherDTO> dtoList = new ArrayList<PtPersonInOtherDTO>();
		if(CollectionUtils.isNotEmpty(boList))
		{
			dtoList = BeanHelper.copyProperties(boList, PtPersonInOtherDTO.class);
		}
		
		return dtoList;
	}
	
	/**
	 * 通过人员，证件号码，证件类型查询人员信息
	 * @param idCode
	 * @param idNo
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtPersonInOtherDTO> checkUniquePbPerson(String idCode,String idNo,Long bizPersonInOtherOid) throws ServiceException {
		if(StringUtils.isBlank(idCode)||StringUtils.isBlank(idNo))
		{
			throw new ServiceException(null,"缺少查询条件!");
		}
		String hql = "from PtPersonInOther pt where pt.personStatus in('110','120','130','206','207','208','209','210','300','399') and pt.idCode='"+idCode +"' and pt.idNo='"+idNo+"'";
		if (null!=bizPersonInOtherOid) {
			hql+=(" and pt.bizPersonOid !="+bizPersonInOtherOid);
		}
		return BeanHelper.copyProperties(DaoUtil.find(hql), PtPersonInOtherDTO.class);
	}

	
	/**
	 * 通过证件号码，证件类型查询人员信息
	 * @param idCode
	 * @param idNo
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtPersonInOtherDTO> checkUniquePbPerson(String idCode,String idNo) throws ServiceException {
		if(StringUtils.isBlank(idCode)||StringUtils.isBlank(idNo))
		{
			throw new ServiceException(null,"缺少查询条件!");
		}
		String hql = "from PtPersonInOther pt where  pt.idCode='"+idCode +"' and pt.idNo='"+idNo+"'";
		return BeanHelper.copyProperties(DaoUtil.find(hql), PtPersonInOtherDTO.class);
	}
	
	/**
	 * 根据bizPersonOid删除人员转任信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtPersonInOther pt where pt.bizPersonOid = " + bizPersonOid);
	}
	
	/*
	 * 通过bizPersonInOtherOid获取
	 */
	public static List<PtPersonInOtherDTO> listPtPersonInOtherDTO(Long bizPersonInOtherOid) throws ServiceException {
		final StringBuffer hBuffer =  new StringBuffer("from PtPersonIn pt where  1 =1 ");
		hBuffer.append(" and  pt.bizPersonInOtherOid =" + bizPersonInOtherOid);//必须是按人来查询
		return BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), PtPersonInOtherDTO.class);
	}
	
	public static PtPersonInOtherDTO getPtPersonInOtherDTO(Long bizPersonOid) throws ServiceException {
		final StringBuffer hBuffer =  new StringBuffer("from PtPersonInOther pt where  1 =1 ");
		hBuffer.append(" and  pt.bizPersonOid =" + bizPersonOid);//必须是按人来查询
		return BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()).get(0), PtPersonInOtherDTO.class);
	}
	
	public static PtPersonInOther getPtPersonInOther(Long bizPersonOid) throws ServiceException {
		return DaoUtil.uniqueResult(" from PtPersonInOther poi where poi.bizPersonOid = " + bizPersonOid);
	}
}