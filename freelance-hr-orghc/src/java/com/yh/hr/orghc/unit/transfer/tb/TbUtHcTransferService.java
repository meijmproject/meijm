
package com.yh.hr.orghc.unit.transfer.tb;

import java.util.List;

import com.yh.hr.orghc.ub.dto.UbHcDTO;
import com.yh.hr.orghc.ub.service.UbHcService;
import com.yh.hr.orghc.ut.dto.BizUtHcDTO;
import com.yh.hr.orghc.ut.service.BizHcInfoService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * @desc 转基础编制信息
 * @author xiongyx
 * @createDate 2016-12-20
 */
public class TbUtHcTransferService implements InfoTransferService {
	private UbHcService ubHcService = (UbHcService)SpringBeanUtil.getBean("ubHcService");
	private BizHcInfoService bizHcInfoService = (BizHcInfoService)SpringBeanUtil.getBean("bizHcInfoService");
		
	/*
	 * (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long, java.lang.Long)
	 */
	public void transfer(Long utUnitOid, Long unitOid) throws ServiceException {
		List<BizUtHcDTO> bizUtHcDTO = bizHcInfoService.list(utUnitOid);
		if (CollectionUtils.isNotEmpty(bizUtHcDTO)) {
				for (BizUtHcDTO dto : bizUtHcDTO) {
					UbHcDTO ubHcDTO = new UbHcDTO();
					BeanHelper.copyProperties(dto, ubHcDTO);
					ubHcDTO.setUnitOid(unitOid);
					Long changeConut = ubHcDTO.getChgCount();
					ubHcDTO.setChgCount((long)0);
					ubHcDTO.setPreCount(ubHcDTO.getCurCount());
					if(null != dto.getHcOid()){
						ubHcService.updateHcInfo(ubHcDTO,changeConut);
					}else{
						ubHcDTO.setUnitOid(unitOid);
						ubHcService.createHcInfo(ubHcDTO,changeConut);
					}
				}
		}
	}

}
