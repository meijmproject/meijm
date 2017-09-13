package com.yh.hr.res.pb.service;

import java.util.List;

import com.yh.hr.res.pb.dto.PbProfTechInfoDTO;
import com.yh.platform.core.exception.ServiceException;

/**专业技术资格信息校核业务逻辑层
 * 
 * @author chenjl
 * @created 2016-08-15
 * @version 1.0
 */
public interface PbProfTechInfoService {
	
    /** 新增专业技术资格信息
     * 
     * @param pbProfTechInfoDTO
     * @throws ServiceException
     */
    public void addPbProfTechInfoDTO(PbProfTechInfoDTO pbProfTechInfoDTO) throws ServiceException;
	
	/**修改专业技术资格信息
	 * 
	 * @param pbProfTechInfoDTO
	 * @throws ServiceException
	 */
	public void modifyPbProfTechInfoDTO(PbProfTechInfoDTO pbProfTechInfoDTO) throws ServiceException;
	
	/**根据id获取专业技术资格信息
	 * 
	 * @param profTechOid
	 * @return
	 * @throws ServiceException
	 */
	public PbProfTechInfoDTO getPbProfTechInfoDTO(Long profTechOid) throws ServiceException;
	
	/**列出所有专业技术资格信息
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<PbProfTechInfoDTO> listPbProfTechInfoDTO(Long personOid) throws ServiceException;
	
	/**删除专业技术资格信息
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public void deletePbProfTechInfoDTO(Long profTechOid) throws ServiceException;
	
}
