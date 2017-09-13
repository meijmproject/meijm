package com.yh.hr.component.yngw.facade.impl;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.yngw.dto.GwYnInfoDTO;
import com.yh.hr.component.yngw.facade.GwYnInfoFacade;
import com.yh.hr.component.yngw.service.GwYnInfoService;
import com.yh.platform.core.exception.ServiceException;

public class GwYnInfoFacadeImpl implements GwYnInfoFacade {
	
	private GwYnInfoService gwYnInfoService;

	public void setGwYnInfoService(GwYnInfoService gwYnInfoService) {
		this.gwYnInfoService = gwYnInfoService;
	}

	public void create(GwYnInfoDTO gwYnInfoDTO) throws ServiceException {
		//根据父id获取名称
		if(null != gwYnInfoDTO.getParentPositionOid())
		{
			GwYnInfoDTO parentDto = gwYnInfoService.get(gwYnInfoDTO.getParentPositionOid());
			if(null != parentDto)
			{
				gwYnInfoDTO.setParentPositionName(parentDto.getPositionName());
			}
		}
		gwYnInfoService.create(gwYnInfoDTO);
	}

	public void delete(Long positionOid) throws ServiceException {
		gwYnInfoService.delete(positionOid);
	}

	public GwYnInfoDTO get(Long positionOid) throws ServiceException {
		return gwYnInfoService.get(positionOid);
	}

	public List<GwYnInfoDTO> find(TableTagBean ttb) throws ServiceException {
		return gwYnInfoService.find(ttb);
	}

	public void update(GwYnInfoDTO gwYnInfoDTO) throws ServiceException {
		//根据父id获取名称
		if(null != gwYnInfoDTO.getParentPositionOid())
		{
			GwYnInfoDTO parentDto = gwYnInfoService.get(gwYnInfoDTO.getParentPositionOid());
			if(null != parentDto)
			{
				gwYnInfoDTO.setParentPositionName(parentDto.getPositionName());
			}
		}
		gwYnInfoService.update(gwYnInfoDTO);
	}

	public List<GwYnInfoDTO> listAllGwYnInfo() throws ServiceException {
		return gwYnInfoService.listAllGwYnInfo();
	}

	/**
	 * 获取岗位名称信息
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public List<GwYnInfoDTO> listPositionName(TableTagBean ttb) throws ServiceException {
		List<GwYnInfoDTO> hisPositionNameList=null;
		try{
			hisPositionNameList = gwYnInfoService.listPositionName(ttb);	
		}catch(ServiceException ex){
			throw new ServiceException(null,ex.getMessage()); 
		}	
		return hisPositionNameList;
	}

	/**
	 * 确认传入的岗位名称(大类)是否已经创建
	 * @return true 已创建, false未创建
	 * @throws ServiceException
	 */
	public boolean findPositionNameParentNodeIsExist(String positionNameDl) throws ServiceException {
		return gwYnInfoService.positionNameParentNodeIsExist(positionNameDl);
	}

	/*
	 * (non-Javadoc)
	 * @see GwYnInfoFacade#findPositionInfoByPositionType(java.lang.String)
	 */
	public List<GwYnInfoDTO> findPositionInfoByPositionType(String positionType)
			throws ServiceException 
	{
		return gwYnInfoService.findPositionInfoByPositionType(positionType);
	}
	
	

}
