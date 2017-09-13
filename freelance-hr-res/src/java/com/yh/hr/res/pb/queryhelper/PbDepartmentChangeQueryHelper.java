package com.yh.hr.res.pb.queryhelper;

import java.util.Date;
import java.util.List;

import com.yh.hr.res.pb.dto.PbDepartmentChangeDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 人员科室变动基础信息queryhelper查询类
 * @author wangx
 * @date 2017-06-26
 * @version 1.0
 */
public class PbDepartmentChangeQueryHelper {

	/**
	 * 通过基础人员OID和时间段查找人员科室变动基础信息
	 * @param personOid
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ServiceException
	 */
	public static List<PbDepartmentChangeDTO> findDepartmentChangeDTOListByPersonOidAndDate(Long personOid, Date startDate, Date endDate) throws ServiceException {
		String hql = "from PbDepartmentChange dc where dc.personOid=? and dc.entryDepartmentDate between ? and ?";
		return BeanHelper.copyProperties(DaoUtil.find(hql,personOid, startDate, endDate), PbDepartmentChangeDTO.class);
	}
}
