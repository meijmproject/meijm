package com.yh.hr.info.dataimport.person.service.transfer;

import jade.workflow.utils.SpringBeanUtil;

import java.util.Date;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.im.dto.ImCheckPersonInfoDTO;
import com.yh.hr.res.im.service.ImCheckPersonInfoService;
import com.yh.hr.res.pb.dto.PbPoliticInfoDTO;
import com.yh.hr.res.pb.service.PbPoliticInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.StringUtil;

/**
 * 人员导入政治面貌信息入库service
 * @author wangx
 * @date 2017-07-14
 * @version 1.0
 */
public class ImportPoliticInfoTransferService implements InfoTransferService {

	private ImCheckPersonInfoService imCheckPersonInfoService = (ImCheckPersonInfoService) SpringBeanUtil.getBean("imCheckPersonInfoService");
	private PbPoliticInfoService pbPoliticInfoService = (PbPoliticInfoService) SpringBeanUtil.getBean("pbPoliticInfoService");

	/**
	 * 入库
	 */
	@Override
	public void transfer(Long checkPersonInfoOid, Long personOid)
			throws ServiceException {
		ImCheckPersonInfoDTO ImCheckPersonInfoDTO = imCheckPersonInfoService.get(checkPersonInfoOid);
		PbPoliticInfoDTO pbPoliticInfoDTO = new PbPoliticInfoDTO();
		String politicStatusCode = ImCheckPersonInfoDTO.getPoliticStatusCode();
		Date joinPoliticDate = ImCheckPersonInfoDTO.getJoinPoliticDate();
		if(StringUtil.isNotBlank(politicStatusCode)||joinPoliticDate!=null) {
			pbPoliticInfoDTO.setPersonOid(personOid);
			pbPoliticInfoDTO.setPoliticStatusCode(politicStatusCode);
			pbPoliticInfoDTO.setJoinPoliticDate(joinPoliticDate);
			pbPoliticInfoService.create(pbPoliticInfoDTO);
		}
	}
}
