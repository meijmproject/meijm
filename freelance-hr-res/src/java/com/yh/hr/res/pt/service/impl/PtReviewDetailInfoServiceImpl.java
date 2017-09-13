package com.yh.hr.res.pt.service.impl;

import java.util.List;

import com.yh.hr.res.pt.queryhelper.PtReviewDetailInfoQueryHelper;
import net.sf.json.JSONObject;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.pt.bo.PtReviewDetailInfo;
import com.yh.hr.res.pt.dto.PtReviewDetailInfoDTO;
import com.yh.hr.res.pt.service.PtReviewDetailInfoService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class PtReviewDetailInfoServiceImpl implements PtReviewDetailInfoService {

	/*
	 * (non-Javadoc)
	 * @see PtReviewDetailInfoService#create(PtReviewDetailInfoDTO)
	 */
	public void create(PtReviewDetailInfoDTO serdto) throws ServiceException {
	
			PtReviewDetailInfo ptReviewDetailInfo = new PtReviewDetailInfo();
			BeanHelper.copyProperties(serdto, ptReviewDetailInfo);
			ptReviewDetailInfo.setCreatedByCode(UserContext.getLoginUserID());
			ptReviewDetailInfo.setCreatedByName(UserContext.getLoginUserName());
			ptReviewDetailInfo.setCreatedDate(DateUtil.now());
			ptReviewDetailInfo.save();
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see PtReviewDetailInfoService#get(java.lang.Long)
	 */
	public PtReviewDetailInfoDTO get(Long reviewDetailInfoOid) throws ServiceException{
		return PtReviewDetailInfoQueryHelper.get(reviewDetailInfoOid);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see PtReviewDetailInfoService#update(PtReviewDetailInfoDTO)
	 */
	public void update(PtReviewDetailInfoDTO serdto) throws ServiceException{
		
			// dto转换为po
			PtReviewDetailInfo ptReviewDetailInfo = DaoUtil.get(PtReviewDetailInfo.class, serdto.getReviewDetailInfoOid());
			if(null != ptReviewDetailInfo){
				BeanHelper.copyProperties(serdto, ptReviewDetailInfo,new String[]{"createdDate","createdByCode","createdByName"});
				// 设置修改人信息
				ptReviewDetailInfo.setUpdatedByCode(UserContext.getLoginUserID());
				ptReviewDetailInfo.setUpdatedByName(UserContext.getLoginUserName());
				ptReviewDetailInfo.setUpdatedDate(DateUtil.now());
				ptReviewDetailInfo.update();
			}
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see PtReviewDetailInfoService#delete(java.lang.Long)
	 */
	public void delete(Long reviewDetailInfoOid) throws ServiceException{
		PtReviewDetailInfoQueryHelper.delete(reviewDetailInfoOid);
	}

	/*
	 * (non-Javadoc)
	 * @see PtReviewDetailInfoService#listPtReviewDetailInfo(TableTagBean)
	 */
	public List<JSONObject> listPtReviewDetailInfo(TableTagBean ttb)
			throws ServiceException {
		
		return PtReviewDetailInfoQueryHelper.listPtReviewDetailInfo(ttb);
	}
	
	/*
	 * (non-Javadoc)
	 * @see PtReviewDetailInfoService#saveAllPersonYearCheckCompetent(java.lang.Long)
	 */
	public void saveAllPersonYearCheckCompetent(Long reviewBizInfoOid)
			throws ServiceException {
		
//		//得到当前业务所有的人员列表
//		List<PtReviewDetailInfo> ptReviewDetailInfos = PtReviewDetailInfoQueryHelper.listBoByReviewBizInfoOid(reviewBizInfoOid);
//		if(ptReviewDetailInfos!=null&&ptReviewDetailInfos.size()>0){
//			//循环更新
//			for(PtReviewDetailInfo ptReviewDetailInfo:ptReviewDetailInfos){
//				boolean flag = true;
//				//得到人员id
//				Long personOid = ptReviewDetailInfo.getPersonOid();
//				//得到人员信息
//				PbPersonInfoDTO pbPersonInfoDTO = PbPersonInfoQueryHelper.getPbPersonInfoDTOById(personOid);
//				if(pbPersonInfoDTO!=null){
//					//人员状态为试用期
//					if(DicConstants.YHRS0009_120.equals(pbPersonInfoDTO.getPersonStatus())){
//						UtUnit utUnit = UtUnitQueryHelper.get(pbPersonInfoDTO.getUnitOid());
//						if (utUnit != null && utUnit.getUnitKind() != null && utUnit.getUnitKind().equals(DicConstants.YHRS0090_104)) {
//							//事业考核结论类别为不定等次
//							ptReviewDetailInfo.setReviewResultType(DicConstants.YHRS0123_05);
//						}else {
//							//考核结论类别为不确定考核等级
//							ptReviewDetailInfo.setReviewResultType(DicConstants.YHRS0070_5);
//						}
//						//参加考核不定等次原因为新录(聘)用的人员在试用期间，应对起其进行考核，但在年度考核时只写评语，不定等次
//						ptReviewDetailInfo.setReviewReason(DicConstants.YHRS0072_1);
//					 
//						flag = false;					
//					}
//				}
//				
//				//是否有国家公务员处分信息
//				String punishmentCodes = DicConstants.YHRS0081_10+","+DicConstants.YHRS0081_12+","+DicConstants.YHRS0081_13
//				           +","+DicConstants.YHRS0081_14+","+DicConstants.YHRS0081_17+","+DicConstants.YHRS0081_19;
//				PbPunishmentInfoDTO pbPunishmentInfoDTO = PbPunishmentInfoQueryHelper.getChuFenPbPunishmentInfo(personOid, punishmentCodes);
//			     
//			    if(pbPunishmentInfoDTO!=null){
//			    	//有处分
//			    	UtUnit utUnit = new UtUnit();
//			    	if (pbPersonInfoDTO!=null) {
//			    		utUnit = UtUnitQueryHelper.get(pbPersonInfoDTO.getUnitOid());
//					}
//					if (utUnit != null && utUnit.getUnitKind() != null && utUnit.getUnitKind().equals(DicConstants.YHRS0090_104)) {
//						//事业考核结论类别为不定等次
//						ptReviewDetailInfo.setReviewResultType(DicConstants.YHRS0123_05);
//					}else {
//						//考核结论类别为不确定考核等级
//						ptReviewDetailInfo.setReviewResultType(DicConstants.YHRS0070_5);
//					}
//					//参加考核不定等次原因为受记过，记大过，降级，撤职处分的公务员，对其进行考核，但在受处分期间只写评语，不定等次
//					ptReviewDetailInfo.setReviewReason(DicConstants.YHRS0072_3);
//				 
//					flag = false;		
//			    }
//			    
//			    if(flag){
//			    	//既不是试用期也不是有国家公务员纪律处分信息
//			    	UtUnit utUnit = new UtUnit();
//			    	if (pbPersonInfoDTO!=null) {
//			    		utUnit = UtUnitQueryHelper.get(pbPersonInfoDTO.getUnitOid());
//					}
//					if (utUnit != null && utUnit.getUnitKind() != null && utUnit.getUnitKind().equals(DicConstants.YHRS0090_104)) {
//						//事业设置为合格
//						ptReviewDetailInfo.setReviewResultType(DicConstants.YHRS0123_02);
//					}else {
//						//设置为称职
//						ptReviewDetailInfo.setReviewResultType(DicConstants.YHRS0070_2);
//					}
//			       
//			    }
//			    
//			    ptReviewDetailInfo.setIsPromote(DicConstants.YHRS0003_0);
//			    //更新
//				ptReviewDetailInfo.setUpdatedByCode(UserContext.getLoginUserID());
//				ptReviewDetailInfo.setUpdatedByName(UserContext.getLoginUserName());
//				ptReviewDetailInfo.setUpdatedDate(DateUtil.now());
//				ptReviewDetailInfo.update();
//			}
//		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see PtReviewDetailInfoService#countByCondition(java.lang.String, java.lang.String)
	 */
	public int countByCondition(Long reviewBizInfoOid,String reviewResultType, String isPromote,String dutyLevel)
			throws ServiceException {
		
		return PtReviewDetailInfoQueryHelper.countByCondition(reviewBizInfoOid,reviewResultType,isPromote,dutyLevel);
	}
}
