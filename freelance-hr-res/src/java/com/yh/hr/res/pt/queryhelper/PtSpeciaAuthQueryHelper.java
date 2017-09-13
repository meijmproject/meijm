package com.yh.hr.res.pt.queryhelper;

import com.yh.hr.res.pt.dto.PtSpeciaAuthDTO;
import jade.workflow.utils.ObjectUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.pt.bo.PtSpeciaAuth;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringMap;

public class PtSpeciaAuthQueryHelper {

	/**
	 * 查找特殊授权情况业务信息列表
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtSpeciaAuthDTO> list(TableTagBean ttb) throws ServiceException {
        StringBuilder hql = new StringBuilder();
        HashMap<String, Object> hqlParams = new HashMap<String, Object>();
        buildHQL(ttb.getCondition(), hql, hqlParams);
        String order = ttb.getOrderBy();
        if (order != null) {
            if (ttb.getAsc()) {
                hql.append(" order by " + order + " asc");
            } else {
                hql.append(" order by " + order + " desc");
            }
        }
        List<PtSpeciaAuthDTO> list = new ArrayList<PtSpeciaAuthDTO>();
        List<PtSpeciaAuth> boList = DaoUtil.listByCondition(hql.toString(), hqlParams, ttb.getPage() * ttb.getPageSize(), ttb.getPageSize());
		if(!CollectionUtils.isEmpty(boList))
		{
			for(PtSpeciaAuth bo : boList)
			{
				PtSpeciaAuthDTO dto = new PtSpeciaAuthDTO();
				BeanHelper.copyProperties(bo, dto);
				list.add(dto);
			}
		}
        ttb.setList(list);
        ttb.setTotal(DaoUtil.countByCondition("select count(*) "+hql, hqlParams));
        return list;
	}
	
	/**
	 * hql拼装
	 * @param params
	 * @param hql
	 * @param hqlParams
	 * @throws ServiceException
	 */
    public static void buildHQL(StringMap params, StringBuilder hql, HashMap<String, Object> hqlParams) throws ServiceException {
        hql.append("from PtSpeciaAuth where 1=1");
        String bizPersonOid = params.getAsStringEmptyNull("bizPersonOid");
        if (bizPersonOid != null){
           	hql.append(" and bizPersonOid = :bizPersonOid");
           	try{
           		hqlParams.put("bizPersonOid", ObjectUtil.getValue(bizPersonOid, java.lang.Long.class));
        	} catch (jade.workflow.exception.ServiceException e) {
			e.printStackTrace();
			}
        }
        String authType = params.getAsStringEmptyNull("authType");
        if (authType != null){
           hql.append(" and authType like :authType");
           hqlParams.put("authType", "%"+authType.trim()+"%");
        }
        String authLevel = params.getAsStringEmptyNull("authLevel");
        if (authLevel != null){
           hql.append(" and authLevel like :authLevel");
           hqlParams.put("authLevel", "%"+authLevel.trim()+"%");
        }
        String authStatus = params.getAsStringEmptyNull("authStatus");
        if (authStatus != null){
           hql.append(" and authStatus like :authStatus");
           hqlParams.put("authStatus", "%"+authStatus.trim()+"%");
        }
   }

	/**
	 * 根据业务人员OID获取特殊授权情况业务信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException 
	 * @throws  
	 */
	public static List<PtSpeciaAuthDTO> listPtSpeciaAuthByBizPersonOid(Long bizPersonOid) throws ServiceException {
		String hql=" from PtSpeciaAuth  ps where ps.bizPersonOid ="+bizPersonOid;
		return BeanHelper.copyProperties(DaoUtil.find(hql), PtSpeciaAuthDTO.class);
	}
	
	/**
	 * 通过基础OID查找该人员的特殊授权情况业务信息
	 * @param baseSpeciaAuthOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtSpeciaAuthDTO> listPtSpeciaAuthByBaseSpeciaAuthOid(Long baseSpeciaAuthOid) throws ServiceException {
		List<PtSpeciaAuth> list = DaoUtil.findByNamed("from PtSpeciaAuth sa where sa.baseSpeciaAuthOid = :baseSpeciaAuthOid order by sa.authDate desc", "baseSpeciaAuthOid", baseSpeciaAuthOid);
		return BeanHelper.copyProperties(list, PtSpeciaAuthDTO.class);
	}

	/**
	 * 通过业务人员OID删除该人员的所有特殊授权情况业务信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtSpeciaAuth where bizPersonOid="+bizPersonOid);
	}
}
