package com.yh.hr.res.pt.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pt.bo.PtYnGwEmployInfo;
import com.yh.hr.res.pt.dto.PtYnGwEmployInfoDTO;
import org.apache.commons.collections.CollectionUtils;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringUtil;

/**
 * @desc 院内岗位聘任业务信息查询工具类
 * @author liul
 * @createDate 2017-2-17
 */
public class PtYnGwEmployInfoQueryHelper
{
	/**
	 * 不在聘信息记录的唯一校验
	 * @param PtYnGwEmployInfoDTO
	 * @return
	 * @throws ServiceException
	 */
	public static boolean checkStatus(PtYnGwEmployInfoDTO ptYnGwEmployInfoDTO) throws ServiceException {
		
		List<PtYnGwEmployInfo> contractBo= DaoUtil.find("from PtYnGwEmployInfo r where r.hisPositionStatus = '"+ptYnGwEmployInfoDTO.getHisPositionStatus()+"' and r.hisPositionStatus = '"+ DicConstants.YHRS0026_001+"' and r.bizPersonOid='"+ptYnGwEmployInfoDTO.getBizPersonOid()+"' ");
		if(CollectionUtils.isEmpty(contractBo)){
			return true;
		}
		return false;
	}
    /**
     * 根据条件获取院内岗位聘任业务信息
     * @param bizPersonOid
     * @param flag
     * @return
     * @throws ServiceException 
     */
	public static PtYnGwEmployInfoDTO findPtYnGwEmployInfoByCond(Long bizPersonOid,
			String flag) throws ServiceException {
		StringBuffer hBuffer = new StringBuffer("from PtYnGwEmployInfo pt where  1 =1 ");
		if (StringUtil.isNotNull(bizPersonOid)) {
            hBuffer.append(" and  pt.bizPersonOid =" + bizPersonOid);
        }
		if (StringUtil.isNotBlank(flag)) {
            hBuffer.append(" and  pt.hisPositionStatus ='" + flag).append("'");
        }
		 List<PtYnGwEmployInfo> list = DaoUtil.find(hBuffer.toString());
		 if(CollectionUtils.isNotEmpty(list))
		 {
			 return BeanHelper.copyProperties(list.get(0),PtYnGwEmployInfoDTO.class);
		 }
		return null;
	}
	
	/**
	 * 根据业务人员OID查询该人员所有的院内岗位聘任业务信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtYnGwEmployInfoDTO> listPtYnGwEmployInfoByBizPersonOid(Long bizPersonOid) throws ServiceException{
		String hql = "from PtYnGwEmployInfo ei where ei.bizPersonOid = :bizPersonOid ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		List<PtYnGwEmployInfo> boList = DaoUtil.find(hql, params);
		List<PtYnGwEmployInfoDTO> dtoList = new ArrayList<PtYnGwEmployInfoDTO>();
		if(CollectionUtils.isNotEmpty(boList))
		{
			dtoList = BeanHelper.copyProperties(boList, PtYnGwEmployInfoDTO.class);
		}
		return dtoList;
	}
	
	/**
	 * 根据基础OID查询该人员所有的院内岗位聘任业务信息
	 * @param baseYnGwEmployOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtYnGwEmployInfoDTO> listPtYnGwEmployInfoByBaseYnGwEmployOid(Long baseYnGwEmployOid) throws ServiceException{
		String hql = "from PtYnGwEmployInfo ei where ei.baseYnGwEmployOid = :baseYnGwEmployOid ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("baseYnGwEmployOid", baseYnGwEmployOid);
		List<PtYnGwEmployInfo> boList = DaoUtil.find(hql, params);
		List<PtYnGwEmployInfoDTO> dtoList = new ArrayList<PtYnGwEmployInfoDTO>();
		if(CollectionUtils.isNotEmpty(boList))
		{
			dtoList = BeanHelper.copyProperties(boList, PtYnGwEmployInfoDTO.class);
		}
		return dtoList;
	}

	/**
	 * 根据业务人员OID删除该人员院内岗位聘任业务信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtYnGwEmployInfo where bizPersonOid = " + bizPersonOid);
	}	
}
