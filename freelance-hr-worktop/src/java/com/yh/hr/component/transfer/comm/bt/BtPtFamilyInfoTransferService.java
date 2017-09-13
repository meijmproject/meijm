/**
 * 
 */
package com.yh.hr.component.transfer.comm.bt;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pb.dto.PbFamilyInfoDTO;
import com.yh.hr.res.pb.queryhelper.PbFamilyInfoQueryHelper;
import com.yh.hr.res.pt.bo.PtFamilyInfo;
import com.yh.hr.res.pt.dto.PtPersonDTO;
import com.yh.hr.res.pt.service.PtPersonService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;


/**
 * @desc 家族成员基础库转业务 库
 * @author luqy
 * @createDate 2016-11-8上午08:41:35
 */
public class BtPtFamilyInfoTransferService implements InfoTransferService {
	private PtPersonService ptPersonService = (PtPersonService)SpringBeanUtil.getBean("ptPersonService");
	
	
	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long)
	 */
	public void transfer(Long taskOid, Long personOid) throws ServiceException {
		//获取基础库学历信息
		 List<PbFamilyInfoDTO> list = PbFamilyInfoQueryHelper.listPbFamilyInfoDTOByPersonId(personOid);
		 if (CollectionUtils.isNotEmpty(list)) {
			PtPersonDTO ptPersonDTO = ptPersonService.getByTaskOid(taskOid);
			if(ptPersonDTO != null){
				PtFamilyInfo  ptFamilyInfo = null;
				for (PbFamilyInfoDTO dto : list) {
					ptFamilyInfo = new PtFamilyInfo();
					BeanHelper.copyProperties(dto, ptFamilyInfo);
					ptFamilyInfo.setBizPersonOid(ptPersonDTO.getBizPersonOid());
					ptFamilyInfo.setCreateBy(UserContext.getLoginUserID());
					ptFamilyInfo.setCreateName(UserContext.getLoginUserName());
					ptFamilyInfo.setCreateDate(DateUtil.now());
					ptFamilyInfo.save();
				}
			}
		}
	}

}
