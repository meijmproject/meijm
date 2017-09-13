package com.yh.hr.res.pb.queryhelper;

import com.yh.hr.res.pb.dto.PbSpeciaAuthDTO;
import jade.workflow.utils.ObjectUtil;

import java.util.HashMap;
import java.util.List;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringMap;

public class PbSpeciaAuthQueryHelper {

	/**
	 * hql拼装
	 * @param params
	 * @param hql
	 * @param hqlParams
	 * @throws ServiceException
	 */
    public static void buildHQL(StringMap params, StringBuilder hql, HashMap<String, Object> hqlParams) throws ServiceException {
        hql.append("from PbSpeciaAuth where 1=1");
        String personOid = params.getAsStringEmptyNull("personOid");
        if (personOid != null){
           	hql.append(" and personOid = :personOid");
           	try{
           		hqlParams.put("personOid", ObjectUtil.getValue(personOid, java.lang.Long.class));
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
	 * 根据id获取PbSpeciaAuth详细信息
	 * @param personOid
	 * @return
	 * @throws ServiceException 
	 * @throws  
	 */
	public static List<PbSpeciaAuthDTO> getPbSpeciaAuthById(Long personOid) throws ServiceException {
		String hql=" from PbSpeciaAuth  ps where ps.personOid ="+personOid;
		return BeanHelper.copyProperties(DaoUtil.find(hql), PbSpeciaAuthDTO.class);
	}
}
