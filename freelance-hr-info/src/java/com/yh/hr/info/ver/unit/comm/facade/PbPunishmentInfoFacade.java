﻿/**
 * @desctiption   This file is generated by  code generation tool version 0.2 ^_^
 * @Created   2017-02-13
**/
package com.yh.hr.info.ver.unit.comm.facade;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.pb.dto.PbPunishmentInfoDTO;
import com.yh.hr.res.pb.service.PbPunishmentInfoService;
import com.yh.platform.core.exception.ServiceException;

public class PbPunishmentInfoFacade {

	private PbPunishmentInfoService pbPunishmentInfoService;

	public void setPbPunishmentInfoService(PbPunishmentInfoService pbPunishmentInfoService) {
		this.pbPunishmentInfoService = pbPunishmentInfoService;
	}

	/**
	 * 新增PbPunishmentInfo详细信息
	 * 
	 * @param 
	 * @return
	 * @throws ServiceException
	 */
	public void create(PbPunishmentInfoDTO pbPunishmentInfoDto) throws ServiceException {
		pbPunishmentInfoService.create(pbPunishmentInfoDto);
	}
	
	    /**
	 * 删除多PbPunishmentInfo信息
	 * 
	 * @param 
	 * @return
	 * @throws ServiceException
	 */
	public void delete(java.lang.Long pbPunishmentInfoId) throws ServiceException {
		pbPunishmentInfoService.delete(pbPunishmentInfoId);
	}



	/**
	 * 查找PbPunishmentInfo详细信息
	 * 
	 * @param 
	 * @return
	 * @throws ServiceException
	 */
	public PbPunishmentInfoDTO get(java.lang.Long pbPunishmentInfoId) throws ServiceException {
		return pbPunishmentInfoService.get(pbPunishmentInfoId);
	}
	
	/**
	 * 列出多条PbPunishmentInfo信息
	 * 
	 * @param 
	 * @return
	 * @throws ServiceException
	 */
	public List<PbPunishmentInfoDTO> find(TableTagBean ttb) throws ServiceException {
		return pbPunishmentInfoService.find(ttb);
	}
    
    /**
	 * 更新&修改PbPunishmentInfo详细信息
	 * 
	 * @param 
	 * @return
	 * @throws ServiceException
	 */
	public void update(PbPunishmentInfoDTO pbPunishmentInfoDto) throws ServiceException {
		pbPunishmentInfoService.update(pbPunishmentInfoDto);
	}
	/**
	 * 查找PbPunishmentInfo详细信息
	 * 
	 * @param 
	 * @return
	 * @throws ServiceException
	 */
	public List<PbPunishmentInfoDTO> getPunishmentInfoByPersonOid(java.lang.Long personOid) throws ServiceException {
		return pbPunishmentInfoService.getPunishmentInfoByPersonOid(personOid);
	}

}