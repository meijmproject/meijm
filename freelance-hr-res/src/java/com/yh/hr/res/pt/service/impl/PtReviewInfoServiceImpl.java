package com.yh.hr.res.pt.service.impl;

import java.util.List;

import com.yh.hr.res.pt.bo.PtReviewInfo;
import com.yh.hr.res.pt.dto.PtReviewInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtReviewInfoQueryHelper;
import com.yh.hr.res.pt.service.PtReviewInfoService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class PtReviewInfoServiceImpl implements PtReviewInfoService {

	/**
	 * 新增考核休情况详细信息
	 * 
	 * @param serdto
	 * @return
	 * @throws ServiceException
	 */
	public void createPtReviewInfo(PtReviewInfoDTO serdto) throws ServiceException {
	
			// dto转换为po
			PtReviewInfo ptReviewInfo = new PtReviewInfo();
			BeanHelper.copyProperties(serdto, ptReviewInfo);

			// 设置新增人信息
			ptReviewInfo.setCreatedByCode(UserContext.getLoginUserID());
			ptReviewInfo.setCreatedByName(UserContext.getLoginUserName());
			ptReviewInfo.setCreatedDate(DateUtil.now());
			ptReviewInfo.save();
		
	}
	
	/**
	 * 根据ID查询考核情况详细信息
	 * @param reviewOid
	 * @return
	 * @throws ServiceException
	 */	
	public PtReviewInfoDTO getPtReviewInfoByOid(Long reviewOid) throws ServiceException{
		//查询数据
		PtReviewInfo ptReviewInfo = DaoUtil.get(PtReviewInfo.class, reviewOid);
		//po转换为dto
		PtReviewInfoDTO serdto = new PtReviewInfoDTO();
		return BeanHelper.copyRtnProperties(ptReviewInfo, serdto);
		
	}
	
	/**
	 * 更新考核休情况详细信息
	 * @param serdto
	 * @return
	 * @throws ServiceException
	 */
	public void updatePtReviewInfo(PtReviewInfoDTO serdto) throws ServiceException{
		
			// dto转换为po
			PtReviewInfo ptReviewInfo = DaoUtil.get(PtReviewInfo.class, serdto.getPtReviewOid());
			//PtReviewInfo PtReviewInfo =  DaoUtil.get(PtReviewInfo.class, PtReviewInfoDTO.getReviewOid());//new PtReviewInfo();
			if(null != ptReviewInfo){
				if("4".equals(serdto.getReviewTypeCode())){
					//设置时间为空
					serdto.setReviewYear(null);
					
				}
				if("2".equals(serdto.getReviewTypeCode())){
					//设置时间为空
					serdto.setReviewendDate(null);
					
				}
				BeanHelper.copyProperties(serdto, ptReviewInfo,new String[]{"createdDate","createdByCode","createdByName"});
				// 设置修改人信息
				ptReviewInfo.setUpdatedByCode(UserContext.getLoginUserID());
				ptReviewInfo.setUpdatedByName(UserContext.getLoginUserName());
				ptReviewInfo.setUpdatedDate(DateUtil.now());
				ptReviewInfo.update();
			}
		
	}
	
	/**
	 * 根据ID删除考核情况详细信息
	 * @param reviewOid
	 * @return
	 * @throws ServiceException
	 */	
	public void deletePtReviewInfoByOid(Long reviewOid) throws ServiceException{
	
			//查询数据
			PtReviewInfo ptReviewInfo = DaoUtil.get(PtReviewInfo.class, reviewOid);
			//判断数据是否存在，存在则删除
			if(null != ptReviewInfo){
				ptReviewInfo.delete();
			}
	
	}
	
	/**
	 * 考核情况详细信息列表
	 * @param reviewOid
	 * @return
	 * @throws ServiceException
	 */	
	public List<PtReviewInfoDTO> listPtReviewInfo(Long personOid) throws ServiceException{
		return PtReviewInfoQueryHelper.listPtReviewInfo(personOid);
	}

	/*
	 * (non-Javadoc)
	 * @see PtReviewInfoService#countPtReviewInfoByBizPersonOid(java.lang.Long)
	 */
	public int countPtReviewInfoByBizPersonOid(Long personOid)
			throws ServiceException {
		return PtReviewInfoQueryHelper.countPtReviewInfoByBizPersonOid(personOid);
	}

}
