package com.yh.hr.component.transfer.comm.tb;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.gb.bo.GbSortManagement;
import com.yh.hr.res.gb.dto.GbSortManagementDTO;
import com.yh.hr.res.gb.dto.GbSortManagementDetailDTO;
import com.yh.hr.res.gb.queryhelper.GbSortManagementDetailQueryHelper;
import com.yh.hr.res.gb.queryhelper.GbSortManagementQueryHelper;
import com.yh.hr.res.gb.service.GbSortManagementDetailService;
import com.yh.hr.res.gt.dto.GtSortManagementDTO;
import com.yh.hr.res.gt.dto.GtSortManagementDetailDTO;
import com.yh.hr.res.gt.queryhelper.GtSortManagementQueryHelper;
import com.yh.hr.res.gt.service.GtSortManagementDetailService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

/**
 * 业务库事业单位岗位分类管理情况转到基础库
 * @author wangjie
 * @date 2016-12-26 
 * @version 1.0 
 */
public class TbGtSortManagementDetailService implements InfoTransferService {
	private GbSortManagementDetailService gbSortManagementDetailService =(GbSortManagementDetailService)SpringBeanUtil.getBean("gbSortManagementDetailService");
	private GtSortManagementDetailService gtSortManagementDetailService =(GtSortManagementDetailService)SpringBeanUtil.getBean("gtSortManagementDetailService");
	
	
	@Override
	public void transfer(Long taskOid, Long unitOid) throws ServiceException {
		
		List<GtSortManagementDTO> gtSortManagementDTOList = GtSortManagementQueryHelper.listGtSortManagementDTO(unitOid, taskOid);
		if(CollectionUtils.isNotEmpty(gtSortManagementDTOList))
		{
			GtSortManagementDTO gtSortManagementDTO = gtSortManagementDTOList.get(0);
			List<GtSortManagementDetailDTO> listGt = gtSortManagementDetailService.listGtSortManagementDetailByManageId(gtSortManagementDTO.getGtSortManagementOid());
			
			List<GbSortManagementDTO> gbSortManagementDTOList = GbSortManagementQueryHelper.listGbSortManagementByUnitOid(unitOid);
			if(CollectionUtils.isNotEmpty(gbSortManagementDTOList))
			{
				for(GtSortManagementDetailDTO gtSortManagementDetailDTO : listGt)
				{
					List<GbSortManagementDetailDTO> gbSortManagementDetailDTOList = GbSortManagementDetailQueryHelper.listGbSortManagementDetailByPostInfo(gbSortManagementDTOList.get(0).getGbSortManagementOid(), gtSortManagementDetailDTO.getPostType(), gtSortManagementDetailDTO.getPostSetType());
					if(CollectionUtils.isNotEmpty(gbSortManagementDetailDTOList))
					{
						GbSortManagementDetailDTO gbSortManagementDetailDTO = BeanHelper.copyProperties(gtSortManagementDetailDTO, GbSortManagementDetailDTO.class);
						gbSortManagementDetailDTO.setGbSortManagementDetailOid(gbSortManagementDetailDTOList.get(0).getGbSortManagementDetailOid());
						gbSortManagementDetailDTO.setGbSortManagementOid(gbSortManagementDTOList.get(0).getGbSortManagementOid());
						gbSortManagementDetailService.updateGbSortManagementDetail(gbSortManagementDetailDTO);
					}
					else
					{
						GbSortManagementDetailDTO gbSortManagementDetailDTO = BeanHelper.copyProperties(gtSortManagementDetailDTO, GbSortManagementDetailDTO.class);
						gbSortManagementDetailDTO.setGbSortManagementOid(gbSortManagementDTOList.get(0).getGbSortManagementOid());
						gbSortManagementDetailService.createGbSortManagementDetail(gbSortManagementDetailDTO);
					}
				}
			}
			else
			{
				GbSortManagement gbSortManagement = new GbSortManagement();
				gbSortManagement.setUnitOid(unitOid);
				gbSortManagement.setCreatedByCode(UserContext.getLoginUserID());
				gbSortManagement.setCreatedByName(UserContext.getLoginUserName());
				gbSortManagement.setCreatedDate(DateUtil.now());
				gbSortManagement.save();
				
				for(GtSortManagementDetailDTO gtSortManagementDetailDTO : listGt)
				{
					GbSortManagementDetailDTO gbSortManagementDetailDTO = BeanHelper.copyProperties(gtSortManagementDetailDTO, GbSortManagementDetailDTO.class);
					gbSortManagementDetailDTO.setGbSortManagementOid(gbSortManagement.getGbSortManagementOid());
					gbSortManagementDetailService.createGbSortManagementDetail(gbSortManagementDetailDTO);
				}
			}
		}
	}
}
