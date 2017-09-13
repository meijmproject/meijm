/**
 * 
 */
package com.yh.hr.component.transfer.comm.bt;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pb.dto.PbPositioningOtherInfoDTO;
import com.yh.hr.res.pb.service.PbPositioningOtherInfoService;
import com.yh.hr.res.pt.dto.PtPersonDTO;
import com.yh.hr.res.pt.dto.PtPositioningOtherInfoDTO;
import com.yh.hr.res.pt.service.PtPersonService;
import com.yh.hr.res.pt.service.PtPositioningOtherService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 基础库其他职务信息转到业务库
 * @author	lihj
 */

public class BtPtPositioningOtherTransferService implements InfoTransferService {
	private PtPositioningOtherService ptPositioningOtherService = (PtPositioningOtherService) SpringBeanUtil.getBean("ptPositioningOtherService");
	private PbPositioningOtherInfoService pbPositioningOtherInfoService = (PbPositioningOtherInfoService) SpringBeanUtil.getBean("pbPositioningOtherInfoService");
	private PtPersonService ptPersonService = (PtPersonService)SpringBeanUtil.getBean("ptPersonService");
	
	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long)
	 */
	public void transfer(Long taskOid, Long personOid) throws ServiceException {
		//获取基础库其他职务信息
		List<PbPositioningOtherInfoDTO> list = pbPositioningOtherInfoService.listPbPositioningOtherInfoByPersonOid(personOid);
		
		if(CollectionUtils.isNotEmpty(list)) {
			
			String uid = UserContext.getLoginUserID();
			String uname = UserContext.getLoginUserName();
			PtPersonDTO ptPersonDTO = ptPersonService.getByTaskOid(taskOid);
			if(ptPersonDTO != null){
				PtPositioningOtherInfoDTO ptPositioningOtherInfoDTO = null;
				for (PbPositioningOtherInfoDTO dto : list) {
					ptPositioningOtherInfoDTO = new PtPositioningOtherInfoDTO();
					BeanHelper.copyProperties(dto, ptPositioningOtherInfoDTO);
					ptPositioningOtherInfoDTO.setBizPersonOid(ptPersonDTO.getBizPersonOid());
					ptPositioningOtherInfoDTO.setCreatedByCode(uid);
					ptPositioningOtherInfoDTO.setCreatedByName(uname);
					ptPositioningOtherInfoDTO.setCreatedDate(DateUtil.now());
					ptPositioningOtherInfoDTO.setUpdatedByCode(uid);
					ptPositioningOtherInfoDTO.setUpdatedByName(uname);
					ptPositioningOtherInfoDTO.setUpdatedDate(DateUtil.now());
					ptPositioningOtherService.createPtPositioningOtherInfo(ptPositioningOtherInfoDTO);
				}
			}
		}
	}

}
