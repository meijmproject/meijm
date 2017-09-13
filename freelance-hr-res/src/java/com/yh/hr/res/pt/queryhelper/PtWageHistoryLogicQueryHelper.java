package com.yh.hr.res.pt.queryhelper;

import java.util.List;

import com.yh.hr.res.pt.dto.PtWageHistoryLogicDTO;
import org.apache.commons.collections.CollectionUtils;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringUtil;

public class PtWageHistoryLogicQueryHelper {
	/**
	 * 工资逻辑表详细信息列表
	 * 
	 * @param
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtWageHistoryLogicDTO> listPtWageHistoryLogic(
			Long ptWageHistoryItemOid) throws ServiceException {
		final StringBuffer hBuffer = new StringBuffer(
				"from PtWageHistoryLogic pt where  1 = 1");
		if (StringUtil.isNotNull(ptWageHistoryItemOid)) {
			hBuffer.append(" and pt.ptWageHistoryItemOid ="
					+ ptWageHistoryItemOid);
		}
		return BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()),
				PtWageHistoryLogicDTO.class);
	}

	/**
	 * 根据wagehistoryItemOid、logicCode获取工资逻辑表详细信息
	 * 
	 * @param
	 * @return
	 * @throws ServiceException
	 */
	public static PtWageHistoryLogicDTO getPtWHistoryLogicBylogicCode(
			Long ptWageHistoryItemOid, String logicCode)
			throws ServiceException {
		final StringBuffer hBuffer = new StringBuffer(
				"from PtWageHistoryLogic pt  ");
		if (StringUtil.isNotNull(ptWageHistoryItemOid) && logicCode != null) {
			hBuffer.append("  where pt.ptWageHistoryItemOid ="
					+ ptWageHistoryItemOid);
			hBuffer.append(" and pt.logicCode ='" + logicCode + "'");
		}
		@SuppressWarnings("rawtypes")
		List list = DaoUtil.find(hBuffer.toString());
		if (CollectionUtils.isEmpty(list))
			return null;
		return BeanHelper.copyProperties(list.get(0),
				PtWageHistoryLogicDTO.class);
	}
	/**
	 * 根据wagehistoryItemOid、logicCode删除工资逻辑表详细信息
	 * 
	 * @param
	 * @return
	 * @throws ServiceException
	 */
	public static void deletePtWageHistoryLogic(Long ptWageHistoryItemOid,
			String logicCode) throws ServiceException{
		DaoUtil.executeUpdate("delete from PtWageHistoryLogic pb where pb.ptWageHistoryItemOid = " + ptWageHistoryItemOid+"and pb.logicCode = '"+logicCode+"'");
	}
	/**
	 * 根据wagehistoryItemOid、wageItemCode获取工资逻辑表详细信息
	 * 
	 * @param
	 * @return
	 * @throws ServiceException
	 */
	public static List<PtWageHistoryLogicDTO> getPtWHistoryLogicBywageItemCode(
			Long ptWageHistoryItemOid, String wageItemCode)  throws ServiceException{
		final StringBuffer hBuffer = new StringBuffer(
				"from PtWageHistoryLogic pt  ");
		if (StringUtil.isNotNull(ptWageHistoryItemOid) && wageItemCode != null) {
			hBuffer.append("  where pt.ptWageHistoryItemOid ="
					+ ptWageHistoryItemOid);
			hBuffer.append(" and pt.wageItemCode ='" + wageItemCode + "'");
		}
		@SuppressWarnings("rawtypes")
		List list = DaoUtil.find(hBuffer.toString());
		if (CollectionUtils.isEmpty(list))
			return null;
		return BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()),
				PtWageHistoryLogicDTO.class);
	}
	/**
	 * 根据wagehistoryItemOid、wageItemCode修改工资逻辑表详细信息
	 * @param
	 * @return
	 * @throws ServiceException
	 */
	public static void updatelogicValue(Long ptWageHistoryItemOid,
			String logicCode, String logicValue) throws ServiceException{
		DaoUtil.executeUpdate("update PtWageHistoryLogic pb set pb.logicValue = '" +logicValue+ "'where pb.ptWageHistoryItemOid = " + ptWageHistoryItemOid+"and pb.logicCode = '"+logicCode+"'");
		//DaoUtil.executeUpdate("delete from PtWageHistoryLogic pb where pb.ptWageHistoryItemOid = " + ptWageHistoryItemOid+"and pb.logicCode = '"+logicCode+"'");
	}
}
