package com.yh.hr.orghc.ut.queryhelper;

import java.util.List;

import com.yh.hr.orghc.ut.bo.BizUtLeader;
import com.yh.hr.orghc.ut.dto.BizUtLeaderDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * @desc 领导职数信息查询工具类
 * @author xiongyx
 * @createDate 2016-12-20
 */
public class BizLeaderInfoQueryHelper {
	
	/**
	 * 根据ID查询
	 */	
	public static BizUtLeaderDTO get(Long utLeaderOid) throws ServiceException{
		//查询数据
		BizUtLeader bizUtLeader = DaoUtil.get(BizUtLeader.class, utLeaderOid);
		//po转换为dto
		BizUtLeaderDTO serdto = new BizUtLeaderDTO();
		return BeanHelper.copyRtnProperties(bizUtLeader, serdto);
		
	}

	/*
	 * 获取列表
	 */  
	public static List<BizUtLeaderDTO> listBizUtLeader(Long utUnitOid) throws ServiceException {
		final StringBuffer hBuffer =  new StringBuffer("from BizUtLeader ld where  1 =1  ");
		hBuffer.append(" and ld.utUnitOid =" +utUnitOid);
		//hBuffer.append(" order by Pt.reviewYear desc");
		return BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), BizUtLeaderDTO.class);
	}
	
	/**
	 * 删除
	 */
	public static void delete(Long utLeaderOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from BizUtLeader ld where ld.utLeaderOid = " + utLeaderOid);
	}
	
	/**
	 * 通过utUnitOid删除
	 * @param utUnitOid
	 * @throws ServiceException
	 */
	public static void deleteByUtUnitOid(Long utUnitOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from BizUtLeader ld where ld.utUnitOid = " + utUnitOid);
	}
}