/**
 * 
 */
package com.yh.hr.component.transfer.comm.bt;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pb.dto.PbProbationInfoDTO;
import com.yh.hr.res.pb.service.PbProbationInfoService;
import com.yh.hr.res.pt.dto.PtProbationInfoDTO;
import com.yh.hr.res.pt.dto.PtPersonDTO;
import com.yh.hr.res.pt.service.PtProbationInfoService;
import com.yh.hr.res.pt.service.PtPersonService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 基础库试用信息转到业务库
 * @author	lihj
 * @version	1.0,	16/12/07
 */

public class BtPbProbationInfoTransferService implements InfoTransferService {
	private PtProbationInfoService ptProbationInfoService = (PtProbationInfoService) SpringBeanUtil.getBean("ptProbationInfoService");
	private PbProbationInfoService pbProbationInfoService = (PbProbationInfoService) SpringBeanUtil.getBean("pbProbationInfoService");
	private PtPersonService ptPersonService = (PtPersonService)SpringBeanUtil.getBean("ptPersonService");
	
	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long)
	 */
	public void transfer(Long taskOid, Long personOid) throws ServiceException {
		//获取基础库试用信息
		List<PbProbationInfoDTO> list = pbProbationInfoService.listPbProbationInfoDTO(personOid);
		
		if(CollectionUtils.isNotEmpty(list)) {
			
			String uid = UserContext.getLoginUserID();
			String uname = UserContext.getLoginUserName();
			PtPersonDTO ptPersonDTO = ptPersonService.getByTaskOid(taskOid);
			if(ptPersonDTO != null){
				PtProbationInfoDTO ptProbationInfoDTO = null;
				for (PbProbationInfoDTO dto : list) {
					ptProbationInfoDTO = new PtProbationInfoDTO();
					BeanHelper.copyProperties(dto, ptProbationInfoDTO);
					ptProbationInfoDTO.setBizPersonOid(ptPersonDTO.getBizPersonOid());
					ptProbationInfoDTO.setCreatedByCode(uid);
					ptProbationInfoDTO.setCreatedByName(uname);
					ptProbationInfoDTO.setCreatedDate(DateUtil.now());
					ptProbationInfoDTO.setUpdatedByCode(uid);
					ptProbationInfoDTO.setUpdatedByName(uname);
					ptProbationInfoDTO.setUpdatedDate(DateUtil.now());
					ptProbationInfoService.create(ptProbationInfoDTO);
				}
			}
		}
	}

}
