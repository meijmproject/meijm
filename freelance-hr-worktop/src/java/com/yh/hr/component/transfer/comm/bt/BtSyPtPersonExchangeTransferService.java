/**
 * 
 */
package com.yh.hr.component.transfer.comm.bt;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.bt.bo.BtTask;
import com.yh.hr.res.pb.bo.PbPersonInfo;
import com.yh.hr.res.pt.bo.PtPersonExchange;
import com.yh.hr.res.pt.dto.PtPersonDTO;
import com.yh.hr.res.pt.service.PtPersonService;
import com.yh.hr.res.unit.dto.UtUnitDTO;
import com.yh.hr.res.unit.service.UtUnitService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;


/**
 * @desc   创建授权交流业务信息表(事业)
 * @author lihj
 * @createDate 2016-11-25
 */
public class BtSyPtPersonExchangeTransferService implements InfoTransferService {
	
	private PtPersonService ptPersonService = (PtPersonService)SpringBeanUtil.getBean("ptPersonService");
	private UtUnitService utUnitService = (UtUnitService)SpringBeanUtil.getBean("utUnitService");
	
	public void transfer(Long taskOid, Long personOid) throws ServiceException {
		if(NumberUtils.isNotNullOrZero(taskOid)){
			BtTask btTask = DaoUtil.get(BtTask.class, taskOid);
			if(btTask != null){
				PtPersonDTO ptPersonDTO = ptPersonService.getByTaskOid(taskOid);
				if(ptPersonDTO != null && NumberUtils.isNotNullOrZero(personOid)){
					PbPersonInfo pbPersonInfo = DaoUtil.get(PbPersonInfo.class,personOid);
					if(pbPersonInfo != null){
						PtPersonExchange ptPersonExchange = new PtPersonExchange();
						ptPersonExchange.setBizPersonOid(ptPersonDTO.getBizPersonOid());
						ptPersonExchange.setAuthStatusCode("SU600");
						ptPersonExchange.setPrePersonOid(pbPersonInfo.getPersonOid());
						ptPersonExchange.setUnitOid(pbPersonInfo.getUnitOid());
						ptPersonExchange.setUnitName(utUnitService.getUnitName(pbPersonInfo.getUnitOid()));
						ptPersonExchange.setItemCode(btTask.getItemCode());
						UtUnitDTO  utUnitDTO = utUnitService.getAdminUnit(pbPersonInfo.getUnitOid());
						if(utUnitDTO != null){
							ptPersonExchange.setAdminUnitOid(utUnitDTO.getUnitOid());
							ptPersonExchange.setAdminUnitName(utUnitDTO.getUnitName());
						}
						String uid = UserContext.getLoginUserID();
						String uname = UserContext.getLoginUserName();
						ptPersonExchange.setCreatedByCode(uid);
						ptPersonExchange.setCreatedByName(uname);
						ptPersonExchange.setCreatedDate(DateUtil.now());
						ptPersonExchange.save();
					}
				}
			}
		}
	}

}
