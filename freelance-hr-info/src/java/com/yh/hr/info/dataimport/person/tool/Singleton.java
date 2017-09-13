package com.yh.hr.info.dataimport.person.tool;

public class Singleton {
   private static Singleton instance;
   private String checkRelationNum;
   private String checkDicCount;
   private String checkDicNum;
   private String checkCompleteNum;
   private String checkDataMust;
   private String checkDataFormat;
   private String checjDataLength;
   private String nowCheckDataNum;
   private String rowNum; 
   private String rowDicNum;
   private String exportPerson;
   private String nowImportPersonNum;
   private String message;
   private String unitOid;
   
   public String getUnitOid() {
	return unitOid;
}

public void setUnitOid(String unitOid) {
	this.unitOid = unitOid;
}

public String getMessage() {
	return message;
}

public synchronized void setMessage(String message) {
	this.message = message;
}

public String getNowImportPersonNum() {
	return nowImportPersonNum;
}

public synchronized void setNowImportPersonNum(String nowImportPersonNum) {
	this.nowImportPersonNum = nowImportPersonNum;
}

public String getExportPerson() {
	return exportPerson;
}

public synchronized void setExportPerson(String exportPerson) {
	this.exportPerson = exportPerson;
}

public String getRowDicNum() {
	return rowDicNum;
}

public synchronized void setRowDicNum(String rowDicNum) {
	this.rowDicNum = rowDicNum;
}

public String getCheckDicCount() {
	return checkDicCount;
}

public  synchronized void setCheckDicCount(String checkDicCount) {
	this.checkDicCount = checkDicCount;
}

public String getCheckDicNum() {
	return checkDicNum;
}

public synchronized void setCheckDicNum(String checkDicNum) {
	this.checkDicNum = checkDicNum;
}

public String getCheckCompleteNum() {
	return checkCompleteNum;
}

public synchronized void setCheckCompleteNum(String checkCompleteNum) {
	this.checkCompleteNum = checkCompleteNum;
}

public String getCheckDataMust() {
	return checkDataMust;
}

public synchronized void setCheckDataMust(String checkDataMust) {
	this.checkDataMust = checkDataMust;
}

public String getCheckDataFormat() {
	return checkDataFormat;
}

public synchronized void setCheckDataFormat(String checkDataFormat) {
	this.checkDataFormat = checkDataFormat;
}

public String getChecjDataLength() {
	return checjDataLength;
}

public synchronized void setChecjDataLength(String checjDataLength) {
	this.checjDataLength = checjDataLength;
}

public String getNowCheckDataNum() {
	return nowCheckDataNum;
}

public  synchronized void setNowCheckDataNum(String nowCheckDataNum) {
	this.nowCheckDataNum = nowCheckDataNum;
}

public String getRowNum() {
	return rowNum;
}

public synchronized void setRowNum(String rowNum) {
	this.rowNum = rowNum;
}

public String getCheckRelationNum() {
	return checkRelationNum;
}

public synchronized void setCheckRelationNum(String checkRelationNum) {
	this.checkRelationNum = checkRelationNum;
}

private Singleton(){}
   
   public static   Singleton getInstance(){
	   if(instance==null){
		   instance = new Singleton();
	   }
	return instance;
   }
}
