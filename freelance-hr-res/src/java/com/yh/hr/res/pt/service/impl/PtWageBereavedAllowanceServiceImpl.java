package com.yh.hr.res.pt.service.impl;

import java.util.List;

import com.yh.hr.res.pt.bo.PtWageBereavedAllowance;
import com.yh.hr.res.pt.dto.PtWageBereavedAllowanceDTO;
import com.yh.hr.res.pt.queryhelper.PtWageBereavedAllowanceQueryHelper;
import com.yh.hr.res.pt.service.PtWageBereavedAllowanceService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class PtWageBereavedAllowanceServiceImpl implements PtWageBereavedAllowanceService {
	/*
	 * 由信息主键查询人员遗属生活困难补助信息
	 * */
	public PtWageBereavedAllowanceDTO getBereavedAllowanceDTOByMainId(
			Long ptBereavedAllowanceInfoOid) throws ServiceException {
		
		return  PtWageBereavedAllowanceQueryHelper.getPtWageBereavedAllowanceDTOByMainId(ptBereavedAllowanceInfoOid);
	}
	/*
	 * 由人员ID查询人员遗属生活困难补助信息
	 * */
	public List<PtWageBereavedAllowanceDTO> getPtBereavedAllowanceDTOById(Long bizPersonOid) throws ServiceException {
		
		return PtWageBereavedAllowanceQueryHelper.getPtWageBereavedAllowanceDTOById(bizPersonOid);
	}
	/*
	 * 修改人员遗属生活困难补助信息
	 * */
	public void updatePtWageBereavedAllowance(
			PtWageBereavedAllowanceDTO ptWageBereavedAllowanceDTO)
			throws ServiceException {
		
		PtWageBereavedAllowance ptWageBereavedAllowance = DaoUtil.get(PtWageBereavedAllowance.class,ptWageBereavedAllowanceDTO.getPtBereavedAllowanceInfoOid());
		BeanHelper.copyProperties(ptWageBereavedAllowanceDTO, PtWageBereavedAllowance.class);
		
		if (ptWageBereavedAllowance != null) {
			//排除不需要更新的字段"createdDate","createdByCode","createdByName"
			BeanHelper.copyProperties(ptWageBereavedAllowanceDTO, ptWageBereavedAllowance, new String[]{"createdDate","createdByCode","createdByName"});
			ptWageBereavedAllowance.setUpdatedByCode(UserContext.getLoginUserID());
			ptWageBereavedAllowance.setUpdatedByName(UserContext.getLoginUserName());
			ptWageBereavedAllowance.setUpdatedDate(DateUtil.now());
			ptWageBereavedAllowance.update();
		}
	}
	/*
	 * 删除人员遗属生活困难补助信息
	 * */
	public void deletePtWageBereavedAllowance(Long bizPersonOid)
			throws ServiceException {
		   // 可根据bizPersonOid删除
		PtWageBereavedAllowanceQueryHelper.deleteByBizPersonOid(bizPersonOid);
		
	}

	public void deletePtWageBereavedAllowanceByMainId(
			Long ptBereavedAllowanceInfoOid) throws ServiceException {
		// 可根据ptBereavedAllowanceInfoOid删除
		PtWageBereavedAllowance ptWageBereavedAllowance = DaoUtil.get(PtWageBereavedAllowance.class,
				ptBereavedAllowanceInfoOid);
		ptWageBereavedAllowance.delete();
		
	}
	/*
	 * 新增
	 */
	public void addPtWageBereavedAllowance(PtWageBereavedAllowanceDTO ptWageBereavedAllowanceDTO)throws ServiceException {
		PtWageBereavedAllowance ptWageBereavedAllowance = BeanHelper.copyProperties(
				ptWageBereavedAllowanceDTO, PtWageBereavedAllowance.class);
				//ptWageBereavedAllowance.setCreatedByCode(UserContext.getLoginUserID());
				//ptWageBereavedAllowance.setCreatedByName(UserContext.getLoginUserName());
				//ptWageBereavedAllowance.setCreatedDate(DateUtil.now());
		        ptWageBereavedAllowance.save();		
	}


}
