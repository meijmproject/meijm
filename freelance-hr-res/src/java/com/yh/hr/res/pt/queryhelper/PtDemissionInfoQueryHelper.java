package com.yh.hr.res.pt.queryhelper;

import java.util.List;

import com.yh.hr.res.pt.dto.PtDemissionInfoDTO;
import org.apache.commons.collections.CollectionUtils;
import com.yh.hr.res.pt.bo.PtDemissionInfo;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 辞职信息查询工具类（业务）
 * @author lenghh
 *
 * 时间:2016-11-24下午03:09:39
 */
public class PtDemissionInfoQueryHelper
{

	public static PtDemissionInfoDTO findByBizPersonOid(Long bizPersonOid) throws ServiceException {
		String hql = "from PtDemissionInfo pd where pd.bizPersonOid ="+bizPersonOid;
		List<PtDemissionInfo> ptDemissionInfos = DaoUtil.find(hql);
		if(CollectionUtils.isNotEmpty(ptDemissionInfos)){
			return BeanHelper.copyProperties(ptDemissionInfos.get(0), PtDemissionInfoDTO.class);
		}
		return null;
	}
	
	/**
	 * 根据bizPersonOid删除公务员登记信息
	 * 
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtDemissionInfo pdi where pdi.bizPersonOid = " + bizPersonOid);
	}

}
