package com.yh.hr.res.pt.queryhelper;

import com.yh.hr.res.pt.bo.PtEducationTrainingInfo;
import com.yh.hr.res.pt.dto.PtEducationTrainingInfoDTO;
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
/**
 * @desc 教育培训业务信息查询工具类
 * @author liul
 * @createDate 2017-2-17
 */
public class PtEducationTrainingInfoQueryHelper {
	
	public static List<PtEducationTrainingInfoDTO> list(TableTagBean ttb) throws ServiceException {
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
        List<PtEducationTrainingInfo> list = DaoUtil.listByCondition(hql.toString(), hqlParams, ttb.getPage() * ttb.getPageSize(), ttb.getPageSize());
        
        
        List<PtEducationTrainingInfoDTO> listDTO = new ArrayList<PtEducationTrainingInfoDTO>();
        
        for(PtEducationTrainingInfo bo: list){
        	PtEducationTrainingInfoDTO dto = new PtEducationTrainingInfoDTO();
        	BeanHelper.copyProperties(bo, dto);
			listDTO.add(dto);
        }
        
        ttb.setList(listDTO);
        ttb.setTotal(DaoUtil.countByCondition("select count(*) "+hql, hqlParams));
        return listDTO;
	}

    private static void buildHQL(StringMap params, StringBuilder hql, HashMap<String, Object> hqlParams) throws ServiceException {
        hql.append("from PtEducationTrainingInfo where 1=1");
        String bizPersonOid = params.getAsStringEmptyNull("bizPersonOid");
        if (bizPersonOid != null){
           	hql.append(" and bizPersonOid = :bizPersonOid");
           	try{
           		hqlParams.put("bizPersonOid", ObjectUtil.getValue(bizPersonOid, java.lang.Long.class));
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

    /**
	 * 通过业务人员OID查找该人员的教育培训业务信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtEducationTrainingInfoDTO> listPtEducationTrainingInfoByBizPersonOid(Long bizPersonOid) throws ServiceException {
		String hql = "from PtEducationTrainingInfo ei where ei.bizPersonOid = :bizPersonOid order by ei.trainingBeginDate desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		List<PtEducationTrainingInfo> boList = DaoUtil.find(hql, params);
		List<PtEducationTrainingInfoDTO> dtoList = new ArrayList<PtEducationTrainingInfoDTO>();
		if(CollectionUtils.isNotEmpty(boList))
		{
			dtoList = BeanHelper.copyProperties(boList, PtEducationTrainingInfoDTO.class);
		}
		return dtoList;
	}
	
	/**
	 * 通过基础OID查找该人员的教育培训业务信息
	 * @param baseEducationTrainingOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtEducationTrainingInfoDTO> listPtEducationTrainingInfoByBaseEducationTrainingOid(Long baseEducationTrainingOid) throws ServiceException {
		List<PtEducationTrainingInfo> list = DaoUtil.findByNamed("from PtEducationTrainingInfo ei where ei.baseEducationTrainingOid = :baseEducationTrainingOid order by ei.trainingBeginDate desc", "baseEducationTrainingOid", baseEducationTrainingOid);
		return BeanHelper.copyProperties(list, PtEducationTrainingInfoDTO.class);
	}
	
	/**
	 * 通过业务人员OID删除该人员的所有教育培训业务信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtEducationTrainingInfo where bizPersonOid='"+bizPersonOid+"'");
	}
}
