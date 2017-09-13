package com.yh.hr.info.ver.unit.comm.facade;

import java.util.ArrayList;
import java.util.List;

import com.yh.hr.info.ver.unit.comm.dto.VerOverTimeInfoDTO;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pb.dto.PbOverTimeInfoDTO;
import com.yh.hr.res.pb.service.PbOverTimeInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 人员加班信息 facade
 * @author chenjl
 * @created 2017-04-06
 * @version 1.0
 */
public class VerOverTimeInfoFacade
{
	private PbOverTimeInfoService pbOverTimeInfoService;
	
	public void setPbOverTimeInfoService(PbOverTimeInfoService pbOverTimeInfoService) {
		this.pbOverTimeInfoService=pbOverTimeInfoService;
	}

	/**
	 * 根据主键获取PbOverTimeInfo
	 * @param goOutOid
	 * @return
	 * @throws ServiceException
	 */
	public VerOverTimeInfoDTO findPbOverTimeInfo(Long goOutOid) throws ServiceException
	{
		return BeanHelper.copyProperties(pbOverTimeInfoService.get(goOutOid),VerOverTimeInfoDTO.class);
	}
	
	/**
	 * 根据personOid查询
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public List<VerOverTimeInfoDTO> listPbOverTimeInfoByPersonOid(Long personOid) throws ServiceException
	{
		List<PbOverTimeInfoDTO> pbFamilyInfoDtoList = pbOverTimeInfoService.list(personOid);
		List<VerOverTimeInfoDTO> verPbOverTimeInfoDtoList = new ArrayList<VerOverTimeInfoDTO>();
		if(!CollectionUtils.isEmpty(pbFamilyInfoDtoList))
		{
			for(PbOverTimeInfoDTO pbFamilyInfoDto : pbFamilyInfoDtoList)
			{
				VerOverTimeInfoDTO verPbOverTimeInfoDto = new VerOverTimeInfoDTO();
				BeanHelper.copyProperties(pbFamilyInfoDto, verPbOverTimeInfoDto);
				verPbOverTimeInfoDtoList.add(verPbOverTimeInfoDto);
			}
		}
		return verPbOverTimeInfoDtoList;
	}
	
//	/**
//	 * 获取查询总数
//	 * @param personOid
//	 * @return
//	 * @throws ServiceException
//	 */
//	public int countPbOverTimeInfoByPersonOid(Long personOid) throws ServiceException
//	{
//		return PbOverTimeInfoQueryHelper.countPbOverTimeInfoDTOByPersonId(personOid);
//	}
	
}
