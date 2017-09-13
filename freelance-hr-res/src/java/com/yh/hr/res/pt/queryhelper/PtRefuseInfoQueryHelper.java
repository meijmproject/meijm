package com.yh.hr.res.pt.queryhelper;

import java.util.List;

import com.yh.hr.res.pt.bo.PtRefuseInfo;
import com.yh.hr.res.pt.dto.PtRefuseInfoDTO;
import org.apache.commons.collections.CollectionUtils;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 辞退信息查询工具类（业务）
 * @author lenghh
 *
 * 时间:2016-11-24下午03:09:39
 */
public class PtRefuseInfoQueryHelper
{

	public static PtRefuseInfoDTO findByBizPersonOid(Long bizPersonOid) throws ServiceException {
		String hql = "from PtRefuseInfo pf where pf.bizPersonOid ="+bizPersonOid;
		List<PtRefuseInfo> ptRefuseInfos = DaoUtil.find(hql);
		if(CollectionUtils.isNotEmpty(ptRefuseInfos)){
			return BeanHelper.copyProperties(ptRefuseInfos.get(0), PtRefuseInfoDTO.class);
		}
		return null;
	}
	
	/**
	 * 根据bizPersonOid删除辞退信息
	 * 
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtRefuseInfo pri where pri.bizPersonOid = " + bizPersonOid);
	}
}
