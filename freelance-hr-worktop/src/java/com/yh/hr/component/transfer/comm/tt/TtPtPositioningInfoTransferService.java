/**
 * 
 */
package com.yh.hr.component.transfer.comm.tt;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pt.dto.PtPositioningInfoDTO;
import com.yh.hr.res.pt.service.PtPositioningService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 业务库任职信息转到业务库
 * @author	zhangqp
 * @version	1.0,	16/10/17
 */

public class TtPtPositioningInfoTransferService implements InfoTransferService {
	
	private PtPositioningService ptPositioningService = (PtPositioningService)SpringBeanUtil.getBean("ptPositioningService");

	/* (non-Javadoc)
	 * @see com.yh.hr.component.tansfer.service.impl.InfoTransferService#transfer(java.lang.Long, java.lang.Long)
	 */
	public void transfer(Long refBizPersonOid, Long bizPersonOid) throws ServiceException {
		List<PtPositioningInfoDTO> list = ptPositioningService.listPtPositioningByBizPersonId(refBizPersonOid);
		
		if (CollectionUtils.isNotEmpty(list)) {
			
			String uid = UserContext.getLoginUserID();
			String uname = UserContext.getLoginUserName();

			PtPositioningInfoDTO ptPositioningInfoDTO = null;
			for (PtPositioningInfoDTO dto : list) {
				
				ptPositioningInfoDTO = new PtPositioningInfoDTO();
				BeanHelper.copyProperties(dto, ptPositioningInfoDTO);
				
				ptPositioningInfoDTO.setPtPositioningInfoOid(null);
				ptPositioningInfoDTO.setBizPersonOid(bizPersonOid);
				
				ptPositioningInfoDTO.setCreatedByCode(uid);
				ptPositioningInfoDTO.setCreatedByName(uname);
				ptPositioningInfoDTO.setCreatedDate(DateUtil.now());
				ptPositioningInfoDTO.setUpdatedByCode(uid);
				ptPositioningInfoDTO.setUpdatedByName(uname);
				ptPositioningInfoDTO.setUpdatedDate(DateUtil.now());
				
				ptPositioningService.insertPtPositioning(ptPositioningInfoDTO);
			}
		}
	}

}
