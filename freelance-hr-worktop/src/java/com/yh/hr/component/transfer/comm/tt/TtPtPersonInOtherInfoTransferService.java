/**
 * 
 */
package com.yh.hr.component.transfer.comm.tt;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pt.dto.PtPersonInOtherDTO;
import com.yh.hr.res.pt.service.PtPersonInOtherService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 业务库人员其他转任信息转到业务库
 * 
 * @author lihj
 * @version 1.0, 16/11/03
 */

public class TtPtPersonInOtherInfoTransferService implements InfoTransferService {

	private PtPersonInOtherService ptPersonInOtherService = (PtPersonInOtherService) SpringBeanUtil.getBean("ptPersonInOtherService");

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * InfoTransferService#transfer(java.
	 * lang.Long)
	 */
	public void transfer(Long refBizPersonOid, Long bizPersonOid) throws ServiceException {
		List<PtPersonInOtherDTO> list = ptPersonInOtherService.listPtPersonInOtherInfoByBizPersonOid(refBizPersonOid);

		if(CollectionUtils.isNotEmpty(list)) {
			String uname = UserContext.getLoginUserName();
			
			PtPersonInOtherDTO ptPersonInOtherDTO = null;
			for (PtPersonInOtherDTO dto : list) {
				
				ptPersonInOtherDTO = new PtPersonInOtherDTO();
				BeanHelper.copyProperties(dto, ptPersonInOtherDTO);
				
				ptPersonInOtherDTO.setPtPersonInOtherOid(null);
				ptPersonInOtherDTO.setBizPersonOid(bizPersonOid);
				
				ptPersonInOtherDTO.setCreatedBy(uname);
				ptPersonInOtherDTO.setCreatedDate(DateUtil.now());
				ptPersonInOtherDTO.setUpdatedBy(uname);
				ptPersonInOtherDTO.setUpdatedDate(DateUtil.now());
				
				ptPersonInOtherService.createPtPersonInOther(ptPersonInOtherDTO);
			}
		}
	}
}
