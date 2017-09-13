package com.yh.hr.res.pb.service;

import java.util.List;

import com.yh.hr.res.pb.dto.PbProbationInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**
 * @desc 人员试用期情况业务逻辑层
 * @author luqy
 * @createDate 2016-8-15下午04:30:56
 */

public interface PbProbationInfoService {

/*	final String	USER_ID		= UserContext.getLoginUserID();
	final String	USER_NAME	= UserContext.getLoginUserName();
	final Date		NOW_TIME	= DateUtil.now();*/
	/**
	 * 新增试用信息
	 * @param pbRankInfoDTO
	 * @throws ServiceException
	 */
	public void createPbProbationInfo(PbProbationInfoDTO pbProbationInfoDTO) throws ServiceException;
	/**
	 * 更新试用信息
	 * @param pbRankInfoDTO
	 * @throws ServiceException
	 */
	public void updatePbProbationInfo(PbProbationInfoDTO pbProbationInfoDTO) throws ServiceException;
	/**
	 * 删除试用信息
	 * @param pbRankInfoDTO
	 * @throws ServiceException
	 */
	public void deletePbProbationInfoById(Long probationOid) throws ServiceException;
	/**
	 * 批量删除试用信息
	 * @param pbRankInfoDTO
	 * @throws ServiceException
	 */
	public void deletePbProbationInfoByIds(List<Long> probationOids) throws ServiceException;
	/**
	 * 得到试用信息
	 * @return
	 * @throws ServiceException
	 */
	public PbProbationInfoDTO getPbProbationInfoDTOById(Long probationOid) throws ServiceException;
	/**
	 * 得到试用信息列表
	 * @param personOid
	 * @return
	 * @throws ServiceException
	 */
	public List<PbProbationInfoDTO> listPbProbationInfoDTO(Long personOid) throws ServiceException;
}
