/**
 * 
 */
package com.yh.hr.component.transfer.comm.bt;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.pb.service.PbResumeInfoService;
import com.yh.hr.res.pt.service.PtPersonService;
import com.yh.hr.res.pt.service.PtResumeInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * @desc 工作简历基础库转业务库
 * @author luqy
 * @createDate 2016-11-7下午02:39:21
 */
public class BtPbResumeInfoTransferService implements InfoTransferService {
	@SuppressWarnings("unused")
	private PtResumeInfoService ptResumeInfoService = (PtResumeInfoService) SpringBeanUtil.getBean("ptResumeInfoService");
	@SuppressWarnings("unused")
	private PbResumeInfoService pbResumeInfoService = (PbResumeInfoService) SpringBeanUtil.getBean("pbResumeInfoService");
	@SuppressWarnings("unused")
	private PtPersonService ptPersonService = (PtPersonService)SpringBeanUtil.getBean("ptPersonService");
	
	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long)
	 */
	public void transfer(Long taskOid, Long personOid) throws ServiceException {
//		List<PbResumeInfoDTO> list = pbResumeInfoService.find(personOid);
//		if(CollectionUtils.isNotEmpty(list)) {
//			PtPersonDTO ptPersonDTO = ptPersonService.getByTaskOid(taskOid);
//			if(ptPersonDTO != null){
//				PtResumeInfoDTO ptResumeInfoDTO = null;
//				for (PbResumeInfoDTO pbResumeInfoDTO : list) {
//					ptResumeInfoDTO = new PtResumeInfoDTO();
//					BeanHelper.copyProperties(pbResumeInfoDTO, ptResumeInfoDTO);
//					ptResumeInfoDTO.setBizPersonOid(ptPersonDTO.getBizPersonOid());
//					ptResumeInfoDTO.setCreatedByCode(UserContext.getLoginUserID());
//					ptResumeInfoDTO.setCreatedByName(UserContext.getLoginUserName());
//					ptResumeInfoDTO.setCreatedDate(DateUtil.now());
//					ptResumeInfoService.createPtResumeInfo(ptResumeInfoDTO);
//				}
//			}
//		}
	}

}
