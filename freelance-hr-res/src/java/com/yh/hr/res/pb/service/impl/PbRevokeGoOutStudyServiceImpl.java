package com.yh.hr.res.pb.service.impl;

import com.yh.hr.res.pb.dto.PbRevokeGoOutStudyDTO;
import com.yh.hr.res.pb.queryhelper.PbRevokeGoOutStudyQueryHelper;
import jade.workflow.utils.DateUtil;

import java.util.Date;
import java.util.List;

import com.yh.hr.res.pb.bo.PbRevokeGoOutStudy;
import com.yh.hr.res.pb.service.PbRevokeGoOutStudyService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.web.UserContext;
/**
 * 外出进修销假基础信息service实现类
 * @author wangx
 * @date 2017-05-20
 * @version 1.0
 */
public class PbRevokeGoOutStudyServiceImpl implements PbRevokeGoOutStudyService {

	/**
	 * 新增外出进修销假的基础信息
	 * @param pbRevokeGoOutStudyDTO
	 * @throws ServiceException
	 * @return 
	 */
	public Long create(PbRevokeGoOutStudyDTO pbRevokeGoOutStudyDTO)
			throws ServiceException {
		PbRevokeGoOutStudy pbRevokeGoOutStudy = new PbRevokeGoOutStudy();
		BeanHelper.copyProperties(pbRevokeGoOutStudyDTO, pbRevokeGoOutStudy);
		pbRevokeGoOutStudy.setCreateBy(UserContext.getLoginUserID());
		pbRevokeGoOutStudy.setCreateName(UserContext.getLoginUserName());
		pbRevokeGoOutStudy.setCreateDate(DateUtil.now());
		pbRevokeGoOutStudy.setUpdateBy(UserContext.getLoginUserID());
		pbRevokeGoOutStudy.setUpdateName(UserContext.getLoginUserName());
		pbRevokeGoOutStudy.setUpdateDate(DateUtil.now());
		pbRevokeGoOutStudy.save();
		return pbRevokeGoOutStudy.getPbRevokeGoOutStudyOid();
	}

	/**
	 * 修改外出进修销假的基础信息
	 * @param pbRevokeGoOutStudyDTO
	 * @throws ServiceException
	 */
	public void update(PbRevokeGoOutStudyDTO pbRevokeGoOutStudyDTO)
			throws ServiceException {
		PbRevokeGoOutStudy pbRevokeGoOutStudy = DaoUtil.get(PbRevokeGoOutStudy.class, pbRevokeGoOutStudyDTO.getPbRevokeGoOutStudyOid());
		if(pbRevokeGoOutStudy!=null) {
			BeanHelper.copyProperties(pbRevokeGoOutStudyDTO, pbRevokeGoOutStudy, BeanHelper.getNullPropertyNames(pbRevokeGoOutStudyDTO));
			pbRevokeGoOutStudy.setUpdateBy(UserContext.getLoginUserID());
			pbRevokeGoOutStudy.setUpdateName(UserContext.getLoginUserName());
			pbRevokeGoOutStudy.setUpdateDate(DateUtil.now());
			pbRevokeGoOutStudy.update();
		}

	}

	/**
	 * 删除外出进修销假的基础信息
	 * @param pbRevokeGoOutStudyOid
	 * @throws ServiceException
	 */
	public void delete(Long pbRevokeGoOutStudyOid) throws ServiceException {
		PbRevokeGoOutStudy pbRevokeGoOutStudy = DaoUtil.get(PbRevokeGoOutStudy.class, pbRevokeGoOutStudyOid);
		if(pbRevokeGoOutStudy!=null) {
			pbRevokeGoOutStudy.delete();
		}
	}

	/**
	 * 查询外出进修对应的外出进修销假信息
	 * @param pbGoOutOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PbRevokeGoOutStudyDTO> getPbRevokeGoOutStudyDTOListByPbGoOutStudyOid(
			Long pbGoOutStudyOid) throws ServiceException {
		return PbRevokeGoOutStudyQueryHelper.getPbRevokeGoOutStudyDTOListByPbGoOutStudyOid(pbGoOutStudyOid);
	}

	/**
	 * 根据主键获取外出进修销假信息
	 * @param pbRevokeGoOutStudyOid
	 * @return
	 * @throws ServiceException
	 */
	public PbRevokeGoOutStudyDTO get(Long pbRevokeGoOutStudyOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PbRevokeGoOutStudy.class, pbRevokeGoOutStudyOid), PbRevokeGoOutStudyDTO.class);
	}

	public List<PbRevokeGoOutStudyDTO> getPbRevokeGoOutStudyDTOList(
			Long pbGoOutStudyOid, Date startDate, Date endDate)
			throws ServiceException {
		return PbRevokeGoOutStudyQueryHelper.getPbRevokeGoOutStudyDTOList(pbGoOutStudyOid,startDate,endDate);
	}

}
