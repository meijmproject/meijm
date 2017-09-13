package com.yh.hr.component.yngw.service.impl;

import java.util.List;

import com.yh.hr.component.yngw.bo.GwYnInfo;
import com.yh.hr.component.yngw.dto.GwYnInfoDTO;
import com.yh.hr.component.yngw.service.GwYnInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.yngw.queryhelper.GwYnInfoQueryHelper;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class GwYnInfoServiceImpl implements GwYnInfoService {
	
	/*
	 * (non-Javadoc)
	 * @see GwYnInfoService#create(GwYnInfoDTO)
	 */
	public void create(GwYnInfoDTO gwYnInfoDTO) throws ServiceException {
		GwYnInfo bo = new GwYnInfo();
		BeanHelper.copyProperties(gwYnInfoDTO, bo);
		bo.setCreatedByCode(UserContext.getLoginUserID());
		bo.setCreatedByName(UserContext.getLoginUserName());
		bo.setCreatedDate(DateUtil.now());
		bo.save();
	}

	public void delete(Long positionOid) throws ServiceException {
		GwYnInfoQueryHelper.deleteByPositionOid(positionOid);
	}

	public GwYnInfoDTO get(Long positionOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(GwYnInfo.class, positionOid),GwYnInfoDTO.class);
	}

	public List<GwYnInfoDTO> find(TableTagBean ttb) throws ServiceException {
        return GwYnInfoQueryHelper.find(ttb);
	}

	public void update(GwYnInfoDTO gwYnInfoDTO) throws ServiceException {
		GwYnInfo bo = DaoUtil.get(GwYnInfo.class, gwYnInfoDTO.getPositionOid());
		if(bo!=null) {
			BeanHelper.copyProperties(gwYnInfoDTO, bo,BeanHelper.getNullPropertyNames(gwYnInfoDTO));
			bo.setUpdatedByCode(UserContext.getLoginUserID());
			bo.setUpdatedByName(UserContext.getLoginUserName());
			bo.setUpdatedDate(DateUtil.now());
			bo.update();
		}
	}
	/*
	 * (non-Javadoc)
	 * @see GwYnInfoService#listPositionName(TableTagBean)
	 */
	public List<GwYnInfoDTO> listPositionName(TableTagBean ttb) throws ServiceException 
	{
		List<GwYnInfoDTO> hisPositionNameList = GwYnInfoQueryHelper.listByCondition(ttb);//岗位名称（小类）列表
		if(CollectionUtils.isEmpty(hisPositionNameList)) 
		{
			return null;
		}
		//查询记录是否含有小类
		for(GwYnInfoDTO dto:hisPositionNameList){
			String branch = GwYnInfoQueryHelper.haveBranchPositionName(dto.getPositionOid());
			if(StringUtils.isNotEmpty(branch)){
				dto.setHaveBranchPositionName("true");
			}
		}
		return hisPositionNameList;
	}
	public List<GwYnInfoDTO> listAllGwYnInfo() throws ServiceException {
		return GwYnInfoQueryHelper.listAllGwYnInfo();
	}
	
	/*
	 * 确认传入的岗位名称(大类)是否已经创建
	 */
	public boolean positionNameParentNodeIsExist(String positionNameDl) throws ServiceException {
		String r = GwYnInfoQueryHelper.positionNameParentNodeIsExist(positionNameDl);
		if("".equals(r)){
			return false;	//输入的岗位名称(大类),没有创建父节点,也就是小类为空的记录
		}else{
			return true;	//输入的岗位名称(大类)已经创建
		}
		
	}

	/*
	 * (non-Javadoc)
	 * @see GwYnInfoService#findPositionInfoByPositionType(java.lang.String)
	 */
	public List<GwYnInfoDTO> findPositionInfoByPositionType(String positionType)
			throws ServiceException 
	{
		return GwYnInfoQueryHelper.findPositionInfoByPositionType(positionType);
	}

}
