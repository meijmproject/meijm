package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtReviewBizInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 年度考核信息Service
 * @author xiongyx
 */
public interface PtReviewBizInfoService {

	/**
	 * 新增
	 */
	public Long create(PtReviewBizInfoDTO serdto)throws ServiceException;

	/**
	 * 查询
	 */	
	public PtReviewBizInfoDTO get(Long ptReviewBizOid) throws ServiceException;

	/**
	 * 更新
	 */
	public void update(PtReviewBizInfoDTO serdto) throws ServiceException;

	/**
	 * 删除
	 */	
	public void delete(Long ptReviewBizOid) throws ServiceException;
	
	//检查是否在办
	public List<String> check(Long unitOid,String years) throws ServiceException;
	
	public void tansferPerson(Long reviewBizInfoOid,Long unitOid,String years) throws ServiceException;
	
	public void tansferSyPerson(Long reviewBizInfoOid,Long unitOid,String years) throws ServiceException;
}
