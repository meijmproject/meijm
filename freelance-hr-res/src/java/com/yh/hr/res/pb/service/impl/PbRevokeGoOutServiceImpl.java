package com.yh.hr.res.pb.service.impl;

import com.yh.hr.res.pb.bo.PbRevokeGoOut;
import com.yh.hr.res.pb.dto.PbRevokeGoOutDTO;
import jade.workflow.utils.DateUtil;

import java.util.Date;
import java.util.List;

import com.yh.hr.res.pb.queryhelper.PbRevokeGoOutQueryHelper;
import com.yh.hr.res.pb.service.PbRevokeGoOutService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.web.UserContext;
/**
 * 普通外出销假基础信息service实现类
 * @author wangx
 * @date 2017-05-20
 * @version 1.0
 */
public class PbRevokeGoOutServiceImpl implements PbRevokeGoOutService {

	/**
	 * 新增普通外出销假的基础信息
	 * @param pbRevokeGoOutDTO
	 * @throws ServiceException
	 * @return 
	 */
	public Long create(PbRevokeGoOutDTO pbRevokeGoOutDTO)
			throws ServiceException {
		PbRevokeGoOut pbRevokeGoOut = new PbRevokeGoOut();
		BeanHelper.copyProperties(pbRevokeGoOutDTO, pbRevokeGoOut);
		pbRevokeGoOut.setCreateBy(UserContext.getLoginUserID());
		pbRevokeGoOut.setCreateName(UserContext.getLoginUserName());
		pbRevokeGoOut.setCreateDate(DateUtil.now());
		pbRevokeGoOut.setRemark(pbRevokeGoOutDTO.getRemark());
		pbRevokeGoOut.setUpdateBy(UserContext.getLoginUserID());
		pbRevokeGoOut.setUpdateName(UserContext.getLoginUserName());
		pbRevokeGoOut.setUpdateDate(DateUtil.now());
		pbRevokeGoOut.save();
		return pbRevokeGoOut.getPbRevokeGoOutOid();
	}

	/**
	 * 修改普通外出销假的基础信息
	 * @param pbRevokeGoOutDTO
	 * @throws ServiceException
	 */
	public void update(PbRevokeGoOutDTO pbRevokeGoOutDTO)
			throws ServiceException {
		PbRevokeGoOut pbRevokeGoOut = DaoUtil.get(PbRevokeGoOut.class, pbRevokeGoOutDTO.getPbRevokeGoOutOid());
		if(pbRevokeGoOut!=null) {
			BeanHelper.copyProperties(pbRevokeGoOutDTO, pbRevokeGoOut, BeanHelper.getNullPropertyNames(pbRevokeGoOutDTO));
			pbRevokeGoOut.setUpdateBy(UserContext.getLoginUserID());
			pbRevokeGoOut.setUpdateName(UserContext.getLoginUserName());
			pbRevokeGoOut.setUpdateDate(DateUtil.now());
			pbRevokeGoOut.update();
		}

	}

	/**
	 * 删除普通外出销假的基础信息
	 * @param pbRevokeGoOutOid
	 * @throws ServiceException
	 */
	public void delete(Long pbRevokeGoOutOid) throws ServiceException {
		PbRevokeGoOut pbRevokeGoOut = DaoUtil.get(PbRevokeGoOut.class, pbRevokeGoOutOid);
		if(pbRevokeGoOut!=null) {
			pbRevokeGoOut.delete();
		}
	}

	/**
	 * 查询普通外出对应的普通外出销假信息
	 * @param pbGoOutOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PbRevokeGoOutDTO> getPbRevokeGoOutDTOListByPbGoOutOid(
			Long pbGoOutOid) throws ServiceException {
		return PbRevokeGoOutQueryHelper.getPbRevokeGoOutDTOListByPbGoOutOid(pbGoOutOid);
	}

	/**
	 * 根据主键获取普通外出销假信息
	 * @param pbRevokeGoOutOid
	 * @return
	 * @throws ServiceException
	 */
	public PbRevokeGoOutDTO get(Long pbRevokeGoOutOid) throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PbRevokeGoOut.class, pbRevokeGoOutOid), PbRevokeGoOutDTO.class);
	}

	public List<PbRevokeGoOutDTO> getPbRevokeGoOutDTOList(Long goOutOid,
			Date startDate, Date endDate) throws ServiceException {
		return PbRevokeGoOutQueryHelper.getPbRevokeGoOutDTOList(goOutOid,startDate,endDate);
	}

}
