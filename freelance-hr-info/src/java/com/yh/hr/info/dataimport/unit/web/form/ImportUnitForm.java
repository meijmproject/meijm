package com.yh.hr.info.dataimport.unit.web.form;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;
/**
 * 单位导入form
 * @author chenjl
 * @date 2017-03-23
 * @version 1.0
 */
public class ImportUnitForm extends ValidatorForm{

	private static final long serialVersionUID = -6382979656674972364L;
	
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
