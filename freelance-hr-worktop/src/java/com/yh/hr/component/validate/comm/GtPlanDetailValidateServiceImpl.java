package com.yh.hr.component.validate.comm;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.res.gt.dto.GtPlanDetailDTO;
import com.yh.hr.res.gt.queryhelper.GtPlanDetailQueryHelper;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;

/**
 * 岗位设置信息（必须采集）
 * @author huangyj
 * @version 1.0, 16/12/23
 */
public class GtPlanDetailValidateServiceImpl implements BaseValidateService {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see BaseValidateService#validate()
	 */
	public void validate() throws ServiceException {
		
		// 1.获取托盘数据
		BaseHandleData data = BaseHandler.get();
		
		List<GtPlanDetailDTO> gtPlanDetailDTOList = GtPlanDetailQueryHelper.listGtPlanDetailDTOByTaskOid(NumberUtils.createLong(data.get("bizTaskOid")));
		if(CollectionUtils.isEmpty(gtPlanDetailDTOList))
		{
			throw new ServiceException(null, "岗位设置信息为空");
		}
	}
}
