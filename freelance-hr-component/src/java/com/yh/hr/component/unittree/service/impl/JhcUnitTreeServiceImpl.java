package com.yh.hr.component.unittree.service.impl;

import java.util.List;

import com.yh.hr.component.unittree.service.JhcUnitTreeService;
import net.sf.json.JSONArray;

import com.yh.hr.res.sao.unit.SaoUserUnitAuthorizationService;
import com.yh.hr.res.unit.dto.UtUnitDTO;
import com.yh.hr.res.unit.service.UtUnitService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

public class JhcUnitTreeServiceImpl implements JhcUnitTreeService {
	private SaoUserUnitAuthorizationService saoUserUnitAuthorizationService =(SaoUserUnitAuthorizationService) SpringBeanUtil.getBean("saoUserUnitAuthorizationService");

	private UtUnitService utUnitService = (UtUnitService)SpringBeanUtil.getBean("utUnitService");
	
	public JSONArray findUnitList(String organizationOid) throws ServiceException {
		@SuppressWarnings("unused")
		List<String> authUnits = saoUserUnitAuthorizationService.findAuthorityList(UserContext.getLoginAgentUserID());
		
		/*if(CollectionUtils.isEmpty(authUnits)){
			return null;
		}*/
		//ttb.getCondition().put("authUnits", StringUtil.arrayToSql(authUnits));
		//默认进来，没有unitOid，找出所有有权限单位的主管单位
		/*if(StringUtil.isBlank(organizationOid)){
			List<UtUnitDTO> list = JhcUnitTreeQueryHelper.findUnitList(organizationOid);
			JSONArray jsonArr =  new JSONArray();
			if(CollectionUtils.isNotEmpty(list)){
				for(UtUnitDTO utUnitDTO : list){
					JSONObject obj = new JSONObject();
					obj.put("organizationOid", utUnitDTO.getOrganizationOid());
					obj.put("unitName",utUnitDTO.getUnitName());
					//obj.put("hasPower",ArrayUtils.contains(authUnits.toArray(), UtOrgDTO.getOrgOid().toString()));
					//obj.put("isLeaf",this.checkIsChild(UtOrgDTO.getOrgOid().toString()));
					jsonArr.element(obj);
				}
			}
			return jsonArr;
	    //根据unitoid查出下设单位，如果下设单位没有权限，但是下设单位的下设单位有权限，理应也要展示出来
		}else {
			List<UtOrgDTO> list = JhcUnitTreeQueryHelper.findUnit(organizationOid,relationFlag);
			JSONArray jsonArr =  new JSONArray();
			for(UtOrgDTO UtOrgDTO : list){
				if(CollectionUtils.isNotEmpty(list)){
					JSONObject obj = new JSONObject();
				//	if(ArrayUtils.contains(authUnits.toArray(), UtOrgDTO.getUnitOid().toString())){
						//如果单位名称不为空，主管单位下面的单位都要过滤掉，下设单位不包过单位名称条件，可能下面子单位包过，为了构造树 ，也要展示出来
							obj.put("orgOid", UtOrgDTO.getOrgOid());
							obj.put("orgName",UtOrgDTO.getOrgName());
							obj.put("organizationOid",UtOrgDTO.getOrganizationOid());
							obj.put("hasPower",true);
							obj.put("isLeaf",this.checkIsChild(UtOrgDTO.getOrgOid().toString()));
							jsonArr.element(obj);
					}else{
						if(hasPower(UtOrgDTO.getOrgOid().toString())){
							obj.put("orgOid", UtOrgDTO.getOrgOid());
							obj.put("orgName",UtOrgDTO.getOrgName());
							obj.put("hasPower",false);
							obj.put("isLeaf",this.checkIsChild(UtOrgDTO.getOrgOid().toString()));
							jsonArr.element(obj);
						}
					}
				}
			}
			return jsonArr;
		}
	}*/
   /* private boolean hasPower(String orgOid) throws ServiceException{
    	List<String> authUnits = saoUserUnitAuthorizationService.findAuthorityList(UserContext.getLoginAgentUserID());
    	List<UtOrgDTO> orglist = JhcUnitTreeQueryHelper.findUnit(orgOid.toString());
    	if(CollectionUtils.isNotEmpty(orglist)){
			for(UtOrgDTO u : orglist){
				if(ArrayUtils.contains(authUnits.toArray(), u.getOrgOid().toString())){
					return true;
				}else {
					return hasPower(u.getOrgOid().toString());
				}
			}
		}
		return false;
	}*/
		return null;
	}
	/*
	 * (non-Javadoc)
	 * @see JhcUnitTreeService#findUnitInfoByUnitOid(java.lang.Long)
	 */
	public UtUnitDTO findUnitInfoByUnitOid(Long unitOid)
			throws ServiceException 
	{
		return utUnitService.get(unitOid);
	}
	
}
