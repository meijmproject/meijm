package com.yh.hr.info.ver.unit.comm.facade;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.info.ver.unit.comm.dto.VerPbPersonOutDTO;
import com.yh.hr.res.pb.dto.PbPersonOutDTO;
import com.yh.hr.res.pb.service.PbPersonOutService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * @description PbPersonOut facade
 * @author chenp
 * @created 2017-02-16
 * @version 1.0
 */
public class PbPersonOutFacade
{
	private PbPersonOutService pbPersonOutService;

	public void setPbPersonOutService(PbPersonOutService pbPersonOutService) {
		this.pbPersonOutService = pbPersonOutService;
	}

	/**
	 * 新增
	 * @param verPbPersonOutDto
	 * @throws ServiceException
	 */
	public void createPbPersonOut(VerPbPersonOutDTO verPbPersonOutDTO) throws ServiceException
	{
		PbPersonOutDTO pbPersonOutDTO = new PbPersonOutDTO();
		BeanHelper.copyProperties(verPbPersonOutDTO, pbPersonOutDTO);
		pbPersonOutService.create(pbPersonOutDTO);
	}
	
	/**
	 * 根据主键获取PbPersonOut
	 * @param familyOid
	 * @return
	 * @throws ServiceException
	 */
	public VerPbPersonOutDTO findPbPersonOut(Long personOutOid) throws ServiceException
	{
		PbPersonOutDTO pbPersonOutDTO = pbPersonOutService.get(personOutOid);
		VerPbPersonOutDTO verPbPersonOutDto = new VerPbPersonOutDTO();
		BeanHelper.copyProperties(pbPersonOutDTO, verPbPersonOutDto);
		return verPbPersonOutDto;
	}
	
	/**
	 * 修改
	 * @param verPbPersonOutDto
	 * @throws ServiceException
	 */
	public void updatePbPersonOut(VerPbPersonOutDTO verPbPersonOutDto) throws ServiceException
	{
		PbPersonOutDTO pbPersonOutDTO = new PbPersonOutDTO();
		BeanHelper.copyProperties(verPbPersonOutDto, pbPersonOutDTO);
		pbPersonOutService.update(pbPersonOutDTO);
	}
	
	/**
	 * 删除
	 * @param familyOid
	 * @throws ServiceException
	 */
	public void deletePbPersonOut(Long personOutOid) throws ServiceException
	{
		pbPersonOutService.delete(personOutOid);
	}

	public List<VerPbPersonOutDTO> listPbPersonOutByPersonOid(Long personOid) throws ServiceException {
		List<PbPersonOutDTO> list = pbPersonOutService.listPbPersonOutByPersonOid(personOid);
		List<VerPbPersonOutDTO> verPbPersonOutDTOList = new ArrayList<VerPbPersonOutDTO>();
		if(!CollectionUtils.isEmpty(list))
		{
			for(PbPersonOutDTO pbPersonOutDTO : list)
			{
				VerPbPersonOutDTO verPbPersonOutDTO = new VerPbPersonOutDTO();
				BeanHelper.copyProperties(pbPersonOutDTO, verPbPersonOutDTO);
				verPbPersonOutDTOList.add(verPbPersonOutDTO);
			}
		}
		return verPbPersonOutDTOList;
	}
}
