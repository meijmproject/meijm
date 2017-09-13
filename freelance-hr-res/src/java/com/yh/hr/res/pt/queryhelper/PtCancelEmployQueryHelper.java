package com.yh.hr.res.pt.queryhelper;

import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import com.yh.hr.res.pt.bo.PtCancelEmploy;
import com.yh.hr.res.pt.dto.PtCancelEmployDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 取消聘用信息查询工具类（业务）
 * @author lenghh
 *
 * 时间:2016-11-24下午03:09:39
 */
public class PtCancelEmployQueryHelper
{

	public static PtCancelEmployDTO findByBizPersonOid(Long bizPersonOid) throws ServiceException {
		String hql = "from PtCancelEmploy pe where pe.bizPersonOid ="+bizPersonOid;
		List<PtCancelEmploy> ptCancelEmploys = DaoUtil.find(hql);
		if(CollectionUtils.isNotEmpty(ptCancelEmploys)){
			return BeanHelper.copyProperties(ptCancelEmploys.get(0), PtCancelEmployDTO.class);
		}
		return null;
	}
	
	/**
	 * 根据bizPersonOid删除取消聘用信息
	 * 
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtCancelEmploy pce where pce.bizPersonOid = " + bizPersonOid);
	}
}
