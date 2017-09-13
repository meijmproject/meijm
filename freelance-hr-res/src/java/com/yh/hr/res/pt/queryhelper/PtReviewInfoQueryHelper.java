package com.yh.hr.res.pt.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.pt.bo.PtReviewInfo;
import com.yh.hr.res.pt.dto.PtReviewInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * @desc 考核信息查询工具类
 * @author xiongyx
 * @createDate 2016-10-10
 */
public class PtReviewInfoQueryHelper {
	
	/**
	 * 根据ID查询考核情况详细信息
	 * @param reviewOid
	 * @return
	 * @throws ServiceException
	 */	
	public static PtReviewInfoDTO getPtReviewInfoByOid(Long reviewOid) throws ServiceException{
		//查询数据
		PtReviewInfo PtReviewInfo = DaoUtil.get(PtReviewInfo.class, reviewOid);
		//po转换为dto
		PtReviewInfoDTO serdto = new PtReviewInfoDTO();
		return BeanHelper.copyRtnProperties(PtReviewInfo, serdto);
		
	}

	/*
	 * 获取列表
	 */  //order by Pt.reviewYear desc
	public static List<PtReviewInfoDTO> listPtReviewInfo(Long personOid) throws ServiceException {
		final StringBuffer hBuffer =  new StringBuffer("from PtReviewInfo Pt where  1 =1  ");
		hBuffer.append(" and  Pt.bizPersonOid =" +personOid);
		hBuffer.append(" order by Pt.reviewYear desc");
		return BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), PtReviewInfoDTO.class);
	}
	
	/**
	 * 计数
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static int countPtReviewInfoByBizPersonOid(Long bizPersonOid) throws ServiceException
	{
		String hql = "select count(*) from PtReviewInfo Pt where Pt.bizPersonOid = :bizPersonOid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		return DaoUtil.countByCondition(hql, params);
	}
	
	/**
	 * 根据personOid获取近三年年度考核结果
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
//	public static PtReviewInfoDTO getKhjgByPersonOid(Long personOid) throws ServiceException
//	{
//		String sql = "select pt.khjg from yhc_Pt_temp pt where pt.person_oid = " + personOid;
//		Object obj = DaoUtil.uniqueResultWithSQL(sql);
//		if(null != obj)
//		{
//			PtReviewInfoDTO PtReviewInfoDTO = new PtReviewInfoDTO();
//			PtReviewInfoDTO.setKhjg(obj == null ? null : obj.toString());
//			return PtReviewInfoDTO;
//		}
//		return null;
//	}
	
	/**
	 * 根据bizPersonOid删除人员考核信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtReviewInfo rei where rei.bizPersonOid = " + bizPersonOid);
	}
}