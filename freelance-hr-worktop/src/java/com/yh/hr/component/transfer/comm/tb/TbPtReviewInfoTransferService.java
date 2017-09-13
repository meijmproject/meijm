/**
 * 
 */
package com.yh.hr.component.transfer.comm.tb;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pb.dto.PbReviewInfoDTO;
import com.yh.hr.res.pb.service.PbReviewInfoService;
import com.yh.hr.res.pt.dto.PtReviewInfoDTO;
import com.yh.hr.res.pt.service.PtReviewInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 考核情况信息转到基础库
 * @author	zhangqp
 * @version	1.0,	16/10/17
 */

public class TbPtReviewInfoTransferService implements InfoTransferService {
	
	private PtReviewInfoService ptReviewInfoService = (PtReviewInfoService)SpringBeanUtil.getBean("ptReviewInfoService");
	@SuppressWarnings("unused")
	private PbReviewInfoService pbReviewInfoService = (PbReviewInfoService)SpringBeanUtil.getBean("pbReviewInfoService");
	

	/* (non-Javadoc)
	 * @see com.yh.hr.component.tansfer.service.impl.InfoTransferService#transfer(java.lang.Long, java.lang.Long)
	 */
	public void transfer(Long bizPersonOid, Long personOid) throws ServiceException {
		List<PtReviewInfoDTO> list = ptReviewInfoService.listPtReviewInfo(bizPersonOid);
		
		if (CollectionUtils.isNotEmpty(list)) {

			PbReviewInfoDTO pbReviewInfoDTO = null;
			for (PtReviewInfoDTO dto : list) {
				
				pbReviewInfoDTO = new PbReviewInfoDTO();
				BeanHelper.copyProperties(dto, pbReviewInfoDTO);
				
				pbReviewInfoDTO.setPersonOid(personOid);
				
//				pbReviewInfoService.createPbReviewInfo(pbReviewInfoDTO);
			}
		}
	}

}
