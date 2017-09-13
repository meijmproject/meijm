package com.yh.hr.orghc.unit.common.service.transfer;

import java.util.Date;
import com.yh.hr.component.tansfer.service.impl.AbsItemTransferTemplateService;
import com.yh.hr.orghc.ub.bo.UbUnit;
import com.yh.hr.orghc.ub.service.UbUnitService;
import com.yh.hr.orghc.ut.bo.BizUtUnit;
import com.yh.hr.orghc.ut.queryhelper.BizUtUnitQueryHelper;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.sao.unit.SaoUserUnitAuthorizationService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * （单位撤销）业务库转基础库转库模板
 * @author zhengdr
 *
 * 时间:2016-12-23上午11:40:47
 */
public class TbUnitCancelItemTransferService extends AbsItemTransferTemplateService {
	//单位信息Service
	private UbUnitService ubUnitService = (UbUnitService)SpringBeanUtil.getBean("ubUnitService");
	private SaoUserUnitAuthorizationService saoUserUnitAuthorizationService = (SaoUserUnitAuthorizationService)SpringBeanUtil.getBean("saoUserUnitAuthorizationService");
	
	@Override
	protected void doBefore(Long taskOid) throws ServiceException {
		//得到业务库单位信息
		BizUtUnit bizUtUnit = BizUtUnitQueryHelper.getByTaskOid(taskOid);
		if(bizUtUnit==null){
			throw new ServiceException(null, "单位信息不存在!");
		}
		//得到原单位oid
		Long unitOid = bizUtUnit.getUnitOid();
		if(unitOid!=null){
			//得到单位信息
			UbUnit ubUnit = ubUnitService.get(unitOid);
			//修改单位状态---撤销
			ubUnit.setUnitStatus(DicConstants.YHRS0101_3);
			//撤销日期
			ubUnit.setCancelDate(new Date());
			//修改文号
			ubUnit.setFileNo(bizUtUnit.getFileNo());
			//修改备注
			ubUnit.setRemark(bizUtUnit.getRemark());
			
			//更新
			ubUnit.setUpdatedByCode(UserContext.getLoginUserID());
			ubUnit.setUpdatedByName(UserContext.getLoginUserName());
			ubUnit.setUpdatedDate(DateUtil.now());
			ubUnitService.update(ubUnit);
			
			//同步授权
			saoUserUnitAuthorizationService.updateAuthorityByUserId(taskOid);
		}
		
		//设置参数
		setSourceBizOid(bizUtUnit.getUtUnitOid());
		setTargetBizOid(unitOid);
	}

	@Override
	protected void doAfter(Long taskOid) throws ServiceException {
		
	}
	
}
