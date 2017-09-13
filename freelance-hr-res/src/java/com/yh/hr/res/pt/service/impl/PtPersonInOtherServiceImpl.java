package com.yh.hr.res.pt.service.impl;

import java.util.List;

import com.yh.hr.res.pt.bo.PtPersonInOther;
import com.yh.hr.res.pt.dto.PtPersonInOtherDTO;
import com.yh.hr.res.pt.queryhelper.PtPersonInOtherQueryHelper;
import com.yh.hr.res.pt.service.PtPersonInOtherService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class PtPersonInOtherServiceImpl implements PtPersonInOtherService {
	
	/*
	 * 列表
	 */
	public List<PtPersonInOtherDTO> listPtPersonInOtherDTO(Long personInOtherOid) throws ServiceException {
		return PtPersonInOtherQueryHelper.listPtPersonInOtherDTO(personInOtherOid);
	}
	
	/*
	 * (non-Javadoc)
	 * @see PtPersonInService#createPtPersonIn(PtPersonInDTO)
	 */
	public void createPtPersonInOther(PtPersonInOtherDTO ptPersonInOtherDTO) throws ServiceException
	{
		PtPersonInOther ptPersonInOther =BeanHelper.copyProperties(ptPersonInOtherDTO, PtPersonInOther.class);
		if(null != ptPersonInOther){
			ptPersonInOther.setCreatedBy(UserContext.getLoginUserName());
			ptPersonInOther.setCreatedDate(DateUtil.now());
			ptPersonInOther.save();
		}	
	}
	
	/*
	 * (non-Javadoc)
	 * @see PtPersonInService#updatePtPersonIn(PtPersonInDTO)
	 */
	public void updatePtPersonInOther(PtPersonInOtherDTO ptPersonInOtherDTO) throws ServiceException {
		PtPersonInOther ptPersonInOther = DaoUtil.get(PtPersonInOther.class, ptPersonInOtherDTO.getPtPersonInOtherOid());
		if(null != ptPersonInOther){
			BeanHelper.copyProperties(ptPersonInOtherDTO,ptPersonInOther,new String[]{"createdDate","createdByCode","createdByName"});
			ptPersonInOther.setUpdatedBy(UserContext.getLoginUserName());
			ptPersonInOther.setUpdatedDate(DateUtil.now());
			ptPersonInOther.update();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see PtPersonInService#getPersonInDTOById(java.lang.Long)
	 */
	public PtPersonInOtherDTO getPersonInOtherDTOById(Long personInOtherOid) throws ServiceException {
		return PtPersonInOtherQueryHelper.getPtPersonInOtherDTO(personInOtherOid);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.yh.hr.res.pt.service.PtPersonInOtherInfoService#listPtPersonInOtherInfoByBizPersonOid(java.lang.Long)
	 */
	public List<PtPersonInOtherDTO> listPtPersonInOtherInfoByBizPersonOid(Long bizPersonOid) throws ServiceException {
		
		return PtPersonInOtherQueryHelper.listPtPersonInOtherDTOByBizPersonOid(bizPersonOid);
	}

}