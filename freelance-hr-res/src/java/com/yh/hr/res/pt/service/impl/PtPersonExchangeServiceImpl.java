package com.yh.hr.res.pt.service.impl;

import com.yh.hr.res.pt.bo.PtPersonExchange;
import com.yh.hr.res.pt.dto.PtPersonExchangeDTO;
import com.yh.hr.res.pt.queryhelper.PtPersonExchangeQueryHepler;
import com.yh.hr.res.pt.service.PtPersonExchangeService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class PtPersonExchangeServiceImpl implements PtPersonExchangeService {
	
	public void createPtPersonExchange(PtPersonExchangeDTO ptPersonExchangeDTO) throws ServiceException
	{
		PtPersonExchange ptPersonExchange =BeanHelper.copyProperties(ptPersonExchangeDTO, PtPersonExchange.class);
		if(null != ptPersonExchange){
			ptPersonExchange.setCreatedByCode(UserContext.getLoginUserID());
			ptPersonExchange.setCreatedByName(UserContext.getLoginUserName());
			ptPersonExchange.setCreatedDate(DateUtil.now());
			ptPersonExchange.save();
		}	
	}
	
	
	public void updatePtPersonExchange(PtPersonExchangeDTO ptPersonExchangeDTO) throws ServiceException {
		PtPersonExchange ptPersonExchange = DaoUtil.get(PtPersonExchange.class, ptPersonExchangeDTO.getPtPersonExchangeOid());
		if(null != ptPersonExchange){
			BeanHelper.copyProperties(ptPersonExchangeDTO,ptPersonExchange,new String[]{"createdDate","createdByCode","createdByName"});
			ptPersonExchange.setUpdatedByCode(UserContext.getLoginUserID());
			ptPersonExchange.setUpdatedByName(UserContext.getLoginUserName());
			ptPersonExchange.setUpdatedDate(DateUtil.now());
			ptPersonExchange.update();
		}
	}
	
	
	public PtPersonExchangeDTO getPtPersonExchangeDTOById(Long personOid) throws ServiceException {
		return PtPersonExchangeQueryHepler.getPtPersonExchangeDTOById(personOid);
	}

	public PtPersonExchangeDTO getByPersonOid(Long personOid) throws ServiceException {
		return BeanHelper.copyProperties(PtPersonExchangeQueryHepler.getDTOByPersonId(personOid), PtPersonExchangeDTO.class);
	}
}