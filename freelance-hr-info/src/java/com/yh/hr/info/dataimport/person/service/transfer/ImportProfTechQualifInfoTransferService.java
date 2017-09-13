package com.yh.hr.info.dataimport.person.service.transfer;

import jade.workflow.utils.SpringBeanUtil;

import java.util.Date;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.im.dto.ImCheckPersonInfoDTO;
import com.yh.hr.res.im.service.ImCheckPersonInfoService;
import com.yh.hr.res.pb.dto.PbProfTechQualifInfoDTO;
import com.yh.hr.res.pb.service.PbProfTechQualifInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.StringUtil;

/**
 * 人员导入专业技术资格信息入库service
 * @author wangx
 * @date 2017-07-14
 * @version 1.0
 */
public class ImportProfTechQualifInfoTransferService implements InfoTransferService {

	private ImCheckPersonInfoService imCheckPersonInfoService = (ImCheckPersonInfoService) SpringBeanUtil.getBean("imCheckPersonInfoService");
	private PbProfTechQualifInfoService pbProfTechQualifInfoService = (PbProfTechQualifInfoService) SpringBeanUtil.getBean("pbProfTechQualifInfoService");

	/**
	 * 入库
	 */
	@Override
	public void transfer(Long checkPersonInfoOid, Long personOid)
			throws ServiceException {
		ImCheckPersonInfoDTO ImCheckPersonInfoDTO = imCheckPersonInfoService.get(checkPersonInfoOid);
		PbProfTechQualifInfoDTO pbProfTechQualifInfoDTO = new PbProfTechQualifInfoDTO();
		String profTechCode = ImCheckPersonInfoDTO.getProfTechCode();
		String profTechName = ImCheckPersonInfoDTO.getProfTechCodeDesc();
		String profTechLevel = ImCheckPersonInfoDTO.getProfTechLevel();
		String profTechLevelName = ImCheckPersonInfoDTO.getProfTechLevelDesc();
		Date procureDate = ImCheckPersonInfoDTO.getProfTechDate();
		if(StringUtil.isNotBlank(profTechCode)||StringUtil.isNotBlank(profTechLevel)||procureDate!=null) {
			pbProfTechQualifInfoDTO.setPersonOid(personOid);
			pbProfTechQualifInfoDTO.setProfTechCode(profTechCode);
			pbProfTechQualifInfoDTO.setProfTechName(profTechName);
			pbProfTechQualifInfoDTO.setProfTechLevel(profTechLevel);
			pbProfTechQualifInfoDTO.setProfTechLevelName(profTechLevelName);
			pbProfTechQualifInfoDTO.setProcureDate(procureDate);
			pbProfTechQualifInfoService.create(pbProfTechQualifInfoDTO);
		}
	}
}
