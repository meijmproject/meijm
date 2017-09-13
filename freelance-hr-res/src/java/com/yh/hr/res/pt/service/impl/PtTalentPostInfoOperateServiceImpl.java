package com.yh.hr.res.pt.service.impl;

import java.util.Date;
import java.util.List;

import com.yh.hr.res.pt.bo.PtPostInfoHistory;
import com.yh.hr.res.pt.dto.PtPositioningHistoryDTO;
import com.yh.hr.res.pt.dto.PtTalentPostInfoOperateDTO;
import com.yh.hr.res.pt.queryhelper.PtPositioningHistoryQueryHelper;
import com.yh.hr.res.pt.service.PtTalentPostInfoOperateService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pt.bo.PtPositioningHistory;
import com.yh.hr.res.pt.queryhelper.PtTalentPostInfoOperateQueryHelper;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 任职任聘信息 service实现类（业务）
 * @author zhengdr
 *
 * 时间:2016-10-10上午09:32:48
 */
public class PtTalentPostInfoOperateServiceImpl implements PtTalentPostInfoOperateService {

	/**
	 * 新增任职信息
	 * @param ptPositioningDTO
	 * @throws ServiceException
	 */
	public void insertPtPositioning(PtTalentPostInfoOperateDTO ptTalentPostInfoOperateDTO) throws ServiceException {

			PtPositioningHistory PtPositioningHistory = BeanHelper.copyProperties(ptTalentPostInfoOperateDTO, PtPositioningHistory.class);
			//PtPositioningHistory.setDisposalReasonDesc(DicHelper.viewName(DicConstants.YHRS0035,PtPositioningHistory.getDisposalReason()));
			PtPositioningHistory.setPositioningStatus(ptTalentPostInfoOperateDTO.getPositioningStatus());
			PtPositioningHistory.setDutyChangeTypeName(DicHelper.viewName(DicConstants.YHRS0034,PtPositioningHistory.getDutyChangeType()));
			PtPositioningHistory.setCreatedByCode(UserContext.getLoginUserID());
			PtPositioningHistory.setCreatedByName(UserContext.getLoginUserName());
			PtPositioningHistory.setCreatedDate(DateUtil.now());
			PtPositioningHistory.setDutyDate(DateUtil.parseDate(ptTalentPostInfoOperateDTO.getDutyDateStr()));	//任职(聘任)开始日期
			PtPositioningHistory.setEndDate(DateUtil.parseDate(ptTalentPostInfoOperateDTO.getEndDateStr()));
			PtPositioningHistory.save();
			ptTalentPostInfoOperateDTO.setPtPositioningHistroyOid(PtPositioningHistory.getPtPositioningHistroyOid());
			PtPostInfoHistory ptPostInfoHistory = BeanHelper.copyProperties(ptTalentPostInfoOperateDTO, PtPostInfoHistory.class);
			//得到操作人信息
			String userId = UserContext.getLoginUserID();
			String userName = UserContext.getLoginUserName();
			//当前日期
			Date now = new Date();
		//	ptPostInfoHistory.setPtPostHistoryOid(Long(1111));
			//设置新增人信息和修改人信息
			ptPostInfoHistory.setPtPositioningHistoryOid(PtPositioningHistory.getPtPositioningHistroyOid());
			ptPostInfoHistory.setPositionStatus(PtPositioningHistory.getPositioningStatus());
			//ptPostInfo.setPositioningOid(ptTalentPostInfoOperateDTO.getPositioningOid());
			ptPostInfoHistory.setCreatedByCode(userId);
			ptPostInfoHistory.setCreatedByName(userName);
			ptPostInfoHistory.setCreatedDate(now);
			ptPostInfoHistory.save();
			
			//PtPositioningHistoryInnerService.synchroPtPositioning4PbPersonAttach(PtPositioningHistory.getBizPersonOid());
	}

	/**
	 * 更新任职信息
	 * 
	 * @param ptPositioningDTO
	 * @throws ServiceException
	 */
	public void updatePtPositioning(PtTalentPostInfoOperateDTO ptTalentPostInfoOperateDTO) throws ServiceException {

		PtPositioningHistory ptPositioningHistory = DaoUtil.get(PtPositioningHistory.class, ptTalentPostInfoOperateDTO.getPtPositioningHistroyOid());
		
		if(ptPositioningHistory!=null){
			BeanHelper.copyProperties(ptTalentPostInfoOperateDTO, ptPositioningHistory,new String[]{"createdDate","createdByCode","createdByName"});
			//PtPositioningHistory.setDisposalReasonDesc(DicHelper.viewName(DicConstants.YHRS0035, PtPositioningHistory.getDisposalReason()));
			ptPositioningHistory.setDutyChangeTypeName(DicHelper.viewName(DicConstants.YHRS0034, ptPositioningHistory.getDutyChangeType()));
			ptPositioningHistory.setUpdatedByCode(UserContext.getLoginUserID());
			ptPositioningHistory.setUpdatedByName(UserContext.getLoginUserName());
			ptPositioningHistory.setUpdatedDate(DateUtil.now());
			ptPositioningHistory.setDutyDate(DateUtil.parseDate(ptTalentPostInfoOperateDTO.getDutyDateStr()));	//任职(聘任)开始日期
			ptPositioningHistory.setEndDate(DateUtil.parseDate(ptTalentPostInfoOperateDTO.getEndDateStr()));
			ptPositioningHistory.update();
			
			//PtPositioningHistoryInnerService.synchroPtPositioning4PbPersonAttach(PtPositioningHistory.getBizPersonOid());
			List<PtPostInfoHistory> ptPostInfoHistorys = PtPositioningHistoryQueryHelper.getByPositioningOid(ptTalentPostInfoOperateDTO.getPtPositioningHistroyOid());
			PtPostInfoHistory ptPostInfoHistory = new PtPostInfoHistory();
			if(ptPostInfoHistorys!=null){
				ptPostInfoHistory = DaoUtil.get(PtPostInfoHistory.class, ptPostInfoHistorys.get(0).getPtPostHistoryOid());
			Long postId=ptPostInfoHistory.getPtPostHistoryOid();
				//BeanHelper.copyProperties(ptTalentPostInfoOperateDTO, ptPostInfo,new String[]{"createdDate","createdByCode","createdByName"});
			 BeanHelper.copyProperties(ptTalentPostInfoOperateDTO,ptPostInfoHistory);
			
			//得到操作人信息
			String userId = UserContext.getLoginUserID();
			String userName = UserContext.getLoginUserName();
			//当前日期
			Date now = new Date();
			//设置新增人信息和修改人信息
			//ptPostInfo.setPositioningOid(PtPositioningHistory.getPtPositioningHistroyOid());
			ptPostInfoHistory.setPtPositioningHistoryOid(ptTalentPostInfoOperateDTO.getPtPositioningHistroyOid());
			ptPostInfoHistory.setPtPostHistoryOid(postId);
			ptPostInfoHistory.setUpdatedByCode(userId);
			ptPostInfoHistory.setUpdatedByName(userName);
			ptPostInfoHistory.setUpdatedDate(now);
			ptPostInfoHistory.update();
			}
		}
			
	}

	/**
	 * 获取任职信息
	 * @param PtPositioningHistoryOid
	 * @return
	 * @throws ServiceException
	 */
	public PtTalentPostInfoOperateDTO getPtPositioning(Long PtPositioningHistoryOid) throws ServiceException {
		PtPostInfoHistory ptPostInfoHistory = new PtPostInfoHistory();
		//PtTalentPostInfoOperateDTO ptTalentPostInfoOperateDTO = new PtTalentPostInfoOperateDTO();
	    if(null==PtPositioningHistoryOid){
				return null;
		}
	    
		PtPositioningHistory ptPositioningHistory = DaoUtil.get(PtPositioningHistory.class, PtPositioningHistoryOid);
		List<PtPostInfoHistory> ptPostInfoHistorys = PtPositioningHistoryQueryHelper.getByPositioningOid(PtPositioningHistoryOid);
		if(ptPostInfoHistorys!=null){
			ptPostInfoHistory = DaoUtil.get(PtPostInfoHistory.class, ptPostInfoHistorys.get(0).getPtPostHistoryOid());
		}
		PtTalentPostInfoOperateDTO ptTalentPostInfoOperateDTO =new PtTalentPostInfoOperateDTO();
		  BeanHelper.copyProperties(ptPostInfoHistory, ptTalentPostInfoOperateDTO);
		  BeanHelper.copyProperties(ptPositioningHistory, ptTalentPostInfoOperateDTO);
		  ptTalentPostInfoOperateDTO.setDutyDateStr(DateUtil.formatDate(ptPositioningHistory.getDutyDate()));
		  ptTalentPostInfoOperateDTO.setEndDateStr(DateUtil.formatDate(ptPositioningHistory.getEndDate()));
		ptTalentPostInfoOperateDTO.setEndDateActualStr(DateUtil.formatDate(ptPositioningHistory.getEndDateActual()));
		  return ptTalentPostInfoOperateDTO;
	}

	/*
	 * (non-Javadoc)
	 * @see PtPositioningService#listPtPositioningByBizPersonId(java.lang.Long)
	 */
	public List<PtTalentPostInfoOperateDTO> listPtPositioningByBizPersonId(Long bizPersonOid) throws ServiceException {
		List<PtTalentPostInfoOperateDTO> listsDtos=PtTalentPostInfoOperateQueryHelper.listPtPostHistoryByBizPersonId(bizPersonOid);
		if(CollectionUtils.isNotEmpty(listsDtos)){
			for (PtTalentPostInfoOperateDTO ptTalentPostInfoOperateDTO : listsDtos) {
				PtPositioningHistoryDTO ptPositioningHistoryDTO=PtPositioningHistoryQueryHelper.getPtPositioningHistoryDTOById(ptTalentPostInfoOperateDTO.getPtPositioningHistoryOid());
				ptTalentPostInfoOperateDTO.setPositioningOid(ptPositioningHistoryDTO.getPositioningOid());
				ptTalentPostInfoOperateDTO.setPositioningStatus(ptPositioningHistoryDTO.getPositioningStatus());
				ptTalentPostInfoOperateDTO.setDutyRecordType(ptPositioningHistoryDTO.getDutyRecordType());
				ptTalentPostInfoOperateDTO.setDutyName(ptPositioningHistoryDTO.getDutyName());
				ptTalentPostInfoOperateDTO.setDutyLevel(ptPositioningHistoryDTO.getDutyLevel());
				ptTalentPostInfoOperateDTO.setDutyDateStr(DateUtil.formatDate(ptPositioningHistoryDTO.getDutyDate()));
				ptTalentPostInfoOperateDTO.setEndDateStr(DateUtil.formatDate(ptPositioningHistoryDTO.getEndDate()));
				ptTalentPostInfoOperateDTO.setEndDateActualStr(DateUtil.formatDate(ptPositioningHistoryDTO.getEndDateActual()));
				ptTalentPostInfoOperateDTO.setPtPositioningHistroyOid(ptTalentPostInfoOperateDTO.getPtPositioningHistoryOid());
			}
		}
		return listsDtos;
	}
	
	public int  countPtPositioningHistoryByBizPersonOid (Long bizPersonOid) throws ServiceException {
		return PtPositioningHistoryQueryHelper.countPtPositioningHistoryByBizPersonOid(bizPersonOid);
	}
	/*
	 * (non-Javadoc)
	 * @see PtPositioningService#deletePtPositioningHistoryById(java.lang.Long)
	 */
	public void deletePtPositioningHistoryById(Long PtPositioningHistoryOid) throws ServiceException {

		PtPositioningHistory ptPositioningHistory = DaoUtil.get(PtPositioningHistory.class, PtPositioningHistoryOid);
		if(ptPositioningHistory.getPositioningOid()!=null){
			throw new ServiceException("该信息属于历史信息，不允许删除");
		}
		List<PtPostInfoHistory> ptPostInfoHistorys = PtPositioningHistoryQueryHelper.getByPositioningOid(PtPositioningHistoryOid);
		if(ptPostInfoHistorys!=null){
			PtPostInfoHistory ptPostInfoHistory = DaoUtil.get(PtPostInfoHistory.class, ptPostInfoHistorys.get(0).getPtPostHistoryOid());
			if(ptPostInfoHistory!=null){
				ptPostInfoHistory.delete();
			}
		}
		if(ptPositioningHistory!=null){
			ptPositioningHistory.delete();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see PtPositioningService#deletePtPositioningHistoryByIds(java.util.List)
	 */
	public void deletePtPositioningHistoryByIds(List<Long> positioningOids) throws ServiceException {
		//批量删除
		if (CollectionUtils.isNotEmpty(positioningOids)) {
			for (Long positioningOid : positioningOids) {
				this.deletePtPositioningHistoryById(positioningOid);
			}
		}
	}

	public int countPtPositioningInfoByBizPersonOid(Long bizPersonOid)
			throws ServiceException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void deletePtPositioningInfoById(Long ptPositioningInfoOid)
			throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	public void deletePtPositioningInfoByIds(List<Long> ptPositioningInfoOids){
		try {
			this.deletePtPositioningHistoryByIds(ptPositioningInfoOids);
		} catch (ServiceException e) {
			
			e.printStackTrace();
		}
	}

	/**
	 * @desc 同步任职信息到人员附属表
	 * @author luqy
	 * @createDate 2016-8-17下午01:57:04
	 */
	/*public static class PtPositioningHistoryInnerService {

		// 同步最高任职信息至人员附属表
		public static void synchroPtPositioning4PbPersonAttach(Long personOid) throws ServiceException {
			try {
				PbPersonAttach pbPersonAttach = DaoUtil.get(PbPersonAttach.class, personOid);
				if (null == pbPersonAttach) {
					pbPersonAttach = new PbPersonAttach();
				}
				PtPositioningDTO ptPositioningDTO = getTopPtPositioningDTO(personOid,DicConstants.YHRS0003_1);
				if(null==ptPositioningDTO){ptPositioningDTO = new PtPositioningDTO();}
				pbPersonAttach.setAdministrativeDutyType(ptPositioningDTO.getDutyType());
				pbPersonAttach.setAdministrativeDuty(ptPositioningDTO.getDutyName());
				pbPersonAttach.setAdministrativeDutyLevel(ptPositioningDTO.getDutyLevel());
				pbPersonAttach.setAdministrativeDutyAttribute(ptPositioningDTO.getDutyAttribute());
				pbPersonAttach.setAdministrativeDutyDate(ptPositioningDTO.getDutyDate());
				pbPersonAttach.setAdministrativeStartDate(calculateStartDate(personOid, DicConstants.YHRS0003_1));
				PbPersonInfo pbPersonInfo = DaoUtil.get(PbPersonInfo.class, personOid);
				// 同时同步单位 和内设机构至人员信息表
				if(pbPersonInfo != null){
					if(NumberUtils.isNotNullOrZero(ptPositioningDTO.getDutyUnitOid())){pbPersonInfo.setUnitOid(ptPositioningDTO.getDutyUnitOid());}
					if(StringUtil.isNotBlank(ptPositioningDTO.getDeptName())){	pbPersonInfo.setDeptName(ptPositioningDTO.getDeptName());}
					pbPersonInfo.update();
				}
				if(NumberUtils.isNullOrZero(pbPersonAttach.getPersonOid())){
					pbPersonAttach.setPersonOid(personOid);
					pbPersonAttach.save();
				}
				else{
					pbPersonAttach.update();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException(null, "新增或更新任职信息至人员附属信息失败");
			}
		}

		// 获取现任职务层次最早开始时间
		public static Date calculateStartDate(Long personOid,String isMPosition) throws ServiceException {
			// 找出最高职务层次，找出所有任该职务层次的任职，看免职时间和任职时间是否连续，连续的话取前面的。
			Date preLevelStart = null;
			// 现任最高职务职务层次
			String dutyLevelCode = null;
			// 取得现任最高职务
			PtPositioningDTO ptPositioningDTO = getTopPtPositioningDTO(personOid,isMPosition);
			if (null == ptPositioningDTO) { return null; }
			// 现任最高职务层次
			dutyLevelCode = ptPositioningDTO.getDutyLevel();
			// 默认设置为最高职务层次的任职时间
			preLevelStart = ptPositioningDTO.getDutyDate();
			// 该职务层次在任列表
			List<PtPositioningDTO> posiDispListOnDutyList = new ArrayList<PtPositioningDTO>();
			// 该职务层次不在任列表
			List<PtPositioningDTO> posiDispListNotOnDutyList = new ArrayList<PtPositioningDTO>();

			// 取得所有的任免信息，并根据任职日期倒序排序(不区分在任不在任)
			List<PtPositioningDTO> ptPositioningDTOs = PtPositioningHistoryQueryHelper.listPtPositioningByPersonId(personOid);
			for (PtPositioningDTO pbDto : ptPositioningDTOs) {
				String dutyLevel = pbDto.getDutyLevel();
				// 任职状态
				String positioningStatus = pbDto.getPositioningStatus();
				// 免职日期
				Date disposalDate = pbDto.getEndDate();
				if (StringUtils.isNotBlank(positioningStatus)) {
					if (positioningStatus.equals(DicConstants.YHRS0026_001)) // 统计与最高职务层次相同的在任任职信息
					{
						if (dutyLevelCode.equals(dutyLevel)) {
							posiDispListOnDutyList.add(pbDto);
						}
					}
					else if (disposalDate != null) // 统计不在任且免职日期不为空的任职信息
					{
						posiDispListNotOnDutyList.add(pbDto);
					}
				}
			}

			// **以上代码把该人的任最高职务层次的在任职务和不在任职务分开，在任的职务组成一个列表；不在任的组成另外一个列表；
			// **下面代码是分析比较在任职务、不在任职务，获取时间；
			// **目前是不间断任职的最早任职时间，如果可以间断的，设置间断天数（现在不确定）后，将不在任的职务的免职日期?天即可。

			for (PtPositioningDTO pbDto : posiDispListOnDutyList) {// 该职务层次在任列表中，找出最早的那条
				if (pbDto.getDutyDate().after(preLevelStart) == false) {// 如果这条任职的任职时间早于等于当前设置的最早时间
					preLevelStart = pbDto.getDutyDate();
				}
			}

			Date dutyDate = preLevelStart;// 任职时间初始化为该职务层次在任职务最早的那个时间
			// 该职务层次不在任列表中，找出最早的那条，并且免职时间不间断的(若不存在特殊标记)
			for (PtPositioningDTO positioningDTO : posiDispListNotOnDutyList) {
				// 免职日期
				Date disposalDate = positioningDTO.getEndDate();
				// xuhj modify 2010年5月20日 原来不间断的任职被认为是连续任职，修改后间断31天内的任职均被视为连续任职
				// String day =
				// ConfigUtil.getProperty("unionposition.discontinuous.days");
				// int days = StringUtils.isNotEmpty(day) ? Integer.valueOf(day)
				// : 0;
				int days = 30;
				Date afterCalDisposalDate = DateUtil.addDays(disposalDate, days);
				if (StringUtils.isNotBlank(positioningDTO.getDutyLevel())) {
					if (dutyLevelCode.equals(positioningDTO.getDutyLevel())) { // 如果与最高职务层次相同
						if (dutyDate.after(afterCalDisposalDate) == false) {// 如果这条的免职日期晚于等于上条任职的任职时间
							if (positioningDTO.getDutyDate().after(preLevelStart) == false) { // 如果这条任职的任职时间早于等于当前设置的最早时间
								preLevelStart = positioningDTO.getDutyDate();
							}
							dutyDate = positioningDTO.getDutyDate();
						}
					}
					else { // 否则,直接间断
						break;
					}
				}
			}
			return preLevelStart;
		}

		// 获取现任最高职务任职
		public static PtPositioningDTO getTopPtPositioningDTO(Long personOid,String isMPosition) throws ServiceException {

			// 查询所有在任职务
			// 所有职务按职务层次高低排序
			// 所有职务按单位领导、内设机构领导、非领导任职开始时间排序
			// 取所有职务第一条任职即可
			List<PbPostDTO> list = PbPostInfoQueryHelper.findCurrentDuty(personOid);
			List<PtPositioningDTO> ptPositioningDTOs = null;
			if(CollectionUtils.isEmpty(list))
			{
				ptPositioningDTOs = PtPositioningHistoryQueryHelper.findPDByPersonOidAndStatus(personOid, DicConstants.YHRS0026_001);
			}
			else
			{

				ptPositioningDTOs = PtPositioningHistoryQueryHelper.findSPdByPersonOidAndStatus(personOid, DicConstants.YHRS0026_001,isMPosition);
			}
			if (CollectionUtils.isEmpty(ptPositioningDTOs)) { return null; }
			if (ptPositioningDTOs.size() == 1) { return ptPositioningDTOs.get(0); }

			// 将所有任职转换成层级高低
			convertPtPositioningDutyLevel(ptPositioningDTOs);
			comPtPositioningByDutyLevel(ptPositioningDTOs);

			// 最高任职
			PtPositioningDTO highPositioningDTO = ptPositioningDTOs.get(0);
			String dutyLevel = highPositioningDTO.getDutyLevel();// 获取最高等级

			// 获取与最高任职相等的所有在任职务
			List<PtPositioningDTO> highPtPositioningDTOs = new ArrayList<PtPositioningDTO>();
			for (PtPositioningDTO ptPositioningDTO : ptPositioningDTOs) {
				if (StringUtil.isNotBlank(ptPositioningDTO.getDutyLevel()) && ptPositioningDTO.getDutyLevel().equals(dutyLevel)) {
					highPtPositioningDTOs.add(ptPositioningDTO);
				}
			}

			// 比较最高任职集合
			List<PtPositioningDTO> lastPtPositioningDTOs = new ArrayList<PtPositioningDTO>(); // 最终的集合
			List<PtPositioningDTO> unitLeaderList = new ArrayList<PtPositioningDTO>();// 记录所有单位领导的任职
			List<PtPositioningDTO> orgLeaderList = new ArrayList<PtPositioningDTO>();// 记录所有内设机构领导的任职
			List<PtPositioningDTO> notLeaderList = new ArrayList<PtPositioningDTO>();// 记录所有非领导的任职

			for (PtPositioningDTO pdPositioningDTO : highPtPositioningDTOs) {
				if (DicConstants.YHRS0028_010110.equals(pdPositioningDTO.getDutyAttribute())) {
					unitLeaderList.add(pdPositioningDTO);
				}
			}
			lastPtPositioningDTOs.addAll(comPtPositioningByDutyDate(unitLeaderList));

			for (PtPositioningDTO pdPositioningDTO : highPtPositioningDTOs) {
				if (DicConstants.YHRS0028_010120.equals(pdPositioningDTO.getDutyAttribute())) {
					orgLeaderList.add(pdPositioningDTO);
				}
			}
			lastPtPositioningDTOs.addAll(comPtPositioningByDutyDate(orgLeaderList));

			for (PtPositioningDTO pdPositioningDTO : highPtPositioningDTOs) {
				if (DicConstants.YHRS0028_030.equals(pdPositioningDTO.getDutyAttribute())) {
					notLeaderList.add(pdPositioningDTO);
				}
			}
			lastPtPositioningDTOs.addAll(comPtPositioningByDutyDate(notLeaderList));

			return CollectionUtils.isNotEmpty(lastPtPositioningDTOs) ? lastPtPositioningDTOs.get(0) : highPositioningDTO;
		}

		// 将所有职务层次高低转换一次
		private static void convertPtPositioningDutyLevel(List<PtPositioningDTO> ptPositioningDTOs) throws ServiceException {
		    PbDutyLevelMappingService	pbDutyLevelMappingService	= (PbDutyLevelMappingService) SpringBeanUtil.getBean("pbDutyLevelMappingService");
			for (PtPositioningDTO ptPositioningDTO : ptPositioningDTOs) {
				PbDutyLevelMappingDTO pbDutyLevelMappingDTO = pbDutyLevelMappingService.getPbDutyLevelMappingDTOByLevel(ptPositioningDTO.getDutyLevel());
				if(null!=pbDutyLevelMappingDTO)
				{
					ptPositioningDTO.setMappingLevel(pbDutyLevelMappingDTO.getMappingLevel());
				}
			}
		};

		// 通过职务层次排序
		private static List<PtPositioningDTO> comPtPositioningByDutyLevel(List<PtPositioningDTO> ptPositioningDTOs) {
			Collections.sort(ptPositioningDTOs, new Comparator<PtPositioningDTO>() {
				public int compare(	PtPositioningDTO pDto ,
									PtPositioningDTO _pDto) {

					if(null==pDto.getMappingLevel())
					{
						return 1;
					}
					else if(null==_pDto.getMappingLevel())
					{
						return -1;
					}
					else if (pDto.getMappingLevel() > _pDto.getMappingLevel()) {
						return 1;
					}
					else if (pDto.getMappingLevel().equals(_pDto.getMappingLevel()) ) { return 0; }
					return -1;
				}
			});
			return ptPositioningDTOs;
		};

		// 通过任职日期排序
		private static List<PtPositioningDTO> comPtPositioningByDutyDate(List<PtPositioningDTO> ptPositioningDTOs) {
			Collections.sort(ptPositioningDTOs, new Comparator<PtPositioningDTO>() {
				public int compare(	PtPositioningDTO pDto ,
									PtPositioningDTO _pDto) {
					if (pDto.getDutyDate().after(_pDto.getDutyDate())) {
						return -1;
					}
					else if (pDto.getDutyDate().before(_pDto.getDutyDate())) { return 1; }
					return 0;
				}
			});
			return ptPositioningDTOs;
		}
	}
*/
}
