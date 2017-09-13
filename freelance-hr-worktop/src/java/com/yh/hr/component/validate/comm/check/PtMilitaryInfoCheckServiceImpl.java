package com.yh.hr.component.validate.comm.check;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.service.PtMilitaryInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;

/** 
 * 军转安置干部检查，必须采集军队任职历史
 * @author wangjie
 * @date 创建时间：2016-10-11 
 * @version 1.0 
 * 
 */
public class PtMilitaryInfoCheckServiceImpl implements BaseValidateService {

	private PtMilitaryInfoService ptMilitaryInfoService = (PtMilitaryInfoService) SpringBeanUtil.getBean("ptMilitaryInfoService");
	
	
	@Override
	public void validate() throws ServiceException {
		// 1.获取托盘数据
		BaseHandleData data = BaseHandler.get();

		PtPerson ptPerson = PtPersonQueryHelper.getByTaskOid(NumberUtils.createLong(data.get("bizTaskOid")));
	    if(ptPerson==null)throw new ServiceException(null, "人员业务信息不存在！bizTaskOid="+data.get("bizTaskOid"));
	    
	 // 2.查询是否存在军队任职历史（军转安置干部须采集）；存在则验证通过，不存在抛出异常；
		if (DicConstants.YHRS0003_1.equals(ptPerson.getIsAllocation())) {
			int count = ptMilitaryInfoService.countPtMilitaryInfoByBizPersonOid(ptPerson.getBizPersonOid());
			if (count == 0)
				throw new ServiceException(null, "军转安置干部，必须采集军队任职历史！");
		}
	    
	    
	}


}
