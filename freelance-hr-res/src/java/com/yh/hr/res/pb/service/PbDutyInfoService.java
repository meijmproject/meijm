package com.yh.hr.res.pb.service;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.pb.dto.PbDutyInfoDTO;
import com.yh.platform.core.exception.ServiceException;

public interface PbDutyInfoService{

	/**
	 * 新增-院内中层职务任职信息
	 * @param pbDutyInfoDTO
	 * @throws ServiceException
	 */
	public void create(PbDutyInfoDTO pbDutyInfoDTO) throws ServiceException;
	
	/**
	 * 删除-院内中层职务任职信息
	 * @param pbEducationLevelDegreeId
	 * @throws ServiceException
	 */
	public void delete(Long dutyOid) throws ServiceException;
	
	/**
	 * 根据dutyOid查找院内中层职务任职信息
	 * @param dutyOid
	 * @return PbDutyInfoDTO
	 * @throws ServiceException
	 */
	public PbDutyInfoDTO get(Long dutyOid) throws ServiceException;
	
	/**
	 * 列出多条院内中层职务任职信息
	 * @param ttb
	 * @return List<PbDutyInfoDTO>
	 * @throws ServiceException
	 */
	public List<PbDutyInfoDTO> find(TableTagBean ttb) throws ServiceException;
	
	/**
	 * 更新和修改院内中层职务任职信息
	 * @param pbDutyInfoDto
	 * @throws ServiceException
	 */
	public void update(PbDutyInfoDTO pbDutyInfoDto) throws ServiceException;
	
	/**
	 * 根据人员id查询该人员所有的院内中层职务任职信息记录
	 * @param personOid
	 * @return List<PbDutyInfoDTO>
	 * @throws ServiceException
	 */
	public List<PbDutyInfoDTO> listPbDutyInfoByPersonOid(Long personOid) throws ServiceException;
	/**
	 * 根据人员id查询该人员所有的院内中层职务任职信息记录（导出用）
	 * @param personOid
	 * @return List<PbDutyInfoDTO>
	 * @throws ServiceException
	 */
	public List<PbDutyInfoDTO> listExportPbDutyInfoByPersonOid(Long personOid) throws ServiceException;
	/**
	 * 根据personOid获取主职务
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public PbDutyInfoDTO getPbDutyInfoByPersonOid(Long personOid) throws ServiceException;

}
