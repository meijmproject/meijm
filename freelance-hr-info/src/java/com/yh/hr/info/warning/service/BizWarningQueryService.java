package com.yh.hr.info.warning.service;


import com.yh.component.taglib.TableTagBean;
import com.yh.hr.info.warning.bo.BizWarningInfo;
import com.yh.hr.info.warning.dto.BizWarningPersonDTO;
import com.yh.platform.core.exception.ServiceException;
import net.sf.json.JSONObject;

import java.util.List;


/**
 * @description 业务预警接口
 * @author xuhj
 * @created 2011-3-7
 * @version 1.0
 * 
 */
public interface BizWarningQueryService
{
	/**
	 * 查询预警列表(查询日终预警人员表)
	 * @param ttb
	 * @return
	 */
	public List<JSONObject> listBizWarningInfo(TableTagBean ttb)throws ServiceException;

	/**
	 * 查询预警人员数
	 * @param ttb
	 * @return
	 */
	public long[] countBizWarningInfo(TableTagBean ttb)throws ServiceException;

	/**
	 * 查询预警人员列表
	 * @param bizWarningInfo
	 * @return
	 * @throws ServiceException
	 */
	public List<BizWarningPersonDTO> listBizWarningPersonInfo(BizWarningInfo bizWarningInfo)throws ServiceException;
	/**
	 * 查询预警人员列表(查询基础库)
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public List<JSONObject> listBaseWarningInfo(TableTagBean ttb)throws ServiceException;
	
	/**
	 * 根据预警事项环节代码和系统Id查询预警数量
	 * @param itemCode
	 * @param systemId
	 * @return
	 * @throws ServiceException
	 */
	public int getWarningCountByItemCodeAndSystemId(String itemCode,String systemId)throws ServiceException;
}
