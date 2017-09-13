/**
 * 
 */
package com.yh.hr.component.transfer.comm.tt;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pt.dto.PtWageInfluenceDTO;
import com.yh.hr.res.pt.service.PtWageInfluenceService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 业务库与工资相关信息转到业务库
 * @author	lihj
 */

public class TtPtWageInfluenceTransferService implements InfoTransferService {
	
	private PtWageInfluenceService ptWageInfluenceService = (PtWageInfluenceService) SpringBeanUtil.getBean("ptWageInfluenceService");
	
	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long)
	 */
	public void transfer(Long refBizPersonOid, Long bizPersonOid) throws ServiceException {
		List<PtWageInfluenceDTO> list = ptWageInfluenceService.listWageInfluenceDTO(refBizPersonOid);
		
		if(CollectionUtils.isNotEmpty(list)) {

			
			String uid = UserContext.getLoginUserID();
			String uname = UserContext.getLoginUserName();
			
			PtWageInfluenceDTO ptWageInfluenceDTO = null;
			for (PtWageInfluenceDTO dto : list) {
				
				ptWageInfluenceDTO = new PtWageInfluenceDTO();
				BeanHelper.copyProperties(dto, ptWageInfluenceDTO);
				
				ptWageInfluenceDTO.setBizPersonOid(bizPersonOid);
				
				ptWageInfluenceDTO.setCreatedByCode(uid);
				ptWageInfluenceDTO.setCreatedByName(uname);
				ptWageInfluenceDTO.setCreatedDate(DateUtil.now());
				ptWageInfluenceDTO.setUpdatedByCode(uid);
				ptWageInfluenceDTO.setUpdatedByName(uname);
				ptWageInfluenceDTO.setUpdatedDate(DateUtil.now());
				
				ptWageInfluenceService.addPtWageInfluence(ptWageInfluenceDTO);
			}
		}
	}

}
