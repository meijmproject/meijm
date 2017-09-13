package com.yh.hr.orghc.unit.common.facade;

import java.util.ArrayList;
import java.util.List;

import com.yh.hr.orghc.unit.common.dto.BizUtOrgInfoDTO;
import com.yh.hr.orghc.ut.dto.BizUtUnitDTO;
import com.yh.hr.orghc.ut.service.BizOrgInfoService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.orghc.ut.dto.BizUtOrgDTO;
import com.yh.hr.orghc.ut.service.BizUtUnitService;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.SpringBeanUtil;

public class BizUtOrgInfoFacade {
	private BizOrgInfoService bizOrgInfoService = (BizOrgInfoService)SpringBeanUtil.getBean("bizOrgInfoService");
	private BizUtUnitService bizUtUnitService = (BizUtUnitService)SpringBeanUtil.getBean("bizUtUnitService");
	
	public String getUnitName(java.lang.Long unitOid) throws ServiceException{
		BizUtUnitDTO dto = bizUtUnitService.findBizUtUnitById(unitOid);
		if(null != dto.getUnitName()){
			return dto.getUnitName();
		}
		return null;
	}
	
	public BizUtOrgInfoDTO getBizUtOrgInfoDTOById(java.lang.Long OrgId) throws ServiceException{
		BizUtOrgInfoDTO bizUtOrgInfoDTO = new BizUtOrgInfoDTO();
		BizUtOrgDTO bizUtOrgDTO = bizOrgInfoService.get(OrgId);
		BeanHelper.copyProperties(bizUtOrgDTO, bizUtOrgInfoDTO);
		return bizUtOrgInfoDTO;
	}
	
	public void update(BizUtOrgInfoDTO bizUtOrgInfoDTO) throws ServiceException{
		BizUtOrgDTO bizUtOrgDTO = new BizUtOrgDTO();
		BeanHelper.copyProperties(bizUtOrgInfoDTO, bizUtOrgDTO);
		if(DicConstants.YHRS0101_2.equals(bizUtOrgDTO.getOrgStatus())){			
			List<BizUtOrgDTO> bizUtOrgDTOs = bizOrgInfoService.list(bizUtOrgDTO.getUtUnitOid(),DicConstants.YHRS0101_2);
			if(CollectionUtils.isNotEmpty(bizUtOrgDTOs)){
				for (BizUtOrgDTO dto : bizUtOrgDTOs) {
					if(dto.getOrgName().trim().equals(bizUtOrgDTO.getOrgName().trim())
							&& (dto.getUtOrgOid().longValue() != bizUtOrgDTO.getUtOrgOid().longValue()) ){
						throw new ServiceException(null,"同单位下内设机构名称不能重复！");
					}
				}
			}
		}
		bizOrgInfoService.update(bizUtOrgDTO);
	}
	
	public void create(BizUtOrgInfoDTO bizUtOrgInfoDTO) throws ServiceException{
		BizUtOrgDTO bizUtOrgDTO = new BizUtOrgDTO();
		BeanHelper.copyProperties(bizUtOrgInfoDTO, bizUtOrgDTO);
		if(DicConstants.YHRS0101_2.equals(bizUtOrgDTO.getOrgStatus())){			
			List<BizUtOrgDTO> bizUtOrgDTOs = bizOrgInfoService.list(bizUtOrgDTO.getUtUnitOid(),DicConstants.YHRS0101_2);
			if(CollectionUtils.isNotEmpty(bizUtOrgDTOs)){
				for (BizUtOrgDTO dto : bizUtOrgDTOs) {
					if(dto.getOrgName().trim().equals(bizUtOrgDTO.getOrgName().trim())){
						throw new ServiceException(null,"同单位下内设机构名称不能重复！");
					}
				}
			}
		}
		bizOrgInfoService.create(bizUtOrgDTO);
	}
	
	public List<BizUtOrgInfoDTO> listByUtUnitOid(java.lang.Long utUnitOid) throws ServiceException{
		List<BizUtOrgInfoDTO> bizUtOrgInfoDTOs = new ArrayList<BizUtOrgInfoDTO>();
		List<BizUtOrgDTO> bizUtOrgDTOs = bizOrgInfoService.list(utUnitOid,null);
		if(null != bizUtOrgDTOs){
			for (BizUtOrgDTO bizUtOrgDTO : bizUtOrgDTOs) {
				BizUtOrgInfoDTO bizUtOrgInfoDTO = new BizUtOrgInfoDTO();
				BeanHelper.copyProperties(bizUtOrgDTO, bizUtOrgInfoDTO);
				bizUtOrgInfoDTOs.add(bizUtOrgInfoDTO);
			}
		}
		return bizUtOrgInfoDTOs;
	}
	
	public void delete(java.lang.Long OrgId) throws ServiceException{
		bizOrgInfoService.delete(OrgId);
	}
	
}
