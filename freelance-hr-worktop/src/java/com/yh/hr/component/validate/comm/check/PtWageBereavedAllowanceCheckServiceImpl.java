package com.yh.hr.component.validate.comm.check;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.dto.PtWageBereavedAllowanceDTO;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.service.PtWageBereavedAllowanceService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;

/** 
 * 遗属生活困难补助信息校验
 * 
 * 姓名
 * 性别
 * 与死者关系
 * 出生年月
 * 户籍类型
 * 居住地点
 * 补助开始时间
 * 每月补助金额
 * @author huanglm
 * @date 创建时间：2016-12-02
 * @version 1.0 
 * 
 */

public class PtWageBereavedAllowanceCheckServiceImpl implements BaseValidateService{

	private PtWageBereavedAllowanceService ptWageBereavedAllowanceService = (PtWageBereavedAllowanceService) SpringBeanUtil.getBean("ptWageBereavedAllowanceService");
	
	public void validate() throws ServiceException {
		// 1.获取托盘数据
		BaseHandleData data = BaseHandler.get();
		
		PtPerson ptPerson = PtPersonQueryHelper.getByTaskOid(NumberUtils.createLong(data.get("bizTaskOid")));
	    if(ptPerson==null)throw new ServiceException(null, "人员业务信息不存在！bizTaskOid="+data.get("bizTaskOid"));
	    
	    List<PtWageBereavedAllowanceDTO> ptWageBereavedAllowanceDTOList = ptWageBereavedAllowanceService.getPtBereavedAllowanceDTOById(ptPerson.getBizPersonOid());
	    
	    if(CollectionUtils.isEmpty(ptWageBereavedAllowanceDTOList))
	    {
	    	throw new ServiceException(null, "遗属与生活困难补助信息不存在。");
	    }
	    for(PtWageBereavedAllowanceDTO  dto : ptWageBereavedAllowanceDTOList)
	    {
	    	if(null == dto.getBereavedName())
		    {
		    	throw new ServiceException(null, "遗属与生活困难补助信息中存在姓名为空信息。");
		    }
		    if(null == dto.getBereavedSex())
		    {
		    	throw new ServiceException(null, "遗属与生活困难补助信息中存在性别为空信息。");
		    }
		    if(null == dto.getAddress())
		    {
		    	throw new ServiceException(null, "遗属与生活困难补助信息中存在居住地点为空信息。");
		    }
		    if(null == dto.getRelationshipBetweenDead())
		    {
		    	throw new ServiceException(null, "遗属与生活困难补助信息中存在与死者关系为空信息。");
		    }
		    if(null == dto.getBirthday())
		    {
		    	throw new ServiceException(null, "遗属与生活困难补助信息中存在出生年月为空信息。");
		    }
		    if(null == dto.getHouseholdType())
		    {
		    	throw new ServiceException(null, "遗属与生活困难补助信息中存在户籍类型为空信息。");
		    }
		    if(null == dto.getAllowanceStartDate())
		    {
		    	throw new ServiceException(null, "遗属与生活困难补助信息中存在补助开始时间为空信息。");
		    }
		    if(null == dto.getMonthlyAllowanceAmount())
		    {
		    	throw new ServiceException(null, "遗属与生活困难补助信息中存在每月补助金额为空信息。");
		    }
	    }
	    
	}

}
