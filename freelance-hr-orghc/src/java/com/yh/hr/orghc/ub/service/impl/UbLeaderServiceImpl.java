package com.yh.hr.orghc.ub.service.impl;

import java.util.List;

import com.yh.hr.component.ld.service.LdFlowApprovedService;
import com.yh.hr.orghc.ub.bo.UbLeader;
import com.yh.hr.orghc.ub.dto.UbLeaderDTO;
import com.yh.hr.orghc.ub.queryhelper.UbLeaderQueryHelper;
import com.yh.hr.orghc.ub.service.UbLeaderService;
import com.yh.hr.orghc.ub.service.UbUnitService;
import com.yh.hr.orghc.ub.util.UbLeaderModifyServiceUtil;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

public class UbLeaderServiceImpl implements UbLeaderService {

	public LdFlowApprovedService ldFlowApprovedService = (LdFlowApprovedService)SpringBeanUtil.getBean("ldFlowApprovedService");
	public UbUnitService ubUnitService = (UbUnitService) SpringBeanUtil.getBean("ubUnitService");

	/* (non-Javadoc)
	 * @see UbLeaderService#getUbOrgDTOById(java.lang.Long)
	 */
	@Override
	public UbLeaderDTO getUbOrgDTOById(Long leaderOid) throws ServiceException {
		return UbLeaderQueryHelper.getUbOrgDTOById(leaderOid);
	}

	/* (non-Javadoc)
	 * @see UbLeaderService#listByUnitOid(java.lang.Long)
	 */
	@Override
	public List<UbLeaderDTO> listByUnitOid(Long unitOid) throws ServiceException {
		return UbLeaderQueryHelper.listByUnitOid(unitOid);
	}

	/* (non-Javadoc)
	 * @see UbLeaderService#createLeaderInfo(UbLeaderDTO)
	 */
	@Override
	public void createLeaderInfo(UbLeaderDTO ubLeaderDTO) throws ServiceException {
		UbLeader ubLeader = new UbLeader();
		BeanHelper.copyProperties(ubLeaderDTO, ubLeader);
		ubLeader.setCreateBy(UserContext.getLoginUserID());
		ubLeader.setCreateName(UserContext.getLoginUserName());
		ubLeader.setCreateDate(DateUtil.now());
		ubLeader.save();
	//	UbLeaderModifyServiceUtil.modifyUbLeader(ubLeader);
	}

	/**
	 * 新增领导职数--单位信息校核
	 * @param ubOrgDTO
	 * @param chgCount
     * @throws ServiceException 
	 */
	@Override
	public void createLeaderInfo(UbLeaderDTO ubLeaderDTO,Long chgCount) throws ServiceException {
		UbLeader ubLeader = new UbLeader();
		BeanHelper.copyProperties(ubLeaderDTO, ubLeader);
		ubLeader.setCreateBy(UserContext.getLoginUserID());
		ubLeader.setCreateName(UserContext.getLoginUserName());
		ubLeader.setCreateDate(DateUtil.now());
		ubLeader.save();
		UbLeaderModifyServiceUtil.modifyUbLeader(ubLeader, chgCount);
	}

	/* (non-Javadoc)
	 * @see UbLeaderService#updateLeaderInfo(UbLeaderDTO)
	 */
	@Override
	public void updateLeaderInfo(UbLeaderDTO ubLeaderDTO) throws ServiceException {
		UbLeader ubLeader = DaoUtil.get(UbLeader.class, ubLeaderDTO.getLeaderOid());
		BeanHelper.copyProperties(ubLeaderDTO, ubLeader);
		ubLeader.setUpdateBy(UserContext.getLoginUserID());
		ubLeader.setUpdateName(UserContext.getLoginUserName());
		ubLeader.setUpdateDate(DateUtil.now());
		ubLeader.update();
	//	UbLeaderModifyServiceUtil.modifyUbLeader(ubLeader);
	}
	
	/**
	 * 修改领导职数--单位信息校核
	 * @param ubOrgDTO
	 * @param chgCount
     * @throws ServiceException 
	 */
	@Override
	public void updateLeaderInfo(UbLeaderDTO ubLeaderDTO,Long chgCount) throws ServiceException {
		UbLeader ubLeader = DaoUtil.get(UbLeader.class, ubLeaderDTO.getLeaderOid());
		BeanHelper.copyProperties(ubLeaderDTO, ubLeader);
		ubLeader.setUpdateBy(UserContext.getLoginUserID());
		ubLeader.setUpdateName(UserContext.getLoginUserName());
		ubLeader.setUpdateDate(DateUtil.now());
		ubLeader.update();
		UbLeaderModifyServiceUtil.modifyUbLeader(ubLeader, chgCount);
	}

	/* (non-Javadoc)
	 * @see UbLeaderService#deleteLeaderInfo(java.lang.Long)
	 */
	@Override
	public void deleteLeaderInfo(Long leaderOid) throws ServiceException {
		UbLeader ubLeader = DaoUtil.get(UbLeader.class, leaderOid);
		ubLeader.delete();
		//暂不支持直接删除
	}

	@Override
	public int countAdminUnitLeader(Long adminUnitOid,String positionLevelCode) throws ServiceException {
		return UbLeaderQueryHelper.countAdminUnitLeader(adminUnitOid,positionLevelCode);
	}
}
