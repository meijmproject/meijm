
package com.yh.hr.orghc.unit.transfer.bt;

import java.util.List;

import com.yh.hr.orghc.ub.dto.UbHcDTO;
import com.yh.hr.orghc.ut.dto.BizUtUnitDTO;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.orghc.ub.service.UbHcService;
import com.yh.hr.orghc.ut.bo.BizUtHc;
import com.yh.hr.orghc.ut.service.BizUtUnitService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * @desc 基础编制信息转业务表
 * @author xiongyx
 * @createDate 2016-12-20
 */
public class BtUtHcTransferService implements InfoTransferService {
	private UbHcService ubHcService = (UbHcService)SpringBeanUtil.getBean("ubHcService");
	private BizUtUnitService bizUtUnitService = (BizUtUnitService)SpringBeanUtil.getBean("bizUtUnitService");
		
	/*
	 * (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long, java.lang.Long)
	 */
	public void transfer(Long taskOid, Long unitOid) throws ServiceException {
		List<UbHcDTO> ubHcDTOs = ubHcService.listByUnitOid(unitOid);
		if (CollectionUtils.isNotEmpty(ubHcDTOs)) {
			BizUtUnitDTO bizUtUnitDTO = bizUtUnitService.findBizUtUnitByTaskOid(taskOid);
				for (UbHcDTO dto : ubHcDTOs) {
					BizUtHc bizUtHc = new BizUtHc();
					BeanHelper.copyProperties(dto, bizUtHc);
					bizUtHc.setUtUnitOid(bizUtUnitDTO.getUtUnitOid());
					bizUtHc.setPreCount(dto.getCurCount());
					bizUtHc.setChgCount((long)0);
					bizUtHc.setCreateBy(UserContext.getLoginUserID());
					bizUtHc.setCreateName(UserContext.getLoginUserName());
					bizUtHc.setCreateDate(DateUtil.now());
					bizUtHc.save();
				}
		}
	}

}
