package com.yh.hr.component.validate.comm.check;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.dto.PtRetrieInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.service.PtRetrieInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;

/** 
 * 离退休信息校验
 * 	1、离退类别不能为空
 *  2、离退日期不能为空
 * @author went
 * @date 创建时间：2016-11-09
 * @version 1.0 
 * 
 */

public class PtRetireCheckServiceImpl implements BaseValidateService{

	private PtRetrieInfoService  ptRetrieInfoService = (PtRetrieInfoService) SpringBeanUtil.getBean("ptRetrieInfoService");
	
	public void validate() throws ServiceException {
		// 1.获取托盘数据
		BaseHandleData data = BaseHandler.get();
		
		PtPerson ptPerson = PtPersonQueryHelper.getByTaskOid(NumberUtils.createLong(data.get("bizTaskOid")));
	    if(ptPerson==null)throw new ServiceException(null, "人员业务信息不存在！bizTaskOid="+data.get("bizTaskOid"));
	    
	    PtRetrieInfoDTO ptRetrieInfoDTO = ptRetrieInfoService.getPtRetrieInfoByOid(ptPerson.getBizPersonOid());
	    
	    
	    if(null == ptRetrieInfoDTO)
	    {
	    	throw new ServiceException(null, "离退休基础信息不存在。");
	    }
	    if(null == ptRetrieInfoDTO.getRetrieTypeCode())
	    {
	    	throw new ServiceException(null, "离退休基础信息中离退类别为空。");
	    }
	    if(null ==ptRetrieInfoDTO.getRetrieDate())
	    {
	    	throw new ServiceException(null, "离退休基础信息中离退日期为空。");
	    }
	}	
}
