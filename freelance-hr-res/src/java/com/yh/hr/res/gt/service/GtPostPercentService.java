package com.yh.hr.res.gt.service;

import java.util.List;

import com.yh.hr.res.gt.dto.GtPostPercentDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 岗位信息Service
 * @author zhengdr
 *
 * 时间:2016-12-21下午03:51:20
 */
public interface GtPostPercentService {

	/**
	 * 得到岗位信息列表
	 * @param utUnitOid
	 * @return
	 * @throws ServiceException
	 */
	public List<GtPostPercentDTO> getGtPostPercentDTOList(Long utUnitOid)throws ServiceException;
	
	/**
	 * 得到岗位信息
	 * @return
	 * @throws ServiceException
	 */
	public GtPostPercentDTO getGtPostPercentDTO(Long jhgGtPostPercentOid)throws ServiceException;
	
	/**
	 * 得到岗位信息的记录数
	 * @return
	 * @throws ServiceException
	 */
	public int countGtPostPercentByUtUnitOid(Long utUnitOid) throws ServiceException;
	
	
	/**
	 * 新增岗位信息
	 * @param gtPostPercentDTO
	 * @throws ServiceException
	 */
	public void createGtPostPercent(GtPostPercentDTO gtPostPercentDTO)throws ServiceException;
	
	/**
	 * 修改岗位信息
	 * @param gtPostPercentDTO
	 */
	public void modifyGtPostPercent(GtPostPercentDTO gtPostPercentDTO)throws ServiceException;
	
	/**
	 * 根据id删除岗位信息
	 * @param jhgGtPostPercentOid
	 * @throws ServiceException
	 */
	public void deleteGtPostPercent(Long jhgGtPostPercentOid)throws ServiceException;
}
