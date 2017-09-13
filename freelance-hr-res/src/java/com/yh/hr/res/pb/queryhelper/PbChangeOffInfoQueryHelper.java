package com.yh.hr.res.pb.queryhelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.pb.dto.PbChangeOffInfoDTO;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pb.bo.PbChangeOffInfo;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;

/**
 * 人员调休基础信息查询工具类
 * @author renp
 *
 */
public class PbChangeOffInfoQueryHelper {

	/**
	 * 根据personOid查询人员调休信息
	 * @param personOid
	 * @return
	 * @throws com.yh.platform.core.exception.ServiceException
	 */
	public static List<PbChangeOffInfoDTO> list(Long personOid) throws ServiceException {
		
		String hql = "from PbChangeOffInfo fi where fi.personOid = :personOid order by fi.createDate asc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personOid", personOid);
		List<PbChangeOffInfo> boList = DaoUtil.find(hql, params);
		List<PbChangeOffInfoDTO> dtoList = new ArrayList<PbChangeOffInfoDTO>();
		
		if(!CollectionUtils.isEmpty(boList))
		{
			for(PbChangeOffInfo bo : boList)
			{
				PbChangeOffInfoDTO dto = new PbChangeOffInfoDTO();
				BeanHelper.copyProperties(bo, dto);
				dtoList.add(dto);
			}
		}
		return dtoList;
	}
	
	/**
	 * 统计调休天数
	 * @param personOid
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws DataAccessFailureException 
	 */
	public static List<PbChangeOffInfoDTO> findChangeOffDaysByCon(Long personOid, Date startDate,
			Date endDate,Long parameterSetOid) throws DataAccessFailureException
	{
		StringBuffer sb = new StringBuffer("select change_off_days,start_date,end_date from yhc_pb_change_off_info where 1=1 ")
		.append(" and person_oid = ").append(personOid).append(" and start_date >= str_to_date('").append(DateUtil.format(startDate, DateUtil.DATE_PATTERN_DEFAULT)).append("' ,'%Y-%m-%d') and end_date <= str_to_date('")
		.append(DateUtil.format(endDate, DateUtil.DATE_PATTERN_DEFAULT)).append("','%Y-%m-%d')");
		List<Object[]> list = DaoUtil.findWithSQL(sb.toString());
		List<PbChangeOffInfoDTO>  PbChangeOffInfoDTOList = new ArrayList<PbChangeOffInfoDTO>();
		if(CollectionUtils.isNotEmpty(list))
		{
			for(int i = 0; i < list.size(); i++){
				Object[] task = list.get(i);
				PbChangeOffInfoDTO  pbChangeOffInfoDTO =new PbChangeOffInfoDTO();
				pbChangeOffInfoDTO.setChangeOffDays(task[0] == null ? null:Float.valueOf(task[0].toString()));
				pbChangeOffInfoDTO.setStartDate(task[1] == null ? null:DateUtil.parseDate(task[1].toString()));
				pbChangeOffInfoDTO.setEndDate(task[2] == null ? null:DateUtil.parseDate(task[2].toString()));
				PbChangeOffInfoDTOList.add(pbChangeOffInfoDTO);
			}
		}
		return PbChangeOffInfoDTOList;
	}
}
