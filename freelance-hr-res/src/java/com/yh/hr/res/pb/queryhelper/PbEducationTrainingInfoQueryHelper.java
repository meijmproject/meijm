package com.yh.hr.res.pb.queryhelper;

import com.yh.hr.res.pb.bo.PbEducationTrainingInfo;
import com.yh.hr.res.pb.dto.PbEducationTrainingInfoDTO;
import jade.workflow.utils.ObjectUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringMap;

public class PbEducationTrainingInfoQueryHelper {
	
	public static List<PbEducationTrainingInfoDTO> find(TableTagBean ttb) throws ServiceException {
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
        	//教育培训信息默认排序,起始时间倒序
        	hql.append(" order by trainingBeginDate desc ");
        }
        List<PbEducationTrainingInfo> list = DaoUtil.listByCondition(hql.toString(), hqlParams, ttb.getPage() * ttb.getPageSize(), ttb.getPageSize());
        
        
        List<PbEducationTrainingInfoDTO> listDTO = new ArrayList<PbEducationTrainingInfoDTO>();
        
        for(PbEducationTrainingInfo bo: list){
        	PbEducationTrainingInfoDTO dto = new PbEducationTrainingInfoDTO();
        	BeanHelper.copyProperties(bo, dto);
			listDTO.add(dto);
        }
        
        ttb.setList(listDTO);
        ttb.setTotal(DaoUtil.countByCondition("select count(*) "+hql, hqlParams));
        return listDTO;
	}

    public static void buildHQL(StringMap params, StringBuilder hql, HashMap<String, Object> hqlParams) throws ServiceException {
        hql.append("from PbEducationTrainingInfo where 1=1");
        String personOid = params.getAsStringEmptyNull("personOid");
        if (personOid != null){
           	hql.append(" and personOid = :personOid");
           	try{
           		hqlParams.put("personOid", ObjectUtil.getValue(personOid, java.lang.Long.class));
        	} catch (jade.workflow.exception.ServiceException e) {
			e.printStackTrace();
			}
        }
        String educationTrainingKinkCode = params.getAsStringEmptyNull("educationTrainingKinkCode");
        if (educationTrainingKinkCode != null){
           hql.append(" and educationTrainingKinkCode like :educationTrainingKinkCode");
           hqlParams.put("educationTrainingKinkCode", "%"+educationTrainingKinkCode.trim()+"%");
        }
        String trainingStatus = params.getAsStringEmptyNull("trainingStatus");
        if (trainingStatus != null){
           hql.append(" and trainingStatus like :trainingStatus");
           hqlParams.put("trainingStatus", "%"+trainingStatus.trim()+"%");
        }
        String trainingUnitName = params.getAsStringEmptyNull("trainingUnitName");
        if (trainingUnitName != null){
           hql.append(" and trainingUnitName like :trainingUnitName");
           hqlParams.put("trainingUnitName", "%"+trainingUnitName.trim()+"%");
        }
   }

	public static List<PbEducationTrainingInfoDTO> listPbEducationTrainingInfoByPersonOid(Long personOid) throws ServiceException {
		String hql = "from PbEducationTrainingInfo ei where ei.personOid = :personOid order by ei.trainingBeginDate desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personOid", personOid);
		List<PbEducationTrainingInfo> boList = DaoUtil.find(hql, params);
		List<PbEducationTrainingInfoDTO> dtoList = new ArrayList<PbEducationTrainingInfoDTO>();
		if(CollectionUtils.isNotEmpty(boList))
		{
			dtoList = BeanHelper.copyProperties(boList, PbEducationTrainingInfoDTO.class);
		}
		return dtoList;
	}
}
