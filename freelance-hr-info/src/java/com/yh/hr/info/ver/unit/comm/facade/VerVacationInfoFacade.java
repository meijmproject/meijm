package com.yh.hr.info.ver.unit.comm.facade;

import com.yh.hr.info.ver.unit.comm.dto.VerPbVacationInfoDTO;
import com.yh.hr.res.pb.dto.PbVacationDto;
import com.yh.hr.res.pb.service.PbVacationService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 人员加班信息 facade
 * @author chenjl
 * @created 2017-04-06
 * @version 1.0
 */
public class VerVacationInfoFacade
{
	private PbVacationService pbVacationService;

	public void setPbVacationService(PbVacationService pbVacationService) {
		this.pbVacationService=pbVacationService;
	}

	/**
	 * 根据主键获取VerPbVacationInfo
	 * @param vacationOid
	 * @return
	 * @throws com.yh.platform.core.exception.ServiceException
	 */
	public VerPbVacationInfoDTO findPbVacationInfo(Long vacationOid) throws ServiceException
	{
		return BeanHelper.copyProperties(pbVacationService.get(vacationOid),VerPbVacationInfoDTO.class);
	}

	/**
	 * 根据personOid查询
	 * @param personOid
	 * @return
	 * @throws com.yh.platform.core.exception.ServiceException
	 */
	public List<VerPbVacationInfoDTO> listPbVacationInfoByPersonOid(Long personOid) throws ServiceException
	{
		List<PbVacationDto> pbVacationDtoList = pbVacationService.list(personOid,null);
		List<VerPbVacationInfoDTO> verPbVacationInfoDTOList = new ArrayList<VerPbVacationInfoDTO>();
		if(!CollectionUtils.isEmpty(pbVacationDtoList))
		{
			for(PbVacationDto pbVacationDto : pbVacationDtoList)
			{
				VerPbVacationInfoDTO verPbVacationInfoDTO = new VerPbVacationInfoDTO();
				BeanHelper.copyProperties(pbVacationDto, verPbVacationInfoDTO);
				verPbVacationInfoDTOList.add(verPbVacationInfoDTO);
			}
		}
		return verPbVacationInfoDTOList;
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
