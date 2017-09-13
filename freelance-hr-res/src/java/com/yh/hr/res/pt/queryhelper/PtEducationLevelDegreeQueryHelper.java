package com.yh.hr.res.pt.queryhelper;

import com.yh.hr.res.pt.bo.PtEngageConHistInfo;
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
import com.yh.hr.res.pt.bo.PtEducationLevelDegree;
import com.yh.hr.res.pt.dto.PtEducationLevelDegreeDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.StringMap;
/**
 * @desc 学历学位业务信息查询工具类
 * @author liul
 * @createDate 2017-2-17
 */
public class PtEducationLevelDegreeQueryHelper {
	
	public static List<PtEducationLevelDegreeDTO> list(TableTagBean ttb) throws ServiceException {
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
        List<PtEducationLevelDegree> list = DaoUtil.listByCondition(hql.toString(), hqlParams, ttb.getPage() * ttb.getPageSize(), ttb.getPageSize());
        
        
        List<PtEducationLevelDegreeDTO> listDTO = new ArrayList<PtEducationLevelDegreeDTO>();
        
        for(PtEducationLevelDegree bo: list){
        	PtEducationLevelDegreeDTO dto = new PtEducationLevelDegreeDTO();
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
        hql.append("from PtEducationLevelDegree where 1=1");
        String bizPersonOid = params.getAsStringEmptyNull("bizPersonOid");
        if (bizPersonOid != null){
           	hql.append(" and bizPersonOid = :bizPersonOid");
           	try{
           		hqlParams.put("bizPersonOid", ObjectUtil.getValue(bizPersonOid, java.lang.Long.class));
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
	 * 根据业务人员OID查询该人员所有的学历学位业务信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtEducationLevelDegreeDTO> listPtEducationLevelDegreeByBizPersonOid(Long bizPersonOid) throws ServiceException {
		String hql = "from PtEducationLevelDegree ei where ei.bizPersonOid = :bizPersonOid order by ei.graduateDate desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		List<PtEducationLevelDegree> boList = DaoUtil.find(hql, params);
		List<PtEducationLevelDegreeDTO> dtoList = new ArrayList<PtEducationLevelDegreeDTO>();
		if(CollectionUtils.isNotEmpty(boList))
		{
			dtoList = BeanHelper.copyProperties(boList, PtEducationLevelDegreeDTO.class);
		}
		return dtoList;
	}
	
	/**
	 * 通过基础OID查找该人员的学历学位业务信息
	 * @param baseEducationLevelOid
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtEducationLevelDegreeDTO> listPtEducationLevelDegreeByBaseEducationLevelOid(Long baseEducationLevelOid) throws ServiceException {
		List<PtEngageConHistInfo> list = DaoUtil.findByNamed("from PtEducationLevelDegree ei where ei.baseEducationLevelOid = :baseEducationLevelOid order by ei.graduateDate desc", "baseEducationLevelOid", baseEducationLevelOid);
		return BeanHelper.copyProperties(list, PtEducationLevelDegreeDTO.class);
	}

	/*
	 * 当flag为true时，批量更新最高学历或学位标识
	 */
	public static void updateIsHighFlag(Long bizPersonOid,Long educationLevelOid,boolean flag) throws ServiceException{
		final StringBuffer hql = new StringBuffer(" update PtEducationLevelDegree ei ");
		if(flag){
			hql.append(" set ei.isHighestEducationLevel = '" +DicConstants.YHRS0003_0+ "'");
		}else{
			hql.append(" set ei.isHighestDegree = '" +DicConstants.YHRS0003_0+ "'");
		}
		hql.append(" where  ei.educationLevelOid <> " +educationLevelOid);
		hql.append(" and  ei.bizPersonOid =  " +bizPersonOid);
		DaoUtil.executeUpdate(hql.toString());
	}

	/**
	 * 通过业务人员OID删除该人员的所有学历学位业务信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public static void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		DaoUtil.executeUpdate("delete from PtEducationLevelDegree where bizPersonOid='"+bizPersonOid+"'");
	}
}
