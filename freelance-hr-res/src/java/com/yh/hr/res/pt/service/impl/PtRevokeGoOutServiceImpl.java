package com.yh.hr.res.pt.service.impl;

import com.yh.hr.res.pt.bo.PtRevokeGoOut;
import com.yh.hr.res.pt.queryhelper.PtRevokeGoOutQueryHelper;
import jade.workflow.utils.DateUtil;

import java.util.List;

import com.yh.hr.res.pt.dto.PtRevokeGoOutDTO;
import com.yh.hr.res.pt.service.PtRevokeGoOutService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.web.UserContext;
/**
 * 普通外出销假业务信息service实现类
 * @author wangx
 * @date 2017-05-20
 * @version 1.0
 */
public class PtRevokeGoOutServiceImpl implements PtRevokeGoOutService {

	/**
	 * 新增普通外出销假的业务信息
	 * @param ptRevokeGoOutDTO
	 * @throws ServiceException
	 * @return 
	 */
	public Long create(PtRevokeGoOutDTO ptRevokeGoOutDTO)
			throws ServiceException {
		PtRevokeGoOut ptRevokeGoOut = new PtRevokeGoOut();
		BeanHelper.copyProperties(ptRevokeGoOutDTO, ptRevokeGoOut);
		ptRevokeGoOut.setCreateBy(UserContext.getLoginUserID());
		ptRevokeGoOut.setCreateName(UserContext.getLoginUserName());
		ptRevokeGoOut.setCreateDate(DateUtil.now());
		ptRevokeGoOut.save();
		return ptRevokeGoOut.getPtRevokeGoOutOid();
	}

	/**
	 * 修改普通外出销假的业务信息
	 * @param ptRevokeGoOutDTO
	 * @throws ServiceException
	 */
	public void update(PtRevokeGoOutDTO ptRevokeGoOutDTO)
			throws ServiceException {
		PtRevokeGoOut ptRevokeGoOut = DaoUtil.get(PtRevokeGoOut.class, ptRevokeGoOutDTO.getPtRevokeGoOutOid());
		if(ptRevokeGoOut!=null) {
			BeanHelper.copyProperties(ptRevokeGoOutDTO, ptRevokeGoOut, BeanHelper.getNullPropertyNames(ptRevokeGoOutDTO));
			ptRevokeGoOut.setUpdateBy(UserContext.getLoginUserID());
			ptRevokeGoOut.setUpdateName(UserContext.getLoginUserName());
			ptRevokeGoOut.setUpdateDate(DateUtil.now());
			ptRevokeGoOut.update();
		}

	}

	/**
	 * 删除普通外出销假的业务信息
	 * @param ptRevokeGoOutOid
	 * @throws ServiceException
	 */
	public void delete(Long ptRevokeGoOutOid) throws ServiceException {
		PtRevokeGoOut ptRevokeGoOut = DaoUtil.get(PtRevokeGoOut.class, ptRevokeGoOutOid);
		if(ptRevokeGoOut!=null) {
			ptRevokeGoOut.delete();
		}
	}

	/**
	 * 查询普通外出对应的普通外出销假信息
	 * @param ptGoOutOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtRevokeGoOutDTO> getPtRevokeGoOutDTOListByPbGoOutOid(
			Long ptGoOutOid) throws ServiceException {
		return PtRevokeGoOutQueryHelper.getPtRevokeGoOutDTOListByPtGoOutOid(ptGoOutOid);
	}

	/**
	 * 根据主键获取普通外出销假信息
	 * @param ptRevokeGoOutOid
	 * @return
	 * @throws ServiceException
	 */
	public PtRevokeGoOutDTO get(Long ptRevokeGoOutOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtRevokeGoOut.class, ptRevokeGoOutOid), PtRevokeGoOutDTO.class);
	}

}
