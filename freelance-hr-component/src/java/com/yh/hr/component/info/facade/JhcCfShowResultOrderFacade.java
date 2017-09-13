package com.yh.hr.component.info.facade;

import java.util.List;
import java.util.Map;

import com.yh.hr.res.cf.dto.JhcCfShowResultOrderDTO;
import com.yh.hr.res.cf.service.JhcCfShowResultOrderService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.web.UserContext;

public class JhcCfShowResultOrderFacade {

	private JhcCfShowResultOrderService jhcCfShowResultOrderService;
	
	public void setJhcCfShowResultOrderService(JhcCfShowResultOrderService jhcCfShowResultOrderService) {
		this.jhcCfShowResultOrderService = jhcCfShowResultOrderService;
	}
	
	/**
	 * 查询用户保存的排序
	 * @param userId
	 * @param functionCode
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String,String>> findSortsColumnsByUser(String userId, String functionCode) throws ServiceException {
		return jhcCfShowResultOrderService.find(userId, functionCode);
	}

	/**
	 * 保存用户对表格进行的排序
	 * @param list
	 */
	public void saveSortFields(List<JhcCfShowResultOrderDTO> list, String functionCode) throws ServiceException {
		jhcCfShowResultOrderService.delete(UserContext.getLoginUserID(),functionCode);
		for(JhcCfShowResultOrderDTO dto: list) {
			jhcCfShowResultOrderService.save(dto);
		}
	}
}
