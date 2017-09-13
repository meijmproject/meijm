package com.yh.hr.res.pt.service.impl;

import com.yh.hr.res.pt.bo.PtEngageContractInfo;
import com.yh.hr.res.pt.dto.PtEngageContractInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtEngageContractInfoQueryHelper;
import com.yh.hr.res.pt.service.PtEngageContractInfoService;
import jade.workflow.utils.DateUtil;

import java.util.List;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.web.UserContext;
/**
 * 合同业务信息service实现类
 * @author wangx
 * @date 2017-05-26
 * @version 1.0
 */
public class PtEngageContractInfoServiceImpl implements
		PtEngageContractInfoService {

	/**
	 * 新增合同业务信息
	 * @param ptEngageContractInfoDto
	 * @return 
	 * @throws ServiceException
	 */
	public Long create(PtEngageContractInfoDTO ptEngageContractInfoDto)
			throws ServiceException {
		PtEngageContractInfo ptEngageContractInfo = new PtEngageContractInfo();
		BeanHelper.copyProperties(ptEngageContractInfoDto, ptEngageContractInfo);
		ptEngageContractInfo.setCreateBy(UserContext.getLoginUserID());
		ptEngageContractInfo.setCreateName(UserContext.getLoginUserName());
		ptEngageContractInfo.setCreateDate(DateUtil.now());
		ptEngageContractInfo.setUpdateBy(UserContext.getLoginUserID());
		ptEngageContractInfo.setUpdateName(UserContext.getLoginUserName());
		ptEngageContractInfo.setUpdateDate(DateUtil.now());
		ptEngageContractInfo.save();
		return ptEngageContractInfo.getEngageContractOid();
	}

	/**
	 * 通过主键ID获取合同业务信息
	 * @param ptEngageContractInfoId
	 * @return 
	 * @throws ServiceException
	 */
	public PtEngageContractInfoDTO get(Long ptEngageContractInfoId)
			throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtEngageContractInfo.class, ptEngageContractInfoId), PtEngageContractInfoDTO.class);
	}

	/**
	 * 修改合同业务信息
	 * @param ptEngageContractInfoDto
	 * @throws ServiceException
	 */ 
	public void update(PtEngageContractInfoDTO ptEngageContractInfoDto)
			throws ServiceException {
		PtEngageContractInfo ptEngageContractInfo = DaoUtil.get(PtEngageContractInfo.class, ptEngageContractInfoDto.getEngageContractOid());
		if(ptEngageContractInfo!=null) {
			BeanHelper.copyProperties(ptEngageContractInfoDto, ptEngageContractInfo, BeanHelper.getNullPropertyNames(ptEngageContractInfoDto));
			ptEngageContractInfo.setUpdateBy(UserContext.getLoginUserID());
			ptEngageContractInfo.setUpdateName(UserContext.getLoginUserName());
			ptEngageContractInfo.setUpdateDate(DateUtil.now());
			ptEngageContractInfo.update();
		}
	}

	/**
	 * 通过主键ID删除合同业务信息
	 * @param ptEngageContractInfoId
	 * @throws ServiceException
	 */   
	public void delete(Long ptEngageContractInfoId) throws ServiceException {
		PtEngageContractInfo ptEngageContractInfo = DaoUtil.get(PtEngageContractInfo.class, ptEngageContractInfoId);
		if(ptEngageContractInfo!=null) {
			ptEngageContractInfo.delete();
		}
	}

	/**
	 * 新增时检查合同编号是否重复
	 * @param ptEngageContractInfoDto
	 * @return
	 * @throws ServiceException
	 **/
	public boolean checkContractNo(
			PtEngageContractInfoDTO ptEngageContractInfoDto)
			throws ServiceException {
		return PtEngageContractInfoQueryHelper.checkContractNo(ptEngageContractInfoDto);
	}

	/**
	 * 验证在聘状态规则
	 * @param ptEngageContractInfoDto
	 * @return
	 * @throws ServiceException
	 */
	public boolean checkStatus(PtEngageContractInfoDTO ptEngageContractInfoDto)
			throws ServiceException {
		return PtEngageContractInfoQueryHelper.checkStatus(ptEngageContractInfoDto);
	}

	/**
	 * 通过业务人员OID查找该人员的合同业务信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtEngageContractInfoDTO> listPtEngageContractInfoByPersonOid(
			Long bizPersonOid) throws ServiceException {
		return PtEngageContractInfoQueryHelper.listPtEngageContractInfoByBizPersonOid(bizPersonOid);
	}

	/**
	 * 通过基础OID查找该人员的合同业务信息
	 * @param baseEngageContractOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtEngageContractInfoDTO> listPtEngageContractInfoByBaseEngageContractOid(
			Long baseEngageContractOid) throws ServiceException {
		return PtEngageContractInfoQueryHelper.listPtEngageContractInfoByBaseEngageContractOid(baseEngageContractOid);
	}

	/**
	 * 通过业务人员OID删除合同业务信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		PtEngageContractInfoQueryHelper.deleteByBizPersonOid(bizPersonOid);
	}

}
