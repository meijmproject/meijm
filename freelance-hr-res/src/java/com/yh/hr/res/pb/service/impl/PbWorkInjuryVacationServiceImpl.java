package com.yh.hr.res.pb.service.impl;

import com.yh.hr.res.pb.dto.PbWorkInjuryVacationDTO;
import com.yh.hr.res.pb.service.PbWorkInjuryVacationService;
import jade.workflow.utils.DateUtil;

import com.yh.hr.res.pb.bo.PbWorkInjuryVacation;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.web.UserContext;
/**
 * 工伤假基础信息service实现类
 * @author wangx
 * @date 2017-05-22
 * @version 1.0
 */
public class PbWorkInjuryVacationServiceImpl implements
		PbWorkInjuryVacationService {

	/**
	 * 创建工伤假基础信息
	 * @param pbWorkInjuryVacationDTO
	 * @return
	 * @throws ServiceException
	 */
	public Long create(PbWorkInjuryVacationDTO pbWorkInjuryVacationDTO)
			throws ServiceException {
		PbWorkInjuryVacation pbWorkInjuryVacation = new PbWorkInjuryVacation();
		BeanHelper.copyProperties(pbWorkInjuryVacationDTO, pbWorkInjuryVacation);
		pbWorkInjuryVacation.setCreateBy(UserContext.getLoginUserID());
		pbWorkInjuryVacation.setCreateName(UserContext.getLoginUserName());
		pbWorkInjuryVacation.setCreateDate(DateUtil.now());
		pbWorkInjuryVacation.setUpdateBy(UserContext.getLoginUserID());
		pbWorkInjuryVacation.setUpdateName(UserContext.getLoginUserName());
		pbWorkInjuryVacation.setUpdateDate(DateUtil.now());
		pbWorkInjuryVacation.save();
		return pbWorkInjuryVacation.getVacationOid();
	}

	/**
	 * 修改工伤假基础信息
	 * @param pbWorkInjuryVacationDTO
	 * @throws ServiceException
	 */
	public void update(PbWorkInjuryVacationDTO pbWorkInjuryVacationDTO)
			throws ServiceException {
		PbWorkInjuryVacation pbWorkInjuryVacation = DaoUtil.get(PbWorkInjuryVacation.class, pbWorkInjuryVacationDTO.getVacationOid());
		if(pbWorkInjuryVacation!=null) {
			BeanHelper.copyProperties(pbWorkInjuryVacationDTO, pbWorkInjuryVacation, BeanHelper.getNullPropertyNames(pbWorkInjuryVacationDTO));
			pbWorkInjuryVacation.setUpdateBy(UserContext.getLoginUserID());
			pbWorkInjuryVacation.setUpdateName(UserContext.getLoginUserName());
			pbWorkInjuryVacation.setUpdateDate(DateUtil.now());
			pbWorkInjuryVacation.update();
		}
	}

	/**
	 * 通过主键OID删除工伤假基础信息
	 * @param vacationOid
	 * @throws ServiceException
	 */
	public void delete(Long vacationOid) throws ServiceException {
		PbWorkInjuryVacation pbWorkInjuryVacation = DaoUtil.get(PbWorkInjuryVacation.class, vacationOid);
		if(pbWorkInjuryVacation!=null) {
			pbWorkInjuryVacation.delete();
		}
	}

	/**
	 * 通过主键OID获取工伤假基础信息
	 * @param vacationOid
	 * @return
	 * @throws ServiceException
	 */
	public PbWorkInjuryVacationDTO get(Long vacationOid)
			throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PbWorkInjuryVacation.class, vacationOid), PbWorkInjuryVacationDTO.class);
	}

}
