package com.yh.hr.component.validate.comm.check;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.dto.PtPositioningHistoryDTO;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.service.PtPositioningHistoryService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;

/** 
 * 任职聘任信息只可采集不在任信息
 * @author wangjie
 * @date 创建时间：2016-10-11 
 * @version 1.0 
 * 
 */
public class PtPositioningCheckServiceImpl implements BaseValidateService {

	private PtPositioningHistoryService ptPositioningHistoryService = (PtPositioningHistoryService) SpringBeanUtil.getBean("ptPositioningHistoryService");
	
	
	@Override
	public void validate() throws ServiceException {
		// 1.获取托盘数据
		BaseHandleData data = BaseHandler.get();

		PtPerson ptPerson = PtPersonQueryHelper.getByTaskOid(NumberUtils.createLong(data.get("bizTaskOid")));
	    if(ptPerson==null)throw new ServiceException(null, "人员业务信息不存在！bizTaskOid="+data.get("bizTaskOid"));
	    
	  //2.任职聘任信息只可采集不在任信息 positioningStatus
	    List<PtPositioningHistoryDTO>  list= ptPositioningHistoryService.listPtPositioningByBizPersonId(ptPerson.getBizPersonOid());
	    if(CollectionUtils.isNotEmpty(list)){
	    	 for(PtPositioningHistoryDTO dto : list){
	 	    	if(DicConstants.YHRS0026_001.equals(dto.getPositioningStatus())){
	 	    		throw new ServiceException(null, "任职聘任信息只可采集不在任信息");
	 	  	   }
	 	    }
	    }
	   
	}


}
