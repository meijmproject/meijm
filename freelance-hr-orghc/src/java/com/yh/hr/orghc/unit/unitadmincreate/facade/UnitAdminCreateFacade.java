package com.yh.hr.orghc.unit.unitadmincreate.facade;

import java.util.List;

import com.yh.admin.dto.UserSystemPositionDTO;
import com.yh.admin.users.queryhelper.UserSystemPositionQueryHelper;
import com.yh.hr.orghc.unit.unitadmincreate.dto.UnitAdminCreateDTO;
import com.yh.hr.orghc.unit.unitadmincreate.service.info.UnitAdminCreateService;
import com.yh.platform.core.exception.ServiceException;

/**
 * 主管单位创建  Facade
 * @author zhengdr
 *
 * 时间:2016-12-20下午03:06:48
 */
public class UnitAdminCreateFacade {

	private UnitAdminCreateService unitAdminCreateService;
	
	public void setUnitAdminCreateService(
			UnitAdminCreateService unitAdminCreateService) {
		this.unitAdminCreateService = unitAdminCreateService;
	}

	/**
	 * 得到主管单位信息
	 * @param utUnitOid
	 * @return
	 * @throws ServiceException
	 */
	public UnitAdminCreateDTO get(Long utUnitOid)throws ServiceException {	
		
		return unitAdminCreateService.get(utUnitOid);
	}
	
	/**
	 * 修改
	 * @param unitAdminCreateDTO
	 * @throws ServiceException
	 */
	public void update(UnitAdminCreateDTO unitAdminCreateDTO)throws ServiceException {	
		
		unitAdminCreateService.update(unitAdminCreateDTO);
	}
	
	/**
	 * 得到岗位信息
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	public List<UserSystemPositionDTO> listUserSystemPosition(String userId)throws ServiceException {	
		
		return UserSystemPositionQueryHelper.listByUserId(userId);
	}
}
