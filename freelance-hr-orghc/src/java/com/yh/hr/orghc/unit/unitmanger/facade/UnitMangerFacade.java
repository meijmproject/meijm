package com.yh.hr.orghc.unit.unitmanger.facade;

import java.util.List;

import com.yh.hr.orghc.ub.dto.UbHcDTO;
import com.yh.hr.orghc.ub.dto.UbOrgDTO;
import com.yh.hr.orghc.ub.dto.UbUnitDTO;
import com.yh.hr.orghc.ub.service.UbOrgService;
import com.yh.hr.orghc.unit.unitmanger.service.UnitMangerService;
import net.sf.json.JSONObject;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.orghc.ub.bo.UbUnit;
import com.yh.hr.orghc.ub.dto.UbLeaderDTO;
import com.yh.hr.orghc.unit.unitmanger.dto.OrgDTO;
import com.yh.platform.core.exception.ServiceException;

public class UnitMangerFacade {
	public UnitMangerService unitMangerService;
	public UbOrgService ubOrgService;
	public void setUbOrgService(UbOrgService ubOrgService) {
		this.ubOrgService = ubOrgService;
	}
	public void setUnitMangerService(UnitMangerService unitMangerService) {
		this.unitMangerService = unitMangerService;
	}
	public int listUnitCounts() throws ServiceException {
		return unitMangerService.listUnitCounts();
	}
	public UbUnit getUnitInformationByUnitOid(String unitOid) throws ServiceException {
		return unitMangerService.getUnitInformationByUnitOid(unitOid);
	}
	public List<UbOrgDTO> listOrgByUnitOidAndStatus(String unitOid) throws ServiceException {
		return unitMangerService.listOrgByUnitOidAndStatus(unitOid);
	}
	public List<UbHcDTO> listhcByUnitOid(String unitOid) throws ServiceException{
		return unitMangerService.listhcByUnitOid(unitOid);
	}
	public List<UbLeaderDTO> listleaderByUnitOid(String unitOid) throws ServiceException{
		return unitMangerService.listleaderByUnitOid(unitOid);
	};
	/**
	 * 查询科室列表
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public List<JSONObject> listOrg(TableTagBean ttb) throws ServiceException{
		return unitMangerService.listOrg(ttb);
	}
	/**
	 * 查询科室信息
	 * @param parentOrgOid
	 * @return
	 * @throws ServiceException
	 */
	public UbOrgDTO getParentOrg(Long parentOrgOid)  throws ServiceException{
		return ubOrgService.getUbOrgDTOById(parentOrgOid);
	}
	/**
	 * 创建科室信息
	 * @param dto
	 * @return 
	 * @throws ServiceException
	 */
	public Long create(OrgDTO dto) throws ServiceException{
		return unitMangerService.create(dto);
	}
	/**
	 * 获取科室信息
	 * @param orgOid
	 * @return
	 * @throws ServiceException
	 */
	public OrgDTO getOrg(Long orgOid) throws ServiceException{
		return unitMangerService.getOrg(orgOid);
	}
	/**
	 * 修改科室信息
	 * @param dto
	 * @throws ServiceException
	 */
	public void update(OrgDTO dto) throws ServiceException{
		unitMangerService.update(dto);
	}
	/**
	 * 删除科室信息
	 * @param orgOid
	 * @throws ServiceException
	 */
	public void delete(Long orgOid) throws ServiceException{
		ubOrgService.deleteOrgInfo(orgOid);
	}
	/**
	 * 修改单位信息
	 * @param orgOid
	 * @throws ServiceException
	 */
	public void updateUnitInfo(UbUnitDTO ubUnitDTO) throws ServiceException {
		unitMangerService.updateUnitInfo(ubUnitDTO);
	}
	public void addVerUbHcInfoDTO(UbHcDTO ubHcDTO) throws ServiceException{
		unitMangerService.addVerUbHcInfoDTO(ubHcDTO);
		
	}
	public void deleteVerUbHcInfoDTO(Long hcId) throws ServiceException{
		unitMangerService.deleteSaoUbHcDTO(hcId);		
	}
	public UbHcDTO getVerUbHcInfoDTO(Long hcOid) throws ServiceException{
		return unitMangerService.getVerUbHcInfoDTO(hcOid);
	}
	public void updateUbHcDTO(UbHcDTO oldVerUbHcInfoDTO) throws ServiceException{
		unitMangerService.updateUbHcDTO(oldVerUbHcInfoDTO);		
	}
	public void addVerUbLeaderInfo(UbLeaderDTO ubLeaderDTO) throws ServiceException{
		unitMangerService.addVerUbLeaderInfo(ubLeaderDTO);
	}
	public void deleteVerUbLeaderInfo(Long leaderOid) throws ServiceException{
		unitMangerService.deleteVerUbLeaderInfo(leaderOid);
	}
	public UbLeaderDTO getVerUbLeaderInfo(Long leaderOid) throws ServiceException{
		return unitMangerService.getVerUbLeaderInfo(leaderOid);
	}
	public void updateVerUbLeaderInfo(UbLeaderDTO oldVerUbLeaderInfoDTO) throws ServiceException{
		unitMangerService.updateVerUbLeaderInfo(oldVerUbLeaderInfoDTO);
	}
	public List<UbLeaderDTO> listVerUbLeaderInfo(Long unitOid) throws ServiceException{
		return unitMangerService.listVerUbLeaderInfo(unitOid);
	}
	public void addUnitInfo(UbUnitDTO ubUnitDTO) throws ServiceException{
		unitMangerService.addUnitInfo(ubUnitDTO);
	}
	/**
	 * 根据科室类型查询上级科室
	 * @param parentOrgName
	 * @throws ServiceException
	 */
	public List<OrgDTO> findByOrgType(String unitOid,String orgType) throws ServiceException{
		return unitMangerService.findByOrgType(unitOid,orgType);
	}
	/**
	 * 获取科室子节点
	 * @param unitOid
	 * @param orgOidStr
	 * @return
	 * @throws ServiceException
	 */
	public List<UbOrgDTO> getOrg(Long unitOid, Long orgOid) throws ServiceException{
		return unitMangerService.getOrg(unitOid, orgOid);
	}
	/**
	 * 撤销科室
	 * @param longValue
	 * @throws ServiceException
	 */
	public void updateOrgStatus(Long orgOid) throws ServiceException
	{
		unitMangerService.updateOrgStatus(orgOid);
	}
}
