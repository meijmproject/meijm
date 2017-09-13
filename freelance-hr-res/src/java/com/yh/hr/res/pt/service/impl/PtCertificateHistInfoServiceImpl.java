package com.yh.hr.res.pt.service.impl;

import com.yh.hr.res.pt.bo.PtCertificateHistInfo;
import com.yh.hr.res.pt.dto.PtCertificateHistInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtCertificateHistInfoQueryHelper;
import com.yh.hr.res.pt.service.PtCertificateHistInfoService;
import jade.workflow.utils.DateUtil;

import java.util.List;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.web.UserContext;
/**
 * 执业注册资格历史信息service实现类
 * @author wangx
 * @date 2017-05-26
 * @version 1.0
 */
public class PtCertificateHistInfoServiceImpl implements
		PtCertificateHistInfoService {

	/**
	 * 创建执业注册历史业务信息
	 * @param ptCertificateHistInfoDTO
	 * @return
	 * @throws ServiceException
	 */
	public Long create(PtCertificateHistInfoDTO ptCertificateHistInfoDTO)
			throws ServiceException {
		PtCertificateHistInfo ptCertificateHistInfo = new PtCertificateHistInfo();
		BeanHelper.copyProperties(ptCertificateHistInfoDTO, ptCertificateHistInfo);
		ptCertificateHistInfo.setCreateBy(UserContext.getLoginUserID());
		ptCertificateHistInfo.setCreateName(UserContext.getLoginUserName());
		ptCertificateHistInfo.setCreateDate(DateUtil.now());
		ptCertificateHistInfo.setUpdateBy(UserContext.getLoginUserID());
		ptCertificateHistInfo.setUpdateName(UserContext.getLoginUserName());
		ptCertificateHistInfo.setUpdateDate(DateUtil.now());
		ptCertificateHistInfo.save();
		return ptCertificateHistInfo.getCertificateHistOid();
	}

	/**
	 * 修改执业注册历史业务信息
	 * @param ptCertificateHistInfoDTO
	 * @throws ServiceException
	 */
	public void update(PtCertificateHistInfoDTO ptCertificateHistInfoDTO)
			throws ServiceException {
		PtCertificateHistInfo ptCertificateHistInfo = DaoUtil.get(PtCertificateHistInfo.class,ptCertificateHistInfoDTO.getCertificateHistOid());
		if(ptCertificateHistInfo!=null) {
			BeanHelper.copyProperties(ptCertificateHistInfoDTO, ptCertificateHistInfo, BeanHelper.getNullPropertyNames(ptCertificateHistInfoDTO));
			ptCertificateHistInfo.setUpdateBy(UserContext.getLoginUserID());
			ptCertificateHistInfo.setUpdateName(UserContext.getLoginUserName());
			ptCertificateHistInfo.setUpdateDate(DateUtil.now());
			ptCertificateHistInfo.update();
		}
	}

	/**
	 * 通过主键ID删除执业注册历史业务信息
	 * @param certificateHistOid
	 * @throws ServiceException
	 */
	public void delete(Long certificateHistOid) throws ServiceException {
		PtCertificateHistInfo ptCertificateHistInfo = DaoUtil.get(PtCertificateHistInfo.class,certificateHistOid);
		if(ptCertificateHistInfo!=null) {
			ptCertificateHistInfo.delete();
		}
	}

	/**
	 * 通过业务人员OID删除执业注册历史业务信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		PtCertificateHistInfoQueryHelper.deleteByBizPersonOid(bizPersonOid);
	}

	/**
	 * 通过主键ID获取执业注册历史信息
	 * @param certificateHistOid
	 * @return
	 * @throws ServiceException
	 */
	public PtCertificateHistInfoDTO get(Long certificateHistOid)
			throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtCertificateHistInfo.class, certificateHistOid), PtCertificateHistInfoDTO.class);
	}

	/**
     * 根据业务人员OID查询该人员执业注册历史业务信息
     * @param bizPersonOid
     * @return
     * @throws ServiceException
     */
	public List<PtCertificateHistInfoDTO> getPtCertificateHistInfoByBizPersonOid(
			Long bizPersonOid) throws ServiceException {
		return PtCertificateHistInfoQueryHelper.getPtCertificateHistInfoByBizPersonOid(bizPersonOid);
	}

	/**
     * 根据基础OID查询该人员执业注册历史业务信息列表
     * @param baseCertificateHistOid
     * @return
     * @throws ServiceException
     */
	public List<PtCertificateHistInfoDTO> getPtCertificateHistInfoListByBaseCertificateHistOid(
			Long baseCertificateHistOid) throws ServiceException {
		return PtCertificateHistInfoQueryHelper.getPtCertificateHistInfoListByBaseCertificateHistOid(baseCertificateHistOid);
	}


}
