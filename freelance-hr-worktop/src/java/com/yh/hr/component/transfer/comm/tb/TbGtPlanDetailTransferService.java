package com.yh.hr.component.transfer.comm.tb;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.gb.bo.GbPlanDetail;
import com.yh.hr.res.gb.dto.GbPlanDetailDTO;
import com.yh.hr.res.gb.queryhelper.GbPlanDetailQueryHelper;
import com.yh.hr.res.gb.service.GbPlanDetailService;
import com.yh.hr.res.gt.dto.GtPlanDetailDTO;
import com.yh.hr.res.gt.queryhelper.GtPlanDetailQueryHelper;
import com.yh.hr.res.gt.service.GtPlanDetailService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 岗位设置信息 业务库转基础库
 * @author huangyj
 * 2016-12-23
 */
public class TbGtPlanDetailTransferService implements InfoTransferService {
	
	private GtPlanDetailService gtPlanDetailService = (GtPlanDetailService) SpringBeanUtil.getBean("gtPlanDetailService");
	private GbPlanDetailService gbPlanDetailService = (GbPlanDetailService) SpringBeanUtil.getBean("gbPlanDetailService");

	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long)
	 */
	public void transfer(Long taskOid, Long unitOid) throws ServiceException {
		
		List<GtPlanDetailDTO> gtPlanDetailDTOList = GtPlanDetailQueryHelper.listGtPlanDetailDTOByTaskOid(taskOid);
		if(CollectionUtils.isNotEmpty(gtPlanDetailDTOList))
		{
			for(GtPlanDetailDTO gtPlanDetailDTO : gtPlanDetailDTOList)
			{
				if(!"4".equals(gtPlanDetailDTO.getPostType()))
				{
					List<GbPlanDetailDTO> gbPlanDetailDTOList = GbPlanDetailQueryHelper.listGbPlanDetailDTOByPostInfo(unitOid, gtPlanDetailDTO.getPostType(), gtPlanDetailDTO.getPostLevel());
					if(CollectionUtils.isNotEmpty(gbPlanDetailDTOList))
					{
						GbPlanDetailDTO gbPlanDetailDTO = gbPlanDetailDTOList.get(0);
						gbPlanDetailDTO.setPostLevelCount(gtPlanDetailDTO.getPostLevelCount());
						gbPlanDetailService.updateGbPlanDetail(gbPlanDetailDTO);
						
						gtPlanDetailDTO.setJhgGbPlanDetailOid(gbPlanDetailDTO.getJhgGbPlanDetailOid());
						gtPlanDetailService.updateGtPlanDetail(gtPlanDetailDTO);
					}
					else
					{
						GbPlanDetail gbPlanDetail = new GbPlanDetail();
						gbPlanDetail.setUnitOid(unitOid);
						gbPlanDetail.setPostType(gtPlanDetailDTO.getPostType());
						gbPlanDetail.setPostLevel(gtPlanDetailDTO.getPostLevel());
						gbPlanDetail.setPostLevelCount(gtPlanDetailDTO.getPostLevelCount());
						gbPlanDetail.setCreatedByCode(UserContext.getLoginUserID());
						gbPlanDetail.setCreatedByName(UserContext.getLoginUserName());
						gbPlanDetail.setCreatedDate(DateUtil.now());
						gbPlanDetail.save();
						
						gtPlanDetailDTO.setJhgGbPlanDetailOid(gbPlanDetail.getJhgGbPlanDetailOid());
						gtPlanDetailService.updateGtPlanDetail(gtPlanDetailDTO);
					}
				}
			}
		}
	}
}
