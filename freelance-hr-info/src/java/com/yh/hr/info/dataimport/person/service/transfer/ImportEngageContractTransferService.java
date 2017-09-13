package com.yh.hr.info.dataimport.person.service.transfer;

import jade.workflow.utils.SpringBeanUtil;

import java.util.Date;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.im.dto.ImCheckPersonInfoDTO;
import com.yh.hr.res.im.service.ImCheckPersonInfoService;
import com.yh.hr.res.pb.dto.PbEngageContractInfoDTO;
import com.yh.hr.res.pb.service.PbEngageContractInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.StringUtil;

/**
 * 人员导入合同信息入库service
 * @author wangx
 * @date 2017-07-14
 * @version 1.0
 */
public class ImportEngageContractTransferService implements InfoTransferService {

	private ImCheckPersonInfoService imCheckPersonInfoService = (ImCheckPersonInfoService) SpringBeanUtil.getBean("imCheckPersonInfoService");
	private PbEngageContractInfoService pbEngageContractInfoService = (PbEngageContractInfoService) SpringBeanUtil.getBean("pbEngageContractInfoService");

	/**
	 * 入库
	 */
	@Override
	public void transfer(Long checkPersonInfoOid, Long personOid)
			throws ServiceException {
		ImCheckPersonInfoDTO ImCheckPersonInfoDTO = imCheckPersonInfoService.get(checkPersonInfoOid);
		PbEngageContractInfoDTO pbEngageContractInfoDTO = new PbEngageContractInfoDTO();
		String contractNo = ImCheckPersonInfoDTO.getContractNo();
		String contractType = ImCheckPersonInfoDTO.getContractType();
		String changeType = ImCheckPersonInfoDTO.getChangeType();
		Date contractBegin = ImCheckPersonInfoDTO.getContractBegin();
		Date contractEnd = ImCheckPersonInfoDTO.getContractEnd();
		if(StringUtil.isNotBlank(contractNo)||StringUtil.isNotBlank(contractType)||StringUtil.isNotBlank(changeType)||contractBegin!=null||contractEnd!=null) {
			pbEngageContractInfoDTO.setPersonOid(personOid);
			pbEngageContractInfoDTO.setContractNo(contractNo);
			pbEngageContractInfoDTO.setContractType(contractType);
			pbEngageContractInfoDTO.setChangeType(changeType);
			pbEngageContractInfoDTO.setContractBegin(contractBegin);
			pbEngageContractInfoDTO.setContractEnd(contractEnd);
			pbEngageContractInfoDTO.setStatus(DicConstants.YHRS0116_1);
			pbEngageContractInfoService.create(pbEngageContractInfoDTO);
		}
	}
}
