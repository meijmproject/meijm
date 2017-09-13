package com.yh.hr.orghc.unit.unitinfomaintain.service.info;

import com.yh.hr.orghc.unit.transfer.bt.BtGtPostTransferService;
import com.yh.hr.orghc.unit.transfer.bt.BtUtHcTransferService;
import com.yh.hr.orghc.unit.transfer.bt.BtUtLeaderTransferService;
import com.yh.hr.orghc.unit.transfer.bt.BtUtOrgTransferService;
import com.yh.hr.orghc.unit.transfer.bt.BtUtUnitTransferService;
import com.yh.hr.orghc.unit.unitinfomaintain.dto.UnitInfoMaintainDTO;
import com.yh.hr.orghc.ut.dto.BizUtUnitDTO;
import com.yh.hr.orghc.ut.service.BizUtUnitService;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * Service
 * 
 * @author xiongyx
 * 
 *         时间:2016-11-3下午04:49:01
 */
public class UnitInfoMaintainService {

	private BtUtUnitTransferService 		btUtUnitTransferService 	= (BtUtUnitTransferService)SpringBeanUtil.getBean("btUtUnitTransferService");	
	private BtUtHcTransferService 			btUtHcTransferService 		= (BtUtHcTransferService)SpringBeanUtil.getBean("btUtHcTransferService");
	private BtUtLeaderTransferService 		btUtLeaderTransferService 	= (BtUtLeaderTransferService)SpringBeanUtil.getBean("btUtLeaderTransferService");
	private BtUtOrgTransferService btUtOrgTransferService 		= (BtUtOrgTransferService)SpringBeanUtil.getBean("btUtOrgTransferService");
	private BizUtUnitService 				bizUtUnitService 			= (BizUtUnitService)SpringBeanUtil.getBean("bizUtUnitService");
	private BtGtPostTransferService 		btGtPostTransferService 			= (BtGtPostTransferService)SpringBeanUtil.getBean("btGtPostTransferService");


	/**
	 * 创建业务
	 */
	public void save(UnitInfoMaintainDTO unitInfoMaintainDTO)
			throws ServiceException {

		Long taskOid = unitInfoMaintainDTO.getTaskOid();
		Long unitOid = unitInfoMaintainDTO.getUnitOid();
		
		btUtUnitTransferService.transfer(taskOid, unitOid);
		btUtHcTransferService.transfer(taskOid, unitOid);
		btUtLeaderTransferService.transfer(taskOid, unitOid);
		btUtOrgTransferService.transfer(taskOid, unitOid);
		
		BizUtUnitDTO bizUtUnitDTO = bizUtUnitService.findBizUtUnitByTaskOid(taskOid);
		if(DicConstants.YHRS0090_104.equals(bizUtUnitDTO.getUnitKind())){
			btGtPostTransferService.transfer(taskOid, unitOid);
		}
		unitInfoMaintainDTO.setUtUnitOid(bizUtUnitDTO.getUtUnitOid());
	}

}
