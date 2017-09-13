package com.yh.hr.res.sao.orghc.impl;

import java.util.List;

import com.yh.hr.res.hc.bo.HcCash;
import com.yh.hr.res.hc.queryhelper.HcCashQueryHelper;
import com.yh.hr.res.hc.util.HcFlowConstants;
import com.yh.hr.res.sao.orghc.dto.SaoUbHcDTO;
import com.yh.hr.res.sao.orghc.service.SaoUbHcService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.orghc.ub.dto.UbHcDTO;
import com.yh.hr.orghc.ub.service.UbHcService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 编制信息查询、维护接口
 * @author	lenghh
 * @version	1.0,	16/09/05
 */

public class SaoUbHcServiceImpl implements SaoUbHcService {
	
	private UbHcService		ubHcService;
	
	public void setUbHcService(UbHcService ubHcService) {
		this.ubHcService = ubHcService;
	}
	
	/* (non-Javadoc)
	 * @see SaoUbHcService#getSaoUbHcDTO(java.lang.Long)
	 */
	public SaoUbHcDTO getSaoUbHcDTO(Long hcOid) throws ServiceException {
		UbHcDTO ubHcDTO = ubHcService.getUbHcDTOById(hcOid);
		if(null != ubHcDTO){
			SaoUbHcDTO saoUbHcDTO = new SaoUbHcDTO();
			BeanHelper.copyProperties(ubHcDTO, saoUbHcDTO);
			return saoUbHcDTO;
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see SaoUbHcService#listSaoUbHcDTOByUnitOid(java.lang.Long)
	 */
	public List<SaoUbHcDTO> listSaoUbHcDTOByUnitOid(Long unitOid) throws ServiceException {
		List<SaoUbHcDTO> saoUbHcDTOs = null;
		List<UbHcDTO> ubHcDTODtos = ubHcService.listByUnitOid(unitOid);
		if(CollectionUtils.isNotEmpty(ubHcDTODtos)){
			saoUbHcDTOs = BeanHelper.copyProperties(ubHcDTODtos,SaoUbHcDTO.class);
		}
		return saoUbHcDTOs;
	}
	
	public void updateSaoUbHcDTO(SaoUbHcDTO saoUbHcDTO) throws ServiceException {
		UbHcDTO ubHcDTO = new UbHcDTO();
		BeanHelper.copyProperties(saoUbHcDTO,ubHcDTO);
		HcCash hcCash = HcCashQueryHelper.findHcCash(HcFlowConstants.ACCOUNT_TYPE_1, ubHcDTO.getUnitOid().toString(), ubHcDTO.getHcCode(), ubHcDTO.getBudgetFromCode());
		Long approvedCount = 0L;
		if(hcCash!=null) {
			approvedCount = hcCash.getApprovedCount()==null?0L:hcCash.getApprovedCount();
		}
		Long chgCount = ubHcDTO.getChgCount()-approvedCount;
		ubHcService.updateHcInfo(ubHcDTO, chgCount);
	}

	public void addSaoUbHcDTO(SaoUbHcDTO saoUbHcDTO) throws ServiceException {
		UbHcDTO ubHcDTO = new UbHcDTO();
		BeanHelper.copyProperties(saoUbHcDTO,ubHcDTO);
		HcCash hcCash = HcCashQueryHelper.findHcCash(HcFlowConstants.ACCOUNT_TYPE_1, ubHcDTO.getUnitOid().toString(), ubHcDTO.getHcCode(), ubHcDTO.getBudgetFromCode());
		Long approvedCount = 0L;
		if(hcCash!=null) {
			approvedCount = hcCash.getApprovedCount()==null?0L:hcCash.getApprovedCount();
		}
		Long chgCount = ubHcDTO.getChgCount()-approvedCount;
		ubHcService.createHcInfo(ubHcDTO, chgCount);
	}

	public void deleteSaoUbHcDTO(Long hcOid) throws ServiceException {
		ubHcService.deleteHcInfo(hcOid);
	}

	/**
	 * 根据编制OID统计编制核定数
	 * @param unitOid
	 * @param hcCode
	 * @return num
	 * @throws ServiceException
	 */
	public int countHcByHcCode(Long unitOid,String hcCode) throws ServiceException {
		return ubHcService.countHcByHcCode(unitOid,hcCode);
	}
}
