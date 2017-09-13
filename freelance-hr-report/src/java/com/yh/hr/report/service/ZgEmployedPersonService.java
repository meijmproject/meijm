package com.yh.hr.report.service;

import java.util.List;
import java.util.Map;

import com.yh.platform.core.exception.ServiceException;

public interface ZgEmployedPersonService {

	/**
	 * 年末在岗职工数（包括正式职工、聘用职工、本院规培学员）
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, String>> findZgzg() throws ServiceException;
	
	/**
	 * 卫生技术人员数（未含行政人员）
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, String>> findWsjs() throws ServiceException;
	
	/**
	 * 执业医师数
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, String>> findZyys() throws ServiceException;
	
	/**
	 * 执业医师——中医类别数
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, String>> findZyysZylb() throws ServiceException;
	
	/**
	 * 执业助理医师数
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, String>> findZyzlys() throws ServiceException;
	
	/**
	 * 执业助理医师——中医类别数
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, String>> findZyzlysZylb() throws ServiceException;
	
	/**
	 * 执业药师数
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, String>> findZyyaos() throws ServiceException;
	
	/**
	 * 执业药师师——西药师数
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, String>> findZyyaosxys() throws ServiceException;
	
	/**
	 * 执业药师师——中药师数
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, String>> findZyyaoszys() throws ServiceException;
	
	/**
	 * 检验技师数
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, String>> findJyjs() throws ServiceException;
	
	/**
	 * 影像技师数
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, String>> findYxjs() throws ServiceException;
}
