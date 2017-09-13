package com.yh.hr.res.pb.queryhelper;

import com.yh.hr.res.pb.bo.PbProfTechQualifInfo;
import jade.workflow.utils.ObjectUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.pb.dto.PbProfTechQualifInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringMap;
import com.yh.platform.core.util.StringUtil;

public class PbProfTechQualifInfoQueryHelper {
	
	/**
	 * 根据人员id查询教育培训经历
	 * @param personOid
	 * @return List<PbProfTechQualifInfoDTO>
	 * @throws ServiceException
	 */
	public static List<PbProfTechQualifInfoDTO> listProfTechQualifInfoByPersonOid(Long personOid) throws ServiceException {
		String hql = "from PbProfTechQualifInfo ei where ei.personOid = :personOid order by ei.validityDate desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personOid", personOid);
		List<PbProfTechQualifInfo> boList = DaoUtil.find(hql, params);
		List<PbProfTechQualifInfoDTO> dtoList = new ArrayList<PbProfTechQualifInfoDTO>();
		if(CollectionUtils.isNotEmpty(boList))
		{
			dtoList = BeanHelper.copyProperties(boList, PbProfTechQualifInfoDTO.class);
		}
		return dtoList;
	}
	
	
	public static List<PbProfTechQualifInfoDTO> find(TableTagBean ttb) throws ServiceException {
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
        	//专业技术资格信息默认排序, 取得该资格时期倒序
        	hql.append(" order by procureDate desc ");
        }
        List<PbProfTechQualifInfo> list = DaoUtil.listByCondition(hql.toString(), hqlParams, ttb.getPage() * ttb.getPageSize(), ttb.getPageSize());
        List<PbProfTechQualifInfoDTO> listDTO = new ArrayList<PbProfTechQualifInfoDTO>();
        
        for(PbProfTechQualifInfo bo: list){
        	PbProfTechQualifInfoDTO dto = new PbProfTechQualifInfoDTO();
        	BeanHelper.copyProperties(bo, dto);
			listDTO.add(dto);
        }
        ttb.setList(listDTO);
        ttb.setTotal(DaoUtil.countByCondition("select count(*) "+hql, hqlParams));
        return listDTO;
	}

    public static void buildHQL(StringMap params, StringBuilder hql, HashMap<String, Object> hqlParams) throws ServiceException {
        hql.append("from PbProfTechQualifInfo where 1=1");
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
        String profTechCode = params.getAsStringEmptyNull("profTechCode");
        if (profTechCode != null){
           hql.append(" and profTechCode like :profTechCode");
           hqlParams.put("profTechCode", "%"+profTechCode.trim()+"%");
        }
        String profTechName = params.getAsStringEmptyNull("profTechName");
        if (profTechName != null){
           hql.append(" and profTechName like :profTechName");
           hqlParams.put("profTechName", "%"+profTechName.trim()+"%");
        }
   }

    /**
     * 查询主要专业技术资格
     * @param personOid
     * @param flag
     * @return
     * @throws DataAccessFailureException
     * @throws ServiceException
     */
	public static PbProfTechQualifInfoDTO getPbProfTechQualifInfoByCond(
			Long personOid, String flag) throws DataAccessFailureException, ServiceException  {
		StringBuffer hBuffer = new StringBuffer("from PbProfTechQualifInfo pt where  1 =1 ");
		if (StringUtil.isNotNull(personOid)) {
            hBuffer.append(" and  pt.personOid =" + personOid);
        }
		if (StringUtil.isNotBlank(flag)) {
            hBuffer.append(" and  pt.isHighestLevel ='" + flag).append("'");
        }
		 List<PbProfTechQualifInfo> list = DaoUtil.find(hBuffer.toString());
		 if(CollectionUtils.isNotEmpty(list))
		 {
			 return BeanHelper.copyProperties(list.get(0),PbProfTechQualifInfoDTO.class);
		 }
		return null;
	}


	/**
	 * 删除该人员的所有专业技术资格信息
	 * @param personOid
	 * @throws ServiceException
	 */
	public static void deleteByPersonOid(Long personOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PbProfTechQualifInfo where personOid='"+personOid+"'");
	}
}
