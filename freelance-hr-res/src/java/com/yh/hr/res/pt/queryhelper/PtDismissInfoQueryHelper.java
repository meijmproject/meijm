package com.yh.hr.res.pt.queryhelper;

import java.util.List;

import com.yh.hr.res.pt.bo.PtDismissInfo;
import com.yh.hr.res.pt.dto.PtDismissInfoDTO;
import org.apache.commons.collections.CollectionUtils;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 开除信息查询工具类（业务）
 * @author lenghh
 *
 * 时间:2016-11-24下午03:09:39
 */
public class PtDismissInfoQueryHelper
{

	public static PtDismissInfoDTO findByBizPersonOid(Long bizPersonOid) throws ServiceException {
		String hql = "from PtDismissInfo pd where pd.bizPersonOid ="+bizPersonOid;
		List<PtDismissInfo> ptDismissInfos = DaoUtil.find(hql);
		if(CollectionUtils.isNotEmpty(ptDismissInfos)){
			return BeanHelper.copyProperties(ptDismissInfos.get(0), PtDismissInfoDTO.class);
		}
		return null;
	}
	
	/**
	 * 根据bizPersonOid删除开除信息
	 * 
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtDismissInfo pdi where pdi.bizPersonOid = " + bizPersonOid);
	}
}
