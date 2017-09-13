package com.yh.hr.res.pt.queryhelper;

import java.util.List;

import com.yh.hr.res.pt.bo.PtRewardInfo;
import com.yh.hr.res.pt.dto.PtRewardInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 奖励信息查询工具类
 * @author xiongyx
 *@createDate 2016-10-10
 */
public class PtRewardInfoQueryHelper {
	/**
	 * 通过oid获取奖励信息
	 * @param rewardInfoOid
	 * @return
	 * @throws ServiceException
	 */
	public static PtRewardInfoDTO getPtRewardInfoDTOById(Long rewardInfoOid) throws ServiceException {
		PtRewardInfo ptRewardInfo = DaoUtil.get(PtRewardInfo.class, rewardInfoOid);
		return BeanHelper.copyProperties(ptRewardInfo, PtRewardInfoDTO.class);
	}

	 /**
	  * 获取奖励信息列表
	  * @param bizPersonOid
	  * @return
	  * @throws ServiceException
	  */
	public static List<PtRewardInfoDTO> listPtRewardInfo(Long bizPersonOid) throws ServiceException {
		final StringBuffer hBuffer =  new StringBuffer("from PtRewardInfo Pt where  1 =1 ");
		hBuffer.append(" and  Pt.bizPersonOid =" +bizPersonOid);
		hBuffer.append(" order by Pt.rewardApprDate desc");
		return BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), PtRewardInfoDTO.class);
	}
	
	 /**
	  * 通过业务人员OID获取奖励信息列表
	  * @param bizPersonOid
	  * @return
	  * @throws ServiceException
	  */
	public static List<PtRewardInfoDTO> listPtRewardInfoByBizPersonOid(Long bizPersonOid) throws ServiceException {
		final StringBuffer hBuffer =  new StringBuffer("from PtRewardInfo Pt where  1 =1 ");
		hBuffer.append(" and  Pt.bizPersonOid =" +bizPersonOid);
		hBuffer.append(" order by Pt.rewardDate desc");
		return BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), PtRewardInfoDTO.class);
	}
	
	/**
	  * 获取奖励信息列表
	  * @param bizPersonOid
	  * @return
	  * @throws ServiceException
	  */
	public static List<PtRewardInfoDTO> listPtRewardInfoASC(Long bizPersonOid) throws ServiceException {
		final StringBuffer hBuffer =  new StringBuffer("from PtRewardInfo Pt where  1 =1 ");
		hBuffer.append(" and  Pt.bizPersonOid =" +bizPersonOid);
		return BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), PtRewardInfoDTO.class);
	}
	
	/**
	 * 通过基础OID查找该人员的奖励业务信息
	 * @param rewardOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtRewardInfoDTO> listPtRewardInfoByRewardOid(Long rewardOid) throws ServiceException {
		List<PtRewardInfo> list = DaoUtil.findByNamed("from PtRewardInfo ri where ri.rewardOid = :rewardOid order by ri.rewardApprDate desc", "rewardOid", rewardOid);
		return BeanHelper.copyProperties(list, PtRewardInfoDTO.class);
	}
	
	/**
	 * 根据bizPersonOid删除人员奖励信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtRewardInfo where bizPersonOid = " + bizPersonOid);
	}
}
