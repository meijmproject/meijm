package com.yh.hr.info.dataimport.person.service.manage;

import jade.workflow.utils.SpringBeanUtil;

import com.yh.hr.info.dataimport.person.service.transfer.ImportPersonTransferService;
import com.yh.platform.core.exception.ServiceException;

/**
 * 人员导入入库service管理类
 * @author wangx
 * @date 2017-07-15
 * @version 1.0
 */
public class ImportPersonServiceManage {
	
	private static ImportPersonTransferService importPersonTransferService = (ImportPersonTransferService) SpringBeanUtil.getBean("importPersonTransferService");

	/**
	 * 人员信息入库
	 * @param checkPersonInfoOid
	 * @throws ServiceException
	 */
	public static void transferPersonInfo(Long checkPersonInfoOid) throws ServiceException {
		importPersonTransferService.transfer(checkPersonInfoOid);
	}
}
