package com.yh.hr.component.validate.comm.check;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.dto.PtDelayRetireInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.service.PtDelayRetireInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;

/** 
 * 延迟退休信息校验
 * 	1、原退休日期不能为空
 *  2、延迟退休日期不能为空
 * @author huanglm
 * @date 创建时间：2016-12-08
 * @version 1.0 
 * 
 */

public class PtDelayRetireCheckServiceImpl implements BaseValidateService{

	private PtDelayRetireInfoService  ptDelayRetireInfoService = (PtDelayRetireInfoService) SpringBeanUtil.getBean("ptDelayRetireInfoService");
	
	public void validate() throws ServiceException {
		// 1.获取托盘数据
		BaseHandleData data = BaseHandler.get();
		
		PtPerson ptPerson = PtPersonQueryHelper.getByTaskOid(NumberUtils.createLong(data.get("bizTaskOid")));
	    if(ptPerson==null)throw new ServiceException(null, "人员业务信息不存在！bizTaskOid="+data.get("bizTaskOid"));
	    
	    PtDelayRetireInfoDTO ptDelayRetireInfoDTO = ptDelayRetireInfoService.getPtDelayRetireInfoBybizPersonOid(ptPerson.getBizPersonOid());
	    
	    
	    if(null == ptDelayRetireInfoDTO)
	    {
	    	throw new ServiceException(null, "延迟退休信息不存在。");
	    }
	    if(null == ptDelayRetireInfoDTO.getOriginalRetrieDate())
	    {
	    	throw new ServiceException(null, "延迟退休信息中原退休日期为空。");
	    }
	    if(null ==ptDelayRetireInfoDTO.getDelayretireDate())
	    {
	    	throw new ServiceException(null, "延迟退休信息中延迟退休日期为空。");
	    }
	}	
}
