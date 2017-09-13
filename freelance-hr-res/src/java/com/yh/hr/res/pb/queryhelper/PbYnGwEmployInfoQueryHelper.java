package com.yh.hr.res.pb.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.pb.bo.PbYnGwEmployInfo;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pb.dto.PbYnGwEmployInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringUtil;

/**
 * @desc 院内岗位聘任信息查询工具类
 * @author liul
 * @createDate 2017-2-17
 */
public class PbYnGwEmployInfoQueryHelper
{
	/**
	 * 不在聘信息记录的唯一校验
	 * @param PbYnGwEmployInfoDTO
	 * @return
	 * @throws ServiceException
	 */
	public static boolean checkStatus(PbYnGwEmployInfoDTO pbYnGwEmployInfoDTO) throws ServiceException {
		
		List<PbYnGwEmployInfo> contractBo= DaoUtil.find("from PbYnGwEmployInfo r where r.hisPositionStatus = '"+pbYnGwEmployInfoDTO.getHisPositionStatus()+"' and r.hisPositionStatus = '"+DicConstants.YHRS0026_001+"' and r.personOid='"+pbYnGwEmployInfoDTO.getPersonOid()+"' ");
		if(CollectionUtils.isEmpty(contractBo)){
			return true;
		}
		return false;
	}
    /**
     * 根据条件获取院内岗位聘任信息
     * @param personOid
     * @param flag
     * @return
     * @throws ServiceException 
     */
	public static PbYnGwEmployInfoDTO findYnGwEmployInfoByCond(Long personOid,
			String flag) throws ServiceException {
		StringBuffer hBuffer = new StringBuffer("from PbYnGwEmployInfo pt where  1 =1 ");
		if (StringUtil.isNotNull(personOid)) {
            hBuffer.append(" and  pt.personOid =" + personOid);
        }
		if (StringUtil.isNotBlank(flag)) {
            hBuffer.append(" and  pt.hisPositionStatus ='" + flag).append("'");
        }
		 List<PbYnGwEmployInfo> list = DaoUtil.find(hBuffer.toString());
		 if(CollectionUtils.isNotEmpty(list))
		 {
			 return BeanHelper.copyProperties(list.get(0),PbYnGwEmployInfoDTO.class);
		 }
		return null;
	}
	
	/**
	 * 根据人员id查询该人员所有的院内岗位聘任信息
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PbYnGwEmployInfoDTO> listPbYnGwEmployInfoByPersonOid(Long personOid) throws ServiceException{
		String hql = "from PbYnGwEmployInfo ei where ei.personOid = :personOid ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personOid", personOid);
		List<PbYnGwEmployInfo> boList = DaoUtil.find(hql, params);
		List<PbYnGwEmployInfoDTO> dtoList = new ArrayList<PbYnGwEmployInfoDTO>();
		if(CollectionUtils.isNotEmpty(boList))
		{
			dtoList = BeanHelper.copyProperties(boList, PbYnGwEmployInfoDTO.class);
		}
		return dtoList;
	}
	
	public static PbYnGwEmployInfoDTO findYnGwEmployInfoByCond(Long personOid,
			String hisPositionStatus,String isPartTime) throws ServiceException {
		StringBuffer hBuffer = new StringBuffer("from PbYnGwEmployInfo pt where  1 =1 ");
		if (StringUtil.isNotNull(personOid)) {
            hBuffer.append(" and  pt.personOid =" + personOid);
        }
		if (StringUtil.isNotBlank(hisPositionStatus)) {
            hBuffer.append(" and  pt.hisPositionStatus ='" + hisPositionStatus).append("'");
        }
		if (StringUtil.isNotBlank(isPartTime)) {
            hBuffer.append(" and  pt.isPartTime ='" + isPartTime).append("'");
        }
		 List<PbYnGwEmployInfo> list = DaoUtil.find(hBuffer.toString());
		 if(CollectionUtils.isNotEmpty(list))
		 {
			 return BeanHelper.copyProperties(list.get(0),PbYnGwEmployInfoDTO.class);
		 }
		return null;
	}
	
	/**
	 * 通过人员id删除院内岗位聘任信息
	 * @param personOid
	 */
	public static void deleteByPersonOid(Long personOid) throws ServiceException{
		DaoUtil.executeUpdate("delete from PbYnGwEmployInfo where personOid='"+personOid+"'");
	}
}
