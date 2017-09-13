/**
 * 
 */
package com.yh.hr.component.transfer.comm.bt;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pb.dto.PbProfTechInfoDTO;
import com.yh.hr.res.pb.service.PbProfTechInfoService;
import com.yh.hr.res.pt.dto.PtProfTechInfoDTO;
import com.yh.hr.res.pt.dto.PtPersonDTO;
import com.yh.hr.res.pt.service.PtProfTechInfoService;
import com.yh.hr.res.pt.service.PtPersonService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 基础库专业技术资格信息转到业务库
 * @author	went
 * @version	1.0,	16/11/04
 */

public class BtPbProfTechInfoTransferService implements InfoTransferService {
	private PtProfTechInfoService ptProfTechInfoService = (PtProfTechInfoService) SpringBeanUtil.getBean("ptProfTechInfoService");
	private PbProfTechInfoService pbProfTechInfoService = (PbProfTechInfoService) SpringBeanUtil.getBean("pbProfTechInfoService");
	private PtPersonService ptPersonService = (PtPersonService)SpringBeanUtil.getBean("ptPersonService");
	
	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long)
	 */
	public void transfer(Long taskOid, Long personOid) throws ServiceException {
		//获取基础库专业技术资格信息
		List<PbProfTechInfoDTO> list = pbProfTechInfoService.listPbProfTechInfoDTO(personOid);
		
		if(CollectionUtils.isNotEmpty(list)) {
			
			String uid = UserContext.getLoginUserID();
			String uname = UserContext.getLoginUserName();
			PtPersonDTO ptPersonDTO = ptPersonService.getByTaskOid(taskOid);
			if(ptPersonDTO != null){
				PtProfTechInfoDTO ptProfTechInfoDTO = null;
				for (PbProfTechInfoDTO dto : list) {
					ptProfTechInfoDTO = new PtProfTechInfoDTO();
					BeanHelper.copyProperties(dto, ptProfTechInfoDTO);
					ptProfTechInfoDTO.setBizPersonOid(ptPersonDTO.getBizPersonOid());
					ptProfTechInfoDTO.setCreatedByCode(uid);
					ptProfTechInfoDTO.setCreatedByName(uname);
					ptProfTechInfoDTO.setCreatedDate(DateUtil.now());
					ptProfTechInfoService.createPtProfTechInfo(ptProfTechInfoDTO);
				}
			}
		}
	}

}
