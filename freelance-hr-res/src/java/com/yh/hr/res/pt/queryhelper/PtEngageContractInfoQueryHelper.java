package com.yh.hr.res.pt.queryhelper;

import java.util.List;

import com.yh.hr.res.pt.dto.PtEngageContractInfoDTO;
import org.springframework.util.CollectionUtils;

import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pt.bo.PtEngageContractInfo;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * @desc 合同业务信息查询工具类
 * @author liul
 * @createDate 2017-2-17
 */
public class PtEngageContractInfoQueryHelper
{

	/**
	 * 新增时检查合同编号是否重复
	 * @param ptEngageContractInfoDto
	 * @return
	 * @throws ServiceException
	 **/
	public static boolean checkContractNo(PtEngageContractInfoDTO ptEngageContractInfoDto) throws ServiceException {
	
		List<PtEngageContractInfo> contractBo= DaoUtil.find("from PtEngageContractInfo r where r.contractNo = '"+ptEngageContractInfoDto.getContractNo()+"' and r.bizPersonOid='"+ptEngageContractInfoDto.getBizPersonOid()+"' ");
		if(CollectionUtils.isEmpty(contractBo)){
			return true;
		}
		return false;
	}
	
	/**
	 * 验证在聘状态规则
	 * @param ptEngageContractInfoDto
	 * @return
	 * @throws ServiceException
	 */
	public static boolean checkStatus(PtEngageContractInfoDTO ptEngageContractInfoDto) throws ServiceException {
		
		List<PtEngageContractInfo> contractBo= DaoUtil.find("from PtEngageContractInfo r where r.status = '"+ptEngageContractInfoDto.getStatus()+"' and r.status = '"+DicConstants.YHRS0116_1+"' and r.bizPersonOid='"+ptEngageContractInfoDto.getBizPersonOid()+"' ");
		if(CollectionUtils.isEmpty(contractBo)){
			return true;
		}
		return false;
	}
	
	/**
	 * 通过业务人员OID查找该人员的合同业务信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtEngageContractInfoDTO> listPtEngageContractInfoByBizPersonOid(Long bizPersonOid) throws ServiceException {
		List<PtEngageContractInfo> list = DaoUtil.findByNamed("from PtEngageContractInfo where bizPersonOid =:bizPersonOid and status='1' ", "bizPersonOid", bizPersonOid);
		return BeanHelper.copyProperties(list, PtEngageContractInfoDTO.class);
	}
	
	/**
	 * 通过基础OID查找该人员的合同业务信息
	 * @param baseEngageContractOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtEngageContractInfoDTO> listPtEngageContractInfoByBaseEngageContractOid(Long baseEngageContractOid) throws ServiceException {
		List<PtEngageContractInfo> list = DaoUtil.findByNamed("from PtEngageContractInfo where baseEngageContractOid =:baseEngageContractOid and status='1' ", "baseEngageContractOid", baseEngageContractOid);
		return BeanHelper.copyProperties(list, PtEngageContractInfoDTO.class);
	}
	
	/**
	 * 通过业务人员OID删除合同业务信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtEngageContractInfo where bizPersonOid='"+bizPersonOid+"'");
	}
}
