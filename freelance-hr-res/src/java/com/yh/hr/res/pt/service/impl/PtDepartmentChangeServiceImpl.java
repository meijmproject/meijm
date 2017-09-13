package com.yh.hr.res.pt.service.impl;

import com.yh.hr.res.pt.dto.PtDepartmentChangeDTO;
import com.yh.hr.res.pt.queryhelper.PtDepartmentChangeQueryHelper;
import com.yh.hr.res.pt.service.PtDepartmentChangeService;
import jade.workflow.utils.DateUtil;

import java.util.List;

import com.yh.hr.res.pt.bo.PtDepartmentChange;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.web.UserContext;

/**
 * 人员科室变动业务信息service实现类
 * @author wangx
 * @date 2017-06-26
 * @version 1.0
 */
public class PtDepartmentChangeServiceImpl implements PtDepartmentChangeService {

	/**
	 * 创建人员科室变动业务信息
	 * @param ptDepartmentChangeDTO
	 * @return
	 * @throws ServiceException
	 */
	public Long create(PtDepartmentChangeDTO ptDepartmentChangeDTO) throws ServiceException {
		PtDepartmentChange ptDepartmentChange = new PtDepartmentChange();
		if(ptDepartmentChangeDTO!=null) {
			BeanHelper.copyProperties(ptDepartmentChangeDTO, ptDepartmentChange);
			ptDepartmentChange.setCreateBy(UserContext.getLoginUserID());
			ptDepartmentChange.setCreateName(UserContext.getLoginUserName());
			ptDepartmentChange.setCreateDate(DateUtil.now());
			ptDepartmentChange.setUpdateBy(UserContext.getLoginUserID());
			ptDepartmentChange.setUpdateName(UserContext.getLoginUserName());
			ptDepartmentChange.setUpdateDate(DateUtil.now());
			ptDepartmentChange.save();
			return ptDepartmentChange.getPtDepartmentChangeOid();
		}
		return null;
	}
	
	/**
	 * 修改人员科室变动业务信息
	 * @param ptDepartmentChangeDTO
	 * @throws ServiceException
	 */
	public void update(PtDepartmentChangeDTO ptDepartmentChangeDTO) throws ServiceException {
		PtDepartmentChange ptDepartmentChange = DaoUtil.get(PtDepartmentChange.class, ptDepartmentChangeDTO.getPtDepartmentChangeOid());
		if(ptDepartmentChange!=null) {
			BeanHelper.copyProperties(ptDepartmentChangeDTO, ptDepartmentChange, BeanHelper.getNullPropertyNames(ptDepartmentChangeDTO));
			ptDepartmentChange.setUpdateBy(UserContext.getLoginUserID());
			ptDepartmentChange.setUpdateName(UserContext.getLoginUserName());
			ptDepartmentChange.setUpdateDate(DateUtil.now());
			ptDepartmentChange.update();
		}
	}
	
	/**
	 * 删除人员科室变动业务信息
	 * @param ptDepartmentChangeOid
	 * @throws ServiceException
	 */
	public void delete(Long ptDepartmentChangeOid) throws ServiceException {
		PtDepartmentChange ptDepartmentChange = DaoUtil.get(PtDepartmentChange.class, ptDepartmentChangeOid);
		if(ptDepartmentChange!=null) {
			ptDepartmentChange.delete();
		}
	}
	
	/**
	 * 获取人员科室变动业务信息
	 * @param ptDepartmentChangeOid
	 * @return
	 * @throws ServiceException
	 */
	public PtDepartmentChangeDTO get(Long ptDepartmentChangeOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtDepartmentChange.class, ptDepartmentChangeOid), PtDepartmentChangeDTO.class);
	}
	
	/**
	 * 通过业务人员OID查找人员所有科室变动信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtDepartmentChangeDTO> findDepartmentChangeDTOListByBizPersonOid(Long bizPersonOid) throws ServiceException {
		return PtDepartmentChangeQueryHelper.findDepartmentChangeDTOListByBizPersonOid(bizPersonOid);
	}

	/**
	 * 通过基础OID查找人员科室变动业务信息
	 * @param pbDepartmentChangeOid
	 * @return
	 * @throws ServiceException
	 */
	public PtDepartmentChangeDTO findDepartmentChangeDTOByPbOid(Long pbDepartmentChangeOid) throws ServiceException {
		return PtDepartmentChangeQueryHelper.findDepartmentChangeDTOByPbOid(pbDepartmentChangeOid);
	}

	/**
	 * 通过bizPersonOid删除科室变动业务信息
	 */
	public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete PtDepartmentChange where bizPersonOid = "+bizPersonOid);
	}
}
