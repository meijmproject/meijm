package com.yh.hr.info.view.facade;

import java.util.ArrayList;
import java.util.List;

import com.yh.hr.info.ver.unit.comm.dto.VerPbFamilyInfoDTO;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pb.dto.PbFamilyInfoDTO;
import com.yh.hr.res.pb.queryhelper.PbFamilyInfoQueryHelper;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * @description PbFamilyInfo facade View
 * @author huangyj
 * @created 2016-08-15
 * @version 1.0
 */
public class ViewPbFamilyInfoFacade
{
	
	
	/**
	 * 根据主键获取PbFamilyInfo
	 * @param familyOid
	 * @return
	 * @throws ServiceException
	 */
	public VerPbFamilyInfoDTO findPbFamilyInfo(Long familyOid) throws ServiceException
	{
		PbFamilyInfoDTO pbFamilyInfoDto = PbFamilyInfoQueryHelper.getPbFamilyInfoDTOById(familyOid);
		VerPbFamilyInfoDTO verPbFamilyInfoDto = new VerPbFamilyInfoDTO();
		BeanHelper.copyProperties(pbFamilyInfoDto, verPbFamilyInfoDto);
		return verPbFamilyInfoDto;
	}
	
	/**
	 * 根据personOid查询
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public List<VerPbFamilyInfoDTO> listPbFamilyInfoByPersonOid(Long personOid) throws ServiceException
	{
		List<PbFamilyInfoDTO> pbFamilyInfoDtoList = PbFamilyInfoQueryHelper.listPbFamilyInfoDTOByPersonId(personOid);
		List<VerPbFamilyInfoDTO> verPbFamilyInfoDtoList = new ArrayList<VerPbFamilyInfoDTO>();
		if(!CollectionUtils.isEmpty(pbFamilyInfoDtoList))
		{
			for(PbFamilyInfoDTO pbFamilyInfoDto : pbFamilyInfoDtoList)
			{
				VerPbFamilyInfoDTO verPbFamilyInfoDto = new VerPbFamilyInfoDTO();
				BeanHelper.copyProperties(pbFamilyInfoDto, verPbFamilyInfoDto);
				verPbFamilyInfoDtoList.add(verPbFamilyInfoDto);
			}
		}
		return verPbFamilyInfoDtoList;
	}
	
	/**
	 * 获取查询总数
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public int countPbFamilyInfoByPersonOid(Long personOid) throws ServiceException
	{
		return PbFamilyInfoQueryHelper.countPbFamilyInfoDTOByPersonId(personOid);
	}
	
}
