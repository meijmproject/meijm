package com.yh.hr.res.gb.service.impl;

import java.util.Date;
import java.util.List;

import com.yh.hr.res.gb.bo.GbPostPercent;
import com.yh.hr.res.gb.dto.GbPostPercentDTO;
import com.yh.hr.res.gb.queryhelper.GbPostPercentQueryHelper;
import com.yh.hr.res.gb.service.GbPostPercentService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 岗位信息 Service
 * @author zhengdr
 *
 * 时间:2016-12-22下午01:46:19
 */
public class GbPostPercentServiceImpl implements GbPostPercentService {

	/*
	 * 得到岗位信息列表
	 * (non-Javadoc)
	 * @see com.yh.hr.orghc.ub.service.GbPostPercentService#getGbPostPercentDTOList(java.lang.Long)
	 */
	public List<GbPostPercentDTO> getGbPostPercentDTOList(Long utUnitOid)
			throws ServiceException {
		//得到岗位信息并复制给DTO
		return GbPostPercentQueryHelper.getGbPostPercentDTO(utUnitOid);
	}
	
	/*
	 * 计数
	 * (non-Javadoc)
	 * @see com.yh.hr.orghc.ub.service.GbPostPercentService#countGbPostPercentByUnitOid(java.lang.Long)
	 */
	public int countGbPostPercentByUnitOid (Long unitOid) throws ServiceException {
		
		return GbPostPercentQueryHelper.countGbPostPercentByUnitOid(unitOid);
	}
	
	/*
	 * 得到岗位信息
	 * (non-Javadoc)
	 * @see com.yh.hr.orghc.ub.service.GbPostPercentService#getGbPostPercentDTO(java.lang.Long)
	 */
	public GbPostPercentDTO getGbPostPercentDTO(Long jhgGbPostPercentOid) throws ServiceException {
		
		return BeanHelper.copyProperties(DaoUtil.get(GbPostPercent.class,jhgGbPostPercentOid),GbPostPercentDTO.class);

	}
	
	
    /*
     * 新增岗位信息
     * (non-Javadoc)
     * @see com.yh.hr.orghc.ub.service.GbPostPercentService#createGbPostPercent(com.yh.hr.orghc.ub.dto.GbPostPercentDTO)
     */
	public void createGbPostPercent(GbPostPercentDTO gbPostPercentDTO)throws ServiceException{
		//将dto转换成为po
		GbPostPercent gbPostPercent = BeanHelper.copyProperties(gbPostPercentDTO,GbPostPercent.class);
		 //得到操作人信息
		String userId = UserContext.getLoginUserID();
		String userName = UserContext.getLoginUserName();
		//当前日期
		Date now = new Date();
		//设置新增人信息和修改人信息
		gbPostPercent.setCreatedByCode(userId);
		gbPostPercent.setCreatedByName(userName);
		gbPostPercent.setCreatedDate(now);
		//保存
		gbPostPercent.save();
	}
	
	/**
	 * 修改岗位信息
	 * @param gbPostPercentDTO
	 */
	public void modifyGbPostPercent(GbPostPercentDTO gbPostPercentDTO)throws ServiceException{
		
		//将dto转换成为po
		GbPostPercent gbPostPercent = DaoUtil.get(GbPostPercent.class, gbPostPercentDTO.getJhgGbPostPercentOid());
		if(gbPostPercent != null){
			BeanHelper.copyProperties(gbPostPercentDTO,gbPostPercent,new String[]{"createdDate","createdByCode","createdByName"});
			gbPostPercent.setUpdatedByCode(UserContext.getLoginUserID());
			gbPostPercent.setUpdatedByName(UserContext.getLoginUserName());
			gbPostPercent.setUpdatedDate(DateUtil.now());
			gbPostPercent.update();
		}
	}
	
	/**
	 * 根据id删除岗位信息
	 * @param jhgGbPostPercentOid
	 * @throws ServiceException
	 */
	public void deleteGbPostPercent(Long jhgGbPostPercentOid)throws ServiceException{
		//得到岗位信息
		GbPostPercent gbPostPercent = DaoUtil.get(GbPostPercent.class,jhgGbPostPercentOid);
	    if(gbPostPercent!=null){
	    	//删除
	    	gbPostPercent.delete();
	    }
	}

}
