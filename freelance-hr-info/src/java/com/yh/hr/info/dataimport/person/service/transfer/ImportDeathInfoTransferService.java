package com.yh.hr.info.dataimport.person.service.transfer;

import jade.workflow.utils.SpringBeanUtil;

import java.util.Date;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.im.dto.ImCheckPersonInfoDTO;
import com.yh.hr.res.im.service.ImCheckPersonInfoService;
import com.yh.hr.res.pb.dto.PbDeathInfoDTO;
import com.yh.hr.res.pb.service.PbDeathInfoService;
import com.yh.platform.core.exception.ServiceException;

/**
 * 人员导入死亡信息入库service
 * @author wangx
 * @date 2017-07-14
 * @version 1.0
 */
public class ImportDeathInfoTransferService implements InfoTransferService {

	private ImCheckPersonInfoService imCheckPersonInfoService = (ImCheckPersonInfoService) SpringBeanUtil.getBean("imCheckPersonInfoService");
	private PbDeathInfoService pbDeathInfoService = (PbDeathInfoService) SpringBeanUtil.getBean("pbDeathInfoService");

	/**
	 * 入库
	 */
	@Override
	public void transfer(Long checkPersonInfoOid, Long personOid)
			throws ServiceException {
		ImCheckPersonInfoDTO ImCheckPersonInfoDTO = imCheckPersonInfoService.get(checkPersonInfoOid);
		PbDeathInfoDTO pbDeathInfoDTO = new PbDeathInfoDTO();
		Date deathDate = ImCheckPersonInfoDTO.getDeathDate();
		if(deathDate!=null) {
			pbDeathInfoDTO.setPersonOid(personOid);
			pbDeathInfoDTO.setDeathDate(deathDate);
			pbDeathInfoService.create(pbDeathInfoDTO);
		}
	}
}
