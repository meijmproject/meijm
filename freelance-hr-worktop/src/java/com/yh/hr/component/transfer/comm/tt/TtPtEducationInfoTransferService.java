/**
 * 
 */
package com.yh.hr.component.transfer.comm.tt;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.pt.dto.PtEducationInfoDTO;
import com.yh.hr.res.pt.service.PtEducationInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 业务库学历信息转到业务库
 * @author	zhangqp
 * @version	1.0,	16/10/17
 */

public class TtPtEducationInfoTransferService implements InfoTransferService {
	
	private PtEducationInfoService ptEducationInfoService = (PtEducationInfoService) SpringBeanUtil.getBean("ptEducationInfoService");
	
	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long)
	 */
	public void transfer(Long refBizPersonOid, Long bizPersonOid) throws ServiceException {
		List<PtEducationInfoDTO> list = ptEducationInfoService.listPtEducationInfoByBizPersonOid(refBizPersonOid);
		
		if(CollectionUtils.isNotEmpty(list)) {

			
			String uid = UserContext.getLoginUserID();
			String uname = UserContext.getLoginUserName();
			
			PtEducationInfoDTO ptEducationInfoDTO = null;
			for (PtEducationInfoDTO dto : list) {
				
				ptEducationInfoDTO = new PtEducationInfoDTO();
				BeanHelper.copyProperties(dto, ptEducationInfoDTO);
				
				ptEducationInfoDTO.setPtEducationOid(null);
				ptEducationInfoDTO.setBizPersonOid(bizPersonOid);
				
				ptEducationInfoDTO.setCreatedByCode(uid);
				ptEducationInfoDTO.setCreatedByName(uname);
				ptEducationInfoDTO.setCreatedDate(DateUtil.now());
				ptEducationInfoDTO.setUpdatedByCode(uid);
				ptEducationInfoDTO.setUpdatedByName(uname);
				ptEducationInfoDTO.setUpdatedDate(DateUtil.now());
				
				ptEducationInfoService.createPtEducationInfo(ptEducationInfoDTO);
			}
		}
	}

}
