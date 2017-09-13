package com.yh.hr.res.pb.service.impl;

import com.yh.hr.res.pb.dto.PbProlongMaternityLeaveDTO;
import com.yh.hr.res.pb.service.PbProlongMaternityLeaveService;
import jade.workflow.utils.DateUtil;

import com.yh.hr.res.pb.bo.PbProlongMaternityLeave;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.web.UserContext;
/**
 * 延长产假基础信息service实现类
 * @author wangx
 * @date 2017-05-22
 * @version 1.0
 */
public class PbProlongMaternityLeaveServiceImpl implements
		PbProlongMaternityLeaveService {

	/**
	 * 创建延长产假基础信息
	 * @param pbProlongMaternityLeaveDTO
	 * @return
	 * @throws ServiceException
	 */
	public Long create(PbProlongMaternityLeaveDTO pbProlongMaternityLeaveDTO)
			throws ServiceException {
		PbProlongMaternityLeave pbProlongMaternityLeave = new PbProlongMaternityLeave();
		BeanHelper.copyProperties(pbProlongMaternityLeaveDTO, pbProlongMaternityLeave);
		pbProlongMaternityLeave.setCreateBy(UserContext.getLoginUserID());
		pbProlongMaternityLeave.setCreateName(UserContext.getLoginUserName());
		pbProlongMaternityLeave.setCreateDate(DateUtil.now());
		pbProlongMaternityLeave.setUpdateBy(UserContext.getLoginUserID());
		pbProlongMaternityLeave.setUpdateName(UserContext.getLoginUserName());
		pbProlongMaternityLeave.setUpdateDate(DateUtil.now());
		pbProlongMaternityLeave.save();
		return pbProlongMaternityLeave.getVacationOid();
	}

	/**
	 * 修改延长产假基础信息
	 * @param pbProlongMaternityLeaveDTO
	 * @throws ServiceException
	 */
	public void update(PbProlongMaternityLeaveDTO pbProlongMaternityLeaveDTO)
			throws ServiceException {
		PbProlongMaternityLeave pbProlongMaternityLeave = DaoUtil.get(PbProlongMaternityLeave.class, pbProlongMaternityLeaveDTO.getVacationOid());
		if(pbProlongMaternityLeave!=null) {
			BeanHelper.copyProperties(pbProlongMaternityLeaveDTO, pbProlongMaternityLeave, BeanHelper.getNullPropertyNames(pbProlongMaternityLeaveDTO));
			pbProlongMaternityLeave.setUpdateBy(UserContext.getLoginUserID());
			pbProlongMaternityLeave.setUpdateName(UserContext.getLoginUserName());
			pbProlongMaternityLeave.setUpdateDate(DateUtil.now());
			pbProlongMaternityLeave.update();
		}
	}

	/**
	 * 通过主键OID删除延长产假基础信息
	 * @param vacationOid
	 * @throws ServiceException
	 */
	public void delete(Long vacationOid) throws ServiceException {
		PbProlongMaternityLeave pbProlongMaternityLeave = DaoUtil.get(PbProlongMaternityLeave.class, vacationOid);
		if(pbProlongMaternityLeave!=null) {
			pbProlongMaternityLeave.delete();
		}
	}

	/**
	 * 通过主键OID获取延长产假基础信息
	 * @param vacationOid
	 * @return
	 * @throws ServiceException
	 */
	public PbProlongMaternityLeaveDTO get(Long vacationOid)
			throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PbProlongMaternityLeave.class, vacationOid), PbProlongMaternityLeaveDTO.class);
	}

}
