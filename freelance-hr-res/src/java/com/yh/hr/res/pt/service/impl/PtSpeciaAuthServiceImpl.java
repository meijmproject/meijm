package com.yh.hr.res.pt.service.impl;

import com.yh.hr.res.pt.bo.PtSpeciaAuth;
import com.yh.hr.res.pt.dto.PtSpeciaAuthDTO;
import com.yh.hr.res.pt.queryhelper.PtSpeciaAuthQueryHelper;
import com.yh.hr.res.pt.service.PtSpeciaAuthService;
import jade.workflow.utils.DateUtil;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.web.UserContext;
/**
 * 特殊授权情况业务信息service实现类
 * @author wangx
 * @date 2017-05-26
 * @version 1.0
 */
public class PtSpeciaAuthServiceImpl implements PtSpeciaAuthService {

	/**
	 * 创建特殊授权情况业务信息
	 * @param ptEducationTrainingInfoDto
	 * @return 
	 * @throws ServiceException
	 */
	public Long create(PtSpeciaAuthDTO ptSpeciaAuthDto) throws ServiceException {
		PtSpeciaAuth ptSpeciaAuth = new PtSpeciaAuth();
		BeanHelper.copyProperties(ptSpeciaAuthDto, ptSpeciaAuth);
		ptSpeciaAuth.setCreateBy(UserContext.getLoginUserID());
		ptSpeciaAuth.setCreateName(UserContext.getLoginUserName());
		ptSpeciaAuth.setCreateDate(DateUtil.now());
		ptSpeciaAuth.setUpdateBy(UserContext.getLoginUserID());
		ptSpeciaAuth.setUpdateName(UserContext.getLoginUserName());
		ptSpeciaAuth.setUpdateDate(DateUtil.now());
		ptSpeciaAuth.save();
		return ptSpeciaAuth.getSpeciaAuthOid();
	}

	/**
	 * 通过主键ID获取特殊授权情况业务信息
	 * @param ptSpeciaAuthId
	 * @return 
	 * @throws ServiceException
	 */
	public PtSpeciaAuthDTO get(Long ptSpeciaAuthId) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtSpeciaAuth.class, ptSpeciaAuthId), PtSpeciaAuthDTO.class);
	}

	/**
	 * 修改特殊授权情况业务信息
	 * @param ptEducationTrainingInfoDto
	 * @throws ServiceException
	 */ 
	public void update(PtSpeciaAuthDTO ptSpeciaAuthDto) throws ServiceException {
		PtSpeciaAuth ptSpeciaAuth = DaoUtil.get(PtSpeciaAuth.class, ptSpeciaAuthDto.getSpeciaAuthOid());
		if(ptSpeciaAuth!=null) {
			BeanHelper.copyProperties(ptSpeciaAuthDto, ptSpeciaAuth, BeanHelper.getNullPropertyNames(ptSpeciaAuthDto));
			ptSpeciaAuth.setUpdateBy(UserContext.getLoginUserID());
			ptSpeciaAuth.setUpdateName(UserContext.getLoginUserName());
			ptSpeciaAuth.setUpdateDate(DateUtil.now());
			ptSpeciaAuth.update();
		}
	}

	/**
	 * 删除特殊授权情况业务信息
	 * @param ptSpeciaAuthId
	 * @throws ServiceException
	 */ 
	public void delete(Long ptSpeciaAuthId) throws ServiceException {
		PtSpeciaAuth ptSpeciaAuth = DaoUtil.get(PtSpeciaAuth.class, ptSpeciaAuthId);
		if(ptSpeciaAuth!=null) {
			ptSpeciaAuth.delete();
		}
	}

	/**
	 * 查询所有特殊授权情况信息
	 * @param ttb
	 * @return 
	 * @throws ServiceException
	 */
	public List<PtSpeciaAuthDTO> list(TableTagBean ttb) throws ServiceException {
		return PtSpeciaAuthQueryHelper.list(ttb);
	}

	/**
	 * 根据业务人员OID查询特殊授权情况经历
	 * @param bizPersonOid
	 * @return List<PtSpeciaAuthDTO>
	 * @throws ServiceException
	 */
	public List<PtSpeciaAuthDTO> listPtSpeciaAuthByBizPersonOid(
			Long bizPersonOid) throws ServiceException {
		return PtSpeciaAuthQueryHelper.listPtSpeciaAuthByBizPersonOid(bizPersonOid);
	}

	/**
	 * 通过基础OID查找该人员的特殊授权情况业务信息
	 * @param baseSpeciaAuthOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PtSpeciaAuthDTO> listPtSpeciaAuthByBaseSpeciaAuthOid(
			Long baseSpeciaAuthOid) throws ServiceException {
		return PtSpeciaAuthQueryHelper.listPtSpeciaAuthByBaseSpeciaAuthOid(baseSpeciaAuthOid);
	}

	/**
	 * 通过业务人员OID删除该人员的所有特殊授权情况信息
	 * @param bizPersonOid
	 * @throws ServiceException
	 */
	public void deleteByBizPersonOid(Long bizPersonOid) throws ServiceException {
		PtSpeciaAuthQueryHelper.deleteByBizPersonOid(bizPersonOid);
	}

}
