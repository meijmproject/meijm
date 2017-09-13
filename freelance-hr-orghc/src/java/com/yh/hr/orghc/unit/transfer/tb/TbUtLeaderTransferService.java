
package com.yh.hr.orghc.unit.transfer.tb;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.orghc.ub.dto.UbLeaderDTO;
import com.yh.hr.orghc.ub.service.UbLeaderService;
import com.yh.hr.orghc.ut.dto.BizUtLeaderDTO;
import com.yh.hr.orghc.ut.service.BizLeaderInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * @desc 业务表转 基础领导职数信息
 * @author xiongyx
 * @createDate 2016-12-20
 */
public class TbUtLeaderTransferService implements InfoTransferService {
	private UbLeaderService ubLeaderService = (UbLeaderService)SpringBeanUtil.getBean("ubLeaderService");
	private BizLeaderInfoService bizLeaderInfoService = (BizLeaderInfoService)SpringBeanUtil.getBean("bizLeaderInfoService");
		
	/*
	 * (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long, java.lang.Long)
	 */
	public void transfer(Long utUnitOid, Long unitOid) throws ServiceException {
		List<BizUtLeaderDTO> bizUtLeaderDTO = bizLeaderInfoService.list(utUnitOid);
		if (CollectionUtils.isNotEmpty(bizUtLeaderDTO)) {
				for (BizUtLeaderDTO dto : bizUtLeaderDTO) {
					UbLeaderDTO ubLeaderDTO = new UbLeaderDTO();
					BeanHelper.copyProperties(dto, ubLeaderDTO);
					ubLeaderDTO.setUnitOid(unitOid);
					Long changeConut = ubLeaderDTO.getChgCount();
					ubLeaderDTO.setChgCount((long)0);
					ubLeaderDTO.setPreCount(ubLeaderDTO.getCurCount());
					if(null != dto.getLeaderOid()){
						ubLeaderService.updateLeaderInfo(ubLeaderDTO,changeConut);
					}else{
						ubLeaderDTO.setUnitOid(unitOid);
						ubLeaderService.createLeaderInfo(ubLeaderDTO,changeConut);
					}
				}
		}
	}

}
