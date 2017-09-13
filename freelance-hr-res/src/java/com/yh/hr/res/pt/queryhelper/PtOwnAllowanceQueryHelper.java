package com.yh.hr.res.pt.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.pt.bo.PtOwnAllowance;
import com.yh.hr.res.pt.dto.PtOwnAllowanceDTO;
import org.apache.commons.collections.CollectionUtils;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * @desc 特岗津贴信息查询工具类
 * @author huanglm
 * @createDate 2016-12-12
 */
 public class PtOwnAllowanceQueryHelper {
	 /**
		 * 通过人员ID查找该人所享受的特岗津贴信息
		 * @param educationOid
		 * @return
		 * @throws ServiceException
		 */
	public static List<PtOwnAllowanceDTO> listPtOwnAllowance(Long bizPersonOid) throws ServiceException{
		String hql = "from PtOwnAllowance oa where oa.bizPersonOid = :bizPersonOid order by oa.startDate desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		List<PtOwnAllowance> boList = DaoUtil.find(hql, params);
		List<PtOwnAllowanceDTO> dtoList = new ArrayList<PtOwnAllowanceDTO>();
		if(CollectionUtils.isNotEmpty(boList))
		{
			dtoList = BeanHelper.copyProperties(boList, PtOwnAllowanceDTO.class);
		}
		return dtoList;
	}
	/**
	 * 通过人员ID删除该人所享受的特岗津贴信息
	 * @param educationOid
	 * @return
	 * @throws ServiceException
	 */
 public static void deletePtOwnAllowanceBybizPersonOid(Long bizPersonOid) throws ServiceException{
	DaoUtil.executeUpdate("delete from PtOwnAllowance oa where oa.bizPersonOid = " + bizPersonOid);
}
	
	/**
	 * 通过主键获取
	 * @param educationOid
	 * @return
	 * @throws ServiceException
	 */
	public static PtOwnAllowanceDTO getPtOwnAllowanceDTOById(Long ownAllowanceOid) throws ServiceException
	{
		PtOwnAllowance ptOwnAllowance = DaoUtil.get(PtOwnAllowance.class, ownAllowanceOid);
		return BeanHelper.copyProperties(ptOwnAllowance, PtOwnAllowanceDTO.class);
	}	
	/**
	 * 通过操作类型获取
	 * @param handleCode
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtOwnAllowanceDTO> listPtOwnAllowanceByHandleCode(java.lang.String handleCode) throws ServiceException
	{
		String hql = "from PtOwnAllowance oa where oa.handleCode = :handleCode order by oa.startDate desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("handleCode", handleCode);
		List<PtOwnAllowance> list = DaoUtil.find(hql, params);
		List<PtOwnAllowanceDTO> dtoList = new ArrayList<PtOwnAllowanceDTO>();
		if(CollectionUtils.isNotEmpty(list))
		{
			dtoList = BeanHelper.copyProperties(list, PtOwnAllowanceDTO.class);
		}
		return dtoList;
	}
	/**
	 * 通过对应识别码获取
	 * @param handleCode
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtOwnAllowanceDTO> listPtOwnAllowanceByHandleMatchNo(java.lang.Long handleMatchNo) throws ServiceException
	{
		String hql = "from PtOwnAllowance oa where oa.handleMatchNo = :handleMatchNo order by oa.startDate desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("handleMatchNo", handleMatchNo);
		List<PtOwnAllowance> list = DaoUtil.find(hql, params);
		List<PtOwnAllowanceDTO> dtoList = new ArrayList<PtOwnAllowanceDTO>();
		if(CollectionUtils.isNotEmpty(list))
		{
			dtoList = BeanHelper.copyProperties(list, PtOwnAllowanceDTO.class);
		}
		return dtoList;
	}
	/**
	 * 获取查询总数
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public static int countPtOwnAllowanceDTOByBizPersonId(Long bizPersonOid) throws ServiceException
	{
		String hql = "select count(*) from PtOwnAllowance oa where oa.bizPersonOid = :bizPersonOid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		return DaoUtil.countByCondition(hql, params);
	}
 }