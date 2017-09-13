package com.yh.hr.res.pt.queryhelper;

import jade.workflow.utils.ObjectUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.pt.bo.PtQualificationsInfo;
import com.yh.hr.res.pt.dto.PtQualificationsInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringMap;
import com.yh.platform.core.util.StringUtil;
/**
 * @desc 执业(职业)资格业务信息查询工具类
 * @author liul
 * @createDate 2017-2-17
 */
public class PtQualificationsInfoQueryHelper {
	
	/**
	 * 根据业务人员OID查询执业（职业）资格信息
	 * @param bizPersonOid
	 * @return 
	 * @throws ServiceException
	 */
	public static List<PtQualificationsInfoDTO> listQualificationsInfoByBizPersonOid(Long bizPersonOid) throws ServiceException {
		String hql = "from PtQualificationsInfo ei where ei.bizPersonOid = :bizPersonOid order by ei.validityDate desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		List<PtQualificationsInfo> boList = DaoUtil.find(hql, params);
		List<PtQualificationsInfoDTO> dtoList = new ArrayList<PtQualificationsInfoDTO>();
		if(CollectionUtils.isNotEmpty(boList))
		{
			dtoList = BeanHelper.copyProperties(boList, PtQualificationsInfoDTO.class);
		}
		return dtoList;
	}
	
	/**
	 * 查询执业(职业)资格业务信息列表
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtQualificationsInfoDTO> list(TableTagBean ttb) throws ServiceException {
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
        List<PtQualificationsInfo> list = DaoUtil.listByCondition(hql.toString(), hqlParams, ttb.getPage() * ttb.getPageSize(), ttb.getPageSize());
        
        List<PtQualificationsInfoDTO> listDTO = new ArrayList<PtQualificationsInfoDTO>();
        
        for(PtQualificationsInfo bo: list){
        	PtQualificationsInfoDTO dto = new PtQualificationsInfoDTO();
        	BeanHelper.copyProperties(bo, dto);
			listDTO.add(dto);
        }
        
        ttb.setList(listDTO);
        ttb.setTotal(DaoUtil.countByCondition("select count(*) "+hql, hqlParams));
        return listDTO;
	}

    private static void buildHQL(StringMap params, StringBuilder hql, HashMap<String, Object> hqlParams) throws ServiceException {
        hql.append("from PtQualificationsInfo where 1=1");
        String bizPersonOid = params.getAsStringEmptyNull("bizPersonOid");
        if (bizPersonOid != null){
           	hql.append(" and bizPersonOid = :bizPersonOid");
           	try{
           		hqlParams.put("bizPersonOid", ObjectUtil.getValue(bizPersonOid, java.lang.Long.class));
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
     * @param bizPersonOid
     * @param jdrs00031
     * @return
     * @throws ServiceException 
     */
	public static PtQualificationsInfoDTO getQualificationsInfoByCond(
			Long bizPersonOid, String flag) throws ServiceException {
		StringBuffer hBuffer = new StringBuffer("from PtQualificationsInfo pt where  1 =1 ");
		if (StringUtil.isNotNull(bizPersonOid)) {
            hBuffer.append(" and  pt.bizPersonOid =" + bizPersonOid);
        }
		if (StringUtil.isNotBlank(flag)) {
            hBuffer.append(" and  pt.isHighestLevel ='" + flag).append("'");
        }
		 List<PtQualificationsInfo> list = DaoUtil.find(hBuffer.toString());
		 if(CollectionUtils.isNotEmpty(list))
		 {
			 return BeanHelper.copyProperties(list.get(0),PtQualificationsInfoDTO.class);
		 }
		return null;
	}
	
	/**
	 * 通过基础OID查找该人员的执业(职业)资格业务信息
	 * @param baseQualificationsOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtQualificationsInfoDTO> listPtQualificationsInfoByBaseQualificationsOid(Long baseQualificationsOid) throws ServiceException {
		List<PtQualificationsInfo> list = DaoUtil.findByNamed("from PtQualificationsInfo ei where ei.baseQualificationsOid = :baseQualificationsOid order by ei.procureDate desc", "baseQualificationsOid", baseQualificationsOid);
		return BeanHelper.copyProperties(list, PtQualificationsInfoDTO.class);
	}

	/**
	 * 通过业务人员OID删除该人员的所有职业资格信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtQualificationsInfo where bizPersonOid='"+bizPersonOid+"'");
	}
}
