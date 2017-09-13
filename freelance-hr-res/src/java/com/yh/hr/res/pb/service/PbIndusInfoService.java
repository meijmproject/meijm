package com.yh.hr.res.pb.service;

import java.util.List;

import com.yh.hr.res.pb.dto.PbIndusInfoDTO;

import com.yh.platform.core.exception.ServiceException;
/**
 * @desc 工伤信息业务逻辑层
 * @author xiongyx
 * @createDate 2016-8-15下午04:30:56
 */
public interface PbIndusInfoService{
	
	/**
	 * 得到工伤信息列表
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PbIndusInfoDTO> listPbIndusInfo(Long personOid) throws ServiceException;
	/**
	 * 新增工伤信息
	 * @param pbIndusInfoDTO
	 * @throws ServiceException
	 */
	public void createPbIndusInfo(PbIndusInfoDTO pbIndusInfoDTO) throws ServiceException;
	/**
	 * 更新工伤信息
	 * @param pbIndusInfoDTO
	 * @throws ServiceException
	 */
	public  void updatePbIndusInfo(PbIndusInfoDTO pbIndusInfoDTO) throws ServiceException;
	/**
	 * 删除工伤信息
	 * @param indusOid
	 * @throws ServiceException
	 */
	public  void deletePbIndusInfoById(Long indusOid) throws ServiceException;
	/**
	 * 根据id获取工伤信息
	 * @param indusOid
	 * @return
	 * @throws ServiceException
	 */
	public  PbIndusInfoDTO getPbIndusInfoById(Long indusOid) throws ServiceException;
}