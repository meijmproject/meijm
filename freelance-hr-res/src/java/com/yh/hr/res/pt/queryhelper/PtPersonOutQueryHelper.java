package com.yh.hr.res.pt.queryhelper;

import java.util.List;

import com.yh.hr.res.pt.bo.PtPersonOut;
import com.yh.hr.res.pt.dto.PtPersonOutDTO;
import org.apache.commons.collections.CollectionUtils;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 人员调离信息查询工具类（业务）
 * @author lenghh
 *
 * 时间:2016-11-24下午03:09:39
 */
public class PtPersonOutQueryHelper
{

	public static PtPersonOutDTO findByBizPersonOid(Long bizPersonOid) throws ServiceException {
		String hql = "from PtPersonOut pp where pp.bizPersonOid ="+bizPersonOid;
		List<PtPersonOut> ptPersonOuts = DaoUtil.find(hql);
		if(CollectionUtils.isNotEmpty(ptPersonOuts)){
			return BeanHelper.copyProperties(ptPersonOuts.get(0), PtPersonOutDTO.class);
		}
		return null;
	}
	
	/**
	 * 根据bizPersonOid删除人员调离信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtPersonOut ppo where ppo.bizPersonOid = " + bizPersonOid);
	}
	
}
