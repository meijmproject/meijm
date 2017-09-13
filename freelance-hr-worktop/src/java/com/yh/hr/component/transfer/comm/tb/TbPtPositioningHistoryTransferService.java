package com.yh.hr.component.transfer.comm.tb;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pb.dto.PbPositioningDTO;
import com.yh.hr.res.pb.service.PbPositioningService;
import com.yh.hr.res.pt.dto.PtPositioningHistoryDTO;
import com.yh.hr.res.pt.service.PtPositioningHistoryService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 业务库任职历史信息转到基础库
 * @author wangjie
 * @date 2016-10-26 
 * @version 1.0 
 */
public class TbPtPositioningHistoryTransferService implements InfoTransferService {
	private PtPositioningHistoryService ptPositioningHistoryService = (PtPositioningHistoryService)SpringBeanUtil.getBean("ptPositioningHistoryService");
	private PbPositioningService pbPositioningService = (PbPositioningService)SpringBeanUtil.getBean("pbPositioningService");
	
	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long, java.lang.Long)
	 */
	public void transfer(Long bizPersonOid, Long personOid) throws ServiceException {
		List<PtPositioningHistoryDTO>  list = ptPositioningHistoryService.listPtPositioningByBizPersonId(bizPersonOid);
		
		if (CollectionUtils.isNotEmpty(list)) {

			PbPositioningDTO pbPositioningInfoDTO = null;
			for (PtPositioningHistoryDTO dto : list) {
				
				pbPositioningInfoDTO = new PbPositioningDTO();
				BeanHelper.copyProperties(dto, pbPositioningInfoDTO);
				
				pbPositioningInfoDTO.setPersonOid(personOid);
				
				if (pbPositioningInfoDTO.getDutyDate() == null) {
					pbPositioningInfoDTO.setDutyDate(DateUtil.now());
					dto.setDutyDate(pbPositioningInfoDTO.getDutyDate());
					ptPositioningHistoryService.updatePtPositioning(dto);
				}
				
				pbPositioningService.insertPbPositioning(pbPositioningInfoDTO);
			}
		}
	}
}
