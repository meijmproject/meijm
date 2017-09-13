package com.yh.hr.info.warning.facade.impl;




import com.yh.component.taglib.TableTagBean;
import com.yh.hr.info.warning.dto.BizWarConfigInfoDTO;
import com.yh.hr.info.warning.dto.BizWarningInfoDTO;
import com.yh.hr.info.warning.service.BizWarningInfoService;
import com.yh.hr.info.warning.service.BizWarningQueryService;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.SpringBeanUtil;
import com.yh.platform.core.web.UserContext;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * 预警信息-实现类
 * @author	liuhw
 * @created 2014-03-31
 */
public class BizWarningInfoFacadeImpl 
{
	/**
	 * 用于查询人员基础预警信息，不查询日终任务预警信息
	 */
	@SuppressWarnings("unused")
	private static final String WARNINGFLAG = "BASE_";
	private BizWarningInfoService bizWarningInfoService;
	
	public void setBizWarningInfoService(BizWarningInfoService bizWarningInfoService) {
		this.bizWarningInfoService = bizWarningInfoService;
	}
	/**
	 * 预警信息-查询
	 * @return BizWarningInfoDTOs	预警信息-集合
	 * @throws ServiceException
	 */
	public List<BizWarningInfoDTO> listBizWarningInfoDTOs() throws ServiceException
	{
		return bizWarningInfoService.listBizWarningSetingInfo(UserContext.getSystemId());
	}
	/**
	 * 设置预警天数
	 * @throws ServiceException
	 */
	public void updateWarningDays(BizWarningInfoDTO dto) throws ServiceException
	{
		bizWarningInfoService.updateWarningDays(dto);
	}
	/*
	 * (non-Javadoc)
	 * @see gov.szghrs.unitpanel.warning.facade.BizWarningInfoFacade#listBizWarningInfo(yh.core.taglib.TableTagBean)
	 */
	public List<JSONObject> getBizWarningInfo(TableTagBean ttb)throws ServiceException
	{
		//如果预警天数是空的，则查询日终任务生成的数据
//		if(StringUtils.isEmpty(ttb.getCondition().get("warningDays")))
//		{
			BizWarningQueryService bizWarningQueryService = (BizWarningQueryService)SpringBeanUtil.getBean(ttb.getCondition().get("itemCode"));
			return bizWarningQueryService.listBizWarningInfo(ttb);
//		}
//		//如果预警天数不是空的，则根据人员基础库信息统计预警数据
//		else
//		{
//			BizWarningQueryService bizWarningQueryService = (BizWarningQueryService)SpringBeanUtil.getBean(WARNINGFLAG+ttb.getCondition().get("itemCode"));
//			return bizWarningQueryService.listBaseWarningInfo(ttb);
//		}
	}
	public void insertWarningPerson() throws ServiceException 
	{
		bizWarningInfoService.insertWarningPerson(null);
	}
	public String getBizWarningDays(String itemCode) throws ServiceException 
	{
		return bizWarningInfoService.getBizWarningDays(itemCode);
	}
	public List<BizWarConfigInfoDTO> listBizWarningInfo(TableTagBean ttb) throws ServiceException {
		List<BizWarConfigInfoDTO> list = bizWarningInfoService.listBizWarningInfo(ttb);
		if(CollectionUtils.isNotEmpty(list)){
			for(BizWarConfigInfoDTO bizWarConfigInfoDTO : list){
				BizWarningQueryService bizWarningQueryService = (BizWarningQueryService)SpringBeanUtil.getBean(bizWarConfigInfoDTO.getItemCode());
				long[] count = bizWarningQueryService.countBizWarningInfo(ttb);
				if(null != count && count.length>0)
				{
					bizWarConfigInfoDTO.setCount(count[0]);
					bizWarConfigInfoDTO.setHighLevelCount(count[1]);
					bizWarConfigInfoDTO.setMiddleLevelCount(count[2]);
					bizWarConfigInfoDTO.setLowLevelCount(count[3]);
				}else
				{
					bizWarConfigInfoDTO.setCount(0l);
					bizWarConfigInfoDTO.setHighLevelCount(0l);
					bizWarConfigInfoDTO.setMiddleLevelCount(0l);
					bizWarConfigInfoDTO.setLowLevelCount(0l);
				}
			}
			return list;
		}
		return null;
	}
}