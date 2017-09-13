package com.yh.hr.component.worktop.queryhelper;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;

import com.yh.hr.component.worktop.dto.WbWorkBenchForwardDTO;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;

/**
 * 事项树跳转工作台  queryhelper
 * @author huw
 * @time 2016-09-29
 */
public class WbWorkBenchQueryHelper 
{
	/**
	 * 查询工作台模版地址
	 * @param 	itemNodeCode	业务事项环节
	 * @return  templateUrl		模版地址
	 * @throws DataAccessFailureException 
	 * @throws ServiceException
	 */
	public static String getItemNodeCode(String menuCode) throws DataAccessFailureException
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" select jmn.FLOW_NODE_CODE from YHD_MT_NODE jmn where jmn.MENU_CODE ='"+menuCode+"'");
		List<String> ls = DaoUtil.findWithSQL(sql.toString());
		if(CollectionUtils.isNotEmpty(ls)){
			return ls.get(0);
		}
		return null;
	}
	
	/**
	 * 根据事项节点码查询工作台
	 * @param itemNodeCode
	 * @return BizWorkTopDTO
	 * @throws DataAccessException
	 * @throws DataAccessFailureException 
	 */
	public static WbWorkBenchForwardDTO findBizWorkTopDTOByNodeCode(String itemNodeCode) throws DataAccessFailureException
	{
		StringBuffer hql = new StringBuffer();
		hql.append(" select  jww.WORKBENCH_OID," +
							" jww.PANEL_URL," +
							" jww.TBAR_URL," +
							" jwwd.WORKBENCH_FORM," +
							" jwwd.WORKBENCH_GRID," +
							" jwwd.WORKBENCH_URL" +
					" from   YHD_WB_WORKBENCH jww," +
							"YHD_WB_WORKBENCH_DETAIL jwwd," +
							"YHD_WB_WORKBENCH_REF jwwr " +
					" where 1 = 1 " +
					"  and  jww.WORKBENCH_OID = jwwd.WORKBENCH_OID" +
					"  and  jwwd.DETAIL_OID = jwwr.DETAIL_OID");
		if(StringUtils.isNotEmpty(itemNodeCode))
		{
			hql.append(" and jwwr.REF_CODE = " + itemNodeCode);
		}
		List<Object[]> list = DaoUtil.findWithSQL(hql.toString());
		if(CollectionUtils.isNotEmpty(list))
		{
			Object[]obj = list.get(0);
			Long workBenchOid = obj[0] == null ? null : new Long(obj[0].toString());
			String workBenchPanel = obj[1] == null ? null : obj[1].toString();
			String workBenchTbar = obj[2] == null ? null : obj[2].toString();
			String workBenchForm = obj[3] == null ? null : obj[3].toString();
			String workBenchGrid = obj[4] == null ? null : obj[4].toString();
			String workBenchUrl = obj[5] == null ? null : obj[5].toString();
			
			WbWorkBenchForwardDTO dto = new WbWorkBenchForwardDTO();
			dto.setWorkBenchOid(workBenchOid);
			dto.setWorkBenchPanel(workBenchPanel);
			dto.setWorkBenchTbar(workBenchTbar);
			dto.setWorkBenchForm(workBenchForm);
			dto.setWorkBenchGrid(workBenchGrid);
			dto.setWorkBenchUrl(workBenchUrl);
			
			return dto;
		}
		return null;
	}
}
