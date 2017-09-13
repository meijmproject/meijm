package com.yh.hr.info.ver.unit.comm.facade;

import com.yh.hr.info.ver.unit.comm.dto.VerChangeOffInfoDTO;
import com.yh.hr.res.pb.dto.PbChangeOffInfoDTO;
import com.yh.hr.res.pb.service.PbChangeOffInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 人员调休信息 facade
 * @author renp
 * @created 2017-04-07
 * @version 1.0
 */
public class VerChangeOffInfoFacade
{
	private PbChangeOffInfoService pbChangeOffInfoService;

	public void setPbChangeOffInfoService(PbChangeOffInfoService pbChangeOffInfoService) {
		this.pbChangeOffInfoService=pbChangeOffInfoService;
	}

	/**
	 * 根据主键获取PbChangeOffInfo
	 * @param changeOffOid
	 * @return
	 * @throws com.yh.platform.core.exception.ServiceException
	 */
	public VerChangeOffInfoDTO findPbChangeOffInfo(Long changeOffOid) throws ServiceException
	{
		return BeanHelper.copyProperties(pbChangeOffInfoService.get(changeOffOid),VerChangeOffInfoDTO.class);
	}

	/**
	 * 根据personOid查询
	 * @param personOid
	 * @return
	 * @throws com.yh.platform.core.exception.ServiceException
	 */
	public List<VerChangeOffInfoDTO> listPbChangeOffInfoByPersonOid(Long personOid) throws ServiceException
	{
		List<PbChangeOffInfoDTO> pbChangeOffInfoDTOList = pbChangeOffInfoService.list(personOid);
		List<VerChangeOffInfoDTO> verChangeOffInfoDTOList = new ArrayList<VerChangeOffInfoDTO>();
		if(!CollectionUtils.isEmpty(pbChangeOffInfoDTOList))
		{
			for(PbChangeOffInfoDTO pbChangeOffInfoDTO : pbChangeOffInfoDTOList)
			{
				VerChangeOffInfoDTO verChangeOffInfoDTO = new VerChangeOffInfoDTO();
				BeanHelper.copyProperties(pbChangeOffInfoDTO, verChangeOffInfoDTO);
				verChangeOffInfoDTOList.add(verChangeOffInfoDTO);
			}
		}
		return verChangeOffInfoDTOList;
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
