package com.yh.hr.res.pt.service.impl;

import com.yh.hr.res.pt.bo.PtRevokeVacation;
import com.yh.hr.res.pt.dto.PtRevokeVacationDto;
import com.yh.hr.res.pt.queryhelper.PtRevokeQueryHelper;
import com.yh.hr.res.pt.service.PtRevokeService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class PtRevokeServiceImpl implements PtRevokeService {

	
	/* (non-Javadoc)
	 * @see PtRevokeService#create(PtRevokeVacationDto)
	 */
	public Long create(PtRevokeVacationDto serdto) throws ServiceException {
	
			PtRevokeVacation ptReviewBizInfo = new PtRevokeVacation();
			BeanHelper.copyProperties(serdto, ptReviewBizInfo);
			ptReviewBizInfo.setCreateBy(UserContext.getLoginUserID());
			ptReviewBizInfo.setCreateName(UserContext.getLoginUserName());
			ptReviewBizInfo.setCreateDate(DateUtil.now());
			ptReviewBizInfo.save();
		return ptReviewBizInfo.getBizRevokeVacationOid();
	}
	
	/* (non-Javadoc)
	 * @see PtRevokeService#get(java.lang.Long)
	 */
	public PtRevokeVacationDto get(Long bizRevokeVacationOid) throws ServiceException{
		return BeanHelper.copyProperties(DaoUtil.get(PtRevokeVacation.class, bizRevokeVacationOid), PtRevokeVacationDto.class);
	}
	
	/* (non-Javadoc)
	 * @see PtRevokeService#update(PtRevokeVacationDto)
	 */
	public void update(PtRevokeVacationDto serdto) throws ServiceException{
		
			// dto转换为po
			PtRevokeVacation ptReviewInfo = DaoUtil.get(PtRevokeVacation.class, serdto.getBizRevokeVacationOid());
			if(null != ptReviewInfo){
				BeanHelper.copyProperties(serdto, ptReviewInfo,new String[]{"createdDate","createdByCode","createdByName"});
				// 设置修改人信息
				ptReviewInfo.setUpdateBy(UserContext.getLoginUserID());
				ptReviewInfo.setUpdateName(UserContext.getLoginUserName());
				ptReviewInfo.setUpdateDate(DateUtil.now());
				ptReviewInfo.update();
			}
		
	}
	
	/* (non-Javadoc)
	 * @see PtRevokeService#delete(java.lang.Long)
	 */
	public void delete(Long bizRevokeVacationOid) throws ServiceException{
		DaoUtil.get(PtRevokeVacation.class, bizRevokeVacationOid).delete();
	}

	/* (non-Javadoc)
	 * @see PtRevokeService#getPtRevokeByVacationOid(java.lang.Long)
	 */
	public PtRevokeVacationDto getPtRevokeByVacationOid(Long vacationOid)
			throws ServiceException {
		return PtRevokeQueryHelper.getPtRevokeByVacationOid(vacationOid);
	}
}
