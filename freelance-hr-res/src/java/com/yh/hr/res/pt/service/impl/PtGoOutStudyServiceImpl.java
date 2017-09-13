package com.yh.hr.res.pt.service.impl;

import com.yh.hr.res.pt.queryhelper.PtGoOutStudyQueryHelper;
import com.yh.hr.res.pt.service.PtGoOutStudyService;
import jade.workflow.utils.DateUtil;

import com.yh.hr.res.pt.bo.PtGoOutStudy;
import com.yh.hr.res.pt.dto.PtGoOutStudyDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.web.UserContext;

public class PtGoOutStudyServiceImpl implements PtGoOutStudyService {

	/**
	 * 新增外出进修的业务信息
	 * @param ptGoOutStudyDTO
	 * @throws ServiceException
	 */
	public Long create(PtGoOutStudyDTO ptGoOutStudyDTO) throws ServiceException {
		PtGoOutStudy ptGoOutStudy = new PtGoOutStudy();
		BeanHelper.copyProperties(ptGoOutStudyDTO, ptGoOutStudy);
		ptGoOutStudy.setCreateBy(UserContext.getLoginUserID());
		ptGoOutStudy.setCreateName(UserContext.getLoginUserName());
		ptGoOutStudy.setCreateDate(DateUtil.now());
		ptGoOutStudy.save();
		return ptGoOutStudy.getPtGoOutStudyOid();
	}

	/**
	 * 修改外出进修的业务信息
	 * @param ptGoOutStudyDTO
	 * @throws ServiceException
	 */
	public void update(PtGoOutStudyDTO ptGoOutStudyDTO) throws ServiceException {
		PtGoOutStudy ptGoOutStudy = DaoUtil.get(PtGoOutStudy.class, ptGoOutStudyDTO.getPtGoOutStudyOid());
		if(ptGoOutStudy!=null) {
			BeanHelper.copyProperties(ptGoOutStudyDTO, ptGoOutStudy, BeanHelper.getNullPropertyNames(ptGoOutStudyDTO));
			ptGoOutStudy.setUpdateBy(UserContext.getLoginUserID());
			ptGoOutStudy.setUpdateName(UserContext.getLoginUserName());
			ptGoOutStudy.setUpdateDate(DateUtil.now());
			ptGoOutStudy.update();
		}
	}

	/**
	 * 删除外出进修的业务信息
	 * @param ptGoOutStudyOid
	 * @throws ServiceException
	 */
	public void delete(Long ptGoOutStudyOid) throws ServiceException {
		PtGoOutStudy ptGoOutStudy = DaoUtil.get(PtGoOutStudy.class, ptGoOutStudyOid);
		if(ptGoOutStudy!=null) {
			ptGoOutStudy.delete();
		}
	}

	/**
	 * 查询人员对应的外出进修业务信息
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public PtGoOutStudyDTO getPtGoOutStudyDTOByBizPersonOid(
			Long bizPersonOid) throws ServiceException {
		return PtGoOutStudyQueryHelper.getPtGoOutStudyDTOByPersonOid(bizPersonOid);
	}

	/**
	 * 根据主键获取外出进修业务信息
	 * @param ptGoOutStudyOid
	 * @return
	 * @throws ServiceException
	 */
	public PtGoOutStudyDTO get(Long ptGoOutStudyOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtGoOutStudy.class, ptGoOutStudyOid), PtGoOutStudyDTO.class);
	}

}
