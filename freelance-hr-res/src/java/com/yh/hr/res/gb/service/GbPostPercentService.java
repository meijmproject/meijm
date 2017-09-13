package com.yh.hr.res.gb.service;

import java.util.List;

import com.yh.hr.res.gb.dto.GbPostPercentDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 岗位信息Service
 * @author zhengdr
 *
 * 时间:2016-12-22下午01:43:48
 */
public interface GbPostPercentService {

	/**
	 * 得到岗位信息列表
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public List<GbPostPercentDTO> getGbPostPercentDTOList(Long unitOid)throws ServiceException;
	
	/**
	 * 得到岗位信息
	 * @return
	 * @throws ServiceException
	 */
	public GbPostPercentDTO getGbPostPercentDTO(Long jhgGbPostPercentOid)throws ServiceException;
	
	/**
	 * 得到岗位信息的记录数
	 * @return
	 * @throws ServiceException
	 */
	public int countGbPostPercentByUnitOid(Long unitOid) throws ServiceException;
	
	
	/**
	 * 新增岗位信息
	 * @param gbPostPercentDTO
	 * @throws ServiceException
	 */
	public void createGbPostPercent(GbPostPercentDTO gbPostPercentDTO)throws ServiceException;
	
	/**
	 * 修改岗位信息
	 * @param gbPostPercentDTO
	 */
	public void modifyGbPostPercent(GbPostPercentDTO gbPostPercentDTO)throws ServiceException;
	
	/**
	 * 根据id删除岗位信息
	 * @param jhgGbPostPercentOid
	 * @throws ServiceException
	 */
	public void deleteGbPostPercent(Long jhgGbPostPercentOid)throws ServiceException;
}
