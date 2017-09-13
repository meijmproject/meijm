package com.yh.hr.res.pt.service.impl;

import com.yh.hr.res.pt.bo.PtResumeInfo;
import com.yh.hr.res.pt.service.PtResumeInfoService;
import jade.workflow.utils.ObjectUtil;

import java.util.HashMap;
import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.pt.dto.PtResumeInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtResumeInfoQueryHelper;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.StringMap;
import com.yh.platform.core.web.UserContext;

/**
 * 工作简历信息service实现类（业务）
 * @author zhengdr
 * 时间:2016-10-10上午10:18:00
 */
public class PtResumeInfoServiceImpl implements PtResumeInfoService {

	/*
	 * (non-Javadoc)
	 * @see PtResumeInfoService#createPtResumeInfo(PtResumeInfoDTO)
	 */
	public void createPtResumeInfo(PtResumeInfoDTO ptResumeInfoDTO)
			throws ServiceException {
		PtResumeInfo ptResumeInfo = BeanHelper.copyProperties(ptResumeInfoDTO,
				PtResumeInfo.class);
		//新增人信息
		ptResumeInfo.setCreateBy(UserContext.getLoginUserID());
		ptResumeInfo.setCreateName(UserContext.getLoginUserName());
		ptResumeInfo.setCreateDate(DateUtil.now());
		 ptResumeInfo.setUpdateBy(UserContext.getLoginUserID());
		 ptResumeInfo.setUpdateName(UserContext.getLoginUserName());
		 ptResumeInfo.setUpdateDate(DateUtil.now());
		ptResumeInfo.save();
	}
	
	/*
	 * (non-Javadoc)
	 * @see PtResumeInfoService#updatePtResumeInfo(PtResumeInfoDTO)
	 */
	public void updatePtResumeInfo(PtResumeInfoDTO ptResumeInfoDTO) throws ServiceException {
		 PtResumeInfo ptResumeInfo = DaoUtil.get(PtResumeInfo.class, ptResumeInfoDTO.getPtResumeOid());
		 if(ptResumeInfo!=null){
			 BeanHelper.copyProperties(ptResumeInfoDTO, ptResumeInfo, BeanHelper.getNullPropertyNames(ptResumeInfoDTO));
			 ptResumeInfo.setUpdateBy(UserContext.getLoginUserID());
			 ptResumeInfo.setUpdateName(UserContext.getLoginUserName());
			 ptResumeInfo.setUpdateDate(DateUtil.now());
			 ptResumeInfo.update();
	     }
		
	}

	/*
	 * (non-Javadoc)
	 * @see PtResumeInfoService#getPtResumeInfoById(java.lang.Long)
	 */
	public PtResumeInfoDTO getPtResumeInfoById(Long ptResumeOid)
			throws ServiceException {
		
		return PtResumeInfoQueryHelper.getPtResumeInfoDTOById(ptResumeOid);
	}

	/*
	 * (non-Javadoc)
	 * @see PtResumeInfoService#deletePtResumeInfoById(java.lang.Long)
	 */
	public void deletePtResumeInfoById(Long ptResumeOid) throws ServiceException {
		
		PtResumeInfo ptResumeInfo = DaoUtil.get(PtResumeInfo.class, ptResumeOid);
	    if(ptResumeInfo!=null){
	    	
	    	ptResumeInfo.delete();
	    }
	}
    
	/*
	 * (non-Javadoc)
	 * @see PtResumeInfoService#listPtResumeInfo(java.lang.Long)
	 */
	public List<PtResumeInfoDTO> listPtResumeInfo(Long bizPersonOid)
			throws ServiceException {
		
		return PtResumeInfoQueryHelper.listPtResumeInfo(bizPersonOid);
	}

	/*
	 * (non-Javadoc)
	 * @see PtResumeInfoService#countPtResumeInfoByBizPersonOid(java.lang.Long)
	 */
	public int countPtResumeInfoByBizPersonOid(Long bizPersonOid)
			throws ServiceException {
		return PtResumeInfoQueryHelper.countPtResumeInfoByBizPersonOid(bizPersonOid);
	}

	public List<PtResumeInfoDTO> listNotEndPtResumeInfo(Long bizPersonOid) throws ServiceException {
		return PtResumeInfoQueryHelper.listNotEndPtResumeInfo(bizPersonOid);
	}
	
	/**
	 * 通过基础OID查找该人员的工作简历业务信息
	 * @param resumeOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtResumeInfoDTO> listPtResumeInfoByResumeOid(Long resumeOid) throws ServiceException {
		return PtResumeInfoQueryHelper.listPtResumeInfoByResumeOid(resumeOid);
	}
	
	/**
	 * 根据bizPersonOid删除人员工作简历信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		PtResumeInfoQueryHelper.deleteByBizPersonOid(bizPersonOid);
	}
	
	public List<PtResumeInfoDTO> find(TableTagBean ttb) throws ServiceException {
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
        List<PtResumeInfoDTO> list = BeanHelper.copyProperties(DaoUtil.listByCondition(hql.toString(), hqlParams, ttb.getPage() * ttb.getPageSize(), ttb.getPageSize()),PtResumeInfoDTO.class);
        ttb.setList(list);
        ttb.setTotal(DaoUtil.countByCondition("select count(*) "+hql, hqlParams));
        return list;
	}

    public void buildHQL(StringMap params, StringBuilder hql, HashMap<String, Object> hqlParams) throws ServiceException {
        hql.append(" from PtResumeInfo where 1=1");
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