package com.yh.hr.component.transfer.comm.tt;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.pt.dto.PtResumeInfoDTO;
import com.yh.hr.res.pt.service.PtResumeInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 业务库简历信息转到业务库
 * @date 2016-10-18 
 * @version 1.0 
 */
public class TtPtResumeInfoTransferService implements InfoTransferService{

	private PtResumeInfoService ptResumeInfoService = (PtResumeInfoService)SpringBeanUtil.getBean("ptResumeInfoService");

	public void transfer(Long refBizPersonOid, Long bizPersonOid) throws ServiceException {
		List<PtResumeInfoDTO>  list = ptResumeInfoService.listPtResumeInfo(refBizPersonOid);
		
		if (CollectionUtils.isNotEmpty(list)) {
			
			String uid = UserContext.getLoginUserID();
			String uname = UserContext.getLoginUserName();

			PtResumeInfoDTO ptResumeInfoDTO = null;
			for (PtResumeInfoDTO dto : list) {
				
				ptResumeInfoDTO = new PtResumeInfoDTO();
				BeanHelper.copyProperties(dto, ptResumeInfoDTO);
				
				ptResumeInfoDTO.setPtResumeOid(null);
				ptResumeInfoDTO.setBizPersonOid(bizPersonOid);
				
				ptResumeInfoDTO.setCreateBy(uid);
				ptResumeInfoDTO.setCreateByName(uname);
				ptResumeInfoDTO.setCreateDate(DateUtil.now());
				ptResumeInfoDTO.setUpdateBy(uid);
				ptResumeInfoDTO.setUpdateByName(uname);
				ptResumeInfoDTO.setUpdateDate(DateUtil.now());
				
				ptResumeInfoService.createPtResumeInfo(ptResumeInfoDTO);
			}
		}
	}

}
