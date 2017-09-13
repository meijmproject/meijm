package com.yh.hr.res.pt.service.impl;

import com.yh.hr.res.pt.dto.PtEngageContractInfoDTO;
import jade.workflow.utils.DateUtil;

import java.util.List;

import com.yh.hr.res.pt.bo.PtEngageConHistInfo;
import com.yh.hr.res.pt.dto.PtEngageConHistInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtEngageConHistInfoQueryHelper;
import com.yh.hr.res.pt.service.PtEngageConHistInfoService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.web.UserContext;
/**
 * 合同变动历史业务信息service实现类
 * @author wangx
 * @date 2017-05-26
 * @version 1.0
 */
public class PtEngageConHistInfoServiceImpl implements
		PtEngageConHistInfoService {

	/**
	 * 创建合同变动历史业务信息
	 * @param ptEngageConHistInfoDto
	 * @return 
	 * @throws ServiceException
	 */
	public Long create(PtEngageConHistInfoDTO ptEngageConHistInfoDto)
			throws ServiceException {
		PtEngageConHistInfo ptEngageConHistInfo = new PtEngageConHistInfo();
		BeanHelper.copyProperties(ptEngageConHistInfoDto, ptEngageConHistInfo);
		ptEngageConHistInfo.setCreateBy(UserContext.getLoginUserID());
		ptEngageConHistInfo.setCreateName(UserContext.getLoginUserName());
		ptEngageConHistInfo.setCreateDate(DateUtil.now());
		ptEngageConHistInfo.setUpdateBy(UserContext.getLoginUserID());
		ptEngageConHistInfo.setUpdateName(UserContext.getLoginUserName());
		ptEngageConHistInfo.setUpdateDate(DateUtil.now());
		ptEngageConHistInfo.save();
		return ptEngageConHistInfo.getEngageContractHistOid();
	}

	/**
	 * 通过主键ID获取合同变动历史业务信息
	 * @param ptEngageConHistInfoId
	 * @return 
	 * @throws ServiceException
	 */
	public PtEngageConHistInfoDTO get(Long ptEngageConHistInfoId)
			throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtEngageConHistInfo.class, ptEngageConHistInfoId), PtEngageConHistInfoDTO.class);
	}

	/**
	 * 通过主键ID获取合同变动历史业务信息
	 * @param ptEngageConHistInfoId
	 * @throws ServiceException
	 */ 
	public void update(PtEngageConHistInfoDTO ptEngageConHistInfoDto)
			throws ServiceException {
		PtEngageConHistInfo ptEngageConHistInfo = DaoUtil.get(PtEngageConHistInfo.class, ptEngageConHistInfoDto.getEngageContractHistOid());
		if(ptEngageConHistInfo!=null) {
			BeanHelper.copyProperties(ptEngageConHistInfoDto, ptEngageConHistInfo, BeanHelper.getNullPropertyNames(ptEngageConHistInfoDto));
			ptEngageConHistInfo.setUpdateBy(UserContext.getLoginUserID());
			ptEngageConHistInfo.setUpdateName(UserContext.getLoginUserName());
			ptEngageConHistInfo.setUpdateDate(DateUtil.now());
			ptEngageConHistInfo.update();
		}
	}

	/**
	 * 通过主键ID删除合同变动历史业务信息
	 * @param ptEngageConHistInfoId
	 * @throws ServiceException
	 */  
	public void delete(Long ptEngageConHistInfoId) throws ServiceException {
		PtEngageConHistInfo ptEngageConHistInfo = DaoUtil.get(PtEngageConHistInfo.class, ptEngageConHistInfoId);
		if(ptEngageConHistInfo!=null) {
			ptEngageConHistInfo.delete();
		}
	}

	/**
	 * 新增时检查合同编号是否重复
	 * @param ptEngageConHistInfoDto
	 * @return
	 * @throws ServiceException
	 **/
	public boolean checkContractNo(PtEngageConHistInfoDTO ptEngageConHistInfoDto)
			throws ServiceException {
		return PtEngageConHistInfoQueryHelper.checkContractNo(ptEngageConHistInfoDto);
	}

	/**
	 * 通过业务人员OID查找该人员的合同历史信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtEngageConHistInfoDTO> listPtEngageConHistInfoByBizPersonOid(
			Long bizPersonOid) throws ServiceException {
		return PtEngageConHistInfoQueryHelper.listPtEngageConHistInfoByBizPersonOid(bizPersonOid);
	}
	
	/**
	 * 通过基础OID查找该人员的合同业务信息
	 * @param baseEngageContractOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtEngageContractInfoDTO> listPtEngageContractInfoByBaseEngageContractOid(Long baseEngageContractOid) throws ServiceException {
		return PtEngageConHistInfoQueryHelper.listPtEngageContractInfoByBaseEngageContractOid(baseEngageContractOid);
	}

	/**
	 * 通过业务人员OID删除该人员的所有合同历史信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		PtEngageConHistInfoQueryHelper.deleteByBizPersonOid(bizPersonOid);
	}
}
