package com.yh.hr.res.pt.service.impl;

import java.util.Date;
import java.util.List;
import com.yh.hr.res.pt.bo.PtMilitaryInfo;
import com.yh.hr.res.pt.dto.PtMilitaryInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtMilitaryInfoQueryHelper;
import com.yh.hr.res.pt.service.PtMilitaryInfoService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.StringUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 军队任职信息Service实现类(业务)
 * @author zhengdr
 *
 * 时间:2016-10-10上午09:28:43
 */
public class PtMilitaryInfoServiceImpl implements PtMilitaryInfoService{

	/*
	 * 得到军队任职信息
	 * (non-Javadoc)
	 * @see com.yh.hr.res.pb.service.PtMilitaryInfoService#getPtMilitaryInfoDTO(java.lang.Long)
	 */
	public List<PtMilitaryInfoDTO> getPtMilitaryInfoDTOList(Long bizPersonOid)
			throws ServiceException {
		//得到军队任职信息并复制给DTO
		return PtMilitaryInfoQueryHelper.getPtMilitaryInfoDTO(bizPersonOid);
	}
	public int countPtMilitaryInfoByBizPersonOid (Long bizPersonOid) throws ServiceException {
		return PtMilitaryInfoQueryHelper.countPtMilitaryInfoByBizPersonOid(bizPersonOid);
	}
	/*
	 * 得到军队任职信息
	 * (non-Javadoc)
	 * @see com.yh.hr.res.pb.service.PtMilitaryInfoService#getPtMilitaryInfoDTO()
	 */
	public PtMilitaryInfoDTO getPtMilitaryInfoDTO(Long ptMilitaryOid) throws ServiceException {
		
		return BeanHelper.copyProperties(DaoUtil.get(PtMilitaryInfo.class,ptMilitaryOid),PtMilitaryInfoDTO.class);

	}
	
	
   /**
	 * 新增军队任职信息
	 * @param ptMilitaryInfoDTO
	 * @throws ServiceException
	 */
	public void createPtMilitaryInfo(PtMilitaryInfoDTO ptMilitaryInfoDTO)throws ServiceException{
		//将dto转换成为po
		PtMilitaryInfo ptMilitaryInfo = BeanHelper.copyProperties(ptMilitaryInfoDTO,PtMilitaryInfo.class);
		if(StringUtil.isBlank(ptMilitaryInfo.getMilitaryDutyGrade()) && StringUtil.isBlank(ptMilitaryInfo.getMilitaryTechCivilLevel()) 
				&& StringUtil.isBlank(ptMilitaryInfo.getMilitaryBontechCivilLevel())){
			throw new ServiceException(null,"军队职级、军队文职专业技术级别、军队非专业技术文职级别必采集一项");
		}
		 //得到操作人信息
		String userId = UserContext.getLoginUserID();
		String userName = UserContext.getLoginUserName();
		//当前日期
		Date now = new Date();
		//设置新增人信息和修改人信息
		ptMilitaryInfo.setCreatedByCode(userId);
		ptMilitaryInfo.setCreatedByName(userName);
		ptMilitaryInfo.setCreatedDate(now);
//		ptMilitaryInfo.setUpdatedByCode(userId);
//		ptMilitaryInfo.setUpdatedByName(userName);
//		ptMilitaryInfo.setUpdatedDate(now);
		//保存
		ptMilitaryInfo.save();
	}
	
	/**
	 * 修改军队任职信息
	 * @param ptMilitaryInfoDTO
	 */
	public void modifyPtMilitaryInfo(PtMilitaryInfoDTO ptMilitaryInfoDTO)throws ServiceException{
		
		if(StringUtil.isBlank(ptMilitaryInfoDTO.getMilitaryDutyGrade()) && StringUtil.isBlank(ptMilitaryInfoDTO.getMilitaryTechCivilLevel()) 
				&& StringUtil.isBlank(ptMilitaryInfoDTO.getMilitaryBontechCivilLevel())){
			throw new ServiceException(null,"军队职级、军队文职专业技术级别、军队非专业技术文职级别必采集一项");
		}
		
		//将dto转换成为po
		PtMilitaryInfo ptMilitaryInfo = DaoUtil.get(PtMilitaryInfo.class, ptMilitaryInfoDTO.getPtMilitaryOid());
		if(ptMilitaryInfo != null){
			BeanHelper.copyProperties(ptMilitaryInfoDTO,ptMilitaryInfo,new String[]{"createdDate","createdByCode","createdByName"});
			ptMilitaryInfo.setUpdatedByCode(UserContext.getLoginUserID());
			ptMilitaryInfo.setUpdatedByName(UserContext.getLoginUserName());
			ptMilitaryInfo.setUpdatedDate(DateUtil.now());
			ptMilitaryInfo.update();
		}
	}
	
	/**
	 * 根据id删除军队任职信息
	 * @param ptMilitaryOid
	 * @throws ServiceException
	 */
	public void deletePtMilitaryInfo(Long ptMilitaryOid)throws ServiceException{
		//得到军队任职信息
		PtMilitaryInfo ptMilitaryInfo = DaoUtil.get(PtMilitaryInfo.class,ptMilitaryOid);
	    if(ptMilitaryInfo!=null){
	    	//删除
	    	ptMilitaryInfo.delete();
	    }
	}

}
