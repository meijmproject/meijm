/**
 * 
 */
package com.yh.hr.component.transfer.comm.tb;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pb.bo.PbPersonInfo;
import com.yh.hr.res.pt.dto.PtPersonDTO;
import com.yh.hr.res.pt.service.PtPersonService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;


/**
 * @desc 工资业务库人员信息转基础库（离退休用，修改人员状态）
 * @author huanglm
 * @createDate 2016-11-7上午11:40:41
 */
public class TbPtWagePersonTransferService implements InfoTransferService {
	
	private PtPersonService ptPersonService = (PtPersonService)SpringBeanUtil.getBean("ptPersonService");
	
	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long, java.lang.Long)
	 */
	public void transfer(Long bizPersonOid, Long personOid) throws ServiceException {
		PtPersonDTO ptPersonDTO  = ptPersonService.getPtPersonDTO(bizPersonOid);
		if(ptPersonDTO != null){
			PbPersonInfo pbPersonInfo= DaoUtil.get(PbPersonInfo.class, personOid);
			if(pbPersonInfo != null){
				pbPersonInfo.setPersonStatus(DicConstants.YHRS0009_207);
				pbPersonInfo.setUpdateBy(UserContext.getLoginUserID());
				pbPersonInfo.setUpdateName(UserContext.getLoginUserName());
				pbPersonInfo.setUpdateDate(DateUtil.now());
				pbPersonInfo.update();
			}
		}
	}

}
