package com.yh.hr.res.pt.service.impl;

import jade.workflow.utils.DateUtil;
import jade.workflow.utils.StringUtil;

import java.util.List;

import com.yh.hr.res.pt.bo.PtYnGwEmployInfo;
import com.yh.hr.res.pt.dto.PtYnGwEmployInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtYnGwEmployInfoQueryHelper;
import com.yh.hr.res.pt.service.PtYnGwEmployInfoService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.web.UserContext;

/**
 * 院内岗位聘任业务信息service实现类
 * @author wangx
 * @date 2017-05-26
 * @version 1.0
 */
public class PtYnGwEmployInfoServiceImpl implements PtYnGwEmployInfoService {

	/**
	 * 创建院内岗位聘任业务信息
	 * @param ptYnGwEmployInfoDto
	 * @return
	 * @throws ServiceException
	 */
	public Long create(PtYnGwEmployInfoDTO ptYnGwEmployInfoDto)
			throws ServiceException {
		PtYnGwEmployInfo ptYnGwEmployInfo = new PtYnGwEmployInfo();
		BeanHelper.copyProperties(ptYnGwEmployInfoDto, ptYnGwEmployInfo);
		if(StringUtil.isBlank(ptYnGwEmployInfo.getHisPositionOid())){
			ptYnGwEmployInfo.setHisPositionOid(null);
		}
		ptYnGwEmployInfo.setCreateBy(UserContext.getLoginUserID());
		ptYnGwEmployInfo.setCreateName(UserContext.getLoginUserName());
		ptYnGwEmployInfo.setCreateDate(DateUtil.now());
		ptYnGwEmployInfo.setUpdateBy(UserContext.getLoginUserID());
		ptYnGwEmployInfo.setUpdateName(UserContext.getLoginUserName());
		ptYnGwEmployInfo.setUpdateDate(DateUtil.now());
		ptYnGwEmployInfo.save();
		return ptYnGwEmployInfo.getYnGwEmployOid();
	}

	/**
	 * 通过主键ID获取院内岗位聘任业务信息
	 * @param ptYnGwEmployInfoDto
	 * @throws ServiceException
	 */
	public PtYnGwEmployInfoDTO get(Long ptYnGwEmployInfoId)
			throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtYnGwEmployInfo.class, ptYnGwEmployInfoId), PtYnGwEmployInfoDTO.class);
	}

	/**
	 * 修改院内岗位聘任业务信息
	 * @param ptYnGwEmployInfoDto
	 * @throws ServiceException
	 */
	public void update(PtYnGwEmployInfoDTO ptYnGwEmployInfoDto)
			throws ServiceException {
		PtYnGwEmployInfo ptYnGwEmployInfo = DaoUtil.get(PtYnGwEmployInfo.class, ptYnGwEmployInfoDto.getYnGwEmployOid());
		if(ptYnGwEmployInfo!=null) {
			BeanHelper.copyProperties(ptYnGwEmployInfoDto, ptYnGwEmployInfo, BeanHelper.getNullPropertyNames(ptYnGwEmployInfoDto));
			if(StringUtil.isBlank(ptYnGwEmployInfo.getHisPositionOid())){
				ptYnGwEmployInfo.setHisPositionOid(null);
			}
			ptYnGwEmployInfo.setUpdateBy(UserContext.getLoginUserID());
			ptYnGwEmployInfo.setUpdateName(UserContext.getLoginUserName());
			ptYnGwEmployInfo.setUpdateDate(DateUtil.now());
			ptYnGwEmployInfo.update();
		}
	}

	/**
	 * 通过主键ID删除院内岗位聘任业务信息
	 * @param ptYnGwEmployInfoDto
	 * @throws ServiceException
	 */ 
	public void delete(Long ptYnGwEmployInfoId) throws ServiceException {
		PtYnGwEmployInfo ptYnGwEmployInfo = DaoUtil.get(PtYnGwEmployInfo.class, ptYnGwEmployInfoId);
		if(ptYnGwEmployInfo!=null) {
			ptYnGwEmployInfo.delete();
		}
	}

	/**
	 * 通过业务人员OID删除院内岗位聘任业务信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */  
	public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		PtYnGwEmployInfoQueryHelper.deleteByBizPersonOid(bizPersonOid);
	}

	/**
	 * 不在聘信息记录的唯一校验
	 * @param ptYnGwEmployInfoDTO
	 * @return
	 * @throws ServiceException
	 */
	public boolean checkStatus(PtYnGwEmployInfoDTO ptYnGwEmployInfoDTO)
			throws ServiceException {
		return PtYnGwEmployInfoQueryHelper.checkStatus(ptYnGwEmployInfoDTO);
	}

	/**
	 * 根据业务人员OID查询该人员所有的院内岗位聘任业务信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtYnGwEmployInfoDTO> listPtYnGwEmployInfoByBizPersonOid(
			Long bizPersonOid) throws ServiceException {
		return PtYnGwEmployInfoQueryHelper.listPtYnGwEmployInfoByBizPersonOid(bizPersonOid);
	}

	/**
	 * 根据基础OID查询该人员所有的院内岗位聘任业务信息
	 * @param baseYnGwEmployOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtYnGwEmployInfoDTO> listPtYnGwEmployInfoByBaseYnGwEmployOid(
			Long baseYnGwEmployOid) throws ServiceException {
		return PtYnGwEmployInfoQueryHelper.listPtYnGwEmployInfoByBaseYnGwEmployOid(baseYnGwEmployOid);
	}

}
