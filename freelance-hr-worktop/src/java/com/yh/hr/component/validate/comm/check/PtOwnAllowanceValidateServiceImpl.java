package com.yh.hr.component.validate.comm.check;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.res.dictionary.WageConstants;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.dto.PtOwnAllowanceDTO;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.service.PtOwnAllowanceService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 特岗津贴(必需有一条操作过的信息)
 * @author huanglm
 * @version 1.0, 16/12/3
 */
public class PtOwnAllowanceValidateServiceImpl implements BaseValidateService {
	private PtOwnAllowanceService ptOwnAllowanceService = (PtOwnAllowanceService) SpringBeanUtil.getBean("ptOwnAllowanceService");
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see BaseValidateService#validate()
	 */
	public void validate() throws ServiceException {
		// 1.获取托盘数据
		BaseHandleData data = BaseHandler.get();
		
		PtPerson ptPerson = PtPersonQueryHelper.getByTaskOid(NumberUtils.createLong(data.get("bizTaskOid")));
	    if(ptPerson==null)throw new ServiceException(null, "人员业务信息不存在！bizTaskOid="+data.get("bizTaskOid"));
		
	      List<PtOwnAllowanceDTO>  list   =ptOwnAllowanceService.listPtOwnAllowance(ptPerson.getBizPersonOid());
	        List<PtOwnAllowanceDTO> dtoChangeList = new ArrayList<PtOwnAllowanceDTO>();
	        List<PtOwnAllowanceDTO> dtoList = new ArrayList<PtOwnAllowanceDTO>();
	      if (CollectionUtils.isNotEmpty(list))
		{
	    	  for(PtOwnAllowanceDTO dto : list)
				{		
					//操作类型为新增
					if(WageConstants.HANDLE_CODE_02.equals(dto.getHandleCode())){
						if(dto.getAllowanceCategoryCode()!=null
								&&dto.getAllowanceName()!=null
								&&dto.getStartDate()!=null
								&&dto.getAllowanceAmount()!=null){
								dtoChangeList.add(dto);
								}
						    dtoList.add(dto);
				    }
					//操作类型为变更
					if(WageConstants.HANDLE_CODE_03.equals(dto.getHandleCode())){
						if(dto.getAllowanceCategoryCode()!=null
								&&dto.getAllowanceName()!=null
								&&dto.getStartDate()!=null
								&&dto.getAllowanceAmount()!=null){
								dtoChangeList.add(dto);
								}
						  dtoList.add(dto);
				    }
					//操作类型为撤消
					if(WageConstants.HANDLE_CODE_04.equals(dto.getHandleCode())){
						if(dto.getEndDate()!=null&&dto.getRemark()!=null){
							dtoChangeList.add(dto);
						}
						dtoList.add(dto);
				    }
				}
	    	  if(CollectionUtils.isEmpty(dtoList)){
	    		  throw new ServiceException(null, "没有新增或操作过特岗津贴信息。");
	    	  }else{
	    		  
	    		  	if(CollectionUtils.isEmpty(dtoChangeList)){
	    		  		throw new ServiceException(null, "新增或操作过的特岗津贴信息中存在不完整信息。");
	    		  }
	    	  }

		}else{
			throw new ServiceException(null, "特岗津贴信息为空。");
		}
	 
	}	
}
