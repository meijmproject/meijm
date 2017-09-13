package com.yh.hr.res.pt.queryhelper;

import java.util.List;

import com.yh.hr.res.pt.bo.PtEngageConHistInfo;
import com.yh.hr.res.pt.dto.PtEngageContractInfoDTO;
import org.springframework.util.CollectionUtils;

import com.yh.hr.res.pt.dto.PtEngageConHistInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * @desc 合同变动历史业务信息查询工具类
 * @author liul
 * @createDate 2017-2-17
 */
public class PtEngageConHistInfoQueryHelper
{
	/**
	 * 新增时检查合同编号是否重复
	 * @param ptEngageConHistInfoDto
	 * @return
	 * @throws ServiceException
	 **/
	public static boolean checkContractNo(PtEngageConHistInfoDTO ptEngageConHistInfoDto) throws ServiceException {
	
		List<PtEngageConHistInfoDTO> contractBo= DaoUtil.find("from PtEngageConHistInfo r where r.contractNo = '"+ptEngageConHistInfoDto.getContractNo()+"' and r.bizPersonOid='"+ptEngageConHistInfoDto.getBizPersonOid()+"' ");
		if(CollectionUtils.isEmpty(contractBo)){
			return true;
		}
		return false;
	}


	/**
	 * 用业务人员OID查找该人员的合同历史信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtEngageConHistInfoDTO> listPtEngageConHistInfoByBizPersonOid(Long bizPersonOid) throws ServiceException {
		List<PtEngageConHistInfo> list = DaoUtil.findByNamed("from PtEngageConHistInfo ech where ech.bizPersonOid =:bizPersonOid order by ech.contractBegin desc", "bizPersonOid", bizPersonOid);
		return BeanHelper.copyProperties(list, PtEngageConHistInfoDTO.class);
	}
	
	/**
	 * 通过基础OID查找该人员的合同业务信息
	 * @param baseEngageContractOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtEngageContractInfoDTO> listPtEngageContractInfoByBaseEngageContractOid(Long baseEngageContractOid) throws ServiceException {
		List<PtEngageConHistInfo> list = DaoUtil.findByNamed("from PtEngageConHistInfo ech where ech.baseEngageContractOid =:baseEngageContractOid order by ech.contractBegin desc", "baseEngageContractOid", baseEngageContractOid);
		return BeanHelper.copyProperties(list, PtEngageContractInfoDTO.class);
	}


	/**
	 * 通过业务人员OID删除该人员的所有合同历史信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtEngageConHistInfo where bizPersonOid='"+bizPersonOid+"'");
	}
}
