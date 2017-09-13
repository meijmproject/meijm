/**
 * 
 */
package com.yh.admin.bo;

import com.yh.platform.core.bo.BaseBo;

/**
 * 
 * @author	zhangqp
 * @version	1.0,	16/08/23
 */
public class FunctionResource extends BaseBo {
	private static final long serialVersionUID = -5503389004092502116L;
	
	private Long	funcId;
	private Long	resId;
	
	public Long getFuncId() {
		return funcId;
	}
	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}
	public Long getResId() {
		return resId;
	}
	public void setResId(Long resId) {
		this.resId = resId;
	}
}
