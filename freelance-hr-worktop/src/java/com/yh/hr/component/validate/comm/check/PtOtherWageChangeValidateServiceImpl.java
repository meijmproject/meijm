package com.yh.hr.component.validate.comm.check;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.service.PtOtherWageChangeInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;

/** 
 * 其他工资变动信息校验（必填）
 * @author wuxq
 * @date 创建时间：2016-12-10 
 * @version 1.0 
 * 
 */

public class PtOtherWageChangeValidateServiceImpl implements BaseValidateService{

	private PtOtherWageChangeInfoService ptOtherWageChangeInfoService = (PtOtherWageChangeInfoService) SpringBeanUtil.getBean("ptOtherWageChangeInfoService");
	
	public void validate() throws ServiceException {
				// 1.获取托盘数据
				BaseHandleData data = BaseHandler.get();
				
				PtPerson ttPerson = PtPersonQueryHelper.getByTaskOid(NumberUtils.createLong(data.get("bizTaskOid")));
				if(ttPerson == null) {
					throw new ServiceException(null, "人员业务信息不存在！bizTaskOid="+data.get("bizTaskOid"));
				}
				// 2.查询是否存在其他工资变动信息记录；存在则验证通过，不存在抛出异常；
				int count = ptOtherWageChangeInfoService.countPtOtherWageChangeInfoByBizPersonOid(ttPerson.getBizPersonOid());
				if (count == 0)
					throw new ServiceException(null, "其他工资变动信息不存在。");
			}
}
