package com.yh.hr.info.dataimport.person.service.transfer;

import jade.workflow.utils.SpringBeanUtil;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.im.dto.ImCheckPersonInfoDTO;
import com.yh.hr.res.im.service.ImCheckPersonInfoService;
import com.yh.hr.res.pb.dto.PbYnGwEmployInfoDTO;
import com.yh.hr.res.pb.service.PbYnGwEmployInfoService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.StringUtil;

/**
 * 人员导入院内岗位聘任信息入库service
 * @author wangx
 * @date 2017-07-14
 * @version 1.0
 */
public class ImportYngwEmployTransferService implements InfoTransferService {

	private ImCheckPersonInfoService imCheckPersonInfoService = (ImCheckPersonInfoService) SpringBeanUtil.getBean("imCheckPersonInfoService");
	private PbYnGwEmployInfoService pbYnGwEmployInfoService = (PbYnGwEmployInfoService) SpringBeanUtil.getBean("pbYnGwEmployInfoService");

	/**
	 * 入库
	 */
	@Override
	public void transfer(Long checkPersonInfoOid, Long personOid)
			throws ServiceException {
		ImCheckPersonInfoDTO ImCheckPersonInfoDTO = imCheckPersonInfoService.get(checkPersonInfoOid);
		PbYnGwEmployInfoDTO pbYnGwEmployInfoDTO = new PbYnGwEmployInfoDTO();
		String hisPositionType = ImCheckPersonInfoDTO.getHisPositionType();
		if(StringUtil.isNotBlank(hisPositionType)) {
			pbYnGwEmployInfoDTO.setPersonOid(personOid);
			pbYnGwEmployInfoDTO.setHisPositionType(hisPositionType);
			pbYnGwEmployInfoDTO.setHisPositionName(ImCheckPersonInfoDTO.getHisPositionName());
			pbYnGwEmployInfoDTO.setHisPositionStatus(DicConstants.YHRS0026_001);
			pbYnGwEmployInfoService.create(pbYnGwEmployInfoDTO);
		}
	}
}
