package com.yh.hr.orghc.ut.queryhelper;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yh.hr.orghc.ut.bo.BizUtOrg;
import com.yh.hr.orghc.ut.dto.BizUtOrgDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * @desc 内设机构信息查询工具类
 * @author xiongyx
 * @createDate 2016-12-20
 */
public class BizOrgInfoQueryHelper {
	
	/**
	 * 根据ID查询
	 */	
	public static BizUtOrgDTO get(Long utOrgOid) throws ServiceException{
		//查询数据
		BizUtOrg bizUtOrg = DaoUtil.get(BizUtOrg.class, utOrgOid);
		//po转换为dto
		BizUtOrgDTO serdto = new BizUtOrgDTO();
		return BeanHelper.copyRtnProperties(bizUtOrg, serdto);
		
	}

	/*
	 * 获取列表
	 */  
	public static List<BizUtOrgDTO> listBizUtOrg(Long utUnitOid,String Status) throws ServiceException {
		final StringBuffer hBuffer =  new StringBuffer("from BizUtOrg org where  1 =1  ");
		hBuffer.append(" and org.utUnitOid =" +utUnitOid);
		//hBuffer.append(" order by Pt.reviewYear desc");
		if (StringUtils.isNotEmpty(Status)) {
			hBuffer.append(" and org.orgStatus =" +Status);
		}
		return BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), BizUtOrgDTO.class);
	}
	
	/**
	 * 删除
	 */
	public static void delete(Long utOrgOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from BizUtOrg org where org.utOrgOid = " + utOrgOid);
	}
	
	/**
	 * 通过utUnitOid删除
	 * @param utUnitOid
	 * @throws ServiceException
	 */
	public static void deleteByUtUnitOid(Long utUnitOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from BizUtOrg org where org.utUnitOid = " + utUnitOid);
	}
}