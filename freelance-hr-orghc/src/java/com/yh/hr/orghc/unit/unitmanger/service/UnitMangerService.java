package com.yh.hr.orghc.unit.unitmanger.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.orghc.ub.bo.UbUnit;
import com.yh.hr.orghc.ub.dto.UbHcDTO;
import com.yh.hr.orghc.ub.dto.UbLeaderDTO;
import com.yh.hr.orghc.ub.dto.UbOrgDTO;
import com.yh.hr.orghc.ub.dto.UbUnitDTO;
import com.yh.hr.orghc.unit.unitmanger.dto.OrgDTO;
import com.yh.platform.core.exception.ServiceException;

public interface UnitMangerService {

	/**
	 * 查询系统单位数量
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public int listUnitCounts() throws ServiceException;

	public UbUnit getUnitInformationByUnitOid(String unitOid) throws ServiceException;
	public List<UbOrgDTO> listOrgByUnitOidAndStatus(String unitOid) throws ServiceException;

	public List<UbHcDTO> listhcByUnitOid(String unitOid) throws ServiceException;

	public List<UbLeaderDTO> listleaderByUnitOid(String unitOid) throws ServiceException;

	/**
	 * 查询科室列表
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public List<JSONObject> listOrg(TableTagBean ttb) throws ServiceException  ;
	
	/**
	 * 创建科室信息
	 * @param dto
	 * @return 
	 * @throws ServiceException
	 */
	public Long create(OrgDTO dto) throws ServiceException;

	/**
	 * 修改科室信息
	 * @param dto
	 * @throws ServiceException
	 */
	public void update(OrgDTO dto)  throws ServiceException;

	/**
	 * 获取科室信息
	 * @param orgOid
	 * @return
	 * @throws ServiceException
	 */
	public OrgDTO getOrg(Long orgOid) throws ServiceException;

	public void updateUnitInfo(UbUnitDTO ubUnitDTO) throws ServiceException;

	public void addVerUbHcInfoDTO(UbHcDTO ubHcDTO) throws ServiceException;

	public void deleteSaoUbHcDTO(Long hcId) throws ServiceException;

	public UbHcDTO getVerUbHcInfoDTO(Long hcOid) throws ServiceException;

	public void updateUbHcDTO(UbHcDTO oldVerUbHcInfoDTO) throws ServiceException;

	public void addVerUbLeaderInfo(UbLeaderDTO ubLeaderDTO) throws ServiceException;

	public void deleteVerUbLeaderInfo(Long leaderOid) throws ServiceException;

	public void updateVerUbLeaderInfo(UbLeaderDTO oldVerUbLeaderInfoDTO) throws ServiceException;

	public List<UbLeaderDTO> listVerUbLeaderInfo(Long unitOid) throws ServiceException;

	public UbLeaderDTO getVerUbLeaderInfo(Long leaderOid) throws ServiceException;

	public void addUnitInfo(UbUnitDTO ubUnitDTO) throws ServiceException;

	/**
	 * 根据上级科室查询科室类型
	 * @param unitOid,parentOrgName
	 * @throws ServiceException
	 */
	public List<OrgDTO> findByOrgType(String unitOid,String parentOrgName) throws ServiceException;

	/**
	 * 获取科室子节点
	 * @param unitOid
	 * @param orgOid
	 * @return
	 * @throws ServiceException
	 */
	public List<UbOrgDTO> getOrg(Long unitOid, Long orgOid) throws ServiceException;

	/**
	 * 撤销科室
	 * @param orgOid
	 * @throws ServiceException
	 */
	public void updateOrgStatus(Long orgOid) throws ServiceException;

}
