package com.yh.hr.info.warning.service.impl;



import com.yh.component.taglib.TableTagBean;
import com.yh.hr.info.warning.bo.BizWarningInfo;
import com.yh.hr.info.warning.dto.BizWarConfigInfoDTO;
import com.yh.hr.info.warning.dto.BizWarningInfoDTO;
import com.yh.hr.info.warning.queryhelper.BizWarningInfoQueryHelper;
import com.yh.hr.info.warning.service.BizWarningInfoService;
import com.yh.platform.core.exception.ServiceException;

import java.util.List;

/**
 * @description 预警服务实现
 * @author liuhw
 * @created 2014-3-31
 * @version 1.0
 * 
 */
public class BizWarningInfoServiceImpl implements BizWarningInfoService
{
	public List<BizWarningInfo> listAll() throws ServiceException
	{
//		return BizWarningInfoQueryHelper.list();
		return null;
	}
	/**
	 * 预警业务事项-记录
	 * @param itemCode				业务事项
	 * @return bizWarningInfo		预警事项-记录
	 * @throws ServiceException
	 */
	public BizWarningInfo getSystemBizWarningInfo(String itemCode) throws ServiceException
	{
//		try
//		{
//			return BizWarningInfoQueryHelper.getSystemBizWarningInfo(itemCode);
//		}
//		catch (DataAccessException de)
//		{
//			throw new DataAccessFailureException("getSystemBizWarningInfo error", de);
//		}	
		return null;
	}
	
	/**
	 * 设置预警天数
	 * @throws ServiceException
	 */
	public   void updateWarningDays(BizWarningInfoDTO dto) throws ServiceException
	{
//		try
//		{
//			BizWarningInfo bo = DaoUtil.get(BizWarningInfo.class,dto.getBizWarningInfoOid()); 
//			bo.setWarningDays(dto.getWarningDays());
//			bo.update();
//			//修改预警天数 手动触发日终任务
//			String schedulerFlag = "Y";//日终任务开关（如果为空，则是日终任务，否则不是）；
//			this.insertWarningPerson(schedulerFlag);
//		}
//		catch(DataAccessException de)
//		{
//			throw new ServiceException("listSystemBizWarningInfos error", de);
//		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see gov.szghrs.warning.service.BizWarningInfoService#listBizWarningSetingInfo(java.lang.String)
	 */
	public List<BizWarningInfoDTO> listBizWarningSetingInfo(String systemId)throws ServiceException 
	{
//		List<BizWarningInfo> boList = BizWarningInfoQueryHelper.listSystemBizWarningInfos(systemId);
//		List<BizWarningInfoDTO> dtoList = new ArrayList<BizWarningInfoDTO>();
//		for(BizWarningInfo bizWarningInfo : boList)
//		{
//			BizWarningInfoDTO bizWarningInfoDto = new BizWarningInfoDTO();
//			BeanUtils.copyProperties(bizWarningInfo, bizWarningInfoDto);
//			
//			BizWarningParameterDTO bizWarningParameterDTO = new BizWarningParameterDTO();
//			bizWarningParameterDTO.setWarningDays(bizWarningInfo.getWarningDays());
//			BizWarningQueryService bizWarningQueryService = (BizWarningQueryService)SpringBeanUtil.getBean(bizWarningInfo.getItemCode());
//			bizWarningInfoDto.setItemCount(bizWarningQueryService.getWarningCountByItemCodeAndSystemId(bizWarningInfo.getItemCode(),UserContext.getSystemId()));// 事项预警总数
//			dtoList.add(bizWarningInfoDto);
//		}
//		return dtoList;
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see gov.szghrs.warning.service.BizWarningInfoService#insertWarningPerson(java.lang.String)
	 */
	public void insertWarningPerson(String schedulerFlag) throws ServiceException 
	{
//		List<BizWarningInfo> list = BizWarningInfoQueryHelper.list();
//		if(CollectionUtils.isNotEmpty(list))
//		{
//			//执行插入前先删除所有预警人员，再重新插入
//			BizWarningPersonQueryHelper.deleteAll();
//			//循环查询每个预警事项对应的预警人员插入到预警人员表中
//			for(BizWarningInfo bizWarningInfo : list)
//			{
//				BizWarningQueryService bizWarningQueryService = (BizWarningQueryService)SpringBeanUtil.getBean(bizWarningInfo.getItemCode());
//				bizWarningInfo.setSchedulerFlag(schedulerFlag);
//				List<BizWarningPersonDTO> dtoList = bizWarningQueryService.listBizWarningPersonInfo(bizWarningInfo);
//				if(CollectionUtils.isNotEmpty(dtoList))
//				{
//					for(BizWarningPersonDTO dto : dtoList)
//					{
//						BizWarningPerson bo = new BizWarningPerson();
//						bo.setEstimateEndDate(dto.getEstimateEndDate());
//						bo.setEstimateEndDays(dto.getEstimateEndDays());
//						bo.setPersonOid(dto.getPersonOid());
//						bo.setSystemId(dto.getSystemId());
//						bo.setWarningItemCode(dto.getWarningItemCode());
//						bo.setCreatedTs(DateUtil.now());
//						bo.save();
//					}
//				}
//			}
//		}
	}
	public List<BizWarConfigInfoDTO> listBizWarningInfo(TableTagBean ttb)
			throws ServiceException {
		return BizWarningInfoQueryHelper.listBizWarningInfo(ttb);
	}
	public String  getBizWarningDays(String itemCode) throws ServiceException{
		return BizWarningInfoQueryHelper.getBizWarningDays(itemCode);
	}
}
