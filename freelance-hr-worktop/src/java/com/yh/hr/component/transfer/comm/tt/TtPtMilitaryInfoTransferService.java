package com.yh.hr.component.transfer.comm.tt;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pt.dto.PtMilitaryInfoDTO;
import com.yh.hr.res.pt.service.PtMilitaryInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 业务库军队任职信息转到业务库
 * @date 2016-10-18 
 * @version 1.0 
 */
public class TtPtMilitaryInfoTransferService implements InfoTransferService {

	private PtMilitaryInfoService ptMilitaryInfoService = (PtMilitaryInfoService) SpringBeanUtil.getBean("ptMilitaryInfoService");
	
	@Override
	public void transfer(Long refBizPersonOid, Long bizPersonOid) throws ServiceException {
		List<PtMilitaryInfoDTO> list= ptMilitaryInfoService.getPtMilitaryInfoDTOList(refBizPersonOid);
	   
		if(CollectionUtils.isNotEmpty(list)) {

			String uid = UserContext.getLoginUserID();
			String uname = UserContext.getLoginUserName();
			
			PtMilitaryInfoDTO ptMilitaryInfoDTO=null;
            for (PtMilitaryInfoDTO dto : list) {
				
            	ptMilitaryInfoDTO = new PtMilitaryInfoDTO();
				BeanHelper.copyProperties(dto, ptMilitaryInfoDTO);
				
				ptMilitaryInfoDTO.setPtMilitaryOid(null);
				ptMilitaryInfoDTO.setBizPersonOid(bizPersonOid);
				
				ptMilitaryInfoDTO.setCreatedByCode(uid);
				ptMilitaryInfoDTO.setCreatedByName(uname);
				ptMilitaryInfoDTO.setCreatedDate(DateUtil.now());
				ptMilitaryInfoDTO.setUpdatedByCode(uid);
				ptMilitaryInfoDTO.setUpdatedByName(uname);
				ptMilitaryInfoDTO.setUpdatedDate(DateUtil.now());
				
				ptMilitaryInfoService.createPtMilitaryInfo(ptMilitaryInfoDTO);
			}
	    }
		
	}
}
