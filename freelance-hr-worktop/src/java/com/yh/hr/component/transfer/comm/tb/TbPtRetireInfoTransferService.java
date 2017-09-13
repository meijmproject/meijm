package com.yh.hr.component.transfer.comm.tb;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.pb.service.PbRetrieInfoService;
import com.yh.hr.res.pt.service.PtRetrieInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 离退休信息业务库转基础库
 * @author went 20161109
 *
 */
public class TbPtRetireInfoTransferService implements InfoTransferService {
	@SuppressWarnings("unused")
	private PtRetrieInfoService ptRetrieInfoService = (PtRetrieInfoService)SpringBeanUtil.getBean("ptRetrieInfoService");
	@SuppressWarnings("unused")
	private PbRetrieInfoService pbRetrieInfoService = (PbRetrieInfoService)SpringBeanUtil.getBean("pbRetrieInfoService");
	
	@Override
	public void transfer(Long bizPersonOid, Long personOid)
			throws ServiceException {
//		PtRetrieInfoDTO ptRetrieInfoDTO = ptRetrieInfoService.getPtRetrieInfoByOid(bizPersonOid);
//		if(null != ptRetrieInfoDTO)
//		{
//			PbRetrieInfoDTO  pbRetrieInfoDTO  = pbRetrieInfoService.getPbRetrieInfoByOid(personOid);
//			if(null == pbRetrieInfoDTO)//基础库里为空时
//			{
//				pbRetrieInfoDTO = BeanHelper.copyProperties(ptRetrieInfoDTO,PbRetrieInfoDTO.class);
//				pbRetrieInfoDTO.setPersonOid(personOid);
//				pbRetrieInfoService.create(pbRetrieInfoDTO);
//			}
//			else//基础库里不为空时
//			{
//				BeanHelper.copyProperties(ptRetrieInfoDTO,pbRetrieInfoDTO);
//				pbRetrieInfoDTO.setPersonOid(personOid);
//				pbRetrieInfoService.updatePbRetrieInfo(pbRetrieInfoDTO);
//			}
//		}
	}
}
