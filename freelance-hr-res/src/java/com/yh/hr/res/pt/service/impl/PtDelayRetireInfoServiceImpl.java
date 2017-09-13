package com.yh.hr.res.pt.service.impl;


import java.util.List;

import com.yh.hr.res.pt.bo.PtDelayRetireInfo;
import com.yh.hr.res.pt.dto.PtDelayRetireInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtDelayRetireInfoQueryHelper;
import com.yh.hr.res.pt.service.PtDelayRetireInfoService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

 public class PtDelayRetireInfoServiceImpl implements PtDelayRetireInfoService {
	
	/**
	 * 新增延迟退休信息
	 * @param serdto
	 * @return
	 * @throws ServiceException
	 */
	public void addPtDelayRetireInfo(PtDelayRetireInfoDTO ptDelayRetireInfoDTO)throws ServiceException{
			//dto转换为po
			PtDelayRetireInfo ptDelayRetrieInfo =  BeanHelper.copyProperties(ptDelayRetireInfoDTO, PtDelayRetireInfo.class);
			//得到操作人信息
			String userId = UserContext.getLoginUserID();
			String userName = UserContext.getLoginUserName();
			//设置新增人信息
			ptDelayRetrieInfo.setCreatedByCode(userId);
			ptDelayRetrieInfo.setCreatedByName(userName);
			ptDelayRetrieInfo.setCreatedDate(DateUtil.now());
			ptDelayRetrieInfo.save();

	 }
	
	/**
	 * 根据ID查询延迟退休信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */	
	public PtDelayRetireInfoDTO getPtDelayRetireInfoBybizPersonOid(Long bizPersonOid) throws ServiceException{
		return PtDelayRetireInfoQueryHelper.getPtDelayRetireInfoDTOById(bizPersonOid);
	}
	
	/**
	 * 修改延迟退休信息
	 * @param serdto
	 * @return
	 * @throws ServiceException
	 */
	public void updatePtDelayRetireInfo(PtDelayRetireInfoDTO ptDelayRetireInfoDTO) throws ServiceException{			
		PtDelayRetireInfo ptDelayRetrieInfo = BeanHelper.copyProperties(ptDelayRetireInfoDTO, PtDelayRetireInfo.class);
		//得到操作人信息
		String userId = UserContext.getLoginUserID();
		String userName = UserContext.getLoginUserName();
		//设置修改者信息
		ptDelayRetrieInfo.setUpdatedByCode(userId);
		ptDelayRetrieInfo.setUpdatedByName(userName);
		ptDelayRetrieInfo.setUpdatedDate(DateUtil.now());
		ptDelayRetrieInfo.update();

	}
	
	/**
	 * 根据bizPersonOid删除延迟退休信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public void deletePtDelayRetireInfoBybizPersonOid(Long bizPersonOid)throws ServiceException{
		try{
			//根据personOid查询数据
			PtDelayRetireInfo ptDelayRetrieInfo = DaoUtil.get(PtDelayRetireInfo.class, bizPersonOid);
			//数据存在，删除
			if(null != ptDelayRetrieInfo){
				ptDelayRetrieInfo.delete();
			}
		}catch (ServiceException e) {
			throw new ServiceException(null, "延迟退休信息删除失败");
		}
		
	}
	
	/**
	 * 离退休情况详细信息列表
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtDelayRetireInfoDTO> listPtDelayRetireInfo(Long bizPersonOid) throws ServiceException{
		return PtDelayRetireInfoQueryHelper.listPtDelayRetireInfo(bizPersonOid);
		
	}
}
