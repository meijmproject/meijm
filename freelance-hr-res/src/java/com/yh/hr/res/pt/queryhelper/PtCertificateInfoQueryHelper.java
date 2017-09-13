package com.yh.hr.res.pt.queryhelper;

import java.util.List;

import com.yh.hr.res.pt.dto.PtCertificateInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 执业注册信息业务表QueryHelper
 * @author wangx
 * @date 2017-05-26
 * @version 1.0
 */
public class PtCertificateInfoQueryHelper {
	
	/**
	 * 通过业务人员OID查询执业注册业务信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtCertificateInfoDTO> getPtCertificateInfoByBizPersonOid(Long bizPersonOid) throws ServiceException{
		String hql = "from PtCertificateInfo ci where ci.bizPersonOid ="+bizPersonOid;
		return BeanHelper.copyProperties(DaoUtil.find(hql),PtCertificateInfoDTO.class);
	}


	/**
	 * 通过基础OID查询执业注册业务信息列表
	 * @param baseCertificateOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtCertificateInfoDTO> getPtCertificateInfoListByBaseCertificateOid(Long baseCertificateOid) throws ServiceException{
		String hql = "from PtCertificateInfo ci where ci.baseCertificateOid ="+baseCertificateOid;
		return BeanHelper.copyProperties(DaoUtil.find(hql),PtCertificateInfoDTO.class);
	}
	

	/**
	 * 根据业务人员OID删除人员执业注册业务信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtCertificateInfo where bizPersonOid = " + bizPersonOid);
	}	
}
