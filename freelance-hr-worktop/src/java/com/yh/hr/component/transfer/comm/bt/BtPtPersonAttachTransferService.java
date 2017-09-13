/**
 * 
 */
package com.yh.hr.component.transfer.comm.bt;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.bt.bo.BtTask;
import com.yh.hr.res.pb.bo.PbPersonAttach;
import com.yh.hr.res.pt.bo.PtPersonAttach;
import com.yh.hr.res.pt.dto.PtPersonDTO;
import com.yh.hr.res.pt.service.PtPersonService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;


/**
 * @desc  创建人员业务附属信息表
 * @author luqy
 * @createDate 2016-11-4上午10:05:18
 */
public class BtPtPersonAttachTransferService implements InfoTransferService {
	
	private PtPersonService ptPersonService = (PtPersonService)SpringBeanUtil.getBean("ptPersonService");
	

	
	public void transfer(Long taskOid, Long personOid) throws ServiceException {
		if(NumberUtils.isNotNullOrZero(taskOid)){
			BtTask btTask = DaoUtil.get(BtTask.class, taskOid);
			if(btTask != null){
				PtPersonDTO ptPersonDTO = ptPersonService.getByTaskOid(taskOid);
				if(ptPersonDTO != null && NumberUtils.isNotNullOrZero(personOid)){
					PbPersonAttach pbPersonAttach = DaoUtil.get(PbPersonAttach.class, personOid);
					if(null!=pbPersonAttach){
						PtPersonAttach ptPersonAttach = BeanHelper.copyProperties(pbPersonAttach, PtPersonAttach.class);
						ptPersonAttach.setBizPersonOid(ptPersonDTO.getBizPersonOid());
						ptPersonAttach.save();
					}
				}
			}
		}
	}

}
