/**
 * 
 */
package com.yh.hr.component.transfer.comm.tb;
import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.pb.bo.PbPunishmentInfo;
import com.yh.hr.res.pb.dto.PbPunishmentInfoDTO;
import com.yh.hr.res.pb.service.PbPunishmentInfoService;
import com.yh.hr.res.pt.dto.PtDismissInfoDTO;
import com.yh.hr.res.pt.queryhelper.PtDismissInfoQueryHelper;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 处分信息转到基础库
 * @author	cheny
 * @version	1.0,	16/12/06
 */

public class TbPtPExpelunishmentInfoTransferService implements InfoTransferService {
	
	private PbPunishmentInfoService pbPunishmentInfoService = (PbPunishmentInfoService)SpringBeanUtil.getBean("pbPunishmentInfoService");
	

	/* (non-Javadoc)
	 * @see com.yh.hr.component.tansfer.service.impl.InfoTransferService#transfer(java.lang.Long, java.lang.Long)
	 */
	public void transfer(Long bizPersonOid, Long personOid) throws ServiceException {
			   PtDismissInfoDTO ptDismissInfoDTO =PtDismissInfoQueryHelper.findByBizPersonOid(bizPersonOid);
		       PbPunishmentInfo pbPunishmentInfo = new PbPunishmentInfo();
		       pbPunishmentInfo.setPersonOid(personOid);
//		       pbPunishmentInfo.setPunishmentCode(DicConstants.YHRS0081_19);
//		       pbPunishmentInfo.setPunishmentReasonCode(ptDismissInfoDTO.getDismissReason());
//		       pbPunishmentInfo.setPunishmentReasonType(ptDismissInfoDTO.getDismissReasonType());
		       pbPunishmentInfo.setPunishmentDate(ptDismissInfoDTO.getDismissEffectDate());
//		       pbPunishmentInfo.setPunishmentFileno(ptDismissInfoDTO.getDismissApproveFileno());
//		       pbPunishmentInfo.setPunishmentApprovalType(ptDismissInfoDTO.getDismissApproveUnitType());
		       pbPunishmentInfo.setPunishmentApprovalUnit(ptDismissInfoDTO.getDismissApproveUnitName());
			   pbPunishmentInfoService.create(BeanHelper.copyProperties(pbPunishmentInfo, PbPunishmentInfoDTO.class));
	}
}
