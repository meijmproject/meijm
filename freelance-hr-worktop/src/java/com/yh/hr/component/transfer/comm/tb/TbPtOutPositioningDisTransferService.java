/**
 * 
 */
package com.yh.hr.component.transfer.comm.tb;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.bt.bo.BtTask;
import com.yh.hr.res.bt.queryhelper.BtTaskQueryHelper;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pb.bo.PbPositioningInfo;
import com.yh.hr.res.pt.bo.PtNormalOut;
import com.yh.hr.res.pt.dto.PtDismissInfoDTO;
import com.yh.hr.res.pt.dto.PtPersonDTO;
import com.yh.hr.res.pt.dto.PtPersonOutDTO;
import com.yh.hr.res.pt.dto.PtPositioningDisDTO;
import com.yh.hr.res.pt.queryhelper.PtDismissInfoQueryHelper;
import com.yh.hr.res.pt.queryhelper.PtNormalOutQueryHelper;
import com.yh.hr.res.pt.queryhelper.PtPersonOutQueryHelper;
import com.yh.hr.res.pt.queryhelper.PtPersonQueryHelper;
import com.yh.hr.res.pt.service.PtPositioningDisService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * 免职信息转到基础库
 * @author	lenghh
 * @version	1.0,	16/11/05
 */

public class TbPtOutPositioningDisTransferService implements InfoTransferService {
	
	private  PtPositioningDisService ptPositioningDisService = (PtPositioningDisService)SpringBeanUtil.getBean("ptPositioningDisService");
	
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
		List<PtPositioningDisDTO> ptPositioningDisDTOs = ptPositioningDisService.listPtPositioningDisByBizPersonOid(bizPersonOid);
		if(CollectionUtils.isNotEmpty(ptPositioningDisDTOs)){
			for (PtPositioningDisDTO ptPositioningDisDTO : ptPositioningDisDTOs) {
                   /*辞职/辞退*/
				if("01312010".equals(btTask.getItemCode())||"01312020".equals(btTask.getItemCode())){
					PbPositioningInfo pbPositioningInfo = DaoUtil.get(PbPositioningInfo.class, ptPositioningDisDTO.getPbPositioningOid());
					PtNormalOut ptNormalOut=PtNormalOutQueryHelper.listPtNormalOutByBizPersonOid(bizPersonOid);
					pbPositioningInfo.setPositioningStatus(DicConstants.YHRS0026_002);
					pbPositioningInfo.setDisposalFileno(ptNormalOut.getFileNo());
					pbPositioningInfo.setDisposalReason("");
					pbPositioningInfo.setDisposalReasonDesc(ptNormalOut.getOutReason());
					pbPositioningInfo.setEndDateActual(ptNormalOut.getEffectDate());
					pbPositioningInfo.setEndDate(ptNormalOut.getEffectDate());
					pbPositioningInfo.update();
					/*事业  开除*/	
				}else if("01312030".equals(btTask.getItemCode())){
					PtDismissInfoDTO ptDismissInfoDTO=PtDismissInfoQueryHelper.findByBizPersonOid(bizPersonOid);
					PbPositioningInfo pbPositioningInfo = DaoUtil.get(PbPositioningInfo.class, ptPositioningDisDTO.getPbPositioningOid());
					pbPositioningInfo.setPositioningStatus(DicConstants.YHRS0026_002);
					pbPositioningInfo.setEndDateActual(ptDismissInfoDTO.getDismissEffectDate());
					pbPositioningInfo.setEndDate(ptDismissInfoDTO.getDismissEffectDate());
					pbPositioningInfo.update();
				  }
				//调出市本级单位
				else if("01312040".equals(btTask.getItemCode())){
					PbPositioningInfo pbPositioningInfo = DaoUtil.get(PbPositioningInfo.class, ptPositioningDisDTO.getPbPositioningOid());
					PtPersonOutDTO ptPersonOutDTO = PtPersonOutQueryHelper.findByBizPersonOid(bizPersonOid);
					pbPositioningInfo.setEndDateActual(ptPersonOutDTO.getOutDate());
					pbPositioningInfo.setEndDate(ptPersonOutDTO.getOutDate());
					pbPositioningInfo.setPositioningStatus(DicConstants.YHRS0026_002);
					pbPositioningInfo.update();
				  }
				}
			 }
		 }
	}
