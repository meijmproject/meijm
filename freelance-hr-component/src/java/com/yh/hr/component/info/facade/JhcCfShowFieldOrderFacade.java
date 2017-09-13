package com.yh.hr.component.info.facade;

import java.util.List;
import java.util.Map;

import com.yh.hr.res.cf.dto.JhcCfShowFieldOrderDTO;
import com.yh.hr.res.cf.service.JhcCfShowFieldOrderService;
import com.yh.platform.core.exception.ServiceException;


public class JhcCfShowFieldOrderFacade {

	private JhcCfShowFieldOrderService jhcCfShowFieldOrderService;

	public void setJhcCfShowFieldOrderService(JhcCfShowFieldOrderService jhcCfShowFieldOrderService) {
		this.jhcCfShowFieldOrderService = jhcCfShowFieldOrderService;
	}

	/**
	 * 批量保存（更新）
	 * @param list
	 * @throws ServiceException 
	 */
	public void saveOrderFields(List<JhcCfShowFieldOrderDTO> list) throws ServiceException {
		for(JhcCfShowFieldOrderDTO dto: list) {
			jhcCfShowFieldOrderService.delete(dto);
			jhcCfShowFieldOrderService.save(dto);
		}
	}
	
	/**
	 * 查找当前登陆人设置的表格列
	 * @param functionCode 
	 * @param loginUserID
	 * @return
	 * @throws ServiceException 
	 */
	public List<Map<String,String>> findFieldOrderColumnsByUser(String userId, String functionCode) throws ServiceException {
		return jhcCfShowFieldOrderService.find(userId,functionCode);
	}
}
