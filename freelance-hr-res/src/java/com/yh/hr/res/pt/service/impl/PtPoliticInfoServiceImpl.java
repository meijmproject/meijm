package com.yh.hr.res.pt.service.impl;

import jade.workflow.utils.DateUtil;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.pt.bo.PtPoliticInfo;
import com.yh.hr.res.pt.dto.PtPoliticInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtPoliticInfoQueryHelper;
import com.yh.hr.res.pt.service.PtPoliticInfoService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.web.UserContext;

/**
 * 政治面貌与党派业务信息service实现类
 * @author wangx
 * @date 2017-05-26
 * @version 1.0
 */
public class PtPoliticInfoServiceImpl implements PtPoliticInfoService {

	/**
	 * 创建政治面貌与党派业务信息
	 * @param ptEducationTrainingInfoDto
	 * @return 
	 * @throws ServiceException
	 */
	public Long create(PtPoliticInfoDTO ptPoliticInfoDto)
			throws ServiceException {
		PtPoliticInfo ptPoliticInfo = new PtPoliticInfo();
		BeanHelper.copyProperties(ptPoliticInfoDto, ptPoliticInfo);
		ptPoliticInfo.setCreateBy(UserContext.getLoginUserID());
		ptPoliticInfo.setCreateName(UserContext.getLoginUserName());
		ptPoliticInfo.setCreateDate(DateUtil.now());
		ptPoliticInfo.setUpdateBy(UserContext.getLoginUserID());
		ptPoliticInfo.setUpdateName(UserContext.getLoginUserName());
		ptPoliticInfo.setUpdateDate(DateUtil.now());
		ptPoliticInfo.save();
		return ptPoliticInfo.getPoliticOid();
	}

	/**
	 * 通过主键ID获取政治面貌与党派业务信息
	 * @param ptPoliticInfoId
	 * @return 
	 * @throws ServiceException
	 */
	public PtPoliticInfoDTO get(Long ptPoliticInfoId) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtPoliticInfo.class, ptPoliticInfoId), PtPoliticInfoDTO.class);
	}

	/**
	 * 修改政治面貌与党派业务信息
	 * @param ptPoliticInfoDto
	 * @throws ServiceException
	 */ 
	public void update(PtPoliticInfoDTO ptPoliticInfoDto)
			throws ServiceException {
		PtPoliticInfo ptPoliticInfo = DaoUtil.get(PtPoliticInfo.class, ptPoliticInfoDto.getPoliticOid());
		if(ptPoliticInfo!=null) {
			BeanHelper.copyProperties(ptPoliticInfoDto, ptPoliticInfo, BeanHelper.getNullPropertyNames(ptPoliticInfoDto));
			ptPoliticInfo.setUpdateBy(UserContext.getLoginUserID());
			ptPoliticInfo.setUpdateName(UserContext.getLoginUserName());
			ptPoliticInfo.setUpdateDate(DateUtil.now());
			ptPoliticInfo.update();
		}
	}

	/**
	 * 删除政治面貌与党派业务信息
	 * @param ptPoliticInfoId
	 * @throws ServiceException
	 */   
	public void delete(Long ptPoliticInfoId) throws ServiceException {
		PtPoliticInfo ptPoliticInfo = DaoUtil.get(PtPoliticInfo.class, ptPoliticInfoId);
		if(ptPoliticInfo!=null) {
			ptPoliticInfo.delete();
		}
	}

	/**
	 * 查询所有政治面貌与党派信息
	 * @param ttb
	 * @return 
	 * @throws ServiceException
	 */
	public List<PtPoliticInfoDTO> list(TableTagBean ttb)
			throws ServiceException {
		return PtPoliticInfoQueryHelper.list(ttb);
	}

	/**
	 * 根据业务人员OID查询政治面貌与党派经历
	 * @param bizPersonOid
	 * @return List<PtPoliticInfoDTO>
	 * @throws ServiceException
	 */
	public List<PtPoliticInfoDTO> listPtPoliticInfoByBizPersonOid(
			Long bizPersonOid) throws ServiceException {
		return PtPoliticInfoQueryHelper.listPtPoliticInfoByBizPersonOid(bizPersonOid);
	}

	/**
	 * 通过基础OID查找该人员的政治面貌与党派业务信息
	 * @param basePoliticOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtPoliticInfoDTO> listPtPoliticInfoByBasePoliticOid(
			Long basePoliticOid) throws ServiceException {
		return PtPoliticInfoQueryHelper.listPtPoliticInfoByBasePoliticOid(basePoliticOid);
	}

	/**
	 * 通过业务人员OID删除该人员的所有政治面貌与党派信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		PtPoliticInfoQueryHelper.deleteBizByPersonOid(bizPersonOid);
	}

}
