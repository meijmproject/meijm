package com.yh.hr.res.pt.queryhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.hr.res.pt.bo.PtPositioningDis;
import com.yh.hr.res.pt.dto.PtPositioningDisDTO;
import org.springframework.util.CollectionUtils;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 免职信息查询工具类
 * @author zhengdr
 *
 * 时间:2016-11-5下午02:06:27
 */
public class PtPositioningDisQueryHelper
{
	public static List<PtPositioningDisDTO> listPtPositioningDisByBizPersonOid(Long bizPersonOid) throws ServiceException
	{
		String hql = "from PtPositioningDis fi where fi.bizPersonOid = :bizPersonOid ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		List<PtPositioningDis> boList = DaoUtil.find(hql, params);
		List<PtPositioningDisDTO> dtoList = new ArrayList<PtPositioningDisDTO>();
		
		if(!CollectionUtils.isEmpty(boList))
		{
			for(PtPositioningDis bo : boList)
			{
				PtPositioningDisDTO dto = new PtPositioningDisDTO();
				BeanHelper.copyProperties(bo, dto);
				dtoList.add(dto);
			}
		}
		
		return dtoList;
	}
	
	/**
	 * 删除
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
    public static void deletePtPositioningHisByBizPersonOid(Long bizPersonOid)throws ServiceException {
		
		DaoUtil.executeUpdate("delete from PtPositioningDis pod where pod.bizPersonOid = " + bizPersonOid);
	}
    
    /**
     * 得到免职信息列表BO
     * @param bizPersonOid
     * @throws ServiceException
     */
    public static List<PtPositioningDis> listPtPositioningDisBoByBizPersonOid(Long bizPersonOid) throws ServiceException
	{
		String hql = "from PtPositioningDis fi where fi.bizPersonOid = :bizPersonOid ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		List<PtPositioningDis> boList = DaoUtil.find(hql, params);
		
		return boList;
	}
    
 public static PtPositioningDis getByPositioningDisOid(Long ptPositioningDisOid)throws ServiceException {
		return DaoUtil.get(PtPositioningDis.class, ptPositioningDisOid);
	}
}
