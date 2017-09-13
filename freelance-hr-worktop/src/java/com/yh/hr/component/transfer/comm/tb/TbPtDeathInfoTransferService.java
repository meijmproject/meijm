package com.yh.hr.component.transfer.comm.tb;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.pb.service.PbDeathInfoService;
import com.yh.hr.res.pt.service.PtDeathInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 自然减员情况 业务库转基础库
 * @author zhengdr
 *
 * 时间:2016-11-28下午04:50:43
 */
public class TbPtDeathInfoTransferService implements InfoTransferService {
	
	@SuppressWarnings("unused")
	private PbDeathInfoService pbDeathInfoService = (PbDeathInfoService) SpringBeanUtil.getBean("pbDeathInfoService");
	@SuppressWarnings("unused")
	private PtDeathInfoService ptDeathInfoService = (PtDeathInfoService) SpringBeanUtil.getBean("ptDeathInfoService");

	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long)
	 */
	public void transfer(Long bizPersonOid, Long personOid) throws ServiceException {
		//得到业务信息
//		PtDeathInfoDTO ptDeathInfoDTO = ptDeathInfoService.findByBizPersonOid(bizPersonOid);
//		
//		if(ptDeathInfoDTO!=null) {
//
//			PbDeathInfoDTO  pbDeathInfoDTO = new PbDeathInfoDTO();
//			//复制
//			BeanHelper.copyProperties(ptDeathInfoDTO, pbDeathInfoDTO);
//				
//			pbDeathInfoDTO.setPersonOid(personOid);
//			//创建基础库信息
//			pbDeathInfoService.createPbDeathInfo(pbDeathInfoDTO);
//		
//		}
	}

}
