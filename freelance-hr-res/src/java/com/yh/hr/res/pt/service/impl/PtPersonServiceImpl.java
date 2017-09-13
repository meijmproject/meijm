package com.yh.hr.res.pt.service.impl;

import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.dto.PtPersonDTO;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.service.PtPersonService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

public class PtPersonServiceImpl implements PtPersonService {

	/*
	 * (non-Javadoc)
	 * @see PtPersonService#updatePersonInfo(PtPersonDTO)
	 */
	public void updatePersonInfo(PtPersonDTO PtPersonDTO) throws ServiceException {
		PtPerson PtPerson = DaoUtil.get(PtPerson.class, PtPersonDTO.getBizPersonOid());
		if(null != PtPerson){
			BeanHelper.copyProperties(PtPersonDTO,PtPerson,BeanHelper.getNullPropertyNames(PtPersonDTO));
			PtPerson.setUpdatedByCode(UserContext.getLoginUserID());
			PtPerson.setUpdatedByName(UserContext.getLoginUserName());
			PtPerson.setUpdatedDate(DateUtil.now());
			PtPerson.update();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see PtPersonService#addPersonInfo(PtPersonDTO)
	 */
	public Long addPersonInfo(PtPersonDTO PtPersonDTO) throws ServiceException {
		PtPerson PtPerson =BeanHelper.copyProperties(PtPersonDTO,PtPerson.class);
		PtPerson.setCreatedByCode(UserContext.getLoginUserID());
		PtPerson.setCreatedByName(UserContext.getLoginUserName());
		PtPerson.setCreatedDate(DateUtil.now());
		PtPerson.save();
		return PtPerson.getBizPersonOid();
	}

	public void deletePersonInfo(Long bizPersonOid) throws ServiceException {
		PtPerson PtPerson = DaoUtil.get(PtPerson.class, bizPersonOid);
		if(null != PtPerson){
			PtPerson.delete();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see PtPersonService#getPersonInfoDTOById(java.lang.Long)
	 */
	public PtPersonDTO getPtPersonDTO(Long bizPersonOid) throws ServiceException {
		return PtPersonQueryHelper.getPtPersonDTOById(bizPersonOid);
	}
	
	/**
	 * 检查是否可以修改人员信息
	 * @param PtPersonDTO
	 */
	public void checkCanUpdate(PtPersonDTO PtPersonDTO) throws ServiceException {
		List<PtPersonDTO> list = PtPersonQueryHelper.checkUniquePbPerson(PtPersonDTO.getIdCode(), PtPersonDTO.getIdNo(), PtPersonDTO.getBizPersonOid());
		if (CollectionUtils.isNotEmpty(list)) {
			throw new ServiceException(null, "已存在相同证件类型，证件号码人员!");
		}
	}

	/* (non-Javadoc)
	 * @see PtPersonService#getPtPersonByTaskOid(java.lang.Long)
	 */
	public PtPersonDTO getByTaskOid(Long bizTaskOid) throws ServiceException {
		return BeanHelper.copyProperties(PtPersonQueryHelper.getByTaskOid(bizTaskOid), PtPersonDTO.class);
	}

	public PtPersonDTO getByPersonOid(Long personOid) throws ServiceException {
		return BeanHelper.copyProperties(PtPersonQueryHelper.getPtPersonDTOByPersonId(personOid), PtPersonDTO.class);
	}

	/**
	 * 检查工号唯一
	 * @param PtPersonDTO
	 */
	public void checkUniquePersonCode(PtPersonDTO PtPersonDTO) throws ServiceException {
		List<PtPersonDTO> list = PtPersonQueryHelper.checkUniquePersonCode(PtPersonDTO.getPersonCode(), PtPersonDTO.getBizPersonOid());
		if (CollectionUtils.isNotEmpty(list)) {
			throw new ServiceException(null, "已存在相同工号的人员!");
		}
	}
}