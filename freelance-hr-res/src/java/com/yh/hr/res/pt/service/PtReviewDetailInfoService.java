package com.yh.hr.res.pt.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.pt.dto.PtReviewDetailInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * 年度考核信息Service
 * @author xiongyx
 */
public interface PtReviewDetailInfoService {

	/**
	 * 新增
	 */
	public void create(PtReviewDetailInfoDTO serdto)throws ServiceException;

	/**
	 * 查询
	 */	
	public PtReviewDetailInfoDTO get(Long reviewDetailInfoOid) throws ServiceException;

	/**
	 * 更新
	 */
	public void update(PtReviewDetailInfoDTO serdto) throws ServiceException;

	/**
	 * 删除
	 */	
	public void delete(Long ptReviewDetailOid) throws ServiceException;
	
	/**
	 * 得到列表
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public List<JSONObject> listPtReviewDetailInfo(TableTagBean ttb) throws ServiceException;
	
	/**
	 * 全部设为称职
	 * @param reviewBizInfoOid
	 * @throws ServiceException
	 */
	public void saveAllPersonYearCheckCompetent(Long reviewBizInfoOid)throws ServiceException;
 
	/**
	 * 根据不同条件查询不同数量
	 * @param reviewBizInfoOid
	 * @param reviewResultType
	 *         考核结论类别YHRS0070
	 * @param isPromote
	 *         是否因特殊原因不晋升标识YHRS0003
	 * @return
	 * @throws ServiceException
	 */
    public int countByCondition(Long reviewBizInfoOid,String reviewResultType,String isPromote,String dutyLevel)throws ServiceException;
}
