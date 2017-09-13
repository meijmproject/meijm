package com.yh.hr.info.dataimport.person.service.transfer;

import jade.workflow.utils.SpringBeanUtil;

import java.util.Date;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.im.dto.ImCheckPersonInfoDTO;
import com.yh.hr.res.im.service.ImCheckPersonInfoService;
import com.yh.hr.res.pb.dto.PbQualificationsInfoDTO;
import com.yh.hr.res.pb.service.PbQualificationsInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.StringUtil;

/**
 * 人员导入职业资格信息入库service
 * @author wangx
 * @date 2017-07-14
 * @version 1.0
 */
public class ImportQualificationsInfoTransferService implements InfoTransferService {

	private ImCheckPersonInfoService imCheckPersonInfoService = (ImCheckPersonInfoService) SpringBeanUtil.getBean("imCheckPersonInfoService");
	private PbQualificationsInfoService pbQualificationsInfoService = (PbQualificationsInfoService) SpringBeanUtil.getBean("pbQualificationsInfoService");

	/**
	 * 入库
	 */
	@Override
	public void transfer(Long checkPersonInfoOid, Long personOid)
			throws ServiceException {
		ImCheckPersonInfoDTO ImCheckPersonInfoDTO = imCheckPersonInfoService.get(checkPersonInfoOid);
		PbQualificationsInfoDTO pbQualificationsInfoDTO = new PbQualificationsInfoDTO();
		String qualificationsCode = ImCheckPersonInfoDTO.getQualificationsCode();
		String qualificationsName = ImCheckPersonInfoDTO.getQualificationsCodeDesc();
		String qualificationsType = ImCheckPersonInfoDTO.getQualificationsType();
		String qualificationsLevelCode = ImCheckPersonInfoDTO.getQualificationsLevelCode();
		String qualificationsLevelName = ImCheckPersonInfoDTO.getQualificationsLevelCodeDesc();
		Date procureDate = ImCheckPersonInfoDTO.getQualificationsDate();
		if(StringUtil.isNotBlank(qualificationsCode)||StringUtil.isNotBlank(qualificationsType)||StringUtil.isNotBlank(qualificationsLevelCode)||procureDate!=null) {
			pbQualificationsInfoDTO.setPersonOid(personOid);
			pbQualificationsInfoDTO.setQualificationsCode(qualificationsCode);
			pbQualificationsInfoDTO.setQualificationsName(qualificationsName);
			pbQualificationsInfoDTO.setQualificationsType(qualificationsType);
			pbQualificationsInfoDTO.setQualificationsLevelCode(qualificationsLevelCode);
			pbQualificationsInfoDTO.setQualificationsLevelName(qualificationsLevelName);
			pbQualificationsInfoDTO.setProcureDate(procureDate);
			pbQualificationsInfoService.create(pbQualificationsInfoDTO);
		}
	}
}
