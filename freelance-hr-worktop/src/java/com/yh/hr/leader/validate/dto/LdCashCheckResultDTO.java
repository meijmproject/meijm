/**
 * 
 */
package com.yh.hr.leader.validate.dto;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/10/31
 */

public class LdCashCheckResultDTO extends LdCashCheckDTO {
	
	private Long	freeCount = 0L;
//	private String	ruleCode;		//检查规则代码
	private String	ruleDesc;		//检查规则描述
	private String	message;		//检查不通过的错误信息
	private boolean success = true;
	
	public Long getFreeCount() {
		return freeCount;
	}
	public void setFreeCount(Long freeCount) {
		this.freeCount = freeCount;
	}
	public String getRuleDesc() {
		return ruleDesc;
	}
	public void setRuleDesc(String ruleDesc) {
		this.ruleDesc = ruleDesc;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
