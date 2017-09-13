package com.yh.hr.res.cf.bo;

import java.util.Date;

import com.yh.platform.core.bo.BaseBo;

public class JhcCfQuerySymbol extends BaseBo {
	private static final long serialVersionUID = 25155342355227706L;
	
	private Long symbolOid;
	private String symbolName;
	private String symbolValue;
	private String createdByCode;
	private String createdByName;
	private Date createdDate;
	private String updatedByCode;
	private String updatedByName;
	private Date updatedDate;
	public Long getSymbolOid() {
		return symbolOid;
	}
	public void setSymbolOid(Long symbolOid) {
		this.symbolOid = symbolOid;
	}
	public String getSymbolName() {
		return symbolName;
	}
	public void setSymbolName(String symbolName) {
		this.symbolName = symbolName;
	}
	public String getSymbolValue() {
		return symbolValue;
	}
	public void setSymbolValue(String symbolValue) {
		this.symbolValue = symbolValue;
	}
	public String getCreatedByCode() {
		return createdByCode;
	}
	public void setCreatedByCode(String createdByCode) {
		this.createdByCode = createdByCode;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdatedByCode() {
		return updatedByCode;
	}
	public void setUpdatedByCode(String updatedByCode) {
		this.updatedByCode = updatedByCode;
	}
	public String getUpdatedByName() {
		return updatedByName;
	}
	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}
