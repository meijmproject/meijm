package com.yh.hr.res.pt.service.impl;

import com.yh.hr.res.pt.bo.PtEducationLevelDegree;
import com.yh.hr.res.pt.dto.PtEducationLevelDegreeDTO;
import com.yh.hr.res.pt.queryhelper.PtEducationLevelDegreeQueryHelper;
import com.yh.hr.res.pt.service.PtEducationLevelDegreeService;
import jade.workflow.utils.DateUtil;
import jade.workflow.utils.ObjectUtil;

import java.util.HashMap;
import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringMap;
import com.yh.platform.core.web.UserContext;
/**
 * 学历学位业务信息service实现类
 * @author wangx
 * @date 2017-05-26
 * @version 1.0
 */
public class PtEducationLevelDegreeServiceImpl implements
		PtEducationLevelDegreeService {

	/**
	 * 创建学历学位业务信息
	 * @param ptEducationLevelDegreeDto
	 * @return 
	 * @throws ServiceException
	 */
	public Long create(PtEducationLevelDegreeDTO ptEducationLevelDegreeDto)
			throws ServiceException {
		PtEducationLevelDegree ptEducationLevelDegree = new PtEducationLevelDegree();
		BeanHelper.copyProperties(ptEducationLevelDegreeDto, ptEducationLevelDegree);
		ptEducationLevelDegree.setCreateBy(UserContext.getLoginUserID());
		ptEducationLevelDegree.setCreateName(UserContext.getLoginUserName());
		ptEducationLevelDegree.setCreateDate(DateUtil.now());
		ptEducationLevelDegree.setUpdateBy(UserContext.getLoginUserID());
		ptEducationLevelDegree.setUpdateName(UserContext.getLoginUserName());
		ptEducationLevelDegree.setUpdateDate(DateUtil.now());
		ptEducationLevelDegree.save();
		return ptEducationLevelDegree.getEducationLevelOid();
	}

	/**
	 * 通过主键ID获取学历学位业务信息
	 * @param ptEducationLevelDegreeId
	 * @return 
	 * @throws ServiceException
	 */
	public PtEducationLevelDegreeDTO get(Long ptEducationLevelDegreeId)
			throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtEducationLevelDegree.class, ptEducationLevelDegreeId), PtEducationLevelDegreeDTO.class);
	}

	/**
	 * 修改学历学位业务信息
	 * @param ptEducationLevelDegreeDto
	 * @throws ServiceException
	 */ 
	public void update(PtEducationLevelDegreeDTO ptEducationLevelDegreeDto)
			throws ServiceException {
		PtEducationLevelDegree ptEducationLevelDegree = DaoUtil.get(PtEducationLevelDegree.class, ptEducationLevelDegreeDto.getEducationLevelOid());
		if(ptEducationLevelDegree!=null) {
			BeanHelper.copyProperties(ptEducationLevelDegreeDto, ptEducationLevelDegree, BeanHelper.getNullPropertyNames(ptEducationLevelDegreeDto));
			ptEducationLevelDegree.setUpdateBy(UserContext.getLoginUserID());
			ptEducationLevelDegree.setUpdateName(UserContext.getLoginUserName());
			ptEducationLevelDegree.setUpdateDate(DateUtil.now());
			ptEducationLevelDegree.update();
		}
	}

	/**
	 * 删除学历学位业务信息
	 * @param ptEducationLevelDegreeId
	 * @throws ServiceException
	 */ 
	public void delete(Long ptEducationLevelDegreeId) throws ServiceException {
		PtEducationLevelDegree ptEducationLevelDegree = DaoUtil.get(PtEducationLevelDegree.class, ptEducationLevelDegreeId);
		if(ptEducationLevelDegree!=null) {
			ptEducationLevelDegree.delete();
		}
	}

	/**
	 * 查询所有学历学位业务信息
	 * @param ttb
	 * @return 
	 * @throws ServiceException
	 */
	public List<PtEducationLevelDegreeDTO> list(TableTagBean ttb)
			throws ServiceException {
		return PtEducationLevelDegreeQueryHelper.list(ttb);
	}

	/**
	 * 根据业务人员OID查询该人员所有的学历学位业务信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtEducationLevelDegreeDTO> listPtEducationLevelDegreeByBizPersonOid(
			Long bizPersonOid) throws ServiceException {
		return PtEducationLevelDegreeQueryHelper.listPtEducationLevelDegreeByBizPersonOid(bizPersonOid);
	}

	/**
	 * 通过基础OID查找该人员的学历学位业务信息
	 * @param baseEducationLevelOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtEducationLevelDegreeDTO> listPtEducationLevelDegreeByBaseEducationLevelOid(
			Long baseEducationLevelOid) throws ServiceException {
		return PtEducationLevelDegreeQueryHelper.listPtEducationLevelDegreeByBaseEducationLevelOid(baseEducationLevelOid);
	}

	/**
	 * 删除该人员的所有学历学位记录
	 * @param bizPersonOid
	 */
	public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		PtEducationLevelDegreeQueryHelper.deleteByBizPersonOid(bizPersonOid);
	}

	public List<PtEducationLevelDegreeDTO> find(TableTagBean ttb) throws ServiceException {
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
	        List<PtEducationLevelDegreeDTO> list = BeanHelper.copyProperties(DaoUtil.listByCondition(hql.toString(), hqlParams, ttb.getPage() * ttb.getPageSize(), ttb.getPageSize()),PtEducationLevelDegreeDTO.class);
	        ttb.setList(list);
	        ttb.setTotal(DaoUtil.countByCondition("select count(*) "+hql, hqlParams));
	        return list;
		}

	    public void buildHQL(StringMap params, StringBuilder hql, HashMap<String, Object> hqlParams) throws ServiceException {
	        hql.append(" from PtEducationLevelDegree where 1=1");
	        String personOid = params.getAsStringEmptyNull("bizPersonOid");
	        if (personOid != null){
	           	hql.append(" and bizPersonOid = :bizPersonOid");
	           	try{
	           		hqlParams.put("bizPersonOid", ObjectUtil.getValue(personOid, java.lang.Long.class));
	        	} catch (jade.workflow.exception.ServiceException e) {
				e.printStackTrace();
				}
	        }
	        String startDate = params.getAsStringEmptyNull("startDateStr");
	        if (startDate != null){
	            hql.append(" and startDate = :startDate");
	            hqlParams.put("startDate", DateUtil.parseDate(startDate));
	        }
	        String endDate = params.getAsStringEmptyNull("endDateStr");
	        if (endDate != null){
	            hql.append(" and endDate = :endDate");
	            hqlParams.put("endDate", DateUtil.parseDate(endDate));
	        }
	        String unit = params.getAsStringEmptyNull("unit");
	        if (unit != null){
	           hql.append(" and unit like :unit");
	           hqlParams.put("unit", "%"+unit.trim()+"%");
	        }
	    }
}
