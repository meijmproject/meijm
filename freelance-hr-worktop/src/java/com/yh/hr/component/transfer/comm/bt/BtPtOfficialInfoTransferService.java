/**
 * 
 */
package com.yh.hr.component.transfer.comm.bt;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.pb.dto.PbPersonInfoDTO;
import com.yh.hr.res.pb.service.PbPersonInfoService;
import com.yh.hr.res.pt.bo.PtOfficialInfo;
import com.yh.hr.res.pt.dto.PtPersonDTO;
import com.yh.hr.res.pt.service.PtPersonService;
import com.yh.hr.res.unit.service.UtUnitService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;


/**
 * @desc 创建公务员登记业务信息
 * @author luqy
 * @createDate 2016-11-12上午10:26:56
 */
public class BtPtOfficialInfoTransferService implements InfoTransferService {
	
	private PbPersonInfoService pbPersonInfoService = (PbPersonInfoService)SpringBeanUtil.getBean("pbPersonInfoService");
	private PtPersonService ptPersonService = (PtPersonService)SpringBeanUtil.getBean("ptPersonService");
	@SuppressWarnings("unused")
	private UtUnitService utUnitService = (UtUnitService)SpringBeanUtil.getBean("utUnitService");

	
	public void transfer(Long taskOid, Long personOid) throws ServiceException {
		PbPersonInfoDTO pbPersonInfoDTO = pbPersonInfoService.getPbPersonInfoDTOById(personOid);
		if(pbPersonInfoDTO != null){
			PtPersonDTO ptPersonDTO = ptPersonService.getByTaskOid(taskOid);
			if(ptPersonDTO != null){
				PtOfficialInfo ptOfficialInfo = new PtOfficialInfo();
				ptOfficialInfo.setBizPersonOid(ptPersonDTO.getBizPersonOid());
//				ptOfficialInfo.setEnrolDate(pbPersonInfoDTO.getEnterDate()); //登记日期
//				ptOfficialInfo.setEnterMode(pbPersonInfoDTO.getEnterMode()); //登记方式
//				ptOfficialInfo.setIsEnrol(pbPersonInfoDTO.getIsEnrol()); //登记标志
//				if(NumberUtils.isNotNullOrZero(pbPersonInfoDTO.getUnitOid())){
//					ptOfficialInfo.setPreEnterUnit(StringUtil.isNotBlank(pbPersonInfoDTO.getUnitName()) ? pbPersonInfoDTO.getUnitName() : utUnitService.getUnitName(pbPersonInfoDTO.getUnitOid()));
//				}
				ptOfficialInfo.setPreEnterOfficialType(pbPersonInfoDTO.getPersonType());
				ptOfficialInfo.save();
			}
		}
	}

}
