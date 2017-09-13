package com.yh.hr.report.service.impl;

import java.util.List;
import java.util.Map;

import com.yh.hr.report.queryhelper.ZgEmployedPersonQueryHelper;
import com.yh.hr.report.service.ZgEmployedPersonService;
import com.yh.platform.core.exception.ServiceException;

public class ZgEmployedPersonServiceImpl implements ZgEmployedPersonService {

	@Override
	public List<Map<String, String>> findZgzg() throws ServiceException {
		return ZgEmployedPersonQueryHelper.findZgzg();
	}
	
	@Override
	public List<Map<String, String>> findWsjs() throws ServiceException {
		return ZgEmployedPersonQueryHelper.findWsjs();
	}
	
	@Override
	public List<Map<String, String>> findZyys() throws ServiceException {
		return ZgEmployedPersonQueryHelper.findZyys();
	}
	
	@Override
	public List<Map<String, String>> findZyysZylb() throws ServiceException {
		return ZgEmployedPersonQueryHelper.findZyysZylb();
	}
	
	@Override
	public List<Map<String, String>> findZyzlys() throws ServiceException {
		return ZgEmployedPersonQueryHelper.findZyzlys();
	}
	
	@Override
	public List<Map<String, String>> findZyzlysZylb() throws ServiceException {
		return ZgEmployedPersonQueryHelper.findZyzlysZylb();
	}
	
	@Override
	public List<Map<String, String>> findZyyaos() throws ServiceException {
		return ZgEmployedPersonQueryHelper.findZyyaos();
	}
	
	@Override
	public List<Map<String, String>> findZyyaosxys() throws ServiceException {
		return ZgEmployedPersonQueryHelper.findZyyaosxys();
	}
	
	@Override
	public List<Map<String, String>> findZyyaoszys() throws ServiceException {
		return ZgEmployedPersonQueryHelper.findZyyaoszys();
	}
	
	@Override
	public List<Map<String, String>> findJyjs() throws ServiceException {
		return ZgEmployedPersonQueryHelper.findJyjs();
	}
	
	@Override
	public List<Map<String, String>> findYxjs() throws ServiceException {
		return ZgEmployedPersonQueryHelper.findYxjs();
	}

}
