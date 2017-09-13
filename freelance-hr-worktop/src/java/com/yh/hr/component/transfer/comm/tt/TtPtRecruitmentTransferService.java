/**
 * 
 */
package com.yh.hr.component.transfer.comm.tt;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.pt.dto.PtRecruitmentDTO;
import com.yh.hr.res.pt.service.PtRecruitmentService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 业务库招考信息转到业务库
 * 
 * @author xiongyx
 * @version 1.0, 16/11/18
 */

public class TtPtRecruitmentTransferService implements InfoTransferService {

	private PtRecruitmentService ptRecruitmentService = (PtRecruitmentService) SpringBeanUtil.getBean("ptRecruitmentService");

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * InfoTransferService#transfer(java.
	 * lang.Long)
	 */
	public void transfer(Long refBizPersonOid, Long bizPersonOid) throws ServiceException {
		PtRecruitmentDTO refptRecruitmentDTO = ptRecruitmentService.find(refBizPersonOid);

		if (refptRecruitmentDTO != null) {

			PtRecruitmentDTO ptRecruitmentDTO = new PtRecruitmentDTO();
			BeanHelper.copyProperties(refptRecruitmentDTO, ptRecruitmentDTO);

			ptRecruitmentDTO.setBizPersonOid(bizPersonOid);

			ptRecruitmentService.create(ptRecruitmentDTO);
		}
	}
}
