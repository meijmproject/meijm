
package com.yh.hr.orghc.unit.transfer.tb;

import java.util.List;

import com.yh.hr.orghc.ub.dto.UbOrgDTO;
import com.yh.hr.orghc.ub.service.UbOrgService;
import com.yh.hr.orghc.ut.dto.BizUtUnitDTO;
import com.yh.hr.orghc.ut.service.BizOrgInfoService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.orghc.ut.dto.BizUtOrgDTO;
import com.yh.hr.orghc.ut.service.BizUtUnitService;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * @desc 转基础内设机构信息
 * @author xiongyx
 * @createDate 2016-12-20
 */
public class TbUtOrgTransferService implements InfoTransferService {
	private UbOrgService ubOrgService = (UbOrgService)SpringBeanUtil.getBean("ubOrgService");
	private BizOrgInfoService bizOrgInfoService = (BizOrgInfoService)SpringBeanUtil.getBean("bizOrgInfoService");
	private BizUtUnitService bizUtUnitService = (BizUtUnitService)SpringBeanUtil.getBean("bizUtUnitService");
		
	/*
	 * (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long, java.lang.Long)
	 */
	public void transfer(Long utUnitOid, Long unitOid) throws ServiceException {
		List<BizUtOrgDTO> bizUtOrgDTO = bizOrgInfoService.list(utUnitOid,null);
		BizUtUnitDTO bizUtUnitDTO = bizUtUnitService.findBizUtUnitById(utUnitOid);
		if (CollectionUtils.isNotEmpty(bizUtOrgDTO)) {
				for (BizUtOrgDTO dto : bizUtOrgDTO) {
					UbOrgDTO ubOrgDTO = new UbOrgDTO();
					BeanHelper.copyProperties(dto, ubOrgDTO);
					ubOrgDTO.setUnitOid(unitOid);
					if(null != dto.getOrgOid()){
						if(DicConstants.YHRS0102_1.equals(dto.getOrgType())){
							ubOrgDTO.setLevelCode(bizUtUnitDTO.getLevelCode());
						}
						if(DicConstants.YHRS0102_3.equals(dto.getOrgType())){
							ubOrgDTO.setLevelCode(bizUtUnitDTO.getInternalOrgLevel());
						}
						ubOrgService.updateOrgInfo(ubOrgDTO);
					}else{
						ubOrgDTO.setUnitOid(unitOid);
						Long orgOid =ubOrgService.createOrgInfo(ubOrgDTO);
						dto.setOrgOid(orgOid);
					}
				}
		}
	}

}
