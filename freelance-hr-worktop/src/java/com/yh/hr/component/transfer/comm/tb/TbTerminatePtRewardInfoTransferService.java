/**
 * 
 */
package com.yh.hr.component.transfer.comm.tb;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pb.service.PbRewardInfoService;
import com.yh.hr.res.pt.dto.PtPersonDTO;
import com.yh.hr.res.pt.dto.PtRewardInfoDTO;
import com.yh.hr.res.pt.service.PtPersonService;
import com.yh.hr.res.pt.service.PtRewardInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 撤销奖励信息转到基础库
 * @author	xiongyx
 * @version	1.0,	16/12/2
 */

public class TbTerminatePtRewardInfoTransferService implements InfoTransferService {
	
	@SuppressWarnings("unused")
	private PbRewardInfoService pbRewardInfoService = (PbRewardInfoService)SpringBeanUtil.getBean("pbRewardInfoService");
	private PtRewardInfoService ptRewardInfoService = (PtRewardInfoService)SpringBeanUtil.getBean("ptRewardInfoService");
	private PtPersonService ptPersonService = (PtPersonService)SpringBeanUtil.getBean("ptPersonService");

	public void transfer(Long bizPersonOid, Long personOid) throws ServiceException {
		@SuppressWarnings("unused")
		PtPersonDTO ptPersonDTO = ptPersonService.getPtPersonDTO(bizPersonOid);
		List<PtRewardInfoDTO> pt = ptRewardInfoService.findPtRewardInfoByCondition(bizPersonOid);
//		List<PbRewardInfoDTO> listPb = pbRewardInfoService.findPbRewardInfoByCondition(ptPersonDTO.getPersonOid());
		if (CollectionUtils.isNotEmpty(pt)) {
			PtRewardInfoDTO ptDTO = new PtRewardInfoDTO();
			for (PtRewardInfoDTO dto : pt) {
				if(null == dto.getRewardOid()){
					BeanHelper.copyProperties(dto, ptDTO);
				}
			}
			for (@SuppressWarnings("unused") PtRewardInfoDTO dto : pt) {
//				for (PbRewardInfoDTO pb : listPb) {
//					if(null != dto.getRewardOid()){
//						if(dto.getRewardOid().longValue() == pb.getRewardOid().longValue()){
//							pb.setIsQuashReward(DicConstants.YHRS0003_1);
//							pb.setQuashRewardReason(ptDTO.getQuashRewardReason());
//							pb.setQuashRewardDate(ptDTO.getQuashRewardDate());
//							pb.setQuashRewardFileno(ptDTO.getQuashRewardFileno());
//							pb.setQuashRewardUnit(ptDTO.getQuashRewardUnit());					
//							pbRewardInfoService.update(pb);
//						}
//					}
//				}
			}
		}
	}

}
