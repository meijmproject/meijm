package com.yh.hr.info.dataimport.person.web.form;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;

public class UploadPersonForm extends ValidatorForm {

	private static final long serialVersionUID = -1860776252489221149L;
	
	private FormFile uploadFile;
	private String   unitOid;
	private String   orgOid;
	public FormFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getUnitOid() {
		return unitOid;
	}
	public void setUnitOid(String unitOid) {
		this.unitOid = unitOid;
	}
	public String getOrgOid() {
		return orgOid;
	}
	public void setOrgOid(String orgOid) {
		this.orgOid = orgOid;
	}
}
