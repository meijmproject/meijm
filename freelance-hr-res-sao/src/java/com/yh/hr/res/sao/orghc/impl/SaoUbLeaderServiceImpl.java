package com.yh.hr.res.sao.orghc.impl;

import java.util.List;

import com.yh.hr.res.ld.bo.LdCash;
import com.yh.hr.res.ld.util.LdFlowConstants;
import com.yh.hr.res.sao.orghc.dto.SaoUbLeaderDTO;
import com.yh.hr.res.sao.orghc.service.SaoUbLeaderService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.orghc.ub.dto.UbLeaderDTO;
import com.yh.hr.orghc.ub.service.UbLeaderService;
import com.yh.hr.res.ld.queryhelper.LdCashQueryHelper;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 领导职数信息查询、维护接口
 * @author	lenghh
 * @version	1.0,	16/09/05
 */

public class SaoUbLeaderServiceImpl implements SaoUbLeaderService {
	
	private UbLeaderService ubLeaderService;
	
	public void setUbLeaderService(UbLeaderService ubLeaderService) {
		this.ubLeaderService = ubLeaderService;
	}
	
	/* (non-Javadoc)
	 * @see SaoUbLeaderService#getSaoUbLeaderDTO(java.lang.Long)
	 */
	public SaoUbLeaderDTO getSaoUbLeaderDTO(Long leaderOid) throws ServiceException {
		UbLeaderDTO ubLeaderDTO = ubLeaderService.getUbOrgDTOById(leaderOid);
		if(null != ubLeaderDTO){
			SaoUbLeaderDTO saoUbLeaderDTO = new SaoUbLeaderDTO();
			BeanHelper.copyProperties(ubLeaderDTO, saoUbLeaderDTO);
			return saoUbLeaderDTO;
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see SaoUbLeaderService#listSaoUbLeaderDTOByUnitOid(java.lang.Long)
	 */
	public List<SaoUbLeaderDTO> listSaoUbLeaderDTOByUnitOid(Long unitOid) throws ServiceException {
		List<SaoUbLeaderDTO> saoUbLeaderDTOs = null;
		List<UbLeaderDTO> ubLeaderDTOs = ubLeaderService.listByUnitOid(unitOid);
		if(CollectionUtils.isNotEmpty(ubLeaderDTOs)){
			saoUbLeaderDTOs = BeanHelper.copyProperties(ubLeaderDTOs,SaoUbLeaderDTO.class);
		}
		return saoUbLeaderDTOs;
	}

	public void addSaoUbLeaderDTO(SaoUbLeaderDTO saoUbLeaderDTO) throws ServiceException {
		UbLeaderDTO ubLeaderDTO = new UbLeaderDTO();
		BeanHelper.copyProperties(saoUbLeaderDTO,ubLeaderDTO);
		LdCash ldCash = LdCashQueryHelper.findLdCash(LdFlowConstants.ACCOUNT_TYPE_1, ubLeaderDTO.getUnitOid().toString(), ubLeaderDTO.getDutyAttribute(), ubLeaderDTO.getDutyLevel());
		Long approvedCount = 0L;
		if(ldCash!=null) {
			approvedCount = ldCash.getApprovedCount()==null?0L:ldCash.getApprovedCount();
		}
		Long chgCount = ubLeaderDTO.getChgCount()-approvedCount;
		ubLeaderService.createLeaderInfo(ubLeaderDTO, chgCount);
	}
	
	public void updateSaoUbLeaderDTO(SaoUbLeaderDTO saoUbLeaderDTO) throws ServiceException {
		UbLeaderDTO ubLeaderDTO = new UbLeaderDTO();
		BeanHelper.copyProperties(saoUbLeaderDTO,ubLeaderDTO);
		LdCash ldCash = LdCashQueryHelper.findLdCash(LdFlowConstants.ACCOUNT_TYPE_1, ubLeaderDTO.getUnitOid().toString(), ubLeaderDTO.getDutyAttribute(), ubLeaderDTO.getDutyLevel());
		Long approvedCount = 0L;
		if(ldCash!=null) {
			approvedCount = ldCash.getApprovedCount()==null?0L:ldCash.getApprovedCount();
		}
		Long chgCount = ubLeaderDTO.getChgCount()-approvedCount;
		ubLeaderService.updateLeaderInfo(ubLeaderDTO, chgCount);
		
	}

	public void deleteSaoUbLeaderDTO(Long leaderOid) throws ServiceException {
		ubLeaderService.deleteLeaderInfo(leaderOid);
	}

	public int countAdminUnitLeader(Long adminUnitOid,
			String positionLevelCode) throws ServiceException {
		return ubLeaderService.countAdminUnitLeader(adminUnitOid,positionLevelCode);
	}
}
