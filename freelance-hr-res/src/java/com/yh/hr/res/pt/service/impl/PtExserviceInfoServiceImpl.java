package com.yh.hr.res.pt.service.impl;

import java.util.Date;
import java.util.List;

import com.yh.hr.res.pt.bo.PtExserviceInfo;
import com.yh.hr.res.pt.dto.PtExserviceInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtExserviceInfoQueryHelper;
import com.yh.hr.res.pt.service.PtExserviceInfoService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.web.UserContext;

/**
 * @description 退役军人信息
 * @author wuxq
 * @created 2016年11月21日
 * @version 1.0
 */
public class PtExserviceInfoServiceImpl implements PtExserviceInfoService{

	/**
	 * 得到退役军人信息
	 * @param bizPersonOid
	 */
	public PtExserviceInfoDTO getPtExserviceInfo(Long bizPersonOid)
			throws ServiceException {
		/*StringBuffer sb = new StringBuffer();
		sb.append("select * from YHC_PT_VETERAN_INFO pv where pv.biz_person_oid ="+bizPersonOid);
		List<Object> findWithSQL = DaoUtil.findWithSQL(sb.toString());
		PtExserviceInfoDTO dto = new PtExserviceInfoDTO();
		dto.setBizPersonOid(Long.valueOf(2283));
		dto.setExserviceApprovalDate(new Date());
		dto.setMilitaryInPlace("123");
		return dto;*/
		//return (PtExserviceInfoDTO)findWithSQL.get(0);
		//根据得到退役军人信息并复制给DTO
		return BeanHelper.copyProperties(DaoUtil.get(PtExserviceInfo.class, bizPersonOid),
				PtExserviceInfoDTO.class);
	}
	
	/**
	 * 新增退役军人信息
	 * @param PtExserviceInfoDTO
	 */
	public void createPtExserviceInfo(PtExserviceInfoDTO PtExserviceInfoDTO)throws ServiceException{
		//将DTO复制到PO
		PtExserviceInfo PtExserviceInfo = BeanHelper.copyProperties(PtExserviceInfoDTO, PtExserviceInfo.class);
	    //得到操作人信息
		String userId = UserContext.getLoginUserID();
		String userName = UserContext.getLoginUserName();
		//当前日期
		Date now = new Date();
		//设置创建人
		PtExserviceInfo.setCreatedByCode(userId);
		PtExserviceInfo.setCreatedByName(userName);
		PtExserviceInfo.setCreatedDate(now);
		//设置更新人
		PtExserviceInfo.setUpdatedByCode(userId);
		PtExserviceInfo.setUpdatedByName(userName);
		PtExserviceInfo.setUpdatedDate(now);
		//保存
		PtExserviceInfo.save();
	}
	
	/**
	 * 修改退役军人信息
	 * @param PtExserviceInfoDTO
	 */
	public void modifyPtExserviceInfo(PtExserviceInfoDTO PtExserviceInfoDTO)throws ServiceException{
		//将DTO复制到PO
		PtExserviceInfo PtExserviceInfo = BeanHelper.copyProperties(PtExserviceInfoDTO, PtExserviceInfo.class);
		//得到操作人信息
		String userId = UserContext.getLoginUserID();
		String userName = UserContext.getLoginUserName();
		//当前时间
		Date now = new Date();
		//更新修改人信息
		PtExserviceInfo.setUpdatedByCode(userId);
		PtExserviceInfo.setUpdatedByName(userName);
		PtExserviceInfo.setUpdatedDate(now);
		
		PtExserviceInfo.update();
	}
	
	/**
	 * 删除退役军人信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public void deletePtExserviceInfo(Long bizPersonOid)throws ServiceException{
		//得到退役军人信息
		PtExserviceInfo PtExserviceInfo = DaoUtil.get(PtExserviceInfo.class, bizPersonOid);   
		if(PtExserviceInfo!=null){
			//删除
			PtExserviceInfo.delete();
		}
	}
	/**
	 * 退役军人信息列表
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public List<PtExserviceInfoDTO> listPtExserviceInfo(Long bizPersonOid) throws ServiceException {
		return PtExserviceInfoQueryHelper.listPtExserviceInfo(bizPersonOid);
		
	}

}
