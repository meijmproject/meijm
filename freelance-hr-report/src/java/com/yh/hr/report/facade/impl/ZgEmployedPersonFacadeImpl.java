package com.yh.hr.report.facade.impl;

import java.util.List;
import java.util.Map;

import com.yh.hr.report.service.ZgEmployedPersonService;
import com.yh.platform.core.exception.ServiceException;

public class ZgEmployedPersonFacadeImpl {

	private ZgEmployedPersonService zgEmployedPersonService;

	public void setZgEmployedPersonService(
			ZgEmployedPersonService zgEmployedPersonService) {
		this.zgEmployedPersonService = zgEmployedPersonService;
	}

	public List<Map<String, String>> findZgzg() throws ServiceException {
		return zgEmployedPersonService.findZgzg();
	}
	
	public List<Map<String, String>> findWsjs() throws ServiceException {
		return zgEmployedPersonService.findWsjs();
	}
	
	public List<Map<String, String>> findZyys() throws ServiceException {
		return zgEmployedPersonService.findZyys();
	}
	
	
	public List<Map<String, String>> findZyysZylb() throws ServiceException {
		return zgEmployedPersonService.findZyysZylb();
	}
	
	
	public List<Map<String, String>> findZyzlys() throws ServiceException {
		return zgEmployedPersonService.findZyzlys();
	}
	
	
	public List<Map<String, String>> findZyzlysZylb() throws ServiceException {
		return zgEmployedPersonService.findZyzlysZylb();
	}
	
	
	public List<Map<String, String>> findZyyaos() throws ServiceException {
		return zgEmployedPersonService.findZyyaos();
	}
	
	
	public List<Map<String, String>> findZyyaosxys() throws ServiceException {
		return zgEmployedPersonService.findZyyaosxys();
	}
	
	
	public List<Map<String, String>> findZyyaoszys() throws ServiceException {
		return zgEmployedPersonService.findZyyaoszys();
	}
	
	
	public List<Map<String, String>> findJyjs() throws ServiceException {
		return zgEmployedPersonService.findJyjs();
	}
	
	
	public List<Map<String, String>> findYxjs() throws ServiceException {
		return zgEmployedPersonService.findYxjs();
	}
}
