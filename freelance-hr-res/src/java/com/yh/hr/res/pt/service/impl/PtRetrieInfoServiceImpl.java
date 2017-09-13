package com.yh.hr.res.pt.service.impl;


import java.util.List;

import com.yh.hr.res.pt.bo.PtRetrieInfo;
import com.yh.hr.res.pt.dto.PtRetrieInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtRetrieInfoQueryHelper;
import com.yh.hr.res.pt.service.PtRetrieInfoService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

 public class PtRetrieInfoServiceImpl implements PtRetrieInfoService {
	
	/**
	 * 新增离退休情况详细信息
	 * @param serdto
	 * @return
	 * @throws ServiceException
	 */
	public void createPtRetrieInfo(PtRetrieInfoDTO serdto)throws ServiceException{
			//dto转换为po
			PtRetrieInfo ptRetrieInfo =  BeanHelper.copyProperties(serdto, PtRetrieInfo.class);
			//得到操作人信息
			String userId = UserContext.getLoginUserID();
			String userName = UserContext.getLoginUserName();
			//设置新增人信息
			ptRetrieInfo.setCreatedByCode(userId);
			ptRetrieInfo.setCreatedByName(userName);
			ptRetrieInfo.setCreatedDate(DateUtil.now());
			ptRetrieInfo.save();

	 }
	
	/**
	 * 根据ID查询离退休情况详细信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */	
	public PtRetrieInfoDTO getPtRetrieInfoByOid(Long bizPersonOid) throws ServiceException{
		return PtRetrieInfoQueryHelper.getPtRetireInfoDTOById(bizPersonOid);
	}
	
	/**
	 * 修改离退休情况详细信息
	 * @param serdto
	 * @return
	 * @throws ServiceException
	 */
	public void updatePtRetrieInfo(PtRetrieInfoDTO serdto) throws ServiceException{

			
		PtRetrieInfo ptRetrieInfo = BeanHelper.copyProperties(serdto, PtRetrieInfo.class);
		ptRetrieInfo.update();

	}
	
	/**
	 * 根据bizPersonOid删除离退休情况详细信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public void deletePtRetrieInfoByOid(Long bizPersonOid)throws ServiceException{
		try{
			//根据personOid查询数据
			PtRetrieInfo ptRetrieInfo = DaoUtil.get(PtRetrieInfo.class, bizPersonOid);
			//数据存在，删除
			if(null != ptRetrieInfo){
				ptRetrieInfo.delete();
			}
		}catch (ServiceException e) {
			throw new ServiceException(null, "离退休删除失败");
		}
		
	}
	
	/**
	 * 离退休情况详细信息列表
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtRetrieInfoDTO> listPtRetrieInfo(Long bizPersonOid) throws ServiceException{
		return PtRetrieInfoQueryHelper.listPtRetrieInfo(bizPersonOid);
		
	}
}
