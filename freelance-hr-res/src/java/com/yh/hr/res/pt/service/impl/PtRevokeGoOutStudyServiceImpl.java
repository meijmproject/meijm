package com.yh.hr.res.pt.service.impl;

import com.yh.hr.res.pt.bo.PtRevokeGoOutStudy;
import com.yh.hr.res.pt.queryhelper.PtRevokeGoOutStudyQueryHelper;
import jade.workflow.utils.DateUtil;

import java.util.List;

import com.yh.hr.res.pt.dto.PtRevokeGoOutStudyDTO;
import com.yh.hr.res.pt.service.PtRevokeGoOutStudyService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.web.UserContext;
/**
 * 外出进修销假业务信息service实现类
 * @author wangx
 * @date 2017-05-20
 * @version 1.0
 */
public class PtRevokeGoOutStudyServiceImpl implements PtRevokeGoOutStudyService {

	/**
	 * 新增外出进修销假的业务信息
	 * @param ptRevokeGoOutStudyDTO
	 * @throws ServiceException
	 * @return 
	 */
	public Long create(PtRevokeGoOutStudyDTO ptRevokeGoOutStudyDTO)
			throws ServiceException {
		PtRevokeGoOutStudy ptRevokeGoOutStudy = new PtRevokeGoOutStudy();
		BeanHelper.copyProperties(ptRevokeGoOutStudyDTO, ptRevokeGoOutStudy);
		ptRevokeGoOutStudy.setCreateBy(UserContext.getLoginUserID());
		ptRevokeGoOutStudy.setCreateName(UserContext.getLoginUserName());
		ptRevokeGoOutStudy.setCreateDate(DateUtil.now());
		ptRevokeGoOutStudy.save();
		return ptRevokeGoOutStudy.getPtRevokeGoOutStudyOid();
	}

	/**
	 * 修改外出进修销假的业务信息
	 * @param ptRevokeGoOutStudyDTO
	 * @throws ServiceException
	 */
	public void update(PtRevokeGoOutStudyDTO ptRevokeGoOutStudyDTO)
			throws ServiceException {
		PtRevokeGoOutStudy ptRevokeGoOutStudy = DaoUtil.get(PtRevokeGoOutStudy.class, ptRevokeGoOutStudyDTO.getPtRevokeGoOutStudyOid());
		if(ptRevokeGoOutStudy!=null) {
			BeanHelper.copyProperties(ptRevokeGoOutStudyDTO, ptRevokeGoOutStudy, BeanHelper.getNullPropertyNames(ptRevokeGoOutStudyDTO));
			ptRevokeGoOutStudy.setUpdateBy(UserContext.getLoginUserID());
			ptRevokeGoOutStudy.setUpdateName(UserContext.getLoginUserName());
			ptRevokeGoOutStudy.setUpdateDate(DateUtil.now());
			ptRevokeGoOutStudy.update();
		}

	}

	/**
	 * 删除外出进修销假的业务信息
	 * @param ptRevokeGoOutStudyOid
	 * @throws ServiceException
	 */
	public void delete(Long ptRevokeGoOutStudyOid) throws ServiceException {
		PtRevokeGoOutStudy ptRevokeGoOutStudy = DaoUtil.get(PtRevokeGoOutStudy.class, ptRevokeGoOutStudyOid);
		if(ptRevokeGoOutStudy!=null) {
			ptRevokeGoOutStudy.delete();
		}
	}

	/**
	 * 查询外出进修对应的外出进修销假信息
	 * @param ptGoOutStudyOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtRevokeGoOutStudyDTO> getPtRevokeGoOutStudyDTOListByPbGoOutStudyOid(
			Long ptGoOutStudyOid) throws ServiceException {
		return PtRevokeGoOutStudyQueryHelper.getPtRevokeGoOutStudyDTOListByPtGoOutStudyOid(ptGoOutStudyOid);
	}

	/**
	 * 根据主键获取外出进修销假信息
	 * @param ptRevokeGoOutStudyOid
	 * @return
	 * @throws ServiceException
	 */
	public PtRevokeGoOutStudyDTO get(Long ptRevokeGoOutStudyOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtRevokeGoOutStudy.class, ptRevokeGoOutStudyOid), PtRevokeGoOutStudyDTO.class);
	}

	/* (non-Javadoc)
	 * @see PtRevokeGoOutStudyService#getPtGoOutCancelDTOByBizPersonOid(java.lang.Long)
	 */
	public PtRevokeGoOutStudyDTO getPtGoOutCancelDTOByBizPersonOid(
			Long bizPersonOid) throws ServiceException {
		return PtRevokeGoOutStudyQueryHelper.getPtGoOutCancelDTOByBizPersonOid(bizPersonOid);
	}

}
