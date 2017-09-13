package com.yh.hr.orghc.unit.unitchildcreate.facade;

import java.util.List;

import com.yh.hr.orghc.unit.unitchildcreate.dto.UnitChildCreateDTO;
import com.yh.hr.orghc.unit.unitchildcreate.service.info.UnitChildCreateService;
import com.yh.hr.res.sao.unit.SaoUserUnitAuthorizationService;
import com.yh.hr.res.unit.dto.UtUnitDTO;
import com.yh.hr.res.unit.service.UtUnitService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.StringMap;

/**
 * 下级单位创建 Facade
 * @author zhengdr
 *
 * 时间:2016-12-22下午02:58:44
 */
public class UnitChildCreateFacade {

	private UnitChildCreateService unitChildCreateService;
	private SaoUserUnitAuthorizationService saoUserUnitAuthorizationService;
	private UtUnitService utUnitService;
	
	public void setUnitChildCreateService(
			UnitChildCreateService unitChildCreateService) {
		this.unitChildCreateService = unitChildCreateService;
	}
	
	public void setSaoUserUnitAuthorizationService(
			SaoUserUnitAuthorizationService saoUserUnitAuthorizationService) {
		this.saoUserUnitAuthorizationService = saoUserUnitAuthorizationService;
	}


	public void setUtUnitService(UtUnitService utUnitService) {
		this.utUnitService = utUnitService;
	}
	

	/**
	 * 得到下级单位信息
	 * @param utUnitOid
	 * @return
	 * @throws ServiceException
	 */
	public UnitChildCreateDTO get(Long utUnitOid)throws ServiceException {	
		
		return unitChildCreateService.get(utUnitOid);
	}
	
	/**
	 * 修改
	 * @param unitChildCreateDTO
	 * @throws ServiceException
	 */
	public void update(UnitChildCreateDTO unitChildCreateDTO)throws ServiceException {	
		
		unitChildCreateService.update(unitChildCreateDTO);
	}
	
	/**
	 * 查找用户授权的单位列表信息
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	public List<UtUnitDTO> findUserAuthedUnitList(String userId,StringMap params) throws ServiceException {		
		List<String> unit = saoUserUnitAuthorizationService.findAuthorityList(userId);
		return utUnitService.findUnitListByAuth(unit,params);
	}
	
	/**
	 * 查找单位列表信息
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	public List<UtUnitDTO> findUnitList(StringMap params) throws ServiceException {		
		return utUnitService.findUnitList(params);
	}
}
