package com.yh.hr.res.pt.service.impl;

import com.yh.hr.res.pt.bo.PtEducationTrainingInfo;
import com.yh.hr.res.pt.dto.PtEducationTrainingInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtEducationTrainingInfoQueryHelper;
import com.yh.hr.res.pt.service.PtEducationTrainingInfoService;
import jade.workflow.utils.DateUtil;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.web.UserContext;
/**
 * 教育培训业务信息service实现类
 * @author wangx
 * @date 2017-05-26
 * @version 1.0
 */
public class PtEducationTrainingInfoServiceImpl implements
		PtEducationTrainingInfoService {

	/**
	 * 创建教育培训业务信息
	 * @param ptEducationTrainingInfoDto
	 * @return 
	 * @throws ServiceException
	 */
	public Long create(PtEducationTrainingInfoDTO ptEducationTrainingInfoDto)
			throws ServiceException {
		PtEducationTrainingInfo ptEducationTrainingInfo = new PtEducationTrainingInfo();
		BeanHelper.copyProperties(ptEducationTrainingInfoDto, ptEducationTrainingInfo);
		ptEducationTrainingInfo.setCreateBy(UserContext.getLoginUserID());
		ptEducationTrainingInfo.setCreateName(UserContext.getLoginUserName());
		ptEducationTrainingInfo.setCreateDate(DateUtil.now());
		ptEducationTrainingInfo.setUpdateBy(UserContext.getLoginUserID());
		ptEducationTrainingInfo.setUpdateName(UserContext.getLoginUserName());
		ptEducationTrainingInfo.setUpdateDate(DateUtil.now());
		ptEducationTrainingInfo.save();
		return ptEducationTrainingInfo.getEducationTrainingOid();
	}

	/**
	 * 通过主键ID获取教育培训业务信息
	 * @param ptEducationTrainingInfoId
	 * @return 
	 * @throws ServiceException
	 */
	public PtEducationTrainingInfoDTO get(Long ptEducationTrainingInfoId)
			throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtEducationTrainingInfo.class, ptEducationTrainingInfoId), PtEducationTrainingInfoDTO.class);
	}

	/**
	 * 修改教育培训业务信息
	 * @param ptEducationTrainingInfoDto
	 * @throws ServiceException
	 */ 
	public void update(PtEducationTrainingInfoDTO ptEducationTrainingInfoDto)
			throws ServiceException {
		PtEducationTrainingInfo ptEducationTrainingInfo = DaoUtil.get(PtEducationTrainingInfo.class, ptEducationTrainingInfoDto.getEducationTrainingOid());
		if(ptEducationTrainingInfo!=null) {
			BeanHelper.copyProperties(ptEducationTrainingInfoDto, ptEducationTrainingInfo, BeanHelper.getNullPropertyNames(ptEducationTrainingInfoDto));
			ptEducationTrainingInfo.setUpdateBy(UserContext.getLoginUserID());
			ptEducationTrainingInfo.setUpdateName(UserContext.getLoginUserName());
			ptEducationTrainingInfo.setUpdateDate(DateUtil.now());
			ptEducationTrainingInfo.update();
		}
	}

	/**
	 * 删除教育培训业务信息
	 * @param ptEducationTrainingInfoId
	 * @throws ServiceException
	 */ 
	public void delete(Long ptEducationTrainingInfoId) throws ServiceException {
		PtEducationTrainingInfo ptEducationTrainingInfo = DaoUtil.get(PtEducationTrainingInfo.class, ptEducationTrainingInfoId);
		if(ptEducationTrainingInfo!=null) {
			ptEducationTrainingInfo.delete();
		}
	}

	/**
	 * 查询所有教育培训业务信息
	 * @param ttb
	 * @return 
	 * @throws ServiceException
	 */
	public List<PtEducationTrainingInfoDTO> list(TableTagBean ttb)
			throws ServiceException {
		return PtEducationTrainingInfoQueryHelper.list(ttb);
	}

	/**
	 * 根据业务人员OID查询该人员所有的教育培训业务信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtEducationTrainingInfoDTO> listPtEducationTrainingInfoByBizPersonOid(
			Long bizPersonOid) throws ServiceException {
		return PtEducationTrainingInfoQueryHelper.listPtEducationTrainingInfoByBizPersonOid(bizPersonOid);
	}

	/**
	 * 通过基础OID查找该人员的教育培训业务信息
	 * @param baseEducationTrainingOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtEducationTrainingInfoDTO> listPtEducationTrainingInfoByBaseEducationTrainingOid(
			Long baseEducationTrainingOid) throws ServiceException {
		return PtEducationTrainingInfoQueryHelper.listPtEducationTrainingInfoByBaseEducationTrainingOid(baseEducationTrainingOid);
	}

	/**
	 * 删除该人员的所有教育培训记录
	 * @param bizPersonOid
	 */
	public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		PtEducationTrainingInfoQueryHelper.deleteByBizPersonOid(bizPersonOid);
	}

}
