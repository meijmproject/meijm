package com.yh.hr.component.orgtree.facade;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.component.taglib.TableTagBean;
import com.yh.hr.component.orgtree.service.JhcOrgTreeService;
import com.yh.hr.res.sao.unit.SaoUserUnitAuthorizationService;
import com.yh.hr.res.unit.dto.UtOrgDTO;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.TreeNode;

public class JhcOrgTreeFacade{
	private JhcOrgTreeService jhcOrgTreeService;
	public SaoUserUnitAuthorizationService saoUserUnitAuthorizationService;// 查询用户单位权限服务接口
	public void setJhcOrgTreeService(JhcOrgTreeService jhcOrgTreeService) {
		this.jhcOrgTreeService = jhcOrgTreeService;
	}
	public void setSaoUserUnitAuthorizationService(
			SaoUserUnitAuthorizationService saoUserUnitAuthorizationService) {
		this.saoUserUnitAuthorizationService = saoUserUnitAuthorizationService;
	}

	/**
	 * 获取内设机构信息
	 * @param ttb
	 * @return
	 * @throws ServiceException
	 */
	public List<UtOrgDTO> listOrg(TableTagBean ttb) throws ServiceException {
		List<UtOrgDTO> orgList=null;
		try{
			orgList = jhcOrgTreeService.listOrg(ttb);	
		}catch(ServiceException ex){
			throw new ServiceException(null,ex.getMessage()); 
		}	
		return orgList;
	}
	
	/**
	 * 根据内设机构Oid查询内设机构信息
	 * @param orgOid
	 * @return
	 * @throws ServiceException
	 */
	public UtOrgDTO findOrgByOid(Long orgOid) throws ServiceException
	{
		return jhcOrgTreeService.findOrgByOid(orgOid);
	}

	/**
	 * 根据单位ID查询所有内设机构信息
	 * @param unitOid
	 * @return
	 * @throws ServiceException
	 */
	public List<UtOrgDTO> getAllOrgInfoByUnitOid(Long unitOid) throws ServiceException
	{
		return jhcOrgTreeService.getAllOrgInfoByUnitOid(unitOid);
	}
	
	/**
	 * 根据单位Oid和内设机构Oid查询内设机构信息
	 * @param unitOid
	 * @param orgOidStr
	 * @return
	 * @throws ServiceException
	 */
	public List<UtOrgDTO> findOrgInfoByCon(String unitOid, String orgOidStr,String powerControl) throws ServiceException
	{
		return jhcOrgTreeService.findOrgInfoByCon(unitOid,orgOidStr,powerControl);
	}

	/**
	 * 根据单位OID和内设机构oid查询上级机构信息
	 * @param unitOid
	 * @param orgOid
	 * @return
	 */
	public UtOrgDTO findOrgInfoUp(String unitOid, String orgOid) throws ServiceException
	{
		return jhcOrgTreeService.findOrgInfoUp(unitOid,orgOid);
	}
	/**
	 * 查找内设机构树
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public List<TreeNode<UtOrgDTO>> findOrgList(String controlDataAuthority) throws ServiceException {
		TreeNode<UtOrgDTO> root = new TreeNode<UtOrgDTO>();
		//List<String> authUnits = saoUserUnitAuthorizationService.findAuthorityList(UserContext.getLoginAgentUserID());
		root.setChildren(cycleGetChildItem(root,controlDataAuthority));

		return root.getChildren();
	}

	private List<TreeNode<UtOrgDTO>> cycleGetChildItem(TreeNode<UtOrgDTO> root,String controlDataAuthority) throws ServiceException {
		
		List<UtOrgDTO> childUtUnit = jhcOrgTreeService.findOrgList(true,null);//加载出单位
		List<UtOrgDTO> childUtOrg = jhcOrgTreeService.findOrgList(false,controlDataAuthority);//加载出所有有权限的科室(末级权限，也会把上级菜单带出来)

		List<TreeNode<UtOrgDTO>> list = new ArrayList<TreeNode<UtOrgDTO>>();

		TreeNode<UtOrgDTO> node = new TreeNode<UtOrgDTO>();;
		if (CollectionUtils.isNotEmpty(childUtUnit)) {
			for (UtOrgDTO UtOrgDTO : childUtUnit) {
				node.setEntry(UtOrgDTO);
				node.setId(UtOrgDTO.getOrganizationOid());
				node.setAttribute("checked", UtOrgDTO.getIsleaf());
				node.setChildren(cycleGetChildOrgItem(childUtOrg,""));
				list.add(node);
			}
		}
		return list;
	}
	
	private List<TreeNode<UtOrgDTO>> cycleGetChildOrgItem(List<UtOrgDTO> childUtOrg,String hierarchyCode) throws ServiceException {

		List<TreeNode<UtOrgDTO>> list = new ArrayList<TreeNode<UtOrgDTO>>();

		TreeNode<UtOrgDTO> node = null;
		
		if (CollectionUtils.isNotEmpty(childUtOrg)) {
			for (UtOrgDTO utOrgDTO : childUtOrg) {
				if(hierarchyCode.length()<=0){
					String childHierarchyCode = utOrgDTO.getHierarchyCode();
					if(childHierarchyCode.length()==3){//首先加载一级节点
						node = new TreeNode<UtOrgDTO>();
						node.setEntry(utOrgDTO);
						node.setId(utOrgDTO.getOrganizationOid());
						node.setAttribute("checked", utOrgDTO.getIsleaf());
						node.setChildren(cycleGetChildOrgItem(childUtOrg,utOrgDTO.getHierarchyCode()));
						list.add(node);
					}
				}else{
					String childHierarchyCode = utOrgDTO.getHierarchyCode();
					int codeLength = hierarchyCode.length();
					if(childHierarchyCode.indexOf(hierarchyCode)!=-1&&childHierarchyCode.length()-hierarchyCode.length()==3&&childHierarchyCode.substring(0,codeLength).equals(hierarchyCode)){
						node = new TreeNode<UtOrgDTO>();
						node.setEntry(utOrgDTO);
						node.setId(utOrgDTO.getOrganizationOid());
						node.setAttribute("checked", utOrgDTO.getIsleaf());
						node.setChildren(cycleGetChildOrgItem(childUtOrg,utOrgDTO.getHierarchyCode()));
						list.add(node);
					}
				}
			}
		}
		return list;
	}
}
