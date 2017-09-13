package com.yh.hr.component.orgtree.service;

import java.util.List;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.res.unit.dto.UtOrgDTO;
import com.yh.platform.core.exception.ServiceException;
/**
 * 内设机构服务接口
 * @author liuhw
 * 2017-2-22
 */
public interface JhcOrgTreeService 
{
	/**
	 * 获取内设机构信息
	 * @param ttb
	 * @return 
     * @throws ServiceException 
	 */
	public List<UtOrgDTO> listOrg(TableTagBean ttb) throws ServiceException;
	
	/**
	 * 根据内设机构oid查询内设机构信息
	 * @param orgOid
	 * @return
	 * @throws ServiceException
	 */
	public UtOrgDTO findOrgByOid(Long orgOid)throws ServiceException;

	/**
	 * 根据单位查询内设机构信息
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public List<UtOrgDTO> getAllOrgInfoByUnitOid(Long unitOid)throws ServiceException;

	/**
	 * 根据单位Oid和内设机构Oid查询内设机构信息
	 * @param unitOid
	 * @param orgOidStr
	 * @return
	 * @throws ServiceException
	 */
	public List<UtOrgDTO> findOrgInfoByCon(String unitOid, String orgOidStr,String powerControl)throws ServiceException;

	/**
	 * 根据单位OID和内设机构oid查询上级机构信息
	 * @param unitOid
	 * @param orgOid
	 * @return
	 * @throws ServiceException
	 */
	public UtOrgDTO findOrgInfoUp(String unitOid, String orgOid)throws ServiceException;
	/**
	 * 根据单位查询内设机构信息
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public List<UtOrgDTO> findOrgList(boolean checkUnit,String controlDataAuthority) throws ServiceException;
	/**
	 * 是否为子节点
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public boolean checkIsChildByorganizationOid(Long organizationOid) throws ServiceException;
}
