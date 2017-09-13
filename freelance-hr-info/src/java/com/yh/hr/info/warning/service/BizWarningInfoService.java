package com.yh.hr.info.warning.service;


import com.yh.component.taglib.TableTagBean;
import com.yh.hr.info.warning.bo.BizWarningInfo;
import com.yh.hr.info.warning.dto.BizWarConfigInfoDTO;
import com.yh.hr.info.warning.dto.BizWarningInfoDTO;
import com.yh.platform.core.exception.ServiceException;

import java.util.List;


/**
 * @description 预警信息服务
 * @author xuhj
 * @created 2011-3-7
 * @version 1.0
 * 
 */
public interface BizWarningInfoService
{
	/**
	 * @param  获取所有预警信息 
	 * @return List<BaseGuazhi>
	 * @throws ServiceException
	 */
    public List<BizWarningInfo> listAll() throws ServiceException;
	/**
	 * 预警业务事项-记录
	 * @param itemCode				业务事项
	 * @return bizWarningInfo		预警事项-记录
	 * @throws ServiceException
	 */
	public BizWarningInfo getSystemBizWarningInfo(String itemCode) throws ServiceException;
	
	/**
	 * 设置预警天数
	 * @throws ServiceException
	 */
	public   void updateWarningDays(BizWarningInfoDTO dto) throws ServiceException;
	
	/**
	 * 根据系统id查询预警事项集合
	 * @param systemId
	 * @return
	 * @throws ServiceException
	 */
	public List<BizWarningInfoDTO> listBizWarningSetingInfo(String systemId)throws ServiceException;
	/**
	 * 通过日终任务插入预警人员
	 * @param  schedulerFlag
	 * @throws ServiceException
	 */
	public void insertWarningPerson(String schedulerFlag)throws ServiceException;
	public List<BizWarConfigInfoDTO> listBizWarningInfo(TableTagBean ttb) throws ServiceException;
	public String getBizWarningDays(String itemCode) throws ServiceException;
}
