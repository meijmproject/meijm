package com.yh.hr.info.dataimport.person.service.transfer;

import jade.workflow.utils.SpringBeanUtil;

import java.util.Date;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.im.dto.ImCheckPersonInfoDTO;
import com.yh.hr.res.im.service.ImCheckPersonInfoService;
import com.yh.hr.res.pb.dto.PbCertificateInfoDTO;
import com.yh.hr.res.pb.service.PbCertificateInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.StringUtil;

/**
 * 人员导入执业注册信息入库service
 * @author wangx
 * @date 2017-07-14
 * @version 1.0
 */
public class ImportCertificateInfoTransferService implements InfoTransferService {

	private ImCheckPersonInfoService imCheckPersonInfoService = (ImCheckPersonInfoService) SpringBeanUtil.getBean("imCheckPersonInfoService");
	private PbCertificateInfoService pbCertificateInfoService = (PbCertificateInfoService) SpringBeanUtil.getBean("pbCertificateInfoService");

	/**
	 * 入库
	 */
	@Override
	public void transfer(Long checkPersonInfoOid, Long personOid)
			throws ServiceException {
		ImCheckPersonInfoDTO ImCheckPersonInfoDTO = imCheckPersonInfoService.get(checkPersonInfoOid);
		PbCertificateInfoDTO pbCertificateInfoDTO = new PbCertificateInfoDTO();
		String certificateNo = ImCheckPersonInfoDTO.getCertificateNo();
		String approveOrganName = ImCheckPersonInfoDTO.getApproveOrganName();
		Date issuedDate = ImCheckPersonInfoDTO.getIssuedDate();
		Date certificateBeginDate = ImCheckPersonInfoDTO.getCertificateBeginDate();
		Date certificateEndDate = ImCheckPersonInfoDTO.getCertificateEndDate();
		if(StringUtil.isNotBlank(certificateNo)||StringUtil.isNotBlank(approveOrganName)
				||issuedDate!=null||certificateBeginDate!=null||certificateEndDate!=null) {
			pbCertificateInfoDTO.setPersonOid(personOid);
			pbCertificateInfoDTO.setCertificateNo(certificateNo);
			pbCertificateInfoDTO.setApproveOrganName(approveOrganName);
			pbCertificateInfoDTO.setIssuedDate(issuedDate);
			pbCertificateInfoDTO.setCertificateBeginDate(certificateBeginDate);
			pbCertificateInfoDTO.setCertificateEndDate(certificateEndDate);
			pbCertificateInfoService.create(pbCertificateInfoDTO);
		}
	}
}
