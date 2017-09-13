package com.yh.hr.res.pb.queryhelper;

import java.util.ArrayList;
import java.util.List;

import com.yh.hr.res.pb.bo.PbRetrieInfo;
import com.yh.hr.res.pb.dto.PbRetrieInfoDTO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;

/**
 * 离退休查询帮助服务类
 * @author liuhw
 *
 */
public class PbRetrieInfoQueryHelper {

	/**
	 * 根据人员ID查询人员离退休信息
	 * @param personOid
	 */
	public static List<PbRetrieInfoDTO> findByPersonOid(Long personOid) throws DataAccessFailureException
	{
		List<PbRetrieInfo> list = DaoUtil.findByNamed("from PbRetrieInfo where personOid=:personOid", "personOid", personOid);
		if(CollectionUtils.isNotEmpty(list))
		{
			List<PbRetrieInfoDTO> dtoList = new ArrayList<PbRetrieInfoDTO>();
			for(PbRetrieInfo bo : list)
			{
				PbRetrieInfoDTO dto = new PbRetrieInfoDTO();
				BeanUtils.copyProperties(bo, dto);
				dtoList.add(dto);
			}
			return dtoList;
		}
		return null;
	}
}
