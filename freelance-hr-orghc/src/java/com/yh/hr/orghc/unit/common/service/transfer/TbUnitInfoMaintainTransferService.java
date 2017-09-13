package com.yh.hr.orghc.unit.common.service.transfer;

import com.yh.hr.component.tansfer.service.impl.AbsItemTransferTemplateService;
import com.yh.hr.component.transfer.comm.tb.TbGtPostPercentTransferService;
import com.yh.hr.orghc.ub.dto.UbUnitDTO;
import com.yh.hr.orghc.ub.service.UbUnitService;
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
public class TbUnitInfoMaintainTransferService extends AbsItemTransferTemplateService {
	//单位信息Service
	private UbUnitService ubUnitService = (UbUnitService)SpringBeanUtil.getBean("ubUnitService");
	//岗位信息转库
	private TbGtPostPercentTransferService tbGtPostPercentTransferService 
	      = (TbGtPostPercentTransferService)SpringBeanUtil.getBean("tbGtPostPercentTransferService");
	private SaoUserUnitAuthorizationService saoUserUnitAuthorizationService = (SaoUserUnitAuthorizationService)SpringBeanUtil.getBean("saoUserUnitAuthorizationService");
	
	@Override
	protected void doBefore(Long taskOid) throws ServiceException {
		//得到业务库单位信息
		BizUtUnit bizUtUnit = BizUtUnitQueryHelper.getByTaskOid(taskOid);
		if(bizUtUnit==null){
			throw new ServiceException(null, "单位信息不存在!");
		}
		//基础单位信息
		UbUnitDTO ubUnitDTO = ubUnitService.getUbUnitDTOById(bizUtUnit.getUnitOid());
		//复制
		BeanHelper.copyProperties(bizUtUnit, ubUnitDTO);
		
		ubUnitService.update(ubUnitDTO);
		
		//同步授权
		saoUserUnitAuthorizationService.updateAuthorityByUserId(taskOid);
		
		//判断单位性质是否为事业单位
		if(DicConstants.YHRS0090_104.equals(bizUtUnit.getUnitKind())){
			//岗位信息转库
			tbGtPostPercentTransferService.transfer(bizUtUnit.getUtUnitOid(), bizUtUnit.getUnitOid());
		}
		
		//设置参数
		setSourceBizOid(bizUtUnit.getUtUnitOid());
		setTargetBizOid(bizUtUnit.getUnitOid());
	}

	@Override
	protected void doAfter(Long taskOid) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	
}
