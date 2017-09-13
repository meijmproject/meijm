package com.yh.hr.res.pt.queryhelper;

import java.util.List;

import com.yh.hr.res.pt.bo.PtExserviceInfo;
import com.yh.hr.res.pt.dto.PtExserviceInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 退役军人查询工具类
 * @author wuxq	
 *@createDate 2016-11-21
 */
public class PtExserviceInfoQueryHelper {
	/**
	 * 通过bizPersonOid获取退役军人信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static PtExserviceInfoDTO getExserviceInfoDTOById(Long bizPersonOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtExserviceInfo.class, bizPersonOid), PtExserviceInfoDTO.class);
	}
	 /**
	  * 获取退役军人信息列表
	  * @param bizPersonOid
	  * @return
	  * @throws ServiceException
	  */
	public static List<PtExserviceInfoDTO> listPtExserviceInfo(Long bizPersonOid) throws ServiceException {
		final StringBuffer hBuffer =  new StringBuffer("from PtExserviceInfo pt where  1 =1 ");
		hBuffer.append(" and  pt.bizPersonOid =" +bizPersonOid);
		hBuffer.append(" order by pt.exserviceApprovalDate desc");
		return BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), PtExserviceInfoDTO.class);
	}
}
