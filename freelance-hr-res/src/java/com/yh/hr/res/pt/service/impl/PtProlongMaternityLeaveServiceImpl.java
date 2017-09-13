package com.yh.hr.res.pt.service.impl;

import com.yh.hr.res.pt.bo.PtProlongMaternityLeave;
import com.yh.hr.res.pt.dto.PtProlongMaternityLeaveDTO;
import com.yh.hr.res.pt.service.PtProlongMaternityLeaveService;
import jade.workflow.utils.DateUtil;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.web.UserContext;
/**
 * 延长产假业务信息service实现类
 * @author wangx
 * @date 2017-05-22
 * @version 1.0
 */
public class PtProlongMaternityLeaveServiceImpl implements
		PtProlongMaternityLeaveService {

	/**
	 * 创建延长产假业务信息
	 * @param ptProlongMaternityLeaveDTO
	 * @return
	 * @throws ServiceException
	 */
	public Long create(PtProlongMaternityLeaveDTO ptProlongMaternityLeaveDTO)
			throws ServiceException {
		PtProlongMaternityLeave ptProlongMaternityLeave = new PtProlongMaternityLeave();
		BeanHelper.copyProperties(ptProlongMaternityLeaveDTO, ptProlongMaternityLeave);
		ptProlongMaternityLeave.setCreateBy(UserContext.getLoginUserID());
		ptProlongMaternityLeave.setCreateName(UserContext.getLoginUserName());
		ptProlongMaternityLeave.setCreateDate(DateUtil.now());
		ptProlongMaternityLeave.setUpdateBy(UserContext.getLoginUserID());
		ptProlongMaternityLeave.setUpdateName(UserContext.getLoginUserName());
		ptProlongMaternityLeave.setUpdateDate(DateUtil.now());
		ptProlongMaternityLeave.save();
		return ptProlongMaternityLeave.getVacationOid();
	}

	/**
	 * 修改延长产假业务信息
	 * @param ptProlongMaternityLeaveDTO
	 * @throws ServiceException
	 */
	public void update(PtProlongMaternityLeaveDTO ptProlongMaternityLeaveDTO)
			throws ServiceException {
		PtProlongMaternityLeave ptProlongMaternityLeave = DaoUtil.get(PtProlongMaternityLeave.class, ptProlongMaternityLeaveDTO.getVacationOid());
		if(ptProlongMaternityLeave!=null) {
			BeanHelper.copyProperties(ptProlongMaternityLeaveDTO, ptProlongMaternityLeave, BeanHelper.getNullPropertyNames(ptProlongMaternityLeaveDTO));
			ptProlongMaternityLeave.setUpdateBy(UserContext.getLoginUserID());
			ptProlongMaternityLeave.setUpdateName(UserContext.getLoginUserName());
			ptProlongMaternityLeave.setUpdateDate(DateUtil.now());
			ptProlongMaternityLeave.update();
		}
	}

	/**
	 * 通过主键OID删除延长产假业务信息
	 * @param vacationOid
	 * @throws ServiceException
	 */
	public void delete(Long vacationOid) throws ServiceException {
		PtProlongMaternityLeave ptProlongMaternityLeave = DaoUtil.get(PtProlongMaternityLeave.class, vacationOid);
		if(ptProlongMaternityLeave!=null) {
			ptProlongMaternityLeave.delete();
		}
	}

	/**
	 * 通过主键OID获取延长产假业务信息
	 * @param vacationOid
	 * @return
	 * @throws ServiceException
	 */
	public PtProlongMaternityLeaveDTO get(Long vacationOid)
			throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtProlongMaternityLeave.class, vacationOid), PtProlongMaternityLeaveDTO.class);
	}

}
