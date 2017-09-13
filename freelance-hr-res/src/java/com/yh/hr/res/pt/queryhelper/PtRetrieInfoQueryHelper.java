package com.yh.hr.res.pt.queryhelper;

import java.util.List;

import com.yh.hr.res.pt.bo.PtRetrieInfo;
import com.yh.hr.res.pt.dto.PtRetrieInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringUtil;

/**
 * @desc 离退休信息查询工具类
 * @author went
 * @createDate 2016-11-08
 */
public class PtRetrieInfoQueryHelper {

	//根据oid查询数据
	public static PtRetrieInfoDTO getPtRetireInfoDTOById(Long bizPersonOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtRetrieInfo.class, bizPersonOid), PtRetrieInfoDTO.class);
	}
	
	/*
	 * 获取列表
	 */
	public static List<PtRetrieInfoDTO> listPtRetrieInfo(Long bizPersonOid) throws ServiceException {
		final StringBuffer hBuffer =  new StringBuffer("from PtRetrieInfo pt where  1 =1 ");
		if(StringUtil.isNotNull(bizPersonOid)){
			hBuffer.append(" and  pt.bizPersonOid =" +bizPersonOid);
		}
		return BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), PtRetrieInfoDTO.class);
	}
	
	
	/**
	 * 根据bizPersonOid删除人员任职信息
	 * 
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtRetrieInfo pt where pt.bizPersonOid = " + bizPersonOid);
	}	
	
}