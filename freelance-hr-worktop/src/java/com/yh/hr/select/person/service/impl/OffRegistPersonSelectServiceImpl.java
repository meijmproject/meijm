package com.yh.hr.select.person.service.impl;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.select.person.dto.PersonSelectDTO;
import com.yh.hr.select.person.queryhelper.OffRegistPersonSelectQueryHelper;
import com.yh.platform.core.exception.ServiceException;

public class OffRegistPersonSelectServiceImpl extends AbsPersonSelectService{
	protected List<PersonSelectDTO> getPbPersonList(TableTagBean ttb) throws ServiceException {
		
		return OffRegistPersonSelectQueryHelper.listPbpersonInfo(ttb);
	}
}
