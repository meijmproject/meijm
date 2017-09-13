package com.yh.hr.res.pt.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pt.bo.PtEducationInfo;
import com.yh.hr.res.pt.bo.PtPersonAttach;
import com.yh.hr.res.pt.dto.PtEducationInfoDTO;
import com.yh.hr.res.pt.queryhelper.SyPtEducationInfoQueryHelper;
import com.yh.hr.res.pt.service.PtEducationInfoService;
import com.yh.platform.core.constant.Constant;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.web.UserContext;

/**
 * 学历学位信息service实现类（业务）
 * @author zhengdr
 *
 * 时间:2016-10-9下午06:20:48
 */
public class SyPtEducationInfoServiceImpl implements PtEducationInfoService
{
	/*
	 * (non-Javadoc)
	 * @see PtEducationInfoService#createPtEducationInfo(PtEducationInfoDTO)
	 */
	public void createPtEducationInfo(PtEducationInfoDTO ptEducationInfoDto) throws ServiceException
	{
		PtEducationInfo ptEducationInfo = BeanHelper.copyProperties(ptEducationInfoDto, PtEducationInfo.class);
		//得到操作人信息
		String userId = UserContext.getLoginUserID();
		String userName = UserContext.getLoginUserName();
		//当前日期
		Date now = new Date();
		//设置新增人信息和修改人信息
		ptEducationInfo.setCreatedByCode(userId);
		ptEducationInfo.setCreatedByName(userName);
		ptEducationInfo.setCreatedDate(now);
		ptEducationInfo.save();
		
		PtEducationInfoInnerService.synchroPtEducationInfo4PtPersonAttach(ptEducationInfo.getBizPersonOid());
	}
	
	/*
	 * (non-Javadoc)
	 * @see PtEducationInfoService#findPtEducationInfoById(java.lang.Long)
	 */
	public PtEducationInfoDTO findPtEducationInfoById(Long ptEducationOid) throws ServiceException
	{
		PtEducationInfo ptEducationInfo = DaoUtil.get(PtEducationInfo.class, ptEducationOid);
		return BeanHelper.copyProperties(ptEducationInfo, PtEducationInfoDTO.class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see PtEducationInfoService#updatePtEducationInfo(PtEducationInfoDTO)
	 */
	public void updatePtEducationInfo(PtEducationInfoDTO ptEducationInfoDto) throws ServiceException
	{
		PtEducationInfo ptEducationInfo = DaoUtil.get(PtEducationInfo.class, ptEducationInfoDto.getPtEducationOid());
		
		if(ptEducationInfo!=null){
			BeanHelper.copyProperties(ptEducationInfoDto,ptEducationInfo,new String[]{"createdDate","createdByCode","createdByName"});
			ptEducationInfo.setUpdatedByCode(UserContext.getLoginUserID());
			ptEducationInfo.setUpdatedByName(UserContext.getLoginUserName());
			ptEducationInfo.setUpdatedDate(DateUtil.now());
			ptEducationInfo.update();
		}
		PtEducationInfoInnerService.synchroPtEducationInfo4PtPersonAttach(ptEducationInfo.getBizPersonOid());
	}
	
	/*
	 * (non-Javadoc)
	 * @see PtEducationInfoService#deletePtEducationInfo(java.lang.Long)
	 */
	public void deletePtEducationInfo(Long ptEducationOid) throws ServiceException
	{
		PtEducationInfo ptEducationInfo = 	DaoUtil.get(PtEducationInfo.class, ptEducationOid);
		if(ptEducationInfo!=null){
			ptEducationInfo.delete();
		}
		PtEducationInfoInnerService.synchroPtEducationInfo4PtPersonAttach(ptEducationInfo.getBizPersonOid());
	}
	
	/*
	 * (non-Javadoc)
	 * @see PtEducationInfoService#listPtEducationInfoByBizPersonOid(java.lang.Long)
	 */
	public List<PtEducationInfoDTO> listPtEducationInfoByBizPersonOid(Long bizPersonOid) throws ServiceException {
		
		return SyPtEducationInfoQueryHelper.listPtEducationDTOByBizPersonOid(bizPersonOid);
	}
	 
	/*
	 * (non-Javadoc)
	 * @see PtEducationInfoService#countPtEducationInfoByBizPersonOid(java.lang.Long)
	 */
	public int countPtEducationInfoByBizPersonOid(Long bizPersonOid)
			throws ServiceException {
		
		return SyPtEducationInfoQueryHelper.countPtEducationDTOByBizPersonId(bizPersonOid);
	}
	
	/**
	 * @desc 设置且同步最高学历学位至人员附属表
	 * 			
	 * 		  1)、附属表要同步全日制、在职最高学历与学位;
	 * 		  2)、给学历信息集中的最高学历、学位打上最高的标识	。
	 * @author luqy
	 * @createDate 2016-9-17
	 */
	public static class  PtEducationInfoInnerService {

		
		public static void synchroPtEducationInfo4PtPersonAttach(Long bizPersonOid) throws ServiceException {
			try {
				PtPersonAttach ptPersonAttach = DaoUtil.get(PtPersonAttach.class, bizPersonOid);
				if (null == ptPersonAttach) {ptPersonAttach = new PtPersonAttach();}
				List<PtEducationInfoDTO> ptInfoDTOs  =  SyPtEducationInfoQueryHelper.listPtEducationDTOByBizPersonOid(bizPersonOid);
				if(CollectionUtils.isEmpty(ptInfoDTOs)){
					PtEducationInfoInnerService.setFtTopDegreeCodeWithNull(ptPersonAttach);
					PtEducationInfoInnerService.setOjTopEduLevelCodeWithNull(ptPersonAttach);
					return;
				}
				
				// 设置最高学历
				PtEducationInfoInnerService.setTopEduLevelCode(ptInfoDTOs);
				// 设置最高学位
				PtEducationInfoInnerService.setTopDegreeCode(ptInfoDTOs);
				
				List<PtEducationInfoDTO>  ftpInfoDTOs = PtEducationInfoInnerService.getFtOrOjPtEducationInfo(ptInfoDTOs, true);
				if(CollectionUtils.isNotEmpty(ftpInfoDTOs)){
					//设置全日制最高学历
					PtEducationInfoInnerService.setFtTopEduLevelCode(ptPersonAttach,ftpInfoDTOs);
					//设置全日制最高学位
					PtEducationInfoInnerService.setFtTopDegreeCode(ptPersonAttach,ftpInfoDTOs);
				}else{ //若找不到全日制学历信息，则清空附属表里面的字段
					PtEducationInfoInnerService.setFtTopDegreeCodeWithNull(ptPersonAttach);
				}
				
				
				List<PtEducationInfoDTO> ojpInfoDTOs = PtEducationInfoInnerService.getFtOrOjPtEducationInfo(ptInfoDTOs, false);
				if(CollectionUtils.isNotEmpty(ojpInfoDTOs)){
					//设置在职最高学历
					PtEducationInfoInnerService.setOjTopEduLevelCode(ptPersonAttach,ojpInfoDTOs);
					//设置在职最高学位
					PtEducationInfoInnerService.setOjTopDegreeCode(ptPersonAttach,ojpInfoDTOs);
				}else{//若找不到在职学历信息，则清空附属表里面的字段
					PtEducationInfoInnerService.setOjTopEduLevelCodeWithNull(ptPersonAttach);
				}
				
				
				//同步至附属表
				if(NumberUtils.isNullOrZero(ptPersonAttach.getBizPersonOid())){
					ptPersonAttach.setBizPersonOid(bizPersonOid);
					ptPersonAttach.save();
				}
				else{
					ptPersonAttach.update();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(null, "新增或更新学历学位信息至人员附属信息失败");
			}
		}

		

		// 设置最高学历
		private static void setTopEduLevelCode(List<PtEducationInfoDTO> ptInfoDTOs) throws ServiceException {
			PtEducationInfoInnerService.sortPtEducationInfoByLevelCode(ptInfoDTOs);
			PtEducationInfoDTO pEducationInfoDTO = ptInfoDTOs.get(0);
			if(pEducationInfoDTO != null ){
				PtEducationInfo PtEducationInfo = DaoUtil.get(PtEducationInfo.class, pEducationInfoDTO.getPtEducationOid());
				PtEducationInfo.setIsHighestEducationLevel(Constant.YES);
				PtEducationInfo.update();
				PtEducationInfoInnerService.cleanOtherHighFlag(PtEducationInfo.getBizPersonOid(),PtEducationInfo.getPtEducationOid(), true);
			}
		}

		// 设置最高学位
		private static void setTopDegreeCode(List<PtEducationInfoDTO> ptInfoDTOs) throws ServiceException {
			PtEducationInfoInnerService.sortPtEducationInfoByDegreeCode(ptInfoDTOs);
			PtEducationInfoDTO pEducationInfoDTO = ptInfoDTOs.get(0);
			if(pEducationInfoDTO != null ){
				PtEducationInfo PtEducationInfo = DaoUtil.get(PtEducationInfo.class, pEducationInfoDTO.getPtEducationOid());
				PtEducationInfo.setIsHighestDegree(Constant.YES);
				PtEducationInfo.update();
				PtEducationInfoInnerService.cleanOtherHighFlag(PtEducationInfo.getBizPersonOid(),PtEducationInfo.getPtEducationOid(), false);
			}
		}
		
		
		
		// 设置全日制最高学历
		private static void setFtTopEduLevelCode(PtPersonAttach ptPersonAttach,List<PtEducationInfoDTO> ptInfoDTOs) throws ServiceException {
			PtEducationInfoInnerService.sortPtEducationInfoByLevelCode(ptInfoDTOs);
			PtEducationInfoDTO PtEducationInfoDTO = ptInfoDTOs.get(0);
			if(PtEducationInfoDTO != null){
				ptPersonAttach.setFtSchoolName(PtEducationInfoDTO.getSchoolName());
				ptPersonAttach.setFtMajorName(PtEducationInfoDTO.getMajorName());
				ptPersonAttach.setFtEducationLevelCode(PtEducationInfoDTO.getEducationCode());
			}
		}
		
		//设置全日制最高学位
		private static void setFtTopDegreeCode(PtPersonAttach ptPersonAttach,List<PtEducationInfoDTO> ptInfoDTOs) throws ServiceException {
			PtEducationInfoInnerService.sortPtEducationInfoByDegreeCode(ptInfoDTOs);
			PtEducationInfoDTO PtEducationInfoDTO = ptInfoDTOs.get(0);
			if(PtEducationInfoDTO != null){
				ptPersonAttach.setFtDegreeCode(PtEducationInfoDTO.getDegreeCode());
			}
		}
		
		// 清空人员附属表全日制信息
		private static void setFtTopDegreeCodeWithNull(PtPersonAttach ptPersonAttach) throws ServiceException {
			ptPersonAttach.setFtSchoolName(null);
			ptPersonAttach.setFtMajorName(null);
			ptPersonAttach.setFtEducationLevelCode(null);
			ptPersonAttach.setFtDegreeCode(null);
		}
		
		
		// 设置在职最高学历
		private static void setOjTopEduLevelCode(PtPersonAttach ptPersonAttach,List<PtEducationInfoDTO> ptInfoDTOs) throws ServiceException {
			PtEducationInfoInnerService.sortPtEducationInfoByLevelCode(ptInfoDTOs);
			PtEducationInfoDTO PtEducationInfoDTO = ptInfoDTOs.get(0);
			if(PtEducationInfoDTO != null){
				ptPersonAttach.setOjSchoolName(PtEducationInfoDTO.getSchoolName());
				ptPersonAttach.setOjMajorName(PtEducationInfoDTO.getMajorName());
				ptPersonAttach.setOjEducationLevelCode(PtEducationInfoDTO.getEducationCode());
			}
		}
		
		// 设置在职最高学位
		private static void setOjTopDegreeCode(PtPersonAttach ptPersonAttach,List<PtEducationInfoDTO> ptInfoDTOs) throws ServiceException {
			PtEducationInfoInnerService.sortPtEducationInfoByDegreeCode(ptInfoDTOs);
			PtEducationInfoDTO PtEducationInfoDTO = ptInfoDTOs.get(0);
			if(PtEducationInfoDTO != null){
				ptPersonAttach.setOjDegreeCode(PtEducationInfoDTO.getDegreeCode());
			}
		}
		
		// 清空人员附属表在职信息
		private static void setOjTopEduLevelCodeWithNull(PtPersonAttach ptPersonAttach) throws ServiceException {
			ptPersonAttach.setOjSchoolName(null);
			ptPersonAttach.setOjMajorName(null);
			ptPersonAttach.setOjEducationLevelCode(null);
			ptPersonAttach.setOjDegreeCode(null);
		}
		
		
		//当找到最高学历学位时，清空其它学历信息集中的最高标识 
		private static void cleanOtherHighFlag(Long bizPersonOid,Long educationOid,boolean flag) throws ServiceException {
			SyPtEducationInfoQueryHelper.updateIsHighFlag(bizPersonOid,educationOid, flag);
		}
		
		
		
		// 根据人员所有学历信息集过滤出全日制或在职学历集,true表示取全日制，否则表示在职
		private static List<PtEducationInfoDTO> getFtOrOjPtEducationInfo(List<PtEducationInfoDTO> ptInfoDTOs,boolean isOjEdu) throws ServiceException {
			List<PtEducationInfoDTO> newInfoDTOs = new ArrayList<PtEducationInfoDTO>();
			for (PtEducationInfoDTO PtEducationInfoDTO : ptInfoDTOs) {
				if(isOjEdu){
					if(DicConstants.YHRS0043_1.equals(PtEducationInfoDTO.getEduTypeCode())){
						newInfoDTOs.add(PtEducationInfoDTO);
					}
				}else{
					if(DicConstants.YHRS0043_2.equals(PtEducationInfoDTO.getEduTypeCode())){
						newInfoDTOs.add(PtEducationInfoDTO);
					}
				}
			}
			return newInfoDTOs;
		}
		
		
		// 通过学历排序
		private static void sortPtEducationInfoByLevelCode(List<PtEducationInfoDTO> ptInfoDTOs) {
			Collections.sort(ptInfoDTOs, new Comparator<PtEducationInfoDTO>() {
				public int compare(	PtEducationInfoDTO pDto ,
									PtEducationInfoDTO _pDto) {
					String eduLevel1 = pDto.getEducationCode();
					String eduLevel2 = _pDto.getEducationCode();
					if(eduLevel1 == null && eduLevel2 == null) return 0;
					if(eduLevel1 == null)return 1;
					if(eduLevel2 == null) return -1;
					Integer i1 = eduLevelMap.get(eduLevel1);
					Integer i2 = eduLevelMap.get(eduLevel2);
					if(i1 == null && i2 == null) return 0;
					if(i1 == null) return 1;
					if(i2 == null) return -1;
					int ii = i1-i2;
					if(ii > 0)return 1;
					if(ii < 0)return -1;
					return ii;
				}
			});
		};
		
		
		// 通过学位排序
		private static void sortPtEducationInfoByDegreeCode(List<PtEducationInfoDTO> ptInfoDTOs) {
			Collections.sort(ptInfoDTOs, new Comparator<PtEducationInfoDTO>() {
				public int compare(	PtEducationInfoDTO pDto ,
									PtEducationInfoDTO _pDto) {
					String degree1 = pDto.getDegreeCode();
					String degree2 = _pDto.getDegreeCode();
					if(degree1 == null && degree2 == null) return 0;
					if(degree1 == null)return 1;
					if(degree2 == null) return -1;
					Integer i1 = degreeLevelMap.get(degree1);
					Integer i2 = degreeLevelMap.get(degree2);
					if(i1 == null && i2 == null) return 0;
					if(i1 == null) return 1;
					if(i2 == null) return -1;
					int ii = i1-i2;
					if(ii > 0)return 1;
					if(ii < 0)return -1;
					return ii;
				}
			});
		};
		
		
		
		/**   学历编码排序   */
		private static HashMap<String, Integer>	 eduLevelMap			= new HashMap<String, Integer>();
		static {
			eduLevelMap.put("10", 1);
			eduLevelMap.put("11", 2);
			eduLevelMap.put("12", 3);
			eduLevelMap.put("1", 4);
			eduLevelMap.put("2", 5);
			eduLevelMap.put("28", 6);
			eduLevelMap.put("3", 7);
			eduLevelMap.put("4", 8);
			eduLevelMap.put("5", 9);
			eduLevelMap.put("6", 10);
			eduLevelMap.put("7", 11);
			eduLevelMap.put("8", 12);
			eduLevelMap.put("9", 13);
			eduLevelMap.put("90", 14);

		}
		
		
		/**   学位编码排序   */
		private static HashMap<String, Integer>	degreeLevelMap			= new HashMap<String, Integer>();
		static {
			degreeLevelMap.put("5", 1);
			degreeLevelMap.put("4", 2);
			degreeLevelMap.put("3", 3);
			degreeLevelMap.put("2", 4);
			degreeLevelMap.put("1", 5);
		}
	}

	public void createWagePtEducationInfo(PtEducationInfoDTO ptEducationInfoDto)
			throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	
}

