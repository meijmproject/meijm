package com.yh.hr.report.service;


import java.util.List;

import com.yh.platform.core.exception.ServiceException;

/**
 *  User: liul 
 *  Date: 2017-2-25
 *  从OfficePrintService拆分的公共类
 */
public interface OfficeCommonService
{
	/**
	 * 获取基础库简历信息
	 * 
	 * @param personOid
	 *            基础库人员ID
	 * @param hasEducation
	 *            是否包含学习经历
	 * @return
	 * @throws ServiceException
	 */
	public List<String> getResumeByPersonOid(Long personOid, boolean hasEducation) throws ServiceException;

	/**
	 * 根据基础库PersonOid获取近N年考核情况
	 * 
	 * @param personOid
	 *            基础库ID
	 * @param n
	 *            近几年
	 * @return
	 * @throws ServiceException
	 */
	public String getReviewInfoByPersonOid(Long personOid, int n) throws ServiceException;

	

	
	
	
	
}
