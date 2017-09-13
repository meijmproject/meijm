package com.yh.hr.component.transfer.comm.tb;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.pb.dto.PbProbationInfoDTO;
import com.yh.hr.res.pb.service.PbProbationInfoService;
import com.yh.hr.res.pt.bo.PtProbationInfo;
import com.yh.hr.res.pt.queryhelper.PtProbationInfoQueryHelper;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 试用期情况 业务库 转 基础库
 * @author zhengdr
 *
 * 时间:2016-11-21上午09:13:57
 */
public class TbPtProbationInfoTransferService implements InfoTransferService {
	
	private PbProbationInfoService pbProbationInfoService = (PbProbationInfoService) SpringBeanUtil.getBean("pbProbationInfoService");


	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long)
	 */
	public void transfer(Long bizPersonOid, Long personOid) throws ServiceException {
		//得到业务信息
		PtProbationInfo ptProbationInfo = PtProbationInfoQueryHelper.getByBizPersonOid(bizPersonOid);
		if(ptProbationInfo!=null) {
			
			PbProbationInfoDTO  pbProbationInfoDTO = new PbProbationInfoDTO();
			//复制
			BeanHelper.copyProperties(ptProbationInfo, pbProbationInfoDTO);
			pbProbationInfoDTO.setPersonOid(personOid);
			
			//试用期情况只存在一条 
			List<PbProbationInfoDTO> pbProbationInfoDTOList = pbProbationInfoService.listPbProbationInfoDTO(personOid);
			if(pbProbationInfoDTOList!=null&&pbProbationInfoDTOList.size()>0){
				PbProbationInfoDTO tempPbProbationInfoDTO = pbProbationInfoDTOList.get(0);
				pbProbationInfoDTO.setProbationOid(tempPbProbationInfoDTO.getProbationOid());
				//更新
				pbProbationInfoService.updatePbProbationInfo(pbProbationInfoDTO);
			}else{
				//创建基础库信息
				pbProbationInfoService.createPbProbationInfo(pbProbationInfoDTO);
			}
			
		}
	}

}
