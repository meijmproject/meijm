package com.yh.hr.res.pt.queryhelper;


import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.res.pt.dto.PtWageHistoryItemsDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringUtil;

public class PtWageHistoryItemsQueryHelper {
	/**
	 * 工资历史明细表详细信息列表
	 * @param 
	 * @return
	 * @throws ServiceException
	 */	
	public static List<PtWageHistoryItemsDTO> listPtWageHistoryItems(
		Long ptWageHistoryOid) throws ServiceException{
		final StringBuffer hBuffer =  new StringBuffer("from PtWageHistoryItems pt where  1 = 1");
		if(StringUtil.isNotNull(ptWageHistoryOid)){
			hBuffer.append(" and pt.ptWageHistoryOid =" +ptWageHistoryOid);
		}
		return BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), PtWageHistoryItemsDTO.class);
	}
/**
 * 根据工资历史ID、工资项编码查询人员工资历史
 * @param ptWageHistoryOid 工资历史ID
 * @param wageItemCode 工资项编码
 * @return List<PtWageHistoryItemsDTO> 业务工资历史明细表
 * @throws ServiceException
 */
public static PtWageHistoryItemsDTO getPtWageHistoryItems(Long ptWageHistoryOid,String wageItemCode) throws ServiceException 
{
	final StringBuffer hBuffer = new StringBuffer(
			"from PtWageHistoryItems wh  ");
	if (StringUtil.isNotNull(ptWageHistoryOid) && wageItemCode != null) {
		hBuffer.append("  where wh.ptWageHistoryOid ="
				+ ptWageHistoryOid);
		hBuffer.append(" and wh.wageItemCode ='" + wageItemCode + "'");
	}
	@SuppressWarnings("rawtypes")
	List list = DaoUtil.find(hBuffer.toString());
	if (CollectionUtils.isEmpty(list))
		return null;
	return BeanHelper.copyProperties(list.get(0),
			PtWageHistoryItemsDTO.class);
}


/**
 * 根据工资历史ID、工资项编码查询人员工资历史(集合)
 * @param ptWageHistoryOid 工资历史ID
 * @param wageItemCode 工资项编码
 * @return List<PtWageHistoryItemsDTO> 业务工资历史明细表
 * @throws ServiceException
 */
@SuppressWarnings("unchecked")
public static List<PtWageHistoryItemsDTO> getPtWageHistoryItemsList(
		Long ptWageHistoryOid, String wageItemCode) throws ServiceException {
	final StringBuffer hBuffer = new StringBuffer(
			"from PtWageHistoryItems wh  ");
	if (StringUtil.isNotNull(ptWageHistoryOid) && wageItemCode != null) {
		hBuffer.append("  where wh.ptWageHistoryOid ="
				+ ptWageHistoryOid);
		hBuffer.append(" and wh.wageItemCode ='" + wageItemCode + "'");
	}
	@SuppressWarnings("rawtypes")
	List list = DaoUtil.find(hBuffer.toString());
	if (CollectionUtils.isEmpty(list))
		return null;
	return BeanHelper.copyProperties(list,
			PtWageHistoryItemsDTO.class);
}
}
