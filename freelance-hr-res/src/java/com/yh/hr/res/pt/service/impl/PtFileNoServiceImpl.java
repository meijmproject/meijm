package com.yh.hr.res.pt.service.impl;

import com.yh.hr.res.pt.bo.PtFileNo;
import com.yh.hr.res.pt.dto.PtFileNoDTO;
import com.yh.hr.res.pt.queryhelper.PtFileNoQueryHelper;
import com.yh.hr.res.pt.service.PtFileNoService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class PtFileNoServiceImpl implements PtFileNoService {

	public void create(PtFileNoDTO fileNoDTO) throws ServiceException {
		PtFileNo fileNo = BeanHelper.copyProperties(fileNoDTO, PtFileNo.class);
		//设置新增人信息和修改人信息
		fileNo.setCreatedByCode(UserContext.getLoginUserID());
		fileNo.setCreatedByName(UserContext.getLoginUserName());
		fileNo.setCreatedDate(DateUtil.now());
		fileNo.save();
	}

	public void update(PtFileNoDTO fileNoDTO) throws ServiceException {
		PtFileNo fileNo = DaoUtil.get(PtFileNo.class, fileNoDTO.getFileNoOid());
		if(null != fileNo){
			BeanHelper.copyProperties(fileNoDTO, fileNo,new String[]{"createdDate","createdByCode","createdByName"});
			fileNo.setUpdatedByCode(UserContext.getLoginUserID());
			fileNo.setUpdatedByName(UserContext.getLoginUserName());
			fileNo.setUpdatedDate(DateUtil.now());
			fileNo.update();
		}
	}
	
	public PtFileNoDTO get(Long fileNoOid) throws ServiceException {
		PtFileNo fileNo = DaoUtil.get(PtFileNo.class, fileNoOid);
		if(null != fileNo){
			return BeanHelper.copyProperties(fileNo,PtFileNoDTO.class);
		}
		return null;
	}

	public PtFileNoDTO findMaxByFileTypeAndYear(String fileType, String year) throws ServiceException {
		return PtFileNoQueryHelper.findMaxByFileTypeAndYear(fileType,year);
	}

}
