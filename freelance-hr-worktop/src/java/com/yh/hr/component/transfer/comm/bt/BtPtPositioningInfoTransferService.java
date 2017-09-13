package com.yh.hr.component.transfer.comm.bt;


import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pb.dto.PbPositioningDTO;
import com.yh.hr.res.pb.service.PbPositioningService;
import com.yh.hr.res.pt.dto.PtPersonDTO;
import com.yh.hr.res.pt.dto.PtPositioningInfoDTO;
import com.yh.hr.res.pt.service.PtPersonService;
import com.yh.hr.res.pt.service.PtPositioningService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 基础任职信息转任职业务表
 * @author zhengdr
 *
 * 时间:2016-12-5下午04:42:59
 */
public class BtPtPositioningInfoTransferService implements InfoTransferService {
	
	private PbPositioningService pbPositioningService = (PbPositioningService)SpringBeanUtil.getBean("pbPositioningService");
	private PtPositioningService ptPositioningService = (PtPositioningService)SpringBeanUtil.getBean("ptPositioningService");
	private PtPersonService ptPersonService = (PtPersonService)SpringBeanUtil.getBean("ptPersonService");
	
	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long, java.lang.Long)
	 */
	public void transfer(Long taskOid, Long personOid) throws ServiceException {
		
		//得到基础库任职信息
		PbPositioningDTO pbPositioningDTO = pbPositioningService.getTopPbPositioningInfoDTO(personOid,DicConstants.YHRS0003_1);
		
		if (null != pbPositioningDTO) {
			PtPersonDTO ptPersonDTO = ptPersonService.getByTaskOid(taskOid);
			if(null != ptPersonDTO){
				 //创建任职信息
				 PtPositioningInfoDTO ptPositioningInfoDTO = new PtPositioningInfoDTO();
				 BeanHelper.copyProperties(pbPositioningDTO, ptPositioningInfoDTO);
				    
				 ptPositioningInfoDTO.setBizPersonOid(ptPersonDTO.getBizPersonOid());
				    
			     ptPositioningService.insertPtPositioning(ptPositioningInfoDTO);
			}
		   
		}
	}

}
