/**
 * 
 */
package com.yh.hr.res.pt.service.impl;

import jade.workflow.utils.DateUtil;

import java.util.List;

import com.yh.hr.res.pt.bo.PtCertificateInfo;
import com.yh.hr.res.pt.dto.PtCertificateInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtCertificateInfoQueryHelper;
import com.yh.hr.res.pt.service.PtCertificateInfoService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.web.UserContext;

/**
 * 执业注册资格信息service实现类
 * @author wangx
 * @date 2017-05-26
 * @version 1.0
 */
public class PtCertificateInfoServiceImpl implements PtCertificateInfoService {

	/**
	 * 创建执业注册业务信息
	 * @param ptCertificateInfoDTO
	 * @return
	 * @throws ServiceException
	 */
	public Long create(PtCertificateInfoDTO ptCertificateInfoDTO)
			throws ServiceException {
		PtCertificateInfo ptCertificateInfo = new PtCertificateInfo();
		BeanHelper.copyProperties(ptCertificateInfoDTO, ptCertificateInfo);
		ptCertificateInfo.setCreateBy(UserContext.getLoginUserID());
		ptCertificateInfo.setCreateName(UserContext.getLoginUserName());
		ptCertificateInfo.setCreateDate(DateUtil.now());
		ptCertificateInfo.setUpdateBy(UserContext.getLoginUserID());
		ptCertificateInfo.setUpdateName(UserContext.getLoginUserName());
		ptCertificateInfo.setUpdateDate(DateUtil.now());
		ptCertificateInfo.save();
		return ptCertificateInfo.getCertificateOid();
	}

	/**
	 * 修改执业注册业务信息
	 * @param ptCertificateInfoDTO
	 * @throws ServiceException
	 */
	public void update(PtCertificateInfoDTO ptCertificateInfoDTO)
			throws ServiceException {
		PtCertificateInfo ptCertificateInfo = DaoUtil.get(PtCertificateInfo.class, ptCertificateInfoDTO.getCertificateOid());
		if(ptCertificateInfo!=null) {
			BeanHelper.copyProperties(ptCertificateInfoDTO, ptCertificateInfo, BeanHelper.getNullPropertyNames(ptCertificateInfoDTO));
			ptCertificateInfo.setUpdateBy(UserContext.getLoginUserID());
			ptCertificateInfo.setUpdateName(UserContext.getLoginUserName());
			ptCertificateInfo.setUpdateDate(DateUtil.now());
			ptCertificateInfo.update();
		}
	}

	/**
	 * 通过主键ID删除执业注册业务信息
	 * @param certificateOid
	 * @throws ServiceException
	 */
	public void delete(Long certificateOid) throws ServiceException {
		PtCertificateInfo ptCertificateInfo = DaoUtil.get(PtCertificateInfo.class, certificateOid);
		if(ptCertificateInfo!=null) {
			ptCertificateInfo.delete();
		}
	}

	/**
	 * 通过业务人员OID删除执业注册业务信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		PtCertificateInfoQueryHelper.deleteByBizPersonOid(bizPersonOid);
	}

	/**
	 * 通过主键ID获取执业注册信息
	 * @param certificateOid
	 * @return
	 * @throws ServiceException
	 */
	public PtCertificateInfoDTO get(Long certificateOid)
			throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtCertificateInfo.class, certificateOid), PtCertificateInfoDTO.class);
	}

	/**
     * 根据业务人员OID查询该人员执业注册业务信息
     * @param bizPersonOid
     * @return
     * @throws ServiceException
     */
	public List<PtCertificateInfoDTO> getPtCertificateInfoByBizPersonOid(
			Long bizPersonOid) throws ServiceException {
		return PtCertificateInfoQueryHelper.getPtCertificateInfoByBizPersonOid(bizPersonOid);
	}

	/**
     * 根据基础OID查询该人员执业注册业务信息列表
     * @param baseCertificateOid
     * @return
     * @throws ServiceException
     */
	public List<PtCertificateInfoDTO> getPtCertificateInfoListByBaseCertificateOid(
			Long baseCertificateOid) throws ServiceException {
		return PtCertificateInfoQueryHelper.getPtCertificateInfoListByBaseCertificateOid(baseCertificateOid);
	}

}
