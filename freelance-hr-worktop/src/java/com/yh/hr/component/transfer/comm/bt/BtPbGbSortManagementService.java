
package com.yh.hr.component.transfer.comm.bt;
import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.gb.dto.GbSortManagementDTO;
import com.yh.hr.res.gb.dto.GbSortManagementDetailDTO;
import com.yh.hr.res.gb.service.GbSortManagementDetailService;
import com.yh.hr.res.gb.service.GbSortManagementService;
import com.yh.hr.res.gt.bo.GtSortManagement;
import com.yh.hr.res.gt.dto.GtSortManagementDetailDTO;
import com.yh.hr.res.gt.service.GtSortManagementDetailService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.NumberUtils;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 基础库事业单位岗位分类管理情况转到业务库
 * @author wangjie
 * @date 2016-12-26 
 * @version 1.0 
 */
public class BtPbGbSortManagementService implements InfoTransferService {
	private GbSortManagementService gbSortManagementService =(GbSortManagementService)SpringBeanUtil.getBean("gbSortManagementService");
	private GbSortManagementDetailService gbSortManagementDetailService =(GbSortManagementDetailService)SpringBeanUtil.getBean("gbSortManagementDetailService");
	private GtSortManagementDetailService gtSortManagementDetailService =(GtSortManagementDetailService)SpringBeanUtil.getBean("gtSortManagementDetailService");
	
	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long, java.lang.Long)
	 */
	@Override
	public void transfer(Long taskOid, Long utUnitOid)
			throws ServiceException {
		String uid = UserContext.getLoginUserID();
		String uname = UserContext.getLoginUserName();
		if(NumberUtils.isNotNullOrZero(utUnitOid)){
		GbSortManagementDTO gbSortManagementDTO=  gbSortManagementService.findGbSortManagementById(utUnitOid);
		if(null!=gbSortManagementDTO){
			GtSortManagement gtSortManagement=BeanHelper.copyProperties(gbSortManagementDTO, GtSortManagement.class);
			gtSortManagement.setUpdatedByCode(uid);
			gtSortManagement.setUpdatedByName(uname);
			gtSortManagement.setUpdatedDate(DateUtil.now());
			gtSortManagement.setTaskOid(taskOid);
			gtSortManagement.save();
			
			if(NumberUtils.isNotNullOrZero(gbSortManagementDTO.getGbSortManagementOid())){
				List<GbSortManagementDetailDTO> list= gbSortManagementDetailService.listGbSortManagementDetailByManageId(gbSortManagementDTO.getGbSortManagementOid());
				if(CollectionUtils.isNotEmpty(list)){
					for(GbSortManagementDetailDTO gbSortManagementDetailDTO: list){
						GtSortManagementDetailDTO gtSortManagementDetailDTO=BeanHelper.copyProperties(gbSortManagementDetailDTO, GtSortManagementDetailDTO.class);
						gtSortManagementDetailDTO.setCreatedByCode(uid);
						gtSortManagementDetailDTO.setCreatedByName(uname);
						gtSortManagementDetailDTO.setCreatedDate(DateUtil.now());
						gtSortManagementDetailDTO.setUpdatedByCode(uid);
						gtSortManagementDetailDTO.setUpdatedByName(uname);
						gtSortManagementDetailDTO.setUpdatedDate(DateUtil.now());
						gtSortManagementDetailDTO.setGbSortManagementDetailOid(gbSortManagementDetailDTO.getGbSortManagementDetailOid());
						gtSortManagementDetailDTO.setGtSortManagementOid(gtSortManagement.getGtSortManagementOid());
						gtSortManagementDetailService.createGtSortManagementDetail(gtSortManagementDetailDTO);
					}
					
				}
			}
			
		}
	}
		
	}

}
