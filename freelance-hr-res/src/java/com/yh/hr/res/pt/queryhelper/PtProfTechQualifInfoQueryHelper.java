package com.yh.hr.res.pt.queryhelper;

import com.yh.hr.res.pt.dto.PtProfTechQualifInfoDTO;
import jade.workflow.utils.ObjectUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.pt.bo.PtProfTechQualifInfo;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringMap;
import com.yh.platform.core.util.StringUtil;
/**
 * @desc 专业技术资格业务信息查询工具类
 * @author liul
 * @createDate 2017-2-17
 */
public class PtProfTechQualifInfoQueryHelper {
	
	/**
	 * 根据业务人员OID查询专业技术资格经历
	 * @param bizPersonOid
	 * @return List<PtProfTechQualifInfoDTO>
	 * @throws ServiceException
	 */
	public static List<PtProfTechQualifInfoDTO> listProfTechQualifInfoByBizPersonOid(Long bizPersonOid) throws ServiceException {
		String hql = "from PtProfTechQualifInfo ei where ei.bizPersonOid = :bizPersonOid order by ei.validityDate desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		List<PtProfTechQualifInfo> boList = DaoUtil.find(hql, params);
		List<PtProfTechQualifInfoDTO> dtoList = new ArrayList<PtProfTechQualifInfoDTO>();
		if(CollectionUtils.isNotEmpty(boList))
		{
			dtoList = BeanHelper.copyProperties(boList, PtProfTechQualifInfoDTO.class);
		}
		return dtoList;
	}
	
	
	public static List<PtProfTechQualifInfoDTO> list(TableTagBean ttb) throws ServiceException {
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
        List<PtProfTechQualifInfo> list = DaoUtil.listByCondition(hql.toString(), hqlParams, ttb.getPage() * ttb.getPageSize(), ttb.getPageSize());
        List<PtProfTechQualifInfoDTO> listDTO = new ArrayList<PtProfTechQualifInfoDTO>();
        
        for(PtProfTechQualifInfo bo: list){
        	PtProfTechQualifInfoDTO dto = new PtProfTechQualifInfoDTO();
        	BeanHelper.copyProperties(bo, dto);
			listDTO.add(dto);
        }
        ttb.setList(listDTO);
        ttb.setTotal(DaoUtil.countByCondition("select count(*) "+hql, hqlParams));
        return listDTO;
	}

    private static void buildHQL(StringMap params, StringBuilder hql, HashMap<String, Object> hqlParams) throws ServiceException {
        hql.append("from PtProfTechQualifInfo where 1=1");
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
     * @param bizPersonOid
     * @param flag
     * @return
     * @throws DataAccessFailureException
     * @throws ServiceException
     */
	public static PtProfTechQualifInfoDTO getPtProfTechQualifInfoByCond(
			Long bizPersonOid, String flag) throws DataAccessFailureException, ServiceException  {
		StringBuffer hBuffer = new StringBuffer("from PtProfTechQualifInfo pt where  1 =1 ");
		if (StringUtil.isNotNull(bizPersonOid)) {
            hBuffer.append(" and  pt.bizPersonOid =" + bizPersonOid);
        }
		if (StringUtil.isNotBlank(flag)) {
            hBuffer.append(" and  pt.isHighestLevel ='" + flag).append("'");
        }
		 List<PtProfTechQualifInfo> list = DaoUtil.find(hBuffer.toString());
		 if(CollectionUtils.isNotEmpty(list))
		 {
			 return BeanHelper.copyProperties(list.get(0),PtProfTechQualifInfoDTO.class);
		 }
		return null;
	}
	
	/**
	 * 通过基础OID查找该人员的专业技术资格业务信息
	 * @param baseProfTechQualifOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtProfTechQualifInfoDTO> listPtProfTechQualifInfoByBaseProfTechQualifOid(Long baseProfTechQualifOid) throws ServiceException {
		List<PtProfTechQualifInfo> list = DaoUtil.findByNamed("from PtProfTechQualifInfo ei where ei.baseProfTechQualifOid = :baseProfTechQualifOid order by ei.procureDate desc", "baseProfTechQualifOid", baseProfTechQualifOid);
		return BeanHelper.copyProperties(list, PtProfTechQualifInfoDTO.class);
	}

	/**
	 * 通过业务人员OID删除该人员的所有专业技术资格信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtProfTechQualifInfo where bizPersonOid='"+bizPersonOid+"'");
	}
}
