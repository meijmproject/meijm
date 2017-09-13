/**
 * 
 */
package com.yh.hr.component.transfer.comm.tt;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pt.dto.PtPositioningInfoDTO;
import com.yh.hr.res.pt.dto.PtPostInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtPositioningInfoQueryHelper;
import com.yh.hr.res.pt.service.PtPositioningService;
import com.yh.hr.res.pt.service.PtPostInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 岗位信息转到业务库
 * @author	zhangqp
 * @version	1.0,	16/10/17
 */

public class TtPtPostInfoTransferService implements InfoTransferService {
	
	private PtPostInfoService ptPostInfoService = (PtPostInfoService) SpringBeanUtil.getBean("ptPostInfoService");
	private PtPositioningService ptPositioningService = (PtPositioningService)SpringBeanUtil.getBean("ptPositioningService");
	
	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long)
	 */
	public void transfer(Long refBizPersonOid, Long bizPersonOid) throws ServiceException {
		List<PtPostInfoDTO> list = ptPostInfoService.listPtPostInfoByBizPersonOid(refBizPersonOid);
		
		if(CollectionUtils.isNotEmpty(list)) {

			
			String uid = UserContext.getLoginUserID();
			String uname = UserContext.getLoginUserName();
			
			PtPostInfoDTO ptPostInfoDTO = null;
			for (PtPostInfoDTO dto : list) {
				PtPositioningInfoDTO positioningInfoDTO=new PtPositioningInfoDTO();
				PtPositioningInfoDTO ptPositioningInfoDTO=PtPositioningInfoQueryHelper.getPtPositioningInfoDTOById(dto.getPositioningOid());
				BeanHelper.copyProperties(ptPositioningInfoDTO, positioningInfoDTO);
				positioningInfoDTO.setBizPersonOid(bizPersonOid);
				
				positioningInfoDTO.setCreatedByCode(uid);
				positioningInfoDTO.setCreatedByName(uname);
				positioningInfoDTO.setCreatedDate(DateUtil.now());
				ptPositioningService.insertPtPositioning(positioningInfoDTO);
				
				
				ptPostInfoDTO = new PtPostInfoDTO();
				BeanHelper.copyProperties(dto, ptPostInfoDTO);
				
				ptPostInfoDTO.setPtPostOid(null);
				ptPostInfoDTO.setBizPersonOid(bizPersonOid);
				ptPostInfoDTO.setPositioningOid(positioningInfoDTO.getPtPositioningInfoOid());
				
				ptPostInfoDTO.setCreatedByCode(uid);
				ptPostInfoDTO.setCreatedByName(uname);
				ptPostInfoDTO.setCreatedDate(DateUtil.now());
				
				ptPostInfoService.createPtPost(ptPostInfoDTO);
			}
		}
	}

}
