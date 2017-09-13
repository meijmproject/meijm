package com.yh.hr.component.task.dto;
/**
 * 待办流程节点配置表
 * @author liuhw
 * 2016-8-29
 */
public class BtFlowNodeDTO 
{
	private String flowNodeCode;
	private String flowNodeName;
	public String getFlowNodeCode() {
		return flowNodeCode;
	}
	public void setFlowNodeCode(String flowNodeCode) {
		this.flowNodeCode = flowNodeCode;
	}
	public String getFlowNodeName() {
		return flowNodeName;
	}
	public void setFlowNodeName(String flowNodeName) {
		this.flowNodeName = flowNodeName;
	}
}
