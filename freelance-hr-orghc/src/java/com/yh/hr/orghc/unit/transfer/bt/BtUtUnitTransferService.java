/**
 * 
 */
package com.yh.hr.orghc.unit.transfer.bt;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.orghc.ub.bo.UbUnit;
import com.yh.hr.orghc.ub.service.UbUnitService;
import com.yh.hr.orghc.ut.bo.BizUtUnit;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;


/**
 * @desc  创建单位业务信息表
 * @author xiongyx
 * @createDate 2016-12-20
 **/
public class BtUtUnitTransferService implements InfoTransferService {
	
	private UbUnitService ubUnitService = (UbUnitService)SpringBeanUtil.getBean("ubUnitService");
	
	public void transfer(Long taskOid, Long unitOid) throws ServiceException {
		UbUnit ubUnitDTO = ubUnitService.get(unitOid);
		if(ubUnitDTO != null){
			BizUtUnit bizUtUnit = BeanHelper.copyRtnProperties(ubUnitDTO, new BizUtUnit());
			if(bizUtUnit != null){

				bizUtUnit.setTaskOid(taskOid);
				
				String uid = UserContext.getLoginUserID();
				String uname = UserContext.getLoginUserName();
				bizUtUnit.setCreatedByCode(uid);
				bizUtUnit.setCreatedByName(uname);
				bizUtUnit.setCreatedDate(DateUtil.now());
				bizUtUnit.save();
			}
		}
	}

}
