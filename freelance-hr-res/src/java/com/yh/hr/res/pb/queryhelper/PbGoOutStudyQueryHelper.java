package com.yh.hr.res.pb.queryhelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yh.hr.res.pb.dto.PbGoOutStudyDTO;
import org.apache.commons.collections.CollectionUtils;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;

/**
 * 外出进修基础信息查询类
 * @author wangx
 * @date 2017-05-20
 * @version 1.0
 */
public class PbGoOutStudyQueryHelper {

	/**
	 * 根据personOid查询外出进修基础信息
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PbGoOutStudyDTO> getPbGoOutStudyDTOListByPersonOid(Long personOid) throws ServiceException {
		
		String hql = "from PbGoOutStudy gs where gs.personOid = ? order by gs.updateDate desc";
		return BeanHelper.copyProperties(DaoUtil.find(hql, personOid), PbGoOutStudyDTO.class);
	}
	
	/**
	 * 根据人员ID、开始时间、结束时间查询请休假信息
	 * @param personOid
	 * @param startDate
	 * @param endDate
	 * @param flag 
	 * @return
	 */
	public static List<PbGoOutStudyDTO> findGoOutInfoByPeriodStartDateAndEndDate(
			Long personOid, Date startDate, Date endDate,String flag) throws DataAccessFailureException
	{		
		StringBuffer sb = new StringBuffer("select p.pb_go_out_study_oid,p.person_oid,1,p.start_date,p.end_date,p.day_count")
		.append(",statutory_holiday_days,p.start_date_days,p.end_date_days from yhc_pb_go_out_study p where 1=1 ");
		
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
			List<PbGoOutStudyDTO> dtoList = new ArrayList<PbGoOutStudyDTO>();
			for(Object[] obj : list)
			{
				PbGoOutStudyDTO dto = new PbGoOutStudyDTO();
				dto.setPbGoOutStudyOid(obj[0] == null ? null:Long.valueOf(obj[0].toString()));
				dto.setPersonOid(personOid);
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
	 * 根据人员ID、查询请休假信息
	 * @param personOid
	 * @param startDate
	 * @param endDate
	 * @param flag 
	 * @return
	 */
	public static List<PbGoOutStudyDTO> findGoOutInfoByPersonOid(
			Long personOid, Date startDate, Date endDate) throws DataAccessFailureException
	{		
		StringBuffer sb = new StringBuffer("select p.pb_go_out_study_oid,p.person_oid,1,p.start_date,p.end_date,p.day_count")
		.append(",statutory_holiday_days,p.start_date_days,p.end_date_days from yhc_pb_go_out_study p where 1=1 ");
		sb.append(" and person_oid = ").append(personOid).append(" and start_date <= str_to_date('").append(DateUtil.format(endDate, DateUtil.DATE_PATTERN_DEFAULT)).append("' ,'%Y-%m-%d') and end_date >= str_to_date('")
		.append(DateUtil.format(startDate, DateUtil.DATE_PATTERN_DEFAULT)).append("','%Y-%m-%d')");
		List<Object[]> list = DaoUtil.findWithSQL(sb.toString());
		if(CollectionUtils.isNotEmpty(list))
		{
			List<PbGoOutStudyDTO> dtoList = new ArrayList<PbGoOutStudyDTO>();
			for(Object[] obj : list)
			{
				PbGoOutStudyDTO dto = new PbGoOutStudyDTO();
				dto.setPbGoOutStudyOid(obj[0] == null ? null:Long.valueOf(obj[0].toString()));
				dto.setPersonOid(personOid);
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
