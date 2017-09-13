package com.yh.hr.info.dataimport.person.service.transfer;

import jade.workflow.utils.SpringBeanUtil;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.im.dto.ImCheckPersonInfoDTO;
import com.yh.hr.res.im.service.ImCheckPersonInfoService;
import com.yh.hr.res.pb.dto.PbEducationLevelDegreeDTO;
import com.yh.hr.res.pb.service.PbEducationLevelDegreeService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.StringUtil;

/**
 * 人员导入学历学位信息入库service
 * @author wangx
 * @date 2017-07-14
 * @version 1.0
 */
public class ImportEducationLevelDegreeTransferService implements InfoTransferService {

	private ImCheckPersonInfoService imCheckPersonInfoService = (ImCheckPersonInfoService) SpringBeanUtil.getBean("imCheckPersonInfoService");
	private PbEducationLevelDegreeService pbEducationLevelDegreeService = (PbEducationLevelDegreeService) SpringBeanUtil.getBean("pbEducationLevelDegreeService");

	/**
	 * 入库
	 */
	@Override
	public void transfer(Long checkPersonInfoOid, Long personOid)
			throws ServiceException {
		ImCheckPersonInfoDTO ImCheckPersonInfoDTO = imCheckPersonInfoService.get(checkPersonInfoOid);
		PbEducationLevelDegreeDTO pbEducationLevelDegreeDTO = new PbEducationLevelDegreeDTO();
		String schoolName = ImCheckPersonInfoDTO.getSchoolName();
		String majorCode = ImCheckPersonInfoDTO.getMajorCode();
		String studyTypeCode = ImCheckPersonInfoDTO.getStudyTypeCode();
		String eduType = ImCheckPersonInfoDTO.getEduType();
		String educationCode = ImCheckPersonInfoDTO.getEducationCode();
		String degreeCode = ImCheckPersonInfoDTO.getDegreeCode();
		if(StringUtil.isNotBlank(schoolName)||StringUtil.isNotBlank(majorCode)||StringUtil.isNotBlank(studyTypeCode)
				||StringUtil.isNotBlank(eduType)||StringUtil.isNotBlank(educationCode)||StringUtil.isNotBlank(degreeCode)) {
			pbEducationLevelDegreeDTO.setPersonOid(personOid);
			pbEducationLevelDegreeDTO.setSchoolName(schoolName);
			pbEducationLevelDegreeDTO.setMajorCode(majorCode);
			pbEducationLevelDegreeDTO.setStudyTypeCode(studyTypeCode);
			pbEducationLevelDegreeDTO.setEduType(eduType);
			pbEducationLevelDegreeDTO.setEducationCode(educationCode);
			pbEducationLevelDegreeDTO.setDegreeCode(degreeCode);
			pbEducationLevelDegreeService.create(pbEducationLevelDegreeDTO);
		}
	}
}
