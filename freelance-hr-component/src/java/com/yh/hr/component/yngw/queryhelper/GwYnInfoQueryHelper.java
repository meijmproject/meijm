package com.yh.hr.component.yngw.queryhelper;

import com.yh.hr.component.yngw.bo.GwYnInfo;
import com.yh.hr.component.yngw.dto.GwYnInfoDTO;
import jade.workflow.utils.ObjectUtil;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.yh.component.dictionary.bo.DicItem;
import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringMap;

public class GwYnInfoQueryHelper {
	
	public static List<GwYnInfoDTO> find(TableTagBean ttb) throws ServiceException {
		StringBuilder hql = new StringBuilder();
        HashMap<String, Object> hqlParams = new HashMap<String, Object>();
        StringBuilder queryhql = new StringBuilder();
        queryhql.append(" select gy.position_oid,gy.POSITION_NAME,gy.PARENT_POSITION_OID,gy.PARENT_POSITION_NAME,")
		.append(" gy.HIS_WORK_TYPE,gy.HIS_POSITION_TYPE,gy.HIS_POSITION_QUALIFICATIONS,gy.HIS_POSITION_OBLIGATION,gy.REMARK ");
        buildHQL(ttb.getCondition(), hql, hqlParams);
        queryhql.append(hql.toString());
        queryhql.append(" ORDER BY FIND_IN_SET(gy.position_Oid,(select GROUP_CONCAT(GET_ALL_CHILD_YNGW(g.position_Oid))" )
    	.append(" from yhg_gb_yn_info g where g.parent_Position_Oid is null order by g.position_Oid desc))");
        //List<GwYnInfo> list = DaoUtil.listByCondition(hql.toString(), hqlParams, ttb.getPage(), ttb.getPageSize());
        List<Object[]> list = DaoUtil.listWithSQLByCondition(queryhql.toString(), hqlParams, ttb.getPage(), ttb.getPageSize());
        List<GwYnInfoDTO> dtoList = new ArrayList<GwYnInfoDTO>();
		if(!CollectionUtils.isEmpty(list))
		{
			for(Object[] obj : list)
			{
				GwYnInfoDTO dto = new GwYnInfoDTO();
				dto.setPositionOid(obj[0]!=null?Long.valueOf(obj[0].toString()):null);
				dto.setPositionName(obj[1]!=null?obj[1].toString():"");
				dto.setParentPositionOid(obj[2]!=null?Long.valueOf(obj[2].toString()):null);
				dto.setParentPositionName(obj[3]!=null?obj[3].toString():"");
				dto.setHisWorkType(obj[4]!=null?obj[4].toString():"");
				dto.setHisPositionType(obj[5]!=null?obj[5].toString():"");
				dto.setHisPositionQualifications(obj[6]!=null?obj[6].toString():"");
				dto.setHisPositionObligation(obj[7]!=null?obj[7].toString():"");
				dto.setRemark(obj[8]!=null?obj[8].toString():"");
				dtoList.add(dto);
			}
			/*for(GwYnInfo bo : list)
			{
				GwYnInfoDTO dto = new GwYnInfoDTO();
				BeanHelper.copyProperties(bo, dto);
				dtoList.add(dto);
			}*/
		}
        ttb.setList(list);
        //ttb.setTotal(DaoUtil.countByCondition("select count(*) "+hql, hqlParams));
        ttb.setTotal(DaoUtil.countWithSQLByCondition("select count(*) "+hql, hqlParams));
        return dtoList;
	}
	
	public static void buildHQL(StringMap params, StringBuilder hql, HashMap<String, Object> hqlParams) throws ServiceException {
       // hql.append("from GwYnInfo where 1=1");
       
       /* hql.append(" from GwYnInfo gy where ")
        .append(" FIND_IN_SET (gy.positionOid,(select GROUP_CONCAT(GET_ALL_CHILD_YNGW(g.positionOid))")
        .append(" from GwYnInfo g where g.parentPositionOid is null order by g.positionOid desc)) >= 0 ");*/
		
		hql.append( "  from yhg_gb_yn_info gy ")
        .append(" where  FIND_IN_SET(gy.position_Oid,(select GROUP_CONCAT(GET_ALL_CHILD_YNGW(g.position_Oid)) ")
        .append(" from yhg_gb_yn_info g where g.parent_Position_Oid is null order by g.position_Oid desc))");
        
        String parentPositionName = params.getAsStringEmptyNull("parentPositionName");
        if (StringUtils.isNotEmpty(parentPositionName)){
           	hql.append(" and parent_Position_Name = :parentPositionName");
           	try{
           		hqlParams.put("parentPositionName", ObjectUtil.getValue(parentPositionName, java.lang.String.class));
        	} catch (jade.workflow.exception.ServiceException e) {
			e.printStackTrace();
			}
        }
        //因选择组件用的此名称，为了不修改组件，影响其他功能，故添加此查询逻辑
        String hisPositionName = params.getAsStringEmptyNull("hisPositionName");
        if (StringUtils.isNotEmpty(hisPositionName)){
           	hql.append(" and parent_Position_Name = :parentPositionName");
           	try{
           		hqlParams.put("parentPositionName", ObjectUtil.getValue(hisPositionName, java.lang.String.class));
        	} catch (jade.workflow.exception.ServiceException e) {
			e.printStackTrace();
			}
        }
        
        String hisWorkType = params.getAsStringEmptyNull("hisWorkType");
        if (StringUtils.isNotEmpty(hisWorkType)){
           	hql.append(" and his_Work_Type = :hisWorkType");
           	try{
           		hqlParams.put("hisWorkType", ObjectUtil.getValue(hisWorkType, java.lang.String.class));
        	} catch (jade.workflow.exception.ServiceException e) {
			e.printStackTrace();
			}
        }
        String hisPositionType = params.getAsStringEmptyNull("hisPositionType");
        if (StringUtils.isNotEmpty(hisPositionType)){
           	hql.append(" and his_Position_Type = :hisPositionType");
           	try{
           		hqlParams.put("hisPositionType", ObjectUtil.getValue(hisPositionType, java.lang.String.class));
        	} catch (jade.workflow.exception.ServiceException e) {
			e.printStackTrace();
			}
        }
        String positionName = params.getAsStringEmptyNull("positionName");
        if (StringUtils.isNotEmpty(positionName)){
           hql.append(" and position_Name like :positionName");
           hqlParams.put("positionName", "%"+positionName.trim()+"%");
        }
   }

	
	/**
	 * 列出所有的院内岗位信息
	 * @return List<GwYnInfoDTO>
	 * @throws ServiceException
	 */
	public static List<GwYnInfoDTO> listAllGwYnInfo() throws ServiceException {
		String hql = "from GwYnInfo gyi";
		List<GwYnInfo> boList = DaoUtil.find(hql);
		List<GwYnInfoDTO> dtoList = new ArrayList<GwYnInfoDTO>();
		if(CollectionUtils.isNotEmpty(boList))
		{
			dtoList = BeanHelper.copyProperties(boList, GwYnInfoDTO.class);
		}
		return dtoList;
	}

	/**
	 * 按条件查询岗位名称信息
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public static List<GwYnInfoDTO> listByCondition(TableTagBean ttb) throws ServiceException {
			
			StringBuilder hql = new StringBuilder();
			Map<String, Object> params = new HashMap<String, Object>();
			String hisWorkType = ttb.getCondition().get("hisWorkType");
			String hisPositionType = ttb.getCondition().get("hisPositionType");
			String positionName = ttb.getCondition().get("positionName");
			String queryPositionName = ttb.getCondition().get("queryPositionName");
				
			hql.append(" from GwYnInfo u where 1=1 ");
			//如果是查小类  则大类为条件到岗位名称表中查（点击了大类）（岗位名称不为空？）
			if (StringUtils.isNotEmpty(hisWorkType)&&!"undefined".equals(hisWorkType)) {
				hql.append(" and u.hisWorkType ='"+hisWorkType+"'");
			}
			if(StringUtils.isNotEmpty(hisPositionType)&&!"undefined".equals(hisWorkType)){
				hql.append(" and u.hisPositionType ='"+hisPositionType+"'");
			}
			if(StringUtils.isNotEmpty(positionName)){
				hql.append(" and u.parentPositionName='").append(positionName).append("'");
			}
			if(StringUtils.isEmpty(positionName)&&StringUtils.isEmpty(queryPositionName)){
				hql.append(" and u.parentPositionOid is null ");
			}
			if(StringUtils.isNotEmpty(queryPositionName)){
				hql.append(" and u.positionName  like '%").append(queryPositionName).append("%'");
			}
			
			return BeanHelper.copyProperties(DaoUtil.listByCondition(
					new StringBuilder().append(hql).append(" order by u.parentPositionOid,u.positionOid").toString(), params, ttb.getPage(), ttb.getPageSize()), GwYnInfoDTO.class);
		}
	/**
	 * 根据名称获取code(点击)
	 * @throws ServiceException
	 */
	public static String getDicItemByName1(String hisPositionName) throws ServiceException {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		hql.append("from DicItem di where 1=1");
		hql.append(" and di.dicItemName = '"+hisPositionName+"' and di.parentCode is not null" );
		List<DicItem> list=BeanHelper.copyProperties(DaoUtil.listByCondition(
				new StringBuilder().append(hql).toString(), params, 0, 0), DicItem.class);
		if(!CollectionUtils.isEmpty(list)){
			return list.get(0).getDicItemCode();
		}
		return "";
	}
	/**
	 * 根据名称获取code(模糊查询)
	 * @throws ServiceException
	 */
	public static String getDicItemByName2(String hisPositionName) throws ServiceException {
		StringBuilder hql = new StringBuilder();
		String positionNameDl = "";
		Map<String, Object> params = new HashMap<String, Object>();
		hql.append("from DicItem di where 1=1");
		hql.append(" and di.dicItemName like '"+hisPositionName+"' and di.parentCode is not null" );
		List<DicItem> list = BeanHelper.copyProperties(DaoUtil.listByCondition(
				new StringBuilder().append(hql).toString(), params, 0, 0), DicItem.class);
		if(!CollectionUtils.isEmpty(list)){
			for(int i=0;i<list.size();i++){
				positionNameDl+="'"+list.get(i).getDicItemCode()+"'";
				if(i!=list.size()-1){
					positionNameDl+=",";
				}
				
			}
		}
		return positionNameDl;
	}
	/**
	 * 是否存在小类
	 * @param positionNameDl
	 * @return
	 * @throws ServiceException
	 */
	public static String haveBranchPositionName(Long positionOid) throws ServiceException {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		hql.append("from GwYnInfo di where 1=1");
		hql.append(" and di.parentPositionOid = '"+positionOid+"'");
		List<GwYnInfo> list=BeanHelper.copyProperties(DaoUtil.listByCondition(
				new StringBuilder().append(hql).toString(), params, 0, 0), GwYnInfo.class);
		if(CollectionUtils.isNotEmpty(list)){
			return "have";
		}
		return "";
		
	}
	
	/**
	 * 该大类的父节点是否已经存在
	 * @param positionNameDl
	 * @return
	 * @throws ServiceException 
	 * @throws DataAccessFailureException 
	 */
	public static String positionNameParentNodeIsExist(String positionNameDl) throws ServiceException {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		hql.append("from GwYnInfo di where 1=1");
		hql.append(" and di.positionNameDl = '"+positionNameDl+"' and di.positionNameXl is null");
		List<GwYnInfo> list=BeanHelper.copyProperties(DaoUtil.listByCondition(
				new StringBuilder().append(hql).toString(), params, 0, 0), GwYnInfo.class);
		if(CollectionUtils.isNotEmpty(list)){
			return "have";
		}
		return "";
	}

	/**
	 * 根据岗位类别查询岗位信息
	 * @param positionType
	 * @return
	 */
	public static List<GwYnInfoDTO> findPositionInfoByPositionType(
			String positionType)throws DataAccessFailureException 
	{
		List<GwYnInfo> list = DaoUtil.findByNamed("from GwYnInfo where hisPositionType=:hisPositionType", "hisPositionType", positionType);
		if(CollectionUtils.isNotEmpty(list))
		{
			List<GwYnInfoDTO> dtoList = new ArrayList<GwYnInfoDTO>();
			for(GwYnInfo bo : list)
			{
				GwYnInfoDTO dto = new GwYnInfoDTO();
				BeanUtils.copyProperties(bo, dto);
				dtoList.add(dto);
			}
			return dtoList;
		}
		return null;
	}
	
	/**
	 * 根据岗位OID删除岗位信息，包括叶子节点
	 * @param positionOid
	 */
	public static void deleteByPositionOid(Long positionOid) throws DataAccessFailureException
	{
		
		StringBuffer sb = new StringBuffer(" select position_Oid from yhg_gb_yn_info where FIND_IN_SET(POSITION_OID,GET_ALL_CHILD_YNGW(")
		.append(positionOid).append("))");
		List<BigInteger> list = DaoUtil.findWithSQL(sb.toString());
		if(CollectionUtils.isNotEmpty(list))
		{
			for(BigInteger po : list)
			{
				GwYnInfo bo = DaoUtil.get(GwYnInfo.class, po.longValue());
				if(null != bo)
				{
					bo.delete();
				}
			}
		}
	}
}
