package com.yh.hr.info.dataimport.person.service.transfer;

import jade.workflow.utils.SpringBeanUtil;

import java.util.Date;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.im.dto.ImCheckPersonInfoDTO;
import com.yh.hr.res.im.service.ImCheckPersonInfoService;
import com.yh.hr.res.pb.dto.PbPersonInDTO;
import com.yh.hr.res.pb.service.PbPersonInService;
import com.yh.platform.core.exception.ServiceException;

/**
 * 人员导入人员进入信息入库service
 * @author wangx
 * @date 2017-07-14
 * @version 1.0
 */
public class ImportPersonInTransferService implements InfoTransferService {

	private ImCheckPersonInfoService imCheckPersonInfoService = (ImCheckPersonInfoService) SpringBeanUtil.getBean("imCheckPersonInfoService");
	private PbPersonInService pbPersonInService = (PbPersonInService) SpringBeanUtil.getBean("pbPersonInService");

	/**
	 * 入库
	 */
	@Override
	public void transfer(Long checkPersonInfoOid, Long personOid)
			throws ServiceException {
		ImCheckPersonInfoDTO ImCheckPersonInfoDTO = imCheckPersonInfoService.get(checkPersonInfoOid);
		PbPersonInDTO pbPersonInDTO = new PbPersonInDTO();
		Date entryCurrentUnitDate = ImCheckPersonInfoDTO.getEntryCurrentUnitDate();
		if(entryCurrentUnitDate!=null) {
			pbPersonInDTO.setPersonOid(personOid);
			pbPersonInDTO.setEntryCurrentUnitDate(entryCurrentUnitDate);
			pbPersonInService.create(pbPersonInDTO);
		}
	}
}
