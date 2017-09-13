package com.yh.hr.orghc.unit.transfer.bt;

import java.util.List;

import com.yh.hr.orghc.ut.dto.BizUtUnitDTO;
import org.apache.commons.collections.CollectionUtils;
import com.yh.hr.component.tansfer.service.InfoTransferService;
import com.yh.hr.orghc.ut.service.BizUtUnitService;
import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.gb.dto.GbPostPercentDTO;
import com.yh.hr.res.gb.service.GbPostPercentService;
import com.yh.hr.res.gt.dto.GtPostPercentDTO;
import com.yh.hr.res.gt.service.GtPostPercentService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.SpringBeanUtil;

/**
 * 岗位信息  转  业务库
 * @author zhengdr
 *
 * 时间:2016-12-22下午01:56:04
 */
public class BtGtPostTransferService implements InfoTransferService {
	
	//岗位信息Service--基础
	private GbPostPercentService gbPostPercentService = (GbPostPercentService) SpringBeanUtil.getBean("gbPostPercentService");
	//岗位信息Service--业务
	private GtPostPercentService gtPostPercentService = (GtPostPercentService) SpringBeanUtil.getBean("gtPostPercentService");
	private BizUtUnitService bizUtUnitService = (BizUtUnitService)SpringBeanUtil.getBean("bizUtUnitService");


	/* (non-Javadoc)
	 * @see InfoTransferService#transfer(java.lang.Long)
	 */
	public void transfer(Long taskOid, Long unitOid) throws ServiceException {
		
		List<GbPostPercentDTO> gbPostPercentDTOs = gbPostPercentService.getGbPostPercentDTOList(unitOid);
		if(CollectionUtils.isNotEmpty(gbPostPercentDTOs)){
			BizUtUnitDTO bizUtUnitDTO = bizUtUnitService.findBizUtUnitByTaskOid(taskOid);
			for(GbPostPercentDTO gbPostPercentDTO : gbPostPercentDTOs){	
				//专业技术类才转业务库
				if(DicConstants.YHRS0022_2.equals(gbPostPercentDTO.getPostType())){
					GtPostPercentDTO gtPostPercentDTO = new GtPostPercentDTO();
					//复制
					BeanHelper.copyProperties(gbPostPercentDTO, gtPostPercentDTO);
					gtPostPercentDTO.setUtUnitOid(bizUtUnitDTO.getUtUnitOid());
					
					gtPostPercentService.createGtPostPercent(gtPostPercentDTO);
				}
				
			}
		}
	}

}
