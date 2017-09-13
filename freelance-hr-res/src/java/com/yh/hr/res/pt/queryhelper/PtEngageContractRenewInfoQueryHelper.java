package com.yh.hr.res.pt.queryhelper;

import com.yh.hr.res.pt.bo.PtEngageContractRenew;
import com.yh.hr.res.pt.dto.PtEngageContractRenewDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @desc 续签合同信息查询工具类
 * @author liul
 * @createDate 2017-2-17
 */
public class PtEngageContractRenewInfoQueryHelper
{

	/**
	 * 根据业务人员Id获取续签合同信息
	 * @param
	 * */
	public static PtEngageContractRenewDTO getContractRenewInfoDTO(Long bizPersonOid) throws ServiceException {
		PtEngageContractRenewDTO ptEngageContractRenewDTO = new PtEngageContractRenewDTO();
		List<PtEngageContractRenew> contractBoList= DaoUtil.find("from PtEngageContractRenew r where r.bizPersonOid='"+bizPersonOid+"'");
		if(!CollectionUtils.isEmpty(contractBoList)){
			BeanHelper.copyProperties(contractBoList.get(0),ptEngageContractRenewDTO);
			return ptEngageContractRenewDTO;
		}
		return null;
	}
	/**
	 * 根据基础合同信息获取续签合同信息
	 * @param
	 * */
	public static PtEngageContractRenewDTO getContractRenewInfoDTOByBaseContractOid(Long engageContractOid) throws ServiceException {
		PtEngageContractRenewDTO ptEngageContractRenewDTO = new PtEngageContractRenewDTO();
		List<PtEngageContractRenew> contractBoList= DaoUtil.find("from PtEngageContractRenew r where r.baseEngageContractOid='"+engageContractOid+"'");
		if(!CollectionUtils.isEmpty(contractBoList)){
			BeanHelper.copyProperties(contractBoList.get(0),ptEngageContractRenewDTO);
			return ptEngageContractRenewDTO;
		}
		return null;
	}
}
