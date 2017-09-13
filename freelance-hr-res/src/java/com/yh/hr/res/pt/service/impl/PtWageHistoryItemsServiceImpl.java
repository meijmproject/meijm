package com.yh.hr.res.pt.service.impl;

import java.util.List;

import com.yh.hr.res.pt.bo.PtWageHistoryItems;
import com.yh.hr.res.pt.dto.PtWageHistoryLogicDTO;
import com.yh.hr.res.pt.service.PtWageHistoryItemsService;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pt.dto.PtWageHistoryItemsDTO;
import com.yh.hr.res.pt.queryhelper.PtWageHistoryItemsQueryHelper;
import com.yh.hr.res.pt.service.PtWageHistoryLogicService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

public class PtWageHistoryItemsServiceImpl implements PtWageHistoryItemsService {
private PtWageHistoryLogicService ptWageHistoryLogicService;
	
	public void setPtWageHistoryLogicService(PtWageHistoryLogicService ptWageHistoryLogicService) {
		this.ptWageHistoryLogicService = ptWageHistoryLogicService;
	}

	/**
	 * 工资历史明细表详细信息列表
	 * @param 
	 * @return
	 * @throws ServiceException
	 */	

	public List<PtWageHistoryItemsDTO> listPtWageHistoryItems(Long ptWageHistoryOid)
			throws ServiceException {
		return setLogicByList(PtWageHistoryItemsQueryHelper.listPtWageHistoryItems(ptWageHistoryOid));
	}
	
	
	/**
	 * 根据wagehistoryItemOid删除工资历史明细信息
	 * @param wagehistoryItemOid
	 * @throws ServiceException
	 */
	public void deletePtWageHistoryItems(Long ptWageHistoryItemOid)throws ServiceException{
		//得到工资历史明细信息
		PtWageHistoryItems ptWageHistoryItems = DaoUtil.get(PtWageHistoryItems.class,ptWageHistoryItemOid);
	    if(ptWageHistoryItems!=null){
	    	//删除
	    	ptWageHistoryItems.delete();
	    }
	}

	/**
	 * 根据wagehistoryItemOid工资历史明细信息
	 */
	public PtWageHistoryItemsDTO getPtWageHistoryItems(Long ptWageHistoryItemOid)
			throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtWageHistoryItems.class,ptWageHistoryItemOid),PtWageHistoryItemsDTO.class);

	}

	/**
	 * 根据ptWageHistoryOid、wageItemCode查询工资历史明细信息
	 */
	public PtWageHistoryItemsDTO getPtWageHistoryItems(Long ptWageHistoryOid,String wageItemCode)
			throws ServiceException {
		PtWageHistoryItemsDTO ptWageHistoryItemsDTO = PtWageHistoryItemsQueryHelper.getPtWageHistoryItems(ptWageHistoryOid, wageItemCode);
		if(null!=ptWageHistoryItemsDTO){
			ptWageHistoryItemsDTO.setPtWageHistoryLogiclist(
					ptWageHistoryLogicService.listPtWageHistoryLogic(ptWageHistoryItemsDTO.getPtWageHistoryItemOid())
					);
		}
		return ptWageHistoryItemsDTO;
	}
	
	/**
	 * 修改工资历史明细信息
	 */
	public void modifyPtWageHistoryItems(
			PtWageHistoryItemsDTO ptWageHistoryItemsDTO)
			throws ServiceException {
		//将dto转换成为po
		PtWageHistoryItems ptWageHistoryItems = DaoUtil.get(PtWageHistoryItems.class, ptWageHistoryItemsDTO.getPtWageHistoryItemOid());
		if(ptWageHistoryItems != null){
			//这里的值并没有复制过来
			BeanHelper.copyProperties(ptWageHistoryItemsDTO,ptWageHistoryItems);
			ptWageHistoryItems.update();
			//取出ptWageHistoryItemsDTO保存的Wagelogic的集合，遍历将它保存
			List<PtWageHistoryLogicDTO> ptWageLogicDTOList= ptWageHistoryItemsDTO.getPtWageHistoryLogiclist();
				if(!CollectionUtils.isEmpty(ptWageLogicDTOList)){
					 for(PtWageHistoryLogicDTO ptWageHistoryLogicDTO : ptWageLogicDTOList){
						 ptWageHistoryLogicDTO.setPtWageHistoryItemOid(ptWageHistoryItems.getPtWageHistoryItemOid());
						 ptWageHistoryLogicService.modifyOrAddPtWageHistoryLogic(ptWageHistoryLogicDTO);
					 	}
				}
		}
	}
	
	/**根据ptWageHistoryOid、wageItemCode
	 * 查询出记录
	 * @throws ServiceException 
	 * 
	 */
	public List<PtWageHistoryItemsDTO> getPtWageHistoryItemsList(
			Long ptWageHistoryOid, String wageItemCode) throws ServiceException {
		List<PtWageHistoryItemsDTO> ptWageItemsDTOlist = PtWageHistoryItemsQueryHelper.getPtWageHistoryItemsList(ptWageHistoryOid, wageItemCode);
		return setLogicByList(ptWageItemsDTOlist);
	}	
	
	
	/**根据ptWageHistoryOid、wageItemCode是否查询出记录
	 * 判断修改或者新增工资历史明细信息
	 */
	public void modifyOraddPtWageHistoryItems(
			PtWageHistoryItemsDTO ptWageHistoryItemsDTO)
			throws ServiceException {
		//根据ptWageHistoryOid、wageItemCode查询工资历史明细信息
		PtWageHistoryItemsDTO ptWageItemsDTO = getPtWageHistoryItems(
				ptWageHistoryItemsDTO.getPtWageHistoryOid(),ptWageHistoryItemsDTO.getWageItemCode());
		if(null!=ptWageItemsDTO){
			//将查询出的主键id设置到修改的DTO中
			ptWageHistoryItemsDTO.setPtWageHistoryItemOid(ptWageItemsDTO.getPtWageHistoryItemOid());
			modifyPtWageHistoryItems(ptWageHistoryItemsDTO);
		}
		else{
			//没有就创建
			save(ptWageHistoryItemsDTO);
			}
		}



	/**
	 * 新增工资历史明细信息
	 */
	public void createPtWageHistoryItems(
			PtWageHistoryItemsDTO ptWageHistoryItemsDTO)
			throws ServiceException {
		PtWageHistoryItems ptWageHistoryItems = BeanHelper.copyProperties(ptWageHistoryItemsDTO, PtWageHistoryItems.class);
		//保存
		ptWageHistoryItems.save();
		
/*		//获取生成的主键id   ptWageHistoryItemOid
		Long  ptWageHistoryItemOid = ptWageHistoryItems.getPtWageHistoryItemOid();
		//获取到其中的工资逻辑表数据
		List<PtWageHistoryLogicDTO> listwagelogicDTO = ptWageHistoryItemsDTO.getPtWageHistoryLogiclist();
		//循环更改子表的数据  
		for(PtWageHistoryLogicDTO ptWageHistoryLogicDTO : listwagelogicDTO){
			//将新增后产生的主键id设置进去
			ptWageHistoryLogicDTO.setPtWageHistoryItemOid(ptWageHistoryItemOid);
			ptWageHistoryLogicService.modifyPtWageHistoryLogic(ptWageHistoryLogicDTO);
		}*/
		
	}
	public PtWageHistoryItemsDTO getPtWageHistoryItemsOid(Long ptWageHistoryItemOid) throws ServiceException {
		
		return BeanHelper.copyProperties(DaoUtil.get(PtWageHistoryItems.class,ptWageHistoryItemOid),PtWageHistoryItemsDTO.class);

	}
	/**
	 * 传入工资项集合，遍历工资项集合将工资项逻辑关系设置进去
	 * @param list
	 * @return
	 * @throws ServiceException
	 */
	private List<PtWageHistoryItemsDTO> setLogicByList(List<PtWageHistoryItemsDTO> list)throws ServiceException
	{
		if(CollectionUtils.isNotEmpty(list))
		{
			for(PtWageHistoryItemsDTO ptWageHistoryItemsDTO : list){
				ptWageHistoryItemsDTO.setPtWageHistoryLogiclist(
						ptWageHistoryLogicService.listPtWageHistoryLogic(ptWageHistoryItemsDTO.getPtWageHistoryItemOid()));
				
			}
		}
		return list; 
	}
	/**
	 * 新增工资历史明细信息（同时创建工资逻辑关系）
	 * @param ptWageHistoryItemsDTO
	 * @throws ServiceException
	 */
	public void save(PtWageHistoryItemsDTO ptWageHistoryItemsDTO) throws ServiceException 
	{
		PtWageHistoryItems ptWageHistoryItems = BeanHelper.copyProperties(ptWageHistoryItemsDTO, PtWageHistoryItems.class);
		if(null == ptWageHistoryItems.getWageAmount())
		{
			ptWageHistoryItems.setWageAmount(0.0);
		}
		//保存
		ptWageHistoryItems.save();
		
		//获取生成的主键id   ptWageHistoryItemOid
		Long  ptWageHistoryItemOid = ptWageHistoryItems.getPtWageHistoryItemOid();
		//获取到其中的工资逻辑表数据
		List<PtWageHistoryLogicDTO> listwagelogicDTO = ptWageHistoryItemsDTO.getPtWageHistoryLogiclist();
		if(CollectionUtils.isNotEmpty(listwagelogicDTO))
		{
			//循环创建子表的数据  
			for(PtWageHistoryLogicDTO ptWageHistoryLogicDTO : listwagelogicDTO){
				//将新增后产生的主键id设置进去
				ptWageHistoryLogicDTO.setPtWageHistoryItemOid(ptWageHistoryItemOid);
				ptWageHistoryLogicService.createPtWageHistoryLogic(ptWageHistoryLogicDTO);
			}
		}
		
	}

}
