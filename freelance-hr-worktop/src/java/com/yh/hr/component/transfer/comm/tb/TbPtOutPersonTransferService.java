/**
 * 
 */
package com.yh.hr.component.transfer.comm.tb;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.bt.bo.BtTask;
import com.yh.hr.res.bt.queryhelper.BtTaskQueryHelper;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pb.bo.PbPersonInfo;
import com.yh.hr.res.pt.dto.PtPersonDTO;
import com.yh.hr.res.pt.dto.PtPersonInDTO;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
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
public class TbPtOutPersonTransferService implements InfoTransferService {
	
	private PtPersonInService ptPersonInService = (PtPersonInService)SpringBeanUtil.getBean("ptPersonInService");
	
	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long, java.lang.Long)
	 */
	public void transfer(Long bizPersonOid, Long personOid) throws ServiceException {
		PtPersonInDTO ptPersonInDTO  = ptPersonInService.getPersonInDTOById(bizPersonOid);
		PtPersonDTO ptPersonDTO = PtPersonQueryHelper.getPtPersonDTOById(bizPersonOid);
		if(null == ptPersonDTO)
		{
			throw new ServiceException(null, "人员基础信息为空");
		}
		BtTask btTask = BtTaskQueryHelper.getBtTaskById(ptPersonDTO.getTaskOid());
		if(null == btTask)
		{
			throw new ServiceException(null, "人员业务信息为空");
		}
		if(ptPersonInDTO != null){
			PbPersonInfo pbPersonInfo = DaoUtil.get(PbPersonInfo.class, personOid);
			if(pbPersonInfo != null){
				if("01312050".equals(btTask.getItemCode()))
				{
					//自然减员
					pbPersonInfo.setPersonStatus(DicConstants.YHRS0009_209);
				}
				else if("01312020".equals(btTask.getItemCode()))
				{
					//辞职
					pbPersonInfo.setPersonStatus(DicConstants.YHRS0009_203);
				}
				else if("01312010".equals(btTask.getItemCode()))
				{
					//辞退
					pbPersonInfo.setPersonStatus(DicConstants.YHRS0009_204);
				}
				else if("01312030".equals(btTask.getItemCode()))
				{
					//开除
					pbPersonInfo.setPersonStatus(DicConstants.YHRS0009_205);
				}else if("01312040".equals(btTask.getItemCode())){
					//调出市本级单位
					pbPersonInfo.setPersonStatus(DicConstants.YHRS0009_400);
					
				}else if("01312090".equals(btTask.getItemCode())){
					//试用期人员取消聘用
					pbPersonInfo.setPersonStatus(DicConstants.YHRS0009_299);
				}
				pbPersonInfo.setUpdateBy(UserContext.getLoginUserID());
				pbPersonInfo.setUpdateName(UserContext.getLoginUserName());
				pbPersonInfo.setUpdateDate(DateUtil.now());
				pbPersonInfo.update();
			}
		}
	}

}
