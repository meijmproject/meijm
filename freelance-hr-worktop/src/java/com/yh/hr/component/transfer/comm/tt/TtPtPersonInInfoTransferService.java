/**
 * 
 */
package com.yh.hr.component.transfer.comm.tt;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.pt.dto.PtPersonInDTO;
import com.yh.hr.res.pt.service.PtPersonInService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 业务库人员转任信息转到业务库
 * 
 * @author zhangqp
 * @version 1.0, 16/10/17
 */

public class TtPtPersonInInfoTransferService implements InfoTransferService {

	private PtPersonInService ptPersonInService = (PtPersonInService) SpringBeanUtil.getBean("ptPersonInService");

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * InfoTransferService#transfer(java.
	 * lang.Long)
	 */
	public void transfer(Long refBizPersonOid, Long bizPersonOid) throws ServiceException {
		PtPersonInDTO refPtPersonInDTO = ptPersonInService.getPersonInDTOById(refBizPersonOid);

		if (refPtPersonInDTO != null) {

			PtPersonInDTO ptPersonInDTO = new PtPersonInDTO();
			BeanHelper.copyProperties(refPtPersonInDTO, ptPersonInDTO);

			ptPersonInDTO.setBizPersonOid(bizPersonOid);

			ptPersonInService.createPtPersonIn(ptPersonInDTO);
		}
	}
}
