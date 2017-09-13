package com.yh.hr.component.validate.comm.check;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.dto.PtOwnAllowanceDTO;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.service.PtOwnAllowanceService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;

/** 
 * 特岗津贴信息校验
 * @author huanglm
 * @date 创建时间：2016-12-14
 * @version 1.0 
 * 
 */

public class PtOwnAllowanceCheckServiceImpl implements BaseValidateService{

	private PtOwnAllowanceService  ptOwnAllowanceService = (PtOwnAllowanceService) SpringBeanUtil.getBean("ptOwnAllowanceService");
	
	public void validate() throws ServiceException {
		// 1.获取托盘数据
		BaseHandleData data = BaseHandler.get();
		
		PtPerson ptPerson = PtPersonQueryHelper.getByTaskOid(NumberUtils.createLong(data.get("bizTaskOid")));
	    if(ptPerson==null)throw new ServiceException(null, "人员业务信息不存在！bizTaskOid="+data.get("bizTaskOid"));
	    
	    List<PtOwnAllowanceDTO> list = ptOwnAllowanceService.listPtOwnAllowance(ptPerson.getBizPersonOid());
	    
	    
	    if(CollectionUtils.isEmpty(list))
	    {
	    	throw new ServiceException(null, "特岗津贴信息不存在。");
	    }
	    for(@SuppressWarnings("unused") PtOwnAllowanceDTO dto:list){
	    		
	    	
	    }
	    
	}	
}
