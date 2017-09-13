package com.yh.hr.res.pt.service.impl;

import java.util.Date;
import java.util.List;

import com.yh.hr.res.pt.bo.PtPositioningOtherInfo;
import com.yh.hr.res.pt.dto.PtPositioningOtherInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtPositioningOtherInfoQueryHelper;
import com.yh.hr.res.pt.service.PtPositioningOtherService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class PtPositioningOtherServiceImpl implements PtPositioningOtherService {
	/*
	 * (non-Javadoc)
	 * @see com.yh.hr.res.pt.service.PtPositioningOtherInfoService#createPtPositioningOtherInfo(PtPositioningOtherInfoDTO)
	 */
	public void createPtPositioningOtherInfo(PtPositioningOtherInfoDTO ptPositioningOtherInfoDto) throws ServiceException
	{
		PtPositioningOtherInfo ptPositioningOtherInfo = BeanHelper.copyProperties(ptPositioningOtherInfoDto, PtPositioningOtherInfo.class);
		//得到操作人信息
		String userId = UserContext.getLoginUserID();
		String userName = UserContext.getLoginUserName();
		//当前日期
		Date now = new Date();
		//设置新增人信息和修改人信息
		ptPositioningOtherInfo.setCreatedByCode(userId);
		ptPositioningOtherInfo.setCreatedByName(userName);
		ptPositioningOtherInfo.setCreatedDate(now);
		ptPositioningOtherInfo.save();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.yh.hr.res.pt.service.PtPositioningOtherInfoService#findPtPositioningOtherInfoById(java.lang.Long)
	 */
	public PtPositioningOtherInfoDTO findPtPositioningOtherInfoById(Long ptPositioningOtherOid) throws ServiceException
	{
		PtPositioningOtherInfo ptPositioningOtherInfo = DaoUtil.get(PtPositioningOtherInfo.class, ptPositioningOtherOid);
		return BeanHelper.copyProperties(ptPositioningOtherInfo, PtPositioningOtherInfoDTO.class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.yh.hr.res.pt.service.PtPositioningOtherInfoService#updatePtPositioningOtherInfo(PtPositioningOtherInfoDTO)
	 */
	public void updatePtPositioningOtherInfo(PtPositioningOtherInfoDTO ptPositioningOtherInfoDto) throws ServiceException
	{
		PtPositioningOtherInfo ptPositioningOtherInfo = DaoUtil.get(PtPositioningOtherInfo.class, ptPositioningOtherInfoDto.getPtPositioningOtherOid());
		
		if(ptPositioningOtherInfo!=null){
			BeanHelper.copyProperties(ptPositioningOtherInfoDto,ptPositioningOtherInfo,new String[]{"createdDate","createdByCode","createdByName"});
			ptPositioningOtherInfo.setUpdatedByCode(UserContext.getLoginUserID());
			ptPositioningOtherInfo.setUpdatedByName(UserContext.getLoginUserName());
			ptPositioningOtherInfo.setUpdatedDate(DateUtil.now());
			ptPositioningOtherInfo.update();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.yh.hr.res.pt.service.PtPositioningOtherInfoService#deletePtPositioningOtherInfo(java.lang.Long)
	 */
	public void deletePtPositioningOtherInfo(Long ptPositioningOtherOid) throws ServiceException
	{
		PtPositioningOtherInfo ptPositioningOtherInfo = 	DaoUtil.get(PtPositioningOtherInfo.class, ptPositioningOtherOid);
		if(ptPositioningOtherInfo!=null){
			ptPositioningOtherInfo.delete();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.yh.hr.res.pt.service.PtPositioningOtherInfoService#listPtPositioningOtherInfoByBizPersonOid(java.lang.Long)
	 */
	public List<PtPositioningOtherInfoDTO> listPtPositioningOtherInfoByBizPersonOid(Long bizPersonOid) throws ServiceException {
		
		return PtPositioningOtherInfoQueryHelper.listPtPositioningOtherDTOByBizPersonOid(bizPersonOid);
	}
	 
	/*
	 * (non-Javadoc)
	 * @see com.yh.hr.res.pt.service.PtPositioningOtherInfoService#countPtPositioningOtherInfoByBizPersonOid(java.lang.Long)
	 */
	public int countPtPositioningOtherInfoByBizPersonOid(Long bizPersonOid)
			throws ServiceException {
		
		return PtPositioningOtherInfoQueryHelper.countPtPositioningOtherDTOByBizPersonId(bizPersonOid);
	}
}