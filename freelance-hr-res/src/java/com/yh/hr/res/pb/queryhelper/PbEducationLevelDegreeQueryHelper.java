package com.yh.hr.res.pb.queryhelper;

import jade.workflow.utils.ObjectUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yh.component.dictionary.utils.DicHelper;
import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pb.bo.PbEducationLevelDegree;
import com.yh.hr.res.pb.dto.PbEducationLevelDegreeDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.StringMap;

public class PbEducationLevelDegreeQueryHelper {
	
	public static List<PbEducationLevelDegreeDTO> find(TableTagBean ttb) throws ServiceException {
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
        	//学历学位信息默认排序,毕业日期倒序
        	hql.append(" order by schoolEnrollDate desc ");
        }
        List<PbEducationLevelDegree> list = DaoUtil.listByCondition(hql.toString(), hqlParams, ttb.getPage() * ttb.getPageSize(), ttb.getPageSize());
        
        
        List<PbEducationLevelDegreeDTO> listDTO = new ArrayList<PbEducationLevelDegreeDTO>();
        
        for(PbEducationLevelDegree bo: list){
        	PbEducationLevelDegreeDTO dto = new PbEducationLevelDegreeDTO();
        	BeanHelper.copyProperties(bo, dto);
        	if(StringUtils.isNotEmpty(dto.getEduType()))
			{
        		dto.setEduType(DicHelper.viewName(DicConstants.YHRS0043, dto.getEduType()));
			}
			if(StringUtils.isNotEmpty(dto.getEducationCode()))
			{
				dto.setEducationCode(DicHelper.viewName(DicConstants.YHRS0039, dto.getEducationCode()));
			}
			if(StringUtils.isNotEmpty(dto.getDegreeCode()))
			{
				dto.setDegreeCode(DicHelper.viewName(DicConstants.YHRS0040, dto.getDegreeCode()));
			}
			if(null != dto.getSchoolEnrollDate())
			{
				dto.setSchoolEnrollDateStr(DateUtil.format(dto.getSchoolEnrollDate(), "yyyy-MM-dd"));
			}
			if(null != dto.getGraduateDate())
			{
				dto.setGraduateDateStr(DateUtil.format(dto.getGraduateDate(), "yyyy-MM-dd"));
			}
			if(null != dto.getDegreeGrantDate())
			{
				dto.setDegreeGrantDateStr(DateUtil.formatDate(dto.getDegreeGrantDate()));
			}
			listDTO.add(dto);
        }
        ttb.setList(listDTO);
        ttb.setTotal(DaoUtil.countByCondition("select count(*) "+hql, hqlParams));
        return listDTO;
	}

    public static void buildHQL(StringMap params, StringBuilder hql, HashMap<String, Object> hqlParams) throws ServiceException {
        hql.append("from PbEducationLevelDegree where 1=1");
        String personOid = params.getAsStringEmptyNull("personOid");
        if (personOid != null){
           	hql.append(" and personOid = :personOid");
           	try{
           		hqlParams.put("personOid", ObjectUtil.getValue(personOid, java.lang.Long.class));
        	} catch (jade.workflow.exception.ServiceException e) {
			e.printStackTrace();
			}
        }
        String schoolName = params.getAsStringEmptyNull("schoolName");
        if (schoolName != null){
           hql.append(" and schoolName like :schoolName");
           hqlParams.put("schoolName", "%"+schoolName.trim()+"%");
        }
        String schoolDesc = params.getAsStringEmptyNull("schoolDesc");
        if (schoolDesc != null){
           hql.append(" and schoolDesc like :schoolDesc");
           hqlParams.put("schoolDesc", "%"+schoolDesc.trim()+"%");
        }
        String academyName = params.getAsStringEmptyNull("academyName");
        if (academyName != null){
           hql.append(" and academyName like :academyName");
           hqlParams.put("academyName", "%"+academyName.trim()+"%");
        }
   }
	
	/**
	 * 根据人员id查询该人员所有的学历学位记录
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PbEducationLevelDegreeDTO> listPbEducationLevelDegreeByPersonOid(Long personOid) throws ServiceException {
		String hql = "from PbEducationLevelDegree ei where ei.personOid = :personOid order by ei.graduateDate desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("personOid", personOid);
		List<PbEducationLevelDegree> boList = DaoUtil.find(hql, params);
		List<PbEducationLevelDegreeDTO> dtoList = new ArrayList<PbEducationLevelDegreeDTO>();
		if(CollectionUtils.isNotEmpty(boList))
		{
			dtoList = BeanHelper.copyProperties(boList, PbEducationLevelDegreeDTO.class);
		}
		return dtoList;
	}

	/*
	 * 当flag为true时，批量更新最高学历或学位标识
	 */
	public static void updateIsHighFlag(Long personOid,Long educationLevelOid,boolean flag) throws ServiceException{
		final StringBuffer hql = new StringBuffer(" update  PbEducationLevelDegree ei ");
		if(flag){
			hql.append(" set ei.isHighestEducationLevel = '" +DicConstants.YHRS0003_0+ "'");
		}else{
			hql.append(" set ei.isHighestDegree = '" +DicConstants.YHRS0003_0+ "'");
		}
		hql.append(" where  ei.educationLevelOid <> " +educationLevelOid);
		hql.append(" and  ei.personOid =  " +personOid);
		DaoUtil.executeUpdate(hql.toString());
	}

	/**
	 * 删除该人员的所有学历学位记录
	 * @param personOid
	 * @throws ServiceException
	 */
	public static void deleteByPersonOid(Long personOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PbEducationLevelDegree where personOid='"+personOid+"'");
	}
}
