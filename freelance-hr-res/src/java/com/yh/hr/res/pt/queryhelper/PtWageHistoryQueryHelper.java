package com.yh.hr.res.pt.queryhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.yh.hr.res.dictionary.DicConstants;
import com.yh.hr.res.pt.dto.PtWageHistoryDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.ServiceException;
import com.yh.platform.core.util.BeanHelper;
import com.yh.platform.core.util.StringUtil;

/**
 * @desc 工资历史信息查询工具类
 * 
 */
public class PtWageHistoryQueryHelper {
	/**
	 * 根据ptWageHistoryOid查询人员工资历史
	 * @param ptWageHistoryOid 
	 * @return List<PtWageHistoryDTO> 业务工资历史列表
	 * @throws ServiceException
	 */
	public static List<PtWageHistoryDTO> listWageHistoryStruts(Long ptWageHistoryOid) throws ServiceException{
		final StringBuffer hBuffer =  new StringBuffer("from PtWageHistory pt where  1 =1  ");
		if(StringUtil.isNotNull(ptWageHistoryOid)){
			hBuffer.append(" and  pt.calWageHistoryOid =" +ptWageHistoryOid);
		}
			//hBuffer.append(" order by pb.reviewYear desc");
		return BeanHelper.copyProperties(DaoUtil.find(hBuffer.toString()), PtWageHistoryDTO.class);
	}

/*	public static PtWageHistoryDTO getPtWageHistory(Long bizPersonOid) throws ServiceException {
		
		PtWageHistory ptWageHistory =DaoUtil.get(PtWageHistory.class, bizPersonOid);
		return BeanHelper.copyProperties(ptWageHistory, PtWageHistoryDTO.class);
	}*/
	
	/**
	 * 根据业务人员ID、生效标识查询人员工资历史
	 * @param bizPersonOid 业务人员ID
	 * @param effectFlag 生效标识
	 * @return List<PtWageHistoryDTO> 业务工资历史列表
	 * @throws ServiceException
	 */
	public static List<PtWageHistoryDTO> getPtWageHistoryList(Long bizPersonOid,String effectFlag) throws ServiceException 
	{
		final StringBuffer hql = new StringBuffer();
		final HashMap<String, Object> hqlParams = new HashMap<String, Object>();
		hql.append("from PtWageHistory wh");
		hql.append(" where 1=1 ");
		if(null != bizPersonOid)
		{
			hql.append(" and wh.bizPersonOid = :bizPersonOid");
			hqlParams.put("bizPersonOid", bizPersonOid);
		}
		if(StringUtil.isNotBlank(effectFlag))
		{
			hql.append(" and wh.effectiveFlag = :effectFlag");
			hqlParams.put("effectFlag", effectFlag);
		}
		hql.append(" order by wh.startDateOfWage  asc,wh.orderId  asc");
		return BeanHelper.copyProperties(DaoUtil.find(hql.toString(), hqlParams), PtWageHistoryDTO.class);
	}
	/**
	 * 根据计算后的工资历史oid查询该条数据有没有修正值
	 * @param ptWageHistoryOid  业务工资历史oid
	 * @return List<PtWageHistoryDTO>
	 * @throws ServiceException
	 */
	public static  List<PtWageHistoryDTO> getModifyPtWageHistory(Long calWageHistoryOid) throws ServiceException{
		final StringBuffer hql = new StringBuffer();
		final HashMap<String, Object> hqlParams = new HashMap<String, Object>();
		hql.append("from PtWageHistory wh where 1=1");
		hql.append(" and wh.effectiveFlag = "+DicConstants.YHRS1000_4);
		if(null != calWageHistoryOid)
		{
			hql.append(" and wh.calWageHistoryOid ="+calWageHistoryOid);
			//hqlParams.put("calWageHistoryOid", calWageHistoryOid);
		}
		hql.append(" order by wh.startDateOfWage asc,wh.orderId asc");
		return BeanHelper.copyProperties(DaoUtil.find(hql.toString(), hqlParams), PtWageHistoryDTO.class);
	}
	public static void removePtWageHistory(Long bizPersonOid,String effectiveFlag) throws ServiceException
	{
		//1、删除逻辑关系
				
		StringBuffer logicDel = new StringBuffer();
		logicDel.append("delete PtWageHistoryLogic l where l.ptWageHistoryItemOid in ");
		logicDel.append("(select pt.ptWageHistoryItemOid from PtWageHistoryItems pt,PtWageHistory t ");
		logicDel.append(" where pt.ptWageHistoryOid = t.ptWageHistoryOid and t.bizPersonOid = "+bizPersonOid);
		logicDel.append(" and t.effectiveFlag = "+effectiveFlag+"))");
		DaoUtil.executeUpdate(logicDel.toString());
		/*List<PtWageHistoryLogic> list = DaoUtil.find(logicDel.toString());
		if(CollectionUtils.isNotEmpty(list))
		{
			for (PtWageHistoryLogic ptWageHistoryLogic : list) {
				ptWageHistoryLogic.delete();
			}
		}*/
		
		//2、删除工资项
		StringBuffer itemDel = new StringBuffer();
		itemDel.append("delete PtWageHistoryItems pt where pt.ptWageHistoryOid in ");
		itemDel.append("(select t.ptWageHistoryOid from PtWageHistory t where t.bizPersonOid = "+bizPersonOid);
		itemDel.append(" and t.effectiveFlag = '"+effectiveFlag+"')");
		DaoUtil.executeUpdate(itemDel.toString());
		
		//3、删除工资历史信息
		StringBuffer historyDel = new StringBuffer();
		historyDel.append("delete from PtWageHistory pt where  pt.bizPersonOid = "+bizPersonOid+" and pt.effectiveFlag = '" + effectiveFlag+"'");
		DaoUtil.executeUpdate(historyDel.toString());
	}
	
	/**
	 * 获取查询总数
	 * @param bizPersonOid
	 * @return
	 * @throws ServiceException
	 */
	public static int countPtWageHistoryByBizPersonOid(Long bizPersonOid) throws ServiceException
	{
		String hql = "select count(*) from PtWageHistory ei where ei.bizPersonOid = :bizPersonOid";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizPersonOid", bizPersonOid);
		return DaoUtil.countByCondition(hql, params);
	}
	/**根据changeType(变动类型)、startDateOfWage(起薪时间)
	 * 查询工资历史详细信息
	 * @return
	 * @throws ServiceException
	 * */
	public static PtWageHistoryDTO getPtWageHistoryDTOByChangeType(Long bizPersonOid, String changeType, String startDateOfWageStr, String effectiveFlag) throws ServiceException{
		final StringBuffer hql = new StringBuffer();
		final HashMap<String, Object> hqlParams = new HashMap<String, Object>();
		hql.append("from PtWageHistory pt where 1=1");
		hql.append(" and pt.effectiveFlag = "+effectiveFlag);
		hql.append(" and pt.startDateOfWage = "+"to_date"+"('"+startDateOfWageStr+"','"+"yyyy-mm-dd"+"')");
		if(null != bizPersonOid)
		{
			hql.append(" and pt.bizPersonOid ="+bizPersonOid);
		}
		if(StringUtils.isNotBlank(changeType)){
			hql.append(" and  pt.changeType =" +changeType);
		}
		hql.append(" order by pt.startDateOfWage asc,pt.orderId asc");
		@SuppressWarnings("rawtypes")
		List list = DaoUtil.find(hql.toString(), hqlParams);
		if(!CollectionUtils.isEmpty(list)){
			return BeanHelper.copyProperties(list.get(0), PtWageHistoryDTO.class);
		}
		return null;
	}
}
