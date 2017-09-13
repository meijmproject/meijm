package com.yh.hr.res.pb.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pb.bo.PbCertificateInfo;
import com.yh.hr.res.pb.dto.PbCertificateInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringUtil;

/**
 * 职业注册信息QueryHelper
 * @author liul
 *
 */
public class PbCertificateInfoQueryHelper {
	
    
    /**
     * 根据人员id和职业证编号查询该人员职业注册信息记录
     * @param personOid
     * @param certificateInfoNo
     * @return
     * @throws ServiceException
     */
	public static PbCertificateInfoDTO getCertificateInfoByCertificateNo(Long personOid,String certificateInfoNo) throws ServiceException{
		String hql = "from PbCertificateInfo ei where ei.personOid = :personOid and ei.certificateNo like :certificateInfoNo";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personOid", personOid);
		params.put("certificateInfoNo", StringUtil.wrapPercent(certificateInfoNo));
		List<PbCertificateInfo> boList = DaoUtil.find(hql, params);
		List<PbCertificateInfoDTO> dtoList = new ArrayList<PbCertificateInfoDTO>();
		if(CollectionUtils.isNotEmpty(boList))
		{
			dtoList = BeanHelper.copyProperties(boList, PbCertificateInfoDTO.class);
			return dtoList.get(0); //如果有多条记录取第一条（不应该存在多条）
		}
		return null;
	}
	
	public static List<PbCertificateInfoDTO> getCertificateInfoById(Long personOid) throws ServiceException{
		String hql = "from PbCertificateInfo ei where ei.personOid ="+personOid;
		return BeanHelper.copyProperties(DaoUtil.find(hql),PbCertificateInfoDTO.class);
	}

	/**
	 * 删除该人员的所有执业注册信息
	 * @param personOid
	 * @throws ServiceException
	 */
	public static void deleteByPersonOid(Long personOid) throws ServiceException{
		DaoUtil.executeUpdate("delete from PbCertificateInfo where personOid='"+personOid+"'");
	}

}
