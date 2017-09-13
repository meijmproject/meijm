package com.yh.hr.res.pt.queryhelper;

import java.util.List;

import com.yh.hr.res.pt.bo.PtWageInfluence;
import com.yh.hr.res.pt.dto.PtWageInfluenceDTO;
import com.yh.hr.res.unit.bo.UtUnit;
import com.yh.hr.res.unit.queryhelper.UtUnitQueryHelper;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringUtil;

public class PtWageInfluenceQueryHelper {

	public static PtWageInfluenceDTO getPtWageInfluenceDTOById(Long bizPersonOid) throws ServiceException {
		PtWageInfluence ptWageInfluence = DaoUtil.get(PtWageInfluence.class, bizPersonOid);
		if(null != ptWageInfluence)
		{
			PtWageInfluenceDTO dto = BeanHelper.copyProperties(ptWageInfluence, PtWageInfluenceDTO.class);
			if(null != dto.getWageServiceUnit())
			{
				UtUnit unit = UtUnitQueryHelper.get(dto.getWageServiceUnit());
				dto.setWageServiceUnitName(unit == null ? null : unit.getUnitName());
			}
			return dto;
		}
		else 
		{
			return null;
		}

	}

	public static List<PtWageInfluenceDTO> listPtWageInfluenceByPersonId(
			Long bizPersonOid) throws DataAccessFailureException, ServiceException {
		final StringBuffer hBuffer =  new StringBuffer("from PtWageInfluence pt where  1 =1 ");
		if(StringUtil.isNotNull(bizPersonOid)){
			hBuffer.append(" and  pt.bizPersonOid =" +bizPersonOid);
		}
		return BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), PtWageInfluenceDTO.class);
	}
	
	/**
	 * 根据bizPersonOid删除人员任职信息
	 * 
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtWageInfluence pt where pt.bizPersonOid = " + bizPersonOid);
	}	
	

}
