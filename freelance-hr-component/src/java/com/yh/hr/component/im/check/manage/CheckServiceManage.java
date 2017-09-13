package com.yh.hr.component.im.check.manage;

import java.util.ArrayList;
import java.util.List;

import com.yh.hr.component.im.check.factory.ImportCheckFactory;
import com.yh.hr.component.im.dto.CheckColumnDTO;
import com.yh.hr.component.im.dto.CheckResultDTO;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.component.im.check.service.CollItemCheckService;
import com.yh.hr.res.im.dto.ImCollectTemplateDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 导入数据检查service管理类
 * @author wangx
 * @date 2017-07-11
 * @version 1.0
 */
public class CheckServiceManage {

	/**
	 * 导入前数据检查
	 * @param dataList
	 * @param checkType
	 * @param collList
	 * @throws ServiceException
	 */
	public static List<CheckResultDTO> check(List<CheckColumnDTO> dataList, List<ImCollectTemplateDTO> collList) throws ServiceException {
		List<CheckResultDTO> resultDTOList = new ArrayList<CheckResultDTO>();
		if(CollectionUtils.isNotEmpty(dataList)) {
			for(CheckColumnDTO dto : dataList) {
				CollItemCheckService collItemCheckService = ImportCheckFactory.getCollItemCheckService(dto.getCheckType());
				if(collItemCheckService==null) {
					throw new ServiceException(null,"该检查类service没有配置！");
				}
				CheckResultDTO resultDTO = collItemCheckService.check(dto,collList);
				if(resultDTO!=null) {
					resultDTOList.add(resultDTO);
				}
			}
			return resultDTOList;
		}
		return null;
	}
}
