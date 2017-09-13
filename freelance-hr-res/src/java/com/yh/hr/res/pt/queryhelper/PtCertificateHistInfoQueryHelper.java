package com.yh.hr.res.pt.queryhelper;

import java.util.List;

import com.yh.hr.res.pt.dto.PtCertificateHistInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 执业注册历史信息业务表QueryHelper
 * @author wangx
 * @date 2017-05-26
 * @version 1.0
 */
public class PtCertificateHistInfoQueryHelper {
	
    
    /**
     * 根据业务人员OID查询该人员执业注册历史业务信息
     * @param bizPersonOid
     * @return
     * @throws ServiceException
     */
	public static List<PtCertificateHistInfoDTO> getPtCertificateHistInfoByBizPersonOid(Long bizPersonOid) throws ServiceException{
		String hql = "from PtCertificateHistInfo chi where chi.bizPersonOid =  "+bizPersonOid;
		return BeanHelper.copyProperties(DaoUtil.find(hql),PtCertificateHistInfoDTO.class);
	}
	
    /**
     * 根据基础OID查询该人员执业注册历史业务信息列表
     * @param baseCertificateHistOid
     * @return
     * @throws ServiceException
     */
	public static List<PtCertificateHistInfoDTO> getPtCertificateHistInfoListByBaseCertificateHistOid(Long baseCertificateHistOid) throws ServiceException{
		String hql = "from PtCertificateHistInfo chi where chi.baseCertificateHistOid =  "+baseCertificateHistOid;
		return BeanHelper.copyProperties(DaoUtil.find(hql),PtCertificateHistInfoDTO.class);
	}

	/**
	 * 根据业务人员OID删除人员执业注册历史业务信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtCertificateHistInfo where bizPersonOid = " + bizPersonOid);
	}	
}
