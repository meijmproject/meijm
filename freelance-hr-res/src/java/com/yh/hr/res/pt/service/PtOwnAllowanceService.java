package com.yh.hr.res.pt.service;

import java.util.List;

import com.yh.hr.res.pt.dto.PtOwnAllowanceDTO;
import com.yh.platform.core.exception.ServiceException;


/** * @author  huanglm
* @date 创建时间：2016-12-12 
* @Package com.yh.hr.res.pb.service 
* @version 1.0
* @parameter   
*/
public interface PtOwnAllowanceService {

	/**
	 * 人员享受特岗津贴信息列表
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */	
	public List<PtOwnAllowanceDTO> listPtOwnAllowance(Long bizPersonOid) throws ServiceException;
	
	/**
	 * 新增人员享受特岗津贴信息
	 * @return
	 * @throws ServiceException
	 */	
	public void addPtOwnAllowance(PtOwnAllowanceDTO dto) throws ServiceException;

	/**
	 * 根据ownAllowanceOid获取人员享受特岗津贴信息
	 * @return
	 * @throws ServiceException
	 */	
	public PtOwnAllowanceDTO getPtOwnAllowance(Long ownAllowanceOid) throws ServiceException;
	
	/**
	 * 修改人员享受特岗津贴信息
	 * @return
	 * @throws ServiceException
	 */	
	public void updatePtOwnAllowance(PtOwnAllowanceDTO dto) throws ServiceException;

	/**
	 * 删除人员享受特岗津贴信息
	 * @return
	 * @throws ServiceException
	 */	
	public void deletePtOwnAllowance(Long ownAllowanceOid) throws ServiceException;
	
	/**
	 * 删除人员享受特岗津贴信息通过人员ID
	 * @return
	 * @throws ServiceException
	 */	
	public void deletePtOwnAllowanceById(Long bizPersonOid) throws ServiceException;
	
	/**
	 * 通过操作类型获取
	 * @param handleCode
	 * @return
	 * @throws ServiceException
	 */
	public  List<PtOwnAllowanceDTO> listPtOwnAllowanceByHandleCode(java.lang.String handleCode) throws ServiceException;
	/**
	 * 通过对应识别码获取
	 * @param handleCode
	 * @return
	 * @throws ServiceException
	 */
	public  List<PtOwnAllowanceDTO> listPtOwnAllowanceByHandleMatchNo(java.lang.Long handleMatchNo) throws ServiceException;


	
} 