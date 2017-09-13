package com.yh.hr.info.dataimport.person.service.transfer;

import jade.workflow.utils.SpringBeanUtil;

import java.util.Date;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.im.dto.ImCheckPersonInfoDTO;
import com.yh.hr.res.im.service.ImCheckPersonInfoService;
import com.yh.hr.res.pb.dto.PbRetrieInfoDTO;
import com.yh.hr.res.pb.service.PbRetrieInfoService;
import com.yh.platform.core.exception.ServiceException;

/**
 * 人员导入离退休信息入库service
 * @author wangx
 * @date 2017-07-14
 * @version 1.0
 */
public class ImportRetrieInfoTransferService implements InfoTransferService {

	private ImCheckPersonInfoService imCheckPersonInfoService = (ImCheckPersonInfoService) SpringBeanUtil.getBean("imCheckPersonInfoService");
	private PbRetrieInfoService pbRetrieInfoService = (PbRetrieInfoService) SpringBeanUtil.getBean("pbRetrieInfoService");

	/**
	 * 入库
	 */
	@Override
	public void transfer(Long checkPersonInfoOid, Long personOid)
			throws ServiceException {
		ImCheckPersonInfoDTO ImCheckPersonInfoDTO = imCheckPersonInfoService.get(checkPersonInfoOid);
		PbRetrieInfoDTO pbRetrieInfoDTO = new PbRetrieInfoDTO();
		Date retrieDate = ImCheckPersonInfoDTO.getRetrieDate();
		if(retrieDate!=null) {
			pbRetrieInfoDTO.setPersonOid(personOid);
			pbRetrieInfoDTO.setRetrieDate(retrieDate);
			pbRetrieInfoService.create(pbRetrieInfoDTO);
		}
	}
}
