package com.yh.hr.component.validate.comm.check;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.dto.PtFamilyInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.service.PtFamilyInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;

/** 
 * 已婚人员检查，家庭及社会关系信息必须存在配偶信息
 * @author wangj
 * @date 创建时间：2016-10-11 
 * @version 1.0 
 * 
 */

public class PtFamilyInfoCheckServiceImpl implements BaseValidateService{

	private PtFamilyInfoService ptFamilyInfoService = (PtFamilyInfoService) SpringBeanUtil.getBean("ptFamilyInfoService");
	
	public void validate() throws ServiceException {
		// 1.获取托盘数据
		BaseHandleData data = BaseHandler.get();
		
		PtPerson ptPerson = PtPersonQueryHelper.getByTaskOid(NumberUtils.createLong(data.get("bizTaskOid")));
	    if(ptPerson==null)throw new ServiceException(null, "人员业务信息不存在！bizTaskOid="+data.get("bizTaskOid"));
	    
	    //检查已婚人员的配偶信息是否存在
	    if(DicConstants.YHRS0008_20.equals(ptPerson.getMarriageStatusCode())){
	    	List<PtFamilyInfoDTO> listPtFamilyInfoDTO= ptFamilyInfoService.listPtFamilyInfoByPersonOid(ptPerson.getBizPersonOid());
	    	if(CollectionUtils.isNotEmpty(listPtFamilyInfoDTO)){
	    		int count=0;
	    		for(PtFamilyInfoDTO ptFamilyInfoDTO : listPtFamilyInfoDTO ){
	    			if((DicConstants.YHRS0024_11).equals(ptFamilyInfoDTO.getRelationship())||(DicConstants.YHRS0024_12).equals(ptFamilyInfoDTO.getRelationship())){
	    				count++;
	    			}
		    	}
	    		
	    		if(count==0) throw new ServiceException(null, "已婚人员，家庭及社会关系信息必须存在配偶信息！");
	    	}
	    	
    	}
	}

}
