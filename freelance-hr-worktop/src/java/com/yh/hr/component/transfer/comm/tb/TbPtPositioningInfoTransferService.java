/**
 * 
 */
package com.yh.hr.component.transfer.comm.tb;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pb.dto.PbPositioningDTO;
import com.yh.hr.res.pb.service.PbPositioningService;
import com.yh.hr.res.pt.dto.PtPositioningInfoDTO;
import com.yh.hr.res.pt.service.PtPositioningService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 业务库任职信息转到基础库
 * @author	zhangqp
 * @version	1.0,	16/10/17
 */

public class TbPtPositioningInfoTransferService implements InfoTransferService {
	
	private PtPositioningService ptPositioningService = (PtPositioningService)SpringBeanUtil.getBean("ptPositioningService");
	private PbPositioningService pbPositioningService = (PbPositioningService)SpringBeanUtil.getBean("pbPositioningService");
	
	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long, java.lang.Long)
	 */
	public void transfer(Long bizPersonOid, Long personOid) throws ServiceException {
		List<PtPositioningInfoDTO> list = ptPositioningService.listPtPositioningByBizPersonId(bizPersonOid);
		
		if (CollectionUtils.isNotEmpty(list)) {

			PbPositioningDTO pbPositioningInfoDTO = null;
			for (PtPositioningInfoDTO dto : list) {
				
				pbPositioningInfoDTO = new PbPositioningDTO();
				BeanHelper.copyProperties(dto, pbPositioningInfoDTO);
				
				pbPositioningInfoDTO.setPersonOid(personOid);
				
				if (pbPositioningInfoDTO.getDutyDate() == null) {
					pbPositioningInfoDTO.setDutyDate(DateUtil.now());
					dto.setDutyDate(pbPositioningInfoDTO.getDutyDate());
					ptPositioningService.updatePtPositioning(dto);
				}
				
				pbPositioningInfoDTO.setPositioningStatus(DicConstants.YHRS0026_001);
				pbPositioningInfoDTO.setDutyRecordType(DicConstants.YHRS0036_1);
				pbPositioningService.insertPbPositioning(pbPositioningInfoDTO);
			}
		}
	}

}
