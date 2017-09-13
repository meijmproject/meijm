package com.yh.hr.res.pt.service;

import java.util.List;



import com.yh.hr.res.pt.dto.PtAnnuallyPromoteDTO;
import com.yh.platform.core.exception.ServiceException;

public interface PtAnnuallyPromoteService {
	/*
	 * 由信息主键查询人员年度考核信息
	 * */
	public PtAnnuallyPromoteDTO getAnnuallyPromoteDTOByMainId(Long ptAnnuallyPromoteInfoOid) throws ServiceException;
	/*
	 * 由人员ID查询人员年度考核信息
	 * */
	public  List<PtAnnuallyPromoteDTO> getPtAnnuallyPromoteDTOById(Long bizPersonOid) throws ServiceException;
	/*
	 * 由年度查询人员年度考核信息
	 * */
	public  List<PtAnnuallyPromoteDTO> getPtAnnuallyPromoteDTOByYear(java.lang.Integer promoteYear) throws ServiceException;
	/*
	 * 修改人员年度考核信息
	 * */
	public  void updatePtAnnuallyPromote(PtAnnuallyPromoteDTO ptAnnuallyPromoteDTO) throws ServiceException;


	/*
	 * 删除人员年度考核信息
	 * */
	public void deletePtAnnuallyPromote(Long bizPersonOid)throws ServiceException;
	public void deletePtAnnuallyPromoteByMainId(Long ptAnnuallyPromoteInfoOid)throws ServiceException;
	
	/*
	 * 新增
	 */
	public void addPtAnnuallyPromote(PtAnnuallyPromoteDTO ptAnnuallyPromoteDTO)
			throws ServiceException;
	
	

	
}
