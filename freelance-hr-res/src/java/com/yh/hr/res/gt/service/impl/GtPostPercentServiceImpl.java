package com.yh.hr.res.gt.service.impl;

import java.util.Date;
import java.util.List;

import com.yh.hr.res.gt.bo.GtPostPercent;
import com.yh.hr.res.gt.dto.GtPostPercentDTO;
import com.yh.hr.res.gt.queryhelper.GtPostPercentQueryHelper;
import com.yh.hr.res.gt.service.GtPostPercentService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 岗位信息 Service
 * @author zhengdr
 *
 * 时间:2016-12-21下午03:55:47
 */
public class GtPostPercentServiceImpl implements GtPostPercentService{

	/*
	 * 得到岗位信息列表
	 * (non-Javadoc)
	 * @see com.yh.hr.orghc.ut.service.GtPostPercentService#getGtPostPercentDTOList(java.lang.Long)
	 */
	public List<GtPostPercentDTO> getGtPostPercentDTOList(Long utUnitOid)
			throws ServiceException {
		//得到岗位信息并复制给DTO
		return GtPostPercentQueryHelper.getGtPostPercentDTO(utUnitOid);
	}
	
	/*
	 * 计数
	 * (non-Javadoc)
	 * @see com.yh.hr.orghc.ut.service.GtPostPercentService#countGtPostPercentByUtUnitOid(java.lang.Long)
	 */
	public int countGtPostPercentByUtUnitOid (Long utUnitOid) throws ServiceException {
		
		return GtPostPercentQueryHelper.countGtPostPercentByUtUnitOid(utUnitOid);
	}
	
	/*
	 * 得到岗位信息
	 * (non-Javadoc)
	 * @see com.yh.hr.orghc.ut.service.GtPostPercentService#getGtPostPercentDTO(java.lang.Long)
	 */
	public GtPostPercentDTO getGtPostPercentDTO(Long jhgGtPostPercentOid) throws ServiceException {
		
		return BeanHelper.copyProperties(DaoUtil.get(GtPostPercent.class,jhgGtPostPercentOid),GtPostPercentDTO.class);

	}
	
	
    /*
     * 新增岗位信息
     * (non-Javadoc)
     * @see com.yh.hr.orghc.ut.service.GtPostPercentService#createGtPostPercent(com.jade.hr.orghc.ut.dto.GtPostPercentDTO)
     */
	public void createGtPostPercent(GtPostPercentDTO gtPostPercentDTO)throws ServiceException{
		//将dto转换成为po
		GtPostPercent gtPostPercent = BeanHelper.copyProperties(gtPostPercentDTO,GtPostPercent.class);
		 //得到操作人信息
		String userId = UserContext.getLoginUserID();
		String userName = UserContext.getLoginUserName();
		//当前日期
		Date now = new Date();
		//设置新增人信息和修改人信息
		gtPostPercent.setCreatedByCode(userId);
		gtPostPercent.setCreatedByName(userName);
		gtPostPercent.setCreatedDate(now);
		//保存
		gtPostPercent.save();
	}
	
	/**
	 * 修改岗位信息
	 * @param gtPostPercentDTO
	 */
	public void modifyGtPostPercent(GtPostPercentDTO gtPostPercentDTO)throws ServiceException{
		
		//将dto转换成为po
		GtPostPercent gtPostPercent = DaoUtil.get(GtPostPercent.class, gtPostPercentDTO.getJhgGtPostPercentOid());
		if(gtPostPercent != null){
			BeanHelper.copyProperties(gtPostPercentDTO,gtPostPercent,new String[]{"createdDate","createdByCode","createdByName"});
			gtPostPercent.setUpdatedByCode(UserContext.getLoginUserID());
			gtPostPercent.setUpdatedByName(UserContext.getLoginUserName());
			gtPostPercent.setUpdatedDate(DateUtil.now());
			gtPostPercent.update();
		}
	}
	
	/**
	 * 根据id删除岗位信息
	 * @param jhgGtPostPercentOid
	 * @throws ServiceException
	 */
	public void deleteGtPostPercent(Long jhgGtPostPercentOid)throws ServiceException{
		//得到岗位信息
		GtPostPercent gtPostPercent = DaoUtil.get(GtPostPercent.class,jhgGtPostPercentOid);
	    if(gtPostPercent!=null){
	    	//删除
	    	gtPostPercent.delete();
	    }
	}

}
