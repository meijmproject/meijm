package com.yh.hr.res.pt.service.impl;

import java.util.List;

import com.yh.hr.res.pt.bo.PtAnnuallyPromote;
import com.yh.hr.res.pt.dto.PtAnnuallyPromoteDTO;
import com.yh.hr.res.pt.queryhelper.PtAnnuallyPromoteQueryHelper;
import com.yh.hr.res.pt.service.PtAnnuallyPromoteService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class PtAnnuallyPromoteServiceImpl implements PtAnnuallyPromoteService {
	/*
	 * 由信息主键查询人员遗属生活困难补助信息
	 * */
	public PtAnnuallyPromoteDTO getAnnuallyPromoteDTOByMainId(
			Long ptAnnuallyPromoteInfoOid) throws ServiceException {
		
		return  PtAnnuallyPromoteQueryHelper.getPtAnnuallyPromoteDTOByMainId(ptAnnuallyPromoteInfoOid);
	}
	/*
	 * 由人员ID查询人员遗属生活困难补助信息
	 * */
	public List<PtAnnuallyPromoteDTO> getPtAnnuallyPromoteDTOById(Long bizPersonOid) throws ServiceException {
		
		return PtAnnuallyPromoteQueryHelper.getPtAnnuallyPromoteDTOById(bizPersonOid);
	}
	/*
	 * 修改人员遗属生活困难补助信息
	 * */
	public void updatePtAnnuallyPromote(
			PtAnnuallyPromoteDTO ptAnnuallyPromoteDTO)
			throws ServiceException {
		
		PtAnnuallyPromote ptAnnuallyPromote = DaoUtil.get(PtAnnuallyPromote.class,ptAnnuallyPromoteDTO.getPtAnnuallyPromoteInfoOid());
		if (ptAnnuallyPromote != null) {
			//排除不需要更新的字段"createdDate","createdByCode","createdByName"
			BeanHelper.copyProperties(ptAnnuallyPromoteDTO, ptAnnuallyPromote, new String[]{"createdDate","createdByCode","createdByName"});
			ptAnnuallyPromote.setUpdatedByCode(UserContext.getLoginUserID());
			ptAnnuallyPromote.setUpdatedByName(UserContext.getLoginUserName());
			ptAnnuallyPromote.setUpdatedDate(DateUtil.now());
			ptAnnuallyPromote.update();
		}
	}
	/*
	 * 删除人员遗属生活困难补助信息
	 * */
	public void deletePtAnnuallyPromote(Long bizPersonOid)
			throws ServiceException {
		   // 可根据bizPersonOid删除
		PtAnnuallyPromoteQueryHelper.deleteByBizPersonOid(bizPersonOid);
		
	}

	public void deletePtAnnuallyPromoteByMainId(
			Long ptAnnuallyPromoteInfoOid) throws ServiceException {
		// 可根据ptAnnuallyPromoteInfoOid删除
		PtAnnuallyPromote ptAnnuallyPromote = DaoUtil.get(PtAnnuallyPromote.class,
				ptAnnuallyPromoteInfoOid);
		ptAnnuallyPromote.delete();
		
	}
	/*
	 * 新增
	 */
	public void addPtAnnuallyPromote(PtAnnuallyPromoteDTO ptAnnuallyPromoteDTO)throws ServiceException {
		PtAnnuallyPromote ptAnnuallyPromote = BeanHelper.copyProperties(
				ptAnnuallyPromoteDTO, PtAnnuallyPromote.class);
				ptAnnuallyPromote.setCreatedByCode(UserContext.getLoginUserID());
				ptAnnuallyPromote.setCreatedByName(UserContext.getLoginUserName());
				ptAnnuallyPromote.setCreatedDate(DateUtil.now());
		        ptAnnuallyPromote.save();		
	}

	public List<PtAnnuallyPromoteDTO> getPtAnnuallyPromoteDTOByYear(
			Integer promoteYear) throws ServiceException {
		
		return PtAnnuallyPromoteQueryHelper.getPtAnnuallyPromoteDTOByYear(promoteYear);
	}


}
