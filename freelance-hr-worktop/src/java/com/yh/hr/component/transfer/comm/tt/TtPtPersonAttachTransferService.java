/**
 * 
 */
package com.yh.hr.component.transfer.comm.tt;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.pt.bo.PtPersonAttach;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;



/**
 * @desc 创建业务库人员附属表信息
 * @author luqy
 * @createDate 2016-11-10上午11:51:43
 */
public class TtPtPersonAttachTransferService implements InfoTransferService {
	
	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long, java.lang.Long)
	 */
	public void transfer(Long refBizPersonOid, Long bizPersonOid) throws ServiceException {
		PtPersonAttach ptPersonAttach = DaoUtil.get(PtPersonAttach.class, refBizPersonOid);
		if(ptPersonAttach != null){
				PtPersonAttach newPtPersonAttach = BeanHelper.copyProperties(ptPersonAttach,PtPersonAttach.class);
				newPtPersonAttach.setBizPersonOid(bizPersonOid);
				newPtPersonAttach.save();
		}
	}

}
