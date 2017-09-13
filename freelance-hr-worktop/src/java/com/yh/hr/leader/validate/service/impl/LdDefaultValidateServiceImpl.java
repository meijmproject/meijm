/**
 * 
 */
package com.yh.hr.leader.validate.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yh.hr.leader.validate.dto.LdCashCheckDTO;
import com.yh.hr.leader.validate.service.LdValidateAbstractService;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.bo.PtPositioningInfo;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.queryhelper.PtPositioningInfoQueryHelper;
import com.yh.platform.core.exception.ServiceException;

/**
 * 上报前领导职数验证 默认现实
 * @author	zhangqp
 * @version	1.0,	16/11/01
 */

public class LdDefaultValidateServiceImpl extends LdValidateAbstractService {
	
	/**
	 * 获取业务参数数据
	 * @param taskOids
	 * @return
	 * @throws ServiceException
	 */
	protected List<LdCashCheckDTO> getCheckData(List<Long> taskOids) throws ServiceException {
		List<LdCashCheckDTO> ldCashCheckDTOs = new ArrayList<LdCashCheckDTO>();
		
		for (Long taskOid : taskOids) {
			PtPerson ptPerson = PtPersonQueryHelper.getByTaskOid(taskOid);
			PtPositioningInfo ptPositioningInfo = PtPositioningInfoQueryHelper.getByBizPersonOid(ptPerson.getBizPersonOid());
			
			if (ptPositioningInfo == null 
//					|| StringUtils.isEmpty(ptPositioningInfo.getDutyAttribute()) 
					|| StringUtils.isEmpty(ptPositioningInfo.getDutyLevel())) {
				throw new ServiceException(null, "拟任职信息不完整");
			}
			
			if (!needCheck(ptPositioningInfo.getDutyAttribute(), ptPositioningInfo.getDutyLevel())) {
				continue;
			}
			
			//该职务属性、级别是否需要验证
			
			LdCashCheckDTO ldCashCheckDTO = getLdCashCheckDTO(ldCashCheckDTOs, ptPositioningInfo.getDutyUnitOid(), ptPositioningInfo.getDutyAttribute(), ptPositioningInfo.getDutyLevel());
			
			if (ldCashCheckDTO == null) {
				ldCashCheckDTO = new LdCashCheckDTO();
				ldCashCheckDTO.setUnitOid(ptPositioningInfo.getDutyUnitOid());
				ldCashCheckDTO.setUnitName(ptPositioningInfo.getDutyUnitName());
				ldCashCheckDTO.setDutyAttribute(ptPositioningInfo.getDutyAttribute());
				ldCashCheckDTO.setDutyAttributeName(DicHelper.viewName(DicConstants.YHRS0028, ptPositioningInfo.getDutyAttribute()));
				ldCashCheckDTO.setDutyLevel(ptPositioningInfo.getDutyLevel());
				ldCashCheckDTO.setDutyLevelName(DicHelper.viewName(DicConstants.YHRS0015, ptPositioningInfo.getDutyLevel()));
				
				ldCashCheckDTOs.add(ldCashCheckDTO);
			}
			
			ldCashCheckDTO.setCheckCount(ldCashCheckDTO.getCheckCount()+1);
		}
		return ldCashCheckDTOs;
	}
	
	/**
	 * 是否需要检查
	 * @param dutyAttribute
	 * @param dutyLevel
	 * @return
	 * @throws ServiceException
	 */
	private boolean needCheck(String dutyAttribute, String dutyLevel) throws ServiceException {
	
		//领导职务才验证
		return !(StringUtils.isEmpty(dutyAttribute) || ArrayUtils.contains(new String[]{DicConstants.YHRS0028_31, DicConstants.YHRS0028_9}, dutyAttribute));
	}

	/**
	 * 查找相同参数
	 * @param ldCashCheckDTOs
	 * @param unitOid
	 * @param dutyAttribute
	 * @param dutyLevel
	 * @return
	 */
	private LdCashCheckDTO getLdCashCheckDTO(List<LdCashCheckDTO> ldCashCheckDTOs, Long unitOid, String dutyAttribute, String dutyLevel) {
		
		for (LdCashCheckDTO dto : ldCashCheckDTOs) {
			if (dto.getUnitOid().equals(unitOid)
					&& dto.getDutyAttribute().equals(dutyAttribute)
					&& dto.getDutyLevel().equals(dutyLevel)) {
				return dto;
			}
		}
		
		return null;
	}
}
