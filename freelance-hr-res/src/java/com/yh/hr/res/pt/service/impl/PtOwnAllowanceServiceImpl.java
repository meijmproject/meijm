package com.yh.hr.res.pt.service.impl;

import java.util.Date;
import java.util.List;

import com.yh.hr.res.pt.bo.PtOwnAllowance;
import com.yh.hr.res.pt.dto.PtOwnAllowanceDTO;
import com.yh.hr.res.pt.queryhelper.PtOwnAllowanceQueryHelper;
import com.yh.hr.res.pt.service.PtOwnAllowanceService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class PtOwnAllowanceServiceImpl implements PtOwnAllowanceService {
	
	/**
	 * 人员享受特岗津贴信息列表
	 * @return
	 * @throws ServiceException
	 */	
	public List<PtOwnAllowanceDTO> listPtOwnAllowance(Long bizPersonOid)
			throws ServiceException {
		return    PtOwnAllowanceQueryHelper.listPtOwnAllowance(bizPersonOid);
	}
	/**
	 * 根据ownAllowanceOid获取人员享受特岗津贴信息
	 * @return
	 * @throws ServiceException
	 */	
	public PtOwnAllowanceDTO getPtOwnAllowance(Long ownAllowanceOid)
			throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtOwnAllowance.class,ownAllowanceOid),PtOwnAllowanceDTO.class);
	}
	
	/**
	 * 新增人员享受特岗津贴信息
	 * @return
	 * @throws ServiceException
	 */	
	public void addPtOwnAllowance(PtOwnAllowanceDTO dto)
			throws ServiceException {
		//将dto转换成为po
		PtOwnAllowance ptOwnAllowance =BeanHelper.copyProperties(dto,PtOwnAllowance.class);
		 //得到操作人信息
		String userId = UserContext.getLoginUserID();
		String userName = UserContext.getLoginUserName();
		//当前日期
		Date now = new Date();
		//设置新增人信息和修改人信息
		ptOwnAllowance.setCreatedByCode(userId);
		ptOwnAllowance.setCreatedByName(userName);
		ptOwnAllowance.setCreatedDate(now);	
		ptOwnAllowance.save();
	}
	/**
	 * 修改人员享受特岗津贴信息
	 * @return
	 * @throws ServiceException
	 */	
	public void updatePtOwnAllowance(PtOwnAllowanceDTO dto)
			throws ServiceException {
		//将dto转换成为po
		PtOwnAllowance ptOwnAllowance = DaoUtil.get(PtOwnAllowance.class, dto.getOwnAllowanceOid());
		if(ptOwnAllowance != null){
			BeanHelper.copyProperties(dto,ptOwnAllowance,new String[]{"createdDate","createdByCode","createdByName"});
			ptOwnAllowance.setUpdatedByCode(UserContext.getLoginUserID());
			ptOwnAllowance.setUpdatedByName(UserContext.getLoginUserName());
			ptOwnAllowance.setUpdatedDate(DateUtil.now());
			ptOwnAllowance.update();
		}		
	}
	/**
	 * 删除人员享受特岗津贴信息
	 * @return
	 * @throws ServiceException
	 */	
	public void deletePtOwnAllowance(Long ownAllowanceOid)
			throws ServiceException {
		//人员享受特岗津贴信息
		PtOwnAllowance PtOwnAllowance = DaoUtil.get(PtOwnAllowance.class,ownAllowanceOid);
	    if(PtOwnAllowance!=null){
	    	//删除
	    	PtOwnAllowance.delete();
	    }
	}
	
	/**
	 * 删除人员享受特岗津贴信息通过人员ID
	 * @return
	 * @throws ServiceException
	 */	
	public void deletePtOwnAllowanceById(Long bizPersonOid) throws ServiceException
	{
		if(bizPersonOid!=null){
			DaoUtil.executeUpdate("delete from PtOwnAllowance p where p.bizPersonOid ="+bizPersonOid);
		}
	}
	/**
	 * 通过操作类型获取
	 * @param handleCode
	 * @return
	 * @throws ServiceException
	 */
	public List<PtOwnAllowanceDTO> listPtOwnAllowanceByHandleCode(
			String handleCode) throws ServiceException {
		
		return PtOwnAllowanceQueryHelper.listPtOwnAllowanceByHandleCode(handleCode);
	}
	/**
	 * 通过对应识别码获取
	 * @param handleCode
	 * @return
	 * @throws ServiceException
	 */
	public List<PtOwnAllowanceDTO> listPtOwnAllowanceByHandleMatchNo(
			Long handleMatchNo) throws ServiceException {
		
		return PtOwnAllowanceQueryHelper.listPtOwnAllowanceByHandleMatchNo(handleMatchNo);
	}

}
