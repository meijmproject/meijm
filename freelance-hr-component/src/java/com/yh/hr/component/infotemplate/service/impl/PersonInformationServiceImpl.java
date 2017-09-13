package com.yh.hr.component.infotemplate.service.impl;

import java.util.List;

import com.yh.hr.component.infotemplate.queryhelper.PersonInformationQueryHelper;
import com.yh.hr.component.infotemplate.service.PersonInformationService;
import org.apache.commons.lang.ArrayUtils;

import com.yh.hr.component.infotemplate.dto.ItLibraryGroupDetailDTO;
import com.yh.hr.res.unit.dto.UtUnitDTO;
import com.yh.platform.core.exception.ServiceException;

public class PersonInformationServiceImpl implements PersonInformationService {
	/**
	 * 机关单位性质集合
	 */
	public static final String[] JG_UNIT_KINDS = new String[]{"101", "102", "103", "105"};
	/**
	 * 事业单位性质集合
	 */
	public static final String[] SY_UNIT_KINDS = new String[]{"104"};
	public List<ItLibraryGroupDetailDTO> findInforList(String functionCode) throws ServiceException {
		return PersonInformationQueryHelper.findInforList(functionCode);
	}

	public String findUnitType(String unitOid) throws ServiceException {
		
		UtUnitDTO utUnitDTO = PersonInformationQueryHelper.findUnitType(unitOid);
        if(ArrayUtils.contains(JG_UNIT_KINDS, utUnitDTO.getUnitKind())){
        	return "VIEW_UNIT_JG";
        }else if(ArrayUtils.contains(SY_UNIT_KINDS, utUnitDTO.getUnitKind())){
        	return "VIEW_UNIT_SY";
        }
		return "";
	}

	public String findUnitTypeForUpdate(String unitOid) throws ServiceException {
		UtUnitDTO utUnitDTO = PersonInformationQueryHelper.findUnitType(unitOid);
        if(ArrayUtils.contains(JG_UNIT_KINDS, utUnitDTO.getUnitKind())){
        	return "UNIT_JG";
        }else if(ArrayUtils.contains(SY_UNIT_KINDS, utUnitDTO.getUnitKind())){
        	return "UNIT_SY";
        }
		return "";
	}

}
