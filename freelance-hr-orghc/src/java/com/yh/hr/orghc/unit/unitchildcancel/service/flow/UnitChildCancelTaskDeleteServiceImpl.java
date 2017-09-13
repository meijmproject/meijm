package com.yh.hr.orghc.unit.unitchildcancel.service.flow;

import com.yh.hr.component.task.service.impl.TaskDeleteAbstractService;
import com.yh.hr.orghc.ut.bo.BizUtUnit;
import com.yh.hr.orghc.ut.queryhelper.BizHcInfoQueryHelper;
import com.yh.hr.orghc.ut.queryhelper.BizLeaderInfoQueryHelper;
import com.yh.hr.orghc.ut.queryhelper.BizOrgInfoQueryHelper;
import com.yh.hr.orghc.ut.queryhelper.BizUtUnitQueryHelper;
import com.yh.hr.res.gt.queryhelper.GtPostPercentQueryHelper;
import com.yh.platform.core.exception.ServiceException;

/**
 * 下级单位撤销---业务删除
 * @author zhengdr
 *
 * 时间:2016-12-23下午01:57:25
 */
public class UnitChildCancelTaskDeleteServiceImpl extends TaskDeleteAbstractService {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see TaskDeleteAbstractService#
	 * deleteSubTaskInfo(java.lang.Long)
	 */
	public void deleteSubTaskInfo(Long bizTaskOid) throws ServiceException {
		
		//得到单位信息
		BizUtUnit bizUtUnit = BizUtUnitQueryHelper.getByTaskOid(bizTaskOid);
		if(bizUtUnit!=null){
			
			Long utUnitOid = bizUtUnit.getUtUnitOid();
			//删除岗位信息
			GtPostPercentQueryHelper.deleteByUtUnitOid(utUnitOid);
			//删除编制信息
			BizHcInfoQueryHelper.deleteByUtUnitOid(utUnitOid);
			//删除 领导职数信息
			BizLeaderInfoQueryHelper.deleteByUtUnitOid(utUnitOid);
			//删除内设机构信息
			BizOrgInfoQueryHelper.deleteByUtUnitOid(utUnitOid);
			
			//删除单位信息
			bizUtUnit.delete();
		}
	}

}