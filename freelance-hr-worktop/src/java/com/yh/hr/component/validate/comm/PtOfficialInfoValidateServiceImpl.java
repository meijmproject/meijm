package com.yh.hr.component.validate.comm;

import com.yh.component.datahandler.data.BaseHandleData;
import com.yh.component.datahandler.handler.BaseHandler;
import com.yh.component.validate.BaseValidateService;
import com.yh.hr.res.pt.bo.PtPerson;
import com.yh.hr.res.pt.dto.PtOfficialInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.service.PtOfficialInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;

/** 
 * 公务员登记信息（必须采集）
 * @author  wujj
 * @date 创建时间：2016-11-07
 * @version 1.0 
 */
public class PtOfficialInfoValidateServiceImpl implements BaseValidateService{
	private PtOfficialInfoService ptOfficialInfoService = (PtOfficialInfoService)SpringBeanUtil.getBean("ptOfficialInfoService");
	
	@Override
	public void validate() throws ServiceException {
		// 1.获取托盘数据
		BaseHandleData data = BaseHandler.get();

		PtPerson ptPerson = PtPersonQueryHelper.getByTaskOid(NumberUtils.createLong(data.get("bizTaskOid")));
		if(ptPerson==null)throw new ServiceException(null, "人员业务信息不存在！bizTaskOid="+data.get("bizTaskOid"));
		
		//获取登记信息
		PtOfficialInfoDTO ptOfficialInfoDTO = ptOfficialInfoService.getPtOfficialInfoDTO(ptPerson.getBizPersonOid());

		if(ptOfficialInfoDTO==null || ptOfficialInfoDTO.getEnterMode()==null ||ptOfficialInfoDTO.getEnrolDate()==null )
		{
			throw new ServiceException(null, "公务员登记信息不完整！");
		}
		
	}

}
