package com.yh.hr.res.pt.service.impl;

import java.util.List;

import com.yh.hr.res.pt.bo.PtReviewBizInfo;
import com.yh.hr.res.pt.dto.PtReviewBizInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtReviewBizInfoQueryHelper;
import com.yh.hr.res.pt.service.PtReviewBizInfoService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class PtReviewBizInfoServiceImpl implements PtReviewBizInfoService {

	/*
	 * (non-Javadoc)
	 * @see PtReviewBizInfoService#create(PtReviewBizInfoDTO)
	 */
	public Long create(PtReviewBizInfoDTO serdto) throws ServiceException {
	
			PtReviewBizInfo ptReviewBizInfo = new PtReviewBizInfo();
			BeanHelper.copyProperties(serdto, ptReviewBizInfo);
			ptReviewBizInfo.setCreatedByCode(UserContext.getLoginUserID());
			ptReviewBizInfo.setCreatedByName(UserContext.getLoginUserName());
			ptReviewBizInfo.setCreatedDate(DateUtil.now());
			ptReviewBizInfo.save();
		return ptReviewBizInfo.getPtReviewBizOid();
	}
	
	/*
	 * (non-Javadoc)
	 * @see PtReviewBizInfoService#get(java.lang.Long)
	 */
	public PtReviewBizInfoDTO get(Long ptReviewBizOid) throws ServiceException{
		return PtReviewBizInfoQueryHelper.get(ptReviewBizOid);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see PtReviewBizInfoService#update(PtReviewBizInfoDTO)
	 */
	public void update(PtReviewBizInfoDTO serdto) throws ServiceException{
		
			// dto转换为po
			PtReviewBizInfo ptReviewInfo = DaoUtil.get(PtReviewBizInfo.class, serdto.getPtReviewBizOid());
			if(null != ptReviewInfo){
				BeanHelper.copyProperties(serdto, ptReviewInfo,new String[]{"createdDate","createdByCode","createdByName"});
				// 设置修改人信息
				ptReviewInfo.setUpdatedByCode(UserContext.getLoginUserID());
				ptReviewInfo.setUpdatedByName(UserContext.getLoginUserName());
				ptReviewInfo.setUpdatedDate(DateUtil.now());
				ptReviewInfo.update();
			}
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see PtReviewBizInfoService#delete(java.lang.Long)
	 */
	public void delete(Long ptReviewBizOid) throws ServiceException{
		PtReviewBizInfoQueryHelper.delete(ptReviewBizOid);
	}

	public List<String> check(Long unitOid,String years) throws ServiceException {
		return PtReviewBizInfoQueryHelper.check(unitOid,years);
	}

	public void tansferPerson(Long reviewBizInfoOid, Long unitOid, String years)
			throws ServiceException {
		PtReviewBizInfoQueryHelper.tansferPerson(reviewBizInfoOid,unitOid,years);
	}
	
	public void tansferSyPerson(Long reviewBizInfoOid, Long unitOid, String years)
			throws ServiceException {
		PtReviewBizInfoQueryHelper.tansferSyPerson(reviewBizInfoOid,unitOid,years);
	}

}
