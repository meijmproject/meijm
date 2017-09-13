/**
 * 
 */
package com.yh.hr.component.transfer.comm.bt;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pb.dto.PbEducationInfoDTO;
import com.yh.hr.res.pb.service.PbEducationInfoService;
import com.yh.hr.res.pt.dto.PtEducationInfoDTO;
import com.yh.hr.res.pt.dto.PtPersonDTO;
import com.yh.hr.res.pt.service.PtEducationInfoService;
import com.yh.hr.res.pt.service.PtPersonService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 基础库学历信息转到业务库(事业)
 * @author	lihj
 * @version	1.0,	16/11/25
 */

public class BtSyPbEducationInfoTransferService implements InfoTransferService {
	private PtEducationInfoService syPtEducationInfoService = (PtEducationInfoService) SpringBeanUtil.getBean("syPtEducationInfoService");
	private PbEducationInfoService pbEducationInfoService = (PbEducationInfoService) SpringBeanUtil.getBean("pbEducationInfoService");
	private PtPersonService ptPersonService = (PtPersonService)SpringBeanUtil.getBean("ptPersonService");
	
	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long)
	 */
	public void transfer(Long taskOid, Long personOid) throws ServiceException {
		//获取基础库学历信息
		List<PbEducationInfoDTO> list = pbEducationInfoService.listPbEducationInfoByPersonOid(personOid);
		
		if(CollectionUtils.isNotEmpty(list)) {
			
			String uid = UserContext.getLoginUserID();
			String uname = UserContext.getLoginUserName();
			PtPersonDTO ptPersonDTO = ptPersonService.getByTaskOid(taskOid);
			if(ptPersonDTO != null){
				PtEducationInfoDTO ptEducationInfoDTO = null;
				for (PbEducationInfoDTO dto : list) {
					ptEducationInfoDTO = new PtEducationInfoDTO();
					BeanHelper.copyProperties(dto, ptEducationInfoDTO);
					ptEducationInfoDTO.setBizPersonOid(ptPersonDTO.getBizPersonOid());
					ptEducationInfoDTO.setCreatedByCode(uid);
					ptEducationInfoDTO.setCreatedByName(uname);
					ptEducationInfoDTO.setCreatedDate(DateUtil.now());
					ptEducationInfoDTO.setUpdatedByCode(uid);
					ptEducationInfoDTO.setUpdatedByName(uname);
					ptEducationInfoDTO.setUpdatedDate(DateUtil.now());
					syPtEducationInfoService.createPtEducationInfo(ptEducationInfoDTO);
				}
			}
		}
	}

}
