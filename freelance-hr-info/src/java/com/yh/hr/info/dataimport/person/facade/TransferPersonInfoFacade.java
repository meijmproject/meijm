package com.yh.hr.info.dataimport.person.facade;

import java.util.List;

import com.yh.hr.info.dataimport.person.service.TransferPersonInfoService;
import com.yh.hr.res.im.bo.ImCheckPersonInfo;

public class TransferPersonInfoFacade {

	private TransferPersonInfoService transferPersonInfoService;

	public List<ImCheckPersonInfo> listPersonInfo() throws Exception{
		return transferPersonInfoService.listPersonInfo();
	}

	public void setTransferPersonInfoService(
			TransferPersonInfoService transferPersonInfoService) {
		this.transferPersonInfoService = transferPersonInfoService;
	}

	public void transferPersonInfo(Long checkPersonInfoOid) throws Exception {
		transferPersonInfoService.transferPersonInfo(checkPersonInfoOid);
	}

}
