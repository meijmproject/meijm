package com.yh.hr.res.pb.queryhelper;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pb.bo.PbDutyInfo;
import com.yh.hr.res.pb.dto.PbDutyInfoDTO;
import com.yh.hr.res.unit.bo.UtOrg;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringMap;
import com.yh.platform.core.util.StringUtil;
import jade.workflow.utils.ObjectUtil;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PbDutyInfoQueryHelper {
	
	public static List<PbDutyInfoDTO> find(TableTagBean ttb) throws ServiceException {
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
        	//院内中层职务信息默认排序,任职时间倒序
        	hql.append(" order by startDate desc ");
        }
        List<PbDutyInfo> list = DaoUtil.listByCondition(hql.toString(), hqlParams, ttb.getPage() * ttb.getPageSize(), ttb.getPageSize());
        
        
        List<PbDutyInfoDTO> listDTO = new ArrayList<PbDutyInfoDTO>();
        
        for(PbDutyInfo bo: list){
        	PbDutyInfoDTO dto = new PbDutyInfoDTO();
        	BeanHelper.copyProperties(bo, dto);
			listDTO.add(dto);
        }
        ttb.setList(listDTO);
        ttb.setTotal(DaoUtil.countByCondition("select count(*) "+hql, hqlParams));
        return listDTO;
	}

    public static void buildHQL(StringMap params, StringBuilder hql, HashMap<String, Object> hqlParams) throws ServiceException {
        hql.append("from PbDutyInfo where 1=1");
        String personOid = params.getAsStringEmptyNull("personOid");
        if (personOid != null){
           	hql.append(" and personOid = :personOid");
           	try{
           		hqlParams.put("personOid", ObjectUtil.getValue(personOid, java.lang.Long.class));
        	} catch (jade.workflow.exception.ServiceException e) {
			e.printStackTrace();
			}
        }
   }
    
    /**
     * 根据人员id查询该人员所有的院内中层职务任职信息记录
     * @param personOid
     * @return List<PbDutyInfoDTO>
     */
	public static List<PbDutyInfoDTO> listPbDutyInfoByPersonOid(Long personOid) throws ServiceException{
		String hql = "from PbDutyInfo ei where ei.personOid = :personOid order by ei.startDate desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personOid", personOid);
		List<PbDutyInfo> boList = DaoUtil.find(hql, params);
		List<PbDutyInfoDTO> dtoList = new ArrayList<PbDutyInfoDTO>();
		if(CollectionUtils.isNotEmpty(boList))
		{
			dtoList = BeanHelper.copyProperties(boList, PbDutyInfoDTO.class);
		}
		return dtoList;
	}
    /**
     * 根据人员id查询该人员所有的院内中层职务任职信息记录
     * @param personOid
     * @return List<PbDutyInfoDTO>
     */
	public static List<PbDutyInfoDTO> listExportPbDutyInfoByPersonOid(Long personOid) throws ServiceException{
		List<PbDutyInfo> list = DaoUtil.findByNamed("from PbDutyInfo di where di.personOid =:personOid order by di.startDate desc", "personOid", personOid);
		List<PbDutyInfoDTO> listDTO = new ArrayList<PbDutyInfoDTO>();
		if(!CollectionUtils.isNotEmpty(list)) {
			return null;
		}else{
			for(PbDutyInfo pbDutyInfo:list){
				List<UtOrg> utOrgList = DaoUtil.findByNamed("from UtOrg uo where uo.orgOid =:deptOid ", "deptOid", pbDutyInfo.getDeptOid());
				PbDutyInfoDTO pbDutyInfoDTO = new PbDutyInfoDTO();
				BeanHelper.copyProperties(pbDutyInfo, pbDutyInfoDTO);
				pbDutyInfoDTO.setDeptName(utOrgList.get(0).getOrgName());
				listDTO.add(pbDutyInfoDTO);
			}
			return listDTO;
		}
	}
	/**
     * 查询主要院内中层职务任职信息记录
     * @param personOid
     * @param flag
     * @return
     * @throws DataAccessFailureException
     * @throws ServiceException
     */
	public static PbDutyInfoDTO getPbProfTechQualifInfoByCond(
			Long personOid, String flag) throws DataAccessFailureException, ServiceException  {
		StringBuffer hBuffer = new StringBuffer("from PbDutyInfo pt where  1 =1 ");
		if (StringUtil.isNotNull(personOid)) {
            hBuffer.append(" and  pt.personOid =" + personOid);
        }
		if (StringUtil.isNotBlank(flag)) {
            hBuffer.append(" and  pt.isMainDutyInfo ='" + flag).append("'");
        }
		 List<PbDutyInfo> list = DaoUtil.find(hBuffer.toString());
		 if(CollectionUtils.isNotEmpty(list))
		 {
			 return BeanHelper.copyProperties(list.get(0),PbDutyInfoDTO.class);
		 }
		return null;
	}


	/**
	 * 根据人员id获取主职务
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public static PbDutyInfoDTO getPbDutyInfoByPersonOid(Long personOid)throws ServiceException {
		// TODO Auto-generated method stub
		String hql = "from PbDutyInfo ei where ei.personOid = :personOid and ei.isMainDutyInfo='"+DicConstants.YHRS0003_1+"' and ei.dutyStatus = '"+DicConstants.YHRS0026_001+"'";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personOid", personOid);
		List<PbDutyInfo> boList = DaoUtil.find(hql, params);
		List<PbDutyInfoDTO> dtoList = new ArrayList<PbDutyInfoDTO>();
		if(CollectionUtils.isNotEmpty(boList))
		{
			dtoList = BeanHelper.copyProperties(boList, PbDutyInfoDTO.class);
			return dtoList.get(0);//如果有多条记录取第一条（不应该存在多条）
			
		}
		return null;
	}

}
