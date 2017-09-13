package com.yh.hr.res.pt.service.impl;

import java.util.List;

import com.yh.hr.res.pt.bo.PtWageHistoryLogic;
import com.yh.hr.res.pt.dto.PtWageHistoryLogicDTO;
import com.yh.hr.res.pt.queryhelper.PtWageHistoryLogicQueryHelper;
import com.yh.hr.res.pt.service.PtWageHistoryLogicService;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;

public class PtWageHistoryLogicServiceImpl implements PtWageHistoryLogicService{
	/**
	 * 工资逻辑表详细信息列表
	 * @return
	 * @throws ServiceException
	 */	
	public List<PtWageHistoryLogicDTO> listPtWageHistoryLogic(
			Long ptWageHistoryItemOid) throws ServiceException {
		return  PtWageHistoryLogicQueryHelper.listPtWageHistoryLogic(ptWageHistoryItemOid);
	}
	/**
	 * 删除工资历史逻辑表信息
	 */
	public void deletePtWageHistoryLogic(Long ptWageHistoryItemOid,String logicCode)//, Long logicCode)
			throws ServiceException {
/*		//查询数据
		PtWageHistoryLogic ptWageHistoryLogic = DaoUtil.get(PtWageHistoryLogic.class, ptWageHistoryItemOid);
		//判断数据是否存在，存在则删除
		if(null != ptWageHistoryLogic){
			ptWageHistoryLogic.delete();
			//pbWageHistory.delete();
		}*/	
		PtWageHistoryLogicQueryHelper.deletePtWageHistoryLogic(ptWageHistoryItemOid,logicCode);
	}

	/**
	 * 新增工资历史逻辑表信息
	 */
	public void createPtWageHistoryLogic(
			PtWageHistoryLogicDTO ptWageHistoryLogicDTO)
			throws ServiceException {
		PtWageHistoryLogic ptWageHistoryLogic = BeanHelper.copyProperties(ptWageHistoryLogicDTO, PtWageHistoryLogic.class);
		//保存
		ptWageHistoryLogic.save();
	}
	/**
	 * 获取工资历史逻辑表信息
	 */
	public PtWageHistoryLogicDTO getPtWageHistoryLogic(Long ptWageHistoryItemOid)
			throws ServiceException {
		return BeanHelper.copyProperties(DaoUtil.get(PtWageHistoryLogic.class,ptWageHistoryItemOid),PtWageHistoryLogicDTO.class);
	}
	
	/**判断是修改还是新增
	 * 工资历史逻辑表信息
	 */	
	public void modifyOrAddPtWageHistoryLogic(
			PtWageHistoryLogicDTO ptWageHistoryLogicDTO)
			throws ServiceException {
		
		//根据ptWageHistoryItemOid、logicCode查询是否有记录 
		PtWageHistoryLogicDTO	ptWageLogicDTO = getPtWageHistoryLogicBylogicCode(
				ptWageHistoryLogicDTO.getPtWageHistoryItemOid(), ptWageHistoryLogicDTO.getLogicCode());
			if(null!=ptWageLogicDTO){
				//修改逻辑表信息
				modifyPtWageHistoryLogic(ptWageLogicDTO);
			}
			else{
				//没有就新增
				createPtWageHistoryLogic(ptWageHistoryLogicDTO);
			}
		}
	/**
	 * 修改工资历史逻辑表信息
	 */
	public void modifyPtWageHistoryLogic(
			PtWageHistoryLogicDTO ptWageHistoryLogicDTO)
			throws ServiceException {
		PtWageHistoryLogic ptWageHistoryLogic = BeanHelper.copyProperties(ptWageHistoryLogicDTO,PtWageHistoryLogic.class);
		PtWageHistoryLogicQueryHelper.updatelogicValue(ptWageHistoryLogic.getPtWageHistoryItemOid(),
				ptWageHistoryLogic.getLogicCode(),ptWageHistoryLogic.getLogicValue());	
		//ptWageHistoryLogic.update();
	}
	/**根据ptWageHistoryItemOid、logicCode
	 * 查询工资历史逻辑表信息
	 */
	public PtWageHistoryLogicDTO getPtWageHistoryLogicBylogicCode(
			Long ptWageHistoryItemOid, String logicCode) throws ServiceException {
		return PtWageHistoryLogicQueryHelper.getPtWHistoryLogicBylogicCode(ptWageHistoryItemOid, logicCode);
		
	}
	/**根据ptWageHistoryItemOid、wageItemCode
	 * 查询工资历史逻辑表信息
	 */
	public List<PtWageHistoryLogicDTO> getPtWageHistoryLogicBywageItemCode(
			Long ptWageHistoryItemOid, String wageItemCode) throws ServiceException {
		return PtWageHistoryLogicQueryHelper.getPtWHistoryLogicBywageItemCode(ptWageHistoryItemOid, wageItemCode);
		
	}





	



}
