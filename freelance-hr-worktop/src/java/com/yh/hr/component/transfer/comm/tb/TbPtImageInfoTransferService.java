package com.yh.hr.component.transfer.comm.tb;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.pb.dto.PbImageDTO;
import com.yh.hr.res.pb.service.PbImageService;
import com.yh.hr.res.pt.dto.PtImageDTO;
import com.yh.hr.res.pt.service.PtImageService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 业务库照片信息转到基础库
 * @author	zhangqp
 * @version	1.0,	16/10/17
 */
public class TbPtImageInfoTransferService implements InfoTransferService {
	private PtImageService ptImageService = (PtImageService) SpringBeanUtil.getBean("ptImageService");
	private PbImageService pbImageService = (PbImageService) SpringBeanUtil.getBean("pbImageService");

	@Override
	public void transfer(Long bizPersonOid, Long personOid)throws ServiceException {
		
		PtImageDTO ptImageDTO= ptImageService.getPtImage(bizPersonOid);
		
		if(null!=ptImageDTO){
			PbImageDTO pbImageDTO = new PbImageDTO();
			
			BeanHelper.copyProperties(ptImageDTO, pbImageDTO);
			pbImageDTO.setPersonOid(personOid);
			pbImageService.create(pbImageDTO);
		}
		
	};
}
