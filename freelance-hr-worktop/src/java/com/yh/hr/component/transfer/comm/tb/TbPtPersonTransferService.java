/**
 * 
 */
package com.yh.hr.component.transfer.comm.tb;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.pb.bo.PbPersonInfo;
import com.yh.hr.res.pt.dto.PtPersonInDTO;
import com.yh.hr.res.pt.service.PtPersonInService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;


/**
 * @desc 业务库人员信息转基础库
 * @author luqy
 * @createDate 2016-11-7上午11:40:41
 */
public class TbPtPersonTransferService implements InfoTransferService {
	
	private PtPersonInService ptPersonInService = (PtPersonInService)SpringBeanUtil.getBean("ptPersonInService");
	
	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long, java.lang.Long)
	 */
	public void transfer(Long bizPersonOid, Long personOid) throws ServiceException {
		PtPersonInDTO ptPersonInDTO  = ptPersonInService.getPersonInDTOById(bizPersonOid);
		if(ptPersonInDTO != null){
			PbPersonInfo pbPersonInfo = DaoUtil.get(PbPersonInfo.class, personOid);
			if(pbPersonInfo != null){
				pbPersonInfo.setUnitOid(ptPersonInDTO.getPostUnitOid());
				pbPersonInfo.setDeptOid(ptPersonInDTO.getPostDeptOid());
//		gg		pbPersonInfo.setDeptName(ptPersonInDTO.getPostDeptName());
				pbPersonInfo.setUpdateBy(UserContext.getLoginUserID());
				pbPersonInfo.setUpdateName(UserContext.getLoginUserName());
				pbPersonInfo.setUpdateDate(DateUtil.now());
				pbPersonInfo.update();
			}
		}
	}

}
