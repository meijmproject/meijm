package com.yh.hr.res.sao.users.dto;
/**
 * @description 账号信息DTO(freelance-hr-res模块使用)
 * @author	wangx
 * @date 2017-05-22
 * @version	1.0
 */
public class SaoUsersDTO {
	private java.lang.Long		userOid;			// 主键
	private java.lang.String	userId;				// 用户登陆ID
	private java.lang.String	password;			// 登陆密码
	private java.lang.String	userName;			// 用户姓名
	private java.lang.String	userSex;			// 性别
	private java.lang.String	address;			// 用户地址
	private java.lang.String	contactPhone;		// 联系电话
	private java.util.Date		registDate;			// 注册日期
	private java.util.Date		effectiveDate;		// 生效日期
	private java.util.Date		expiredDate;		// 失效日期
	private java.lang.String	userType;			// 用户类型
	private java.lang.String	userStatus;			// 用户状态
	private java.lang.Long		unitId;				// 所属单位OID
	private java.lang.String	unitName;			// 所属单位名称
	private java.lang.Long		deptId;				// 所属部门OID
	private java.lang.String	deptName;			// 所属部门名称
	private java.lang.String	updatedByCode;		// 修改人代码
	private java.lang.String	updatedByName;		// 修改人姓名
	private java.util.Date		updatedDate;		// 修改时间
	private java.lang.String	defaultLoginSystem;	// 默认登录系统
	
	private Long systemPositionOid;                 //用户岗位信息
	
	private Long personOid;							//基础人员OID

	public SaoUsersDTO() {
		
	}

	public void setUserOid(java.lang.Long userOid){
		this.userOid = userOid;
	}

	public java.lang.Long getUserOid(){
		return this.userOid;
	}

	public void setUserId(java.lang.String userId){
		this.userId = userId;
	}

	public java.lang.String getUserId(){
		return this.userId;
	}

	public void setPassword(java.lang.String password){
		this.password = password;
	}

	public java.lang.String getPassword(){
		return this.password;
	}

	public void setUserName(java.lang.String userName){
		this.userName = userName;
	}

	public java.lang.String getUserName(){
		return this.userName;
	}

	public void setUserSex(java.lang.String userSex){
		this.userSex = userSex;
	}

	public java.lang.String getUserSex(){
		return this.userSex;
	}

	public void setAddress(java.lang.String address){
		this.address = address;
	}

	public java.lang.String getAddress(){
		return this.address;
	}

	public void setContactPhone(java.lang.String contactPhone){
		this.contactPhone = contactPhone;
	}

	public java.lang.String getContactPhone(){
		return this.contactPhone;
	}

	public void setRegistDate(java.util.Date registDate){
		this.registDate = registDate;
	}

	public java.util.Date getRegistDate(){
		return this.registDate;
	}


	public void setExpiredDate(java.util.Date expiredDate){
		this.expiredDate = expiredDate;
	}

	public java.util.Date getExpiredDate(){
		return this.expiredDate;
	}

	public void setUserType(java.lang.String userType){
		this.userType = userType;
	}

	public java.lang.String getUserType(){
		return this.userType;
	}

	public void setUserStatus(java.lang.String userStatus){
		this.userStatus = userStatus;
	}

	public java.lang.String getUserStatus(){
		return this.userStatus;
	}

	public void setUnitId(java.lang.Long unitId){
		this.unitId = unitId;
	}

	public java.lang.Long getUnitId(){
		return this.unitId;
	}

	public void setUnitName(java.lang.String unitName){
		this.unitName = unitName;
	}

	public java.lang.String getUnitName(){
		return this.unitName;
	}

	public void setDeptId(java.lang.Long deptId){
		this.deptId = deptId;
	}

	public java.lang.Long getDeptId(){
		return this.deptId;
	}

	public void setDeptName(java.lang.String deptName){
		this.deptName = deptName;
	}

	public java.lang.String getDeptName(){
		return this.deptName;
	}

	public void setUpdatedByCode(java.lang.String updatedByCode){
		this.updatedByCode = updatedByCode;
	}

	public java.lang.String getUpdatedByCode(){
		return this.updatedByCode;
	}

	public void setUpdatedByName(java.lang.String updatedByName){
		this.updatedByName = updatedByName;
	}

	public java.lang.String getUpdatedByName(){
		return this.updatedByName;
	}

	public void setUpdatedDate(java.util.Date updatedDate){
		this.updatedDate = updatedDate;
	}

	public java.util.Date getUpdatedDate(){
		return this.updatedDate;
	}

	public void setDefaultLoginSystem(java.lang.String defaultLoginSystem){
		this.defaultLoginSystem = defaultLoginSystem;
	}

	public java.lang.String getDefaultLoginSystem(){
		return this.defaultLoginSystem;
	}

	public java.util.Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(java.util.Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Long getSystemPositionOid() {
		return systemPositionOid;
	}

	public void setSystemPositionOid(Long systemPositionOid) {
		this.systemPositionOid = systemPositionOid;
	}

	public Long getPersonOid() {
		return personOid;
	}

	public void setPersonOid(Long personOid) {
		this.personOid = personOid;
	}
}
