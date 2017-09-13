package com.yh.hr.info.dataimport.person.service.transfer;

import jade.workflow.utils.SpringBeanUtil;

import java.util.Date;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.im.dto.ImCheckPersonInfoDTO;
import com.yh.hr.res.im.service.ImCheckPersonInfoService;
import com.yh.hr.res.pb.dto.PbPersonOutDTO;
import com.yh.hr.res.pb.service.PbPersonOutService;
import com.yh.platform.core.exception.ServiceException;

/**
 * 人员导入人员离开信息入库service
 * @author wangx
 * @date 2017-07-14
 * @version 1.0
 */
public class ImportPersonOutTransferService implements InfoTransferService {

	private ImCheckPersonInfoService imCheckPersonInfoService = (ImCheckPersonInfoService) SpringBeanUtil.getBean("imCheckPersonInfoService");
	private PbPersonOutService pbPersonOutService = (PbPersonOutService) SpringBeanUtil.getBean("pbPersonOutService");

	/**
	 * 入库
	 */
	@Override
	public void transfer(Long checkPersonInfoOid, Long personOid)
			throws ServiceException {
		ImCheckPersonInfoDTO ImCheckPersonInfoDTO = imCheckPersonInfoService.get(checkPersonInfoOid);
		PbPersonOutDTO pbPersonOutDTO = new PbPersonOutDTO();
		Date outDate = ImCheckPersonInfoDTO.getOutDate();
		if(outDate!=null) {
			pbPersonOutDTO.setPersonOid(personOid);
			pbPersonOutDTO.setOutDate(outDate);
			pbPersonOutService.create(pbPersonOutDTO);
		}
	}
}
