package com.yh.hr.res.gt.service.impl;

import java.util.List;

import com.yh.hr.res.gt.bo.GtDescription;
import com.yh.hr.res.gt.dto.GtDescriptionDTO;
import com.yh.hr.res.gt.queryhelper.GtDescriptionQueryHelper;
import com.yh.hr.res.gt.service.GtDescriptionService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 岗位说明service实现类（业务）
 * @author huangyj
 */
public class GtDescriptionServiceImpl implements GtDescriptionService {

	/* (non-Javadoc)
	 * @see GtDescriptionService#createGtDescription(GtDescriptionDTO)
	 */
	public void createGtDescription(GtDescriptionDTO gtDescriptionDTO) throws ServiceException
	{
		GtDescription gtDescription = BeanHelper.copyProperties(gtDescriptionDTO, GtDescription.class);
		gtDescription.setCreatedByCode(UserContext.getLoginUserID());
		gtDescription.setCreatedByName(UserContext.getLoginUserName());
		gtDescription.setCreatedDate(DateUtil.now());
		gtDescription.save();
	}
	
	/* (non-Javadoc)
	 * @see GtDescriptionService#findGtDescriptionById(java.lang.Long)
	 */
	public GtDescriptionDTO findGtDescriptionById(Long jhgGtDescriptionOid) throws ServiceException
	{
		GtDescriptionDTO gtDescriptionDTO = GtDescriptionQueryHelper.getGtDescriptionDTOById(jhgGtDescriptionOid);
		return gtDescriptionDTO;
	}
	
	/* (non-Javadoc)
	 * @see GtDescriptionService#updateGtDescription(GtDescriptionDTO)
	 */
	public void updateGtDescription(GtDescriptionDTO gtDescriptionDTO) throws ServiceException
	{
		GtDescription gtDescription = DaoUtil.get(GtDescription.class, gtDescriptionDTO.getJhgGtDescriptionOid());
		BeanHelper.copyProperties(gtDescriptionDTO, gtDescription);
		gtDescription.setUpdatedByCode(UserContext.getLoginUserID());
		gtDescription.setUpdatedByName(UserContext.getLoginUserName());
		gtDescription.setUpdatedDate(DateUtil.now());
		gtDescription.update();
	}
	
	/* (non-Javadoc)
	 * @see GtDescriptionService#deleteGtDescription(java.lang.Long)
	 */
	public void deleteGtDescription(Long jhgGtDescriptionOid) throws ServiceException
	{
		GtDescription gtDescription = DaoUtil.get(GtDescription.class, jhgGtDescriptionOid);
		gtDescription.delete();
	}

	/* (non-Javadoc)
	 * @see GtDescriptionService#findGtDescriptionByUnitOid(java.lang.Long)
	 */
	public GtDescription findGtDescriptionByUnitOid(Long unitOid)
			throws ServiceException {
		return DaoUtil.uniqueResult("from GtDescription gd where gd.unitOid=?", unitOid);
	}

	/* (non-Javadoc)
	 * @see GtDescriptionService#listGtDescriptionById(java.lang.Long)
	 */
	public List<GtDescriptionDTO> listGtDescriptionById(Long unitOid,Long taskOid)
			throws ServiceException {
		return GtDescriptionQueryHelper.listGtDescriptionDTOByUnitOid(unitOid,taskOid);
	}
}
