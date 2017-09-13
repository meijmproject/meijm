package com.yh.hr.res.pt.service.impl;

import java.util.List;

import com.yh.hr.res.pt.bo.PtRewardInfo;
import com.yh.hr.res.pt.dto.PtRewardInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtRewardInfoQueryHelper;
import com.yh.hr.res.pt.service.PtRewardInfoService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;
/**
 * @description The class for PtRewardInfo service
 * @author wuxq
 * @created 2016-08-15
 * @version 1.0
 */
public class PtRewardInfoServiceImpl implements PtRewardInfoService {

	/**
	 * 新增奖励信息
	 * @param PtRewardInfoDTO
	 * @return
	 * @throws ServiceException
	 */
	public void create(PtRewardInfoDTO PtRewardInfoDTO) throws ServiceException {
		PtRewardInfo ptRewardInfo = BeanHelper.copyProperties(PtRewardInfoDTO, PtRewardInfo.class);
		ptRewardInfo.setCreateBy(UserContext.getLoginUserID());
		ptRewardInfo.setCreateName(UserContext.getLoginUserName());
		ptRewardInfo.setCreateDate(DateUtil.now());
		ptRewardInfo.setUpdateBy(UserContext.getLoginUserID());
		ptRewardInfo.setUpdateName(UserContext.getLoginUserName());
		ptRewardInfo.setUpdateDate(DateUtil.now());
		ptRewardInfo.save();					
	}
	/**
	 * 查询单条奖励信息
	 * @param ptRewardOid
	 * @return
	 * @throws ServiceException
	 */
	public PtRewardInfo find(Long ptRewardOid) throws ServiceException {
	   PtRewardInfo ptRewardInfo = DaoUtil.get(PtRewardInfo.class, ptRewardOid);
	   if(ptRewardInfo != null){
		   return ptRewardInfo;
	   }
	   return null;
	}
	/**
	 * 更新奖励信息
	 * @param PtRewardInfoDTO
	 * @return
	 * @throws ServiceException
	 */
	public void update(PtRewardInfoDTO ptRewardInfoDTO) throws ServiceException {
		PtRewardInfo ptRewardInfo = DaoUtil.get(PtRewardInfo.class, ptRewardInfoDTO.getPtRewardOid());
		if(ptRewardInfo != null){
			BeanHelper.copyProperties(ptRewardInfoDTO, ptRewardInfo,BeanHelper.getNullPropertyNames(ptRewardInfoDTO));
			ptRewardInfo.setUpdateBy(UserContext.getLoginUserID());
			ptRewardInfo.setUpdateName(UserContext.getLoginUserName());
			ptRewardInfo.setUpdateDate(DateUtil.now());
			ptRewardInfo.update();
		}		
	}
	/**
	 * 删除奖励信息
	 * @param ptRewardOid
	 * @return
	 * @throws ServiceException
	 */
	public void delete(Long ptRewardOid) throws ServiceException {	
		PtRewardInfo ptRewardInfo = DaoUtil.get(PtRewardInfo.class, ptRewardOid);
		if(null != ptRewardInfo){
			ptRewardInfo.delete();				
		}
	}
	
	/**
	 *分页查询奖励信息
	 * @param PtRewardInfoDTO
	 * @return
	 * @throws ServiceException
	 */
	public List<PtRewardInfoDTO> findPtRewardInfoByCondition(Long personOid) throws ServiceException {
			return PtRewardInfoQueryHelper.listPtRewardInfo(personOid);	
	}
	
	/**
	  * 通过业务人员OID获取奖励信息列表
	  * @param bizPersonOid
	  * @return
	  * @throws ServiceException
	  */
	public List<PtRewardInfoDTO> listPtRewardInfoByBizPersonOid(Long bizPersonOid) throws ServiceException {
		return PtRewardInfoQueryHelper.listPtRewardInfoByBizPersonOid(bizPersonOid);	
	}
	
	/**
	 * 通过基础OID查找该人员的奖励业务信息
	 * @param rewardOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtRewardInfoDTO> listPtRewardInfoByRewardOid(Long rewardOid) throws ServiceException {
		return PtRewardInfoQueryHelper.listPtRewardInfoByRewardOid(rewardOid);
	}
	
	/**
	 * 通过业务人员OID删除该人员的所有奖励信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		PtRewardInfoQueryHelper.deleteByBizPersonOid(bizPersonOid);
	}
	
	/**
	 * 通过oid获取奖励信息
	 */
	public PtRewardInfoDTO get(Long ptRewardOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtRewardInfo.class, ptRewardOid), PtRewardInfoDTO.class);
	}
}
