package com.yh.hr.info.dataimport.person.service.impl;

import java.util.List;
import java.util.Map;

import com.yh.hr.info.dataimport.person.service.ImportPersonService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pb.dto.PbCertificateInfoDTO;
import com.yh.hr.res.pb.dto.PbDeathInfoDTO;
import com.yh.hr.res.pb.dto.PbEducationLevelDegreeDTO;
import com.yh.hr.res.pb.dto.PbEngageConHistInfoDTO;
import com.yh.hr.res.pb.dto.PbEngageContractInfoDTO;
import com.yh.hr.res.pb.dto.PbPersonInDTO;
import com.yh.hr.res.pb.dto.PbPersonInfoDTO;
import com.yh.hr.res.pb.dto.PbPersonOutDTO;
import com.yh.hr.res.pb.dto.PbPoliticInfoDTO;
import com.yh.hr.res.pb.dto.PbProfTechQualifInfoDTO;
import com.yh.hr.res.pb.dto.PbQualificationsInfoDTO;
import com.yh.hr.res.pb.dto.PbRetrieInfoDTO;
import com.yh.hr.res.pb.dto.PbYnGwEmployInfoDTO;
import com.yh.hr.res.pb.service.PbCertificateInfoService;
import com.yh.hr.res.pb.service.PbDeathInfoService;
import com.yh.hr.res.pb.service.PbEducationLevelDegreeService;
import com.yh.hr.res.pb.service.PbEngageConHistInfoService;
import com.yh.hr.res.pb.service.PbEngageContractInfoService;
import com.yh.hr.res.pb.service.PbPersonInService;
import com.yh.hr.res.pb.service.PbPersonInfoService;
import com.yh.hr.res.pb.service.PbPersonOutService;
import com.yh.hr.res.pb.service.PbPoliticInfoService;
import com.yh.hr.res.pb.service.PbProfTechQualifInfoService;
import com.yh.hr.res.pb.service.PbQualificationsInfoService;
import com.yh.hr.res.pb.service.PbRetrieInfoService;
import com.yh.hr.res.pb.service.PbYnGwEmployInfoService;
import com.yh.platform.core.exception.ServiceException;

public class ImportPersonServiceImpl implements ImportPersonService {
	
	private PbPersonInfoService	pbPersonInfoService;
	private PbEducationLevelDegreeService pbEducationLevelDegreeService;
	private PbCertificateInfoService pbCertificateInfoService;
	private PbPoliticInfoService pbPoliticInfoService;
	private PbEngageContractInfoService pbEngageContractInfoService;
	private PbEngageConHistInfoService pbEngageConHistInfoService;
	private PbProfTechQualifInfoService pbProfTechQualifInfoService;
	private PbQualificationsInfoService pbQualificationsInfoService;
	private PbPersonInService pbPersonInService;
	private PbPersonOutService pbPersonOutService;
	private PbRetrieInfoService pbRetrieInfoService;
	private PbDeathInfoService pbDeathInfoService;
	private PbYnGwEmployInfoService pbYnGwEmployInfoService;
	
	public void setPbPersonInfoService(PbPersonInfoService pbPersonInfoService) {
		this.pbPersonInfoService = pbPersonInfoService;
	}

	public void setPbEducationLevelDegreeService(
			PbEducationLevelDegreeService pbEducationLevelDegreeService) {
		this.pbEducationLevelDegreeService = pbEducationLevelDegreeService;
	}

	public void setPbCertificateInfoService(
			PbCertificateInfoService pbCertificateInfoService) {
		this.pbCertificateInfoService = pbCertificateInfoService;
	}

	public void setPbPoliticInfoService(PbPoliticInfoService pbPoliticInfoService) {
		this.pbPoliticInfoService = pbPoliticInfoService;
	}

	public void setPbEngageContractInfoService(
			PbEngageContractInfoService pbEngageContractInfoService) {
		this.pbEngageContractInfoService = pbEngageContractInfoService;
	}

	public void setPbEngageConHistInfoService(
			PbEngageConHistInfoService pbEngageConHistInfoService) {
		this.pbEngageConHistInfoService = pbEngageConHistInfoService;
	}

	public void setPbProfTechQualifInfoService(
			PbProfTechQualifInfoService pbProfTechQualifInfoService) {
		this.pbProfTechQualifInfoService = pbProfTechQualifInfoService;
	}

	public void setPbQualificationsInfoService(
			PbQualificationsInfoService pbQualificationsInfoService) {
		this.pbQualificationsInfoService = pbQualificationsInfoService;
	}

	public void setPbPersonInService(PbPersonInService pbPersonInService) {
		this.pbPersonInService = pbPersonInService;
	}

	public void setPbPersonOutService(PbPersonOutService pbPersonOutService) {
		this.pbPersonOutService = pbPersonOutService;
	}

	public void setPbRetrieInfoService(PbRetrieInfoService pbRetrieInfoService) {
		this.pbRetrieInfoService = pbRetrieInfoService;
	}

	public void setPbDeathInfoService(PbDeathInfoService pbDeathInfoService) {
		this.pbDeathInfoService = pbDeathInfoService;
	}

	public void setPbYnGwEmployInfoService(
			PbYnGwEmployInfoService pbYnGwEmployInfoService) {
		this.pbYnGwEmployInfoService = pbYnGwEmployInfoService;
	}

	public void importPersonGroupByOrg(Map<String, Object> map, String unitOid, String orgOid) throws ServiceException {
		PbPersonInfoDTO pbPersonInfoDTO = (PbPersonInfoDTO) map.get("pbPersonInfo");
		pbPersonInfoDTO.setUnitOid(Long.valueOf(unitOid));
		pbPersonInfoDTO.setHireDeptOid(Long.valueOf(orgOid));
		//验证人员是否存在
		List<PbPersonInfoDTO> persons = pbPersonInfoService.findPersonInfoByNameAndCode(pbPersonInfoDTO.getName(),pbPersonInfoDTO.getPersonCode());
		if(CollectionUtils.isNotEmpty(persons)) {//更新
			for(PbPersonInfoDTO person: persons) {
				Long personOid = person.getPersonOid();
				pbPersonInfoDTO.setPersonOid(personOid);
				map.put("verPbPersonInfo", pbPersonInfoDTO);
				updatePerson(map, unitOid, orgOid);
			}
		}else {//新建
			map.put("verPbPersonInfo", pbPersonInfoDTO);
			createPerson(map, unitOid, orgOid);
		}
	}

	/**
	 * 创建人员
	 * @param map
	 * @throws ServiceException 
	 */
	private void createPerson(Map<String, Object> map, String unitOid, String orgOid) throws ServiceException {
		//基础
		PbPersonInfoDTO pbPersonInfoDTO = (PbPersonInfoDTO) map.get("pbPersonInfo");
		Long personOid = pbPersonInfoService.createPbPersonInfo(pbPersonInfoDTO);
		//人员进入信息
		if(map.get("pbPersonIn")!=null) {
			PbPersonInDTO pbPersonInDTO = (PbPersonInDTO) map.get("pbPersonIn");
			pbPersonInDTO.setPersonOid(personOid);
			pbPersonInService.create(pbPersonInDTO);
		}
		//人员离开信息
		if(map.get("pbPersonOut")!=null) {
			PbPersonOutDTO pbPersonOutDTO = (PbPersonOutDTO) map.get("pbPersonOut");
			pbPersonOutDTO.setPersonOid(personOid);
			pbPersonOutService.create(pbPersonOutDTO);
		}
		//离退休信息
		if(map.get("pbRetrieInfo")!=null) {
			PbRetrieInfoDTO pbRetrieInfoDTO = (PbRetrieInfoDTO) map.get("pbRetrieInfo");
			pbRetrieInfoDTO.setPersonOid(personOid);
			pbRetrieInfoService.create(pbRetrieInfoDTO);
		}
		//自然减员信息
		if(map.get("pbDeathInfo")!=null) {
			PbDeathInfoDTO pbDeathInfoDTO = (PbDeathInfoDTO) map.get("pbDeathInfo");
			pbDeathInfoDTO.setPersonOid(personOid);
			pbDeathInfoService.create(pbDeathInfoDTO);
		}
		//学历学位(全日制)
		if(map.get("ftBbEducationLevelDegree")!=null) {
			PbEducationLevelDegreeDTO ftBbEducationLevelDegreeDTO = (PbEducationLevelDegreeDTO) map.get("ftBbEducationLevelDegree");
			ftBbEducationLevelDegreeDTO.setPersonOid(personOid);
			pbEducationLevelDegreeService.create(ftBbEducationLevelDegreeDTO);
		}
		//学历学位(在职)
		if(map.get("ojBbEducationLevelDegree")!=null) {
			PbEducationLevelDegreeDTO ojBbEducationLevelDegreeDTO = (PbEducationLevelDegreeDTO) map.get("ojBbEducationLevelDegree");
			ojBbEducationLevelDegreeDTO.setPersonOid(personOid);
			pbEducationLevelDegreeService.create(ojBbEducationLevelDegreeDTO);
		}
		//职业注册
		if(map.get("pbCertificateInfo")!=null) {
			PbCertificateInfoDTO pbCertificateInfoDTO = (PbCertificateInfoDTO) map.get("pbCertificateInfo");
			pbCertificateInfoDTO.setPersonOid(personOid);
			pbCertificateInfoService.create(pbCertificateInfoDTO);
		}
		//政治面貌
		if(map.get("pbPoliticInfo")!=null) {
			PbPoliticInfoDTO pbPoliticInfoDTO = (PbPoliticInfoDTO) map.get("pbPoliticInfo");
			pbPoliticInfoDTO.setPersonOid(personOid);
			pbPoliticInfoService.create(pbPoliticInfoDTO);
		}
		//合同信息
		if(map.get("pbEngageContractInfo")!=null) {
			PbEngageContractInfoDTO pbEngageContractInfoDTO = (PbEngageContractInfoDTO) map.get("pbEngageContractInfo");
			pbEngageContractInfoDTO.setUnitOid(Long.valueOf(unitOid));
			pbEngageContractInfoDTO.setPersonOid(personOid);
			pbEngageContractInfoService.create(pbEngageContractInfoDTO);
		}
		//合同历史信息
		if(map.get("pbEngageConHistInfo")!=null) {
			PbEngageConHistInfoDTO pbEngageConHistInfoDTO = (PbEngageConHistInfoDTO) map.get("pbEngageConHistInfo");
			pbEngageConHistInfoDTO.setPersonOid(personOid);
			pbEngageConHistInfoService.create(pbEngageConHistInfoDTO);
		}
		//专业技术资格信息
		if(map.get("pbProfTechQualifInfo")!=null) {
			PbProfTechQualifInfoDTO pbProfTechQualifInfoDTO = (PbProfTechQualifInfoDTO) map.get("pbProfTechQualifInfo");
			pbProfTechQualifInfoDTO.setPersonOid(personOid);
			pbProfTechQualifInfoService.create(pbProfTechQualifInfoDTO);
		}
		//职业资格信息
		if(map.get("pbQualificationsInfo")!=null) {
			PbQualificationsInfoDTO pbQualificationsInfoDTO = (PbQualificationsInfoDTO) map.get("pbQualificationsInfo");
			pbQualificationsInfoDTO.setPersonOid(personOid);
			pbQualificationsInfoService.create(pbQualificationsInfoDTO);
		}
		//院内岗位聘任信息
		if(map.get("pbYnGwEmployInfo")!=null) {
			PbYnGwEmployInfoDTO pbYnGwEmployInfoDTO = (PbYnGwEmployInfoDTO) map.get("pbYnGwEmployInfo");
			pbYnGwEmployInfoDTO.setPersonOid(personOid);
			pbYnGwEmployInfoService.create(pbYnGwEmployInfoDTO);
		}
	}

	/**
	 * 修改人员
	 * @param map
	 * @throws ServiceException 
	 */
	private void updatePerson(Map<String, Object> map, String unitOid, String orgOid) throws ServiceException {
		//基础
		PbPersonInfoDTO pbPersonInfoDTO = (PbPersonInfoDTO) map.get("pbPersonInfo");
		Long personOid = pbPersonInfoDTO.getPersonOid();
		pbPersonInfoService.modifyPbPersonInfo(pbPersonInfoDTO);
		//人员进入信息
		if(map.get("pbPersonIn")!=null) {
			pbPersonInService.deleteByPersonOid(personOid);
			PbPersonInDTO pbPersonInDTO = (PbPersonInDTO) map.get("pbPersonIn");
			pbPersonInDTO.setPersonOid(personOid);
			pbPersonInService.create(pbPersonInDTO);
		}
		//人员离开信息
		if(map.get("pbPersonOut")!=null) {
			pbPersonOutService.deleteByPersonOid(personOid);
			PbPersonOutDTO pbPersonOutDTO = (PbPersonOutDTO) map.get("pbPersonOut");
			pbPersonOutDTO.setPersonOid(personOid);
			pbPersonOutService.create(pbPersonOutDTO);
		}
		//离退休信息
		if(map.get("pbRetrieInfo")!=null) {
			pbRetrieInfoService.deleteByPersonOid(personOid);
			PbRetrieInfoDTO pbRetrieInfoDTO = (PbRetrieInfoDTO) map.get("pbRetrieInfo");
			pbRetrieInfoDTO.setPersonOid(personOid);
			pbRetrieInfoService.create(pbRetrieInfoDTO);
		}
		//自然减员信息
		if(map.get("pbDeathInfo")!=null) {
			pbDeathInfoService.deleteByPersonOid(personOid);
			PbDeathInfoDTO pbDeathInfoDTO = (PbDeathInfoDTO) map.get("pbDeathInfo");
			pbDeathInfoDTO.setPersonOid(personOid);
			pbDeathInfoService.create(pbDeathInfoDTO);
		}
		//学历学位(全日制)
		if(map.get("ftBbEducationLevelDegree")!=null) {
			pbEducationLevelDegreeService.deleteByPersonOid(personOid);
			PbEducationLevelDegreeDTO ftBbEducationLevelDegreeDTO = (PbEducationLevelDegreeDTO) map.get("ftBbEducationLevelDegree");
			ftBbEducationLevelDegreeDTO.setPersonOid(personOid);
			pbEducationLevelDegreeService.create(ftBbEducationLevelDegreeDTO);
		}
		//学历学位(在职)
		if(map.get("ojBbEducationLevelDegree")!=null) {
			PbEducationLevelDegreeDTO ojBbEducationLevelDegreeDTO = (PbEducationLevelDegreeDTO) map.get("ojBbEducationLevelDegree");
			ojBbEducationLevelDegreeDTO.setPersonOid(personOid);
			pbEducationLevelDegreeService.create(ojBbEducationLevelDegreeDTO);
		}
		//职业注册
		if(map.get("pbCertificateInfo")!=null) {
			pbCertificateInfoService.deleteByPersonOid(personOid);
			PbCertificateInfoDTO pbCertificateInfoDTO = (PbCertificateInfoDTO) map.get("pbCertificateInfo");
			pbCertificateInfoDTO.setPersonOid(personOid);
			pbCertificateInfoService.create(pbCertificateInfoDTO);
		}
		//政治面貌
		if(map.get("pbPoliticInfo")!=null) {
			pbPoliticInfoService.deleteByPersonOid(personOid);
			PbPoliticInfoDTO pbPoliticInfoDTO = (PbPoliticInfoDTO) map.get("pbPoliticInfo");
			pbPoliticInfoDTO.setPersonOid(personOid);
			pbPoliticInfoService.create(pbPoliticInfoDTO);
		}
		//合同信息
		if(map.get("pbEngageContractInfo")!=null) {
			pbEngageContractInfoService.deleteByPersonOid(personOid);
			PbEngageContractInfoDTO pbEngageContractInfoDTO = (PbEngageContractInfoDTO) map.get("pbEngageContractInfo");
			pbEngageContractInfoDTO.setUnitOid(Long.valueOf(unitOid));
			pbEngageContractInfoDTO.setPersonOid(personOid);
			pbEngageContractInfoService.create(pbEngageContractInfoDTO);
		}
		//合同历史信息
		if(map.get("pbEngageConHistInfo")!=null) {
			pbEngageConHistInfoService.deleteByPersonOid(personOid);
			PbEngageConHistInfoDTO pbEngageConHistInfoDTO = (PbEngageConHistInfoDTO) map.get("pbEngageConHistInfo");
			pbEngageConHistInfoDTO.setPersonOid(personOid);
			pbEngageConHistInfoService.create(pbEngageConHistInfoDTO);
		}
		//专业技术资格信息
		if(map.get("pbProfTechQualifInfo")!=null) {
			pbProfTechQualifInfoService.deleteByPersonOid(personOid);
			PbProfTechQualifInfoDTO pbProfTechQualifInfoDTO = (PbProfTechQualifInfoDTO) map.get("pbProfTechQualifInfo");
			pbProfTechQualifInfoDTO.setPersonOid(personOid);
			pbProfTechQualifInfoService.create(pbProfTechQualifInfoDTO);
		}
		//职业资格信息
		if(map.get("pbQualificationsInfo")!=null) {
			pbQualificationsInfoService.deleteByPersonOid(personOid);
			PbQualificationsInfoDTO pbQualificationsInfoDTO = (PbQualificationsInfoDTO) map.get("pbQualificationsInfo");
			pbQualificationsInfoDTO.setPersonOid(personOid);
			pbQualificationsInfoService.create(pbQualificationsInfoDTO);
		}
		//院内岗位聘任信息
		if(map.get("pbYnGwEmployInfo")!=null) {
			pbYnGwEmployInfoService.deleteByPersonOid(personOid);
			PbYnGwEmployInfoDTO pbYnGwEmployInfoDTO = (PbYnGwEmployInfoDTO) map.get("pbYnGwEmployInfo");
			pbYnGwEmployInfoDTO.setPersonOid(personOid);
			pbYnGwEmployInfoService.create(pbYnGwEmployInfoDTO);
		}
	}

}
