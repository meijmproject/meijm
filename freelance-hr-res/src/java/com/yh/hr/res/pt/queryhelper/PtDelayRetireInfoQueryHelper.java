package com.yh.hr.res.pt.queryhelper;

import java.util.List;

import com.yh.hr.res.pt.bo.PtDelayRetireInfo;
import com.yh.hr.res.pt.dto.PtDelayRetireInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringUtil;

/**
 * @desc 延迟退休信息查询工具类
 * @author huanglm
 * @createDate 2016-12-08
 */
public class PtDelayRetireInfoQueryHelper {

	//根据bizPersonOid查询数据
	public static PtDelayRetireInfoDTO getPtDelayRetireInfoDTOById(Long bizPersonOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtDelayRetireInfo.class, bizPersonOid), PtDelayRetireInfoDTO.class);
	}
	
	/*
	 * 获取列表
	 */
	public static List<PtDelayRetireInfoDTO> listPtDelayRetireInfo(Long bizPersonOid) throws ServiceException {
		final StringBuffer hBuffer =  new StringBuffer("from PtDelayRetireInfo pt where  1 =1 ");
		if(StringUtil.isNotNull(bizPersonOid)){
			hBuffer.append(" and  pt.bizPersonOid =" +bizPersonOid);
		}
		return BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), PtDelayRetireInfoDTO.class);
	}
	
	
	/**
	 * 根据bizPersonOid删除人员延迟退休信息
	 * 
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtDelayRetireInfo pt where pt.bizPersonOid = " + bizPersonOid);
	}	
	
}