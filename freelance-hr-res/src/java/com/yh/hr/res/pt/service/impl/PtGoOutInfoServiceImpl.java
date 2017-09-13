package com.yh.hr.res.pt.service.impl;

import com.yh.hr.res.pt.bo.PtGoOutInfo;
import com.yh.hr.res.pt.service.PtGoOutInfoService;
import jade.workflow.utils.DateUtil;

import java.util.List;

import com.yh.hr.res.pt.dto.PtGoOutInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtGoOutInfoQueryHelper;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.web.UserContext;

public class PtGoOutInfoServiceImpl implements PtGoOutInfoService {
	
	/**
	 * 新增人员外出的业务信息
	 * @param bizGoOutInfoDTO
	 * @throws ServiceException
	 */
	public Long create(PtGoOutInfoDTO ptGoOutInfoDTO) throws ServiceException{
		PtGoOutInfo ptGoOutInfo = new PtGoOutInfo();
		BeanHelper.copyProperties(ptGoOutInfoDTO, ptGoOutInfo);
		ptGoOutInfo.setCreateBy(UserContext.getLoginUserID());
		ptGoOutInfo.setCreateName(UserContext.getLoginUserName());
		ptGoOutInfo.setCreateDate(DateUtil.now());
		ptGoOutInfo.save();
		return ptGoOutInfo.getGoOutOid();
	}

	/**
	 * 修改人员外出的业务信息
	 * @param ptGoOutInfoDTO
	 * @throws ServiceException
	 */
	public void update(PtGoOutInfoDTO ptGoOutInfoDTO) throws ServiceException {
		PtGoOutInfo ptGoOutInfo = DaoUtil.get(PtGoOutInfo.class, ptGoOutInfoDTO.getGoOutOid());
		if(ptGoOutInfo!=null) {
			BeanHelper.copyProperties(ptGoOutInfoDTO, ptGoOutInfo, BeanHelper.getNullPropertyNames(ptGoOutInfoDTO));
			ptGoOutInfo.setUpdateBy(UserContext.getLoginUserID());
			ptGoOutInfo.setUpdateName(UserContext.getLoginUserName());
			ptGoOutInfo.setUpdateDate(DateUtil.now());
			ptGoOutInfo.update();
		}
	}
	
	/**
	 * 删除人员外出的业务信息
	 * @param goOutOid
	 * @throws ServiceException
	 */
	public void delete(Long goOutOid) throws ServiceException {
		PtGoOutInfo PtGoOutInfo = DaoUtil.get(PtGoOutInfo.class, goOutOid);
		if(PtGoOutInfo!=null) {
			PtGoOutInfo.delete();
		}
	}
	
	/**
	 * 查询人员对应的外出信息
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtGoOutInfoDTO> list(Long bizPersonOid) throws ServiceException {
		return PtGoOutInfoQueryHelper.list(bizPersonOid);
	}

	/**
	 * 根据主键获取外出信息
	 * @param goOutOid
	 * @return
	 * @throws ServiceException
	 */
	public PtGoOutInfoDTO get(Long goOutOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtGoOutInfo.class, goOutOid), PtGoOutInfoDTO.class);
	}
}
