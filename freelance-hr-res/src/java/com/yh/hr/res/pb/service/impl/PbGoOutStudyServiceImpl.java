package com.yh.hr.res.pb.service.impl;

import com.yh.hr.res.pb.bo.PbGoOutStudy;
import com.yh.hr.res.pb.dto.PbGoOutStudyDTO;
import com.yh.hr.res.pb.queryhelper.PbGoOutStudyQueryHelper;
import com.yh.hr.res.pb.service.PbGoOutStudyService;
import jade.workflow.utils.DateUtil;

import java.util.Date;
import java.util.List;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.web.UserContext;
/**
 * 外出进修基础信息service实现类
 * @author wangx
 * @date 2017-05-20
 * @version 1.0
 */
public class PbGoOutStudyServiceImpl implements PbGoOutStudyService {

	/**
	 * 新增外出进修的基础信息
	 * @param pbGoOutStudyDTO
	 * @throws ServiceException
	 */
	public Long create(PbGoOutStudyDTO pbGoOutStudyDTO) throws ServiceException {
		PbGoOutStudy pbGoOutStudy = new PbGoOutStudy();
		BeanHelper.copyProperties(pbGoOutStudyDTO, pbGoOutStudy);
		pbGoOutStudy.setCreateBy(UserContext.getLoginUserID());
		pbGoOutStudy.setCreateName(UserContext.getLoginUserName());
		pbGoOutStudy.setCreateDate(DateUtil.now());
		pbGoOutStudy.setUpdateBy(UserContext.getLoginUserID());
		pbGoOutStudy.setUpdateName(UserContext.getLoginUserName());
		pbGoOutStudy.setUpdateDate(DateUtil.now());
		pbGoOutStudy.save();
		return pbGoOutStudy.getPbGoOutStudyOid();
	}

	/**
	 * 修改外出进修的基础信息
	 * @param pbGoOutStudyDTO
	 * @throws ServiceException
	 */
	public void update(PbGoOutStudyDTO pbGoOutStudyDTO) throws ServiceException {
		PbGoOutStudy pbGoOutStudy = DaoUtil.get(PbGoOutStudy.class, pbGoOutStudyDTO.getPbGoOutStudyOid());
		if(pbGoOutStudy!=null) {
			BeanHelper.copyProperties(pbGoOutStudyDTO, pbGoOutStudy, BeanHelper.getNullPropertyNames(pbGoOutStudyDTO));
			pbGoOutStudy.setUpdateBy(UserContext.getLoginUserID());
			pbGoOutStudy.setUpdateName(UserContext.getLoginUserName());
			pbGoOutStudy.setUpdateDate(DateUtil.now());
			pbGoOutStudy.update();
		}
	}

	/**
	 * 删除外出进修的基础信息
	 * @param pbGoOutStudyOid
	 * @throws ServiceException
	 */
	public void delete(Long pbGoOutStudyOid) throws ServiceException {
		PbGoOutStudy pbGoOutStudy = DaoUtil.get(PbGoOutStudy.class, pbGoOutStudyOid);
		if(pbGoOutStudy!=null) {
			pbGoOutStudy.delete();
		}
	}

	/**
	 * 查询人员对应的外出进修基础信息
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PbGoOutStudyDTO> getPbGoOutStudyDTOListByPersonOid(Long personOid) throws ServiceException {
		return PbGoOutStudyQueryHelper.getPbGoOutStudyDTOListByPersonOid(personOid);
	}

	/**
	 * 根据主键获取外出进修基础信息
	 * @param pbGoOutStudyOid
	 * @return
	 * @throws ServiceException
	 */
	public PbGoOutStudyDTO get(Long pbGoOutStudyOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PbGoOutStudy.class, pbGoOutStudyOid), PbGoOutStudyDTO.class);
	}

	/*
	 * (non-Javadoc)
	 * @see PbGoOutStudyService#findGoOutInfoByPeriodStartDateAndEndDate(java.lang.Long, java.util.Date, java.util.Date)
	 */
	public List<PbGoOutStudyDTO> findGoOutInfoByPeriodStartDateAndEndDate(
			Long personOid, Date startDate, Date endDate)
			throws ServiceException {
		return PbGoOutStudyQueryHelper.findGoOutInfoByPeriodStartDateAndEndDate(personOid, startDate, endDate, "1");
	}

	/*
	 * (non-Javadoc)
	 * @see PbGoOutStudyService#findGoOutInfoByPeriodStartDateAndEndDate1(java.lang.Long, java.util.Date, java.util.Date)
	 */
	public List<PbGoOutStudyDTO> findGoOutInfoByPeriodStartDateAndEndDate1(
			Long personOid, Date startDate, Date endDate)
			throws ServiceException {
		return PbGoOutStudyQueryHelper.findGoOutInfoByPeriodStartDateAndEndDate(personOid, startDate, endDate, "2");
	}

	/*
	 * (non-Javadoc)
	 * @see PbGoOutStudyService#findGoOutInfoByPeriodStartDateAndEndDate2(java.lang.Long, java.util.Date, java.util.Date)
	 */
	public List<PbGoOutStudyDTO> findGoOutInfoByPeriodStartDateAndEndDate2(
			Long personOid, Date startDate, Date endDate)
			throws ServiceException {
		return PbGoOutStudyQueryHelper.findGoOutInfoByPeriodStartDateAndEndDate(personOid, startDate, endDate, "3");
	}

	/*
	 * (non-Javadoc)
	 * @see PbGoOutStudyService#findGoOutInfoByPeriodStartDateAndEndDate3(java.lang.Long, java.util.Date, java.util.Date)
	 */
	public List<PbGoOutStudyDTO> findGoOutInfoByPeriodStartDateAndEndDate3(
			Long personOid, Date startDate, Date endDate)
			throws ServiceException {
		return PbGoOutStudyQueryHelper.findGoOutInfoByPeriodStartDateAndEndDate(personOid, startDate, endDate, "4");
	}

	public List<PbGoOutStudyDTO> findGoOutInfoByPersonOid(Long personOid,
			Date startDate, Date endDate) throws ServiceException {
		return PbGoOutStudyQueryHelper.findGoOutInfoByPersonOid(personOid, startDate, endDate);
	}

}
