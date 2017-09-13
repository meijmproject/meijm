package com.yh.hr.info.ver.unit.comm.dto;


public class VerPbReviewInfoDTO {

	/**
     *主键
     **/
	private java.lang.Long reviewOid;
    /**
     *PersonOid
     **/
	private java.lang.Long personOid;
    /**
     *考核类别YHRS0069
     **/
	private java.lang.String reviewTypeCode;
    /**
     *是否参加考核YHRS0003
     **/
	private java.lang.String isReview;
    /**
     *考核年度
     **/
	private java.util.Date reviewYear;
	
	//考核年度日期
	private int reviewYearInt;
	
	//考核年度日期Str
	private String reviewYearStr;
	
    /**
     *考核起始日期
     **/
	private java.util.Date reviewDate;
	
	//考核起始日期日期转换
	private String reviewDateStr;
	
    /**
     *考核终止日期
     **/
	private java.util.Date reviewendDate;
	
	//考核终止日期日期转换
	private String reviewendDateStr;

	public java.util.Date getReviewendDate() {
		return reviewendDate;
	}

	public void setReviewendDate(java.util.Date reviewendDate) {
		this.reviewendDate = reviewendDate;
	}

	public String getReviewendDateStr() {
		return reviewendDateStr;
	}

	public void setReviewendDateStr(String reviewendDateStr) {
		this.reviewendDateStr = reviewendDateStr;
	}
	/**
     *考核结论类别YHRS0070
     **/
	private java.lang.String reviewResultType;
    /**
     *未参加考核原因YHRS0071
     **/
	private java.lang.String notReviewReason;
    /**
     *参加考核不定等次原因YHRS0072
     **/
	private java.lang.String reviewReason;
    /**
     *年度考核基本称职或不称职原因
     **/
	private java.lang.String isReviewResult;
    /**
     *考核结论类别描述
     **/
	private java.lang.String reviewTypeDesc;
    /**
     *是否因特殊原因不晋升标识YHRS0003
     **/
	private java.lang.String isPromote;
    /**
     *不晋升原因YHRS0073
     **/
	private java.lang.String noPromoteReason;
  
	
	/**
	 * 近三年年度考核结果
	 */
	private java.lang.String khjg;

	public java.lang.String getKhjg() {
		return khjg;
	}

	public void setKhjg(java.lang.String khjg) {
		this.khjg = khjg;
	}
	
	public int getReviewYearInt() {
		return reviewYearInt;
	}
	
	public void setReviewYearInt(int reviewYearInt) {
		this.reviewYearInt = reviewYearInt;
	}
	
	public String getReviewDateStr() {
		return reviewDateStr;
	}
	
	public void setReviewDateStr(String reviewDateStr) {
		this.reviewDateStr = reviewDateStr;
	}
	
/**
* public JhcPbReviewInfo(java.lang.Long reviewOid) {
*  *       this.reviewOid = reviewOid;
**   }
**/
	public void setReviewOid(java.lang.Long reviewOid){
		this.reviewOid = reviewOid;
	}

	public java.lang.Long getReviewOid(){
		return this.reviewOid;
	}

	public void setPersonOid(java.lang.Long personOid){
		this.personOid = personOid;
	}

	public java.lang.Long getPersonOid(){
		return this.personOid;
	}

	public void setReviewTypeCode(java.lang.String reviewTypeCode){
		this.reviewTypeCode = reviewTypeCode;
	}

	public java.lang.String getReviewTypeCode(){
		return this.reviewTypeCode;
	}

	public void setIsReview(java.lang.String isReview){
		this.isReview = isReview;
	}

	public java.lang.String getIsReview(){
		return this.isReview;
	}

	public void setReviewYear(java.util.Date reviewYear){
		this.reviewYear = reviewYear;
	}

	public java.util.Date getReviewYear(){
		return this.reviewYear;
	}

	public void setReviewDate(java.util.Date reviewDate){
		this.reviewDate = reviewDate;
	}

	public java.util.Date getReviewDate(){
		return this.reviewDate;
	}

	public void setReviewResultType(java.lang.String reviewResultType){
		this.reviewResultType = reviewResultType;
	}

	public java.lang.String getReviewResultType(){
		return this.reviewResultType;
	}

	public void setNotReviewReason(java.lang.String notReviewReason){
		this.notReviewReason = notReviewReason;
	}

	public java.lang.String getNotReviewReason(){
		return this.notReviewReason;
	}

	public void setReviewReason(java.lang.String reviewReason){
		this.reviewReason = reviewReason;
	}

	public java.lang.String getReviewReason(){
		return this.reviewReason;
	}

	public void setIsReviewResult(java.lang.String isReviewResult){
		this.isReviewResult = isReviewResult;
	}

	public java.lang.String getIsReviewResult(){
		return this.isReviewResult;
	}

	public void setReviewTypeDesc(java.lang.String reviewTypeDesc){
		this.reviewTypeDesc = reviewTypeDesc;
	}

	public java.lang.String getReviewTypeDesc(){
		return this.reviewTypeDesc;
	}

	public void setIsPromote(java.lang.String isPromote){
		this.isPromote = isPromote;
	}

	public java.lang.String getIsPromote(){
		return this.isPromote;
	}

	public void setNoPromoteReason(java.lang.String noPromoteReason){
		this.noPromoteReason = noPromoteReason;
	}

	public java.lang.String getNoPromoteReason(){
		return this.noPromoteReason;
	}


	public void setReviewYearStr(String reviewYearStr) {
		this.reviewYearStr = reviewYearStr;
		
	}
	public String getReviewYearStr(){
		return this.reviewYearStr;
	}
	
}
