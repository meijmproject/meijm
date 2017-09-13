package com.yh.component.flow.dto;

/**
 *	流程流转信息 - DTO	
 *	@author		liuhw
 *	@created	2016-08-25
 */
public class JadeWorkflowDTO
{
	private Long	flowInstanceOid;	// 流程实例ID
	private String	flowNode;	// 环节名称
	private	Long	flowNodeOid;	// 流程环节OID
	
	
	public Long getFlowInstanceOid() {
		return flowInstanceOid;
	}
	public void setFlowInstanceOid(Long flowInstanceOid) {
		this.flowInstanceOid = flowInstanceOid;
	}
	public String getFlowNode() {
		return flowNode;
	}
	public void setFlowNode(String flowNode) {
		this.flowNode = flowNode;
	}
	public Long getFlowNodeOid()
	{
		return flowNodeOid;
	}
	public void setFlowNodeOid(Long flowNodeOid)
	{
		this.flowNodeOid = flowNodeOid;
	}
}
