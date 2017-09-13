package com.yh.component.flow.queryhelper;


import com.yh.component.flow.dto.JadeWorkflowDTO;
import com.yh.component.flow.utils.JadeFlowConstants;
import jade.workflow.engine.bo.FlowInstance;
import jade.workflow.engine.bo.FlowNode;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yh.platform.core.dao.DaoUtil;
import com.yh.platform.core.exception.DataAccessFailureException;

/**
 * 查询待办事项服务类
 * @author liuhw
 * 2016-8-25
 */
public class JadeFlowNodeInfoQueryHelper {
	
	/**
	 *	根据流程实例OID查询流程流转信息
	 *	@param 	flowInstanceOid		业务OID
	 *	@return	JadeWorkflowDTO	流程流转信息
	 * @throws DataAccessFailureException 
	 */
	public static JadeWorkflowDTO findDTOByFlowInstanceOid(Long flowInstanceOid)throws DataAccessFailureException
	{
		StringBuffer querySQL = new StringBuffer();
		
		querySQL.append(" select fn.flow_node_oid,");
		querySQL.append("        fn.node_code,");
		querySQL.append("        fn.flow_instance_oid");
		querySQL.append(" from flow_node          fn");
		querySQL.append(" where 1=1");
		querySQL.append(" and   fn.node_type = 'action'");
		querySQL.append(" and   fn.node_status = '" + JadeFlowConstants.QUERY_TYPE_1 + "'");
		querySQL.append(" and   fn.flow_instance_oid = " + flowInstanceOid);
		
		List<Object[]> list = DaoUtil.findWithSQL(querySQL.toString());
		
		if(CollectionUtils.isEmpty(list))
		{
			return null;
		}
		else
		{
			Object[] obj = list.get(0);
			
			JadeWorkflowDTO dto = new JadeWorkflowDTO();
			dto.setFlowNodeOid(obj[0] == null ? null:Long.valueOf(obj[0].toString()));
			dto.setFlowNode(obj[1] == null ? null : obj[1].toString());
			dto.setFlowInstanceOid(obj[2] == null ? null:Long.valueOf(obj[2].toString()));
			return dto;
		}
	}
	
	/**
	 * 根据参数查询工作流节点信息
	 * @param flowInstanceOid
	 * @return
	 * @throws DataAccessFailureException 
	 */
	public static FlowNode getFlowNodeByFlowInstanceOid(Long flowInstanceOid)throws DataAccessFailureException
	{
		List<FlowNode> list = DaoUtil.findByNamed("select fn from FlowNode fn where fn.flowInstanceOid =:flowInstanceOid and fn.nodeStatus =:status ", new String[]{"flowInstanceOid","status"}, new Object[]{flowInstanceOid,JadeFlowConstants.QUERY_TYPE_1});
		if(CollectionUtils.isEmpty(list))return null;
		return list.get(0);
	}
	/**
	 * 流程实例信息
	 * @param flowInstanceOid
	 * @return
	 * @throws DataAccessFailureException
	 */
	public static FlowInstance getFlowInstanceByFlowInstanceOid(Long flowInstanceOid)throws DataAccessFailureException
	{
		return DaoUtil.get(FlowInstance.class, flowInstanceOid);
	}
}
