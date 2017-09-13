package com.yh.hr.res.pt.queryhelper;

import com.yh.hr.res.pt.bo.PtRecruitment;
import com.yh.hr.res.pt.dto.PtRecruitmentDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * @desc 招考信息查询工具类
 * @author xiongyx
 * @createDate 2016-11-17
 */
public class PtRecruitmentQueryHelper {
	/*
	 * 通过ID获取
	 */
	public static PtRecruitmentDTO getPtRecruitmentDTOById(Long bizPersonOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtRecruitment.class, bizPersonOid), PtRecruitmentDTO.class);
	}
	
	/**
	 * 根据bizPersonOid删除信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtRecruitment pr where pr.bizPersonOid = " + bizPersonOid);
	}
}