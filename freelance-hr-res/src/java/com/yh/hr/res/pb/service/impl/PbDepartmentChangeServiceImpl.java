package com.yh.hr.res.pb.service.impl;

import java.util.Date;
import java.util.List;

import com.yh.hr.res.pb.bo.PbDepartmentChange;
import com.yh.hr.res.pb.dto.PbDepartmentChangeDTO;
import com.yh.hr.res.pb.queryhelper.PbDepartmentChangeQueryHelper;
import com.yh.hr.res.pb.service.PbDepartmentChangeService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 人员科室变动基础信息service实现类
 * @author wangx
 * @date 2017-06-26
 * @version 1.0
 */
public class PbDepartmentChangeServiceImpl implements PbDepartmentChangeService {

	/**
	 * 创建人员科室变动基础信息
	 * @param pbDepartmentChangeDTO
	 * @return
	 * @throws ServiceException
	 */
	public Long create(PbDepartmentChangeDTO pbDepartmentChangeDTO) throws ServiceException {
		PbDepartmentChange pbDepartmentChange = new PbDepartmentChange();
		if(pbDepartmentChangeDTO!=null) {
			BeanHelper.copyProperties(pbDepartmentChangeDTO, pbDepartmentChange);
			pbDepartmentChange.setCreateBy(UserContext.getLoginUserID());
			pbDepartmentChange.setCreateName(UserContext.getLoginUserName());
			pbDepartmentChange.setCreateDate(DateUtil.now());
			pbDepartmentChange.setUpdateBy(UserContext.getLoginUserID());
			pbDepartmentChange.setUpdateName(UserContext.getLoginUserName());
			pbDepartmentChange.setUpdateDate(DateUtil.now());
			pbDepartmentChange.save();
			return pbDepartmentChange.getPbDepartmentChangeOid();
		}
		return null;
	}
	
	/**
	 * 修改人员科室变动基础信息
	 * @param pbDepartmentChangeDTO
	 * @throws ServiceException
	 */
	public void update(PbDepartmentChangeDTO pbDepartmentChangeDTO) throws ServiceException {
		PbDepartmentChange pbDepartmentChange = DaoUtil.get(PbDepartmentChange.class, pbDepartmentChangeDTO.getPbDepartmentChangeOid());
		if(pbDepartmentChange!=null) {
			BeanHelper.copyProperties(pbDepartmentChangeDTO, pbDepartmentChange, BeanHelper.getNullPropertyNames(pbDepartmentChangeDTO));
			pbDepartmentChange.setUpdateBy(UserContext.getLoginUserID());
			pbDepartmentChange.setUpdateName(UserContext.getLoginUserName());
			pbDepartmentChange.setUpdateDate(DateUtil.now());
			pbDepartmentChange.update();
		}
	}
	
	/**
	 * 删除人员科室变动基础信息
	 * @param pbDepartmentChangeOid
	 * @throws ServiceException
	 */
	public void delete(Long pbDepartmentChangeOid) throws ServiceException {
		PbDepartmentChange pbDepartmentChange = DaoUtil.get(PbDepartmentChange.class, pbDepartmentChangeOid);
		if(pbDepartmentChange!=null) {
			pbDepartmentChange.delete();
		}
	}
	
	/**
	 * 获取人员科室变动基础信息
	 * @param pbDepartmentChangeOid
	 * @return
	 * @throws ServiceException
	 */
	public PbDepartmentChangeDTO get(Long pbDepartmentChangeOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PbDepartmentChange.class, pbDepartmentChangeOid), PbDepartmentChangeDTO.class);
	}
	
	/**
	 * 通过基础人员OID和时间段查找人员科室变动基础信息
	 * @param personOid
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ServiceException
	 */
	public List<PbDepartmentChangeDTO> findDepartmentChangeDTOListByPersonOidAndDate(Long personOid, Date startDate, Date endDate) throws ServiceException {
		return PbDepartmentChangeQueryHelper.findDepartmentChangeDTOListByPersonOidAndDate(personOid, startDate, endDate);
	}
}
