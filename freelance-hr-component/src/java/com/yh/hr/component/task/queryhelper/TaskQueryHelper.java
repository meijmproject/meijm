package com.yh.hr.component.task.queryhelper;

import java.util.List;

import com.yh.hr.component.task.dto.BtFlowNodeDTO;
import org.apache.commons.collections.CollectionUtils;

import com.yh.hr.component.bt.dto.BtItemDto;
import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;

/**
 * 业务流程配置查询服务类
 * @author liuhw
 * 2016-8-29
 */
public class TaskQueryHelper 
{
	/**
	 * 根据流程节点查询配置表对应的业务环节
	 * @throws DataAccessFailureException
	 */
	public static BtFlowNodeDTO findTaskItemByFlowNode(Long flowInstanceOid, String flowNodeCode)throws DataAccessFailureException
	{
		StringBuffer querySQL = new StringBuffer();
		
		querySQL.append(" select ");
		querySQL.append("  bf.flow_node_code,");
		querySQL.append("  bf.flow_node_name");
		querySQL.append("   from   YHD_BT_WORKFLOW_REF bi,");
		querySQL.append("      YHD_BT_FLOW_NODE      bf");
		querySQL.append("      ,yhc_bt_task bt ");
		querySQL.append(" where bt.flow_instance_oid = ").append(flowInstanceOid);
		querySQL.append(" and   bi.workflow_node_code = '").append(flowNodeCode).append("'");
		querySQL.append(" and bi.flow_node_code = bf.flow_node_code");
		querySQL.append(" and bi.item_code = bt.item_code");
		
		List<Object[]> list = DaoUtil.findWithSQL(querySQL.toString());
		
		if(CollectionUtils.isEmpty(list))
		{
			return null;
		}
		else
		{
			Object[] obj = list.get(0);
			
			BtFlowNodeDTO dto = new BtFlowNodeDTO();
			dto.setFlowNodeCode(obj[0] == null ? null : obj[0].toString());
			dto.setFlowNodeName( obj[1] == null ? null : obj[1].toString());
			
			return dto;
		}
	}
	
	/**
	 * 根据itemCode找事项
	 * @throws DataAccessFailureException
	 */
	public static BtItemDto findBtItemByCode(String itemCode)throws DataAccessFailureException
	{
		StringBuffer querySQL = new StringBuffer();
		querySQL.append("select ");
		querySQL.append(" jbi.ITEM_CODE,");
		querySQL.append(" jbi.FLOW_CODE,");
		querySQL.append(" jbi.ITEM_NAME,");
		querySQL.append(" jbi.IS_ACTIVE");
		querySQL.append(" from YHD_BT_ITEM jbi where jbi.item_code='").append(itemCode).append("'");
		
		List<Object[]> list = DaoUtil.findWithSQL(querySQL.toString());
		
		if(CollectionUtils.isEmpty(list))
		{
			return null;
		}
		else
		{
			Object[] obj = list.get(0);
			
			BtItemDto dto = new BtItemDto();
			dto.setItemCode(obj[0] == null ? null : obj[0].toString());
			dto.setFlowCode( obj[1] == null ? null : obj[1].toString());
			dto.setITEM_NAME( obj[1] == null ? null : obj[2].toString());
			dto.setIsActive( obj[1] == null ? null : obj[3].toString());
			return dto;
		}
		
	}
}




