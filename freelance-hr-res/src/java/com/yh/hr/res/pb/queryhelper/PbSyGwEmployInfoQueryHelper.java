package com.yh.hr.res.pb.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.pb.bo.PbSyGwEmployInfo;
import com.yh.hr.res.pb.dto.PbSyGwEmployInfoDTO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringUtil;

/**
 * @desc 事业岗位聘任信息查询工具类
 * @author liul
 * @createDate 2017-2-17
 */
public class PbSyGwEmployInfoQueryHelper
{
	/**
	 * 同一岗位类别不在聘信息记录的唯一校验
	 * @param PbSyGwEmployInfoDTO
	 * @return
	 * @throws ServiceException
	 */
	public static boolean checkStatus(PbSyGwEmployInfoDTO pbSyGwEmployInfoDTO) throws ServiceException {
		List<PbSyGwEmployInfo> contractBo = new ArrayList<PbSyGwEmployInfo>();
			//同一岗位只有一笔在聘
			contractBo= DaoUtil.find("from PbSyGwEmployInfo r where r.positioningStatus = '"+pbSyGwEmployInfoDTO.getPositioningStatus()+"' and r.positionType = '"+pbSyGwEmployInfoDTO.getPositionType()+"' and r.positioningStatus = '"+DicConstants.YHRS0026_001+"' and r.personOid='"+pbSyGwEmployInfoDTO.getPersonOid()+"' ");
		
		
		if(CollectionUtils.isEmpty(contractBo)){
			return true;
		}
		return false;
	}
	/**
	 * 是否存在在聘
	 * @param PbSyGwEmployInfoDTO
	 * @return
	 * @throws ServiceException
	 */
	public static boolean haveOrNot(PbSyGwEmployInfoDTO pbSyGwEmployInfoDTO) throws ServiceException {
		/*
		List<PbSyGwEmployInfo> contractBo = new ArrayList<PbSyGwEmployInfo>();
			contractBo= DaoUtil.find("from PbSyGwEmployInfo r where  r.positioningStatus = '"+DicConstants.YHRS0026_001+"' and r.personOid='"+pbSyGwEmployInfoDTO.getPersonOid()+"' ");
		
			if(!CollectionUtils.isEmpty(contractBo)){
			return true;
		}
		return false;
		*/
		
		StringBuffer sql= new StringBuffer();
		sql.append("from PbSyGwEmployInfo r where  r.positioningStatus = '"+DicConstants.YHRS0026_001+"' and r.personOid='"+pbSyGwEmployInfoDTO.getPersonOid()+"' ");
		if(pbSyGwEmployInfoDTO.getSyGwEmployOid()!=null){
			sql.append(" and r.syGwEmployOid !='"+pbSyGwEmployInfoDTO.getSyGwEmployOid()+"'");
		}
		List<PbSyGwEmployInfo> contractBo = DaoUtil.find(sql.toString());
		if(!CollectionUtils.isEmpty(contractBo)){
			return true;
		}
		return false;
	}
	/**
	 *  双肩挑主岗位只能有一笔验证
	 * @param PbSyGwEmployInfoDTO
	 * @return
	 * @throws ServiceException
	 */
	public static boolean checkIsMPosition(PbSyGwEmployInfoDTO pbSyGwEmployInfoDTO) throws ServiceException {
		StringBuffer sql= new StringBuffer();
		sql.append("from PbSyGwEmployInfo r where r.isMPosition='1'  and r.positioningStatus = '"+DicConstants.YHRS0026_001+"' and r.personOid='"+pbSyGwEmployInfoDTO.getPersonOid()+"' ");
		if(pbSyGwEmployInfoDTO.getSyGwEmployOid()!=null){
			sql.append(" and r.syGwEmployOid !='"+pbSyGwEmployInfoDTO.getSyGwEmployOid()+"'");
		}
		List<PbSyGwEmployInfo> isMPositionBo= DaoUtil.find(sql.toString());
		if(CollectionUtils.isEmpty(isMPositionBo)){
			return true;
		}
		return false;
	}
	/**
	 *  双肩挑岗位验证
	 * @param PbSyGwEmployInfoDTO
	 * @return
	 * @throws ServiceException
	 */
	public static boolean checkTwoDuty(PbSyGwEmployInfoDTO pbSyGwEmployInfoDTO) throws ServiceException {
		
		//查询是否已存在在聘的工勤岗 若有 则返回错误信息
		List<PbSyGwEmployInfo> twoDutyBoGq= DaoUtil.find("from PbSyGwEmployInfo r where r.positionType = '"+DicConstants.YHRS0022_3+"' and r.positioningStatus = '"+DicConstants.YHRS0026_001+"' and r.personOid='"+pbSyGwEmployInfoDTO.getPersonOid()+"' ");
		if(!CollectionUtils.isEmpty(twoDutyBoGq)){
			return false;
		}
		List<PbSyGwEmployInfo> twoDutyBo = new ArrayList<PbSyGwEmployInfo>();
		
		if(StringUtils.isNotEmpty(pbSyGwEmployInfoDTO.getPositionType())&&pbSyGwEmployInfoDTO.getPositionType().equals("1"))
		{
			//如果保存的是管理岗
			twoDutyBo= DaoUtil.find("from PbSyGwEmployInfo r where r.positionType = '"+DicConstants.YHRS0022_2+"' and r.positioningStatus = '"+DicConstants.YHRS0026_001+"' and r.personOid='"+pbSyGwEmployInfoDTO.getPersonOid()+"' ");

		}else if(StringUtils.isNotEmpty(pbSyGwEmployInfoDTO.getPositionType())&&pbSyGwEmployInfoDTO.getPositionType().equals("2"))
		{
			//如果保存的是专技岗
			twoDutyBo= DaoUtil.find("from PbSyGwEmployInfo r where r.positionType = '"+DicConstants.YHRS0022_1+"' and r.positioningStatus = '"+DicConstants.YHRS0026_001+"' and r.personOid='"+pbSyGwEmployInfoDTO.getPersonOid()+"' ");

		}else if(StringUtils.isNotEmpty(pbSyGwEmployInfoDTO.getPositionType())&&pbSyGwEmployInfoDTO.getPositionType().equals("3"))
		{
			//如果保存的是工勤岗
			twoDutyBo= DaoUtil.find("from PbSyGwEmployInfo r where r.positionType in('"+DicConstants.YHRS0022_1+"','"+DicConstants.YHRS0022_2+"') and r.syGwEmployOid !="+pbSyGwEmployInfoDTO.getSyGwEmployOid()+" and r.positioningStatus = '"+DicConstants.YHRS0026_001+"' and r.personOid='"+pbSyGwEmployInfoDTO.getPersonOid()+"' ");
			if(CollectionUtils.isNotEmpty(twoDutyBo)){
				return false;
			}
			//return false;

		}
		if(!CollectionUtils.isEmpty(twoDutyBo)&&twoDutyBo.size()>1){
			return false;
		}
		return true;
	}
	/**
	 * 查询在聘的事业岗位信息
	 * @param personOid
	 * @param jdrs0026001
	 * @return
	 * @throws ServiceException 
	 */
	public static List<PbSyGwEmployInfoDTO> findSyGwEmployInfoByCond(
			Long personOid, String flag) throws ServiceException {
		StringBuffer hBuffer = new StringBuffer("from PbSyGwEmployInfo pt where  1 =1 ");
		if (StringUtil.isNotNull(personOid)) {
            hBuffer.append(" and  pt.personOid =" + personOid);
        }
		if (StringUtil.isNotBlank(flag)) {
            hBuffer.append(" and  pt.positioningStatus ='" + flag).append("'");
        }
		 List<PbSyGwEmployInfoDTO> list = DaoUtil.find(hBuffer.toString());
		 if(CollectionUtils.isNotEmpty(list))
		 {
			 return BeanHelper.copyProperties(list,PbSyGwEmployInfoDTO.class);
		 }
		return null;
	}
	
	/**
	 * 根据人员id查询该人员所有的事业岗位聘任信息
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PbSyGwEmployInfoDTO> listPbSyGwEmployInfoByPersonOid(Long personOid) throws ServiceException{
		String hql = "from PbSyGwEmployInfo ei where ei.personOid = :personOid ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personOid", personOid);
		List<PbSyGwEmployInfo> boList = DaoUtil.find(hql, params);
		List<PbSyGwEmployInfoDTO> dtoList = new ArrayList<PbSyGwEmployInfoDTO>();
		if(CollectionUtils.isNotEmpty(boList))
		{
			dtoList = BeanHelper.copyProperties(boList, PbSyGwEmployInfoDTO.class);
		}
		return dtoList;
	}
}
