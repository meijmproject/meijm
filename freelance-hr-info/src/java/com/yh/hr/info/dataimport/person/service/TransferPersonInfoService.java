package com.yh.hr.info.dataimport.person.service;

import java.util.List;

import com.yh.hr.res.im.bo.ImCheckPersonInfo;


public interface TransferPersonInfoService {

	List<ImCheckPersonInfo> listPersonInfo() throws Exception;

	void transferPersonInfo(Long checkPersonInfoOid) throws Exception;
   
}
