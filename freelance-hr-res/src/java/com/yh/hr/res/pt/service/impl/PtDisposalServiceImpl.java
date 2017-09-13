package com.yh.hr.res.pt.service.impl;

import java.util.List;

import com.yh.hr.res.pt.bo.PtDisposal;
import com.yh.hr.res.pt.dto.PtDisposalDTO;
import com.yh.hr.res.pt.queryhelper.PtDisposalQueryHelper;
import com.yh.hr.res.pt.service.PtDisposalService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

public class PtDisposalServiceImpl implements PtDisposalService {

	public List<PtDisposalDTO> listPtDisposalDTO(Long bizPersonOid)
			throws ServiceException {
		return PtDisposalQueryHelper.listPtDisposalDTOByBizPersonOid(bizPersonOid);
	}

	public void createPtDisposal(PtDisposalDTO ptDisposalDto)
			throws ServiceException {
		PtDisposal ptDisposal = BeanHelper.copyProperties(ptDisposalDto, PtDisposal.class);
		//得到操作人信息
		ptDisposal.save();
	}

	public void updatePtDisposal(PtDisposalDTO ptDisposalDTO)
			throws ServiceException {
		PtDisposal ptDisposal = BeanHelper.copyProperties(ptDisposalDTO, PtDisposal.class);
		//得到操作人信息
		ptDisposal.update();
	}

	public PtDisposal getDisposalByBizPersonId(Long bizPersonOid)
			throws ServiceException {
		
		return PtDisposalQueryHelper.getPtDisposal(bizPersonOid);
	}

	public PtDisposalDTO getDisposalDTOById(Long ptDisposalOid)
			throws ServiceException {
		PtDisposal disposal=DaoUtil.get(PtDisposal.class, ptDisposalOid);
		return BeanHelper.copyProperties(disposal, PtDisposalDTO.class);
	}

}
