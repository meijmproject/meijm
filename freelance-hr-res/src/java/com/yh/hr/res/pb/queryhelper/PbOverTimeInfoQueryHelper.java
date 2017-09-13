package com.yh.hr.res.pb.queryhelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.pb.bo.PbOvertimeInfo;
import com.yh.hr.res.pb.dto.PbOverTimeInfoDTO;
import org.apache.commons.collections.CollectionUtils;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DataConverUtils;
import com.yh.platform.core.util.DateUtil;

/**
 * 人员加班基础信息查询工具类
 * @author chenjl
 * @date 2017-04-06
 * @version 1.0
 */
public class PbOverTimeInfoQueryHelper {

	/**
	 * 根据personOid查询人员加班信息
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PbOverTimeInfoDTO> list(Long personOid) throws ServiceException {
		
		String hql = "from PbOvertimeInfo fi where fi.personOid = :personOid order by fi.createDate asc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personOid", personOid);
		List<PbOvertimeInfo> boList = DaoUtil.find(hql, params);
		List<PbOverTimeInfoDTO> dtoList = new ArrayList<PbOverTimeInfoDTO>();
		
		if(!CollectionUtils.isEmpty(boList))
		{
			for(PbOvertimeInfo bo : boList)
			{
				PbOverTimeInfoDTO dto = new PbOverTimeInfoDTO();
				BeanHelper.copyProperties(bo, dto);
				dtoList.add(dto);
			}
		}
		return dtoList;
	}
	
	/**
	 * 统计加班天数
	 * @param personOid
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static Float findOvertimeDaysByCon(Long personOid, Date startDate,
			Date endDate)throws DataAccessFailureException
	{
		StringBuffer sb = new StringBuffer("select START_DATE,END_DATE,OVERTIME_DAYS from yhc_pb_overtime_info where 1=1 ")
		.append(" and person_oid = ").append(personOid).append(" and ( (start_date >= str_to_date('").append(DateUtil.format(startDate, DateUtil.DATE_PATTERN_DEFAULT)).append("' ,'%Y-%m-%d') and start_date <= str_to_date('")
		.append(DateUtil.format(endDate, DateUtil.DATE_PATTERN_DEFAULT)).append("','%Y-%m-%d') )or( ").append("end_date >= str_to_date('").append(DateUtil.format(startDate, DateUtil.DATE_PATTERN_DEFAULT)).append("' ,'%Y-%m-%d') and end_date <= str_to_date('")
		.append(DateUtil.format(endDate, DateUtil.DATE_PATTERN_DEFAULT)).append("','%Y-%m-%d') ) ")
		.append("or").append("(end_date >= str_to_date('").append(DateUtil.format(endDate, DateUtil.DATE_PATTERN_DEFAULT)).append("' ,'%Y-%m-%d') and start_date <= str_to_date('")
		.append(DateUtil.format(startDate, DateUtil.DATE_PATTERN_DEFAULT)).append("','%Y-%m-%d'))").append(")");
		
		List<Object[]> list = DaoUtil.findWithSQL(sb.toString());
		if(CollectionUtils.isNotEmpty(list))
		{
			long bd=0;
			long day=0;
			for(int i=0;i<list.size();i++){
				Object[] objs = list.get(i);
				Date obStartDate=DataConverUtils.toDate(objs[0]);
				Date obEndDate=DataConverUtils.toDate(objs[1]);
				@SuppressWarnings("unused")
				String days =DataConverUtils.toString(objs[2]);
			    if(obStartDate.getTime()<=startDate.getTime()){
			    	obStartDate=startDate;
			    }
			    if(obEndDate.getTime()>=endDate.getTime()){
			    	obEndDate=endDate;
			    }
			    day=(obEndDate.getTime()-obStartDate.getTime())/(24*60*60*1000)+1; 
			    bd=bd+day;
			}
			return Float.valueOf(bd);
		}
		return Float.valueOf("0");
	}
}
