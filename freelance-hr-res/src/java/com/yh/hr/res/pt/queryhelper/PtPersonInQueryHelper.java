package com.yh.hr.res.pt.queryhelper;

import java.util.List;

import com.yh.hr.res.pt.bo.PtPersonIn;
import com.yh.hr.res.pt.dto.PtPersonInDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * @desc 人员转任信息查询工具类
 * @author xiongyx
 * @createDate 2016-10-09
 */
public class PtPersonInQueryHelper {
	/*
	 * 通过ID获取
	 */
	public static PtPersonInDTO getPtPersonInDTOById(Long bizPersonOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtPersonIn.class, bizPersonOid), PtPersonInDTO.class);
	}
	
	/**
	 * 根据bizPersonOid删除人员转任信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtPersonIn pi where pi.bizPersonOid = " + bizPersonOid);
	}
	
	/*
	 * 通过bizPersonOid获取
	 */
	public static List<PtPersonInDTO> listPtPersonInDTO(Long bizPersonOid) throws ServiceException {
		final StringBuffer hBuffer =  new StringBuffer("from PtPersonIn pt where  1 =1 ");
		hBuffer.append(" and  pt.bizPersonOid =" + bizPersonOid);//必须是按人来查询
		return BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), PtPersonInDTO.class);
	}
}