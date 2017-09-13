/**
 * 
 */
package com.yh.hr.component.transfer.comm.tb;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pb.dto.PbFamilyInfoDTO;
import com.yh.hr.res.pb.service.PbFamilyInfoService;
import com.yh.hr.res.pt.dto.PtFamilyInfoDTO;
import com.yh.hr.res.pt.service.PtFamilyInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 家庭成员信息转到基础库
 * @author	zhangqp
 * @version	1.0,	16/10/17
 */

public class TbPtFamilyInfoTransferService implements InfoTransferService {
	
	private PtFamilyInfoService ptFamilyInfoService = (PtFamilyInfoService)SpringBeanUtil.getBean("ptFamilyInfoService");
	private PbFamilyInfoService pbFamilyInfoService = (PbFamilyInfoService)SpringBeanUtil.getBean("pbFamilyInfoService");
	

	/* (non-Javadoc)
	 * @see com.jade.hr.component.tansfer.service.impl.InfoTransferService#transfer(java.lang.Long, java.lang.Long)
	 */
	public void transfer(Long bizPersonOid, Long personOid) throws ServiceException {
		List<PtFamilyInfoDTO> list = ptFamilyInfoService.listPtFamilyInfoByPersonOid(bizPersonOid);
		
		if (CollectionUtils.isNotEmpty(list)) {

			PbFamilyInfoDTO pbFamilyInfoDTO = null;
			for (PtFamilyInfoDTO dto : list) {
				
				pbFamilyInfoDTO = new PbFamilyInfoDTO();
				BeanHelper.copyProperties(dto, pbFamilyInfoDTO);
				
				pbFamilyInfoDTO.setPersonOid(personOid);
				
				pbFamilyInfoDTO.setCreateBy(UserContext.getLoginUserID());
				pbFamilyInfoDTO.setCreateName(UserContext.getLoginUserName());
				pbFamilyInfoDTO.setCreateDate(DateUtil.now());
				
				pbFamilyInfoDTO.setUpdateBy(UserContext.getLoginUserID());
				pbFamilyInfoDTO.setUpdateName(UserContext.getLoginUserName());
				pbFamilyInfoDTO.setUpdateDate(DateUtil.now());
				
				pbFamilyInfoService.createPbFamilyInfo(pbFamilyInfoDTO);
			}
		}
	}

}
