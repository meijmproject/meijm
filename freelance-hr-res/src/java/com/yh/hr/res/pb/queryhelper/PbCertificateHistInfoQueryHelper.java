package com.yh.hr.res.pb.queryhelper;

import java.util.List;

import com.yh.hr.res.pb.dto.PbCertificateHistInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 职业注册历史信息QueryHelper
 * @author chenjl
 *
 */
public class PbCertificateHistInfoQueryHelper {
	
    
    /**
     * 根据人员id查询该人员职业注册信息记录
     * @param personOid
     * @return
     * @throws ServiceException
     */
	public static List<PbCertificateHistInfoDTO> getCertificateHistInfoById(Long personOid) throws ServiceException{
		String hql = "from PbCertificateHistInfo ei where ei.personOid =  "+personOid;
		return BeanHelper.copyProperties(DaoUtil.find(hql),PbCertificateHistInfoDTO.class);
	}

}
