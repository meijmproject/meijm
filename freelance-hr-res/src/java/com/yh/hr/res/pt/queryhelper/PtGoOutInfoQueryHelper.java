package com.yh.hr.res.pt.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.pt.bo.PtGoOutInfo;
import org.springframework.util.CollectionUtils;

import com.yh.hr.res.pt.dto.PtGoOutInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 普通外出业务信息查询工具类
 * @author duxw
 *
 */
public class PtGoOutInfoQueryHelper {

	/**
	 * 根据bizPersonOid查询人员普通外出业务信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtGoOutInfoDTO> list(Long bizPersonOid) throws ServiceException {
		
		String hql = "from PtGoOutInfo fi where fi.bizPersonOid = :bizPersonOid order by fi.updateDate desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		List<PtGoOutInfo> boList = DaoUtil.find(hql, params);
		List<PtGoOutInfoDTO> dtoList = new ArrayList<PtGoOutInfoDTO>();
		
		if(!CollectionUtils.isEmpty(boList))
		{
			for(PtGoOutInfo bo : boList)
			{
				PtGoOutInfoDTO dto = new PtGoOutInfoDTO();
				BeanHelper.copyProperties(bo, dto);
				dtoList.add(dto);
			}
		}
		return dtoList;
	}
}
