package com.yh.hr.orghc.ub.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yh.hr.component.hc.service.HcFlowApprovedService;
import com.yh.hr.orghc.ub.bo.UbHc;
import com.yh.hr.orghc.ub.dto.UbHcDTO;
import com.yh.hr.orghc.ub.queryhelper.UbHcQueryHelper;
import com.yh.hr.orghc.ub.service.UbHcService;
import com.yh.hr.orghc.ub.service.UbUnitService;
import com.yh.hr.res.hc.dto.HcFlowDTO;
import com.yh.hr.res.hc.util.HcFlowConstants;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * @author Administrator
 *
 */
public class UbHcServiceImpl implements UbHcService 
{
	public HcFlowApprovedService hcFlowApprovedService = (HcFlowApprovedService)SpringBeanUtil.getBean("hcFlowApprovedService");
	public UbUnitService ubUnitService = (UbUnitService) SpringBeanUtil.getBean("ubUnitService");
	
	/* (non-Javadoc)
	 * @see UbHcService#getUbHcDTOById(java.lang.Long)
	 */
	@Override
	public UbHcDTO getUbHcDTOById(Long hcOid) throws ServiceException {
		return UbHcQueryHelper.getUbHcDTOById(hcOid);
	}

	/* (non-Javadoc)
	 * @see UbHcService#listByUnitOid(java.lang.Long)
	 */
	@Override
	public List<UbHcDTO> listByUnitOid(Long unitOid) throws ServiceException {
		return UbHcQueryHelper.listByUnitOid(unitOid);
	}

	/* (non-Javadoc)
	 * @see UbHcService#createHcInfo(UbHcDTO)
	 */
	@Override
	public void createHcInfo(UbHcDTO ubHcDTO) throws ServiceException 
	{
		UbHc ubHc = new UbHc();
		BeanHelper.copyProperties(ubHcDTO, ubHc);
		ubHc.setCreateBy(UserContext.getLoginUserID());
		ubHc.setCreateName(UserContext.getLoginUserName());
		ubHc.setCreateDate(DateUtil.now());
		ubHc.save();
		//编制信息账户，头寸，流水创建/更新
		this.modifyUbHc(ubHc,ubHc.getCurCount());
	}

	/**
	 * 新增编制--单位信息校核
	 * @param ubOrgDTO
	 * @param chgCount
     * @throws ServiceException 
	 */
	@Override
	public void createHcInfo(UbHcDTO ubHcDTO, Long chgCount) throws ServiceException 
	{
		UbHc ubHc = new UbHc();
		BeanHelper.copyProperties(ubHcDTO, ubHc);
		ubHc.setCreateBy(UserContext.getLoginUserID());
		ubHc.setCreateName(UserContext.getLoginUserName());
		ubHc.setCreateDate(DateUtil.now());
		ubHc.save();
		//编制信息账户，头寸，流水创建/更新
		this.modifyUbHc(ubHc,ubHc.getCurCount());
	}

	/* (non-Javadoc)
	 * @see UbHcService#updateHcInfo(UbHcDTO)
	 */
	@Override
	public void updateHcInfo(UbHcDTO ubHcDTO) throws ServiceException 
	{
		UbHc ubHc = DaoUtil.get(UbHc.class,ubHcDTO.getHcOid());
		
		BeanHelper.copyProperties(ubHcDTO, ubHc);
		ubHc.setUpdateBy(UserContext.getLoginUserID());
		ubHc.setUpdateName(UserContext.getLoginUserName());
		ubHc.setUpdateDate(DateUtil.now());
		ubHc.update();
		//编制信息账户，头寸，流水创建/更新
		//this.modifyUbHc(ubHc,ubHcDTO.getCurCount());
	}
	
	
	/**
	 * 修改编制--单位信息校核
	 * @param ubOrgDTO
	 * @param chgCount
     * @throws ServiceException 
	 */
	@Override
	public void updateHcInfo(UbHcDTO ubHcDTO, Long chgCount) throws ServiceException 
	{
		UbHc ubHc = DaoUtil.get(UbHc.class,ubHcDTO.getHcOid());
		
		BeanHelper.copyProperties(ubHcDTO, ubHc);
		ubHc.setUpdateBy(UserContext.getLoginUserID());
		ubHc.setUpdateName(UserContext.getLoginUserName());
		ubHc.setUpdateDate(DateUtil.now());
		ubHc.update();
		//编制信息账户，头寸，流水创建/更新
		this.modifyUbHc(ubHc,chgCount);
	}

	/* (non-Javadoc)
	 * @see UbHcService#deleteHcInfo(java.lang.Long)
	 */
	@Override
	public void deleteHcInfo(Long hcOid) throws ServiceException 
	{
		UbHc ubHc = DaoUtil.get(UbHc.class,hcOid);
		
		ubHc.delete();
		//编制信息账户，头寸，流水创建/更新
		//this.modifyUbHc(ubHc,-ubHc.getCurCount());
	}
	
	/**
	 * 编制数下达
	 * @param ubHc
	 * @param chgCount 变动数
	 * @throws ServiceException
	 */
	public void modifyUbHc(UbHc ubHc,Long chgCount) throws ServiceException
	{
		List<HcFlowDTO> list = new ArrayList<HcFlowDTO>();
		if(ubHc!=null)
		{
			HcFlowDTO dto = new HcFlowDTO();
			dto.setAccountCode(ubHc.getUnitOid().toString());
			dto.setAccountName(ubUnitService.getUnitName(ubHc.getUnitOid()));
			dto.setAccountType(HcFlowConstants.ACCOUNT_TYPE_1);
			dto.setHcCode(ubHc.getHcCode());
			dto.setHcName(ubHc.getHcName());
			dto.setBudgetFromCode(ubHc.getBudgetFromCode());
			dto.setBudgetFromName(ubHc.getBudgetFromName());
			dto.setFlowCount(chgCount);//变动数
			dto.setFlowType(HcFlowConstants.RES_TYPE_1);
			dto.setFlowTypeName(HcFlowConstants.getResTypeNameMap().get(HcFlowConstants.RES_TYPE_1));
			dto.setCreatedByCode(UserContext.getLoginUserID());
			dto.setCreatedByName(UserContext.getLoginUserName());
			dto.setCreatedDate(DateUtil.now());
			dto.setUpdatedByCode(UserContext.getLoginUserID());
			dto.setUpdatedByName(UserContext.getLoginUserName());
			dto.setUpdatedDate(DateUtil.now());
			list.add(dto);
		}
//		hcFlowApprovedService.approved(list);
	}

	/**
	 * 根据编制OID统计编制核定数
	 * @param unitOid
	 * @param hcCode
	 * @return num
	 * @throws ServiceException
	 */
	public int countHcByHcCode(Long unitOid,String hcCode) throws ServiceException {
		return UbHcQueryHelper.countHcByHcCode(unitOid,hcCode);
	}
}
