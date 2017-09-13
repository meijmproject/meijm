package com.yh.hr.res.pt.service.impl;

import com.yh.hr.res.pt.dto.PtQualificationsInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtQualificationsInfoQueryHelper;
import com.yh.hr.res.pt.service.PtQualificationsInfoService;
import jade.workflow.utils.DateUtil;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.pt.bo.PtQualificationsInfo;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.web.UserContext;

/**
 * 执业(职业)资格业务信息service实现类
 * @author wangx
 * @date 2017-05-26
 * @version 1.0
 */
public class PtQualificationsInfoServiceImpl implements
		PtQualificationsInfoService {

	/**
	 * 创建执业(职业)资格业务信息
	 * @param ptEducationTrainingInfoDto
	 * @return 
	 * @throws ServiceException
	 */
	public Long create(PtQualificationsInfoDTO ptQualificationsInfoDto)
			throws ServiceException {
		PtQualificationsInfo ptQualificationsInfo = new PtQualificationsInfo();
		BeanHelper.copyProperties(ptQualificationsInfoDto, ptQualificationsInfo);
		ptQualificationsInfo.setCreateBy(UserContext.getLoginUserID());
		ptQualificationsInfo.setCreateName(UserContext.getLoginUserName());
		ptQualificationsInfo.setCreateDate(DateUtil.now());
		ptQualificationsInfo.setUpdateBy(UserContext.getLoginUserID());
		ptQualificationsInfo.setUpdateName(UserContext.getLoginUserName());
		ptQualificationsInfo.setUpdateDate(DateUtil.now());
		ptQualificationsInfo.save();
		return ptQualificationsInfo.getQualificationsOid();
	}

	/**
	 * 通过主键ID获取执业(职业)资格业务信息
	 * @param ptQualificationsInfoId
	 * @return 
	 * @throws ServiceException
	 */
	public PtQualificationsInfoDTO get(Long ptQualificationsInfoId)
			throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtQualificationsInfo.class, ptQualificationsInfoId), PtQualificationsInfoDTO.class);
	}

	/**
	 * 修改执业(职业)资格业务信息
	 * @param ptEducationTrainingInfoDto
	 * @throws ServiceException
	 */ 
	public void update(PtQualificationsInfoDTO ptQualificationsInfoDto)
			throws ServiceException {
		PtQualificationsInfo ptQualificationsInfo = DaoUtil.get(PtQualificationsInfo.class, ptQualificationsInfoDto.getQualificationsOid());
		if(ptQualificationsInfo!=null) {
			BeanHelper.copyProperties(ptQualificationsInfoDto, ptQualificationsInfo, BeanHelper.getNullPropertyNames(ptQualificationsInfoDto));
			ptQualificationsInfo.setUpdateBy(UserContext.getLoginUserID());
			ptQualificationsInfo.setUpdateName(UserContext.getLoginUserName());
			ptQualificationsInfo.setUpdateDate(DateUtil.now());
			ptQualificationsInfo.update();
		}
	}

	/**
	 * 删除执业(职业)资格业务信息
	 * @param ptQualificationsInfoId
	 * @throws ServiceException
	 */
	public void delete(Long ptQualificationsInfoId) throws ServiceException {
		PtQualificationsInfo ptQualificationsInfo = DaoUtil.get(PtQualificationsInfo.class, ptQualificationsInfoId);
		if(ptQualificationsInfo!=null) {
			ptQualificationsInfo.delete();
		}
	}

	/**
	 * 查询所有执业(职业)资格信息
	 * @param ttb
	 * @return 
	 * @throws ServiceException
	 */
	public List<PtQualificationsInfoDTO> list(TableTagBean ttb)
			throws ServiceException {
		return PtQualificationsInfoQueryHelper.list(ttb);
	}

	/**
	 * 根据业务人员OID查询执业(职业)资格经历
	 * @param bizPersonOid
	 * @return List<PtQualificationsInfoDTO>
	 * @throws ServiceException
	 */
	public List<PtQualificationsInfoDTO> listPtQualificationsInfoByBizPersonOid(
			Long bizPersonOid) throws ServiceException {
		return PtQualificationsInfoQueryHelper.listQualificationsInfoByBizPersonOid(bizPersonOid);
	}

	/**
	 * 通过基础OID查找该人员的执业(职业)资格业务信息
	 * @param baseQualificationsOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtQualificationsInfoDTO> listPtQualificationsInfoByBaseQualificationsOid(
			Long baseQualificationsOid) throws ServiceException {
		return PtQualificationsInfoQueryHelper.listPtQualificationsInfoByBaseQualificationsOid(baseQualificationsOid);
	}

	/**
	 * 通过业务人员OID删除该人员的所有职业资格信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		PtQualificationsInfoQueryHelper.deleteByBizPersonOid(bizPersonOid);
	}

}
