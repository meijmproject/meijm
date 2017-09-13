package com.yh.hr.info.ver.unit.comm.facade;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.info.ver.unit.comm.dto.VerGoOutInfoDTO;
import com.yh.hr.res.pb.dto.PbGoOutInfoDTO;
import com.yh.hr.res.pb.service.PbGoOutInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 人员外出信息 facade
 * @author chenjl
 * @created 2017-04-06
 * @version 1.0
 */
public class VerGoOutInfoFacade
{
	private PbGoOutInfoService pbGoOutInfoService;
	
	public void setPbGoOutInfoService(PbGoOutInfoService pbGoOutInfoService) {
		this.pbGoOutInfoService=pbGoOutInfoService;
	}

	/**
	 * 根据主键获取PbGoOutInfo
	 * @param goOutOid
	 * @return
	 * @throws ServiceException
	 */
	public VerGoOutInfoDTO findPbGoOutInfo(Long goOutOid) throws ServiceException
	{
		return BeanHelper.copyProperties(pbGoOutInfoService.get(goOutOid),VerGoOutInfoDTO.class);
	}
	
	/**
	 * 根据personOid查询
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public List<VerGoOutInfoDTO> listPbGoOutInfoByPersonOid(Long personOid) throws ServiceException
	{
		List<PbGoOutInfoDTO> pbFamilyInfoDtoList = pbGoOutInfoService.list(personOid);
		List<VerGoOutInfoDTO> verPbGoOutInfoDtoList = new ArrayList<VerGoOutInfoDTO>();
		if(!CollectionUtils.isEmpty(pbFamilyInfoDtoList))
		{
			for(PbGoOutInfoDTO pbFamilyInfoDto : pbFamilyInfoDtoList)
			{
				VerGoOutInfoDTO verPbGoOutInfoDto = new VerGoOutInfoDTO();
				BeanHelper.copyProperties(pbFamilyInfoDto, verPbGoOutInfoDto);
				verPbGoOutInfoDtoList.add(verPbGoOutInfoDto);
			}
		}
		return verPbGoOutInfoDtoList;
	}
	
//	/**
//	 * 获取查询总数
//	 * @param personOid
//	 * @return
//	 * @throws ServiceException
//	 */
//	public int countPbGoOutInfoByPersonOid(Long personOid) throws ServiceException
//	{
//		return PbGoOutInfoQueryHelper.countPbGoOutInfoDTOByPersonId(personOid);
//	}
	
}
