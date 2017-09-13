package com.yh.hr.res.pb.queryhelper;

import jade.workflow.utils.ObjectUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pb.bo.PbReviewInfo;
import com.yh.hr.res.pb.dto.PbReviewInfoDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.StringMap;

public class PbReviewInfoQueryHelper{

	/**
	 * hql拼装
	 * @param params
	 * @param hql
	 * @param hqlParams
	 * @throws ServiceException
	 */
    public static void buildHQL(StringMap params, StringBuilder hql, HashMap<String, Object> hqlParams) throws ServiceException {
        hql.append("from PbReviewInfo where 1=1");
        String personOid = params.getAsStringEmptyNull("personOid");
        if (personOid != null){
           	hql.append(" and personOid = :personOid");
           	try{
           		hqlParams.put("personOid", ObjectUtil.getValue(personOid, java.lang.Long.class));
        	} catch (jade.workflow.exception.ServiceException e) {
			e.printStackTrace();
			}
        }
        String reviewTypeCode = params.getAsStringEmptyNull("reviewTypeCode");
        if (reviewTypeCode != null){
           hql.append(" and reviewTypeCode like :reviewTypeCode");
           hqlParams.put("reviewTypeCode", "%"+reviewTypeCode.trim()+"%");
        }
        String reviewTypeName = params.getAsStringEmptyNull("reviewTypeName");
        if (reviewTypeName != null){
           hql.append(" and reviewTypeName like :reviewTypeName");
           hqlParams.put("reviewTypeName", "%"+reviewTypeName.trim()+"%");
        }
        String reviewYear = params.getAsStringEmptyNull("reviewYearStr");
        if (reviewYear != null){
            hql.append(" and reviewYear = :reviewYear");
            hqlParams.put("reviewYear", DateUtil.parseDate(reviewYear));
        }
   }
	/**
	 * 
	 * @param params
	 * @param hql
	 * @param hqlParams
	 * @throws ServiceException
	 */
    public static List<PbReviewInfoDTO> findReviewInfoByPersonOidAndReviewYear(Long personOid,Date date) throws ServiceException {
    	StringBuilder hql = new StringBuilder();
        HashMap<String, Object> hqlParams = new HashMap<String, Object>();
    	hql.append("from PbReviewInfo r where personOid=:personOid and reviewTypeCode=:reviewTypeCode  and date_format(reviewYear,'%Y')=:reviewYear");
    	hqlParams.put("personOid", personOid);
		hqlParams.put("reviewTypeCode", DicConstants.YHRS0069_1);
		hqlParams.put("reviewYear", DateUtil.format(date,"yyyy"));
		List<PbReviewInfoDTO> list = new ArrayList<PbReviewInfoDTO>();
		List<PbReviewInfo> boList = DaoUtil.listByCondition(hql.toString(), hqlParams, 0, 0);
		if(!CollectionUtils.isEmpty(boList))
		{
			for(PbReviewInfo bo : boList)
			{
				PbReviewInfoDTO dto = new PbReviewInfoDTO();
				BeanHelper.copyProperties(bo, dto);
				list.add(dto);
			}
		}
		return list;
   }
    
    /**
     * 根据人员id和考核年度查找记录
     * @param reviewYearStr
     * @return
     * @throws DataAccessFailureException
     */
    public static List<PbReviewInfoDTO> findByReviewYear(TableTagBean ttb) throws DataAccessFailureException{
    	
    	StringBuffer hql = new StringBuffer("from PbReviewInfo ri where ri.personOid = :personOid and date_format(ri.reviewYear,'%Y') = :reviewYearStr ");
		Map<String, Object> params = new HashMap<String, Object>();
		if(null!=ttb.getCondition().get("reviewOid")){
			hql.append(" and ri.reviewOid <> :reviewOid");
			params.put("reviewOid", Long.parseLong(ttb.getCondition().get("reviewOid")));
		}
		hql.append(" order by ri.createDate asc");
		params.put("personOid", Long.parseLong(ttb.getCondition().get("personOid")));
		params.put("reviewYearStr", ttb.getCondition().get("reviewYearStr"));
		List<PbReviewInfo> boList = DaoUtil.find(hql.toString(), params);
		List<PbReviewInfoDTO> dtoList = new ArrayList<PbReviewInfoDTO>();
		
		if(!CollectionUtils.isEmpty(boList))
		{
			for(PbReviewInfo bo : boList)
			{
				PbReviewInfoDTO dto = new PbReviewInfoDTO();
				BeanHelper.copyProperties(bo, dto);
				dtoList.add(dto);
			}
		}
		return dtoList;
    	
    }
	/**
	 * 根据personOid获取PbReviewInfo详细信息
	 * @param personOid
	 * @return
	 */
	public static List<PbReviewInfoDTO> getPbReviewInfoById(Long personOid) throws ServiceException {
		StringBuffer hql = new StringBuffer("select jpri.review_type_code,jpri.review_result_code,jpri.review_year,jpri.review_date,jpri.remark,juo.org_name from YHC_PB_REVIEW_INFO jpri,yhc_ut_org juo  where juo.org_oid = jpri.review_unit_oid and jpri.person_oid ="+personOid);
		List<Object[]> list= DaoUtil.findWithSQL(hql.toString());
		if (CollectionUtils.isNotEmpty(list)) 
		{
			List<PbReviewInfoDTO> items = new ArrayList<PbReviewInfoDTO>();
			
			for (Object[] objs : list) 
			{
				PbReviewInfoDTO dto = new PbReviewInfoDTO();
				dto.setReviewTypeCode(objs[0] == null ? null : objs[0].toString());
				dto.setReviewResultCode(objs[1] == null ? null : Long.valueOf(objs[1].toString()));
				dto.setReviewYear(DateUtil.parseDate(objs[2] == null ? null : objs[2].toString()));
				dto.setReviewDate(DateUtil.parseDate(objs[3] == null ? null : objs[3].toString()));
				dto.setRemark(objs[4] == null ? null : objs[4].toString());
				dto.setReviewUnitName(objs[5] == null ? null : objs[5].toString());
				items.add(dto);
			}
			return items;
		}
		return null;
	}
    
}
