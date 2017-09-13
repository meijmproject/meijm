package com.yh.hr.res.pb.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.pb.bo.PbPositioningInfo;
import com.yh.hr.res.pb.dto.PbPositioningDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringUtil;

/**
 * @desc 公务员任职聘任信息查询工具类
 * @author luqy
 * @createDate 2016-8-16上午09:53:23
 */
public class PbPositioningInfoQueryHelper {

	/*
	 * 通过ID获取
	 */
	public static PbPositioningDTO getPbPositioningInfoDTOById(Long positioningOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PbPositioningInfo.class, positioningOid), PbPositioningDTO.class);
	}

	/*
	 * 通过ID获取
	 */
	public static List<PbPositioningDTO> listPbPositioningByPersonId(Long personOid) throws ServiceException {
		List<PbPositioningDTO> list = null;
		
			final StringBuffer hBuffer = new StringBuffer("from PbPositioningInfo pb where  1 =1 ");
			if (StringUtil.isNotNull(personOid)) {
                hBuffer.append(" and  pb.personOid =" + personOid);
            }
			 hBuffer.append(" order by pb.dutyDate desc");
			list = BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), PbPositioningDTO.class);
		
		return list;
	}
	/*
	 * 通过ID获取
	 */
	public static List<PbPositioningInfo> listPbPositioningByRefOid(Long refPositioningOid) throws ServiceException {
		List<PbPositioningInfo> list = null;
		
			final StringBuffer hBuffer = new StringBuffer("from PbPositioningInfo pb where  1 =1 ");
                hBuffer.append(" and  pb.refPositioningOid =" + refPositioningOid);
			 hBuffer.append(" order by pb.dutyDate desc");
			list = BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), PbPositioningInfo.class);
		
		return list;
	}

	/*
	 * 通过人 员ID和状态查询任职信息
	 */
	public static List<PbPositioningDTO> findPDByPersonOidAndStatus(	Long personOid ,
																String dutyStatus) throws ServiceException {
		
			Map<String, Object> params = new HashMap<String, Object>();
			final StringBuffer hBuffer = new StringBuffer("from PbPositioningInfo pd where pd.personOid = :personOid");
			if (StringUtil.isNotBlank(dutyStatus)) {
                hBuffer.append(" and pd.positioningStatus= :positioningStatus");
				params.put("positioningStatus", dutyStatus);
            }
			params.put("personOid", personOid);
			hBuffer.append(" order by pd.dutyDate desc");
			List<PbPositioningInfo> list = DaoUtil.find(hBuffer.toString(), params);
			return BeanHelper.copyProperties(list, PbPositioningDTO.class);
		
	}
	/*
	 * 根据岗位类型查询任职情况
	 */
	public static List<PbPositioningDTO> findSPdByPersonOidAndStatus(	Long personOid ,
																		String dutyStatus,String isMPosition) throws ServiceException {
		
			Map<String, Object> params = new HashMap<String, Object>();
			final StringBuffer hBuffer = new StringBuffer("select pd from PbPositioningInfo pd,PbPostInfo pi where pi.positioningOid=pd.positioningOid and pd.personOid = :personOid and pi.isMPosition = :isMPosition");
			if (StringUtil.isNotBlank(dutyStatus)) {
				hBuffer.append(" and pd.positioningStatus= :positioningStatus");
				params.put("positioningStatus", dutyStatus);
			}
			params.put("personOid", personOid);
			params.put("isMPosition", isMPosition);
			hBuffer.append(" order by pd.dutyDate desc");
			List<PbPositioningInfo> list = DaoUtil.find(hBuffer.toString(), params);
			return BeanHelper.copyProperties(list, PbPositioningDTO.class);
		
	}
	
	/**
	 * 根据personOid删除职务信息
	 * @param personOid
	 * @throws ServiceException
	 */
	public static void deletePbPositioningByPersonId(Long personOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PbPositioningInfo pi where pi.personOid = " + personOid);
	}
}
