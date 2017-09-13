package com.yh.hr.res.pt.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.pt.bo.PtResumeInfo;
import com.yh.hr.res.pt.dto.PtResumeInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 工作简历查询工具类（业务）
 * @author zhengdr
 *
 * 时间:2016-10-9下午02:54:51
 */
public class PtResumeInfoQueryHelper {

	/**
	 * 通过ID获取
	 * @param ptResumeOid
	 * @return
	 * @throws ServiceException
	 */
	public static PtResumeInfoDTO getPtResumeInfoDTOById(Long ptResumeOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtResumeInfo.class, ptResumeOid), PtResumeInfoDTO.class);
	}
	
	/**
	 * 获取列表
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtResumeInfoDTO> listPtResumeInfo(Long bizPersonOid) throws ServiceException {
		final StringBuffer hBuffer =  new StringBuffer("from PtResumeInfo pt where  1 =1 ");
		hBuffer.append(" and  pt.bizPersonOid =" +bizPersonOid);
		
		hBuffer.append(" order by pt.bizPersonOid");//升序
		
		return BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), PtResumeInfoDTO.class);
	}
	
	/**
	 * 获取列表
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtResumeInfoDTO> listNotEndPtResumeInfo(Long bizPersonOid) throws ServiceException {
		final StringBuffer hBuffer =  new StringBuffer("from PtResumeInfo pt where  1 =1 ");
		hBuffer.append(" and  pt.bizPersonOid =" +bizPersonOid);
		hBuffer.append(" and  pt.endDate is null");
		hBuffer.append(" order by pt.bizPersonOid");//升序
		
		return BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), PtResumeInfoDTO.class);
	}
	
	/**
	 * 计数
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static int countPtResumeInfoByBizPersonOid(Long bizPersonOid) throws ServiceException
	{
		String hql = "select count(*) from PtResumeInfo pt where pt.bizPersonOid = :bizPersonOid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		return DaoUtil.countByCondition(hql, params);
	}
	
	/**
	 * 通过基础OID查找该人员的工作简历业务信息
	 * @param resumeOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtResumeInfoDTO> listPtResumeInfoByResumeOid(Long resumeOid) throws ServiceException {
		List<PtResumeInfo> list = DaoUtil.findByNamed("from PtResumeInfo ri where ri.resumeOid = :resumeOid order by ri.endDate desc", "resumeOid", resumeOid);
		return BeanHelper.copyProperties(list, PtResumeInfoDTO.class);
	}
	
	/**
	 * 根据bizPersonOid删除人员工作简历信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtResumeInfo where bizPersonOid = " + bizPersonOid);
	}
}