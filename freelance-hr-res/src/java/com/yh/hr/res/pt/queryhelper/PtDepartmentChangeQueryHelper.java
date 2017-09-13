package com.yh.hr.res.pt.queryhelper;

import java.util.List;

import com.yh.hr.res.pt.dto.PtDepartmentChangeDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 人员科室变动业务信息queryhelper查询类
 * @author wangx
 * @date 2017-06-26
 * @version 1.0
 */
public class PtDepartmentChangeQueryHelper {

	/**
	 * 通过业务人员OID查找人员所有科室变动业务信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtDepartmentChangeDTO> findDepartmentChangeDTOListByBizPersonOid(Long bizPersonOid) throws ServiceException {
		String hql = "from PtDepartmentChange dc where dc.bizPersonOid="+bizPersonOid;
		return BeanHelper.copyProperties(DaoUtil.find(hql), PtDepartmentChangeDTO.class);
	}

	/**
	 * 通过基础OID查找人员科室变动业务信息
	 * @param pbDepartmentChangeOid
	 * @return
	 * @throws ServiceException
	 */
	public static PtDepartmentChangeDTO findDepartmentChangeDTOByPbOid(Long pbDepartmentChangeOid) throws ServiceException {
		String hql = "from PtDepartmentChange dc where dc.pbDepartmentChangeOid="+pbDepartmentChangeOid;
		return BeanHelper.copyProperties(DaoUtil.uniqueResult(hql), PtDepartmentChangeDTO.class);
	}
}
