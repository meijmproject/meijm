package com.yh.hr.res.pt.service.impl;

import com.yh.hr.res.pt.bo.PtWorkInjuryVacation;
import jade.workflow.utils.DateUtil;

import com.yh.hr.res.pt.dto.PtWorkInjuryVacationDTO;
import com.yh.hr.res.pt.service.PtWorkInjuryVacationService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.web.UserContext;
/**
 * 工伤假业务信息service实现类
 * @author wangx
 * @date 2017-05-22
 * @version 1.0
 */
public class PtWorkInjuryVacationServiceImpl implements
		PtWorkInjuryVacationService {

	/**
	 * 创建工伤假业务信息
	 * @param ptWorkInjuryVacationDTO
	 * @return
	 * @throws ServiceException
	 */
	public Long create(PtWorkInjuryVacationDTO ptWorkInjuryVacationDTO)
			throws ServiceException {
		PtWorkInjuryVacation ptWorkInjuryVacation = new PtWorkInjuryVacation();
		BeanHelper.copyProperties(ptWorkInjuryVacationDTO, ptWorkInjuryVacation);
		ptWorkInjuryVacation.setCreateBy(UserContext.getLoginUserID());
		ptWorkInjuryVacation.setCreateName(UserContext.getLoginUserName());
		ptWorkInjuryVacation.setCreateDate(DateUtil.now());
		ptWorkInjuryVacation.setUpdateBy(UserContext.getLoginUserID());
		ptWorkInjuryVacation.setUpdateName(UserContext.getLoginUserName());
		ptWorkInjuryVacation.setUpdateDate(DateUtil.now());
		ptWorkInjuryVacation.save();
		return ptWorkInjuryVacation.getVacationOid();
	}

	/**
	 * 修改工伤假业务信息
	 * @param ptWorkInjuryVacationDTO
	 * @throws ServiceException
	 */
	public void update(PtWorkInjuryVacationDTO ptWorkInjuryVacationDTO)
			throws ServiceException {
		PtWorkInjuryVacation ptWorkInjuryVacation = DaoUtil.get(PtWorkInjuryVacation.class, ptWorkInjuryVacationDTO.getVacationOid());
		if(ptWorkInjuryVacation!=null) {
			BeanHelper.copyProperties(ptWorkInjuryVacationDTO, ptWorkInjuryVacation, BeanHelper.getNullPropertyNames(ptWorkInjuryVacationDTO));
			ptWorkInjuryVacation.setUpdateBy(UserContext.getLoginUserID());
			ptWorkInjuryVacation.setUpdateName(UserContext.getLoginUserName());
			ptWorkInjuryVacation.setUpdateDate(DateUtil.now());
			ptWorkInjuryVacation.update();
		}
	}

	/**
	 * 通过主键OID删除工伤假业务信息
	 * @param vacationOid
	 * @throws ServiceException
	 */
	public void delete(Long vacationOid) throws ServiceException {
		PtWorkInjuryVacation ptWorkInjuryVacation = DaoUtil.get(PtWorkInjuryVacation.class, vacationOid);
		if(ptWorkInjuryVacation!=null) {
			ptWorkInjuryVacation.delete();
		}
	}

	/**
	 * 通过主键OID获取工伤假业务信息
	 * @param vacationOid
	 * @return
	 * @throws ServiceException
	 */
	public PtWorkInjuryVacationDTO get(Long vacationOid)
			throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtWorkInjuryVacation.class, vacationOid), PtWorkInjuryVacationDTO.class);
	}

}
