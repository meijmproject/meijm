package com.yh.hr.res.pb.queryhelper;

import jade.workflow.utils.ObjectUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.pb.bo.PbQualificationsInfo;
import com.yh.hr.res.pb.dto.PbQualificationsInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringMap;
import com.yh.platform.core.util.StringUtil;

public class PbQualificationsInfoQueryHelper {
	
	/**
	 * 根据人员id查询执业（职业）资格信息
	 * @param longValue
	 * @return ServiceException
	 */
	public static List<PbQualificationsInfoDTO> listQualificationsInfoByPersonOid(Long personOid) throws ServiceException {
		String hql = "from PbQualificationsInfo ei where ei.personOid = :personOid order by ei.validityDate desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personOid", personOid);
		List<PbQualificationsInfo> boList = DaoUtil.find(hql, params);
		List<PbQualificationsInfoDTO> dtoList = new ArrayList<PbQualificationsInfoDTO>();
		if(CollectionUtils.isNotEmpty(boList))
		{
			dtoList = BeanHelper.copyProperties(boList, PbQualificationsInfoDTO.class);
		}
		return dtoList;
	}
	
	
	public static List<PbQualificationsInfoDTO> find(TableTagBean ttb) throws ServiceException {
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
        }else{
        	//执业(职业)资格信息默认排序,取得资格日期倒序
        	hql.append(" order by procureDate desc ");
        }
        List<PbQualificationsInfo> list = DaoUtil.listByCondition(hql.toString(), hqlParams, ttb.getPage() * ttb.getPageSize(), ttb.getPageSize());
        
        List<PbQualificationsInfoDTO> listDTO = new ArrayList<PbQualificationsInfoDTO>();
        
        for(PbQualificationsInfo bo: list){
        	PbQualificationsInfoDTO dto = new PbQualificationsInfoDTO();
        	BeanHelper.copyProperties(bo, dto);
			listDTO.add(dto);
        }
        
        ttb.setList(listDTO);
        ttb.setTotal(DaoUtil.countByCondition("select count(*) "+hql, hqlParams));
        return listDTO;
	}

    public static void buildHQL(StringMap params, StringBuilder hql, HashMap<String, Object> hqlParams) throws ServiceException {
        hql.append("from PbQualificationsInfo where 1=1");
        String personOid = params.getAsStringEmptyNull("personOid");
        if (personOid != null){
           	hql.append(" and personOid = :personOid");
           	try{
           		hqlParams.put("personOid", ObjectUtil.getValue(personOid, java.lang.Long.class));
        	} catch (jade.workflow.exception.ServiceException e) {
			e.printStackTrace();
			}
        }
        String certificateNo = params.getAsStringEmptyNull("certificateNo");
        if (certificateNo != null){
           hql.append(" and certificateNo like :certificateNo");
           hqlParams.put("certificateNo", "%"+certificateNo.trim()+"%");
        }
        String qualificationsCode = params.getAsStringEmptyNull("qualificationsCode");
        if (qualificationsCode != null){
           hql.append(" and qualificationsCode like :qualificationsCode");
           hqlParams.put("qualificationsCode", "%"+qualificationsCode.trim()+"%");
        }
        String qualificationsName = params.getAsStringEmptyNull("qualificationsName");
        if (qualificationsName != null){
           hql.append(" and qualificationsName like :qualificationsName");
           hqlParams.put("qualificationsName", "%"+qualificationsName.trim()+"%");
        }
   }

    /**
     * 查询最高执业资格信息
     * @param personOid
     * @param jdrs00031
     * @return
     * @throws ServiceException 
     */
	public static PbQualificationsInfoDTO getQualificationsInfoByCond(
			Long personOid, String flag) throws ServiceException {
		StringBuffer hBuffer = new StringBuffer("from PbQualificationsInfo pt where  1 =1 ");
		if (StringUtil.isNotNull(personOid)) {
            hBuffer.append(" and  pt.personOid =" + personOid);
        }
		if (StringUtil.isNotBlank(flag)) {
            hBuffer.append(" and  pt.isHighestLevel ='" + flag).append("'");
        }
		 List<PbQualificationsInfo> list = DaoUtil.find(hBuffer.toString());
		 if(CollectionUtils.isNotEmpty(list))
		 {
			 return BeanHelper.copyProperties(list.get(0),PbQualificationsInfoDTO.class);
		 }
		return null;
	}


	/**
	 * 删除该人员的所有职业资格信息
	 * @param personOid
	 * @throws ServiceException
	 */
	public static void deleteByPersonOid(Long personOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PbQualificationsInfo where personOid='"+personOid+"'");
	}
}
