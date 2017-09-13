/**
 * 
 */
package com.yh.hr.info.ver.unit.workbench.facade;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.info.ver.unit.workbench.dto.VerPersonDTO;
import com.yh.hr.info.ver.unit.workbench.service.VerPbPersonWorkbenchService;
import com.yh.hr.res.pb.dto.PbPersonInfoDTO;
import com.yh.hr.res.pb.service.PbPersonInfoService;
import com.yh.hr.res.sao.unit.SaoUserUnitAuthorizationService;
import com.yh.hr.res.unit.dto.UtOrgDTO;
import com.yh.hr.res.unit.dto.UtUnitDTO;
import com.yh.hr.res.unit.service.UtOrgService;
import com.yh.hr.res.unit.service.UtUnitService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.StringMap;

public class VerPbPersonWorkbenchFacade {
	
	private UtUnitService utUnitService;
	private UtOrgService utOrgService;
	private SaoUserUnitAuthorizationService saoUserUnitAuthorizationService;
	private VerPbPersonWorkbenchService verPbPersonWorkbenchService;
	private PbPersonInfoService pbPersonInfoService;
	
	public void setUtUnitService(UtUnitService utUnitService) {
		this.utUnitService = utUnitService;
	}

	public void setSaoUserUnitAuthorizationService(SaoUserUnitAuthorizationService saoUserUnitAuthorizationService) {
		this.saoUserUnitAuthorizationService = saoUserUnitAuthorizationService;
	}

	public void setVerPbPersonWorkbenchService(VerPbPersonWorkbenchService verPbPersonWorkbenchService) {
		this.verPbPersonWorkbenchService = verPbPersonWorkbenchService;
	}

	public void setPbPersonInfoService(PbPersonInfoService pbPersonInfoService) {
		this.pbPersonInfoService = pbPersonInfoService;
	}

	public void setUtOrgService(UtOrgService utOrgService) {
		this.utOrgService = utOrgService;
	}

	/**
	 * 查找用户授权的单位列表信息
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	public List<UtUnitDTO> findUserAuthedUnitList(String userId,StringMap params) throws ServiceException {
		
		return utUnitService.findUnitListByAuth(saoUserUnitAuthorizationService.findAuthorityList(userId), params);
	}

	public List<VerPersonDTO> listVerPerson(TableTagBean ttb) throws ServiceException {
//		List<String> authUnits = saoUserUnitAuthorizationService.findAuthorityList(UserContext.getLoginAgentUserID());
//		if (CollectionUtils.isEmpty(authUnits)) return null;
		
//		ttb.getCondition().put("authUnits", StringUtil.arrayToSql(authUnits));
		return verPbPersonWorkbenchService.listVerPerson(ttb);
	}
	/**
     * 查询内设机构列表
     * @param unitOid
     * @return
     * @throws ServiceException 
     */
	public List<UtOrgDTO> findOrgList(Long unitOid) throws ServiceException {
		return utOrgService.findUnitOrg(unitOid);
	}
	/**
	 * 校核人员信息
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public String updateVerPersonResult(Long personOid) throws ServiceException {
		return verPbPersonWorkbenchService.updateVerPersonResult(personOid);
	}

	/**
	 * 查询所有单位信息
	 * @return
	 * @throws ServiceException
	 */
	public List<UtUnitDTO> findUnitInfo() throws ServiceException {
		return utUnitService.findUnitInfo();
	}
	public List<UtOrgDTO> listLeafOrg(String orgOid,String organizationOid) throws ServiceException {
		return verPbPersonWorkbenchService.listLeafOrg(orgOid,organizationOid);
	}
	public List<VerPersonDTO> listVerPersonByOrgOid(Long orgOid) throws ServiceException {
		return verPbPersonWorkbenchService.listVerPersonByOrgOid(orgOid);
	}

	public PbPersonInfoDTO getPbPersonById(Long personOid) throws ServiceException {
		return pbPersonInfoService.getPbPersonInfoDTOById(personOid);
	}
	
	
}
