package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtPositionPlansDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * @desc 拟聘岗位信息服务层
 * @author chenjl
 * @createDate 2016-11-10
 */
public interface PtPositionPlansService {

	/**
     * 获取拟聘岗位信息
     * @param ptPostOid
     * @return
     * @throws ServiceException
     */
    public PtPositionPlansDTO getPtPositionPlans(Long ptPostOid) throws ServiceException;
    
    /**
     * 通过人员ID获取拟聘岗位信息集合
     * @param bizPersonOid
     * @return
     * @throws ServiceException
     */
    public List<PtPositionPlansDTO> listPtPositionPlansByBizPersonId(Long bizPersonOid) throws ServiceException;
    /**
     * 通过人员ID获取拟聘岗位信息记录数
     * @param bizPersonOid
     * @return
     * @throws ServiceException
     */
    public int  countPtPositionPlansByBizPersonOid (Long bizPersonOid) throws ServiceException;
    
    /**
     * 删除拟聘岗位信息
     * @param ptPostOid
     * @return
     * @throws ServiceException
     */
    public void deletePtPositionPlans(Long ptPostOid) throws ServiceException;
    /**
     * 新增拟聘岗位信息
     * @param ptPositionPlansDTO
     * @return
     * @throws ServiceException
     */
	public void insertPtPositionPlans(PtPositionPlansDTO ptPositionPlansDTO) throws ServiceException;
	/**
     * 修改拟聘岗位信息
     * @param ptPositionPlansDTO
     * @return
     * @throws ServiceException
     */
	public void updatePtPositionPlans(PtPositionPlansDTO ptPositionPlansDTO) throws ServiceException;
	/**
     * 批量删除拟聘岗位信息
     * @param ptPostOids
     * @return
     * @throws ServiceException
     */
	public void deletePtPositionPlansByIds(List<Long> ptPostOids) throws ServiceException ;
}