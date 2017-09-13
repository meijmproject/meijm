/**
 * 
 */
package com.yh.hr.component.transfer.comm.tb;

import java.util.Date;
import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.bt.bo.BtTask;
import com.yh.hr.res.bt.queryhelper.BtTaskQueryHelper;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pb.bo.PbPostInfo;
import com.yh.hr.res.pt.bo.PtNormalOut;
import com.yh.hr.res.pt.dto.PtDismissInfoDTO;
import com.yh.hr.res.pt.dto.PtPersonDTO;
import com.yh.hr.res.pt.dto.PtPersonOutDTO;
import com.yh.hr.res.pt.dto.PtPostInfoDisDTO;
import com.yh.hr.res.pt.queryhelper.PtDismissInfoQueryHelper;
import com.yh.hr.res.pt.queryhelper.PtNormalOutQueryHelper;
import com.yh.hr.res.pt.queryhelper.PtPersonOutQueryHelper;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.service.PtPostInfoDisService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 免职岗位信息转到基础库
 * @author	cheny
 * @version	1.0,	16/12/1
 */

public class TbPtOutPostDisTransferService implements InfoTransferService {
	
	private PtPostInfoDisService ptPostInfoDisService = (PtPostInfoDisService)SpringBeanUtil.getBean("ptPostInfoDisService");
	/*
	 * 免掉免职记录对应的基础任职记录
	 */
	public void transfer(Long bizPersonOid, Long personOid) throws ServiceException{
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
		List<PtPostInfoDisDTO> ptPostInfoDisDTOs = ptPostInfoDisService.listPtPostInfoDisByBizPersonId(bizPersonOid);
		if(CollectionUtils.isNotEmpty(ptPostInfoDisDTOs)){
			for (PtPostInfoDisDTO ptPostInfoDisDTO : ptPostInfoDisDTOs) {
				PbPostInfo pbPostInfo = DaoUtil.get(PbPostInfo.class, ptPostInfoDisDTO.getPostOid());
				if(pbPostInfo != null){
					/*事业  辞职/辞退*/
					if("01312010".equals(btTask.getItemCode())||"01312020".equals(btTask.getItemCode())){
						PtNormalOut ptNormalOut=PtNormalOutQueryHelper.listPtNormalOutByBizPersonOid(bizPersonOid);
						pbPostInfo.setEndDateActual(ptNormalOut.getEffectDate());
						pbPostInfo.setEndDate(ptNormalOut.getEffectDate());
						pbPostInfo.setPositionStatus(DicConstants.YHRS0026_002);
						/*事业  开除*/	
					}else if("01312030".equals(btTask.getItemCode())){
						PtDismissInfoDTO ptDismissInfoDTO=PtDismissInfoQueryHelper.findByBizPersonOid(bizPersonOid);
						pbPostInfo.setEndDateActual(ptDismissInfoDTO.getDismissEffectDate());
						pbPostInfo.setEndDate(ptDismissInfoDTO.getDismissEffectDate());
						pbPostInfo.setPositionStatus(DicConstants.YHRS0026_002);
						//调出市本级单位
					}else if("01312040".equals(btTask.getItemCode())){
						PtPersonOutDTO ptPersonOutDTO = PtPersonOutQueryHelper.findByBizPersonOid(bizPersonOid);
						pbPostInfo.setEndDateActual(ptPersonOutDTO.getOutDate());
						pbPostInfo.setEndDate(ptPersonOutDTO.getOutDate());
						pbPostInfo.setPositionStatus(DicConstants.YHRS0026_002);
					}
					pbPostInfo.setUpdatedByCode(UserContext.getLoginUserID());
					pbPostInfo.setUpdatedByName(UserContext.getLoginUserName());
					pbPostInfo.setUpdatedDate(new Date());
					pbPostInfo.update();
				}
			 }
		 }
	}
}
