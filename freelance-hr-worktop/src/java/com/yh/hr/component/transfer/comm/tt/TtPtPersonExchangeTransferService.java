package com.yh.hr.component.transfer.comm.tt;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.pt.bo.PtPersonExchange;
import com.yh.hr.res.pt.dto.PtPersonExchangeDTO;
import com.yh.hr.res.pt.queryhelper.PtPersonExchangeQueryHepler;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

/**
 * 业务库人员交流信息转到业务库
 * @date 2016-10-18 
 * @version 1.0 
 */
public class TtPtPersonExchangeTransferService implements InfoTransferService {

	@Override
	public void transfer(Long refBizPersonOid, Long bizPersonOid) throws ServiceException {
		PtPersonExchangeDTO ptPersonExchangeDTO = PtPersonExchangeQueryHepler.getPtPersonExchangeDTOById(refBizPersonOid);
		if(ptPersonExchangeDTO != null){
			PtPersonExchange ptPersonExchange = BeanHelper.copyProperties(ptPersonExchangeDTO, PtPersonExchange.class);
			ptPersonExchange.setBizPersonOid(bizPersonOid);
			ptPersonExchange.save();
		}
	}
}
