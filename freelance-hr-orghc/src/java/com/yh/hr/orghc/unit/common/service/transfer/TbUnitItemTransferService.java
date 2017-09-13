package com.yh.hr.orghc.unit.common.service.transfer;

import com.yh.hr.component.tansfer.service.impl.AbsItemTransferTemplateService;
import com.yh.hr.component.transfer.comm.tb.TbGtPostPercentTransferService;
import com.yh.hr.orghc.ub.dto.UbOrgDTO;
import com.yh.hr.orghc.ub.dto.UbUnitDTO;
import com.yh.hr.orghc.ub.service.UbOrgService;
import com.yh.hr.orghc.ub.service.UbUnitService;
import com.yh.hr.orghc.unit.util.UnitTaskItemFlowConstants;
import com.yh.hr.orghc.ut.bo.BizUtUnit;
import com.yh.hr.orghc.ut.queryhelper.BizUtUnitQueryHelper;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.sao.unit.SaoUserUnitAuthorizationService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * （单位新增）业务库转基础库转库模板
 * @author zhengdr
 *
 * 时间:2016-12-22上午11:39:06
 */
public class TbUnitItemTransferService extends AbsItemTransferTemplateService {
	//单位信息Service
	private UbUnitService ubUnitService = (UbUnitService)SpringBeanUtil.getBean("ubUnitService");
	//岗位信息转库
	private TbGtPostPercentTransferService tbGtPostPercentTransferService 
	      = (TbGtPostPercentTransferService)SpringBeanUtil.getBean("tbGtPostPercentTransferService");
	//内设机构信息
	private UbOrgService ubOrgService = (UbOrgService)SpringBeanUtil.getBean("ubOrgService");
	private SaoUserUnitAuthorizationService saoUserUnitAuthorizationService = (SaoUserUnitAuthorizationService)SpringBeanUtil.getBean("saoUserUnitAuthorizationService");
	
	@Override
	protected void doBefore(Long taskOid) throws ServiceException {
		//得到业务库单位信息
		BizUtUnit bizUtUnit = BizUtUnitQueryHelper.getByTaskOid(taskOid);
		if(bizUtUnit==null){
			throw new ServiceException(null, "单位信息不存在!");
		}
		//基础单位信息
		UbUnitDTO ubUnitDTO = new UbUnitDTO();
		//复制
		BeanHelper.copyProperties(bizUtUnit, ubUnitDTO);
		
		//返回新增的单位oid
		Long unitOid = ubUnitService.create(ubUnitDTO);
		
		//同步授权
		saoUserUnitAuthorizationService.updateAuthorityByUserId(taskOid);
		
		//更新UnitOid
		bizUtUnit.setUnitOid(unitOid);
		bizUtUnit.update();
		
		//判断单位性质是否为事业单位
		if(DicConstants.YHRS0090_104.equals(bizUtUnit.getUnitKind())){
			//岗位信息转库
			tbGtPostPercentTransferService.transfer(bizUtUnit.getUtUnitOid(), unitOid);
		}
		
		//内设机构信息
		UbOrgDTO ubOrgDTO = new UbOrgDTO();
		//单位领导内设机构
		ubOrgDTO.setUnitOid(unitOid);
		ubOrgDTO.setOrgName("单位领导");
		//内设机构类型
		ubOrgDTO.setOrgType(DicConstants.YHRS0102_1);
		//内设机构编码
		ubOrgDTO.setBranchTypeCode(bizUtUnit.getBranchTypeCode());
		//内设机构级别YHRS0093
		ubOrgDTO.setLevelCode(bizUtUnit.getLevelCode());
		//成立时间
		ubOrgDTO.setEstablishedDate(bizUtUnit.getEstablishedDate());
		//机构职能
		ubOrgDTO.setOrgFunction(bizUtUnit.getUnitFunction());
		//状态
		ubOrgDTO.setOrgStatus(DicConstants.YHRS0101_2);
		//排序号
		ubOrgDTO.setOrderOfView("001");
		//全局排序
		ubOrgDTO.setOrderOfAll("001");
		
		ubOrgService.createOrgInfo(ubOrgDTO);
		
		
		ubOrgDTO = new UbOrgDTO();
		//其他在职人员内设机构
		ubOrgDTO.setUnitOid(unitOid);
		ubOrgDTO.setOrgName("其他在职人员");
		//内设机构类型
		ubOrgDTO.setOrgType(DicConstants.YHRS0102_3);
		//内设机构编码
		ubOrgDTO.setBranchTypeCode(bizUtUnit.getBranchTypeCode());
		//内设机构级别YHRS0093--降半级
		ubOrgDTO.setLevelCode(UnitTaskItemFlowConstants.getLevelCodeMap().get(bizUtUnit.getLevelCode()));
		//成立时间
		ubOrgDTO.setEstablishedDate(bizUtUnit.getEstablishedDate());
		//机构职能
		ubOrgDTO.setOrgFunction(bizUtUnit.getUnitFunction());
		//状态
		ubOrgDTO.setOrgStatus(DicConstants.YHRS0101_2);
		//排序号
		ubOrgDTO.setOrderOfView("999");
		//全局排序
		ubOrgDTO.setOrderOfAll("999");
		
		ubOrgService.createOrgInfo(ubOrgDTO);
		
		
		
		//设置参数
		setSourceBizOid(bizUtUnit.getUtUnitOid());
		setTargetBizOid(unitOid);
	}

	@Override
	protected void doAfter(Long taskOid) throws ServiceException {
		
	}
	
}
