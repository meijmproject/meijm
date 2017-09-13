package com.yh.hr.info.dataimport.person.service.impl;

import java.util.List;

import com.yh.hr.info.dataimport.person.queryhelper.VerPersonDataQueryHelper;
import com.yh.hr.info.dataimport.person.service.TransferPersonInfoService;
import com.yh.hr.info.dataimport.person.service.manage.ImportPersonServiceManage;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.im.bo.ImCheckPersonInfo;

public class TransferPersonInfoServiceImpl implements TransferPersonInfoService {

	@Override
	public List<ImCheckPersonInfo> listPersonInfo() throws Exception {
		return VerPersonDataQueryHelper.listImCheckPersonInfos(DicConstants.YHRS0003_1);
	}

	@Override
	public void transferPersonInfo(Long checkPersonInfoOid) throws Exception {
		ImportPersonServiceManage.transferPersonInfo(checkPersonInfoOid);
	}
}
