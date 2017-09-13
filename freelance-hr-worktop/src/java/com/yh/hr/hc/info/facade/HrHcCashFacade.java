/**
 * 
 */
package com.yh.hr.hc.info.facade;

import java.util.List;

import com.yh.hr.res.hc.dto.HcCashDTO;
import com.yh.hr.res.hc.service.HcCashService;
import com.yh.hr.res.hc.util.HcFlowConstants;
import com.yh.platform.core.exception.ServiceException;

/**
 * 编制资源查询
 * @author	zhangqp
 * @version	1.0,	16/10/27
 */

public class HrHcCashFacade {

	private HcCashService hcCashService;

	public void setHcCashService(HcCashService hcCashService) {
		this.hcCashService = hcCashService;
	}
	
	/**
	 * 查询编制资源头寸信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<HcCashDTO> findUnitHcCash(Long unitOid, String hcCode, String budgetFromCode) throws ServiceException {
		HcCashDTO dto = new HcCashDTO();
		
		dto.setAccountCode(String.valueOf(unitOid));
		dto.setAccountType(HcFlowConstants.ACCOUNT_TYPE_1);
		dto.setHcCode(hcCode);
		dto.setBudgetFromCode(budgetFromCode);
		
		return hcCashService.findHcCash(dto);
	}
}
