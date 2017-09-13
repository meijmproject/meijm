/**
 * 
 */
package com.yh.hr.orghc.unit.transfer.tb;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.orghc.ub.dto.UbUnitDTO;
import com.yh.hr.orghc.ub.service.UbUnitService;
import com.yh.hr.orghc.ut.dto.BizUtUnitDTO;
import com.yh.hr.orghc.ut.service.BizUtUnitService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.SpringBeanUtil;


/**
 * @desc  单位业务表
 * @author xiongyx
 * @createDate 2016-12-20
 **/
public class TbUtUnitTransferService implements InfoTransferService {
	private BizUtUnitService bizUtUnitService = (BizUtUnitService)SpringBeanUtil.getBean("bizUtUnitService");
	private UbUnitService ubUnitService = (UbUnitService)SpringBeanUtil.getBean("ubUnitService");
	
	public void transfer(Long utUnitOid, Long unitOid) throws ServiceException {
		BizUtUnitDTO bizUtUnitDTO = bizUtUnitService.findBizUtUnitById(utUnitOid);
		if(null != bizUtUnitDTO){
			UbUnitDTO ubUnitDTO = new UbUnitDTO();
			BeanHelper.copyProperties(bizUtUnitDTO, ubUnitDTO);
			if(null != bizUtUnitDTO.getUnitOid()){
				ubUnitService.update(ubUnitDTO);
			}else{				
				ubUnitService.create(ubUnitDTO);
			}
		}
	}

}
