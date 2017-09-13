/**
 * 
 */
package com.yh.hr.component.transfer.comm.tb;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.pb.service.PbResumeInfoService;
import com.yh.hr.res.pt.service.PtResumeInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;


/**
 * @desc 简历信息业务库转基础库
 * @author luqy
 * @createDate 2016-11-7下午03:45:45
 */
public class TbPtResumeInfoTransferService implements InfoTransferService {
	
	@SuppressWarnings("unused")
	private PtResumeInfoService ptResumeInfoService = (PtResumeInfoService)SpringBeanUtil.getBean("ptResumeInfoService");
	@SuppressWarnings("unused")
	private PbResumeInfoService pbResumeInfoService = (PbResumeInfoService)SpringBeanUtil.getBean("pbResumeInfoService");
	

	/* (non-Javadoc)
	 * @see com.yh.hr.component.tansfer.service.impl.InfoTransferService#transfer(java.lang.Long, java.lang.Long)
	 */
	public void transfer(Long bizPersonOid, Long personOid) throws ServiceException {
		//先删除之前的所有简历信息,然后创建新简历
//		List<PbResumeInfoDTO> pbResumeInfoDTOs = pbResumeInfoService.listPbResumeInfo(personOid);
//		if(CollectionUtils.isNotEmpty(pbResumeInfoDTOs)){
//			for (PbResumeInfoDTO pbResumeInfoDTO : pbResumeInfoDTOs) {
//				pbResumeInfoService.deletePbResumeInfoById(pbResumeInfoDTO.getResumeOid());
//			}
//		}
//		
//		//重新生成新的简历
//		List<PtResumeInfoDTO> list = ptResumeInfoService.listPtResumeInfo(bizPersonOid);
//		if (CollectionUtils.isNotEmpty(list)) {
//			PbResumeInfoDTO pbResumeInfoDTO = null;
//			for (PtResumeInfoDTO dto : list) {
//				pbResumeInfoDTO = new PbResumeInfoDTO();
//				BeanHelper.copyProperties(dto, pbResumeInfoDTO);
//				pbResumeInfoDTO.setPersonOid(personOid);
//				pbResumeInfoService.createPbResumeInfo(pbResumeInfoDTO);
//			}
//		}
	}

}
