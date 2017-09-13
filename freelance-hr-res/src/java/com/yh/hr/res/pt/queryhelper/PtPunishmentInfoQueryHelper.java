package com.yh.hr.res.pt.queryhelper;

import java.util.List;

import com.yh.hr.res.pt.dto.PtPunishmentInfoDTO;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pt.bo.PtPunishmentInfo;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 惩处信息查询工具类
 * @author xiongyx	
 *@createDate 2016-10-10
 */
public class PtPunishmentInfoQueryHelper {
	/**
	 * 通过oid获取惩处信息
	 * @param punishmentInfoOid
	 * @return
	 * @throws ServiceException
	 */
	public static PtPunishmentInfoDTO getPunishmentInfoDTOById(Long punishmentInfoOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtPunishmentInfo.class, punishmentInfoOid), PtPunishmentInfoDTO.class);
	}

	/**
	 * 通过personoid获取奖惩情况
	 * @param personOid
	 * @return
	 * @throws  ServiceException 
	 */
//	public static List<PtPunishmentInfoDTO> getJCQKByPersonOid (Long personOid) throws  ServiceException{
//		String sql = "select pt.JCQK from yhc_Pt_temp pt where pt.person_oid = " + personOid;
//		List<String> list = DaoUtil.findWithSQL(sql);
//		List<PtPunishmentInfoDTO> PtPunishmentInfoDTOList = null;
//		if(CollectionUtils.isNotEmpty(list))
//		{
//			PtPunishmentInfoDTOList = new ArrayList<PtPunishmentInfoDTO>();
//			for(String s : list){	
//				PtPunishmentInfoDTO PtPunishmentInfoDTO = new PtPunishmentInfoDTO();
//				PtPunishmentInfoDTO.setJcqk(s == null ? "" : s);
//				PtPunishmentInfoDTOList.add(PtPunishmentInfoDTO);
//			}
//			return PtPunishmentInfoDTOList;
//		}
//		return null;
//		
//		
//	}
	 /**
	  * 获取惩处信息列表
	  * @param bizPersonOid
	  * @return
	  * @throws ServiceException
	  */
	public static List<PtPunishmentInfoDTO> listPtPunishmentInfo(Long bizPersonOid) throws ServiceException {
		final StringBuffer hBuffer =  new StringBuffer("from PtPunishmentInfo pt where  1 =1 ");
		hBuffer.append(" and  pt.bizPersonOid =" +bizPersonOid);//必须是按人来查询
		hBuffer.append(" order by pt.punishmentDate desc");
		return BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), PtPunishmentInfoDTO.class);
	}
	
	/**
	  * 获取惩处信息列表
	  * @param bizPersonOid
	  * @return
	  * @throws ServiceException
	  */
	public static List<PtPunishmentInfo> listPtPunishmentInfoBo(Long bizPersonOid) throws ServiceException {
		final StringBuffer hBuffer =  new StringBuffer("from PtPunishmentInfo pt where  1 =1 ");
		hBuffer.append(" and  pt.bizPersonOid =" +bizPersonOid);//必须是按人来查询
		hBuffer.append(" order by pt.punishmentDate desc");
		
		return DaoUtil.find(hBuffer.toString());
	}
	
	 /**
	  * 获取惩处信息列表
	  * @param bizPersonOid
	  * @return
	  * @throws ServiceException
	  */
	public static PtPunishmentInfo getPtPunishmentInfo(Long bizPersonOid) throws ServiceException {
		final StringBuffer hBuffer =  new StringBuffer("from PtPunishmentInfo pt where  1 =1 ");
		hBuffer.append(" and  pt.bizPersonOid =" +bizPersonOid);//必须是按人来查询
		hBuffer.append(" order by pt.punishmentDate desc");
		List<PtPunishmentInfo> list = DaoUtil.find(hBuffer.toString());
		
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}
	
/*	*//**
	  * 获取惩处信息列表
	  * @param personOid
	  * @return
	  * @throws ServiceException
	  *//*
	public static List<PtPunishmentInfoDTO> findPtPunishmentInfoByCondition(StringMap params, int start, int size, String order,
			boolean asc) throws DataAccessFailureException,ServiceException{
		final StringBuffer hql = new StringBuffer();
		hql.append(" from PtPunishmentInfo Pt ");
		final HashMap<String, Object> hqlParams = new HashMap<String, Object>();
		TableTagBean ttb =new TableTagBean();
		ttb.setCondition(params);
		ttb.setAsc(asc);
		ttb.setOrderBy(order);
		buildHQL(ttb,hql,hqlParams);
		List<PtPunishmentInfo> list = DaoUtil.listByCondition(hql.toString(), hqlParams,start, size);
		return buildPtPunishmentInfoDTO(list);
		}
	private static void buildHQL(TableTagBean ttb, StringBuffer hql, HashMap<String, Object> hqlParams )
			throws DataAccessFailureException {
		hql.append(" where 1=1 ");
		if (ttb.getCondition().getAsStringEmptyNull("ptPunishmentOid") != null) {
			hql.append(" and bh.ptPunishmentOid=:ptPunishmentOid ");
			hqlParams.put("ptPunishmentOid",
					Long.valueOf(ttb.getCondition().getAsStringEmptyNull("ptPunishmentOid")));
		}
		hql.append(" order by Pt.ptPunishmentOid desc");
	}
	private static List<PtPunishmentInfoDTO> buildPtPunishmentInfoDTO(List<PtPunishmentInfo> list) throws DataAccessFailureException,ServiceException
	{
		if(CollectionUtils.isEmpty(list)) return null;
		List<PtPunishmentInfoDTO> PtPunishmentInfoDTOList = new ArrayList<PtPunishmentInfoDTO>();
		for(PtPunishmentInfo PtPunishmentInfo : list){
			PtPunishmentInfoDTO PtPunishmentInfoDTO = null;
				PtPunishmentInfoDTO = BeanHelper.copyProperties(PtPunishmentInfo, PtPunishmentInfoDTO.class);
			PtPunishmentInfoDTOList.add(PtPunishmentInfoDTO);
		}
		return PtPunishmentInfoDTOList;
		
	}*/
	
	/**
	 * 根据bizPersonOid删除人员惩处信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtPunishmentInfo pui where pui.bizPersonOid = " + bizPersonOid);
	}
}
