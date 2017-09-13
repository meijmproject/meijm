package com.yh.hr.res.pt.queryhelper;

import com.yh.hr.res.pt.bo.PtPoliticInfo;
import jade.workflow.utils.ObjectUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.pt.dto.PtPoliticInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.StringMap;
import com.yh.platform.core.util.StringUtil;

/**
 * @desc 政治面貌与党派业务信息查询工具类
 * @author wangx
 * @date 2017-05-26
 */
public class PtPoliticInfoQueryHelper {
     /**
      * 查询最高的一笔政治面貌信息
      * @param bizPersonOid
      * @return
     * @throws ServiceException 
      */
	public static PtPoliticInfoDTO getPtPoliticInfoInfoDTOById(Long bizPersonOid) throws ServiceException {
		StringBuffer hBuffer = new StringBuffer("from PtPoliticInfo pt where  1 =1 ");
		if (StringUtil.isNotNull(bizPersonOid)) {
            hBuffer.append(" and  pt.bizPersonOid =" + bizPersonOid);
        }
         hBuffer.append(" and  pt.outPartyDate is null" );
         hBuffer.append(" order by pt.joinPoliticDate desc" );
		 List<PtPoliticInfo> list = DaoUtil.find(hBuffer.toString());
		 if(CollectionUtils.isNotEmpty(list))
		 {
			 return BeanHelper.copyProperties(list.get(0),PtPoliticInfoDTO.class);
		 }
		return null;
	}
	
	/**
	 * 查找政治面貌与党派业务信息列表
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtPoliticInfoDTO> list(TableTagBean ttb) throws ServiceException {
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
        List<PtPoliticInfoDTO> list = new ArrayList<PtPoliticInfoDTO>();
        List<PtPoliticInfo> boList = DaoUtil.listByCondition(hql.toString(), hqlParams, ttb.getPage() * ttb.getPageSize(), ttb.getPageSize());
		if(!CollectionUtils.isEmpty(boList))
		{
			for(PtPoliticInfo bo : boList)
			{
				PtPoliticInfoDTO dto = new PtPoliticInfoDTO();
				BeanHelper.copyProperties(bo, dto);
				list.add(dto);
			}
		}
        ttb.setList(list);
        ttb.setTotal(DaoUtil.countByCondition("select count(*) "+hql, hqlParams));
        return list;
	}

    private static void buildHQL(StringMap params, StringBuilder hql, HashMap<String, Object> hqlParams) throws ServiceException {
        hql.append("from PtPoliticInfo where 1=1");
        String bizPersonOid = params.getAsStringEmptyNull("bizPersonOid");
        if (bizPersonOid != null){
           	hql.append(" and bizPersonOid = :bizPersonOid");
           	try{
           		hqlParams.put("bizPersonOid", ObjectUtil.getValue(bizPersonOid, java.lang.Long.class));
        	} catch (jade.workflow.exception.ServiceException e) {
			e.printStackTrace();
			}
        }
        String politicStatusCode = params.getAsStringEmptyNull("politicStatusCode");
        if (politicStatusCode != null){
           hql.append(" and politicStatusCode like :politicStatusCode");
           hqlParams.put("politicStatusCode", "%"+politicStatusCode.trim()+"%");
        }
        String joinPoliticDate = params.getAsStringEmptyNull("joinPoliticDateStr");
        if (joinPoliticDate != null){
            hql.append(" and joinPoliticDate = :joinPoliticDate");
            hqlParams.put("joinPoliticDate", DateUtil.parseDate(joinPoliticDate));
        }
        String unitOfJoinParty = params.getAsStringEmptyNull("unitOfJoinParty");
        if (unitOfJoinParty != null){
           hql.append(" and unitOfJoinParty like :unitOfJoinParty");
           hqlParams.put("unitOfJoinParty", "%"+unitOfJoinParty.trim()+"%");
        }
   }

    /**
     * 通过业务人员OID删除该人员的所有政治面貌信息
     * @param bizPersonOid
     * @throws ServiceException
     */
	public static void deleteBizByPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtPoliticInfo where bizPersonOid='"+bizPersonOid+"'");
	}

	/**
     * 通过业务人员OID查询该人员的所有政治面貌业务信息
     * @param bizPersonOid
     * @return 
     * @throws ServiceException
     */
	public static List<PtPoliticInfoDTO> listPtPoliticInfoByBizPersonOid(Long bizPersonOid) throws DataAccessFailureException {
		String hql = "from PtPoliticInfo fi where fi.bizPersonOid = :bizPersonOid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		List<PtPoliticInfo> boList = DaoUtil.find(hql, params);
		List<PtPoliticInfoDTO> dtoList = new ArrayList<PtPoliticInfoDTO>();
		
		if(!CollectionUtils.isEmpty(boList))
		{
			for(PtPoliticInfo bo : boList)
			{
				PtPoliticInfoDTO dto = new PtPoliticInfoDTO();
				BeanHelper.copyProperties(bo, dto);
				dtoList.add(dto);
			}
		}
		return dtoList;
	}
	
	/**
	 * 通过基础OID查找该人员的政治面貌业务信息
	 * @param basePoliticOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtPoliticInfoDTO> listPtPoliticInfoByBasePoliticOid(Long basePoliticOid) throws ServiceException {
		List<PtPoliticInfo> list = DaoUtil.findByNamed("from PtPoliticInfo pc where pc.basePoliticOid = :basePoliticOid order by pc.joinPoliticDate desc", "basePoliticOid", basePoliticOid);
		return BeanHelper.copyProperties(list, PtPoliticInfoDTO.class);
	}
}
