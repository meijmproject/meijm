package com.yh.hr.res.pt.queryhelper;

import com.yh.hr.res.pt.bo.PtOfficialInfo;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;


/**
 * @desc 公务员登记信息查询帮助类
 * @author luqy
 * @createDate 2016-11-5下午09:31:57
 */
public class PtOfficialInfoQueryHelper {

	/**
	 * 通过bizPersonOid获取
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static PtOfficialInfo getPtOfficialInfoDTOByBizPersonOid(Long bizPersonOid) throws ServiceException {
		return DaoUtil.uniqueResult("from PtOfficialInfo pt where pt.bizPersonOid = ? ", bizPersonOid);
	}
	
	/**
	 * 根据bizPersonOid删除公务员登记信息
	 * 
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtOfficialInfo poi where poi.bizPersonOid = " + bizPersonOid);
	}

}
