package com.yh.hr.component.transfer.comm.tt;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pt.dto.PtReviewInfoDTO;
import com.yh.hr.res.pt.service.PtReviewInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 业务库考核信息转到业务库
 * @date 2016-10-18 
 * @version 1.0 
 */
public class TtPtReviewInfoTransferService implements InfoTransferService {

	private PtReviewInfoService ptReviewInfoService = (PtReviewInfoService)SpringBeanUtil.getBean("ptReviewInfoService");

	public void transfer(Long refBizPersonOid, Long bizPersonOid) throws ServiceException {
		List<PtReviewInfoDTO>   list = ptReviewInfoService.listPtReviewInfo(refBizPersonOid);
		
		if (CollectionUtils.isNotEmpty(list)) {
			
			String uid = UserContext.getLoginUserID();
			String uname = UserContext.getLoginUserName();

			PtReviewInfoDTO ptReviewInfoDTO = null;
			for (PtReviewInfoDTO dto : list) {
				
				ptReviewInfoDTO = new PtReviewInfoDTO();
				BeanHelper.copyProperties(dto, ptReviewInfoDTO);
				
				ptReviewInfoDTO.setPtReviewOid(null);
				ptReviewInfoDTO.setBizPersonOid(bizPersonOid);
				
				ptReviewInfoDTO.setCreatedByCode(uid);
				ptReviewInfoDTO.setCreatedByName(uname);
				ptReviewInfoDTO.setCreatedDate(DateUtil.now());
				ptReviewInfoDTO.setUpdatedByCode(uid);
				ptReviewInfoDTO.setUpdatedByName(uname);
				ptReviewInfoDTO.setUpdatedDate(DateUtil.now());
				
				ptReviewInfoService.createPtReviewInfo(ptReviewInfoDTO);
			}
		}
	}

}
