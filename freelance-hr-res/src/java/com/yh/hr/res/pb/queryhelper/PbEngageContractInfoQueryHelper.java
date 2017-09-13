package com.yh.hr.res.pb.queryhelper;

import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pb.bo.PbEngageContractInfo;
import com.yh.hr.res.pb.dto.PbEngageContractInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc 合同信息查询工具类
 * @author liul
 * @createDate 2017-2-17
 */
public class PbEngageContractInfoQueryHelper
{

	/**
	 * 新增时检查合同编号是否重复
	 * @param 
	 * */
	public static boolean checkContractNo(PbEngageContractInfoDTO pbEngageContractInfoDto) throws ServiceException {
	
		List<PbEngageContractInfo> contractBo= DaoUtil.find("from PbEngageContractInfo r where r.contractNo = '"+pbEngageContractInfoDto.getContractNo()+"' and r.personOid='"+pbEngageContractInfoDto.getPersonOid()+"' ");
		if(CollectionUtils.isEmpty(contractBo)){
			return true;
		}
		return false;
	}
	/**
	 * 验证在聘状态规则
	 * @param pbEngageContractInfoDto
	 * @return
	 * @throws ServiceException
	 */
	public static boolean checkStatus(PbEngageContractInfoDTO pbEngageContractInfoDto) throws ServiceException {
		
		List<PbEngageContractInfo> contractBo= DaoUtil.find("from PbEngageContractInfo r where r.status = '"+pbEngageContractInfoDto.getStatus()+"' and r.status = '"+DicConstants.YHRS0116_1+"' and r.personOid='"+pbEngageContractInfoDto.getPersonOid()+"' ");
		if(CollectionUtils.isEmpty(contractBo)){
			return true;
		}
		return false;
	}
	
	/**
	 * 用人员id查找该人员的合同信息
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public static PbEngageContractInfo getPbEngageContractInfoByPersonOid(Long personOid) throws ServiceException {
		List<PbEngageContractInfo> list = DaoUtil.findByNamed("from PbEngageContractInfo where personOid =:personOid and status='1' ", "personOid", personOid);
		if(CollectionUtils.isEmpty(list)) return null;
		return list.get(0);
	}

	/**
	 * 用人员id查找该人员的合同信息
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PbEngageContractInfoDTO> listPbEngageContractInfoByPersonOid(Long personOid) throws ServiceException {
		List<PbEngageContractInfo> list = DaoUtil.findByNamed("from PbEngageContractInfo where personOid =:personOid and status='1' ", "personOid", personOid);
		List<PbEngageContractInfoDTO> listDTO = new ArrayList<PbEngageContractInfoDTO>();
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}else{
			for(PbEngageContractInfo pbEngageContractInfo:list){
				PbEngageContractInfoDTO pbEngageContractInfoDTO = new PbEngageContractInfoDTO();
				BeanHelper.copyProperties(pbEngageContractInfo,pbEngageContractInfoDTO);
				listDTO.add(pbEngageContractInfoDTO);
			}
			return listDTO;
		}
	}
	
	/**
	 * 通过人员id删除
	 * @param personOid
	 */
	public static void deleteByPersonOid(Long personOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PbEngageContractInfo where personOid='"+personOid+"'");
	}
}
