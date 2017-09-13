
package com.yh.hr.orghc.unit.transfer.bt;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.orghc.ub.dto.UbOrgDTO;
import com.yh.hr.orghc.ub.service.UbOrgService;
import com.yh.hr.orghc.ut.bo.BizUtOrg;
import com.yh.hr.orghc.ut.dto.BizUtUnitDTO;
import com.yh.hr.orghc.ut.service.BizUtUnitService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * @desc 基础内设机构信息转业务表
 * @author xiongyx
 * @createDate 2016-12-20
 */
public class BtUtOrgTransferService implements InfoTransferService {
	private UbOrgService ubOrgService = (UbOrgService)SpringBeanUtil.getBean("ubOrgService");
	private BizUtUnitService bizUtUnitService = (BizUtUnitService)SpringBeanUtil.getBean("bizUtUnitService");
	/*
	 * (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long, java.lang.Long)
	 */
	public void transfer(Long taskOid, Long unitOid) throws ServiceException {
		List<UbOrgDTO> ubOrgDTOs = ubOrgService.listByUnitOid(unitOid);
		if (CollectionUtils.isNotEmpty(ubOrgDTOs)) {
			BizUtUnitDTO bizUtUnitDTO = bizUtUnitService.findBizUtUnitByTaskOid(taskOid);
				for (UbOrgDTO dto : ubOrgDTOs) {
					BizUtOrg bizUtOrg = new BizUtOrg();
					BeanHelper.copyProperties(dto, bizUtOrg);
					bizUtOrg.setUtUnitOid(bizUtUnitDTO.getUtUnitOid());
					bizUtOrg.setCreatedByCode(UserContext.getLoginUserID());
					bizUtOrg.setCreatedByName(UserContext.getLoginUserName());
					bizUtOrg.setCreatedDate(DateUtil.now());
					bizUtOrg.save();
				}
		}
	}

}
