package com.yh.hr.component.transfer.comm.tb;

import java.util.List;

import com.yh.hr.component.tansfer.service.InfoTransferService;
import org.apache.commons.collections.CollectionUtils;
import com.yh.hr.res.gb.dto.GbPostPercentDTO;
import com.yh.hr.res.gb.service.GbPostPercentService;
import com.yh.hr.res.gt.dto.GtPostPercentDTO;
import com.yh.hr.res.gt.service.GtPostPercentService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 岗位信息 业务库 转  基础库
 * @author zhengdr
 *
 * 时间:2016-12-22下午01:56:04
 */
public class TbGtPostPercentTransferService implements InfoTransferService {
	
	//岗位信息Service--基础
	private GbPostPercentService gbPostPercentService = (GbPostPercentService) SpringBeanUtil.getBean("gbPostPercentService");
	//岗位信息Service--业务
	private GtPostPercentService gtPostPercentService = (GtPostPercentService) SpringBeanUtil.getBean("gtPostPercentService");


	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long)
	 */
	public void transfer(Long utUnitOid, Long unitOid) throws ServiceException {
		List<GbPostPercentDTO> gbPostPercentDTOs = gbPostPercentService.getGbPostPercentDTOList(unitOid);
		//得到业务信息
		List<GtPostPercentDTO> gtPostPercentDTOs = gtPostPercentService.getGtPostPercentDTOList(utUnitOid);
		boolean flag = false;
			if(CollectionUtils.isNotEmpty(gtPostPercentDTOs)){				
				//遍历业务信息
				for(GtPostPercentDTO gt:gtPostPercentDTOs){
					//基础信息
					GbPostPercentDTO gbPostPercentDTO = new GbPostPercentDTO();
					//复制
					BeanHelper.copyProperties(gt, gbPostPercentDTO);
					if(CollectionUtils.isNotEmpty(gbPostPercentDTOs)) {
						for(GbPostPercentDTO gb:gbPostPercentDTOs){
							if(gt.getPostLevel().equals(gb.getPostLevel())
									&& gt.getPostType().equals(gb.getPostType())){
								if(null != gbPostPercentDTO.getPercent()){									
									gb.setPercent(gbPostPercentDTO.getPercent());
									gbPostPercentService.modifyGbPostPercent(gb);
								}
								flag=true;
							}
						}
					}
						if(flag==false){							
							//设置unitOid
							gbPostPercentDTO.setUnitOid(unitOid);
							//创建保存
							gbPostPercentService.createGbPostPercent(gbPostPercentDTO);
						}
				}
		
			}
	}

}
