package com.yh.hr.res.pt.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pt.bo.PtWageHistory;
import com.yh.hr.res.pt.dto.PtWageHistoryDTO;
import com.yh.hr.res.pt.dto.PtWageHistoryItemsDTO;
import com.yh.hr.res.pt.queryhelper.PtWageHistoryQueryHelper;
import com.yh.hr.res.pt.service.PtWageHistoryItemsService;
import com.yh.hr.res.pt.service.PtWageHistoryService;
import com.yh.platform.core.constant.Constant;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.DateUtil;
import com.yh.platform.core.web.UserContext;

public class PtWageHistoryServiceImpl implements PtWageHistoryService{

	private PtWageHistoryItemsService	ptWageHistoryItemsService;


	public void setPtWageHistoryItemsService(
			PtWageHistoryItemsService ptWageHistoryItemsService) {
		this.ptWageHistoryItemsService = ptWageHistoryItemsService;
	}
	/**
	 * 工资历史详细信息列表
	 * @param 
	 * @return
	 * @throws ServiceException
	 */	
	public List<PtWageHistoryDTO> listWageHistoryStruts(Long ptWageHistoryOid)
			throws ServiceException {
		return setWageItemListByList(PtWageHistoryQueryHelper.listWageHistoryStruts(ptWageHistoryOid));
	}

	/**
	 * 新增工资历史信息
	 * @param 
	 * @return
	 * @throws ServiceException
	 */		
	public void createPtWageHistory(PtWageHistoryDTO dto)
			throws ServiceException {
		try{
			//dto转换为po
			PtWageHistory ptWageHistory =  BeanHelper.copyProperties(dto, PtWageHistory.class);
			//得到操作人信息
			String userId = UserContext.getLoginUserID();
			String userName = UserContext.getLoginUserName();
			//设置新增人信息
			ptWageHistory.setCreatedByCode(userId);
			ptWageHistory.setCreatedByName(userName);
			ptWageHistory.setCreatedDate(DateUtil.now());
			//设置修正原因为Y
			ptWageHistory.setCorrectFlag(Constant.YES);
			ptWageHistory.save();
			//获取生成的主键id   ptWageHistoryOid
			Long  ptWageHistoryOid = ptWageHistory.getPtWageHistoryOid();
			//获取到其中的工资子表（工资详细表）数据
			List<PtWageHistoryItemsDTO> listwageItemDTO = dto.getPtWHistoryItemslist();
			//循环更改子表的数据
			for(PtWageHistoryItemsDTO ptWageHistoryItemsDTO : listwageItemDTO){
				//将新增后产生的主键id设置进去
				ptWageHistoryItemsDTO.setPtWageHistoryOid(ptWageHistoryOid);
				ptWageHistoryItemsService.modifyOraddPtWageHistoryItems(ptWageHistoryItemsDTO);
			}
			
		}catch (ServiceException e) {
			throw new ServiceException(null, "工资历史信息新增失败");
		}	
	}

	/**
	 * 根据ID删除工资历史信息
	 * @param wagehistoryOid
	 * @return
	 * @throws ServiceException
	 */	
	public void deletePtWageHistory(Long ptWageHistoryOid)
			throws ServiceException {
		try{
			//查询数据
			PtWageHistory ptWageHistory = DaoUtil.get(PtWageHistory.class, ptWageHistoryOid);
			//判断数据是否存在，存在则删除
			if(null != ptWageHistory){
				ptWageHistory.setEffectiveFlag("0");
				//pbWageHistory.delete();
			}
		}catch (ServiceException e) {
			throw new ServiceException(null, "工资历史信息删除失败");
		}		
	}
	
	/**
	 * 修改工资历史信息
	 * @param ptWageHistoryDTO
	 * @return
	 * @throws ServiceException
	 */	
	public void modifyPtWageHistory(PtWageHistoryDTO ptWageHistoryDTO)
			throws ServiceException {
		//将dto转换成为po
		PtWageHistory ptWageHistory = DaoUtil.get(PtWageHistory.class, ptWageHistoryDTO.getPtWageHistoryOid());
		if(ptWageHistory != null){
			//排除不需要更新的字段"createdDate","createdByCode","createdByName"
			BeanHelper.copyProperties(ptWageHistoryDTO,ptWageHistory, 
				new String[]{"createdDate","createdByCode","createdByName","orderId"});
			ptWageHistory.setUpdatedByCode(UserContext.getLoginUserID());
			ptWageHistory.setUpdatedByName(UserContext.getLoginUserName());
			ptWageHistory.setUpdatedDate(DateUtil.now());
			//设置修正原因为Y
			ptWageHistory.setCorrectFlag(Constant.YES);
			ptWageHistory.setCorrectReason(ptWageHistoryDTO.getCorrectReason());
			ptWageHistory.update();
			
			//获取到其中的工资子表（工资详细表）数据
			List<PtWageHistoryItemsDTO> listwageItemDTO = ptWageHistoryDTO.getPtWHistoryItemslist();
			if(!CollectionUtils.isEmpty(listwageItemDTO)){
			//循环更改子表的数据
			for(PtWageHistoryItemsDTO ptWageHistoryItemsDTO : listwageItemDTO){
				//将新增后产生的主键id设置进去
				ptWageHistoryItemsDTO.setPtWageHistoryOid(ptWageHistory.getPtWageHistoryOid());
				ptWageHistoryItemsService.modifyOraddPtWageHistoryItems(ptWageHistoryItemsDTO);
			}
			}
		}
	}	
	
	/**
	 * 根据ptWageHistoryOid判断是修改还是新增工资历史信息
	 * @param ptWageHistoryDTO
	 * @return
	 * @throws ServiceException
	 */	
	public void modifyORaddPtWageHistory(PtWageHistoryDTO ptWageHistoryDTO)
			throws ServiceException {
		try{
		//获取业务工资历史ID来判断是新增还是修改
		Long ptWageHistoryOid = ptWageHistoryDTO.getPtWageHistoryOid();
		//如果PtWageHistoryOid为空则新增，否则为修改
		if(null==ptWageHistoryOid||ptWageHistoryOid==0){
			createPtWageHistory(ptWageHistoryDTO);
		}
		else
		{
			modifyPtWageHistory(ptWageHistoryDTO);
		}

		}catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(null, "工资历史信息修改失败"+e.getMessage());
		}
		
	}

	/**
	 * 根据业务人员ID、生效标识查询人员工资历史
	 * @param bizPersonOid 业务人员IDs
	 * @param effectFlag 生效标识
	 * @return List<PtWageHistoryDTO> 业务工资历史列表
	 * @throws ServiceException
	 */
	public List<PtWageHistoryDTO> getPtWageHistoryList(Long bizPersonOid,String effectFlag) throws ServiceException
	{
		List<PtWageHistoryDTO> list = setWageItemListByList(PtWageHistoryQueryHelper.getPtWageHistoryList(bizPersonOid, effectFlag));
		return list;
	}
	
	/**
	 * 根据业务工资历史id 查询此条数据有没有修正值
	 * @param calWageHistoryOid
	 * @param effectFlag 生效标识
	 * @return List<PtWageHistoryDTO>
	 * @throws ServiceException
	 */
	public List<PtWageHistoryDTO> getModifyPtWageHistoryList(Long calWageHistoryOid) throws ServiceException
	{
		List<PtWageHistoryDTO> list = setWageItemListByList(PtWageHistoryQueryHelper.getModifyPtWageHistory(calWageHistoryOid));
		return list;
	}
	/**
	 *	传入工资历史集合，遍历工资历史集合将工资项设置进去
	 * @param list
	 * @return
	 * @throws ServiceException
	 */
	private List<PtWageHistoryDTO> setWageItemListByList(List<PtWageHistoryDTO> list) throws ServiceException
	{
		if(CollectionUtils.isNotEmpty(list))
		{
			for (PtWageHistoryDTO ptWageHistoryDTO : list) 
			{
				ptWageHistoryDTO.setPtWHistoryItemslist(
						ptWageHistoryItemsService.listPtWageHistoryItems(ptWageHistoryDTO.getPtWageHistoryOid()));
			}
		}
		return list;
	}

	/**
	 * 根据人员ID，生效标识删除人员业务工资历史（先删除逻辑关系、工资项、后删除工资历史记录）
	 * @param bizPersonOid
	 * @param effectiveFlag
	 * @throws ServiceException
	 */
	public void removePtWageHistory(Long bizPersonOid,String effectiveFlag) throws ServiceException
	{
		PtWageHistoryQueryHelper.removePtWageHistory(bizPersonOid, effectiveFlag);
	}
	/**
	 * 计数
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public int countPtWageHistoryByBizPersonOid(Long bizPersonOid) throws ServiceException
	{
		return PtWageHistoryQueryHelper.countPtWageHistoryByBizPersonOid(bizPersonOid);
	}
	/**
	 * 创建PtWageHistory，同时创建DTO中的工资项、工资逻辑
	 * @param ptWageHistoryDTO
	 * @throws ServiceException
	 */
	public Long save(PtWageHistoryDTO ptWageHistoryDTO) throws ServiceException
	{
		//dto转换为po
		PtWageHistory ptWageHistory =  BeanHelper.copyProperties(ptWageHistoryDTO, PtWageHistory.class);
		//得到操作人信息
		String userId = UserContext.getLoginUserID();
		String userName = UserContext.getLoginUserName();
		//设置新增人信息
		ptWageHistory.setCreatedByCode(userId);
		ptWageHistory.setCreatedByName(userName);
		ptWageHistory.setCreatedDate(DateUtil.now());
		ptWageHistory.save();
		
		//获取生成的主键id   ptWageHistoryOid
		Long  ptWageHistoryOid = ptWageHistory.getPtWageHistoryOid();
		//获取到其中的工资子表（工资详细表）数据
		List<PtWageHistoryItemsDTO> listWageItemDTO = ptWageHistoryDTO.getPtWHistoryItemslist();
		if(CollectionUtils.isNotEmpty(listWageItemDTO))
		{
			//循环更改子表的数据
			for(PtWageHistoryItemsDTO ptWageHistoryItemsDTO : listWageItemDTO){
				//将新增后产生的主键id设置进去
				ptWageHistoryItemsDTO.setPtWageHistoryOid(ptWageHistoryOid);
				ptWageHistoryItemsService.save(ptWageHistoryItemsDTO);
			}
		}
		return ptWageHistoryOid;
	}
	/**
	 * 更新计算Id
	 * @param ptWageHistoryOid
	 * @param calPtWageHistoryOid
	 * @throws ServiceException
	 */
	public void updateCalPtWageHistoryOid(Long ptWageHistoryOid,Long calPtWageHistoryOid) throws ServiceException
	{
		PtWageHistory ptWageHistory = DaoUtil.get(PtWageHistory.class, ptWageHistoryOid);
		ptWageHistory.setCalWageHistoryOid(calPtWageHistoryOid);
		ptWageHistory.update();
	}
	
	/**
	 * 根据主键ID查询工资历史
	 * @param ptWageHistoryOid
	 * @return
	 * @throws ServiceExcption
	 */
	public PtWageHistoryDTO getPtWageHistoryDTO(Long ptWageHistoryOid) throws ServiceException
	{
		PtWageHistory ptWageHistory = DaoUtil.get(PtWageHistory.class, ptWageHistoryOid);
		if(null != ptWageHistory)
		{
			return BeanHelper.copyProperties(ptWageHistory, PtWageHistoryDTO.class);
		}
		return null; 
	}
	/**根据changeType(变动类型)、startDateOfWage(起薪时间)
	 * 查询工资历史详细信息
	 * @return
	 * @throws ServiceException
	 * */
	public PtWageHistoryDTO getPtWageHistoryDTOByChangeType(Long bizPersonOid, String changeType,
			String startDateOfWageStr,String effectiveFlag) throws ServiceException {
		PtWageHistoryDTO ptWageHistoryDTO = PtWageHistoryQueryHelper.getPtWageHistoryDTOByChangeType(bizPersonOid , changeType,startDateOfWageStr,effectiveFlag);
		if(ptWageHistoryDTO!=null){
			List<PtWageHistoryItemsDTO>  ptWageHistoryItemsDTOList = ptWageHistoryItemsService.listPtWageHistoryItems(ptWageHistoryDTO.getPtWageHistoryOid());
				ptWageHistoryDTO.setPtWHistoryItemslist(ptWageHistoryItemsDTOList);
				return ptWageHistoryDTO;
		}
		return null;
	}
}
