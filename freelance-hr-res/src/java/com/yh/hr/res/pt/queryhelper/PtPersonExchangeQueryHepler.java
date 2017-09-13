package com.yh.hr.res.pt.queryhelper;

import com.yh.hr.res.pt.bo.PtPersonExchange;
import com.yh.hr.res.pt.dto.PtPersonExchangeDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * @desc 人员交流查询工具类
 * @author xiongyx
 * @createDate 2016-10-09
 */
public class PtPersonExchangeQueryHepler {
	/*
	 * 通过bizPersonOid查找人员业务基础信息
	 */
	public static PtPersonExchangeDTO getPtPersonExchangeDTOById(Long bizPersonOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.uniqueResult("from PtPersonExchange pp where pp.bizPersonOid = ? ", bizPersonOid), PtPersonExchangeDTO.class);
	}
	
	/**
	 * 通过personOid查找人员业务基础信息
	 * @param bizTaskOid
	 * @return
	 * @throws ServiceException
	 */
	public static PtPersonExchange getDTOByPersonId(Long personOid) throws ServiceException {
		return DaoUtil.uniqueResult("from PtPersonExchange pp where pp.prePersonOid = ? ", personOid);
	}
	
	/**
	 * 删除人员交流信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid)throws ServiceException {
		
		DaoUtil.executeUpdate("delete from PtPersonExchange ppe where ppe.bizPersonOid = " + bizPersonOid);
	}
}