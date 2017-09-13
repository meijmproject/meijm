package com.yh.hr.res.pt.queryhelper;

import java.util.List;

import com.yh.hr.res.pt.bo.PtDeathInfo;
import com.yh.hr.res.pt.dto.PtDeathInfoDTO;
import org.apache.commons.collections.CollectionUtils;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 自然减员信息查询工具类（业务）
 * @author lenghh
 *
 * 时间:2016-11-24下午03:09:39
 */
public class PtDeathInfoQueryHelper
{

	public static PtDeathInfoDTO findByBizPersonOid(Long bizPersonOid) throws ServiceException {
		String hql = "from PtDeathInfo pd where pd.bizPersonOid ="+bizPersonOid;
		List<PtDeathInfo> ptDeathInfos = DaoUtil.find(hql);
		if(CollectionUtils.isNotEmpty(ptDeathInfos)){
			return BeanHelper.copyProperties(ptDeathInfos.get(0), PtDeathInfoDTO.class);
		}
		return null;
	}
	
	/**
	 * 根据bizPersonOid删除自然减员信息
	 * 
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtDeathInfo pdi where pdi.bizPersonOid = " + bizPersonOid);
	}
	
}
