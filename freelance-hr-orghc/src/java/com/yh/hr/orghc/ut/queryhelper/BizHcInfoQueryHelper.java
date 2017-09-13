package com.yh.hr.orghc.ut.queryhelper;

import java.util.List;

import com.yh.hr.orghc.ut.bo.BizUtHc;
import com.yh.hr.orghc.ut.dto.BizUtHcDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * @desc 编制信息查询工具类
 * @author xiongyx
 * @createDate 2016-12-20
 */
public class BizHcInfoQueryHelper {
	
	/**
	 * 根据ID查询
	 */	
	public static BizUtHcDTO get(Long utHcOid) throws ServiceException{
		//查询数据
		BizUtHc bizUtHc = DaoUtil.get(BizUtHc.class, utHcOid);
		//po转换为dto
		BizUtHcDTO serdto = new BizUtHcDTO();
		return BeanHelper.copyRtnProperties(bizUtHc, serdto);
		
	}

	/*
	 * 获取列表
	 */  
	public static List<BizUtHcDTO> listBizUtHc(Long utUnitOid) throws ServiceException {
		final StringBuffer hBuffer =  new StringBuffer("from BizUtHc hc where  1 =1  ");
		hBuffer.append(" and  hc.utUnitOid =" +utUnitOid);
		//hBuffer.append(" order by Pt.reviewYear desc");
		return BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), BizUtHcDTO.class);
	}
	
	/**
	 * 删除
	 */
	public static void delete(Long utHcOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from BizUtHc hc where hc.utHcOid = " + utHcOid);
	}
	
	/**
	 * 通过utUnitOid删除
	 * @param utUnitOid
	 * @throws ServiceException
	 */
	public static void deleteByUtUnitOid(Long utUnitOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from BizUtHc hc where hc.utUnitOid = " + utUnitOid);
	}
}