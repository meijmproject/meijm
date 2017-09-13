/**
 * 
 */
package com.yh.hr.component.transfer.comm.bt;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.bt.bo.BtTask;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pb.bo.PbPersonInfo;
import com.yh.hr.res.pt.bo.PtPersonAttach;
import com.yh.hr.res.pt.dto.PtPersonDTO;
import com.yh.hr.res.pt.dto.PtPersonInDTO;
import com.yh.hr.res.pt.service.PtPersonInService;
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
 * @desc   创建交流或变动业务信息表
 * @author luqy
 * @createDate 2016-11-4上午10:05:18
 */
public class BtPtPersonInTransferService implements InfoTransferService {
	
	private PtPersonInService ptPersonInService = (PtPersonInService)SpringBeanUtil.getBean("ptPersonInService");
	private PtPersonService ptPersonService = (PtPersonService)SpringBeanUtil.getBean("ptPersonService");
	private UtUnitService utUnitService = (UtUnitService)SpringBeanUtil.getBean("utUnitService");
	
	public void transfer(Long taskOid, Long personOid) throws ServiceException {
		if(NumberUtils.isNotNullOrZero(taskOid)){
			BtTask btTask = DaoUtil.get(BtTask.class, taskOid);
			if(btTask != null){
				PtPersonDTO ptPersonDTO = ptPersonService.getByTaskOid(taskOid);
				if(ptPersonDTO != null && NumberUtils.isNotNullOrZero(personOid)){
					PtPersonInDTO ptPersonInDto = new PtPersonInDTO();
					ptPersonInDto.setBizPersonOid(ptPersonDTO.getBizPersonOid());
					
					//人员基础信息
					PbPersonInfo pbPersonInfo = DaoUtil.get(PbPersonInfo.class,personOid);
					if(pbPersonInfo != null){
						ptPersonInDto.setPreUnitOid(pbPersonInfo.getUnitOid());
						ptPersonInDto.setPreUnitName(utUnitService.getUnitName(pbPersonInfo.getUnitOid()));
						ptPersonInDto.setPreDeptOid(pbPersonInfo.getDeptOid());
//			gg			ptPersonInDto.setPreDeptName(pbPersonInfo.getDeptName());
						ptPersonInDto.setPrePersonType(pbPersonInfo.getPersonType());
						ptPersonInDto.setPreBankpoll(pbPersonInfo.getBankpoll());
//			gg			ptPersonInDto.setPreDPositionType(pbPersonInfo.getdPositionType());
						UtUnitDTO adminUnitDTO = utUnitService.getAdminUnit(pbPersonInfo.getUnitOid()); 
						if(adminUnitDTO != null){
							ptPersonInDto.setPreUnitAdminOid(adminUnitDTO.getUnitOid());
							ptPersonInDto.setPreUnitAdminName(adminUnitDTO.getUnitName());
						}
						UtUnitDTO utUnitDTO = utUnitService.get(pbPersonInfo.getUnitOid());
						if(utUnitDTO != null){
							ptPersonInDto.setPreUnitType(utUnitDTO.getUnitKind());
						}
						
						
						//人员职务信息
						PtPersonAttach ptPersonAttach = DaoUtil.get(PtPersonAttach.class, ptPersonDTO.getBizPersonOid());
						if(ptPersonAttach != null){
							ptPersonInDto.setPreDutyLevel(ptPersonAttach.getAdministrativeDutyLevel());
							ptPersonInDto.setPreDutyLevelName(DicHelper.viewName(DicConstants.YHRS0015, ptPersonAttach.getAdministrativeDutyLevel()));
							ptPersonInDto.setPreDutyName(ptPersonAttach.getAdministrativeDuty());
							ptPersonInDto.setPreDutyAttribute(ptPersonAttach.getAdministrativeDutyAttribute());
							ptPersonInDto.setPreDutyDate(ptPersonAttach.getAdministrativeDutyDate());
							ptPersonInDto.setPreDuteLevelDate(ptPersonAttach.getAdministrativeStartDate());
						}
						
						ptPersonInDto.setPreUnitType(DicConstants.YHRS0106_11);
						String uid = UserContext.getLoginUserID();
						String uname = UserContext.getLoginUserName();
						ptPersonInDto.setCreatedByCode(uid);
						ptPersonInDto.setCreatedByName(uname);
						ptPersonInDto.setCreatedDate(DateUtil.now());
						ptPersonInService.createPtPersonIn(ptPersonInDto);
					}
				}
			}
		}
	}

}
