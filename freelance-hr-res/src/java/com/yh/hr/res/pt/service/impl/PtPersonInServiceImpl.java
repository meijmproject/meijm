package com.yh.hr.res.pt.service.impl;

import java.util.List;

import com.yh.hr.res.pt.bo.PtPersonIn;
import com.yh.hr.res.pt.dto.PtPersonInDTO;
import com.yh.hr.res.pt.queryhelper.PtPersonInQueryHelper;
import com.yh.hr.res.pt.service.PtPersonInService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class PtPersonInServiceImpl implements PtPersonInService {
	
	/*
	 * 列表
	 */
	public List<PtPersonInDTO> listPtPersonInDTO(Long personOid) throws ServiceException {
		return PtPersonInQueryHelper.listPtPersonInDTO(personOid);
	}

	/*
	 * (non-Javadoc)
	 * @see PtPersonInService#createPtPersonIn(PtPersonInDTO)
	 */
	public void createPtPersonIn(PtPersonInDTO ptPersonInDTO) throws ServiceException
	{
		PtPersonIn ptPersonIn =BeanHelper.copyProperties(ptPersonInDTO, PtPersonIn.class);
		if(null != ptPersonIn){
			ptPersonIn.setCreatedByCode(UserContext.getLoginUserID());
			ptPersonIn.setCreatedByName(UserContext.getLoginUserName());
			ptPersonIn.setCreatedDate(DateUtil.now());
			ptPersonIn.save();
		}	
	}
	
	/*
	 * (non-Javadoc)
	 * @see PtPersonInService#updatePtPersonIn(PtPersonInDTO)
	 */
	public void updatePtPersonIn(PtPersonInDTO ptPersonInDTO) throws ServiceException {
		PtPersonIn ptPersonIn = DaoUtil.get(PtPersonIn.class, ptPersonInDTO.getBizPersonOid());
		if(null != ptPersonIn){
			BeanHelper.copyProperties(ptPersonInDTO,ptPersonIn,new String[]{"createdDate","createdByCode","createdByName"});
			ptPersonIn.setUpdatedByCode(UserContext.getLoginUserID());
			ptPersonIn.setUpdatedByName(UserContext.getLoginUserName());
			ptPersonIn.setUpdatedDate(DateUtil.now());
			ptPersonIn.update();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see PtPersonInService#getPersonInDTOById(java.lang.Long)
	 */
	public PtPersonInDTO getPersonInDTOById(Long personOid) throws ServiceException {
		return PtPersonInQueryHelper.getPtPersonInDTOById(personOid);
	}

}