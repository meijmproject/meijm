
package com.yh.hr.orghc.unit.transfer.bt;

import java.util.List;

import com.yh.hr.orghc.ub.dto.UbLeaderDTO;
import com.yh.hr.orghc.ub.service.UbLeaderService;
import com.yh.hr.orghc.ut.bo.BizUtLeader;
import com.yh.hr.orghc.ut.dto.BizUtUnitDTO;
import com.yh.hr.orghc.ut.service.BizUtUnitService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * @desc 基础领导职数信息转业务表
 * @author xiongyx
 * @createDate 2016-12-20
 */
public class BtUtLeaderTransferService implements InfoTransferService {
	private UbLeaderService ubLeaderService = (UbLeaderService)SpringBeanUtil.getBean("ubLeaderService");
	private BizUtUnitService bizUtUnitService = (BizUtUnitService)SpringBeanUtil.getBean("bizUtUnitService");
	/*
	 * (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long, java.lang.Long)
	 */
	public void transfer(Long taskOid, Long unitOid) throws ServiceException {
		List<UbLeaderDTO> leaderList = ubLeaderService.listByUnitOid(unitOid);
		if (CollectionUtils.isNotEmpty(leaderList)) {
			BizUtUnitDTO bizUtUnitDTO = bizUtUnitService.findBizUtUnitByTaskOid(taskOid);
				for (UbLeaderDTO dto : leaderList) {
					BizUtLeader bizUtLeader = new BizUtLeader();
					BeanHelper.copyProperties(dto, bizUtLeader);
					bizUtLeader.setUtUnitOid(bizUtUnitDTO.getUtUnitOid());
					bizUtLeader.setPreCount(dto.getCurCount());
					bizUtLeader.setChgCount((long)0);
					bizUtLeader.setCreateBy(UserContext.getLoginUserID());
					bizUtLeader.setCreateName(UserContext.getLoginUserName());
					bizUtLeader.setCreateDate(DateUtil.now());
					bizUtLeader.save();
				}
		}
	}

}
