package com.yh.hr.component.transfer.comm.tt;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pt.dto.PtFamilyInfoDTO;
import com.yh.hr.res.pt.service.PtFamilyInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 业务库家庭信息转到业务库
 * @date 2016-10-18 
 * @version 1.0 
 */
public class TtPtFamilyInfoTransferService implements InfoTransferService {
	
	private PtFamilyInfoService ptFamilyInfoService = (PtFamilyInfoService) SpringBeanUtil.getBean("ptFamilyInfoService");
	
	@Override
	public void transfer(Long refBizPersonOid, Long bizPersonOid) throws ServiceException {
		List<PtFamilyInfoDTO> list= ptFamilyInfoService.listPtFamilyInfoByPersonOid(refBizPersonOid);
	   
		if(CollectionUtils.isNotEmpty(list)) {

			String uid = UserContext.getLoginUserID();
			String uname = UserContext.getLoginUserName();
			
			PtFamilyInfoDTO ptFamilyInfoDTO=null;
            for (PtFamilyInfoDTO dto : list) {
				
            	ptFamilyInfoDTO = new PtFamilyInfoDTO();
				BeanHelper.copyProperties(dto, ptFamilyInfoDTO);
				
				ptFamilyInfoDTO.setPtFamilyOid(null);
				ptFamilyInfoDTO.setBizPersonOid(bizPersonOid);
				
				ptFamilyInfoDTO.setCreateBy(uid);
				ptFamilyInfoDTO.setCreateName(uname);
				ptFamilyInfoDTO.setCreateDate(DateUtil.now());
				ptFamilyInfoDTO.setUpdateBy(uid);
				ptFamilyInfoDTO.setUpdateName(uname);
				ptFamilyInfoDTO.setUpdateDate(DateUtil.now());
				
				ptFamilyInfoService.createPtFamilyInfo(ptFamilyInfoDTO);
			}
	    }
		
	}

}
