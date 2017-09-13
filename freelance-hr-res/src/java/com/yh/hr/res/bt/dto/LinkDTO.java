package com.yh.hr.res.bt.dto;

import java.util.Date;

/**
 * 业务环节dto
 * @author liuhw
 * 2016-8-29
 */ 

public class LinkDTO extends FlowDTO
{
	private Long bizTaskOid;
	private String opinion;//审批意见
	private Date auditDate;//审批时间
	private String auditResult;//审批结果
	private String defFlowExpressName;//自定义流程表达式名称（区分是上报还是审核）
	private String defFlowExpress;//自定义流程表达式值（区分是同意还是退回等）
	
	
	public Long getBizTaskOid() {
		return bizTaskOid;
	}

	public void setBizTaskOid(Long bizTaskOid) {
		this.bizTaskOid = bizTaskOid;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}
	
	public String getDefFlowExpressName() {
		return defFlowExpressName;
	}

	public void setDefFlowExpressName(String defFlowExpressName) {
		this.defFlowExpressName = defFlowExpressName;
	}

	public String getDefFlowExpress() 
	{
		return defFlowExpress;
	}

	public void setDefFlowExpress(String defFlowExpress) 
	{
		this.defFlowExpress = defFlowExpress;
	}
	
}
