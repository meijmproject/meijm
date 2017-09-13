package com.yh.hr.res.pb.queryhelper;

import com.yh.hr.res.pb.bo.PbEngageConHistInfo;
import com.yh.hr.res.pb.dto.PbEngageConHistInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc 合同变动历史信息查询工具类
 * @author liul
 * @createDate 2017-2-17
 */
public class PbEngageConHistInfoQueryHelper
{
	/**
	 * 新增时检查合同编号是否重复
	 * @param 
	 * */
	public static boolean checkContractNo(PbEngageConHistInfoDTO pbEngageConHistInfoDto) throws ServiceException {
	
		List<PbEngageConHistInfoDTO> contractBo= DaoUtil.find("from PbEngageConHistInfo r where r.contractNo = '"+pbEngageConHistInfoDto.getContractNo()+"' and r.personOid='"+pbEngageConHistInfoDto.getPersonOid()+"' ");
		if(CollectionUtils.isEmpty(contractBo)){
			return true;
		}
		return false;
	}


	/**
	 * 用人员id查找该人员的合同历史信息
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PbEngageConHistInfoDTO> listPbEngageConHistInfoByPersonOid(Long personOid) throws ServiceException {
		List<PbEngageConHistInfo> list = DaoUtil.findByNamed("from PbEngageConHistInfo ech where ech.personOid =:personOid order by ech.contractBegin desc", "personOid", personOid);
		List<PbEngageConHistInfoDTO> listDTO = new ArrayList<PbEngageConHistInfoDTO>();
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}else{
			for(PbEngageConHistInfo pbEngageConHistInfo:list){
				PbEngageConHistInfoDTO pbEngageConHistInfoDTO = new PbEngageConHistInfoDTO();
				BeanHelper.copyProperties(pbEngageConHistInfo, pbEngageConHistInfoDTO);
				listDTO.add(pbEngageConHistInfoDTO);
			}
			return listDTO;
		}
	}


	/**
	 * 删除该人员的所有合同历史信息
	 * @param personOid
	 * @throws ServiceException
	 */
	public static void deleteByPersonOid(Long personOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PbEngageConHistInfo where personOid='"+personOid+"'");
	}
}
