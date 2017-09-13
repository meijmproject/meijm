package com.yh.hr.info.ver.unit.comm.facade;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.pb.dto.PbDutyInfoDTO;
import com.yh.hr.res.pb.service.PbDutyInfoService;
import com.yh.platform.core.exception.ServiceException;

public class PbDutyInfoFacade {
	
	private PbDutyInfoService pbDutyInfoService;

	public void setPbDutyInfoService(PbDutyInfoService pbDutyInfoService) {
		this.pbDutyInfoService = pbDutyInfoService;
	}
	
	/**
	 * 新增-院内中层职务任职信息
	 * @param pbDutyInfoDTO
	 * @throws ServiceException
	 */
	public void create(PbDutyInfoDTO pbDutyInfoDTO) throws ServiceException{
		pbDutyInfoService.create(pbDutyInfoDTO);
	}
	
	/**
	 * 删除-院内中层职务任职信息
	 * @param pbEducationLevelDegreeId
	 * @throws ServiceException
	 */
	public void delete(java.lang.Long dutyOid) throws ServiceException {
		pbDutyInfoService.delete(dutyOid);
	}
	
	/**
	 * 根据dutyOid查找院内中层职务任职信息
	 * @param dutyOid
	 * @return PbDutyInfoDTO
	 * @throws ServiceException
	 */
	public PbDutyInfoDTO get(java.lang.Long dutyOid) throws ServiceException {
		return pbDutyInfoService.get(dutyOid);
	}
	
	/**
	 * 列出多条院内中层职务任职信息
	 * @param ttb
	 * @return List<PbDutyInfoDTO>
	 * @throws ServiceException
	 */
	public List<PbDutyInfoDTO> find(TableTagBean ttb) throws ServiceException {
		return pbDutyInfoService.find(ttb);
	}
	
	/**
	 * 更新和修改院内中层职务任职信息
	 * @param pbDutyInfoDto
	 * @throws ServiceException
	 */
	public void update(PbDutyInfoDTO pbDutyInfoDto) throws ServiceException {
		pbDutyInfoService.update(pbDutyInfoDto);
	}
	
	/**
	 * 根据人员id查询该人员所有的院内中层职务任职信息记录
	 * @param dutyOid
	 * @return List<PbDutyInfoDTO>
	 * @throws ServiceException
	 */
	public List<PbDutyInfoDTO> listPbDutyInfoByPersonOid(Long personOid) throws ServiceException{
		return pbDutyInfoService.listPbDutyInfoByPersonOid(personOid);
	}

	/**
	 * 根据人员id查询该人员所有的院内中层职务任职信息记录（导出用）
	 * @param dutyOid
	 * @return List<PbDutyInfoDTO>
	 * @throws ServiceException
	 */
	public List<PbDutyInfoDTO> listExportPbDutyInfoByPersonOid(Long personOid) throws ServiceException{
		return pbDutyInfoService.listExportPbDutyInfoByPersonOid(personOid);
	}

}
