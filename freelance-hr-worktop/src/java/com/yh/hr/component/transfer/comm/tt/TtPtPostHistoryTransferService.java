/**
 * 
 */
package com.yh.hr.component.transfer.comm.tt;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.pt.dto.PtPositioningHistoryDTO;
import com.yh.hr.res.pt.dto.PtPostInfoHistoryDTO;
import com.yh.hr.res.pt.queryhelper.PtPositioningHistoryQueryHelper;
import com.yh.hr.res.pt.service.PtPositioningHistoryService;
import com.yh.hr.res.pt.service.PtPostInfoHistoryService;
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

public class TtPtPostHistoryTransferService implements InfoTransferService {
	
	private PtPostInfoHistoryService ptPostInfoHistoryService = (PtPostInfoHistoryService) SpringBeanUtil.getBean("ptPostInfoHistoryService");
	private PtPositioningHistoryService ptPositioningHistoryService = (PtPositioningHistoryService) SpringBeanUtil.getBean("ptPositioningHistoryService");
	
	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long)
	 */
	public void transfer(Long refBizPersonOid, Long bizPersonOid) throws ServiceException {
		List<PtPostInfoHistoryDTO> list = ptPostInfoHistoryService.listPtPostInfoByBizPersonId(refBizPersonOid);
		
		if(CollectionUtils.isNotEmpty(list)) {

			
			String uid = UserContext.getLoginUserID();
			String uname = UserContext.getLoginUserName();
			
			PtPostInfoHistoryDTO ptPostInfoHistoryDTO = null;
			for (PtPostInfoHistoryDTO dto : list) {
				PtPositioningHistoryDTO ptPositioningHistoryDTO=new PtPositioningHistoryDTO();
				PtPositioningHistoryDTO ptPositioningInfoDTO=PtPositioningHistoryQueryHelper.getPtPositioningHistoryDTOById(dto.getPtPositioningHistoryOid());
				BeanHelper.copyProperties(ptPositioningInfoDTO, ptPositioningHistoryDTO);
				ptPositioningHistoryDTO.setBizPersonOid(bizPersonOid);
				
				ptPositioningHistoryDTO.setCreatedByCode(uid);
				ptPositioningHistoryDTO.setCreatedByName(uname);
				ptPositioningHistoryDTO.setCreatedDate(DateUtil.now());
				ptPositioningHistoryService.insertPtPositioning(ptPositioningHistoryDTO);
				
				ptPostInfoHistoryDTO = new PtPostInfoHistoryDTO();
				BeanHelper.copyProperties(dto, ptPostInfoHistoryDTO);
				
				ptPostInfoHistoryDTO.setPtPostHistoryOid(null);
				ptPostInfoHistoryDTO.setBizPersonOid(bizPersonOid);
				ptPostInfoHistoryDTO.setPtPositioningHistoryOid(ptPositioningHistoryDTO.getPtPositioningHistroyOid());
				
				ptPostInfoHistoryDTO.setCreatedByCode(uid);
				ptPostInfoHistoryDTO.setCreatedByName(uname);
				ptPostInfoHistoryDTO.setCreatedDate(DateUtil.now());
				
				ptPostInfoHistoryService.insertPtPostInfo(ptPostInfoHistoryDTO);
			}
		}
	}

}
