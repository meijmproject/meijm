/**
 * 
 */
package com.yh.hr.component.transfer.comm.bt;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.pb.dto.PbPersonInfoDTO;
import com.yh.hr.res.pb.service.PbPersonInfoService;
import com.yh.hr.res.pt.dto.PtPersonDTO;
import com.yh.hr.res.pt.service.PtPersonService;
import com.yh.hr.res.unit.service.UtUnitService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;


/**
 * @desc  创建人员业务信息表
 * @author luqy
 * @createDate 2016-11-4上午10:05:18
 */
public class BtPtPersonTransferService implements InfoTransferService {
	
	private PbPersonInfoService pbPersonInfoService = (PbPersonInfoService)SpringBeanUtil.getBean("pbPersonInfoService");
	private PtPersonService ptPersonService = (PtPersonService)SpringBeanUtil.getBean("ptPersonService");
	private UtUnitService utUnitService = (UtUnitService)SpringBeanUtil.getBean("utUnitService");

	
	public void transfer(Long taskOid, Long personOid) throws ServiceException {
		PbPersonInfoDTO pbPersonInfoDTO = pbPersonInfoService.getPbPersonInfoDTOById(personOid);
		if(pbPersonInfoDTO != null){
			PtPersonDTO ptPersonDTO = BeanHelper.copyRtnProperties(pbPersonInfoDTO, new PtPersonDTO());
			if(ptPersonDTO != null){
				//修改的部分(原本为null)
				//ptPersonDTO.setPersonOid(personOid); 
				ptPersonDTO.setTaskOid(taskOid);
				
				ptPersonDTO.setUnitName(utUnitService.getUnitName(ptPersonDTO.getUnitOid()));
				String uid = UserContext.getLoginUserID();
				String uname = UserContext.getLoginUserName();
				ptPersonDTO.setCreatedByCode(uid);
				ptPersonDTO.setCreatedByName(uname);
				ptPersonDTO.setCreatedDate(DateUtil.now());
				ptPersonService.addPersonInfo(ptPersonDTO);
			}
		}
	}

}
