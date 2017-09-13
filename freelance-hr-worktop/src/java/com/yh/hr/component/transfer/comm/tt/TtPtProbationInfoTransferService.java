/**
 * 
 */
package com.yh.hr.component.transfer.comm.tt;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.pt.dto.PtProbationInfoDTO;
import com.yh.hr.res.pt.service.PtProbationInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 业务库招考信息转到业务库
 * 
 * @author xiongyx
 * @version 1.0, 16/11/22
 */

public class TtPtProbationInfoTransferService implements InfoTransferService {

	private PtProbationInfoService ptProbationInfoService = (PtProbationInfoService) SpringBeanUtil.getBean("ptProbationInfoService");

	public void transfer(Long refBizPersonOid, Long bizPersonOid) throws ServiceException {
		PtProbationInfoDTO refptProbationInfoDTO = ptProbationInfoService.getById(refBizPersonOid);

		if (refptProbationInfoDTO != null) {

			PtProbationInfoDTO ptProbationInfoDTO = new PtProbationInfoDTO();
			BeanHelper.copyProperties(refptProbationInfoDTO, ptProbationInfoDTO);

			ptProbationInfoDTO.setBizPersonOid(bizPersonOid);

			ptProbationInfoService.create(ptProbationInfoDTO);
		}
	}
}
