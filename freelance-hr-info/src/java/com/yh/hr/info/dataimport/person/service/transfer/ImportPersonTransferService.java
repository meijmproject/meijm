package com.yh.hr.info.dataimport.person.service.transfer;

import jade.workflow.utils.SpringBeanUtil;

import com.yh.hr.component.tansfer.service.impl.AbsItemTransferTemplateService;
import com.yh.hr.res.im.dto.ImCheckPersonInfoDTO;
import com.yh.hr.res.im.service.ImCheckPersonInfoService;
import com.yh.hr.res.pb.dto.PbPersonInfoDTO;
import com.yh.hr.res.pb.service.PbPersonInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 校核人员转入基础库操作类
 * @author wangx
 * @date 2017-07-15
 * @version 1.0
 */
public class ImportPersonTransferService extends AbsItemTransferTemplateService {
	
	private ImCheckPersonInfoService imCheckPersonInfoService = (ImCheckPersonInfoService) SpringBeanUtil.getBean("imCheckPersonInfoService");
	private PbPersonInfoService pbPersonInfoService = (PbPersonInfoService) SpringBeanUtil.getBean("pbPersonInfoService");

	
	@Override
	protected void doBefore(Long checkPersonInfoOid) throws ServiceException {
		ImCheckPersonInfoDTO ImCheckPersonInfoDTO = imCheckPersonInfoService.get(checkPersonInfoOid);
		PbPersonInfoDTO pbPersonInfoDTO = new PbPersonInfoDTO();
		BeanHelper.copyProperties(ImCheckPersonInfoDTO, pbPersonInfoDTO);
		//导入人员基础表
		Long personOid = pbPersonInfoService.transferImportPersonInfo(pbPersonInfoDTO);
		
		setSourceBizOid(checkPersonInfoOid);
		setTargetBizOid(personOid);
	}

	@Override
	protected void doAfter(Long checkPersonInfoOid) throws ServiceException {
		// TODO Auto-generated method stub
		
	}
}
