package com.yh.hr.res.pt.service.impl;

import com.yh.hr.res.pt.bo.PtProfTechQualifInfo;
import com.yh.hr.res.pt.dto.PtProfTechQualifInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtProfTechQualifInfoQueryHelper;
import com.yh.hr.res.pt.service.PtProfTechQualifInfoService;
import jade.workflow.utils.DateUtil;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.web.UserContext;
/**
 * 专业技术资格业务信息service实现类
 * @author wangx
 * @date 2017-05-26
 * @version 1.0
 */
public class PtProfTechQualifInfoServiceImpl implements
		PtProfTechQualifInfoService {

	/**
	 * 创建专业技术资格业务信息
	 * @param ptProfTechQualifInfoDto
	 * @return 
	 * @throws ServiceException
	 */
	public Long create(PtProfTechQualifInfoDTO ptProfTechQualifInfoDto)
			throws ServiceException {
		PtProfTechQualifInfo ptProfTechQualifInfo = new PtProfTechQualifInfo();
		BeanHelper.copyProperties(ptProfTechQualifInfoDto, ptProfTechQualifInfo);
		ptProfTechQualifInfo.setCreateBy(UserContext.getLoginUserID());
		ptProfTechQualifInfo.setCreateName(UserContext.getLoginUserName());
		ptProfTechQualifInfo.setCreateDate(DateUtil.now());
		ptProfTechQualifInfo.setUpdateBy(UserContext.getLoginUserID());
		ptProfTechQualifInfo.setUpdateName(UserContext.getLoginUserName());
		ptProfTechQualifInfo.setUpdateDate(DateUtil.now());
		ptProfTechQualifInfo.save();
		return ptProfTechQualifInfo.getProfTechQualifOid();
	}

	/**
	 * 通过主键ID获取专业技术资格业务信息
	 * @param ptProfTechQualifInfoId
	 * @return 
	 * @throws ServiceException
	 */
	public PtProfTechQualifInfoDTO get(Long ptProfTechQualifInfoId)
			throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtProfTechQualifInfo.class, ptProfTechQualifInfoId), PtProfTechQualifInfoDTO.class);
	}

	/**
	 * 修改专业技术资格业务信息
	 * @param ptProfTechQualifInfoDto
	 * @throws ServiceException
	 */ 
	public void update(PtProfTechQualifInfoDTO ptProfTechQualifInfoDto)
			throws ServiceException {
		PtProfTechQualifInfo ptProfTechQualifInfo = DaoUtil.get(PtProfTechQualifInfo.class, ptProfTechQualifInfoDto.getProfTechQualifOid());
		if(ptProfTechQualifInfo!=null) {
			BeanHelper.copyProperties(ptProfTechQualifInfoDto, ptProfTechQualifInfo, BeanHelper.getNullPropertyNames(ptProfTechQualifInfoDto));
			ptProfTechQualifInfo.setUpdateBy(UserContext.getLoginUserID());
			ptProfTechQualifInfo.setUpdateName(UserContext.getLoginUserName());
			ptProfTechQualifInfo.setUpdateDate(DateUtil.now());
			ptProfTechQualifInfo.update();
		}
	}

	/**
	 * 删除专业技术资格业务信息
	 * @param ptProfTechQualifInfoId
	 * @throws ServiceException
	 */ 
	public void delete(Long ptProfTechQualifInfoId) throws ServiceException {
		PtProfTechQualifInfo ptProfTechQualifInfo = DaoUtil.get(PtProfTechQualifInfo.class, ptProfTechQualifInfoId);
		if(ptProfTechQualifInfo!=null) {
			ptProfTechQualifInfo.delete();
		}
	}

	/**
	 * 查询所有专业技术资格信息
	 * @param ttb
	 * @return 
	 * @throws ServiceException
	 */
	public List<PtProfTechQualifInfoDTO> list(TableTagBean ttb)
			throws ServiceException {
		return PtProfTechQualifInfoQueryHelper.list(ttb);
	}

	/**
	 * 根据业务人员OID查询专业技术资格经历
	 * @param bizPersonOid
	 * @return List<PtProfTechQualifInfoDTO>
	 * @throws ServiceException
	 */
	public List<PtProfTechQualifInfoDTO> listPtProfTechQualifInfoByBizPersonOid(
			Long bizPersonOid) throws ServiceException {
		return PtProfTechQualifInfoQueryHelper.listProfTechQualifInfoByBizPersonOid(bizPersonOid);
	}

	/**
	 * 通过基础OID查找该人员的专业技术资格业务信息
	 * @param baseProfTechQualifOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtProfTechQualifInfoDTO> listPtProfTechQualifInfoByBaseProfTechQualifOid(
			Long baseProfTechQualifOid) throws ServiceException {
		return PtProfTechQualifInfoQueryHelper.listPtProfTechQualifInfoByBaseProfTechQualifOid(baseProfTechQualifOid);
	}

	/**
	 * 通过业务人员OID删除该人员的所有专业技术资格信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		PtProfTechQualifInfoQueryHelper.deleteByBizPersonOid(bizPersonOid);
	}

}
