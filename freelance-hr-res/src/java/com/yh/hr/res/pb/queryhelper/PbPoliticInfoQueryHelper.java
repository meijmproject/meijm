package com.yh.hr.res.pb.queryhelper;

import com.yh.hr.res.pb.bo.PbPoliticInfo;
import com.yh.hr.res.pb.dto.PbPoliticInfoDTO;
import jade.workflow.utils.ObjectUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.StringMap;
import com.yh.platform.core.util.StringUtil;

/**
 * 政治面貌QueryHelper
 * @author chenp
 * 2017-2-20
 */
public class PbPoliticInfoQueryHelper {
     /**
      * 查询最高的一笔政治面貌信息
      * @param personOid
      * @return
     * @throws ServiceException 
      */
	public static PbPoliticInfoDTO getPbPoliticInfoInfoDTOById(Long personOid) throws ServiceException {
		StringBuffer hBuffer = new StringBuffer("from PbPoliticInfo pt where  1 =1 ");
		if (StringUtil.isNotNull(personOid)) {
            hBuffer.append(" and  pt.personOid =" + personOid);
        }
         hBuffer.append(" and  pt.outPartyDate is null" );
         hBuffer.append(" order by pt.joinPoliticDate desc" );
		 List<PbPoliticInfo> list = DaoUtil.find(hBuffer.toString());
		 if(CollectionUtils.isNotEmpty(list))
		 {

			 return BeanHelper.copyProperties(list.get(0),PbPoliticInfoDTO.class);
		 }
		return null;
	}

    public static void buildHQL(StringMap params, StringBuilder hql, HashMap<String, Object> hqlParams) throws ServiceException {
        hql.append("from PbPoliticInfo where 1=1");
        String personOid = params.getAsStringEmptyNull("personOid");
        if (personOid != null){
           	hql.append(" and personOid = :personOid");
           	try{
           		hqlParams.put("personOid", ObjectUtil.getValue(personOid, java.lang.Long.class));
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
     * 删除该人员的所有政治面貌信息
     * @param personOid
     * @throws ServiceException
     */
	public static void deleteByPersonOid(Long personOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PbPoliticInfo where personOid='"+personOid+"'");
	}

	public static List<PbPoliticInfoDTO> listPoliticInfo(Long personOid) throws DataAccessFailureException {
		String hql = "from PbPoliticInfo fi where fi.personOid = :personOid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personOid", personOid);
		List<PbPoliticInfo> boList = DaoUtil.find(hql, params);
		List<PbPoliticInfoDTO> dtoList = new ArrayList<PbPoliticInfoDTO>();
		
		if(!CollectionUtils.isEmpty(boList))
		{
			for(PbPoliticInfo bo : boList)
			{
				PbPoliticInfoDTO dto = new PbPoliticInfoDTO();
				BeanHelper.copyProperties(bo, dto);
				dtoList.add(dto);
			}
		}
		return dtoList;
	}
}
