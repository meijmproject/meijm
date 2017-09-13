package com.yh.hr.res.pt.service.impl;

import com.yh.hr.res.pt.bo.PtEngageContractRenew;
import com.yh.hr.res.pt.dto.PtEngageContractRenewDTO;
import com.yh.hr.res.pt.service.PtEngageContractRenewService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class PtEngageContractRenewServiceImpl implements PtEngageContractRenewService {

	/**
	 * 新增续签合同业务信息
	 *
	 * @param contractRenewDTO
	 * @return
	 * @throws com.yh.platform.core.exception.ServiceException
	 */
	public void createPtEngageContractRenew(PtEngageContractRenewDTO contractRenewDTO) throws ServiceException {

			// dto转换为po
			PtEngageContractRenew ptEngageContractRenew = new PtEngageContractRenew();
			BeanHelper.copyProperties(contractRenewDTO, ptEngageContractRenew);

			// 设置新增人信息
		ptEngageContractRenew.setCreateBy(UserContext.getLoginUserID());
		ptEngageContractRenew.setCreateName(UserContext.getLoginUserName());
		ptEngageContractRenew.setCreateDate(DateUtil.now());
		ptEngageContractRenew.save();

	}

	/**
	 * 根据ID查询续签合同业务信息
	 * @param bizEngageContractOid
	 * @return
	 * @throws com.yh.platform.core.exception.ServiceException
	 */
	public PtEngageContractRenewDTO getPtEngageContractRenewByOid(Long bizEngageContractOid) throws ServiceException{
		//查询数据
		PtEngageContractRenew ptEngageContractRenew = DaoUtil.get(PtEngageContractRenew.class, bizEngageContractOid);
		//po转换为dto
		PtEngageContractRenewDTO ptEngageContractRenewDTO = new PtEngageContractRenewDTO();
		return BeanHelper.copyRtnProperties(ptEngageContractRenew, ptEngageContractRenewDTO);

	}

	/**
	 * 更新续签合同业务信息
	 * @param contractRenewDTO
	 * @return
	 * @throws com.yh.platform.core.exception.ServiceException
	 */
	public void updatePtEngageContractRenew(PtEngageContractRenewDTO contractRenewDTO) throws ServiceException{

			// dto转换为po
			PtEngageContractRenew ptEngageContractRenew = DaoUtil.get(PtEngageContractRenew.class, contractRenewDTO.getBizEngageContractOid());
			contractRenewDTO.setCreateDate(ptEngageContractRenew.getCreateDate());
			contractRenewDTO.setCreateBy(ptEngageContractRenew.getCreateBy());
			contractRenewDTO.setCreateName(ptEngageContractRenew.getCreateName());
			//PtReviewInfo PtReviewInfo =  DaoUtil.get(PtReviewInfo.class, PtReviewInfoDTO.getReviewOid());//new PtReviewInfo();
			if(null != ptEngageContractRenew){
				BeanHelper.copyProperties(contractRenewDTO, ptEngageContractRenew,new String[]{"createdDate","createdByCode","createdByName"});
				// 设置修改人信息
				ptEngageContractRenew.setUpdateBy(UserContext.getLoginUserID());
				ptEngageContractRenew.setUpdateName(UserContext.getLoginUserName());
				ptEngageContractRenew.setUpdateDate(DateUtil.now());
				ptEngageContractRenew.update();
			}

	}

	/**
	 * 根据ID删除续签合同业务信息
	 * @param bizEngageContractOid
	 * @return
	 * @throws com.yh.platform.core.exception.ServiceException
	 */
	public void deletePtEngageContractRenewByOid(Long bizEngageContractOid) throws ServiceException{

			//查询数据
			PtEngageContractRenew ptEngageContractRenew = DaoUtil.get(PtEngageContractRenew.class, bizEngageContractOid);
			//判断数据是否存在，存在则删除
			if(null != ptEngageContractRenew){
				ptEngageContractRenew.delete();
			}

	}
}
