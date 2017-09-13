package com.yh.hr.res.pb.queryhelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pb.bo.PbGoOutInfo;
import com.yh.hr.res.pb.dto.PbGoOutInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;

/**
 * 人员外出基础信息查询工具类
 * @author duxw
 *
 */
public class PbGoOutInfoQueryHelper {

	/**
	 * 根据personOid查询人员外出信息
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PbGoOutInfoDTO> list(Long personOid) throws ServiceException {
		
		String hql = "from PbGoOutInfo fi where fi.personOid = :personOid order by fi.createDate asc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personOid", personOid);
		List<PbGoOutInfo> boList = DaoUtil.find(hql, params);
		List<PbGoOutInfoDTO> dtoList = new ArrayList<PbGoOutInfoDTO>();
		
		if(!CollectionUtils.isEmpty(boList))
		{
			for(PbGoOutInfo bo : boList)
			{
				PbGoOutInfoDTO dto = new PbGoOutInfoDTO();
				BeanHelper.copyProperties(bo, dto);
				dtoList.add(dto);
			}
		}
		return dtoList;
	}
	
	/**
	 * 根据人员ID、开始时间、结束时间查询请休假信息
	 * @param personOid
	 * @param startDate
	 * @param endDate
	 * @param flag 
	 * @return
	 */
	public static List<PbGoOutInfoDTO> findGoOutInfoByPeriodStartDateAndEndDate(
			Long personOid, Date startDate, Date endDate,String flag) throws DataAccessFailureException
	{		
		StringBuffer sb = new StringBuffer("select p.go_out_oid,p.person_oid,p.go_out_type,p.start_date,p.end_date,p.day_count")
		.append(",statutory_holiday_days,p.start_date_days,p.end_date_days from yhc_pb_go_out_info p where 1=1 ");
		
		if("1".equals(flag))
		{
			sb.append(" and person_oid = ").append(personOid).append(" and start_date >= str_to_date('").append(DateUtil.format(startDate, DateUtil.DATE_PATTERN_DEFAULT)).append("' ,'%Y-%m-%d') and end_date <= str_to_date('")
			.append(DateUtil.format(endDate, DateUtil.DATE_PATTERN_DEFAULT)).append("','%Y-%m-%d')");
		}
		if("2".equals(flag))
		{
			sb.append(" and person_oid = ").append(personOid).append(" and start_date < str_to_date('").append(DateUtil.format(startDate, DateUtil.DATE_PATTERN_DEFAULT)).append("' ,'%Y-%m-%d') and end_date <= str_to_date('")
			.append(DateUtil.format(endDate, DateUtil.DATE_PATTERN_DEFAULT)).append("','%Y-%m-%d')").append(" and end_date > str_to_date('").append(DateUtil.format(startDate, DateUtil.DATE_PATTERN_DEFAULT)).append("','%Y-%m-%d')");
		}
		if("3".equals(flag))
		{
			sb.append(" and person_oid = ").append(personOid).append(" and start_date >= str_to_date('").append(DateUtil.format(startDate, DateUtil.DATE_PATTERN_DEFAULT)).append("' ,'%Y-%m-%d') and end_date > str_to_date('")
			.append(DateUtil.format(endDate, DateUtil.DATE_PATTERN_DEFAULT)).append("','%Y-%m-%d')").append(" and start_date < str_to_date('").append(DateUtil.format(endDate, DateUtil.DATE_PATTERN_DEFAULT)).append("','%Y-%m-%d')");
		}
		if("4".equals(flag))
		{
			sb.append(" and person_oid = ").append(personOid).append(" and start_date < str_to_date('").append(DateUtil.format(startDate, DateUtil.DATE_PATTERN_DEFAULT)).append("' ,'%Y-%m-%d') and end_date > str_to_date('")
			.append(DateUtil.format(endDate, DateUtil.DATE_PATTERN_DEFAULT)).append("','%Y-%m-%d')");
		}
		List<Object[]> list = DaoUtil.findWithSQL(sb.toString());
		if(CollectionUtils.isNotEmpty(list))
		{
			List<PbGoOutInfoDTO> dtoList = new ArrayList<PbGoOutInfoDTO>();
			for(Object[] obj : list)
			{
				PbGoOutInfoDTO dto = new PbGoOutInfoDTO();
				dto.setGoOutOid(obj[0] == null ? null:Long.valueOf(obj[0].toString()));
				dto.setPersonOid(personOid);
				dto.setGoOutType(obj[2] == null ? null:obj[2].toString());
				dto.setStartDate(obj[3] == null ? null:(Date)obj[3]);
				dto.setEndDate(obj[4] == null ? null:(Date)obj[4]);
				dto.setDayCount(obj[5] == null ? null:Float.valueOf(obj[5].toString()));
				dto.setStatutoryHolidayDays(obj[6] == null ? null:Double.valueOf(obj[6].toString()));
				dto.setStartDateDays(obj[7] == null ? null:Double.valueOf(obj[7].toString()));
				dto.setEndDateDays(obj[8] == null ? null:Double.valueOf(obj[8].toString()));
				dtoList.add(dto);
			}
			return dtoList;
		}
		return null;
	}
	
	/**
	 * 根据人员ID查询请休假信息
	 * @param personOid
	 * @param startDate
	 * @param endDate
	 * @param flag 
	 * @return
	 */
	public static List<PbGoOutInfoDTO> findGoOutInfoByPersonOid(
			Long personOid, Date startDate, Date endDate) throws DataAccessFailureException
	{		
		StringBuffer sb = new StringBuffer("select p.go_out_oid,p.person_oid,p.go_out_type,p.start_date,p.end_date,p.day_count")
		.append(",statutory_holiday_days,p.start_date_days,p.end_date_days from yhc_pb_go_out_info p where 1=1 ");
		sb.append(" and person_oid = ").append(personOid).append(" and start_date <= str_to_date('").append(DateUtil.format(endDate, DateUtil.DATE_PATTERN_DEFAULT)).append("' ,'%Y-%m-%d') and end_date >= str_to_date('")
		.append(DateUtil.format(startDate, DateUtil.DATE_PATTERN_DEFAULT)).append("','%Y-%m-%d')");
		List<Object[]> list = DaoUtil.findWithSQL(sb.toString());
		if(CollectionUtils.isNotEmpty(list))
		{
			List<PbGoOutInfoDTO> dtoList = new ArrayList<PbGoOutInfoDTO>();
			for(Object[] obj : list)
			{
				PbGoOutInfoDTO dto = new PbGoOutInfoDTO();
				dto.setGoOutOid(obj[0] == null ? null:Long.valueOf(obj[0].toString()));
				dto.setPersonOid(personOid);
				dto.setGoOutType(obj[2] == null ? null:obj[2].toString());
				dto.setStartDate(obj[3] == null ? null:(Date)obj[3]);
				dto.setEndDate(obj[4] == null ? null:(Date)obj[4]);
				dto.setDayCount(obj[5] == null ? null:Float.valueOf(obj[5].toString()));
				dto.setStatutoryHolidayDays(obj[6] == null ? null:Double.valueOf(obj[6].toString()));
				dto.setStartDateDays(obj[7] == null ? null:Double.valueOf(obj[7].toString()));
				dto.setEndDateDays(obj[8] == null ? null:Double.valueOf(obj[8].toString()));
				dtoList.add(dto);
			}
			return dtoList;
		}
		return null;
	}
}
