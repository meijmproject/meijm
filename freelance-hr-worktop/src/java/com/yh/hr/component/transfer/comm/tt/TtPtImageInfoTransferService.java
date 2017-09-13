package com.yh.hr.component.transfer.comm.tt;



import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.pt.dto.PtImageDTO;
import com.yh.hr.res.pt.service.PtImageService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 业务库照片信息转到业务库
 * @date 2016-10-18 
 * @version 1.0 
 */
public class TtPtImageInfoTransferService implements InfoTransferService{
		private PtImageService ptImageService = (PtImageService) SpringBeanUtil.getBean("ptImageService");
		
		public void transfer(Long refBizPersonOid, Long bizPersonOid) throws ServiceException {
			PtImageDTO ptImageDTO  = ptImageService.getPtImage(refBizPersonOid);
			
			if(null!=ptImageDTO) {
				
				String uid = UserContext.getLoginUserID();
				String uname = UserContext.getLoginUserName();
					
					//ptImageDTO.setPtOid(null);
					ptImageDTO.setBizPersonOid(bizPersonOid);
					
					ptImageDTO.setCreatedByCode(uid);
					ptImageDTO.setCreatedByName(uname);
					ptImageDTO.setCreatedDate(DateUtil.now());
					ptImageDTO.setUpdatedByCode(uid);
					ptImageDTO.setUpdatedByName(uname);
					ptImageDTO.setUpdatedDate(DateUtil.now());
					
					ptImageService.createPtImageInfo(ptImageDTO);
				
			}
		}

}
