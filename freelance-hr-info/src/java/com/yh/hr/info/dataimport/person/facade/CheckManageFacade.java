package com.yh.hr.info.dataimport.person.facade;

import java.util.List;

import com.yh.hr.component.im.check.manage.CheckServiceManage;
import com.yh.hr.component.im.dto.CheckColumnDTO;
import com.yh.hr.component.im.dto.CheckResultDTO;
import com.yh.hr.res.im.dto.ImCollectTemplateDTO;
import com.yh.platform.core.exception.ServiceException;


public class CheckManageFacade {

	/**
	 * 导入前检查
	 * @param dataList
	 * @param collList
	 * @return
	 * @throws ServiceException
	 */
	public List<CheckResultDTO> check(List<CheckColumnDTO> dataList,List<ImCollectTemplateDTO> collList) throws ServiceException {
		return CheckServiceManage.check(dataList, collList);
	}
}