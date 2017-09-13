package com.yh.hr.info.ver.unit.comm.facade;


import java.util.ArrayList;
import java.util.List;

import com.yh.hr.info.ver.unit.comm.dto.VerPbPersonInDTO;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pb.dto.PbPersonInDTO;
import com.yh.hr.res.pb.service.PbPersonInService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * @description PbPersonIn facade
 * @author chenp
 * @created 2017-02-16
 * @version 1.0
 */
public class PbPersonInFacade
{
	private PbPersonInService pbPersonInService;

	public void setPbPersonInService(PbPersonInService pbPersonInService) {
		this.pbPersonInService = pbPersonInService;
	}
	
	/**
	 * 新增
	 * @param verPbPersonInDto
	 * @throws ServiceException
	 */
	public void createPbPersonIn(VerPbPersonInDTO verPbPersonInDto) throws ServiceException
	{
		PbPersonInDTO pbPersonInDto = new PbPersonInDTO();
		BeanHelper.copyProperties(verPbPersonInDto, pbPersonInDto);
		pbPersonInService.create(pbPersonInDto);
	}
	
	/**
	 * 根据主键获取PbPersonIn
	 * @param familyOid
	 * @return
	 * @throws ServiceException
	 */
	public VerPbPersonInDTO findPbPersonIn(Long personInOid) throws ServiceException
	{
		PbPersonInDTO pbPersonInDto = pbPersonInService.get(personInOid);
		VerPbPersonInDTO verPbPersonInDto = new VerPbPersonInDTO();
		BeanHelper.copyProperties(pbPersonInDto, verPbPersonInDto);
		return verPbPersonInDto;
	}
	
	/**
	 * 修改
	 * @param verPbPersonInDto
	 * @throws ServiceException
	 */
	public void updatePbPersonIn(VerPbPersonInDTO verPbPersonInDto) throws ServiceException
	{
		PbPersonInDTO pbPersonInDto = new PbPersonInDTO();
		BeanHelper.copyProperties(verPbPersonInDto, pbPersonInDto);
		pbPersonInService.update(pbPersonInDto);
	}
	
	/**
	 * 删除
	 * @param familyOid
	 * @throws ServiceException
	 */
	public void deletePbPersonIn(Long personInOid) throws ServiceException
	{
		pbPersonInService.delete(personInOid);
	}

	public List<VerPbPersonInDTO> listPbPersonInByPersonOid(Long longValue) throws ServiceException {
		List<PbPersonInDTO> list = pbPersonInService.listPbPersonInByPersonOid(longValue);
		List<VerPbPersonInDTO> verPbPersonInDTOList = new ArrayList<VerPbPersonInDTO>();
		if(!CollectionUtils.isEmpty(list))
		{
			for(PbPersonInDTO pbPersonInDTO : list)
			{
				VerPbPersonInDTO verPbPersonInDTO = new VerPbPersonInDTO();
				BeanHelper.copyProperties(pbPersonInDTO, verPbPersonInDTO);
				verPbPersonInDTOList.add(verPbPersonInDTO);
			}
		}
		return verPbPersonInDTOList;
	}
}
