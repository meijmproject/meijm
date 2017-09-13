package com.yh.hr.component.validate.comm;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.service.PtFamilyInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;

/** 
 * 家庭及社会关系信息（已婚需采集）
 * @author wangj
 * @date 创建时间：2016-10-11 
 * @version 1.0 
 * 
 */
public class PtFamilyInfoValidateServiceImpl implements BaseValidateService {

	private PtFamilyInfoService ptFamilyInfoService = (PtFamilyInfoService) SpringBeanUtil.getBean("ptFamilyInfoService");
	
	@Override
	public void validate() throws ServiceException {
		
		// 1.获取托盘数据
		BaseHandleData data = BaseHandler.get();
		
		PtPerson ptPerson = PtPersonQueryHelper.getByTaskOid(NumberUtils.createLong(data.get("bizTaskOid")));
	    if(ptPerson==null)throw new ServiceException(null, "人员业务信息不存在！bizTaskOid="+data.get("bizTaskOid"));
		
		//2.查询是否存在家庭及社会关系信息(已婚需采集)；存在则验证通过，不存在抛出异常；
    	if(DicConstants.YHRS0008_20.equals(ptPerson.getMarriageStatusCode())){
    		int nums=ptFamilyInfoService.countPtFamilyInfoByPersonOid(ptPerson.getBizPersonOid());
    		if(nums==0){
    			throw new ServiceException(null, "婚姻状况为已婚，家庭及社会关系信息中需采集配偶信息。");
    		}
    	}
	    
	}
}
